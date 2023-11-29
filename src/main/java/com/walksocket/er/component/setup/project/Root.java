package com.walksocket.er.component.setup.project;

import com.walksocket.er.Config;
import com.walksocket.er.Date;
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
   * button save.
   */
  private final JButton buttonSave = new JButton("Save");

  /**
   * button save and close.
   */
  private final JButton buttonSaveAndClose = new JButton("Save and close");

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
    buttonSave.addActionListener(actionEvent -> {
      save();
    });
    panel.add(buttonSave);
    buttonSaveAndClose.addActionListener(actionEvent -> {
      if (save()) {
        setupProject.dispose();
      }
    });
    panel.add(buttonSaveAndClose);
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
