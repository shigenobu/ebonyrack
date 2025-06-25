package com.walksocket.er.sqlite.bucket;

import com.walksocket.er.Log;
import com.walksocket.er.Pos;
import com.walksocket.er.Utils;
import com.walksocket.er.definition.NotNull;
import com.walksocket.er.sqlite.Bucket;
import com.walksocket.er.sqlite.Connection;
import com.walksocket.er.sqlite.Dump;
import com.walksocket.er.sqlite.Entity;
import com.walksocket.er.sqlite.ImportForeignKey;
import com.walksocket.er.sqlite.ImportTable;
import com.walksocket.er.sqlite.context.CtxTable;
import com.walksocket.er.sqlite.context.inner.CtxInnerForeignKey;
import com.walksocket.er.sqlite.context.inner.CtxInnerKey;
import com.walksocket.er.sqlite.context.inner.CtxInnerPrimaryKey;
import com.walksocket.er.sqlite.context.inner.CtxInnerUniqueKey;
import com.walksocket.er.sqlite.entity.DbDictColumn;
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
import com.walksocket.er.sqlite.tmp.TmpForeignKey;
import com.walksocket.er.sqlite.tmp.TmpKey;
import com.walksocket.er.sqlite.tmp.TmpTable;
import java.awt.Point;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * BucketTable.
 */
public class BucketTable {

  /**
   * con.
   */
  private final Connection con;

  /**
   * ctxTableList.
   */
  public List<CtxTable> ctxTableList = new ArrayList<>();

  /**
   * Constructor.
   *
   * @param con con
   */
  public BucketTable(Connection con) {
    this.con = con;

    read();
  }

  /**
   * read.
   */
  public void read() {
    ctxTableList.clear();

    try {
      // DbTable, DbTableOption
      var sql = "SELECT * FROM "
          + "DbTable as t1 "
          + "join "
          + "DbTableOption as t2 "
          + "ON t1.tableId = t2.tableId "
          + "ORDER BY t1.tableName ASC";
      var records = con.getRecords(sql);
      for (var record : records) {
        var dbTable = Entity.convertEntity(record, DbTable.class);
        var dbTableOption = Entity.convertEntity(record, DbTableOption.class);
        Log.trace(dbTable);
        Log.trace(dbTableOption);

        // modify pos
        if (dbTableOption.posX < Pos.MIN) {
          dbTableOption.posX = Pos.MIN;
        }
        if (dbTableOption.posY < Pos.MIN) {
          dbTableOption.posY = Pos.MIN;
        }
        if (dbTableOption.posX > Pos.MAX) {
          dbTableOption.posX = Pos.MAX;
        }
        if (dbTableOption.posY > Pos.MAX) {
          dbTableOption.posY = Pos.MAX;
        }

        // create context
        var ctxTable = new CtxTable();
        ctxTable.dbTable = dbTable;
        ctxTable.dbTableOption = dbTableOption;

        ctxTableList.add(ctxTable);
      }

      // --------------------
      // DbTableGroup
      sql = "SELECT * FROM DbTableGroup";
      records = con.getRecords(sql);
      for (var record : records) {
        var dbTableGroup = Entity.convertEntity(record, DbTableGroup.class);
        Log.trace(dbTableGroup);

        var opt = ctxTableList.stream()
            .filter(t -> t.dbTable.tableId.equals(dbTableGroup.tableId))
            .findFirst();
        if (opt.isPresent()) {
          opt.get().dbTableGroup = dbTableGroup;
        }
      }

      // --------------------
      // DbTableColumn
      sql = "SELECT * FROM DbTableColumn";
      records = con.getRecords(sql);
      for (var record : records) {
        var dbTableColumn = Entity.convertEntity(record, DbTableColumn.class);
        Log.trace(dbTableColumn);

        var opt = ctxTableList.stream()
            .filter(t -> t.dbTable.tableId.equals(dbTableColumn.tableId))
            .findFirst();
        if (opt.isPresent()) {
          opt.get().dbTableColumnList.add(dbTableColumn);
        }
      }

      // --------------------
      // DbTablePartition
      sql = "SELECT * FROM DbTablePartition";
      records = con.getRecords(sql);
      for (var record : records) {
        var dbTablePartition = Entity.convertEntity(record, DbTablePartition.class);
        Log.trace(dbTablePartition);

        var opt = ctxTableList.stream()
            .filter(t -> t.dbTable.tableId.equals(dbTablePartition.tableId))
            .findFirst();
        if (opt.isPresent()) {
          opt.get().dbTablePartition = dbTablePartition;
        }
      }

      // --------------------
      // DbTablePrimaryKey
      sql = "SELECT * FROM DbTablePrimaryKey";
      records = con.getRecords(sql);
      for (var record : records) {
        var dbTablePrimaryKey = Entity.convertEntity(record, DbTablePrimaryKey.class);
        Log.trace(dbTablePrimaryKey);

        var opt = ctxTableList.stream()
            .filter(t -> t.dbTable.tableId.equals(dbTablePrimaryKey.tableId))
            .findFirst();
        if (opt.isPresent()) {
          opt.get().ctxInnerPrimaryKey.dbTablePrimaryKey = dbTablePrimaryKey;
        }
      }

      // DbTablePrimaryKeyColumn
      sql = "SELECT * FROM DbTablePrimaryKeyColumn";
      records = con.getRecords(sql);
      for (var record : records) {
        var dbTablePrimaryKeyColumn = Entity.convertEntity(record, DbTablePrimaryKeyColumn.class);
        Log.trace(dbTablePrimaryKeyColumn);

        var opt = ctxTableList.stream()
            .filter(t -> t.dbTable.tableId.equals(dbTablePrimaryKeyColumn.tableId))
            .findFirst();
        if (opt.isPresent()) {
          opt.get().ctxInnerPrimaryKey.dbTablePrimaryKeyColumnList.add(dbTablePrimaryKeyColumn);
        }
      }

      // --------------------
      // DbTableUniqueKey
      sql = "SELECT * FROM DbTableUniqueKey";
      records = con.getRecords(sql);
      for (var record : records) {
        var dbTableUniqueKey = Entity.convertEntity(record, DbTableUniqueKey.class);
        Log.trace(dbTableUniqueKey);

        var opt = ctxTableList.stream()
            .filter(t -> t.dbTable.tableId.equals(dbTableUniqueKey.tableId))
            .findFirst();
        if (opt.isPresent()) {
          var ctxInnerUniqueKey = new CtxInnerUniqueKey();
          ctxInnerUniqueKey.dbTableUniqueKey = dbTableUniqueKey;
          opt.get().ctxInnerUniqueKeyList.add(ctxInnerUniqueKey);
        }
      }

      // DbTableUniqueKeyColumn
      sql = "SELECT * FROM DbTableUniqueKeyColumn";
      records = con.getRecords(sql);
      for (var record : records) {
        var dbTableUniqueKeyColumn = Entity.convertEntity(record, DbTableUniqueKeyColumn.class);
        Log.trace(dbTableUniqueKeyColumn);

        var opt = ctxTableList.stream()
            .filter(t -> t.dbTable.tableId.equals(dbTableUniqueKeyColumn.tableId))
            .findFirst();
        if (opt.isPresent()) {
          var o = opt.get().ctxInnerUniqueKeyList.stream()
              .filter(c -> c.dbTableUniqueKey.seq == dbTableUniqueKeyColumn.seq)
              .findFirst();
          if (o.isPresent()) {
            o.get().dbTableUniqueKeyColumnList.add(dbTableUniqueKeyColumn);
          }
        }
      }

      // --------------------
      // DbTableKey
      sql = "SELECT * FROM DbTableKey";
      records = con.getRecords(sql);
      for (var record : records) {
        var dbTableKey = Entity.convertEntity(record, DbTableKey.class);
        Log.trace(dbTableKey);

        var opt = ctxTableList.stream()
            .filter(t -> t.dbTable.tableId.equals(dbTableKey.tableId))
            .findFirst();
        if (opt.isPresent()) {
          var ctxInnerKey = new CtxInnerKey();
          ctxInnerKey.dbTableKey = dbTableKey;
          opt.get().ctxInnerKeyList.add(ctxInnerKey);
        }
      }

      // DbTableKeyColumn
      sql = "SELECT * FROM DbTableKeyColumn";
      records = con.getRecords(sql);
      for (var record : records) {
        var dbTableKeyColumn = Entity.convertEntity(record, DbTableKeyColumn.class);
        Log.trace(dbTableKeyColumn);

        var opt = ctxTableList.stream()
            .filter(t -> t.dbTable.tableId.equals(dbTableKeyColumn.tableId))
            .findFirst();
        if (opt.isPresent()) {
          var o = opt.get().ctxInnerKeyList.stream()
              .filter(c -> c.dbTableKey.seq == dbTableKeyColumn.seq)
              .findFirst();
          if (o.isPresent()) {
            o.get().dbTableKeyColumnList.add(dbTableKeyColumn);
          }
        }
      }

      // --------------------
      // DbTableForeignKey
      sql = "SELECT * FROM DbTableForeignKey";
      records = con.getRecords(sql);
      for (var record : records) {
        var dbTableForeignKey = Entity.convertEntity(record, DbTableForeignKey.class);
        Log.trace(dbTableForeignKey);

        var opt = ctxTableList.stream()
            .filter(t -> t.dbTable.tableId.equals(dbTableForeignKey.tableId))
            .findFirst();
        if (opt.isPresent()) {
          var ctxInnerForeignKey = new CtxInnerForeignKey();
          ctxInnerForeignKey.dbTableForeignKey = dbTableForeignKey;
          opt.get().ctxInnerForeignKeyList.add(ctxInnerForeignKey);
        }
      }

      // DbTableForeignKeyColumn
      sql = "SELECT * FROM DbTableForeignKeyColumn";
      records = con.getRecords(sql);
      for (var record : records) {
        var dbTableForeignKeyColumn = Entity.convertEntity(record, DbTableForeignKeyColumn.class);
        Log.trace(dbTableForeignKeyColumn);

        var opt = ctxTableList.stream()
            .filter(t -> t.dbTable.tableId.equals(dbTableForeignKeyColumn.tableId))
            .findFirst();
        if (opt.isPresent()) {
          var o = opt.get().ctxInnerForeignKeyList.stream()
              .filter(c -> c.dbTableForeignKey.seq == dbTableForeignKeyColumn.seq)
              .findFirst();
          if (o.isPresent()) {
            o.get().dbTableForeignKeyColumnList.add(dbTableForeignKeyColumn);
          }
        }
      }

      // --------------------
      // DbTableCheck
      sql = "SELECT * FROM DbTableCheck";
      records = con.getRecords(sql);
      for (var record : records) {
        var dbTableCheck = Entity.convertEntity(record, DbTableCheck.class);
        Log.trace(dbTableCheck);

        var opt = ctxTableList.stream()
            .filter(t -> t.dbTable.tableId.equals(dbTableCheck.tableId))
            .findFirst();
        if (opt.isPresent()) {
          opt.get().dbTableCheckList.add(dbTableCheck);
        }
      }

    } catch (SQLException e) {
      Log.error(e);
    }
  }

