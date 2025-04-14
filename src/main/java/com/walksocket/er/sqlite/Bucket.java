package com.walksocket.er.sqlite;

import com.walksocket.er.App;
import com.walksocket.er.Const;
import com.walksocket.er.FileUtils;
import com.walksocket.er.Log;
import com.walksocket.er.Utils;
import com.walksocket.er.definition.DataType;
import com.walksocket.er.sqlite.bucket.BucketConnector;
import com.walksocket.er.sqlite.bucket.BucketDefault;
import com.walksocket.er.sqlite.bucket.BucketDict;
import com.walksocket.er.sqlite.bucket.BucketNote;
import com.walksocket.er.sqlite.bucket.BucketSequence;
import com.walksocket.er.sqlite.bucket.BucketTable;
import com.walksocket.er.sqlite.context.CtxNote;
import com.walksocket.er.sqlite.context.CtxSequence;
import com.walksocket.er.sqlite.context.CtxTable;
import com.walksocket.er.sqlite.entity.DbDefault;
import com.walksocket.er.sqlite.entity.DbDictColumnType;
import com.walksocket.er.sqlite.entity.DbTable;
import com.walksocket.er.sqlite.entity.DbTableForeignKey;
import com.walksocket.er.sqlite.tmp.TmpColumn;
import com.walksocket.er.sqlite.tmp.TmpForeignKey;
import com.walksocket.er.sqlite.tmp.TmpKey;
import com.walksocket.er.template.ErTemplate;
import java.io.File;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.file.StandardOpenOption;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Bucket.
 */
public class Bucket {

  /**
   * bucket.
   */
  private static Bucket bucket;

  /**
   * lock.
   */
  private static FileLock lock;

  /**
   * create ddl.
   *
   * @param con connection
   * @throws SQLException
   * @throws IOException
   */
  public static void createDdl(Connection con) throws SQLException, IOException {
    try (var stream = App.class.getClassLoader()
        .getResourceAsStream("database/create.sql")) {
      // ----------
      // load create sql
      for (var ddl : FileUtils.readString(stream).split(";")) {
        var builder = new StringBuilder();
        for (var line : ddl.split("\n")) {
          line = line.trim();
          if (Utils.isNullOrEmpty(line)) {
            continue;
          }
          if (line.startsWith("--")) {
            continue;
          }
          builder.append(line);
        }
        var q = builder.toString();
        if (!Utils.isNullOrEmpty(q)) {
          con.execute(q);
        }
      }
    }
  }

  /**
   * init.
   *
   * @param con con
   * @throws Exception
   */
  public static void init(Connection con) throws Exception {
    // ----------
    var lockPath = con.getDbPath() + ".exlock";
    var f = new File(lockPath);
    var lockChannel = FileChannel.open(f.toPath(), StandardOpenOption.CREATE,
        StandardOpenOption.WRITE);
    lock = lockChannel.tryLock();
    if (lock == null) {
      throw new Exception("Already locked at project.");
    }

    // ----------
    createDdl(con);

    // ----------
    con.begin();

    // init default
    var sql = "SELECT * FROM DbDefault LIMIT 1";
    var record = con.getRecord(sql);
    if (record == null) {
      var dbDefault = new DbDefault();
      con.executeInsert(dbDefault);
    }

    // init dict column type
    sql = "SELECT * FROM DbDictColumnType LIMIT 1";
    record = con.getRecord(sql);
    if (record == null) {
      var seq = 101;
      for (int i = 0; i < DataType.getDataTypeList().size(); i++) {
        var dataType = DataType.getDataTypeList().get(i);
        for (int j = 0; j < dataType.getLengths().length; j++) {
          var l = dataType.getLengths()[j];

          var dbDictColumnType = new DbDictColumnType();
          dbDictColumnType.dictColumnTypeId = String.format("PRESET_%05d", seq);
          dbDictColumnType.seq = seq;
          if (l == 0) {
            dbDictColumnType.columnType = dataType.getTypeName();
          } else {
            dbDictColumnType.columnType = String.format("%s(%s)", dataType.getTypeName(), l);
          }
          dbDictColumnType.remarks = dataType.getRemarks();
          con.executeInsert(dbDictColumnType);
          seq += 100;
          if (l == 0) {
            break;
          }
        }
      }
    }
    con.commit();
    bucket = new Bucket(con);
  }

