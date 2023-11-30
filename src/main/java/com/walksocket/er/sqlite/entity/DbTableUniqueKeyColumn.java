package com.walksocket.er.sqlite.entity;

import com.walksocket.er.Utils;
import com.walksocket.er.sqlite.EntityKeyColumn;
import com.walksocket.er.sqlite.Record;
import java.util.Arrays;
import java.util.List;

/**
 * DbTableUniqueKeyColumn.
 */
public class DbTableUniqueKeyColumn extends EntityKeyColumn {

  @Override
  public void bind(Record record) {
    tableId = record.getOrEmpty("tableId");
    seq = record.getOrZeroByInt("seq");
    dictColumnId = record.getOrEmpty("dictColumnId");
    length = record.getOrEmpty("length");
    seqInIndex = record.getOrEmpty("seqInIndex");
    collation = record.getOrEmpty("collation");
  }

  @Override
  public String createInsert() {
    return String.format(
        "INSERT INTO DbTableUniqueKeyColumn "
            + "(tableId, seq, dictColumnId, length, seqInIndex, collation) "
            + "VALUES "
            + "('%s', %s, '%s', '%s', '%s', '%s')",
        Utils.quote(tableId),
        seq,
        Utils.quote(dictColumnId),
        Utils.quote(length),
        Utils.quote(seqInIndex),
        Utils.quote(collation)
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
