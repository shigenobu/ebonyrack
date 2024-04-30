package com.walksocket.er.component;

import com.walksocket.er.Const;
import com.walksocket.er.Size.DialogUsed;
import com.walksocket.er.component.edit.dict.columntypes.root.Form;
import com.walksocket.er.component.used.dict.columntypes.Root;
import com.walksocket.er.custom.ErDialog;
import com.walksocket.er.sqlite.entity.DbDictColumnType;
import java.awt.Dimension;
import java.awt.Point;
import javax.swing.WindowConstants;

/**
 * UsedDictColumnTypes.
 */
public class UsedDictColumnTypes extends ErDialog {

  /**
   * Constructor.
   *
   * @param form             form
   * @param dbDictColumnType dbDictColumnType
   */
  public UsedDictColumnTypes(Form form, DbDictColumnType dbDictColumnType) {
    super();

    // pos
    var b = form.getRoot().getEditDictColumnTypes().getGraphicsConfiguration().getBounds();
    var x = (b.getWidth() - DialogUsed.WIDTH) / 2 + b.getX();
    var y = (b.getHeight() - DialogUsed.HEIGHT) / 2 + b.getY();

    // init
    setTitle(String.format("%s - dict column types", Const.TITLE));
    setLocation(new Point((int) x, (int) y));
    setMinimumSize(new Dimension(DialogUsed.WIDTH, DialogUsed.HEIGHT));
    setResizable(false);
    setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

    // load
    var container = getContentPane();
    container.removeAll();
    container.add(new Root(dbDictColumnType));
    container.revalidate();
    container.repaint();
  }
}
