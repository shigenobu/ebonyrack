package com.walksocket.er.custom;

import com.walksocket.er.Log;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

/**
 * ErConnectorPositioned.
 */
public abstract class ErConnectorPositioned extends ErMoverArea {

  /**
   * Constructor.
   */
  public ErConnectorPositioned() {
    super();

    // focus
    var positioned = this;
    setFocusable(true);
    addFocusListener(new FocusAdapter() {
      @Override
      public void focusGained(FocusEvent e) {
        Log.trace(String.format("focus gained: %s", positioned));
      }

      @Override
      public void focusLost(FocusEvent e) {
      }
    });
  }
}
