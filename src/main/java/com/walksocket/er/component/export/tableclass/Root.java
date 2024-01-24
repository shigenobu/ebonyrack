package com.walksocket.er.component.export.tableclass;

import com.walksocket.er.Config;
import com.walksocket.er.Const;
import com.walksocket.er.Env;
import com.walksocket.er.Log;
import com.walksocket.er.Size.DialogSmall;
import com.walksocket.er.Utils;
import com.walksocket.er.component.ExportTableClass;
import com.walksocket.er.component.export.tableclass.root.Form;
import com.walksocket.er.config.CfgProject;
import com.walksocket.er.custom.ErLinkLabel;
import com.walksocket.er.sqlite.Bucket;
import com.walksocket.er.sqlite.tmp.TmpTableClass;
import com.walksocket.er.template.ErTemplate;
import com.walksocket.er.template.ErTemplateNoEscapeHandler;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.util.Comparator;
import java.util.stream.Collectors;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * Root.
 */
public class Root extends JPanel {

  /**
   * export table class.
   */
  private final ExportTableClass exportTableClass;

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
   * @param exportTableClass exportTableClass
   * @param cfgProject       cfgProject
   */
  public Root(ExportTableClass exportTableClass, CfgProject cfgProject) {
    this.exportTableClass = exportTableClass;

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
        var tmpTableClass = form.getResult().getTmpList().get(0);
        if (Utils.isNullOrEmpty(tmpTableClass.templateValue)) {
          return;
        }

        // chooser
        var dir = new File(System.getProperty("user.home"));
        var lastTableClassSaveDir = cfgProject.lastTableClassSaveDir;
        if (!Utils.isNullOrEmpty(lastTableClassSaveDir)) {
          dir = new File(lastTableClassSaveDir);
        }
        var chooser = new JFileChooser(dir);
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        var result = chooser.showSaveDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
          var f = chooser.getSelectedFile();
          saveTableClass(f, tmpTableClass);
          cfgProject.lastTableClassSaveDir = f.getAbsolutePath();
          Config.save();

          JOptionPane.showMessageDialog(
              this,
              new ErLinkLabel(
                  String.format("<html>At: <a>%s</a></html>", f.getAbsolutePath()),
                  new URI(String.format("file://%s", f.getAbsolutePath()))
              ),
              "Saved table class",
              JOptionPane.INFORMATION_MESSAGE);
        }
      } catch (Exception e) {
        Log.error(e);
        JOptionPane.showMessageDialog(this, e.getMessage());
      }
    });
    panel.add(buttonOk);
  }

  /**
   * save table class.
   *
   * @param dir           dir
   * @param tmpTableClass tmpTableClass
   * @throws IOException
   */
  private void saveTableClass(File dir, TmpTableClass tmpTableClass) throws IOException {
    for (var ctxTable : Bucket.getInstance().getBucketTable().ctxTableList
        .stream()
        .sorted(Comparator.comparing(t -> t.dbTable.tableName))
        .collect(Collectors.toList())) {
      var actionCommand = tmpTableClass.filterTableActionCommand;
      var filterValue = tmpTableClass.filterTableValue;
      if (actionCommand.equals(TmpTableClass.FILTER_CONTAINS)
          && !ctxTable.dbTable.tableName.contains(filterValue)) {
        continue;
      } else if (actionCommand.equals(TmpTableClass.FILTER_START_WITH)
          && !ctxTable.dbTable.tableName.startsWith(filterValue)) {
        continue;
      } else if (actionCommand.equals(TmpTableClass.FILTER_END_WITH)
          && !ctxTable.dbTable.tableName.endsWith(filterValue)) {
        continue;
      }

      if (ctxTable.dbTable == null || ctxTable.dbTable.tableName.startsWith(
          Const.NEW_TABLE_PREFIX)) {
        continue;
      }

      var template = new ErTemplate(Env.getTemplateDir(), tmpTableClass.templateValue,
          ErTemplateNoEscapeHandler.class);
      Bucket.getInstance().assignTableVars(ctxTable, template);
      var data = template.render();

      var f = new File(dir, tmpTableClass.getFinalFileName(ctxTable.dbTable.tableName));
      com.walksocket.er.File.writeString(new FileOutputStream(f), data);
    }
  }
}
