package com.walksocket.er.component.setup.project;

import com.walksocket.er.Config;
import com.walksocket.er.Date;
import com.walksocket.er.Pos;
import com.walksocket.er.Size.DialogSmall;
import com.walksocket.er.Utils;
import com.walksocket.er.component.SetupProject;
import com.walksocket.er.component.setup.project.root.Form;
import com.walksocket.er.config.CfgProject;
import com.walksocket.er.sqlite.Connection;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * Root.
 */
public class Root extends JPanel {

  /**
   * cfg project.
   */
  private final CfgProject cfgProject;

  /**
   * form.
   */
  private final Form form;

  /**
   * button ok.
   */
  private final JButton buttonOk = new JButton("Ok");

  /**
   * is new.
   */
  private boolean isNew;

  /**
   * Constructor.
   *
   * @param setupProject setupProject
   * @param cfgProject   cfgProject
   */
  public Root(SetupProject setupProject, CfgProject cfgProject) {
    this.cfgProject = cfgProject;

    // check is new
    if (Utils.isNullOrEmpty(cfgProject.name)) {
      isNew = true;
    }

    // layout
    setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

    // panel - form
    form = new Form(this, cfgProject);
    form.setPreferredSize(new Dimension(DialogSmall.WIDTH - 20, DialogSmall.HEIGHT / 10 * 9));
    form.setAlignmentX(Component.LEFT_ALIGNMENT);
    add(form);

    // panel - button
    var panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    panel.setPreferredSize(new Dimension(DialogSmall.WIDTH - 20, DialogSmall.HEIGHT / 10));
    panel.setAlignmentX(Component.LEFT_ALIGNMENT);
    add(panel);
    buttonOk.addActionListener(actionEvent -> {
      if (save()) {
        setupProject.dispose();
      }
    });
    panel.add(buttonOk);
  }

  /**
   * save.
   *
   * @return success or fault
   */
  private boolean save() {
    form.packCfgProject();
    if (Utils.isNullOrEmpty(cfgProject.name)) {
      JOptionPane.showMessageDialog(this, "Required 'Name'.");
      return false;
    }
    if (Utils.isNullOrEmpty(cfgProject.dbPath)) {
      JOptionPane.showMessageDialog(this, "Required 'Db path'.");
      return false;
    }
    if (cfgProject.miniSize < 0 || cfgProject.miniSize > Pos.MAX) {
      JOptionPane.showMessageDialog(this, String.format("Between 0 and %s 'Mini size'.", Pos.MAX));
      return false;
    }
    if (isNew
        && Config.getInstance().cfgProjectList.stream()
        .filter(cfg -> cfg.name.equals(cfgProject.name))
        .findAny()
        .isPresent()) {
      JOptionPane.showMessageDialog(this, "Already used 'Name'.");
      return false;
    }

    if (!cfgProject.dbPath.endsWith(Connection.DB_PATH_PREFIX)) {
      cfgProject.dbPath += Connection.DB_PATH_PREFIX;
    }

    if (cfgProject.lastOpened == 0) {
      Config.getInstance().cfgProjectList.add(cfgProject);
    }
    cfgProject.lastOpened = Date.timestamp();
    Config.save();

    return true;
  }
}