  /**
   * get instance.
   *
   * @return bucket
   */
  public static Bucket getInstance() {
    return bucket;
  }

  /**
   * release.
   */
  public static void release() {
    bucket.by();
    bucket = null;

    // ----------
    if (lock != null) {
      try {
        lock.release();
      } catch (IOException e) {
        Log.error(e);
      }
    }
  }

  /**
   * con.
   */
  private final Connection con;

  /**
   * bucket default.
   */
  private final BucketDefault bucketDefault;

  /**
   * bucket dict.
   */
  private final BucketDict bucketDict;

  /**
   * bucket table.
   */
  private final BucketTable bucketTable;

  /**
   * bucket sequence.
   */
  private final BucketSequence bucketSequence;

  /**
   * bucket note.
   */
  private final BucketNote bucketNote;

  /**
   * bucket connector.
   */
  private final BucketConnector bucketConnector;

  /**
   * Constructor.
   *
   * @param con con
   */
  private Bucket(Connection con) {
    this.con = con;

    this.bucketDefault = new BucketDefault(con);
    this.bucketDict = new BucketDict(con);
    this.bucketTable = new BucketTable(con);
    this.bucketSequence = new BucketSequence(con);
    this.bucketNote = new BucketNote(con);
    this.bucketConnector = new BucketConnector(con);
  }

  /**
   * by.
   */
  private void by() {
    try {
      con.close();
    } catch (Exception e) {
      Log.error(e);
    }
  }

  /**
   * is readonly.
   *
   * @return if readonly, return true
   */
  public boolean isReadOnly() {
    return con.isReadonly();
  }

  /**
   * get bucket default.
   *
   * @return bucket default
   */
  public BucketDefault getBucketDefault() {
    return bucketDefault;
  }

  /**
   * get bucket dict.
   *
   * @return bucket dict
   */
  public BucketDict getBucketDict() {
    return bucketDict;
  }

  /**
   * get bucket table.
   *
   * @return bucket table
   */
  public BucketTable getBucketTable() {
    return bucketTable;
  }

  /**
   * get bucket sequence.
   *
   * @return bucket sequence
   */
  public BucketSequence getBucketSequence() {
    return bucketSequence;
  }

  /**
   * get bucket note.
   *
   * @return bucket note
   */
  public BucketNote getBucketNote() {
    return bucketNote;
  }

  /**
   * get bucket connector.
   *
   * @return bucket connector
   */
  public BucketConnector getBucketConnector() {
    return bucketConnector;
  }

