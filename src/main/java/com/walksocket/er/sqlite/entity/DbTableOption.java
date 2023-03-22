package com.walksocket.er.sqlite.entity;

import com.walksocket.er.Utils;
import com.walksocket.er.sqlite.Entity;
import com.walksocket.er.sqlite.Record;
import java.awt.Color;

/**
 * DbTableOption.
 */
public class DbTableOption extends Entity {

  /**
   * tableId.
   */
  public String tableId;

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
  public int color = (new Color(255, 204, 204)).getRGB();

  @Override
  public void bind(Record record) {
    tableId = record.getOrEmpty("tableId");
    posX = record.getOrZeroByInt("posX");
    posY = record.getOrZeroByInt("posY");
    color = record.getOrZeroByInt("color");
  }

  @Override
  public String createInsert() {
    return String.format(
        "INSERT INTO DbTableOption "
            + "(tableId, posX, posY, color) "
            + "VALUES "
            + "('%s', %s, %s, %s)",
        Utils.quote(tableId),
        posX,
        posY,
        color
    );
  }

  @Override
  public String createUpdate() {
    return String.format(
        "UPDATE DbTableOption SET "
            + "posX = %s, "
            + "posY = %s, "
            + "color = %s "
            + "WHERE "
            + "tableId = '%s' ",
        posX,
        posY,
        color,
        Utils.quote(tableId)
    );
  }

  @Override
  public String createDelete() {
    return null;
  }
}
