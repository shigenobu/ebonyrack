package com.walksocket.er.sqlite.entity;

import com.walksocket.er.Utils;
import com.walksocket.er.sqlite.Entity;
import com.walksocket.er.sqlite.Record;

/**
 * DbNote.
 */
public class DbNote extends Entity {

  /**
   * noteId.
   */
  public String noteId;

  /**
   * subject.
   */
  public String subject = "";

  /**
   * body.
   */
  public String body = "";


  @Override
  public void bind(Record record) {
    noteId = record.getOrEmpty("noteId");
    subject = record.getOrEmpty("subject");
    body = record.getOrEmpty("body");
  }

  @Override
  public String createInsert() {
    return String.format(
        "INSERT INTO DbNote "
            + "(noteId, subject, body) "
            + "VALUES "
            + "('%s', '%s', '%s')",
        Utils.quote(noteId),
        Utils.quote(subject),
        Utils.quote(body)
    );
  }

  @Override
  public String createUpdate() {
    return String.format(
        "UPDATE DbNote SET "
            + "subject = '%s', "
            + "body = '%s' "
            + "WHERE "
            + "noteId = '%s' ",
        Utils.quote(subject),
        Utils.quote(body),
        Utils.quote(noteId)
    );
  }

  @Override
  public String createDelete() {
    return String.format(
        "DELETE FROM DbNote "
            + "WHERE noteId = '%s' ",
        Utils.quote(noteId)
    );
  }
}
