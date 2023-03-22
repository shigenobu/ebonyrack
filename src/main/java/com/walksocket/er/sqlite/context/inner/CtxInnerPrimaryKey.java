package com.walksocket.er.sqlite.context.inner;

import com.walksocket.er.sqlite.entity.DbTablePrimaryKey;
import com.walksocket.er.sqlite.entity.DbTablePrimaryKeyColumn;
import java.util.ArrayList;
import java.util.List;

/**
 * CtxInnerPrimaryKey.
 */
public class CtxInnerPrimaryKey {

  /**
   * dbTablePrimaryKey.
   */
  public DbTablePrimaryKey dbTablePrimaryKey;

  /**
   * dbTablePrimaryKeyColumnList.
   */
  public List<DbTablePrimaryKeyColumn> dbTablePrimaryKeyColumnList = new ArrayList<>();
}
