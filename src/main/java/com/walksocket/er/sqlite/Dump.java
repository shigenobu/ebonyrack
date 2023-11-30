package com.walksocket.er.sqlite;

import com.google.gson.internal.LinkedTreeMap;
import com.walksocket.er.File;
import com.walksocket.er.Json;
import com.walksocket.er.Log;
import com.walksocket.er.config.CfgProject;
import com.walksocket.er.sqlite.entity.DbDefault;
import com.walksocket.er.sqlite.entity.DbDictColumn;
import com.walksocket.er.sqlite.entity.DbDictColumnType;
import com.walksocket.er.sqlite.entity.DbDictGroup;
import com.walksocket.er.sqlite.entity.DbDictGroupColumn;
import com.walksocket.er.sqlite.entity.DbNote;
import com.walksocket.er.sqlite.entity.DbNoteConnectorSequence;
import com.walksocket.er.sqlite.entity.DbNoteConnectorTable;
import com.walksocket.er.sqlite.entity.DbNoteOption;
import com.walksocket.er.sqlite.entity.DbSequence;
import com.walksocket.er.sqlite.entity.DbSequenceOption;
import com.walksocket.er.sqlite.entity.DbTable;
import com.walksocket.er.sqlite.entity.DbTableColumn;
import com.walksocket.er.sqlite.entity.DbTableForeignKey;
import com.walksocket.er.sqlite.entity.DbTableForeignKeyColumn;
import com.walksocket.er.sqlite.entity.DbTableGroup;
import com.walksocket.er.sqlite.entity.DbTableKey;
import com.walksocket.er.sqlite.entity.DbTableKeyColumn;
import com.walksocket.er.sqlite.entity.DbTableOption;
import com.walksocket.er.sqlite.entity.DbTablePrimaryKey;
import com.walksocket.er.sqlite.entity.DbTablePrimaryKeyColumn;
import com.walksocket.er.sqlite.entity.DbTableUniqueKey;
import com.walksocket.er.sqlite.entity.DbTableUniqueKeyColumn;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Dump.
 */
public class Dump {

  /**
   * extension.
   */
  public static String EXTENSION = "ebjson";

  /**
   * classes.
   */
  private static final List<Class> classes = new ArrayList<>();

  static {
    classes.add(DbDefault.class);
    classes.add(DbDictColumnType.class);
    classes.add(DbDictColumn.class);
    classes.add(DbDictGroup.class);
    classes.add(DbDictGroupColumn.class);
    classes.add(DbTable.class);
    classes.add(DbTableOption.class);
    classes.add(DbTableColumn.class);
    classes.add(DbTableGroup.class);
    classes.add(DbTablePrimaryKey.class);
    classes.add(DbTablePrimaryKeyColumn.class);
    classes.add(DbTableUniqueKey.class);
    classes.add(DbTableUniqueKeyColumn.class);
    classes.add(DbTableKey.class);
    classes.add(DbTableKeyColumn.class);
    classes.add(DbTableForeignKey.class);
    classes.add(DbTableForeignKeyColumn.class);
    classes.add(DbSequence.class);
    classes.add(DbSequenceOption.class);
    classes.add(DbNote.class);
    classes.add(DbNoteOption.class);
    classes.add(DbNoteConnectorTable.class);
    classes.add(DbNoteConnectorSequence.class);
  }

  /**
   * write out.
   *
   * @param cfgProject cfgProject
   * @param path       out path
   * @return if success, true
   */
  public static boolean writeOut(CfgProject cfgProject, String path) {
    Map<String, List<Entity>> data = new LinkedHashMap<>();
    try (var con = new Connection(cfgProject.dbPath)) {
      con.setReadonly(true);

      for (var cls : classes) {
        data.put(cls.getSimpleName(), new ArrayList<>());

        Entity dummy = (Entity) cls.getDeclaredConstructor().newInstance();
        var order = dummy.orderColumns().stream()
            .collect(Collectors.joining(", "));
        var sql = String.format("SELECT * FROM %s ORDER BY %s", cls.getSimpleName(), order);
        var records = con.getRecords(sql);
        for (var record : records) {
          Entity instance = (Entity) cls.getDeclaredConstructor().newInstance();
          instance.bind(record);

          data.get(cls.getSimpleName()).add(instance);
        }
      }
      File.writeString(new FileOutputStream(path), Json.toJsonStringFriendly(data));
      return true;
    } catch (Exception e) {
      Log.error(e);
    }
    return false;
  }

  /**
   * read from.
   *
   * @param cfgProject cfgProject
   * @param path       read path
   * @return if success, true
   */
  public static boolean readFrom(CfgProject cfgProject, String path) {
    try (var con = new Connection(cfgProject.dbPath)) {
      con.begin();

      var tmpClasses = new ArrayList<>(classes);
      Collections.reverse(tmpClasses);
      for (var cls : tmpClasses) {
        var sql = String.format("DELETE FROM %s", cls.getSimpleName());
        con.execute(sql);
      }

      Map<String, ?> data = Json.toObject(File.readString(new FileInputStream(path)), Map.class);
      for (var entry : data.entrySet()) {
        var clsName = entry.getKey();
        var cls = classes.stream()
            .filter(c -> c.getSimpleName().equals(clsName))
            .findFirst()
            .get();
        for (var tm : (List<LinkedTreeMap>) entry.getValue()) {
          var entity = (Entity) cls.getDeclaredConstructor().newInstance();
          Map<String, String> r = new HashMap<>();
          for (var k : tm.keySet()) {
            var v = tm.get(k);
            var nk = String.valueOf(k);
            var nv = String.valueOf(v);
            if (v.getClass() == Double.class) {
              r.put(nk, String.valueOf(Double.valueOf(nv).longValue()));
            } else {
              r.put(nk, nv);
            }
          }
          var list = new ArrayList<Map<String, String>>();
          list.add(r);
          entity.bind(new Record(list));
          var sql = entity.createInsert();
          con.execute(sql);
        }
      }
      con.commit();
      return true;
    } catch (Exception e) {
      Log.error(e);
    }
    return false;
  }
}