package com.walksocket.er.sqlite.tmp;

import com.walksocket.er.Utils;
import com.walksocket.er.sqlite.Tmp;

/**
 * TmpCheck.
 */
public class TmpCheck implements Tmp {

  /**
   * constraintName.
   */
  public String constraintName;

  /**
   * expression.
   */
  public String expression;

  /**
   * is all empty.
   *
   * @return if empty, true
   */
  public boolean isAllEmpty() {
    return Utils.isNullOrEmpty(constraintName)
        && Utils.isNullOrEmpty(expression);
  }
}
