package com.walksocket.er.config;

import com.walksocket.er.sqlite.tmp.TmpDdl;

/**
 * CfgProjectDdlHistory.
 */
public class CfgProjectDdlHistory {

  /**
   * exported.
   */
  public String exported;

  /**
   * ddl.
   */
  public TmpDdl ddl;

  @Override
  public int hashCode() {
    return ddl.getHash().hashCode();
  }

  @Override
  public boolean equals(Object obj) {
    return ddl.getHash().equals(((CfgProjectDdlHistory) obj).ddl.getHash());
  }
}
