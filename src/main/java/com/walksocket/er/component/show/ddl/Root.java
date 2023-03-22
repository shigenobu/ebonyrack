package com.walksocket.er.component.show.ddl;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.text.JTextComponent;

/**
 * Root.
 */
public class Root extends JPanel {

  /**
   * Constructor.
   *
   * @param ddl ddl
   */
  public Root(String ddl) {
    // layout
    setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

    // textarea
    var textAreaDdl = new JTextArea();
    textAreaDdl.setEditable(false);
//    textAreaDdl.setWrapStyleWord(true);
//    textAreaDdl.setLineWrap(true);
    textAreaDdl.setText(ddl);
    textAreaDdl.addFocusListener(new FocusAdapter() {
      @Override
      public void focusGained(FocusEvent e) {
        ((JTextComponent) e.getComponent()).selectAll();
      }
    });
    var sp = new JScrollPane(textAreaDdl);
    sp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
    sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    add(sp);
  }
}
