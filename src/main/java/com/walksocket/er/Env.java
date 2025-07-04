package com.walksocket.er;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class Env {

  /**
   * is debug.
   */
  private static boolean isDebug = false;

  /**
   * is readonly.
   */
  private static boolean isReadonly = false;

  /**
   * home directory.
   */
  private static String home = System.getProperty("user.home") + File.separator + "." + Const.TITLE;

  static {
    var tmpEnv = System.getenv("ER_ENV");
    if (tmpEnv != null && tmpEnv.equals("DEBUG")) {
      isDebug = true;
    }

    var tmpReadonly = System.getenv("ER_READONLY");
    if (tmpReadonly != null && tmpReadonly.equals("YES")) {
      isReadonly = true;
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
   * is readonly.
   *
   * @return if readonly, true
   */
  public static boolean isReadonly() {
    return isReadonly;
  }

  /**
   * set readonly.
   */
  public static void setReadonly() {
    isReadonly = true;
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
    }
    var sampleFileNameList = new ArrayList<String>();
    sampleFileNameList.add("Sample.txt.vm");
    sampleFileNameList.add("Sample.java.vm");
    sampleFileNameList.add("__SampleMacro.vm");
    for (var sampleFileName : sampleFileNameList) {
      var sampleFile = new File(f, sampleFileName);
      if (!sampleFile.exists()) {
        try {
          var stream = App.class.getClassLoader()
              .getResourceAsStream(String.format("template/%s", sampleFileName));
          var data = FileUtils.readString(stream);
          FileUtils.writeString(new FileOutputStream(sampleFile), data);
        } catch (IOException e) {
          Log.error(e);
        }
      }
    }
    return f.getAbsolutePath();
  }
}
