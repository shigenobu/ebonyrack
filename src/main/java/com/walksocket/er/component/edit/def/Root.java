package com.walksocket.er.component.edit.def;

import com.walksocket.er.Log;
import com.walksocket.er.Size.DialogSmall;
import com.walksocket.er.component.EditDefault;
import com.walksocket.er.component.edit.def.root.Form;
import com.walksocket.er.sqlite.Bucket;
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
   * edit default.
   */
  private final EditDefault editDefault;

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
   * @param editDefault editDefault
   */
  public Root(EditDefault editDefault) {
    this.editDefault = editDefault;

    // layout
    setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

    // panel - form
    form = new Form();
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
        editDefault.dispose();
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
      var tmp = result.getTmpList().get(0);
      Bucket.getInstance().getBucketDefault().save(tmp);

      // load
      editDefault.getMain().load();

      return true;

    } catch (Exception e) {
      Log.error(e);
      JOptionPane.showMessageDialog(this, e.getMessage());
    }

    return false;
  }
}
