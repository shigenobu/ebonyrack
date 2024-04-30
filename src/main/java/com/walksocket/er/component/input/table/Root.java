package com.walksocket.er.component.input.table;

import com.walksocket.er.Log;
import com.walksocket.er.Size.DialogLarge;
import com.walksocket.er.component.InputTable;
import com.walksocket.er.component.input.table.root.Form;
import com.walksocket.er.sqlite.Bucket;
import com.walksocket.er.sqlite.context.CtxTable;
import com.walksocket.er.sqlite.tmp.TmpCheck;
import com.walksocket.er.sqlite.tmp.TmpColumn;
import com.walksocket.er.sqlite.tmp.TmpForeignKey;
import com.walksocket.er.sqlite.tmp.TmpKey;
import com.walksocket.er.sqlite.tmp.TmpTable;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * Root.
 */
public class Root extends JPanel {

  /**
   * input table.
   */
  private final InputTable inputTable;

  /**
   * ctx table.
   */
  private final CtxTable ctxTable;

  /**
   * form.
   */
  private final Form form;

  /**
   * button save.
   */
  private final JButton buttonSave = new JButton("Save");

  /**
   * button save and close.
   */
  private final JButton buttonSaveAndClose = new JButton("Save and close");

  /**
   * Constructor.
   *
   * @param inputTable inputTable
   * @param ctxTable   ctxTable
   */
  public Root(InputTable inputTable, CtxTable ctxTable) {
    this.inputTable = inputTable;
    this.ctxTable = ctxTable;

    // layout
    setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

    // panel - form
    form = new Form(this, ctxTable);
    form.setPreferredSize(new Dimension(DialogLarge.WIDTH - 20, DialogLarge.HEIGHT / 40 * 38));
    form.setAlignmentX(Component.LEFT_ALIGNMENT);
    add(form);

    // panel - button
    var panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    panel.setPreferredSize(new Dimension(DialogLarge.WIDTH - 20, DialogLarge.HEIGHT / 40 * 2));
    panel.setAlignmentX(Component.LEFT_ALIGNMENT);
    add(panel);
    buttonSave.addActionListener(actionEvent -> {
      save();
    });
    panel.add(buttonSave);
    buttonSaveAndClose.addActionListener(actionEvent -> {
      if (save()) {
        inputTable.dispose();
        inputTable.getTable().requestFocusInWindow();
      }
    });
    panel.add(buttonSaveAndClose);
  }

  /**
   * save.
   *
   * @return success or fault
   */
  private boolean save() {
    try {
      // save
      TmpTable tmpTable = form.getBase().getTable().getResult().getTmpList().get(0);
      List<TmpColumn> tmpColumnList = form.getColumn().getColumns().getResult().getTmpList();
      var tmpGroupName = form.getColumn().getGroup().getGroupName();
      var tmpPartitionName = form.getColumn().getPartition().getPartitionName();
      List<TmpKey> tmpPrimaryKeyList = form.getOther().getPrimaryKey().getResult().getTmpList();
      List<TmpKey> tmpUniqueKeyList = form.getOther().getUniqueKey().getResult().getTmpList();
      List<TmpKey> tmpKeyList = form.getOther().getKey().getResult().getTmpList();
      List<TmpForeignKey> tmpForeignKeyList = form.getOther().getForeignKey().getResult()
          .getTmpList();
      List<TmpCheck> tmpCheckList = form.getOther().getCheck().getResult().getTmpList();

      Bucket.getInstance().getBucketTable().save(
          ctxTable,
          tmpTable,
          tmpColumnList,
          tmpGroupName,
          tmpPartitionName,
          tmpPrimaryKeyList,
          tmpUniqueKeyList,
          tmpKeyList,
          tmpForeignKeyList,
          tmpCheckList);

      // load
      inputTable.load();
      inputTable.getTable().redraw();
      inputTable.getTable().getWorkspace().resetConnectorTableToTable(inputTable.getTable());
      inputTable.getTable().getWorkspace().reloadTable();

      return true;

    } catch (Exception e) {
      Log.error(e);
      JOptionPane.showMessageDialog(this, e.getMessage());
    }
    return false;
  }

  /**
   * get input table.
   *
   * @return input table
   */
  public InputTable getInputTable() {
    return inputTable;
  }
}
