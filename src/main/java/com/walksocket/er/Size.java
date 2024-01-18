package com.walksocket.er;

import java.awt.Dimension;
import java.awt.Toolkit;

/**
 * Size.
 */
public class Size {

  /**
   * Screen.
   */
  public static class Screen {

    /**
     * screen.
     */
    private static final Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();

    /**
     * get width.
     *
     * @return screen width
     */
    public static int getWidth() {
      return dimension.width;
    }

    /**
     * get height.
     *
     * @return screen height
     */
    public static int getHeight() {
      return dimension.height;
    }
  }

  /**
   * WindowStartup.
   */
  public static class WindowStartup {

    /**
     * width.
     */
    public static final int WIDTH = 1024;

    /**
     * height.
     */
    public static final int HEIGHT = 600;
  }

  /**
   * WindowMain.
   */
  public static class WindowMain {

    /**
     * width.
     */
    public static final int WIDTH = 1366;

    /**
     * height.
     */
    public static final int HEIGHT = 768;
  }

  /**
   * DialogLarge.
   */
  public static class DialogLarge {

    /**
     * width.
     */
    public static final int WIDTH = 1366;

    /**
     * height.
     */
    public static final int HEIGHT = 768;
  }

  /**
   * DialogMedium.
   */
  public static class DialogMedium {

    /**
     * width.
     */
    public static final int WIDTH = 1024;

    /**
     * height.
     */
    public static final int HEIGHT = 600;
  }

  /**
   * DialogSmall.
   */
  public static class DialogSmall {

    /**
     * width.
     */
    public static final int WIDTH = 720;

    /**
     * height.
     */
    public static final int HEIGHT = 480;
  }

  /**
   * DialogMini.
   */
  public static class DialogMini {

    /**
     * width.
     */
    public static final int WIDTH = 320;

    /**
     * height.
     */
    public static final int HEIGHT = 240;
  }

  /**
   * DialogProcessing.
   */
  public static class DialogProcessing {

    /**
     * width.
     */
    public static final int WIDTH = 400;

    /**
     * height.
     */
    public static final int HEIGHT = 400;
  }

  /**
   * DialogUsed.
   */
  public static class DialogUsed {

    /**
     * width.
     */
    public static final int WIDTH = 640;

    /**
     * height.
     */
    public static final int HEIGHT = 768;
  }
}
