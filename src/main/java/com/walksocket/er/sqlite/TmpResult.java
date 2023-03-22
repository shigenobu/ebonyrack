package com.walksocket.er.sqlite;

import java.util.ArrayList;
import java.util.List;

/**
 * TmpResult.
 */
public abstract class TmpResult<T extends Tmp> {

  /**
   * tmp.
   */
  protected List<T> tmpList;

  /**
   * Constructor.
   *
   * @param tmp tmp
   */
  public TmpResult(T tmp) {
    this.tmpList = new ArrayList<T>();
    this.tmpList.add(tmp);
  }

  /**
   * Constructor.
   *
   * @param tmpList tmpList
   */
  public TmpResult(List<T> tmpList) {
    this.tmpList = tmpList;
  }

  /**
   * validate.
   */
  protected abstract void validate() throws Exception;

  /**
   * get tmp list.
   *
   * @return tmp list
   * @throws Exception
   */
  public List<T> getTmpList() throws Exception {
    validate();
    return tmpList;
  }
}
