package com.walksocket.er.sqlite.entity;

import com.walksocket.er.Utils;
import com.walksocket.er.sqlite.Entity;
import com.walksocket.er.sqlite.Record;
import java.util.List;

/**
 * DbDefault.
 */
public class DbDefault extends Entity {

  /**
   * defaultId.
   */
  private final String defaultId = "default";

  /**
   * defaultEngine.
   */
  public String defaultEngine = "";

  /**
   * defaultCharset.
   */
  public String defaultCharset = "";

  /**
   * defaultCollate
   */
  public String defaultCollate = "";

  @Override
  public void bind(Record record) {
    defaultEngine = record.getOrEmpty("defaultEngine");
    defaultCharset = record.getOrEmpty("defaultCharset");
    defaultCollate = record.getOrEmpty("defaultCollate");
  }

  @Override
  public String createInsert() {
    return String.format(
        "INSERT INTO DbDefault "
            + "(defaultId, defaultEngine, defaultCharset, defaultCollate) "
            + "VALUES "
            + "('%s', '%s', '%s', '%s')",
        Utils.quote(defaultId),
        Utils.quote(defaultEngine),
        Utils.quote(defaultCharset),
        Utils.quote(defaultCollate)
    );
  }

  @Override
  public String createUpdate() {
    return String.format(
        "UPDATE DbDefault SET "
            + "defaultEngine = '%s', "
            + "defaultCharset = '%s', "
            + "defaultCollate = '%s' "
            + "WHERE "
            + "defaultId = '%s' ",
        Utils.quote(defaultEngine),
        Utils.quote(defaultCharset),
        Utils.quote(defaultCollate),
        Utils.quote(defaultId)
    );
  }

  @Override
  public String createDelete() {
    return null;
  }

  @Override
  public List<String> orderColumns() {
    return List.of("defaultId");
  }
}
