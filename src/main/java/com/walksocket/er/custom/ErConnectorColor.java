package com.walksocket.er.custom;

import java.awt.Color;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

/**
 * ErConnectorColor.
 */
public class ErConnectorColor {

  /**
   * default color.
   */
  public static final Color DEFAULT_COLOR = Color.LIGHT_GRAY;

  /**
   * focused color.
   */
  public static final Color FOCUSED_COLOR = new Color(248, 0, 0);

  /**
   * selected color.
   */
  public static final Color SELECTED_COLOR = new Color(248, 132, 0);

  /**
   * selecting color.
   */
  public static final Color SELECTING_COLOR = new Color(25, 0, 255);

  /**
   * default border.
   */
  public static final Border DEFAULT_BORDER = new LineBorder(DEFAULT_COLOR, 2, true);

  /**
   * focused border.
   */
  public static final Border FOCUSED_BORDER = new LineBorder(FOCUSED_COLOR, 2, true);

  /**
   * selected border.
   */
  public static final Border SELECTED_BORDER = new LineBorder(SELECTED_COLOR, 2, true);

  /**
   * selecting border.
   */
  public static final Border SELECTING_BORDER = new LineBorder(SELECTING_COLOR, 2, false);

  /**
   * get border.
   *
   * @param color color
   * @return border
   */
  public static Border getBorder(Color color) {
    if (color == FOCUSED_COLOR) {
      return FOCUSED_BORDER;
    }
    if (color == SELECTED_COLOR) {
      return SELECTED_BORDER;
    }
    if (color == SELECTING_COLOR) {
      return SELECTING_BORDER;
    }
    return DEFAULT_BORDER;
  }
}
