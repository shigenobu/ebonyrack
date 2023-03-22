package com.walksocket.er.sqlite.entity;

import com.walksocket.er.Utils;
import com.walksocket.er.sqlite.Entity;
import com.walksocket.er.sqlite.Record;

/**
 * DbDictColumnType.
 */
public class DbDictColumnType extends Entity {

  /**
   * dictColumnTypeId.
   */
  public String dictColumnTypeId;

  /**
   * seq.
   */
  public int seq;

  /**
   * columnType.
   */
  public String columnType;

  /**
   * remarks.
   */
  public String remarks = "";

  @Override
  public void bind(Record record) {
    dictColumnTypeId = record.getOrEmpty("dictColumnTypeId");
    seq = record.getOrZeroByInt("seq");
    columnType = record.getOrEmpty("columnType");
    remarks = record.getOrEmpty("remarks");
  }

  @Override
  public String createInsert() {
    return String.format(
        "INSERT INTO DbDictColumnType "
            + "(dictColumnTypeId, seq, columnType, remarks) "
            + "VALUES "
            + "('%s', %s, '%s', '%s')",
        Utils.quote(dictColumnTypeId),
        seq,
        Utils.quote(columnType),
        Utils.quote(remarks)
    );
  }

  @Override
  public String createUpdate() {
    return String.format(
        "UPDATE DbDictColumnType SET "
            + "seq = %s, "
            + "columnType = '%s', "
            + "remarks = '%s' "
            + "WHERE "
            + "dictColumnTypeId = '%s' ",
        seq,
        Utils.quote(columnType),
        Utils.quote(remarks),
        Utils.quote(dictColumnTypeId)
    );
  }

  @Override
  public String createDelete() {
    return String.format(
        "DELETE FROM DbDictColumnType "
            + "WHERE dictColumnTypeId = '%s' ",
        Utils.quote(dictColumnTypeId)
    );
  }
}
