package com.walksocket.er.component.edit.dict.columns.root;

import com.walksocket.er.Log;
import com.walksocket.er.Size.DialogMedium;
import com.walksocket.er.Utils;
import com.walksocket.er.definition.AutoIncrement;
import com.walksocket.er.definition.Charset;
import com.walksocket.er.definition.Collate;
import com.walksocket.er.definition.NotNull;
import com.walksocket.er.definition.OnUpdate;
import com.walksocket.er.sqlite.Bucket;
import com.walksocket.er.sqlite.entity.DbDictColumn;
import com.walksocket.er.sqlite.tmp.TmpDictColumn;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
   * combo box column type.
   */
  private final JComboBox comboBoxColumnType;

  /**
   * label not null.
   */
  private final JLabel labelNotNull = new JLabel("Not null:");

  /**
   * combo box not null.
   */
  private final JComboBox comboBoxNotNull;

  /**
   * label charset.
   */
  private final JLabel labelCharset = new JLabel("Charset:");

  /**
   * combo box charset.
   */
  private final JComboBox comboBoxCharset;

  /**
   * label collate.
   */
  private final JLabel labelCollate = new JLabel("Collate:");

  /**
   * combo box collate.
   */
  private final JComboBox comboBoxCollate;

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
   * combo box on update.
   */
  private final JComboBox comboBoxOnUpdate;

  /**
   * label auto increment.
   */
  private final JLabel labelAutoIncrement = new JLabel("Auto increment:");

  /**
   * combo box auto increment.
   */
  private final JComboBox comboBoxAutoIncrement;

  /**
   * label option.
   */
  private final JLabel labelOption = new JLabel("Option:");

  /**
   * text field option.
   */
  private final JTextField textFieldOption = new JTextField(30);

  /**
   * button save.
   */
  private final JButton buttonSave = new JButton("Save");

  /**
   * button clear.
   */
  private final JButton buttonClear = new JButton("Clear");

  /**
   * button remove.
   */
  private final JButton buttonRemove = new JButton("Remove");

  /**
   * column name and width maps.
   */
  private static final Map<String, Integer> columnNameWidthMaps = new LinkedHashMap<>();

  static {
    // b -> required
    // i -> open dialog
    // u -> with dict
    // s -> show only
    columnNameWidthMaps.put("<html><s>Dict column id</s></html>", 200);
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
    columnNameWidthMaps.put("<html><i>Used</i></html>", 100);
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
   */
  public Form() {
    var form = this;

    // layout
    setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));

    // dict column id
    var panel1 = new JPanel();
    add(panel1);
    panel1.add(labelDictColumnId);
    textFieldDictColumnId.setEditable(false);
    panel1.add(textFieldDictColumnId);

    // column name
    var panel2 = new JPanel();
    add(panel2);
    panel2.add(labelColumnName);
    panel2.add(textFieldColumnName);

    // column comment
    var panel3 = new JPanel();
    add(panel3);
    panel3.add(labelColumnComment);
    panel3.add(textFieldColumnComment);

    // column type
    var panel4 = new JPanel();
    add(panel4);
    panel4.add(labelColumnType);
    var dbDictColumnTypeList = Bucket.getInstance().getBucketDict().dbDictColumnTypeList.stream()
        .sorted(Comparator.comparing(c -> c.seq))
        .map(c -> c.columnType)
        .collect(Collectors.toList());
    comboBoxColumnType = new JComboBox(
        new DefaultComboBoxModel(dbDictColumnTypeList.toArray()));
    panel4.add(comboBoxColumnType);

    // not null
    var panel5 = new JPanel();
    add(panel5);
    panel5.add(labelNotNull);
    comboBoxNotNull = new JComboBox(
        new DefaultComboBoxModel(NotNull.getNotNullListForColumn().toArray()));
    panel5.add(comboBoxNotNull);

    // charset
    var panel6 = new JPanel();
    add(panel6);
    panel6.add(labelCharset);
    comboBoxCharset = new JComboBox(
        new DefaultComboBoxModel(Charset.getCharsetListForColumn().toArray()));
    comboBoxCharset.setPreferredSize(new Dimension(150, comboBoxCharset.getFont().getSize() * 2));
    comboBoxCharset.setEditable(true);
    panel6.add(comboBoxCharset);

    // collate
    var panel7 = new JPanel();
    add(panel7);
    panel7.add(labelCollate);
    comboBoxCollate = new JComboBox(
        new DefaultComboBoxModel(Collate.getCollateListForColumn().toArray()));
    comboBoxCollate.setPreferredSize(new Dimension(150, comboBoxCollate.getFont().getSize() * 2));
    comboBoxCollate.setEditable(true);
    panel7.add(comboBoxCollate);

    // default
    var panel8 = new JPanel();
    add(panel8);
    panel8.add(labelDefault);
    panel8.add(textFieldDefault);

    // on update
    var panel9 = new JPanel();
    add(panel9);
    panel9.add(labelOnUpdate);
    comboBoxOnUpdate = new JComboBox(
        new DefaultComboBoxModel(OnUpdate.getOnUpdateListForColumn()
            .toArray()));
    panel9.add(comboBoxOnUpdate);

    // auto increment
    var panel10 = new JPanel();
    add(panel10);
    panel10.add(labelAutoIncrement);
    comboBoxAutoIncrement = new JComboBox(
        new DefaultComboBoxModel(AutoIncrement.getAutoIncrementForColumn()
            .toArray()));
    panel10.add(comboBoxAutoIncrement);

    // option
    var panel11 = new JPanel();
    add(panel11);
    panel11.add(labelOption);
    panel11.add(textFieldOption);

    // button
    var panelButton = new JPanel();
    add(panelButton);
    buttonSave.addActionListener(actionEvent -> {
      try {
        // tmp
        var tmpDictColumn = new TmpDictColumn();
        tmpDictColumn.dictColumnId = Utils.getString(textFieldDictColumnId);
        tmpDictColumn.columnName = Utils.getString(textFieldColumnName);
        tmpDictColumn.columnComment = Utils.getString(textFieldColumnComment);
        tmpDictColumn.columnType = Utils.getString(comboBoxColumnType);
        tmpDictColumn.notNullValue = Utils.getString(comboBoxNotNull);
        tmpDictColumn.charsetValue = Utils.getString(comboBoxCharset);
        tmpDictColumn.collateValue = Utils.getString(comboBoxCollate);
        tmpDictColumn.defaultValue = Utils.getString(textFieldDefault);
        tmpDictColumn.onUpdate = Utils.getString(comboBoxOnUpdate);
        tmpDictColumn.autoIncrementDefinition = Utils.getString(comboBoxAutoIncrement);
        tmpDictColumn.option = Utils.getString(textFieldOption);

        // check
        var tmpError = tmpDictColumn.checkAndGetError();
        if (!Utils.isNullOrEmpty(tmpError)) {
          throw new Exception(tmpError);
        }

        // save
        Bucket.getInstance().getBucketDict().saveDictColumn(tmpDictColumn);

        // load
        loadTable();

        // clear
        clear();

        // disabled
        buttonRemove.setEnabled(false);

      } catch (Exception e) {
        Log.error(e);
        JOptionPane.showMessageDialog(form, e.getMessage());
      }
    });
    panelButton.add(buttonSave);
    buttonClear.addActionListener(actionEvent -> {
      clear();
    });
    panelButton.add(buttonClear);
    buttonRemove.setEnabled(false);
    buttonRemove.addActionListener(actionEvent -> {
      // check
      var dictColumnId = Utils.getString(textFieldDictColumnId);
      if (Utils.isNullOrEmpty(dictColumnId)) {
        JOptionPane.showMessageDialog(form, "Already removed.");
        return;
      }

      try {
        // tmp
        var tmpDictColumn = new TmpDictColumn();
        tmpDictColumn.dictColumnId = dictColumnId;

        // save
        Bucket.getInstance().getBucketDict().removeDictColumn(tmpDictColumn);

        // load
        loadTable();

        // clear
        clear();

        // disabled
        buttonRemove.setEnabled(false);

      } catch (Exception e) {
        Log.error(e);
        JOptionPane.showMessageDialog(form, e.getMessage());
      }
    });
    panelButton.add(buttonRemove);

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
    table.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        Point pt = e.getPoint();
        int r = table.rowAtPoint(pt);
        if (r >= 0) {
          textFieldDictColumnId.setText(Utils.getString(table.getValueAt(r, 0)));
          textFieldColumnName.setText(Utils.getString(table.getValueAt(r, 1)));
          textFieldColumnComment.setText(Utils.getString(table.getValueAt(r, 2)));
          comboBoxColumnType.setSelectedItem(Utils.getString(table.getValueAt(r, 3)));
          comboBoxNotNull.setSelectedItem(Utils.getString(table.getValueAt(r, 4)));
          comboBoxCharset.setSelectedItem(Utils.getString(table.getValueAt(r, 5)));
          comboBoxCollate.setSelectedItem(Utils.getString(table.getValueAt(r, 6)));
          textFieldDefault.setText(Utils.getString(table.getValueAt(r, 7)));
          comboBoxOnUpdate.setSelectedItem(Utils.getString(table.getValueAt(r, 8)));
          comboBoxAutoIncrement.setSelectedItem(Utils.getString(table.getValueAt(r, 9)));
          textFieldOption.setText(Utils.getString(table.getValueAt(r, 10)));

          buttonRemove.setEnabled(false);
          var used = Utils.getString(table.getValueAt(r, 11));
          if (!used.equals("yes")) {
            buttonRemove.setEnabled(true);
          }
        }
      }
    });
    var sp = new JScrollPane(table);
    sp.setPreferredSize(new Dimension(DialogMedium.WIDTH - 40, DialogMedium.HEIGHT / 10 * 6));
    panelTable.add(sp);

    // load
    loadTable();
  }

  /**
   * load table.
   */
  private void loadTable() {
    tableModel.setRowCount(0);

    var ctxTableList = Bucket.getInstance().getBucketTable().ctxTableList;

    var dbDictColumnTypeList = Bucket.getInstance().getBucketDict().dbDictColumnTypeList;
    var dbDictColumnList = Bucket.getInstance().getBucketDict().dbDictColumnList;
    var dbDictGroupList = Bucket.getInstance().getBucketDict().dbDictGroupList;
    var dbDictGroupColumnList = Bucket.getInstance().getBucketDict().dbDictGroupColumnList;

    var i = 0;
    for (var dbDictColumn : dbDictColumnList.stream()
        .sorted(Comparator.comparing(DbDictColumn::getColumnNameForSort)
            .thenComparing(DbDictColumn::getColumnCommentForSort))
        .collect(Collectors.toList())) {
      tableModel.setRowCount(i + 1);

      table.setValueAt(dbDictColumn.dictColumnId, i, 0);
      table.setValueAt(dbDictColumn.columnName, i, 1);
      table.setValueAt(dbDictColumn.columnComment, i, 2);

      // column type
      table.setValueAt("", i, 3);
      var opt = dbDictColumnTypeList.stream()
          .filter(d -> d.dictColumnTypeId.equals(dbDictColumn.dictColumnTypeId))
          .findFirst();
      if (opt.isPresent()) {
        table.setValueAt(opt.get().columnType, i, 3);
      }

      table.setValueAt(dbDictColumn.notNullValue, i, 4);
      table.setValueAt(dbDictColumn.charsetValue, i, 5);
      table.setValueAt(dbDictColumn.collateValue, i, 6);
      table.setValueAt(dbDictColumn.defaultValue, i, 7);
      table.setValueAt(dbDictColumn.onUpdate, i, 8);
      table.setValueAt(dbDictColumn.autoIncrementDefinition, i, 9);
      table.setValueAt(dbDictColumn.option, i, 10);

      // used
      table.setValueAt("", i, 11);
      var found = false;
      if (!found) {
        // dict group column
        var o = dbDictGroupColumnList.stream()
            .filter(c -> c.dictColumnId.equals(dbDictColumn.dictColumnId))
            .findFirst();
        if (o.isPresent()) {
          table.setValueAt("yes", i, 11);
          found = true;
        }
      }
      if (!found) {
        for (var ctxTable : ctxTableList) {
          // table column
          var optColumn = ctxTable.dbTableColumnList.stream()
              .filter(c -> c.dictColumnId.equals(dbDictColumn.dictColumnId))
              .findFirst();
          if (optColumn.isPresent()) {
            table.setValueAt("yes", i, 11);
            found = true;
            break;
          }

          // primary
          var optPrimaryKey = ctxTable.ctxInnerPrimaryKey.dbTablePrimaryKeyColumnList.stream()
              .filter(c -> c.dictColumnId.equals(dbDictColumn.dictColumnId))
              .findFirst();
          if (optPrimaryKey.isPresent()) {
            table.setValueAt("yes", i, 11);
            found = true;
            break;
          }

          // unique key
          for (var uniqueKeyColumnList : ctxTable.ctxInnerUniqueKeyList.stream()
              .map(c -> c.dbTableUniqueKeyColumnList)
              .collect(Collectors.toList())) {
            var optUniqueKey = uniqueKeyColumnList.stream()
                .filter(c -> c.dictColumnId.equals(dbDictColumn.dictColumnId))
                .findFirst();
            if (optUniqueKey.isPresent()) {
              table.setValueAt("yes", i, 11);
              found = true;
              break;
            }
          }
          if (found) {
            break;
          }

          // key
          for (var keyColumnList : ctxTable.ctxInnerKeyList.stream()
              .map(c -> c.dbTableKeyColumnList)
              .collect(Collectors.toList())) {
            var optKey = keyColumnList.stream()
                .filter(c -> c.dictColumnId.equals(dbDictColumn.dictColumnId))
                .findFirst();
            if (optKey.isPresent()) {
              table.setValueAt("yes", i, 11);
              found = true;
              break;
            }
          }
          if (found) {
            break;
          }

          // foreign key
          for (var foreignKeyColumnList : ctxTable.ctxInnerForeignKeyList.stream()
              .map(c -> c.dbTableForeignKeyColumnList)
              .collect(Collectors.toList())) {
            var optForeignKey = foreignKeyColumnList.stream()
                .filter(c -> c.dictColumnId.equals(dbDictColumn.dictColumnId))
                .findFirst();
            if (optForeignKey.isPresent()) {
              table.setValueAt("yes", i, 11);
              found = true;
              break;
            }
            var optReferenceForeignKey = foreignKeyColumnList.stream()
                .filter(c -> c.referenceDictColumnId.equals(dbDictColumn.dictColumnId))
                .findFirst();
            if (optReferenceForeignKey.isPresent()) {
              table.setValueAt("yes", i, 11);
              found = true;
              break;
            }
          }
          if (found) {
            break;
          }
        }
      }

      i++;
    }
  }

  /**
   * clear.
   */
  private void clear() {
    textFieldDictColumnId.setText("");
    textFieldColumnName.setText("");
    textFieldColumnComment.setText("");
    comboBoxColumnType.setSelectedIndex(0);
    comboBoxNotNull.setSelectedIndex(0);
    comboBoxCharset.setSelectedIndex(0);
    comboBoxCollate.setSelectedIndex(0);
    textFieldDefault.setText("");
    comboBoxOnUpdate.setSelectedIndex(0);
    comboBoxAutoIncrement.setSelectedIndex(0);
    textFieldOption.setText("");

    buttonRemove.setEnabled(false);
  }
}
