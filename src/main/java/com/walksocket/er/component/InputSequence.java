package com.walksocket.er.component;

import com.walksocket.er.Const;
import com.walksocket.er.Size.DialogSmall;
import com.walksocket.er.Size.Screen;
import com.walksocket.er.component.input.sequence.Root;
import com.walksocket.er.component.main.root.wokspace.Sequence;
import com.walksocket.er.custom.ErDialog;
import java.awt.Dimension;
import java.awt.Point;
import javax.swing.WindowConstants;

/**
 * InputSequence.
 */
public class InputSequence extends ErDialog {

  /**
   * sequence.
   */
  private final Sequence sequence;

  /**
   * Constructor.
   *
   * @param sequence sequence
   */
  public InputSequence(Sequence sequence) {
    super();
    this.sequence = sequence;

    // pos
    var x = (Screen.getWidth() - DialogSmall.WIDTH) / 2;
    var y = (Screen.getHeight() - DialogSmall.HEIGHT) / 2;

    // init
    setTitle(String.format("%s - sequence", Const.TITLE));
    setLocation(new Point(x, y));
    setMinimumSize(new Dimension(DialogSmall.WIDTH, DialogSmall.HEIGHT));
    setResizable(false);
    setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

    // load
    load();
  }

  /**
   * load.
   */
  public void load() {
    var container = getContentPane();
    container.removeAll();
    container.add(new Root(this, sequence.getCtxSequence()));
    container.revalidate();
    container.repaint();
  }

  /**
   * get sequence.
   *
   * @return sequence
   */
  public Sequence getSequence() {
    return sequence;
  }
}