  /**
   * register.
   *
   * @param ctxTable ctxTable
   * @throws Exception
   */
  public void register(CtxTable ctxTable) throws Exception {
    try {
      // database
      con.begin();
      con.executeInsert(ctxTable.dbTable);
      con.executeInsert(ctxTable.dbTableOption);
      con.commit();

      // memory
      ctxTableList.add(ctxTable);

    } catch (Exception e) {
      con.rollback();
      Log.error(e);

      throw e;
    }
  }

  /**
   * register for copy.
   *
   * @param ctxTable ctxTable
   * @throws Exception
   */
  public void registerForCopy(CtxTable ctxTable) throws Exception {
    try {
      // database
      con.begin();
      con.executeInsert(ctxTable.dbTable);
      con.executeInsert(ctxTable.dbTableOption);

      // DbTableGroup
      if (ctxTable.dbTableGroup != null) {
        con.executeInsert(ctxTable.dbTableGroup);
      }

      // List<DbTableColumn>
      for (var dbTableColumn : ctxTable.dbTableColumnList) {
        con.executeInsert(dbTableColumn);
      }

      // DbTablePartition
      if (ctxTable.dbTablePartition != null) {
        con.executeInsert(ctxTable.dbTablePartition);
      }

      // CtxInnerPrimaryKey
      if (ctxTable.ctxInnerPrimaryKey.dbTablePrimaryKey != null) {
        con.executeInsert(ctxTable.ctxInnerPrimaryKey.dbTablePrimaryKey);
        for (var dbTablePrimaryKeyColumn : ctxTable.ctxInnerPrimaryKey.dbTablePrimaryKeyColumnList) {
          con.executeInsert(dbTablePrimaryKeyColumn);
        }
      }

      // List<CtxInnerUniqueKey>
      for (var ctxInnerUniqueKey : ctxTable.ctxInnerUniqueKeyList) {
        if (ctxInnerUniqueKey.dbTableUniqueKey != null) {
          con.executeInsert(ctxInnerUniqueKey.dbTableUniqueKey);
          for (var dbTableUniqueKeyColumn : ctxInnerUniqueKey.dbTableUniqueKeyColumnList) {
            con.executeInsert(dbTableUniqueKeyColumn);
          }
        }
      }

      // List<CtxInnerKey>
      for (var ctxInnerKey : ctxTable.ctxInnerKeyList) {
        if (ctxInnerKey.dbTableKey != null) {
          con.executeInsert(ctxInnerKey.dbTableKey);
          for (var dbTableKeyColumn : ctxInnerKey.dbTableKeyColumnList) {
            con.executeInsert(dbTableKeyColumn);
          }
        }
      }

      // List<CtxInnerForeignKey>
      // not copy

      // List<DbTableCheck>
      for (var dbTableCheck : ctxTable.dbTableCheckList) {
        con.executeInsert(dbTableCheck);
      }

      con.commit();

      // memory
      ctxTableList.add(ctxTable);

    } catch (Exception e) {
      con.rollback();
      Log.error(e);

      throw e;
    }
  }

