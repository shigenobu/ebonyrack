package com.walksocket.er.sqlite.tmp;

import com.walksocket.er.Utils;
import com.walksocket.er.Value;
import com.walksocket.er.sqlite.Tmp;

/**
 * TmpPartition
 */
public class TmpPartition implements Tmp, Value {

  /**
   * expression.
   */
  public String expression;

  /**
   * get expression hash.
   *
   * @return expression hash
   */
  public String getExpressionHash() {
    return Utils.getHash(expression);
  }
}
