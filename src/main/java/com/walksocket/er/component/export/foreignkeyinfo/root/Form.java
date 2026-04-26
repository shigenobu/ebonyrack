package com.walksocket.er.component.export.foreignkeyinfo.root;

import com.walksocket.er.Config;
import com.walksocket.er.Json;
import com.walksocket.er.Log;
import com.walksocket.er.Size.DialogExport;
import com.walksocket.er.Utils;
import com.walksocket.er.config.CfgProject;
import com.walksocket.er.custom.ErDottedUnderlineBorder;
import com.walksocket.er.custom.ErHeaderFormatter;
import com.walksocket.er.custom.ErHeaderFormatter.Type;
import com.walksocket.er.custom.ErUnderlineBorder;
import com.walksocket.er.sqlite.TmpResult;
import com.walksocket.er.sqlite.tmp.TmpForeignKeyInfo;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Point;
import java.awt.event.ItemEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

/**
 * Form.
 */
public class Form extends JPanel {

  /**
   * separator.
   */
  private static final String SEPARATOR = " / ";

  /**
   * column name and width maps.
   */
  private static final Map<String, Integer> columnNameWidthMaps = new LinkedHashMap<>();

  static {
    columnNameWidthMaps.put(ErHeaderFormatter.format("Exported", Type.ordinal), 100);
    columnNameWidthMaps.put(ErHeaderFormatter.format("TFilter", Type.ordinal), 100);
    columnNameWidthMaps.put(ErHeaderFormatter.format("RtFilter", Type.ordinal), 100);
    columnNameWidthMaps.put(ErHeaderFormatter.format("Save path", Type.ordinal), 200);
  }

  /**
   * cfg project.
   */
  private final CfgProject cfgProject;

  /**
   * label table.
   */
  private final JLabel labelTable = new JLabel("Table:");

  /**
   * label filter table.
   */
  private final JLabel labelFilterTable = new JLabel("Table name filter:");

  /**
   * button group table filter.
   */
  private final ButtonGroup buttonGroupTableFilter = new ButtonGroup();

  /**
   * radio button table filter none.
   */
  private final JRadioButton radioButtonTableFilterNone = new JRadioButton(
      TmpForeignKeyInfo.FILTER_NONE,
      true);

  /**
   * radio button table filter contains.
   */
  private final JRadioButton radioButtonTableFilterContains = new JRadioButton(
      TmpForeignKeyInfo.FILTER_CONTAINS);

  /**
   * radio button table filter start with.
   */
  private final JRadioButton radioButtonTableFilterStartWith = new JRadioButton(
      TmpForeignKeyInfo.FILTER_START_WITH);

  /**
   * radio button table filter end with.
   */
  private final JRadioButton radioButtonTableFilterEndWith = new JRadioButton(
      TmpForeignKeyInfo.FILTER_END_WITH);

  /**
   * radio button table filter contains in list.
   */
  private final JRadioButton radioButtonTableFilterContainsInList = new JRadioButton(
      TmpForeignKeyInfo.FILTER_CONTAINS_IN_LIST);

  /**
   * text field table filter.
   */
  private final JTextField textFieldTableFilter = new JTextField(40);

  /**
   * label reference table.
   */
  private final JLabel labelReferenceTable = new JLabel("Reference table:");

  /**
   * label filter reference table.
   */
  private final JLabel labelFilterReferenceTable = new JLabel("Reference table name filter:");

  /**
   * button group reference table filter.
   */
  private final ButtonGroup buttonGroupReferenceTableFilter = new ButtonGroup();

  /**
   * radio button reference table filter none.
   */
  private final JRadioButton radioButtonReferenceTableFilterNone = new JRadioButton(
      TmpForeignKeyInfo.FILTER_NONE,
      true);

  /**
   * radio button reference table filter contains.
   */
  private final JRadioButton radioButtonReferenceTableFilterContains = new JRadioButton(
      TmpForeignKeyInfo.FILTER_CONTAINS);

  /**
   * radio button reference table filter start with.
   */
  private final JRadioButton radioButtonReferenceTableFilterStartWith = new JRadioButton(
      TmpForeignKeyInfo.FILTER_START_WITH);

  /**
   * radio button reference table filter end with.
   */
  private final JRadioButton radioButtonReferenceTableFilterEndWith = new JRadioButton(
      TmpForeignKeyInfo.FILTER_END_WITH);

