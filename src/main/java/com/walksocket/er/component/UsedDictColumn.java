package com.walksocket.er.component;

import com.walksocket.er.Const;
import com.walksocket.er.Size.DialogUsed;
import com.walksocket.er.component.edit.dict.columns.root.Form;
import com.walksocket.er.component.used.dict.columns.Root;
import com.walksocket.er.custom.ErDialog;
import com.walksocket.er.sqlite.entity.DbDictColumn;
import java.awt.Dimension;
import java.awt.Point;
import javax.swing.WindowConstants;

/**
 * UsedDictColumn.
 */
public class UsedDictColumn extends ErDialog {

  /**
   * Constructor.
   *
   * @param form         form
   * @param dbDictColumn dbDictColumn
   */
  public UsedDictColumn(Form form, DbDictColumn dbDictColumn) {
    super();

    // pos
    var b = form.getRoot().getEditDictColumns().getGraphicsConfiguration().getBounds();
    var x = (b.getWidth() - DialogUsed.WIDTH) / 2 + b.getX();
    var y = (b.getHeight() - DialogUsed.HEIGHT) / 2 + b.getY();

    // init
    setTitle(String.format("%s - dict columns", Const.TITLE));
    setLocation(new Point((int) x, (int) y));
    setMinimumSize(new Dimension(DialogUsed.WIDTH, DialogUsed.HEIGHT));
    setResizable(false);
    setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

    // load
    var container = getContentPane();
    container.removeAll();
    container.add(new Root(dbDictColumn));
    container.revalidate();
    container.repaint();
  }
}
