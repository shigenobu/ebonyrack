package com.walksocket.er.sqlite;

import com.google.gson.internal.LinkedTreeMap;
import com.walksocket.er.File;
import com.walksocket.er.Json;
import com.walksocket.er.Log;
import com.walksocket.er.config.CfgProject;
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
      var maxY = 0;

      // sequence
      var importSequence = new ImportSequence(con);
      var ddlSequenceList = parseCreateSequence(path);
      for (int i = 0; i < ddlSequenceList.size(); i++) {
        var ddl = ddlSequenceList.get(i);

        var ctxSequence = importSequence.createSequenceAndGet(ddl);
        if (ctxSequence == null) {
          continue;
        }

        // DbSequenceOption
        var dbSequenceOption = new DbSequenceOption();
        dbSequenceOption.sequenceId = ctxSequence.dbSequence.sequenceId;
        int x = i % 40;
        int y = i / 40;
        dbSequenceOption.posX = x * 200 + (x + 1) * 20;
        dbSequenceOption.posY = (y + 1) * 20 + y * 200;
        con.executeInsert(dbSequenceOption);

        maxY = dbSequenceOption.posY + 200;
      }

      // table
      var importTable = new ImportTable(con);
      var ddlTableList = parseCreateTable(path);
      for (int i = 0; i < ddlTableList.size(); i++) {
        var ddl = ddlTableList.get(i);

        var ctxTable = importTable.createTableAndGet(ddl);
        if (ctxTable == null) {
          continue;
        }

        // DbTableOption
        var dbTableOption = new DbTableOption();
        dbTableOption.tableId = ctxTable.dbTable.tableId;
        int x = i % 30;
        int y = i / 30;
        dbTableOption.posX = x * 300 + (x + 1) * 20;
        dbTableOption.posY = maxY + (y + 1) * 20 + y * 300;
        con.executeInsert(dbTableOption);
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
