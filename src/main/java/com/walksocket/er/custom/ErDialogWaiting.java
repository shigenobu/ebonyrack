package com.walksocket.er.custom;

import com.walksocket.er.Size.DialogProcessing;
import com.walksocket.er.Size.Screen;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridBagLayout;
import javax.swing.JDialog;
import javax.swing.JLabel;

/**
 * ErDialogWaiting.
 */
public class ErDialogWaiting extends JDialog {

  /**
   * show.
   *
   * @param dialog dialog
   */
  public static void show(ErDialogWaiting dialog) {
    dialog.setAlwaysOnTop(true);
    dialog.setModal(true);
    dialog.setVisible(true);
  }

  /**
   * Constructor.
   *
   * @param owner owner
   * @param title title
   */
  public ErDialogWaiting(Frame owner, String title) {
    super(owner, title);

    // pos
    var px = (Screen.getWidth() - DialogProcessing.WIDTH) / 2;
    var py = (Screen.getHeight() - DialogProcessing.HEIGHT) / 2;

    // init
    setResizable(false);
    setLayout(new GridBagLayout());
    setLocation(px, py);
    setSize(new Dimension(DialogProcessing.WIDTH, DialogProcessing.HEIGHT));
    var labelProcessing = new JLabel("Please wait ...");
    labelProcessing.setFont(new Font(labelProcessing.getFont().getName(), Font.BOLD, 24));
    add(labelProcessing);
  }
}
