package com.walksocket.er.sqlite.tmp;

import com.walksocket.er.sqlite.Tmp;

/**
 * TmpDdl.
 */
public class TmpDdl implements Tmp {

  /**
   * selected sequence.
   */
  public boolean selectedSequence;

  /**
   * selected table.
   */
  public boolean selectedTable;

  /**
   * selected foreign key.
   */
  public boolean selectedForeignKey;
}
