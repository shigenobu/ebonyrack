package com.walksocket.er.sqlite.bucket;

import com.walksocket.er.Log;
import com.walksocket.er.Utils;
import com.walksocket.er.sqlite.Connection;
import com.walksocket.er.sqlite.Entity;
import com.walksocket.er.sqlite.entity.DbDictColumn;
import com.walksocket.er.sqlite.entity.DbDictColumnType;
import com.walksocket.er.sqlite.entity.DbDictGroup;
import com.walksocket.er.sqlite.entity.DbDictGroupColumn;
import com.walksocket.er.sqlite.tmp.TmpDictColumn;
import com.walksocket.er.sqlite.tmp.TmpDictColumnType;
import com.walksocket.er.sqlite.tmp.TmpDictGroup;
import com.walksocket.er.sqlite.tmp.TmpDictGroupColumn;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * BucketDict.
 */
public class BucketDict {

  /**
   * con.
   */
  private final Connection con;

  /**
   * dbDictColumnTypeList.
   */
  public List<DbDictColumnType> dbDictColumnTypeList = new ArrayList<>();

  /**
   * dbDictColumnList.
   */
  public List<DbDictColumn> dbDictColumnList = new ArrayList<>();

  /**
   * dbDictGroupList.
   */
  public List<DbDictGroup> dbDictGroupList = new ArrayList<>();

  /**
   * dbDictGroupColumnList.
   */
  public List<DbDictGroupColumn> dbDictGroupColumnList = new ArrayList<>();

  /**
   * Constructor.
   *
   * @param con con
   */
  public BucketDict(Connection con) {
    this.con = con;

    read();
  }

  /**
   * read.
   */
  public void read() {
    dbDictColumnTypeList.clear();
    dbDictColumnList.clear();
    dbDictGroupList.clear();
    dbDictGroupColumnList.clear();

    try {
      // --------------------
      // DbDictColumnType
      var sql = "SELECT * FROM DbDictColumnType";
      var records = con.getRecords(sql);
      for (var record : records) {
        var dbDictColumnType = Entity.convertEntity(record, DbDictColumnType.class);
        Log.trace(dbDictColumnType);

        dbDictColumnTypeList.add(dbDictColumnType);
      }

      // --------------------
      // DbDictColumn
      sql = "SELECT * FROM DbDictColumn";
      records = con.getRecords(sql);
      for (var record : records) {
        var dbDictColumn = Entity.convertEntity(record, DbDictColumn.class);
        Log.trace(dbDictColumn);

        dbDictColumnList.add(dbDictColumn);
      }

      // --------------------
      // DbDictGroup
      sql = "SELECT * FROM DbDictGroup";
      records = con.getRecords(sql);
      for (var record : records) {
        var dbDictGroup = Entity.convertEntity(record, DbDictGroup.class);
        Log.trace(dbDictGroup);

        dbDictGroupList.add(dbDictGroup);
      }

      // DbDictGroupColumn
      sql = "SELECT * FROM DbDictGroupColumn";
      records = con.getRecords(sql);
      for (var record : records) {
        var dbDictGroupColumn = Entity.convertEntity(record, DbDictGroupColumn.class);
        Log.trace(dbDictGroupColumn);

        dbDictGroupColumnList.add(dbDictGroupColumn);
      }

    } catch (SQLException e) {
      Log.error(e);
    }
  }

  /**
   * save dict column type.
   *
   * @param tmpDictColumnType tmpDictColumnType
   * @throws Exception
   */
  public void saveDictColumnType(TmpDictColumnType tmpDictColumnType) throws Exception {
    try {
      con.begin();

      // database
      var d = new DbDictColumnType();
      d.dictColumnTypeId = tmpDictColumnType.dictColumnTypeId;
      d.seq = tmpDictColumnType.seq;
      d.columnType = tmpDictColumnType.columnType;
      d.remarks = tmpDictColumnType.remarks;
      var sql = String.format(
          "SELECT * FROM DbDictColumnType WHERE dictColumnTypeId = '%s'",
          Utils.quote(d.dictColumnTypeId));
      var record = con.getRecord(sql);
      if (record == null) {
        d.dictColumnTypeId = Utils.randomString();
        con.executeInsert(d);
      } else {
        con.executeUpdate(d);
      }

      con.commit();

      // memory
      var opt = dbDictColumnTypeList.stream()
          .filter(t -> t.dictColumnTypeId.equals(d.dictColumnTypeId))
          .findFirst();
      if (opt.isPresent()) {
        dbDictColumnTypeList.remove(opt.get());
      }
      dbDictColumnTypeList.add(d);
    } catch (Exception e) {
      con.rollback();
      Log.error(e);

      throw e;
    }
  }

