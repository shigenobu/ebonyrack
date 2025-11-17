package com.walksocket.er;

import com.formdev.flatlaf.FlatLightLaf;
import com.walksocket.er.component.Startup;
import java.awt.Component;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.io.IOException;
import java.util.Locale;
import javax.swing.Icon;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.plaf.FontUIResource;
import javax.swing.plaf.IconUIResource;

/**
 * App.
 */
public class App {

  /**
   * arg log path.
   */
  private static final String ARG_LOG_PATH = "--logPath=";

  /**
   * arg add seconds.
   */
  private static final String ARG_ADD_SECONDS = "--addSeconds=";

  /**
   * main.
   *
   * @param args args
   */
  public static void main(String[] args) throws IOException {
    // check args
    String logPath = null;
    int addSeconds = 0;
    for (String arg : args) {
      if (arg.startsWith(ARG_LOG_PATH)) {
        logPath = arg.substring(ARG_LOG_PATH.length());
      }
      if (arg.startsWith(ARG_ADD_SECONDS)) {
        addSeconds = Integer.parseInt(arg.substring(ARG_ADD_SECONDS.length()));
      }
    }

    // init date
    Date.init(addSeconds);

    // set log
    Log.open(logPath);
    Log.trace(String.format(
        "(ENV) isDebug:%s, home:%s",
        Env.isDebug(),
        Env.getHome()));
    Log.trace(String.format("(ARGS) logPath:%s", logPath));
    Log.trace(String.format("(ARGS) addSeconds:%s", addSeconds));

    // load font
    try (var stream = App.class.getClassLoader()
        .getResourceAsStream("font/migu-1c-regular.ttf")) {
      var font = Font.createFont(Font.TRUETYPE_FONT, stream).deriveFont(11f);
      var fontUiResource = new FontUIResource(font);
      UIManager.put("defaultFont", fontUiResource);
      Log.trace(String.format("font:%s", font));
      UIManager.getLookAndFeelDefaults().forEach((key, value) -> {
        if (key.toString().toLowerCase(Locale.ENGLISH).endsWith("font")) {
          UIManager.put(key, fontUiResource);
        }
      });
      GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(font);
    } catch (IOException | FontFormatException e) {
      Log.error(e);
    }

    // look and feel
    FlatLightLaf.setup();
    var d = UIManager.getLookAndFeelDefaults();
    d.put("Table.showHorizontalLines", true);
    d.put("Table.showVerticalLines", true);
    d.put("Tree.showDefaultIcons", true);
    d.put("apple.awt.documentModalSheet", true);

    var emptyIcon = new Icon() {
      @Override
      public void paintIcon(Component component, Graphics graphics, int i, int i1) {
      }

      @Override
      public int getIconWidth() {
        return 0;
      }

      @Override
      public int getIconHeight() {
        return 0;
      }
    };
    var emptyIconUiResource = new IconUIResource(emptyIcon);
    d.put("Tree.leftChildIndent", 3);
    d.put("Tree.rightChildIndent", 3);
    d.put("Tree.expandedIcon", emptyIconUiResource);
    d.put("Tree.collapsedIcon", emptyIconUiResource);
    d.put("Tree.leafIcon", emptyIconUiResource);

    // execute
    SwingUtilities.invokeLater(() -> new Startup());

    // shutdown
    Thread hook = new Thread(() -> {
      Log.trace("shutdown");
      Log.close();
    });
    Runtime.getRuntime().addShutdownHook(hook);
  }

  /**
   * get version.
   *
   * @return version
   */
  public static String getVersion() {
    return App.class.getPackage().getImplementationVersion();
  }
}
