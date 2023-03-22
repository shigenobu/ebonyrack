package com.walksocket.er.sqlite.entity;

import com.walksocket.er.Utils;
import com.walksocket.er.sqlite.Entity;
import com.walksocket.er.sqlite.Record;

/**
 * DbDictGroupColumn.
 */
public class DbDictGroupColumn extends Entity {

  /**
   * dictGroupId.
   */
  public String dictGroupId;

  /**
   * seq.
   */
  public int seq;

  /**
   * dictColumnId.
   */
  public String dictColumnId;

  @Override
  public void bind(Record record) {
    dictGroupId = record.getOrEmpty("dictGroupId");
    seq = record.getOrZeroByInt("seq");
    dictColumnId = record.getOrEmpty("dictColumnId");
  }

  @Override
  public String createInsert() {
    return String.format(
        "INSERT INTO DbDictGroupColumn "
            + "(dictGroupId, seq, dictColumnId) "
            + "VALUES "
            + "('%s', %s, '%s')",
        Utils.quote(dictGroupId),
        seq,
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
}
