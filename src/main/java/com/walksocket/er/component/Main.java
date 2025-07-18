package com.walksocket.er.component;

import com.walksocket.er.App;
import com.walksocket.er.Config;
import com.walksocket.er.Const;
import com.walksocket.er.Copiable;
import com.walksocket.er.Date;
import com.walksocket.er.FileUtils;
import com.walksocket.er.Log;
import com.walksocket.er.Size.Screen;
import com.walksocket.er.Size.WindowMain;
import com.walksocket.er.Utils;
import com.walksocket.er.component.main.Root;
import com.walksocket.er.component.main.root.Workspace;
import com.walksocket.er.component.main.root.workspace.Note;
import com.walksocket.er.component.main.root.workspace.Sequence;
import com.walksocket.er.component.main.root.workspace.Table;
import com.walksocket.er.config.CfgProject;
import com.walksocket.er.custom.ErDialogWaiting;
import com.walksocket.er.custom.ErLinkLabel;
import com.walksocket.er.sqlite.Bucket;
import com.walksocket.er.sqlite.Connection;
import com.walksocket.er.template.ErTemplate;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;
import javax.swing.WindowConstants;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * Main.
 */
public class Main extends JFrame {

  /**
   * title.
   */
  private String title;

  /**
   * copied info map.
   */
  private final HashMap<Class, CopiedInfo> copiedInfoMap = new HashMap<>();

  /**
   * draw lock.
   */
  private final Object drawLock = new Object();

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
    var readonlyStr = "";
    if (readonly) {
      JOptionPane.showMessageDialog(this, "Open readonly mode.");
      readonlyStr = " (readonly)";
    }

