package com.walksocket.er.definition;

import java.util.ArrayList;
import java.util.List;

/**
 * IndexType.
 */
public class IndexType {

  /**
   * btree value.
   */
  public static final String BTREE_VALUE = "BTREE";

  /**
   * index type list for column.
   */
  private static final List<String> indexTypeListForColumn = new ArrayList<>();

  static {
    indexTypeListForColumn.add("");
    indexTypeListForColumn.add(BTREE_VALUE);
  }

  /**
   * get index type list for column.
   *
   * @return index type list
   */
  public static List<String> getIndexTypeListForColumn() {
    return indexTypeListForColumn;
  }
}
