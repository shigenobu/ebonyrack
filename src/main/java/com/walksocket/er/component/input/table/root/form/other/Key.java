package com.walksocket.er.component.input.table.root.form.other;

import com.walksocket.er.Size.DialogLarge;
import com.walksocket.er.Utils;
import com.walksocket.er.Word;
import com.walksocket.er.component.InputKey;
import com.walksocket.er.component.input.table.root.form.Other;
import com.walksocket.er.custom.ErTableButtons;
import com.walksocket.er.definition.IndexType;
import com.walksocket.er.parts.ColumnKeyOption;
import com.walksocket.er.sqlite.Bucket;
import com.walksocket.er.sqlite.Tmp;
import com.walksocket.er.sqlite.TmpResult;
import com.walksocket.er.sqlite.context.CtxTable;
import com.walksocket.er.sqlite.tmp.TmpKey;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 * Key.
 */
public class Key extends JPanel {

  /**
   * column name and width maps.
   */
  private static final Map<String, Integer> columnNameWidthMaps = new LinkedHashMap<>();

  static {
    // b -> required
    // i -> open dialog
    // u -> with dict
    // s -> show only
    columnNameWidthMaps.put("<html>Key name</html>", 200);
    columnNameWidthMaps.put("<html><b><u><i>Columns and collations</i></u></b></html>", 400);
    columnNameWidthMaps.put("<html>Index comment</html>", 200);
    columnNameWidthMaps.put("<html>Index type</html>", 100);
  }

  /**
   * other.
   */
  private final Other other;

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
   * tmp key list.
   */
  private final List<TmpKey> tmpKeyList = new ArrayList<>();

  /**
   * Constructor.
   *
   * @param other    other
   * @param ctxTable ctxTable
   */
  public Key(Other other, CtxTable ctxTable) {
    this.other = other;
    this.ctxTable = ctxTable;

    // layout
    setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
    var key = this;

    // panel - table
    var panel1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
    add(panel1);

    var columnNames = columnNameWidthMaps.keySet().toArray();
    tableModel = new DefaultTableModel(columnNames, 3) {
      @Override
      public boolean isCellEditable(int row, int column) {
        // Columns and collations is not editable
        return column != 1;
      }
    };

    var widthList = columnNameWidthMaps.values().toArray(new Integer[columnNameWidthMaps.size()]);
    table = new JTable(tableModel);
    table.putClientProperty("terminateEditOnFocusLost", true);
    table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
    for (int i = 0; i < table.getColumnModel().getColumnCount(); i++) {
      var tc = table.getColumnModel().getColumn(i);
      tc.setPreferredWidth(widthList[i]);

      // index type
      if (i == 3) {
        var comboBoxIndexType = new JComboBox(
            new DefaultComboBoxModel(IndexType.getIndexTypeListForColumn()
                .toArray()));
        comboBoxIndexType.setEditable(true);
        tc.setCellEditor(new DefaultCellEditor(comboBoxIndexType));
      }
    }
    table.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        if (e.getClickCount() == 2) {
          Point pt = e.getPoint();
          int row = table.rowAtPoint(pt);
          int col = table.columnAtPoint(pt);
          if (row >= 0 && col == 1) {
            var tmpKey = getTmpKey(row);
            var tmpColumnList = other.getForm().getColumn().getColumns().getTmpColumnList();
            tmpColumnList.addAll(other.getForm().getColumn().getGroup().getTmpColumnList());
            var inputKey = new InputKey(key, row, tmpKey, tmpColumnList);
            inputKey.setModal(true);
            inputKey.setVisible(true);
          }
        }
      }
    });
    var sp = new JScrollPane(table);
    sp.setPreferredSize(new Dimension(DialogLarge.WIDTH - 40, DialogLarge.HEIGHT / 40 * 6));
    panel1.add(sp);

    // panel - buttons
    var panel2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
    add(panel2);
    panel2.add(new ErTableButtons(table));

    // bucket
    var dbDictColumnList = Bucket.getInstance().getBucketDict().dbDictColumnList;

    // tmp key
    for (int i = 0; i < ctxTable.ctxInnerKeyList.size(); i++) {
      var ctxInnerKey = ctxTable.ctxInnerKeyList.get(i);
      var tmpKey = Tmp.createTmpKey(ctxInnerKey.dbTableKey,
          ctxInnerKey.dbTableKeyColumnList, dbDictColumnList);
      tmpKeyList.add(tmpKey);
      tableModel.setRowCount(i + 1);

      table.setValueAt(tmpKey.keyName, i, 0);
      table.setValueAt(tmpKey.getColumnsAndCollations(), i, 1);
      table.setValueAt(tmpKey.indexComment, i, 2);
      table.setValueAt(tmpKey.indexType, i, 3);
    }
  }

  /**
   * get tmp key.
   *
   * @param row row
   * @return tmp key
   */
  public TmpKey getTmpKey(int row) {
    var tmpKey = new TmpKey();
    tmpKey.keyName = Utils.getString(table.getValueAt(row, 0));
    tmpKey.columnKeyOptionList = ColumnKeyOption.createColumnKeyOptionList(
        Utils.getString(table.getValueAt(row, 1)));
    tmpKey.indexComment = Utils.getString(table.getValueAt(row, 2));
    tmpKey.indexType = Utils.getString(table.getValueAt(row, 3));
    return tmpKey;
  }

  /**
   * get valid tmp key list.
   *
   * @return valid tmp key list
   */
  public List<TmpKey> getValidTmpKeyList() {
    var tmpKeyList = new ArrayList<TmpKey>();
    for (int i = 0; i < tableModel.getRowCount(); i++) {
      var t = getTmpKey(i);
      if (t.columnKeyOptionList.size() == 0) {
        continue;
      }
      tmpKeyList.add(t);
    }
    return tmpKeyList;
  }

  /**
   * reset table.
   *
   * @param row    row
   * @param tmpKey tmpKey
   */
  public void resetTable(int row, TmpKey tmpKey) {
    table.setValueAt(tmpKey.getColumnsAndCollations(), row, 1);
  }

  /**
   * get result.
   *
   * @return result.
   */
  public TmpResult<TmpKey> getResult() {
    var tmpKeyList = new ArrayList<TmpKey>();
    for (int i = 0; i < tableModel.getRowCount(); i++) {
      tmpKeyList.add(getTmpKey(i));
    }

    return new TmpResult<TmpKey>(tmpKeyList) {
      @Override
      protected void validate() throws Exception {
        for (var tmp : tmpList) {
          if (Utils.isNullOrEmpty(tmp.keyName)) {
            tmp.keyName = "k_" + tmp.columnKeyOptionList.stream()
                .map(e -> e.columnName)
                .collect(Collectors.joining("_"));
          }
          if (!Word.isValid(tmp.keyName)) {
            throw new Exception("Invalid 'Key name'.");
          }
        }
      }
    };
  }
}
