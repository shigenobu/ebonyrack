package com.walksocket.er.custom;

import com.walksocket.er.Log;
import com.walksocket.er.custom.ErConnectorStyle.LineStyle;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;

/**
 * ErConnector.
 */
public class ErConnector extends JPanel {

  /**
   * relation.
   */
  private boolean relation;

  /**
   * self relation.
   */
  private final boolean selfRelation;

  /**
   * relation data maps.
   */
  private final Map<ErConnectorEndpointRelation, List<String>> relationDataMaps = new HashMap<>();

  /**
   * positioned;
   */
  private final ErConnectorPositioned positioned;

  /**
   * src.
   */
  private ErConnectorEndpoint src;

  /**
   * dst.
   */
  private ErConnectorEndpoint dst;

  /**
   * line style.
   */
  private final LineStyle lineStyle;

  /**
   * sort name.
   */
  private String sortName;

  /**
   * lines.
   */
  private final List<JPanel> lines = new ArrayList<>();

  /**
   * adapter.
   */
  private final ErConnectorMouseAdapter adapter;

  /**
   * start.
   */
  private Point start;

  /**
   * end.
   */
  private Point end;

  /**
   * old z.
   */
  private int oldZ;

  /**
   * line color.
   */
  private Color lineColor = ErConnectorColor.DEFAULT_COLOR;

  /**
   * Constructor.
   *
   * @param positioned positioned
   * @param src        src
   * @param dst        end
   * @param lineStyle  lineStyle
   */
  public ErConnector(ErConnectorPositioned positioned, ErConnectorEndpoint src,
      ErConnectorEndpoint dst, LineStyle lineStyle) {
    this.positioned = positioned;
    this.src = src;
    this.dst = dst;
    this.lineStyle = lineStyle;
    this.adapter = new ErConnectorMouseAdapter(this);

    // layout
    setLayout(null);

    // init
    src.addConnector(this);
    dst.addConnector(this);

    // sort name
    if (src instanceof ErConnectorEndpointOrigin) {
      var origin = (ErConnectorEndpointOrigin) src;
      this.sortName = origin.getNameForSort();
    } else if (dst instanceof ErConnectorEndpointOrigin) {
      var origin = (ErConnectorEndpointOrigin) dst;
      this.sortName = origin.getNameForSort();
    }

    // relation
    if (src instanceof ErConnectorEndpointRelation && dst instanceof ErConnectorEndpointRelation) {
      var relation = (ErConnectorEndpointRelation) src;
      this.sortName = relation.getNameForSort();
      this.relation = true;
    }

    // self relation
    selfRelation = src == dst;

    // focus
    var connector = this;
    setFocusable(true);
    addFocusListener(new FocusAdapter() {
      @Override
      public void focusGained(FocusEvent e) {
        if (src != null) {
          src.zFirst(ErConnectorColor.SELECTED_BORDER);
          if (relation) {
            var r = (ErConnectorEndpointRelation) src;
            r.strengthening(relationDataMaps.get(r), ErConnectorColor.SELECTED_COLOR);
          }
        }
        if (dst != null) {
          dst.zFirst(ErConnectorColor.SELECTED_BORDER);
          if (relation) {
            var r = (ErConnectorEndpointRelation) dst;
            r.strengthening(relationDataMaps.get(r), ErConnectorColor.SELECTED_COLOR);
          }
        }
        zFirst(null, ErConnectorColor.FOCUSED_COLOR);
      }

      @Override
      public void focusLost(FocusEvent e) {
        zRestore(null);
        if (src != null) {
          src.zRestore();
          if (relation) {
            var r = (ErConnectorEndpointRelation) src;
            r.weakening(relationDataMaps.get(r));
          }
        }
        if (dst != null) {
          dst.zRestore();
          if (relation) {
            var r = (ErConnectorEndpointRelation) dst;
            r.weakening(relationDataMaps.get(r));
          }
        }
      }
    });

    // draw
    draw();
  }

  /**
   * get sort name.
   *
   * @return sort name.
   */
  public String getSortName() {
    return sortName;
  }

