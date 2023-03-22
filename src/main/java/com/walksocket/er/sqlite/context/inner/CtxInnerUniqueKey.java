package com.walksocket.er.sqlite.context.inner;

import com.walksocket.er.sqlite.entity.DbTableUniqueKey;
import com.walksocket.er.sqlite.entity.DbTableUniqueKeyColumn;
import java.util.ArrayList;
import java.util.List;

/**
 * CtxInnerUniqueKey.
 */
public class CtxInnerUniqueKey {

  /**
   * dbTableUniqueKey.
   */
  public DbTableUniqueKey dbTableUniqueKey;

  /**
   * dbTableUniqueKeyColumnList.
   */
  public List<DbTableUniqueKeyColumn> dbTableUniqueKeyColumnList = new ArrayList<>();
}
