package com.walksocket.er.sqlite.entity;

import com.walksocket.er.Utils;
import com.walksocket.er.sqlite.Entity;
import com.walksocket.er.sqlite.Record;
import java.util.List;

/**
 * DbDictGroup.
 */
public class DbDictGroup extends Entity {

  /**
   * dictGroupId.
   */
  public String dictGroupId;

  /**
   * groupName.
   */
  public String groupName;

  @Override
  public void bind(Record record) {
    dictGroupId = record.getOrEmpty("dictGroupId");
    groupName = record.getOrEmpty("groupName");
  }

  @Override
  public String createInsert() {
    return String.format(
        "INSERT INTO DbDictGroup "
            + "(dictGroupId, groupName) "
            + "VALUES "
            + "('%s', '%s')",
        Utils.quote(dictGroupId),
        Utils.quote(groupName)
    );
  }

  @Override
  public String createUpdate() {
    return String.format(
        "UPDATE DbDictGroup SET "
            + "groupName = '%s' "
            + "WHERE "
            + "dictGroupId = '%s' ",
        Utils.quote(groupName),
        Utils.quote(dictGroupId)
    );
  }

  @Override
  public String createDelete() {
    return String.format(
        "DELETE FROM DbDictGroup "
            + "WHERE dictGroupId = '%s' ",
        Utils.quote(dictGroupId)
    );
  }

  @Override
  public List<String> orderColumns() {
    return List.of("dictGroupId");
  }
}
