package com.walksocket.er.component.used.dict.partitions;

import com.walksocket.er.Size.DialogUsed;
import com.walksocket.er.component.used.dict.partitions.root.Form;
import com.walksocket.er.sqlite.entity.DbDictPartition;
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
   * @param dbDictPartition dbDictPartition
   */
  public Root(DbDictPartition dbDictPartition) {
    // layout
    setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

    // panel - form
    var form = new Form(dbDictPartition);
    form.setPreferredSize(new Dimension(DialogUsed.WIDTH - 20, DialogUsed.HEIGHT));
    form.setAlignmentX(Component.LEFT_ALIGNMENT);
    add(form);
  }
}
