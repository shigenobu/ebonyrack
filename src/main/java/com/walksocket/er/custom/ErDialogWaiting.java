package com.walksocket.er.custom;

import com.walksocket.er.Size.DialogProcessing;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * ErDialogWaiting.
 */
public class ErDialogWaiting extends JDialog {

  /**
   * Constructor.
   *
   * @param owner owner
   * @param title title
   */
  public ErDialogWaiting(JFrame owner, String title) {
    super(owner, title);

    // pos
    var b = owner.getGraphicsConfiguration().getBounds();
    var px = (b.getWidth() - DialogProcessing.WIDTH) / 2 + b.getX();
    var py = (b.getHeight() - DialogProcessing.HEIGHT) / 2 + b.getY();

    // init
    setResizable(false);
    setLayout(new GridBagLayout());
    setLocation((int) px, (int) py);
    setSize(new Dimension(DialogProcessing.WIDTH, DialogProcessing.HEIGHT));
    var labelProcessing = new JLabel("Please wait ...");
    labelProcessing.setFont(new Font(labelProcessing.getFont().getName(), Font.BOLD, 24));
    add(labelProcessing);
  }
}
