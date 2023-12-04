package com.walksocket.er.sqlite.entity;

import com.walksocket.er.Utils;
import com.walksocket.er.sqlite.Entity;
import com.walksocket.er.sqlite.Record;
import java.util.Arrays;
import java.util.List;

/**
 * DbTableCheck.
 */
public class DbTableCheck extends Entity {

  /**
   * tableId.
   */
  public String tableId;

  /**
   * seq.
   */
  public int seq;

  /**
   * constraintName.
   */
  public String constraintName;

  /**
   * expression.
   */
  public String expression;

  @Override
  public void bind(Record record) {
    tableId = record.getOrEmpty("tableId");
    seq = record.getOrZeroByInt("seq");
    constraintName = record.getOrEmpty("constraintName");
    expression = record.getOrEmpty("expression");
  }

  @Override
  public String createInsert() {
    return String.format(
        "INSERT INTO DbTableCheck "
            + "(tableId, seq, constraintName, expression) "
            + "VALUES "
            + "('%s', %s, '%s', '%s')",
        Utils.quote(tableId),
        seq,
        Utils.quote(constraintName),
        Utils.quote(expression)
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
