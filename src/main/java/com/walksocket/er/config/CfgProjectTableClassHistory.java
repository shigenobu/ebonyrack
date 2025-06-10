package com.walksocket.er.config;

import com.walksocket.er.sqlite.tmp.TmpTableClass;

/**
 * CfgProjectTableClassHistory.
 */
public class CfgProjectTableClassHistory {

  /**
   * exported.
   */
  public String exported;

  /**
   * tableClass.
   */
  public TmpTableClass tableClass;

  @Override
  public int hashCode() {
    return tableClass.getHash().hashCode();
  }

  @Override
  public boolean equals(Object obj) {
    return tableClass.getHash().equals(((CfgProjectTableClassHistory) obj).tableClass.getHash());
  }
}
