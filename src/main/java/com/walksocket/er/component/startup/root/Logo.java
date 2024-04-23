package com.walksocket.er.component.startup.root;

import com.walksocket.er.App;
import com.walksocket.er.Const;
import com.walksocket.er.Http;
import com.walksocket.er.Log;
import com.walksocket.er.Size.WindowStartup;
import com.walksocket.er.Utils;
import com.walksocket.er.component.startup.Root;
import com.walksocket.er.custom.ErLinkLabel;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.net.URI;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingWorker;
import org.apache.maven.artifact.versioning.ComparableVersion;

/**
 * Logo.
 */
public class Logo extends JPanel {

  /**
   * Constructor.
   *
   * @param root root
   */
  public Logo(Root root) {
    // color
    var bgColor = Const.COLOR_EBONY;
    setBackground(bgColor);

    // panel - title
    var panel1 = new JPanel(new FlowLayout(FlowLayout.CENTER));
    panel1.setBackground(bgColor);
    panel1.setPreferredSize(new Dimension(WindowStartup.WIDTH - 20, WindowStartup.HEIGHT / 10));
    add(panel1);
    var labelTitle = new JLabel(
        String.format("<html><font color='white'>%s</font></html>", Const.TITLE));
    labelTitle.setFont(new Font(labelTitle.getFont().getName(), Font.BOLD, 36));
    labelTitle.setBackground(bgColor);
    panel1.add(labelTitle);

    // panel - description
    var panel2 = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
    panel2.setBackground(bgColor);
    panel2.setPreferredSize(new Dimension(WindowStartup.WIDTH - 20, WindowStartup.HEIGHT / 30));
    add(panel2);
    var labelDescription = new JLabel(String.format("<html><font color='white'>%s</font></html>",
        "Entity relationship creation tool for MariaDB."));
    labelDescription.setFont(new Font(labelDescription.getFont().getName(), Font.BOLD, 14));
    labelDescription.setBackground(bgColor);
    panel2.add(labelDescription);

    // panel - latest version
    var panel3 = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
    panel3.setBackground(bgColor);
    panel3.setPreferredSize(new Dimension(WindowStartup.WIDTH - 20, WindowStartup.HEIGHT / 40));
    add(panel3);
    (new SwingWorker<String, String>() {

      @Override
      protected String doInBackground() throws Exception {
        try {
          var appVersion = App.getVersion();
          if (Utils.isNullOrEmpty(appVersion)) {
            appVersion = "v0.0.0";
          }
          appVersion = appVersion.substring(1);

          var latestVersion = Http.getLatestVersion();
          Log.trace(String.format("latest:%s, this:%s", latestVersion, appVersion));

          var base = new ComparableVersion(appVersion);
          var compare = new ComparableVersion(latestVersion);
          if (base.compareTo(compare) >= 0) {
            return null;
          }
          if (base.compareTo(compare) < 0) {
            return latestVersion;
          }
        } catch (Exception e) {
          Log.error(e);
        }
        return null;
      }

      @Override
      protected void done() {
        try {
          var latestVersion = get();
          if (Utils.isNullOrEmpty(latestVersion)) {
            return;
          }
          var labelNewVersion = new ErLinkLabel(
              String.format(
                  "<html><font color='silver'>New version <u>v%s</u> found.</font></html>",
                  latestVersion),
              new URI(Const.VERSION_CHECK_URL)
          );
          labelNewVersion.setBackground(bgColor);
          panel3.add(labelNewVersion);
          revalidate();
          repaint();
        } catch (Exception e) {
          Log.error(e);
        }
      }
    }).execute();
  }
}
