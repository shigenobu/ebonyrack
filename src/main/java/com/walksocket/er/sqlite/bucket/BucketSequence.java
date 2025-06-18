package com.walksocket.er.sqlite.bucket;

import com.walksocket.er.Log;
import com.walksocket.er.Pos;
import com.walksocket.er.sqlite.Bucket;
import com.walksocket.er.sqlite.Connection;
import com.walksocket.er.sqlite.Entity;
import com.walksocket.er.sqlite.ImportSequence;
import com.walksocket.er.sqlite.context.CtxSequence;
import com.walksocket.er.sqlite.entity.DbSequence;
import com.walksocket.er.sqlite.entity.DbSequenceOption;
import com.walksocket.er.sqlite.tmp.TmpSequence;
import java.awt.Point;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * BucketSequence.
 */
public class BucketSequence {

  /**
   * con.
   */
  private final Connection con;

  /**
   * ctxSequenceList.
   */
  public List<CtxSequence> ctxSequenceList = new ArrayList<>();

  /**
   * Constructor.
   *
   * @param con con
   */
  public BucketSequence(Connection con) {
    this.con = con;

    read();
  }

  /**
   * read.
   */
  public void read() {
    ctxSequenceList.clear();

    try {
      // DbSequence, DBSequenceOption
      var sql = "SELECT * FROM "
          + "DbSequence as t1 "
          + "join "
          + "DBSequenceOption as t2 "
          + "ON t1.sequenceId = t2.sequenceId "
          + "ORDER BY t1.sequenceName ASC";
      var records = con.getRecords(sql);
      for (var record : records) {
        var dbSequence = Entity.convertEntity(record, DbSequence.class);
        var dbSequenceOption = Entity.convertEntity(record, DbSequenceOption.class);
        Log.trace(dbSequence);
        Log.trace(dbSequenceOption);

        // modify pos
        if (dbSequenceOption.posX < Pos.MIN) {
          dbSequenceOption.posX = Pos.MIN;
        }
        if (dbSequenceOption.posY < Pos.MIN) {
          dbSequenceOption.posY = Pos.MIN;
        }
        if (dbSequenceOption.posX > Pos.MAX) {
          dbSequenceOption.posX = Pos.MAX;
        }
        if (dbSequenceOption.posY > Pos.MAX) {
          dbSequenceOption.posY = Pos.MAX;
        }

        // create context
        var ctxSequence = new CtxSequence();
        ctxSequence.dbSequence = dbSequence;
        ctxSequence.dbSequenceOption = dbSequenceOption;

        ctxSequenceList.add(ctxSequence);
      }

    } catch (SQLException e) {
      Log.error(e);
    }
  }

  /**
   * register.
   *
   * @param ctxSequence ctxSequence
   * @throws Exception
   */
  public void register(CtxSequence ctxSequence) throws Exception {
    try {
      // database
      con.begin();
      con.executeInsert(ctxSequence.dbSequence);
      con.executeInsert(ctxSequence.dbSequenceOption);
      con.commit();

      // memory
      ctxSequenceList.add(ctxSequence);

    } catch (Exception e) {
      con.rollback();
      Log.error(e);

      throw e;
    }
  }

  /**
   * register for copy.
   *
   * @param ctxSequence ctxSequence
   * @throws Exception
   */
  public void registerForCopy(CtxSequence ctxSequence) throws Exception {
    register(ctxSequence);
  }

  /**
   * save.
   *
   * @param ctxSequence ctxSequence
   * @param tmpSequence tmpSequence
   * @throws Exception
   */
  public void save(CtxSequence ctxSequence, TmpSequence tmpSequence) throws Exception {
    try {
      con.begin();

      // database
      var dbSequence = new DbSequence();
      dbSequence.sequenceId = ctxSequence.dbSequence.sequenceId;
      dbSequence.sequenceName = tmpSequence.sequenceName;
      dbSequence.startValue = tmpSequence.startValue;
      dbSequence.minimumValue = tmpSequence.minimumValue;
      dbSequence.maximumValue = tmpSequence.maximumValue;
      dbSequence.incrementValue = tmpSequence.incrementValue;
      dbSequence.cacheSize = tmpSequence.cacheSize;
      dbSequence.cycle = tmpSequence.cycle;
      con.executeUpdate(dbSequence);

      con.commit();

      // memory
      ctxSequence.dbSequence = dbSequence;

    } catch (Exception e) {
      con.rollback();
      Log.error(e);

      throw e;
    }
  }

  /**
   * remove.
   *
   * @param ctxSequence ctxSequence
   * @throws Exception
   */
  public void remove(CtxSequence ctxSequence) throws Exception {
    try {
      // database
      con.begin();
      con.executeDelete(ctxSequence.dbSequence);
      con.commit();

      // memory
      ctxSequenceList.remove(ctxSequence);

    } catch (Exception e) {
      con.rollback();
      Log.error(e);

      throw e;
    }
  }

  /**
   * save option.
   *
   * @param ctxSequence ctxSequence
   * @throws Exception
   */
  public void saveOption(CtxSequence ctxSequence) throws Exception {
    try {
      // database
      con.begin();
      con.executeUpdate(ctxSequence.dbSequenceOption);
      con.commit();
    } catch (Exception e) {
      con.rollback();
      Log.error(e);

      throw e;
    }
  }

  /**
   * import from ddl.
   *
   * @param ddl   ddl
   * @param point point
   * @return ctx
   * @throws Exception
   */
  public CtxSequence importFromDdl(String ddl, Point point) throws Exception {
    try {
      // database
      con.begin();

      var importSequence = new ImportSequence(con);
      importSequence.addExistingSequences(
          Bucket.getInstance().getBucketSequence().ctxSequenceList.stream()
              .map(s -> s.dbSequence.sequenceName)
              .collect(Collectors.toList()));
      var ctxSequence = importSequence.createSequenceAndGet(ddl);
      if (ctxSequence == null) {
        throw new Exception("Fault to import sequence.");
      }

      // DbSequenceOption
      var dbSequenceOption = new DbSequenceOption();
      dbSequenceOption.sequenceId = ctxSequence.dbSequence.sequenceId;
      dbSequenceOption.posX = point.x;
      dbSequenceOption.posY = point.y;
      con.executeInsert(dbSequenceOption);
      ctxSequence.dbSequenceOption = dbSequenceOption;
      con.commit();

      // memory
      ctxSequenceList.add(ctxSequence);

      return ctxSequence;

    } catch (Exception e) {
      con.rollback();
      Log.error(e);

      throw e;
    }
  }

  /**
   * save bulk.
   *
   * @param newDbSequenceList new db sequence list
   * @throws Exception
   */
  public void saveBulk(List<DbSequence> newDbSequenceList) throws Exception {
    try {
      con.begin();

      // database
      for (var newDbSequence : newDbSequenceList) {
        con.executeUpdate(newDbSequence);
      }

      con.commit();

      // memory
      for (var newDbSequence : newDbSequenceList) {
        var opt = Bucket.getInstance().getBucketSequence()
            .ctxSequenceList
            .stream()
            .filter(c -> c.dbSequence.sequenceId.equals(newDbSequence.sequenceId))
            .findFirst();
        if (!opt.isPresent()) {
          throw new Exception("Not found sequence.");
        }
        opt.get().dbSequence = newDbSequence;
      }

    } catch (Exception e) {
      con.rollback();
      Log.error(e);

      throw e;
    }
  }
}
