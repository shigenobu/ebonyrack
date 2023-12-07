package com.walksocket.er.sqlite;

import com.walksocket.er.Json;
import com.walksocket.er.Log;
import com.walksocket.er.Value;
import java.sql.SQLException;
import java.util.List;

/**
 * Entity.
 */
public abstract class Entity implements Cloneable, Value {

  /**
   * convert entity.
   *
   * @param record record
   * @param cls    class
   * @param <T>    type of class
   * @return entity
   * @throws SQLException sql error
   */
  public static <T extends Entity> T convertEntity(Record record, Class<T> cls)
      throws SQLException {
    try {
      var entity = cls.newInstance();
      entity.bind(record);
      return entity;
    } catch (InstantiationException | IllegalAccessException e) {
      Log.error(e);
      throw new SQLException("Fault to convert entity.");
    }
  }

  /**
   * bind.
   *
   * @param record record
   */
  public abstract void bind(Record record);

  /**
   * create insert sql.
   *
   * @return sql
   */
  public abstract String createInsert();

  /**
   * create update sql.
   *
   * @return sql
   */
  public abstract String createUpdate();

  /**
   * create delete sql.
   *
   * @return sql
   */
  public abstract String createDelete();

  /**
   * order columns.
   *
   * @return order columns
   */
  public abstract List<String> orderColumns();

  @Override
  public String toString() {
    return Json.toJsonString(this);
  }
}
