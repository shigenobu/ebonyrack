package com.walksocket.er.custom;

import com.walksocket.er.Const;
import com.walksocket.er.Size.DialogMini;
import com.walksocket.er.Size.Screen;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import javax.swing.border.LineBorder;

/**
 * ErColorChooser.
 */
public class ErColorChooser extends JDialog {

  /**
   * show dialog.
   *
   * @param initialColor initialColor
   * @return selectedColor
   */
  public static Color showDialog(Color initialColor) {
    var chooser = new ErColorChooser(initialColor);
    chooser.setModal(true);
    chooser.setVisible(true);
    return chooser.selectedColor;
  }

  /**
   * selected color.
   */
  private Color selectedColor;

  /**
   * Constructor.
   *
   * @param initialColor initialColor
   */
  private ErColorChooser(Color initialColor) {
    this.selectedColor = initialColor;

    // pos
    var x = (Screen.getWidth() - DialogMini.WIDTH) / 2;
    var y = (Screen.getHeight() - DialogMini.HEIGHT) / 2;

    // init
    setTitle(String.format("%s - color", Const.TITLE));
    setLocation(new Point(x, y));
    setMinimumSize(new Dimension(DialogMini.WIDTH, DialogMini.HEIGHT));
    setResizable(false);
    setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

    // load
    var container = getContentPane();
    container.removeAll();
    container.add(new ErColorPanel(this, initialColor));
    container.revalidate();
    container.repaint();
  }

  /**
   * colors.
   */
  private static final List<Color> colors = new ArrayList<>();

  static {
    colors.add(new Color(255, 255, 255));
    colors.add(new Color(255, 204, 204));
    colors.add(new Color(204, 255, 255));
    colors.add(new Color(204, 255, 204));
    colors.add(new Color(255, 204, 229));
    colors.add(new Color(209, 204, 255));
    colors.add(new Color(204, 229, 255));

    colors.add(new Color(224, 224, 224));
    colors.add(new Color(255, 153, 153));
    colors.add(new Color(153, 255, 255));
    colors.add(new Color(153, 255, 153));
    colors.add(new Color(255, 153, 204));
    colors.add(new Color(204, 153, 255));
    colors.add(new Color(153, 204, 255));

    colors.add(new Color(192, 192, 192));
    colors.add(new Color(255, 102, 102));
    colors.add(new Color(102, 255, 255));
    colors.add(new Color(102, 255, 102));
    colors.add(new Color(255, 102, 178));
    colors.add(new Color(178, 102, 255));
    colors.add(new Color(102, 178, 255));

    colors.add(new Color(160, 160, 160));
    colors.add(new Color(255, 51, 51));
    colors.add(new Color(51, 255, 255));
    colors.add(new Color(51, 255, 51));
    colors.add(new Color(255, 51, 153));
    colors.add(new Color(153, 51, 255));
    colors.add(new Color(51, 153, 255));
  }

  /**
   * ErColorPanel.
   */
  private class ErColorPanel extends JPanel {

    /**
     * Constructor.
     *
     * @param chooser      chooser
     * @param initialColor initialColor
     */
    private ErColorPanel(ErColorChooser chooser, Color initialColor) {
      // layout
      setLayout(new GridLayout(4, 7));

      // color
      for (var color : colors) {
        var button = new JButton();
        button.setBackground(color);
        button.addActionListener(actionEvent -> {
          selectedColor = ((JButton) actionEvent.getSource()).getBackground();
          chooser.dispose();
        });
        add(button);
        if (color.getRGB() == initialColor.getRGB()) {
          button.setBorder(new LineBorder(Color.BLACK, 2));
        }
      }
    }
  }
}
