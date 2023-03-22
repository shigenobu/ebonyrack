package com.walksocket.er.component.input.sequence.root;

import com.walksocket.er.Size.DialogSmall;
import com.walksocket.er.Utils;
import com.walksocket.er.Word;
import com.walksocket.er.component.input.sequence.Root;
import com.walksocket.er.definition.Cycle;
import com.walksocket.er.sqlite.Tmp;
import com.walksocket.er.sqlite.TmpResult;
import com.walksocket.er.sqlite.context.CtxSequence;
import com.walksocket.er.sqlite.tmp.TmpSequence;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.JTextComponent;

/**
 * Form.
 */
public class Form extends JPanel {

  /**
   * root.
   */
  private final Root root;

  /**
   * ctx sequence.
   */
  private final CtxSequence ctxSequence;

  /**
   * label sequence name.
   */
  private final JLabel labelSequenceName = new JLabel("Sequence name:");

  /**
   * text field sequence name.
   */
  private final JTextField textFieldSequenceName = new JTextField(30);

  /**
   * label start value.
   */
  private final JLabel labelStartValue = new JLabel("Start value:");

  /**
   * text field start value.
   */
  private final JTextField textFieldStartValue = new JTextField(30);

  /**
   * label minimum value.
   */
  private final JLabel labelMinimumValue = new JLabel("Minimum value:");

  /**
   * text field minimum value.
   */
  private final JTextField textFieldMinimumValue = new JTextField(30);

  /**
   * label maximum value.
   */
  private final JLabel labelMaximumValue = new JLabel("Maximum value:");

  /**
   * text field maximum value.
   */
  private final JTextField textFieldMaximumValue = new JTextField(30);

  /**
   * label increment value.
   */
  private final JLabel labelIncrementValue = new JLabel("Increment value:");

  /**
   * text field increment value.
   */
  private final JTextField textFieldIncrementValue = new JTextField(30);

  /**
   * label cache size.
   */
  private final JLabel labelCacheSize = new JLabel("Cache size:");

  /**
   * text field cache size.
   */
  private final JTextField textFieldCacheSize = new JTextField(30);

  /**
   * label cycle.
   */
  private final JLabel labelCycle = new JLabel("Cycle:");

  /**
   * combo box cycle.
   */
  private final JComboBox comboBoxCycle = new JComboBox(
      new DefaultComboBoxModel(Cycle.getCycleListForColumn().toArray()));

