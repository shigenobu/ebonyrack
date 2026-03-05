package com.walksocket.er.config;

import com.walksocket.er.sqlite.tmp.TmpForeignKeyInfo;

/**
 * CfgProjectForeignKeyInfoHistory.
 */
public class CfgProjectForeignKeyInfoHistory {

  /**
   * exported.
   */
  public String exported;

  /**
   * foreignKeyInfo.
   */
  public TmpForeignKeyInfo foreignKeyInfo;

  @Override
  public int hashCode() {
    return foreignKeyInfo.getHash().hashCode();
  }

  @Override
  public boolean equals(Object obj) {
    return foreignKeyInfo.getHash()
        .equals(((CfgProjectForeignKeyInfoHistory) obj).foreignKeyInfo.getHash());
  }
}
