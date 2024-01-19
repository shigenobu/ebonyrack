package com.walksocket.er.component.input.table.root.form.column;

import com.walksocket.er.Size.DialogLarge;
import com.walksocket.er.Utils;
import com.walksocket.er.sqlite.Bucket;
import com.walksocket.er.sqlite.context.CtxTable;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.stream.Collectors;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * Partition.
 */
public class Partition extends JPanel {

  /**
   * ctx table.
   */
  private final CtxTable ctxTable;

  /**
   * label partition.
   */
  private final JLabel labelPartition = new JLabel("Partition:");

  /**
   * combo box partition name.
   */
  private final JComboBox comboBoxPartitionName;

  /**
   * label expression.
   */
  private final JLabel labelExpression = new JLabel("Expression:");

  /**
   * text area expression.
   */
  private final JTextArea textAreaExpression = new JTextArea(10, 50);

  /**
   * Constructor.
   *
   * @param ctxTable ctxTable
   */
  public Partition(CtxTable ctxTable) {
    this.ctxTable = ctxTable;

    var dbDictPartitionList = Bucket.getInstance().getBucketDict().dbDictPartitionList;

    // layout
    setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
    var group = this;

    // panel - partition name
    var panel1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
    add(panel1);
    panel1.add(labelPartition);
    var partitions = new ArrayList<String>();
    partitions.add("");
    partitions.addAll(dbDictPartitionList.stream()
        .map(g -> g.partitionName)
        .collect(Collectors.toList()));
    comboBoxPartitionName = new JComboBox(partitions.toArray());
    comboBoxPartitionName.addActionListener(actionEvent -> {
      loadExpression();
    });
    panel1.add(comboBoxPartitionName);

    // empty
    var emptyPanel1 = new JPanel();
    emptyPanel1.setPreferredSize(new Dimension(DialogLarge.WIDTH, 0));
    emptyPanel1.setAlignmentX(Component.LEFT_ALIGNMENT);
    add(emptyPanel1);

    // panel - expression
    var panel2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
    add(panel2);
    panel2.add(labelExpression);

    // empty
    var emptyPanel2 = new JPanel();
    emptyPanel2.setPreferredSize(new Dimension(DialogLarge.WIDTH, 0));
    emptyPanel2.setAlignmentX(Component.LEFT_ALIGNMENT);
    add(emptyPanel2);

    var panel3 = new JPanel();
    add(panel3);
    textAreaExpression.setEditable(false);
    panel3.add(new JScrollPane(textAreaExpression));

    // set
    if (ctxTable.dbTablePartition != null) {
      var partitionName = dbDictPartitionList.stream()
          .filter(d -> d.dictPartitionId.equals(ctxTable.dbTablePartition.dictPartitionId))
          .findFirst()
          .get()
          .partitionName;
      comboBoxPartitionName.setSelectedItem(partitionName);
    }

    // load
    loadExpression();
  }

  /**
   * load expression.
   */
  private void loadExpression() {
    textAreaExpression.setText("");

    var partitionName = getPartitionName();
    if (Utils.isNullOrEmpty(partitionName)) {
      return;
    }

    var dbDictPartitionList = Bucket.getInstance().getBucketDict().dbDictPartitionList;
    var opt = dbDictPartitionList.stream()
        .filter(p -> p.partitionName.equals(partitionName))
        .findFirst();
    if (!opt.isPresent()) {
      return;
    }
    textAreaExpression.setText(opt.get().expression);
  }

  /**
   * get partition name.
   *
   * @return partition name.
   */
  public String getPartitionName() {
    return Utils.getString(comboBoxPartitionName.getSelectedItem());
  }
}
