package com.walksocket.er.component.startup;

import com.walksocket.er.Size.WindowStartup;
import com.walksocket.er.component.Startup;
import com.walksocket.er.component.startup.root.Copyright;
import com.walksocket.er.component.startup.root.Logo;
import com.walksocket.er.component.startup.root.Project;
import java.awt.Component;
import java.awt.Dimension;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

/**
 * Root.
 */
public class Root extends JPanel {

  /**
   * startup.
   */
  private final Startup startup;

  /**
   * logo.
   */
  private final Logo logo;

  /**
   * project.
   */
  private final Project project;

  /**
   * copyright.
   */
  private final Copyright copyright;

  /**
   * Constructor.
   *
   * @param startup startup
   */
  public Root(Startup startup) {
    this.startup = startup;

    // layout
    setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

    // logo
    logo = new Logo(this);
    logo.setPreferredSize(new Dimension(WindowStartup.WIDTH - 20, WindowStartup.HEIGHT / 10 * 2));
    logo.setAlignmentX(Component.LEFT_ALIGNMENT);
    add(logo);

    // project
    project = new Project(this);
    project.setPreferredSize(
        new Dimension(WindowStartup.WIDTH - 20, WindowStartup.HEIGHT / 10 * 7));
    project.setAlignmentX(Component.LEFT_ALIGNMENT);
    add(project);

    // copyright
    copyright = new Copyright(this);
    copyright.setPreferredSize(
        new Dimension(WindowStartup.WIDTH - 20, WindowStartup.HEIGHT / 10));
    copyright.setAlignmentX(Component.LEFT_ALIGNMENT);
    add(copyright);
  }

  /**
   * get startup.
   *
   * @return startup
   */
  public Startup getStartup() {
    return startup;
  }
}