  /**
   * remove dict column type.
   *
   * @param tmpDictColumnType tmpDictColumnType
   * @throws Exception
   */
  public void removeDictColumnType(TmpDictColumnType tmpDictColumnType) throws Exception {
    try {
      con.begin();

      // database
      var sql = String.format(
          "SELECT * FROM DbDictColumnType WHERE dictColumnTypeId = '%s'",
          Utils.quote(tmpDictColumnType.dictColumnTypeId));
      var record = con.getRecord(sql);
      if (record != null) {
        var d = Entity.convertEntity(record, DbDictColumnType.class);
        con.executeDelete(d);
      }

      con.commit();

      // memory
      var opt = dbDictColumnTypeList.stream()
          .filter(t -> t.dictColumnTypeId.equals(tmpDictColumnType.dictColumnTypeId))
          .findFirst();
      if (opt.isPresent()) {
        dbDictColumnTypeList.remove(opt.get());
      }

    } catch (Exception e) {
      con.rollback();
      Log.error(e);

      throw e;
    }
  }

  /**
   * save dict column.
   *
   * @param tmpDictColumn tmpDictColumn
   * @throws Exception
   */
  public void saveDictColumn(TmpDictColumn tmpDictColumn) throws Exception {
    try {
      con.begin();

      // database
      var d = new DbDictColumn();
      d.dictColumnId = tmpDictColumn.dictColumnId;
      d.columnName = tmpDictColumn.columnName;
      d.columnComment = tmpDictColumn.columnComment;

      // column type
      d.dictColumnTypeId = "";
      var optionalDbDictColumnType = dbDictColumnTypeList.stream()
          .filter(c -> c.columnType.equals(tmpDictColumn.columnType))
          .findFirst();
      if (optionalDbDictColumnType.isPresent()) {
        d.dictColumnTypeId = optionalDbDictColumnType.get().dictColumnTypeId;
      }

      d.notNullValue = tmpDictColumn.notNullValue;
      d.charsetValue = tmpDictColumn.charsetValue;
      d.collateValue = tmpDictColumn.collateValue;
      d.defaultValue = tmpDictColumn.defaultValue;
      d.onUpdate = tmpDictColumn.onUpdate;
      d.autoIncrementDefinition = tmpDictColumn.autoIncrementDefinition;
      d.option = tmpDictColumn.option;
      var sql = String.format(
          "SELECT * FROM DbDictColumn WHERE dictColumnId = '%s'",
          Utils.quote(d.dictColumnId));
      var record = con.getRecord(sql);
      if (record == null) {
        d.dictColumnId = Utils.randomString();
        con.executeInsert(d);
      } else {
        con.executeUpdate(d);
      }

      con.commit();

      // memory
      var optionalDbDictColumn = dbDictColumnList.stream()
          .filter(t -> t.dictColumnId.equals(d.dictColumnId))
          .findFirst();
      if (optionalDbDictColumn.isPresent()) {
        dbDictColumnList.remove(optionalDbDictColumn.get());
      }
      dbDictColumnList.add(d);

    } catch (Exception e) {
      con.rollback();
      Log.error(e);

      throw e;
    }
  }