  /**
   * set sort name.
   *
   * @param sortName sort name
   */
  public void setSortName(String sortName) {
    this.sortName = sortName;
  }

  /**
   * draw.
   */
  public void draw() {
    if (src == null || dst == null) {
      return;
    }

    // ---
    var s = getConnectionPoint(src, dst);
    var e = getConnectionPoint(dst, src);
    if (selfRelation) {
      s = new Point(src.getX() + src.getWidth(), src.getY() + src.getHeight() / 2);
      e = new Point(src.getX() + src.getWidth() / 2, src.getY());
    }
    Log.trace(String.format("s -> e : %s -> %s", s, e));

    // ---
    var minX = src.getX() < dst.getX() ? src.getX() : dst.getX();
    var maxX =
        src.getX() + src.getWidth() > dst.getX() + dst.getWidth() ? src.getX() + src.getWidth()
            : dst.getX() + dst.getWidth();
    var minY = src.getY() < dst.getY() ? src.getY() : dst.getY();
    var maxY =
        src.getY() + src.getHeight() > dst.getY() + dst.getHeight() ? src.getY() + src.getHeight()
            : dst.getY() + dst.getHeight();
    if (selfRelation) {
      minX -= 100;
      minY -= 100;
      maxX += 100;
      maxY += 100;
    }

    start = new Point((int) (s.getX() - minX), (int) (s.getY() - minY));
    end = new Point((int) (e.getX() - minX), (int) (e.getY() - minY));
    Log.trace(String.format("start:%s", start));
    Log.trace(String.format("end:%s", end));

    setBackground(new Color(0, 0, 0, 0));
//    setBackground(new Color(255, 0, 0, 20));
    setBounds(minX, minY, maxX - minX, maxY - minY);

    // ---
    for (var line : lines) {
      line.removeMouseListener(adapter);
      remove(line);
    }
    lines.clear();

    // ---
    if (!selfRelation) {
      // y = ax + b
      var a = (end.getY() - start.getY()) / (end.getX() - start.getX());
      var b = start.getY() - a * start.getX();
      Log.trace(String.format("a:%s, b:%s", a, b));

      var dx = start.getX() > end.getX() ? -1 : 1;
      var sx = (int) start.getX();
      var ex = (int) end.getX();
      while (sx != ex) {
        var xx = sx;
        var yy = a * xx + b;
        var p = new JPanel();
        p.setBounds(xx, (int) yy, 10, 10);
        lines.add(p);

        sx += dx;
      }

      var dy = start.getY() > end.getY() ? -1 : 1;
      var sy = (int) start.getY();
      var ey = (int) end.getY();
      while (sy != ey) {
        var yy = sy;
        var xx = (yy - b) / a;
        var p = new JPanel();
        p.setBounds((int) xx, yy, 10, 10);
        lines.add(p);

        sy += dy;
      }
    } else {
      for (int i = 0; i < 4; i++) {
        var xx = start.getX() + i * 10;
        var yy = start.getY() - 10;
        var p = new JPanel();
        p.setBounds((int) xx, (int) yy, 20, 20);
        lines.add(p);
      }

      var sy = (int) start.getY();
      var ey = (int) end.getY() - 40;
      while (sy != ey) {
        var xx = (int) start.getX() + 30 - 10;
        var yy = sy;
        var p = new JPanel();
        p.setBounds(xx, yy, 20, 20);
        lines.add(p);

        sy--;
      }

      var sx = (int) start.getX() + 30;
      var ex = (int) end.getX();
      while (sx != ex) {
        var xx = sx - 10;
        var yy = sy;
        var p = new JPanel();
        p.setBounds(xx, yy, 20, 20);
        lines.add(p);

        sx--;
      }

      for (int i = 3; i >= 0; i--) {
        var xx = end.getX() - 10;
        var yy = end.getY() - i * 10;
        var p = new JPanel();
        p.setBounds((int) xx, (int) yy, 20, 20);
        lines.add(p);
      }
    }

    // ---
    for (var line : lines) {
      add(line);
      line.addMouseListener(adapter);
    }

    // complete
    src.connectComplete(this, dst);
    dst.connectComplete(this, src);
  }

