package com.walksocket.er.component.startup.root;

import com.walksocket.er.Config;
import com.walksocket.er.Size.WindowStartup;
import com.walksocket.er.component.Main;
import com.walksocket.er.component.SetupProject;
import com.walksocket.er.component.startup.Root;
import com.walksocket.er.config.CfgProject;
import com.walksocket.er.custom.ErUnderlineBorder;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.io.File;
import java.util.Comparator;
import java.util.stream.Collectors;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

/**
 * Project.
 */
public class Project extends JPanel {

  /**
   * root.
   */
  private final Root root;

  /**
   * button new project.
   */
  private final JButton buttonNewProject = new JButton("New project");

  /**
   * Constructor.
   *
   * @param root root
   */
  public Project(Root root) {
    this.root = root;

    // panel - new button
    var panel1 = new JPanel(new FlowLayout(FlowLayout.CENTER));
    panel1.setPreferredSize(new Dimension(WindowStartup.WIDTH - 20, WindowStartup.HEIGHT / 15));
    add(panel1);
    buttonNewProject.addActionListener(actionEvent -> {
      // new
      var setupProject = new SetupProject(root, new CfgProject());
      setupProject.setModal(true);
      setupProject.setVisible(true);
    });
    panel1.add(buttonNewProject);

    // panel - list
    var panel2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
    panel2.add(getDataPanel());
    var sp = new JScrollPane(panel2);
    sp.setPreferredSize(new Dimension(WindowStartup.WIDTH - 20, WindowStartup.HEIGHT / 15 * 8));
    sp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
    add(sp);
  }

  /**
   * get root.
   *
   * @return root
   */
  public Root getRoot() {
    return root;
  }

  /**
   * get data panel.
   *
   * @return data panel
   */
  private JPanel getDataPanel() {
    var panel = new JPanel();
    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

    var config = Config.getInstance();
    var cfgProjectList = config.cfgProjectList.stream()
        .sorted(Comparator.comparing(c -> c.lastOpened, Comparator.reverseOrder()))
        .collect(Collectors.toList());
    for (var cfgProject : cfgProjectList) {
      var p = new JPanel(new FlowLayout(FlowLayout.CENTER));
      p.setBorder(new ErUnderlineBorder());
      p.setPreferredSize(new Dimension(WindowStartup.WIDTH - 60, WindowStartup.HEIGHT / 15));

      // open
      var buttonOpen = new JButton("Open");
      buttonOpen.addActionListener(actionEvent -> {
        new Main(cfgProject, false);
        getRoot().getStartup().dispose();
      });
      p.add(buttonOpen);

      // open readonly
      var buttonOpenReadonly = new JButton("Open RO");
      if (!(new File(cfgProject.dbPath)).exists()) {
        buttonOpenReadonly.setEnabled(false);
      }
      buttonOpenReadonly.addActionListener(actionEvent -> {
        new Main(cfgProject, true);
        getRoot().getStartup().dispose();
      });
      p.add(buttonOpenReadonly);

      // label
      var labelProjectName = new JLabel(cfgProject.name);
      labelProjectName.setPreferredSize(
          new Dimension(WindowStartup.WIDTH / 3 + 100, WindowStartup.HEIGHT / 20));
      p.add(labelProjectName);

      // edit
      var buttonEdit = new JButton("Edit");
      buttonEdit.addActionListener(actionEvent -> {
        var setupProject = new SetupProject(root, cfgProject);
        setupProject.setModal(true);
        setupProject.setVisible(true);
      });
      p.add(buttonEdit);

      // remove
      var buttonRemove = new JButton("Remove");
      buttonRemove.addActionListener(actionEvent -> {
        if (JOptionPane.showConfirmDialog(this,
            String.format("Remove project '%s' ?", cfgProject.name)) != 0) {
          return;
        }
        config.cfgProjectList.remove(cfgProject);
        Config.save();
        root.getStartup().load();
      });
      p.add(buttonRemove);

      panel.add(p);
    }

    return panel;
  }
}
