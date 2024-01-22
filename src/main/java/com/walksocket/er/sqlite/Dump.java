package com.walksocket.er.sqlite;

import com.google.gson.internal.LinkedTreeMap;
import com.walksocket.antlr4.MariaDBLexer;
import com.walksocket.antlr4.MariaDBParser;
import com.walksocket.er.File;
import com.walksocket.er.Json;
import com.walksocket.er.Log;
import com.walksocket.er.Utils;
import com.walksocket.er.component.main.root.workspace.Sequence;
import com.walksocket.er.component.main.root.workspace.Table;
import com.walksocket.er.config.CfgProject;
import com.walksocket.er.parse.SequenceListener;
import com.walksocket.er.parse.TableListener;
import com.walksocket.er.sqlite.entity.DbDefault;
import com.walksocket.er.sqlite.entity.DbDictColumn;
import com.walksocket.er.sqlite.entity.DbDictColumnType;
import com.walksocket.er.sqlite.entity.DbDictGroup;
import com.walksocket.er.sqlite.entity.DbDictGroupColumn;
import com.walksocket.er.sqlite.entity.DbDictPartition;
import com.walksocket.er.sqlite.entity.DbNote;
import com.walksocket.er.sqlite.entity.DbNoteConnectorSequence;
import com.walksocket.er.sqlite.entity.DbNoteConnectorTable;
import com.walksocket.er.sqlite.entity.DbNoteOption;
import com.walksocket.er.sqlite.entity.DbSequence;
import com.walksocket.er.sqlite.entity.DbSequenceOption;
import com.walksocket.er.sqlite.entity.DbTable;
import com.walksocket.er.sqlite.entity.DbTableCheck;
import com.walksocket.er.sqlite.entity.DbTableColumn;
import com.walksocket.er.sqlite.entity.DbTableForeignKey;
import com.walksocket.er.sqlite.entity.DbTableForeignKeyColumn;
import com.walksocket.er.sqlite.entity.DbTableGroup;
import com.walksocket.er.sqlite.entity.DbTableKey;
import com.walksocket.er.sqlite.entity.DbTableKeyColumn;
import com.walksocket.er.sqlite.entity.DbTableOption;
import com.walksocket.er.sqlite.entity.DbTablePartition;
import com.walksocket.er.sqlite.entity.DbTablePrimaryKey;
import com.walksocket.er.sqlite.entity.DbTablePrimaryKeyColumn;
import com.walksocket.er.sqlite.entity.DbTableUniqueKey;
import com.walksocket.er.sqlite.entity.DbTableUniqueKeyColumn;
import com.walksocket.er.sqlite.tmp.TmpCheck;
import com.walksocket.er.sqlite.tmp.TmpColumn;
import com.walksocket.er.sqlite.tmp.TmpKey;
import com.walksocket.er.sqlite.tmp.TmpPartition;
import com.walksocket.er.sqlite.tmp.TmpSequence;
import com.walksocket.er.sqlite.tmp.TmpTable;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

/**
 * Dump.
 */
public class Dump {

  /**
   * extension.
   */
  public static String EXTENSION = "erjson";

  /**
   * classes.
   */
  private static final List<Class> classes = new ArrayList<>();

  static {
    classes.add(DbDefault.class);
    classes.add(DbDictColumnType.class);
    classes.add(DbDictColumn.class);
    classes.add(DbDictGroup.class);
    classes.add(DbDictGroupColumn.class);
    classes.add(DbDictPartition.class);
    classes.add(DbTable.class);
    classes.add(DbTableOption.class);
    classes.add(DbTableColumn.class);
    classes.add(DbTableGroup.class);
    classes.add(DbTablePartition.class);
    classes.add(DbTablePrimaryKey.class);
    classes.add(DbTablePrimaryKeyColumn.class);
    classes.add(DbTableUniqueKey.class);
    classes.add(DbTableUniqueKeyColumn.class);
    classes.add(DbTableKey.class);
    classes.add(DbTableKeyColumn.class);
    classes.add(DbTableForeignKey.class);
    classes.add(DbTableForeignKeyColumn.class);
    classes.add(DbTableCheck.class);
    classes.add(DbSequence.class);
    classes.add(DbSequenceOption.class);
    classes.add(DbNote.class);
    classes.add(DbNoteOption.class);
    classes.add(DbNoteConnectorTable.class);
    classes.add(DbNoteConnectorSequence.class);
  }

