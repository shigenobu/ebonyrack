package com.walksocket.er.component.edit.sequences.root;

import com.walksocket.er.Log;
import com.walksocket.er.Size.DialogMedium;
import com.walksocket.er.Utils;
import com.walksocket.er.Word;
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
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
   * button save.
   */
  private final JButton buttonSave = new JButton("Save");

  /**
   * button save and close.
   */
  private final JButton buttonSaveAndClose = new JButton("Save and close");

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
        var comboBoxCycle = new JComboBox(
            new DefaultComboBoxModel(Cycle.getCycleListForColumn().toArray()));
        tc.setCellEditor(new DefaultCellEditor(comboBoxCycle));
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
                  .toList();
              for (int i = 0; i < rows.size(); i++) {
                var cols = Arrays.stream(rows.get(i).split("\t"))
                    .toList();
                for (int j = 1; j < columnCount; j++) {
                  if (i + selectedRow < rowCount) {
                    var value = "";
                    if (cols.size() > j) {
                      value = cols.get(j);
                    }
                    table.setValueAt(value, i + selectedRow, j);
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

    // button
    var panelButton = new JPanel(new FlowLayout(FlowLayout.CENTER));
    panelButton.setPreferredSize(new Dimension(DialogMedium.WIDTH - 20, DialogMedium.HEIGHT / 10));
    panelButton.setAlignmentX(Component.LEFT_ALIGNMENT);
    add(panelButton);
    buttonSave.addActionListener(actionEvent -> {
      save();
    });
    panelButton.add(buttonSave);
    buttonSaveAndClose.addActionListener(actionEvent -> {
      if (save()) {
        root.getEditSequences().dispose();
      }
    });
    panelButton.add(buttonSaveAndClose);

    // load
    loadTable();
  }

  /**
   * load table.
   */
  private void loadTable() {
    tableModel.setRowCount(0);

    var ctxSequenceList = Bucket.getInstance().getBucketSequence().ctxSequenceList;

    var i = 0;
    for (var dbSequence : ctxSequenceList.stream()
        .map(s -> s.dbSequence)
        .sorted(Comparator.comparing(DbSequence::getSequenceNameForSort))
        .collect(Collectors.toList())) {
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

  /**
   * save.
   *
   * @return if true, ok
   */
  private boolean save() {
    try {
      var newDbSequenceList = new ArrayList<DbSequence>();
      for (int i = 0; i < table.getRowCount(); i++) {
        var newDbSequence = new DbSequence();
        newDbSequence.sequenceId = Utils.getString(table.getValueAt(i, 0));
        newDbSequence.sequenceName = Utils.getString(table.getValueAt(i, 1));
        newDbSequence.startValue = Utils.getString(table.getValueAt(i, 2));
        newDbSequence.minimumValue = Utils.getString(table.getValueAt(i, 3));
        newDbSequence.maximumValue = Utils.getString(table.getValueAt(i, 4));
        newDbSequence.incrementValue = Utils.getString(table.getValueAt(i, 5));
        newDbSequence.cacheSize = Utils.getString(table.getValueAt(i, 6));
        newDbSequence.cycle = Utils.getString(table.getValueAt(i, 7));

        if (Utils.isNullOrEmpty(newDbSequence.sequenceName)) {
          throw new Exception("Required 'Sequence name'.");
        }
        if (!Word.isValid(newDbSequence.sequenceName)) {
          throw new Exception("Invalid 'Sequence name'.");
        }
        if (!Utils.isNullOrEmpty(newDbSequence.startValue) && !Utils.isNumber(
            newDbSequence.startValue)) {
          throw new Exception("Must be number 'Start value'.");
        }
        if (!Utils.isNullOrEmpty(newDbSequence.minimumValue) && !Utils.isNumber(
            newDbSequence.minimumValue)) {
          throw new Exception("Must be number 'Minimum value'.");
        }
        if (!Utils.isNullOrEmpty(newDbSequence.maximumValue) && !Utils.isNumber(
            newDbSequence.maximumValue)) {
          throw new Exception("Must be number 'Maximum value'.");
        }
        if (!Utils.isNullOrEmpty(newDbSequence.incrementValue) && !Utils.isNumber(
            newDbSequence.incrementValue)) {
          throw new Exception("Must be number 'Increment value'.");
        }
        if (!Utils.isNullOrEmpty(newDbSequence.cacheSize) && !Utils.isNumber(
            newDbSequence.cacheSize)) {
          throw new Exception("Must be number 'Cache size'.");
        }

        if (Utils.isNullOrEmpty(newDbSequence.cycle)) {
          newDbSequence.cycle = Cycle.NOCYCLE_VALUE;
        }

        newDbSequenceList.add(newDbSequence);
      }

      // save
      Bucket.getInstance().getBucketSequence().saveBulk(newDbSequenceList);
      root.getEditSequences().changeState();

      // load
      loadTable();

      return true;

    } catch (Exception e) {
      Log.error(e);
      JOptionPane.showMessageDialog(this, e.getMessage());
    }

    return false;
  }
}
