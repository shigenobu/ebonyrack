package com.walksocket.er.component.export.foreignkeyinfo;

import com.walksocket.er.Config;
import com.walksocket.er.Date;
import com.walksocket.er.FileUtils;
import com.walksocket.er.Json;
import com.walksocket.er.Log;
import com.walksocket.er.Size.DialogExport;
import com.walksocket.er.component.ExportForeignKeyInfo;
import com.walksocket.er.component.export.foreignkeyinfo.root.Form;
import com.walksocket.er.config.CfgProject;
import com.walksocket.er.config.CfgProjectForeignKeyInfoHistory;
import com.walksocket.er.custom.ErLinkLabel;
import com.walksocket.er.parts.info.InfoForeignKey;
import com.walksocket.er.parts.info.InfoForeignKeyContext;
import com.walksocket.er.sqlite.Bucket;
import com.walksocket.er.sqlite.Tmp;
import com.walksocket.er.sqlite.tmp.TmpForeignKeyInfo;
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
   * export foreign key info.
   */
  private final ExportForeignKeyInfo exportForeignKeyInfo;

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
   * @param exportForeignKeyInfo exportForeignKeyInfo
   * @param cfgProject           cfgProject
   */
  public Root(ExportForeignKeyInfo exportForeignKeyInfo, CfgProject cfgProject) {
    this.exportForeignKeyInfo = exportForeignKeyInfo;

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
        // foreign key info
        var result = form.getResult().getTmpList().get(0);
        var json = getForeignKeyInfo(result);
        var f = new File(result.savePath);
        FileUtils.writeString(new FileOutputStream(f), json);

        var newHistory = new CfgProjectForeignKeyInfoHistory();
        newHistory.exported = Date.now();
        newHistory.foreignKeyInfo = result;

        var newHistories = new ArrayList<CfgProjectForeignKeyInfoHistory>();
        newHistories.add(newHistory);
        newHistories.addAll(cfgProject.foreignKeyInfoHistories);

        var sortedHistories = newHistories
            .stream()
            .sorted(Comparator.comparing(h -> h.exported, Comparator.reverseOrder()))
            .distinct()
            .limit(10)
            .toList();
        cfgProject.foreignKeyInfoHistories = sortedHistories;
        cfgProject.lastForeignKeyInfoSavePath = f.getAbsolutePath();
        Config.save();

        JOptionPane.showMessageDialog(
            this,
            new ErLinkLabel(
                String.format("<html>At: <a>%s</a></html>", f.getAbsolutePath()),
                new URI(String.format("file://%s", f.getAbsolutePath()))
            ),
            "Saved foreign key info",
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
   * get foreign key info.
   *
   * @param tmpForeignKeyInfo tmpForeignKeyInfo
   * @return foreign key info
   */
  private String getForeignKeyInfo(TmpForeignKeyInfo tmpForeignKeyInfo) {
    var infoForeignKeyList = new ArrayList<InfoForeignKey>();

    var dbTableList = Bucket.getInstance().getBucketTable().ctxTableList.stream()
        .map(d -> d.dbTable)
        .collect(Collectors.toList());
    var dbDictColumnList = Bucket.getInstance().getBucketDict().dbDictColumnList;

    // table
    for (var ctxTable : Bucket.getInstance().getBucketTable().ctxTableList
        .stream()
        .sorted(Comparator.comparing(t -> t.dbTable.tableName))
        .collect(Collectors.toList())) {
      var actionCommand = tmpForeignKeyInfo.filterTableActionCommand;
      var filterValue = tmpForeignKeyInfo.filterTableValue;
      if (actionCommand.equals(TmpForeignKeyInfo.FILTER_CONTAINS)
          && !ctxTable.dbTable.tableName.contains(filterValue)) {
        continue;
      } else if (actionCommand.equals(TmpForeignKeyInfo.FILTER_START_WITH)
          && !ctxTable.dbTable.tableName.startsWith(filterValue)) {
        continue;
      } else if (actionCommand.equals(TmpForeignKeyInfo.FILTER_END_WITH)
          && !ctxTable.dbTable.tableName.endsWith(filterValue)) {
        continue;
      } else if (actionCommand.equals(TmpForeignKeyInfo.FILTER_CONTAINS_IN_LIST)) {
        var includeList = Arrays.stream(filterValue.split(","))
            .map(v -> v.trim())
            .toList();
        if (!includeList.contains(ctxTable.dbTable.tableName)) {
          continue;
        }
      }

      var info = new InfoForeignKey();
      info.tableName = ctxTable.dbTable.tableName;

      for (var ctxInnerForeignKey : ctxTable.ctxInnerForeignKeyList) {
        var tmpForeignKey = Tmp.createTmpForeignKey(ctxInnerForeignKey.dbTableForeignKey,
            ctxInnerForeignKey.dbTableForeignKeyColumnList, dbTableList, dbDictColumnList);

        var actionReferenceCommand = tmpForeignKeyInfo.filterReferenceTableActionCommand;
        var filterReferenceValue = tmpForeignKeyInfo.filterReferenceTableValue;
        if (actionReferenceCommand.equals(TmpForeignKeyInfo.FILTER_CONTAINS)
            && !tmpForeignKey.referenceTableName.contains(filterReferenceValue)) {
          continue;
        } else if (actionReferenceCommand.equals(TmpForeignKeyInfo.FILTER_START_WITH)
            && !tmpForeignKey.referenceTableName.startsWith(filterReferenceValue)) {
          continue;
        } else if (actionReferenceCommand.equals(TmpForeignKeyInfo.FILTER_END_WITH)
            && !tmpForeignKey.referenceTableName.endsWith(filterReferenceValue)) {
          continue;
        } else if (actionReferenceCommand.equals(TmpForeignKeyInfo.FILTER_CONTAINS_IN_LIST)) {
          var includeList = Arrays.stream(filterReferenceValue.split(","))
              .map(v -> v.trim())
              .toList();
          if (!includeList.contains(tmpForeignKey.referenceTableName)) {
            continue;
          }
        }

        var infoContext = new InfoForeignKeyContext();
        infoContext.constraintName = tmpForeignKey.constraintName;
        infoContext.keyName = tmpForeignKey.keyName;
        infoContext.columnNames = tmpForeignKey.columnForeignKeyOptionList.stream()
            .map(v -> v.columnName).toList();
        infoContext.referenceTableName = tmpForeignKey.referenceTableName;
        infoContext.referenceColumnNames = tmpForeignKey.referenceColumnForeignKeyOptionList.stream()
            .map(v -> v.columnName).toList();
        infoContext.onUpdate = tmpForeignKey.onUpdate;
        infoContext.onDelete = tmpForeignKey.onDelete;
        infoContext.relationship = tmpForeignKey.relationship;
        info.foreignKeys.add(infoContext);
      }

      infoForeignKeyList.add(info);
    }

    return Json.toJsonStringFriendly(infoForeignKeyList);
  }
}
