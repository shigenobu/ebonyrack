package com.walksocket.er.component.input.table.root.form.other;

import com.walksocket.er.Size.DialogLarge;
import com.walksocket.er.Utils;
import com.walksocket.er.component.input.table.root.form.Other;
import com.walksocket.er.custom.ErTableButtons;
import com.walksocket.er.sqlite.Tmp;
import com.walksocket.er.sqlite.TmpResult;
import com.walksocket.er.sqlite.context.CtxTable;
import com.walksocket.er.sqlite.tmp.TmpCheck;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 * Check.
 */
public class Check extends JPanel {

  /**
   * column name and width maps.
   */
  private static final Map<String, Integer> columnNameWidthMaps = new LinkedHashMap<>();

  static {
    // b -> required
    // i -> open dialog
    // u -> with dict
    // s -> show only
    columnNameWidthMaps.put("<html><b>Constraint name</b></html>", 200);
    columnNameWidthMaps.put("<html><b>Expression</b></html>", 600);
  }

  /**
   * other.
   */
  private final Other other;

  /**
   * ctx table.
   */
  private final CtxTable ctxTable;

  /**
   * table.
   */
  private final JTable table;

  /**
   * table model.
   */
  private final DefaultTableModel tableModel;

  /**
   * Constructor.
   *
   * @param other    other
   * @param ctxTable ctxTable
   */
  public Check(Other other, CtxTable ctxTable) {
    this.other = other;
    this.ctxTable = ctxTable;

    // layout
    setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
    var key = this;

    // panel - table
    var panel1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
    add(panel1);

    var columnNames = columnNameWidthMaps.keySet().toArray();
    tableModel = new DefaultTableModel(columnNames, 3) {
      @Override
      public boolean isCellEditable(int row, int column) {
        // all editable
        return true;
      }
    };

    var widthList = columnNameWidthMaps.values().toArray(new Integer[columnNameWidthMaps.size()]);
    table = new JTable(tableModel);
    table.putClientProperty("terminateEditOnFocusLost", true);
    table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
    for (int i = 0; i < table.getColumnModel().getColumnCount(); i++) {
      var tc = table.getColumnModel().getColumn(i);
      tc.setPreferredWidth(widthList[i]);
    }
    var sp = new JScrollPane(table);
    sp.setPreferredSize(new Dimension(DialogLarge.WIDTH - 40, DialogLarge.HEIGHT / 40 * 6));
    panel1.add(sp);

    // panel - buttons
    var panel2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
    add(panel2);
    panel2.add(new ErTableButtons(table));

    // tmp check
    for (int i = 0; i < ctxTable.dbTableCheckList.size(); i++) {
      var dbTableCheck = ctxTable.dbTableCheckList.get(i);
      var tmpCheck = Tmp.createTmpCheck(dbTableCheck);
      tableModel.setRowCount(i + 1);

      table.setValueAt(tmpCheck.constraintName, i, 0);
      table.setValueAt(tmpCheck.expression, i, 1);
    }
  }

  /**
   * get tmp check.
   *
   * @param row row
   * @return tmp check
   */
  public TmpCheck getTmpCheck(int row) {
    var tmpCheck = new TmpCheck();
    tmpCheck.constraintName = Utils.getString(table.getValueAt(row, 0));
    tmpCheck.expression = Utils.getString(table.getValueAt(row, 1));
    return tmpCheck;
  }

  /**
   * get result.
   *
   * @return result.
   */
  public TmpResult<TmpCheck> getResult() {
    var tmpCheckList = new ArrayList<TmpCheck>();
    for (int i = 0; i < tableModel.getRowCount(); i++) {
      var tmpCheck = getTmpCheck(i);
      if (tmpCheck.isAllEmpty()) {
        continue;
      }
      tmpCheckList.add(tmpCheck);
    }

    return new TmpResult<TmpCheck>(tmpCheckList) {
      @Override
      protected void validate() throws Exception {
        for (var tmp : tmpList) {
          if (Utils.isNullOrEmpty(tmp.constraintName)) {
            throw new Exception("Required 'Constraint name'.");
          }
          if (Utils.isNullOrEmpty(tmp.expression)) {
            throw new Exception("Required 'Expression'.");
          }
        }
      }
    };
  }
}
