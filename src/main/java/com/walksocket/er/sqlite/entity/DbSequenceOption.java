package com.walksocket.er.sqlite.entity;

import com.walksocket.er.Utils;
import com.walksocket.er.sqlite.Entity;
import com.walksocket.er.sqlite.Record;
import java.awt.Color;
import java.util.List;

/**
 * DbSequenceOption.
 */
public class DbSequenceOption extends Entity {

  /**
   * sequenceId.
   */
  public String sequenceId;

  /**
   * posX.
   */
  public int posX = 0;

  /**
   * posY.
   */
  public int posY = 0;

  /**
   * color.
   */
  public int color = (new Color(204, 255, 255)).getRGB();

  @Override
  public void bind(Record record) {
    sequenceId = record.getOrEmpty("sequenceId");
    posX = record.getOrZeroByInt("posX");
    posY = record.getOrZeroByInt("posY");
    color = record.getOrZeroByInt("color");
  }

  @Override
  public String createInsert() {
    return String.format(
        "INSERT INTO DBSequenceOption "
            + "(sequenceId, posX, posY, color) "
            + "VALUES "
            + "('%s', %s, %s, %s)",
        Utils.quote(sequenceId),
        posX,
        posY,
        color
    );
  }

  @Override
  public String createUpdate() {
    return String.format(
        "UPDATE DBSequenceOption SET "
            + "posX = %s, "
            + "posY = %s, "
            + "color = %s "
            + "WHERE "
            + "sequenceId = '%s' ",
        posX,
        posY,
        color,
        Utils.quote(sequenceId)
    );
  }

  @Override
  public String createDelete() {
    return null;
  }

  @Override
  public List<String> orderColumns() {
    return List.of("sequenceId");
  }
}
