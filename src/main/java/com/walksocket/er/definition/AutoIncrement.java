package com.walksocket.er.definition;

import java.util.ArrayList;
import java.util.List;

/**
 * AutoIncrement.
 */
public class AutoIncrement {

  /**
   * auto increment for column.
   */
  private static final List<String> autoIncrementForColumn = new ArrayList<>();

  static {
    autoIncrementForColumn.add("");
    autoIncrementForColumn.add("AUTO_INCREMENT");
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
