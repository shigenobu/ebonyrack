package com.walksocket.er.custom;

import com.walksocket.er.Log;
import com.walksocket.er.Utils;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.LinkedList;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.border.Border;

/**
 * ErConnectorEndpoint.
 */
public abstract class ErConnectorEndpoint extends JPanel {

  /**
   * old z.
   */
  private int oldZ;

  /**
   * positioned.
   */
  private final ErConnectorPositioned positioned;

  /**
   * connectors.
   */
  private final LinkedList<ErConnector> connectors = new LinkedList<>();

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
   * @param positioned positioned
   */
  public ErConnectorEndpoint(ErConnectorPositioned positioned) {
    this.positioned = positioned;

    // border
    setBorder(ErConnectorColor.DEFAULT_BORDER);

    // focus
    var endpoint = this;
    setFocusable(true);
    addFocusListener(new FocusAdapter() {
      @Override
      public void focusGained(FocusEvent e) {
        for (var c : connectors) {
          c.zFirst(endpoint, ErConnectorColor.SELECTED_COLOR);
        }
        zFirst(ErConnectorColor.FOCUSED_BORDER);
      }

      @Override
      public void focusLost(FocusEvent e) {
        zRestore();
        for (var c : connectors) {
          c.zRestore(endpoint);
        }
      }
    });
    addMouseListener(new MouseAdapter() {
      @Override
      public void mouseReleased(MouseEvent e) {
        requestFocusInWindow();
      }
    });

    // moving
    ActionListener movingListener = actionEvent -> {
      if (movingInitPoint == null) {
        return;
      }

      // https://ateraimemo.com/Swing/MouseInfo.html
      var pi = MouseInfo.getPointerInfo();
      Point pt = pi.getLocation();
      SwingUtilities.convertPointFromScreen(pt, positioned);

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
   * get connectors.
   *
   * @return connectors
   */
  public LinkedList<ErConnector> getConnectors() {
    return connectors;
  }

  /**
   * add connector.
   *
   * @param connector connector
   */
  public void addConnector(ErConnector connector) {
    connectors.add(connector);
  }

  /**
   * remove connector.
   *
   * @param connector connector
   */
  public void removeConnector(ErConnector connector) {
    Log.trace(String.format("remove connector '%s' from '%s'", connector, this));
    connector.clear(this);
    connectors.remove(connector);
  }

  /**
   * remove all connectors.
   */
  public void removeAllConnectors() {
    for (var connector : connectors) {
      Log.trace(String.format("remove connector '%s' from '%s'", connector, this));
      connector.clear(this);
    }
    connectors.clear();
  }

  /**
   * redraw all connectors.
   */
  public void redrawAllConnectors() {
    for (var connector : connectors) {
      connector.draw();
    }
  }

  /**
   * connect complete.
   *
   * @param connector     connector
   * @param otherEndpoint other endpoint
   */
  public void connectComplete(ErConnector connector, ErConnectorEndpoint otherEndpoint) {
    Log.trace(String.format("connect complete %s from %s to %s", connector, this, otherEndpoint));
  }

  /**
   * disconnect complete.
   *
   * @param connector     connector
   * @param otherEndpoint other endpoint
   */
  public void disconnectComplete(ErConnector connector, ErConnectorEndpoint otherEndpoint) {
    Log.trace(
        String.format("disconnect complete %s from %s to %s", connector, this, otherEndpoint));
  }

  /**
   * z first.
   *
   * @param border border
   */
  public void zFirst(Border border) {
    oldZ = positioned.getComponentZOrder(this);
    if (oldZ != -1 && oldZ < positioned.getComponentCount()) {
      positioned.setComponentZOrder(this, 0);
    }
    setBorder(border);
  }

  /**
   * z restore.
   */
  public void zRestore() {
    var z = positioned.getComponentZOrder(this);
    if (z != -1 && oldZ < positioned.getComponentCount()) {
      positioned.setComponentZOrder(this, oldZ);
    }
    setBorder(ErConnectorColor.DEFAULT_BORDER);
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
