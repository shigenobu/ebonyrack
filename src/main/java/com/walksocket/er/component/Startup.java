package com.walksocket.er.component;

import com.walksocket.er.Const;
import com.walksocket.er.Log;
import com.walksocket.er.Size.Screen;
import com.walksocket.er.Size.WindowStartup;
import com.walksocket.er.component.startup.Root;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

/**
 * Startup.
 */
public class Startup extends JFrame {

  /**
   * Constructor.
   */
  public Startup() {
    // pos
    var x = (Screen.getWidth() - WindowStartup.WIDTH) / 2;
    var y = (Screen.getHeight() - WindowStartup.HEIGHT) / 2;

    // init
    setTitle(String.format("%s - startup", Const.TITLE));
    setLocation(new Point(x, y));
    setMinimumSize(new Dimension(WindowStartup.WIDTH, WindowStartup.HEIGHT));
    setResizable(false);
    setVisible(true);
    setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    addWindowListener(new WindowAdapter() {
      @Override
      public void windowClosed(WindowEvent e) {
        Log.trace(e);
      }
    });

    // load
    load();
  }

  /**
   * load.
   */
  public void load() {
    var container = getContentPane();
    container.removeAll();
    container.add(new Root(this));
    container.revalidate();
    container.repaint();
  }
}
