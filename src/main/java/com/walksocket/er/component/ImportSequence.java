package com.walksocket.er.component;

import com.walksocket.er.Const;
import com.walksocket.er.Size.DialogSmall;
import com.walksocket.er.Size.Screen;
import com.walksocket.er.component.imp.sequence.Root;
import com.walksocket.er.component.main.root.Workspace;
import com.walksocket.er.custom.ErDialog;
import java.awt.Dimension;
import java.awt.Point;
import javax.swing.WindowConstants;

/**
 * ImportSequence.
 */
public class ImportSequence extends ErDialog {

  /**
   * workspace.
   */
  private final Workspace workspace;

  /**
   * point.
   */
  private final Point point;

  /**
   * Constructor.
   *
   * @param workspace workspace
   * @param point     point
   */
  public ImportSequence(Workspace workspace, Point point) {
    super();

    this.workspace = workspace;
    this.point = point;

    // pos
    var x = (Screen.getWidth() - DialogSmall.WIDTH) / 2;
    var y = (Screen.getHeight() - DialogSmall.HEIGHT) / 2;

    // init
    setTitle(String.format("%s - import sequence", Const.TITLE));
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
    if (workspace.completeSequenceDdl(ddl, point)) {
      dispose();
    }
  }
}
