package com.walksocket.er.custom;

import com.walksocket.er.Log;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.LinkedList;
import javax.swing.border.Border;

/**
 * ErConnectorEndpoint.
 */
public abstract class ErConnectorEndpoint extends ErMover {

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
   * Constructor.
   *
   * @param positioned positioned
   */
  public ErConnectorEndpoint(ErConnectorPositioned positioned) {
    super(positioned);
    this.positioned = positioned;

    // border
    setBorder(ErConnectorColor.DEFAULT_BORDER);

    // focus
    var endpoint = this;
    setFocusable(true);
    addFocusListener(new FocusAdapter() {
      @Override
      public void focusGained(FocusEvent e) {
        Log.trace(String.format("focus gained: %s", endpoint));
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
   * get coords.
   *
   * @return coords
   */
  public String getCoords() {
    return String.format("%s,%s,%s,%s", getX(), getY(), getX() + getWidth(), getY() + getHeight());
  }

  /**
   * get name for sort.
   *
   * @return name for sort
   */
  public String getNameForSort() {
    return toString();
  }

  /**
   * show popup menu.
   *
   * @param mouseEvent mouseEvent
   */
  public abstract void showPopupMenu(MouseEvent mouseEvent);
}
