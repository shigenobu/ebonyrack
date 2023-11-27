package com.walksocket.er.component.export.ddl.root;

import com.walksocket.er.Size.DialogSmall;
import com.walksocket.er.sqlite.TmpResult;
import com.walksocket.er.sqlite.tmp.TmpDdl;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

/**
 * Form.
 */
public class Form extends JPanel {

  /**
   * label sequence.
   */
  private final JLabel labelSequence = new JLabel("Sequence:");

  /**
   * button group sequence.
   */
  private final ButtonGroup buttonGroupSequence = new ButtonGroup();

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
   * button group table.
   */
  private final ButtonGroup buttonGroupTable = new ButtonGroup();

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
   * button group foreign key.
   */
  private final ButtonGroup buttonGroupForeignKey = new ButtonGroup();

  /**
   * radio button foreign key yes.
   */
  private final JRadioButton radioButtonForeignKeyYes = new JRadioButton("Yes", true);

  /**
   * radio button foreign key no.
   */
  private final JRadioButton radioButtonForeignKeyNo = new JRadioButton("No");

  /**
   * Constructor.
   */
  public Form() {
    // panel - sequence
    var panel1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
    panel1.setPreferredSize(new Dimension(DialogSmall.WIDTH - 20, DialogSmall.HEIGHT / 10));
    add(panel1);
    labelSequence.setPreferredSize(
        new Dimension(DialogSmall.WIDTH / 4, DialogSmall.HEIGHT / 10));
    panel1.add(labelSequence);
    buttonGroupSequence.add(radioButtonSequenceYes);
    buttonGroupSequence.add(radioButtonSequenceNo);
    radioButtonSequenceYes.setPreferredSize(
        new Dimension(DialogSmall.WIDTH / 8, radioButtonSequenceYes.getFont().getSize() * 2));
    panel1.add(radioButtonSequenceYes);
    radioButtonSequenceNo.setPreferredSize(
        new Dimension(DialogSmall.WIDTH / 8, radioButtonSequenceNo.getFont().getSize() * 2));
    panel1.add(radioButtonSequenceNo);

    // panel - table
    var panel2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
    panel2.setPreferredSize(new Dimension(DialogSmall.WIDTH - 20, DialogSmall.HEIGHT / 10));
    add(panel2);
    labelTable.setPreferredSize(
        new Dimension(DialogSmall.WIDTH / 4, DialogSmall.HEIGHT / 10));
    panel2.add(labelTable);
    buttonGroupTable.add(radioButtonTableYes);
    buttonGroupTable.add(radioButtonTableNo);
    radioButtonTableYes.setPreferredSize(
        new Dimension(DialogSmall.WIDTH / 8, radioButtonTableYes.getFont().getSize() * 2));
    panel2.add(radioButtonTableYes);
    radioButtonTableNo.setPreferredSize(
        new Dimension(DialogSmall.WIDTH / 8, radioButtonTableNo.getFont().getSize() * 2));
    panel2.add(radioButtonTableNo);

    // panel - foregin key
    var panel3 = new JPanel(new FlowLayout(FlowLayout.LEFT));
    panel3.setPreferredSize(new Dimension(DialogSmall.WIDTH - 20, DialogSmall.HEIGHT / 10));
    add(panel3);
    labelForeignKey.setPreferredSize(
        new Dimension(DialogSmall.WIDTH / 4, DialogSmall.HEIGHT / 10));
    panel3.add(labelForeignKey);
    buttonGroupForeignKey.add(radioButtonForeignKeyYes);
    buttonGroupForeignKey.add(radioButtonForeignKeyNo);
    radioButtonForeignKeyYes.setPreferredSize(
        new Dimension(DialogSmall.WIDTH / 8, radioButtonForeignKeyYes.getFont().getSize() * 2));
    panel3.add(radioButtonForeignKeyYes);
    radioButtonForeignKeyNo.setPreferredSize(
        new Dimension(DialogSmall.WIDTH / 8, radioButtonForeignKeyNo.getFont().getSize() * 2));
    panel3.add(radioButtonForeignKeyNo);
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

    return new TmpResult<TmpDdl>(tmpDdl) {
      @Override
      protected void validate() throws Exception {
      }
    };
  }
}
