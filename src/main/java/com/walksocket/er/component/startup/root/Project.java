package com.walksocket.er.component.startup.root;

import com.walksocket.er.Config;
import com.walksocket.er.Env;
import com.walksocket.er.Log;
import com.walksocket.er.Size.WindowStartup;
import com.walksocket.er.Utils;
import com.walksocket.er.component.Main;
import com.walksocket.er.component.SetupProject;
import com.walksocket.er.component.startup.Root;
import com.walksocket.er.config.CfgProject;
import com.walksocket.er.custom.ErDialogWaiting;
import com.walksocket.er.custom.ErLinkLabel;
import com.walksocket.er.custom.ErUnderlineBorder;
import com.walksocket.er.sqlite.Dump;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.Comparator;
import java.util.stream.Collectors;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingWorker;
import javax.swing.filechooser.FileNameExtensionFilter;

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

    // lock
    try {
      Config.lock();
    } catch (IOException e) {
      if (JOptionPane.showConfirmDialog(this, "Now locking, exit application ?")
          == JOptionPane.OK_OPTION) {
        System.exit(1);
      }
      return;
    }

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
      if (Env.isReadonly()) {
        buttonOpen.setEnabled(false);
      }

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
          new Dimension(WindowStartup.WIDTH / 3 + 20, WindowStartup.HEIGHT / 20));
      p.add(labelProjectName);

      // write
      var buttonWrite = new JButton("Write");
      if (!(new File(cfgProject.dbPath)).exists()) {
        buttonWrite.setEnabled(false);
      }
      buttonWrite.setToolTipText("Write all data out to json file.");
      buttonWrite.addActionListener(actionEvent -> {
        // chooser
        var format = Dump.EXTENSION;
        var dotFormat = "." + format;
        var dir = System.getProperty("user.home");
        var file = String.format("%s%s", cfgProject.name, dotFormat);
        var lastWriteOutPath = cfgProject.lastWriteOutPath;
        if (!Utils.isNullOrEmpty(lastWriteOutPath)) {
          dir = new File(lastWriteOutPath).getParent();
          file = new File(lastWriteOutPath).getName();
        }
        var chooser = new JFileChooser(dir);
        chooser.setAcceptAllFileFilterUsed(false);
        chooser.setFileFilter(new FileNameExtensionFilter("*" + dotFormat,
            format));
        chooser.setSelectedFile(new File(file));
        var result = chooser.showSaveDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
          var fileName = chooser.getSelectedFile().getAbsolutePath();
          if (!fileName.endsWith(dotFormat)) {
            fileName += dotFormat;
          }

          // writing
          var dialogWriting = new ErDialogWaiting(getRoot().getStartup(), "Writing");

          String finalFileName = fileName;
          (new SwingWorker<File, Void>() {
            @Override
            protected File doInBackground() throws Exception {
              var f = new File(finalFileName);
              if (!Dump.writeOut(cfgProject, f.getAbsolutePath())) {
                throw new IOException(
                    String.format("Fail to write out to '%s'.", f.getAbsolutePath()));
              }

              cfgProject.lastWriteOutPath = f.getAbsolutePath();
              Config.save();

              return f;
            }

            @Override
            protected void done() {
              try {
                dialogWriting.dispose();

                var f = get();
                JOptionPane.showMessageDialog(
                    getRoot(),
                    new ErLinkLabel(
                        String.format("<html>At: <a>%s</a></html>", f.getAbsolutePath()),
                        new URI(String.format("file://%s", f.getAbsolutePath()))
                    ),
                    "Written json",
                    JOptionPane.INFORMATION_MESSAGE);
              } catch (Exception e) {
                Log.error(e);
                JOptionPane.showMessageDialog(getRoot(), e.getMessage());
              }
            }
          }).execute();
          dialogWriting.setModal(true);
          dialogWriting.setVisible(true);
        }
      });
      p.add(buttonWrite);

      // read
      var buttonRead = new JButton("Read");
      buttonRead.setToolTipText(
          "Read all data from json file, be careful to overwrite completely.");
      buttonRead.addActionListener(actionEvent -> {
        // chooser
        var format = Dump.EXTENSION;
        var dotFormat = "." + format;
        var dir = System.getProperty("user.home");
        var file = String.format("%s%s", cfgProject.name, dotFormat);
        var lastReadFromPath = cfgProject.lastReadFromPath;
        if (!Utils.isNullOrEmpty(lastReadFromPath)) {
          dir = new File(lastReadFromPath).getParent();
          file = new File(lastReadFromPath).getName();
        }
        var chooser = new JFileChooser(dir);
        chooser.setAcceptAllFileFilterUsed(false);
        chooser.setFileFilter(new FileNameExtensionFilter("*" + dotFormat,
            format));
        chooser.setSelectedFile(new File(file));
        var result = chooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
          var fileName = chooser.getSelectedFile().getAbsolutePath();
          if (!fileName.endsWith(dotFormat)) {
            fileName += dotFormat;
          }

          // confirm
          if (JOptionPane.showConfirmDialog(this, "Can you overwrite completely ?") != 0) {
            return;
          }

          // reading
          var dialogReading = new ErDialogWaiting(getRoot().getStartup(), "Reading");

          String finalFileName = fileName;
          (new SwingWorker<File, Void>() {
            @Override
            protected File doInBackground() throws Exception {
              var f = new File(finalFileName);
              if (!Dump.readFrom(cfgProject, f.getAbsolutePath())) {
                throw new IOException(
                    String.format("Fail to read from '%s'.", f.getAbsolutePath()));
              }

              cfgProject.lastReadFromPath = f.getAbsolutePath();
              Config.save();

              return f;
            }

            @Override
            protected void done() {
              try {
                dialogReading.dispose();

                var f = get();
                JOptionPane.showMessageDialog(
                    getRoot(),
                    new ErLinkLabel(
                        String.format("<html>At: <a>%s</a></html>", f.getAbsolutePath()),
                        new URI(String.format("file://%s", f.getAbsolutePath()))
                    ),
                    "Read json",
                    JOptionPane.INFORMATION_MESSAGE);

                // enable
                buttonOpenReadonly.setEnabled(true);
                buttonWrite.setEnabled(true);
              } catch (Exception e) {
                Log.error(e);
                JOptionPane.showMessageDialog(getRoot(), e.getMessage());
              }
            }
          }).execute();
          dialogReading.setModal(true);
          dialogReading.setVisible(true);
        }
      });
      p.add(buttonRead);

      // edit
      var buttonEdit = new JButton("Edit");
      buttonEdit.addActionListener(actionEvent -> {
        var setupProject = new SetupProject(root, cfgProject);
        setupProject.setModal(true);
        setupProject.setVisible(true);
      });
      p.add(buttonEdit);

      // import
      var buttonImport = new JButton("Import");
      if ((new File(cfgProject.dbPath)).exists()) {
        buttonImport.setEnabled(false);
      }
      buttonImport.setToolTipText("Import from ddl, be careful to overwrite completely.");
      buttonImport.addActionListener(actionEvent -> {
        // chooser
        var dir = System.getProperty("user.home");
        var chooser = new JFileChooser(dir);
        var result = chooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
          var fileName = chooser.getSelectedFile().getAbsolutePath();

          // confirm
          if (JOptionPane.showConfirmDialog(this, "Can you overwrite completely ?") != 0) {
            return;
          }

          // importing
          var dialogImporting = new ErDialogWaiting(getRoot().getStartup(), "Importing");

          String finalFileName = fileName;
          (new SwingWorker<File, Void>() {
            @Override
            protected File doInBackground() throws Exception {
              var f = new File(finalFileName);
              if (!Dump.importFromDdl(cfgProject, f.getAbsolutePath())) {
                throw new IOException(
                    String.format("Fail to import from '%s'.", f.getAbsolutePath()));
              }

              return f;
            }

            @Override
            protected void done() {
              try {
                dialogImporting.dispose();

                var f = get();
                JOptionPane.showMessageDialog(
                    getRoot(),
                    new ErLinkLabel(
                        String.format("<html>At: <a>%s</a></html>", f.getAbsolutePath()),
                        new URI(String.format("file://%s", f.getAbsolutePath()))
                    ),
                    "Import ddl",
                    JOptionPane.INFORMATION_MESSAGE);

                // enable
                buttonOpenReadonly.setEnabled(true);
                buttonWrite.setEnabled(true);
                buttonImport.setEnabled(false);
              } catch (Exception e) {
                Log.error(e);
                JOptionPane.showMessageDialog(getRoot(), e.getMessage());
              }
            }
          }).execute();
          dialogImporting.setModal(true);
          dialogImporting.setVisible(true);
        }
      });
      p.add(buttonImport);

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
