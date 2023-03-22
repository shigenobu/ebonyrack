package com.walksocket.er;

import java.io.File;

public class Env {

  /**
   * is debug.
   */
  private static boolean isDebug = false;

  /**
   * home directory.
   */
  private static String home = System.getProperty("user.home") + File.separator + "." + Const.TITLE;

  static {
    var tmpEnv = System.getenv("ER_ENV");
    if (tmpEnv != null && tmpEnv.equals("DEBUG")) {
      isDebug = true;
    }

    var tmpHome = System.getenv("ER_HOME");
    if (!Utils.isNullOrEmpty(tmpHome) && new File(tmpHome).canWrite()) {
      home = tmpHome;
    }
    var f = new File(home);
    if (!f.exists()) {
      f.mkdir();
    }
  }

  /**
   * is debug.
   *
   * @return if debug, true
   */
  public static boolean isDebug() {
    return isDebug;
  }

  /**
   * get home directory.
   *
   * @return home directory
   */
  public static String getHome() {
    return home;
  }
}
