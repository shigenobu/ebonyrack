package com.walksocket.er.sqlite.tmp;

import com.walksocket.er.Value;
import com.walksocket.er.sqlite.Tmp;

/**
 * TmpTable.
 */
public class TmpTable implements Tmp, Value {

  /**
   * tableName.
   */
  public String tableName;

  /**
   * tableComment.
   */
  public String tableComment;

  /**
   * engine.
   */
  public String engine;

  /**
   * charsetValue.
   */
  public String charsetValue;

  /**
   * collateValue
   */
  public String collateValue;

  /**
   * autoIncrementValue.
   */
  public String autoIncrementValue;

  /**
   * option
   */
  public String option;
}
