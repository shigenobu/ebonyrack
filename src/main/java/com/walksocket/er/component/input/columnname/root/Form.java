package com.walksocket.er.component.input.columnname.root;

import com.walksocket.er.Size.DialogMedium;
import com.walksocket.er.Utils;
import com.walksocket.er.component.input.columnname.Root;
import com.walksocket.er.sqlite.Bucket;
import com.walksocket.er.sqlite.tmp.TmpColumn;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Point;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 * Form.
 */
public class Form extends JPanel {

  /**
   * column name and width maps.
   */
  private static final Map<String, Integer> columnNameWidthMaps = new LinkedHashMap<>();

  static {
    // b -> required
    // i -> open dialog
    // u -> with dict
    // s -> show only
    columnNameWidthMaps.put("<html><s>Column name</s></html>", 200);
    columnNameWidthMaps.put("<html><s>Column comment</s></html>", 200);
    columnNameWidthMaps.put("<html><s>Column type</s></html>", 200);
    columnNameWidthMaps.put("<html><s>Not null</s></html>", 200);
    columnNameWidthMaps.put("<html><s>Charset</s></html>", 100);
    columnNameWidthMaps.put("<html><s>Collate</s></html>", 100);
    columnNameWidthMaps.put("<html><s>Default</s></html>", 100);
    columnNameWidthMaps.put("<html><s>On update</s></html>", 100);
    columnNameWidthMaps.put("<html><s>Auto increment</s></html>", 100);
    columnNameWidthMaps.put("<html><s>Option</s></html>", 100);
  }

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
   * @param root             root
   * @param row              row
   * @param tmpColumnForDict tmpColumnForDict
   */
  public Form(Root root, int row, TmpColumn tmpColumnForDict) {
    // layout
    setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));

    // panel - input
    var panel1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
    add(panel1);

    var labelSearchColumnName = new JLabel("(Search) Column name:");
    panel1.add(labelSearchColumnName);
    var textFieldSearchColumnName = new JTextField(20);
    textFieldSearchColumnName.setText(tmpColumnForDict.columnName);
    textFieldSearchColumnName.addKeyListener(new KeyAdapter() {

      @Override
      public void keyReleased(KeyEvent e) {
        var columnName = Utils.getString(textFieldSearchColumnName);
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
          root.getInputColumnName().getColumns()
              .setTmpColumnForDictOnlyColumnName(row, columnName);
          root.getInputColumnName().dispose();
          return;
        }
        reloadTable(columnName);
      }
    });
    panel1.add(textFieldSearchColumnName);
    var buttonOk = new JButton("Ok");
    buttonOk.addActionListener(actionEvent -> {
      var columnName = Utils.getString(textFieldSearchColumnName);
      root.getInputColumnName().getColumns().setTmpColumnForDictOnlyColumnName(row, columnName);
      root.getInputColumnName().dispose();
    });
    panel1.add(buttonOk);

    // panel - table
    var panel2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
    add(panel2);

    var columnNames = columnNameWidthMaps.keySet().toArray();
    tableModel = new DefaultTableModel(columnNames, 0) {
      @Override
      public boolean isCellEditable(int row, int column) {
        return false;
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
    table.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        if (e.getClickCount() == 2) {
          Point pt = e.getPoint();
          int r = table.rowAtPoint(pt);
          if (r >= 0) {
            var tc = new TmpColumn();
            tc.columnName = Utils.getString(table.getValueAt(r, 0));
            tc.columnComment = Utils.getString(table.getValueAt(r, 1));
            tc.columnType = Utils.getString(table.getValueAt(r, 2));
            tc.notNullValue = Utils.getString(table.getValueAt(r, 3));
            tc.charsetValue = Utils.getString(table.getValueAt(r, 4));
            tc.collateValue = Utils.getString(table.getValueAt(r, 5));
            tc.defaultValue = Utils.getString(table.getValueAt(r, 6));
            tc.onUpdate = Utils.getString(table.getValueAt(r, 7));
            tc.autoIncrementDefinition = Utils.getString(table.getValueAt(r, 8));
            tc.option = Utils.getString(table.getValueAt(r, 9));

            root.getInputColumnName().getColumns().setTmpColumnForDict(row, tc);
            root.getInputColumnName().dispose();
          }
        }
      }
    });
    var sp = new JScrollPane(table);
    sp.setPreferredSize(new Dimension(DialogMedium.WIDTH - 40, DialogMedium.HEIGHT / 10 * 8));
    panel2.add(sp);

    // tmp column for dict
    reloadTable(tmpColumnForDict.columnName);
  }

  /**
   * reload table.
   *
   * @param columName columName
   */
  private void reloadTable(String columName) {
    tableModel.setRowCount(0);

    var dbDictColumnTypeList = Bucket.getInstance().getBucketDict().dbDictColumnTypeList;
    var dbDictColumnList = Bucket.getInstance().getBucketDict().dbDictColumnList;

    var tmpColumnForDictList = dbDictColumnList.stream()
        .sorted(Comparator.comparing(t -> t.columnName))
        .sorted(Comparator.comparing(t -> t.columnComment))
        .collect(Collectors.toList());
    if (!Utils.isNullOrEmpty(columName)) {
      tmpColumnForDictList = dbDictColumnList.stream()
          .filter(c -> c.columnName.startsWith(columName))
          .sorted(Comparator.comparing(t -> t.columnName))
          .sorted(Comparator.comparing(t -> t.columnComment))
          .collect(Collectors.toList());
    }
    for (int i = 0; i < tmpColumnForDictList.size(); i++) {
      tableModel.setRowCount(i + 1);

      var tc = tmpColumnForDictList.get(i);
      table.setValueAt(tc.columnName, i, 0);
      table.setValueAt(tc.columnComment, i, 1);
      var dbDictColumnType = dbDictColumnTypeList.stream()
          .filter(c -> c.dictColumnTypeId.equals(tc.dictColumnTypeId))
          .findFirst()
          .get();
      table.setValueAt(dbDictColumnType.columnType, i, 2);

      table.setValueAt(tc.notNullValue, i, 3);
      table.setValueAt(tc.charsetValue, i, 4);
      table.setValueAt(tc.collateValue, i, 5);
      table.setValueAt(tc.defaultValue, i, 6);
      table.setValueAt(tc.onUpdate, i, 7);
      table.setValueAt(tc.autoIncrementDefinition, i, 8);
      table.setValueAt(tc.option, i, 9);
    }
  }
}
