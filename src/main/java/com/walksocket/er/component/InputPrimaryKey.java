package com.walksocket.er.component;

import com.walksocket.er.Const;
import com.walksocket.er.Size.DialogMedium;
import com.walksocket.er.Size.Screen;
import com.walksocket.er.component.input.primarykey.Root;
import com.walksocket.er.component.input.table.root.form.other.PrimaryKey;
import com.walksocket.er.custom.ErDialog;
import com.walksocket.er.sqlite.tmp.TmpColumn;
import com.walksocket.er.sqlite.tmp.TmpKey;
import java.awt.Dimension;
import java.awt.Point;
import java.util.List;
import javax.swing.WindowConstants;

/**
 * InputPrimaryKey.
 */
public class InputPrimaryKey extends ErDialog {

  /**
   * primary key.
   */
  private final PrimaryKey primaryKey;

  /**
   * Constructor.
   *
   * @param primaryKey    primaryKey
   * @param row           row
   * @param tmpPrimaryKey tmpPrimaryKey
   * @param tmpColumnList tmpColumnList
   */
  public InputPrimaryKey(PrimaryKey primaryKey, int row, TmpKey tmpPrimaryKey,
      List<TmpColumn> tmpColumnList) {
    super();
    this.primaryKey = primaryKey;

    // pos
    var x = (Screen.getWidth() - DialogMedium.WIDTH) / 2;
    var y = (Screen.getHeight() - DialogMedium.HEIGHT) / 2;

    // init
    setTitle(String.format("%s - primary key", Const.TITLE));
    setLocation(new Point(x, y));
    setMinimumSize(new Dimension(DialogMedium.WIDTH, DialogMedium.HEIGHT));
    setResizable(false);
    setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

    // load
    var container = getContentPane();
    container.removeAll();
    container.add(new Root(this, row, tmpPrimaryKey, tmpColumnList));
    container.revalidate();
    container.repaint();
  }

  /**
   * get primary key.
   *
   * @return primary key
   */
  public PrimaryKey getPrimaryKey() {
    return primaryKey;
  }
}