  /**
   * radio button reference table filter contains in list.
   */
  private final JRadioButton radioButtonReferenceTableFilterContainsInList = new JRadioButton(
      TmpForeignKeyInfo.FILTER_CONTAINS_IN_LIST);

  /**
   * text field reference table filter.
   */
  private final JTextField textFieldReferenceTableFilter = new JTextField(40);

  /**
   * label save path.
   */
  private final JLabel labelSavePath = new JLabel("Save path:");

  /**
   * text field save path.
   */
  private final JTextField textFieldSavePath = new JTextField(30);

  /**
   * label choose from history.
   */
  private final JLabel labelChooseFromHistory = new JLabel("Choose from history:");

  /**
   * table.
   */
  private final JTable table;

  /**
   * table model.
   */
  private final DefaultTableModel tableModel;

  /**
   * button remove.
   */
  private final JButton buttonRemove = new JButton("Remove row");

  /**
   * Constructor.
   *
   * @param cfgProject cfgProject
   */
  public Form(CfgProject cfgProject) {
    this.cfgProject = cfgProject;
    var form = this;

    // panel - table
    var panelTable = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
    panelTable.setPreferredSize(
        new Dimension(DialogExport.WIDTH - 20, DialogExport.HEIGHT / 20 * 2));
    add(panelTable);

    var panelTableInnerLeft = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
    panelTableInnerLeft.setPreferredSize(
        new Dimension(DialogExport.WIDTH / 5, DialogExport.HEIGHT / 20 * 2));
    panelTable.add(panelTableInnerLeft);

    labelTable.setPreferredSize(
        new Dimension(DialogExport.WIDTH / 4, DialogExport.HEIGHT / 20));
    panelTableInnerLeft.add(labelTable);

    var panelTableInnerRight = new JPanel(new FlowLayout(FlowLayout.LEFT, 2, 0));
    panelTableInnerRight.setPreferredSize(
        new Dimension(DialogExport.WIDTH / 5 * 3, DialogExport.HEIGHT / 20 * 2));
    panelTable.add(panelTableInnerRight);

    labelFilterTable.setPreferredSize(
        new Dimension(DialogExport.WIDTH / 5 * 3, labelFilterTable.getFont().getSize() * 2));
    panelTableInnerRight.add(labelFilterTable);

    radioButtonTableFilterNone.addItemListener(itemEvent -> {
      if (itemEvent.getStateChange() == ItemEvent.SELECTED) {
        textFieldTableFilter.setEditable(false);
      } else if (itemEvent.getStateChange() == ItemEvent.DESELECTED) {
        textFieldTableFilter.setEditable(true);
      }
    });
    radioButtonTableFilterNone.setActionCommand(TmpForeignKeyInfo.FILTER_NONE);
    radioButtonTableFilterContains.setActionCommand(TmpForeignKeyInfo.FILTER_CONTAINS);
    radioButtonTableFilterStartWith.setActionCommand(TmpForeignKeyInfo.FILTER_START_WITH);
    radioButtonTableFilterEndWith.setActionCommand(TmpForeignKeyInfo.FILTER_END_WITH);
    radioButtonTableFilterContainsInList.setActionCommand(
        TmpForeignKeyInfo.FILTER_CONTAINS_IN_LIST);
    buttonGroupTableFilter.add(radioButtonTableFilterNone);
    buttonGroupTableFilter.add(radioButtonTableFilterContains);
    buttonGroupTableFilter.add(radioButtonTableFilterStartWith);
    buttonGroupTableFilter.add(radioButtonTableFilterEndWith);
    buttonGroupTableFilter.add(radioButtonTableFilterContainsInList);
    panelTableInnerRight.add(radioButtonTableFilterNone);
    panelTableInnerRight.add(radioButtonTableFilterContains);
    panelTableInnerRight.add(radioButtonTableFilterStartWith);
    panelTableInnerRight.add(radioButtonTableFilterEndWith);
    panelTableInnerRight.add(radioButtonTableFilterContainsInList);
    textFieldTableFilter.setEditable(false);
    panelTableInnerRight.add(textFieldTableFilter);

    var borderPanel1 = new JPanel();
    borderPanel1.setPreferredSize(new Dimension(DialogExport.WIDTH - 20, 10));
    borderPanel1.setBorder(new ErDottedUnderlineBorder());
    add(borderPanel1);

    // panel - reference table
    var panelReferenceTable = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
    panelReferenceTable.setPreferredSize(
        new Dimension(DialogExport.WIDTH - 20, DialogExport.HEIGHT / 20 * 2));
    add(panelReferenceTable);

    var panelReferenceTableInnerLeft = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
    panelReferenceTableInnerLeft.setPreferredSize(
        new Dimension(DialogExport.WIDTH / 5, DialogExport.HEIGHT / 20 * 2));
    panelReferenceTable.add(panelReferenceTableInnerLeft);

    labelReferenceTable.setPreferredSize(
        new Dimension(DialogExport.WIDTH / 4, DialogExport.HEIGHT / 20));
    panelReferenceTableInnerLeft.add(labelReferenceTable);

    var panelReferenceTableInnerRight = new JPanel(new FlowLayout(FlowLayout.LEFT, 2, 0));
    panelReferenceTableInnerRight.setPreferredSize(
        new Dimension(DialogExport.WIDTH / 5 * 3, DialogExport.HEIGHT / 20 * 2));
    panelReferenceTable.add(panelReferenceTableInnerRight);

    labelFilterReferenceTable.setPreferredSize(
        new Dimension(DialogExport.WIDTH / 5 * 3, labelFilterTable.getFont().getSize() * 2));
    panelReferenceTableInnerRight.add(labelFilterReferenceTable);

    radioButtonReferenceTableFilterNone.addItemListener(itemEvent -> {
      if (itemEvent.getStateChange() == ItemEvent.SELECTED) {
        textFieldReferenceTableFilter.setEditable(false);
      } else if (itemEvent.getStateChange() == ItemEvent.DESELECTED) {
        textFieldReferenceTableFilter.setEditable(true);
      }
    });
    radioButtonReferenceTableFilterNone.setActionCommand(TmpForeignKeyInfo.FILTER_NONE);
    radioButtonReferenceTableFilterContains.setActionCommand(TmpForeignKeyInfo.FILTER_CONTAINS);
    radioButtonReferenceTableFilterStartWith.setActionCommand(TmpForeignKeyInfo.FILTER_START_WITH);
    radioButtonReferenceTableFilterEndWith.setActionCommand(TmpForeignKeyInfo.FILTER_END_WITH);
    radioButtonReferenceTableFilterContainsInList.setActionCommand(
        TmpForeignKeyInfo.FILTER_CONTAINS_IN_LIST);
    buttonGroupReferenceTableFilter.add(radioButtonReferenceTableFilterNone);
    buttonGroupReferenceTableFilter.add(radioButtonReferenceTableFilterContains);
    buttonGroupReferenceTableFilter.add(radioButtonReferenceTableFilterStartWith);
    buttonGroupReferenceTableFilter.add(radioButtonReferenceTableFilterEndWith);
    buttonGroupReferenceTableFilter.add(radioButtonReferenceTableFilterContainsInList);
    panelReferenceTableInnerRight.add(radioButtonReferenceTableFilterNone);
    panelReferenceTableInnerRight.add(radioButtonReferenceTableFilterContains);
    panelReferenceTableInnerRight.add(radioButtonReferenceTableFilterStartWith);
    panelReferenceTableInnerRight.add(radioButtonReferenceTableFilterEndWith);
    panelReferenceTableInnerRight.add(radioButtonReferenceTableFilterContainsInList);
    textFieldReferenceTableFilter.setEditable(false);
    panelReferenceTableInnerRight.add(textFieldReferenceTableFilter);

    var borderPanel2 = new JPanel();
    borderPanel2.setPreferredSize(new Dimension(DialogExport.WIDTH - 20, 10));
    borderPanel2.setBorder(new ErDottedUnderlineBorder());
    add(borderPanel2);

    // panel - save path
    var panelSavePath = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
    panelSavePath.setPreferredSize(
        new Dimension(DialogExport.WIDTH - 20, DialogExport.HEIGHT / 20));
    add(panelSavePath);

    var panelSavePathInnerLeft = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 2));
    panelSavePathInnerLeft.setPreferredSize(
        new Dimension(DialogExport.WIDTH / 5, DialogExport.HEIGHT / 20));
    panelSavePath.add(panelSavePathInnerLeft);

    labelSavePath.setPreferredSize(
        new Dimension(DialogExport.WIDTH / 5, DialogExport.HEIGHT / 20));
    panelSavePathInnerLeft.add(labelSavePath);

    var panelSavePathInnerRight = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 10));
    panelSavePathInnerRight.setPreferredSize(
        new Dimension(DialogExport.WIDTH / 5 * 3, DialogExport.HEIGHT / 20));
    panelSavePath.add(panelSavePathInnerRight);

    textFieldSavePath.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        // chooser
        var format = "json";
        var dotFormat = "." + format;
        var dir = System.getProperty("user.home");
        var file = String.format("fk_info_%s%s", cfgProject.name, dotFormat);
        var sqlPath = Utils.getString(textFieldSavePath);
        if (!Utils.isNullOrEmpty(sqlPath)) {
          dir = new File(sqlPath).getParent();
          file = new File(sqlPath).getName();
        } else {
          var lastForeignKeyInfoSavePath = cfgProject.lastForeignKeyInfoSavePath;
          if (!Utils.isNullOrEmpty(lastForeignKeyInfoSavePath)) {
            dir = new File(lastForeignKeyInfoSavePath).getParent();
            file = new File(lastForeignKeyInfoSavePath).getName();
          }
        }
        var chooser = new JFileChooser(dir);
        chooser.setAcceptAllFileFilterUsed(false);
        chooser.setFileFilter(new FileNameExtensionFilter("*" + dotFormat,
            format));
        chooser.setSelectedFile(new File(file));
        var result = chooser.showSaveDialog(form);
        if (result == JFileChooser.APPROVE_OPTION) {
          var fileName = chooser.getSelectedFile().getAbsolutePath();
          if (!fileName.endsWith(dotFormat)) {
            fileName += dotFormat;
          }
          textFieldSavePath.setText(fileName);
        }
      }
    });

    panelSavePathInnerRight.add(textFieldSavePath);

    var borderPanel3 = new JPanel();
    borderPanel3.setPreferredSize(new Dimension(DialogExport.WIDTH - 20, 10));
    borderPanel3.setBorder(new ErUnderlineBorder());
    add(borderPanel3);

    // panel - choose from history
    var panelChooseFromHistory = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
    panelChooseFromHistory.setPreferredSize(
        new Dimension(DialogExport.WIDTH - 20, DialogExport.HEIGHT / 20 * 10));
    add(panelChooseFromHistory);

    labelChooseFromHistory.setPreferredSize(
        new Dimension(DialogExport.WIDTH / 5, DialogExport.HEIGHT / 20 - 10));
    panelChooseFromHistory.add(labelChooseFromHistory);

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
          // table filter
          var tableFilter = TmpForeignKeyInfo.FILTER_NONE;
          var tableFilterValue = "";
          var tableFilterComplex = Utils.getString(table.getValueAt(r, 1));
          var tableFilterPos = tableFilterComplex.indexOf(SEPARATOR);
          if (tableFilterPos >= 0) {
            tableFilter = tableFilterComplex.substring(0, tableFilterPos);
            tableFilterValue = tableFilterComplex.substring(tableFilterPos + SEPARATOR.length());
          }
          switch (tableFilter) {
            case TmpForeignKeyInfo.FILTER_NONE:
              radioButtonTableFilterNone.setSelected(true);
              break;
            case TmpForeignKeyInfo.FILTER_CONTAINS:
              radioButtonTableFilterContains.setSelected(true);
              break;
            case TmpForeignKeyInfo.FILTER_START_WITH:
              radioButtonTableFilterStartWith.setSelected(true);
              break;
            case TmpForeignKeyInfo.FILTER_END_WITH:
              radioButtonTableFilterEndWith.setSelected(true);
              break;
            case TmpForeignKeyInfo.FILTER_CONTAINS_IN_LIST:
              radioButtonTableFilterContainsInList.setSelected(true);
              break;
          }
          textFieldTableFilter.setText(tableFilterValue);

          // reference table filter
          var referenceTableFilter = TmpForeignKeyInfo.FILTER_NONE;
          var referenceTableFilterValue = "";
          var referenceTableFilterComplex = Utils.getString(table.getValueAt(r, 2));
          var referenceTableFilterPos = referenceTableFilterComplex.indexOf(SEPARATOR);
          if (referenceTableFilterPos >= 0) {
            referenceTableFilter = referenceTableFilterComplex.substring(0,
                referenceTableFilterPos);
            referenceTableFilterValue = referenceTableFilterComplex.substring(
                referenceTableFilterPos + SEPARATOR.length());
          }
          switch (referenceTableFilter) {
            case TmpForeignKeyInfo.FILTER_NONE:
              radioButtonReferenceTableFilterNone.setSelected(true);
              break;
            case TmpForeignKeyInfo.FILTER_CONTAINS:
              radioButtonReferenceTableFilterContains.setSelected(true);
              break;
            case TmpForeignKeyInfo.FILTER_START_WITH:
              radioButtonReferenceTableFilterStartWith.setSelected(true);
              break;
            case TmpForeignKeyInfo.FILTER_END_WITH:
              radioButtonReferenceTableFilterEndWith.setSelected(true);
              break;
            case TmpForeignKeyInfo.FILTER_CONTAINS_IN_LIST:
              radioButtonReferenceTableFilterContainsInList.setSelected(true);
              break;
          }
          textFieldReferenceTableFilter.setText(referenceTableFilterValue);

          // save path
          textFieldSavePath.setText(Utils.getString(table.getValueAt(r, 3)));
        }
      }
    });
    var sp = new JScrollPane(table);
    sp.setPreferredSize(new Dimension(DialogExport.WIDTH - 20, DialogExport.HEIGHT / 20 * 8));
    panelChooseFromHistory.add(sp);

    // button remove
    var panelRemove = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 10));
    panelChooseFromHistory.add(panelRemove);
    buttonRemove.addActionListener(actionEvent -> {
      var selectedRow = table.getSelectedRow();
      if (selectedRow >= 0) {
        var selected = Utils.getString(table.getValueAt(selectedRow, 0));
        var newHistories = cfgProject.foreignKeyInfoHistories.stream()
            .filter(h -> !h.exported.equals(selected))
            .toList();
        cfgProject.foreignKeyInfoHistories = newHistories;
        Config.save();

        tableModel.removeRow(selectedRow);
      }
    });
    panelRemove.add(buttonRemove);

    var borderPanel4 = new JPanel();
    borderPanel4.setPreferredSize(new Dimension(DialogExport.WIDTH - 20, 10));
    borderPanel4.setBorder(new ErUnderlineBorder());
    add(borderPanel4);

    // reload
    reloadTable();
  }

  /**
   * reload table.
   */
  public void reloadTable() {
    tableModel.setRowCount(0);

    for (int i = 0; i < cfgProject.foreignKeyInfoHistories.size(); i++) {
      tableModel.setRowCount(i + 1);

      var history = cfgProject.foreignKeyInfoHistories.get(i);
      table.setValueAt(history.exported, i, 0);

      var tFilter = history.foreignKeyInfo.filterTableActionCommand;
      if (!Utils.isNullOrEmpty(history.foreignKeyInfo.filterTableValue)) {
        tFilter += String.format("%s%s", SEPARATOR, history.foreignKeyInfo.filterTableValue);
      }
      table.setValueAt(tFilter, i, 1);

      var rtFilter = history.foreignKeyInfo.filterReferenceTableActionCommand;
      if (!Utils.isNullOrEmpty(history.foreignKeyInfo.filterReferenceTableValue)) {
        rtFilter += String.format("%s%s", SEPARATOR,
            history.foreignKeyInfo.filterReferenceTableValue);
      }
      table.setValueAt(rtFilter, i, 2);

      table.setValueAt(history.foreignKeyInfo.savePath, i, 3);
    }
  }

  /**
   * get result.
   *
   * @return result
   */
  public TmpResult<TmpForeignKeyInfo> getResult() {
    var tmpForeignKeyInfo = new TmpForeignKeyInfo();

    tmpForeignKeyInfo.filterTableActionCommand = buttonGroupTableFilter.getSelection()
        .getActionCommand();
    tmpForeignKeyInfo.filterTableValue = Utils.getString(textFieldTableFilter);

    tmpForeignKeyInfo.filterReferenceTableActionCommand = buttonGroupReferenceTableFilter.getSelection()
        .getActionCommand();
    tmpForeignKeyInfo.filterReferenceTableValue = Utils.getString(textFieldReferenceTableFilter);

    tmpForeignKeyInfo.savePath = Utils.getString(textFieldSavePath);

    return new TmpResult<TmpForeignKeyInfo>(tmpForeignKeyInfo) {
      @Override
      protected void validate() throws Exception {
        for (var tmp : tmpList) {
          Log.trace(Json.toJsonString(tmp));
          if (Utils.isNullOrEmpty(tmp.savePath)) {
            throw new Exception("Required 'Save path'.");
          }
        }
      }
    };
  }
}
