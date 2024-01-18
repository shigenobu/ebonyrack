package com.walksocket.er.component.edit.dict.groups.root;

import com.walksocket.er.Json;
import com.walksocket.er.Log;
import com.walksocket.er.Size.DialogMedium;
import com.walksocket.er.Utils;
import com.walksocket.er.component.UsedDictGroups;
import com.walksocket.er.component.edit.dict.groups.Root;
import com.walksocket.er.sqlite.Bucket;
import com.walksocket.er.sqlite.entity.DbDictColumn;
import com.walksocket.er.sqlite.tmp.TmpDictGroup;
import com.walksocket.er.sqlite.tmp.TmpDictGroupColumn;
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
   * label dict group id.
   */
  private final JLabel labelDictGroupId = new JLabel("Dict group id:");

  /**
   * text field dict group id.
   */
  private final JTextField textFieldDictGroupId = new JTextField(20);

  /**
   * label group name.
   */
  private final JLabel labelGroupName = new JLabel("Group name:");

  /**
   * text field group name.
   */
  private final JTextField textFieldGroupName = new JTextField(20);

  /**
   * label columns.
   */
  private final JLabel labelColumns = new JLabel("Columns:");

  /**
   * combo box dict column list.
   */
  private final List<JComboBox> comboBoxDictColumnList = new ArrayList<>();

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
   * columns.
   */
  private final List<String> columns = new ArrayList<>();

  /**
   * column name and width maps.
   */
  private static final Map<String, Integer> columnNameWidthMaps = new LinkedHashMap<>();

  static {
    // b -> required
    // i -> open dialog
    // u -> with dict
    // s -> show only
    columnNameWidthMaps.put("<html><s>Dict group id</s></html>", 200);
    columnNameWidthMaps.put("<html><s>Group name</s></html>", 200);
    columnNameWidthMaps.put("<html><s>Columns</s></html>", 600);
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
   *
   * @param root root
   */
  public Form(Root root) {
    var form = this;

    // layout
    setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));

    // dict columns
    columns.add("");
    var dbDictColumnTypeList = Bucket.getInstance().getBucketDict().dbDictColumnTypeList;
    var dbDictColumnList = Bucket.getInstance().getBucketDict().dbDictColumnList;
    for (var dbDictColumn : dbDictColumnList.stream()
        .sorted(Comparator.comparing(DbDictColumn::getColumnNameForSort)
            .thenComparing(DbDictColumn::getColumnCommentForSort))
        .collect(Collectors.toList())) {
      var opt = dbDictColumnTypeList.stream()
          .filter(d -> d.dictColumnTypeId.equals(dbDictColumn.dictColumnTypeId))
          .findFirst();
      if (opt.isPresent()) {
        var dbDictColumnType = opt.get();
        columns.add(String.format("%s: %s [%s]", dbDictColumn.dictColumnId,
            dbDictColumn.getShowColumnName(), dbDictColumnType.columnType));
      }
    }

    // dict group id
    var panel1 = new JPanel();
    add(panel1);
    panel1.add(labelDictGroupId);
    textFieldDictGroupId.setEditable(false);
    panel1.add(textFieldDictGroupId);

    // group name
    var panel2 = new JPanel();
    add(panel2);
    panel2.add(labelGroupName);
    panel2.add(textFieldGroupName);

    // columns
    var panel3 = new JPanel(new FlowLayout(FlowLayout.LEFT));
    panel3.setPreferredSize(new Dimension(DialogMedium.WIDTH, DialogMedium.HEIGHT / 20 * 5));
    add(panel3);

    var panel3Inner1 = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 2));
    panel3Inner1.setPreferredSize(new Dimension(50, DialogMedium.HEIGHT / 20 * 5));
    panel3.add(panel3Inner1);
    panel3Inner1.add(labelColumns);

    var panel3Inner2 = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 2));
    panel3Inner2.setPreferredSize(
        new Dimension(DialogMedium.WIDTH - 60, DialogMedium.HEIGHT / 20 * 5));
    panel3.add(panel3Inner2);

    for (int i = 0; i < 10; i++) {
      var comboBoxDictColumn = new JComboBox(columns.toArray());
      comboBoxDictColumn.setPreferredSize(
          new Dimension((DialogMedium.WIDTH - 80) / 2, comboBoxDictColumn.getFont().getSize() * 2));
      panel3Inner2.add(comboBoxDictColumn);

      comboBoxDictColumnList.add(comboBoxDictColumn);
    }

    // button
    var panelButton = new JPanel();
    add(panelButton);
    buttonSave.addActionListener(actionEvent -> {
      // check
      var groupName = Utils.getString(textFieldGroupName);
      if (Utils.isNullOrEmpty(groupName)) {
        JOptionPane.showMessageDialog(form, "Required 'Group name'.");
        return;
      }

      try {
        // tmp
        var tmpDictGroup = new TmpDictGroup();
        tmpDictGroup.dictGroupId = Utils.getString(textFieldDictGroupId);
        tmpDictGroup.groupName = groupName;

        var tmpDictGroupColumnList = new ArrayList<TmpDictGroupColumn>();
        var i = 1;
        for (var comboBoxDictColumn : comboBoxDictColumnList) {
          var column = Utils.getString(comboBoxDictColumn);
          if (Utils.isNullOrEmpty(column)) {
            continue;
          }
          var tmps = column.split(":");
          var dictColumnId = tmps[0];

          var tmpDictGroupColumn = new TmpDictGroupColumn();
          tmpDictGroupColumn.seq = i;
          tmpDictGroupColumn.dictColumnId = dictColumnId;

          tmpDictGroupColumnList.add(tmpDictGroupColumn);

          i++;
        }

        // save
        Bucket.getInstance().getBucketDict().saveDictGroup(tmpDictGroup, tmpDictGroupColumnList);
        root.getEditDictGroups().changeState();

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
      var dictGroupId = Utils.getString(textFieldDictGroupId);
      if (Utils.isNullOrEmpty(dictGroupId)) {
        JOptionPane.showMessageDialog(form, "Already removed.");
        return;
      }

      try {
        // tmp
        var tmpDictGroup = new TmpDictGroup();
        tmpDictGroup.dictGroupId = dictGroupId;

        // remove
        Bucket.getInstance().getBucketDict().removeDictGroup(tmpDictGroup);
        root.getEditDictGroups().changeState();

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
        if (e.getClickCount() == 2) {
          Point pt = e.getPoint();
          int row = table.rowAtPoint(pt);
          int col = table.columnAtPoint(pt);
          var used = Utils.getString(table.getValueAt(row, 3));
          if (row >= 0 && col == 3 && used.equals("yes")) {
            var dictGroupId = Utils.getString(table.getValueAt(row, 0));
            var dbDictGroup = Bucket.getInstance().getBucketDict().dbDictGroupList.stream()
                .filter(d -> d.dictGroupId.equals(dictGroupId))
                .findFirst()
                .get();

            var usedDictGroups = new UsedDictGroups(dbDictGroup);
            usedDictGroups.setModal(true);
            usedDictGroups.setVisible(true);
          }
          return;
        }

        Point pt = e.getPoint();
        int r = table.rowAtPoint(pt);
        if (r >= 0) {
          clear();

          textFieldDictGroupId.setText(Utils.getString(table.getValueAt(r, 0)));
          textFieldGroupName.setText(Utils.getString(table.getValueAt(r, 1)));

          var groupColumnJson = Utils.getString(table.getValueAt(r, 2));
          if (!Utils.isNullOrEmpty(groupColumnJson)) {
            var groupColumns = Json.toObject(groupColumnJson, List.class);
            for (int i = 0; i < groupColumns.size(); i++) {
              comboBoxDictColumnList.get(i).setSelectedItem(groupColumns.get(i));
            }
          }

          buttonRemove.setEnabled(false);
          var used = Utils.getString(table.getValueAt(r, 3));
          if (!used.equals("yes")) {
            buttonRemove.setEnabled(true);
          }
        }
      }
    });
    var sp = new JScrollPane(table);
    sp.setPreferredSize(new Dimension(DialogMedium.WIDTH - 40, DialogMedium.HEIGHT / 10 * 5));
    panelTable.add(sp);

    // load
    loadTable();
  }

  /**
   * load table.
   */
  private void loadTable() {
    tableModel.setRowCount(0);

    var dbTableGroupList = Bucket.getInstance().getBucketTable().ctxTableList.stream()
        .map(c -> c.dbTableGroup)
        .filter(d -> d != null)
        .collect(Collectors.toList());

    var i = 0;
    for (var dbDictGroup : Bucket.getInstance().getBucketDict().dbDictGroupList.stream()
        .sorted(Comparator.comparing(c -> c.groupName))
        .collect(Collectors.toList())) {
      tableModel.setRowCount(i + 1);

      table.setValueAt(dbDictGroup.dictGroupId, i, 0);
      table.setValueAt(dbDictGroup.groupName, i, 1);

      var groupColumns = new ArrayList<String>();
      var dbDictGroupColumnList = Bucket.getInstance()
          .getBucketDict().dbDictGroupColumnList.stream()
          .filter(c -> c.dictGroupId.equals(dbDictGroup.dictGroupId))
          .sorted(Comparator.comparing(c -> c.seq))
          .collect(Collectors.toList());
      for (var dbDictGroupColumn : dbDictGroupColumnList) {
        var opt = columns.stream()
            .filter(c -> c.startsWith(dbDictGroupColumn.dictColumnId))
            .findFirst();
        if (opt.isPresent()) {
          groupColumns.add(opt.get());
        }
      }

      table.setValueAt("", i, 2);
      if (groupColumns.size() > 0) {
        table.setValueAt(Json.toJsonString(groupColumns), i, 2);
      }

      table.setValueAt("", i, 3);
      var o = dbTableGroupList.stream()
          .filter(d -> d.dictGroupId.equals(dbDictGroup.dictGroupId))
          .findFirst();
      if (o.isPresent()) {
        table.setValueAt("yes", i, 3);
      }

      i++;
    }
  }

  /**
   * clear.
   */
  private void clear() {
    textFieldDictGroupId.setText("");
    textFieldGroupName.setText("");
    for (var comboBoxDictColumn : comboBoxDictColumnList) {
      comboBoxDictColumn.setSelectedIndex(0);
    }

    buttonRemove.setEnabled(false);
  }
}
