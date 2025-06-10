package com.walksocket.er.custom;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.Stroke;
import javax.swing.border.Border;

/**
 * ErDottedUnderlineBorder.
 *
 * @see <a href="https://osima.jp/posts/create-java-swing-custom-border/">UnderlineBorder</a>
 */
public class ErDottedUnderlineBorder implements Border {

  private final Insets insets = new Insets(0, 0, 10, 0);

  private final Stroke dotted = new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0,
      new float[]{3}, 0);

  @Override
  public Insets getBorderInsets(Component arg0) {
    return insets;
  }

  @Override
  public boolean isBorderOpaque() {
    return true;
  }

  @Override
  public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
    Insets insets = getBorderInsets(c);

    Color keepC = g.getColor();
    int x1 = x;
    int y1 = y + height - insets.bottom / 2;

    int x2 = x + width;
    int y2 = y1;

    var g2 = (Graphics2D) g;
    g2.setStroke(dotted);

    g2.setColor(Color.LIGHT_GRAY);
    g2.drawLine(x1, y1, x2, y2);

    x1 = x;
    y1 = y + height - (insets.bottom / 2) + 1;

    x2 = x + width;
    y2 = y1;

    g2.setColor(Color.WHITE);
    g2.drawLine(x1, y1, x2, y2);

    g2.setColor(keepC);
  }
}