  /**
   * get sequence ddl.
   *
   * @return ddl
   */
  public String getSequenceDdl(CtxSequence ctxSequence) {
    var dbSequence = ctxSequence.dbSequence;

    // note
    var relatedNoteIdList = getBucketConnector().dbNoteConnectorSequenceList.stream()
        .filter(c -> c.sequenceId.equals(dbSequence.sequenceId))
        .map(c -> c.noteId)
        .collect(Collectors.toList());
    var relatedCtxNoteList = getBucketNote().ctxNoteList.stream()
        .filter(n -> relatedNoteIdList.contains(n.dbNote.noteId))
        .sorted(
            Comparator.comparing(n -> String.format("%s-%s", n.dbNote.subject, n.dbNote.noteId)))
        .collect(Collectors.toList());

    var builder = new StringBuilder();
    if (dbSequence != null && !dbSequence.sequenceName.startsWith(Const.NEW_SEQUENCE_PREFIX)) {
      // note
      for (var relatedCtxNote : relatedCtxNoteList) {
        if (Utils.isNullOrEmpty(relatedCtxNote.dbNote.subject)
            || relatedCtxNote.dbNote.subject.startsWith(Const.NEW_NOTE_PREFIX)) {
          continue;
        }

        builder.append(String.format("-- %s :\n", relatedCtxNote.dbNote.subject));
        if (!Utils.isNullOrEmpty(relatedCtxNote.dbNote.body)) {
          var bodies = relatedCtxNote.dbNote.body.split("\n");
          for (var body : bodies) {
            if (!Utils.isNullOrEmpty(body)) {
              builder.append(String.format("-- -- %s\n", body));
            }
          }
        }
      }

      // CREATE SEQUENCE `s_1` start with 1 minvalue 1 maxvalue 9223372036854775806 increment by 1 cache 1000 nocycle ENGINE=InnoDB;
      builder.append(String.format("CREATE SEQUENCE `%s`\n", dbSequence.sequenceName));
      builder.append(String.format("    start with %s\n", dbSequence.startValue));
      builder.append(String.format("    minvalue %s\n", dbSequence.minimumValue));
      builder.append(String.format("    maxvalue %s\n", dbSequence.maximumValue));
      builder.append(String.format("    increment by %s\n", dbSequence.incrementValue));
      builder.append(String.format("    cache %s\n", dbSequence.cacheSize));
      builder.append(String.format("    %s\n", dbSequence.cycle));
      builder.append(String.format("    %s\n", "ENGINE=InnoDB"));
      builder.append(";");
    }
    return builder.toString();
  }

