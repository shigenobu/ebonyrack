package com.walksocket.er.sqlite.entity;

import com.walksocket.er.Utils;
import com.walksocket.er.sqlite.EntityKey;
import com.walksocket.er.sqlite.Record;
import java.util.Arrays;
import java.util.List;

/**
 * DbTableForeignKey.
 */
public class DbTableForeignKey extends EntityKey {

  /**
   * constraintName.
   */
  public String constraintName;

  /**
   * referenceTableId.
   */
  public String referenceTableId;

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

  @Override
  public void bind(Record record) {
    tableId = record.getOrEmpty("tableId");
    seq = record.getOrZeroByInt("seq");
    constraintName = record.getOrEmpty("constraintName");
    keyName = record.getOrEmpty("keyName");
    referenceTableId = record.getOrEmpty("referenceTableId");
    onUpdate = record.getOrEmpty("onUpdate");
    onDelete = record.getOrEmpty("onDelete");
    relationship = record.getOrEmpty("relationship");
  }

  @Override
  public String createInsert() {
    return String.format(
        "INSERT INTO DbTableForeignKey "
            + "(tableId, seq, constraintName, keyName, referenceTableId, onUpdate, onDelete, relationship) "
            + "VALUES "
            + "('%s', %s, '%s', '%s', '%s', '%s', '%s', '%s')",
        Utils.quote(tableId),
        seq,
        Utils.quote(constraintName),
        Utils.quote(keyName),
        Utils.quote(referenceTableId),
        Utils.quote(onUpdate),
        Utils.quote(onDelete),
        Utils.quote(relationship)
    );
  }

  @Override
  public String createUpdate() {
    return null;
  }

  @Override
  public String createDelete() {
    return null;
  }

  @Override
  public List<String> orderColumns() {
    return Arrays.asList("tableId", "seq");
  }
}
