package com.walksocket.er.sqlite;

import com.walksocket.er.parts.ColumnForeignKeyOption;
import com.walksocket.er.parts.ColumnKeyOption;
import com.walksocket.er.sqlite.entity.DbDictColumn;
import com.walksocket.er.sqlite.entity.DbDictColumnType;
import com.walksocket.er.sqlite.entity.DbDictGroup;
import com.walksocket.er.sqlite.entity.DbDictGroupColumn;
import com.walksocket.er.sqlite.entity.DbSequence;
import com.walksocket.er.sqlite.entity.DbTable;
import com.walksocket.er.sqlite.entity.DbTableColumn;
import com.walksocket.er.sqlite.entity.DbTableForeignKey;
import com.walksocket.er.sqlite.entity.DbTableForeignKeyColumn;
import com.walksocket.er.sqlite.entity.DbTableGroup;
import com.walksocket.er.sqlite.tmp.TmpColumn;
import com.walksocket.er.sqlite.tmp.TmpForeignKey;
import com.walksocket.er.sqlite.tmp.TmpKey;
import com.walksocket.er.sqlite.tmp.TmpSequence;
import com.walksocket.er.sqlite.tmp.TmpTable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Tmp.
 */
public interface Tmp {

  /**
   * create tmp sequence.
   *
   * @param dbSequence dbSequence
   * @return tmp sequence
   */
  static TmpSequence createTmpSequence(DbSequence dbSequence) {
    var tmpSequence = new TmpSequence();
    tmpSequence.sequenceName = dbSequence.sequenceName;
    tmpSequence.startValue = dbSequence.startValue;
    tmpSequence.minimumValue = dbSequence.minimumValue;
    tmpSequence.maximumValue = dbSequence.maximumValue;
    tmpSequence.incrementValue = dbSequence.incrementValue;
    tmpSequence.cacheSize = dbSequence.cacheSize;
    tmpSequence.cycle = dbSequence.cycle;
    return tmpSequence;
  }

  /**
   * create tmp table.
   *
   * @param dbTable dbTable
   * @return tmp table
   */
  static TmpTable createTmpTable(DbTable dbTable) {
    var tmpTable = new TmpTable();
    tmpTable.tableName = dbTable.tableName;
    tmpTable.tableComment = dbTable.tableComment;
    tmpTable.engine = dbTable.engine;
    tmpTable.charsetValue = dbTable.charsetValue;
    tmpTable.collateValue = dbTable.collateValue;
    tmpTable.autoIncrementValue = dbTable.autoIncrementValue;
    tmpTable.option = dbTable.option;
    return tmpTable;
  }

  /**
   * create tmp column list.
   *
   * @param dbTableColumnList    dbTableColumnList
   * @param dbDictColumnTypeList dbDictColumnTypeList
   * @param dbDictColumnList     dbDictColumnList
   * @return tmp column list
   */
  static List<TmpColumn> createTmpColumnList(List<DbTableColumn> dbTableColumnList,
      List<DbDictColumnType> dbDictColumnTypeList, List<DbDictColumn> dbDictColumnList) {
    var tmpColumnList = new ArrayList<TmpColumn>();
    for (var dbTableColumn : dbTableColumnList.stream()
        .sorted(Comparator.comparing(t -> t.ordinalPosition)).collect(Collectors.toList())) {
      var tmpColumn = new TmpColumn();

      var dictColumnId = dbTableColumn.dictColumnId;
      var opt = dbDictColumnList.stream()
          .filter(t -> t.dictColumnId.equals(dictColumnId))
          .findFirst();
      if (opt.isPresent()) {
        tmpColumn.columnName = opt.get().columnName;
        tmpColumn.columnComment = opt.get().columnComment;

        var o = dbDictColumnTypeList.stream()
            .filter(d -> d.dictColumnTypeId.equals(opt.get().dictColumnTypeId))
            .findFirst();
        if (o.isPresent()) {
          tmpColumn.columnType = o.get().columnType;
        }

        tmpColumn.notNullValue = opt.get().notNullValue;
        tmpColumn.charsetValue = opt.get().charsetValue;
        tmpColumn.collateValue = opt.get().collateValue;
        tmpColumn.defaultValue = opt.get().defaultValue;
        tmpColumn.onUpdate = opt.get().onUpdate;
        tmpColumn.autoIncrementDefinition = opt.get().autoIncrementDefinition;
        tmpColumn.option = opt.get().option;
      }

      tmpColumnList.add(tmpColumn);
    }
    return tmpColumnList;
  }

