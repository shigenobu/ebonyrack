package com.walksocket.er.component;

import com.walksocket.er.Config;
import com.walksocket.er.Const;
import com.walksocket.er.Date;
import com.walksocket.er.Log;
import com.walksocket.er.Size.Screen;
import com.walksocket.er.Size.WindowMain;
import com.walksocket.er.Utils;
import com.walksocket.er.component.main.Root;
import com.walksocket.er.component.main.root.Workspace;
import com.walksocket.er.config.CfgProject;
import com.walksocket.er.config.CfgProjectPos;
import com.walksocket.er.sqlite.Bucket;
import com.walksocket.er.sqlite.Connection;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;
import javax.swing.WindowConstants;

/**
 * Main.
 */
public class Main extends JFrame {

  /**
   * cfg project.
   */
  private final CfgProject cfgProject;

  /**
   * stop redraw.
   */
  private final AtomicBoolean stopRedraw = new AtomicBoolean(false);

  /**
   * root.
   */
  private Root root;

  /**
   * Constructor.
   *
   * @param cfgProject cfgProject
   * @param readonly   readonly
   */
  public Main(CfgProject cfgProject, boolean readonly) {
    this.cfgProject = cfgProject;
    var main = this;

    // save
    cfgProject.lastOpened = Date.timestamp();
    Config.save();

    // connection
    var con = new Connection(cfgProject.dbPath);
    con.setReadonly(readonly);

    // init bucket
    Bucket.init(con);

    // pos
    var x = (Screen.getWidth() - WindowMain.WIDTH) / 2;
    var y = (Screen.getHeight() - WindowMain.HEIGHT) / 2;
    if (cfgProject.pos != null) {
      x = cfgProject.pos.x;
      y = cfgProject.pos.y;
    }

    // init
    var w = WindowMain.WIDTH;
    var h = WindowMain.HEIGHT;
    if (cfgProject.pos != null) {
      w = cfgProject.pos.width;
      h = cfgProject.pos.height;
    }
    setTitle(String.format("%s - main", Const.TITLE));
    setLocation(new Point(x, y));
    setSize(new Dimension(w, h));
    setMinimumSize(new Dimension(WindowMain.WIDTH, WindowMain.HEIGHT));
    setResizable(true);
    setVisible(true);
    setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    addWindowListener(new WindowAdapter() {
      @Override
      public void windowClosed(WindowEvent e) {
        Log.trace(e);
        var frame = (JFrame) e.getSource();
        cfgProject.pos = new CfgProjectPos();
        cfgProject.pos.x = frame.getX();
        cfgProject.pos.y = frame.getY();
        cfgProject.pos.width = frame.getWidth();
        cfgProject.pos.height = frame.getHeight();
        Config.save();

        // release bucket
        Bucket.release();

        // stop redraw
        stopRedraw.set(true);
      }
    });

    // menu
    var menuBar = new JMenuBar();
    setJMenuBar(menuBar);

    // menu - File
    var menuFile = new JMenu("File");
    var menuItemCloseProject = new JMenuItem("Close project");
    menuItemCloseProject.addActionListener(actionEvent -> {
      new Startup();
      dispose();
    });
    menuFile.add(menuItemCloseProject);
    menuFile.addSeparator();
    var menuItemExportDdl = new JMenuItem("Export ddl");
    menuItemExportDdl.addActionListener(actionEvent -> {
      try {
        var fileName = String.format("%s_%s_%s.sql", Const.TITLE, cfgProject.name,
            Date.timestamp());
        var f = new File(fileName);
        com.walksocket.er.File.writeString(new FileOutputStream(f), getDdl());
        JOptionPane.showMessageDialog(main,
            String.format("<html>Saved ddl:<br /><u>%s</u></html>",
                f.getAbsolutePath()));
      } catch (IOException e) {
        Log.error(e);
        JOptionPane.showMessageDialog(main, e.getMessage());
      }
    });
    menuFile.add(menuItemExportDdl);
    var menuItemExportImage = new JMenuItem("Export image");
    menuItemExportImage.addActionListener(actionEvent -> {
      Workspace workspace = root.getWorkspace();
      try {
        var rect = workspace.getBounds();
        var format = "png";
        var fileName = String.format("%s_%s_%s.%s", Const.TITLE, cfgProject.name, Date.timestamp(),
            format);
        var captureImage =
            new BufferedImage(rect.width, rect.height,
                BufferedImage.TYPE_INT_ARGB);
        workspace.paint(captureImage.getGraphics());
        var f = new File(fileName);
        ImageIO.write(captureImage, format, f);
        JOptionPane.showMessageDialog(main,
            String.format("<html>Saved image:<br /><u>%s</u></html>",
                f.getAbsolutePath()));
      } catch (IOException e) {
        Log.error(e);
        JOptionPane.showMessageDialog(main, e.getMessage());
      }
    });
    menuFile.add(menuItemExportImage);
    menuBar.add(menuFile);

    // menu - Edit
    var menuEdit = new JMenu("Edit");
    var menuItemEditDefault = new JMenuItem("Edit default");
    menuItemEditDefault.addActionListener(actionEvent -> {
      var editDefault = new EditDefault(main);
      editDefault.setModal(true);
      editDefault.setVisible(true);
    });
    menuEdit.add(menuItemEditDefault);
    var menuItemEditDictColumnTypes = new JMenuItem("Edit dict column types");
    menuItemEditDictColumnTypes.addActionListener(actionEvent -> {
      var editDictColumnTypes = new EditDictColumnTypes(main);
      editDictColumnTypes.setModal(true);
      editDictColumnTypes.setVisible(true);
    });
    menuEdit.add(menuItemEditDictColumnTypes);
    var menuItemEditDictColumns = new JMenuItem("Edit dict columns");
    menuItemEditDictColumns.addActionListener(actionEvent -> {
      var editDictColumns = new EditDictColumns(main);
      editDictColumns.setModal(true);
      editDictColumns.setVisible(true);
    });
    menuEdit.add(menuItemEditDictColumns);
    var menuItemEditDictGroups = new JMenuItem("Edit dict groups");
    menuItemEditDictGroups.addActionListener(actionEvent -> {
      var editDictGroups = new EditDictGroups(main);
      editDictGroups.setModal(true);
      editDictGroups.setVisible(true);
    });
    menuEdit.add(menuItemEditDictGroups);
    menuBar.add(menuEdit);

    // revalidate and repaint
    (new SwingWorker<Void, Void>() {

      @Override
      protected Void doInBackground() throws Exception {
        while (!stopRedraw.get()) {
          try {
            Thread.sleep(100);
            publish();
          } catch (InterruptedException e) {
          }
        }
        return null;
      }

      @Override
      protected void process(List<Void> chunks) {
        revalidate();
        repaint();
      }
    }).execute();

    // load
    load();
  }


