package com.walksocket.er.component.main.root.wokspace;

import com.walksocket.er.Const;
import com.walksocket.er.Date;
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
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
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
import javax.swing.text.JTextComponent;

/**
 * Note.
 */
public class Note extends ErConnectorEndpoint implements ErConnectorEndpointOrigin {

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
  private final JTextField textFieldSubject = new JTextField();

  /**
   * text area body.
   */
  private final JTextArea textAreaBody = new JTextArea();

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

    // side
    panelSide = new JPanel(new FlowLayout(FlowLayout.LEFT));
    panelSide.setLocation(new Point(2, 2));
    panelSide.setSize(new Dimension(10, ctxNote.dbNoteOption.height - 4));
    panelSide.setBackground(new Color(96, 96, 96));
    panelSide.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseReleased(MouseEvent e) {
        // focus
        note.requestFocusInWindow();

        // connector
        var x = note.getX() + e.getX() + 2;
        var y = note.getY() + e.getY() + 2;
        var target = getWorkspace().getComponentAt(x, y);
        if (target == null) {
          return;
        }
        Log.trace("connected from note:" + target.getClass().getName());
        if (target instanceof Table) {
          var table = (Table) target;

          try {
            // register
            var dbNoteConnectorTable = new DbNoteConnectorTable();
            dbNoteConnectorTable.noteId = ctxNote.dbNote.noteId;
            dbNoteConnectorTable.tableId = table.getCtxTable().dbTable.tableId;

            Bucket.getInstance().getBucketConnector().registerNoteToTable(dbNoteConnectorTable);

            // connector
            new ErConnector(getWorkspace(), note, table, LineStyle.SIMPLE);

          } catch (Exception ex) {
            Log.error(e);
            JOptionPane.showMessageDialog(workspace, ex.getMessage());
          }
        } else if (target instanceof Sequence) {
          var sequence = (Sequence) target;

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
            Log.error(e);
            JOptionPane.showMessageDialog(workspace, ex.getMessage());
          }
        }
      }
    });
    add(panelSide);

    // subject
    panelSubject = new JPanel(new FlowLayout(FlowLayout.LEFT));
    panelSubject.setLocation(new Point(panelSide.getWidth() + 2, 2));
    panelSubject.setSize(new Dimension(getWidth() - 4 - panelSide.getWidth(), 36));
    panelSubject.setBackground(new Color(ctxNote.dbNoteOption.color));
    panelSubject.addMouseListener(new MouseAdapter() {

      /**
       * pressed.
       */
      private long pressed;

      /**
       * pressed x.
       */
      private int pressedX;

      /**
       * pressed y.
       */
      private int pressedY;

      @Override
      public void mousePressed(MouseEvent e) {
        // pressed
        pressed = Date.timestampMillis();

        // focus
        note.requestFocusInWindow();

        // menu
        if (e.isPopupTrigger() || SwingUtilities.isRightMouseButton(e)) {
          doPop(e);
          return;
        }

        // change cursor
        var cursor = Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR);
        panelSubject.setCursor(cursor);
        pressedX = e.getX();
        pressedY = e.getY();
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

        // reverse cursor
        var cursor = Cursor.getDefaultCursor();
        panelSubject.setCursor(cursor);

        // move
        if (Date.timestampMillis() - pressed > 250) {
          var startX = note.getX();
          var startY = note.getY();
          var x = startX + e.getX() - pressedX;
          var y = startY + e.getY() - pressedY;
          if (x < 0) {
            x = 0;
          }
          if (y < 0) {
            y = 0;
          }
          if (x > 9999) {
            x = 9999;
          }
          if (y > 9999) {
            y = 9999;
          }

          try {
            // save
            ctxNote.dbNoteOption.posX = x;
            ctxNote.dbNoteOption.posY = y;
            Bucket.getInstance().getBucketNote().saveOption(ctxNote);

            // position
            note.setLocation(x, y);

            // redraw connector
            redrawAllConnectors();

            // focus
            note.requestFocusInWindow();

          } catch (Exception ex) {
            Log.error(e);
            JOptionPane.showMessageDialog(workspace, ex.getMessage());
          }
        }
      }

      private void doPop(MouseEvent e) {
        var menu = new PopupMenu(note, e.getX(), e.getY());
        menu.show(e.getComponent(), e.getX(), e.getY());
      }
    });
    add(panelSubject);
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
    panelBody.setLocation(new Point(panelSide.getWidth() + 2, panelSubject.getHeight() + 2));
    panelBody.setSize(new Dimension(getWidth() - 4 - panelSide.getWidth(),
        getHeight() - 4 - panelSubject.getHeight() - 10));
    panelBody.setBackground(Color.WHITE);
    panelBody.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseReleased(MouseEvent e) {
        // focus
        note.requestFocusInWindow();
      }
    });
    add(panelBody);
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
        var g2 = (Graphics2D) g;

        var x1 = getWidth();
        var y1 = getHeight() - 4;
        var x2 = getWidth() - 4;
        var y2 = getHeight();

        g2.setColor(new Color(96, 96, 96));
        g2.setStroke(new BasicStroke(10));
        g2.drawLine(x1, y1, x2, y2);
      }
    };
    panelFooter.setLocation(new Point(panelSide.getWidth() + 2, getHeight() - 12));
    panelFooter.setSize(new Dimension(getWidth() - 4 - panelSide.getWidth(), 10));
    panelFooter.setBackground(Color.WHITE);
    panelFooter.addMouseListener(new MouseAdapter() {

      /**
       * resizing.
       */
      private boolean resizing;

      @Override
      public void mousePressed(MouseEvent e) {
        // resize
        resizing = false;
        var rangeStartX = panelFooter.getWidth() - 10;
        var rangeEndX = panelFooter.getWidth();
        var rangeStartY = panelFooter.getHeight() - 10;
        var rangeEndY = panelFooter.getHeight();
        var x = e.getX();
        var y = e.getY();
        Log.trace(String.format("range %s-%s, %s-%s %s:%s", rangeStartX, rangeEndX, rangeStartY,
            rangeEndY, x, y));
        if (x >= rangeStartX && x <= rangeEndX && y >= rangeStartY && y <= rangeEndY) {
          resizing = true;
        }
      }

      @Override
      public void mouseReleased(MouseEvent e) {
        // focus
        note.requestFocusInWindow();

        // resize
        if (resizing) {
          var w = e.getX() + panelSide.getWidth();
          var h = e.getY() + panelSubject.getHeight() + panelBody.getHeight();
          if (w < 100) {
            w = 100;
          }
          if (w > 300) {
            w = 300;
          }
          if (h < 100) {
            h = 100;
          }
          if (h > 300) {
            h = 300;
          }
          panelSide.setSize(new Dimension(panelSide.getWidth(), h - 4));
          panelSubject.setSize(
              new Dimension(w - 4 - panelSide.getWidth(), panelSubject.getHeight()));
          panelBody.setSize(new Dimension(w - 4 - panelSide.getWidth(),
              h - 4 - panelSubject.getHeight() - panelFooter.getHeight()));
          panelFooter.setLocation(new Point(panelSide.getWidth() + 2, h - 12));
          panelFooter.setSize(new Dimension(w - 4 - panelSide.getWidth(), panelFooter.getHeight()));
          note.setSize(new Dimension(w, h));

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
            Log.error(e);
            JOptionPane.showMessageDialog(workspace, ex.getMessage());
          }
        }
      }
    });
    add(panelFooter);

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
    if (otherEndpoint instanceof Table) {
      getWorkspace().removeConnectorFromNoteToTable(connector);

      try {
        // remove
        var table = (Table) otherEndpoint;
        var dbNoteConnectorTable = new DbNoteConnectorTable();
        dbNoteConnectorTable.noteId = ctxNote.dbNote.noteId;
        dbNoteConnectorTable.tableId = table.getCtxTable().dbTable.tableId;

        Bucket.getInstance().getBucketConnector().removeNoteToTable(dbNoteConnectorTable);
      } catch (Exception e) {
        Log.error(e);
        JOptionPane.showMessageDialog(workspace, e.getMessage());
      }
    } else if (otherEndpoint instanceof Sequence) {
      getWorkspace().removeConnectorFromNoteToSequence(connector);

      try {
        // remove
        var sequence = (Sequence) otherEndpoint;
        var dbNoteConnectorSequence = new DbNoteConnectorSequence();
        dbNoteConnectorSequence.noteId = ctxNote.dbNote.noteId;
        dbNoteConnectorSequence.sequenceId = sequence.getCtxSequence().dbSequence.sequenceId;

        Bucket.getInstance().getBucketConnector().removeNoteToSequence(dbNoteConnectorSequence);
      } catch (Exception e) {
        Log.error(e);
        JOptionPane.showMessageDialog(workspace, e.getMessage());
      }
    }
  }

  @Override
  public String getNameForSort() {
    return toString();
  }

  @Override
  public String toString() {
    return ctxNote.dbNote.subject;
  }

  /**
   * PopupMenu.
   */
  public class PopupMenu extends JPopupMenu {

    /**
     * Constructor.
     *
     * @param note note
     * @param x    x
     * @param y    y
     */
    public PopupMenu(Note note, int x, int y) {
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
          JOptionPane.showMessageDialog(workspace, e.getMessage());
        }
      });
      add(menuItemColor);

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
          JOptionPane.showMessageDialog(workspace, e.getMessage());
        }
      });
      addSeparator();
      add(menuItemRemove);
    }
  }
}
