package com.walksocket.er.component.input.table.root.form.column;

import com.walksocket.er.Size.DialogLarge;
import com.walksocket.er.Utils;
import com.walksocket.er.custom.ErHeaderFormatter;
import com.walksocket.er.custom.ErHeaderFormatter.Type;
import com.walksocket.er.sqlite.Bucket;
import com.walksocket.er.sqlite.context.CtxTable;
import com.walksocket.er.sqlite.tmp.TmpColumn;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 * Group.
 */
public class Group extends JPanel {

  /**
   * column name and width maps.
   */
  private static final Map<String, Integer> columnNameWidthMaps = new LinkedHashMap<>();

  static {
    columnNameWidthMaps.put(ErHeaderFormatter.format("Column name", Type.showOnly), 200);
    columnNameWidthMaps.put(ErHeaderFormatter.format("Column comment", Type.showOnly), 200);
    columnNameWidthMaps.put(ErHeaderFormatter.format("Column type", Type.showOnly), 200);
    columnNameWidthMaps.put(ErHeaderFormatter.format("Not null", Type.showOnly), 200);
    columnNameWidthMaps.put(ErHeaderFormatter.format("Charset", Type.showOnly), 100);
    columnNameWidthMaps.put(ErHeaderFormatter.format("Collate", Type.showOnly), 100);
    columnNameWidthMaps.put(ErHeaderFormatter.format("Default", Type.showOnly), 100);
    columnNameWidthMaps.put(ErHeaderFormatter.format("On update", Type.showOnly), 100);
    columnNameWidthMaps.put(ErHeaderFormatter.format("Auto increment", Type.showOnly), 100);
    columnNameWidthMaps.put(ErHeaderFormatter.format("Option", Type.showOnly), 100);
  }

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
   * label group.
   */
  private final JLabel labelGroup = new JLabel("Group:");

  /**
   * combo box group name.
   */
  private final JComboBox comboBoxGroupName;

  /**
   * Constructor.
   *
   * @param ctxTable ctxTable
   */
  public Group(CtxTable ctxTable) {
    this.ctxTable = ctxTable;

    var dbDictGroupList = Bucket.getInstance().getBucketDict().dbDictGroupList;

    // layout
    setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
    var group = this;

    // panel - group
    var panel1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
    add(panel1);
    panel1.add(labelGroup);
    var groups = new ArrayList<String>();
    groups.add("");
    groups.addAll(dbDictGroupList.stream()
        .map(g -> g.groupName)
        .collect(Collectors.toList()));
    comboBoxGroupName = new JComboBox(groups.toArray());
    comboBoxGroupName.addActionListener(actionEvent -> {
      loadTable();
    });
    panel1.add(comboBoxGroupName);

    // panel - table
    var panel2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
    add(panel2);

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
    sp.setPreferredSize(new Dimension(DialogLarge.WIDTH - 40, DialogLarge.HEIGHT / 40 * 10));
    panel2.add(sp);

    // set
    if (ctxTable.dbTableGroup != null) {
      var groupName = dbDictGroupList.stream()
          .filter(d -> d.dictGroupId.equals(ctxTable.dbTableGroup.dictGroupId))
          .findFirst()
          .get()
          .groupName;
      comboBoxGroupName.setSelectedItem(groupName);
    }

    // load
    loadTable();
  }

  /**
   * load table.
   */
  private void loadTable() {
    tableModel.setRowCount(0);

    var groupName = getGroupName();
    if (Utils.isNullOrEmpty(groupName)) {
      return;
    }

    var dbDictColumnTypeList = Bucket.getInstance().getBucketDict().dbDictColumnTypeList;
    var dbDictColumnList = Bucket.getInstance().getBucketDict().dbDictColumnList;
    var dbDictGroupList = Bucket.getInstance().getBucketDict().dbDictGroupList;
    var dbDictGroupColumnList = Bucket.getInstance().getBucketDict().dbDictGroupColumnList;

    var opt = dbDictGroupList.stream()
        .filter(g -> g.groupName.equals(groupName))
        .findFirst();
    if (!opt.isPresent()) {
      return;
    }
    var dbDictGroup = opt.get();

    var i = 0;
    for (var dbDictGroupColumn : dbDictGroupColumnList.stream()
        .filter(c -> c.dictGroupId.equals(dbDictGroup.dictGroupId))
        .sorted(Comparator.comparing(c -> c.seq))
        .collect(Collectors.toList())) {
      var dictColumnId = dbDictGroupColumn.dictColumnId;

      var o = dbDictColumnList.stream()
          .filter(d -> d.dictColumnId.equals(dictColumnId))
          .findFirst();
      if (!o.isPresent()) {
        continue;
      }

      tableModel.setRowCount(i + 1);
      table.setValueAt(o.get().columnName, i, 0);
      table.setValueAt(o.get().columnComment, i, 1);

      table.setValueAt("", i, 2);
      var od = dbDictColumnTypeList.stream()
          .filter(c -> c.dictColumnTypeId.equals(o.get().dictColumnTypeId))
          .findFirst();
      if (od.isPresent()) {
        table.setValueAt(od.get().columnType, i, 2);
      }

      table.setValueAt(o.get().notNullValue, i, 3);
      table.setValueAt(o.get().charsetValue, i, 4);
      table.setValueAt(o.get().collateValue, i, 5);
      table.setValueAt(o.get().defaultValue, i, 6);
      table.setValueAt(o.get().onUpdate, i, 7);
      table.setValueAt(o.get().autoIncrementDefinition, i, 8);
      table.setValueAt(o.get().option, i, 9);

      i++;
    }
  }

  /**
   * get group name.
   *
   * @return group name.
   */
  public String getGroupName() {
    return Utils.getString(comboBoxGroupName.getSelectedItem());
  }

  /**
   * get tmp column list.
   *
   * @return tmp column list
   */
  public List<TmpColumn> getTmpColumnList() {
    var tmpColumnList = new ArrayList<TmpColumn>();
    for (int i = 0; i < table.getRowCount(); i++) {
      var tmpColumn = new TmpColumn();
      tmpColumn.columnName = Utils.getString(table.getValueAt(i, 0));
      tmpColumn.columnComment = Utils.getString(table.getValueAt(i, 1));
      tmpColumn.columnType = Utils.getString(table.getValueAt(i, 2));
      tmpColumn.notNullValue = Utils.getString(table.getValueAt(i, 3));
      tmpColumn.charsetValue = Utils.getString(table.getValueAt(i, 4));
      tmpColumn.collateValue = Utils.getString(table.getValueAt(i, 5));
      tmpColumn.defaultValue = Utils.getString(table.getValueAt(i, 6));
      tmpColumn.onUpdate = Utils.getString(table.getValueAt(i, 7));
      tmpColumn.autoIncrementDefinition = Utils.getString(table.getValueAt(i, 8));
      tmpColumn.option = Utils.getString(table.getValueAt(i, 9));

      tmpColumnList.add(tmpColumn);
    }
    return tmpColumnList;
  }
}