  /**
   * write out.
   *
   * @param cfgProject cfgProject
   * @param path       out path
   * @return if success, true
   */
  public static boolean writeOut(CfgProject cfgProject, String path) {
    Map<String, List<Entity>> data = new LinkedHashMap<>();
    try (var con = new Connection(cfgProject.dbPath)) {
      con.setReadonly(true);

      for (var cls : classes) {
        data.put(cls.getSimpleName(), new ArrayList<>());

        Entity dummy = (Entity) cls.getDeclaredConstructor().newInstance();
        var order = dummy.orderColumns().stream()
            .collect(Collectors.joining(", "));
        var sql = String.format("SELECT * FROM %s ORDER BY %s", cls.getSimpleName(), order);
        var records = con.getRecords(sql);
        for (var record : records) {
          Entity instance = (Entity) cls.getDeclaredConstructor().newInstance();
          instance.bind(record);

          data.get(cls.getSimpleName()).add(instance);
        }
      }
      File.writeString(new FileOutputStream(path), Json.toJsonStringFriendly(data));
      return true;
    } catch (Exception e) {
      Log.error(e);
    }
    return false;
  }

  /**
   * read from.
   *
   * @param cfgProject cfgProject
   * @param path       read path
   * @return if success, true
   */
  public static boolean readFrom(CfgProject cfgProject, String path) {
    try (var con = new Connection(cfgProject.dbPath)) {
      var tmpClasses = new ArrayList<>(classes);
      Collections.reverse(tmpClasses);
      for (var cls : tmpClasses) {
        var sql = String.format("DROP TABLE IF EXISTS %s", cls.getSimpleName());
        con.execute(sql);
      }
      Bucket.createDdl(con);

      con.begin();

      Map<String, ?> data = Json.toObject(File.readString(new FileInputStream(path)), Map.class);
      for (var entry : data.entrySet()) {
        var clsName = entry.getKey();
        var cls = classes.stream()
            .filter(c -> c.getSimpleName().equals(clsName))
            .findFirst()
            .get();
        for (var tm : (List<LinkedTreeMap>) entry.getValue()) {
          var entity = (Entity) cls.getDeclaredConstructor().newInstance();
          Map<String, String> r = new HashMap<>();
          for (var k : tm.keySet()) {
            var v = tm.get(k);
            var nk = String.valueOf(k);
            var nv = String.valueOf(v);
            if (v.getClass() == Double.class) {
              r.put(nk, String.valueOf(Double.valueOf(nv).longValue()));
            } else {
              r.put(nk, nv);
            }
          }
          var list = new ArrayList<Map<String, String>>();
          list.add(r);
          entity.bind(new Record(list));
          var sql = entity.createInsert();
          con.execute(sql);
        }
      }
      con.commit();
      return true;
    } catch (Exception e) {
      Log.error(e);
    }
    return false;
  }