  /**
   * save.
   *
   * @param ctxTable          ctxTable
   * @param tmpTable          tmpTable
   * @param tmpColumnList     tmpColumnList
   * @param tmpGroupName      tmpGroupName
   * @param tmpPartitionName  tmpPartitionName
   * @param tmpPrimaryKeyList tmpPrimaryKeyList
   * @param tmpUniqueKeyList  tmpUniqueKeyList
   * @param tmpKeyList        tmpKeyList
   * @param tmpForeignKeyList tmpForeignKeyList
   * @param tmpCheckList      tmpCheckList
   * @throws Exception
   */
  public void save(
      CtxTable ctxTable,
      TmpTable tmpTable,
      List<TmpColumn> tmpColumnList,
      String tmpGroupName,
      String tmpPartitionName,
      List<TmpKey> tmpPrimaryKeyList,
      List<TmpKey> tmpUniqueKeyList,
      List<TmpKey> tmpKeyList,
      List<TmpForeignKey> tmpForeignKeyList,
      List<TmpCheck> tmpCheckList)
      throws Exception {
    var sql = "";
    try {
      con.begin();

      var existedDbDictColumnList = new ArrayList<DbDictColumn>();
      var duplicateColumnNameList = new ArrayList<String>();

      // ----------------------------------------
      // DbTable
      var dbTable = saveDbTable(ctxTable, tmpTable);
      var isAutoIncrementTable = !Utils.isNullOrEmpty(dbTable.autoIncrementValue);

      // ----------------------------------------
      // DbTableGroup
      DbTableGroup dbTableGroup = saveDbTableGroup(dbTable, tmpGroupName);
      if (dbTableGroup != null) {
        var opt = Bucket.getInstance().getBucketDict().dbDictGroupList.stream()
            .filter(d -> d.dictGroupId.equals(dbTableGroup.dictGroupId))
            .findFirst();
        if (opt.isPresent()) {
          for (var dbDictGroupColumn : Bucket.getInstance()
              .getBucketDict().dbDictGroupColumnList.stream()
              .filter(d -> d.dictGroupId.equals(dbTableGroup.dictGroupId))
              .collect(Collectors.toList())) {
            var o = Bucket.getInstance().getBucketDict().dbDictColumnList.stream()
                .filter(c -> c.dictColumnId.equals(dbDictGroupColumn.dictColumnId))
                .findFirst();
            if (o.isPresent()) {
              existedDbDictColumnList.add(o.get());

              if (duplicateColumnNameList.contains(o.get().columnName)) {
                throw new Exception("Duplicate 'Column name'.");
              }
              duplicateColumnNameList.add(o.get().columnName);
            }
          }
        }
      }

      // ----------------------------------------
      // DbTablePartition
      DbTablePartition dbTablePartition = saveDbTablePartition(dbTable, tmpPartitionName);

      // ----------------------------------------
      // DbDictColumn, DbTableColumn
      var dbDictColumnTypeList = Bucket.getInstance().getBucketDict().dbDictColumnTypeList;
      sql = String.format(
          "DELETE FROM DbTableColumn WHERE tableId = '%s'",
          ctxTable.dbTable.tableId
      );
      con.execute(sql);
      var additionalDbDictColumnList = new ArrayList<DbDictColumn>();
      var dbTableColumnList = new ArrayList<DbTableColumn>();
      DbTableColumn autoIncrementDbTableColumn = null;
      List<DbTableColumn> generatedDbTableColumnList = new ArrayList<>();
      var ordinalPosition = 1;
      for (var tmpColumn : tmpColumnList) {
        var dbDictColumnType = dbDictColumnTypeList.stream()
            .filter(d -> d.columnType.equals(tmpColumn.columnType))
            .findFirst()
            .get();

        // --------------------
        // DbDictColumn
        sql = String.format("SELECT * FROM DbDictColumn "
                + "WHERE "
                + "checksum = '%s' ",
            Utils.quote(tmpColumn.getDictColumnChecksum(dbDictColumnType))
        );
        var record = con.getRecord(sql);
        var dbDictColumn = new DbDictColumn();
        if (record != null) {
          dbDictColumn.bind(record);
        } else {
          dbDictColumn.dictColumnId = Utils.randomString();
          dbDictColumn.columnName = tmpColumn.columnName;
          dbDictColumn.columnComment = tmpColumn.columnComment;
          dbDictColumn.dictColumnTypeId = dbDictColumnType.dictColumnTypeId;
          dbDictColumn.notNullValue = tmpColumn.notNullValue;
          dbDictColumn.charsetValue = tmpColumn.charsetValue;
          dbDictColumn.collateValue = tmpColumn.collateValue;
          dbDictColumn.defaultValue = tmpColumn.defaultValue;
          dbDictColumn.onUpdate = tmpColumn.onUpdate;
          dbDictColumn.autoIncrementDefinition = tmpColumn.autoIncrementDefinition;
          dbDictColumn.option = tmpColumn.option;

          con.executeInsert(dbDictColumn);
          additionalDbDictColumnList.add(dbDictColumn);
        }
        existedDbDictColumnList.add(dbDictColumn);

        // --------------------
        // DbTableColumn
        var dbTableColumn = new DbTableColumn();
        dbTableColumn.tableId = ctxTable.dbTable.tableId;
        dbTableColumn.ordinalPosition = ordinalPosition;
        dbTableColumn.dictColumnId = dbDictColumn.dictColumnId;

        con.executeInsert(dbTableColumn);
        dbTableColumnList.add(dbTableColumn);

        ordinalPosition++;

        // column name
        if (duplicateColumnNameList.contains(dbDictColumn.columnName)) {
          throw new Exception("Duplicate 'Column name'.");
        }
        duplicateColumnNameList.add(dbDictColumn.columnName);

        // auto increment
        if (!Utils.isNullOrEmpty(dbDictColumn.autoIncrementDefinition)) {
          autoIncrementDbTableColumn = dbTableColumn;
        }
      }

      // check auto increment
      if (isAutoIncrementTable && autoIncrementDbTableColumn == null) {
        throw new Exception("Auto increment column is not existed.");
      }

      // ----------------------------------------
      // DbTablePrimaryKey, DbTablePrimaryKeyColumn
      var ctxInnerPrimaryKey = saveDbTablePrimaryKey(dbTable, tmpPrimaryKeyList,
          existedDbDictColumnList);
      if (ctxInnerPrimaryKey.dbTablePrimaryKeyColumnList.size() == 0
          && autoIncrementDbTableColumn != null) {
        throw new Exception("Auto increment must be single primary key. No primary disallow.");
      }
      for (var dbTablePrimaryKeyColumn : ctxInnerPrimaryKey.dbTablePrimaryKeyColumnList) {
        // check auto increment
        if (autoIncrementDbTableColumn != null
            && autoIncrementDbTableColumn.dictColumnId != dbTablePrimaryKeyColumn.dictColumnId) {
          throw new Exception("Auto increment must be single primary key. Mismatch primary.");
        }

        // check generated column
        var o = generatedDbTableColumnList.stream()
            .filter(t -> t.dictColumnId.equals(dbTablePrimaryKeyColumn.dictColumnId))
            .findFirst();
        if (o.isPresent()) {
          throw new Exception("Generated column is disallowed primary.");
        }
      }

      // ----------------------------------------
      // DbTableUniqueKey, DbTableUniqueKeyColumn
      List<CtxInnerUniqueKey> ctxInnerUniqueKeyList = saveDbTableUniqueKeyList(dbTable,
          tmpUniqueKeyList, existedDbDictColumnList);

      // ----------------------------------------
      // DbTableKey, DbTableKeyColumn
      List<CtxInnerKey> ctxInnerKeyList = saveDbTableKeyList(dbTable, tmpKeyList,
          existedDbDictColumnList);

      // ----------------------------------------
      // DbTableForeignKey, DbTableForeignKeyColumn
      List<CtxInnerForeignKey> ctxInnerForeignKeyList = saveDbTableForeignKeyList(dbTable,
          tmpForeignKeyList, existedDbDictColumnList);
      for (var ctxInnerForeignKey : ctxInnerForeignKeyList) {
        for (var dbTableForeignKeyColumn : ctxInnerForeignKey.dbTableForeignKeyColumnList) {
          // check generated
          var opt = generatedDbTableColumnList.stream()
              .filter(c -> c.dictColumnId.equals(dbTableForeignKeyColumn.dictColumnId))
              .findFirst();
          if (opt.isPresent()) {
            throw new Exception("Generated column is not foreign key.");
          }

          // check seq index 1
          if (dbTableForeignKeyColumn.seqInIndex != 1) {
            continue;
          }
          Log.trace(String.format("column:%s", dbTableForeignKeyColumn));
          var isFirst = false;
          if (!isFirst) {
            isFirst = ctxInnerPrimaryKey.dbTablePrimaryKeyColumnList.stream()
                .filter(c -> c.seqInIndex == 1)
                .filter(c -> c.dictColumnId.equals(dbTableForeignKeyColumn.dictColumnId))
                .findFirst()
                .isPresent();
          }
          if (!isFirst) {
            for (var ctxInnerUniqueKey : ctxInnerUniqueKeyList) {
              isFirst = ctxInnerUniqueKey.dbTableUniqueKeyColumnList.stream()
                  .filter(c -> c.seqInIndex == 1)
                  .filter(c -> c.dictColumnId.equals(dbTableForeignKeyColumn.dictColumnId))
                  .findFirst()
                  .isPresent();
              if (isFirst) {
                break;
              }
            }
          }
          if (!isFirst) {
            for (var ctxInnerKey : ctxInnerKeyList) {
              isFirst = ctxInnerKey.dbTableKeyColumnList.stream()
                  .filter(c -> c.seqInIndex == 1)
                  .filter(c -> c.dictColumnId.equals(dbTableForeignKeyColumn.dictColumnId))
                  .findFirst()
                  .isPresent();
              if (isFirst) {
                break;
              }
            }
          }
          if (!isFirst) {
            throw new Exception("First key column is need for foreign key.");
          }
        }
      }

      // ----------------------------------------
      // had DbTableForeignKey, DbTableForeignKeyColumn
      checkHadForeignKey(dbTable, dbTableColumnList, existedDbDictColumnList);

      // ----------------------------------------
      // DbTableCheck
      sql = String.format(
          "DELETE FROM DbTableCheck WHERE tableId = '%s'",
          ctxTable.dbTable.tableId
      );
      con.execute(sql);
      var seq = 1;
      var dbTableCheckList = new ArrayList<DbTableCheck>();
      for (var tmpCheck : tmpCheckList) {
        var dbTableCheck = new DbTableCheck();
        dbTableCheck.tableId = ctxTable.dbTable.tableId;
        dbTableCheck.seq = seq;
        dbTableCheck.constraintName = tmpCheck.constraintName;
        dbTableCheck.expression = tmpCheck.expression;
        con.executeInsert(dbTableCheck);
        dbTableCheckList.add(dbTableCheck);

        seq++;
      }

      con.commit();

      // back
      ctxTable.dbTable = dbTable;
      ctxTable.dbTableGroup = dbTableGroup;
      Bucket.getInstance().getBucketDict().dbDictColumnList.addAll(
          additionalDbDictColumnList);    // for dict
      ctxTable.dbTableColumnList = dbTableColumnList;
      ctxTable.dbTablePartition = dbTablePartition;
      ctxTable.ctxInnerPrimaryKey = ctxInnerPrimaryKey;
      ctxTable.ctxInnerUniqueKeyList = ctxInnerUniqueKeyList;
      ctxTable.ctxInnerKeyList = ctxInnerKeyList;
      ctxTable.ctxInnerForeignKeyList = ctxInnerForeignKeyList;
      ctxTable.dbTableCheckList = dbTableCheckList;

    } catch (Exception e) {
      con.rollback();
      Log.error(e);

      throw e;
    }
  }

