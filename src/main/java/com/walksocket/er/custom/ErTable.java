package com.walksocket.er.custom;

import javax.swing.JTable;
import javax.swing.table.TableModel;

/**
 * ErTable.
 */
public class ErTable extends JTable {

  /**
   * Constructor.
   *
   * @param tableModel tableModel
   */
  public ErTable(TableModel tableModel) {
    super(tableModel);

    putClientProperty("terminateEditOnFocusLost", true);
    getTableHeader().setReorderingAllowed(false);
    setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
  }
}
