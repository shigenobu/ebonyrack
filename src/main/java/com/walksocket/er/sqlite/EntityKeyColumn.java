package com.walksocket.er.sqlite;

import com.walksocket.er.definition.Collation;

/**
 * EntityKeyColumn.
 */
public abstract class EntityKeyColumn extends Entity {

  /**
   * tableId.
   */
  public String tableId;

  /**
   * seq.
   */
  public int seq;

  /**
   * dictColumnId.
   */
  public String dictColumnId;

  /**
   * length.
   */
  public String length = "";

  /**
   * seqInIndex.
   */
  public String seqInIndex = "";

  /**
   * collation.
   */
  public String collation = Collation.ASC_VALUE;
}
