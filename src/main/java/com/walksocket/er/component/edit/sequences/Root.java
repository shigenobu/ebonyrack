package com.walksocket.er.component.edit.sequences;

import com.walksocket.er.Size.DialogLarge;
import com.walksocket.er.component.EditSequences;
import com.walksocket.er.component.edit.sequences.root.Form;
import java.awt.Component;
import java.awt.Dimension;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

/**
 * Root.
 */
public class Root extends JPanel {

  /**
   * editSequences.
   */
  private final EditSequences editSequences;

  /**
   * form.
   */
  private final Form form;

  /**
   * Constructor.
   *
   * @param editSequences editSequences
   */
  public Root(EditSequences editSequences) {
    this.editSequences = editSequences;

    // layout
    setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

    // panel - form
    form = new Form(this);
    form.setPreferredSize(new Dimension(DialogLarge.WIDTH - 20, DialogLarge.HEIGHT));
    form.setAlignmentX(Component.LEFT_ALIGNMENT);
    add(form);
  }

  /**
   * get edit sequences.
   *
   * @return edit sequences
   */
  public EditSequences getEditSequences() {
    return editSequences;
  }
}
