package com.walksocket.er.component;

import com.walksocket.er.Const;
import com.walksocket.er.Size.DialogMedium;
import com.walksocket.er.Size.Screen;
import com.walksocket.er.component.input.table.root.form.other.UniqueKey;
import com.walksocket.er.component.input.uniquekey.Root;
import com.walksocket.er.custom.ErDialog;
import com.walksocket.er.sqlite.tmp.TmpColumn;
import com.walksocket.er.sqlite.tmp.TmpKey;
import java.awt.Dimension;
import java.awt.Point;
import java.util.List;
import javax.swing.WindowConstants;

/**
 * InputUniqueKey.
 */
public class InputUniqueKey extends ErDialog {

  /**
   * unique key.
   */
  private final UniqueKey uniqueKey;

  /**
   * Constructor.
   *
   * @param uniqueKey     uniqueKey
   * @param row           row
   * @param tmpUniqueKey  tmpUniqueKey
   * @param tmpColumnList tmpColumnList
   */
  public InputUniqueKey(UniqueKey uniqueKey, int row, TmpKey tmpUniqueKey,
      List<TmpColumn> tmpColumnList) {
    super();
    this.uniqueKey = uniqueKey;

    // pos
    var x = (Screen.getWidth() - DialogMedium.WIDTH) / 2;
    var y = (Screen.getHeight() - DialogMedium.HEIGHT) / 2;

    // init
    setTitle(String.format("%s - unique key", Const.TITLE));
    setLocation(new Point(x, y));
    setMinimumSize(new Dimension(DialogMedium.WIDTH, DialogMedium.HEIGHT));
    setResizable(false);
    setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

    // load
    var container = getContentPane();
    container.removeAll();
    container.add(new Root(this, row, tmpUniqueKey, tmpColumnList));
    container.revalidate();
    container.repaint();
  }

  /**
   * get unique key.
   *
   * @return unique key
   */
  public UniqueKey getUniqueKey() {
    return uniqueKey;
  }
}
