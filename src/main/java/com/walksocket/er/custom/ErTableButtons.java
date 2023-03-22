package com.walksocket.er.custom;

import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 * ErTableButtons.
 */
public class ErTableButtons extends JPanel {

  /**
   * Constructor.
   *
   * @param table
   */
  public ErTableButtons(JTable table) {
    var model = (DefaultTableModel) table.getModel();

    // layout
    setLayout(new FlowLayout(FlowLayout.LEFT, 20, 0));

    // -----
    // panel - up, down
    var panel1 = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
    add(panel1);

    // up
    var buttonUp = new JButton("Up row");
    buttonUp.addActionListener(actionEvent -> {
      var selectedRow = table.getSelectedRow();
      if (selectedRow > 0) {
        model.moveRow(selectedRow, selectedRow, selectedRow - 1);
        table.setRowSelectionInterval(selectedRow - 1, selectedRow - 1);
      }
    });
    panel1.add(buttonUp);

    // down
    var buttonDown = new JButton("Down row");
    buttonDown.addActionListener(actionEvent -> {
      var selectedRow = table.getSelectedRow();
      var len = table.getRowCount();
      if (selectedRow >= 0 && selectedRow < len - 1) {
        model.moveRow(selectedRow, selectedRow, selectedRow + 1);
        table.setRowSelectionInterval(selectedRow + 1, selectedRow + 1);
      }
    });
    panel1.add(buttonDown);

    // -----
    // panel - add, remove
    var panel2 = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
    add(panel2);

    // add
    var buttonAdd = new JButton("Add row");
    buttonAdd.addActionListener(actionEvent -> {
      var selectedRow = table.getSelectedRow();
      var newRow = new Object[table.getColumnCount()];
      var len = table.getRowCount();
      if (selectedRow >= 0) {
        model.insertRow(selectedRow + 1, newRow);
        table.setRowSelectionInterval(selectedRow + 1, selectedRow + 1);
      } else {
        model.addRow(newRow);
        table.setRowSelectionInterval(len, len);
      }
    });
    panel2.add(buttonAdd);

    // remove
    var buttonRemove = new JButton("Remove row");
    buttonRemove.addActionListener(actionEvent -> {
      var selectedRow = table.getSelectedRow();
      var len = table.getRowCount();
      if (selectedRow >= 0) {
        model.removeRow(selectedRow);
        if (selectedRow > 0) {
          table.setRowSelectionInterval(selectedRow - 1, selectedRow - 1);
        }
      } else if (len > 0) {
        model.removeRow(len - 1);
        if (len > 1) {
          table.setRowSelectionInterval(len - 2, len - 2);
        }
      }
    });
    panel2.add(buttonRemove);
  }
}