  /**
   * save db table.
   *
   * @param ctxTable ctxTable
   * @param tmpTable tmpTable
   * @return db table
   * @throws SQLException
   */
  private DbTable saveDbTable(CtxTable ctxTable, TmpTable tmpTable) throws SQLException {
    var dbTable = new DbTable();
    dbTable.tableId = ctxTable.dbTable.tableId;
    dbTable.tableName = tmpTable.tableName;
    dbTable.tableComment = tmpTable.tableComment;
    dbTable.engine = tmpTable.engine;
    dbTable.charsetValue = tmpTable.charsetValue;
    dbTable.collateValue = tmpTable.collateValue;
    dbTable.autoIncrementValue = tmpTable.autoIncrementValue;
    dbTable.option = tmpTable.option;
    con.executeUpdate(dbTable);
    return dbTable;
  }

  /**
   * save db table group.
   *
   * @param dbTable      dbTable
   * @param tmpGroupName tmpGroupName
   * @return db table group
   * @throws SQLException
   */
  private DbTableGroup saveDbTableGroup(DbTable dbTable, String tmpGroupName) throws SQLException {
    var sql = String.format(
        "DELETE FROM DbTableGroup WHERE tableId = '%s'",
        dbTable.tableId
    );
    con.execute(sql);

    DbTableGroup dbTableGroup = null;
    if (!Utils.isNullOrEmpty(tmpGroupName)) {
      var opt = Bucket.getInstance().getBucketDict().dbDictGroupList.stream()
          .filter(g -> g.groupName.equals(tmpGroupName))
          .findFirst();
      if (opt.isPresent()) {
        var dbDictGroup = opt.get();

        dbTableGroup = new DbTableGroup();
        dbTableGroup.tableId = dbTable.tableId;
        dbTableGroup.dictGroupId = dbDictGroup.dictGroupId;
        con.executeInsert(dbTableGroup);
      }
    }
    return dbTableGroup;
  }

  /**
   * save db table partition.
   *
   * @param dbTable          dbTable
   * @param tmpPartitionName tmpPartitionName
   * @return db table partition
   * @throws SQLException
   */
  private DbTablePartition saveDbTablePartition(DbTable dbTable, String tmpPartitionName)
      throws SQLException {
    var sql = String.format(
        "DELETE FROM DbTablePartition WHERE tableId = '%s'",
        dbTable.tableId
    );
    con.execute(sql);

    DbTablePartition dbTablePartition = null;
    if (!Utils.isNullOrEmpty(tmpPartitionName)) {
      var opt = Bucket.getInstance().getBucketDict().dbDictPartitionList.stream()
          .filter(p -> p.partitionName.equals(tmpPartitionName))
          .findFirst();
      if (opt.isPresent()) {
        var dbDictPartition = opt.get();

        dbTablePartition = new DbTablePartition();
        dbTablePartition.tableId = dbTable.tableId;
        dbTablePartition.dictPartitionId = dbDictPartition.dictPartitionId;
        con.executeInsert(dbTablePartition);
      }
    }
    return dbTablePartition;
  }

