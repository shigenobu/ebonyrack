package com.walksocket.er.component.edit.tables.root;

import com.walksocket.er.Log;
import com.walksocket.er.Size.DialogLarge;
import com.walksocket.er.Utils;
import com.walksocket.er.Word;
import com.walksocket.er.component.edit.tables.Root;
import com.walksocket.er.custom.ErHeaderFormatter;
import com.walksocket.er.custom.ErHeaderFormatter.Type;
import com.walksocket.er.definition.Charset;
import com.walksocket.er.definition.Collate;
import com.walksocket.er.definition.Engine;
import com.walksocket.er.sqlite.Bucket;
import com.walksocket.er.sqlite.entity.DbTable;
import com.walksocket.er.sqlite.entity.DbTableGroup;
import com.walksocket.er.sqlite.entity.DbTablePartition;
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
    columnNameWidthMaps.put(ErHeaderFormatter.format("Table id", Type.showOnly), 200);
    columnNameWidthMaps.put(ErHeaderFormatter.format("Table name", Type.ordinal), 200);
    columnNameWidthMaps.put(ErHeaderFormatter.format("Table comment", Type.ordinal), 200);
    columnNameWidthMaps.put(ErHeaderFormatter.format("Engine", Type.ordinal), 100);
    columnNameWidthMaps.put(ErHeaderFormatter.format("Charset", Type.ordinal), 100);
    columnNameWidthMaps.put(ErHeaderFormatter.format("Collate", Type.ordinal), 100);
    columnNameWidthMaps.put(ErHeaderFormatter.format("Auto increment", Type.ordinal), 100);
    columnNameWidthMaps.put(ErHeaderFormatter.format("Option", Type.ordinal), 100);
    columnNameWidthMaps.put(ErHeaderFormatter.format("Group", Type.ordinal), 100);
    columnNameWidthMaps.put(ErHeaderFormatter.format("Partition", Type.ordinal), 100);
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
    var tables = this;

    // layout
    setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));

    // groups, partitions
    var groups = new ArrayList<String>();
    groups.add("");
    groups.addAll(Bucket.getInstance().getBucketDict().dbDictGroupList.stream()
        .map(g -> g.groupName)
        .toList());
    var partitions = new ArrayList<String>();
    partitions.add("");
    partitions.addAll(Bucket.getInstance().getBucketDict().dbDictPartitionList.stream()
        .map(p -> p.partitionName)
        .toList());

    // table
    var panelTable = new JPanel();
    add(panelTable);

    var columnNames = columnNameWidthMaps.keySet().toArray();
    tableModel = new DefaultTableModel(columnNames, 0) {
      @Override
      public boolean isCellEditable(int row, int column) {
        // table id is not editable
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

      // table id
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

      // engine
      if (i == 3) {
        var comboBoxEngine = new JComboBox(
            new DefaultComboBoxModel(Engine.getEngineListForTable().toArray()));
        comboBoxEngine.setEditable(true);
        tc.setCellEditor(new DefaultCellEditor(comboBoxEngine));
      }

      // charset
      if (i == 4) {
        var comboBoxCharset = new JComboBox(
            new DefaultComboBoxModel(Charset.getCharsetListForTable().toArray()));
        comboBoxCharset.setEditable(true);
        tc.setCellEditor(new DefaultCellEditor(comboBoxCharset));
      }

      // collate
      if (i == 5) {
        var comboBoxCollate = new JComboBox(
            new DefaultComboBoxModel(Collate.getCollateListForTable().toArray()));
        comboBoxCollate.setEditable(true);
        tc.setCellEditor(new DefaultCellEditor(comboBoxCollate));
      }

      // group
      if (i == 8) {
        var comboBoxGroup = new JComboBox(
            new DefaultComboBoxModel(groups.toArray()));
        tc.setCellEditor(new DefaultCellEditor(comboBoxGroup));
      }

      // partition
      if (i == 9) {
        var comboBoxPartition = new JComboBox(
            new DefaultComboBoxModel(partitions.toArray()));
        tc.setCellEditor(new DefaultCellEditor(comboBoxPartition));
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
        root.getEditTables().dispose();
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

    var ctxTableList = Bucket.getInstance().getBucketTable().ctxTableList;
    var dbDictGroupList = Bucket.getInstance().getBucketDict().dbDictGroupList;
    var dbDictPartitionList = Bucket.getInstance().getBucketDict().dbDictPartitionList;

    var i = 0;
    for (var dbTable : ctxTableList.stream()
        .map(t -> t.dbTable)
        .sorted(Comparator.comparing(DbTable::getTableNameForSort))
        .collect(Collectors.toList())) {
      tableModel.setRowCount(i + 1);

      table.setValueAt(dbTable.tableId, i, 0);
      table.setValueAt(dbTable.tableName, i, 1);
      table.setValueAt(dbTable.tableComment, i, 2);
      table.setValueAt(dbTable.engine, i, 3);
      table.setValueAt(dbTable.charsetValue, i, 4);
      table.setValueAt(dbTable.collateValue, i, 5);
      table.setValueAt(dbTable.autoIncrementValue, i, 6);
      table.setValueAt(dbTable.option, i, 7);

      var groupName = "";
      var optGroup = ctxTableList.stream()
          .filter(t -> t.dbTableGroup != null)
          .filter(t -> t.dbTableGroup.tableId.equals(dbTable.tableId))
          .findFirst();
      if (optGroup.isPresent()) {
        var dictGroupId = optGroup.get().dbTableGroup.dictGroupId;
        groupName = dbDictGroupList.stream()
            .filter(g -> g.dictGroupId.equals(dictGroupId))
            .findFirst()
            .get()
            .groupName;
      }
      table.setValueAt(groupName, i, 8);

      var partitionName = "";
      var optPartition = ctxTableList.stream()
          .filter(t -> t.dbTablePartition != null)
          .filter(t -> t.dbTablePartition.tableId.equals(dbTable.tableId))
          .findFirst();
      if (optPartition.isPresent()) {
        var dictPartitionId = optPartition.get().dbTablePartition.dictPartitionId;
        partitionName = dbDictPartitionList.stream()
            .filter(p -> p.dictPartitionId.equals(dictPartitionId))
            .findFirst()
            .get()
            .partitionName;
      }
      table.setValueAt(partitionName, i, 9);

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
      var dbDictGroupList = Bucket.getInstance().getBucketDict().dbDictGroupList;
      var dbDictPartitionList = Bucket.getInstance().getBucketDict().dbDictPartitionList;

      var newDbTableList = new ArrayList<DbTable>();
      var newDbTableGroupList = new ArrayList<DbTableGroup>();
      var newDbTablePartitionList = new ArrayList<DbTablePartition>();
      for (int i = 0; i < table.getRowCount(); i++) {
        var newDbTable = new DbTable();
        newDbTable.tableId = Utils.getString(table.getValueAt(i, 0));
        newDbTable.tableName = Utils.getString(table.getValueAt(i, 1));
        newDbTable.tableComment = Utils.getString(table.getValueAt(i, 2));
        newDbTable.engine = Utils.getString(table.getValueAt(i, 3));
        newDbTable.charsetValue = Utils.getString(table.getValueAt(i, 4));
        newDbTable.collateValue = Utils.getString(table.getValueAt(i, 5));
        newDbTable.autoIncrementValue = Utils.getString(table.getValueAt(i, 6));
        newDbTable.option = Utils.getString(table.getValueAt(i, 7));

        if (Utils.isNullOrEmpty(newDbTable.tableName)) {
          throw new Exception("Required 'Table name'.");
        }
        if (!Word.isValid(newDbTable.tableName)) {
          throw new Exception("Invalid 'Table name'.");
        }

        if (newDbTable.engine.equals(Engine.DEFAULT_ENGINE)) {
          newDbTable.engine = "";
        }

        if (newDbTable.charsetValue.equals(Charset.DEFAULT_CHARSET)) {
          newDbTable.charsetValue = "";
        }

        if (newDbTable.collateValue.equals(Collate.DEFAULT_COLLATE)) {
          newDbTable.collateValue = "";
        }

        if (!Utils.isNullOrEmpty(newDbTable.autoIncrementValue) && !Utils.isNumber(
            newDbTable.autoIncrementValue)) {
          throw new Exception("Must be number 'Auto increment'.");
        }

        newDbTableList.add(newDbTable);

        var groupName = Utils.getString(table.getValueAt(i, 8));
        if (!Utils.isNullOrEmpty(groupName)) {
          var opt = dbDictGroupList.stream()
              .filter(g -> g.groupName.equals(groupName))
              .findFirst();
          if (opt.isPresent()) {
            var newDbTableGroup = new DbTableGroup();
            newDbTableGroup.tableId = newDbTable.tableId;
            newDbTableGroup.dictGroupId = opt.get().dictGroupId;

            newDbTableGroupList.add(newDbTableGroup);
          }
        }

        var partitionName = Utils.getString(table.getValueAt(i, 9));
        if (!Utils.isNullOrEmpty(partitionName)) {
          var opt = dbDictPartitionList.stream()
              .filter(g -> g.partitionName.equals(partitionName))
              .findFirst();
          if (opt.isPresent()) {
            var newDbTablePartition = new DbTablePartition();
            newDbTablePartition.tableId = newDbTable.tableId;
            newDbTablePartition.dictPartitionId = opt.get().dictPartitionId;

            newDbTablePartitionList.add(newDbTablePartition);
          }
        }
      }

      // save
      Bucket.getInstance().getBucketTable()
          .saveBulk(newDbTableList, newDbTableGroupList, newDbTablePartitionList);
      root.getEditTables().changeState();

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
