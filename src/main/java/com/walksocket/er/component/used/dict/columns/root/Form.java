package com.walksocket.er.component.used.dict.columns.root;

import com.walksocket.er.Size.DialogUsed;
import com.walksocket.er.sqlite.Bucket;
import com.walksocket.er.sqlite.entity.DbDictColumn;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;
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
   * label dict column id.
   */
  private final JLabel labelDictColumnId = new JLabel("Dict column id:");

  /**
   * text field dict column id.
   */
  private final JTextField textFieldDictColumnId = new JTextField(20);

  /**
   * label column name.
   */
  private final JLabel labelColumnName = new JLabel("Column name:");

  /**
   * text field column name.
   */
  private final JTextField textFieldColumnName = new JTextField(20);

  /**
   * label column comment.
   */
  private final JLabel labelColumnComment = new JLabel("Column comment:");

  /**
   * text field column comment.
   */
  private final JTextField textFieldColumnComment = new JTextField(20);

  /**
   * label column type.
   */
  private final JLabel labelColumnType = new JLabel("Column type:");

  /**
   * text field column type.
   */
  private final JTextField textFieldColumnType = new JTextField(20);

  /**
   * label not null.
   */
  private final JLabel labelNotNull = new JLabel("Not null:");

  /**
   * text field not null.
   */
  private final JTextField textFieldNotNull = new JTextField(20);

  /**
   * label charset.
   */
  private final JLabel labelCharset = new JLabel("Charset:");

  /**
   * text field charset.
   */
  private final JTextField textFieldCharset = new JTextField(20);

  /**
   * label collate.
   */
  private final JLabel labelCollate = new JLabel("Collate:");

  /**
   * text field collate.
   */
  private final JTextField textFieldCollate = new JTextField(20);

  /**
   * label default.
   */
  private final JLabel labelDefault = new JLabel("Default:");

  /**
   * text field default.
   */
  private final JTextField textFieldDefault = new JTextField(20);

  /**
   * label on update.
   */
  private final JLabel labelOnUpdate = new JLabel("On update:");

  /**
   * text field on update.
   */
  private final JTextField textFieldOnUpdate = new JTextField(20);

  /**
   * label auto increment.
   */
  private final JLabel labelAutoIncrement = new JLabel("Auto increment:");

  /**
   * text field auto increment.
   */
  private final JTextField textFieldAutoIncrement = new JTextField(20);

  /**
   * label option.
   */
  private final JLabel labelOption = new JLabel("Option:");

  /**
   * text field option.
   */
  private final JTextField textFieldOption = new JTextField(50);

  /**
   * db dict column.
   */
  private final DbDictColumn dbDictColumn;

  /**
   * column name and width maps for group.
   */
  private static final Map<String, Integer> columnNameWidthMapsForGroup = new LinkedHashMap<>();

  static {
    // b -> required
    // i -> open dialog
    // u -> with dict
    // s -> show only
    columnNameWidthMapsForGroup.put("<html><s>Dict group id</s></html>", 200);
    columnNameWidthMapsForGroup.put("<html><s>Group name</s></html>", 200);
  }

  /**
   * table for group.
   */
  private final JTable tableForGroup;

  /**
   * table model for group.
   */
  private final DefaultTableModel tableModelForGroup;

  /**
   * column name and width maps for db table.
   */
  private static final Map<String, Integer> columnNameWidthMapsForDbTable = new LinkedHashMap<>();

  static {
    // b -> required
    // i -> open dialog
    // u -> with dict
    // s -> show only
    columnNameWidthMapsForDbTable.put("<html><s>Table id</s></html>", 200);
    columnNameWidthMapsForDbTable.put("<html><s>Table name</s></html>", 200);
    columnNameWidthMapsForDbTable.put("<html><s>Table comment</s></html>", 200);
    columnNameWidthMapsForDbTable.put("<html><s>At</s></html>", 200);
  }

  /**
   * table for db table.
   */
  private final JTable tableForDbTable;

  /**
   * table model for db table.
   */
  private final DefaultTableModel tableModelForDbTable;

  /**
   * column name and width maps for reference.
   */
  private static final Map<String, Integer> columnNameWidthMapsForReference = new LinkedHashMap<>();

  static {
    // b -> required
    // i -> open dialog
    // u -> with dict
    // s -> show only
    columnNameWidthMapsForReference.put("<html><s>Table id</s></html>", 200);
    columnNameWidthMapsForReference.put("<html><s>Table name</s></html>", 200);
    columnNameWidthMapsForReference.put("<html><s>Table comment</s></html>", 200);
    columnNameWidthMapsForReference.put("<html><s>Reference table id</s></html>", 200);
    columnNameWidthMapsForReference.put("<html><s>Reference table name</s></html>", 200);
    columnNameWidthMapsForReference.put("<html><s>Reference table comment</s></html>", 200);
  }

  /**
   * table for reference.
   */
  private final JTable tableForReference;

  /**
   * table model for reference.
   */
  private final DefaultTableModel tableModelForReference;

  /**
   * Constructor.
   *
   * @param dbDictColumn dbDictColumn
   */
  public Form(DbDictColumn dbDictColumn) {
    this.dbDictColumn = dbDictColumn;

    // layout
    setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));

    // dict column id
    var panel1 = new JPanel();
    add(panel1);
    panel1.add(labelDictColumnId);
    textFieldDictColumnId.setText(dbDictColumn.dictColumnId);
    textFieldDictColumnId.setEditable(false);
    panel1.add(textFieldDictColumnId);

    // column name
    var panel2 = new JPanel();
    add(panel2);
    panel2.add(labelColumnName);
    textFieldColumnName.setText(dbDictColumn.columnName);
    panel2.add(textFieldColumnName);

    // column comment
    var panel3 = new JPanel();
    add(panel3);
    panel3.add(labelColumnComment);
    textFieldColumnComment.setText(dbDictColumn.columnComment);
    panel3.add(textFieldColumnComment);

    // column type
    var panel4 = new JPanel();
    add(panel4);
    panel4.add(labelColumnType);
    var dbDictColumnType = Bucket.getInstance().getBucketDict().dbDictColumnTypeList.stream()
        .filter(d -> d.dictColumnTypeId.equals(dbDictColumn.dictColumnTypeId))
        .findFirst()
        .get();
    textFieldColumnType.setText(dbDictColumnType.columnType);
    panel4.add(textFieldColumnType);

    // not null
    var panel5 = new JPanel();
    add(panel5);
    panel5.add(labelNotNull);
    textFieldNotNull.setText(dbDictColumn.notNullValue);
    panel5.add(textFieldNotNull);

    // charset
    var panel6 = new JPanel();
    add(panel6);
    panel6.add(labelCharset);
    textFieldCharset.setText(dbDictColumn.charsetValue);
    panel6.add(textFieldCharset);

    // collate
    var panel7 = new JPanel();
    add(panel7);
    panel7.add(labelCollate);
    textFieldCollate.setText(dbDictColumn.collateValue);
    panel7.add(textFieldCollate);

    // default
    var panel8 = new JPanel();
    add(panel8);
    panel8.add(labelDefault);
    textFieldDefault.setText(dbDictColumn.defaultValue);
    panel8.add(textFieldDefault);

    // on update
    var panel9 = new JPanel();
    add(panel9);
    panel9.add(labelOnUpdate);
    textFieldOnUpdate.setText(dbDictColumn.onUpdate);
    panel9.add(textFieldOnUpdate);

    // auto increment
    var panel10 = new JPanel();
    add(panel10);
    panel10.add(labelAutoIncrement);
    textFieldAutoIncrement.setText(dbDictColumn.autoIncrementDefinition);
    panel10.add(textFieldAutoIncrement);

    // option
    var panel11 = new JPanel();
    add(panel11);
    panel11.add(labelOption);
    textFieldOption.setText(dbDictColumn.option);
    panel11.add(textFieldOption);

    // table for group
    var panelTableForGroup = new JPanel();
    add(panelTableForGroup);

    var columnNamesForGroup = columnNameWidthMapsForGroup.keySet().toArray();
    tableModelForGroup = new DefaultTableModel(columnNamesForGroup, 0) {
      @Override
      public boolean isCellEditable(int row, int column) {
        return false;
      }
    };

    var widthListForGroup = columnNameWidthMapsForGroup.values()
        .toArray(new Integer[columnNameWidthMapsForGroup.size()]);
    tableForGroup = new JTable(tableModelForGroup);
    tableForGroup.putClientProperty("terminateEditOnFocusLost", true);
    tableForGroup.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
    for (int i = 0; i < tableForGroup.getColumnModel().getColumnCount(); i++) {
      var tc = tableForGroup.getColumnModel().getColumn(i);
      tc.setPreferredWidth(widthListForGroup[i]);
    }

    var spForGroup = new JScrollPane(tableForGroup);
    spForGroup.setPreferredSize(new Dimension(DialogUsed.WIDTH - 40, DialogUsed.HEIGHT / 20 * 2));
    panelTableForGroup.add(spForGroup);

    // load for group
    loadTableForGroup();

    // table for db table
    var panelTableForDbTable = new JPanel();
    add(panelTableForDbTable);

    var columnNamesForDbTable = columnNameWidthMapsForDbTable.keySet().toArray();
    tableModelForDbTable = new DefaultTableModel(columnNamesForDbTable, 0) {
      @Override
      public boolean isCellEditable(int row, int column) {
        return false;
      }
    };

    var widthListForDbTable = columnNameWidthMapsForDbTable.values()
        .toArray(new Integer[columnNameWidthMapsForDbTable.size()]);
    tableForDbTable = new JTable(tableModelForDbTable);
    tableForDbTable.putClientProperty("terminateEditOnFocusLost", true);
    tableForDbTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
    for (int i = 0; i < tableForDbTable.getColumnModel().getColumnCount(); i++) {
      var tc = tableForDbTable.getColumnModel().getColumn(i);
      tc.setPreferredWidth(widthListForDbTable[i]);
    }

    var spForDbTable = new JScrollPane(tableForDbTable);
    spForDbTable.setPreferredSize(
        new Dimension(DialogUsed.WIDTH - 40, DialogUsed.HEIGHT / 20 * 6));
    panelTableForDbTable.add(spForDbTable);

    // load for db table
    loadTableForDbTable();

    // table for reference
    var panelTableForReference = new JPanel();
    add(panelTableForReference);

    var columnNamesForReference = columnNameWidthMapsForReference.keySet().toArray();
    tableModelForReference = new DefaultTableModel(columnNamesForReference, 0) {
      @Override
      public boolean isCellEditable(int row, int column) {
        return false;
      }
    };

    var widthListForReference = columnNameWidthMapsForReference.values()
        .toArray(new Integer[columnNameWidthMapsForReference.size()]);
    tableForReference = new JTable(tableModelForReference);
    tableForReference.putClientProperty("terminateEditOnFocusLost", true);
    tableForReference.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
    for (int i = 0; i < tableForReference.getColumnModel().getColumnCount(); i++) {
      var tc = tableForReference.getColumnModel().getColumn(i);
      tc.setPreferredWidth(widthListForReference[i]);
    }

    var spForReference = new JScrollPane(tableForReference);
    spForReference.setPreferredSize(
        new Dimension(DialogUsed.WIDTH - 40, DialogUsed.HEIGHT / 20 * 5));
    panelTableForReference.add(spForReference);

    // load for reference
    loadTableForReference();
  }

  /**
   * load table for group.
   */
  private void loadTableForGroup() {
    tableModelForGroup.setRowCount(0);

    var i = 0;
    var dbDictGroupColumnList = Bucket.getInstance().getBucketDict().dbDictGroupColumnList.stream()
        .filter(c -> c.dictColumnId.equals(dbDictColumn.dictColumnId))
        .collect(Collectors.toList());
    for (var dbDictGroupColumn : dbDictGroupColumnList) {
      var dbDictGroupList = Bucket.getInstance().getBucketDict().dbDictGroupList.stream()
          .filter(d -> d.dictGroupId.equals(dbDictGroupColumn.dictGroupId))
          .collect(Collectors.toList());
      for (var dbDictGroup : dbDictGroupList) {
        tableModelForGroup.setRowCount(i + 1);

        tableModelForGroup.setValueAt(dbDictGroup.dictGroupId, i, 0);
        tableModelForGroup.setValueAt(dbDictGroup.groupName, i, 1);

        i++;
      }
    }
  }

  /**
   * load table for db table.
   */
  private void loadTableForDbTable() {
    tableModelForDbTable.setRowCount(0);

    var ctxTableList = Bucket.getInstance().getBucketTable().ctxTableList;

    var i = 0;
    for (var ctxTable : ctxTableList) {
      // table column
      var optTableColumn = ctxTable.dbTableColumnList.stream()
          .filter(c -> c.dictColumnId.equals(dbDictColumn.dictColumnId))
          .findFirst();
      if (optTableColumn.isPresent()) {
        tableModelForDbTable.setRowCount(i + 1);

        tableModelForDbTable.setValueAt(ctxTable.dbTable.tableId, i, 0);
        tableModelForDbTable.setValueAt(ctxTable.dbTable.tableName, i, 1);
        tableModelForDbTable.setValueAt(ctxTable.dbTable.tableComment, i, 2);
        tableModelForDbTable.setValueAt("table column", i, 3);

        i++;
      }

      // primary
      var optPrimaryKey = ctxTable.ctxInnerPrimaryKey.dbTablePrimaryKeyColumnList.stream()
          .filter(c -> c.dictColumnId.equals(dbDictColumn.dictColumnId))
          .findFirst();
      if (optPrimaryKey.isPresent()) {
        tableModelForDbTable.setRowCount(i + 1);

        tableModelForDbTable.setValueAt(ctxTable.dbTable.tableId, i, 0);
        tableModelForDbTable.setValueAt(ctxTable.dbTable.tableName, i, 1);
        tableModelForDbTable.setValueAt(ctxTable.dbTable.tableComment, i, 2);
        tableModelForDbTable.setValueAt("primary key", i, 3);

        i++;
      }

      // unique key
      for (var uniqueKeyColumnList : ctxTable.ctxInnerUniqueKeyList.stream()
          .map(c -> c.dbTableUniqueKeyColumnList)
          .collect(Collectors.toList())) {
        var optUniqueKey = uniqueKeyColumnList.stream()
            .filter(c -> c.dictColumnId.equals(dbDictColumn.dictColumnId))
            .findFirst();
        if (optUniqueKey.isPresent()) {
          tableModelForDbTable.setRowCount(i + 1);

          tableModelForDbTable.setValueAt(ctxTable.dbTable.tableId, i, 0);
          tableModelForDbTable.setValueAt(ctxTable.dbTable.tableName, i, 1);
          tableModelForDbTable.setValueAt(ctxTable.dbTable.tableComment, i, 2);
          tableModelForDbTable.setValueAt("unique key", i, 3);

          i++;
        }
      }

      // key
      for (var keyColumnList : ctxTable.ctxInnerKeyList.stream()
          .map(c -> c.dbTableKeyColumnList)
          .collect(Collectors.toList())) {
        var optKey = keyColumnList.stream()
            .filter(c -> c.dictColumnId.equals(dbDictColumn.dictColumnId))
            .findFirst();
        if (optKey.isPresent()) {
          tableModelForDbTable.setRowCount(i + 1);

          tableModelForDbTable.setValueAt(ctxTable.dbTable.tableId, i, 0);
          tableModelForDbTable.setValueAt(ctxTable.dbTable.tableName, i, 1);
          tableModelForDbTable.setValueAt(ctxTable.dbTable.tableComment, i, 2);
          tableModelForDbTable.setValueAt("key", i, 3);

          i++;
        }
      }

      // foreign key
      for (var foreignKeyColumnList : ctxTable.ctxInnerForeignKeyList.stream()
          .map(c -> c.dbTableForeignKeyColumnList)
          .collect(Collectors.toList())) {
        var optForeignKey = foreignKeyColumnList.stream()
            .filter(c -> c.dictColumnId.equals(dbDictColumn.dictColumnId))
            .findFirst();
        if (optForeignKey.isPresent()) {
          tableModelForDbTable.setRowCount(i + 1);

          tableModelForDbTable.setValueAt(ctxTable.dbTable.tableId, i, 0);
          tableModelForDbTable.setValueAt(ctxTable.dbTable.tableName, i, 1);
          tableModelForDbTable.setValueAt(ctxTable.dbTable.tableComment, i, 2);
          tableModelForDbTable.setValueAt("foreign key", i, 3);

          i++;
        }
      }
    }
  }

  /**
   * load table for reference.
   */
  private void loadTableForReference() {
    tableModelForReference.setRowCount(0);

    var ctxTableList = Bucket.getInstance().getBucketTable().ctxTableList;

    var i = 0;
    for (var ctxTable : ctxTableList) {
      // reference foreign key
      for (var foreignKeyColumnList : ctxTable.ctxInnerForeignKeyList.stream()
          .map(c -> c.dbTableForeignKeyColumnList)
          .collect(Collectors.toList())) {
        var optReferenceForeignKey = foreignKeyColumnList.stream()
            .filter(c -> c.referenceDictColumnId.equals(dbDictColumn.dictColumnId))
            .findFirst();
        if (optReferenceForeignKey.isPresent()) {
          tableModelForReference.setRowCount(i + 1);

          var dbTableForeignKeyColumn = optReferenceForeignKey.get();
          for (var ctxInnerForeignKey : ctxTable.ctxInnerForeignKeyList) {
            if (ctxInnerForeignKey.dbTableForeignKey.seq == dbTableForeignKeyColumn.seq) {
              var ctxReferenceDbTable = Bucket.getInstance().getBucketTable().ctxTableList.stream()
                  .filter(c -> c.dbTable.tableId.equals(
                      ctxInnerForeignKey.dbTableForeignKey.referenceTableId))
                  .findFirst()
                  .get();

              tableModelForReference.setValueAt(ctxTable.dbTable.tableId, i, 0);
              tableModelForReference.setValueAt(ctxTable.dbTable.tableName, i, 1);
              tableModelForReference.setValueAt(ctxTable.dbTable.tableComment, i, 2);
              tableModelForReference.setValueAt(ctxReferenceDbTable.dbTable.tableId, i, 3);
              tableModelForReference.setValueAt(ctxReferenceDbTable.dbTable.tableName, i, 4);
              tableModelForReference.setValueAt(ctxReferenceDbTable.dbTable.tableComment, i, 5);

              i++;
            }
          }
        }
      }
    }
  }
}
