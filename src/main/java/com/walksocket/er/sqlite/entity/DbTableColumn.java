package com.walksocket.er.sqlite.entity;

import com.walksocket.er.Utils;
import com.walksocket.er.sqlite.Entity;
import com.walksocket.er.sqlite.Record;
import java.util.Arrays;
import java.util.List;

/**
 * DbTableColumn.
 */
public class DbTableColumn extends Entity {

  /**
   * tableId.
   */
  public String tableId;

  /**
   * ordinalPosition.
   */
  public int ordinalPosition;

  /**
   * dictColumnId.
   */
  public String dictColumnId;

  @Override
  public void bind(Record record) {
    tableId = record.getOrEmpty("tableId");
    ordinalPosition = record.getOrZeroByInt("ordinalPosition");
    dictColumnId = record.getOrEmpty("dictColumnId");
  }

  @Override
  public String createInsert() {
    return String.format(
        "INSERT INTO DbTableColumn "
            + "(tableId, ordinalPosition, dictColumnId) "
            + "VALUES "
            + "('%s', %s, '%s')",
        Utils.quote(tableId),
        ordinalPosition,
        Utils.quote(dictColumnId)
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
    return Arrays.asList("tableId", "ordinalPosition");
  }
}
