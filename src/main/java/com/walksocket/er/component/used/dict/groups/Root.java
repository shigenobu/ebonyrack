package com.walksocket.er.component.used.dict.groups;

import com.walksocket.er.Size.DialogUsed;
import com.walksocket.er.component.used.dict.groups.root.Form;
import com.walksocket.er.sqlite.entity.DbDictGroup;
import java.awt.Component;
import java.awt.Dimension;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

/**
 * Root.
 */
public class Root extends JPanel {

  /**
   * Constructor.
   *
   * @param dbDictGroup dbDictGroup
   */
  public Root(DbDictGroup dbDictGroup) {
    // layout
    setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

    // panel - form
    var form = new Form(dbDictGroup);
    form.setPreferredSize(new Dimension(DialogUsed.WIDTH - 20, DialogUsed.HEIGHT));
    form.setAlignmentX(Component.LEFT_ALIGNMENT);
    add(form);
  }
}
