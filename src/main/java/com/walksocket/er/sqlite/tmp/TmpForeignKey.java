package com.walksocket.er.sqlite.tmp;

import com.walksocket.er.parts.ColumnForeignKeyOption;
import com.walksocket.er.sqlite.Tmp;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * TmpForeignKey.
 */
public class TmpForeignKey implements Tmp {

  /**
   * constraintName.
   */
  public String constraintName;

  /**
   * keyName.
   */
  public String keyName;

  /**
   * columnForeignKeyOptionList.
   */
  public List<ColumnForeignKeyOption> columnForeignKeyOptionList = new ArrayList<>();

  /**
   * referenceTableName.
   */
  public String referenceTableName;

  /**
   * referenceColumnForeignKeyOptionList.
   */
  public List<ColumnForeignKeyOption> referenceColumnForeignKeyOptionList = new ArrayList<>();

  /**
   * onUpdate.
   */
  public String onUpdate;

  /**
   * onDelete.
   */
  public String onDelete;

  /**
   * relationship.
   */
  public String relationship;

  /**
   * get columns.
   *
   * @return columns
   */
  public String getColumns() {
    var columns = "";
    if (columnForeignKeyOptionList.size() > 0) {
      columns = columnForeignKeyOptionList.stream()
          .map(c -> c.getColumn())
          .collect(Collectors.joining(", "));
    }
    return columns;
  }

  /**
   * get reference columns.
   *
   * @return reference columns
   */
  public String getReferenceColumns() {
    var columns = "";
    if (referenceColumnForeignKeyOptionList.size() > 0) {
      columns = referenceColumnForeignKeyOptionList.stream()
          .map(c -> c.getColumn())
          .collect(Collectors.joining(", "));
    }
    return columns;
  }
}