  /**
   * get connection point.
   *
   * @param e1 e1
   * @param e2 e2
   * @return connection point
   */
  private Point getConnectionPoint(ErConnectorEndpoint e1, ErConnectorEndpoint e2) {
    var e1Cx = e1.getX() + e1.getWidth() / 2;
    var e1Cy = e1.getY() + e1.getHeight() / 2;
    var e2Cx = e2.getX() + e2.getWidth() / 2;
    var e2Cy = e2.getY() + e2.getHeight() / 2;

    // ax + by + c = 0
    var a = e1Cy - e2Cy;
    var b = e2Cx - e1Cx;
    var c = e1Cx * e2Cy - e2Cx * e1Cy;

    var directionX = e2Cx - e1Cx;
    var directionY = e2Cy - e1Cy;
    // top
    if (a != 0 && directionY < 0) {
      var y = e1.getY();
      var x = -(b * y + c) / a;
      if (e1.getX() <= x && x <= e1.getX() + e1.getWidth()) {
        return new Point(x, y);
      }
    }
    // right
    if (b != 0 && directionX > 0) {
      var x = e1.getX() + e1.getWidth();
      var y = -(a * x + c) / b;
      if (e1.getY() <= y && y <= e1.getY() + e1.getHeight()) {
        return new Point(x, y);
      }
    }
    // bottom
    if (a != 0 && directionY > 0) {
      double y = e1.getY() + e1.getHeight();
      var x = -(b * y + c) / a;
      if (e1.getX() <= x && x <= e1.getX() + e1.getWidth()) {
        return new Point((int) x, (int) y);
      }
    }
    // left
    if (b != 0 && directionX < 0) {
      var x = e1.getX();
      var y = -(a * x + c) / b;
      if (e1.getY() <= y && y <= e1.getY() + e1.getHeight()) {
        return new Point(x, y);
      }
    }

    return new Point(e1.getX(), e1.getY());
  }

  /**
   * set relation data.
   *
   * @param endpointRelation endpointRelation
   * @param data             data
   */
  public void setRelationData(ErConnectorEndpointRelation endpointRelation, List<String> data) {
    relationDataMaps.put(endpointRelation, data);
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);

