package com.walksocket.er.component.startup.root;

import com.walksocket.er.Const;
import com.walksocket.er.Size.WindowStartup;
import com.walksocket.er.component.startup.Root;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Logo.
 */
public class Logo extends JPanel {

  /**
   * Constructor.
   *
   * @param root root
   */
  public Logo(Root root) {
    // color
    var bgColor = new Color(73, 70, 44);
    setBackground(bgColor);

    // panel - title
    var panel1 = new JPanel(new FlowLayout(FlowLayout.CENTER));
    panel1.setBackground(bgColor);
    panel1.setPreferredSize(new Dimension(WindowStartup.WIDTH - 20, WindowStartup.HEIGHT / 10));
    add(panel1);
    var labelTitle = new JLabel(
        String.format("<html><font color='white'>%s</font></html>", Const.TITLE));
    labelTitle.setFont(new Font(labelTitle.getFont().getName(), Font.BOLD, 36));
    labelTitle.setBackground(bgColor);
    panel1.add(labelTitle);

    // panel - description
    var panel2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
    panel2.setBackground(bgColor);
    panel2.setPreferredSize(new Dimension(WindowStartup.WIDTH - 20, WindowStartup.HEIGHT / 10));
    add(panel2);
    var labelDescription = new JLabel(String.format("<html><font color='white'>%s</font></html>",
        "Entity relationship creation tool for MariaDB."));
    labelDescription.setFont(new Font(labelDescription.getFont().getName(), Font.BOLD, 14));
    labelDescription.setBackground(bgColor);
    panel2.add(labelDescription);
  }
}
