package com.walksocket.er.component.input.table.root.form;

import com.walksocket.er.Size.DialogLarge;
import com.walksocket.er.component.input.table.root.form.base.Table;
import com.walksocket.er.sqlite.context.CtxTable;
import java.awt.Dimension;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

/**
 * Base.
 */
public class Base extends JPanel {

  /**
   * table.
   */
  private final Table table;

  /**
   * Constructor.
   *
   * @param ctxTable ctxTable
   */
  public Base(CtxTable ctxTable) {
    // table
    table = new Table(ctxTable);
    table.setPreferredSize(new Dimension(DialogLarge.WIDTH - 20, DialogLarge.HEIGHT / 40 * 5));

    // tab
    var tab = new JTabbedPane();
    tab.addTab("table", table);
    add(tab);
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