    if (start != null && end != null) {
      var g2 = (Graphics2D) g;
      if (relation) {
        g2.setStroke(new BasicStroke(1.0f));
      } else {
        g2.setStroke(new BasicStroke(1.0f,
            BasicStroke.JOIN_ROUND,
            BasicStroke.CAP_BUTT,
            1.0f,
            new float[]{20.0f, 5.0f, 3.0f, 5.0f},
            0.0f));
      }
      g2.setColor(lineColor);
      if (!selfRelation) {
        g2.drawLine((int) start.getX(), (int) start.getY(), (int) end.getX(), (int) end.getY());
      } else {
        g2.drawLine((int) start.getX(), (int) start.getY(), (int) start.getX() + 30,
            (int) start.getY());
        g2.drawLine((int) start.getX() + 30, (int) start.getY(), (int) start.getX() + 30,
            (int) end.getY() - 30);
        g2.drawLine((int) start.getX() + 30, (int) end.getY() - 30, (int) end.getX(),
            (int) end.getY() - 30);
        g2.drawLine((int) end.getX(), (int) end.getY() - 30, (int) end.getX(), (int) end.getY());
      }

      if (relation) {
        var ls = lineStyle.getStyle().split("->");
        if (ls.length > 1) {
          var s = ls[0].trim();
          var e = ls[1].trim();

          var fontSize = 13;
          var font = g2.getFont().deriveFont(Font.BOLD, (float) fontSize);
          g2.setFont(font);
          var offset = 3;

          var sx = 0;
          var sy = 0;
          var ex = 0;
          var ey = 0;

          if (!selfRelation) {
            if (start.getX() < end.getX()) {
              sx = (int) start.getX() + offset;
              ex = (int) end.getX() - fontSize * (e.length() == 1 ? 1 : 2) - offset;
            } else {
              sx = (int) start.getX() - fontSize * (s.length() == 1 ? 1 : 2) - offset;
              ex = (int) end.getX() + offset;
            }

            if (start.getY() < end.getY()) {
              sy = (int) start.getY() + fontSize;
              ey = (int) end.getY() - offset;
            } else {
              sy = (int) start.getY() - offset;
              ey = (int) end.getY() + fontSize;
            }
          } else {
            sx = (int) start.getX() + offset;
            sy = (int) start.getY() + fontSize;

            ex = (int) end.getX() + offset;
            ey = (int) end.getY() - offset;
          }

          g2.drawString(s, sx, sy);
          g2.drawString(e, ex, ey);
        }
      }

      g2.dispose();
    }
  }

  /**
   * clear.
   *
   * @param endpoint endpoint
   */
  public void clear(ErConnectorEndpoint endpoint) {
    if (endpoint == null) {
      return;
    }

    if (endpoint == src) {
      src.disconnectComplete(this, dst);
      src = null;
      if (dst != null) {
        dst.removeConnector(this);
      }
    } else if (endpoint == dst) {
      dst.disconnectComplete(this, src);
      dst = null;
      if (src != null) {
        src.removeConnector(this);
      }
    }

    for (var line : lines) {
      line.removeMouseListener(adapter);
      remove(line);
    }
    lines.clear();
  }

  /**
   * z first.
   *
   * @param endpoint endpoint
   * @param color    color
   */
  public void zFirst(ErConnectorEndpoint endpoint, Color color) {
    if (endpoint != null) {
      if (endpoint == src && dst != null) {
        dst.zFirst(ErConnectorColor.getBorder(color));
      } else if (endpoint == dst && src != null) {
        src.zFirst(ErConnectorColor.getBorder(color));
      }
    }

    lineColor = color;

    oldZ = positioned.getComponentZOrder(this);
    if (oldZ != -1 && oldZ < positioned.getComponentCount()) {
      positioned.setComponentZOrder(this, 0);
    }
  }

  /**
   * z restore.
   *
   * @param endpoint endpoint
   */
  public void zRestore(ErConnectorEndpoint endpoint) {
    lineColor = ErConnectorColor.DEFAULT_COLOR;

    var z = positioned.getComponentZOrder(this);
    if (z != -1 && oldZ < positioned.getComponentCount()) {
      positioned.setComponentZOrder(this, oldZ);
    }

    if (endpoint != null) {
      if (endpoint == src && dst != null) {
        dst.zRestore();
      } else if (endpoint == dst && src != null) {
        src.zRestore();
      }
    }
  }

  /**
   * ErConnectorMouseAdapter.
   */
  public class ErConnectorMouseAdapter extends MouseAdapter {

    /**
     * connector.
     */
    private final ErConnector connector;

    /**
     * Constructor.
     *
     * @param connector connector
     */
    public ErConnectorMouseAdapter(ErConnector connector) {
      this.connector = connector;
    }

    @Override
    public void mousePressed(MouseEvent e) {
      if (e.isPopupTrigger()) {
        doPop(e);
      }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
      // focus
      connector.requestFocusInWindow();

      if (e.isPopupTrigger()) {
        doPop(e);
      }
    }

    /**
     * doPop.
     *
     * @param e event
     */
    private void doPop(MouseEvent e) {
      if (relation) {
        return;
      }
      var menu = new PopupMenu(connector);
      menu.show(e.getComponent(), e.getX(), e.getY());
    }
  }

  /**
   * PopupMenu.
   */
  public class PopupMenu extends JPopupMenu {

    /**
     * Constructor.
     *
     * @param connector connector
     */
    public PopupMenu(ErConnector connector) {
      // remove
      var menuItemRemove = new JMenuItem("Remove connector");
      menuItemRemove.addActionListener(actionEvent -> {
        connector.clear(src);
        connector.clear(dst);
      });
      add(menuItemRemove);
    }
  }
}
