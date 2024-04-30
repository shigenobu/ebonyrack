package com.walksocket.er.component;

import com.walksocket.er.Const;
import com.walksocket.er.Size.DialogLarge;
import com.walksocket.er.component.input.table.Root;
import com.walksocket.er.component.main.root.workspace.Table;
import com.walksocket.er.custom.ErDialog;
import java.awt.Dimension;
import java.awt.Point;
import javax.swing.WindowConstants;

/**
 * InputTable.
 */
public class InputTable extends ErDialog {

  /**
   * table.
   */
  private final Table table;

  /**
   * Constructor.
   *
   * @param table table
   */
  public InputTable(Table table) {
    super();
    this.table = table;

    // pos
    var b = table.getWorkspace().getRoot().getMain().getGraphicsConfiguration().getBounds();
    var x = (b.getWidth() - DialogLarge.WIDTH) / 2 + b.getX();
    var y = (b.getHeight() - DialogLarge.HEIGHT) / 2 + b.getY();

    // init
    setTitle(String.format("%s - table", Const.TITLE));
    setLocation(new Point((int) x, (int) y));
    setMinimumSize(new Dimension(DialogLarge.WIDTH, DialogLarge.HEIGHT));
    setResizable(false);
    setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

    // load
    load();
  }

  /**
   * load.
   */
  public void load() {
    var container = getContentPane();
    container.removeAll();
    container.add(new Root(this, table.getCtxTable()));
    container.revalidate();
    container.repaint();
  }

  /**
   * get table.
   *
   * @return table
   */
  public Table getTable() {
    return table;
  }
}
