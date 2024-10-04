package com.walksocket.er.sqlite.entity;

import com.walksocket.er.Utils;
import com.walksocket.er.sqlite.Entity;
import com.walksocket.er.sqlite.Record;
import java.util.List;

/**
 * DbTable.
 */
public class DbTable extends Entity {

  /**
   * tableId.
   */
  public String tableId;

  /**
   * tableName.
   */
  public String tableName = "";

  /**
   * tableComment.
   */
  public String tableComment = "";

  /**
   * engine.
   */
  public String engine = "";

  /**
   * charsetValue.
   */
  public String charsetValue = "";

  /**
   * collateValue
   */
  public String collateValue = "";

  /**
   * autoIncrementValue.
   */
  public String autoIncrementValue = "";

  /**
   * option
   */
  public String option = "";

  @Override
  public void bind(Record record) {
    tableId = record.getOrEmpty("tableId");
    tableName = record.getOrEmpty("tableName");
    tableComment = record.getOrEmpty("tableComment");
    engine = record.getOrEmpty("engine");
    charsetValue = record.getOrEmpty("charsetValue");
    collateValue = record.getOrEmpty("collateValue");
    autoIncrementValue = record.getOrEmpty("autoIncrementValue");
    option = record.getOrEmpty("option");
  }

  @Override
  public String createInsert() {
    return String.format(
        "INSERT INTO DbTable "
            + "(tableId, tableName, tableComment, engine, charsetValue, collateValue, autoIncrementValue, option) "
            + "VALUES "
            + "('%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s')",
        Utils.quote(tableId),
        Utils.quote(tableName),
        Utils.quote(tableComment),
        Utils.quote(engine),
        Utils.quote(charsetValue),
        Utils.quote(collateValue),
        Utils.quote(autoIncrementValue),
        Utils.quote(option)
    );
  }

  @Override
  public String createUpdate() {
    return String.format(
        "UPDATE DbTable SET "
            + "tableName = '%s', "
            + "tableComment = '%s', "
            + "engine = '%s', "
            + "charsetValue = '%s', "
            + "collateValue = '%s', "
            + "autoIncrementValue = '%s', "
            + "option = '%s' "
            + "WHERE "
            + "tableId = '%s' ",
        Utils.quote(tableName),
        Utils.quote(tableComment),
        Utils.quote(engine),
        Utils.quote(charsetValue),
        Utils.quote(collateValue),
        Utils.quote(autoIncrementValue),
        Utils.quote(option),
        Utils.quote(tableId)
    );
  }

  @Override
  public String createDelete() {
    return String.format(
        "DELETE FROM DbTable "
            + "WHERE tableId = '%s' ",
        Utils.quote(tableId)
    );
  }

  @Override
  public List<String> orderColumns() {
    return List.of("tableId");
  }

  /**
   * get show table name.
   *
   * @return show table name
   */
  public String getShowTableName() {
    if (!Utils.isNullOrEmpty(tableComment)) {
      return tableName + " / " + tableComment;
    }
    return tableName;
  }

  /**
   * get tip text.
   *
   * @return tip text
   */
  public String getTipText() {
    return String.format("%s", getShowTableName());
  }

  /**
   * get table name for sort.
   *
   * @return table name
   */
  public String getTableNameForSort() {
    return tableName;
  }
}
