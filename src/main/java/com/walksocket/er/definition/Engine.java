package com.walksocket.er.definition;

import java.util.ArrayList;
import java.util.List;

/**
 * Engine.
 */
public class Engine {

  /**
   * default engine.
   */
  public static final String DEFAULT_ENGINE = "<Default>";

  /**
   * engine list for project.
   */
  private static final List<String> engineListForProject = new ArrayList<>();

  static {
    engineListForProject.add("");
    engineListForProject.add("InnoDB");
  }

  /**
   * engine list for table.
   */
  private static final List<String> engineListForTable = new ArrayList<>();

  static {
    engineListForTable.add(DEFAULT_ENGINE);
    engineListForTable.add("InnoDB");
  }

  /**
   * get engine list for project.
   *
   * @return engine list
   */
  public static List<String> getEngineListForProject() {
    return engineListForProject;
  }

  /**
   * get engine list for table.
   *
   * @return engine list
   */
  public static List<String> getEngineListForTable() {
    return engineListForTable;
  }
}
