package com.walksocket.er.sqlite.entity;

import com.walksocket.er.Utils;
import com.walksocket.er.definition.Cycle;
import com.walksocket.er.sqlite.Entity;
import com.walksocket.er.sqlite.Record;
import java.util.List;

/**
 * DbSequence.
 */
public class DbSequence extends Entity {

  /**
   * sequenceId.
   */
  public String sequenceId;

  /**
   * sequenceName
   */
  public String sequenceName;

  /**
   * startValue.
   */
  public String startValue = "1";

  /**
   * minimumValue.
   */
  public String minimumValue = "1";

  /**
   * maximumValue.
   */
  public String maximumValue = "9223372036854775806";

  /**
   * incrementValue.
   */
  public String incrementValue = "1";

  /**
   * cacheSize.
   */
  public String cacheSize = "1000";

  /**
   * cycle.
   */
  public String cycle = Cycle.NOCYCLE_VALUE;

  @Override
  public void bind(Record record) {
    sequenceId = record.getOrEmpty("sequenceId");
    sequenceName = record.getOrEmpty("sequenceName");
    startValue = record.getOrEmpty("startValue");
    minimumValue = record.getOrEmpty("minimumValue");
    maximumValue = record.getOrEmpty("maximumValue");
    incrementValue = record.getOrEmpty("incrementValue");
    cacheSize = record.getOrEmpty("cacheSize");
    cycle = record.getOrEmpty("cycle");
  }

  @Override
  public String createInsert() {
    return String.format(
        "INSERT INTO DbSequence "
            + "(sequenceId, sequenceName, startValue, minimumValue, maximumValue, incrementValue, cacheSize, cycle) "
            + "VALUES "
            + "('%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s')",
        Utils.quote(sequenceId),
        Utils.quote(sequenceName),
        Utils.quote(startValue),
        Utils.quote(minimumValue),
        Utils.quote(maximumValue),
        Utils.quote(incrementValue),
        Utils.quote(cacheSize),
        Utils.quote(cycle)
    );
  }

  @Override
  public String createUpdate() {
    return String.format(
        "UPDATE DbSequence SET "
            + "sequenceName = '%s', "
            + "startValue = '%s', "
            + "minimumValue = '%s', "
            + "maximumValue = '%s', "
            + "incrementValue = '%s', "
            + "cacheSize = '%s', "
            + "cycle = '%s' "
            + "WHERE "
            + "sequenceId = '%s' ",
        Utils.quote(sequenceName),
        Utils.quote(startValue),
        Utils.quote(minimumValue),
        Utils.quote(maximumValue),
        Utils.quote(incrementValue),
        Utils.quote(cacheSize),
        Utils.quote(cycle),
        Utils.quote(sequenceId)
    );
  }

  @Override
  public String createDelete() {
    return String.format(
        "DELETE FROM DbSequence "
            + "WHERE sequenceId = '%s' ",
        Utils.quote(sequenceId)
    );
  }

  @Override
  public List<String> orderColumns() {
    return List.of("sequenceId");
  }

  /**
   * get tip text.
   *
   * @return tip text
   */
  public String getTipText() {
    return sequenceName;
  }
}
