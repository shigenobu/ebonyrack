package com.walksocket.er.component.imp.table;

import com.walksocket.er.Size.DialogSmall;
import com.walksocket.er.component.ImpTable;
import com.walksocket.er.component.imp.table.root.Form;
import java.awt.Component;
import java.awt.Dimension;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * Root.
 */
public class Root extends JPanel {

  /**
   * button import.
   */
  private final JButton buttonImport = new JButton("Import");

  /**
   * Constructor.
   *
   * @param impTable impTable
   */
  public Root(ImpTable impTable) {
    // layout
    setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

    // panel - form
    var form = new Form();
    form.setPreferredSize(new Dimension(DialogSmall.WIDTH - 20, DialogSmall.HEIGHT / 10 * 9));
    form.setAlignmentX(Component.LEFT_ALIGNMENT);
    add(form);

    // panel - button
    var panel = new JPanel();
    panel.setPreferredSize(new Dimension(DialogSmall.WIDTH - 20, DialogSmall.HEIGHT / 10));
    panel.setAlignmentX(Component.LEFT_ALIGNMENT);
    add(panel);
    buttonImport.addActionListener(actionEvent -> {
      var ddl = form.getDdl();
      impTable.complete(ddl);
    });
    panel.add(buttonImport);
  }
}
