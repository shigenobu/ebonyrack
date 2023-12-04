package com.walksocket.er.component;

import com.walksocket.er.Const;
import com.walksocket.er.Size.DialogMedium;
import com.walksocket.er.Size.Screen;
import com.walksocket.er.component.edit.dict.partitions.Root;
import com.walksocket.er.custom.ErDialog;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.WindowConstants;

/**
 * EditDictPartitions.
 */
public class EditDictPartitions extends ErDialog {

  /**
   * main.
   */
  private final Main main;

  /**
   * Constructor.
   *
   * @param main main
   */
  public EditDictPartitions(Main main) {
    super();
    this.main = main;

    // pos
    var x = (Screen.getWidth() - DialogMedium.WIDTH) / 2;
    var y = (Screen.getHeight() - DialogMedium.HEIGHT) / 2;

    // init
    setTitle(String.format("%s - dict partitions", Const.TITLE));
    setLocation(new Point(x, y));
    setMinimumSize(new Dimension(DialogMedium.WIDTH, DialogMedium.HEIGHT));
    setResizable(false);
    setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    addWindowListener(new WindowAdapter() {
      @Override
      public void windowClosed(WindowEvent e) {
        dispose();
        main.load();
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
}
