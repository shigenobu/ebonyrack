package com.walksocket.er.custom;

import com.walksocket.er.Pos;
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
   * nx.
   */
  private int nx;

  /**
   * ny.
   */
  private int ny;

  /**
   * moving init point.
   */
  private Point movingInitPoint;

  /**
   * moving timer.
   */
  private final Timer movingTimer;

  /**
   * range unit.
   */
  protected int rangeUnit = Pos.DEFAULT_UNIT;

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
      if (x < Pos.MIN) {
        x = Pos.MIN;
      }
      if (y < Pos.MIN) {
        y = Pos.MIN;
      }
      if (x > Pos.MAX) {
        x = Pos.MAX;
      }
      if (y > Pos.MAX) {
        y = Pos.MAX;
      }
      var nx = Utils.floorDegree(x, rangeUnit);
      var ny = Utils.floorDegree(y, rangeUnit);

      if (Math.abs(endpoint.getX() - nx) < rangeUnit
          && Math.abs(endpoint.getY() - ny) < rangeUnit) {
        return;
      }

      setLocation(nx, ny);
      this.nx = nx;
      this.ny = ny;
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

    // complete
    movingComplete(nx, ny);
  }

  /**
   * moving complete.
   *
   * @param x x
   * @param y y
   */
  protected abstract void movingComplete(int x, int y);
}
