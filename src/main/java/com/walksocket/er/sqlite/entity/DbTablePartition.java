package com.walksocket.er.sqlite.entity;

import com.walksocket.er.Utils;
import com.walksocket.er.sqlite.Entity;
import com.walksocket.er.sqlite.Record;
import java.util.List;

/**
 * DbTablePartition.
 */
public class DbTablePartition extends Entity {

  /**
   * tableId.
   */
  public String tableId;

  /**
   * dictPartitionId.
   */
  public String dictPartitionId;

  @Override
  public void bind(Record record) {
    tableId = record.getOrEmpty("tableId");
    dictPartitionId = record.getOrEmpty("dictPartitionId");
  }

  @Override
  public String createInsert() {
    return String.format(
        "INSERT INTO DbTablePartition "
            + "(tableId, dictPartitionId) "
            + "VALUES "
            + "('%s', '%s')",
        Utils.quote(tableId),
        Utils.quote(dictPartitionId)
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
    return List.of("tableId");
  }
}
