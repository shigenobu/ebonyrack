package com.walksocket.er.component.export.ddl.root;

import com.walksocket.er.Size.DialogSmall;
import com.walksocket.er.component.export.ddl.Root;
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
   * Constructor.
   *
   * @param root root
   */
  public Form(Root root) {
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


  }
}
