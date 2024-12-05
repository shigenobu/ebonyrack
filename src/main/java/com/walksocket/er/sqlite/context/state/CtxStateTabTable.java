package com.walksocket.er.sqlite.context.state;

/**
 * CtxStateTabTable.
 */
public class CtxStateTabTable {

  /**
   * lastSelectedBaseTabIndex.
   */
  private int lastSelectedBaseTabIndex;

  /**
   * set last selected base tab index.
   *
   * @param selectedBaseTabIndex selectedBaseTabIndex
   */
  public void setLastSelectedBaseTabIndex(int selectedBaseTabIndex) {
    this.lastSelectedBaseTabIndex = selectedBaseTabIndex;
  }

  /**
   * get last selected base tab index.
   *
   * @return last selected base tab index
   */
  public int getLastSelectedBaseTabIndex() {
    return lastSelectedBaseTabIndex;
  }

  /**
   * reset last selected base tab index.
   */
  public void resetLastSelectedBaseTabIndex() {
    this.lastSelectedBaseTabIndex = 0;
  }

  /**
   * lastSelectedColumnTabIndex.
   */
  private int lastSelectedColumnTabIndex;

  /**
   * set last selected column tab index.
   *
   * @param selectedColumnTabIndex selectedColumnTabIndex
   */
  public void setLastSelectedColumnTabIndex(int selectedColumnTabIndex) {
    this.lastSelectedColumnTabIndex = selectedColumnTabIndex;
  }

  /**
   * get last selected column tab index.
   *
   * @return last selected column tab index
   */
  public int getLastSelectedColumnTabIndex() {
    return lastSelectedColumnTabIndex;
  }

  /**
   * reset last selected column tab index.
   */
  public void resetLastSelectedColumnTabIndex() {
    this.lastSelectedColumnTabIndex = 0;
  }

  /**
   * lastSelectedOtherTabIndex.
   */
  private int lastSelectedOtherTabIndex;

  /**
   * set last selected other tab index.
   *
   * @param selectedOtherTabIndex selectedOtherTabIndex
   */
  public void setLastSelectedOtherTabIndex(int selectedOtherTabIndex) {
    this.lastSelectedOtherTabIndex = selectedOtherTabIndex;
  }

  /**
   * get last selected other tab index.
   *
   * @return last selected other tab index
   */
  public int getLastSelectedOtherTabIndex() {
    return lastSelectedOtherTabIndex;
  }

  /**
   * reset last selected other tab index.
   */
  public void resetLastSelectedOtherTabIndex() {
    this.lastSelectedOtherTabIndex = 0;
  }
}
