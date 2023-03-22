package com.walksocket.er.definition;

import java.util.ArrayList;
import java.util.List;

/**
 * Collation.
 */
public class Collation {

  /**
   * asc value.
   */
  public static final String ASC_VALUE = "ASC";

  /**
   * collation list for column.
   */
  private static final List<String> collationListForColumn = new ArrayList<>();

  static {
    collationListForColumn.add(ASC_VALUE);
    collationListForColumn.add("DESC");
  }

  /**
   * get collation list for column.
   *
   * @return index collation
   */
  public static List<String> getCollationListForColumn() {
    return collationListForColumn;
  }
}
