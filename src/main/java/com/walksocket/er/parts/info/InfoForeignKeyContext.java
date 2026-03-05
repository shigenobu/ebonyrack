package com.walksocket.er.parts.info;

import java.util.ArrayList;
import java.util.List;

/**
 * InfoForeignKeyContext.
 */
public class InfoForeignKeyContext {

  /**
   * constraintName.
   */
  public String constraintName = "";

  /**
   * keyName.
   */
  public String keyName = "";

  /**
   * columnNames.
   */
  public List<String> columnNames = new ArrayList<>();

  /**
   * referenceTableName.
   */
  public String referenceTableName = "";

  /**
   * referenceColumnNames.
   */
  public List<String> referenceColumnNames = new ArrayList<>();

  /**
   * onUpdate.
   */
  public String onUpdate = "";

  /**
   * onDelete.
   */
  public String onDelete = "";

  /**
   * relationship.
   */
  public String relationship = "";
}
