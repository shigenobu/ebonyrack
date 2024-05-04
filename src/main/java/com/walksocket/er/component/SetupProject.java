package com.walksocket.er.component;

import com.walksocket.er.Const;
import com.walksocket.er.Log;
import com.walksocket.er.Size.DialogSmall;
import com.walksocket.er.component.setup.project.Root;
import com.walksocket.er.config.CfgProject;
import com.walksocket.er.custom.ErDialog;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.WindowConstants;

/**
 * SetupProject.
 */
public class SetupProject extends ErDialog {

  /**
   * Constructor.
   *
   * @param root       root
   * @param cfgProject cfgProject
   */
  public SetupProject(com.walksocket.er.component.startup.Root root, CfgProject cfgProject) {
    super();

    // pos
    var b = root.getStartup().getGraphicsConfiguration().getBounds();
    var x = (b.getWidth() - DialogSmall.WIDTH) / 2 + b.getX();
    var y = (b.getHeight() - DialogSmall.HEIGHT) / 2 + b.getY();

    // init
    setTitle(String.format("%s - project", Const.TITLE));
    setLocation(new Point((int) x, (int) y));
    setMinimumSize(new Dimension(DialogSmall.WIDTH, DialogSmall.HEIGHT));
    setResizable(false);
    setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    addWindowListener(new WindowAdapter() {
      @Override
      public void windowClosed(WindowEvent e) {
        Log.trace(e);

        // load startup
        root.getStartup().load();
      }
    });

    // load
    var container = getContentPane();
    container.removeAll();
    container.add(new Root(this, cfgProject));
    container.revalidate();
    container.repaint();
  }
}
