package com.walksocket.er.component;

import com.walksocket.er.Const;
import com.walksocket.er.Size.DialogMedium;
import com.walksocket.er.Size.Screen;
import com.walksocket.er.component.input.columnname.Root;
import com.walksocket.er.component.input.table.root.form.column.Columns;
import com.walksocket.er.custom.ErDialog;
import com.walksocket.er.sqlite.tmp.TmpColumn;
import java.awt.Dimension;
import java.awt.Point;
import javax.swing.WindowConstants;

/**
 * InputColumnName.
 */
public class InputColumnName extends ErDialog {

  /**
   * columns.
   */
  private final Columns columns;

  /**
   * Constructor.
   *
   * @param columns          columns
   * @param row              row
   * @param tmpColumnForDict tmpColumnForDict
   */
  public InputColumnName(Columns columns, int row, TmpColumn tmpColumnForDict) {
    super();
    this.columns = columns;

    // pos
    var x = (Screen.getWidth() - DialogMedium.WIDTH) / 2;
    var y = (Screen.getHeight() - DialogMedium.HEIGHT) / 2;

    // init
    setTitle(String.format("%s - column name", Const.TITLE));
    setLocation(new Point(x, y));
    setMinimumSize(new Dimension(DialogMedium.WIDTH, DialogMedium.HEIGHT));
    setResizable(false);
    setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

    // load
    var container = getContentPane();
    container.removeAll();
    container.add(new Root(this, row, tmpColumnForDict));
    container.revalidate();
    container.repaint();
  }

  /**
   * get columns.
   *
   * @return columns
   */
  public Columns getColumns() {
    return columns;
  }
}
