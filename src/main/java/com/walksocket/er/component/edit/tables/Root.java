package com.walksocket.er.component.edit.tables;

import com.walksocket.er.Size.DialogLarge;
import com.walksocket.er.component.EditTables;
import com.walksocket.er.component.edit.tables.root.Form;
import java.awt.Component;
import java.awt.Dimension;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

/**
 * Root.
 */
public class Root extends JPanel {

  /**
   * editTables.
   */
  private final EditTables editTables;

  /**
   * form.
   */
  private final Form form;

  /**
   * Constructor.
   *
   * @param editTables editTables
   */
  public Root(EditTables editTables) {
    this.editTables = editTables;

    // layout
    setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

    // panel - form
    form = new Form(this);
    form.setPreferredSize(new Dimension(DialogLarge.WIDTH - 20, DialogLarge.HEIGHT));
    form.setAlignmentX(Component.LEFT_ALIGNMENT);
    add(form);
  }

  /**
   * get edit tables.
   *
   * @return edit tables
   */
  public EditTables getEditTables() {
    return editTables;
  }
}