  /**
   * save db table primary key.
   *
   * @param dbTable                 dbTable
   * @param tmpPrimaryKeyList       tmpPrimaryKeyList
   * @param existedDbDictColumnList existedDbDictColumnList
   * @return db table primary key
   * @throws Exception
   */
  private CtxInnerPrimaryKey saveDbTablePrimaryKey(DbTable dbTable, List<TmpKey> tmpPrimaryKeyList,
      List<DbDictColumn> existedDbDictColumnList)
      throws Exception {
    var sql = String.format(
        "DELETE FROM DbTablePrimaryKey WHERE tableId = '%s'",
        dbTable.tableId
    );
    con.execute(sql);
    sql = String.format(
        "DELETE FROM DbTablePrimaryKeyColumn WHERE tableId = '%s'",
        dbTable.tableId
    );
    con.execute(sql);
    var dbTablePrimaryKey = new DbTablePrimaryKey();
    var dbTablePrimaryKeyColumnList = new ArrayList<DbTablePrimaryKeyColumn>();
    var seqForPrimaryKey = 1;
    for (var tmpPrimaryKey : tmpPrimaryKeyList) {
      if (tmpPrimaryKey.columnKeyOptionList.size() == 0) {
        continue;
      }

      // DbTablePrimaryKey
      dbTablePrimaryKey.tableId = dbTable.tableId;
      dbTablePrimaryKey.seq = seqForPrimaryKey;
      dbTablePrimaryKey.constraintName = tmpPrimaryKey.constraintName;
      dbTablePrimaryKey.keyName = tmpPrimaryKey.keyName;
      dbTablePrimaryKey.indexComment = tmpPrimaryKey.indexComment;
      dbTablePrimaryKey.indexType = tmpPrimaryKey.indexType;

      con.executeInsert(dbTablePrimaryKey);

      // DbTablePrimaryKeyColumn
      for (var columnKeyOption : tmpPrimaryKey.columnKeyOptionList) {
        var dbTablePrimaryKeyColumn = new DbTablePrimaryKeyColumn();
        dbTablePrimaryKeyColumn.tableId = dbTable.tableId;
        dbTablePrimaryKeyColumn.seq = seqForPrimaryKey;

        var optDictColumn = existedDbDictColumnList.stream()
            .filter(d -> d.columnName.equals(columnKeyOption.columnName))
            .findFirst();
        if (!optDictColumn.isPresent()) {
          throw new Exception(
              String.format("For primary key '%s' is not exist.", columnKeyOption.columnName));
        }
        dbTablePrimaryKeyColumn.dictColumnId = optDictColumn.get().dictColumnId;
        dbTablePrimaryKeyColumn.length = columnKeyOption.length;
        dbTablePrimaryKeyColumn.seqInIndex = columnKeyOption.seqInIndex;
        dbTablePrimaryKeyColumn.collation = columnKeyOption.collation;

        con.executeInsert(dbTablePrimaryKeyColumn);
        dbTablePrimaryKeyColumnList.add(dbTablePrimaryKeyColumn);

        // check not null
        var o = existedDbDictColumnList.stream()
            .filter(d -> d.dictColumnId.equals(dbTablePrimaryKeyColumn.dictColumnId))
            .findFirst()
            .get();
        if (!o.notNullValue.equals(NotNull.NOT_NULL_VALUE)) {
          throw new Exception("Primary key must be 'Not null'.");
        }
      }
      seqForPrimaryKey++;

      break;
    }

    var ctxInnerPrimaryKey = new CtxInnerPrimaryKey();
    ctxInnerPrimaryKey.dbTablePrimaryKey = dbTablePrimaryKey;
    ctxInnerPrimaryKey.dbTablePrimaryKeyColumnList = dbTablePrimaryKeyColumnList;
    return ctxInnerPrimaryKey;
  }

  /**
   * save db table unique key list.
   *
   * @param dbTable                 dbTable
   * @param tmpUniqueKeyList        tmpUniqueKeyList
   * @param existedDbDictColumnList existedDbDictColumnList
   * @return db table unique key list
   * @throws Exception
   */
  private List<CtxInnerUniqueKey> saveDbTableUniqueKeyList(DbTable dbTable,
      List<TmpKey> tmpUniqueKeyList, List<DbDictColumn> existedDbDictColumnList)
      throws Exception {
    var sql = String.format(
        "DELETE FROM DbTableUniqueKey WHERE tableId = '%s'",
        dbTable.tableId
    );
    con.execute(sql);
    sql = String.format(
        "DELETE FROM DbTableUniqueKeyColumn WHERE tableId = '%s'",
        dbTable.tableId
    );
    con.execute(sql);

    List<CtxInnerUniqueKey> ctxInnerUniqueKeyList = new ArrayList<>();
    var seqForUniqueKey = 1;
    var duplicateUniqueKeyColumnAndCollation = new ArrayList<String>();
    for (var tmpUniqueKey : tmpUniqueKeyList) {
      if (tmpUniqueKey.columnKeyOptionList.size() == 0) {
        continue;
      }

      var ctxInnerUniqueKey = new CtxInnerUniqueKey();

      // DbTableUniqueKey
      var dbTableUniqueKey = new DbTableUniqueKey();
      dbTableUniqueKey.tableId = dbTable.tableId;
      dbTableUniqueKey.seq = seqForUniqueKey;
      dbTableUniqueKey.constraintName = tmpUniqueKey.constraintName;
      dbTableUniqueKey.keyName = tmpUniqueKey.keyName;
      dbTableUniqueKey.indexComment = tmpUniqueKey.indexComment;
      dbTableUniqueKey.indexType = tmpUniqueKey.indexType;

      con.executeInsert(dbTableUniqueKey);
      ctxInnerUniqueKey.dbTableUniqueKey = dbTableUniqueKey;

      // check duplicate
      var keyPairs = tmpUniqueKey.columnKeyOptionList.stream()
          .map(c -> c.columnName)
          .collect(Collectors.joining(", "));
      if (duplicateUniqueKeyColumnAndCollation.contains(keyPairs)) {
        throw new Exception(String.format("Duplicate unique key columns '%s'.", keyPairs));
      }
      duplicateUniqueKeyColumnAndCollation.add(keyPairs);

      // DbTableUniqueKeyColumn
      for (var columnKeyOption : tmpUniqueKey.columnKeyOptionList) {
        var dbTableUniqueKeyColumn = new DbTableUniqueKeyColumn();
        dbTableUniqueKeyColumn.tableId = dbTable.tableId;
        dbTableUniqueKeyColumn.seq = seqForUniqueKey;

        var optDictColumn = existedDbDictColumnList.stream()
            .filter(d -> d.columnName.equals(columnKeyOption.columnName))
            .findFirst();
        if (!optDictColumn.isPresent()) {
          throw new Exception(
              String.format("For unique key '%s' is not exist.", columnKeyOption.columnName));
        }
        dbTableUniqueKeyColumn.dictColumnId = optDictColumn.get().dictColumnId;
        dbTableUniqueKeyColumn.length = columnKeyOption.length;
        dbTableUniqueKeyColumn.seqInIndex = columnKeyOption.seqInIndex;
        dbTableUniqueKeyColumn.collation = columnKeyOption.collation;

        con.executeInsert(dbTableUniqueKeyColumn);
        ctxInnerUniqueKey.dbTableUniqueKeyColumnList.add(dbTableUniqueKeyColumn);
      }

      ctxInnerUniqueKeyList.add(ctxInnerUniqueKey);
      seqForUniqueKey++;
    }

    return ctxInnerUniqueKeyList;
  }

