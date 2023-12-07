package com.walksocket.er.component.startup.root;

import com.walksocket.er.App;
import com.walksocket.er.Const;
import com.walksocket.er.Log;
import com.walksocket.er.component.startup.Root;
import com.walksocket.er.custom.ErLinkLabel;
import java.awt.Color;
import java.awt.FlowLayout;
import java.net.URI;
import javax.swing.BoxLayout;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * Copyright.
 */
public class Copyright extends JPanel {

  /**
   * Constructor.
   *
   * @param root root
   */
  public Copyright(Root root) {
    // layout
    setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

    try {
      var panel1 = new JPanel(new FlowLayout(FlowLayout.CENTER));
      panel1.setBackground(Color.GRAY);
      add(panel1);

      var labelCopyRight = new ErLinkLabel(
          String.format("<html><font color='white'>version %s @%s project.</font></html>",
              App.getVersion(), Const.TITLE),
          new URI("https://github.com/shigenobu/ebonyrack")
      );
      panel1.add(labelCopyRight);

      var panel2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
      panel2.setBackground(Color.GRAY);
      add(panel2);

      var labelFont = new ErLinkLabel(
          "<html><font color='white'><small>Font <u>Mplus1-Regular</u></small>.</font></html>",
          new URI("https://mplusfonts.github.io/")
      );
      panel2.add(labelFont);

      var labelLookAndFeel = new ErLinkLabel(
          "<html><font color='white'><small>Look and Feel <u>FlatLaf</u></small>.</font></html>",
          new URI("https://www.formdev.com/flatlaf/")
      );
      panel2.add(labelLookAndFeel);
    } catch (Exception e) {
      Log.error(e);
      JOptionPane.showMessageDialog(this, e.getMessage());
    }
  }
}
