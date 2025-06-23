package com.walksocket.er.sqlite.entity;

import com.walksocket.er.Utils;
import com.walksocket.er.sqlite.EntityKeyColumn;
import com.walksocket.er.sqlite.Record;
import java.util.Arrays;
import java.util.List;

/**
 * DbTableForeignKeyColumn.
 */
public class DbTableForeignKeyColumn extends EntityKeyColumn {

  /**
   * referenceDictColumnId.
   */
  public String referenceDictColumnId;

  @Override
  public void bind(Record record) {
    tableId = record.getOrEmpty("tableId");
    seq = record.getOrZeroByInt("seq");
    dictColumnId = record.getOrEmpty("dictColumnId");
    referenceDictColumnId = record.getOrEmpty("referenceDictColumnId");
    seqInIndex = record.getOrZeroByInt("seqInIndex");
  }

  @Override
  public String createInsert() {
    return String.format(
        "INSERT INTO DbTableForeignKeyColumn "
            + "(tableId, seq, dictColumnId, referenceDictColumnId, seqInIndex) "
            + "VALUES "
            + "('%s', %s, '%s', '%s', %s)",
        Utils.quote(tableId),
        seq,
        Utils.quote(dictColumnId),
        Utils.quote(referenceDictColumnId),
        seqInIndex
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
    return Arrays.asList("tableId", "seq", "dictColumnId");
  }
}
