package com.walksocket.er.component.export.ddl.root;

import com.walksocket.er.Config;
import com.walksocket.er.Size.DialogExport;
import com.walksocket.er.Utils;
import com.walksocket.er.config.CfgProject;
import com.walksocket.er.custom.ErDottedUnderlineBorder;
import com.walksocket.er.custom.ErHeaderFormatter;
import com.walksocket.er.custom.ErHeaderFormatter.Type;
import com.walksocket.er.custom.ErUnderlineBorder;
import com.walksocket.er.sqlite.TmpResult;
import com.walksocket.er.sqlite.tmp.TmpDdl;
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
    columnNameWidthMaps.put(ErHeaderFormatter.format("Schema", Type.ordinal), 100);
    columnNameWidthMaps.put(ErHeaderFormatter.format("Sequence", Type.ordinal), 100);
    columnNameWidthMaps.put(ErHeaderFormatter.format("SFilter", Type.ordinal), 100);
    columnNameWidthMaps.put(ErHeaderFormatter.format("Table", Type.ordinal), 100);
    columnNameWidthMaps.put(ErHeaderFormatter.format("Foreign key", Type.ordinal), 100);
    columnNameWidthMaps.put(ErHeaderFormatter.format("TFilter", Type.ordinal), 100);
    columnNameWidthMaps.put(ErHeaderFormatter.format("Save path", Type.ordinal), 200);
  }

  /**
   * cfg project.
   */
  private final CfgProject cfgProject;

  /**
   * label schema.
   */
  private final JLabel labelSchema = new JLabel("Schema:");

  /**
   * text field schema.
   */
  private final JTextField textFieldSchema = new JTextField(30);

  /**
   * label sequence.
   */
  private final JLabel labelSequence = new JLabel("Sequence:");

  /**
   * radio button sequence yes.
   */
  private final JRadioButton radioButtonSequenceYes = new JRadioButton("Yes", true);

  /**
   * radio button sequence no.
   */
  private final JRadioButton radioButtonSequenceNo = new JRadioButton("No");

  /**
   * label table.
   */
  private final JLabel labelTable = new JLabel("Table:");

  /**
   * radio button table yes.
   */
  private final JRadioButton radioButtonTableYes = new JRadioButton("Yes", true);

  /**
   * radio button table no.
   */
  private final JRadioButton radioButtonTableNo = new JRadioButton("No");

  /**
   * label foreign key.
   */
  private final JLabel labelForeignKey = new JLabel("Foreign key:");

  /**
   * radio button foreign key yes.
   */
  private final JRadioButton radioButtonForeignKeyYes = new JRadioButton("Yes", true);

  /**
   * radio button foreign key no.
   */
  private final JRadioButton radioButtonForeignKeyNo = new JRadioButton("No");

  /**
   * label filter sequence.
   */
  private final JLabel labelFilterSequence = new JLabel("Sequence name filter:");

  /**
   * button group sequence filter.
   */
  private final ButtonGroup buttonGroupSequenceFilter = new ButtonGroup();

  /**
   * radio button sequence filter none.
   */
  private final JRadioButton radioButtonSequenceFilterNone = new JRadioButton(TmpDdl.FILTER_NONE,
      true);

  /**
   * radio button sequence filter contains.
   */
  private final JRadioButton radioButtonSequenceFilterContains = new JRadioButton(
      TmpDdl.FILTER_CONTAINS);

  /**
   * radio button sequence filter start with.
   */
  private final JRadioButton radioButtonSequenceFilterStartWith = new JRadioButton(
      TmpDdl.FILTER_START_WITH);

  /**
   * radio button sequence filter end with.
   */
  private final JRadioButton radioButtonSequenceFilterEndWith = new JRadioButton(
      TmpDdl.FILTER_END_WITH);

  /**
   * text field sequence filter.
   */
  private final JTextField textFieldSequenceFilter = new JTextField(30);

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
  private final JRadioButton radioButtonTableFilterNone = new JRadioButton(TmpDdl.FILTER_NONE,
      true);

  /**
   * radio button table filter contains.
   */
  private final JRadioButton radioButtonTableFilterContains = new JRadioButton(
      TmpDdl.FILTER_CONTAINS);

  /**
   * radio button table filter start with.
   */
  private final JRadioButton radioButtonTableFilterStartWith = new JRadioButton(
      TmpDdl.FILTER_START_WITH);

  /**
   * radio button table filter end with.
   */
  private final JRadioButton radioButtonTableFilterEndWith = new JRadioButton(
      TmpDdl.FILTER_END_WITH);

  /**
   * text field table filter.
   */
  private final JTextField textFieldTableFilter = new JTextField(30);

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

    // panel - use
    var panelSchema = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
    panelSchema.setPreferredSize(
        new Dimension(DialogExport.WIDTH - 20, DialogExport.HEIGHT / 20));
    add(panelSchema);

    var panelSchemaInnerLeft = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 2));
    panelSchemaInnerLeft.setPreferredSize(
        new Dimension(DialogExport.WIDTH / 5, DialogExport.HEIGHT / 20));
    panelSchema.add(panelSchemaInnerLeft);

    labelSchema.setPreferredSize(
        new Dimension(DialogExport.WIDTH / 5, DialogExport.HEIGHT / 20));
    panelSchemaInnerLeft.add(labelSchema);

    var panelSchemaInnerRight = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 10));
    panelSchemaInnerRight.setPreferredSize(
        new Dimension(DialogExport.WIDTH / 5 * 3, DialogExport.HEIGHT / 20));
    panelSchema.add(panelSchemaInnerRight);

    panelSchemaInnerRight.add(textFieldSchema);

    var borderPanel1 = new JPanel();
    borderPanel1.setPreferredSize(new Dimension(DialogExport.WIDTH - 20, 10));
    borderPanel1.setBorder(new ErDottedUnderlineBorder());
    add(borderPanel1);

    // panel - sequence
    var panelSequence = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
    panelSequence.setPreferredSize(
        new Dimension(DialogExport.WIDTH - 20, DialogExport.HEIGHT / 20 * 3));
    add(panelSequence);

    var panelSequenceInnerLeft = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
    panelSequenceInnerLeft.setPreferredSize(
        new Dimension(DialogExport.WIDTH / 5, DialogExport.HEIGHT / 20 * 3));
    panelSequence.add(panelSequenceInnerLeft);

    labelSequence.setPreferredSize(
        new Dimension(DialogExport.WIDTH / 5, DialogExport.HEIGHT / 20));
    panelSequenceInnerLeft.add(labelSequence);

    var panelSequenceInnerRight = new JPanel(new FlowLayout(FlowLayout.LEFT, 2, 0));
    panelSequenceInnerRight.setPreferredSize(
        new Dimension(DialogExport.WIDTH / 5 * 3, DialogExport.HEIGHT / 20 * 3));
    panelSequence.add(panelSequenceInnerRight);

    var buttonGroupSequence = new ButtonGroup();
    buttonGroupSequence.add(radioButtonSequenceYes);
    buttonGroupSequence.add(radioButtonSequenceNo);
    radioButtonSequenceYes.setPreferredSize(
        new Dimension(DialogExport.WIDTH / 8, DialogExport.HEIGHT / 20));
    panelSequenceInnerRight.add(radioButtonSequenceYes);
    radioButtonSequenceNo.setPreferredSize(
        new Dimension(DialogExport.WIDTH / 8, DialogExport.HEIGHT / 20));
    panelSequenceInnerRight.add(radioButtonSequenceNo);

    labelFilterSequence.setPreferredSize(
        new Dimension(DialogExport.WIDTH / 5 * 3, labelFilterSequence.getFont().getSize() * 2));
    panelSequenceInnerRight.add(labelFilterSequence);

    radioButtonSequenceFilterNone.addItemListener(itemEvent -> {
      if (itemEvent.getStateChange() == ItemEvent.SELECTED) {
        textFieldSequenceFilter.setEditable(false);
      } else if (itemEvent.getStateChange() == ItemEvent.DESELECTED) {
        textFieldSequenceFilter.setEditable(true);
      }
    });
    radioButtonSequenceFilterNone.setActionCommand(TmpDdl.FILTER_NONE);
    radioButtonSequenceFilterContains.setActionCommand(TmpDdl.FILTER_CONTAINS);
    radioButtonSequenceFilterStartWith.setActionCommand(TmpDdl.FILTER_START_WITH);
    radioButtonSequenceFilterEndWith.setActionCommand(TmpDdl.FILTER_END_WITH);
    buttonGroupSequenceFilter.add(radioButtonSequenceFilterNone);
    buttonGroupSequenceFilter.add(radioButtonSequenceFilterContains);
    buttonGroupSequenceFilter.add(radioButtonSequenceFilterStartWith);
    buttonGroupSequenceFilter.add(radioButtonSequenceFilterEndWith);
    panelSequenceInnerRight.add(radioButtonSequenceFilterNone);
    panelSequenceInnerRight.add(radioButtonSequenceFilterContains);
    panelSequenceInnerRight.add(radioButtonSequenceFilterStartWith);
    panelSequenceInnerRight.add(radioButtonSequenceFilterEndWith);
    textFieldSequenceFilter.setEditable(false);
    panelSequenceInnerRight.add(textFieldSequenceFilter);

    var borderPanel2 = new JPanel();
    borderPanel2.setPreferredSize(new Dimension(DialogExport.WIDTH - 20, 10));
    borderPanel2.setBorder(new ErDottedUnderlineBorder());
    add(borderPanel2);

    // panel - table, foreign key
    var panelTable = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
    panelTable.setPreferredSize(
        new Dimension(DialogExport.WIDTH - 20, DialogExport.HEIGHT / 20 * 4));
    add(panelTable);

    var panelTableInnerLeft = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
    panelTableInnerLeft.setPreferredSize(
        new Dimension(DialogExport.WIDTH / 5, DialogExport.HEIGHT / 20 * 4));
    panelTable.add(panelTableInnerLeft);

    labelTable.setPreferredSize(
        new Dimension(DialogExport.WIDTH / 4, DialogExport.HEIGHT / 20));
    panelTableInnerLeft.add(labelTable);

    var emptyPanel1 = new JPanel();
    emptyPanel1.setPreferredSize(new Dimension(DialogExport.WIDTH / 5, 0));
    panelTableInnerLeft.add(emptyPanel1);

    labelForeignKey.setPreferredSize(
        new Dimension(DialogExport.WIDTH / 4, DialogExport.HEIGHT / 20));
    panelTableInnerLeft.add(labelForeignKey);

    var panelTableInnerRight = new JPanel(new FlowLayout(FlowLayout.LEFT, 2, 0));
    panelTableInnerRight.setPreferredSize(
        new Dimension(DialogExport.WIDTH / 5 * 3, DialogExport.HEIGHT / 20 * 4));
    panelTable.add(panelTableInnerRight);

    var buttonGroupTable = new ButtonGroup();
    buttonGroupTable.add(radioButtonTableYes);
    buttonGroupTable.add(radioButtonTableNo);
    radioButtonTableYes.setPreferredSize(
        new Dimension(DialogExport.WIDTH / 8, DialogExport.HEIGHT / 20));
    panelTableInnerRight.add(radioButtonTableYes);
    radioButtonTableNo.setPreferredSize(
        new Dimension(DialogExport.WIDTH / 8, DialogExport.HEIGHT / 20));
    panelTableInnerRight.add(radioButtonTableNo);

    var emptyPanel2 = new JPanel();
    emptyPanel2.setPreferredSize(new Dimension(DialogExport.WIDTH / 5 * 3, 0));
    panelTableInnerRight.add(emptyPanel2);

    var buttonGroupForeignKey = new ButtonGroup();
    buttonGroupForeignKey.add(radioButtonForeignKeyYes);
    buttonGroupForeignKey.add(radioButtonForeignKeyNo);
    radioButtonForeignKeyYes.setPreferredSize(
        new Dimension(DialogExport.WIDTH / 8, DialogExport.HEIGHT / 20));
    panelTableInnerRight.add(radioButtonForeignKeyYes);
    radioButtonForeignKeyNo.setPreferredSize(
        new Dimension(DialogExport.WIDTH / 8, DialogExport.HEIGHT / 20));
    panelTableInnerRight.add(radioButtonForeignKeyNo);

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
    radioButtonTableFilterNone.setActionCommand(TmpDdl.FILTER_NONE);
    radioButtonTableFilterContains.setActionCommand(TmpDdl.FILTER_CONTAINS);
    radioButtonTableFilterStartWith.setActionCommand(TmpDdl.FILTER_START_WITH);
    radioButtonTableFilterEndWith.setActionCommand(TmpDdl.FILTER_END_WITH);
    buttonGroupTableFilter.add(radioButtonTableFilterNone);
    buttonGroupTableFilter.add(radioButtonTableFilterContains);
    buttonGroupTableFilter.add(radioButtonTableFilterStartWith);
    buttonGroupTableFilter.add(radioButtonTableFilterEndWith);
    panelTableInnerRight.add(radioButtonTableFilterNone);
    panelTableInnerRight.add(radioButtonTableFilterContains);
    panelTableInnerRight.add(radioButtonTableFilterStartWith);
    panelTableInnerRight.add(radioButtonTableFilterEndWith);
    textFieldTableFilter.setEditable(false);
    panelTableInnerRight.add(textFieldTableFilter);

    var borderPanel3 = new JPanel();
    borderPanel3.setPreferredSize(new Dimension(DialogExport.WIDTH - 20, 10));
    borderPanel3.setBorder(new ErDottedUnderlineBorder());
    add(borderPanel3);

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
        var format = "sql";
        var dotFormat = "." + format;
        var dir = System.getProperty("user.home");
        var file = String.format("%s%s", cfgProject.name, dotFormat);
        var sqlPath = Utils.getString(textFieldSavePath);
        if (!Utils.isNullOrEmpty(sqlPath)) {
          dir = new File(sqlPath).getParent();
          file = new File(sqlPath).getName();
        } else {
          var lastDdlSavePath = cfgProject.lastDdlSavePath;
          if (!Utils.isNullOrEmpty(lastDdlSavePath)) {
            dir = new File(lastDdlSavePath).getParent();
            file = new File(lastDdlSavePath).getName();
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

    var borderPanel4 = new JPanel();
    borderPanel4.setPreferredSize(new Dimension(DialogExport.WIDTH - 20, 10));
    borderPanel4.setBorder(new ErUnderlineBorder());
    add(borderPanel4);

    // panel - choose from history
    var panelChooseFromHistory = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
    panelChooseFromHistory.setPreferredSize(
        new Dimension(DialogExport.WIDTH - 20, DialogExport.HEIGHT / 20 * 6));
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
          // schema
          textFieldSchema.setText(Utils.getString(table.getValueAt(r, 1)));

          // sequence
          var sequenceYesNo = Utils.getString(table.getValueAt(r, 2));
          if (sequenceYesNo.equals("Yes")) {
            radioButtonSequenceYes.setSelected(true);
          } else {
            radioButtonSequenceNo.setSelected(true);
          }

          // sequence filter
          var sequenceFilter = TmpDdl.FILTER_NONE;
          var sequenceFilterValue = "";
          var sequenceFilterComplex = Utils.getString(table.getValueAt(r, 3));
          var sequenceFilterPos = sequenceFilterComplex.indexOf(SEPARATOR);
          if (sequenceFilterPos >= 0) {
            sequenceFilter = sequenceFilterComplex.substring(0, sequenceFilterPos);
            sequenceFilterValue = sequenceFilterComplex.substring(
                sequenceFilterPos + SEPARATOR.length());
          }
          switch (sequenceFilter) {
            case TmpDdl.FILTER_NONE:
              radioButtonSequenceFilterNone.setSelected(true);
              break;
            case TmpDdl.FILTER_CONTAINS:
              radioButtonSequenceFilterContains.setSelected(true);
              break;
            case TmpDdl.FILTER_START_WITH:
              radioButtonSequenceFilterStartWith.setSelected(true);
              break;
            case TmpDdl.FILTER_END_WITH:
              radioButtonSequenceFilterEndWith.setSelected(true);
              break;
          }
          textFieldSequenceFilter.setText(sequenceFilterValue);

          // table
          var tableYesNo = Utils.getString(table.getValueAt(r, 4));
          if (tableYesNo.equals("Yes")) {
            radioButtonTableYes.setSelected(true);
          } else {
            radioButtonTableNo.setSelected(true);
          }

          // foreign key
          var foreignKeyYesNo = Utils.getString(table.getValueAt(r, 5));
          if (foreignKeyYesNo.equals("Yes")) {
            radioButtonForeignKeyYes.setSelected(true);
          } else {
            radioButtonForeignKeyNo.setSelected(true);
          }

          // table filter
          var tableFilter = TmpDdl.FILTER_NONE;
          var tableFilterValue = "";
          var tableFilterComplex = Utils.getString(table.getValueAt(r, 6));
          var tableFilterPos = tableFilterComplex.indexOf(SEPARATOR);
          if (tableFilterPos >= 0) {
            tableFilter = tableFilterComplex.substring(0, tableFilterPos);
            tableFilterValue = tableFilterComplex.substring(tableFilterPos + SEPARATOR.length());
          }
          switch (tableFilter) {
            case TmpDdl.FILTER_NONE:
              radioButtonTableFilterNone.setSelected(true);
              break;
            case TmpDdl.FILTER_CONTAINS:
              radioButtonTableFilterContains.setSelected(true);
              break;
            case TmpDdl.FILTER_START_WITH:
              radioButtonTableFilterStartWith.setSelected(true);
              break;
            case TmpDdl.FILTER_END_WITH:
              radioButtonTableFilterEndWith.setSelected(true);
              break;
          }
          textFieldTableFilter.setText(tableFilterValue);

          // save path
          textFieldSavePath.setText(Utils.getString(table.getValueAt(r, 7)));
        }
      }
    });
    var sp = new JScrollPane(table);
    sp.setPreferredSize(new Dimension(DialogExport.WIDTH - 20, DialogExport.HEIGHT / 20 * 4));
    panelChooseFromHistory.add(sp);

    // button remove
    var panelRemove = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 10));
    panelChooseFromHistory.add(panelRemove);
    buttonRemove.addActionListener(actionEvent -> {
      var selectedRow = table.getSelectedRow();
      if (selectedRow >= 0) {
        var selected = Utils.getString(table.getValueAt(selectedRow, 0));
        var newHistories = cfgProject.ddlHistories.stream()
            .filter(h -> !h.exported.equals(selected))
            .toList();
        cfgProject.ddlHistories = newHistories;
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

    for (int i = 0; i < cfgProject.ddlHistories.size(); i++) {
      tableModel.setRowCount(i + 1);

      var history = cfgProject.ddlHistories.get(i);
      table.setValueAt(history.exported, i, 0);
      table.setValueAt(history.ddl.schemaValue, i, 1);

      table.setValueAt(history.ddl.selectedSequence ? "Yes" : "No", i, 2);
      var sFilter = history.ddl.filterSequenceActionCommand;
      if (!Utils.isNullOrEmpty(history.ddl.filterSequenceValue)) {
        sFilter += String.format("%s%s", SEPARATOR, history.ddl.filterSequenceValue);
      }
      table.setValueAt(sFilter, i, 3);

      table.setValueAt(history.ddl.selectedTable ? "Yes" : "No", i, 4);
      table.setValueAt(history.ddl.selectedForeignKey ? "Yes" : "No", i, 5);
      var tFilter = history.ddl.filterTableActionCommand;
      if (!Utils.isNullOrEmpty(history.ddl.filterTableValue)) {
        tFilter += String.format("%s%s", SEPARATOR, history.ddl.filterTableValue);
      }
      table.setValueAt(tFilter, i, 6);

      table.setValueAt(history.ddl.savePath, i, 7);
    }
  }

  /**
   * get result.
   *
   * @return result
   */
  public TmpResult<TmpDdl> getResult() {
    var tmpDdl = new TmpDdl();
    tmpDdl.schemaValue = Utils.getString(textFieldSchema);
    if (radioButtonSequenceYes.isSelected()) {
      tmpDdl.selectedSequence = true;
    }
    if (radioButtonTableYes.isSelected()) {
      tmpDdl.selectedTable = true;
    }
    if (radioButtonForeignKeyYes.isSelected()) {
      tmpDdl.selectedForeignKey = true;
    }

    tmpDdl.filterSequenceActionCommand = buttonGroupSequenceFilter.getSelection()
        .getActionCommand();
    tmpDdl.filterSequenceValue = Utils.getString(textFieldSequenceFilter);
    tmpDdl.filterTableActionCommand = buttonGroupTableFilter.getSelection().getActionCommand();
    tmpDdl.filterTableValue = Utils.getString(textFieldTableFilter);

    tmpDdl.savePath = Utils.getString(textFieldSavePath);

    return new TmpResult<TmpDdl>(tmpDdl) {
      @Override
      protected void validate() throws Exception {
        for (var tmp : tmpList) {
          if (Utils.isNullOrEmpty(tmp.savePath)) {
            throw new Exception("Required 'Save path'.");
          }
        }
      }
    };
  }
}
