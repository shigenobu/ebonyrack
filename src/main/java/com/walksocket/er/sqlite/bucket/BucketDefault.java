package com.walksocket.er.sqlite.bucket;

import com.walksocket.er.Log;
import com.walksocket.er.sqlite.Connection;
import com.walksocket.er.sqlite.Entity;
import com.walksocket.er.sqlite.entity.DbDefault;
import com.walksocket.er.sqlite.tmp.TmpDefault;
import java.sql.SQLException;

/**
 * BucketDefault.
 */
public class BucketDefault {

  /**
   * con.
   */
  private final Connection con;

  /**
   * db default.
   */
  public DbDefault dbDefault;

  /**
   * Constructor.
   *
   * @param con con
   */
  public BucketDefault(Connection con) {
    this.con = con;

    read();
  }

  /**
   * read.
   */
  public void read() {
    try {
      // DbDefault
      var sql = "SELECT * FROM DbDefault";
      var records = con.getRecords(sql);
      for (var record : records) {
        dbDefault = Entity.convertEntity(record, DbDefault.class);
        Log.trace(dbDefault);
      }
    } catch (SQLException e) {
      Log.error(e);
    }
  }

  /**
   * save.
   *
   * @param tmpDefault tmpDefault
   * @throws Exception
   */
  public void save(TmpDefault tmpDefault) throws Exception {
    try {
      con.begin();

      // database
      var d = new DbDefault();
      d.defaultEngine = tmpDefault.defaultEngine;
      d.defaultCharset = tmpDefault.defaultCharset;
      d.defaultCollate = tmpDefault.defaultCollate;
      con.executeUpdate(d);

      con.commit();

      // memory
      dbDefault = d;

    } catch (Exception e) {
      con.rollback();
      Log.error(e);

      throw e;
    }
  }
}
