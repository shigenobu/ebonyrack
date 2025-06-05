package com.walksocket.er.component.main.root.workspace;

import com.walksocket.er.Const;
import com.walksocket.er.Log;
import com.walksocket.er.Utils;
import com.walksocket.er.component.main.root.Workspace;
import com.walksocket.er.custom.ErColorChooser;
import com.walksocket.er.custom.ErConnector;
import com.walksocket.er.custom.ErConnectorColor;
import com.walksocket.er.custom.ErConnectorEndpoint;
import com.walksocket.er.custom.ErConnectorEndpointOrigin;
import com.walksocket.er.custom.ErConnectorStyle.LineStyle;
import com.walksocket.er.sqlite.Bucket;
import com.walksocket.er.sqlite.context.CtxNote;
import com.walksocket.er.sqlite.entity.DbNoteConnectorSequence;
import com.walksocket.er.sqlite.entity.DbNoteConnectorTable;
import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import javax.swing.text.JTextComponent;

/**
 * Note.
 */
public class Note extends ErConnectorEndpoint implements ErConnectorEndpointOrigin {

  /**
   * max positioned.
   */
  public static final int MAX_POSITIONED = 999;

  /**
   * min width.
   */
  private static final int MIN_WIDTH = 100;

  /**
   * max width.
   */
  private static final int MAX_WIDTH = 600;

  /**
   * min height.
   */
  private static final int MIN_HEIGHT = 100;

  /**
   * max height.
   */
  private static final int MAX_HEIGHT = 600;

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
  private final CtxNote ctxNote;

  /**
   * panel side.
   */
  private final JPanel panelSide;

  /**
   * panel subject.
   */
  private final JPanel panelSubject;

  /**
   * panel body.
   */
  private final JPanel panelBody;

  /**
   * panel footer.
   */
  private final JPanel panelFooter;

  /**
   * text field subject.
   */
  private final JTextField textFieldSubject;

  /**
   * text area body.
   */
  private final JTextArea textAreaBody;

  /**
   * resizing.
   */
  private boolean resizing;

  /**
   * resizing timer.
   */
  private final Timer resizingTimer;

