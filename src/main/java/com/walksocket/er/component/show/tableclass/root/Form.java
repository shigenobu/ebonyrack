package com.walksocket.er.component.show.tableclass.root;

import com.walksocket.er.Env;
import com.walksocket.er.Log;
import com.walksocket.er.Size.DialogMedium;
import com.walksocket.er.Utils;
import com.walksocket.er.custom.ErUnderlineBorder;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.io.File;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.text.JTextComponent;

/**
 * Form.
 */
public class Form extends JPanel {

  /**
   * label template.
   */
  private final JLabel labelTemplate = new JLabel("Template:");

  /**
   * combo box template.
   */
  private final JComboBox comboBoxTemplate = new JComboBox();

  /**
   * button generate.
   */
  private final JButton buttonGenerate = new JButton("Generate");

  /**
   * text area class.
   */
  private final JTextArea textAreaClass = new JTextArea();

  /**
   * Constructor.
   */
  public Form() {
    // panel - template
    var panel1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
    panel1.setPreferredSize(new Dimension(DialogMedium.WIDTH - 20, DialogMedium.HEIGHT / 16));
    add(panel1);
    labelTemplate.setPreferredSize(
        new Dimension(DialogMedium.WIDTH / 8, DialogMedium.HEIGHT / 16));
    panel1.add(labelTemplate);
    comboBoxTemplate.setPreferredSize(
        new Dimension(DialogMedium.WIDTH / 6, comboBoxTemplate.getFont().getSize() * 2));
    comboBoxTemplate.setEditable(true);
    panel1.add(comboBoxTemplate);

    var templateDir = new File(Env.getTemplateDir());
    for (var file : templateDir.listFiles()) {
      if (file.getName().endsWith(".vm")) {
        comboBoxTemplate.addItem(file.getName());
      }
    }

    buttonGenerate.addActionListener(actionEvent -> {
      var fileName = Utils.getString(comboBoxTemplate);
      var f = new File(Env.getTemplateDir(), fileName);
      Log.trace(f.getAbsolutePath());


    });
    panel1.add(buttonGenerate);

    var panel2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
    panel2.setPreferredSize(new Dimension(DialogMedium.WIDTH - 20, DialogMedium.HEIGHT / 20));
    panel2.setBorder(new ErUnderlineBorder());
    add(panel2);

    // panel - class
    var panel3 = new JPanel(new FlowLayout(FlowLayout.LEFT));
    panel3.setPreferredSize(new Dimension(DialogMedium.WIDTH - 20, DialogMedium.HEIGHT / 10 * 8));
    add(panel3);
    textAreaClass.setEditable(false);
    textAreaClass.addFocusListener(new FocusAdapter() {
      @Override
      public void focusGained(FocusEvent e) {
        ((JTextComponent) e.getComponent()).selectAll();
      }
    });
    var sp = new JScrollPane(textAreaClass);
    sp.setPreferredSize(new Dimension(DialogMedium.WIDTH - 40, DialogMedium.HEIGHT / 10 * 8 - 20));
    sp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
    sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    panel3.add(sp);
  }
}
