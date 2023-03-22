package com.walksocket.er.component.edit.dict.groups;

import com.walksocket.er.Size.DialogMedium;
import com.walksocket.er.component.EditDictGroups;
import com.walksocket.er.component.edit.dict.groups.root.Form;
import java.awt.Component;
import java.awt.Dimension;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

/**
 * Root.
 */
public class Root extends JPanel {

  /**
   * form.
   */
  private final Form form;

  /**
   * Constructor.
   *
   * @param editDictGroups editDictGroups
   */
  public Root(EditDictGroups editDictGroups) {
    // layout
    setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

    // panel - form
    form = new Form();
    form.setPreferredSize(new Dimension(DialogMedium.WIDTH - 20, DialogMedium.HEIGHT));
    form.setAlignmentX(Component.LEFT_ALIGNMENT);
    add(form);
  }
}