  /**
   * save db table key list.
   *
   * @param dbTable                 dbTable
   * @param tmpKeyList              tmpKeyList
   * @param existedDbDictColumnList existedDbDictColumnList
   * @return db table key list
   * @throws Exception
   */
  private List<CtxInnerKey> saveDbTableKeyList(DbTable dbTable, List<TmpKey> tmpKeyList,
      List<DbDictColumn> existedDbDictColumnList)
      throws Exception {
    var sql = String.format(
        "DELETE FROM DbTableKey WHERE tableId = '%s'",
        dbTable.tableId
    );
    con.execute(sql);
    sql = String.format(
        "DELETE FROM DbTableKeyColumn WHERE tableId = '%s'",
        dbTable.tableId
    );
    con.execute(sql);

    List<CtxInnerKey> ctxInnerKeyList = new ArrayList<>();
    var seqForKey = 1;
    var duplicateKeyColumnAndCollation = new ArrayList<String>();
    for (var tmpKey : tmpKeyList) {
      if (tmpKey.columnKeyOptionList.size() == 0) {
        continue;
      }

      var ctxInnerKey = new CtxInnerKey();

      // DbTableKey
      var dbTableKey = new DbTableKey();
      dbTableKey.tableId = dbTable.tableId;
      dbTableKey.seq = seqForKey;
      dbTableKey.keyName = tmpKey.keyName;
      dbTableKey.indexComment = tmpKey.indexComment;
      dbTableKey.indexType = tmpKey.indexType;

      con.executeInsert(dbTableKey);
      ctxInnerKey.dbTableKey = dbTableKey;

      // check duplicate
      var keyPairs = tmpKey.columnKeyOptionList.stream()
          .map(c -> c.columnName)
          .collect(Collectors.joining(", "));
      if (duplicateKeyColumnAndCollation.contains(keyPairs)) {
        throw new Exception(String.format("Duplicate key columns '%s'.", keyPairs));
      }
      duplicateKeyColumnAndCollation.add(keyPairs);

      // DbTableKeyColumn
      for (var columnKeyOption : tmpKey.columnKeyOptionList) {
        var dbTableKeyColumn = new DbTableKeyColumn();
        dbTableKeyColumn.tableId = dbTable.tableId;
        dbTableKeyColumn.seq = seqForKey;

        var optDictColumn = existedDbDictColumnList.stream()
            .filter(d -> d.columnName.equals(columnKeyOption.columnName))
            .findFirst();
        if (!optDictColumn.isPresent()) {
          throw new Exception(
              String.format("For key '%s' is not exist.", columnKeyOption.columnName));
        }
        dbTableKeyColumn.dictColumnId = optDictColumn.get().dictColumnId;

        dbTableKeyColumn.length = columnKeyOption.length;
        dbTableKeyColumn.seqInIndex = columnKeyOption.seqInIndex;
        dbTableKeyColumn.collation = columnKeyOption.collation;

        con.executeInsert(dbTableKeyColumn);
        ctxInnerKey.dbTableKeyColumnList.add(dbTableKeyColumn);
      }

      ctxInnerKeyList.add(ctxInnerKey);
      seqForKey++;
    }

    return ctxInnerKeyList;
  }

  /**
   * save db table foreign key list.
   *
   * @param dbTable                 dbTable
   * @param tmpForeignKeyList       tmpForeignKeyList
   * @param existedDbDictColumnList existedDbDictColumnList
   * @return db table foreign key list
   * @throws Exception
   */
  private List<CtxInnerForeignKey> saveDbTableForeignKeyList(DbTable dbTable,
      List<TmpForeignKey> tmpForeignKeyList, List<DbDictColumn> existedDbDictColumnList)
      throws Exception {
    var sql = String.format(
        "DELETE FROM DbTableForeignKey WHERE tableId = '%s'",
        dbTable.tableId
    );
    con.execute(sql);
    sql = String.format(
        "DELETE FROM DbTableForeignKeyColumn WHERE tableId = '%s'",
        dbTable.tableId
    );
    con.execute(sql);

    List<CtxInnerForeignKey> ctxInnerForeignKeyList = new ArrayList<>();
    var seqForForeignKey = 1;
    var duplicateForeignKeyColumn = new ArrayList<String>();
    for (var tmpForeignKey : tmpForeignKeyList) {
      if (tmpForeignKey.columnForeignKeyOptionList.size() == 0) {
        continue;
      }
      if (Utils.isNullOrEmpty(tmpForeignKey.referenceTableName)) {
        continue;
      }
      if (tmpForeignKey.referenceColumnForeignKeyOptionList.size() == 0) {
        continue;
      }

      var ctxInnerForeignKey = new CtxInnerForeignKey();

      // DbTableForeignKey
      var dbTableForeignKey = new DbTableForeignKey();
      dbTableForeignKey.tableId = dbTable.tableId;
      dbTableForeignKey.seq = seqForForeignKey;
      dbTableForeignKey.constraintName = tmpForeignKey.constraintName;
      dbTableForeignKey.keyName = tmpForeignKey.keyName;

      if (tmpForeignKey.referenceTableName.startsWith("(*)")) {
        dbTableForeignKey.referenceTableId = dbTable.tableId;
      } else {
        var opt = ctxTableList.stream()
            .map(c -> c.dbTable)
            .filter(c -> c.tableName.equals(tmpForeignKey.referenceTableName))
            .findFirst();
        if (!opt.isPresent()) {
          throw new Exception(
              String.format("Not exist reference table '%s'.", tmpForeignKey.referenceTableName));
        }
        dbTableForeignKey.referenceTableId = opt.get().tableId;
      }

      dbTableForeignKey.onUpdate = tmpForeignKey.onUpdate;
      dbTableForeignKey.onDelete = tmpForeignKey.onDelete;
      dbTableForeignKey.relationship = tmpForeignKey.relationship;

      con.executeInsert(dbTableForeignKey);
      ctxInnerForeignKey.dbTableForeignKey = dbTableForeignKey;

      // check duplicate
      var keyPairs = tmpForeignKey.columnForeignKeyOptionList.stream()
          .map(c -> c.columnName)
          .collect(Collectors.joining(", "));
      if (duplicateForeignKeyColumn.contains(keyPairs)) {
        throw new Exception(String.format("Duplicate foreign key columns '%s'.", keyPairs));
      }
      duplicateForeignKeyColumn.add(keyPairs);

      // DbTableForeignKeyColumn
      if (tmpForeignKey.columnForeignKeyOptionList.size()
          != tmpForeignKey.referenceColumnForeignKeyOptionList.size()) {
        throw new Exception("Mismatch column size between base and reference.");
      }

      for (int i = 0; i < tmpForeignKey.columnForeignKeyOptionList.size(); i++) {
        // self
        var columnForeignKeyOption = tmpForeignKey.columnForeignKeyOptionList.get(i);
        var referenceColumnForeignKeyOption = tmpForeignKey.referenceColumnForeignKeyOptionList.get(
            i);

        var dbTableForeignKeyColumn = new DbTableForeignKeyColumn();
        dbTableForeignKeyColumn.tableId = dbTable.tableId;
        dbTableForeignKeyColumn.seq = seqForForeignKey;

        var optDictColumn = existedDbDictColumnList.stream()
            .filter(d -> d.columnName.equals(columnForeignKeyOption.columnName))
            .findFirst();
        if (!optDictColumn.isPresent()) {
          throw new Exception(
              String.format("For foreign key '%s' is not exist.",
                  columnForeignKeyOption.columnName));
        }
        dbTableForeignKeyColumn.dictColumnId = optDictColumn.get().dictColumnId;

        // check same column type - self
        var dictColumnTypeId = existedDbDictColumnList.stream()
            .filter(e -> e.dictColumnId.equals(dbTableForeignKeyColumn.dictColumnId))
            .findFirst()
            .get()
            .dictColumnTypeId;

        // ---
        // other
        var ctxTable = ctxTableList.stream()
            .filter(c -> c.dbTable.tableId.equals(dbTableForeignKey.referenceTableId))
            .findFirst()
            .get();
        DbDictColumn referenceDbDictColumn = null;
        for (var dbTableColumn : ctxTable.dbTableColumnList) {
          var opt = Bucket.getInstance().getBucketDict().dbDictColumnList.stream()
              .filter(c -> c.dictColumnId.equals(dbTableColumn.dictColumnId))
              .filter(c -> c.columnName.equals(referenceColumnForeignKeyOption.columnName))
              .findFirst();
          if (opt.isPresent()) {
            referenceDbDictColumn = opt.get();
            break;
          }
        }
        if (referenceDbDictColumn == null) {
          // group
          var opt = Bucket.getInstance().getBucketDict().dbDictGroupColumnList.stream()
              .filter(c -> c.dictGroupId.equals(ctxTable.dbTableGroup.dictGroupId))
              .findFirst();
          if (opt.isPresent()) {
            referenceDbDictColumn = Bucket.getInstance().getBucketDict().dbDictColumnList.stream()
                .filter(c -> c.dictColumnId.equals(opt.get().dictColumnId))
                .findFirst()
                .get();
          }
        }
        if (referenceDbDictColumn == null) {
          throw new Exception(
              String.format("For reference foreign key '%s' is not exist.",
                  referenceColumnForeignKeyOption.columnName));
        }
        dbTableForeignKeyColumn.referenceDictColumnId = referenceDbDictColumn.dictColumnId;
        dbTableForeignKeyColumn.seqInIndex = columnForeignKeyOption.seqInIndex;

        // check same column type - self and other
        if (!dictColumnTypeId.equals(referenceDbDictColumn.dictColumnTypeId)) {
          throw new Exception("Foreign key Column type is mismatch.");
        }

        con.executeInsert(dbTableForeignKeyColumn);
        ctxInnerForeignKey.dbTableForeignKeyColumnList.add(dbTableForeignKeyColumn);
      }

      ctxInnerForeignKeyList.add(ctxInnerForeignKey);
      seqForForeignKey++;
    }

    return ctxInnerForeignKeyList;
  }

