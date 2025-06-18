package com.walksocket.er.component.export.ddl;

import com.walksocket.er.Config;
import com.walksocket.er.Date;
import com.walksocket.er.FileUtils;
import com.walksocket.er.Log;
import com.walksocket.er.Size.DialogExport;
import com.walksocket.er.Utils;
import com.walksocket.er.component.ExportDdl;
import com.walksocket.er.component.export.ddl.root.Form;
import com.walksocket.er.config.CfgProject;
import com.walksocket.er.config.CfgProjectDdlHistory;
import com.walksocket.er.custom.ErLinkLabel;
import com.walksocket.er.sqlite.Bucket;
import com.walksocket.er.sqlite.tmp.TmpDdl;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.io.File;
import java.io.FileOutputStream;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Collectors;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

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
    form = new Form(cfgProject);
    form.setPreferredSize(new Dimension(DialogExport.WIDTH - 20, DialogExport.HEIGHT / 20 * 19));
    form.setAlignmentX(Component.LEFT_ALIGNMENT);
    add(form);

    // panel - button
    var panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    panel.setPreferredSize(new Dimension(DialogExport.WIDTH - 20, DialogExport.HEIGHT / 20));
    panel.setAlignmentX(Component.LEFT_ALIGNMENT);
    add(panel);
    buttonOk.addActionListener(actionEvent -> {
      try {
        // ddl
        var result = form.getResult().getTmpList().get(0);
        var ddl = getDdl(result);
        var f = new File(result.savePath);
        FileUtils.writeString(new FileOutputStream(f), ddl);

        var newHistory = new CfgProjectDdlHistory();
        newHistory.exported = Date.now();
        newHistory.ddl = result;

        var newHistories = new ArrayList<CfgProjectDdlHistory>();
        newHistories.add(newHistory);
        newHistories.addAll(cfgProject.ddlHistories);

        var sortedHistories = newHistories
            .stream()
            .sorted(Comparator.comparing(h -> h.exported, Comparator.reverseOrder()))
            .distinct()
            .limit(10)
            .toList();
        cfgProject.ddlHistories = sortedHistories;
        cfgProject.lastDdlSavePath = f.getAbsolutePath();
        Config.save();

        JOptionPane.showMessageDialog(
            this,
            new ErLinkLabel(
                String.format("<html>At: <a>%s</a></html>", f.getAbsolutePath()),
                new URI(String.format("file://%s", f.getAbsolutePath()))
            ),
            "Saved ddl",
            JOptionPane.INFORMATION_MESSAGE);

        form.reloadTable();
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

    // schema
    if (!Utils.isNullOrEmpty(tmpDdl.schemaValue)) {
      builder.append(String.format("use `%s`;\n\n", tmpDdl.schemaValue));
    }

    // sequence
    if (tmpDdl.selectedSequence) {
      builder.append("-- ----------------------------------------\n");
      builder.append("-- sequence\n");
      builder.append("-- ----------------------------------------\n");
      for (var ctxSequence : Bucket.getInstance().getBucketSequence().ctxSequenceList
          .stream()
          .sorted(Comparator.comparing(s -> s.dbSequence.sequenceName))
          .collect(Collectors.toList())) {
        var actionCommand = tmpDdl.filterSequenceActionCommand;
        var filterValue = tmpDdl.filterSequenceValue;
        if (actionCommand.equals(TmpDdl.FILTER_CONTAINS)
            && !ctxSequence.dbSequence.sequenceName.contains(filterValue)) {
          continue;
        } else if (actionCommand.equals(TmpDdl.FILTER_START_WITH)
            && !ctxSequence.dbSequence.sequenceName.startsWith(filterValue)) {
          continue;
        } else if (actionCommand.equals(TmpDdl.FILTER_END_WITH)
            && !ctxSequence.dbSequence.sequenceName.endsWith(filterValue)) {
          continue;
        } else if (actionCommand.equals(TmpDdl.FILTER_CONTAINS_IN_LIST)) {
          var includeList = Arrays.stream(filterValue.split(","))
              .map(v -> v.trim())
              .toList();
          if (!includeList.contains(ctxSequence.dbSequence.sequenceName)) {
            continue;
          }
        }

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
        var actionCommand = tmpDdl.filterTableActionCommand;
        var filterValue = tmpDdl.filterTableValue;
        if (actionCommand.equals(TmpDdl.FILTER_CONTAINS)
            && !ctxTable.dbTable.tableName.contains(filterValue)) {
          continue;
        } else if (actionCommand.equals(TmpDdl.FILTER_START_WITH)
            && !ctxTable.dbTable.tableName.startsWith(filterValue)) {
          continue;
        } else if (actionCommand.equals(TmpDdl.FILTER_END_WITH)
            && !ctxTable.dbTable.tableName.endsWith(filterValue)) {
          continue;
        } else if (actionCommand.equals(TmpDdl.FILTER_CONTAINS_IN_LIST)) {
          var includeList = Arrays.stream(filterValue.split(","))
              .map(v -> v.trim())
              .toList();
          if (!includeList.contains(ctxTable.dbTable.tableName)) {
            continue;
          }
        }

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
        var actionCommand = tmpDdl.filterTableActionCommand;
        var filterValue = tmpDdl.filterTableValue;
        if (actionCommand.equals(TmpDdl.FILTER_CONTAINS)
            && !ctxTable.dbTable.tableName.contains(filterValue)) {
          continue;
        } else if (actionCommand.equals(TmpDdl.FILTER_START_WITH)
            && !ctxTable.dbTable.tableName.startsWith(filterValue)) {
          continue;
        } else if (actionCommand.equals(TmpDdl.FILTER_END_WITH)
            && !ctxTable.dbTable.tableName.endsWith(filterValue)) {
          continue;
        } else if (actionCommand.equals(TmpDdl.FILTER_CONTAINS_IN_LIST)) {
          var includeList = Arrays.stream(filterValue.split(","))
              .map(v -> v.trim())
              .toList();
          if (!includeList.contains(ctxTable.dbTable.tableName)) {
            continue;
          }
        }

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
