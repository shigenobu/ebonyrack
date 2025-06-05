package com.walksocket.er.custom;

import com.walksocket.er.Date;
import com.walksocket.er.Pos;
import com.walksocket.er.Utils;
import java.awt.Color;
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
   * start timestamp millis.
   */
  private long startTimestampMillis;

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
   * selecting panel.
   */
  private final JPanel selectingPanel = new JPanel();

  /**
   * Constructor.
   *
   * @param parent parent
   */
  public ErMover(JPanel parent) {
    // selecting panel
    selectingPanel.setBackground(Color.WHITE);

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
      var newNx = Utils.floorDegree(x, rangeUnit);
      var newNy = Utils.floorDegree(y, rangeUnit);
      this.nx = newNx;
      this.ny = newNy;

      if (Math.abs(endpoint.getX() - newNx) < rangeUnit
          && Math.abs(endpoint.getY() - newNy) < rangeUnit) {
        return;
      }

      movingContinuing(newNx, newNy);
    };
    movingTimer = new Timer(100, movingListener);
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
    startTimestampMillis = Date.timestampMillis();
  }

  /**
   * moving end.
   */
  protected void movingEnd() {
    movingTimer.stop();

    var nowMills = Date.timestampMillis();
    if (nowMills - startTimestampMillis < 500) {
      return;
    }

    // complete
    movingComplete(nx, ny);
  }

  /**
   * moving continuing.
   *
   * @param x x
   * @param y y
   */
  protected abstract void movingContinuing(int x, int y);

  /**
   * moving complete.
   *
   * @param x x
   * @param y y
   */
  protected abstract void movingComplete(int x, int y);

  /**
   * add selecting panel.
   */
  public void addSelectingPanel() {
    selectingPanel.setSize(getSize());
    selectingPanel.setBorder(ErConnectorColor.SELECTING_BORDER);
    add(selectingPanel);
  }

  /**
   * resize selecting panel.
   */
  public void resizeSelectingPanel() {
    selectingPanel.setSize(getSize());
  }

  /**
   * remove selecting panel.
   */
  public void removeSelectingPanel() {
    remove(selectingPanel);
  }
}
