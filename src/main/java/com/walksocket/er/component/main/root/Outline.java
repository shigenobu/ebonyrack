package com.walksocket.er.component.main.root;

import com.walksocket.er.Const;
import com.walksocket.er.component.main.Root;
import com.walksocket.er.custom.ErMover;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;

/**
 * Outline.
 */
public class Outline extends JPanel {

  /**
   * root.
   */
  private final Root root;

  /**
   * view frame.
   */
  private final ViewFrame viewFrame;

  /**
   * workspace.
   */
  private Workspace workspace;

  /**
   * Constructor.
   *
   * @param root root
   */
  public Outline(Root root) {
    this.root = root;

    // view frame
    viewFrame = new ViewFrame(this);
    add(viewFrame);

    addMouseListener(new MouseAdapter() {
      @Override
      public void mousePressed(MouseEvent e) {
        if (e.getX() >= viewFrame.getX()
            && e.getY() >= viewFrame.getY()
            && e.getX() <= viewFrame.getX() + viewFrame.getWidth()
            && e.getY() <= viewFrame.getY() + viewFrame.getHeight()) {
          viewFrame.dispatchEvent(e);
        }
      }

      @Override
      public void mouseReleased(MouseEvent e) {
        if (e.getX() >= viewFrame.getX()
            && e.getY() >= viewFrame.getY()
            && e.getX() <= viewFrame.getX() + viewFrame.getWidth()
            && e.getY() <= viewFrame.getY() + viewFrame.getHeight()) {
          viewFrame.dispatchEvent(e);
        }
      }
    });
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    if (workspace == null) {
      return;
    }
    var rect = workspace.getBounds();
    var captureImage =
        new BufferedImage(rect.width, rect.height,
            BufferedImage.TYPE_INT_ARGB);
    var cg = captureImage.getGraphics();
    workspace.printComponents(cg);
    cg.dispose();

    var w = getWidth();
    double wRatio = (double) getWidth() / (double) rect.width;
    double scaleRatio = wRatio;
    var h = (int) (wRatio * rect.height);
    if (h > getHeight()) {
      h = getHeight();
      double hRatio = (double) getHeight() / (double) rect.height;
      scaleRatio = hRatio;
      w = (int) (hRatio * rect.width);
    }
    viewFrame.updateScaleRatio(scaleRatio);

    var g2 = (Graphics2D) g;
    g2.drawImage(captureImage.getScaledInstance(w, h, Image.SCALE_DEFAULT), 0, 0, w, h,
        new Color(73, 70, 44, 8), null);

    var p = workspace.getParent();
    var pw = scaleRatio * (double) p.getWidth();
    var ph = scaleRatio * (double) p.getHeight();

    var sp = (JScrollPane) workspace.getParent().getParent();
    var sx = scaleRatio * (double) sp.getHorizontalScrollBar().getValue();
    var sy = scaleRatio * (double) sp.getVerticalScrollBar().getValue();

    if (viewFrame.getX() != (int) sx || viewFrame.getY() != (int) sy) {
      viewFrame.setLocation((int) sx, (int) sy);
    }
    if (viewFrame.getWidth() != (int) pw || viewFrame.getHeight() != (int) ph) {
      viewFrame.setSize((int) pw, (int) ph);
    }
  }

  /**
   * set workspace.
   *
   * @param workspace workspace
   */
  public void setWorkspace(Workspace workspace) {
    this.workspace = workspace;
    viewFrame.setWorkspace(workspace);
  }

  /**
   * ViewFrame.
   */
  public static class ViewFrame extends ErMover {

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

      // change unit
      rangeUnit = 1;

      // init
      setOpaque(false);
      setBorder(BorderFactory.createLineBorder(Const.COLOR_EBONY, 1));
      addMouseListener(new MouseAdapter() {
        @Override
        public void mousePressed(MouseEvent e) {
          if (e.isPopupTrigger() || SwingUtilities.isRightMouseButton(e)) {
            return;
          }

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
}
