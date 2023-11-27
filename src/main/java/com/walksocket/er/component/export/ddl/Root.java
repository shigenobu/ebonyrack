package com.walksocket.er.component.export.ddl;

import com.walksocket.er.Config;
import com.walksocket.er.Log;
import com.walksocket.er.Size.DialogSmall;
import com.walksocket.er.Utils;
import com.walksocket.er.component.ExportDdl;
import com.walksocket.er.component.export.ddl.root.Form;
import com.walksocket.er.config.CfgProject;
import com.walksocket.er.sqlite.Bucket;
import com.walksocket.er.sqlite.tmp.TmpDdl;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.stream.Collectors;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * Root.
 */
public class Root extends JPanel {

  /**
   * export ddl.
   */
  private final ExportDdl exportDdl;

  /**
   * form.
   */
  private final Form form;

  /**
   * button ok.
   */
  private final JButton buttonOk = new JButton("Ok");

  /**
   * Constructor.
   *
   * @param exportDdl  exportDdl
   * @param cfgProject cfgProject
   */
  public Root(ExportDdl exportDdl, CfgProject cfgProject) {
    this.exportDdl = exportDdl;

    // layout
    setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

    // panel - form
    form = new Form();
    form.setPreferredSize(new Dimension(DialogSmall.WIDTH - 20, DialogSmall.HEIGHT / 10 * 9));
    form.setAlignmentX(Component.LEFT_ALIGNMENT);
    add(form);

    // panel - button
    var panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    panel.setPreferredSize(new Dimension(DialogSmall.WIDTH - 20, DialogSmall.HEIGHT / 10));
    panel.setAlignmentX(Component.LEFT_ALIGNMENT);
    add(panel);
    buttonOk.addActionListener(actionEvent -> {
      try {
        // chooser
        var format = "sql";
        var dotFormat = "." + format;
        var dir = Paths.get("").toAbsolutePath().toString();
        var file = String.format("%s%s", cfgProject.name, dotFormat);
        var lastDdlSavePath = cfgProject.lastDdlSavePath;
        if (!Utils.isNullOrEmpty(lastDdlSavePath)) {
          dir = new File(lastDdlSavePath).getParent();
          file = new File(lastDdlSavePath).getName();
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

          // ddl
          var ddl = getDdl(form.getResult().getTmpList().get(0));
          var f = new File(fileName);
          com.walksocket.er.File.writeString(new FileOutputStream(f), ddl);
          JOptionPane.showMessageDialog(this,
              String.format("<html>Saved ddl:<br /><u>%s</u></html>",
                  f.getAbsolutePath()));

          cfgProject.lastDdlSavePath = f.getAbsolutePath();
          Config.save();

          exportDdl.dispose();
        }
      } catch (Exception e) {
        Log.error(e);
        JOptionPane.showMessageDialog(this, e.getMessage());
      }
    });
    panel.add(buttonOk);
  }

  /**
   * get ddl.
   *
   * @param tmpDdl tmpDdl
   * @return ddl
   */
  private String getDdl(TmpDdl tmpDdl) {
    var builder = new StringBuilder();

    // sequence
    if (tmpDdl.selectedSequence) {
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
    }

    // table
    if (tmpDdl.selectedTable) {
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
    }

    // foreign key
    if (tmpDdl.selectedForeignKey) {
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
    }

    return builder.toString();
  }
}