  /**
   * get table ddl.
   *
   * @param ctxTable ctxTable
   * @return ddl
   */
  public String getTableDdl(CtxTable ctxTable) {
    var dbDefault = getBucketDefault().dbDefault;

    var dbTable = ctxTable.dbTable;
    var dbTableColumnList = ctxTable.dbTableColumnList;
    var dbTableGroup = ctxTable.dbTableGroup;
    var dbTablePartition = ctxTable.dbTablePartition;

    var ctxInnerPrimaryKey = ctxTable.ctxInnerPrimaryKey;
    var ctxInnerUniqueKeyList = ctxTable.ctxInnerUniqueKeyList;
    var ctxInnerKeyList = ctxTable.ctxInnerKeyList;

    var dbDictColumnTypeList = getBucketDict().dbDictColumnTypeList;
    var dbDictColumnList = getBucketDict().dbDictColumnList;
    var dbDictGroupList = getBucketDict().dbDictGroupList;
    var dbDictGroupColumnList = getBucketDict().dbDictGroupColumnList;
    var dbDictPartitionList = getBucketDict().dbDictPartitionList;

    var dbTableCheckList = ctxTable.dbTableCheckList;

    // note
    var relatedNoteIdList = getBucketConnector().dbNoteConnectorTableList.stream()
        .filter(c -> c.tableId.equals(dbTable.tableId))
        .map(c -> c.noteId)
        .collect(Collectors.toList());
    var relatedCtxNoteList = getBucketNote().ctxNoteList.stream()
        .filter(n -> relatedNoteIdList.contains(n.dbNote.noteId))
        .sorted(
            Comparator.comparing(n -> String.format("%s-%s", n.dbNote.subject, n.dbNote.noteId)))
        .collect(Collectors.toList());

    var builder = new StringBuilder();
    if (dbTable != null && !dbTable.tableName.startsWith(Const.NEW_TABLE_PREFIX)
        && dbTableColumnList.size() > 0) {
      // note
      for (var relatedCtxNote : relatedCtxNoteList) {
        if (Utils.isNullOrEmpty(relatedCtxNote.dbNote.subject)
            || relatedCtxNote.dbNote.subject.startsWith(Const.NEW_NOTE_PREFIX)) {
          continue;
        }

        builder.append(String.format("-- %s :\n", relatedCtxNote.dbNote.subject));
        if (!Utils.isNullOrEmpty(relatedCtxNote.dbNote.body)) {
          var bodies = relatedCtxNote.dbNote.body.split("\n");
          for (var body : bodies) {
            if (!Utils.isNullOrEmpty(body)) {
              builder.append(String.format("-- -- %s\n", body));
            }
          }
        }
      }

      // table - first
      builder.append(String.format("CREATE TABLE `%s`\n", dbTable.tableName));
      builder.append("(\n");

      // column
      var columns = new ArrayList<String>();
      for (var tmpColumn : Tmp.createTmpColumnList(dbTableColumnList, dbDictColumnTypeList,
          dbDictColumnList)) {
        var b = new StringBuilder();
        b.append(String.format("`%s` %s", tmpColumn.columnName, tmpColumn.columnType));
        if (!Utils.isNullOrEmpty(tmpColumn.charsetValue)) {
          b.append(String.format(" CHARACTER SET %s", tmpColumn.charsetValue));
        }
        if (!Utils.isNullOrEmpty(tmpColumn.collateValue)) {
          b.append(String.format(" COLLATE %s", tmpColumn.collateValue));
        }
        if (!Utils.isNullOrEmpty(tmpColumn.notNullValue)) {
          b.append(String.format(" %s", tmpColumn.notNullValue));
        }
        if (!Utils.isNullOrEmpty(tmpColumn.defaultValue)) {
          b.append(String.format(" DEFAULT %s", tmpColumn.defaultValue));
        }
        if (!Utils.isNullOrEmpty(tmpColumn.onUpdate)) {
          b.append(String.format(" ON UPDATE %s", tmpColumn.onUpdate));
        }
        if (!Utils.isNullOrEmpty(tmpColumn.autoIncrementDefinition)) {
          b.append(String.format(" %s", tmpColumn.autoIncrementDefinition));
        }
        if (!Utils.isNullOrEmpty(tmpColumn.option)) {
          b.append(String.format(" %s", tmpColumn.option));
        }
        if (!Utils.isNullOrEmpty(tmpColumn.columnComment)) {
          b.append(String.format(" COMMENT '%s'", Utils.quote(tmpColumn.columnComment)));
        }

        columns.add(b.toString());
      }
      if (dbTableGroup != null) {
        for (var tmpColumn : Tmp.createTmpGroupColumnList(dbTableGroup, dbDictColumnTypeList,
            dbDictColumnList, dbDictGroupList, dbDictGroupColumnList)) {
          var b = new StringBuilder();
          b.append(String.format("`%s` %s", tmpColumn.columnName, tmpColumn.columnType));
          if (!Utils.isNullOrEmpty(tmpColumn.charsetValue)) {
            b.append(String.format(" CHARACTER SET %s", tmpColumn.charsetValue));
          }
          if (!Utils.isNullOrEmpty(tmpColumn.collateValue)) {
            b.append(String.format(" COLLATE %s", tmpColumn.collateValue));
          }
          if (!Utils.isNullOrEmpty(tmpColumn.notNullValue)) {
            b.append(String.format(" %s", tmpColumn.notNullValue));
          }
          if (!Utils.isNullOrEmpty(tmpColumn.defaultValue)) {
            b.append(String.format(" DEFAULT %s", tmpColumn.defaultValue));
          }
          if (!Utils.isNullOrEmpty(tmpColumn.onUpdate)) {
            b.append(String.format(" ON UPDATE %s", tmpColumn.onUpdate));
          }
          if (!Utils.isNullOrEmpty(tmpColumn.autoIncrementDefinition)) {
            b.append(String.format(" %s", tmpColumn.autoIncrementDefinition));
          }
          if (!Utils.isNullOrEmpty(tmpColumn.option)) {
            b.append(String.format(" %s", tmpColumn.option));
          }
          if (!Utils.isNullOrEmpty(tmpColumn.columnComment)) {
            b.append(String.format(" COMMENT '%s'", Utils.quote(tmpColumn.columnComment)));
          }

          columns.add(b.toString());
        }
      }
      var column = columns.stream()
          .map(c -> c = "    " + c)
          .collect(Collectors.joining(",\n"));
      builder.append(column);

      // primary key
      var tmpPrimaryKey = Tmp.createTmpKey(ctxInnerPrimaryKey.dbTablePrimaryKey,
          ctxInnerPrimaryKey.dbTablePrimaryKeyColumnList, dbDictColumnList);
      var primaryColumnAndCollation = tmpPrimaryKey.getColumnsAndCollations();
      if (!Utils.isNullOrEmpty(primaryColumnAndCollation)) {
        builder.append(
            String.format(",\n    PRIMARY KEY (%s)", primaryColumnAndCollation));
        if (!Utils.isNullOrEmpty(tmpPrimaryKey.indexType)) {
          builder.append(String.format(" USING %s", tmpPrimaryKey.indexType));
        }
        if (!Utils.isNullOrEmpty(tmpPrimaryKey.indexComment)) {
          builder.append(String.format(" COMMENT '%s'", Utils.quote(tmpPrimaryKey.indexComment)));
        }
      }

      // unique key
      for (var ctxInnerUniqueKey : ctxInnerUniqueKeyList) {
        var tmpUniqueKey = Tmp.createTmpKey(ctxInnerUniqueKey.dbTableUniqueKey,
            ctxInnerUniqueKey.dbTableUniqueKeyColumnList, dbDictColumnList);
        var uniqueColumnAndCollation = tmpUniqueKey.getColumnsAndCollations();
        if (!Utils.isNullOrEmpty(uniqueColumnAndCollation)) {
          builder.append(
              String.format(",\n    UNIQUE KEY `%s` (%s)", tmpUniqueKey.keyName,
                  uniqueColumnAndCollation));
          if (!Utils.isNullOrEmpty(tmpUniqueKey.indexType)) {
            builder.append(String.format(" USING %s", tmpUniqueKey.indexType));
          }
          if (!Utils.isNullOrEmpty(tmpUniqueKey.indexComment)) {
            builder.append(String.format(" COMMENT '%s'", Utils.quote(tmpUniqueKey.indexComment)));
          }
        }
      }

      // key
      for (var ctxInnerKey : ctxInnerKeyList) {
        var tmpKey = Tmp.createTmpKey(ctxInnerKey.dbTableKey, ctxInnerKey.dbTableKeyColumnList,
            dbDictColumnList);
        var keyColumnAndCollation = tmpKey.getColumnsAndCollations();
        if (!Utils.isNullOrEmpty(keyColumnAndCollation)) {
          builder.append(
              String.format(",\n    KEY `%s` (%s)", tmpKey.keyName, keyColumnAndCollation));
          if (!Utils.isNullOrEmpty(tmpKey.indexType)) {
            builder.append(String.format(" USING %s", tmpKey.indexType));
          }
          if (!Utils.isNullOrEmpty(tmpKey.indexComment)) {
            builder.append(String.format(" COMMENT '%s'", Utils.quote(tmpKey.indexComment)));
          }
        }
      }

      // check
      for (var dbTableCheck : dbTableCheckList) {
        builder.append(
            String.format(",\n    CONSTRAINT `%s` CHECK (%s)", dbTableCheck.constraintName,
                dbTableCheck.expression));
      }

      // table - last
      builder.append("\n)");
      if (!Utils.isNullOrEmpty(dbTable.engine)) {
        builder.append(String.format(" ENGINE=%s", dbTable.engine));
      } else if (!Utils.isNullOrEmpty(dbDefault.defaultEngine)) {
        builder.append(String.format(" ENGINE=%s", dbDefault.defaultEngine));
      }
      if (!Utils.isNullOrEmpty(dbTable.autoIncrementValue)) {
        builder.append(String.format(" AUTO_INCREMENT=%s", dbTable.autoIncrementValue));
      }
      if (!Utils.isNullOrEmpty(dbTable.charsetValue)) {
        builder.append(String.format(" CHARACTER SET=%s", dbTable.charsetValue));
      } else if (!Utils.isNullOrEmpty(dbDefault.defaultCharset)) {
        builder.append(String.format(" CHARACTER SET=%s", dbDefault.defaultCharset));
      }
      if (!Utils.isNullOrEmpty(dbTable.collateValue)) {
        builder.append(String.format(" COLLATE=%s", dbTable.collateValue));
      } else if (!Utils.isNullOrEmpty(dbDefault.defaultCollate)) {
        builder.append(String.format(" COLLATE=%s", dbDefault.defaultCollate));
      }
      if (!Utils.isNullOrEmpty(dbTable.option)) {
        builder.append(String.format(" %s", dbTable.option));
      }
      if (!Utils.isNullOrEmpty(dbTable.tableComment)) {
        builder.append(String.format(" COMMENT='%s'", Utils.quote(dbTable.tableComment)));
      }
      builder.append("\n");

      // partition
      if (dbTablePartition != null) {
        var opt = dbDictPartitionList.stream()
            .filter(p -> p.dictPartitionId.equals(dbTablePartition.dictPartitionId))
            .findFirst();
        if (opt.isPresent()) {
          builder.append(
              String.format("  %s", opt.get().expression));
          builder.append("\n");
        }
      }

      builder.append(";\n");
    }
    return builder.toString();
  }

