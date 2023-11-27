package com.walksocket.er.custom;

import com.walksocket.er.Utils;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

/**
 * ErMover.
 */
public abstract class ErMover extends JPanel {

  /**
   * moving init point.
   */
  private Point movingInitPoint;

  /**
   * moving timer.
   */
  private final Timer movingTimer;

  /**
   * Constructor.
   *
   * @param parent parent
   */
  public ErMover(JPanel parent) {
    // moving
    var endpoint = this;
    ActionListener movingListener = actionEvent -> {
      if (movingInitPoint == null) {
        return;
      }

      // https://ateraimemo.com/Swing/MouseInfo.html
      var pi = MouseInfo.getPointerInfo();
      Point pt = pi.getLocation();
      SwingUtilities.convertPointFromScreen(pt, parent);

      var x = pt.getX() - movingInitPoint.getX();
      var y = pt.getY() - movingInitPoint.getY();
      if (x < 0) {
        x = 0;
      }
      if (y < 0) {
        y = 0;
      }
      if (x > 9999) {
        x = 9999;
      }
      if (y > 9999) {
        y = 9999;
      }
      var nx = Utils.floorOneDegree(x);
      var ny = Utils.floorOneDegree(y);

      if (Math.abs(endpoint.getX() - nx) < 10 && Math.abs(endpoint.getY() - ny) < 10) {
        return;
      }

      // complete
      movingComplete(nx, ny);
    };
    movingTimer = new Timer(50, movingListener);
    movingTimer.setRepeats(true);
  }

  /**
   * moving start.
   *
   * @param movingInitPoint movingInitPoint
   */
  protected void movingStart(Point movingInitPoint) {
    this.movingInitPoint = movingInitPoint;

    movingTimer.start();
  }

  /**
   * moving end.
   */
  protected void movingEnd() {
    movingTimer.stop();
  }

  /**
   * moving complete.
   *
   * @param x x
   * @param y y
   */
  protected abstract void movingComplete(int x, int y);
}