  /**
   * import.
   *
   * @param cfgProject cfgProject
   * @param path       read path
   * @return if success, true
   */
  public static boolean importFromDdl(CfgProject cfgProject, String path) {
    try (var con = new Connection(cfgProject.dbPath)) {
      var tmpClasses = new ArrayList<>(classes);
      Collections.reverse(tmpClasses);
      for (var cls : tmpClasses) {
        var sql = String.format("DROP TABLE IF EXISTS %s", cls.getSimpleName());
        con.execute(sql);
      }
      Bucket.createDdl(con);

      con.begin();

      // sequence
      var maxY = 0;
      var createdSequenceNames = new ArrayList<String>();
      var ddlSequenceList = parseCreateSequence(path);
      for (int i = 0; i < ddlSequenceList.size(); i++) {
        if (createdSequenceNames.size() > Sequence.MAX_POSITIONED) {
          continue;
        }

        var ddl = ddlSequenceList.get(i);

        var stream = CharStreams.fromString(ddl);
        var lexer = new MariaDBLexer(stream);
        var tokens = new CommonTokenStream(lexer);
        var parser = new MariaDBParser(tokens);

        var tmpSequence = new TmpSequence();
        var listener = new SequenceListener(tmpSequence);
        ParseTreeWalker.DEFAULT.walk(listener, parser.root());

        if (createdSequenceNames.contains(tmpSequence.sequenceName)) {
          continue;
        }

        // DbSequence
        var dbSequence = new DbSequence();
        dbSequence.sequenceId = Utils.randomString();
        dbSequence.sequenceName = tmpSequence.sequenceName;
        if (Utils.isNullOrEmpty(dbSequence.sequenceName)) {
          continue;
        }
        if (!Utils.isNullOrEmpty(tmpSequence.startValue)) {
          dbSequence.startValue = tmpSequence.startValue;
        }
        if (!Utils.isNullOrEmpty(tmpSequence.minimumValue)) {
          dbSequence.minimumValue = tmpSequence.minimumValue;
        }
        if (!Utils.isNullOrEmpty(tmpSequence.maximumValue)) {
          dbSequence.maximumValue = tmpSequence.maximumValue;
        }
        if (!Utils.isNullOrEmpty(tmpSequence.incrementValue)) {
          dbSequence.incrementValue = tmpSequence.incrementValue;
        }
        if (!Utils.isNullOrEmpty(tmpSequence.cacheSize)) {
          dbSequence.cacheSize = tmpSequence.cacheSize;
        }
        if (!Utils.isNullOrEmpty(tmpSequence.cycle)) {
          dbSequence.cycle = tmpSequence.cycle;
        }
        con.executeInsert(dbSequence);

        // DbSequenceOption
        var dbSequenceOption = new DbSequenceOption();
        dbSequenceOption.sequenceId = dbSequence.sequenceId;
        int x = i % 40;
        int y = i / 40;
        dbSequenceOption.posX = x * 200 + (x + 1) * 20;
        dbSequenceOption.posY = (y + 1) * 20 + y * 200;
        con.executeInsert(dbSequenceOption);

        maxY = dbSequenceOption.posY + 200;
        createdSequenceNames.add(dbSequence.sequenceName);
      }

      // table
      var seq = 10001;
      var createdTableNames = new ArrayList<String>();
      var ddlTableList = parseCreateTable(path);
      for (int i = 0; i < ddlTableList.size(); i++) {
        if (createdTableNames.size() > Table.MAX_POSITIONED) {
          continue;
        }

        var ddl = ddlTableList.get(i);

        var stream = CharStreams.fromString(ddl);
        var lexer = new MariaDBLexer(stream);
        var tokens = new CommonTokenStream(lexer);
        var parser = new MariaDBParser(tokens);

        var tmpTable = new TmpTable();
        var tmpColumnList = new ArrayList<TmpColumn>();
        var tmpPrimaryKey = new TmpKey();
        var tmpUniqueKeyList = new ArrayList<TmpKey>();
        var tmpKeyList = new ArrayList<TmpKey>();
        var tmpCheckList = new ArrayList<TmpCheck>();
        var tmpPartition = new TmpPartition();
        var listener = new TableListener(tmpTable, tmpColumnList, tmpPrimaryKey, tmpUniqueKeyList,
            tmpKeyList, tmpCheckList, tmpPartition);
        ParseTreeWalker.DEFAULT.walk(listener, parser.root());

        if (createdTableNames.contains(tmpTable.tableName)) {
          continue;
        }

        // DbTable
        var dbTable = new DbTable();
        dbTable.tableId = Utils.randomString();
        dbTable.tableName = tmpTable.tableName;
        if (Utils.isNullOrEmpty(tmpTable.tableName)) {
          continue;
        }
        if (!Utils.isNullOrEmpty(tmpTable.tableComment)) {
          dbTable.tableComment = tmpTable.tableComment;
        }
        if (!Utils.isNullOrEmpty(tmpTable.tableComment)) {
          dbTable.tableComment = tmpTable.tableComment;
        }
        if (!Utils.isNullOrEmpty(tmpTable.engine)) {
          dbTable.engine = tmpTable.engine;
        }
        if (!Utils.isNullOrEmpty(tmpTable.charsetValue)) {
          dbTable.charsetValue = tmpTable.charsetValue;
        }
        if (!Utils.isNullOrEmpty(tmpTable.collateValue)) {
          dbTable.collateValue = tmpTable.collateValue;
        }
        if (!Utils.isNullOrEmpty(tmpTable.autoIncrementValue)) {
          dbTable.autoIncrementValue = tmpTable.autoIncrementValue;
        }
        if (!Utils.isNullOrEmpty(tmpTable.option)) {
          dbTable.option = tmpTable.option;
        }
        con.executeInsert(dbTable);

        // DbTableOption
        var dbTableOption = new DbTableOption();
        dbTableOption.tableId = dbTable.tableId;
        int x = i % 30;
        int y = i / 30;
        dbTableOption.posX = x * 300 + (x + 1) * 20;
        dbTableOption.posY = maxY + (y + 1) * 20 + y * 300;
        con.executeInsert(dbTableOption);

        createdTableNames.add(dbTable.tableName);

        // column
        var ordinalPosition = 1;
        var createdDbDictColumnList = new ArrayList<DbDictColumn>();
        for (var tmpColumn : tmpColumnList) {
          // DbDictColumnType
          var dbDictColumnType = new DbDictColumnType();
          var sql = String.format(
              "SELECT * FROM DbDictColumnType WHERE columnType = '%s'",
              Utils.quote(tmpColumn.columnType));
          var record = con.getRecord(sql);
          if (record != null) {
            dbDictColumnType.bind(record);
          } else {
            dbDictColumnType.dictColumnTypeId = Utils.randomString();
            dbDictColumnType.seq = seq;
            dbDictColumnType.columnType = tmpColumn.columnType;
            con.executeInsert(dbDictColumnType);
            seq++;
          }

          // DbDictColumn
          var dbDictColumn = new DbDictColumn();
          dbDictColumn.columnName = tmpColumn.columnName;
          if (!Utils.isNullOrEmpty(tmpColumn.columnComment)) {
            dbDictColumn.columnComment = tmpColumn.columnComment;
          }
          dbDictColumn.dictColumnTypeId = dbDictColumnType.dictColumnTypeId;
          if (!Utils.isNullOrEmpty(tmpColumn.notNullValue)) {
            dbDictColumn.notNullValue = tmpColumn.notNullValue;
          }
          if (!Utils.isNullOrEmpty(tmpColumn.charsetValue)) {
            dbDictColumn.charsetValue = tmpColumn.charsetValue;
          }
          if (!Utils.isNullOrEmpty(tmpColumn.collateValue)) {
            dbDictColumn.collateValue = tmpColumn.collateValue;
          }
          if (!Utils.isNullOrEmpty(tmpColumn.defaultValue)) {
            dbDictColumn.defaultValue = tmpColumn.defaultValue;
          }
          if (!Utils.isNullOrEmpty(tmpColumn.onUpdate)) {
            dbDictColumn.onUpdate = tmpColumn.onUpdate;
          }
          if (!Utils.isNullOrEmpty(tmpColumn.autoIncrementDefinition)) {
            dbDictColumn.autoIncrementDefinition = tmpColumn.autoIncrementDefinition;
          }
          if (!Utils.isNullOrEmpty(tmpColumn.option)) {
            dbDictColumn.option = tmpColumn.option;
          }
          sql = String.format(
              "SELECT * FROM DbDictColumn WHERE checksum = '%s'",
              Utils.quote(dbDictColumn.createAndGetCheckSum()));
          record = con.getRecord(sql);
          if (record != null) {
            dbDictColumn.bind(record);
          } else {
            dbDictColumn.dictColumnId = Utils.randomString();
            con.executeInsert(dbDictColumn);
          }
          createdDbDictColumnList.add(dbDictColumn);

          // DbTableColumn
          var dbTableColumn = new DbTableColumn();
          dbTableColumn.tableId = dbTable.tableId;
          dbTableColumn.ordinalPosition = ordinalPosition;
          dbTableColumn.dictColumnId = dbDictColumn.dictColumnId;
          con.executeInsert(dbTableColumn);
          ordinalPosition++;
        }

        // partition
        if (!Utils.isNullOrEmpty(tmpPartition.expression)) {
          // DbDictPartition
          var dbDictPartition = new DbDictPartition();
          var sql = String.format(
              "SELECT * FROM DbDictPartition WHERE partitionName = '%s'",
              Utils.quote(tmpPartition.getExpressionHash()));
          var record = con.getRecord(sql);
          if (record != null) {
            dbDictPartition.bind(record);
          } else {
            dbDictPartition.dictPartitionId = Utils.randomString();
            dbDictPartition.partitionName = tmpPartition.getExpressionHash();
            dbDictPartition.expression = tmpPartition.expression;
            con.executeInsert(dbDictPartition);
          }

          // DbTablePartition
          var dbTablePartition = new DbTablePartition();
          dbTablePartition.tableId = dbTable.tableId;
          dbTablePartition.dictPartitionId = dbDictPartition.dictPartitionId;
          con.executeInsert(dbTablePartition);
        }

        // primary key
        var seqPrimaryKey = 1;
        if (!Utils.isNullOrEmpty(tmpPrimaryKey.constraintName)) {
          // DbTablePrimaryKey
          var dbTablePrimaryKey = new DbTablePrimaryKey();
          dbTablePrimaryKey.tableId = dbTable.tableId;
          dbTablePrimaryKey.seq = seqPrimaryKey;
          dbTablePrimaryKey.constraintName = tmpPrimaryKey.constraintName;
          dbTablePrimaryKey.keyName = tmpPrimaryKey.keyName;
          if (!Utils.isNullOrEmpty(tmpPrimaryKey.indexComment)) {
            dbTablePrimaryKey.indexComment = tmpPrimaryKey.indexComment;
          }
          if (!Utils.isNullOrEmpty(tmpPrimaryKey.indexType)) {
            dbTablePrimaryKey.indexType = tmpPrimaryKey.indexType;
          }
          con.executeInsert(dbTablePrimaryKey);

          var seqInIndex = 1;
          for (var columnKey : tmpPrimaryKey.columnKeyOptionList) {
            // DbTablePrimaryKeyColumn
            var dbTablePrimaryKeyColumn = new DbTablePrimaryKeyColumn();
            dbTablePrimaryKeyColumn.tableId = dbTable.tableId;
            dbTablePrimaryKeyColumn.seq = seqPrimaryKey;

            var dbDictColumn = createdDbDictColumnList.stream()
                .filter(d -> d.columnName.equals(columnKey.columnName))
                .findFirst()
                .get();
            dbTablePrimaryKeyColumn.dictColumnId = dbDictColumn.dictColumnId;

            if (!Utils.isNullOrEmpty(columnKey.length)) {
              dbTablePrimaryKeyColumn.length = columnKey.length;
            }
            dbTablePrimaryKeyColumn.seqInIndex = String.valueOf(seqInIndex);
            if (!Utils.isNullOrEmpty(columnKey.collation)) {
              dbTablePrimaryKeyColumn.collation = columnKey.collation;
            }
            con.executeInsert(dbTablePrimaryKeyColumn);

            seqInIndex++;
          }
        }

        // unique key
        var seqUniqueKey = 1;
        for (var tmpUniqueKey : tmpUniqueKeyList) {
          // DbTableUniqueKey
          var dbTableUniqueKey = new DbTableUniqueKey();
          dbTableUniqueKey.tableId = dbTable.tableId;
          dbTableUniqueKey.seq = seqUniqueKey;
          dbTableUniqueKey.constraintName = tmpUniqueKey.constraintName;
          dbTableUniqueKey.keyName = tmpUniqueKey.keyName;
          if (!Utils.isNullOrEmpty(tmpUniqueKey.indexComment)) {
            dbTableUniqueKey.indexComment = tmpUniqueKey.indexComment;
          }
          if (!Utils.isNullOrEmpty(tmpUniqueKey.indexType)) {
            dbTableUniqueKey.indexType = tmpUniqueKey.indexType;
          }
          con.executeInsert(dbTableUniqueKey);

          var seqInIndex = 1;
          for (var columnKey : tmpUniqueKey.columnKeyOptionList) {
            // DbTableUniqueKeyColumn
            var dbTableUniqueKeyColumn = new DbTableUniqueKeyColumn();
            dbTableUniqueKeyColumn.tableId = dbTable.tableId;
            dbTableUniqueKeyColumn.seq = seqUniqueKey;

            var dbDictColumn = createdDbDictColumnList.stream()
                .filter(d -> d.columnName.equals(columnKey.columnName))
                .findFirst()
                .get();
            dbTableUniqueKeyColumn.dictColumnId = dbDictColumn.dictColumnId;

            if (!Utils.isNullOrEmpty(columnKey.length)) {
              dbTableUniqueKeyColumn.length = columnKey.length;
            }
            dbTableUniqueKeyColumn.seqInIndex = String.valueOf(seqInIndex);
            if (!Utils.isNullOrEmpty(columnKey.collation)) {
              dbTableUniqueKeyColumn.collation = columnKey.collation;
            }
            con.executeInsert(dbTableUniqueKeyColumn);

            seqInIndex++;
          }
          seqUniqueKey++;
        }

        // key
        var seqKey = 1;
        for (var tmpKey : tmpKeyList) {
          // DbTableKey
          var dbTableKey = new DbTableKey();
          dbTableKey.tableId = dbTable.tableId;
          dbTableKey.seq = seqKey;
          dbTableKey.keyName = tmpKey.keyName;
          if (!Utils.isNullOrEmpty(tmpKey.indexComment)) {
            dbTableKey.indexComment = tmpKey.indexComment;
          }
          if (!Utils.isNullOrEmpty(tmpKey.indexType)) {
            dbTableKey.indexType = tmpKey.indexType;
          }
          con.executeInsert(dbTableKey);

          var seqInIndex = 1;
          for (var columnKey : tmpKey.columnKeyOptionList) {
            // DbTableKeyColumn
            var dbTableKeyColumn = new DbTableKeyColumn();
            dbTableKeyColumn.tableId = dbTable.tableId;
            dbTableKeyColumn.seq = seqKey;

            var dbDictColumn = createdDbDictColumnList.stream()
                .filter(d -> d.columnName.equals(columnKey.columnName))
                .findFirst()
                .get();
            dbTableKeyColumn.dictColumnId = dbDictColumn.dictColumnId;

            if (!Utils.isNullOrEmpty(columnKey.length)) {
              dbTableKeyColumn.length = columnKey.length;
            }
            dbTableKeyColumn.seqInIndex = String.valueOf(seqInIndex);
            if (!Utils.isNullOrEmpty(columnKey.collation)) {
              dbTableKeyColumn.collation = columnKey.collation;
            }
            con.executeInsert(dbTableKeyColumn);

            seqInIndex++;
          }
          seqKey++;
        }

        // check
        var seqCheck = 1;
        for (var tmpCheck : tmpCheckList) {
          // DbTableCheck
          var dbTableCheck = new DbTableCheck();
          dbTableCheck.tableId = dbTable.tableId;
          dbTableCheck.seq = seqCheck;
          dbTableCheck.constraintName = tmpCheck.constraintName;
          dbTableCheck.expression = tmpCheck.expression;
          con.executeInsert(dbTableCheck);

          seqCheck++;
        }
      }

      con.commit();
      return true;
    } catch (Exception e) {
      Log.error(e);
    }
    return false;
  }

