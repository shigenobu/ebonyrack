package com.walksocket.er.component;

import com.walksocket.er.Const;
import com.walksocket.er.Size.DialogSmall;
import com.walksocket.er.component.export.ddl.Root;
import com.walksocket.er.config.CfgProject;
import com.walksocket.er.custom.ErDialog;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.WindowConstants;

/**
 * ExportDdl.
 */
public class ExportDdl extends ErDialog {

  /**
   * main.
   */
  private final Main main;

  /**
   * Constructor.
   *
   * @param main       main
   * @param cfgProject cfgProject
   */
  public ExportDdl(Main main, CfgProject cfgProject) {
    super();
    this.main = main;

    // pos
    var b = main.getGraphicsConfiguration().getBounds();
    var x = (b.getWidth() - DialogSmall.WIDTH) / 2 + b.getX();
    var y = (b.getHeight() - DialogSmall.HEIGHT) / 2 + b.getY();

    // init
    setTitle(String.format("%s - export ddl", Const.TITLE));
    setLocation(new Point((int) x, (int) y));
    setMinimumSize(new Dimension(DialogSmall.WIDTH, DialogSmall.HEIGHT));
    setResizable(false);
    setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    addWindowListener(new WindowAdapter() {
      @Override
      public void windowClosed(WindowEvent e) {
        dispose();
      }
    });

    // load
    var container = getContentPane();
    container.removeAll();
    container.add(new Root(this, cfgProject));
    container.revalidate();
    container.repaint();
  }

  /**
   * get main.
   *
   * @return main
   */
  public Main getMain() {
    return main;
  }
}
