package com.walksocket.er.sqlite.entity;

import com.walksocket.er.Utils;
import com.walksocket.er.sqlite.EntityKeyColumn;
import com.walksocket.er.sqlite.Record;
import java.util.Arrays;
import java.util.List;

/**
 * DbTableKeyColumn.
 */
public class DbTableKeyColumn extends EntityKeyColumn {

  @Override
  public void bind(Record record) {
    tableId = record.getOrEmpty("tableId");
    seq = record.getOrZeroByInt("seq");
    dictColumnId = record.getOrEmpty("dictColumnId");
    length = record.getOrEmpty("length");
    seqInIndex = record.getOrZeroByInt("seqInIndex");
    collation = record.getOrEmpty("collation");
  }

  @Override
  public String createInsert() {
    return String.format(
        "INSERT INTO DbTableKeyColumn "
            + "(tableId, seq, dictColumnId, length, seqInIndex, collation) "
            + "VALUES "
            + "('%s', %s, '%s', '%s', %s, '%s')",
        Utils.quote(tableId),
        seq,
        Utils.quote(dictColumnId),
        Utils.quote(length),
        seqInIndex,
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
