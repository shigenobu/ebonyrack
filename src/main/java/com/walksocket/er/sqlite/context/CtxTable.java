package com.walksocket.er.sqlite.context;

import com.walksocket.er.Copiable;
import com.walksocket.er.Date;
import com.walksocket.er.Json;
import com.walksocket.er.Utils;
import com.walksocket.er.Value;
import com.walksocket.er.sqlite.context.inner.CtxInnerForeignKey;
import com.walksocket.er.sqlite.context.inner.CtxInnerKey;
import com.walksocket.er.sqlite.context.inner.CtxInnerPrimaryKey;
import com.walksocket.er.sqlite.context.inner.CtxInnerUniqueKey;
import com.walksocket.er.sqlite.entity.DbTable;
import com.walksocket.er.sqlite.entity.DbTableCheck;
import com.walksocket.er.sqlite.entity.DbTableColumn;
import com.walksocket.er.sqlite.entity.DbTableGroup;
import com.walksocket.er.sqlite.entity.DbTableOption;
import com.walksocket.er.sqlite.entity.DbTablePartition;
import java.util.ArrayList;
import java.util.List;

/**
 * CtxTable.
 */
public class CtxTable implements Value, Copiable<CtxTable> {

  /**
   * dbTable.
   */
  public DbTable dbTable;

  /**
   * dbTableOption.
   */
  public DbTableOption dbTableOption;

  /**
   * dbTableGroup.
   */
  public DbTableGroup dbTableGroup;

  /**
   * dbTableColumnList.
   */
  public List<DbTableColumn> dbTableColumnList = new ArrayList<>();

  /**
   * DbTablePartition.
   */
  public DbTablePartition dbTablePartition;

  /**
   * ctxInnerPrimaryKey.
   */
  public CtxInnerPrimaryKey ctxInnerPrimaryKey = new CtxInnerPrimaryKey();

  /**
   * ctxInnerUniqueKeyList.
   */
  public List<CtxInnerUniqueKey> ctxInnerUniqueKeyList = new ArrayList<>();

  /**
   * ctxInnerKeyList.
   */
  public List<CtxInnerKey> ctxInnerKeyList = new ArrayList<>();

  /**
   * ctxInnerForeignKeyList.
   */
  public List<CtxInnerForeignKey> ctxInnerForeignKeyList = new ArrayList<>();

  /**
   * dbTableCheckList.
   */
  public List<DbTableCheck> dbTableCheckList = new ArrayList<>();

  @Override
  public CtxTable copy() {
    var newTableId = Utils.randomString();
    var newCtxTable = Json.copy(this, CtxTable.class);

    // DbTable
    newCtxTable.dbTable.tableId = newTableId;
    newCtxTable.dbTable.tableName = String.format("CopyOf_%s_%s",
        newCtxTable.dbTable.tableName,
        Date.timestamp());

    // DbTableOption
    newCtxTable.dbTableOption.tableId = newTableId;

    // DbTableGroup
    if (newCtxTable.dbTableGroup != null) {
      newCtxTable.dbTableGroup.tableId = newTableId;
    }

    // List<DbTableColumn>
    for (var newDbTableColumn : newCtxTable.dbTableColumnList) {
      newDbTableColumn.tableId = newTableId;
    }

    // DbTablePartition
    if (newCtxTable.dbTablePartition != null) {
      newCtxTable.dbTablePartition.tableId = newTableId;
    }

    // CtxInnerPrimaryKey
    if (newCtxTable.ctxInnerPrimaryKey.dbTablePrimaryKey != null) {
      newCtxTable.ctxInnerPrimaryKey.dbTablePrimaryKey.tableId = newTableId;
      for (var newDbTablePrimaryKeyColumn : newCtxTable.ctxInnerPrimaryKey.dbTablePrimaryKeyColumnList) {
        newDbTablePrimaryKeyColumn.tableId = newTableId;
      }
    }

    // List<CtxInnerUniqueKey>
    for (var newCtxInnerUniqueKey : newCtxTable.ctxInnerUniqueKeyList) {
      if (newCtxInnerUniqueKey.dbTableUniqueKey != null) {
        newCtxInnerUniqueKey.dbTableUniqueKey.tableId = newTableId;
        for (var newDbTableUniqueKeyColumn : newCtxInnerUniqueKey.dbTableUniqueKeyColumnList) {
          newDbTableUniqueKeyColumn.tableId = newTableId;
        }
      }
    }

    // List<CtxInnerKey>
    for (var newCtxInnerKey : newCtxTable.ctxInnerKeyList) {
      if (newCtxInnerKey.dbTableKey != null) {
        newCtxInnerKey.dbTableKey.tableId = newTableId;
        for (var newDbTableKeyColumn : newCtxInnerKey.dbTableKeyColumnList) {
          newDbTableKeyColumn.tableId = newTableId;
        }
      }
    }

    // List<CtxInnerForeignKey>
    newCtxTable.ctxInnerForeignKeyList = new ArrayList<>();

    // List<DbTableCheck>
    for (var newDbTableCheck : newCtxTable.dbTableCheckList) {
      newDbTableCheck.tableId = newTableId;
    }

    return newCtxTable;
  }
}
