package com.walksocket.er.component.used.dict.columns.root;

import com.walksocket.er.Size.DialogSmall;
import com.walksocket.er.sqlite.Bucket;
import com.walksocket.er.sqlite.entity.DbDictColumn;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.LinkedHashMap;
import java.util.Map;
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
  private final JTextField textFieldOption = new JTextField(60);

  /**
   * db dict column.
   */
  private final DbDictColumn dbDictColumn;

  /**
   * column name and width maps.
   */
  private static final Map<String, Integer> columnNameWidthMaps = new LinkedHashMap<>();

  static {
    // b -> required
    // i -> open dialog
    // u -> with dict
    // s -> show only
    columnNameWidthMaps.put("<html><s>Table id</s></html>", 200);
    columnNameWidthMaps.put("<html><s>Table name</s></html>", 200);
    columnNameWidthMaps.put("<html><s>Table comment</s></html>", 200);
    columnNameWidthMaps.put("<html><s>At</s></html>", 200);
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

    // table
    var panelTable = new JPanel();
    add(panelTable);

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

    var sp = new JScrollPane(table);
    sp.setPreferredSize(new Dimension(DialogSmall.WIDTH - 40, DialogSmall.HEIGHT / 20 * 9));
    panelTable.add(sp);

    // load
    loadTable();
  }

  /**
   * load table.
   */
  private void loadTable() {
    tableModel.setRowCount(0);
//
//    var dbDictColumnList = Bucket.getInstance().getBucketDict().dbDictColumnList.stream()
//        .filter(d -> d.dictColumnTypeId.equals(dbDictColumnType.dictColumnTypeId))
//        .collect(Collectors.toList());
//
//    var i = 0;
//    for (var dbDictColumn : dbDictColumnList) {
//      tableModel.setRowCount(i + 1);
//
//      table.setValueAt(dbDictColumn.dictColumnTypeId, i, 0);
//      table.setValueAt(dbDictColumn.columnName, i, 1);
//      table.setValueAt(dbDictColumn.columnComment, i, 2);
//
//      i++;
//    }
  }
}
