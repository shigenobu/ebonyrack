package com.walksocket.er.component.input.table.root.form.column;

import com.walksocket.er.Log;
import com.walksocket.er.Size.DialogLarge;
import com.walksocket.er.Utils;
import com.walksocket.er.component.InputColumnName;
import com.walksocket.er.component.input.table.root.form.Column;
import com.walksocket.er.custom.ErHeaderFormatter;
import com.walksocket.er.custom.ErHeaderFormatter.Type;
import com.walksocket.er.custom.ErTableButtons;
import com.walksocket.er.definition.AutoIncrement;
import com.walksocket.er.definition.Charset;
import com.walksocket.er.definition.Collate;
import com.walksocket.er.definition.DataType;
import com.walksocket.er.definition.NotNull;
import com.walksocket.er.definition.OnUpdate;
import com.walksocket.er.sqlite.Bucket;
import com.walksocket.er.sqlite.Tmp;
import com.walksocket.er.sqlite.TmpResult;
import com.walksocket.er.sqlite.context.CtxTable;
import com.walksocket.er.sqlite.tmp.TmpColumn;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 * Columns.
 */
public class Columns extends JPanel {

  /**
   * column name and width maps.
   */
  private static final Map<String, Integer> columnNameWidthMaps = new LinkedHashMap<>();

  static {
    columnNameWidthMaps.put(ErHeaderFormatter.format("Column name", Type.required, Type.openDialog),
        200);
    columnNameWidthMaps.put(ErHeaderFormatter.format("Column comment", Type.ordinal), 200);
    columnNameWidthMaps.put(ErHeaderFormatter.format("Column type", Type.required), 200);
    columnNameWidthMaps.put(ErHeaderFormatter.format("Not null", Type.ordinal), 200);
    columnNameWidthMaps.put(ErHeaderFormatter.format("Charset", Type.ordinal), 100);
    columnNameWidthMaps.put(ErHeaderFormatter.format("Collate", Type.ordinal), 100);
    columnNameWidthMaps.put(ErHeaderFormatter.format("Default", Type.ordinal), 100);
    columnNameWidthMaps.put(ErHeaderFormatter.format("On update", Type.ordinal), 100);
    columnNameWidthMaps.put(ErHeaderFormatter.format("Auto increment", Type.ordinal), 100);
    columnNameWidthMaps.put(ErHeaderFormatter.format("Option", Type.ordinal), 100);
  }