  /**
   * remove dict colum.
   *
   * @param tmpDictColumn tmpDictColumn
   * @throws Exception
   */
  public void removeDictColumn(TmpDictColumn tmpDictColumn) throws Exception {
    try {
      con.begin();

      // database
      var sql = String.format(
          "SELECT * FROM DbDictColumn WHERE dictColumnId = '%s'",
          Utils.quote(tmpDictColumn.dictColumnId));
      var record = con.getRecord(sql);
      if (record != null) {
        var d = Entity.convertEntity(record, DbDictColumn.class);
        con.executeDelete(d);
      }

      con.commit();

      // memory
      var opt = dbDictColumnList.stream()
          .filter(t -> t.dictColumnId.equals(tmpDictColumn.dictColumnId))
          .findFirst();
      if (opt.isPresent()) {
        dbDictColumnList.remove(opt.get());
      }
    } catch (Exception e) {
      con.rollback();
      Log.error(e);

      throw e;
    }
  }

  /**
   * save dict group.
   *
   * @param tmpDictGroup           tmpDictGroup
   * @param tmpDictGroupColumnList tmpDictGroupColumnList
   * @throws Exception
   */
  public void saveDictGroup(TmpDictGroup tmpDictGroup,
      List<TmpDictGroupColumn> tmpDictGroupColumnList)
      throws Exception {
    try {
      con.begin();

      // database
      var d = new DbDictGroup();
      d.dictGroupId = tmpDictGroup.dictGroupId;
      d.groupName = tmpDictGroup.groupName;
      var sql = String.format(
          "SELECT * FROM DbDictGroup WHERE dictGroupId = '%s'",
          Utils.quote(d.dictGroupId));
      var record = con.getRecord(sql);
      if (record == null) {
        d.dictGroupId = Utils.randomString();
        con.executeInsert(d);
      } else {
        con.executeUpdate(d);
      }

      sql = String.format(
          "DELETE FROM DbDictGroupColumn WHERE dictGroupId = '%s'",
          Utils.quote(d.dictGroupId));
      con.execute(sql);
      var ds = new ArrayList<DbDictGroupColumn>();
      for (var ts : tmpDictGroupColumnList) {
        var dc = new DbDictGroupColumn();
        dc.dictGroupId = d.dictGroupId;
        dc.seq = ts.seq;
        dc.dictColumnId = ts.dictColumnId;
        con.executeInsert(dc);

        ds.add(dc);
      }

      con.commit();

      // memory
      var optionalDbDictGroup = dbDictGroupList.stream()
          .filter(t -> t.dictGroupId.equals(d.dictGroupId))
          .findFirst();
      if (optionalDbDictGroup.isPresent()) {
        dbDictGroupList.remove(optionalDbDictGroup.get());
      }
      dbDictGroupList.add(d);

      var nds = dbDictGroupColumnList.stream()
          .filter(t -> t.dictGroupId.equals(d.dictGroupId))
          .collect(Collectors.toList());
      dbDictGroupColumnList.removeAll(nds);
      dbDictGroupColumnList.addAll(ds);

    } catch (Exception e) {
      con.rollback();
      Log.error(e);

      throw e;
    }
  }

  /**
   * remove dict group.
   *
   * @param tmpDictGroup tmpDictGroup
   * @throws Exception
   */
  public void removeDictGroup(TmpDictGroup tmpDictGroup) throws Exception {
    try {
      con.begin();

      // database
      var sql = String.format(
          "SELECT * FROM DbDictGroup WHERE dictGroupId = '%s'",
          Utils.quote(tmpDictGroup.dictGroupId));
      var record = con.getRecord(sql);
      if (record != null) {
        var d = Entity.convertEntity(record, DbDictColumn.class);
        con.executeDelete(d);
      }

      con.commit();

      // memory
      var optionalDbDictGroup = dbDictGroupList.stream()
          .filter(t -> t.dictGroupId.equals(tmpDictGroup.dictGroupId))
          .findFirst();
      if (optionalDbDictGroup.isPresent()) {
        dbDictGroupList.remove(optionalDbDictGroup.get());
      }

      var nds = dbDictGroupColumnList.stream()
          .filter(t -> t.dictGroupId.equals(tmpDictGroup.dictGroupId))
          .collect(Collectors.toList());
      dbDictGroupColumnList.removeAll(nds);

    } catch (Exception e) {
      con.rollback();
      Log.error(e);

      throw e;
    }
  }
}
