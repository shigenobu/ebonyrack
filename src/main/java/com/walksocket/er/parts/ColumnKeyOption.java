package com.walksocket.er.parts;

import com.walksocket.er.Log;
import com.walksocket.er.Utils;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * ColumnKeyOption.
 */
public class ColumnKeyOption {

  /**
   * create column key option list.
   *
   * @param columnsAndCollations columnsAndCollations
   * @return column key option list
   */
  public static List<ColumnKeyOption> createColumnKeyOptionList(String columnsAndCollations) {
    var columnKeyOptionList = new ArrayList<ColumnKeyOption>();
    var ccList = columnsAndCollations.split(", ");
    for (int i = 0; i < ccList.length; i++) {
      var cc = ccList[i];

      var pattern = Pattern.compile("^`(.+?)`(.+)$");
      var match = pattern.matcher(cc);
      if (!match.find()) {
        continue;
      }
      var columnKeyOption = new ColumnKeyOption();
      columnKeyOption.columnName = match.group(1);
      Log.trace(columnKeyOption.columnName);

      var lengthAndCollation = match.group(2).trim().split(" ");
      if (lengthAndCollation.length > 1) {
        var p = Pattern.compile("^\\((.+?)\\)$");
        var m = p.matcher(lengthAndCollation[0]);
        if (m.find()) {
          Log.trace(m);
          if (Utils.isNumber(m.group(1))) {
            columnKeyOption.length = m.group(1);
          }
        }
        columnKeyOption.collation = lengthAndCollation[1];
      } else {
        columnKeyOption.collation = lengthAndCollation[0];
      }

      columnKeyOption.seqInIndex = String.valueOf(i + 1);

      columnKeyOptionList.add(columnKeyOption);
    }
    return columnKeyOptionList;
  }

  /**
   * columnName.
   */
  public String columnName;

  /**
   * length.
   */
  public String length;

  /**
   * seqInIndex.
   */
  public String seqInIndex;

  /**
   * collation.
   */
  public String collation;

  /**
   * get column and collation.
   *
   * @return column and collation
   */
  public String getColumnAndCollation() {
    if (Utils.isNumber(length) && Integer.parseInt(length) > 0) {
      return String.format("`%s`(%s) %s", columnName, length, collation);
    }
    return String.format("`%s` %s", columnName, collation);
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
