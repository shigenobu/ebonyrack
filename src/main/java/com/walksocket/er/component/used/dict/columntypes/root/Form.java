package com.walksocket.er.component.used.dict.columntypes.root;

import com.walksocket.er.Size.DialogUsed;
import com.walksocket.er.custom.ErHeaderFormatter;
import com.walksocket.er.custom.ErHeaderFormatter.Type;
import com.walksocket.er.sqlite.Bucket;
import com.walksocket.er.sqlite.entity.DbDictColumnType;
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
   * label dict column type id.
   */
  private final JLabel labelDictColumnTypeId = new JLabel("Dict column type id:");

  /**
   * text field dict column type id.
   */
  private final JTextField textFieldDictColumnTypeId = new JTextField(20);

  /**
   * label seq.
   */
  private final JLabel labelSeq = new JLabel("Seq:");

  /**
   * text field seq.
   */
  private final JTextField textFieldSeq = new JTextField(10);

  /**
   * label column type.
   */
  private final JLabel labelColumnType = new JLabel("Column type:");

  /**
   * text field column type.
   */
  private final JTextField textFieldColumnType = new JTextField(20);

  /**
   * label remarks.
   */
  private final JLabel labelRemarks = new JLabel("Remarks:");

  /**
   * text field remarks.
   */
  private final JTextField textFieldRemarks = new JTextField(40);

  /**
   * db dict column type.
   */
  private final DbDictColumnType dbDictColumnType;

  /**
   * column name and width maps.
   */
  private static final Map<String, Integer> columnNameWidthMaps = new LinkedHashMap<>();

  static {
    columnNameWidthMaps.put(ErHeaderFormatter.format("Dict column id", Type.showOnly), 200);
    columnNameWidthMaps.put(ErHeaderFormatter.format("Column name", Type.showOnly), 200);
    columnNameWidthMaps.put(ErHeaderFormatter.format("Column comment", Type.showOnly), 200);
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
   * @param dbDictColumnType dbDictColumnType
   */
  public Form(DbDictColumnType dbDictColumnType) {
    this.dbDictColumnType = dbDictColumnType;

    // layout
    setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));

    // dict column type id
    var panel1 = new JPanel();
    add(panel1);
    panel1.add(labelDictColumnTypeId);
    textFieldDictColumnTypeId.setText(dbDictColumnType.dictColumnTypeId);
    textFieldDictColumnTypeId.setEditable(false);
    panel1.add(textFieldDictColumnTypeId);

    // seq
    var panel2 = new JPanel();
    add(panel2);
    panel2.add(labelSeq);
    textFieldSeq.setText(String.valueOf(dbDictColumnType.seq));
    panel2.add(textFieldSeq);

    // column type
    var panel3 = new JPanel();
    add(panel3);
    panel3.add(labelColumnType);
    textFieldColumnType.setText(dbDictColumnType.columnType);
    panel3.add(textFieldColumnType);

    // remarks
    var panel4 = new JPanel();
    add(panel4);
    panel4.add(labelRemarks);
    textFieldRemarks.setText(dbDictColumnType.remarks);
    panel4.add(textFieldRemarks);

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
    sp.setPreferredSize(new Dimension(DialogUsed.WIDTH - 40 + 30, DialogUsed.HEIGHT / 10 * 8));
    panelTable.add(sp);

    // load
    loadTable();
  }

  /**
   * load table.
   */
  private void loadTable() {
    tableModel.setRowCount(0);

    var dbDictColumnList = Bucket.getInstance().getBucketDict().dbDictColumnList.stream()
        .filter(d -> d.dictColumnTypeId.equals(dbDictColumnType.dictColumnTypeId))
        .collect(Collectors.toList());

    var i = 0;
    for (var dbDictColumn : dbDictColumnList) {
      tableModel.setRowCount(i + 1);

      table.setValueAt(dbDictColumn.dictColumnId, i, 0);
      table.setValueAt(dbDictColumn.columnName, i, 1);
      table.setValueAt(dbDictColumn.columnComment, i, 2);

      i++;
    }
  }
}
