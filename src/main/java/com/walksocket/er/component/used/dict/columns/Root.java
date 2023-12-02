package com.walksocket.er.component.used.dict.columns;

import com.walksocket.er.Size.DialogSmall;
import com.walksocket.er.component.used.dict.columns.root.Form;
import com.walksocket.er.sqlite.entity.DbDictColumn;
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
   * @param dbDictColumn dbDictColumn
   */
  public Root(DbDictColumn dbDictColumn) {
    // layout
    setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

    // panel - form
    var form = new Form(dbDictColumn);
    form.setPreferredSize(new Dimension(DialogSmall.WIDTH - 20, DialogSmall.HEIGHT));
    form.setAlignmentX(Component.LEFT_ALIGNMENT);
    add(form);
  }
}
