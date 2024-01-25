package com.walksocket.er.sqlite;

import com.walksocket.antlr4.MariaDBLexer;
import com.walksocket.antlr4.MariaDBParser;
import com.walksocket.er.Utils;
import com.walksocket.er.component.main.root.workspace.Table;
import com.walksocket.er.parse.TableListener;
import com.walksocket.er.sqlite.context.CtxTable;
import com.walksocket.er.sqlite.context.inner.CtxInnerKey;
import com.walksocket.er.sqlite.context.inner.CtxInnerUniqueKey;
import com.walksocket.er.sqlite.entity.DbDictColumn;
import com.walksocket.er.sqlite.entity.DbDictColumnType;
import com.walksocket.er.sqlite.entity.DbDictPartition;
import com.walksocket.er.sqlite.entity.DbTable;
import com.walksocket.er.sqlite.entity.DbTableCheck;
import com.walksocket.er.sqlite.entity.DbTableColumn;
import com.walksocket.er.sqlite.entity.DbTableKey;
import com.walksocket.er.sqlite.entity.DbTableKeyColumn;
import com.walksocket.er.sqlite.entity.DbTablePartition;
import com.walksocket.er.sqlite.entity.DbTablePrimaryKey;
import com.walksocket.er.sqlite.entity.DbTablePrimaryKeyColumn;
import com.walksocket.er.sqlite.entity.DbTableUniqueKey;
import com.walksocket.er.sqlite.entity.DbTableUniqueKeyColumn;
import com.walksocket.er.sqlite.tmp.TmpCheck;
import com.walksocket.er.sqlite.tmp.TmpColumn;
import com.walksocket.er.sqlite.tmp.TmpKey;
import com.walksocket.er.sqlite.tmp.TmpPartition;
import com.walksocket.er.sqlite.tmp.TmpTable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

/**
 * ImportTable.
 */
public class ImportTable {

  /**
   * con.
   */
  private final Connection con;

  /**
   * created table names.
   */
  private final List<String> createdTableNames = new ArrayList<>();

  /**
   * seq.
   */
  private int seq;

  /**
   * Constructor.
   *
   * @param con con
   * @throws SQLException
   */
  public ImportTable(Connection con) throws SQLException {
    this.con = con;

    // max seq
    seq = 1;
    var sql = "SELECT MAX(seq) as maxSeq FROM DbDictColumnType";
    var record = con.getRecord(sql);
    if (record != null && record.get("maxSeq") != null) {
      seq = (Integer.parseInt(record.get("maxSeq")) + 1000) / 100 * 100 + 1;
    }
  }

  /**
   * create table and get.
   *
   * @param ddl ddl
   * @return ctx
   * @throws SQLException
   */
  public CtxTable createTableAndGet(String ddl) throws SQLException {
    if (createdTableNames.size() > Table.MAX_POSITIONED) {
      return null;
    }

    // parse
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

    if (Utils.isNullOrEmpty(tmpTable.tableName)) {
      return null;
    }
    if (createdTableNames.contains(tmpTable.tableName)) {
      return null;
    }

    // CtxTable
    var ctxTable = new CtxTable();

    // DbTable
    var dbTable = new DbTable();
    dbTable.tableId = Utils.randomString();
    dbTable.tableName = tmpTable.tableName;
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
    createdTableNames.add(dbTable.tableName);
    ctxTable.dbTable = dbTable;

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

        if (Bucket.initiated()) {
          Bucket.getInstance().getBucketDict().dbDictColumnTypeList.add(dbDictColumnType);
        }
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

        if (Bucket.initiated()) {
          Bucket.getInstance().getBucketDict().dbDictColumnList.add(dbDictColumn);
        }
      }
      createdDbDictColumnList.add(dbDictColumn);

      // DbTableColumn
      var dbTableColumn = new DbTableColumn();
      dbTableColumn.tableId = dbTable.tableId;
      dbTableColumn.ordinalPosition = ordinalPosition;
      dbTableColumn.dictColumnId = dbDictColumn.dictColumnId;
      con.executeInsert(dbTableColumn);
      ctxTable.dbTableColumnList.add(dbTableColumn);
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

        if (Bucket.initiated()) {
          Bucket.getInstance().getBucketDict().dbDictPartitionList.add(dbDictPartition);
        }
      }

      // DbTablePartition
      var dbTablePartition = new DbTablePartition();
      dbTablePartition.tableId = dbTable.tableId;
      dbTablePartition.dictPartitionId = dbDictPartition.dictPartitionId;
      con.executeInsert(dbTablePartition);
      ctxTable.dbTablePartition = dbTablePartition;
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
      ctxTable.ctxInnerPrimaryKey.dbTablePrimaryKey = dbTablePrimaryKey;

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
        ctxTable.ctxInnerPrimaryKey.dbTablePrimaryKeyColumnList.add(dbTablePrimaryKeyColumn);

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
      var ctxInnerUniqueKey = new CtxInnerUniqueKey();
      ctxTable.ctxInnerUniqueKeyList.add(ctxInnerUniqueKey);
      ctxInnerUniqueKey.dbTableUniqueKey = dbTableUniqueKey;

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
        ctxInnerUniqueKey.dbTableUniqueKeyColumnList.add(dbTableUniqueKeyColumn);

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
      var ctxInnerKey = new CtxInnerKey();
      ctxTable.ctxInnerKeyList.add(ctxInnerKey);
      ctxInnerKey.dbTableKey = dbTableKey;

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
        ctxInnerKey.dbTableKeyColumnList.add(dbTableKeyColumn);

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
      ctxTable.dbTableCheckList.add(dbTableCheck);

      seqCheck++;
    }

    return ctxTable;
  }
}
