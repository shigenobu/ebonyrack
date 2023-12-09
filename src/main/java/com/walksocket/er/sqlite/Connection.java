package com.walksocket.er.sqlite;

import com.walksocket.er.Log;
import com.walksocket.er.Utils;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * Connection.
 */
public class Connection implements AutoCloseable {

  /**
   * db path prefix.
   */
  public static final String DB_PATH_PREFIX = ".sqlite3";

  /**
   * db path.
   */
  private final String dbPath;

  /**
   * connection string.
   */
  private final String connectionString;

  /**
   * connection.
   */
  private java.sql.Connection con;

  /**
   * in transaction.
   */
  private boolean inTransaction;

  /**
   * readonly.
   */
  private boolean readonly;

  /**
   * constructor.
   *
   * @param dbPath dbPath
   */
  public Connection(String dbPath) {
    this.dbPath = dbPath;
    this.connectionString = String.format("jdbc:sqlite:%s", dbPath);
  }

  /**
   * open.
   *
   * @throws SQLException sql error
   */
  public void open() throws SQLException {
    if (isOpen()) {
      return;
    }

    Log.sql(connectionString);
    try {
      Properties props = new Properties();
      props.put("journal_mode", "WAL");
      props.put("sync_mode", "OFF");
      props.put("foreign_keys", "ON");
      props.put("busy_timeout", 10000);
      props.put("locking_mode", "EXCLUSIVE");

      Class.forName("org.sqlite.JDBC");
      con = DriverManager.getConnection(connectionString, props);
      con.setAutoCommit(false);
      con.setTransactionIsolation(java.sql.Connection.TRANSACTION_SERIALIZABLE);
      Log.sql("CONNECT:" + con);

    } catch (ClassNotFoundException e) {
      Log.error(e);
    }
  }

  /**
   * get db path.
   *
   * @return db path
   */
  public String getDbPath() {
    return dbPath;
  }

  /**
   * is open.
   *
   * @return if opend, true
   * @throws SQLException sql error
   */
  private boolean isOpen() throws SQLException {
    return con != null && !con.isClosed();
  }

  @Override
  public void close() throws Exception {
    try {
      if (con == null || con.isClosed()) {
        return;
      }
      rollback();
      con.close();
      Log.sql("DISCONNECT:" + con);
      con = null;
    } catch (SQLException e) {
      Log.error(e);
    }
  }

  /**
   * set readonly.
   *
   * @param readonly readonly
   */
  public void setReadonly(boolean readonly) {
    this.readonly = readonly;
  }

  /**
   * is readonly.
   *
   * @return if readonly, true
   */
  public boolean isReadonly() {
    return readonly;
  }

  /**
   * begin.
   *
   * @throws SQLException sql error
   */
  public void begin() throws SQLException {
    Log.sql("BEGIN(DUMMY)");
    inTransaction = true;
  }

  /**
   * commit.
   *
   * @throws SQLException sql error
   */
  public void commit() throws SQLException {
    if (isOpen() && inTransaction) {
      if (!readonly) {
        con.commit();
        Log.sql("COMMIT");
      } else {
        Log.sql("NO COMMIT");
      }
      inTransaction = false;
    }
  }

  /**
   * rollback.
   */
  public void rollback() {
    try {
      if (isOpen() && inTransaction) {
        con.rollback();
        Log.sql("ROLLBACK");
        inTransaction = false;
      }
    } catch (SQLException e) {
      Log.error(e);
    }
  }

  /**
   * execute insert.
   *
   * @param entity entity
   * @throws SQLException sql error
   */
  public void executeInsert(Entity entity) throws SQLException {
    execute(entity.createInsert());
  }

  /**
   * execute update.
   *
   * @param entity entity
   * @throws SQLException sql error
   */
  public void executeUpdate(Entity entity) throws SQLException {
    execute(entity.createUpdate());
  }

  /**
   * execute delete.
   *
   * @param entity entity
   * @throws SQLException sql error
   */
  public void executeDelete(Entity entity) throws SQLException {
    execute(entity.createDelete());
  }

  /**
   * execute.
   *
   * @param sql sql
   * @throws SQLException sql error
   */
  public void execute(String sql) throws SQLException {
    Statement stmt = null;
    try {
      open();
      stmt = con.createStatement();
      stmt.execute(sql);
      Log.sql(sql);
    } catch (Exception e) {
      Log.error(sql);
      throw e;
    } finally {
      if (stmt != null) {
        try {
          stmt.close();
        } catch (Exception e) {
          Log.error(e);
        }
      }
    }
  }

  /**
   * get records.
   *
   * @param sql sql
   * @return records
   * @throws SQLException sql error
   */
  public List<Record> getRecords(String sql) throws SQLException {
    List<Record> records = new ArrayList<>();
    Statement stmt = null;
    ResultSet rs = null;
    try {
      open();
      stmt = con.createStatement();
      rs = stmt.executeQuery(sql);
      Log.sql(sql);
      while (rs.next()) {
        var meta = rs.getMetaData();
        List<Map<String, String>> record = new ArrayList<>();
        for (int i = 1; i <= meta.getColumnCount(); i++) {
          String columnName = meta.getColumnName(i).toLowerCase();
          Map<String, String> pair = new HashMap<>();
          pair.put(columnName, rs.getString(i));
          record.add(pair);
        }
        records.add(new Record(record));
      }
    } catch (Exception e) {
      Log.error(sql);
      throw e;
    } finally {
      if (rs != null) {
        try {
          rs.close();
        } catch (Exception e) {
          Log.error(e);
        }
      }
      if (stmt != null) {
        try {
          stmt.close();
        } catch (Exception e) {
          Log.error(e);
        }
      }
    }

    return records;
  }

  /**
   * get record.
   *
   * @param sql sql
   * @return record or null
   * @throws SQLException sql error
   */
  public Record getRecord(String sql) throws SQLException {
    var records = getRecords(sql);
    if (!Utils.isNullOrEmpty(records)) {
      return records.get(0);
    }
    return null;
  }
}
