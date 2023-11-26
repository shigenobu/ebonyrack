package com.walksocket.er.component.export.ddl;

import com.walksocket.er.Size.DialogSmall;
import com.walksocket.er.component.ExportDdl;
import com.walksocket.er.component.export.ddl.root.Form;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * Root.
 */
public class Root extends JPanel {

  /**
   * export ddl.
   */
  private final ExportDdl exportDdl;

  /**
   * form.
   */
  private final Form form;

  /**
   * button ok.
   */
  private final JButton buttonOk = new JButton("Ok");

  /**
   * Constructor.
   *
   * @param exportDdl exportDdl
   */
  public Root(ExportDdl exportDdl) {
    this.exportDdl = exportDdl;

    // layout
    setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

    // panel - form
    form = new Form(this);
    form.setPreferredSize(new Dimension(DialogSmall.WIDTH - 20, DialogSmall.HEIGHT / 10 * 9));
    form.setAlignmentX(Component.LEFT_ALIGNMENT);
    add(form);

    // panel - button
    var panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    panel.setPreferredSize(new Dimension(DialogSmall.WIDTH - 20, DialogSmall.HEIGHT / 10));
    panel.setAlignmentX(Component.LEFT_ALIGNMENT);
    add(panel);
    buttonOk.addActionListener(actionEvent -> {

    });
    panel.add(buttonOk);
  }
}