  /**
   * load.
   */
  public void load() {
    var container = getContentPane();
    container.removeAll();

    this.root = new Root(this, cfgProject);

    container.add(root);
    container.revalidate();
    container.repaint();
  }

  private String getDdl() {
    var builder = new StringBuilder();

    // sequence
    builder.append("-- ----------------------------------------\n");
    builder.append("-- sequence\n");
    builder.append("-- ----------------------------------------\n");
    for (var ctxSequence : Bucket.getInstance().getBucketSequence().ctxSequenceList
        .stream()
        .sorted(Comparator.comparing(s -> s.dbSequence.sequenceName))
        .collect(Collectors.toList())) {
      var ddl = Bucket.getInstance().getSequenceDdl(ctxSequence);
      if (Utils.isNullOrEmpty(ddl)) {
        continue;
      }
      builder.append(ddl);
      builder.append("\n\n");
    }

    // table
    builder.append("-- ----------------------------------------\n");
    builder.append("-- table\n");
    builder.append("-- ----------------------------------------\n");
    for (var ctxTable : Bucket.getInstance().getBucketTable().ctxTableList
        .stream()
        .sorted(Comparator.comparing(t -> t.dbTable.tableName))
        .collect(Collectors.toList())) {
      var ddl = Bucket.getInstance().getTableDdl(ctxTable);
      if (Utils.isNullOrEmpty(ddl)) {
        continue;
      }
      builder.append(ddl);
      builder.append("\n");
    }

    // foreign key
    builder.append("-- ----------------------------------------\n");
    builder.append("-- foreign key\n");
    builder.append("-- ----------------------------------------\n");
    for (var ctxTable : Bucket.getInstance().getBucketTable().ctxTableList
        .stream()
        .sorted(Comparator.comparing(t -> t.dbTable.tableName))
        .collect(Collectors.toList())) {
      var ddl = Bucket.getInstance().getForeignKeyDdl(ctxTable);
      if (Utils.isNullOrEmpty(ddl)) {
        continue;
      }
      builder.append(ddl);
    }

    return builder.toString();
  }
}