  /**
   * Constructor.
   *
   * @param workspace workspace
   * @param ctxNote   ctxNote
   */
  public Note(Workspace workspace, CtxNote ctxNote) {
    super(workspace);
    this.workspace = workspace;
    this.ctxNote = ctxNote;

    // layout
    setLayout(null);

    // color
    setBackground(Color.WHITE);

    // init
    setLocation(new Point(ctxNote.dbNoteOption.posX, ctxNote.dbNoteOption.posY));
    setSize(new Dimension(ctxNote.dbNoteOption.width, ctxNote.dbNoteOption.height));
    var note = this;

    // key event
    addKeyListener(new KeyAdapter() {
      @Override
      public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_C && e.isControlDown()) {
          // copy
          workspace.getRoot().getMain().setCopied(ctxNote, CtxNote.class);
        } else if (e.getKeyCode() == KeyEvent.VK_F && e.isControlDown()) {
          // search
          workspace.showSearchTextDialog();
        } else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
          // clear
          workspace.clearSearchText();
        }
      }
    });

    // side
    panelSide = new JPanel(new FlowLayout(FlowLayout.LEFT));
    panelSide.setLocation(new Point(BORDER_SIZE, BORDER_SIZE));
    panelSide.setSize(new Dimension(10, ctxNote.dbNoteOption.height - BORDER_SIZE * 2));
    panelSide.setBackground(new Color(96, 96, 96));
    panelSide.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseReleased(MouseEvent e) {
        // focus
        note.requestFocusInWindow();

        // connector
        var x = note.getX() + e.getX() + BORDER_SIZE;
        var y = note.getY() + e.getY() + BORDER_SIZE;
        var target = getWorkspace().getComponentAt(x, y);
        if (target == null) {
          return;
        }
        Log.trace("connected from note:" + target.getClass().getName());
        if (target instanceof Table table) {

          try {
            // register
            var dbNoteConnectorTable = new DbNoteConnectorTable();
            dbNoteConnectorTable.noteId = ctxNote.dbNote.noteId;
            dbNoteConnectorTable.tableId = table.getCtxTable().dbTable.tableId;

            Bucket.getInstance().getBucketConnector().registerNoteToTable(dbNoteConnectorTable);

            // connector
            new ErConnector(getWorkspace(), note, table, LineStyle.SIMPLE);

          } catch (Exception ex) {
            Log.error(ex);
            JOptionPane.showMessageDialog(workspace, ex.getMessage());
          }
        } else if (target instanceof Sequence sequence) {

          try {
            // register
            var dbNoteConnectorSequence = new DbNoteConnectorSequence();
            dbNoteConnectorSequence.noteId = ctxNote.dbNote.noteId;
            dbNoteConnectorSequence.sequenceId = sequence.getCtxSequence().dbSequence.sequenceId;
            Bucket.getInstance().getBucketConnector()
                .registerNoteToSequence(dbNoteConnectorSequence);

            // connector
            new ErConnector(getWorkspace(), note, sequence, LineStyle.SIMPLE);

          } catch (Exception ex) {
            Log.error(ex);
            JOptionPane.showMessageDialog(workspace, ex.getMessage());
          }
        }
      }
    });
    add(panelSide);

    // subject
    panelSubject = new JPanel(new FlowLayout(FlowLayout.LEFT));
    panelSubject.setLocation(new Point(panelSide.getWidth() + BORDER_SIZE, BORDER_SIZE));
    panelSubject.setSize(new Dimension(getWidth() - BORDER_SIZE * 2 - panelSide.getWidth(), 36));
    panelSubject.setBackground(new Color(ctxNote.dbNoteOption.color));
    panelSubject.addMouseListener(new MouseAdapter() {

      @Override
      public void mousePressed(MouseEvent e) {
        // focus
        note.requestFocusInWindow();

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
        panelSubject.setCursor(cursor);

        // moving
        // plus side width
        if (getWorkspace().getSelectingRange().isSelecting(note)) {
          getWorkspace().getSelectingRange()
              .movingStart(note, new Point(e.getX() + panelSide.getWidth(), e.getY()));
        } else {
          getWorkspace().getSelectingRange().clearAllMovers();
          movingStart(new Point(e.getX() + panelSide.getWidth(), e.getY()));
        }
      }

      @Override
      public void mouseReleased(MouseEvent e) {
        // focus
        note.requestFocusInWindow();

        // menu
        if (e.isPopupTrigger() || SwingUtilities.isRightMouseButton(e)) {
          doPop(e);
          return;
        }

        // selecting
        if (e.isShiftDown()) {
          if (getWorkspace().getSelectingRange().setOrUnsetMover(note)) {
            for (var c : getConnectors()) {
              c.zRestore(note);
            }
          } else {
            for (var c : getConnectors()) {
              c.zFirst(note, ErConnectorColor.SELECTED_COLOR);
            }
          }
          return;
        }

        // moving
        if (getWorkspace().getSelectingRange().isSelecting(note)) {
          getWorkspace().getSelectingRange().movingEnd();
        } else {
          movingEnd();
          getWorkspace().getSelectingRange().clearAllMovers();
        }

        // reverse cursor
        var cursor = Cursor.getDefaultCursor();
        panelSubject.setCursor(cursor);
      }

      private void doPop(MouseEvent e) {
        showPopupMenu(e);
      }
    });
    add(panelSubject);
    textFieldSubject = new JTextField() {
      @Override
      protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // search
        var searchText = workspace.getSearchText();
        if (Utils.isNullOrEmpty(searchText)) {
          return;
        }
        var targetText = textFieldSubject.getText();
        var startPos = targetText.indexOf(searchText);
        if (startPos < 0) {
          return;
        }
        var endPos = startPos + searchText.length();

        var metrics = textFieldSubject.getFontMetrics(textFieldSubject.getFont());
        String start = targetText.substring(0, startPos);
        String text = targetText.substring(startPos, endPos);
        int startX = metrics.stringWidth(start);
        int startY = 0;
        int width = metrics.stringWidth(text);
        int height = metrics.getHeight();

        g.setColor(new Color(255, 255, 0, 100));
        g.fillRect(startX, startY, width, height);
      }
    };
    textFieldSubject.addFocusListener(new FocusAdapter() {

      @Override
      public void focusGained(FocusEvent e) {
        zFirst(ErConnectorColor.FOCUSED_BORDER);
        ((JTextComponent) e.getComponent()).selectAll();
      }

      @Override
      public void focusLost(FocusEvent e) {
        zRestore();

        try {
          var subject = Utils.getString(textFieldSubject);
          if (subject.equals(ctxNote.dbNote.subject)) {
            return;
          }
          if (subject.getBytes().length > 100) {
            throw new Exception("Subject over 100 length.");
          }

          // save
          ctxNote.dbNote.subject = subject;
          Bucket.getInstance().getBucketNote().save(ctxNote);

          getWorkspace().reloadNote();

        } catch (Exception ex) {
          Log.error(ex);
          JOptionPane.showMessageDialog(note, ex.getMessage());
        }
      }
    });
    textFieldSubject.setSize(
        new Dimension(panelSubject.getWidth() - 20, textFieldSubject.getHeight()));
    textFieldSubject.setColumns(
        panelSubject.getWidth() / (textFieldSubject.getFont().getSize() + 1));
    textFieldSubject.setBorder(BorderFactory.createEmptyBorder());
    panelSubject.add(textFieldSubject);

    // body
    panelBody = new JPanel(new FlowLayout(FlowLayout.LEFT));
    panelBody.setLocation(
        new Point(panelSide.getWidth() + BORDER_SIZE, panelSubject.getHeight() + BORDER_SIZE));
    panelBody.setSize(new Dimension(getWidth() - BORDER_SIZE * 2 - panelSide.getWidth(),
        getHeight() - BORDER_SIZE * 2 - panelSubject.getHeight() - 10));
    panelBody.setBackground(Color.WHITE);
    panelBody.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseReleased(MouseEvent e) {
        // focus
        note.requestFocusInWindow();
      }
    });
    add(panelBody);
    textAreaBody = new JTextArea() {
      @Override
      protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // search
        var searchText = workspace.getSearchText();
        if (Utils.isNullOrEmpty(searchText)) {
          return;
        }
        var targetTextList = textAreaBody.getText().split("\n");
        for (int i = 0; i < targetTextList.length; i++) {
          var targetText = targetTextList[i];
          var startPos = targetText.indexOf(searchText);
          if (startPos < 0) {
            continue;
          }
          var endPos = startPos + searchText.length();

          var metrics = textAreaBody.getFontMetrics(textAreaBody.getFont());
          String start = targetText.substring(0, startPos);
          String text = targetText.substring(startPos, endPos);
          int startX = metrics.stringWidth(start);
          int startY = i * 17;
          int width = metrics.stringWidth(text);
          int height = metrics.getHeight();

          g.setColor(new Color(255, 255, 0, 100));
          g.fillRect(startX, startY, width, height);
        }
      }
    };
    textAreaBody.addFocusListener(new FocusAdapter() {

      @Override
      public void focusGained(FocusEvent e) {
        zFirst(ErConnectorColor.FOCUSED_BORDER);
      }

      @Override
      public void focusLost(FocusEvent e) {
        zRestore();

        try {
          var body = Utils.getString(textAreaBody);
          if (body.equals(ctxNote.dbNote.body)) {
            return;
          }
          if (body.getBytes().length > 1000) {
            throw new Exception("Body over 1000 length.");
          }

          // save
          ctxNote.dbNote.body = body;
          Bucket.getInstance().getBucketNote().save(ctxNote);

          // reload
          getWorkspace().reloadNote();

        } catch (Exception ex) {
          Log.error(ex);

          JOptionPane.showMessageDialog(note, ex.getMessage());
        }
      }
    });
    textAreaBody.setWrapStyleWord(true);
    textAreaBody.setLineWrap(true);
    textAreaBody.setSize(new Dimension(panelBody.getWidth() - 20, panelBody.getHeight() - 20));
    textAreaBody.setColumns(textAreaBody.getWidth() / (textAreaBody.getFont().getSize() + 1));
    if (textAreaBody.getColumns() > 20) {
      textAreaBody.setColumns(20);
    }
    textAreaBody.setRows(textAreaBody.getHeight() / (textAreaBody.getFont().getSize() + 4));
    if (textAreaBody.getRows() > 15) {
      textAreaBody.setRows(15);
    }
    textAreaBody.setBorder(BorderFactory.createEmptyBorder());
    panelBody.add(textAreaBody);

    // panel footer
    panelFooter = new JPanel(new FlowLayout(FlowLayout.LEFT)) {
      @Override
      protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        var g2 = (Graphics2D) g;

        var x1 = getWidth();
        var y1 = getHeight() - BORDER_SIZE * 2;
        var x2 = getWidth() - BORDER_SIZE * 2;
        var y2 = getHeight();

        g2.setColor(new Color(96, 96, 96));
        g2.setStroke(new BasicStroke(10));
        g2.drawLine(x1, y1, x2, y2);
      }
    };
    panelFooter.setLocation(new Point(panelSide.getWidth() + BORDER_SIZE, getHeight() - 12));
    panelFooter.setSize(new Dimension(getWidth() - BORDER_SIZE * 2 - panelSide.getWidth(), 10));
    panelFooter.setBackground(Color.WHITE);
    panelFooter.addMouseListener(new MouseAdapter() {

      @Override
      public void mousePressed(MouseEvent e) {
        // resize
        var rangeStartX = panelFooter.getWidth() - 10;
        var rangeEndX = panelFooter.getWidth();
        var rangeStartY = panelFooter.getHeight() - 10;
        var rangeEndY = panelFooter.getHeight();
        var x = e.getX();
        var y = e.getY();
        Log.trace(String.format("range %s-%s, %s-%s %s:%s", rangeStartX, rangeEndX, rangeStartY,
            rangeEndY, x, y));
        if (x >= rangeStartX && x <= rangeEndX && y >= rangeStartY && y <= rangeEndY) {
          // change cursor
          var cursor = Cursor.getPredefinedCursor(Cursor.NW_RESIZE_CURSOR);
          note.setCursor(cursor);

          // resizing start
          resizing = true;
          resizingTimer.start();
        }
      }

      @Override
      public void mouseReleased(MouseEvent e) {
        // focus
        note.requestFocusInWindow();

        // resize
        if (resizing) {
          // resizing end
          resizingTimer.stop();
          resizing = false;

          // reverse cursor
          var cursor = Cursor.getDefaultCursor();
          note.setCursor(cursor);
        }
      }
    });
    add(panelFooter);

    // resizing
    ActionListener movingListener = actionEvent -> {
      if (!resizing) {
        return;
      }

      // https://ateraimemo.com/Swing/MouseInfo.html
      var pi = MouseInfo.getPointerInfo();
      Point pt = pi.getLocation();
      SwingUtilities.convertPointFromScreen(pt, workspace);

      var w = Utils.floorDegree(pt.getX() - note.getX() + 12, rangeUnit);
      var h = Utils.floorDegree(pt.getY() - note.getY() + 12, rangeUnit);
//      Log.trace(String.format("w:h %s:%s", w, h));
      if (w < MIN_WIDTH) {
        w = MIN_WIDTH;
      }
      if (w > MAX_WIDTH) {
        w = MAX_WIDTH;
      }
      if (h < MIN_HEIGHT) {
        h = MIN_HEIGHT;
      }
      if (h > MAX_HEIGHT) {
        h = MAX_HEIGHT;
      }
      if (Math.abs(note.getWidth() - w) < rangeUnit && Math.abs(note.getHeight() - h) < rangeUnit) {
        return;
      }

      panelSide.setSize(new Dimension(panelSide.getWidth(), h - BORDER_SIZE * 2));
      panelSubject.setSize(
          new Dimension(w - BORDER_SIZE * 2 - panelSide.getWidth(), panelSubject.getHeight()));
      panelBody.setSize(new Dimension(w - BORDER_SIZE * 2 - panelSide.getWidth(),
          h - BORDER_SIZE * 2 - panelSubject.getHeight() - panelFooter.getHeight()));
      panelFooter.setLocation(new Point(panelSide.getWidth() + BORDER_SIZE, h - 12));
      panelFooter.setSize(
          new Dimension(w - BORDER_SIZE * 2 - panelSide.getWidth(), panelFooter.getHeight()));
      note.setSize(new Dimension(w, h));

      // selecting
      resizeSelectingPanel();

      // subject
      textFieldSubject.setSize(
          new Dimension(panelSubject.getWidth() - 20, textFieldSubject.getHeight()));
      textFieldSubject.setColumns(
          panelSubject.getWidth() / (textFieldSubject.getFont().getSize() + 1));

      // body
      textAreaBody.setSize(
          new Dimension(panelBody.getWidth() - 20, panelBody.getHeight() - 20));
      textAreaBody.setColumns(textAreaBody.getWidth() / (textAreaBody.getFont().getSize() + 1));
      if (textAreaBody.getColumns() > 20) {
        textAreaBody.setColumns(20);
      }
      textAreaBody.setRows(textAreaBody.getHeight() / (textAreaBody.getFont().getSize() + 4));
      if (textAreaBody.getRows() > 15) {
        textAreaBody.setRows(15);
      }

      try {
        // save
        ctxNote.dbNoteOption.width = note.getWidth();
        ctxNote.dbNoteOption.height = note.getHeight();
        Bucket.getInstance().getBucketNote().saveOption(ctxNote);

        // redraw connector
        redrawAllConnectors();
      } catch (Exception ex) {
        Log.error(ex);
        JOptionPane.showMessageDialog(workspace, ex.getMessage());
      }
    };
    resizingTimer = new Timer(50, movingListener);
    resizingTimer.setRepeats(true);

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
   * get ctx note.
   *
   * @return ctx note
   */
  public CtxNote getCtxNote() {
    return ctxNote;
  }

  /**
   * redraw.
   */
  public void redraw() {
    textFieldSubject.setText(ctxNote.dbNote.subject);
    textAreaBody.setText(ctxNote.dbNote.body);
  }

  @Override
  public void connectComplete(ErConnector connector, ErConnectorEndpoint otherEndpoint) {
    super.connectComplete(connector, otherEndpoint);

    if (otherEndpoint == null) {
      return;
    }
    if (otherEndpoint instanceof Table) {
      getWorkspace().addConnectorFromNoteToTable(connector);
    } else if (otherEndpoint instanceof Sequence) {
      getWorkspace().addConnectorFromNoteToSequence(connector);
    }
  }

  @Override
  public void disconnectComplete(ErConnector connector, ErConnectorEndpoint otherEndpoint) {
    super.disconnectComplete(connector, otherEndpoint);

    if (otherEndpoint == null) {
      return;
    }
    if (otherEndpoint instanceof Table table) {
      getWorkspace().removeConnectorFromNoteToTable(connector);

      try {
        // remove
        var opt = Bucket.getInstance().getBucketConnector().dbNoteConnectorTableList.stream()
            .filter(c -> c.noteId.equals(ctxNote.dbNote.noteId) && c.tableId.equals(
                table.getCtxTable().dbTable.tableId))
            .findFirst();
        if (opt.isPresent()) {
          var dbNoteConnectorTable = opt.get();
          Bucket.getInstance().getBucketConnector().removeNoteToTable(dbNoteConnectorTable);
        }
      } catch (Exception e) {
        Log.error(e);
        JOptionPane.showMessageDialog(workspace.getRoot(), e.getMessage());
      }
    } else if (otherEndpoint instanceof Sequence sequence) {
      getWorkspace().removeConnectorFromNoteToSequence(connector);

      try {
        // remove
        var opt = Bucket.getInstance().getBucketConnector().dbNoteConnectorSequenceList.stream()
            .filter(c -> c.noteId.equals(ctxNote.dbNote.noteId) && c.sequenceId.equals(
                sequence.getCtxSequence().dbSequence.sequenceId))
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
      ctxNote.dbNoteOption.posX = x;
      ctxNote.dbNoteOption.posY = y;
      Bucket.getInstance().getBucketNote().saveOption(ctxNote);

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
  public String getNameForSort() {
    return String.format("%s-%s", this, ctxNote.dbNote.noteId);
  }

  @Override
  public void showPopupMenu(MouseEvent mouseEvent) {
    var menu = new PopupMenu(this);
    menu.show(mouseEvent.getComponent(), mouseEvent.getX(), mouseEvent.getY());
  }

  @Override
  public void focusGainedComplete() {
    workspace.notifySelectionNote(this);
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);

    // search
    var searchText = workspace.getSearchText();
    if (Utils.isNullOrEmpty(searchText)) {
      return;
    }
    var targetText = textFieldSubject.getText();
    var startPos = targetText.indexOf(searchText);
    if (startPos >= 0) {
      return;
    }
    targetText = textAreaBody.getText();
    startPos = targetText.indexOf(searchText);
    if (startPos >= 0) {
      return;
    }

    var g2 = (Graphics2D) g;
    AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.1f);
    g2.setComposite(ac);
  }

  @Override
  public String toString() {
    var searchText = workspace.getSearchText();
    if (Utils.isNullOrEmpty(searchText)) {
      return ctxNote.dbNote.subject;
    }

    var targetText = textFieldSubject.getText();
    var startPos = targetText.indexOf(searchText);
    if (startPos >= 0) {
      return ctxNote.dbNote.subject;
    }
    targetText = textAreaBody.getText();
    startPos = targetText.indexOf(searchText);
    if (startPos >= 0) {
      return ctxNote.dbNote.subject;
    }

    return String.format("<html><font color=#dcdcdc>%s</font></html>",
        ctxNote.dbNote.subject);
  }

  /**
   * PopupMenu.
   */
  public class PopupMenu extends JPopupMenu {

    /**
     * Constructor.
     *
     * @param note note
     */
    public PopupMenu(Note note) {
      addPopupMenuListener(new PopupMenuListener() {
        @Override
        public void popupMenuWillBecomeVisible(PopupMenuEvent popupMenuEvent) {
          note.requestFocusInWindow();
        }

        @Override
        public void popupMenuWillBecomeInvisible(PopupMenuEvent popupMenuEvent) {
          note.requestFocusInWindow();
        }

        @Override
        public void popupMenuCanceled(PopupMenuEvent popupMenuEvent) {
          note.requestFocusInWindow();
        }
      });

      // color
      var menuItemColor = new JMenuItem("Choose note color");
      menuItemColor.addActionListener(actionEvent -> {
        var color = ErColorChooser.showDialog(new Color(ctxNote.dbNoteOption.color));
        ctxNote.dbNoteOption.color = color.getRGB();

        try {
          // save
          Bucket.getInstance().getBucketNote().saveOption(ctxNote);
          panelSubject.setBackground(color);
        } catch (Exception e) {
          Log.error(e);
          JOptionPane.showMessageDialog(workspace.getRoot(), e.getMessage());
        }
      });
      add(menuItemColor);

      // copy
      var menuItemCopy = new JMenuItem("Copy note");
      menuItemCopy.addActionListener(actionEvent -> {
        workspace.getRoot().getMain().setCopied(ctxNote, CtxNote.class);
      });
      addSeparator();
      add(menuItemCopy);

      // remove
      var menuItemRemove = new JMenuItem("Remove note");
      menuItemRemove.addActionListener(actionEvent -> {
        // confirm
        var subject = ctxNote.dbNote.subject;
        if (!subject.startsWith(Const.NEW_NOTE_PREFIX)
            && JOptionPane.showConfirmDialog(note, String.format("Remove note '%s' ?", subject))
            != 0) {
          return;
        }

        try {
          // remove
          Bucket.getInstance().getBucketNote().remove(ctxNote);
          note.getWorkspace().removeNote(note);
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
