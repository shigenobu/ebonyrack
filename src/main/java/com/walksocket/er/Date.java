package com.walksocket.er;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Date.
 */
public class Date {

  static {
    // default locale en
    Locale.setDefault(Locale.ENGLISH);
  }

  /**
   * add seconds for now.
   */
  private static long addSeconds;

  /**
   * init.
   *
   * @param addSeconds add seconds
   */
  public static void init(long addSeconds) {
    Date.addSeconds = addSeconds;
  }

  /**
   * now.
   *
   * @return now
   */
  public static String now() {
    var sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
    sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
    var date = new java.util.Date();
    date.setTime(date.getTime() + addSeconds * 1000);
    return sdf.format(date);
  }

  /**
   * timestamp.
   *
   * @return timestamp
   */
  public static int timestamp() {
    return (int) (timestampMillis() / 1000);
  }

  /**
   * timestamp millis.
   *
   * @return timestamp millis
   */
  public static long timestampMillis() {
    var cal = Calendar.getInstance();
    return cal.getTimeInMillis();
  }
}
