package com.walksocket.er.component.export.tableclass.root;

import com.walksocket.er.Config;
import com.walksocket.er.Env;
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
import com.walksocket.er.sqlite.tmp.TmpTableClass;
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
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
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
    columnNameWidthMaps.put(ErHeaderFormatter.format("Template", Type.ordinal), 100);
    columnNameWidthMaps.put(ErHeaderFormatter.format("TFilter", Type.ordinal), 100);
    columnNameWidthMaps.put(ErHeaderFormatter.format("FPrefix", Type.ordinal), 100);
    columnNameWidthMaps.put(ErHeaderFormatter.format("FName", Type.ordinal), 100);
    columnNameWidthMaps.put(ErHeaderFormatter.format("FSuffix", Type.ordinal), 100);
    columnNameWidthMaps.put(ErHeaderFormatter.format("FExtension", Type.ordinal), 100);
    columnNameWidthMaps.put(ErHeaderFormatter.format("Save path", Type.ordinal), 200);
  }

  /**
   * cfg project.
   */
  private final CfgProject cfgProject;

  /**
   * label template.
   */
  private final JLabel labelTemplate = new JLabel("Template:");

  /**
   * combo box template.
   */
  private final JComboBox comboBoxTemplate = new JComboBox();

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
      TmpTableClass.FILTER_NONE,
      true);

  /**
   * radio button table filter contains.
   */
  private final JRadioButton radioButtonTableFilterContains = new JRadioButton(
      TmpTableClass.FILTER_CONTAINS);

  /**
   * radio button table filter start with.
   */
  private final JRadioButton radioButtonTableFilterStartWith = new JRadioButton(
      TmpTableClass.FILTER_START_WITH);

  /**
   * radio button table filter end with.
   */
  private final JRadioButton radioButtonTableFilterEndWith = new JRadioButton(
      TmpTableClass.FILTER_END_WITH);

  /**
   * radio button table filter contains in list.
   */
  private final JRadioButton radioButtonTableFilterContainsInList = new JRadioButton(
      TmpTableClass.FILTER_CONTAINS_IN_LIST);

  /**
   * text field table filter.
   */
  private final JTextField textFieldTableFilter = new JTextField(40);

  /**
   * label convert file.
   */
  private final JLabel labelConvertFileName = new JLabel("File name converter:");

  /**
   * text field file prefix converter.
   */
  private final JTextField textFieldFilePrefixConverter = new JTextField(4);

  /**
   * combo box file name converter.
   */
  private final JComboBox comboBoxFileNameConverter = new JComboBox(
      TmpTableClass.FILE_NAME_CONVERTER_LIST.toArray());

  /**
   * text field file suffix converter.
   */
  private final JTextField textFieldFileSuffixConverter = new JTextField(4);

  /**
   * text field file extension converter.
   */
  private final JTextField textFieldFileExtensionConverter = new JTextField(4);

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

    // panel - template
    var panelTemplate = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
    panelTemplate.setPreferredSize(
        new Dimension(DialogExport.WIDTH - 20, DialogExport.HEIGHT / 20));
    add(panelTemplate);

    var panelTemplateInnerLeft = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 2));
    panelTemplateInnerLeft.setPreferredSize(
        new Dimension(DialogExport.WIDTH / 5, DialogExport.HEIGHT / 20));
    panelTemplate.add(panelTemplateInnerLeft);

    labelTemplate.setPreferredSize(
        new Dimension(DialogExport.WIDTH / 5, DialogExport.HEIGHT / 20));
    panelTemplateInnerLeft.add(labelTemplate);

    var panelTemplateInnerRight = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 10));
    panelTemplateInnerRight.setPreferredSize(
        new Dimension(DialogExport.WIDTH / 5 * 3, DialogExport.HEIGHT / 20));
    panelTemplate.add(panelTemplateInnerRight);

    var templateDir = new File(Env.getTemplateDir());
    for (var file : templateDir.listFiles()) {
      if (file.getName().endsWith(".vm") && !file.getName().startsWith("__")) {
        comboBoxTemplate.addItem(file.getName());
      }
    }
    comboBoxTemplate.setPreferredSize(
        new Dimension(DialogExport.WIDTH / 4, comboBoxTemplate.getFont().getSize() * 2));
    textFieldFileExtensionConverter.setText(getExtension(Utils.getString(comboBoxTemplate)));
    comboBoxTemplate.addActionListener(actionEvent -> {
      textFieldFileExtensionConverter.setText(getExtension(Utils.getString(comboBoxTemplate)));
    });
    panelTemplateInnerRight.add(comboBoxTemplate);

    var borderPanel1 = new JPanel();
    borderPanel1.setPreferredSize(new Dimension(DialogExport.WIDTH - 20, 10));
    borderPanel1.setBorder(new ErDottedUnderlineBorder());
    add(borderPanel1);

    // panel - table
    var panelTable = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
    panelTable.setPreferredSize(
        new Dimension(DialogExport.WIDTH - 20, DialogExport.HEIGHT / 20 * 4));
    add(panelTable);

    var panelTableInnerLeft = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
    panelTableInnerLeft.setPreferredSize(
        new Dimension(DialogExport.WIDTH / 5, DialogExport.HEIGHT / 20 * 4));
    panelTable.add(panelTableInnerLeft);

    labelTable.setPreferredSize(
        new Dimension(DialogExport.WIDTH / 5, DialogExport.HEIGHT / 20));
    panelTableInnerLeft.add(labelTable);

    var panelTableInnerRight = new JPanel(new FlowLayout(FlowLayout.LEFT, 2, 0));
    panelTableInnerRight.setPreferredSize(
        new Dimension(DialogExport.WIDTH / 5 * 3, DialogExport.HEIGHT / 20 * 4));
    panelTable.add(panelTableInnerRight);

    labelFilterTable.setPreferredSize(
        new Dimension(DialogExport.WIDTH / 5 * 3, DialogExport.HEIGHT / 20));
    panelTableInnerRight.add(labelFilterTable);

    radioButtonTableFilterNone.addItemListener(itemEvent -> {
      if (itemEvent.getStateChange() == ItemEvent.SELECTED) {
        textFieldTableFilter.setEditable(false);
      } else if (itemEvent.getStateChange() == ItemEvent.DESELECTED) {
        textFieldTableFilter.setEditable(true);
      }
    });
    radioButtonTableFilterNone.setActionCommand(TmpTableClass.FILTER_NONE);
    radioButtonTableFilterContains.setActionCommand(TmpTableClass.FILTER_CONTAINS);
    radioButtonTableFilterStartWith.setActionCommand(TmpTableClass.FILTER_START_WITH);
    radioButtonTableFilterEndWith.setActionCommand(TmpTableClass.FILTER_END_WITH);
    radioButtonTableFilterContainsInList.setActionCommand(TmpTableClass.FILTER_CONTAINS_IN_LIST);
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

    var emptyPanel2 = new JPanel();
    emptyPanel2.setPreferredSize(new Dimension(DialogExport.WIDTH / 5 * 3, 0));
    panelTableInnerRight.add(emptyPanel2);

    labelConvertFileName.setPreferredSize(
        new Dimension(DialogExport.WIDTH / 5 * 3, DialogExport.HEIGHT / 20));
    panelTableInnerRight.add(labelConvertFileName);

    panelTableInnerRight.add(textFieldFilePrefixConverter);
    panelTableInnerRight.add(comboBoxFileNameConverter);
    panelTableInnerRight.add(textFieldFileSuffixConverter);

    textFieldFileExtensionConverter.setEditable(false);
    panelTableInnerRight.add(textFieldFileExtensionConverter);

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
        var dir = System.getProperty("user.home");
        var tableClassPath = Utils.getString(textFieldSavePath);
        if (!Utils.isNullOrEmpty(tableClassPath)) {
          dir = new File(tableClassPath).getAbsolutePath();
        } else {
          var lastTableClassSaveDir = cfgProject.lastTableClassSaveDir;
          if (!Utils.isNullOrEmpty(lastTableClassSaveDir)) {
            dir = new File(lastTableClassSaveDir).getAbsolutePath();
          }
        }
        var chooser = new JFileChooser(dir);
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        var result = chooser.showSaveDialog(form);
        if (result == JFileChooser.APPROVE_OPTION) {
          var dirName = chooser.getSelectedFile().getAbsolutePath();
          textFieldSavePath.setText(dirName);
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
          // template
          var template = Utils.getString(table.getValueAt(r, 1));
          comboBoxTemplate.setSelectedItem(template);

          // table filter
          var tableFilter = TmpTableClass.FILTER_NONE;
          var tableFilterValue = "";
          var tableFilterComplex = Utils.getString(table.getValueAt(r, 2));
          var tableFilterPos = tableFilterComplex.indexOf(SEPARATOR);
          if (tableFilterPos >= 0) {
            tableFilter = tableFilterComplex.substring(0, tableFilterPos);
            tableFilterValue = tableFilterComplex.substring(tableFilterPos + SEPARATOR.length());
          }
          switch (tableFilter) {
            case TmpTableClass.FILTER_NONE:
              radioButtonTableFilterNone.setSelected(true);
              break;
            case TmpTableClass.FILTER_CONTAINS:
              radioButtonTableFilterContains.setSelected(true);
              break;
            case TmpTableClass.FILTER_START_WITH:
              radioButtonTableFilterStartWith.setSelected(true);
              break;
            case TmpTableClass.FILTER_END_WITH:
              radioButtonTableFilterEndWith.setSelected(true);
              break;
            case TmpTableClass.FILTER_CONTAINS_IN_LIST:
              radioButtonTableFilterContainsInList.setSelected(true);
              break;
          }
          textFieldTableFilter.setText(tableFilterValue);

          // prefix
          textFieldFilePrefixConverter.setText(Utils.getString(table.getValueAt(r, 3)));

          // filename
          comboBoxFileNameConverter.setSelectedItem(Utils.getString(table.getValueAt(r, 4)));

          // suffix
          textFieldFileSuffixConverter.setText(Utils.getString(table.getValueAt(r, 5)));

          // extension
          textFieldFileExtensionConverter.setText(Utils.getString(table.getValueAt(r, 6)));

          // save path
          textFieldSavePath.setText(Utils.getString(table.getValueAt(r, 7)));
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
        var newHistories = cfgProject.tableClassHistories.stream()
            .filter(h -> !h.exported.equals(selected))
            .toList();
        cfgProject.tableClassHistories = newHistories;
        Config.save();

        tableModel.removeRow(selectedRow);
      }
    });
    panelRemove.add(buttonRemove);

    var borderPanel5 = new JPanel();
    borderPanel5.setPreferredSize(new Dimension(DialogExport.WIDTH - 20, 10));
    borderPanel5.setBorder(new ErUnderlineBorder());
    add(borderPanel5);

    // reload
    reloadTable();
  }

  /**
   * reload table.
   */
  public void reloadTable() {
    tableModel.setRowCount(0);

    for (int i = 0; i < cfgProject.tableClassHistories.size(); i++) {
      tableModel.setRowCount(i + 1);

      var history = cfgProject.tableClassHistories.get(i);
      table.setValueAt(history.exported, i, 0);
      table.setValueAt(history.tableClass.templateValue, i, 1);

      var tFilter = history.tableClass.filterTableActionCommand;
      if (!Utils.isNullOrEmpty(history.tableClass.filterTableValue)) {
        tFilter += String.format("%s%s", SEPARATOR, history.tableClass.filterTableValue);
      }
      table.setValueAt(tFilter, i, 2);

      table.setValueAt(history.tableClass.convertFilePrefixValue, i, 3);
      table.setValueAt(history.tableClass.convertFileNameValue, i, 4);
      table.setValueAt(history.tableClass.convertFileSuffixValue, i, 5);
      table.setValueAt(history.tableClass.convertFileExtensionValue, i, 6);

      table.setValueAt(history.tableClass.savePath, i, 7);
    }
  }

  /**
   * get extension.
   *
   * @param templateName templateName
   * @return extension
   */
  private String getExtension(String templateName) {
    var extension = "";
    var templateNameList = templateName.split("\\.");
    for (int i = 0; i < templateNameList.length; i++) {
      var e = templateNameList[i];
      if (e.equals("vm") && i >= 2) {
        extension = "." + templateNameList[i - 1];
      }
    }
    return extension;
  }

  /**
   * get result.
   *
   * @return result
   */
  public TmpResult<TmpTableClass> getResult() {
    var tmpTableClass = new TmpTableClass();

    tmpTableClass.templateValue = Utils.getString(comboBoxTemplate);
    tmpTableClass.filterTableActionCommand = buttonGroupTableFilter.getSelection()
        .getActionCommand();
    tmpTableClass.filterTableValue = Utils.getString(textFieldTableFilter);
    tmpTableClass.convertFilePrefixValue = Utils.getString(textFieldFilePrefixConverter);
    tmpTableClass.convertFileNameValue = Utils.getString(comboBoxFileNameConverter);
    tmpTableClass.convertFileSuffixValue = Utils.getString(textFieldFileSuffixConverter);
    tmpTableClass.convertFileExtensionValue = Utils.getString(textFieldFileExtensionConverter);
    tmpTableClass.savePath = Utils.getString(textFieldSavePath);

    return new TmpResult<TmpTableClass>(tmpTableClass) {
      @Override
      protected void validate() throws Exception {
        for (var tmp : tmpList) {
          Log.trace(Json.toJsonString(tmp));
          if (Utils.isNullOrEmpty(tmp.templateValue)) {
            throw new Exception("Required 'Template'.");
          }
          if (Utils.isNullOrEmpty(tmp.savePath)) {
            throw new Exception("Required 'Save path'.");
          }
        }
      }
    };
  }
}
