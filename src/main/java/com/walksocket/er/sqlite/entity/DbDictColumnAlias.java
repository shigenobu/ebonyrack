package com.walksocket.er.sqlite.entity;

import com.walksocket.er.Utils;
import com.walksocket.er.sqlite.Entity;
import com.walksocket.er.sqlite.Record;
import java.util.List;

/**
 * DbDictColumnAlias.
 */
public class DbDictColumnAlias extends Entity {

  /**
   * dictColumnId.
   */
  public String dictColumnId;

  /**
   * explanation.
   */
  public String explanation = "";

  /**
   * alias1.
   */
  public String alias1 = "";

  /**
   * alias2.
   */
  public String alias2 = "";

  /**
   * alias3.
   */
  public String alias3 = "";

  @Override
  public void bind(Record record) {
    dictColumnId = record.getOrEmpty("dictColumnId");
    explanation = record.getOrEmpty("explanation");
    alias1 = record.getOrEmpty("alias1");
    alias2 = record.getOrEmpty("alias2");
    alias3 = record.getOrEmpty("alias3");
  }

  @Override
  public String createInsert() {
    return String.format(
        "INSERT INTO DbDictColumnAlias "
            + "(dictColumnId, explanation, alias1, alias2, alias3) "
            + "VALUES "
            + "('%s', '%s', '%s', '%s', '%s')",
        Utils.quote(dictColumnId),
        Utils.quote(explanation),
        Utils.quote(alias1),
        Utils.quote(alias2),
        Utils.quote(alias3)
    );
  }

  @Override
  public String createUpdate() {
    return null;
  }

  @Override
  public String createDelete() {
    return String.format(
        "DELETE FROM DbDictColumnAlias "
            + "WHERE dictColumnId = '%s' ",
        Utils.quote(dictColumnId)
    );
  }

  @Override
  public List<String> orderColumns() {
    return List.of("dictColumnId");
  }

  /**
   * is valid.
   *
   * @return if valid, return true.
   */
  public boolean isValid() {
    return !Utils.isNullOrEmpty(explanation)
        || !Utils.isNullOrEmpty(alias1)
        || !Utils.isNullOrEmpty(alias2)
        || !Utils.isNullOrEmpty(alias3);
  }
}
