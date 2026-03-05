package com.walksocket.er.parts.info;

import java.util.ArrayList;
import java.util.List;

/**
 * InfoForeignKey.
 */
public class InfoForeignKey {

  /**
   * tableName.
   */
  public String tableName = "";

  /**
   * foreignKeys.
   */
  public List<InfoForeignKeyContext> foreignKeys = new ArrayList<>();
}
