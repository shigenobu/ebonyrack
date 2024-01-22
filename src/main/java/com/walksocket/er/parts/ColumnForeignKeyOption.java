package com.walksocket.er.parts;

import com.walksocket.er.Log;
import com.walksocket.er.Value;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * ColumnForeignKeyOption.
 */
public class ColumnForeignKeyOption implements Value {

  /**
   * create column foreign key option list.
   *
   * @param columns columns
   * @return column foreign key option list
   */
  public static List<ColumnForeignKeyOption> createColumnForeignKeyOptionList(String columns) {
    var columnForeignKeyOptionList = new ArrayList<ColumnForeignKeyOption>();
    var ccList = columns.split(", ");
    for (int i = 0; i < ccList.length; i++) {
      var cc = ccList[i];

      var pattern = Pattern.compile("^`(.+?)`$");
      var match = pattern.matcher(cc);
      if (!match.find()) {
        continue;
      }
      var columnForeignKeyOption = new ColumnForeignKeyOption();
      columnForeignKeyOption.columnName = match.group(1);
      columnForeignKeyOption.seqInIndex = String.valueOf(i + 1);
      Log.trace(columnForeignKeyOption.columnName);

      columnForeignKeyOptionList.add(columnForeignKeyOption);
    }
    return columnForeignKeyOptionList;
  }

  /**
   * columnName.
   */
  public String columnName;

  /**
   * seqInIndex.
   */
  public String seqInIndex;

  /**
   * get column.
   *
   * @return column
   */
  public String getColumn() {
    return String.format("`%s`", columnName);
  }

  /**
   * get seq in index for sort.
   *
   * @return seq in index
   */
  public int getSeqInIndexForSort() {
    return Integer.parseInt(seqInIndex);
  }

  /**
   * get column name for sort.
   *
   * @return column name
   */
  public String getColumnNameForSort() {
    return columnName;
  }
}
