package com.walksocket.er.sqlite;

/**
 * EntityKey.
 */
public abstract class EntityKey extends Entity {

  /**
   * tableId.
   */
  public String tableId;

  /**
   * seq.
   */
  public int seq;

  /**
   * keyName.
   */
  public String keyName = "";

  /**
   * indexComment.
   */
  public String indexComment = "";

  /**
   * indexType.
   */
  public String indexType = "";
}