  /**
   * column.
   */
  private final Column colum;

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
   * @param column   column
   * @param ctxTable ctxTable
   */
  public Columns(Column column, CtxTable ctxTable) {
    this.colum = column;
    this.ctxTable = ctxTable;

    // layout
    setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
    var columns = this;

    // panel - table
    var panelTable = new JPanel(new FlowLayout(FlowLayout.LEFT));
    add(panelTable);

    var rowCount = ctxTable.dbTableColumnList.size();
    if (rowCount == 0) {
      rowCount = 9;
    }
    rowCount += 3;
    var columnNames = columnNameWidthMaps.keySet().toArray();
    tableModel = new DefaultTableModel(columnNames, rowCount) {
      @Override
      public boolean isCellEditable(int row, int column) {
        // column name is not editable
        return column != 0;
      }
    };

    // bucket
    var dbDictColumnTypeList = Bucket.getInstance().getBucketDict().dbDictColumnTypeList;
    var dbDictColumnList = Bucket.getInstance().getBucketDict().dbDictColumnList;

    var widthList = columnNameWidthMaps.values().toArray(new Integer[columnNameWidthMaps.size()]);
    table = new JTable(tableModel);
    table.putClientProperty("terminateEditOnFocusLost", true);
    table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
    for (int i = 0; i < table.getColumnModel().getColumnCount(); i++) {
      var tc = table.getColumnModel().getColumn(i);
      tc.setPreferredWidth(widthList[i]);

      // column name
      if (i == 0) {
        tc.setCellRenderer(new DefaultTableCellRenderer() {
          @Override
          public Component getTableCellRendererComponent(JTable table, Object value,
              boolean isSelected, boolean hasFocus, int row, int column) {
            setBackground(new Color(255, 51, 51, 20));
            return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row,
                column);
          }
        });
      }

      // column type
      if (i == 2) {
        var columnTypeList = dbDictColumnTypeList.stream()
            .sorted(Comparator.comparing(c -> c.seq))
            .map(c -> c.columnType)
            .collect(Collectors.toList());
        columnTypeList.add(0, "");
        var comboBoxColumnType = new JComboBox(
            new DefaultComboBoxModel(columnTypeList.toArray()));
        tc.setCellEditor(new DefaultCellEditor(comboBoxColumnType));
      }

      // not null
      if (i == 3) {
        var comboBoxNotNull = new JComboBox(
            new DefaultComboBoxModel(NotNull.getNotNullListForColumn().toArray()));
        tc.setCellEditor(new DefaultCellEditor(comboBoxNotNull));
      }

      // charset
      if (i == 4) {
        var comboBoxCharset = new JComboBox(
            new DefaultComboBoxModel(Charset.getCharsetListForColumn().toArray()));
        comboBoxCharset.setEditable(true);
        tc.setCellEditor(new DefaultCellEditor(comboBoxCharset));
      }

      // collate
      if (i == 5) {
        var comboBoxCollate = new JComboBox(
            new DefaultComboBoxModel(Collate.getCollateListForColumn().toArray()));
        comboBoxCollate.setEditable(true);
        tc.setCellEditor(new DefaultCellEditor(comboBoxCollate));
      }

      // on update
      if (i == 7) {
        var comboBoxOnUpdate = new JComboBox(
            new DefaultComboBoxModel(OnUpdate.getOnUpdateListForColumn()
                .toArray()));
        tc.setCellEditor(new DefaultCellEditor(comboBoxOnUpdate));
      }

      // auto increment
      if (i == 8) {
        var comboBoxAutoIncrement = new JComboBox(
            new DefaultComboBoxModel(AutoIncrement.getAutoIncrementForColumn()
                .toArray()));
        tc.setCellEditor(new DefaultCellEditor(comboBoxAutoIncrement));
      }
    }
    table.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        if (e.getClickCount() == 2) {
          Point pt = e.getPoint();
          int row = table.rowAtPoint(pt);
          int col = table.columnAtPoint(pt);

          // column name
          if (row >= 0 && col == 0) {
            var tmpColumnForDict = getTmpColumnForDict(row);
            var inputColumnName = new InputColumnName(columns, row, tmpColumnForDict);
            inputColumnName.setAlwaysOnTop(true);
            inputColumnName.setModal(true);
            inputColumnName.setVisible(true);
          }
        }
      }
    });
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
              var pasteStr = (String) transferable.getTransferData(DataFlavor.stringFlavor);
              pasteStr = pasteStr.replace("\r", "");
              var rows = Arrays.stream(pasteStr.split("\n"))
                  .toList();
              for (int i = 0; i < rows.size(); i++) {
                tableModel.insertRow(selectedRow, new Object[columnCount]);
              }
              for (int i = 0; i < rows.size(); i++) {
                var cols = Arrays.stream(rows.get(i).split("\t"))
                    .toList();
                var colLimit = cols.size();
                if (colLimit > columnCount) {
                  colLimit = columnCount;
                }
                for (int j = 0; j < colLimit; j++) {
                  table.setValueAt(cols.get(j), i + selectedRow, j);
                }
              }
              table.setRowSelectionInterval(selectedRow, selectedRow + rows.size() - 1);
            } catch (Exception ex) {
              Log.error(ex);
              JOptionPane.showMessageDialog(columns.getColum().getForm().getRoot(),
                  ex.getMessage());
            }
          }
        } else if (e.getKeyCode() == KeyEvent.VK_DELETE) {
          var rs = table.getSelectedRows();
          var columnCount = table.getColumnCount();
          for (var r : rs) {
            for (int i = 0; i < columnCount; i++) {
              table.setValueAt("", r, i);
            }
          }
        }
      }
    });
    var sp = new JScrollPane(table);
    sp.setPreferredSize(new Dimension(DialogLarge.WIDTH - 40, DialogLarge.HEIGHT / 40 * 16));
    panelTable.add(sp);

    // panel - buttons
    var panel2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
    add(panel2);
    panel2.add(new ErTableButtons(table));

    // tmp column list
    var tmpColumnList = Tmp.createTmpColumnList(ctxTable.dbTableColumnList, dbDictColumnTypeList,
        dbDictColumnList);
    for (int i = 0; i < tmpColumnList.size(); i++) {
      var tmpColumn = tmpColumnList.get(i);
      setTmpColumnForDict(i, tmpColumn);
    }
  }

  /**
   * get column.
   *
   * @return column
   */
  public Column getColum() {
    return colum;
  }

  /**
   * get tmp column for dict
   *
   * @param row row
   * @return tmp column
   */
  private TmpColumn getTmpColumnForDict(int row) {
    var tmpColumn = new TmpColumn();
    tmpColumn.columnName = Utils.getString(table.getValueAt(row, 0));
    tmpColumn.columnComment = Utils.getString(table.getValueAt(row, 1));
    tmpColumn.columnType = Utils.getString(table.getValueAt(row, 2));
    tmpColumn.notNullValue = Utils.getString(table.getValueAt(row, 3));
    tmpColumn.charsetValue = Utils.getString(table.getValueAt(row, 4));
    tmpColumn.collateValue = Utils.getString(table.getValueAt(row, 5));
    tmpColumn.defaultValue = Utils.getString(table.getValueAt(row, 6));
    tmpColumn.onUpdate = Utils.getString(table.getValueAt(row, 7));
    tmpColumn.autoIncrementDefinition = Utils.getString(table.getValueAt(row, 8));
    tmpColumn.option = Utils.getString(table.getValueAt(row, 9));
    return tmpColumn;
  }

  /**
   * set tmp column for dict
   *
   * @param row       row
   * @param tmpColumn tmp column
   */
  public void setTmpColumnForDict(int row, TmpColumn tmpColumn) {
    table.setValueAt(tmpColumn.columnName, row, 0);
    table.setValueAt(tmpColumn.columnComment, row, 1);
    table.setValueAt(tmpColumn.columnType, row, 2);
    table.setValueAt(tmpColumn.notNullValue, row, 3);
    table.setValueAt(tmpColumn.charsetValue, row, 4);
    table.setValueAt(tmpColumn.collateValue, row, 5);
    table.setValueAt(tmpColumn.defaultValue, row, 6);
    table.setValueAt(tmpColumn.onUpdate, row, 7);
    table.setValueAt(tmpColumn.autoIncrementDefinition, row, 8);
    table.setValueAt(tmpColumn.option, row, 9);
  }

  /**
   * set tmp column for dict only column name.
   *
   * @param row        row
   * @param columnName columnName
   */
  public void setTmpColumnForDictOnlyColumnName(int row, String columnName) {
    table.setValueAt(columnName, row, 0);
  }

  /**
   * get tmp column list.
   *
   * @return tmp column list
   */
  public List<TmpColumn> getTmpColumnList() {
    var tmpColumnList = new ArrayList<TmpColumn>();
    for (int i = 0; i < table.getRowCount(); i++) {
      var tmpColumn = getTmpColumnForDict(i);
      if (tmpColumn.isAllEmpty()) {
        continue;
      }

      tmpColumnList.add(tmpColumn);
    }
    return tmpColumnList;
  }

  /**
   * get result.
   *
   * @return result
   */
  public TmpResult<TmpColumn> getResult() {
    var tmpColumnList = getTmpColumnList();

    return new TmpResult<TmpColumn>(tmpColumnList) {
      @Override
      protected void validate() throws Exception {
        var duplicateColumnNames = new ArrayList<String>();
        var autoIncrementAppears = 0;
        for (var tmp : tmpList) {
          var tmpError = tmp.checkAndGetError();
          if (!Utils.isNullOrEmpty(tmpError)) {
            throw new Exception(tmpError);
          }

          // column name
          if (duplicateColumnNames.contains(tmp.columnName)) {
            throw new Exception(String.format("Duplicate '%s'.", tmp.columnName));
          }
          duplicateColumnNames.add(tmp.columnName);

          // auto increment
          if (!Utils.isNullOrEmpty(tmp.autoIncrementDefinition)) {
            autoIncrementAppears++;

            if (!DataType.allowAutoIncrement(tmp.columnType)) {
              throw new Exception("Not allowed 'Auto increment'.");
            }
          }
        }

        // auto increment
        if (autoIncrementAppears > 1) {
          throw new Exception("Only once 'Auto increment'.");
        }
      }
    };
  }
}
