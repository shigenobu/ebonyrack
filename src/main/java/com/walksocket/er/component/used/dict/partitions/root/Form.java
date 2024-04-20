package com.walksocket.er.component.used.dict.partitions.root;

import com.walksocket.er.Size.DialogMedium;
import com.walksocket.er.Size.DialogUsed;
import com.walksocket.er.custom.ErHeaderFormatter;
import com.walksocket.er.custom.ErHeaderFormatter.Type;
import com.walksocket.er.sqlite.Bucket;
import com.walksocket.er.sqlite.entity.DbDictPartition;
import java.awt.Component;
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
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 * Form.
 */
public class Form extends JPanel {

  /**
   * label dict partition id.
   */
  private final JLabel labelDictPartitionId = new JLabel("Dict partition id:");

  /**
   * text field dict partition id.
   */
  private final JTextField textFieldDictPartitionId = new JTextField(20);

  /**
   * label partition name.
   */
  private final JLabel labelPartitionName = new JLabel("Partition name:");

  /**
   * text field partition name.
   */
  private final JTextField textFieldPartitionName = new JTextField(20);

  /**
   * label expression.
   */
  private final JLabel labelExpression = new JLabel("Expression:");

  /**
   * text area expression.
   */
  private final JTextArea textAreaExpression = new JTextArea(10, 50);

  /**
   * text field dict column list.
   */
  private final List<JTextField> textFieldDictColumnList = new ArrayList<>();

  /**
   * db dict partition.
   */
  private final DbDictPartition dbDictPartition;

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
   * @param dbDictPartition dbDictPartition
   */
  public Form(DbDictPartition dbDictPartition) {
    this.dbDictPartition = dbDictPartition;

    // layout
    setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));

    // dict partition id
    var panel1 = new JPanel();
    add(panel1);
    panel1.add(labelDictPartitionId);
    textFieldDictPartitionId.setText(dbDictPartition.dictPartitionId);
    textFieldDictPartitionId.setEditable(false);
    panel1.add(textFieldDictPartitionId);

    // partition name
    var panel2 = new JPanel();
    add(panel2);
    panel2.add(labelPartitionName);
    textFieldPartitionName.setText(dbDictPartition.partitionName);
    panel2.add(textFieldPartitionName);

    // empty
    var emptyPanel1 = new JPanel();
    emptyPanel1.setPreferredSize(new Dimension(DialogMedium.WIDTH, 0));
    emptyPanel1.setAlignmentX(Component.LEFT_ALIGNMENT);
    add(emptyPanel1);

    // expression
    var panel3 = new JPanel();
    add(panel3);
    panel3.add(labelExpression);

    // empty
    var emptyPanel2 = new JPanel();
    emptyPanel2.setPreferredSize(new Dimension(DialogMedium.WIDTH, 0));
    emptyPanel2.setAlignmentX(Component.LEFT_ALIGNMENT);
    add(emptyPanel2);

    var panel4 = new JPanel();
    add(panel4);
    textAreaExpression.setText(dbDictPartition.expression);
    panel4.add(new JScrollPane(textAreaExpression));

    // empty
    var emptyPanel3 = new JPanel();
    emptyPanel3.setPreferredSize(new Dimension(DialogMedium.WIDTH, 0));
    emptyPanel3.setAlignmentX(Component.LEFT_ALIGNMENT);
    add(emptyPanel3);

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
    sp.setPreferredSize(new Dimension(DialogUsed.WIDTH - 40 + 30, DialogUsed.HEIGHT / 10 * 6));
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
        .filter(c -> c.dbTablePartition != null)
        .filter(c -> c.dbTablePartition.dictPartitionId.equals(dbDictPartition.dictPartitionId))
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
