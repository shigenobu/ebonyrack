package com.walksocket.er.component.edit.dict.partitions.root;

import com.walksocket.er.Log;
import com.walksocket.er.Size.DialogMedium;
import com.walksocket.er.Utils;
import com.walksocket.er.component.UsedDictPartition;
import com.walksocket.er.component.edit.dict.partitions.Root;
import com.walksocket.er.custom.ErHeaderFormatter;
import com.walksocket.er.custom.ErHeaderFormatter.Type;
import com.walksocket.er.sqlite.Bucket;
import com.walksocket.er.sqlite.tmp.TmpDictPartition;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
   * expression starting.
   */
  private static final String expressionStarting = "PARTITION BY ...";

  /**
   * column name and width maps.
   */
  private static final Map<String, Integer> columnNameWidthMaps = new LinkedHashMap<>();

  static {
    columnNameWidthMaps.put(ErHeaderFormatter.format("Dict partition id", Type.showOnly), 200);
    columnNameWidthMaps.put(ErHeaderFormatter.format("Partition name", Type.showOnly), 200);
    columnNameWidthMaps.put(ErHeaderFormatter.format("Expression", Type.showOnly), 600);
    columnNameWidthMaps.put(ErHeaderFormatter.format("Used", Type.openDialog), 100);
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

    // dict partition id
    var panel1 = new JPanel();
    add(panel1);
    panel1.add(labelDictPartitionId);
    textFieldDictPartitionId.setEditable(false);
    panel1.add(textFieldDictPartitionId);

    // partition name
    var panel2 = new JPanel();
    add(panel2);
    panel2.add(labelPartitionName);
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
    textAreaExpression.setText(expressionStarting);
    panel4.add(new JScrollPane(textAreaExpression));

    // empty
    var emptyPanel3 = new JPanel();
    emptyPanel3.setPreferredSize(new Dimension(DialogMedium.WIDTH, 0));
    emptyPanel3.setAlignmentX(Component.LEFT_ALIGNMENT);
    add(emptyPanel3);

    // button
    var panelButton = new JPanel();
    add(panelButton);
    buttonSave.addActionListener(actionEvent -> {
      // check
      var partitionName = Utils.getString(textFieldPartitionName);
      if (Utils.isNullOrEmpty(partitionName)) {
        JOptionPane.showMessageDialog(form, "Required 'Partition name'.");
        return;
      }
      var expression = Utils.getString(textAreaExpression);
      if (Utils.isNullOrEmpty(expression)) {
        JOptionPane.showMessageDialog(form, "Required 'Expression'.");
        return;
      }

      try {
        // tmp
        var tmpDictPartition = new TmpDictPartition();
        tmpDictPartition.dictPartitionId = Utils.getString(textFieldDictPartitionId);
        tmpDictPartition.partitionName = partitionName;
        tmpDictPartition.expression = expression;

        // save
        Bucket.getInstance().getBucketDict().saveDictPartition(tmpDictPartition);
        root.getEditDictPartitions().changeState();

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
      var dictPartitionId = Utils.getString(textFieldDictPartitionId);
      if (Utils.isNullOrEmpty(dictPartitionId)) {
        JOptionPane.showMessageDialog(form, "Already removed.");
        return;
      }

      try {
        // tmp
        var tmpDictPartition = new TmpDictPartition();
        tmpDictPartition.dictPartitionId = dictPartitionId;

        // remove
        Bucket.getInstance().getBucketDict().removeDictPartition(tmpDictPartition);
        root.getEditDictPartitions().changeState();

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
            var dictPartitionId = Utils.getString(table.getValueAt(row, 0));
            var dbDictPartition = Bucket.getInstance().getBucketDict().dbDictPartitionList.stream()
                .filter(d -> d.dictPartitionId.equals(dictPartitionId))
                .findFirst()
                .get();

            var usedDictPartitions = new UsedDictPartition(dbDictPartition);
            usedDictPartitions.setModal(true);
            usedDictPartitions.setVisible(true);
          }
          return;
        }

        Point pt = e.getPoint();
        int r = table.rowAtPoint(pt);
        if (r >= 0) {
          textFieldDictPartitionId.setText(Utils.getString(table.getValueAt(r, 0)));
          textFieldPartitionName.setText(Utils.getString(table.getValueAt(r, 1)));
          textAreaExpression.setText(Utils.getString(table.getValueAt(r, 2)));

          buttonRemove.setEnabled(false);
          var used = Utils.getString(table.getValueAt(r, 3));
          if (!used.equals("yes")) {
            buttonRemove.setEnabled(true);
          }
        }
      }
    });
    var sp = new JScrollPane(table);
    sp.setPreferredSize(new Dimension(DialogMedium.WIDTH - 40 + 30, DialogMedium.HEIGHT / 10 * 4));
    panelTable.add(sp);

    // load
    loadTable();
  }

  /**
   * load table.
   */
  private void loadTable() {
    tableModel.setRowCount(0);

    var dbTablePartitionList = Bucket.getInstance().getBucketTable().ctxTableList.stream()
        .map(c -> c.dbTablePartition)
        .filter(d -> d != null)
        .collect(Collectors.toList());

    var i = 0;
    for (var dbDictPartition : Bucket.getInstance().getBucketDict().dbDictPartitionList.stream()
        .sorted(Comparator.comparing(c -> c.partitionName))
        .collect(Collectors.toList())) {
      tableModel.setRowCount(i + 1);

      table.setValueAt(dbDictPartition.dictPartitionId, i, 0);
      table.setValueAt(dbDictPartition.partitionName, i, 1);
      table.setValueAt(dbDictPartition.expression, i, 2);

      table.setValueAt("", i, 3);
      var o = dbTablePartitionList.stream()
          .filter(d -> d.dictPartitionId.equals(dbDictPartition.dictPartitionId))
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
    textFieldDictPartitionId.setText("");
    textFieldPartitionName.setText("");
    textAreaExpression.setText(expressionStarting);

    buttonRemove.setEnabled(false);
  }
}
