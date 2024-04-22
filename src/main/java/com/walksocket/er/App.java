package com.walksocket.er;

import com.formdev.flatlaf.FlatLightLaf;
import com.walksocket.er.component.Startup;
import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

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
        .getResourceAsStream("font/Mplus1-Regular.otf")) {
      var font = Font.createFont(Font.PLAIN, stream).deriveFont(11f);
      Log.trace(String.format("font:%s", font));
      UIManager.put("CheckBoxMenuItem.acceleratorFont", font);
      UIManager.put("Button.font", font);
      UIManager.put("ToggleButton.font", font);
      UIManager.put("RadioButton.font", font);
      UIManager.put("CheckBox.font", font);
      UIManager.put("ColorChooser.font", font);
      UIManager.put("ComboBox.font", font);
      UIManager.put("Label.font", font);
      UIManager.put("List.font", font);
      UIManager.put("MenuBar.font", font);
      UIManager.put("Menu.acceleratorFont", font);
      UIManager.put("RadioButtonMenuItem.acceleratorFont", font);
      UIManager.put("MenuItem.acceleratorFont", font);
      UIManager.put("MenuItem.font", font);
      UIManager.put("RadioButtonMenuItem.font", font);
      UIManager.put("CheckBoxMenuItem.font", font);
      UIManager.put("OptionPane.buttonFont", font);
      UIManager.put("OptionPane.messageFont", font);
      UIManager.put("Menu.font", font);
      UIManager.put("PopupMenu.font", font);
      UIManager.put("OptionPane.font", font);
      UIManager.put("Panel.font", font);
      UIManager.put("ProgressBar.font", font);
      UIManager.put("ScrollPane.font", font);
      UIManager.put("Viewport.font", font);
      UIManager.put("TabbedPane.font", font);
      UIManager.put("Slider.font", font);
      UIManager.put("Table.font", font);
      UIManager.put("TableHeader.font", font);
      UIManager.put("TextField.font", font);
      UIManager.put("Spinner.font", font);
      UIManager.put("PasswordField.font", font);
      UIManager.put("TextArea.font", font);
      UIManager.put("TextPane.font", font);
      UIManager.put("EditorPane.font", font);
      UIManager.put("TabbedPane.smallFont", font);
      UIManager.put("TitledBorder.font", font);
      UIManager.put("ToolBar.font", font);
      UIManager.put("ToolTip.font", font);
      UIManager.put("Tree.font", font);
      UIManager.put("FormattedTextField.font", font);
      UIManager.put("IconButton.font", font);
      UIManager.put("InternalFrame.optionDialogTitleFont", font);
      UIManager.put("InternalFrame.paletteTitleFont", font);
      UIManager.put("InternalFrame.titleFont", font);
    } catch (IOException e) {
      Log.error(e);
    } catch (FontFormatException e) {
      Log.error(e);
    }

    // look and feel
    FlatLightLaf.setup();
    var d = UIManager.getLookAndFeelDefaults();
    d.put("Table.showHorizontalLines", true);
    d.put("Table.showVerticalLines", true);
    d.put("Tree.showDefaultIcons", true);
    d.put("apple.awt.documentModalSheet", true);

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
