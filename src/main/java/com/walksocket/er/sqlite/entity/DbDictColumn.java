package com.walksocket.er.sqlite.entity;

import com.walksocket.er.Utils;
import com.walksocket.er.definition.AutoIncrement;
import com.walksocket.er.sqlite.Entity;
import com.walksocket.er.sqlite.Record;
import java.util.List;

/**
 * DbDictColumn.
 */
public class DbDictColumn extends Entity {

  /**
   * dictColumnId.
   */
  public String dictColumnId;

  /**
   * checksum.
   */
  private String checksum;

  /**
   * columnName.
   */
  public String columnName = "";

  /**
   * columnComment.
   */
  public String columnComment = "";

  /**
   * dictColumnTypeId.
   */
  public String dictColumnTypeId;

  /**
   * notNullValue.
   */
  public String notNullValue = "";

  /**
   * charsetValue.
   */
  public String charsetValue = "";

  /**
   * collateValue.
   */
  public String collateValue = "";

  /**
   * defaultValue.
   */
  public String defaultValue = "";

  /**
   * onUpdate.
   */
  public String onUpdate = "";

  /**
   * autoIncrementDefinition.
   */
  public String autoIncrementDefinition = "";

  /**
   * option.
   */
  public String option = "";

  @Override
  public void bind(Record record) {
    dictColumnId = record.getOrEmpty("dictColumnId");
    checksum = record.getOrEmpty("checksum");
    columnName = record.getOrEmpty("columnName");
    columnComment = record.getOrEmpty("columnComment");
    dictColumnTypeId = record.getOrEmpty("dictColumnTypeId");
    notNullValue = record.getOrEmpty("notNullValue");
    charsetValue = record.getOrEmpty("charsetValue");
    collateValue = record.getOrEmpty("collateValue");
    defaultValue = record.getOrEmpty("defaultValue");
    onUpdate = record.getOrEmpty("onUpdate");
    autoIncrementDefinition = record.getOrEmpty("autoIncrementDefinition");
    option = record.getOrEmpty("option");
  }

  @Override
  public String createInsert() {
    // set checksum
    setChecksum();

    return String.format(
        "INSERT INTO DbDictColumn "
            + "(dictColumnId, checksum, columnName, columnComment, dictColumnTypeId, notNullValue, charsetValue, collateValue, defaultValue, onUpdate, autoIncrementDefinition, option) "
            + "VALUES "
            + "('%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s')",
        Utils.quote(dictColumnId),
        Utils.quote(checksum),
        Utils.quote(columnName),
        Utils.quote(columnComment),
        Utils.quote(dictColumnTypeId),
        Utils.quote(notNullValue),
        Utils.quote(charsetValue),
        Utils.quote(collateValue),
        Utils.quote(defaultValue),
        Utils.quote(onUpdate),
        Utils.quote(autoIncrementDefinition),
        Utils.quote(option)
    );
  }

  @Override
  public String createUpdate() {
    // set checksum
    setChecksum();

    return String.format(
        "UPDATE DbDictColumn SET "
            + "checksum = '%s', "
            + "columnName = '%s', "
            + "columnComment = '%s', "
            + "dictColumnTypeId = '%s', "
            + "notNullValue = '%s', "
            + "charsetValue = '%s', "
            + "collateValue = '%s', "
            + "defaultValue = '%s', "
            + "onUpdate = '%s', "
            + "autoIncrementDefinition = '%s', "
            + "option = '%s' "
            + "WHERE "
            + "dictColumnId = '%s' ",
        Utils.quote(checksum),
        Utils.quote(columnName),
        Utils.quote(columnComment),
        Utils.quote(dictColumnTypeId),
        Utils.quote(notNullValue),
        Utils.quote(charsetValue),
        Utils.quote(collateValue),
        Utils.quote(defaultValue),
        Utils.quote(onUpdate),
        Utils.quote(autoIncrementDefinition),
        Utils.quote(option),
        Utils.quote(dictColumnId)
    );
  }

  @Override
  public String createDelete() {
    return String.format(
        "DELETE FROM DbDictColumn "
            + "WHERE dictColumnId = '%s' ",
        Utils.quote(dictColumnId)
    );
  }

  @Override
  public List<String> orderColumns() {
    return List.of("dictColumnId");
  }

  /**
   * set checksum.
   */
  private void setChecksum() {
    checksum = Utils.getHash(String.format("%s-%s-%s-%s-%s-%s-%s-%s-%s-%s",
        columnName,
        columnComment,
        dictColumnTypeId,
        notNullValue,
        charsetValue,
        collateValue,
        defaultValue,
        onUpdate,
        autoIncrementDefinition,
        option
    ));
  }

  /**
   * get show column name.
   *
   * @return show column name
   */
  public String getShowColumnName() {
    if (!Utils.isNullOrEmpty(columnComment)) {
      return columnName + " / " + columnComment;
    }
    return columnName;
  }

  /**
   * get tip text.
   *
   * @param dbDictColumnType dbDictColumnType
   * @return tip text
   */
  public String getTipText(DbDictColumnType dbDictColumnType) {
    var builder = new StringBuilder();
    builder.append("<html>");
    if (autoIncrementDefinition.equals(AutoIncrement.AUTO_INCREMENT_VALUE)) {
      builder.append("[AI] ");
    }
    builder.append(String.format("%s <b>%s</b> %s",
        getShowColumnName(),
        dbDictColumnType.columnType,
        notNullValue));
    builder.append("</html>");
    return builder.toString();
  }

  /**
   * get column name for sort.
   *
   * @return column name
   */
  public String getColumnNameForSort() {
    return columnName;
  }

  /**
   * get column comment for sort.
   *
   * @return column comment
   */
  public String getColumnCommentForSort() {
    return columnComment;
  }
}
