package com.walksocket.er.definition;

import java.util.ArrayList;
import java.util.List;

/**
 * Collate.
 */
public class Collate {

  /**
   * default collate.
   */
  public static final String DEFAULT_COLLATE = "<Default>";

  /**
   * collate list for project.
   */
  private static final List<String> collateListForProject = new ArrayList<>();

  static {
    collateListForProject.add(0, "");
    collateListForProject.add("utf8mb4_bin");
    collateListForProject.add("utf8mb4_general_ci");
  }

  /**
   * collate list for table.
   */
  private static final List<String> collateListForTable = new ArrayList<>();

  static {
    collateListForTable.addAll(collateListForProject);
    collateListForTable.add(0, DEFAULT_COLLATE);
  }

  /**
   * collate list for column.
   */
  private static final List<String> collateListForColumn = new ArrayList<>();

  static {
    collateListForColumn.addAll(collateListForProject);
    collateListForColumn.add(0, "");
  }

  /**
   * get collate list for project.
   *
   * @return collate list
   */
  public static List<String> getCollateListForProject() {
    return collateListForProject;
  }

  /**
   * get collate list for table.
   *
   * @return collate list
   */
  public static List<String> getCollateListForTable() {
    return collateListForTable;
  }

  /**
   * get collate list for column.
   *
   * @return collate list
   */
  public static List<String> getCollateListForColumn() {
    return collateListForColumn;
  }
}
