package com.walksocket.er.component.main;

import com.walksocket.er.Size.WindowMain;
import com.walksocket.er.component.Main;
import com.walksocket.er.component.main.root.Outline;
import com.walksocket.er.component.main.root.Side;
import com.walksocket.er.component.main.root.Workspace;
import com.walksocket.er.config.CfgProject;
import com.walksocket.er.sqlite.Bucket;
import java.awt.BorderLayout;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;

/**
 * Root.
 */
public class Root extends JPanel {

  /**
   * main.
   */
  private final Main main;

  /**
   * size.
   */
  private final Side side;

  /**
   * workspace.
   */
  private final Workspace workspace;

  /**
   * Constructor.
   *
   * @param main       main
   * @param cfgProject cfgProject
   */
  public Root(Main main, CfgProject cfgProject) {
    this.main = main;

    // readonly
    if (Bucket.getInstance().isReadOnly()) {
      JOptionPane.showMessageDialog(this, "Open readonly mode.");
    }

    // layout
    setLayout(new BorderLayout());

    // split pane
    var sp = new JSplitPane();
    sp.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
    sp.setDividerLocation(WindowMain.WIDTH / 5);
    sp.setDividerSize(5);
    add(sp, BorderLayout.CENTER);

    // side, outline
    var sideSp = new JSplitPane();
    sideSp.setOrientation(JSplitPane.VERTICAL_SPLIT);
    sideSp.setDividerLocation(WindowMain.HEIGHT / 4 * 3);
    sideSp.setDividerSize(5);

    // side
    side = new Side(this, cfgProject);
    var scrollPaneSide = new JScrollPane(side, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
        JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
    sideSp.setLeftComponent(scrollPaneSide);

    // outline
    var outline = new Outline(this);
    sideSp.setRightComponent(outline);
    sp.setLeftComponent(sideSp);

    // workspace
    workspace = new Workspace(this, cfgProject);
    var scrollPaneWorkspace = new JScrollPane(workspace, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
        JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
    scrollPaneWorkspace.getHorizontalScrollBar().setUnitIncrement(30);
    scrollPaneWorkspace.getVerticalScrollBar().setUnitIncrement(30);
    sp.setRightComponent(scrollPaneWorkspace);

    // sync side between workspace
    side.setWorkspace(workspace);
    workspace.setSide(side);

    // sync outline between workspace
    outline.setWorkspace(workspace);

    // read data
    workspace.readData();
  }

  /**
   * get main.
   *
   * @return main.
   */
  public Main getMain() {
    return main;
  }

  /**
   * get workspace.
   *
   * @return workspace
   */
  public Workspace getWorkspace() {
    return workspace;
  }
}
