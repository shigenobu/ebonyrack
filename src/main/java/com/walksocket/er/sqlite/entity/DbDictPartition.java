package com.walksocket.er.sqlite.entity;

import com.walksocket.er.Utils;
import com.walksocket.er.sqlite.Entity;
import com.walksocket.er.sqlite.Record;
import java.util.List;

/**
 * DbDictPartition.
 */
public class DbDictPartition extends Entity {

  /**
   * dictPartitionId.
   */
  public String dictPartitionId;

  /**
   * partitionName.
   */
  public String partitionName;

  /**
   * expression.
   */
  public String expression;

  @Override
  public void bind(Record record) {
    dictPartitionId = record.getOrEmpty("dictPartitionId");
    partitionName = record.getOrEmpty("partitionName");
    expression = record.getOrEmpty("expression");
  }

  @Override
  public String createInsert() {
    return String.format(
        "INSERT INTO DbDictPartition "
            + "(dictPartitionId, partitionName, expression) "
            + "VALUES "
            + "('%s', '%s', '%s')",
        Utils.quote(dictPartitionId),
        Utils.quote(partitionName),
        Utils.quote(expression)
    );
  }

  @Override
  public String createUpdate() {
    return String.format(
        "UPDATE DbDictPartition SET "
            + "partitionName = '%s', "
            + "expression = '%s' "
            + "WHERE "
            + "dictPartitionId = '%s' ",
        Utils.quote(partitionName),
        Utils.quote(expression),
        Utils.quote(dictPartitionId)
    );
  }

  @Override
  public String createDelete() {
    return String.format(
        "DELETE FROM DbDictPartition "
            + "WHERE dictPartitionId = '%s' ",
        Utils.quote(dictPartitionId)
    );
  }

  @Override
  public List<String> orderColumns() {
    return List.of("dictPartitionId");
  }
}
