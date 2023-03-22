package com.walksocket.er.sqlite.context.inner;

import com.walksocket.er.sqlite.entity.DbTableKey;
import com.walksocket.er.sqlite.entity.DbTableKeyColumn;
import java.util.ArrayList;
import java.util.List;

/**
 * CtxInnerKey.
 */
public class CtxInnerKey {

  /**
   * dbTableKey.
   */
  public DbTableKey dbTableKey;

  /**
   * dbTableKeyColumnList.
   */
  public List<DbTableKeyColumn> dbTableKeyColumnList = new ArrayList<>();
}
