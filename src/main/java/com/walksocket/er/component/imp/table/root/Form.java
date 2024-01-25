package com.walksocket.er.component.imp.table.root;

import com.walksocket.er.Size.DialogSmall;
import com.walksocket.er.Utils;
import java.awt.Dimension;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * Form.
 */
public class Form extends JPanel {

  /**
   * expression starting.
   */
  private static final String expressionStarting = "CREATE TABLE ...";

  /**
   * text area ddl.
   */
  private final JTextArea textAreaDdl = new JTextArea(20, 30);

  /**
   * Constructor.
   */
  public Form() {
    textAreaDdl.setText(expressionStarting);
    var sp = new JScrollPane(textAreaDdl);
    sp.setPreferredSize(new Dimension(DialogSmall.WIDTH - 40, DialogSmall.HEIGHT / 10 * 8));
    sp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
    sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    add(sp);
  }

  /**
   * get ddl.
   *
   * @return ddl
   */
  public String getDdl() {
    return Utils.getString(textAreaDdl);
  }
}
