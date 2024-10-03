package com.walksocket.er.definition;

import java.util.ArrayList;
import java.util.List;

/**
 * DataType.
 */
public class DataType {

  /**
   * TypeGroup.
   */
  public enum TypeGroup {
    /**
     * number.
     */
    NUMBER("number"),

    /**
     * text.
     */
    TEXT("text"),

    /**
     * date.
     */
    DATE("date"),

    /**
     * binary.
     */
    BINARY("binary"),

    /**
     * other (array, address, uuid).
     */
    OTHER("other");

    /**
     * type group.
     */
    private final String typeGroup;

    /**
     * Constructor.
     *
     * @param typeGroup typeGroup
     */
    TypeGroup(String typeGroup) {
      this.typeGroup = typeGroup;
    }

    /**
     * get type group.
     *
     * @return type group
     */
    public String getTypeGroup() {
      return typeGroup;
    }
  }

  /**
   * get type group.
   *
   * @param columnType
   * @return type group.
   */
  public static TypeGroup getTypeGroup(String columnType) {
    if (isNumber(columnType)) {
      return TypeGroup.NUMBER;
    }
    if (isText(columnType)) {
      return TypeGroup.TEXT;
    }
    if (isBinary(columnType)) {
      return TypeGroup.BINARY;
    }
    if (isDate(columnType)) {
      return TypeGroup.DATE;
    }
    return TypeGroup.OTHER;
  }

  /**
   * is number.
   *
   * @param columnType columnType
   * @return if number, true
   */
  public static boolean isNumber(String columnType) {
    columnType = columnType.toLowerCase();
    return columnType.contains("int")
        || columnType.contains("bool")
        || columnType.contains("decimal")
        || columnType.contains("float")
        || columnType.contains("double")
        || columnType.contains("bit");
  }

  /**
   * is text.
   *
   * @param columnType columnType
   * @return if text, true
   */
  public static boolean isText(String columnType) {
    columnType = columnType.toLowerCase();
    return columnType.contains("text")
        || columnType.contains("json")
        || columnType.contains("char");
  }

  /**
   * is binary.
   *
   * @param columnType columnType
   * @return if binary, true
   */
  public static boolean isBinary(String columnType) {
    columnType = columnType.toLowerCase();
    return columnType.contains("blob") || columnType.contains("binary");
  }

  /**
   * is date.
   *
   * @param columnType columnType
   * @return if date, true
   */
  public static boolean isDate(String columnType) {
    columnType = columnType.toLowerCase();
    return columnType.contains("date")
        || columnType.contains("time")
        || columnType.contains("year");
  }

  /**
   * allow auto increment.
   *
   * @param columnType columnType
   * @return if allow auto increment, true
   */
  public static boolean allowAutoIncrement(String columnType) {
    columnType = columnType.toLowerCase();
    return columnType.contains("int");
  }

  /**
   * allow current timestamp.
   *
   * @param columnType columnType
   * @return timestamp or datetime is true
   */
  public static boolean allowCurrentTimestamp(String columnType) {
    columnType = columnType.toLowerCase();
    return columnType.startsWith("timestamp") || columnType.startsWith("datetime");
  }

  /**
   * data type list.
   */
  private static final List<DataType> dataTypeList = new ArrayList<>();

  static {
    // number
    dataTypeList.add(new DataType("tinyint"));
    dataTypeList.add(new DataType("boolean", "synonyms for 'tinyint(1)'."));
    dataTypeList.add(new DataType("smallint"));
    dataTypeList.add(new DataType("mediumint"));
    dataTypeList.add(new DataType("int"));
    dataTypeList.add(new DataType("bigint"));
    dataTypeList.add(new DataType("decimal"));
    dataTypeList.add(new DataType("float"));
    dataTypeList.add(new DataType("double"));
    dataTypeList.add(new DataType("bit"));

    // text
    dataTypeList.add(new DataType("char", new int[]{1, 2, 4}));// 1,2,4
    dataTypeList.add(new DataType("varchar", new int[]{64, 128, 256}));// 64,128,256
    dataTypeList.add(new DataType("tinytext"));
    dataTypeList.add(new DataType("text"));
    dataTypeList.add(new DataType("mediumtext"));
    dataTypeList.add(new DataType("longtext"));
    dataTypeList.add(new DataType("json",
        "synonyms for 'longtext', collate 'binary' and add constraint 'json_valid'."));

    // binary
    dataTypeList.add(new DataType("binary", new int[]{1, 2, 4}));// 1,2,4
    dataTypeList.add(
        new DataType("varbinary", new int[]{64, 128, 256}));// 64,128,256
    dataTypeList.add(new DataType("tinyblob"));
    dataTypeList.add(new DataType("blob"));
    dataTypeList.add(new DataType("mediumblob"));
    dataTypeList.add(new DataType("longblob"));

    // date
    dataTypeList.add(new DataType("date"));
    dataTypeList.add(new DataType("time"));
    dataTypeList.add(new DataType("datetime"));
    dataTypeList.add(new DataType("timestamp"));
    dataTypeList.add(new DataType("year"));

    // other
    dataTypeList.add(new DataType("enum('a','b','c')"));
    dataTypeList.add(new DataType("set('a','b','c')"));
    dataTypeList.add(new DataType("inet4"));
    dataTypeList.add(new DataType("inet6"));
    dataTypeList.add(new DataType("uuid"));
  }

  /**
   * get data type list.
   *
   * @return data type list
   */
  public static List<DataType> getDataTypeList() {
    return dataTypeList;
  }

  /**
   * type name.
   */
  private final String typeName;

  /**
   * lengths.
   */
  private final int[] lengths;

  /**
   * remarks.
   */
  private final String remarks;

  /**
   * Constructor.
   *
   * @param typeName typeName
   */
  private DataType(String typeName) {
    this(typeName, new int[]{0}, "");
  }

  /**
   * Constructor.
   *
   * @param typeName typeName
   * @param remarks  remarks
   */
  private DataType(String typeName, String remarks) {
    this(typeName, new int[]{0}, remarks);
  }

  /**
   * Constructor.
   *
   * @param typeName typeName
   * @param lengths  lengths
   */
  private DataType(String typeName, int[] lengths) {
    this(typeName, lengths, "");
  }

  /**
   * Constructor.
   *
   * @param typeName typeName
   * @param lengths  lengths
   * @param remarks  remarks
   */
  private DataType(String typeName, int[] lengths, String remarks) {
    this.typeName = typeName;
    this.lengths = lengths;
    this.remarks = remarks;
  }

  /**
   * get type name.
   *
   * @return type name
   */
  public String getTypeName() {
    return typeName;
  }

  /**
   * get lengths.
   *
   * @return lengths
   */
  public int[] getLengths() {
    return lengths;
  }

  /**
   * get remarks.
   *
   * @return remarks
   */
  public String getRemarks() {
    return remarks;
  }
}
