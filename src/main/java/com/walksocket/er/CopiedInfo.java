package com.walksocket.er;

/**
 * CopiedInfo.
 *
 * @param <T> type
 */
public class CopiedInfo<T extends Copiable<T>> {

  /**
   * copied timestamp mills.
   */
  public long copiedTimestampMills;

  /**
   * copied data.
   */
  public Copiable<T> data;

  /**
   * get copied timestamp mills.
   *
   * @return copied timestamp mills
   */
  public long getCopiedTimestampMills() {
    return copiedTimestampMills;
  }
}
