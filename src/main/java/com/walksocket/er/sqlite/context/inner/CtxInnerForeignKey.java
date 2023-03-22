package com.walksocket.er.sqlite.context.inner;

import com.walksocket.er.sqlite.entity.DbTableForeignKey;
import com.walksocket.er.sqlite.entity.DbTableForeignKeyColumn;
import java.util.ArrayList;
import java.util.List;

/**
 * CtxInnerForeignKey.
 */
public class CtxInnerForeignKey {

  /**
   * dbTableForeignKey.
   */
  public DbTableForeignKey dbTableForeignKey;

  /**
   * dbTableForeignKeyColumnList.
   */
  public List<DbTableForeignKeyColumn> dbTableForeignKeyColumnList = new ArrayList<>();
}
