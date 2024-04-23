package com.walksocket.er.custom;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.KeyStroke;

/**
 * ErDialog.
 */
public abstract class ErDialog extends JDialog {

  /**
   * Constructor.
   */
  public ErDialog() {
    setAlwaysOnTop(true);

    // escape
    var escape = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0, false);
    getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(escape, "ESCAPE");
    getRootPane().getActionMap().put("ESCAPE", new AbstractAction() {
      @Override
      public void actionPerformed(ActionEvent actionEvent) {
        dispose();
      }
    });
  }
}
