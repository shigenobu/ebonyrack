package com.walksocket.er.definition;

import java.util.ArrayList;
import java.util.List;

/**
 * Charset.
 */
public class Charset {

  /**
   * default charset.
   */
  public static final String DEFAULT_CHARSET = "<Default>";

  /**
   * charset list for project.
   */
  private static final List<String> charsetListForProject = new ArrayList<>();

  static {
    charsetListForProject.add(0, "");
    charsetListForProject.add("utf8mb4");
  }

  /**
   * charset list for table.
   */
  private static final List<String> charsetListForTable = new ArrayList<>();

  static {
    charsetListForTable.addAll(charsetListForProject);
    charsetListForTable.add(0, DEFAULT_CHARSET);
  }

  /**
   * charset list for column.
   */
  private static final List<String> charsetListForColumn = new ArrayList<>();

  static {
    charsetListForColumn.addAll(charsetListForProject);
    charsetListForColumn.add(0, "");
  }

  /**
   * get charset list for project.
   *
   * @return charset list
   */
  public static List<String> getCharsetListForProject() {
    return charsetListForProject;
  }

  /**
   * get charset list for table.
   *
   * @return charset list
   */
  public static List<String> getCharsetListForTable() {
    return charsetListForTable;
  }

  /**
   * get charset list for column.
   *
   * @return charset list
   */
  public static List<String> getCharsetListForColumn() {
    return charsetListForColumn;
  }
}
