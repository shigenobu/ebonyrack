package com.walksocket.er.component.edit.dict.columnaliases.root;

import com.walksocket.er.Log;
import com.walksocket.er.Size.DialogLarge;
import com.walksocket.er.Utils;
import com.walksocket.er.component.edit.dict.columnaliases.Root;
import com.walksocket.er.custom.ErHeaderFormatter;
import com.walksocket.er.custom.ErHeaderFormatter.Type;
import com.walksocket.er.custom.ErUnderlineBorder;
import com.walksocket.er.definition.NotNull;
import com.walksocket.er.sqlite.Bucket;
import com.walksocket.er.sqlite.entity.DbDictColumn;
import com.walksocket.er.sqlite.entity.DbDictColumnAlias;
import com.walksocket.er.sqlite.tmp.TmpDictColumnAlias;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Point;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
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
   * label explanation.
   */
  private final JLabel labelExplanation = new JLabel("Explanation:");

  /**
   * text area explanation.
   */
  private final JTextArea textAreaExplanation = new JTextArea(6, 60);

  /**
   * label alias1.
   */
  private final JLabel labelAlias1 = new JLabel("Alias1:");

  /**
   * text field alias1.
   */
  private final JTextField textFieldAlias1 = new JTextField(40);

  /**
   * label alias2.
   */
  private final JLabel labelAlias2 = new JLabel("Alias2:");

  /**
   * text field alias2.
   */
  private final JTextField textFieldAlias2 = new JTextField(40);

  /**
   * label alias3.
   */
  private final JLabel labelAlias3 = new JLabel("Alias3:");

  /**
   * text field alias3.
   */
  private final JTextField textFieldAlias3 = new JTextField(40);

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
   * text field search column name.
   */
  private final JTextField textFieldSearchColumnName = new JTextField(20);

  /**
   * column name and width maps.
   */
  private static final Map<String, Integer> columnNameWidthMaps = new LinkedHashMap<>();

  static {
    columnNameWidthMaps.put(ErHeaderFormatter.format("Dict column id", Type.showOnly), 200);
    columnNameWidthMaps.put(ErHeaderFormatter.format("Column name", Type.showOnly), 200);
    columnNameWidthMaps.put(ErHeaderFormatter.format("Column comment", Type.showOnly), 200);
    columnNameWidthMaps.put(ErHeaderFormatter.format("Column type", Type.showOnly), 200);
    columnNameWidthMaps.put(ErHeaderFormatter.format("Not null", Type.showOnly), 200);
    columnNameWidthMaps.put(ErHeaderFormatter.format("Explanation", Type.showOnly), 400);
    columnNameWidthMaps.put(ErHeaderFormatter.format("Alias1", Type.showOnly), 300);
    columnNameWidthMaps.put(ErHeaderFormatter.format("Alias2", Type.showOnly), 300);
    columnNameWidthMaps.put(ErHeaderFormatter.format("Alias3", Type.showOnly), 300);
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
    textFieldColumnName.setEditable(false);
    panel2.add(textFieldColumnName);

    // column comment
    var panel3 = new JPanel();
    add(panel3);
    panel3.add(labelColumnComment);
    textFieldColumnComment.setEditable(false);
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
    comboBoxColumnType.setEnabled(false);
    panel4.add(comboBoxColumnType);

    // not null
    var panel5 = new JPanel();
    add(panel5);
    panel5.add(labelNotNull);
    comboBoxNotNull = new JComboBox(
        new DefaultComboBoxModel(NotNull.getNotNullListForColumn().toArray()));
    comboBoxNotNull.setEnabled(false);
    panel5.add(comboBoxNotNull);

    // alias
    var panelAlias = new JPanel();
    add(panelAlias);

    // alias left
    var panelAliasLeft = new JPanel();
    panelAliasLeft.setLayout(new BoxLayout(panelAliasLeft, BoxLayout.Y_AXIS));
    panelAlias.add(panelAliasLeft);

    // explanation
    var panel6 = new JPanel();
    panelAliasLeft.add(panel6);
    panel6.add(labelExplanation);
    panel6.add(new JScrollPane(textAreaExplanation));

    // alias right
    var panelAliasRight = new JPanel();
    panelAliasRight.setLayout(new BoxLayout(panelAliasRight, BoxLayout.Y_AXIS));
    panelAlias.add(panelAliasRight);

    // alias1
    var panel7 = new JPanel();
    panelAliasRight.add(panel7);
    panel7.add(labelAlias1);
    panel7.add(textFieldAlias1);

    // alias2
    var panel8 = new JPanel();
    panelAliasRight.add(panel8);
    panel8.add(labelAlias2);
    panel8.add(textFieldAlias2);

    // alias3
    var panel9 = new JPanel();
    panelAliasRight.add(panel9);
    panel9.add(labelAlias3);
    panel9.add(textFieldAlias3);

    // button
    var panelButton = new JPanel();
    add(panelButton);
    buttonSave.addActionListener(actionEvent -> {
      try {
        // tmp
        var tmpDictColumnAlias = new TmpDictColumnAlias();
        tmpDictColumnAlias.dictColumnId = Utils.getString(textFieldDictColumnId);
        tmpDictColumnAlias.explanation = Utils.getString(textAreaExplanation);
        tmpDictColumnAlias.alias1 = Utils.getString(textFieldAlias1);
        tmpDictColumnAlias.alias2 = Utils.getString(textFieldAlias2);
        tmpDictColumnAlias.alias3 = Utils.getString(textFieldAlias3);

        // save
        Bucket.getInstance().getBucketDict().saveDictColumnAlias(tmpDictColumnAlias);
        root.getEditColumnAliases().changeState();

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
      try {
        // tmp
        var tmpDictColumnAlias = new TmpDictColumnAlias();
        tmpDictColumnAlias.dictColumnId = Utils.getString(textFieldDictColumnId);

        // save
        Bucket.getInstance().getBucketDict().saveDictColumnAlias(tmpDictColumnAlias);
        root.getEditColumnAliases().changeState();

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

    // empty
    var emptyPanel = new JPanel();
    emptyPanel.setPreferredSize(new Dimension(DialogLarge.WIDTH, DialogLarge.HEIGHT / 40));
    emptyPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
    emptyPanel.setBorder(new ErUnderlineBorder());
    add(emptyPanel);

    // search
    var panelSearch = new JPanel();
    add(panelSearch);

    var labelSearchColumnName = new JLabel("(Search) Column name:");
    panelSearch.add(labelSearchColumnName);
    textFieldSearchColumnName.addKeyListener(new KeyAdapter() {
      @Override
      public void keyReleased(KeyEvent e) {
        var columnName = Utils.getString(textFieldSearchColumnName);
        loadTable(columnName);
      }
    });
    panelSearch.add(textFieldSearchColumnName);

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
          textAreaExplanation.setText(Utils.getString(table.getValueAt(r, 5)));
          textFieldAlias1.setText(Utils.getString(table.getValueAt(r, 6)));
          textFieldAlias2.setText(Utils.getString(table.getValueAt(r, 7)));
          textFieldAlias3.setText(Utils.getString(table.getValueAt(r, 8)));

          var d = new DbDictColumnAlias();
          d.dictColumnId = Utils.getString(textFieldDictColumnId);
          d.explanation = Utils.getString(textAreaExplanation);
          d.alias1 = Utils.getString(textFieldAlias1);
          d.alias2 = Utils.getString(textFieldAlias2);
          d.alias3 = Utils.getString(textFieldAlias3);
          buttonRemove.setEnabled(false);
          if (d.isValid()) {
            buttonRemove.setEnabled(true);
          }
        }
      }
    });

    var sp = new JScrollPane(table);
    sp.setPreferredSize(new Dimension(DialogLarge.WIDTH - 40 + 30, DialogLarge.HEIGHT / 10 * 6));
    panelTable.add(sp);

    // load
    loadTable();
  }

  /**
   * load table.
   */
  private void loadTable() {
    textFieldSearchColumnName.setText("");
    loadTable(null);
  }

  /**
   * load table.
   *
   * @param columnName columnName
   */
  private void loadTable(String columnName) {
    tableModel.setRowCount(0);

    var dbDictColumnTypeList = Bucket.getInstance().getBucketDict().dbDictColumnTypeList;
    var dbDictColumnList = Bucket.getInstance().getBucketDict().dbDictColumnList;
    var dbDictColumnAliasList = Bucket.getInstance().getBucketDict().dbDictColumnAliasList;

    var i = 0;
    for (var dbDictColumn : dbDictColumnList.stream()
        .sorted(Comparator.comparing(DbDictColumn::getColumnNameForSort)
            .thenComparing(DbDictColumn::getColumnCommentForSort))
        .collect(Collectors.toList())) {
      if (!Utils.isNullOrEmpty(columnName) && !dbDictColumn.columnName.startsWith(columnName)) {
        continue;
      }

      tableModel.setRowCount(i + 1);

      tableModel.setValueAt(dbDictColumn.dictColumnId, i, 0);
      tableModel.setValueAt(dbDictColumn.columnName, i, 1);
      tableModel.setValueAt(dbDictColumn.columnComment, i, 2);
      var dbDictColumnType = dbDictColumnTypeList.stream()
          .filter(d -> d.dictColumnTypeId.equals(dbDictColumn.dictColumnTypeId))
          .findFirst()
          .get();
      tableModel.setValueAt(dbDictColumnType.columnType, i, 3);
      tableModel.setValueAt(dbDictColumn.notNullValue, i, 4);

      var opt = dbDictColumnAliasList.stream()
          .filter(d -> d.dictColumnId.equals(dbDictColumn.dictColumnId))
          .findFirst();
      if (opt.isPresent()) {
        tableModel.setValueAt(opt.get().explanation, i, 5);
        tableModel.setValueAt(opt.get().alias1, i, 6);
        tableModel.setValueAt(opt.get().alias2, i, 7);
        tableModel.setValueAt(opt.get().alias3, i, 8);
      }

      i++;
    }
  }

  /**
   * clear.
   */
  private void clear() {
    textAreaExplanation.setText("");
    textFieldAlias1.setText("");
    textFieldAlias2.setText("");
    textFieldAlias3.setText("");
  }
}
