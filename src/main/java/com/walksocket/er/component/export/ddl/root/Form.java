package com.walksocket.er.component.export.ddl.root;

import com.walksocket.er.Size.DialogSmall;
import com.walksocket.er.Utils;
import com.walksocket.er.sqlite.TmpResult;
import com.walksocket.er.sqlite.tmp.TmpDdl;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ItemEvent;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

/**
 * Form.
 */
public class Form extends JPanel {

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
   * Constructor.
   */
  public Form() {
    // panel - sequence
    var panel1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
    panel1.setPreferredSize(new Dimension(DialogSmall.WIDTH - 20, DialogSmall.HEIGHT / 10 * 3));
    add(panel1);

    var panel1innerLeft = new JPanel(new FlowLayout(FlowLayout.LEFT));
    panel1innerLeft.setPreferredSize(
        new Dimension(DialogSmall.WIDTH / 5, DialogSmall.HEIGHT / 10 * 3));
    panel1.add(panel1innerLeft);

    labelSequence.setPreferredSize(
        new Dimension(DialogSmall.WIDTH / 5, DialogSmall.HEIGHT / 10));
    panel1innerLeft.add(labelSequence);

    var panel1innerRight = new JPanel(new FlowLayout(FlowLayout.LEFT));
    panel1innerRight.setPreferredSize(
        new Dimension(DialogSmall.WIDTH / 5 * 3, DialogSmall.HEIGHT / 10 * 3));
    panel1.add(panel1innerRight);

    var buttonGroupSequence = new ButtonGroup();
    buttonGroupSequence.add(radioButtonSequenceYes);
    buttonGroupSequence.add(radioButtonSequenceNo);
    radioButtonSequenceYes.setPreferredSize(
        new Dimension(DialogSmall.WIDTH / 8, DialogSmall.HEIGHT / 10));
    panel1innerRight.add(radioButtonSequenceYes);
    radioButtonSequenceNo.setPreferredSize(
        new Dimension(DialogSmall.WIDTH / 8, DialogSmall.HEIGHT / 10));
    panel1innerRight.add(radioButtonSequenceNo);

    labelFilterSequence.setPreferredSize(
        new Dimension(DialogSmall.WIDTH / 5 * 3, labelFilterSequence.getFont().getSize() * 2));
    panel1innerRight.add(labelFilterSequence);

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
    panel1innerRight.add(radioButtonSequenceFilterNone);
    panel1innerRight.add(radioButtonSequenceFilterContains);
    panel1innerRight.add(radioButtonSequenceFilterStartWith);
    panel1innerRight.add(radioButtonSequenceFilterEndWith);
    textFieldSequenceFilter.setEditable(false);
    panel1innerRight.add(textFieldSequenceFilter);

    // panel - table, foreign key
    var panel2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
    panel2.setPreferredSize(new Dimension(DialogSmall.WIDTH - 20, DialogSmall.HEIGHT / 10 * 5));
    add(panel2);

    var panel2innerLeft = new JPanel(new FlowLayout(FlowLayout.LEFT));
    panel2innerLeft.setPreferredSize(
        new Dimension(DialogSmall.WIDTH / 5, DialogSmall.HEIGHT / 10 * 5));
    panel2.add(panel2innerLeft);

    labelTable.setPreferredSize(
        new Dimension(DialogSmall.WIDTH / 4, DialogSmall.HEIGHT / 10));
    panel2innerLeft.add(labelTable);

    var emptyPanel1 = new JPanel();
    emptyPanel1.setPreferredSize(new Dimension(DialogSmall.WIDTH / 5, 0));
    panel2innerLeft.add(emptyPanel1);

    labelForeignKey.setPreferredSize(
        new Dimension(DialogSmall.WIDTH / 4, DialogSmall.HEIGHT / 10));
    panel2innerLeft.add(labelForeignKey);

    var panel2innerRight = new JPanel(new FlowLayout(FlowLayout.LEFT));
    panel2innerRight.setPreferredSize(
        new Dimension(DialogSmall.WIDTH / 5 * 3, DialogSmall.HEIGHT / 10 * 5));
    panel2.add(panel2innerRight);

    var buttonGroupTable = new ButtonGroup();
    buttonGroupTable.add(radioButtonTableYes);
    buttonGroupTable.add(radioButtonTableNo);
    radioButtonTableYes.setPreferredSize(
        new Dimension(DialogSmall.WIDTH / 8, DialogSmall.HEIGHT / 10));
    panel2innerRight.add(radioButtonTableYes);
    radioButtonTableNo.setPreferredSize(
        new Dimension(DialogSmall.WIDTH / 8, DialogSmall.HEIGHT / 10));
    panel2innerRight.add(radioButtonTableNo);

    var emptyPanel2 = new JPanel();
    emptyPanel2.setPreferredSize(new Dimension(DialogSmall.WIDTH / 5 * 3, 0));
    panel2innerRight.add(emptyPanel2);

    var buttonGroupForeignKey = new ButtonGroup();
    buttonGroupForeignKey.add(radioButtonForeignKeyYes);
    buttonGroupForeignKey.add(radioButtonForeignKeyNo);
    radioButtonForeignKeyYes.setPreferredSize(
        new Dimension(DialogSmall.WIDTH / 8, DialogSmall.HEIGHT / 10));
    panel2innerRight.add(radioButtonForeignKeyYes);
    radioButtonForeignKeyNo.setPreferredSize(
        new Dimension(DialogSmall.WIDTH / 8, DialogSmall.HEIGHT / 10));
    panel2innerRight.add(radioButtonForeignKeyNo);

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
    radioButtonTableFilterNone.setActionCommand(TmpDdl.FILTER_NONE);
    radioButtonTableFilterContains.setActionCommand(TmpDdl.FILTER_CONTAINS);
    radioButtonTableFilterStartWith.setActionCommand(TmpDdl.FILTER_START_WITH);
    radioButtonTableFilterEndWith.setActionCommand(TmpDdl.FILTER_END_WITH);
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
  }

  /**
   * get result.
   *
   * @return result
   */
  public TmpResult<TmpDdl> getResult() {
    var tmpDdl = new TmpDdl();
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

    return new TmpResult<TmpDdl>(tmpDdl) {
      @Override
      protected void validate() throws Exception {
      }
    };
  }
}
