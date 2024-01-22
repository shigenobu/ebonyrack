package com.walksocket.er.component.show.tableclass.root;

import com.walksocket.er.Env;
import com.walksocket.er.Log;
import com.walksocket.er.Size.DialogMedium;
import com.walksocket.er.Utils;
import com.walksocket.er.custom.ErUnderlineBorder;
import com.walksocket.er.sqlite.Bucket;
import com.walksocket.er.sqlite.context.CtxTable;
import com.walksocket.er.template.ErTemplate;
import com.walksocket.er.template.ErTemplateNoEscapeHandler;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.text.JTextComponent;

/**
 * Form.
 */
public class Form extends JPanel {

  /**
   * combo box template.
   */
  private final JComboBox comboBoxTemplate = new JComboBox();

  /**
   * button edit.
   */
  private final JButton buttonEdit = new JButton("Edit");

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
   *
   * @param ctxTable ctxTable
   */
  public Form(CtxTable ctxTable) {
    // panel - template
    var panel1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
    panel1.setPreferredSize(new Dimension(DialogMedium.WIDTH - 20, DialogMedium.HEIGHT / 16));
    add(panel1);

    var templateDir = new File(Env.getTemplateDir());
    for (var file : templateDir.listFiles()) {
      if (file.getName().endsWith(".vm")) {
        comboBoxTemplate.addItem(file.getName());
      }
    }

    comboBoxTemplate.setPreferredSize(
        new Dimension(DialogMedium.WIDTH / 4, comboBoxTemplate.getFont().getSize() * 2));
    comboBoxTemplate.addActionListener(actionEvent -> {
      textAreaClass.setText("");
    });
    panel1.add(comboBoxTemplate);

    buttonEdit.addActionListener(actionEvent -> {
      var fileName = Utils.getString(comboBoxTemplate);
      if (Utils.isNullOrEmpty(fileName)) {
        return;
      }

      if (Desktop.isDesktopSupported()) {
        var f = new File(Env.getTemplateDir(), fileName);
        try {
          var uri = new URI(String.format("file://%s", f.getAbsolutePath()));
          Desktop.getDesktop().browse(uri);
        } catch (IOException | URISyntaxException e) {
          Log.error(e);
          JOptionPane.showMessageDialog(this, e.getMessage());
        }
      }
    });
    panel1.add(buttonEdit);

    // panel - button
    var panel2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
    panel2.setPreferredSize(new Dimension(DialogMedium.WIDTH - 20, DialogMedium.HEIGHT / 16));
    add(panel2);
    buttonGenerate.addActionListener(actionEvent -> {
      textAreaClass.setText("");

      var fileName = Utils.getString(comboBoxTemplate);
      if (Utils.isNullOrEmpty(fileName)) {
        return;
      }
      var f = new File(Env.getTemplateDir(), fileName);
      Log.trace(f.getAbsolutePath());

      var template = new ErTemplate(Env.getTemplateDir(), f.getName(),
          ErTemplateNoEscapeHandler.class);
      Bucket.getInstance().assignTableVars(ctxTable, template);
      textAreaClass.setText(template.render());
    });
    panel2.add(buttonGenerate);

    var panel3 = new JPanel(new FlowLayout(FlowLayout.LEFT));
    panel3.setPreferredSize(new Dimension(DialogMedium.WIDTH - 20, 10));
    panel3.setBorder(new ErUnderlineBorder());
    add(panel3);

    // panel - class
    var panel4 = new JPanel(new FlowLayout(FlowLayout.LEFT));
    panel4.setPreferredSize(new Dimension(DialogMedium.WIDTH - 20, DialogMedium.HEIGHT / 10 * 8));
    add(panel4);
    textAreaClass.setEditable(false);
    textAreaClass.addFocusListener(new FocusAdapter() {
      @Override
      public void focusGained(FocusEvent e) {
        ((JTextComponent) e.getComponent()).selectAll();
      }
    });
    var sp = new JScrollPane(textAreaClass);
    sp.setPreferredSize(new Dimension(DialogMedium.WIDTH - 40, DialogMedium.HEIGHT / 10 * 8 - 30));
    sp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
    sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    panel4.add(sp);
  }
}
