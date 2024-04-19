package com.walksocket.er;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Locale;

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
  private static int addSeconds;

  /**
   * init.
   *
   * @param addSeconds add seconds
   */
  public static void init(int addSeconds) {
    Date.addSeconds = addSeconds;
  }

  /**
   * now.
   *
   * @return now
   */
  public static String now() {
    var dt = OffsetDateTime.now(ZoneOffset.ofTotalSeconds(addSeconds));
    return dt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSXXX"));
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
