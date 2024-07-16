package com.walksocket.er.component.export.ddl.root;

import com.walksocket.er.Size.DialogSmall;
import com.walksocket.er.Utils;
import com.walksocket.er.custom.ErUnderlineBorder;
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
   * Constructor.
   */
  public Form() {
    // panel - use
    var panelSchema = new JPanel(new FlowLayout(FlowLayout.LEFT));
    panelSchema.setPreferredSize(
        new Dimension(DialogSmall.WIDTH - 20, DialogSmall.HEIGHT / 15 + 10));
    add(panelSchema);

    var panelSchemaInnerLeft = new JPanel(new FlowLayout(FlowLayout.LEFT));
    panelSchemaInnerLeft.setPreferredSize(
        new Dimension(DialogSmall.WIDTH / 5, DialogSmall.HEIGHT / 15 + 10));
    panelSchema.add(panelSchemaInnerLeft);

    labelSchema.setPreferredSize(
        new Dimension(DialogSmall.WIDTH / 5, DialogSmall.HEIGHT / 15 - 10));
    panelSchemaInnerLeft.add(labelSchema);

    var panelSchemaInnerRight = new JPanel(new FlowLayout(FlowLayout.LEFT));
    panelSchemaInnerRight.setPreferredSize(
        new Dimension(DialogSmall.WIDTH / 5 * 3, DialogSmall.HEIGHT / 15 + 10));
    panelSchema.add(panelSchemaInnerRight);

    panelSchemaInnerRight.add(textFieldSchema);

    var borderPanel1 = new JPanel();
    borderPanel1.setPreferredSize(new Dimension(DialogSmall.WIDTH - 20, 10));
    borderPanel1.setBorder(new ErUnderlineBorder());
    add(borderPanel1);

    // panel - sequence
    var panelSequence = new JPanel(new FlowLayout(FlowLayout.LEFT));
    panelSequence.setPreferredSize(
        new Dimension(DialogSmall.WIDTH - 20, DialogSmall.HEIGHT / 15 * 4));
    add(panelSequence);

    var panelSequenceInnerLeft = new JPanel(new FlowLayout(FlowLayout.LEFT));
    panelSequenceInnerLeft.setPreferredSize(
        new Dimension(DialogSmall.WIDTH / 5, DialogSmall.HEIGHT / 15 * 4));
    panelSequence.add(panelSequenceInnerLeft);

    labelSequence.setPreferredSize(
        new Dimension(DialogSmall.WIDTH / 5, DialogSmall.HEIGHT / 15));
    panelSequenceInnerLeft.add(labelSequence);

    var panelSequenceInnerRight = new JPanel(new FlowLayout(FlowLayout.LEFT));
    panelSequenceInnerRight.setPreferredSize(
        new Dimension(DialogSmall.WIDTH / 5 * 3, DialogSmall.HEIGHT / 15 * 4));
    panelSequence.add(panelSequenceInnerRight);

    var buttonGroupSequence = new ButtonGroup();
    buttonGroupSequence.add(radioButtonSequenceYes);
    buttonGroupSequence.add(radioButtonSequenceNo);
    radioButtonSequenceYes.setPreferredSize(
        new Dimension(DialogSmall.WIDTH / 8, DialogSmall.HEIGHT / 15));
    panelSequenceInnerRight.add(radioButtonSequenceYes);
    radioButtonSequenceNo.setPreferredSize(
        new Dimension(DialogSmall.WIDTH / 8, DialogSmall.HEIGHT / 15));
    panelSequenceInnerRight.add(radioButtonSequenceNo);

    labelFilterSequence.setPreferredSize(
        new Dimension(DialogSmall.WIDTH / 5 * 3, labelFilterSequence.getFont().getSize() * 2));
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
    borderPanel2.setPreferredSize(new Dimension(DialogSmall.WIDTH - 20, 10));
    borderPanel2.setBorder(new ErUnderlineBorder());
    add(borderPanel2);

    // panel - table, foreign key
    var panelTable = new JPanel(new FlowLayout(FlowLayout.LEFT));
    panelTable.setPreferredSize(new Dimension(DialogSmall.WIDTH - 20, DialogSmall.HEIGHT / 15 * 7));
    add(panelTable);

    var panelTableInnerLeft = new JPanel(new FlowLayout(FlowLayout.LEFT));
    panelTableInnerLeft.setPreferredSize(
        new Dimension(DialogSmall.WIDTH / 5, DialogSmall.HEIGHT / 15 * 7));
    panelTable.add(panelTableInnerLeft);

    labelTable.setPreferredSize(
        new Dimension(DialogSmall.WIDTH / 4, DialogSmall.HEIGHT / 15));
    panelTableInnerLeft.add(labelTable);

    var emptyPanel1 = new JPanel();
    emptyPanel1.setPreferredSize(new Dimension(DialogSmall.WIDTH / 5, 0));
    panelTableInnerLeft.add(emptyPanel1);

    labelForeignKey.setPreferredSize(
        new Dimension(DialogSmall.WIDTH / 4, DialogSmall.HEIGHT / 15));
    panelTableInnerLeft.add(labelForeignKey);

    var panelTableInnerRight = new JPanel(new FlowLayout(FlowLayout.LEFT));
    panelTableInnerRight.setPreferredSize(
        new Dimension(DialogSmall.WIDTH / 5 * 3, DialogSmall.HEIGHT / 15 * 7));
    panelTable.add(panelTableInnerRight);

    var buttonGroupTable = new ButtonGroup();
    buttonGroupTable.add(radioButtonTableYes);
    buttonGroupTable.add(radioButtonTableNo);
    radioButtonTableYes.setPreferredSize(
        new Dimension(DialogSmall.WIDTH / 8, DialogSmall.HEIGHT / 15));
    panelTableInnerRight.add(radioButtonTableYes);
    radioButtonTableNo.setPreferredSize(
        new Dimension(DialogSmall.WIDTH / 8, DialogSmall.HEIGHT / 15));
    panelTableInnerRight.add(radioButtonTableNo);

    var emptyPanel2 = new JPanel();
    emptyPanel2.setPreferredSize(new Dimension(DialogSmall.WIDTH / 5 * 3, 0));
    panelTableInnerRight.add(emptyPanel2);

    var buttonGroupForeignKey = new ButtonGroup();
    buttonGroupForeignKey.add(radioButtonForeignKeyYes);
    buttonGroupForeignKey.add(radioButtonForeignKeyNo);
    radioButtonForeignKeyYes.setPreferredSize(
        new Dimension(DialogSmall.WIDTH / 8, DialogSmall.HEIGHT / 15));
    panelTableInnerRight.add(radioButtonForeignKeyYes);
    radioButtonForeignKeyNo.setPreferredSize(
        new Dimension(DialogSmall.WIDTH / 8, DialogSmall.HEIGHT / 15));
    panelTableInnerRight.add(radioButtonForeignKeyNo);

    labelFilterTable.setPreferredSize(
        new Dimension(DialogSmall.WIDTH / 5 * 3, labelFilterTable.getFont().getSize() * 2));
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

    return new TmpResult<TmpDdl>(tmpDdl) {
      @Override
      protected void validate() throws Exception {
      }
    };
  }
}
