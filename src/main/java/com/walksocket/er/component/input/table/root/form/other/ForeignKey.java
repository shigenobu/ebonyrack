package com.walksocket.er.component.input.table.root.form.other;

import com.walksocket.er.Size.DialogLarge;
import com.walksocket.er.Utils;
import com.walksocket.er.Word;
import com.walksocket.er.component.InputForeignKey;
import com.walksocket.er.component.input.table.root.form.Other;
import com.walksocket.er.custom.ErConnectorStyle;
import com.walksocket.er.custom.ErDialog;
import com.walksocket.er.custom.ErHeaderFormatter;
import com.walksocket.er.custom.ErHeaderFormatter.Type;
import com.walksocket.er.custom.ErTableButtons;
import com.walksocket.er.definition.Key;
import com.walksocket.er.parts.ColumnForeignKeyOption;
import com.walksocket.er.sqlite.Bucket;
import com.walksocket.er.sqlite.Tmp;
import com.walksocket.er.sqlite.TmpResult;
import com.walksocket.er.sqlite.context.CtxTable;
import com.walksocket.er.sqlite.entity.DbTable;
import com.walksocket.er.sqlite.tmp.TmpForeignKey;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 * ForeignKey.
 */
public class ForeignKey extends JPanel {

  /**
   * column name and width maps.
   */
  private static final Map<String, Integer> columnNameWidthMaps = new LinkedHashMap<>();

