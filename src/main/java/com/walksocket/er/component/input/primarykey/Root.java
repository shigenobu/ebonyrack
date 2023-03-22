package com.walksocket.er.component.input.primarykey;

import com.walksocket.er.Size.DialogMedium;
import com.walksocket.er.component.InputPrimaryKey;
import com.walksocket.er.component.input.primarykey.root.Form;
import com.walksocket.er.sqlite.tmp.TmpColumn;
import com.walksocket.er.sqlite.tmp.TmpKey;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.JButton;
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
   * button ok.
   */
  private final JButton buttonOk = new JButton("Ok");

  /**
   * Constructor.
   *
   * @param inputPrimaryKey inputPrimaryKey
   * @param row             row
   * @param tmpPrimaryKey   tmpPrimaryKey
   */
  public Root(InputPrimaryKey inputPrimaryKey, int row, TmpKey tmpPrimaryKey,
      List<TmpColumn> tmpColumnList) {
    // layout
    setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

    // panel - form
    form = new Form(tmpPrimaryKey, tmpColumnList);
    form.setPreferredSize(new Dimension(DialogMedium.WIDTH - 20, DialogMedium.HEIGHT / 10 * 9));
    form.setAlignmentX(Component.LEFT_ALIGNMENT);
    add(form);

    // panel - button
    var panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    panel.setPreferredSize(new Dimension(DialogMedium.WIDTH - 20, DialogMedium.HEIGHT / 10));
    panel.setAlignmentX(Component.LEFT_ALIGNMENT);
    add(panel);
    buttonOk.addActionListener(actionEvent -> {
      form.packTmpPrimaryKey();
      inputPrimaryKey.getPrimaryKey().resetTable(row, tmpPrimaryKey);
      inputPrimaryKey.dispose();
    });
    panel.add(buttonOk);
  }
}
