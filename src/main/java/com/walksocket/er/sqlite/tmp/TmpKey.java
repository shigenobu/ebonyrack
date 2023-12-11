package com.walksocket.er.sqlite.tmp;

import com.walksocket.er.Value;
import com.walksocket.er.parts.ColumnKeyOption;
import com.walksocket.er.sqlite.Tmp;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * TmpKey.
 */
public class TmpKey implements Tmp, Value {

  /**
   * constraintName.
   */
  public String constraintName;

  /**
   * keyName.
   */
  public String keyName;

  /**
   * columnKeyOptionList.
   */
  public List<ColumnKeyOption> columnKeyOptionList = new ArrayList<>();

  /**
   * indexComment.
   */
  public String indexComment;

  /**
   * indexType.
   */
  public String indexType;

  /**
   * get columns and collations.
   *
   * @return columns and collations
   */
  public String getColumnsAndCollations() {
    var columnsAndCollations = "";
    if (columnKeyOptionList.size() > 0) {
      columnsAndCollations = columnKeyOptionList.stream()
          .map(t -> t.getColumnAndCollation())
          .collect(Collectors.joining(", "));
    }
    return columnsAndCollations;
  }

  /**
   * get column names.
   *
   * @return column names
   */
  public List<String> getColumnNames() {
    return columnKeyOptionList.stream()
        .map(t -> t.columnName)
        .collect(Collectors.toList());
  }
}
