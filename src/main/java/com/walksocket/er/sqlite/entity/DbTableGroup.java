package com.walksocket.er.sqlite.entity;

import com.walksocket.er.Utils;
import com.walksocket.er.sqlite.Entity;
import com.walksocket.er.sqlite.Record;
import java.util.List;

/**
 * DbTableGroup.
 */
public class DbTableGroup extends Entity {

  /**
   * tableId.
   */
  public String tableId;

  /**
   * dictGroupId.
   */
  public String dictGroupId;

  @Override
  public void bind(Record record) {
    tableId = record.getOrEmpty("tableId");
    dictGroupId = record.getOrEmpty("dictGroupId");
  }

  @Override
  public String createInsert() {
    return String.format(
        "INSERT INTO DbTableGroup "
            + "(tableId, dictGroupId) "
            + "VALUES "
            + "('%s', '%s')",
        Utils.quote(tableId),
        Utils.quote(dictGroupId)
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
