package com.walksocket.er.component;

import com.walksocket.er.Const;
import com.walksocket.er.Size.DialogMedium;
import com.walksocket.er.Size.Screen;
import com.walksocket.er.component.show.tableclass.Root;
import com.walksocket.er.custom.ErDialog;
import java.awt.Dimension;
import java.awt.Point;
import javax.swing.WindowConstants;

/**
 * ShowTableClass.
 */
public class ShowTableClass extends ErDialog {

  /**
   * Constructor.
   */
  public ShowTableClass() {
    super();

    // pos
    var x = (Screen.getWidth() - DialogMedium.WIDTH) / 2;
    var y = (Screen.getHeight() - DialogMedium.HEIGHT) / 2;

    // init
    setTitle(String.format("%s - table class", Const.TITLE));
    setLocation(new Point(x, y));
    setMinimumSize(new Dimension(DialogMedium.WIDTH, DialogMedium.HEIGHT));
    setResizable(false);
    setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

    // load
    var container = getContentPane();
    container.removeAll();
    container.add(new Root());
    container.revalidate();
    container.repaint();
  }
}
