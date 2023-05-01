package com.walksocket.er.custom;

import java.util.ArrayList;
import java.util.List;

/**
 * ErConnectorStyle.
 */
public class ErConnectorStyle {

  /**
   * style simple.
   */
  private static final String STYLE_SIMPLE = "";

  /**
   * style zero one.
   */
  private static final String STYLE_ZERO_ONE = "0..1 -> 1";

  /**
   * style one.
   */
  private static final String STYLE_ONE = "1 -> 1";

  /**
   * style zero n.
   */
  private static final String STYLE_ZERO_N = "0..N -> 1";

  /**
   * style one n.
   */
  private static final String STYLE_ONE_N = "N -> 1";

  /**
   * LineStyle.
   */
  public enum LineStyle {
    /**
     * SIMPLE.
     */
    SIMPLE(STYLE_SIMPLE),

    /**
     * ZERO_ONE.
     */
    ZERO_ONE(STYLE_ZERO_ONE),

    /**
     * ONE.
     */
    ONE(STYLE_ONE),

    /**
     * ZERO_N.
     */
    ZERO_N(STYLE_ZERO_N),

    /**
     * ONE_N
     */
    ONE_N(STYLE_ONE_N),
    ;

    /**
     * style.
     */
    private final String style;

    /**
     * Constructor.
     *
     * @param style style
     */
    LineStyle(String style) {
      this.style = style;
    }

    /**
     * get style.
     *
     * @return style
     */
    public String getStyle() {
      return style;
    }
  }

  /**
   * get line style.
   *
   * @param style style
   * @return line style.
   */
  public static LineStyle getLineStyle(String style) {
    for (var line : LineStyle.values()) {
      if (line.getStyle().equals(style)) {
        return line;
      }
    }
    return LineStyle.SIMPLE;
  }

  /**
   * style list.
   */
  private static final List<String> styleList = new ArrayList<>();

  static {
    styleList.add(STYLE_SIMPLE);
    styleList.add(STYLE_ZERO_ONE);
    styleList.add(STYLE_ONE);
    styleList.add(STYLE_ZERO_N);
    styleList.add(STYLE_ONE_N);
  }

  /**
   * get style list.
   *
   * @return style list
   */
  public static List<String> getStyleList() {
    return styleList;
  }
}
