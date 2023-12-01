package com.walksocket.er.component;

import com.walksocket.er.Const;
import com.walksocket.er.Size.DialogSmall;
import com.walksocket.er.Size.Screen;
import com.walksocket.er.component.used.dict.groups.Root;
import com.walksocket.er.custom.ErDialog;
import com.walksocket.er.sqlite.entity.DbDictGroup;
import java.awt.Dimension;
import java.awt.Point;
import javax.swing.WindowConstants;

/**
 * UsedDictGroups.
 */
public class UsedDictGroups extends ErDialog {

  /**
   * Constructor.
   *
   * @param dbDictGroup dbDictGroup
   */
  public UsedDictGroups(DbDictGroup dbDictGroup) {
    super();

    // pos
    var x = (Screen.getWidth() - DialogSmall.WIDTH) / 2;
    var y = (Screen.getHeight() - DialogSmall.HEIGHT) / 2;

    // init
    setTitle(String.format("%s - dict groups", Const.TITLE));
    setLocation(new Point(x, y));
    setMinimumSize(new Dimension(DialogSmall.WIDTH, DialogSmall.HEIGHT));
    setResizable(false);
    setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

    // load
    var container = getContentPane();
    container.removeAll();
    container.add(new Root(dbDictGroup));
    container.revalidate();
    container.repaint();
  }
}
