package com.walksocket.er.sqlite.entity;

import com.walksocket.er.Utils;
import com.walksocket.er.sqlite.Entity;
import com.walksocket.er.sqlite.Record;
import java.util.List;

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

  /**
   * asExpression.
   */
  public boolean asExpression = false;

  @Override
  public void bind(Record record) {
    noteId = record.getOrEmpty("noteId");
    subject = record.getOrEmpty("subject");
    body = record.getOrEmpty("body");
    asExpression = record.getByBoolean("asExpression");
  }

  @Override
  public String createInsert() {
    return String.format(
        "INSERT INTO DbNote "
            + "(noteId, subject, body, asExpression) "
            + "VALUES "
            + "('%s', '%s', '%s', %s)",
        Utils.quote(noteId),
        Utils.quote(subject),
        Utils.quote(body),
        Utils.FromBooleanToInt(asExpression)
    );
  }

  @Override
  public String createUpdate() {
    return String.format(
        "UPDATE DbNote SET "
            + "subject = '%s', "
            + "body = '%s', "
            + "asExpression = %s "
            + "WHERE "
            + "noteId = '%s' ",
        Utils.quote(subject),
        Utils.quote(body),
        Utils.FromBooleanToInt(asExpression),
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

  @Override
  public List<String> orderColumns() {
    return List.of("noteId");
  }

  /**
   * get note subject for sort.
   *
   * @return note subject
   */
  public String getNoteSubjectForSort() {
    return subject + "-" + asExpression + "-" + body;
  }
}
