package com.walksocket.er.definition;

import java.util.ArrayList;
import java.util.List;

/**
 * AutoIncrement.
 */
public class AutoIncrement {

  /**
   * auto increment value.
   */
  public static final String AUTO_INCREMENT_VALUE = "AUTO_INCREMENT";

  /**
   * auto increment for column.
   */
  private static final List<String> autoIncrementForColumn = new ArrayList<>();

  static {
    autoIncrementForColumn.add("");
    autoIncrementForColumn.add(AUTO_INCREMENT_VALUE);
  }

  /**
   * get auto increment for columns.
   *
   * @return auto increment list
   */
  public static List<String> getAutoIncrementForColumn() {
    return autoIncrementForColumn;
  }
}
