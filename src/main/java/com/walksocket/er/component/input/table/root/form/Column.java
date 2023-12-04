package com.walksocket.er.component.input.table.root.form;

import com.walksocket.er.Size.DialogLarge;
import com.walksocket.er.component.input.table.root.form.column.Columns;
import com.walksocket.er.component.input.table.root.form.column.Group;
import com.walksocket.er.component.input.table.root.form.column.Partition;
import com.walksocket.er.sqlite.context.CtxTable;
import java.awt.Dimension;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

/**
 * Column.
 */
public class Column extends JPanel {

  /**
   * columns.
   */
  private final Columns columns;

  /**
   * group.
   */
  private final Group group;

  /**
   * partition.
   */
  private final Partition partition;

  /**
   * Constructor.
   *
   * @param ctxTable ctxTable
   */
  public Column(CtxTable ctxTable) {
    // columns
    columns = new Columns(ctxTable);
    columns.setPreferredSize(new Dimension(DialogLarge.WIDTH - 20, DialogLarge.HEIGHT / 40 * 20));

    // group
    group = new Group(ctxTable);
    group.setPreferredSize(new Dimension(DialogLarge.WIDTH - 20, DialogLarge.HEIGHT / 40 * 20));

    // group
    partition = new Partition(ctxTable);
    partition.setPreferredSize(new Dimension(DialogLarge.WIDTH - 20, DialogLarge.HEIGHT / 40 * 20));

    // tab
    var tab = new JTabbedPane();
    tab.addTab("columns", columns);
    tab.addTab("group", group);
    tab.addTab("partition", partition);
    add(tab);
  }

  /**
   * get columns.
   *
   * @return columns
   */
  public Columns getColumns() {
    return columns;
  }

  /**
   * get group.
   *
   * @return group
   */
  public Group getGroup() {
    return group;
  }

  /**
   * get partition.
   *
   * @return partition
   */
  public Partition getPartition() {
    return partition;
  }
}
