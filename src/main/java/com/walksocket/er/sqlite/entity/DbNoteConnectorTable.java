package com.walksocket.er.sqlite.entity;

import com.walksocket.er.Utils;
import com.walksocket.er.sqlite.Entity;
import com.walksocket.er.sqlite.Record;

/**
 * DbNoteConnectorTable.
 */
public class DbNoteConnectorTable extends Entity {

  /**
   * noteId.
   */
  public String noteId;

  /**
   * tableId.
   */
  public String tableId;

  @Override
  public void bind(Record record) {
    noteId = record.getOrEmpty("noteId");
    tableId = record.getOrEmpty("tableId");
  }

  @Override
  public String createInsert() {
    return String.format(
        "INSERT INTO DbNoteConnectorTable "
            + "(noteId, tableId) "
            + "VALUES "
            + "('%s', '%s')",
        Utils.quote(noteId),
        Utils.quote(tableId)
    );
  }

  @Override
  public String createUpdate() {
    return null;
  }

  @Override
  public String createDelete() {
    return String.format(
        "DELETE FROM DbNoteConnectorTable "
            + "WHERE noteId = '%s' AND tableId = '%s'",
        Utils.quote(noteId),
        Utils.quote(tableId)
    );
  }
}
