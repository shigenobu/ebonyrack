package com.walksocket.er.component;

import com.walksocket.er.Const;
import com.walksocket.er.Size.DialogSmall;
import com.walksocket.er.Size.Screen;
import com.walksocket.er.component.imp.foreignkey.Root;
import com.walksocket.er.component.main.root.Workspace;
import com.walksocket.er.custom.ErDialog;
import java.awt.Dimension;
import java.awt.Point;
import javax.swing.WindowConstants;

/**
 * ImpForeignKey.
 */
public class ImpForeignKey extends ErDialog {

  /**
   * workspace.
   */
  private final Workspace workspace;

  /**
   * Constructor.
   *
   * @param workspace workspace
   */
  public ImpForeignKey(Workspace workspace) {
    super();

    this.workspace = workspace;

    // pos
    var x = (Screen.getWidth() - DialogSmall.WIDTH) / 2;
    var y = (Screen.getHeight() - DialogSmall.HEIGHT) / 2;

    // init
    setTitle(String.format("%s - import foreign key", Const.TITLE));
    setLocation(new Point(x, y));
    setMinimumSize(new Dimension(DialogSmall.WIDTH, DialogSmall.HEIGHT));
    setResizable(false);
    setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

    // load
    var container = getContentPane();
    container.removeAll();
    container.add(new Root(this));
    container.revalidate();
    container.repaint();
  }

  /**
   * complete.
   *
   * @param ddl ddl
   */
  public void complete(String ddl) {
    if (workspace.completeForeignKeyDdl(ddl)) {
      dispose();
    }
  }
}
