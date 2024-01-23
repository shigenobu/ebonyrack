package com.walksocket.er.component.export.tableclass.root;

import com.walksocket.er.Env;
import com.walksocket.er.Size.DialogSmall;
import com.walksocket.er.Utils;
import com.walksocket.er.sqlite.TmpResult;
import com.walksocket.er.sqlite.tmp.TmpTableClass;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ItemEvent;
import java.io.File;
import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

/**
 * Form.
 */
public class Form extends JPanel {

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
   * text field table filter.
   */
  private final JTextField textFieldTableFilter = new JTextField(30);

  /**
   * label convert file.
   */
  private final JLabel labelConvertFileName = new JLabel("File name converter:");

  /**
   * text field file prefix converter.
   */
  private final JTextField textFieldFilePrefixConverter = new JTextField(10);

  /**
   * combo box file name converter.
   */
  private final JComboBox comboBoxFileNameConverter = new JComboBox(
      TmpTableClass.FILE_NAME_CONVERTER_LIST.toArray());

  /**
   * text field file suffix converter.
   */
  private final JTextField textFieldFileSuffixConverter = new JTextField(10);

  /**
   * text field file extension converter.
   */
  private final JTextField textFieldFileExtensionConverter = new JTextField(4);

  /**
   * Constructor.
   */
  public Form() {
    // panel - template
    var panel1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
    panel1.setPreferredSize(new Dimension(DialogSmall.WIDTH - 20, DialogSmall.HEIGHT / 10));
    add(panel1);

    var panel1innerLeft = new JPanel(new FlowLayout(FlowLayout.LEFT));
    panel1innerLeft.setPreferredSize(
        new Dimension(DialogSmall.WIDTH / 5, DialogSmall.HEIGHT / 10));
    panel1.add(panel1innerLeft);

    labelTemplate.setPreferredSize(
        new Dimension(DialogSmall.WIDTH / 5, DialogSmall.HEIGHT / 10));
    panel1innerLeft.add(labelTemplate);

    var panel1innerRight = new JPanel(new FlowLayout(FlowLayout.LEFT));
    panel1innerRight.setPreferredSize(
        new Dimension(DialogSmall.WIDTH / 5 * 3, DialogSmall.HEIGHT / 10));
    panel1.add(panel1innerRight);

    var templateDir = new File(Env.getTemplateDir());
    for (var file : templateDir.listFiles()) {
      if (file.getName().endsWith(".vm")) {
        comboBoxTemplate.addItem(file.getName());
      }
    }
    comboBoxTemplate.setPreferredSize(
        new Dimension(DialogSmall.WIDTH / 4, comboBoxTemplate.getFont().getSize() * 2));
    textFieldFileExtensionConverter.setText(getExtension(Utils.getString(comboBoxTemplate)));
    comboBoxTemplate.addActionListener(actionEvent -> {
      textFieldFileExtensionConverter.setText(getExtension(Utils.getString(comboBoxTemplate)));
    });
    panel1innerRight.add(comboBoxTemplate);

    // panel - table
    var panel2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
    panel2.setPreferredSize(new Dimension(DialogSmall.WIDTH - 20, DialogSmall.HEIGHT / 10 * 6));
    add(panel2);

    var panel2innerLeft = new JPanel(new FlowLayout(FlowLayout.LEFT));
    panel2innerLeft.setPreferredSize(
        new Dimension(DialogSmall.WIDTH / 5, DialogSmall.HEIGHT / 10 * 6));
    panel2.add(panel2innerLeft);

    labelTable.setPreferredSize(
        new Dimension(DialogSmall.WIDTH / 4, DialogSmall.HEIGHT / 10));
    panel2innerLeft.add(labelTable);

    var panel2innerRight = new JPanel(new FlowLayout(FlowLayout.LEFT));
    panel2innerRight.setPreferredSize(
        new Dimension(DialogSmall.WIDTH / 5 * 3, DialogSmall.HEIGHT / 10 * 6));
    panel2.add(panel2innerRight);

    labelFilterTable.setPreferredSize(
        new Dimension(DialogSmall.WIDTH / 5 * 3, labelFilterTable.getFont().getSize() * 2));
    panel2innerRight.add(labelFilterTable);

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
    buttonGroupTableFilter.add(radioButtonTableFilterNone);
    buttonGroupTableFilter.add(radioButtonTableFilterContains);
    buttonGroupTableFilter.add(radioButtonTableFilterStartWith);
    buttonGroupTableFilter.add(radioButtonTableFilterEndWith);
    panel2innerRight.add(radioButtonTableFilterNone);
    panel2innerRight.add(radioButtonTableFilterContains);
    panel2innerRight.add(radioButtonTableFilterStartWith);
    panel2innerRight.add(radioButtonTableFilterEndWith);
    textFieldTableFilter.setEditable(false);
    panel2innerRight.add(textFieldTableFilter);

    var emptyPanel2 = new JPanel();
    emptyPanel2.setPreferredSize(new Dimension(DialogSmall.WIDTH / 5 * 3, 0));
    panel2innerRight.add(emptyPanel2);

    labelConvertFileName.setPreferredSize(
        new Dimension(DialogSmall.WIDTH / 5 * 3, labelConvertFileName.getFont().getSize() * 2));
    panel2innerRight.add(labelConvertFileName);

    panel2innerRight.add(textFieldFilePrefixConverter);
    panel2innerRight.add(comboBoxFileNameConverter);
    panel2innerRight.add(textFieldFileSuffixConverter);

    textFieldFileExtensionConverter.setEditable(false);
    panel2innerRight.add(textFieldFileExtensionConverter);
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

    return new TmpResult<TmpTableClass>(tmpTableClass) {
      @Override
      protected void validate() throws Exception {
      }
    };
  }
}
