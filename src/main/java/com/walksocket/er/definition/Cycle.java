package com.walksocket.er.definition;

import java.util.ArrayList;
import java.util.List;

/**
 * Cycle.
 */
public class Cycle {

  /**
   * no cycle value.
   */
  public static final String NOCYCLE_VALUE = "NOCYCLE";

  /**
   * cycle value.
   */
  public static final String CYCLE_VALUE = "CYCLE";

  /**
   * cycle list for column.
   */
  private static final List<String> cycleListForColumn = new ArrayList<>();

  static {
    cycleListForColumn.add(NOCYCLE_VALUE);
    cycleListForColumn.add(CYCLE_VALUE);
  }

  /**
   * get cycle list for columns.
   *
   * @return cycle list
   */
  public static List<String> getCycleListForColumn() {
    return cycleListForColumn;
  }
}
