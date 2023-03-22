package com.walksocket.er.component.edit.def.root;

import com.walksocket.er.Size.DialogSmall;
import com.walksocket.er.Utils;
import com.walksocket.er.definition.Charset;
import com.walksocket.er.definition.Collate;
import com.walksocket.er.definition.Engine;
import com.walksocket.er.sqlite.Bucket;
import com.walksocket.er.sqlite.TmpResult;
import com.walksocket.er.sqlite.tmp.TmpDefault;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Form.
 */
public class Form extends JPanel {

  /**
   * label default engine.
   */
  private final JLabel labelDefaultEngine = new JLabel("Default engine:");

  /**
   * combo box default engine
   */
  private final JComboBox comboBoxDefaultEngine = new JComboBox(
      Engine.getEngineListForProject().toArray());

  /**
   * label default charset.
   */
  private final JLabel labelDefaultCharset = new JLabel("Default charset:");

  /**
   * combo box default charset.
   */
  private final JComboBox comboBoxDefaultCharset = new JComboBox(
      Charset.getCharsetListForProject().toArray());

  /**
   * label default collate.
   */
  private final JLabel labelDefaultCollate = new JLabel("Default collate:");

  /**
   * combo box default collate.
   */
  private final JComboBox comboBoxDefaultCollate = new JComboBox(
      Collate.getCollateListForProject().toArray());

  /**
   * Constructor.
   */
  public Form() {
    // panel - default engine
    var panel1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
    panel1.setPreferredSize(new Dimension(DialogSmall.WIDTH - 20, DialogSmall.HEIGHT / 10));
    add(panel1);
    labelDefaultEngine.setPreferredSize(
        new Dimension(DialogSmall.WIDTH / 4, DialogSmall.HEIGHT / 10));
    panel1.add(labelDefaultEngine);
    comboBoxDefaultEngine.setPreferredSize(
        new Dimension(DialogSmall.WIDTH / 4, comboBoxDefaultEngine.getFont().getSize() * 2));
    comboBoxDefaultEngine.setEditable(true);
    panel1.add(comboBoxDefaultEngine);

    // panel - default charset
    var panel2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
    panel2.setPreferredSize(new Dimension(DialogSmall.WIDTH - 20, DialogSmall.HEIGHT / 10));
    add(panel2);
    labelDefaultCharset.setPreferredSize(
        new Dimension(DialogSmall.WIDTH / 4, DialogSmall.HEIGHT / 10));
    panel2.add(labelDefaultCharset);
    comboBoxDefaultCharset.setPreferredSize(
        new Dimension(DialogSmall.WIDTH / 4, comboBoxDefaultCharset.getFont().getSize() * 2));
    comboBoxDefaultCharset.setEditable(true);
    panel2.add(comboBoxDefaultCharset);

    // panel - default collate
    var panel3 = new JPanel(new FlowLayout(FlowLayout.LEFT));
    panel3.setPreferredSize(new Dimension(DialogSmall.WIDTH - 20, DialogSmall.HEIGHT / 10));
    add(panel3);
    labelDefaultCollate.setPreferredSize(
        new Dimension(DialogSmall.WIDTH / 4, DialogSmall.HEIGHT / 10));
    panel3.add(labelDefaultCollate);
    comboBoxDefaultCollate.setPreferredSize(
        new Dimension(DialogSmall.WIDTH / 4, comboBoxDefaultCollate.getFont().getSize() * 2));
    comboBoxDefaultCollate.setEditable(true);
    panel3.add(comboBoxDefaultCollate);

    // set
    var dbDefault = Bucket.getInstance().getBucketDefault().dbDefault;
    comboBoxDefaultEngine.setSelectedItem(dbDefault.defaultEngine);
    comboBoxDefaultCharset.setSelectedItem(dbDefault.defaultCharset);
    comboBoxDefaultCollate.setSelectedItem(dbDefault.defaultCollate);
  }

  /**
   * get result.
   *
   * @return result
   */
  public TmpResult<TmpDefault> getResult() {
    var tmpDefault = new TmpDefault();
    tmpDefault.defaultEngine = Utils.getString(comboBoxDefaultEngine);
    tmpDefault.defaultCharset = Utils.getString(comboBoxDefaultCharset);
    tmpDefault.defaultCollate = Utils.getString(comboBoxDefaultCollate);

    return new TmpResult<TmpDefault>(tmpDefault) {
      @Override
      protected void validate() throws Exception {
      }
    };
  }
}
