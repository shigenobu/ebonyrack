package com.walksocket.er.component;

import com.walksocket.er.Const;
import com.walksocket.er.Size.DialogLarge;
import com.walksocket.er.component.edit.tables.Root;
import com.walksocket.er.custom.ErDialog;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.WindowConstants;

/**
 * EditTables.
 */
public class EditTables extends ErDialog {

  /**
   * changed.
   */
  private boolean changed;

  /**
   * main.
   */
  private final Main main;

  /**
   * Constructor.
   *
   * @param main main
   */
  public EditTables(Main main) {
    super();
    this.main = main;

    // pos
    var b = main.getGraphicsConfiguration().getBounds();
    var x = (b.getWidth() - DialogLarge.WIDTH) / 2 + b.getX();
    var y = (b.getHeight() - DialogLarge.HEIGHT) / 2 + b.getY();

    // init
    setTitle(String.format("%s - tables", Const.TITLE));
    setLocation(new Point((int) x, (int) y));
    setMinimumSize(new Dimension(DialogLarge.WIDTH, DialogLarge.HEIGHT));
    setResizable(false);
    setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    addWindowListener(new WindowAdapter() {
      @Override
      public void windowClosed(WindowEvent e) {
        dispose();
        if (changed) {
          main.load();
        }
      }
    });

    // load
    var container = getContentPane();
    container.removeAll();
    container.add(new Root(this));
    container.revalidate();
    container.repaint();
  }

  /**
   * get main.
   *
   * @return main
   */
  public Main getMain() {
    return main;
  }

  /**
   * change state.
   */
  public void changeState() {
    this.changed = true;
  }
}