  /**
   * get foreign key ddl.
   *
   * @param ctxTable ctxTable
   * @return foreign key ddl
   */
  public String getForeignKeyDdl(CtxTable ctxTable) {
    var dbDefault = getBucketDefault().dbDefault;

    var dbTable = ctxTable.dbTable;
    var dbTableColumnList = ctxTable.dbTableColumnList;
    var dbTableGroup = ctxTable.dbTableGroup;

    var ctxInnerPrimaryKey = ctxTable.ctxInnerPrimaryKey;
    var ctxInnerUniqueKeyList = ctxTable.ctxInnerUniqueKeyList;
    var ctxInnerKeyList = ctxTable.ctxInnerKeyList;
    var ctxInnerForeignKeyList = ctxTable.ctxInnerForeignKeyList;

    var dbTableList = getBucketTable().ctxTableList.stream().map(d -> d.dbTable)
        .collect(Collectors.toList());
    var dbDictColumnTypeList = getBucketDict().dbDictColumnTypeList;
    var dbDictColumnList = getBucketDict().dbDictColumnList;
    var dbDictGroupList = getBucketDict().dbDictGroupList;
    var dbDictGroupColumnList = getBucketDict().dbDictGroupColumnList;

    var builder = new StringBuilder();
    if (dbTable != null && !dbTable.tableName.startsWith(Const.NEW_TABLE_PREFIX)
        && dbTableColumnList.size() > 0 && ctxInnerForeignKeyList.size() > 0) {
      for (var ctxInnerForeignKey : ctxInnerForeignKeyList) {
        var tmpForeignKey = Tmp.createTmpForeignKey(ctxInnerForeignKey.dbTableForeignKey,
            ctxInnerForeignKey.dbTableForeignKeyColumnList, dbTableList, dbDictColumnList);
        builder.append(String.format("ALTER TABLE `%s` ADD \n", dbTable.tableName));

        // constraint
        builder.append(String.format("    CONSTRAINT `%s`\n", tmpForeignKey.constraintName));

        // foreign key
        builder.append(String.format("    FOREIGN KEY `%s` (%s)\n", tmpForeignKey.keyName,
            tmpForeignKey.getColumns()));

        // references
        builder.append(String.format("    REFERENCES `%s` (%s)\n", tmpForeignKey.referenceTableName,
            tmpForeignKey.getReferenceColumns()));

        // on update
        if (!Utils.isNullOrEmpty(tmpForeignKey.onUpdate)) {
          builder.append(String.format("    ON UPDATE %s\n", tmpForeignKey.onUpdate));
        }

        // on delete
        if (!Utils.isNullOrEmpty(tmpForeignKey.onDelete)) {
          builder.append(String.format("    ON DELETE %s\n", tmpForeignKey.onDelete));
        }

        builder.append(";\n\n");
      }
    }
    return builder.toString();
  }

