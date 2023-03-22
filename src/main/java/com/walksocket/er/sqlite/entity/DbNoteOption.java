package com.walksocket.er.sqlite.entity;

import com.walksocket.er.Utils;
import com.walksocket.er.sqlite.Entity;
import com.walksocket.er.sqlite.Record;
import java.awt.Color;

/**
 * DbNoteOption.
 */
public class DbNoteOption extends Entity {

  /**
   * noteId.
   */
  public String noteId;

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
  public int color = (new Color(204, 255, 204)).getRGB();

  /**
   * width.
   */
  public int width = 100;

  /**
   * height.
   */
  public int height = 100;

  @Override
  public void bind(Record record) {
    noteId = record.getOrEmpty("noteId");
    posX = record.getOrZeroByInt("posX");
    posY = record.getOrZeroByInt("posY");
    color = record.getOrZeroByInt("color");
    width = record.getOrZeroByInt("width");
    height = record.getOrZeroByInt("height");
  }

  @Override
  public String createInsert() {
    return String.format(
        "INSERT INTO DbNoteOption "
            + "(noteId, posX, posY, color, width, height) "
            + "VALUES "
            + "('%s', %s, %s, %s, %s, %s)",
        Utils.quote(noteId),
        posX,
        posY,
        color,
        width,
        height
    );
  }

  @Override
  public String createUpdate() {
    return String.format(
        "UPDATE DbNoteOption SET "
            + "posX = %s, "
            + "posY = %s, "
            + "color = %s, "
            + "width = %s, "
            + "height = %s "
            + "WHERE "
            + "noteId = '%s' ",
        posX,
        posY,
        color,
        width,
        height,
        Utils.quote(noteId)
    );
  }

  @Override
  public String createDelete() {
    return null;
  }
}
