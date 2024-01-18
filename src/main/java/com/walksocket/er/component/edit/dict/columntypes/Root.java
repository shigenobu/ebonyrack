package com.walksocket.er.component.edit.dict.columntypes;

import com.walksocket.er.Size.DialogMedium;
import com.walksocket.er.component.EditDictColumnTypes;
import com.walksocket.er.component.edit.dict.columntypes.root.Form;
import java.awt.Component;
import java.awt.Dimension;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

/**
 * Root.
 */
public class Root extends JPanel {

  /**
   * editDictColumnTypes.
   */
  private final EditDictColumnTypes editDictColumnTypes;

  /**
   * form.
   */
  private final Form form;

  /**
   * Constructor.
   *
   * @param editDictColumnTypes editDictColumnTypes
   */
  public Root(EditDictColumnTypes editDictColumnTypes) {
    this.editDictColumnTypes = editDictColumnTypes;

    // layout
    setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

    // panel - form
    form = new Form(this);
    form.setPreferredSize(new Dimension(DialogMedium.WIDTH - 20, DialogMedium.HEIGHT));
    form.setAlignmentX(Component.LEFT_ALIGNMENT);
    add(form);
  }

  /**
   * get edit dict column types.
   *
   * @return edit dict column types
   */
  public EditDictColumnTypes getEditDictColumnTypes() {
    return editDictColumnTypes;
  }
}
