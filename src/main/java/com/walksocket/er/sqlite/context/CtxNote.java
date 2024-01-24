package com.walksocket.er.sqlite.context;

import com.walksocket.er.Copiable;
import com.walksocket.er.Date;
import com.walksocket.er.Json;
import com.walksocket.er.Utils;
import com.walksocket.er.Value;
import com.walksocket.er.sqlite.entity.DbNote;
import com.walksocket.er.sqlite.entity.DbNoteOption;

/**
 * CtxNote.
 */
public class CtxNote implements Value, Copiable<CtxNote> {

  /**
   * dbNote.
   */
  public DbNote dbNote;

  /**
   * dbNoteOption
   */
  public DbNoteOption dbNoteOption;

  @Override
  public CtxNote copy() {
    var newNoteId = Utils.randomString();
    var newCtxNote = Json.copy(this, CtxNote.class);

    // DbNote
    newCtxNote.dbNote.noteId = newNoteId;
    newCtxNote.dbNote.subject = String.format("CopyOf_%s_%s",
        newCtxNote.dbNote.subject,
        Date.timestamp());

    // DbNoteOption
    newCtxNote.dbNoteOption.noteId = newNoteId;

    return newCtxNote;
  }
}
