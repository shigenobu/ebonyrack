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
   * style reverse simple.
   */
  private static final String STYLE_REVERSE_SIMPLE = "";

  /**
   * style reverse zero one.
   */
  private static final String STYLE_REVERSE_ZERO_ONE = "1 -> 0..1";

  /**
   * style reverse one.
   */
  private static final String STYLE_REVERSE_ONE = "1 -> 1";

  /**
   * style reverse zero n.
   */
  private static final String STYLE_REVERSE_ZERO_N = "1 -> 0..N";

  /**
   * style reverse one n.
   */
  private static final String STYLE_REVERSE_ONE_N = "1 -> N";

  /**
   * LineStyle.
   */
  public enum LineStyle {
    /**
     * SIMPLE.
     */
    SIMPLE(STYLE_SIMPLE, STYLE_REVERSE_SIMPLE),

    /**
     * ZERO_ONE.
     */
    ZERO_ONE(STYLE_ZERO_ONE, STYLE_REVERSE_ZERO_ONE),

    /**
     * ONE.
     */
    ONE(STYLE_ONE, STYLE_REVERSE_ONE),

    /**
     * ZERO_N.
     */
    ZERO_N(STYLE_ZERO_N, STYLE_REVERSE_ZERO_N),

    /**
     * ONE_N
     */
    ONE_N(STYLE_ONE_N, STYLE_REVERSE_ONE_N),
    ;

    /**
     * style.
     */
    private final String style;

    /**
     * reverse style.
     */
    private final String reverseStyle;

    /**
     * Constructor.
     *
     * @param style        style
     * @param reverseStyle reverseStyle
     */
    LineStyle(String style, String reverseStyle) {
      this.style = style;
      this.reverseStyle = reverseStyle;
    }

    /**
     * get style.
     *
     * @return style
     */
    public String getStyle() {
      return style;
    }

    /**
     * get show style.
     *
     * @param reverse reverse
     */
    public String getShowStyle(boolean reverse) {
      if (!reverse) {
        return style;
      }
      return reverseStyle;
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
