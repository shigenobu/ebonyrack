package com.walksocket.er.component.input.columnname;

import com.walksocket.er.Size.DialogMedium;
import com.walksocket.er.component.InputColumnName;
import com.walksocket.er.component.input.columnname.root.Form;
import com.walksocket.er.sqlite.tmp.TmpColumn;
import java.awt.Component;
import java.awt.Dimension;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

/**
 * Root.
 */
public class Root extends JPanel {

  /**
   * input column name.
   */
  private final InputColumnName inputColumnName;

  /**
   * Constructor.
   *
   * @param inputColumnName  inputColumnName
   * @param row              row
   * @param tmpColumnForDict tmpColumnForDict
   */
  public Root(InputColumnName inputColumnName, int row, TmpColumn tmpColumnForDict) {
    this.inputColumnName = inputColumnName;

    // layout
    setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

    // panel - form
    var form = new Form(this, row, tmpColumnForDict);
    form.setPreferredSize(new Dimension(DialogMedium.WIDTH - 20, DialogMedium.HEIGHT / 10 * 9));
    form.setAlignmentX(Component.LEFT_ALIGNMENT);
    add(form);
  }

  /**
   * get input column name.
   *
   * @return input column name
   */
  public InputColumnName getInputColumnName() {
    return inputColumnName;
  }
}