    // init bucket
    try {
      Bucket.init(con);
    } catch (Exception e) {
      Log.error(e);
      JOptionPane.showMessageDialog(main, e.getMessage());
      dispose();
      return;
    }

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
    title = String.format("%s - main%s", Const.TITLE, readonlyStr);
    setTitle(title);
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
    var menuItemExportImage = new JMenuItem("Export image");
    menuItemExportImage.addActionListener(actionEvent -> {
      // reset
      resetWorkspace();

      Workspace workspace = root.getWorkspace();

      // chooser
      var format = "png";
      var dotFormat = "." + format;
      var dir = System.getProperty("user.home");
      var file = String.format("%s%s", cfgProject.name, dotFormat);
      var lastImageSavePath = cfgProject.lastImageSavePath;
      if (!Utils.isNullOrEmpty(lastImageSavePath)) {
        dir = new File(lastImageSavePath).getParent();
        file = new File(lastImageSavePath).getName();
      }
      var chooser = new JFileChooser(dir);
      chooser.setAcceptAllFileFilterUsed(false);
      chooser.setFileFilter(new FileNameExtensionFilter("*" + dotFormat,
          format));
      chooser.setSelectedFile(new File(file));
      var result = chooser.showSaveDialog(main);
      if (result == JFileChooser.APPROVE_OPTION) {
        var fileName = chooser.getSelectedFile().getAbsolutePath();
        if (!fileName.endsWith(dotFormat)) {
          fileName += dotFormat;
        }

        // exporting
        var dialogExporting = new ErDialogWaiting(main, "Exporting");

        String finalFileName = fileName;
        (new SwingWorker<File, Void>() {
          @Override
          protected File doInBackground() throws Exception {
            // capture
            var captureImage = createWorkspaceImage(workspace);

            var f = new File(finalFileName);
            ImageIO.write(captureImage, format, f);

            cfgProject.lastImageSavePath = f.getAbsolutePath();
            Config.save();

            return f;
          }

          @Override
          protected void done() {
            try {
              dialogExporting.dispose();

              var f = get();
              JOptionPane.showMessageDialog(
                  main,
                  new ErLinkLabel(
                      String.format("<html>At: <a>%s</a></html>", f.getAbsolutePath()),
                      new URI(String.format("file://%s", f.getAbsolutePath()))
                  ),
                  "Saved image",
                  JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception e) {
              Log.error(e);
              JOptionPane.showMessageDialog(main, e.getMessage());
            }
          }
        }).execute();
        dialogExporting.setModal(true);
        dialogExporting.setVisible(true);
      }
    });
    menuFile.add(menuItemExportImage);
    var menuItemExportHtml = new JMenuItem("Export html");
    menuItemExportHtml.addActionListener(actionEvent -> {
      // reset
      resetWorkspace();

      Workspace workspace = root.getWorkspace();

      // chooser
      var format = "html";
      var dotFormat = "." + format;
      var dir = System.getProperty("user.home");
      var file = String.format("%s%s", cfgProject.name, dotFormat);
      var lastHtmlSavePath = cfgProject.lastHtmlSavePath;
      if (!Utils.isNullOrEmpty(lastHtmlSavePath)) {
        dir = new File(lastHtmlSavePath).getParent();
        file = new File(lastHtmlSavePath).getName();
      }
      var chooser = new JFileChooser(dir);
      chooser.setAcceptAllFileFilterUsed(false);
      chooser.setFileFilter(new FileNameExtensionFilter("*" + dotFormat,
          format));
      chooser.setSelectedFile(new File(file));
      var result = chooser.showSaveDialog(main);
      if (result == JFileChooser.APPROVE_OPTION) {
        var fileName = chooser.getSelectedFile().getAbsolutePath();
        if (!fileName.endsWith(dotFormat)) {
          fileName += dotFormat;
        }

        // exporting
        var dialogExporting = new ErDialogWaiting(main, "Exporting");

        String finalFileName = fileName;
        (new SwingWorker<File, Void>() {
          @Override
          protected File doInBackground() throws Exception {
            // create
            var html = createHtml(workspace);

            var f = new File(finalFileName);
            FileUtils.writeString(new FileOutputStream(f), html);

            cfgProject.lastHtmlSavePath = f.getAbsolutePath();
            Config.save();

            return f;
          }

          @Override
          protected void done() {
            try {
              dialogExporting.dispose();

              var f = get();
              JOptionPane.showMessageDialog(
                  main,
                  new ErLinkLabel(
                      String.format("<html>At: <a>%s</a></html>", f.getAbsolutePath()),
                      new URI(String.format("file://%s", f.getAbsolutePath()))
                  ),
                  "Saved html",
                  JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception e) {
              Log.error(e);
              JOptionPane.showMessageDialog(main, e.getMessage());
            }
          }
        }).execute();
        dialogExporting.setModal(true);
        dialogExporting.setVisible(true);
      }
    });
    menuFile.add(menuItemExportHtml);
    var menuItemExportDdl = new JMenuItem("Export ddl");
    menuItemExportDdl.addActionListener(actionEvent -> {
      // reset
      resetWorkspace();

      var exportDdl = new ExportDdl(main, cfgProject);
      exportDdl.setModal(true);
      exportDdl.setVisible(true);
    });
    menuFile.add(menuItemExportDdl);
    var menuItemExportTableClass = new JMenuItem("Export table class");
    menuItemExportTableClass.addActionListener(actionEvent -> {
      // reset
      resetWorkspace();

      var exportTableClass = new ExportTableClass(main, cfgProject);
      exportTableClass.setModal(true);
      exportTableClass.setVisible(true);
    });
    menuFile.add(menuItemExportTableClass);
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
    menuEdit.addSeparator();
    var menuItemEditTables = new JMenuItem("Edit tables");
    menuItemEditTables.addActionListener(actionEvent -> {
      var editTables = new EditTables(main);
      editTables.setModal(true);
      editTables.setVisible(true);
    });
    menuEdit.add(menuItemEditTables);
    var menuItemEditSequences = new JMenuItem("Edit sequences");
    menuItemEditSequences.addActionListener(actionEvent -> {
      var editSequences = new EditSequences(main);
      editSequences.setModal(true);
      editSequences.setVisible(true);
    });
    menuEdit.add(menuItemEditSequences);
    menuEdit.addSeparator();
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
    var menuItemEditDictPartitions = new JMenuItem("Edit dict partitions");
    menuItemEditDictPartitions.addActionListener(actionEvent -> {
      var editDictPartitions = new EditDictPartitions(main);
      editDictPartitions.setModal(true);
      editDictPartitions.setVisible(true);
    });
    menuEdit.add(menuItemEditDictPartitions);
    menuEdit.addSeparator();
    var menuItemSearch = new JMenuItem("Search");
    menuItemSearch.addActionListener(actionEvent -> {
      root.getOutline().getSearchSpace().showSpace();
    });
    menuEdit.add(menuItemSearch);
    menuBar.add(menuEdit);

    // revalidate and repaint
    (new SwingWorker<Void, Void>() {

      @Override
      protected Void doInBackground() throws Exception {
        while (true) {
          try {
            Thread.sleep(200);
            publish();
          } catch (InterruptedException e) {
          }
          if (stopRedraw.get()) {
            break;
          }
        }
        return null;
      }

      @Override
      protected void process(List<Void> chunks) {
        synchronized (drawLock) {
          revalidate();
          repaint();
        }
      }
    }).execute();

    // load
    load();
  }

  /**
   * load.
   */
  public void load() {
    var main = this;

    // loading
    var dialogLoading = new ErDialogWaiting(this, "Loading");
    (new SwingWorker<Void, Void>() {

      @Override
      protected Void doInBackground() throws Exception {
        var container = getContentPane();
        container.removeAll();

        main.root = new Root(main, cfgProject);

        container.add(root);
        container.revalidate();
        container.repaint();
        return null;
      }

      @Override
      protected void done() {
        dialogLoading.dispose();
        root.getWorkspace().requestFocusInWindow();
      }
    }).execute();
    dialogLoading.setModal(true);
    dialogLoading.setVisible(true);
  }

  /**
   * create workspace image.
   *
   * @param workspace workspace
   * @return image
   */
  private BufferedImage createWorkspaceImage(Workspace workspace) {
    var rect = workspace.getBounds();
    var captureImage =
        new BufferedImage(rect.width, rect.height,
            BufferedImage.TYPE_INT_ARGB);
    synchronized (drawLock) {
      var g2 = captureImage.getGraphics();
      workspace.paintAll(g2);
      g2.dispose();
    }

    return captureImage;
  }

  /**
   * create html.
   *
   * @param workspace workspace
   * @return html
   */
  private String createHtml(Workspace workspace) throws IOException {
    // css
    var cssList = new ArrayList<String>();
    var cssNames = Arrays.asList("base", "main");
    for (var cssName : cssNames) {
      try (var stream = App.class.getClassLoader()
          .getResourceAsStream(String.format("html/css/%s.css", cssName))) {
        var data = FileUtils.readString(stream);
        cssList.add(data);
      } catch (IOException e) {
        Log.error(e);
      }
    }

    // js
    var jsList = new ArrayList<String>();
    var jsNames = Arrays.asList("drag", "menu", "move", "dialog");
    for (var jsName : jsNames) {
      try (var stream = App.class.getClassLoader()
          .getResourceAsStream(String.format("html/js/%s.js", jsName))) {
        var data = FileUtils.readString(stream);
        jsList.add(data);
      } catch (IOException e) {
        Log.error(e);
      }
    }

    // image
    var captureImage = createWorkspaceImage(workspace);
    var os = new ByteArrayOutputStream();
    ImageIO.write(captureImage, "png", os);
    var imageData = Base64.getEncoder().encodeToString(os.toByteArray());
    var imageWidth = captureImage.getWidth();
    var imageHeight = captureImage.getHeight();

    // tables
    var dialogTableList = new ArrayList<String>();
    var tables = workspace.getOrderedPositionedTables();
    for (var table : tables) {
      var template = getTemplate("html/parts/table.vm");

      // table
      // column
      // primary key
      // unique key
      // key
      // foreign key
      // referenced tables
      // ddl
      // notes
      Bucket.getInstance().assignTableVars(table.getCtxTable(), template);

      // render
      var data = template.render();
      dialogTableList.add(data);
    }

    // sequences
    var dialogSequenceList = new ArrayList<String>();
    var sequences = workspace.getOrderedPositionedSequences();
    for (var sequence : sequences) {
      var template = getTemplate("html/parts/sequence.vm");

      // sequence
      // ddl
      // notes
      Bucket.getInstance().assignSequenceVars(sequence.getCtxSequence(), template);

      // render
      var data = template.render();
      dialogSequenceList.add(data);
    }

    // notes
    var connectorsNoteToTableList = workspace.getOrderedPositionedConnectorsNoteToTableList();
    var connectorsNoteToSequenceList = workspace.getOrderedPositionedConnectorsNoteToSequenceList();

    var dialogNoteList = new ArrayList<String>();
    var notes = workspace.getOrderedPositionedNotes();
    for (var note : notes) {
      var template = getTemplate("html/parts/note.vm");

      // note
      Bucket.getInstance().assignNoteVars(note.getCtxNote(), template);

      // related
      var relatedTables = connectorsNoteToTableList.stream()
          .filter(c -> c.getEndpoint(Note.class) == note)
          .map(c -> c.getEndpoint(Table.class))
          .sorted(Comparator.comparing(Table::getNameForSort))
          .collect(Collectors.toList());
      template.assign("relatedTables", relatedTables);

      var relatedSequences = connectorsNoteToSequenceList.stream()
          .filter(c -> c.getEndpoint(Note.class) == note)
          .map(c -> c.getEndpoint(Sequence.class))
          .sorted(Comparator.comparing(Sequence::getNameForSort))
          .collect(Collectors.toList());
      template.assign("relatedSequences", relatedSequences);

      // render
      var data = template.render();
      dialogNoteList.add(data);
    }

    // font
    var fontData = "";
    try (var stream = App.class.getClassLoader()
        .getResourceAsStream("font/Mplus1-Regular.otf")) {
      var fontBytes = stream.readAllBytes();
      fontData = Base64.getEncoder().encodeToString(fontBytes);
    }

    // main
    var mainTemplate = getTemplate("html/main.vm");
    mainTemplate.assign("title", Const.TITLE);
    mainTemplate.assign("name", cfgProject.name);
    mainTemplate.assign("fontData", fontData);
    mainTemplate.assign("cssList", cssList);
    mainTemplate.assign("jsList", jsList);
    mainTemplate.assign("imageData", imageData);
    mainTemplate.assign("imageWidth", imageWidth);
    mainTemplate.assign("imageHeight", imageHeight);
    mainTemplate.assign("tables", tables);
    mainTemplate.assign("sequences", sequences);
    mainTemplate.assign("notes", notes);
    mainTemplate.assign("dialogTableList", dialogTableList);
    mainTemplate.assign("dialogSequenceList", dialogSequenceList);
    mainTemplate.assign("dialogNoteList", dialogNoteList);

    return mainTemplate.render();
  }

  /**
   * get template.
   *
   * @param path path
   * @return template
   */
  private ErTemplate getTemplate(String path) {
    if (Utils.isNullOrEmpty(App.getVersion())) {
      var dir = new File(".").getAbsoluteFile().getParent();
      var basePath = new File(dir, "src/main/resources").getAbsolutePath();
      return new ErTemplate(basePath, path);
    }
    return new ErTemplate(path);
  }

  /**
   * change title.
   *
   * @param d dimension
   */
  public void changeTitle(Dimension d) {
    setTitle(String.format("%s (%sx%s)", title, d.width, d.height));
  }

  /**
   * set copied.
   *
   * @param copy copy
   * @param cls  class
   * @param <T>  type
   */
  public <T extends Copiable> void setCopied(Copiable copy, Class<T> cls) {
    var info = new CopiedInfo<T>();
    info.copiedTimestampMills = Date.timestampMillis();
    info.data = copy;
    copiedInfoMap.put(cls, info);
  }

  /**
   * get copied.
   *
   * @param cls class
   * @param <T> type
   * @return copy
   */
  public <T extends Copiable> T getCopied(Class<T> cls) {
    if (!copiedInfoMap.containsKey(cls)) {
      return null;
    }
    return (T) copiedInfoMap.get(cls).data.copy();
  }

  /**
   * exist copied.
   *
   * @param cls cls
   * @param <T> type
   * @return if exists, true
   */
  public <T extends Copiable> boolean existCopied(Class<T> cls) {
    return copiedInfoMap.containsKey(cls);
  }

  /**
   * get copied latest.
   *
   * @param <T> type
   * @return copy
   */
  public <T extends Copiable> T getCopiedLatest() {
    var opt = copiedInfoMap.values().stream()
        .max(Comparator.comparingLong(CopiedInfo::getCopiedTimestampMills));
    if (!opt.isPresent()) {
      return null;
    }
    return (T) opt.get().data.copy();
  }

  /**
   * reset workspace.
   */
  private void resetWorkspace() {
    root.getWorkspace().getSelectingRange().clearAllMovers();
    root.getWorkspace().reorder();
    root.getWorkspace().requestFocusInWindow();
    root.getWorkspace().hideSearchSpace();
  }

  /**
   * CopiedInfo.
   *
   * @param <T> type
   */
  public class CopiedInfo<T extends Copiable> {

    /**
     * copied timestamp mills.
     */
    public long copiedTimestampMills;

    /**
     * copied data.
     */
    public Copiable<T> data;

    /**
     * get copied timestamp mills.
     *
     * @return copied timestamp mills
     */
    public long getCopiedTimestampMills() {
      return copiedTimestampMills;
    }
  }
}