  /**
   * check had foreign key.
   *
   * @param dbTable                 dbTable
   * @param dbTableColumnList       dbTableColumnList
   * @param existedDbDictColumnList existedDbDictColumnList
   * @throws Exception
   */
  private void checkHadForeignKey(DbTable dbTable, List<DbTableColumn> dbTableColumnList,
      List<DbDictColumn> existedDbDictColumnList) throws Exception {
    var dbTableForeignKeyColumnList = new ArrayList<DbTableForeignKeyColumn>();
    for (var t : ctxTableList) {
      for (var tt : t.ctxInnerForeignKeyList) {
        if (!tt.dbTableForeignKey.referenceTableId.equals(dbTable.tableId)) {
          continue;
        }
        for (var ttt : tt.dbTableForeignKeyColumnList) {
          dbTableForeignKeyColumnList.add(ttt);
        }
      }
    }
    if (dbTableForeignKeyColumnList.size() == 0) {
      return;
    }

    for (var dbTableForeignKeyColumn : dbTableForeignKeyColumnList) {
      var opt = dbTableColumnList.stream()
          .filter(c -> c.dictColumnId.equals(dbTableForeignKeyColumn.referenceDictColumnId))
          .findFirst();
      if (!opt.isPresent()) {
        throw new Exception("Conflict reference foreign key.");
      }

      var selfDbTableColumn = opt.get();
      var selfDbDictColumn = existedDbDictColumnList.stream()
          .filter(c -> c.dictColumnId.equals(selfDbTableColumn.dictColumnId))
          .findFirst()
          .get();

      var ctxTable = ctxTableList.stream()
          .filter(c -> c.dbTable.tableId.equals(dbTableForeignKeyColumn.tableId))
          .findFirst()
          .get();
      var otherDbTableColumnList = ctxTable.dbTableColumnList.stream()
          .filter(c -> c.dictColumnId.equals(dbTableForeignKeyColumn.dictColumnId))
          .collect(Collectors.toList());
      for (var otherDbTableColumn : otherDbTableColumnList) {
        var otherDbDictColumn = Bucket.getInstance().getBucketDict().dbDictColumnList.stream()
            .filter(c -> c.dictColumnId.equals(otherDbTableColumn.dictColumnId))
            .findFirst()
            .get();

        // check column type
        if (!selfDbDictColumn.dictColumnTypeId.equals(otherDbDictColumn.dictColumnTypeId)) {
          throw new Exception("Referenced foreign key Column type is mismatch.");
        }
      }
    }
  }

  /**
   * remove.
   *
   * @param ctxTable ctxTable
   * @throws Exception
   */
  public void remove(CtxTable ctxTable) throws Exception {
    try {
      // database
      con.begin();
      con.executeDelete(ctxTable.dbTable);
      con.commit();

      // memory
      ctxTableList.remove(ctxTable);

    } catch (Exception e) {
      con.rollback();
      Log.error(e);

      throw e;
    }
  }

  /**
   * save option.
   *
   * @param ctxTable ctxTable
   * @throws Exception
   */
  public void saveOption(CtxTable ctxTable) throws Exception {
    try {
      // database
      con.begin();
      con.executeUpdate(ctxTable.dbTableOption);
      con.commit();
    } catch (Exception e) {
      con.rollback();
      Log.error(e);

      throw e;
    }
  }

  /**
   * import from ddl.
   *
   * @param ddl   ddl
   * @param point point
   * @return ctx list
   * @throws Exception
   */
  public List<CtxTable> importFromDdl(String ddl, Point point) throws Exception {
    try {
      // database
      con.begin();

      var offset = 0;
      var newCtxTableList = new ArrayList<CtxTable>();
      var ddlList = Dump.parseCreateTable(ddl);
      for (var d : ddlList) {
        var importTable = new ImportTable(con);
        importTable.addExistingTables(
            Bucket.getInstance().getBucketTable().ctxTableList.stream()
                .map(c -> c.dbTable.tableName)
                .collect(Collectors.toList()));
        var ctxTable = importTable.createTableAndGet(d);
        if (ctxTable == null) {
          throw new Exception("Fault to import table.");
        }

        // dict
        Bucket.getInstance().getBucketDict().dbDictColumnTypeList.addAll(
            importTable.getNewDbDictColumnTypeList());
        Bucket.getInstance().getBucketDict().dbDictColumnList.addAll(
            importTable.getNewDbDictColumnList());
        Bucket.getInstance().getBucketDict().dbDictPartitionList.addAll(
            importTable.getNewDbDictPartitionList());

        // DbTableOption
        var dbTableOption = new DbTableOption();
        dbTableOption.tableId = ctxTable.dbTable.tableId;
        dbTableOption.posX = point.x + offset;
        dbTableOption.posY = point.y + offset;
        con.executeInsert(dbTableOption);
        ctxTable.dbTableOption = dbTableOption;

        // foreign key
        var newCtxInnerForeignKeyList = new ArrayList<CtxInnerForeignKey>();
        var foreginKeyDdlList = importTable.getPartialForeignKeyDdlList();
        for (var fd : foreginKeyDdlList) {
          var importForeignKey = new ImportForeignKey(con);
          var ctxInnerForeignKey = importForeignKey.createForeignKeyAndGet(
              fd,
              Bucket.getInstance().getBucketDict().dbDictColumnList);
          if (ctxInnerForeignKey == null) {
            throw new Exception("Fault to import foreign key.");
          }
          newCtxInnerForeignKeyList.add(ctxInnerForeignKey);
        }
        ctxTable.ctxInnerForeignKeyList = newCtxInnerForeignKeyList;

        newCtxTableList.add(ctxTable);

        offset += 50;
      }
      con.commit();

      // memory
      for (var ctxTable : newCtxTableList) {
        ctxTableList.add(ctxTable);
      }

      return newCtxTableList;

    } catch (Exception e) {
      con.rollback();
      Log.error(e);

      throw e;
    }
  }

