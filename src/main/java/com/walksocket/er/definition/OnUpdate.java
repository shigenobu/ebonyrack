package com.walksocket.er.definition;

import java.util.ArrayList;
import java.util.List;

/**
 * OnUpdate.
 */
public class OnUpdate {

  /**
   * on update list for column.
   */
  private static final List<String> onUpdateListForColumn = new ArrayList<>();

  static {
    onUpdateListForColumn.add("");
    onUpdateListForColumn.add("CURRENT_TIMESTAMP");
  }

  /**
   * get on update list for columns.
   *
   * @return on update list
   */
  public static List<String> getOnUpdateListForColumn() {
    return onUpdateListForColumn;
  }
}
