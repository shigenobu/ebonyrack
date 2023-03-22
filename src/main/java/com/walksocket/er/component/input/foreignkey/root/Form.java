package com.walksocket.er.component.input.foreignkey.root;

import com.walksocket.er.Size.DialogMedium;
import com.walksocket.er.Utils;
import com.walksocket.er.custom.ErUnderlineBorder;
import com.walksocket.er.parts.ColumnForeignKeyOption;
import com.walksocket.er.sqlite.Bucket;
import com.walksocket.er.sqlite.Tmp;
import com.walksocket.er.sqlite.entity.DbTable;
import com.walksocket.er.sqlite.tmp.TmpColumn;
import com.walksocket.er.sqlite.tmp.TmpForeignKey;
import com.walksocket.er.sqlite.tmp.TmpKey;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
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
    // b -> required
    // i -> open dialog
    // u -> with dict
    // s -> show only
    columnNameWidthMaps.put("<html><s>Column name</s></html>", 200);
    columnNameWidthMaps.put("<html><s>Column comment</s></html>", 200);
    columnNameWidthMaps.put("<html><s>Column type</s></html>", 200);
    columnNameWidthMaps.put("<html><s>Not null</s></html>", 200);
    columnNameWidthMaps.put("<html><s>First key</s></html>", 100);
    columnNameWidthMaps.put("<html><b>Seq in index</b></html>", 100);
  }

  /**
   * tmp foreign key.
   */
  private final TmpForeignKey tmpForeignKey;

  /**
   * tmp column list.
   */
  private final List<TmpColumn> tmpColumnList;

  /**
   * valid tmp primary key.
   */
  private final TmpKey validTmpPrimaryKey;

  /**
   * valid tmp unique key list.
   */
  private final List<TmpKey> validTmpUniqueKeyList;

  /**
   * valid tmp key list.
   */
  private final List<TmpKey> validTmpKeyList;

  /**
   * db table list.
   */
  private final List<DbTable> dbTableList;

  /**
   * table.
   */
  private final JTable table;

  /**
   * table model.
   */
  private final DefaultTableModel tableModel;

  /**
   * label reference table.
   */
  private final JLabel labelReferenceTable = new JLabel("Reference table:");

  /**
   * combo box reference table.
   */
  private final JComboBox comboBoxReferenceTable;

  /**
   * table reference.
   */
  private final JTable tableReference;

  /**
   * table model reference.
   */
  private final DefaultTableModel tableModelReference;

  /**
   * Constructor.
   *
   * @param tmpForeignKey         tmpForeignKey
   * @param tmpColumnList         tmpColumnList
   * @param validTmpPrimaryKey    validTmpPrimaryKey
   * @param validTmpUniqueKeyList validTmpUniqueKeyList
   * @param validTmpKeyList       validTmpKeyList
   * @param dbTableList           dbTableList
   */
  public Form(TmpForeignKey tmpForeignKey, List<TmpColumn> tmpColumnList,
      TmpKey validTmpPrimaryKey,
      List<TmpKey> validTmpUniqueKeyList,
      List<TmpKey> validTmpKeyList,
      List<DbTable> dbTableList) {
    this.tmpForeignKey = tmpForeignKey;
    this.tmpColumnList = tmpColumnList;
    this.validTmpPrimaryKey = validTmpPrimaryKey;
    this.validTmpUniqueKeyList = validTmpUniqueKeyList;
    this.validTmpKeyList = validTmpKeyList;
    this.dbTableList = dbTableList;

    // layout
    setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));

    // ----------------------------------------
    // panel - table
    var panel1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
    add(panel1);

    var rowCount = tmpColumnList.size();
    var columnNames = columnNameWidthMaps.keySet().toArray();
    tableModel = new DefaultTableModel(columnNames, rowCount) {
      @Override
      public boolean isCellEditable(int row, int column) {
        return column >= 5;
      }
    };

    var range = new ArrayList<String>();
    range.add("");
    range.addAll(IntStream.rangeClosed(1, rowCount).boxed().map(String::valueOf)
        .collect(Collectors.toList()));

    var widthList = columnNameWidthMaps.values().toArray(new Integer[columnNameWidthMaps.size()]);
    table = new JTable(tableModel);
    table.putClientProperty("terminateEditOnFocusLost", true);
    table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
    for (int i = 0; i < table.getColumnModel().getColumnCount(); i++) {
      var tc = table.getColumnModel().getColumn(i);
      tc.setPreferredWidth(widthList[i]);

      // seq in index
      if (i == 5) {
        var comboBoxSeqInIndex = new JComboBox(new DefaultComboBoxModel(range.toArray()));
        tc.setCellEditor(new DefaultCellEditor(comboBoxSeqInIndex));
      }
    }
    var sp = new JScrollPane(table);
    sp.setPreferredSize(new Dimension(DialogMedium.WIDTH - 40, DialogMedium.HEIGHT / 10 * 3));
    panel1.add(sp);

    // tmp column list, tmpForeignKey
    for (int i = 0; i < tmpColumnList.size(); i++) {
      var tmpColumn = tmpColumnList.get(i);

      table.setValueAt(tmpColumn.columnName, i, 0);
      table.setValueAt(tmpColumn.columnComment, i, 1);
      table.setValueAt(tmpColumn.columnType, i, 2);
      table.setValueAt(tmpColumn.notNullValue, i, 3);

      // contains first key
      var builder = new StringBuilder();
      if (validTmpPrimaryKey != null) {
        var opt = validTmpPrimaryKey.columnKeyOptionList.stream()
            .filter(c -> c.columnName.equals(tmpColumn.columnName) && c.seqInIndex.equals("1"))
            .findFirst();
        if (opt.isPresent()) {
          builder.append("[PK]");
        }
      }
      if (validTmpUniqueKeyList.size() > 0) {
        for (var t : validTmpUniqueKeyList) {
          var opt = t.columnKeyOptionList.stream()
              .filter(c -> c.columnName.equals(tmpColumn.columnName) && c.seqInIndex.equals("1"))
              .findFirst();
          if (opt.isPresent()) {
            builder.append("[UK]");
            break;
          }
        }
      }
      if (validTmpKeyList.size() > 0) {
        for (var t : validTmpKeyList) {
          var opt = t.columnKeyOptionList.stream()
              .filter(c -> c.columnName.equals(tmpColumn.columnName) && c.seqInIndex.equals("1"))
              .findFirst();
          if (opt.isPresent()) {
            builder.append("[K]");
            break;
          }
        }
      }
      table.setValueAt(builder.toString(), i, 4);

      var optColumnName = tmpForeignKey.columnForeignKeyOptionList
          .stream()
          .filter(c -> c.columnName.equals(tmpColumn.columnName))
          .findFirst();
      if (optColumnName.isPresent()) {
        table.setValueAt(optColumnName.get().seqInIndex, i, 5);
      }
    }

    // empty panel
    var panelEmpty = new JPanel(new FlowLayout(FlowLayout.LEFT));
    panelEmpty.setBorder(new ErUnderlineBorder());
    panelEmpty.setPreferredSize(new Dimension(DialogMedium.WIDTH, DialogMedium.HEIGHT / 20));
    add(panelEmpty);

    // ----------------------------------------
    // panel
    var panel11 = new JPanel(new FlowLayout(FlowLayout.LEFT));
    add(panel11);
    panel11.add(labelReferenceTable);
    var showTableList = new ArrayList<String>();
    showTableList.add("");
    showTableList.addAll(
        dbTableList.stream().map(d -> d.getShowTableName()).collect(Collectors.toList()));
    comboBoxReferenceTable = new JComboBox(showTableList.toArray());
    comboBoxReferenceTable.addActionListener(actionEvent -> {
      loadTableReference();
    });
    panel11.add(comboBoxReferenceTable);

    // panel - reference table
    var panel12 = new JPanel(new FlowLayout(FlowLayout.LEFT));
    add(panel12);

    var rowCountReference = 0;
    var columnNamesReference = columnNameWidthMaps.keySet().toArray();
    tableModelReference = new DefaultTableModel(columnNamesReference, rowCountReference) {
      @Override
      public boolean isCellEditable(int row, int column) {
        return column >= 5;
      }
    };

    var widthListReference = columnNameWidthMaps.values()
        .toArray(new Integer[columnNameWidthMaps.size()]);
    tableReference = new JTable(tableModelReference);
    tableReference.putClientProperty("terminateEditOnFocusLost", true);
    tableReference.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
    for (int i = 0; i < tableReference.getColumnModel().getColumnCount(); i++) {
      var tc = tableReference.getColumnModel().getColumn(i);
      tc.setPreferredWidth(widthListReference[i]);
    }
    var spReference = new JScrollPane(tableReference);
    spReference.setPreferredSize(
        new Dimension(DialogMedium.WIDTH - 40, DialogMedium.HEIGHT / 10 * 3));
    panel12.add(spReference);

    // set
    if (!Utils.isNullOrEmpty(tmpForeignKey.referenceTableName)) {
      var dt = dbTableList.stream()
          .filter(d -> tmpForeignKey.referenceTableName.startsWith(d.tableName))
          .findFirst()
          .get();
      comboBoxReferenceTable.setSelectedItem(dt.getShowTableName());
    }

    // load
    loadTableReference();
  }

  /**
   * load table reference.
   */
  private void loadTableReference() {
    tableModelReference.setRowCount(0);

    var referenceTable = Utils.getString(comboBoxReferenceTable.getSelectedItem());
    if (Utils.isNullOrEmpty(referenceTable)) {
      return;
    }

    // ----------
    // same table
    if (referenceTable.startsWith("(*)")) {

      var range = new ArrayList<String>();
      range.add("");
      range.addAll(IntStream.rangeClosed(1, tmpColumnList.size()).boxed().map(String::valueOf)
          .collect(Collectors.toList()));

      var tc = tableReference.getColumnModel().getColumn(5);
      var comboBoxSeqInIndex = new JComboBox(new DefaultComboBoxModel(range.toArray()));
      tc.setCellEditor(new DefaultCellEditor(comboBoxSeqInIndex));

      for (int i = 0; i < tmpColumnList.size(); i++) {
        tableModelReference.setRowCount(i + 1);

        var tmpColumn = tmpColumnList.get(i);
        tableReference.setValueAt(tmpColumn.columnName, i, 0);
        tableReference.setValueAt(tmpColumn.columnComment, i, 1);
        tableReference.setValueAt(tmpColumn.columnType, i, 2);
        tableReference.setValueAt(tmpColumn.notNullValue, i, 3);

        // contains first key
        var builder = new StringBuilder();
        if (validTmpPrimaryKey != null) {
          var opt = validTmpPrimaryKey.columnKeyOptionList.stream()
              .filter(c -> c.columnName.equals(tmpColumn.columnName) && c.seqInIndex.equals("1"))
              .findFirst();
          if (opt.isPresent()) {
            builder.append("[PK]");
          }
        }
        if (validTmpUniqueKeyList.size() > 0) {
          for (var t : validTmpUniqueKeyList) {
            var opt = t.columnKeyOptionList.stream()
                .filter(c -> c.columnName.equals(tmpColumn.columnName) && c.seqInIndex.equals("1"))
                .findFirst();
            if (opt.isPresent()) {
              builder.append("[UK]");
              break;
            }
          }
        }
        if (validTmpKeyList.size() > 0) {
          for (var t : validTmpKeyList) {
            var opt = t.columnKeyOptionList.stream()
                .filter(c -> c.columnName.equals(tmpColumn.columnName) && c.seqInIndex.equals("1"))
                .findFirst();
            if (opt.isPresent()) {
              builder.append("[K]");
              break;
            }
          }
        }
        tableReference.setValueAt(builder.toString(), i, 4);

        var opt = tmpForeignKey.referenceColumnForeignKeyOptionList.stream()
            .filter(c -> c.columnName.equals(tmpColumn.columnName))
            .findFirst();
        if (opt.isPresent()) {
          tableReference.setValueAt(opt.get().seqInIndex, i, 5);
        }
      }
      return;
    }

    // ----------
    // different table
    var opt = dbTableList.stream()
        .filter(d -> d.getShowTableName().equals(referenceTable))
        .findFirst();
    if (!opt.isPresent()) {
      return;
    }
    var referenceTableId = opt.get().tableId;

    // column
    var referenceDbTable = Bucket.getInstance().getBucketTable().ctxTableList.stream()
        .filter(c -> c.dbTable.tableId.equals(referenceTableId))
        .findFirst()
        .get();
    var referenceDbTableColumnList = referenceDbTable.dbTableColumnList;

    var dbDictColumnTypeList = Bucket.getInstance().getBucketDict().dbDictColumnTypeList;
    var dbDictColumnList = Bucket.getInstance().getBucketDict().dbDictColumnList;
    var dbDictGroupList = Bucket.getInstance().getBucketDict().dbDictGroupList;
    var dbDictGroupColumnList = Bucket.getInstance().getBucketDict().dbDictGroupColumnList;

    var referenceTmpColumnList = Tmp.createTmpColumnList(referenceDbTableColumnList,
        dbDictColumnTypeList, dbDictColumnList);

    // group column
    var optGroup = Bucket.getInstance().getBucketTable().ctxTableList.stream()
        .map(c -> c.dbTableGroup)
        .filter(c -> c != null)
        .filter(c -> c.tableId.equals(referenceTableId))
        .findFirst();
    if (optGroup.isPresent()) {
      referenceTmpColumnList.addAll(Tmp.createTmpGroupColumnList(
          optGroup.get(), dbDictColumnTypeList, dbDictColumnList, dbDictGroupList,
          dbDictGroupColumnList));
    }

    var range = new ArrayList<String>();
    range.add("");
    range.addAll(IntStream.rangeClosed(1, tmpColumnList.size()).boxed().map(String::valueOf)
        .collect(Collectors.toList()));

    var tc = tableReference.getColumnModel().getColumn(5);
    var comboBoxSeqInIndex = new JComboBox(new DefaultComboBoxModel(range.toArray()));
    tc.setCellEditor(new DefaultCellEditor(comboBoxSeqInIndex));

    // valid tmp primary key
    TmpKey vTmpPrimaryKey = null;
    var optPrimaryKey = Bucket.getInstance().getBucketTable().ctxTableList.stream()
        .filter(c -> c.dbTable.tableId.equals(referenceTableId))
        .map(c -> c.ctxInnerPrimaryKey)
        .findFirst();
    if (optPrimaryKey.isPresent()) {
      var dbTablePrimaryKey = optPrimaryKey.get().dbTablePrimaryKey;
      var dbTablePrimaryKeyColumnList = optPrimaryKey.get().dbTablePrimaryKeyColumnList;

      var t = Tmp.createTmpKey(dbTablePrimaryKey, dbTablePrimaryKeyColumnList, dbDictColumnList);
      if (t.columnKeyOptionList.size() > 0) {
        vTmpPrimaryKey = t;
      }
    }

    // valid tmp unique key list
    List<TmpKey> vTmpUniqueKeyList = new ArrayList<>();
    var optUniqueKey = Bucket.getInstance().getBucketTable().ctxTableList.stream()
        .filter(c -> c.dbTable.tableId.equals(referenceTableId))
        .map(c -> c.ctxInnerUniqueKeyList)
        .findFirst();
    if (optUniqueKey.isPresent()) {
      for (var ctx : optUniqueKey.get()) {
        var dbTableUniqueKey = ctx.dbTableUniqueKey;
        var dbTableUniqueKeyColumnList = ctx.dbTableUniqueKeyColumnList;

        var t = Tmp.createTmpKey(dbTableUniqueKey, dbTableUniqueKeyColumnList, dbDictColumnList);
        if (t.columnKeyOptionList.size() > 0) {
          vTmpUniqueKeyList.add(t);
        }
      }
    }

    // valid tmp key list
    List<TmpKey> vTmpKeyList = new ArrayList<>();
    var optKey = Bucket.getInstance().getBucketTable().ctxTableList.stream()
        .filter(c -> c.dbTable.tableId.equals(referenceTableId))
        .map(c -> c.ctxInnerKeyList)
        .findFirst();
    if (optKey.isPresent()) {
      for (var ctx : optKey.get()) {
        var dbTableKey = ctx.dbTableKey;
        var dbTableKeyColumnList = ctx.dbTableKeyColumnList;

        var t = Tmp.createTmpKey(dbTableKey, dbTableKeyColumnList, dbDictColumnList);
        if (t.columnKeyOptionList.size() > 0) {
          vTmpKeyList.add(t);
        }
      }
    }

    for (int i = 0; i < referenceTmpColumnList.size(); i++) {
      tableModelReference.setRowCount(i + 1);

      var referenceTmpColumn = referenceTmpColumnList.get(i);
      tableReference.setValueAt(referenceTmpColumn.columnName, i, 0);
      tableReference.setValueAt(referenceTmpColumn.columnComment, i, 1);
      tableReference.setValueAt(referenceTmpColumn.columnType, i, 2);
      tableReference.setValueAt(referenceTmpColumn.notNullValue, i, 3);

      // contains first key
      var builder = new StringBuilder();
      if (vTmpPrimaryKey != null) {
        var o = vTmpPrimaryKey.columnKeyOptionList.stream()
            .filter(
                c -> c.columnName.equals(referenceTmpColumn.columnName) && c.seqInIndex.equals("1"))
            .findFirst();
        if (o.isPresent()) {
          builder.append("[PK]");
        }
      }
      if (vTmpUniqueKeyList.size() > 0) {
        for (var t : vTmpUniqueKeyList) {
          var o = t.columnKeyOptionList.stream()
              .filter(
                  c -> c.columnName.equals(referenceTmpColumn.columnName) && c.seqInIndex.equals(
                      "1"))
              .findFirst();
          if (o.isPresent()) {
            builder.append("[UK]");
            break;
          }
        }
      }
      if (vTmpKeyList.size() > 0) {
        for (var t : vTmpKeyList) {
          var o = t.columnKeyOptionList.stream()
              .filter(
                  c -> c.columnName.equals(referenceTmpColumn.columnName) && c.seqInIndex.equals(
                      "1"))
              .findFirst();
          if (o.isPresent()) {
            builder.append("[K]");
            break;
          }
        }
      }
      tableReference.setValueAt(builder.toString(), i, 4);
      var o = tmpForeignKey.referenceColumnForeignKeyOptionList.stream()
          .filter(c -> c.columnName.equals(referenceTmpColumn.columnName))
          .findFirst();
      if (o.isPresent()) {
        tableReference.setValueAt(o.get().seqInIndex, i, 5);
      }
    }
  }

  /**
   * pack tmp foreign key.
   */
  public void packTmpForeignKey() {
    var forceClear = false;

    // -----
    // self
    Map<String, String> columnNameWithFirstKey = new HashMap<>();
    var columnForeignKeyOptionList = new ArrayList<ColumnForeignKeyOption>();
    for (int i = 0; i < tmpColumnList.size(); i++) {
      var columnName = Utils.getString(table.getValueAt(i, 0));

      var seqInIndex = Utils.getString(table.getValueAt(i, 5));
      if (!Utils.isNumber(seqInIndex)) {
        continue;
      }

      // key
      var firstKey = Utils.getString(table.getValueAt(i, 4));
      columnNameWithFirstKey.put(columnName, firstKey);

      var columnForeignKeyOption = new ColumnForeignKeyOption();
      columnForeignKeyOption.columnName = columnName;
      columnForeignKeyOption.seqInIndex = seqInIndex;

      columnForeignKeyOptionList.add(columnForeignKeyOption);
    }

    // first key check
    if (columnForeignKeyOptionList.size() > 0) {
      var cf = columnForeignKeyOptionList.stream()
          .sorted(Comparator.comparing(t -> Integer.parseInt(t.seqInIndex)))
          .sorted(Comparator.comparing(t -> t.columnName))
          .findFirst()
          .get();
      var firstKey = columnNameWithFirstKey.get(cf.columnName);
      if (Utils.isNullOrEmpty(firstKey)) {
        // force clear
        forceClear = true;
      }
    }

    // other
    var referenceTable = Utils.getString(comboBoxReferenceTable.getSelectedItem());
    if (Utils.isNullOrEmpty(referenceTable)) {
      return;
    }
    var opt = dbTableList.stream()
        .filter(d -> d.getShowTableName().equals(referenceTable))
        .findFirst();
    if (!opt.isPresent()) {
      return;
    }
    var referenceTableName = opt.get().tableName;

    Map<String, String> referenceColumnNameWithFirstKey = new HashMap<>();
    var referenceColumnForeignKeyOptionList = new ArrayList<ColumnForeignKeyOption>();
    for (int i = 0; i < tableReference.getRowCount(); i++) {
      var columnName = Utils.getString(tableReference.getValueAt(i, 0));

      var seqInIndex = Utils.getString(tableReference.getValueAt(i, 5));
      if (!Utils.isNumber(seqInIndex)) {
        continue;
      }

      // key
      var firstKey = Utils.getString(tableReference.getValueAt(i, 4));
      referenceColumnNameWithFirstKey.put(columnName, firstKey);

      var referenceColumnForeignKeyOption = new ColumnForeignKeyOption();
      referenceColumnForeignKeyOption.columnName = columnName;
      referenceColumnForeignKeyOption.seqInIndex = seqInIndex;

      referenceColumnForeignKeyOptionList.add(referenceColumnForeignKeyOption);
    }

    // first key check
    if (referenceColumnForeignKeyOptionList.size() > 0) {
      var cf = referenceColumnForeignKeyOptionList.stream()
          .sorted(Comparator.comparing(t -> Integer.parseInt(t.seqInIndex)))
          .sorted(Comparator.comparing(t -> t.columnName))
          .findFirst()
          .get();
      var firstKey = referenceColumnNameWithFirstKey.get(cf.columnName);
      if (Utils.isNullOrEmpty(firstKey)) {
        // force clear
        forceClear = true;
      }
    }

    // -----
    // self
    var sortedColumnForeignKeyOptionList = columnForeignKeyOptionList.stream()
        .sorted(
            Comparator.comparing(t -> Integer.parseInt(t.seqInIndex)))// TODO seqInIndex, columnName
        .collect(Collectors.toList());
    for (int i = 0; i < sortedColumnForeignKeyOptionList.size(); i++) {
      var c = sortedColumnForeignKeyOptionList.get(i);
      c.seqInIndex = String.valueOf(i + 1);
    }
    tmpForeignKey.columnForeignKeyOptionList = sortedColumnForeignKeyOptionList;

    // other
    tmpForeignKey.referenceTableName = referenceTableName;

    var sortedReferenceColumnForeignKeyOptionList = referenceColumnForeignKeyOptionList.stream()
        .sorted(
            Comparator.comparing(t -> Integer.parseInt(t.seqInIndex)))// TODO seqInIndex, columnName
        .collect(Collectors.toList());
    for (int i = 0; i < sortedReferenceColumnForeignKeyOptionList.size(); i++) {
      var c = sortedReferenceColumnForeignKeyOptionList.get(i);
      c.seqInIndex = String.valueOf(i + 1);
    }
    tmpForeignKey.referenceColumnForeignKeyOptionList = sortedReferenceColumnForeignKeyOptionList;

    // ---
    if (forceClear) {
      tmpForeignKey.columnForeignKeyOptionList.clear();
      tmpForeignKey.referenceTableName = "";
      tmpForeignKey.referenceColumnForeignKeyOptionList.clear();
    }
  }
}
