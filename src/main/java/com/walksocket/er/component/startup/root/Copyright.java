package com.walksocket.er.component.startup.root;

import com.walksocket.er.App;
import com.walksocket.er.Const;
import com.walksocket.er.Log;
import com.walksocket.er.component.startup.Root;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
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

    var panel1 = new JPanel(new FlowLayout(FlowLayout.CENTER));
    panel1.setBackground(Color.GRAY);
    add(panel1);
    var labelCopyRight = new JLabel(
        String.format("<html><font color='white'>version %s @%s project.</font></html>",
            App.class.getPackage().getImplementationVersion(), Const.TITLE));
    labelCopyRight.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        if (Desktop.isDesktopSupported()) {
          try {
            Desktop.getDesktop()
                .browse(new URI("https://github.com/shigenobu/ebonyrack"));
          } catch (IOException | URISyntaxException ex) {
            Log.error(e);
          }
        }
      }
    });
    panel1.add(labelCopyRight);

    var panel2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
    panel2.setBackground(Color.GRAY);
    add(panel2);
    var labelFont = new JLabel(
        "<html><font color='white'><small>Font <u>Mplus1-Regular</u></small>.</font></html>");
    labelFont.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        if (Desktop.isDesktopSupported()) {
          try {
            Desktop.getDesktop()
                .browse(new URI("https://mplusfonts.github.io/"));
          } catch (IOException | URISyntaxException ex) {
            Log.error(e);
          }
        }
      }
    });
    panel2.add(labelFont);

    var labelLookAndFeel = new JLabel(
        "<html><font color='white'><small>Look and Feel <u>FlatLaf</u></small>.</font></html>");
    labelLookAndFeel.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        if (Desktop.isDesktopSupported()) {
          try {
            Desktop.getDesktop().browse(new URI("https://www.formdev.com/flatlaf/"));
          } catch (IOException | URISyntaxException ex) {
            Log.error(e);
          }
        }
      }
    });
    panel2.add(labelLookAndFeel);
  }
}
