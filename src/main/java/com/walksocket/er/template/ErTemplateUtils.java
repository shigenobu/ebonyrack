package com.walksocket.er.template;

import org.apache.commons.lang3.StringUtils;

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

  /**
   * to camel case.
   *
   * @param value value
   * @return value
   */
  public static String toCamelCase(Object value) {
    if (value == null) {
      return "";
    }
    var snake = value.toString();
    var sb = new StringBuilder(snake.length() + snake.length());
    for (int i = 0; i < snake.length(); i++) {
      var c = snake.charAt(i);
      if (c == '_') {
        sb.append((i + 1) < snake.length() ? Character.toUpperCase(snake.charAt(++i)) : "");
      } else {
        sb.append(sb.length() == 0 ? Character.toUpperCase(c) : Character.toLowerCase(c));
      }
    }
    return sb.toString();
  }

  /**
   * to snake case.
   *
   * @param value value
   * @return value
   */
  public static String toSnakeCase(Object value) {
    if (value == null) {
      return "";
    }
    var camel = value.toString();
    var sb = new StringBuilder(camel.length() + camel.length());
    for (int i = 0; i < camel.length(); i++) {
      var c = camel.charAt(i);
      if (Character.isUpperCase(c)) {
        sb.append(sb.length() != 0 ? '_' : "").append(Character.toLowerCase(c));
      } else {
        sb.append(Character.toLowerCase(c));
      }
    }
    return sb.toString();
  }

  /**
   * uc first.
   *
   * @param value value
   * @return value
   */
  public static String ucFirst(Object value) {
    if (value == null) {
      return "";
    }
    return StringUtils.capitalize(value.toString());
  }

  /**
   * lc first.
   *
   * @param value value
   * @return value
   */
  public static String lcFirst(Object value) {
    if (value == null) {
      return "";
    }
    return StringUtils.uncapitalize(value.toString());
  }
}
