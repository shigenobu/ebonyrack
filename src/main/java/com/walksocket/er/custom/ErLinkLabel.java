package com.walksocket.er.custom;

import com.walksocket.er.Log;
import java.awt.Desktop;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;
import javax.swing.JLabel;

/**
 * ErLinkLabel.
 */
public class ErLinkLabel extends JLabel {

  /**
   * Constructor.
   *
   * @param str str
   * @param uri uri
   */
  public ErLinkLabel(String str, URI uri) {
    super(str);
    addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        if (Desktop.isDesktopSupported()) {
          try {
            Desktop.getDesktop().browse(uri);
          } catch (IOException ex) {
            Log.error(ex);
          }
        }
      }
    });
  }
}
