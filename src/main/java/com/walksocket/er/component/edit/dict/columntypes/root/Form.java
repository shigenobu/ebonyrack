package com.walksocket.er.component.edit.dict.columntypes.root;

import com.walksocket.er.Log;
import com.walksocket.er.Size.DialogMedium;
import com.walksocket.er.Utils;
import com.walksocket.er.component.UsedDictColumnTypes;
import com.walksocket.er.component.edit.dict.columntypes.Root;
import com.walksocket.er.custom.ErHeaderFormatter;
import com.walksocket.er.custom.ErHeaderFormatter.Type;
import com.walksocket.er.sqlite.Bucket;
import com.walksocket.er.sqlite.entity.DbDictColumnType;
import com.walksocket.er.sqlite.tmp.TmpDictColumnType;
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
    columnNameWidthMaps.put(ErHeaderFormatter.format("Dict column type id", Type.showOnly), 400);
    columnNameWidthMaps.put(ErHeaderFormatter.format("Seq", Type.showOnly), 200);
    columnNameWidthMaps.put(ErHeaderFormatter.format("Column type", Type.showOnly), 400);
    columnNameWidthMaps.put(ErHeaderFormatter.format("Remarks", Type.showOnly), 400);
    columnNameWidthMaps.put(ErHeaderFormatter.format("Used", Type.openDialog), 100);
  }

  /**
   * root.
   */
  private final Root root;

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
    this.root = root;
    var form = this;

    // layout
    setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));

    // dict column type id
    var panel1 = new JPanel();
    add(panel1);
    panel1.add(labelDictColumnTypeId);
    textFieldDictColumnTypeId.setEditable(false);
    panel1.add(textFieldDictColumnTypeId);

    // seq
    var panel2 = new JPanel();
    add(panel2);
    panel2.add(labelSeq);
    panel2.add(textFieldSeq);

    // column type
    var panel3 = new JPanel();
    add(panel3);
    panel3.add(labelColumnType);
    panel3.add(textFieldColumnType);

    // remarks
    var panel4 = new JPanel();
    add(panel4);
    panel4.add(labelRemarks);
    panel4.add(textFieldRemarks);

    // button
    var panelButton = new JPanel();
    add(panelButton);
    buttonSave.addActionListener(actionEvent -> {
      // check
      var seq = Utils.getString(textFieldSeq);
      if (!Utils.isNumber(seq)) {
        JOptionPane.showMessageDialog(form, "Seq must be number.");
        return;
      }
      var columnType = Utils.getString(textFieldColumnType);
      if (Utils.isNullOrEmpty(columnType)) {
        JOptionPane.showMessageDialog(form, "Required 'Column type'.");
        return;
      }

      try {
        // tmp
        var tmpDictColumnType = new TmpDictColumnType();
        tmpDictColumnType.dictColumnTypeId = Utils.getString(textFieldDictColumnTypeId);
        tmpDictColumnType.seq = Integer.parseInt(seq);
        tmpDictColumnType.columnType = columnType;
        tmpDictColumnType.remarks = Utils.getString(textFieldRemarks);

        // save
        Bucket.getInstance().getBucketDict().saveDictColumnType(tmpDictColumnType);
        root.getEditDictColumnTypes().changeState();

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
      var dictColumnTypeId = Utils.getString(textFieldDictColumnTypeId);
      if (Utils.isNullOrEmpty(dictColumnTypeId)) {
        JOptionPane.showMessageDialog(form, "Already removed.");
        return;
      }

      try {
        // tmp
        var tmpDictColumnType = new TmpDictColumnType();
        tmpDictColumnType.dictColumnTypeId = dictColumnTypeId;

        // remove
        Bucket.getInstance().getBucketDict().removeDictColumnType(tmpDictColumnType);
        root.getEditDictColumnTypes().changeState();

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
          var used = Utils.getString(table.getValueAt(row, 4));
          if (row >= 0 && col == 4 && used.equals("yes")) {
            var dictColumnTypeId = Utils.getString(table.getValueAt(row, 0));
            var dbDictColumnType = Bucket.getInstance()
                .getBucketDict().dbDictColumnTypeList.stream()
                .filter(d -> d.dictColumnTypeId.equals(dictColumnTypeId))
                .findFirst()
                .get();

            var usedDictColumnTypes = new UsedDictColumnTypes(form, dbDictColumnType);
            usedDictColumnTypes.setAlwaysOnTop(true);
            usedDictColumnTypes.setModal(true);
            usedDictColumnTypes.setVisible(true);
          }
          return;
        }

        Point pt = e.getPoint();
        int r = table.rowAtPoint(pt);
        if (r >= 0) {
          textFieldDictColumnTypeId.setText(Utils.getString(table.getValueAt(r, 0)));
          textFieldSeq.setText(Utils.getString(table.getValueAt(r, 1)));
          textFieldColumnType.setText(Utils.getString(table.getValueAt(r, 2)));
          textFieldRemarks.setText(Utils.getString(table.getValueAt(r, 3)));

          buttonRemove.setEnabled(false);
          var used = Utils.getString(table.getValueAt(r, 4));
          if (!used.equals("yes")) {
            buttonRemove.setEnabled(true);
          }
        }
      }
    });
    var sp = new JScrollPane(table);
    sp.setPreferredSize(new Dimension(DialogMedium.WIDTH - 40 + 30, DialogMedium.HEIGHT / 10 * 8));
    panelTable.add(sp);

    // load
    loadTable();
  }

  /**
   * load table.
   */
  private void loadTable() {
    tableModel.setRowCount(0);

    var dbDictColumnTypeList = Bucket.getInstance().getBucketDict().dbDictColumnTypeList;
    var dbDictColumnList = Bucket.getInstance().getBucketDict().dbDictColumnList;

    var i = 0;
    for (var dbDictColumnType : dbDictColumnTypeList.stream()
        .sorted(Comparator.comparing(DbDictColumnType::getSeqForSort)
            .thenComparing(DbDictColumnType::getColumnTypeForSort))
        .collect(Collectors.toList())) {
      tableModel.setRowCount(i + 1);

      table.setValueAt(dbDictColumnType.dictColumnTypeId, i, 0);
      table.setValueAt(dbDictColumnType.seq, i, 1);
      table.setValueAt(dbDictColumnType.columnType, i, 2);
      table.setValueAt(dbDictColumnType.remarks, i, 3);

      // used
      table.setValueAt("", i, 4);
      var opt = dbDictColumnList.stream()
          .filter(d -> d.dictColumnTypeId.equals(dbDictColumnType.dictColumnTypeId))
          .findFirst();
      if (opt.isPresent()) {
        table.setValueAt("yes", i, 4);
      }

      i++;
    }
  }

  /**
   * clear.
   */
  private void clear() {
    textFieldDictColumnTypeId.setText("");
    textFieldSeq.setText("");
    textFieldColumnType.setText("");
    textFieldRemarks.setText("");

    buttonRemove.setEnabled(false);
  }

  /**
   * get root.
   *
   * @return root
   */
  public Root getRoot() {
    return root;
  }
}