  /**
   * parse create sequence.
   *
   * @param path path
   * @return ddl list
   * @throws IOException
   */
  public static List<String> parseCreateSequence(String path) throws IOException {
    var ddlList = new ArrayList<String>();
    try (var reader = new BufferedReader(new FileReader(path))) {
      String data;
      boolean processing = false;
      var builder = new StringBuilder();
      while ((data = reader.readLine()) != null) {
        if (data.toLowerCase().startsWith("create sequence")) {
          processing = true;
        }
        if (processing) {
          builder.append(data);

          if (data.endsWith(";")) {
            processing = false;
            ddlList.add(builder.toString());
            builder = new StringBuilder();
          }
        }
      }
    }
    return ddlList;
  }

  /**
   * parse create table.
   *
   * @param path path
   * @return ddl list
   * @throws IOException
   */
  public static List<String> parseCreateTable(String path) throws IOException {
    var ddlList = new ArrayList<String>();
    try (var reader = new BufferedReader(new FileReader(path))) {
      String data;
      boolean processing = false;
      var builder = new StringBuilder();
      while ((data = reader.readLine()) != null) {
        if (data.toLowerCase().startsWith("create table")) {
          processing = true;
        }
        if (processing) {
          builder.append(data);

          if (data.endsWith(";")) {
            processing = false;
            ddlList.add(builder.toString());
            builder = new StringBuilder();
          }
        }
      }
    }
    return ddlList;
  }
}
