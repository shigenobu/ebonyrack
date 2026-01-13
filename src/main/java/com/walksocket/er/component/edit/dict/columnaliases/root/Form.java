package com.walksocket.er.component.edit.dict.columnaliases.root;

import com.walksocket.er.Log;
import com.walksocket.er.Size.DialogLarge;
import com.walksocket.er.Utils;
import com.walksocket.er.component.edit.dict.columnaliases.Root;
import com.walksocket.er.custom.ErHeaderFormatter;
import com.walksocket.er.custom.ErHeaderFormatter.Type;
import com.walksocket.er.sqlite.Bucket;
import com.walksocket.er.sqlite.entity.DbDictColumn;
import com.walksocket.er.sqlite.entity.DbDictColumnAlias;
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
import javax.swing.JButton;
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
    columnNameWidthMaps.put(ErHeaderFormatter.format("Dict column id", Type.showOnly), 200);
    columnNameWidthMaps.put(ErHeaderFormatter.format("Column name", Type.showOnly), 200);
    columnNameWidthMaps.put(ErHeaderFormatter.format("Column comment", Type.showOnly), 200);
    columnNameWidthMaps.put(ErHeaderFormatter.format("Column type", Type.showOnly), 200);
    columnNameWidthMaps.put(ErHeaderFormatter.format("Not null", Type.showOnly), 200);

    columnNameWidthMaps.put(ErHeaderFormatter.format("Explanation", Type.ordinal), 400);
    columnNameWidthMaps.put(ErHeaderFormatter.format("Alias1", Type.ordinal), 300);
    columnNameWidthMaps.put(ErHeaderFormatter.format("Alias2", Type.ordinal), 300);
    columnNameWidthMaps.put(ErHeaderFormatter.format("Alias3", Type.ordinal), 300);
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
    var form = this;

    // layout
    setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));

    // table
    var panelTable = new JPanel();
    add(panelTable);

    var columnNames = columnNameWidthMaps.keySet().toArray();
    tableModel = new DefaultTableModel(columnNames, 0) {
      @Override
      public boolean isCellEditable(int row, int column) {
        // dict column id - not null, not editable
        return column > 4;
      }
    };

    var widthList = columnNameWidthMaps.values().toArray(new Integer[columnNameWidthMaps.size()]);
    table = new JTable(tableModel);
    table.putClientProperty("terminateEditOnFocusLost", true);
    table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
    for (int i = 0; i < table.getColumnModel().getColumnCount(); i++) {
      var tc = table.getColumnModel().getColumn(i);
      tc.setPreferredWidth(widthList[i]);

      // dict column id
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
                for (int j = 5; j < columnCount; j++) {
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
    sp.setPreferredSize(new Dimension(DialogLarge.WIDTH - 40 + 30, DialogLarge.HEIGHT / 10 * 9));
    panelTable.add(sp);

    // button
    var panelButton = new JPanel(new FlowLayout(FlowLayout.CENTER));
    panelButton.setPreferredSize(new Dimension(DialogLarge.WIDTH - 20, DialogLarge.HEIGHT / 10));
    panelButton.setAlignmentX(Component.LEFT_ALIGNMENT);
    add(panelButton);
    buttonSave.addActionListener(actionEvent -> {
      save();
    });
    panelButton.add(buttonSave);
    buttonSaveAndClose.addActionListener(actionEvent -> {
      if (save()) {
        root.getEditColumnAliases().dispose();
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

    var dbDictColumnTypeList = Bucket.getInstance().getBucketDict().dbDictColumnTypeList;
    var dbDictColumnList = Bucket.getInstance().getBucketDict().dbDictColumnList;
    var dbDictColumnAliasList = Bucket.getInstance().getBucketDict().dbDictColumnAliasList;

    var i = 0;
    for (var dbDictColumn : dbDictColumnList.stream()
        .sorted(Comparator.comparing(DbDictColumn::getColumnNameForSort)
            .thenComparing(DbDictColumn::getColumnCommentForSort))
        .collect(Collectors.toList())) {
      tableModel.setRowCount(i + 1);

      tableModel.setValueAt(dbDictColumn.dictColumnId, i, 0);
      tableModel.setValueAt(dbDictColumn.columnName, i, 1);
      tableModel.setValueAt(dbDictColumn.columnComment, i, 2);
      var dbDictColumnType = dbDictColumnTypeList.stream()
          .filter(d -> d.dictColumnTypeId.equals(dbDictColumn.dictColumnTypeId))
          .findFirst()
          .get();
      tableModel.setValueAt(dbDictColumnType.columnType, i, 3);
      tableModel.setValueAt(dbDictColumn.notNullValue, i, 4);

      var opt = dbDictColumnAliasList.stream()
          .filter(d -> d.dictColumnId.equals(dbDictColumn.dictColumnId))
          .findFirst();
      if (opt.isPresent()) {
        tableModel.setValueAt(opt.get().explanation, i, 5);
        tableModel.setValueAt(opt.get().alias1, i, 6);
        tableModel.setValueAt(opt.get().alias2, i, 7);
        tableModel.setValueAt(opt.get().alias3, i, 8);
      }

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
      var newDbDictColumnAliasList = new ArrayList<DbDictColumnAlias>();
      for (int i = 0; i < table.getRowCount(); i++) {
        // DbDictColumnAlias
        var newDbDictColumnAlias = new DbDictColumnAlias();
        newDbDictColumnAlias.dictColumnId = Utils.getString(table.getValueAt(i, 0));
        newDbDictColumnAlias.explanation = Utils.getString(table.getValueAt(i, 5));
        newDbDictColumnAlias.alias1 = Utils.getString(table.getValueAt(i, 6));
        newDbDictColumnAlias.alias2 = Utils.getString(table.getValueAt(i, 7));
        newDbDictColumnAlias.alias3 = Utils.getString(table.getValueAt(i, 8));
        if (!newDbDictColumnAlias.isValid()) {
          continue;
        }

        newDbDictColumnAliasList.add(newDbDictColumnAlias);
      }

      // save
      Bucket.getInstance().getBucketDict().saveDictColumnAlias(newDbDictColumnAliasList);
      root.getEditColumnAliases().changeState();

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
