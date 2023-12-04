package com.walksocket.er.component.input.table.root;

import com.walksocket.er.Size.DialogLarge;
import com.walksocket.er.component.input.table.Root;
import com.walksocket.er.component.input.table.root.form.Base;
import com.walksocket.er.component.input.table.root.form.Column;
import com.walksocket.er.component.input.table.root.form.Other;
import com.walksocket.er.sqlite.context.CtxTable;
import java.awt.Component;
import java.awt.Dimension;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

/**
 * Form.
 */
public class Form extends JPanel {

  /**
   * root.
   */
  private final Root root;

  /**
   * ctx table.
   */
  private final CtxTable ctxTable;

  /**
   * base.
   */
  private final Base base;

  /**
   * column.
   */
  private final Column column;

  /**
   * other.
   */
  private final Other other;

  /**
   * Constructor.
   *
   * @param root     root
   * @param ctxTable ctxTable
   */
  public Form(Root root, CtxTable ctxTable) {
    this.root = root;
    this.ctxTable = ctxTable;

    // layout
    setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

    // base
    base = new Base(ctxTable);
    base.setPreferredSize(new Dimension(DialogLarge.WIDTH - 20, DialogLarge.HEIGHT / 40 * 5));
    base.setAlignmentX(Component.LEFT_ALIGNMENT);
    add(base);

    // column
    column = new Column(ctxTable);
    column.setPreferredSize(new Dimension(DialogLarge.WIDTH - 20, DialogLarge.HEIGHT / 40 * 20));
    column.setAlignmentX(Component.LEFT_ALIGNMENT);
    add(column);

    // other
    other = new Other(this, ctxTable);
    other.setPreferredSize(new Dimension(DialogLarge.WIDTH - 20, DialogLarge.HEIGHT / 40 * 10));
    other.setAlignmentX(Component.LEFT_ALIGNMENT);
    add(other);
  }

  /**
   * get base.
   *
   * @return base
   */
  public Base getBase() {
    return base;
  }

  /**
   * get column.
   *
   * @return column
   */
  public Column getColumn() {
    return column;
  }

  /**
   * get other.
   *
   * @return other
   */
  public Other getOther() {
    return other;
  }
}