  static {
    columnNameWidthMaps.put(ErHeaderFormatter.format("Constraint name", Type.ordinal), 200);
    columnNameWidthMaps.put(ErHeaderFormatter.format("Key name", Type.ordinal), 200);
    columnNameWidthMaps.put(ErHeaderFormatter.format("Columns", Type.required, Type.openDialog),
        400);
    columnNameWidthMaps.put(
        ErHeaderFormatter.format("Reference table", Type.required, Type.openDialog), 200);
    columnNameWidthMaps.put(
        ErHeaderFormatter.format("Reference columns", Type.required, Type.openDialog), 400);
    columnNameWidthMaps.put(ErHeaderFormatter.format("On update", Type.ordinal), 100);
    columnNameWidthMaps.put(ErHeaderFormatter.format("On delete", Type.ordinal), 100);
    columnNameWidthMaps.put(ErHeaderFormatter.format("Relationship", Type.ordinal), 200);
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
   * db table list.
   */
  private final List<DbTable> dbTableList = new ArrayList<>();

  /**
   * tmp foreign key list.
   */
  private final List<TmpForeignKey> tmpForeignKeyList = new ArrayList<>();

  /**
   * Constructor.
   *
   * @param other    other
   * @param ctxTable ctxTable
   */
  public ForeignKey(Other other, CtxTable ctxTable) {
    this.other = other;
    this.ctxTable = ctxTable;

    // set db table list
    for (var d : Bucket.getInstance().getBucketTable().ctxTableList.stream()
        .map(c -> c.dbTable)
        .sorted(Comparator.comparing(c -> c.tableName))
        .collect(Collectors.toList())) {
      var dbTable = new DbTable();
      dbTable.tableId = d.tableId;
      dbTable.tableName = d.tableName;
      dbTable.tableComment = d.tableComment;

      if (dbTable.tableId.equals(ctxTable.dbTable.tableId)) {
        dbTable.tableName = "(*)" + dbTable.tableName;
      }

      dbTableList.add(dbTable);
    }

    // layout
    setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
    var foreignKey = this;

    // panel - table
    var panel1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
    add(panel1);

    var columnNames = columnNameWidthMaps.keySet().toArray();
    tableModel = new DefaultTableModel(columnNames, 3) {
      @Override
      public boolean isCellEditable(int row, int column) {
        // Columns, Reference table and Reference columns is not editable
        return column != 2 && column != 3 && column != 4;
      }
    };

    var widthList = columnNameWidthMaps.values().toArray(new Integer[columnNameWidthMaps.size()]);
    table = new JTable(tableModel);
    table.putClientProperty("terminateEditOnFocusLost", true);
    table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
    for (int i = 0; i < table.getColumnModel().getColumnCount(); i++) {
      var tc = table.getColumnModel().getColumn(i);
      tc.setPreferredWidth(widthList[i]);

      // on update
      if (i == 5) {
        var comboBoxOnUpdate = new JComboBox(
            new DefaultComboBoxModel(Key.getReferenceOptionList().toArray()));
        tc.setCellEditor(new DefaultCellEditor(comboBoxOnUpdate));
      }

      // on delete
      if (i == 6) {
        var comboBoxOnDelete = new JComboBox(
            new DefaultComboBoxModel(Key.getReferenceOptionList().toArray()));
        tc.setCellEditor(new DefaultCellEditor(comboBoxOnDelete));
      }

      // relationship
      if (i == 7) {
        var comboBoxRelationship = new JComboBox(
            new DefaultComboBoxModel(ErConnectorStyle.getStyleList()
                .toArray()));
        tc.setCellEditor(new DefaultCellEditor(comboBoxRelationship));
      }
    }
    table.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        if (e.getClickCount() == 2) {
          Point pt = e.getPoint();
          int row = table.rowAtPoint(pt);
          int col = table.columnAtPoint(pt);
          if (row >= 0 && (col == 2 || col == 3 || col == 4)) {
            var tmpForeignKey = getTmpForeignKey(row);
            var tmpColumnList = other.getForm().getColumn().getColumns().getTmpColumnList();
            tmpColumnList.addAll(other.getForm().getColumn().getGroup().getTmpColumnList());

            // key
            var validTmpPrimaryKey = other.getPrimaryKey().getValidTmpPrimaryKey();
            var validTmpUniqueKeyList = other.getUniqueKey().getValidTmpUniqueKeyList();
            var validTmpKeyList = other.getKey().getValidTmpKeyList();

            ErDialog.show(new InputForeignKey(foreignKey, row, tmpForeignKey, tmpColumnList,
                validTmpPrimaryKey,
                validTmpUniqueKeyList,
                validTmpKeyList,
                dbTableList));
          }
        }
      }
    });
    var sp = new JScrollPane(table);
    sp.setPreferredSize(new Dimension(DialogLarge.WIDTH - 40, DialogLarge.HEIGHT / 40 * 6));
    panel1.add(sp);

    // panel - buttons
    var panel2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
    add(panel2);
    panel2.add(new ErTableButtons(table));

    // bucket
    var dbDictColumnList = Bucket.getInstance().getBucketDict().dbDictColumnList;

    // tmp foreign key
    for (int i = 0; i < ctxTable.ctxInnerForeignKeyList.size(); i++) {
      var ctxInnerForeignKey = ctxTable.ctxInnerForeignKeyList.get(i);
      var tmpForeignKey = Tmp.createTmpForeignKey(ctxInnerForeignKey.dbTableForeignKey,
          ctxInnerForeignKey.dbTableForeignKeyColumnList, dbTableList, dbDictColumnList);
      tmpForeignKeyList.add(tmpForeignKey);
      tableModel.setRowCount(i + 1);

      table.setValueAt(tmpForeignKey.constraintName, i, 0);
      table.setValueAt(tmpForeignKey.keyName, i, 1);
      table.setValueAt(tmpForeignKey.getColumns(), i, 2);
      table.setValueAt(tmpForeignKey.referenceTableName, i, 3);
      table.setValueAt(tmpForeignKey.getReferenceColumns(), i, 4);
      table.setValueAt(tmpForeignKey.onUpdate, i, 5);
      table.setValueAt(tmpForeignKey.onDelete, i, 6);
      table.setValueAt(tmpForeignKey.relationship, i, 7);
    }
  }

  /**
   * get tmp foreign key.
   *
   * @param row row
   * @return tmp foreign key
   */
  public TmpForeignKey getTmpForeignKey(int row) {
    var tmpForeignKey = new TmpForeignKey();
    tmpForeignKey.constraintName = Utils.getString(table.getValueAt(row, 0));
    tmpForeignKey.keyName = Utils.getString(table.getValueAt(row, 1));
    tmpForeignKey.columnForeignKeyOptionList = ColumnForeignKeyOption.createColumnForeignKeyOptionList(
        Utils.getString(table.getValueAt(row, 2)));
    tmpForeignKey.referenceTableName = Utils.getString(table.getValueAt(row, 3));
    tmpForeignKey.referenceColumnForeignKeyOptionList = ColumnForeignKeyOption.createColumnForeignKeyOptionList(
        Utils.getString(table.getValueAt(row, 4)));
    tmpForeignKey.onUpdate = Utils.getString(table.getValueAt(row, 5));
    tmpForeignKey.onDelete = Utils.getString(table.getValueAt(row, 6));
    tmpForeignKey.relationship = Utils.getString(table.getValueAt(row, 7));
    return tmpForeignKey;
  }

  /**
   * reset table.
   *
   * @param row           row
   * @param tmpForeignKey tmpForeignKey
   */
  public void resetTable(int row, TmpForeignKey tmpForeignKey) {
    table.setValueAt(tmpForeignKey.getColumns(), row, 2);
    table.setValueAt(tmpForeignKey.referenceTableName, row, 3);
    table.setValueAt(tmpForeignKey.getReferenceColumns(), row, 4);
  }

  /**
   * get result.
   *
   * @return result.
   */
  public TmpResult<TmpForeignKey> getResult() {
    var tmpForeignKeyList = new ArrayList<TmpForeignKey>();
    for (int i = 0; i < tableModel.getRowCount(); i++) {
      tmpForeignKeyList.add(getTmpForeignKey(i));
    }

    return new TmpResult<TmpForeignKey>(tmpForeignKeyList) {
      @Override
      protected void validate() throws Exception {
        for (var tmp : tmpList) {
          if (Utils.isNullOrEmpty(tmp.keyName)) {
            tmp.keyName = "fk_" + tmp.columnForeignKeyOptionList.stream()
                .map(e -> e.columnName)
                .collect(Collectors.joining("_"));
          }
          if (!Word.isValid(tmp.keyName)) {
            throw new Exception("Invalid 'Key name'.");
          }
          if (Utils.isNullOrEmpty(tmp.constraintName)) {
            var tmpTable = other.getForm().getBase().getTable().getTmpTable();
            tmp.constraintName = String.format("%s_%s", tmpTable.tableName, tmp.keyName);
          }
        }
      }
    };
  }
}
