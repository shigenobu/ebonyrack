package com.walksocket.er.component.main;

import com.walksocket.er.Config;
import com.walksocket.er.Size.WindowMain;
import com.walksocket.er.component.Main;
import com.walksocket.er.component.main.root.Outline;
import com.walksocket.er.component.main.root.Side;
import com.walksocket.er.component.main.root.Workspace;
import com.walksocket.er.config.CfgProject;
import java.awt.BorderLayout;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
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
   * outline.
   */
  private final Outline outline;

  /**
   * workspace.
   */
  private final Workspace workspace;

  /**
   * resized.
   */
  private boolean resized;

  /**
   * Constructor.
   *
   * @param main       main
   * @param cfgProject cfgProject
   */
  public Root(Main main, CfgProject cfgProject) {
    this.main = main;

    // invisible
    var root = this;
    root.setVisible(false);

    // layout
    setLayout(new BorderLayout());

    // split pane
    var mainDivider = cfgProject.pos.mainDivider;
    if (mainDivider == 0) {
      mainDivider = WindowMain.WIDTH / 5;
    }
    var sp = new JSplitPane();
    sp.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
    sp.setDividerLocation(mainDivider);
    sp.setDividerSize(5);
    sp.addPropertyChangeListener(JSplitPane.DIVIDER_LOCATION_PROPERTY, propertyChangeEvent -> {
      cfgProject.pos.mainDivider = (int) propertyChangeEvent.getNewValue();
      Config.save();
    });
    add(sp, BorderLayout.CENTER);

    // side, outline
    var sideDivider = cfgProject.pos.sideDivider;
    if (sideDivider == 0) {
      sideDivider = WindowMain.HEIGHT / 4 * 3;
    }
    var sideSp = new JSplitPane();
    sideSp.setOrientation(JSplitPane.VERTICAL_SPLIT);
    sideSp.setDividerLocation(sideDivider);
    sideSp.addPropertyChangeListener(JSplitPane.DIVIDER_LOCATION_PROPERTY, propertyChangeEvent -> {
      cfgProject.pos.sideDivider = (int) propertyChangeEvent.getNewValue();
      Config.save();
    });
    sideSp.setDividerSize(5);

    // side
    side = new Side(this, cfgProject);
    var scrollPaneSide = new JScrollPane(side, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
        JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
    scrollPaneSide.getHorizontalScrollBar().setUnitIncrement(15);
    scrollPaneSide.getVerticalScrollBar().setUnitIncrement(15);
    sideSp.setLeftComponent(scrollPaneSide);

    // outline
    outline = new Outline(this);
    sideSp.setRightComponent(outline);
    sp.setLeftComponent(sideSp);

    // workspace
    workspace = new Workspace(this, cfgProject);
    var scrollPaneWorkspace = new JScrollPane(workspace, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
        JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
    scrollPaneWorkspace.getHorizontalScrollBar().setUnitIncrement(30);
    scrollPaneWorkspace.getHorizontalScrollBar().addAdjustmentListener(adjustmentEvent -> {
      var sb = (JScrollBar) adjustmentEvent.getSource();
      if (!sb.getValueIsAdjusting() && resized) {
        cfgProject.pos.workspaceHorizontalScroll = sb.getValue();
        Config.save();
      }
    });
    scrollPaneWorkspace.getVerticalScrollBar().setUnitIncrement(30);
    scrollPaneWorkspace.getVerticalScrollBar().addAdjustmentListener(adjustmentEvent -> {
      var sb = (JScrollBar) adjustmentEvent.getSource();
      if (!sb.getValueIsAdjusting() && resized) {
        cfgProject.pos.workspaceVerticalScroll = sb.getValue();
        Config.save();
      }
    });
    workspace.addComponentListener(new ComponentAdapter() {
      @Override
      public void componentResized(ComponentEvent e) {
        if (!resized) {
          scrollPaneWorkspace.getHorizontalScrollBar()
              .setValue(cfgProject.pos.workspaceHorizontalScroll);
          scrollPaneWorkspace.getVerticalScrollBar()
              .setValue(cfgProject.pos.workspaceVerticalScroll);
          resized = true;

          // visible
          root.setVisible(true);
        }
      }
    });
    sp.setRightComponent(scrollPaneWorkspace);

    // sync side between workspace
    side.setWorkspace(workspace);
    workspace.setSide(side);

    // sync outline between workspace
    outline.setWorkspace(workspace);
    workspace.setOutline(outline);

    // sync outline between side
    outline.setSide(side);
    side.setOutline(outline);

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
