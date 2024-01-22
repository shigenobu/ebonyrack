package com.walksocket.er.component.show.tableclass;

import com.walksocket.er.Size.DialogMedium;
import com.walksocket.er.component.show.tableclass.root.Form;
import com.walksocket.er.sqlite.context.CtxTable;
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
   * @param ctxTable ctxTable
   */
  public Root(CtxTable ctxTable) {
    // layout
    setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

    // panel - form
    var form = new Form(ctxTable);
    form.setPreferredSize(new Dimension(DialogMedium.WIDTH - 20, DialogMedium.HEIGHT));
    form.setAlignmentX(Component.LEFT_ALIGNMENT);
    add(form);
  }
}