  /**
   * assign table vars.
   *
   * @param ctxTable ctxTable
   * @param template template
   */
  public void assignTableVars(CtxTable ctxTable, ErTemplate template) {
    var dbTableList = Bucket.getInstance().getBucketTable().ctxTableList.stream()
        .map(c -> c.dbTable)
        .collect(Collectors.toList());
    template.assign("dbTableList", dbTableList);

    var dbDictColumnTypeList = Bucket.getInstance().getBucketDict().dbDictColumnTypeList;
    var dbDictColumnList = Bucket.getInstance().getBucketDict().dbDictColumnList;
    var dbDictGroupList = Bucket.getInstance().getBucketDict().dbDictGroupList;
    var dbDictGroupColumnList = Bucket.getInstance().getBucketDict().dbDictGroupColumnList;

    var dbTableForeignKeyList = new ArrayList<DbTableForeignKey>();
    for (var tmpCtxTable : Bucket.getInstance().getBucketTable().ctxTableList) {
      for (var ctxInnerForeignKey : tmpCtxTable.ctxInnerForeignKeyList) {
        dbTableForeignKeyList.add(ctxInnerForeignKey.dbTableForeignKey);
      }
    }

    // table
    template.assign("ctxTable", ctxTable);
    template.assign("tmpTable", Tmp.createTmpTable(ctxTable.dbTable));

    // column
    var tmpColumnList = Tmp.createTmpColumnList(
        ctxTable.dbTableColumnList,
        dbDictColumnTypeList,
        dbDictColumnList
    );
    List<TmpColumn> tmpGroupColumnList = new ArrayList<>();
    if (ctxTable.dbTableGroup != null) {
      // group column
      tmpGroupColumnList = Tmp.createTmpGroupColumnList(
          ctxTable.dbTableGroup,
          dbDictColumnTypeList,
          dbDictColumnList,
          dbDictGroupList,
          dbDictGroupColumnList
      );
      tmpColumnList.addAll(tmpGroupColumnList);
    }
    template.assign("tmpColumnList", tmpColumnList);
    template.assign("tmpGroupColumnList", tmpGroupColumnList);

    // primary
    TmpKey tmpPrimaryKey = null;
    if (ctxTable.ctxInnerPrimaryKey.dbTablePrimaryKey != null) {
      tmpPrimaryKey = Tmp.createTmpKey(
          ctxTable.ctxInnerPrimaryKey.dbTablePrimaryKey,
          ctxTable.ctxInnerPrimaryKey.dbTablePrimaryKeyColumnList,
          dbDictColumnList);
    }
    template.assign("tmpPrimaryKey", tmpPrimaryKey);

    // unique
    List<TmpKey> tmpUniqueKeyList = new ArrayList<>();
    for (var t : ctxTable.ctxInnerUniqueKeyList) {
      tmpUniqueKeyList.add(Tmp.createTmpKey(
          t.dbTableUniqueKey,
          t.dbTableUniqueKeyColumnList,
          dbDictColumnList));
    }
    template.assign("tmpUniqueKeyList", tmpUniqueKeyList);

    // key
    List<TmpKey> tmpKeyList = new ArrayList<>();
    for (var t : ctxTable.ctxInnerKeyList) {
      tmpKeyList.add(Tmp.createTmpKey(
          t.dbTableKey,
          t.dbTableKeyColumnList,
          dbDictColumnList));
    }
    template.assign("tmpKeyList", tmpKeyList);

    // foreign key
    List<TmpForeignKey> tmpForeignKeyList = new ArrayList<>();
    for (var t : ctxTable.ctxInnerForeignKeyList) {
      tmpForeignKeyList.add(Tmp.createTmpForeignKey(
          t.dbTableForeignKey,
          t.dbTableForeignKeyColumnList,
          dbTableList,
          dbDictColumnList));
    }
    template.assign("tmpForeignKeyList", tmpForeignKeyList);

    // referenced tables
    var referencedDbTableList = new ArrayList<DbTable>();
    var referenceDbTableForeignKeyList = dbTableForeignKeyList.stream()
        .filter(d -> d.referenceTableId.equals(ctxTable.dbTable.tableId))
        .collect(Collectors.toList());
    for (var referenceDbTableForeignKey : referenceDbTableForeignKeyList) {
      var referencedDbTable = dbTableList.stream()
          .filter(d -> d.tableId.equals(referenceDbTableForeignKey.tableId))
          .findFirst()
          .get();
      referencedDbTableList.add(referencedDbTable);
    }
    template.assign("referencedDbTableList", referencedDbTableList);

    // ddl
    var builder = new StringBuilder();
    builder.append(Bucket.getInstance().getTableDdl(ctxTable));
    var fkDdl = Bucket.getInstance().getForeignKeyDdl(ctxTable);
    if (!Utils.isNullOrEmpty(fkDdl)) {
      builder.append("\n");
      builder.append(fkDdl);
    }
    template.assign("ddl", builder.toString());

    // note
    var relatedNoteIdList = getBucketConnector().dbNoteConnectorTableList.stream()
        .filter(c -> c.tableId.equals(ctxTable.dbTable.tableId))
        .map(c -> c.noteId)
        .collect(Collectors.toList());
    var relatedCtxNoteList = getBucketNote().ctxNoteList.stream()
        .filter(n -> relatedNoteIdList.contains(n.dbNote.noteId))
        .sorted(
            Comparator.comparing(n -> String.format("%s-%s", n.dbNote.subject, n.dbNote.body)))
        .collect(Collectors.toList());
    template.assign("relatedCtxNoteList", relatedCtxNoteList);
  }

