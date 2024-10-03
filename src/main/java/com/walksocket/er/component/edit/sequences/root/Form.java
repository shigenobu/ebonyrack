package com.walksocket.er.component.edit.sequences.root;

import com.walksocket.er.Log;
import com.walksocket.er.Size.DialogMedium;
import com.walksocket.er.Utils;
import com.walksocket.er.component.edit.sequences.Root;
import com.walksocket.er.custom.ErHeaderFormatter;
import com.walksocket.er.custom.ErHeaderFormatter.Type;
import com.walksocket.er.definition.Cycle;
import com.walksocket.er.sqlite.Bucket;
import com.walksocket.er.sqlite.entity.DbSequence;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
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
    columnNameWidthMaps.put(ErHeaderFormatter.format("Sequence id", Type.showOnly), 200);
    columnNameWidthMaps.put(ErHeaderFormatter.format("Sequence name", Type.ordinal), 200);
    columnNameWidthMaps.put(ErHeaderFormatter.format("Start value", Type.ordinal), 100);
    columnNameWidthMaps.put(ErHeaderFormatter.format("Minimum value", Type.ordinal), 100);
    columnNameWidthMaps.put(ErHeaderFormatter.format("Maximum value", Type.ordinal), 100);
    columnNameWidthMaps.put(ErHeaderFormatter.format("Increment value", Type.ordinal), 100);
    columnNameWidthMaps.put(ErHeaderFormatter.format("Cache size", Type.ordinal), 100);
    columnNameWidthMaps.put(ErHeaderFormatter.format("Cycle", Type.ordinal), 100);
  }

  /**
   * root.
   */
  private final Root root;

  /**
   * table.
   */
  private final JTable table;

  /**
   * table model.
   */
  private final DefaultTableModel tableModel;

  /**
   * text field search sequence name.
   */
  private final JTextField textFieldSearchSequenceName = new JTextField(20);

  /**
   * Constructor.
   *
   * @param root root
   */
  public Form(Root root) {
    this.root = root;
    var sequences = this;

    // layout
    setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));

    // panel - input
    var panel1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
    add(panel1);

    var labelSearchSequenceName = new JLabel("(Search) Sequence name:");
    panel1.add(labelSearchSequenceName);
    textFieldSearchSequenceName.addKeyListener(new KeyAdapter() {

      @Override
      public void keyReleased(KeyEvent e) {
        var sequenceName = Utils.getString(textFieldSearchSequenceName);
        loadTable(sequenceName);
      }
    });
    panel1.add(textFieldSearchSequenceName);

    // table
    var panelTable = new JPanel();
    add(panelTable);

    var columnNames = columnNameWidthMaps.keySet().toArray();
    tableModel = new DefaultTableModel(columnNames, 0) {
      @Override
      public boolean isCellEditable(int row, int column) {
        // sequence id is not editable
        return column != 0;
      }
    };

    var widthList = columnNameWidthMaps.values().toArray(new Integer[columnNameWidthMaps.size()]);
    table = new JTable(tableModel);
    table.putClientProperty("terminateEditOnFocusLost", true);
    table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
    for (int i = 0; i < table.getColumnModel().getColumnCount(); i++) {
      var tc = table.getColumnModel().getColumn(i);
      tc.setPreferredWidth(widthList[i]);

      // sequence id
      if (i == 0) {
        tc.setCellRenderer(new DefaultTableCellRenderer() {
          @Override
          public Component getTableCellRendererComponent(JTable table, Object value,
              boolean isSelected, boolean hasFocus, int row, int column) {
            setBackground(new Color(153, 51, 255, 20));
            return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row,
                column);
          }
        });
      }

      // cycle
      if (i == 7) {
        var comboBoxNotNull = new JComboBox(
            new DefaultComboBoxModel(Cycle.getCycleListForColumn().toArray()));
        tc.setCellEditor(new DefaultCellEditor(comboBoxNotNull));
      }
    }
    table.addKeyListener(new KeyAdapter() {
      @Override
      public void keyReleased(KeyEvent e) {
        var selectedColumn = table.getSelectedColumn();
        if (selectedColumn != 0) {
          return;
        }

        if (e.getKeyCode() == KeyEvent.VK_V && e.isControlDown()) {
          var clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
          var transferable = clipboard.getContents(table);
          if (transferable != null) {
            try {
              var selectedRow = table.getSelectedRow();
              var columnCount = table.getColumnCount();
              var rowCount = table.getRowCount();
              var pasteStr = (String) transferable.getTransferData(DataFlavor.stringFlavor);
              pasteStr = pasteStr.replace("\r", "");
              var rows = Arrays.stream(pasteStr.split("\n"))
                  .filter(r -> !Utils.isNullOrEmpty(r))
                  .toList();
              for (int i = 0; i < rows.size(); i++) {
                var cols = Arrays.stream(rows.get(i).split("\t"))
                    .filter(c -> !Utils.isNullOrEmpty(c))
                    .toList();
                var colLimit = cols.size();
                if (colLimit > columnCount) {
                  colLimit = columnCount;
                }
                for (int j = 1; j < colLimit; j++) {
                  if (i + selectedRow < rowCount) {
                    table.setValueAt(cols.get(j), i + selectedRow, j);
                  }
                }
              }
            } catch (Exception ex) {
              Log.error(ex);
              JOptionPane.showMessageDialog(root, ex.getMessage());
            }
          }
        }
      }
    });

    var sp = new JScrollPane(table);
    sp.setPreferredSize(new Dimension(DialogMedium.WIDTH - 40 + 30, DialogMedium.HEIGHT / 10 * 8));
    panelTable.add(sp);

    // load
    loadTable();
  }

  /**
   * load table.
   */
  private void loadTable() {
    textFieldSearchSequenceName.setText("");
    loadTable(null);
  }

  /**
   * load table.
   *
   * @param sequenceName sequenceName
   */
  private void loadTable(String sequenceName) {
    tableModel.setRowCount(0);

    var ctxSequenceList = Bucket.getInstance().getBucketSequence().ctxSequenceList;

    var i = 0;
    for (var dbSequence : ctxSequenceList.stream()
        .map(s -> s.dbSequence)
        .sorted(Comparator.comparing(DbSequence::getSequenceNameForSort))
        .collect(Collectors.toList())) {
      if (!Utils.isNullOrEmpty(sequenceName) && !dbSequence.sequenceName.startsWith(sequenceName)) {
        continue;
      }

      tableModel.setRowCount(i + 1);

      table.setValueAt(dbSequence.sequenceId, i, 0);
      table.setValueAt(dbSequence.sequenceName, i, 1);
      table.setValueAt(dbSequence.startValue, i, 2);
      table.setValueAt(dbSequence.minimumValue, i, 3);
      table.setValueAt(dbSequence.maximumValue, i, 4);
      table.setValueAt(dbSequence.incrementValue, i, 5);
      table.setValueAt(dbSequence.cacheSize, i, 6);
      table.setValueAt(dbSequence.cycle, i, 7);

      i++;
    }
  }
}
