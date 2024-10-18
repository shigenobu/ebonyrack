package com.walksocket.er.custom;

import com.walksocket.er.Date;
import com.walksocket.er.Log;
import com.walksocket.er.Pos;
import com.walksocket.er.Utils;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.Set;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

/**
 * ErSelectingRange.
 */
public class ErSelectingRange extends JPanel {

  /**
   * start timestamp millis.
   */
  private long startTimestampMillis;

  /**
   * target mover.
   */
  private ErMover targetMover;

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
  private final int rangeUnit = Pos.DEFAULT_UNIT;

  /**
   * movers.
   */
  private final Set<ErMover> movers = new HashSet<>();

  /**
   * Constructor.
   *
   * @param moverArea moving area
   */
  public ErSelectingRange(ErMoverArea moverArea) {
    ActionListener movingListener = actionEvent -> {
      if (targetMover == null) {
        return;
      }
      if (movingInitPoint == null) {
        return;
      }

      // https://ateraimemo.com/Swing/MouseInfo.html
      var pi = MouseInfo.getPointerInfo();
      Point pt = pi.getLocation();
      SwingUtilities.convertPointFromScreen(pt, moverArea);

      var x = pt.getX() - movingInitPoint.getX();
      var y = pt.getY() - movingInitPoint.getY();
      if (Math.abs(targetMover.getX() - x) < rangeUnit
          && Math.abs(targetMover.getY() - y) < rangeUnit) {
        return;
      }
      var dx = (int) (x - targetMover.getX());
      var dy = (int) (y - targetMover.getY());

      for (var mover : movers) {
        var nx = Utils.floorDegree(mover.getX() + dx, rangeUnit);
        var ny = Utils.floorDegree(mover.getY() + dy, rangeUnit);
        if (nx < Pos.MIN) {
          return;
        }
        if (ny < Pos.MIN) {
          return;
        }
        if (nx > Pos.MAX) {
          return;
        }
        if (ny > Pos.MAX) {
          return;
        }
      }
      for (var mover : movers) {
        var nx = Utils.floorDegree(mover.getX() + dx, rangeUnit);
        var ny = Utils.floorDegree(mover.getY() + dy, rangeUnit);
        mover.movingContinuing(nx, ny);
      }
    };
    movingTimer = new Timer(100, movingListener);
    movingTimer.setRepeats(true);
  }


  /**
   * moving start.
   *
   * @param movingInitPoint movingInitPoint
   */
  public void movingStart(ErMover targetMover, Point movingInitPoint) {
    this.targetMover = targetMover;
    this.movingInitPoint = movingInitPoint;

    movingTimer.start();
    startTimestampMillis = Date.timestampMillis();
  }

  /**
   * moving end.
   */
  public void movingEnd() {
    movingTimer.stop();

    var nowMills = Date.timestampMillis();
    if (nowMills - startTimestampMillis < 500) {
      return;
    }

    // complete
    for (var mover : movers) {
      var nx = Utils.floorDegree(mover.getX(), rangeUnit);
      var ny = Utils.floorDegree(mover.getY(), rangeUnit);
      mover.movingComplete(nx, ny);
    }
  }

  /**
   * is selecting.
   *
   * @param mover mover
   * @return if mover is contained, true
   */
  public boolean isSelecting(ErMover mover) {
    return movers.contains(mover);
  }

  /**
   * set or unset mover
   *
   * @param mover mover
   * @return if set, true
   */
  public boolean setOrUnsetMover(ErMover mover) {
    var added = movers.add(mover);
    if (added) {
      mover.addSelectingPanel();
      Log.trace(String.format("set mover: %s", mover));
    } else {
      mover.removeSelectingPanel();
      Log.trace(String.format("unset mover: %s", mover));
      movers.remove(mover);
    }
    return added;
  }

  /**
   * clear all movers.
   */
  public void clearAllMovers() {
    for (ErMover mover : movers) {
      Log.trace(String.format("unset mover: %s", mover));
      mover.removeSelectingPanel();
    }
    movers.clear();
  }

  /**
   * get all movers.
   *
   * @return all movers
   */
  public Set<ErMover> getAllMovers() {
    return movers;
  }
}
