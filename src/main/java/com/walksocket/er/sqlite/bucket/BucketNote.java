package com.walksocket.er.sqlite.bucket;

import com.walksocket.er.Log;
import com.walksocket.er.Pos;
import com.walksocket.er.sqlite.Connection;
import com.walksocket.er.sqlite.Entity;
import com.walksocket.er.sqlite.context.CtxNote;
import com.walksocket.er.sqlite.entity.DbNote;
import com.walksocket.er.sqlite.entity.DbNoteOption;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * BucketNote.
 */
public class BucketNote {

  /**
   * con.
   */
  private final Connection con;

  /**
   * ctxNoteList.
   */
  public List<CtxNote> ctxNoteList = new ArrayList<>();

  /**
   * Constructor.
   *
   * @param con con
   */
  public BucketNote(Connection con) {
    this.con = con;

    read();
  }

  /**
   * read.
   */
  public void read() {
    ctxNoteList.clear();

    try {
      // DbNote, DbNoteOption
      var sql = "SELECT * FROM "
          + "DbNote as t1 "
          + "join "
          + "DbNoteOption as t2 "
          + "ON t1.noteId = t2.noteId "
          + "ORDER BY t1.subject ASC";
      var records = con.getRecords(sql);
      for (var record : records) {
        var dbNote = Entity.convertEntity(record, DbNote.class);
        var dbNoteOption = Entity.convertEntity(record, DbNoteOption.class);
        Log.trace(dbNote);
        Log.trace(dbNoteOption);

        // modify pos
        if (dbNoteOption.posX < Pos.MIN) {
          dbNoteOption.posX = Pos.MIN;
        }
        if (dbNoteOption.posY < Pos.MIN) {
          dbNoteOption.posY = Pos.MIN;
        }
        if (dbNoteOption.posX > Pos.MAX) {
          dbNoteOption.posX = Pos.MAX;
        }
        if (dbNoteOption.posY > Pos.MAX) {
          dbNoteOption.posY = Pos.MAX;
        }

        // create context
        var ctxNote = new CtxNote();
        ctxNote.dbNote = dbNote;
        ctxNote.dbNoteOption = dbNoteOption;

        ctxNoteList.add(ctxNote);
      }

    } catch (SQLException e) {
      Log.error(e);
    }
  }

  /**
   * register.
   *
   * @param ctxNote ctxNote
   * @throws Exception
   */
  public void register(CtxNote ctxNote) throws Exception {
    try {
      // database
      con.begin();
      con.executeInsert(ctxNote.dbNote);
      con.executeInsert(ctxNote.dbNoteOption);
      con.commit();

      // memory
      ctxNoteList.add(ctxNote);

    } catch (Exception e) {
      con.rollback();
      Log.error(e);

      throw e;
    }
  }

  /**
   * register for copy.
   *
   * @param ctxNote ctxNote
   * @throws Exception
   */
  public void registerForCopy(CtxNote ctxNote) throws Exception {
    register(ctxNote);
  }

  /**
   * save.
   *
   * @param ctxNote ctxNote
   * @throws Exception
   */
  public void save(CtxNote ctxNote) throws Exception {
    try {
      // database
      con.begin();
      con.executeUpdate(ctxNote.dbNote);
      con.commit();

    } catch (Exception e) {
      con.rollback();
      Log.error(e);

      throw e;
    }
  }

  /**
   * remove.
   *
   * @param ctxNote ctxNote
   * @throws Exception
   */
  public void remove(CtxNote ctxNote) throws Exception {
    try {
      // database
      con.begin();
      con.executeDelete(ctxNote.dbNote);
      con.commit();

      // memory
      ctxNoteList.remove(ctxNote);

    } catch (Exception e) {
      con.rollback();
      Log.error(e);

      throw e;
    }
  }

  /**
   * save option.
   *
   * @param ctxNote ctxNote
   * @throws Exception
   */
  public void saveOption(CtxNote ctxNote) throws Exception {
    try {
      // database
      con.begin();
      con.executeUpdate(ctxNote.dbNoteOption);
      con.commit();
    } catch (Exception e) {
      con.rollback();
      Log.error(e);

      throw e;
    }
  }
}
