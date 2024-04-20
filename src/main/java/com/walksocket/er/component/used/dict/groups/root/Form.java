package com.walksocket.er.component.used.dict.groups.root;

import com.walksocket.er.Size.DialogUsed;
import com.walksocket.er.custom.ErHeaderFormatter;
import com.walksocket.er.custom.ErHeaderFormatter.Type;
import com.walksocket.er.sqlite.Bucket;
import com.walksocket.er.sqlite.entity.DbDictGroup;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
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
   * text field dict column list.
   */
  private final List<JTextField> textFieldDictColumnList = new ArrayList<>();


  /**
   * db dict group.
   */
  private final DbDictGroup dbDictGroup;

  /**
   * column name and width maps.
   */
  private static final Map<String, Integer> columnNameWidthMaps = new LinkedHashMap<>();

  static {
    columnNameWidthMaps.put(ErHeaderFormatter.format("Table id", Type.showOnly), 200);
    columnNameWidthMaps.put(ErHeaderFormatter.format("Table name", Type.showOnly), 200);
    columnNameWidthMaps.put(ErHeaderFormatter.format("Table comment", Type.showOnly), 200);
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
   * @param dbDictGroup dbDictGroup
   */
  public Form(DbDictGroup dbDictGroup) {
    this.dbDictGroup = dbDictGroup;

    // layout
    setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));

    // dict group id
    var panel1 = new JPanel();
    add(panel1);
    panel1.add(labelDictGroupId);
    textFieldDictGroupId.setText(dbDictGroup.dictGroupId);
    textFieldDictGroupId.setEditable(false);
    panel1.add(textFieldDictGroupId);

    // group name
    var panel2 = new JPanel();
    add(panel2);
    panel2.add(labelGroupName);
    textFieldGroupName.setText(dbDictGroup.groupName);
    panel2.add(textFieldGroupName);

    // columns
    var panel3 = new JPanel(new FlowLayout(FlowLayout.LEFT));
    panel3.setPreferredSize(new Dimension(DialogUsed.WIDTH, DialogUsed.HEIGHT / 20 * 4));
    add(panel3);

    var panel3Inner1 = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 2));
    panel3Inner1.setPreferredSize(new Dimension(50, DialogUsed.HEIGHT / 20 * 4));
    panel3.add(panel3Inner1);
    panel3Inner1.add(labelColumns);

    var panel3Inner2 = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 2));
    panel3Inner2.setPreferredSize(
        new Dimension(DialogUsed.WIDTH - 60, DialogUsed.HEIGHT / 20 * 4));
    panel3.add(panel3Inner2);

    for (int i = 0; i < 10; i++) {
      var textFieldDictColumn = new JTextField();
      textFieldDictColumn.setPreferredSize(
          new Dimension((DialogUsed.WIDTH - 80) / 2, textFieldDictColumn.getFont().getSize() * 2));
      panel3Inner2.add(textFieldDictColumn);

      textFieldDictColumnList.add(textFieldDictColumn);
    }

    var dictColumnIdList = Bucket.getInstance().getBucketDict().dbDictGroupColumnList.stream()
        .filter(d -> d.dictGroupId.equals(dbDictGroup.dictGroupId))
        .map(d -> d.dictColumnId)
        .collect(Collectors.toList());
    var idx = 0;
    for (var dictColumnId : dictColumnIdList) {
      var dbDictColumn = Bucket.getInstance().getBucketDict().dbDictColumnList.stream()
          .filter(d -> d.dictColumnId.equals(dictColumnId))
          .findFirst()
          .get();
      var dbDictColumnType = Bucket.getInstance().getBucketDict().dbDictColumnTypeList.stream()
          .filter(d -> d.dictColumnTypeId.equals(dbDictColumn.dictColumnTypeId))
          .findFirst()
          .get();

      var textFieldDictColumn = textFieldDictColumnList.get(idx);
      textFieldDictColumn.setText(String.format("%s: %s [%s]", dbDictColumn.dictColumnId,
          dbDictColumn.getShowColumnName(), dbDictColumnType.columnType));

      idx++;
    }

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
    sp.setPreferredSize(new Dimension(DialogUsed.WIDTH - 40 + 30, DialogUsed.HEIGHT / 10 * 7));
    panelTable.add(sp);

    // load
    loadTable();
  }

  /**
   * load table.
   */
  private void loadTable() {
    tableModel.setRowCount(0);

    var dbTableList = Bucket.getInstance().getBucketTable().ctxTableList.stream()
        .filter(c -> c.dbTableGroup != null)
        .filter(c -> c.dbTableGroup.dictGroupId.equals(dbDictGroup.dictGroupId))
        .map(c -> c.dbTable)
        .collect(Collectors.toList());

    var i = 0;
    for (var dbTable : dbTableList) {
      tableModel.setRowCount(i + 1);

      table.setValueAt(dbTable.tableId, i, 0);
      table.setValueAt(dbTable.tableName, i, 1);
      table.setValueAt(dbTable.tableComment, i, 2);

      i++;
    }
  }
}
