package com.walksocket.er.component;

import com.walksocket.er.Const;
import com.walksocket.er.Size.DialogMedium;
import com.walksocket.er.component.input.key.Root;
import com.walksocket.er.component.input.table.root.form.other.Key;
import com.walksocket.er.custom.ErDialog;
import com.walksocket.er.sqlite.tmp.TmpColumn;
import com.walksocket.er.sqlite.tmp.TmpKey;
import java.awt.Dimension;
import java.awt.Point;
import java.util.List;
import javax.swing.WindowConstants;

/**
 * InputKey.
 */
public class InputKey extends ErDialog {

  /**
   * key.
   */
  private final Key key;

  /**
   * Constructor.
   *
   * @param key           key
   * @param row           row
   * @param tmpKey        tmpKey
   * @param tmpColumnList tmpColumnList
   */
  public InputKey(Key key, int row, TmpKey tmpKey,
      List<TmpColumn> tmpColumnList) {
    super();
    this.key = key;

    // pos
    var b = key.getOther().getForm().getRoot().getInputTable().getGraphicsConfiguration()
        .getBounds();
    var x = (b.getWidth() - DialogMedium.WIDTH) / 2 + b.getX();
    var y = (b.getHeight() - DialogMedium.HEIGHT) / 2 + b.getY();

    // init
    setTitle(String.format("%s - key", Const.TITLE));
    setLocation(new Point((int) x, (int) y));
    setMinimumSize(new Dimension(DialogMedium.WIDTH, DialogMedium.HEIGHT));
    setResizable(false);
    setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

    // load
    var container = getContentPane();
    container.removeAll();
    container.add(new Root(this, row, tmpKey, tmpColumnList));
    container.revalidate();
    container.repaint();
  }

  /**
   * get key.
   *
   * @return key
   */
  public Key getKey() {
    return key;
  }
}
