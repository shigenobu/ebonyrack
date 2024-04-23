package com.walksocket.er.component.input.table.root.form.other;

import com.walksocket.er.Size.DialogLarge;
import com.walksocket.er.Utils;
import com.walksocket.er.component.InputPrimaryKey;
import com.walksocket.er.component.input.table.root.form.Other;
import com.walksocket.er.custom.ErHeaderFormatter;
import com.walksocket.er.custom.ErHeaderFormatter.Type;
import com.walksocket.er.definition.IndexType;
import com.walksocket.er.definition.Key;
import com.walksocket.er.parts.ColumnKeyOption;
import com.walksocket.er.sqlite.Bucket;
import com.walksocket.er.sqlite.Tmp;
import com.walksocket.er.sqlite.TmpResult;
import com.walksocket.er.sqlite.context.CtxTable;
import com.walksocket.er.sqlite.tmp.TmpKey;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 * PrimaryKey.
 */
public class PrimaryKey extends JPanel {

  /**
   * column name and width maps.
   */
  private static final Map<String, Integer> columnNameWidthMaps = new LinkedHashMap<>();

  static {
    columnNameWidthMaps.put(ErHeaderFormatter.format("Key name", Type.showOnly), 200);
    columnNameWidthMaps.put(
        ErHeaderFormatter.format("Columns and collations", Type.required, Type.openDialog), 400);
    columnNameWidthMaps.put(ErHeaderFormatter.format("Index comment", Type.ordinal), 200);
    columnNameWidthMaps.put(ErHeaderFormatter.format("Index type", Type.ordinal), 100);
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
  public PrimaryKey(Other other, CtxTable ctxTable) {
    this.other = other;
    this.ctxTable = ctxTable;

    // layout
    setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
    var primaryKey = this;

    // panel - table
    var panel1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
    add(panel1);

    var columnNames = columnNameWidthMaps.keySet().toArray();
    tableModel = new DefaultTableModel(columnNames, 1) {
      @Override
      public boolean isCellEditable(int row, int column) {
        // Key name is not editable
        // Columns and collations is not editable
        return column > 1;
      }
    };

    var widthList = columnNameWidthMaps.values().toArray(new Integer[columnNameWidthMaps.size()]);
    table = new JTable(tableModel);
    table.putClientProperty("terminateEditOnFocusLost", true);
    table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
    for (int i = 0; i < table.getColumnModel().getColumnCount(); i++) {
      var tc = table.getColumnModel().getColumn(i);
      tc.setPreferredWidth(widthList[i]);

      // index type
      if (i == 3) {
        var comboBoxIndexType = new JComboBox(
            new DefaultComboBoxModel(IndexType.getIndexTypeListForColumn()
                .toArray()));
        comboBoxIndexType.setEditable(true);
        tc.setCellEditor(new DefaultCellEditor(comboBoxIndexType));
      }
    }
    table.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        if (e.getClickCount() == 2) {
          Point pt = e.getPoint();
          int row = table.rowAtPoint(pt);
          int col = table.columnAtPoint(pt);
          if (row >= 0 && col == 1) {
            var tmpPrimaryKey = getTmpPrimaryKey(row);
            var tmpColumnList = other.getForm().getColumn().getColumns().getTmpColumnList();
            tmpColumnList.addAll(other.getForm().getColumn().getGroup().getTmpColumnList());
            var inputPrimaryKey = new InputPrimaryKey(primaryKey, row, tmpPrimaryKey,
                tmpColumnList);
            inputPrimaryKey.setModal(true);
            inputPrimaryKey.setVisible(true);
          }
        }
      }
    });
    var sp = new JScrollPane(table);
    sp.setPreferredSize(new Dimension(DialogLarge.WIDTH - 40, DialogLarge.HEIGHT / 40 * 6));
    panel1.add(sp);

    // bucket
    var dbDictColumnList = Bucket.getInstance().getBucketDict().dbDictColumnList;

    // tmp primary key
    var ctxInnerPrimaryKey = ctxTable.ctxInnerPrimaryKey;
    var tmpPrimaryKey = Tmp.createTmpKey(
        ctxInnerPrimaryKey.dbTablePrimaryKey,
        ctxInnerPrimaryKey.dbTablePrimaryKeyColumnList,
        dbDictColumnList);
    table.setValueAt(tmpPrimaryKey.keyName, 0, 0);
    table.setValueAt(tmpPrimaryKey.getColumnsAndCollations(), 0, 1);
    table.setValueAt(tmpPrimaryKey.indexComment, 0, 2);
    table.setValueAt(tmpPrimaryKey.indexType, 0, 3);
  }

  /**
   * get tmp primary key.
   *
   * @param row row
   * @return tmp primary key
   */
  public TmpKey getTmpPrimaryKey(int row) {
    var tmpPrimaryKey = new TmpKey();
    tmpPrimaryKey.keyName = Utils.getString(table.getValueAt(row, 0));
    tmpPrimaryKey.columnKeyOptionList = ColumnKeyOption.createColumnKeyOptionList(
        Utils.getString(table.getValueAt(row, 1)));
    tmpPrimaryKey.indexComment = Utils.getString(table.getValueAt(row, 2));
    tmpPrimaryKey.indexType = Utils.getString(table.getValueAt(row, 3));
    return tmpPrimaryKey;
  }

  /**
   * get valid tmp primary key.
   *
   * @return valid tmp primary key
   */
  public TmpKey getValidTmpPrimaryKey() {
    var tmpPrimaryKey = getTmpPrimaryKey(0);
    if (tmpPrimaryKey.columnKeyOptionList.size() == 0) {
      return null;
    }
    return tmpPrimaryKey;
  }

  /**
   * reset table.
   *
   * @param row           row
   * @param tmpPrimaryKey tmpPrimaryKey
   */
  public void resetTable(int row, TmpKey tmpPrimaryKey) {
    table.setValueAt(tmpPrimaryKey.getColumnsAndCollations(), row, 1);
  }

  /**
   * get result.
   *
   * @return result.
   */
  public TmpResult<TmpKey> getResult() {
    var tmpPrimaryKey = getTmpPrimaryKey(0);

    return new TmpResult<TmpKey>(tmpPrimaryKey) {
      @Override
      protected void validate() throws Exception {
        for (var tmp : tmpList) {
          tmp.keyName = Key.PRIMARY_KEY_NAME_VALUE;
          tmp.constraintName = tmp.keyName;
        }
      }
    };
  }
}
