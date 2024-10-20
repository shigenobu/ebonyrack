package com.walksocket.er;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Collection;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * Utils.
 */
public class Utils {

  /**
   * is null or empty for object.
   *
   * @param src object
   * @return if null or empty, true
   */
  public static boolean isNullOrEmpty(Object src) {
    return src == null || src.toString().isEmpty();
  }

  /**
   * is null or empty for string.
   *
   * @param src string
   * @return if null or empty, true
   */
  public static boolean isNullOrEmpty(String src) {
    return src == null || src.isEmpty();
  }

  /**
   * is null or empty for array.
   *
   * @param src array
   * @return if null or empty, true
   */
  public static boolean isNullOrEmpty(byte[] src) {
    return src == null || src.length == 0;
  }

  /**
   * is null or empty for array.
   *
   * @param src array
   * @return if null or empty, true
   */
  public static boolean isNullOrEmpty(Object[] src) {
    return src == null || src.length == 0;
  }

  /**
   * is null or empty for collection.
   *
   * @param src list
   * @return if null or empty, true
   */
  public static <T> boolean isNullOrEmpty(Collection<T> src) {
    return src == null || src.isEmpty();
  }

  /**
   * get hash.
   *
   * @param src base string
   * @return hashed string
   */
  public static String getHash(String src) {
    var builder = new StringBuilder();
    byte[] data;
    try {
      data = MessageDigest.getInstance("MD5").digest(src.getBytes(StandardCharsets.UTF_8));
      for (byte d : data) {
        builder.append(String.format("%02x", d));
      }
    } catch (NoSuchAlgorithmException e) {
      Log.error(e);
    }
    return builder.toString();
  }

  /**
   * floor degree.
   *
   * @param src  src
   * @param unit unit
   * @return degree at unit
   */
  public static int floorDegree(int src, int unit) {
    return (int) ((Math.floor((double) src / unit)) * unit);
  }

  /**
   * floor degree.
   *
   * @param src  src
   * @param unit unit
   * @return degree at unit
   */
  public static int floorDegree(double src, int unit) {
    return (int) ((Math.floor(src / unit)) * unit);
  }

  /**
   * is number.
   *
   * @param src string
   * @return if number, true
   */
  public static boolean isNumber(String src) {
    try {
      Long.parseLong(src);
    } catch (NumberFormatException e) {
      return false;
    }
    return true;
  }

  /**
   * to int.
   *
   * @param src string
   * @return int
   */
  public static int toInt(String src) {
    try {
      return Integer.parseInt(src);
    } catch (NumberFormatException e) {
      Log.error(e);
    }
    return 0;
  }

  /**
   * get random string.
   *
   * @return random string
   */
  public static String randomString() {
    return RandomStringUtils.secure().nextAlphanumeric(32).toLowerCase();
  }

  /**
   * quote.
   *
   * @param src string
   * @return quoted string
   */
  public static String quote(String src) {
    if (src == null) {
      return "";
    }
    return StringUtils.replace(src, "'", "''");
  }

  /**
   * remove back quote.
   *
   * @param src string
   * @return back quote removed string
   */
  public static String removeBackQuote(String src) {
    if (src == null) {
      return "";
    }
    return StringUtils.replace(src, "`", "");
  }

  /**
   * remove single quote.
   *
   * @param src string
   * @return single quote removed string
   */
  public static String removeSingleQuote(String src) {
    if (src == null) {
      return "";
    }
    return StringUtils.replace(src, "'", "");
  }

  /**
   * get string.
   *
   * @param textField textField
   * @return value
   */
  public static String getString(JTextField textField) {
    if (textField == null) {
      return "";
    }
    var value = textField.getText();
    if (value == null) {
      return "";
    }
    return value.trim();
  }

  /**
   * get string.
   *
   * @param comboBox comboBox
   * @return value
   */
  public static <T> String getString(JComboBox<T> comboBox) {
    if (comboBox == null) {
      return "";
    }
    var value = comboBox.getSelectedItem();
    if (value == null) {
      return "";
    }
    return ((String) value).trim();
  }

  /**
   * get string.
   *
   * @param textArea textArea
   * @return value
   */
  public static String getString(JTextArea textArea) {
    if (textArea == null) {
      return "";
    }
    var value = textArea.getText();
    if (value == null) {
      return "";
    }
    return value.trim();
  }

  /**
   * get string.
   *
   * @param object object
   * @return value
   */
  public static String getString(Object object) {
    if (object == null) {
      return "";
    }
    return object.toString().trim();
  }
}
