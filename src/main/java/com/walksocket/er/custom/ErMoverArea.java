package com.walksocket.er.custom;

import javax.swing.JPanel;

/**
 * ErMoverArea.
 */
public abstract class ErMoverArea extends JPanel {

  /**
   * selecting range.
   */
  private final ErSelectingRange selectingRange = new ErSelectingRange(this);

  /**
   * get selection range.
   *
   * @return selecting range
   */
  public ErSelectingRange getSelectingRange() {
    return selectingRange;
  }
}
