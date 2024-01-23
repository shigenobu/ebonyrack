package com.walksocket.er.component.export.tableclass;

import com.walksocket.er.Config;
import com.walksocket.er.Log;
import com.walksocket.er.Size.DialogSmall;
import com.walksocket.er.Utils;
import com.walksocket.er.component.ExportTableClass;
import com.walksocket.er.component.export.ddl.root.Form;
import com.walksocket.er.config.CfgProject;
import com.walksocket.er.custom.ErLinkLabel;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.io.File;
import java.net.URI;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * Root.
 */
public class Root extends JPanel {

  /**
   * export table class.
   */
  private final ExportTableClass exportTableClass;

  /**
   * form.
   */
  private final Form form;

  /**
   * button ok.
   */
  private final JButton buttonOk = new JButton("Ok");

  /**
   * Constructor.
   *
   * @param exportTableClass exportTableClass
   * @param cfgProject       cfgProject
   */
  public Root(ExportTableClass exportTableClass, CfgProject cfgProject) {
    this.exportTableClass = exportTableClass;

    // layout
    setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

    // panel - form
    form = new Form();
    form.setPreferredSize(new Dimension(DialogSmall.WIDTH - 20, DialogSmall.HEIGHT / 10 * 9));
    form.setAlignmentX(Component.LEFT_ALIGNMENT);
    add(form);

    // panel - button
    var panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    panel.setPreferredSize(new Dimension(DialogSmall.WIDTH - 20, DialogSmall.HEIGHT / 10));
    panel.setAlignmentX(Component.LEFT_ALIGNMENT);
    add(panel);
    buttonOk.addActionListener(actionEvent -> {
      try {
        // chooser
        var dir = new File(System.getProperty("user.home"));
        var lastTableClassSaveDir = cfgProject.lastTableClassSaveDir;
        if (!Utils.isNullOrEmpty(lastTableClassSaveDir)) {
          dir = new File(lastTableClassSaveDir);
        }
        var chooser = new JFileChooser(dir);
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        var result = chooser.showSaveDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
          var f = chooser.getSelectedFile();
          saveTableClass(f);
          cfgProject.lastTableClassSaveDir = f.getAbsolutePath();
          Config.save();

          JOptionPane.showMessageDialog(
              this,
              new ErLinkLabel(
                  String.format("<html>At: <a>%s</a></html>", f.getAbsolutePath()),
                  new URI(String.format("file://%s", f.getAbsolutePath()))
              ),
              "Saved table class",
              JOptionPane.INFORMATION_MESSAGE);
        }
      } catch (Exception e) {
        Log.error(e);
        JOptionPane.showMessageDialog(this, e.getMessage());
      }
    });
    panel.add(buttonOk);
  }

  private void saveTableClass(File dir) {
  }
}
