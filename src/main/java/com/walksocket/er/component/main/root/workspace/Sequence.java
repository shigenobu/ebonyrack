package com.walksocket.er.component.main.root.workspace;

import com.walksocket.er.Const;
import com.walksocket.er.Log;
import com.walksocket.er.Utils;
import com.walksocket.er.component.InputSequence;
import com.walksocket.er.component.ShowDdl;
import com.walksocket.er.component.main.root.Workspace;
import com.walksocket.er.custom.ErColorChooser;
import com.walksocket.er.custom.ErConnector;
import com.walksocket.er.custom.ErConnectorColor;
import com.walksocket.er.custom.ErConnectorEndpoint;
import com.walksocket.er.sqlite.Bucket;
import com.walksocket.er.sqlite.context.CtxSequence;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.SwingUtilities;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;

/**
 * Sequence.
 */
public class Sequence extends ErConnectorEndpoint {

  /**
   * max positioned.
   */
  public static final int MAX_POSITIONED = 999;

  /**
   * min width.
   */
  private static final int MIN_WIDTH = 200;

  /**
   * max width.
   */
  private static final int MAX_WIDTH = 400;

  /**
   * fix height.
   */
  private static final int FIX_HEIGHT = 36;

  /**
   * border size.
   */
  private static final int BORDER_SIZE = 2;

  /**
   * workspace.
   */
  private final Workspace workspace;

  /**
   * context.
   */
  private final CtxSequence ctxSequence;

  /**
   * panel name.
   */
  private final JPanel panelName;

  /**
   * label sequence name.
   */
  private final JLabel labelSequenceName = new JLabel();

