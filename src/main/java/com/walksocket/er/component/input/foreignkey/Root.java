package com.walksocket.er.component.input.foreignkey;

import com.walksocket.er.Size.DialogMedium;
import com.walksocket.er.component.InputForeignKey;
import com.walksocket.er.component.input.foreignkey.root.Form;
import com.walksocket.er.sqlite.entity.DbTable;
import com.walksocket.er.sqlite.tmp.TmpColumn;
import com.walksocket.er.sqlite.tmp.TmpForeignKey;
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
   * @param inputForeignKey       inputForeignKey
   * @param row                   row
   * @param tmpForeignKey         tmpForeignKey
   * @param tmpColumnList         tmpColumnList
   * @param validTmpPrimaryKey    validTmpPrimaryKey
   * @param validTmpUniqueKeyList validTmpUniqueKeyList
   * @param validTmpKeyList       validTmpKeyList
   * @param dbTableList           dbTableList
   */
  public Root(InputForeignKey inputForeignKey, int row, TmpForeignKey tmpForeignKey,
      List<TmpColumn> tmpColumnList,
      TmpKey validTmpPrimaryKey,
      List<TmpKey> validTmpUniqueKeyList,
      List<TmpKey> validTmpKeyList,
      List<DbTable> dbTableList) {
    // layout
    setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

    // panel - form
    form = new Form(tmpForeignKey, tmpColumnList, validTmpPrimaryKey, validTmpUniqueKeyList,
        validTmpKeyList, dbTableList);
    form.setPreferredSize(new Dimension(DialogMedium.WIDTH - 20, DialogMedium.HEIGHT / 10 * 9));
    form.setAlignmentX(Component.LEFT_ALIGNMENT);
    add(form);

    // panel - button
    var panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    panel.setPreferredSize(new Dimension(DialogMedium.WIDTH - 20, DialogMedium.HEIGHT / 10));
    panel.setAlignmentX(Component.LEFT_ALIGNMENT);
    add(panel);
    buttonOk.addActionListener(actionEvent -> {
      form.packTmpForeignKey();
      inputForeignKey.getForeignKey().resetTable(row, tmpForeignKey);
      inputForeignKey.dispose();
    });
    panel.add(buttonOk);
  }
}