  /**
   * create tmp group column list.
   *
   * @param dbTableGroup dbTableGroup
   * @return tmp group column list
   */
  static List<TmpColumn> createTmpGroupColumnList(
      DbTableGroup dbTableGroup,
      List<DbDictColumnType> dbDictColumnTypeList,
      List<DbDictColumn> dbDictColumnList,
      List<DbDictGroup> dbDictGroupList,
      List<DbDictGroupColumn> dbDictGroupColumnList) {
    var tmpColumnList = new ArrayList<TmpColumn>();

    var dbDictGroup = dbDictGroupList.stream()
        .filter(c -> c.dictGroupId.equals(dbTableGroup.dictGroupId))
        .findFirst()
        .get();
    for (var dbTableColumn : dbDictGroupColumnList.stream()
        .filter(d -> d.dictGroupId.equals(dbDictGroup.dictGroupId))
        .sorted(Comparator.comparing(c -> c.seq))
        .collect(Collectors.toList())) {
      var tmpColumn = new TmpColumn();

      var dictColumnId = dbTableColumn.dictColumnId;
      var opt = dbDictColumnList.stream()
          .filter(t -> t.dictColumnId.equals(dictColumnId))
          .findFirst();
      if (opt.isPresent()) {
        tmpColumn.columnName = opt.get().columnName;
        tmpColumn.columnComment = opt.get().columnComment;

        var o = dbDictColumnTypeList.stream()
            .filter(d -> d.dictColumnTypeId.equals(opt.get().dictColumnTypeId))
            .findFirst();
        if (o.isPresent()) {
          tmpColumn.columnType = o.get().columnType;
        }

        tmpColumn.notNullValue = opt.get().notNullValue;
        tmpColumn.charsetValue = opt.get().charsetValue;
        tmpColumn.collateValue = opt.get().collateValue;
        tmpColumn.defaultValue = opt.get().defaultValue;
        tmpColumn.onUpdate = opt.get().onUpdate;
        tmpColumn.autoIncrementDefinition = opt.get().autoIncrementDefinition;
        tmpColumn.option = opt.get().option;
      }

      tmpColumnList.add(tmpColumn);
    }
    return tmpColumnList;
  }

  /**
   * create tmp key.
   *
   * @param dbKey           dbKey
   * @param dbKeyColumnList dbKeyColumnList
   * @return tmp key
   */
  static <D extends EntityKey, DC extends EntityKeyColumn> TmpKey createTmpKey(D dbKey,
      List<DC> dbKeyColumnList, List<DbDictColumn> dbDictColumnList) {
    var tmpKey = new TmpKey();
    for (var dc : dbKeyColumnList.stream()
        .sorted(Comparator.comparing(t -> t.seqInIndex)).collect(Collectors.toList())) {
      var columnKeyOption = new ColumnKeyOption();

      var dictColumnId = dc.dictColumnId;
      var opt = dbDictColumnList.stream()
          .filter(t -> t.dictColumnId.equals(dictColumnId))
          .findFirst();
      if (opt.isPresent()) {
        columnKeyOption.columnName = opt.get().columnName;
        columnKeyOption.length = dc.length;
        columnKeyOption.seqInIndex = dc.seqInIndex;
        columnKeyOption.collation = dc.collation;
      }
      tmpKey.columnKeyOptionList.add(columnKeyOption);
    }
    if (dbKey != null) {
      tmpKey.keyName = dbKey.keyName;
      tmpKey.indexComment = dbKey.indexComment;
      tmpKey.indexType = dbKey.indexType;
    }
    return tmpKey;
  }

  /**
   * create tmp foreign key.
   *
   * @param dbForeignKey           dbForeignKey
   * @param dbForeignKeyColumnList dbForeignKeyColumnList
   * @param dbTableList            dbTableList
   * @return tmp foreign key
   */
  static TmpForeignKey createTmpForeignKey(
      DbTableForeignKey dbForeignKey,
      List<DbTableForeignKeyColumn> dbForeignKeyColumnList,
      List<DbTable> dbTableList,
      List<DbDictColumn> dbDictColumnList) {
    var tmpForeignKey = new TmpForeignKey();
    for (var dc : dbForeignKeyColumnList.stream()
        .sorted(Comparator.comparing(t -> t.seqInIndex)).collect(Collectors.toList())) {
      var columnForeignKeyOption = new ColumnForeignKeyOption();
      var dictColumnId = dc.dictColumnId;
      var opt = dbDictColumnList.stream()
          .filter(t -> t.dictColumnId.equals(dictColumnId))
          .findFirst();
      if (opt.isPresent()) {
        columnForeignKeyOption.columnName = opt.get().columnName;
        columnForeignKeyOption.seqInIndex = dc.seqInIndex;
      }
      tmpForeignKey.columnForeignKeyOptionList.add(columnForeignKeyOption);

      var referenceColumnForeignKeyOption = new ColumnForeignKeyOption();
      var referenceDictColumnId = dc.referenceDictColumnId;
      opt = dbDictColumnList.stream()
          .filter(t -> t.dictColumnId.equals(referenceDictColumnId))
          .findFirst();
      if (opt.isPresent()) {
        referenceColumnForeignKeyOption.columnName = opt.get().columnName;
        referenceColumnForeignKeyOption.seqInIndex = dc.seqInIndex;
      }
      tmpForeignKey.referenceColumnForeignKeyOptionList.add(referenceColumnForeignKeyOption);
    }
    if (dbForeignKey != null) {
      tmpForeignKey.constraintName = dbForeignKey.constraintName;
      tmpForeignKey.keyName = dbForeignKey.keyName;

      var opt = dbTableList.stream()
          .filter(d -> d.tableId.equals(dbForeignKey.referenceTableId))
          .findFirst();
      if (opt.isPresent()) {
        tmpForeignKey.referenceTableName = opt.get().tableName;
      }

      tmpForeignKey.onUpdate = dbForeignKey.onUpdate;
      tmpForeignKey.onDelete = dbForeignKey.onDelete;
      tmpForeignKey.relationship = dbForeignKey.relationship;
    }
    return tmpForeignKey;
  }
}