  /**
   * import from foreign key ddl.
   *
   * @param ddl ddl
   * @return ctx list
   * @throws Exception
   */
  public List<CtxInnerForeignKey> importFromForeignKeyDdl(String ddl) throws Exception {
    try {
      // database
      con.begin();

      var newCtxInnerForeignKeyList = new ArrayList<CtxInnerForeignKey>();
      var ddlList = Dump.parseAlterTable(ddl);
      for (var d : ddlList) {
        var importForeignKey = new ImportForeignKey(con);
        var ctxInnerForeignKey = importForeignKey.createForeignKeyAndGet(
            d,
            Bucket.getInstance().getBucketDict().dbDictColumnList);
        if (ctxInnerForeignKey == null) {
          throw new Exception("Fault to import foreign key.");
        }
        newCtxInnerForeignKeyList.add(ctxInnerForeignKey);
      }
      con.commit();

      // memory
      for (var ctxInnerForeignKey : newCtxInnerForeignKeyList) {
        ctxTableList.stream()
            .filter(c -> c.dbTable.tableId.equals(ctxInnerForeignKey.dbTableForeignKey.tableId))
            .findFirst()
            .get()
            .ctxInnerForeignKeyList.add(ctxInnerForeignKey);
      }

      return newCtxInnerForeignKeyList;

    } catch (Exception e) {
      con.rollback();
      Log.error(e);

      throw e;
    }
  }

  /**
   * save bulk.
   *
   * @param newDbTableList          new db table list
   * @param newDbTableOptionList    new db table option list
   * @param newDbTableGroupList     new db table group list
   * @param newDbTablePartitionList new db table partition list
   * @throws Exception
   */
  public void saveBulk(List<DbTable> newDbTableList,
      List<DbTableOption> newDbTableOptionList,
      List<DbTableGroup> newDbTableGroupList,
      List<DbTablePartition> newDbTablePartitionList)
      throws Exception {
    try {
      con.begin();

      // database
      for (var newDbTable : newDbTableList) {
        // auto increment
        if (!Utils.isNullOrEmpty(newDbTable.autoIncrementValue)) {
          for (var ctxTable : Bucket.getInstance().getBucketTable().ctxTableList) {
            if (!ctxTable.dbTable.tableId.equals(newDbTable.tableId)) {
              continue;
            }
            boolean existsAutoIncrement = false;
            for (var dbTableColumn : ctxTable.dbTableColumnList.stream()
                .filter(d -> d.tableId.equals(newDbTable.tableId))
                .collect(Collectors.toList())) {
              var opt = Bucket.getInstance().getBucketDict().dbDictColumnList.stream()
                  .filter(d -> d.dictColumnId.equals(dbTableColumn.dictColumnId))
                  .filter(d -> !Utils.isNullOrEmpty(d.autoIncrementDefinition))
                  .findFirst();
              if (opt.isPresent()) {
                existsAutoIncrement = true;
                break;
              }
            }
            if (!existsAutoIncrement) {
              throw new Exception("Undefined auto increment column.");
            }
          }
        }
        con.executeUpdate(newDbTable);
      }
      for (var newDbTableOption : newDbTableOptionList) {
        con.executeUpdate(newDbTableOption);
      }

      con.execute("DELETE FROM DbTableGroup");
      for (var newDbTableGroup : newDbTableGroupList) {
        var dbDictGroup = Bucket.getInstance().getBucketDict().dbDictGroupList.stream()
            .filter(d -> d.dictGroupId.equals(newDbTableGroup.dictGroupId))
            .findFirst()
            .get();
        var groupDictColumnIdList = Bucket.getInstance()
            .getBucketDict().dbDictGroupColumnList.stream()
            .filter(d -> d.dictGroupId.equals(dbDictGroup.dictGroupId))
            .map(d -> d.dictColumnId)
            .collect(Collectors.toList());
        var groupColumnNameList = Bucket.getInstance().getBucketDict().dbDictColumnList.stream()
            .filter(d -> groupDictColumnIdList.contains(d.dictColumnId))
            .map(d -> d.columnName)
            .collect(Collectors.toList());

        for (var ctxTable : Bucket.getInstance().getBucketTable().ctxTableList) {
          if (!ctxTable.dbTable.tableId.equals(newDbTableGroup.tableId)) {
            continue;
          }
          var dictColumnIdList = ctxTable.dbTableColumnList.stream()
              .filter(d -> d.tableId.equals(newDbTableGroup.tableId))
              .map(d -> d.dictColumnId)
              .collect(Collectors.toList());
          var columnNameList = Bucket.getInstance().getBucketDict().dbDictColumnList.stream()
              .filter(d -> dictColumnIdList.contains(d.dictColumnId))
              .map(d -> d.columnName)
              .collect(Collectors.toList());

          for (var columnName : columnNameList) {
            if (groupColumnNameList.contains(columnName)) {
              throw new Exception("Duplicate column name.");
            }
          }
        }
        con.executeInsert(newDbTableGroup);
      }

      con.execute("DELETE FROM DbTablePartition");
      for (var newDbTablePartition : newDbTablePartitionList) {
        con.executeInsert(newDbTablePartition);
      }

      con.commit();

      // memory
      for (var newDbTable : newDbTableList) {
        var opt = Bucket.getInstance().getBucketTable()
            .ctxTableList
            .stream()
            .filter(c -> c.dbTable.tableId.equals(newDbTable.tableId))
            .findFirst();
        if (!opt.isPresent()) {
          throw new Exception("Not found table.");
        }
        opt.get().dbTable = newDbTable;
        opt.get().dbTableGroup = null;
        opt.get().dbTablePartition = null;
      }
      for (var newDbTableOption : newDbTableOptionList) {
        var opt = Bucket.getInstance().getBucketTable()
            .ctxTableList
            .stream()
            .filter(c -> c.dbTableOption.tableId.equals(newDbTableOption.tableId))
            .findFirst();
        if (!opt.isPresent()) {
          throw new Exception("Not found table.");
        }
        opt.get().dbTableOption = newDbTableOption;
      }
      for (var newDbTableGroup : newDbTableGroupList) {
        var opt = Bucket.getInstance().getBucketTable()
            .ctxTableList
            .stream()
            .filter(c -> c.dbTable.tableId.equals(newDbTableGroup.tableId))
            .findFirst();
        opt.get().dbTableGroup = newDbTableGroup;
      }
      for (var newDbTablePartition : newDbTablePartitionList) {
        var opt = Bucket.getInstance().getBucketTable()
            .ctxTableList
            .stream()
            .filter(c -> c.dbTable.tableId.equals(newDbTablePartition.tableId))
            .findFirst();
        opt.get().dbTablePartition = newDbTablePartition;
      }

    } catch (Exception e) {
      con.rollback();
      Log.error(e);

      throw e;
    }
  }
}
