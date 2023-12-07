package com.walksocket.er.template;

/**
 * ErTemplateUtils.
 */
public class ErTemplateUtils {

  /**
   * non escape.
   *
   * @param value
   * @return non escape value
   */
  public static String nonEscape(Object value) {
    if (value == null) {
      return "";
    }
    return value.toString();
  }

  /**
   * line break to br.
   *
   * @param value value
   * @return value replaced to <br />
   */
  public static String nl2br(Object value) {
    if (value == null) {
      return "";
    }
    String valueString = value.toString();
    valueString = valueString.replaceAll("\r", "");
    valueString = valueString.replaceAll("\n", "<br />");
    return valueString;
  }
}
