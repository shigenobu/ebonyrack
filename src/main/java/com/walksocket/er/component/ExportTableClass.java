package com.walksocket.er.component;

import com.walksocket.er.Const;
import com.walksocket.er.Size.DialogExport;
import com.walksocket.er.component.export.tableclass.Root;
import com.walksocket.er.config.CfgProject;
import com.walksocket.er.custom.ErDialog;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.WindowConstants;

/**
 * ExportTableClass.
 */
public class ExportTableClass extends ErDialog {

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
  public ExportTableClass(Main main, CfgProject cfgProject) {
    super();
    this.main = main;

    // pos
    var b = main.getGraphicsConfiguration().getBounds();
    var x = (b.getWidth() - DialogExport.WIDTH) / 2 + b.getX();
    var y = (b.getHeight() - DialogExport.HEIGHT) / 2 + b.getY();

    // init
    setTitle(String.format("%s - export table class", Const.TITLE));
    setLocation(new Point((int) x, (int) y));
    setMinimumSize(new Dimension(DialogExport.WIDTH, DialogExport.HEIGHT));
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