  /**
   * Constructor.
   *
   * @param root        root
   * @param ctxSequence ctxSequence
   */
  public Form(Root root, CtxSequence ctxSequence) {
    this.root = root;
    this.ctxSequence = ctxSequence;

    // panel - sequence name
    var panel1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
    panel1.setPreferredSize(new Dimension(DialogSmall.WIDTH - 20, DialogSmall.HEIGHT / 12));
    add(panel1);
    labelSequenceName.setPreferredSize(
        new Dimension(DialogSmall.WIDTH / 4, DialogSmall.HEIGHT / 12));
    panel1.add(labelSequenceName);
    textFieldSequenceName.addFocusListener(new FocusAdapter() {
      @Override
      public void focusGained(FocusEvent e) {
        ((JTextComponent) e.getComponent()).selectAll();
      }
    });
    panel1.add(textFieldSequenceName);

    // panel - start value
    var panel2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
    panel2.setPreferredSize(new Dimension(DialogSmall.WIDTH - 20, DialogSmall.HEIGHT / 12));
    add(panel2);
    labelStartValue.setPreferredSize(
        new Dimension(DialogSmall.WIDTH / 4, DialogSmall.HEIGHT / 12));
    panel2.add(labelStartValue);
    panel2.add(textFieldStartValue);

    // panel - minimum value
    var panel3 = new JPanel(new FlowLayout(FlowLayout.LEFT));
    panel3.setPreferredSize(new Dimension(DialogSmall.WIDTH - 20, DialogSmall.HEIGHT / 12));
    add(panel3);
    labelMinimumValue.setPreferredSize(
        new Dimension(DialogSmall.WIDTH / 4, DialogSmall.HEIGHT / 12));
    panel3.add(labelMinimumValue);
    panel3.add(textFieldMinimumValue);

    // panel - maximum value
    var panel4 = new JPanel(new FlowLayout(FlowLayout.LEFT));
    panel4.setPreferredSize(new Dimension(DialogSmall.WIDTH - 20, DialogSmall.HEIGHT / 12));
    add(panel4);
    labelMaximumValue.setPreferredSize(
        new Dimension(DialogSmall.WIDTH / 4, DialogSmall.HEIGHT / 12));
    panel4.add(labelMaximumValue);
    panel4.add(textFieldMaximumValue);

    // panel - increment value
    var panel5 = new JPanel(new FlowLayout(FlowLayout.LEFT));
    panel5.setPreferredSize(new Dimension(DialogSmall.WIDTH - 20, DialogSmall.HEIGHT / 12));
    add(panel5);
    labelIncrementValue.setPreferredSize(
        new Dimension(DialogSmall.WIDTH / 4, DialogSmall.HEIGHT / 12));
    panel5.add(labelIncrementValue);
    panel5.add(textFieldIncrementValue);

    // panel - cache size
    var panel6 = new JPanel(new FlowLayout(FlowLayout.LEFT));
    panel6.setPreferredSize(new Dimension(DialogSmall.WIDTH - 20, DialogSmall.HEIGHT / 12));
    add(panel6);
    labelCacheSize.setPreferredSize(
        new Dimension(DialogSmall.WIDTH / 4, DialogSmall.HEIGHT / 12));
    panel6.add(labelCacheSize);
    panel6.add(textFieldCacheSize);

    // panel - cycle
    var panel7 = new JPanel(new FlowLayout(FlowLayout.LEFT));
    panel7.setPreferredSize(new Dimension(DialogSmall.WIDTH - 20, DialogSmall.HEIGHT / 12));
    add(panel7);
    labelCycle.setPreferredSize(
        new Dimension(DialogSmall.WIDTH / 4, DialogSmall.HEIGHT / 12));
    panel7.add(labelCycle);
    panel7.add(comboBoxCycle);

    // tmp sequence
    var tmpSequence = Tmp.createTmpSequence(ctxSequence.dbSequence);
    setTmpSequence(tmpSequence);
  }

  /**
   * set tmp sequence.
   *
   * @param tmpSequence tmpSequence
   */
  private void setTmpSequence(TmpSequence tmpSequence) {
    textFieldSequenceName.setText(tmpSequence.sequenceName);
    textFieldStartValue.setText(tmpSequence.startValue);
    textFieldMinimumValue.setText(tmpSequence.minimumValue);
    textFieldMaximumValue.setText(tmpSequence.maximumValue);
    textFieldIncrementValue.setText(tmpSequence.incrementValue);
    textFieldCacheSize.setText(tmpSequence.cacheSize);
    comboBoxCycle.setSelectedItem(tmpSequence.cycle);
  }

  /**
   * get tmp sequence.
   *
   * @return tmp sequence
   */
  private TmpSequence getTmpSequence() {
    var tmpSequence = new TmpSequence();
    tmpSequence.sequenceName = Utils.getString(textFieldSequenceName);
    tmpSequence.startValue = Utils.getString(textFieldStartValue);
    tmpSequence.minimumValue = Utils.getString(textFieldMinimumValue);
    tmpSequence.maximumValue = Utils.getString(textFieldMaximumValue);
    tmpSequence.incrementValue = Utils.getString(textFieldIncrementValue);
    tmpSequence.cacheSize = Utils.getString(textFieldCacheSize);
    tmpSequence.cycle = Utils.getString(comboBoxCycle);
    return tmpSequence;
  }

  /**
   * get result.
   *
   * @return result
   */
  public TmpResult<TmpSequence> getResult() {
    var tmpSequence = getTmpSequence();

    return new TmpResult<TmpSequence>(tmpSequence) {
      @Override
      protected void validate() throws Exception {
        for (var tmp : tmpList) {
          if (Utils.isNullOrEmpty(tmp.sequenceName)) {
            throw new Exception("Required 'Sequence name'.");
          }
          if (!Word.isValid(tmp.sequenceName)) {
            throw new Exception("Invalid 'Sequence name'.");
          }

          if (Utils.isNullOrEmpty(tmp.cycle)) {
            tmp.cycle = Cycle.NOCYCLE_VALUE;
          }
        }
      }
    };
  }
}
