package com.walksocket.er.sqlite.context;

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
public class CtxTable implements Value {

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
}
