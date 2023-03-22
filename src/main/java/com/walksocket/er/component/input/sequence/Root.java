package com.walksocket.er.component.input.sequence;

import com.walksocket.er.Log;
import com.walksocket.er.Size.DialogSmall;
import com.walksocket.er.component.InputSequence;
import com.walksocket.er.component.input.sequence.root.Form;
import com.walksocket.er.sqlite.Bucket;
import com.walksocket.er.sqlite.context.CtxSequence;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * Root.
 */
public class Root extends JPanel {

  /**
   * input sequence.
   */
  private final InputSequence inputSequence;

  /**
   * ctx sequence.
   */
  private final CtxSequence ctxSequence;

  /**
   * form.
   */
  private final Form form;

  /**
   * button save.
   */
  private final JButton buttonSave = new JButton("Save");

  /**
   * button save and close.
   */
  private final JButton buttonSaveAndClose = new JButton("Save and close");

  /**
   * Constructor.
   *
   * @param inputSequence input sequence
   * @param ctxSequence   ctxSequence
   */
  public Root(InputSequence inputSequence, CtxSequence ctxSequence) {
    this.inputSequence = inputSequence;
    this.ctxSequence = ctxSequence;

    // layout
    setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

    // panel - form
    form = new Form(this, ctxSequence);
    form.setPreferredSize(new Dimension(DialogSmall.WIDTH - 20, DialogSmall.HEIGHT / 10 * 9));
    form.setAlignmentX(Component.LEFT_ALIGNMENT);
    add(form);

    // panel - button
    var panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    panel.setPreferredSize(new Dimension(DialogSmall.WIDTH - 20, DialogSmall.HEIGHT / 10));
    panel.setAlignmentX(Component.LEFT_ALIGNMENT);
    add(panel);
    buttonSave.addActionListener(actionEvent -> {
      save();
    });
    panel.add(buttonSave);
    buttonSaveAndClose.addActionListener(actionEvent -> {
      if (save()) {
        inputSequence.dispose();
        inputSequence.getSequence().requestFocusInWindow();
      }
    });
    panel.add(buttonSaveAndClose);
  }

  /**
   * save.
   *
   * @return success or fault
   */
  private boolean save() {
    try {
      // get result
      var result = form.getResult();

      // save
      var tmpSequence = result.getTmpList().get(0);
      Bucket.getInstance().getBucketSequence().save(ctxSequence, tmpSequence);

      // load
      inputSequence.load();
      inputSequence.getSequence().redraw();
      inputSequence.getSequence().getWorkspace().reloadSequence();

      return true;

    } catch (Exception e) {
      Log.error(e);
      JOptionPane.showMessageDialog(this, e.getMessage());
    }

    return false;
  }
}
