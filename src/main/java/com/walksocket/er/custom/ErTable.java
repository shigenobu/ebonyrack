package com.walksocket.er.custom;

import java.awt.event.MouseEvent;
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

    getSelectionModel().addListSelectionListener(e -> {
      var row = getSelectedRow();
      if (row < 0) {
        return;
      }
      var x = getWidth() / 2;
      var y = getRowHeight() * row;
      dispatchEvent(
          new MouseEvent(this, MouseEvent.MOUSE_CLICKED, System.currentTimeMillis(), 0, x, y, 0,
              false, 0));
    });
  }
}