  /**
   * assign sequence vars.
   *
   * @param ctxSequence ctxSequence
   * @param template    template
   */
  public void assignSequenceVars(CtxSequence ctxSequence, ErTemplate template) {
    // sequence
    template.assign("ctxSequence", ctxSequence);
    template.assign("tmpSequence", Tmp.createTmpSequence(ctxSequence.dbSequence));

    // ddl
    template.assign("ddl", Bucket.getInstance().getSequenceDdl(ctxSequence));

    // note
    var relatedNoteIdList = getBucketConnector().dbNoteConnectorSequenceList.stream()
        .filter(c -> c.sequenceId.equals(ctxSequence.dbSequence.sequenceId))
        .map(c -> c.noteId)
        .collect(Collectors.toList());
    var relatedCtxNoteList = getBucketNote().ctxNoteList.stream()
        .filter(n -> relatedNoteIdList.contains(n.dbNote.noteId))
        .sorted(
            Comparator.comparing(n -> String.format("%s-%s", n.dbNote.subject, n.dbNote.body)))
        .collect(Collectors.toList());
    template.assign("relatedCtxNoteList", relatedCtxNoteList);
  }

  /**
   * assign note vars.
   *
   * @param ctxNote  ctxNote
   * @param template template
   */
  public void assignNoteVars(CtxNote ctxNote, ErTemplate template) {
    // note
    template.assign("ctxNote", ctxNote);
  }
}
