package com.walksocket.er.sqlite.bucket;

import com.walksocket.er.Log;
import com.walksocket.er.sqlite.Connection;
import com.walksocket.er.sqlite.Entity;
import com.walksocket.er.sqlite.entity.DbNoteConnectorSequence;
import com.walksocket.er.sqlite.entity.DbNoteConnectorTable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * BucketConnector.
 */
public class BucketConnector {

  /**
   * con.
   */
  private final Connection con;

  /**
   * dbNoteConnectorTableList.
   */
  public List<DbNoteConnectorTable> dbNoteConnectorTableList = new ArrayList<>();

  /**
   * dbNoteConnectorSequenceList.
   */
  public List<DbNoteConnectorSequence> dbNoteConnectorSequenceList = new ArrayList<>();

  /**
   * Constructor.
   *
   * @param con con
   */
  public BucketConnector(Connection con) {
    this.con = con;

    read();
  }

  /**
   * read.
   */
  public void read() {
    dbNoteConnectorTableList.clear();
    dbNoteConnectorSequenceList.clear();

    try {
      // DbNoteConnectorTable
      var sql = "SELECT * FROM "
          + "DbNoteConnectorTable as t1 "
          + "JOIN "
          + "DbTable as t2 "
          + "ON t1.tableId = t2.tableId "
          + "ORDER BY t2.tableName ASC";
      var records = con.getRecords(sql);
      for (var record : records) {
        var dbNoteConnectorTable = Entity.convertEntity(record, DbNoteConnectorTable.class);
        Log.trace(dbNoteConnectorTable);

        dbNoteConnectorTableList.add(dbNoteConnectorTable);
      }

      // DbNoteConnectorSequence
      sql = "SELECT * FROM "
          + "DbNoteConnectorSequence as t1 "
          + "JOIN "
          + "DbSequence as t2 "
          + "ON t1.sequenceId = t2.sequenceId "
          + "ORDER BY t2.sequenceName ASC";
      records = con.getRecords(sql);
      for (var record : records) {
        var dbNoteConnectorSequence = Entity.convertEntity(record, DbNoteConnectorSequence.class);
        Log.trace(dbNoteConnectorSequence);

        dbNoteConnectorSequenceList.add(dbNoteConnectorSequence);
      }
    } catch (SQLException e) {
      Log.error(e);
    }
  }

  /**
   * register note to table.
   *
   * @param dbNoteConnectorTable dbNoteConnectorTable
   * @throws Exception
   */
  public void registerNoteToTable(DbNoteConnectorTable dbNoteConnectorTable) throws Exception {
    try {
      // database
      con.begin();
      con.executeInsert(dbNoteConnectorTable);
      con.commit();

      // memory
      dbNoteConnectorTableList.add(dbNoteConnectorTable);

    } catch (Exception e) {
      con.rollback();
      Log.error(e);

      throw e;
    }
  }

  /**
   * register note to sequence.
   *
   * @param dbNoteConnectorSequence dbNoteConnectorSequence
   * @throws Exception
   */
  public void registerNoteToSequence(DbNoteConnectorSequence dbNoteConnectorSequence)
      throws Exception {
    try {
      // database
      con.begin();
      con.executeInsert(dbNoteConnectorSequence);
      con.commit();

      // memory
      dbNoteConnectorSequenceList.add(dbNoteConnectorSequence);

    } catch (Exception e) {
      con.rollback();
      Log.error(e);

      throw e;
    }
  }

  /**
   * remove note to table.
   *
   * @param dbNoteConnectorTable dbNoteConnectorTable
   * @throws Exception
   */
  public void removeNoteToTable(DbNoteConnectorTable dbNoteConnectorTable) throws Exception {
    try {
      // database
      con.begin();
      con.executeDelete(dbNoteConnectorTable);
      con.commit();

      // memory
      dbNoteConnectorTableList.remove(dbNoteConnectorTable);

    } catch (Exception e) {
      con.rollback();
      Log.error(e);

      throw e;
    }
  }

  /**
   * remove note to sequence.
   *
   * @param dbNoteConnectorSequence dbNoteConnectorSequence
   * @throws Exception
   */
  public void removeNoteToSequence(DbNoteConnectorSequence dbNoteConnectorSequence)
      throws Exception {
    try {
      // database
      con.begin();
      con.executeDelete(dbNoteConnectorSequence);
      con.commit();

      // memory
      dbNoteConnectorSequenceList.remove(dbNoteConnectorSequence);

    } catch (Exception e) {
      con.rollback();
      Log.error(e);

      throw e;
    }
  }
}
