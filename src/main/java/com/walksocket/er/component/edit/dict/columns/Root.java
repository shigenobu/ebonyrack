package com.walksocket.er.component.edit.dict.columns;

import com.walksocket.er.Size.DialogMedium;
import com.walksocket.er.component.EditDictColumns;
import com.walksocket.er.component.edit.dict.columns.root.Form;
import java.awt.Component;
import java.awt.Dimension;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

/**
 * Root.
 */
public class Root extends JPanel {

  /**
   * editDictColumns.
   */
  private final EditDictColumns editDictColumns;

  /**
   * form.
   */
  private final Form form;

  /**
   * Constructor.
   *
   * @param editDictColumns editDictColumns
   */
  public Root(EditDictColumns editDictColumns) {
    this.editDictColumns = editDictColumns;

    // layout
    setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

    // panel - form
    form = new Form(this);
    form.setPreferredSize(new Dimension(DialogMedium.WIDTH - 20, DialogMedium.HEIGHT));
    form.setAlignmentX(Component.LEFT_ALIGNMENT);
    add(form);
  }

  /**
   * get edit dict columns.
   *
   * @return edit dict columns
   */
  public EditDictColumns getEditDictColumns() {
    return editDictColumns;
  }
}
