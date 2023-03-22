package com.walksocket.er.component.input.uniquekey;

import com.walksocket.er.Size.DialogMedium;
import com.walksocket.er.component.InputUniqueKey;
import com.walksocket.er.component.input.uniquekey.root.Form;
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
   * @param inputUniqueKey inputUniqueKey
   * @param row            row
   * @param tmpUniqueKey   tmpUniqueKey
   * @param tmpColumnList  tmpColumnList
   */
  public Root(InputUniqueKey inputUniqueKey, int row, TmpKey tmpUniqueKey,
      List<TmpColumn> tmpColumnList) {
    // layout
    setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

    // panel - form
    form = new Form(tmpUniqueKey, tmpColumnList);
    form.setPreferredSize(new Dimension(DialogMedium.WIDTH - 20, DialogMedium.HEIGHT / 10 * 9));
    form.setAlignmentX(Component.LEFT_ALIGNMENT);
    add(form);

    // panel - button
    var panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    panel.setPreferredSize(new Dimension(DialogMedium.WIDTH - 20, DialogMedium.HEIGHT / 10));
    panel.setAlignmentX(Component.LEFT_ALIGNMENT);
    add(panel);
    buttonOk.addActionListener(actionEvent -> {
      form.packTmpUniqueKey();
      inputUniqueKey.getUniqueKey().resetTable(row, tmpUniqueKey);
      inputUniqueKey.dispose();
    });
    panel.add(buttonOk);
  }
}
