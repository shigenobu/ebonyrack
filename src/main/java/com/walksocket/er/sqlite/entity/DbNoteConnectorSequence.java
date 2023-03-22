package com.walksocket.er.sqlite.entity;

import com.walksocket.er.Utils;
import com.walksocket.er.sqlite.Entity;
import com.walksocket.er.sqlite.Record;

/**
 * DbNoteConnectorSequence.
 */
public class DbNoteConnectorSequence extends Entity {

  /**
   * noteId.
   */
  public String noteId;

  /**
   * sequenceId.
   */
  public String sequenceId;

  @Override
  public void bind(Record record) {
    noteId = record.getOrEmpty("noteId");
    sequenceId = record.getOrEmpty("sequenceId");
  }

  @Override
  public String createInsert() {
    return String.format(
        "INSERT INTO DbNoteConnectorSequence "
            + "(noteId, sequenceId) "
            + "VALUES "
            + "('%s', '%s')",
        Utils.quote(noteId),
        Utils.quote(sequenceId)
    );
  }

  @Override
  public String createUpdate() {
    return null;
  }

  @Override
  public String createDelete() {
    return String.format(
        "DELETE FROM DbNoteConnectorSequence "
            + "WHERE noteId = '%s' AND sequenceId = '%s'",
        Utils.quote(noteId),
        Utils.quote(sequenceId)
    );
  }
}
