package com.walksocket.er;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

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
   * set debug.
   */
  public static void setDebug() {
    isDebug = true;
  }

  /**
   * get home directory.
   *
   * @return home directory
   */
  public static String getHome() {
    return home;
  }

  /**
   * get template dir.
   *
   * @return template dir
   */
  public static String getTemplateDir() {
    var f = new File(getHome(), "template");
    if (!f.exists()) {
      f.mkdir();

      var sampleFileName = "Sample.java.vm";
      var sampleFile = new File(f, sampleFileName);
      if (!sampleFile.exists()) {
        try {
          var stream = App.class.getClassLoader()
              .getResourceAsStream(String.format("template/%s", sampleFileName));
          var data = com.walksocket.er.File.readString(stream);
          com.walksocket.er.File.writeString(new FileOutputStream(sampleFile), data);
        } catch (IOException e) {
          Log.error(e);
        }
      }
    }
    return f.getAbsolutePath();
  }
}
