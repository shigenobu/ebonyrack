package com.walksocket.er.component.setup.project.root;

import com.walksocket.er.Env;
import com.walksocket.er.Log;
import com.walksocket.er.Pos;
import com.walksocket.er.Size.DialogSmall;
import com.walksocket.er.Utils;
import com.walksocket.er.component.setup.project.Root;
import com.walksocket.er.config.CfgProject;
import com.walksocket.er.sqlite.Connection;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.nio.file.Paths;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.JTextComponent;

/**
 * Form.
 */
public class Form extends JPanel {

  /**
   * root.
   */
  private final Root root;

  /**
   * cfg project.
   */
  private final CfgProject cfgProject;

  /**
   * label name.
   */
  private final JLabel labelName = new JLabel("Name:");

  /**
   * text field name.
   */
  private final JTextField textFieldName = new JTextField(30);

  /**
   * label db path.
   */
  private final JLabel labelDbPath = new JLabel("Db path:");

  /**
   * text field db path.
   */
  private final JTextField textFieldDbPath = new JTextField(30);

  /**
   * label mini size.
   */
  private final JLabel labelMiniSize = new JLabel(String.format("Mini size (0 - %s, 0 is auto):",
      Pos.MAX));

  /**
   * text field mini size.
   */
  private final JTextField textFieldMiniSize = new JTextField(10);

  /**
   * Constructor.
   *
   * @param root       root
   * @param cfgProject cfgProject
   */
  public Form(Root root, CfgProject cfgProject) {
    this.root = root;
    this.cfgProject = cfgProject;
    var form = this;

    // if not new, not editable
    var editable = Utils.isNullOrEmpty(cfgProject.name);

    // panel - name
    var panel1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
    panel1.setPreferredSize(new Dimension(DialogSmall.WIDTH - 20, DialogSmall.HEIGHT / 10));
    add(panel1);
    labelName.setPreferredSize(
        new Dimension(DialogSmall.WIDTH / 4, DialogSmall.HEIGHT / 10));
    panel1.add(labelName);
    textFieldName.addFocusListener(new FocusAdapter() {
      @Override
      public void focusGained(FocusEvent e) {
        ((JTextComponent) e.getComponent()).selectAll();
      }
    });
    textFieldName.setText(cfgProject.name);
    textFieldName.setEnabled(editable);
    panel1.add(textFieldName);

    // panel - db path
    var panel2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
    panel2.setPreferredSize(new Dimension(DialogSmall.WIDTH - 20, DialogSmall.HEIGHT / 10));
    add(panel2);
    labelDbPath.setPreferredSize(
        new Dimension(DialogSmall.WIDTH / 4, DialogSmall.HEIGHT / 10));
    panel2.add(labelDbPath);
    if (editable) {
      textFieldDbPath.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
          var dir = Env.getHome();
          var dbName = Utils.getString(textFieldName);
          if (Utils.isNullOrEmpty(dbName)) {
            dbName = "database";
          }
          var file = dbName + Connection.DB_PATH_PREFIX;
          Log.trace(cfgProject.dbPath);
          if (!Utils.isNullOrEmpty(cfgProject.dbPath) && (new File(cfgProject.dbPath)).exists()) {
            file = Paths.get(cfgProject.dbPath).toAbsolutePath().toString();
            dir = Paths.get(cfgProject.dbPath).getParent().toAbsolutePath().toString();
            Log.trace(dir);
          }
          var chooser = new JFileChooser(dir);
          chooser.setAcceptAllFileFilterUsed(false);
          chooser.setFileFilter(new FileNameExtensionFilter("*" + Connection.DB_PATH_PREFIX,
              Connection.DB_PATH_PREFIX.substring(1)));
          chooser.setSelectedFile(new File(file));
          var result = chooser.showSaveDialog(form);
          if (result == JFileChooser.APPROVE_OPTION) {
            var dbPath = chooser.getSelectedFile().getAbsolutePath();
            if (!dbPath.endsWith(Connection.DB_PATH_PREFIX)) {
              dbPath += Connection.DB_PATH_PREFIX;
            }
            textFieldDbPath.setText(dbPath);
          }
        }
      });
    }
    textFieldDbPath.setText(cfgProject.dbPath);
    textFieldDbPath.setEnabled(editable);
    panel2.add(textFieldDbPath);

    // panel - mini size
    var panel3 = new JPanel(new FlowLayout(FlowLayout.LEFT));
    panel3.setPreferredSize(new Dimension(DialogSmall.WIDTH - 20, DialogSmall.HEIGHT / 10));
    add(panel3);
    labelMiniSize.setPreferredSize(
        new Dimension(DialogSmall.WIDTH / 4, DialogSmall.HEIGHT / 10));
    panel3.add(labelMiniSize);
    textFieldMiniSize.setText(String.valueOf(cfgProject.miniSize));
    panel3.add(textFieldMiniSize);
  }

  /**
   * pack cfg project.
   */
  public void packCfgProject() {
    cfgProject.name = Utils.getString(textFieldName);
    cfgProject.dbPath = Utils.getString(textFieldDbPath);
    cfgProject.miniSize = Utils.toInt(Utils.getString(textFieldMiniSize));
  }
}
