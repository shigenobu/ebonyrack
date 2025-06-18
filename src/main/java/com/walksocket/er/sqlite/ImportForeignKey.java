package com.walksocket.er.sqlite;

import com.walksocket.antlr4.MariaDBLexer;
import com.walksocket.antlr4.MariaDBParser;
import com.walksocket.er.Log;
import com.walksocket.er.Utils;
import com.walksocket.er.parse.ForeignKeyListener;
import com.walksocket.er.sqlite.context.inner.CtxInnerForeignKey;
import com.walksocket.er.sqlite.entity.DbDictColumn;
import com.walksocket.er.sqlite.entity.DbTable;
import com.walksocket.er.sqlite.entity.DbTableForeignKey;
import com.walksocket.er.sqlite.entity.DbTableForeignKeyColumn;
import com.walksocket.er.sqlite.tmp.TmpForeignKey;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

/**
 * ImportForeignKey.
 */
public class ImportForeignKey {

  /**
   * con.
   */
  private final Connection con;

  /**
   * Constructor.
   *
   * @param con con
   * @throws SQLException
   */
  public ImportForeignKey(Connection con) {
    this.con = con;
  }

  /**
   * create foreign key and get.
   *
   * @param ddl              ddl
   * @param dbDictColumnList dbDictColumnList
   * @return ctx
   * @throws SQLException
   */
  public CtxInnerForeignKey createForeignKeyAndGet(String ddl,
      List<DbDictColumn> dbDictColumnList)
      throws SQLException {
    // parse
    Log.trace(ddl);
    var stream = CharStreams.fromString(ddl);
    var lexer = new MariaDBLexer(stream);
    var tokens = new CommonTokenStream(lexer);
    var parser = new MariaDBParser(tokens);

    var tmpForeignKey = new TmpForeignKey();
    var listener = new ForeignKeyListener(tmpForeignKey);
    ParseTreeWalker.DEFAULT.walk(listener, parser.root());

    if (Utils.isNullOrEmpty(tmpForeignKey.tableName)) {
      return null;
    }
    if (Utils.isNullOrEmpty(tmpForeignKey.referenceTableName)) {
      return null;
    }

    // DbTable
    var sql = String.format("SELECT * FROM DbTable WHERE tableName = '%s'",
        Utils.quote(tmpForeignKey.tableName));
    var record = con.getRecord(sql);
    if (record == null) {
      return null;
    }
    var dbTable = Entity.convertEntity(record, DbTable.class);

    sql = String.format("SELECT * FROM DbTable WHERE tableName = '%s'",
        Utils.quote(tmpForeignKey.referenceTableName));
    record = con.getRecord(sql);
    if (record == null) {
      return null;
    }
    var referenceDbTable = Entity.convertEntity(record, DbTable.class);

    // seq
    var seqForeignKey = 1;
    sql = String.format("SELECT MAX(seq) as maxSeq FROM DbTableForeignKey WHERE tableId = '%s'",
        Utils.quote(dbTable.tableId));
    record = con.getRecord(sql);
    if (record != null && record.get("maxSeq") != null) {
      seqForeignKey = Integer.parseInt(record.get("maxSeq")) + 1;
    }

    // DbTableForeignKey
    var dbTableForeignKey = new DbTableForeignKey();
    dbTableForeignKey.tableId = dbTable.tableId;
    dbTableForeignKey.seq = seqForeignKey;
    dbTableForeignKey.constraintName = tmpForeignKey.constraintName;
    dbTableForeignKey.keyName = tmpForeignKey.keyName;
    sql = String.format("SELECT tableId FROM DbTable WHERE tableName = '%s'",
        Utils.quote(tmpForeignKey.referenceTableName));
    record = con.getRecord(sql);
    if (record == null) {
      return null;
    }
    dbTableForeignKey.referenceTableId = record.get("tableId");
    dbTableForeignKey.onUpdate = tmpForeignKey.onUpdate;
    dbTableForeignKey.onDelete = tmpForeignKey.onDelete;
    dbTableForeignKey.relationship = "";
    con.executeInsert(dbTableForeignKey);
    var ctxInnerForeignKey = new CtxInnerForeignKey();
    ctxInnerForeignKey.dbTableForeignKey = dbTableForeignKey;

    var dbDictColumnIdList = new ArrayList<String>();
    sql = String.format(
        "SELECT dictColumnId FROM DbTableColumn WHERE tableId = '%s'",
        Utils.quote(dbTable.tableId));
    var records = con.getRecords(sql);
    for (var r : records) {
      dbDictColumnIdList.add(r.get("dictColumnId"));
    }

    var referenceDbDictColumnIdList = new ArrayList<String>();
    sql = String.format(
        "SELECT dictColumnId FROM DbTableColumn WHERE tableId = '%s'",
        Utils.quote(referenceDbTable.tableId));
    records = con.getRecords(sql);
    for (var r : records) {
      referenceDbDictColumnIdList.add(r.get("dictColumnId"));
    }

    var seqInIndex = 1;
    for (int i = 0; i < tmpForeignKey.columnForeignKeyOptionList.size(); i++) {
      var columnForeignKeyOption = tmpForeignKey.columnForeignKeyOptionList.get(i);
      var referenceColumnForeignKeyOption = tmpForeignKey.referenceColumnForeignKeyOptionList.get(
          i);

      // DbTableForeignKeyColumn
      var dbTableForeignKeyColumn = new DbTableForeignKeyColumn();
      dbTableForeignKeyColumn.tableId = dbTable.tableId;
      dbTableForeignKeyColumn.seq = seqForeignKey;

      var fileteredDbDictColumnList = dbDictColumnList.stream()
          .filter(d -> dbDictColumnIdList.contains(d.dictColumnId))
          .collect(Collectors.toList());
      var dbDictColumn = fileteredDbDictColumnList.stream()
          .filter(d -> d.columnName.equals(columnForeignKeyOption.columnName))
          .findFirst()
          .get();
      dbTableForeignKeyColumn.dictColumnId = dbDictColumn.dictColumnId;

      var fileteredReferenceDbDictColumnList = dbDictColumnList.stream()
          .filter(d -> referenceDbDictColumnIdList.contains(d.dictColumnId))
          .collect(Collectors.toList());
      var referenceDbDictColumn = fileteredReferenceDbDictColumnList.stream()
          .filter(d -> d.columnName.equals(referenceColumnForeignKeyOption.columnName))
          .findFirst()
          .get();
      dbTableForeignKeyColumn.referenceDictColumnId = referenceDbDictColumn.dictColumnId;

      dbTableForeignKeyColumn.seqInIndex = String.valueOf(seqInIndex);

      con.executeInsert(dbTableForeignKeyColumn);
      ctxInnerForeignKey.dbTableForeignKeyColumnList.add(dbTableForeignKeyColumn);

      seqInIndex++;
    }

    return ctxInnerForeignKey;
  }
}
