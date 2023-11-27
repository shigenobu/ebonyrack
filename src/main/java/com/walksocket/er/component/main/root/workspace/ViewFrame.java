package com.walksocket.er.component.main.root.workspace;

import com.walksocket.er.component.main.root.Outline;
import com.walksocket.er.component.main.root.Workspace;
import com.walksocket.er.custom.ErMover;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;

/**
 * ViewFrame.
 */
public class ViewFrame extends ErMover {

  /**
   * workspace.
   */
  private Workspace workspace;

  /**
   * scale ratio.
   */
  private double scaleRatio;

  /**
   * Constructor.
   *
   * @param outline outline
   */
  public ViewFrame(Outline outline) {
    super(outline);

    // init
    setOpaque(false);
    setBorder(BorderFactory.createLineBorder(Color.CYAN, 1));
    addMouseListener(new MouseAdapter() {
      @Override
      public void mousePressed(MouseEvent e) {
        if (e.isPopupTrigger() || SwingUtilities.isRightMouseButton(e)) {
          return;
        }

        // change cursor
        var cursor = Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR);
        setCursor(cursor);

        // moving
        movingStart(e.getPoint());
      }

      @Override
      public void mouseReleased(MouseEvent e) {
        if (e.isPopupTrigger() || SwingUtilities.isRightMouseButton(e)) {
          return;
        }

        // moving
        movingEnd();

        // reverse cursor
        var cursor = Cursor.getDefaultCursor();
        setCursor(cursor);
      }
    });
  }

  /**
   * set workspace.
   *
   * @param workspace workspace
   */
  public void setWorkspace(Workspace workspace) {
    this.workspace = workspace;
  }

  @Override
  protected void movingComplete(int x, int y) {
    var sp = (JScrollPane) workspace.getParent().getParent();
    sp.getHorizontalScrollBar().setValue((int) ((double) x / scaleRatio));
    sp.getVerticalScrollBar().setValue((int) ((double) y / scaleRatio));
  }

  /**
   * update scale ratio.
   *
   * @param scaleRatio scale ratio
   */
  public void updateScaleRatio(double scaleRatio) {
    this.scaleRatio = scaleRatio;
  }
}
