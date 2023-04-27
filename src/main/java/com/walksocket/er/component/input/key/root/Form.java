package com.walksocket.er.component.input.key.root;

import com.walksocket.er.Size.DialogMedium;
import com.walksocket.er.Utils;
import com.walksocket.er.definition.Collation;
import com.walksocket.er.definition.DataType;
import com.walksocket.er.parts.ColumnKeyOption;
import com.walksocket.er.sqlite.Bucket;
import com.walksocket.er.sqlite.tmp.TmpColumn;
import com.walksocket.er.sqlite.tmp.TmpKey;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
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
    columnNameWidthMaps.put("<html>Length</html>", 100);
    columnNameWidthMaps.put("<html><b>Seq in index</b></html>", 100);
    columnNameWidthMaps.put("<html><b>Collation</b></html>", 100);
  }

  /**
   * tmp key.
   */
  private final TmpKey tmpKey;

  /**
   * tmp column list.
   */
  private final List<TmpColumn> tmpColumnList;

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
   * @param tmpKey        tmpKey
   * @param tmpColumnList tmpColumnList
   */
  public Form(TmpKey tmpKey, List<TmpColumn> tmpColumnList) {
    this.tmpKey = tmpKey;
    this.tmpColumnList = tmpColumnList;

    // layout
    setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));

    // panel - table
    var panel1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
    add(panel1);

    var rowCount = tmpColumnList.size();
    var columnNames = columnNameWidthMaps.keySet().toArray();
    tableModel = new DefaultTableModel(columnNames, rowCount) {
      @Override
      public boolean isCellEditable(int row, int column) {
        return column >= 4;
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

      // collation
      if (i == 6) {
        var comboBoxCollation = new JComboBox(new DefaultComboBoxModel(
            Collation.getCollationListForColumn().toArray()));
        tc.setCellEditor(new DefaultCellEditor(comboBoxCollation));
      }
      tc.setCellRenderer(new DefaultTableCellRenderer() {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {
          if (column == 6 && Utils.isNullOrEmpty(value)) {
            value = Collation.ASC_VALUE;
          }
          return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row,
              column);
        }
      });
    }
    var sp = new JScrollPane(table);
    sp.setPreferredSize(new Dimension(DialogMedium.WIDTH - 40, DialogMedium.HEIGHT / 10 * 8));
    panel1.add(sp);

    // tmp column list, tmpKey
    for (int i = 0; i < tmpColumnList.size(); i++) {
      var tmpColumn = tmpColumnList.get(i);

      table.setValueAt(tmpColumn.columnName, i, 0);
      table.setValueAt(tmpColumn.columnComment, i, 1);
      table.setValueAt(tmpColumn.columnType, i, 2);
      table.setValueAt(tmpColumn.notNullValue, i, 3);

      var optColumnName = tmpKey.columnKeyOptionList
          .stream()
          .filter(c -> c.columnName.equals(tmpColumn.columnName))
          .findFirst();
      if (optColumnName.isPresent()) {
        table.setValueAt(optColumnName.get().length, i, 4);
        table.setValueAt(optColumnName.get().seqInIndex, i, 5);
        table.setValueAt(optColumnName.get().collation, i, 6);
      }
    }
  }

  /**
   * pack tmp key.
   */
  public void packTmpKey() {
    // bucket
    var dbDictColumnTypeList = Bucket.getInstance().getBucketDict().dbDictColumnTypeList;

    var columnKeyOptionList = new ArrayList<ColumnKeyOption>();
    for (int i = 0; i < tmpColumnList.size(); i++) {
      var length = Utils.getString(table.getValueAt(i, 4));
      if (!Utils.isNumber(length)) {
        length = "";
      }

      var columnName = Utils.getString(table.getValueAt(i, 0));
      if (!Utils.isNullOrEmpty(length)) {
        var columnType = Utils.getString(table.getValueAt(i, 2));
        var dbDictColumnType = dbDictColumnTypeList.stream()
            .filter(c -> c.columnType.equals(columnType))
            .findFirst()
            .get();
        if (!DataType.isText(dbDictColumnType.columnType) && !DataType.isBinary(
            dbDictColumnType.columnType)) {
          length = "";
        }
      }

      var seqInIndex = Utils.getString(table.getValueAt(i, 5));
      if (!Utils.isNumber(seqInIndex)) {
        continue;
      }

      var collation = Utils.getString(table.getValueAt(i, 6));
      if (Utils.isNullOrEmpty(collation)) {
        collation = Collation.ASC_VALUE;
      }

      var columnKeyOption = new ColumnKeyOption();
      columnKeyOption.columnName = columnName;
      columnKeyOption.length = length;
      columnKeyOption.seqInIndex = seqInIndex;
      columnKeyOption.collation = collation;

      columnKeyOptionList.add(columnKeyOption);
    }

    var sortedColumnKeyOptionList = columnKeyOptionList.stream()
        .sorted(Comparator.comparing(
            t -> Integer.parseInt(t.seqInIndex))) // TODO seqInIndex, columnName
        .collect(Collectors.toList());
    for (int i = 0; i < sortedColumnKeyOptionList.size(); i++) {
      var c = sortedColumnKeyOptionList.get(i);
      c.seqInIndex = String.valueOf(i + 1);
    }
    tmpKey.columnKeyOptionList = sortedColumnKeyOptionList;
  }
}
