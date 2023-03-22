package com.walksocket.er.definition;

import java.util.ArrayList;
import java.util.List;

/**
 * NotNull.
 */
public class NotNull {

  /**
   * not null value.
   */
  public static final String NOT_NULL_VALUE = "NOT NULL";

  /**
   * null value.
   */
  public static final String NULL_VALUE = "NULL";

  /**
   * not null list for column.
   */
  private static final List<String> notNullListForColumn = new ArrayList<>();

  static {
    notNullListForColumn.add("");
    notNullListForColumn.add(NOT_NULL_VALUE);
    notNullListForColumn.add(NULL_VALUE);
  }

  /**
   * get not null list for columns.
   *
   * @return not null list
   */
  public static List<String> getNotNullListForColumn() {
    return notNullListForColumn;
  }
}
