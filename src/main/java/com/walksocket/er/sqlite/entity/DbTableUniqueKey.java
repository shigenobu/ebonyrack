package com.walksocket.er.sqlite.entity;

import com.walksocket.er.Utils;
import com.walksocket.er.sqlite.EntityKey;
import com.walksocket.er.sqlite.Record;
import java.util.Arrays;
import java.util.List;

/**
 * DbTableUniqueKey.
 */
public class DbTableUniqueKey extends EntityKey {

  /**
   * constraintName.
   */
  public String constraintName;

  @Override
  public void bind(Record record) {
    tableId = record.getOrEmpty("tableId");
    seq = record.getOrZeroByInt("seq");
    constraintName = record.getOrEmpty("constraintName");
    keyName = record.getOrEmpty("keyName");
    indexComment = record.getOrEmpty("indexComment");
    indexType = record.getOrEmpty("indexType");
  }

  @Override
  public String createInsert() {
    return String.format(
        "INSERT INTO DbTableUniqueKey "
            + "(tableId, seq, constraintName, keyName, indexComment, indexType) "
            + "VALUES "
            + "('%s', %s, '%s', '%s', '%s', '%s')",
        Utils.quote(tableId),
        seq,
        Utils.quote(constraintName),
        Utils.quote(keyName),
        Utils.quote(indexComment),
        Utils.quote(indexType)
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
