package com.walksocket.er.sqlite.tmp;

import com.walksocket.er.Utils;
import com.walksocket.er.Word;
import com.walksocket.er.definition.DataType;
import com.walksocket.er.sqlite.Tmp;
import com.walksocket.er.sqlite.entity.DbDictColumnType;

/**
 * TmpColumn.
 */
public class TmpColumn implements Tmp {

  /**
   * columnName.
   */
  public String columnName;

  /**
   * columnComment.
   */
  public String columnComment;

  /**
   * columnType.
   */
  public String columnType;

  /**
   * notNullValue.
   */
  public String notNullValue;

  /**
   * charsetValue.
   */
  public String charsetValue;

  /**
   * collateValue.
   */
  public String collateValue;

  /**
   * defaultValue.
   */
  public String defaultValue;

  /**
   * onUpdate.
   */
  public String onUpdate;

  /**
   * autoIncrementDefinition.
   */
  public String autoIncrementDefinition;

  /**
   * option.
   */
  public String option;

  /**
   * is all empty.
   *
   * @return if empty, true
   */
  public boolean isAllEmpty() {
    return Utils.isNullOrEmpty(columnName)
        && Utils.isNullOrEmpty(columnComment)
        && Utils.isNullOrEmpty(columnType)
        && Utils.isNullOrEmpty(notNullValue)
        && Utils.isNullOrEmpty(charsetValue)
        && Utils.isNullOrEmpty(collateValue)
        && Utils.isNullOrEmpty(defaultValue)
        && Utils.isNullOrEmpty(onUpdate)
        && Utils.isNullOrEmpty(autoIncrementDefinition)
        && Utils.isNullOrEmpty(option);
  }

  /**
   * check and get error.
   *
   * @return error
   */
  public String checkAndGetError() {
    // column name
    if (Utils.isNullOrEmpty(columnName)) {
      return "Required 'Column name'.";
    }
    if (!Word.isValid(columnName)) {
      return "Invalid 'Column name'.";
    }

    // column type
    if (Utils.isNullOrEmpty(columnType)) {
      return "Required 'Column type'.";
    }

    // charset
    if (!Utils.isNullOrEmpty(charsetValue) && !DataType.isText(columnType)) {
      return "'Charset' is only on 'text' type.";
    }

    // collate
    if (!Utils.isNullOrEmpty(collateValue) && !DataType.isText(columnType)) {
      return "'Collate' is only on 'text' type.";
    }

    // on update
    if (!Utils.isNullOrEmpty(onUpdate) && !DataType.allowCurrentTimestamp(
        columnType)) {
      return "'On update' is allowed only on 'datetime' or 'timestamp' type.";
    }

    return "";
  }

  /**
   * get dict column checksum.
   *
   * @param dbDictColumnType dbDictColumnType
   * @return dict column checksum
   */
  public String getDictColumnChecksum(DbDictColumnType dbDictColumnType) {
    return Utils.getHash(String.format("%s-%s-%s-%s-%s-%s-%s-%s-%s-%s",
        columnName,
        columnComment,
        dbDictColumnType.dictColumnTypeId,
        notNullValue,
        charsetValue,
        collateValue,
        defaultValue,
        onUpdate,
        autoIncrementDefinition,
        option
    ));
  }
}
