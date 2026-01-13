package com.walksocket.er.component.edit.dict.columnaliases;

import com.walksocket.er.Size.DialogLarge;
import com.walksocket.er.component.EditDictColumnAliases;
import com.walksocket.er.component.edit.dict.columnaliases.root.Form;
import java.awt.Component;
import java.awt.Dimension;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

/**
 * Root.
 */
public class Root extends JPanel {

  /**
   * editDictColumnAliases.
   */
  private final EditDictColumnAliases editDictColumnAliases;

  /**
   * form.
   */
  private final Form form;

  /**
   * Constructor.
   *
   * @param editDictColumnAliases editDictColumnAliases
   */
  public Root(EditDictColumnAliases editDictColumnAliases) {
    this.editDictColumnAliases = editDictColumnAliases;

    // layout
    setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

    // panel - form
    form = new Form(this);
    form.setPreferredSize(new Dimension(DialogLarge.WIDTH - 20, DialogLarge.HEIGHT));
    form.setAlignmentX(Component.LEFT_ALIGNMENT);
    add(form);
  }

  /**
   * get column aliases.
   *
   * @return edit column aliases
   */
  public EditDictColumnAliases getEditColumnAliases() {
    return editDictColumnAliases;
  }
}