  /**
   * Constructor.
   *
   * @param workspace   workspace
   * @param ctxSequence ctxSequence
   */
  public Sequence(Workspace workspace, CtxSequence ctxSequence) {
    super(workspace);
    this.workspace = workspace;
    this.ctxSequence = ctxSequence;

    // layout
    setLayout(null);

    // init
    setLocation(new Point(ctxSequence.dbSequenceOption.posX, ctxSequence.dbSequenceOption.posY));
    setSize(new Dimension(MIN_WIDTH, FIX_HEIGHT));
    var sequence = this;

    // key event
    addKeyListener(new KeyAdapter() {
      @Override
      public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_C && e.isControlDown()) {
          // copy
          workspace.getRoot().getMain().setCopied(ctxSequence, CtxSequence.class);
        } else if (e.getKeyCode() == KeyEvent.VK_F && e.isControlDown()) {
          // search
          workspace.showSearchTextDialog();
        } else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
          // clear
          workspace.clearSearchText();
        }
      }
    });

    // sequence
    panelName = new JPanel(new FlowLayout(FlowLayout.CENTER)) {
      @Override
      protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // search
        var searchText = workspace.getSearchText();
        if (Utils.isNullOrEmpty(searchText)) {
          return;
        }
        var targetText = labelSequenceName.getText();
        var startPos = targetText.indexOf(searchText);
        if (startPos < 0) {
          return;
        }
        var endPos = startPos + searchText.length();

        var metrics = labelSequenceName.getFontMetrics(labelSequenceName.getFont());
        String start = targetText.substring(0, startPos);
        String text = targetText.substring(startPos, endPos);
        int startX = 5 + metrics.stringWidth(start);
        int startY = 8;
        int width = metrics.stringWidth(text);
        int height = metrics.getHeight();

        g.setColor(Color.YELLOW);
        g.fillRect(startX, startY, width, height);
      }
    };
    panelName.setLocation(new Point(BORDER_SIZE, BORDER_SIZE));
    panelName.setSize(new Dimension(getWidth() - BORDER_SIZE * 2, getHeight() - BORDER_SIZE * 2));
    panelName.setBackground(new Color(ctxSequence.dbSequenceOption.color));
    panelName.addMouseListener(new MouseAdapter() {

      @Override
      public void mouseClicked(MouseEvent e) {
        if (e.isPopupTrigger() || SwingUtilities.isRightMouseButton(e)) {
          return;
        }

        // edit
        if (e.getClickCount() >= 2) {
          var inputSequence = new InputSequence(sequence);
          inputSequence.setModal(true);
          inputSequence.setVisible(true);
        }
      }

      @Override
      public void mousePressed(MouseEvent e) {
        // focus
        sequence.requestFocusInWindow();

        // menu
        if (e.isPopupTrigger() || SwingUtilities.isRightMouseButton(e)) {
          doPop(e);
          return;
        }

        // selecting
        if (e.isShiftDown()) {
          return;
        }

        // change cursor
        var cursor = Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR);
        panelName.setCursor(cursor);

        // moving
        if (getWorkspace().getSelectingRange().isSelecting(sequence)) {
          getWorkspace().getSelectingRange().movingStart(sequence, e.getPoint());
        } else {
          getWorkspace().getSelectingRange().clearAllMovers();
          movingStart(e.getPoint());
        }
      }

      @Override
      public void mouseReleased(MouseEvent e) {
        // focus
        sequence.requestFocusInWindow();

        // menu
        if (e.isPopupTrigger() || SwingUtilities.isRightMouseButton(e)) {
          doPop(e);
          return;
        }

        // selecting
        if (e.isShiftDown()) {
          if (getWorkspace().getSelectingRange().setOrUnsetMover(sequence)) {
            for (var c : getConnectors()) {
              c.zRestore(sequence);
            }
          } else {
            for (var c : getConnectors()) {
              c.zFirst(sequence, ErConnectorColor.SELECTED_COLOR);
            }
          }
          return;
        }

        // moving
        if (getWorkspace().getSelectingRange().isSelecting(sequence)) {
          getWorkspace().getSelectingRange().movingEnd();
        } else {
          movingEnd();
          getWorkspace().getSelectingRange().clearAllMovers();
        }

        // reverse cursor
        var cursor = Cursor.getDefaultCursor();
        panelName.setCursor(cursor);
      }

      private void doPop(MouseEvent e) {
        showPopupMenu(e);
      }
    });
    add(panelName);
    panelName.add(labelSequenceName);

    // redraw
    redraw();
  }

  /**
   * get workspace.
   *
   * @return workspace
   */
  public Workspace getWorkspace() {
    return workspace;
  }

  /**
   * get ctx sequence.
   *
   * @return ctx sequence
   */
  public CtxSequence getCtxSequence() {
    return ctxSequence;
  }

  /**
   * redraw.
   */
  public void redraw() {
    labelSequenceName.setText("(S) " + ctxSequence.dbSequence.sequenceName);
    var w = labelSequenceName.getFontMetrics(labelSequenceName.getFont())
        .stringWidth(labelSequenceName.getText());
    if (w < MIN_WIDTH) {
      w = MIN_WIDTH;
    }
    if (w > MAX_WIDTH) {
      w = MAX_WIDTH;
    }
    setSize(new Dimension(w, getHeight()));
    panelName.setSize(new Dimension(w - BORDER_SIZE * 2, panelName.getHeight()));
    panelName.setToolTipText(ctxSequence.dbSequence.getTipText());
    labelSequenceName.setPreferredSize(
        new Dimension(w - BORDER_SIZE * 2 - 10, panelName.getHeight() - 10));

    // redraw connector
    redrawAllConnectors();
  }

  @Override
  public void disconnectComplete(ErConnector connector, ErConnectorEndpoint otherEndpoint) {
    super.disconnectComplete(connector, otherEndpoint);

    if (otherEndpoint == null) {
      return;
    }
    if (otherEndpoint instanceof Note) {
      workspace.removeConnectorFromNoteToSequence(connector);

      try {
        // remove
        var note = (Note) otherEndpoint;
        var opt = Bucket.getInstance().getBucketConnector().dbNoteConnectorSequenceList.stream()
            .filter(c -> c.noteId.equals(note.getCtxNote().dbNote.noteId) && c.sequenceId.equals(
                getCtxSequence().dbSequence.sequenceId))
            .findFirst();
        if (opt.isPresent()) {
          var dbNoteConnectorSequence = opt.get();
          Bucket.getInstance().getBucketConnector().removeNoteToSequence(dbNoteConnectorSequence);
        }
      } catch (Exception e) {
        Log.error(e);
        JOptionPane.showMessageDialog(workspace.getRoot(), e.getMessage());
      }
    }
  }

  @Override
  public void showPopupMenu(MouseEvent mouseEvent) {
    var menu = new PopupMenu(this);
    menu.show(mouseEvent.getComponent(), mouseEvent.getX(), mouseEvent.getY());
  }

  @Override
  protected void movingContinuing(int x, int y) {
    // position
    setLocation(x, y);

    // redraw connector
    redrawAllConnectors();
  }

  @Override
  protected void movingComplete(int x, int y) {
    try {
      // save
      ctxSequence.dbSequenceOption.posX = x;
      ctxSequence.dbSequenceOption.posY = y;
      Bucket.getInstance().getBucketSequence().saveOption(ctxSequence);

      // position
      setLocation(x, y);

      // redraw connector
      redrawAllConnectors();

      // focus
      requestFocusInWindow();
    } catch (Exception e) {
      Log.error(e);
      JOptionPane.showMessageDialog(workspace.getRoot(), e.getMessage());
    }
  }

  @Override
  public void focusGainedComplete() {
    workspace.notifySelectionSequence(this);
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);

    // search
    var searchText = workspace.getSearchText();
    if (Utils.isNullOrEmpty(searchText)) {
      return;
    }
    var targetText = labelSequenceName.getText();
    var startPos = targetText.indexOf(searchText);
    if (startPos < 0) {
      var g2 = (Graphics2D) g;
      AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.1f);
      g2.setComposite(ac);
    }
  }

  @Override
  public String toString() {
    var searchText = workspace.getSearchText();
    if (Utils.isNullOrEmpty(searchText)) {
      return ctxSequence.dbSequence.sequenceName;
    }

    var targetText = labelSequenceName.getText();
    var startPos = targetText.indexOf(searchText);
    if (startPos < 0) {
      return String.format("<html><font color=#dcdcdc>%s</font></html>",
          ctxSequence.dbSequence.sequenceName);
    }
    return ctxSequence.dbSequence.sequenceName;
  }

  /**
   * PopupMenu.
   */
  public class PopupMenu extends JPopupMenu {

    /**
     * Constructor.
     *
     * @param sequence sequence
     */
    public PopupMenu(Sequence sequence) {
      addPopupMenuListener(new PopupMenuListener() {
        @Override
        public void popupMenuWillBecomeVisible(PopupMenuEvent popupMenuEvent) {
          sequence.requestFocusInWindow();
        }

        @Override
        public void popupMenuWillBecomeInvisible(PopupMenuEvent popupMenuEvent) {
          sequence.requestFocusInWindow();
        }

        @Override
        public void popupMenuCanceled(PopupMenuEvent popupMenuEvent) {
          sequence.requestFocusInWindow();
        }
      });

      // Edit
      var menuItemEdit = new JMenuItem("Edit sequence");
      menuItemEdit.addActionListener(actionEvent -> {
        var inputSequence = new InputSequence(sequence);
        inputSequence.setModal(true);
        inputSequence.setVisible(true);
      });
      add(menuItemEdit);
      addSeparator();

      // color
      var menuItemColor = new JMenuItem("Choose sequence color");
      menuItemColor.addActionListener(actionEvent -> {
        var color = ErColorChooser.showDialog(new Color(ctxSequence.dbSequenceOption.color));
        ctxSequence.dbSequenceOption.color = color.getRGB();

        try {
          // save
          Bucket.getInstance().getBucketSequence().saveOption(ctxSequence);
          panelName.setBackground(color);
        } catch (Exception e) {
          Log.error(e);
          JOptionPane.showMessageDialog(workspace.getRoot(), e.getMessage());
        }
      });
      add(menuItemColor);
      addSeparator();

      // ddl
      var menuItemDdl = new JMenuItem("Show sequence ddl");
      menuItemDdl.addActionListener(actionEvent -> {
        var showDdl = new ShowDdl(Bucket.getInstance().getSequenceDdl(ctxSequence));
        showDdl.setModal(true);
        showDdl.setVisible(true);
      });
      add(menuItemDdl);

      // copy
      var menuItemCopy = new JMenuItem("Copy sequence");
      menuItemCopy.addActionListener(actionEvent -> {
        workspace.getRoot().getMain().setCopied(ctxSequence, CtxSequence.class);
      });
      addSeparator();
      add(menuItemCopy);

      // remove
      var menuItemRemove = new JMenuItem("Remove sequence");
      menuItemRemove.addActionListener(actionEvent -> {
        // confirm
        var sequenceName = ctxSequence.dbSequence.sequenceName;
        if (!sequenceName.startsWith(Const.NEW_SEQUENCE_PREFIX)
            && JOptionPane.showConfirmDialog(sequence,
            String.format("Remove sequence '%s' ?", sequenceName))
            != 0) {
          return;
        }

        try {
          // remove
          Bucket.getInstance().getBucketSequence().remove(ctxSequence);
          sequence.getWorkspace().removeSequence(sequence);
        } catch (Exception e) {
          Log.error(e);
          JOptionPane.showMessageDialog(workspace.getRoot(), e.getMessage());
        }
      });
      addSeparator();
      add(menuItemRemove);
    }
  }
}
