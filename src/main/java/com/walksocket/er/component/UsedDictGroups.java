package com.walksocket.er.component;

import com.walksocket.er.Const;
import com.walksocket.er.Size.DialogUsed;
import com.walksocket.er.component.edit.dict.groups.root.Form;
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
   * @param form        form
   * @param dbDictGroup dbDictGroup
   */
  public UsedDictGroups(Form form, DbDictGroup dbDictGroup) {
    super();

    // pos
    var b = form.getRoot().getEditDictGroups().getGraphicsConfiguration().getBounds();
    var x = (b.getWidth() - DialogUsed.WIDTH) / 2 + b.getX();
    var y = (b.getHeight() - DialogUsed.HEIGHT) / 2 + b.getY();

    // init
    setTitle(String.format("%s - dict groups", Const.TITLE));
    setLocation(new Point((int) x, (int) y));
    setMinimumSize(new Dimension(DialogUsed.WIDTH, DialogUsed.HEIGHT));
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
