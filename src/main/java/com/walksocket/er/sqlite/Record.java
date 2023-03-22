package com.walksocket.er.sqlite;

import com.walksocket.er.Utils;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Record.
 */
public class Record {

  /**
   * create empty record.
   *
   * @return db record
   */
  public static Record createEmptyRecord() {
    return new Record();
  }

  /**
   * params.
   */
  private final Map<String, String> params = new LinkedHashMap<>();

  /**
   * constructor.
   */
  private Record() {
  }

  /**
   * constructor for record.
   *
   * @param record record
   */
  public Record(List<Map<String, String>> record) {
    record.forEach(pair -> {
      pair.forEach((columnName, columnValue) -> {
        params.put(columnName.toLowerCase(), columnValue);
      });
    });
  }

  /**
   * get value.
   *
   * @param columnName column name
   * @return column value
   * @throws SQLException no column error
   */
  public String get(String columnName) throws SQLException {
    var colName = columnName.toLowerCase();
    if (params.containsKey(colName)) {
      return params.get(colName);
    }
    throw new SQLException(columnName + " value is not exist !");
  }

  /**
   * get value when not exists, return alternative.
   *
   * @param columnName  column name
   * @param alternative alternative
   * @return column value or alternative
   */
  public String getOrDefault(String columnName, String alternative) {
    var colName = columnName.toLowerCase();
    if (params.containsKey(colName)) {
      return params.get(colName);
    }
    return alternative;
  }

  /**
   * get value when not exists or null, return 0.
   *
   * @param columnName column name
   * @return column value or zero
   */
  public int getOrZeroByInt(String columnName) {
    var value = getOrDefault(columnName, "0");
    if (!Utils.isNumber(value)) {
      value = "0";
    }
    return Integer.parseInt(value);
  }

  /**
   * get value when not exists or null, return 0.
   *
   * @param columnName column name
   * @return column value or zero
   */
  public long getOrZeroByLong(String columnName) {
    var value = getOrDefault(columnName, "0");
    if (!Utils.isNumber(value)) {
      value = "0";
    }
    return Long.parseLong(value);
  }

  /**
   * get value when not exists or null, return empty.
   *
   * @param columnName column name
   * @return column value or empty
   */
  public String getOrEmpty(String columnName) {
    var value = getOrDefault(columnName, "");
    if (value == null) {
      value = "";
    }
    return value;
  }

  /**
   * get all.
   *
   * @return all params
   */
  public Map<String, String> getAll() {
    return params;
  }
}
