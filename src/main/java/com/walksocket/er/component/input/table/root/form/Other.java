package com.walksocket.er.component.input.table.root.form;

import com.walksocket.er.Size.DialogLarge;
import com.walksocket.er.component.input.table.root.Form;
import com.walksocket.er.component.input.table.root.form.other.Check;
import com.walksocket.er.component.input.table.root.form.other.ForeignKey;
import com.walksocket.er.component.input.table.root.form.other.Key;
import com.walksocket.er.component.input.table.root.form.other.PrimaryKey;
import com.walksocket.er.component.input.table.root.form.other.UniqueKey;
import com.walksocket.er.sqlite.context.CtxTable;
import java.awt.Dimension;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

/**
 * Other.
 */
public class Other extends JPanel {

  /**
   * form.
   */
  private final Form form;

  /**
   * primary key.
   */
  private final PrimaryKey primaryKey;

  /**
   * unique key.
   */
  private final UniqueKey uniqueKey;

  /**
   * key.
   */
  private final Key key;

  /**
   * foreign key.
   */
  private final ForeignKey foreignKey;

  /**
   * check.
   */
  private final Check check;

  /**
   * Constructor.
   *
   * @param form     form
   * @param ctxTable ctxTable
   */
  public Other(Form form, CtxTable ctxTable) {
    this.form = form;

    // primary key
    primaryKey = new PrimaryKey(this, ctxTable);
    primaryKey.setPreferredSize(
        new Dimension(DialogLarge.WIDTH - 20, DialogLarge.HEIGHT / 40 * 10));

    // unique key
    uniqueKey = new UniqueKey(this, ctxTable);
    uniqueKey.setPreferredSize(
        new Dimension(DialogLarge.WIDTH - 20, DialogLarge.HEIGHT / 40 * 10));

    // key
    key = new Key(this, ctxTable);
    key.setPreferredSize(
        new Dimension(DialogLarge.WIDTH - 20, DialogLarge.HEIGHT / 40 * 10));

    // foreign key
    foreignKey = new ForeignKey(this, ctxTable);
    foreignKey.setPreferredSize(
        new Dimension(DialogLarge.WIDTH - 20, DialogLarge.HEIGHT / 40 * 10));

    // check
    check = new Check(this, ctxTable);
    check.setPreferredSize(
        new Dimension(DialogLarge.WIDTH - 20, DialogLarge.HEIGHT / 40 * 10));

    // tab
    var tab = new JTabbedPane();
    tab.addTab("primary key", primaryKey);
    tab.addTab("unique key", uniqueKey);
    tab.addTab("key", key);
    tab.addTab("foreign key", foreignKey);
    tab.addTab("check", check);
    add(tab);
  }

  /**
   * get form.
   *
   * @return form
   */
  public Form getForm() {
    return form;
  }

  /**
   * get primary key.
   *
   * @return primary key
   */
  public PrimaryKey getPrimaryKey() {
    return primaryKey;
  }

  /**
   * get unique key.
   *
   * @return unique key
   */
  public UniqueKey getUniqueKey() {
    return uniqueKey;
  }

  /**
   * get key.
   *
   * @return key
   */
  public Key getKey() {
    return key;
  }

  /**
   * get foreign key.
   *
   * @return foreign key
   */
  public ForeignKey getForeignKey() {
    return foreignKey;
  }

  /**
   * get check.
   *
   * @return check
   */
  public Check getCheck() {
    return check;
  }
}
