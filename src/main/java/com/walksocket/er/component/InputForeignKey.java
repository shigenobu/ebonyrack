package com.walksocket.er.component;

import com.walksocket.er.Const;
import com.walksocket.er.Size.DialogMedium;
import com.walksocket.er.component.input.foreignkey.Root;
import com.walksocket.er.component.input.table.root.form.other.ForeignKey;
import com.walksocket.er.custom.ErDialog;
import com.walksocket.er.sqlite.entity.DbTable;
import com.walksocket.er.sqlite.tmp.TmpColumn;
import com.walksocket.er.sqlite.tmp.TmpForeignKey;
import com.walksocket.er.sqlite.tmp.TmpKey;
import java.awt.Dimension;
import java.awt.Point;
import java.util.List;
import javax.swing.WindowConstants;

/**
 * InputForeignKey.
 */
public class InputForeignKey extends ErDialog {

  /**
   * foreign key.
   */
  private final ForeignKey foreignKey;

  /**
   * Constructor.
   *
   * @param foreignKey            foreignKey
   * @param row
   * @param tmpForeignKey         row
   * @param tmpColumnList         tmpColumnList
   * @param validTmpPrimaryKey    validTmpPrimaryKey
   * @param validTmpUniqueKeyList validTmpUniqueKeyList
   * @param validTmpKeyList       validTmpKeyList
   * @param dbTableList           dbTableList
   */
  public InputForeignKey(ForeignKey foreignKey, int row, TmpForeignKey tmpForeignKey,
      List<TmpColumn> tmpColumnList,
      TmpKey validTmpPrimaryKey,
      List<TmpKey> validTmpUniqueKeyList,
      List<TmpKey> validTmpKeyList,
      List<DbTable> dbTableList) {
    super();
    this.foreignKey = foreignKey;

    // pos
    var b = foreignKey.getOther().getForm().getRoot().getInputTable().getGraphicsConfiguration()
        .getBounds();
    var x = (b.getWidth() - DialogMedium.WIDTH) / 2 + b.getX();
    var y = (b.getHeight() - DialogMedium.HEIGHT) / 2 + b.getY();

    // init
    setTitle(String.format("%s - foreign key", Const.TITLE));
    setLocation(new Point((int) x, (int) y));
    setMinimumSize(new Dimension(DialogMedium.WIDTH, DialogMedium.HEIGHT));
    setResizable(false);
    setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

    // load
    var container = getContentPane();
    container.removeAll();
    container.add(
        new Root(this, row, tmpForeignKey, tmpColumnList, validTmpPrimaryKey, validTmpUniqueKeyList,
            validTmpKeyList, dbTableList));
    container.revalidate();
    container.repaint();
  }

  /**
   * get foreign key.
   *
   * @return foreign key
   */
  public ForeignKey getForeignKey() {
    return foreignKey;
  }
}
