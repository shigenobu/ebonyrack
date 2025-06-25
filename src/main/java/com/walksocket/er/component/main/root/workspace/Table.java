package com.walksocket.er.component.main.root.workspace;

import com.walksocket.er.App;
import com.walksocket.er.Const;
import com.walksocket.er.Log;
import com.walksocket.er.Utils;
import com.walksocket.er.component.InputTable;
import com.walksocket.er.component.ShowDdl;
import com.walksocket.er.component.ShowTableClass;
import com.walksocket.er.component.main.root.Workspace;
import com.walksocket.er.custom.ErColorChooser;
import com.walksocket.er.custom.ErConnector;
import com.walksocket.er.custom.ErConnectorColor;
import com.walksocket.er.custom.ErConnectorEndpoint;
import com.walksocket.er.custom.ErConnectorEndpointRelation;
import com.walksocket.er.definition.AutoIncrement;
import com.walksocket.er.definition.DataType;
import com.walksocket.er.definition.NotNull;
import com.walksocket.er.sqlite.Bucket;
import com.walksocket.er.sqlite.context.CtxTable;
import com.walksocket.er.sqlite.entity.DbDictColumn;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;

/**
 * Table.
 */
public class Table extends ErConnectorEndpoint implements ErConnectorEndpointRelation {

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
  private static final int MAX_WIDTH = 500;

  /**
   * border size.
   */
  private static final int BORDER_SIZE = 2;

  /**
   * image key primary.
   */
  private static BufferedImage imageKeyPrimary;

  static {
    try (var stream = App.class.getClassLoader().getResourceAsStream("image/key_primary.png")) {
      imageKeyPrimary = ImageIO.read(stream);
    } catch (IOException e) {
      Log.error(e);
    }
  }

  /**
   * image key primary auto increment.
   */
  private static BufferedImage imageKeyPrimaryAi;

  static {
    try (var stream = App.class.getClassLoader().getResourceAsStream("image/key_primary_ai.png")) {
      imageKeyPrimaryAi = ImageIO.read(stream);
    } catch (IOException e) {
      Log.error(e);
    }
  }

  /**
   * image key foreign.
   */
  private static BufferedImage imageKeyForeign;

  static {
    try (var stream = App.class.getClassLoader().getResourceAsStream("image/key_foreign.png")) {
      imageKeyForeign = ImageIO.read(stream);
    } catch (IOException e) {
      Log.error(e);
    }
  }

  /**
   * image key unique.
   */
  private static BufferedImage imageKeyUnique;

  static {
    try (var stream = App.class.getClassLoader().getResourceAsStream("image/key_unique.png")) {
      imageKeyUnique = ImageIO.read(stream);
    } catch (IOException e) {
      Log.error(e);
    }
  }

  /**
   * image key.
   */
  private static BufferedImage imageKey;

  static {
    try (var stream = App.class.getClassLoader().getResourceAsStream("image/key.png")) {
      imageKey = ImageIO.read(stream);
    } catch (IOException e) {
      Log.error(e);
    }
  }

  /**
   * image type number.
   */
  private static BufferedImage imageTypeNumber;

  static {
    try (var stream = App.class.getClassLoader().getResourceAsStream("image/type_number.png")) {
      imageTypeNumber = ImageIO.read(stream);
    } catch (IOException e) {
      Log.error(e);
    }
  }

  /**
   * image type text.
   */
  private static BufferedImage imageTypeText;

  static {
    try (var stream = App.class.getClassLoader().getResourceAsStream("image/type_text.png")) {
      imageTypeText = ImageIO.read(stream);
    } catch (IOException e) {
      Log.error(e);
    }
  }

  /**
   * image type date.
   */
  private static BufferedImage imageTypeDate;

  static {
    try (var stream = App.class.getClassLoader().getResourceAsStream("image/type_date.png")) {
      imageTypeDate = ImageIO.read(stream);
    } catch (IOException e) {
      Log.error(e);
    }
  }

  /**
   * image type binary.
   */
  private static BufferedImage imageTypeBinary;

  static {
    try (var stream = App.class.getClassLoader().getResourceAsStream("image/type_binary.png")) {
      imageTypeBinary = ImageIO.read(stream);
    } catch (IOException e) {
      Log.error(e);
    }
  }

  /**
   * image type other.
   */
  private static BufferedImage imageTypeOther;

  static {
    try (var stream = App.class.getClassLoader().getResourceAsStream("image/type_other.png")) {
      imageTypeOther = ImageIO.read(stream);
    } catch (IOException e) {
      Log.error(e);
    }
  }

  /**
   * workspace.
   */
  private final Workspace workspace;

  /**
   * ctxTable.
   */
  private final CtxTable ctxTable;

  /**
   * panel table.
   */
  private final JPanel panelTable;

  /**
   * panel column.
   */
  private final JPanel panelColumn;

  /**
   * panel column inner.
   */
  private final JPanel panelColumnInner;

  /**
   * label table name.
   */
  private final JLabel labelTableName = new JLabel();

  /**
   * Constructor.
   *
   * @param workspace workspace
   * @param ctxTable  ctxTable
   */
  public Table(Workspace workspace, CtxTable ctxTable) {
    super(workspace);
    this.workspace = workspace;
    this.ctxTable = ctxTable;

    // layout
    setLayout(null);

    // color
    setBackground(Color.WHITE);

    // init
    setLocation(new Point(ctxTable.dbTableOption.posX, ctxTable.dbTableOption.posY));
    setSize(new Dimension(MIN_WIDTH, 100));
    var table = this;

    // key event
    addKeyListener(new KeyAdapter() {
      @Override
      public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_C && e.isControlDown()) {
          // copy
          workspace.getRoot().getMain().setCopied(ctxTable, CtxTable.class);
        } else if (e.getKeyCode() == KeyEvent.VK_F && e.isControlDown()) {
          // show search
          workspace.showSearchSpace();
        } else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
          // hide search
          workspace.hideSearchSpace();
        } else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
          // edit
          var inputTable = new InputTable(table);
          inputTable.setModal(true);
          inputTable.setVisible(true);
        }
      }
    });

    // table
    panelTable = new JPanel(new FlowLayout(FlowLayout.CENTER)) {
      @Override
      protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // search
        var searchText = workspace.getSearchText();
        if (Utils.isNullOrEmpty(searchText)) {
          return;
        }
        var targetText = labelTableName.getText();
        var startPos = targetText.indexOf(searchText);
        if (startPos < 0) {
          return;
        }
        var endPos = startPos + searchText.length();

        var metrics = labelTableName.getFontMetrics(labelTableName.getFont());
        String start = targetText.substring(0, startPos);
        String text = targetText.substring(startPos, endPos);
        int startX = 5 + metrics.stringWidth(start);
        int startY = 10;
        int width = metrics.stringWidth(text);
        int height = metrics.getHeight();

        g.setColor(ErConnectorColor.FOUND_COLOR);
        g.fillRect(startX, startY, width, height);
        workspace.addSearchHit(table);
      }
    };
    panelTable.setLocation(new Point(BORDER_SIZE, BORDER_SIZE));
    panelTable.setSize(new Dimension(getWidth() - BORDER_SIZE * 2, 36));
    panelTable.setBackground(new Color(ctxTable.dbTableOption.color));
    panelTable.addMouseListener(new MouseAdapter() {

      @Override
      public void mouseClicked(MouseEvent e) {
        if (e.isPopupTrigger() || SwingUtilities.isRightMouseButton(e)) {
          return;
        }

        // edit
        if (e.getClickCount() >= 2) {
          var inputTable = new InputTable(table);
          inputTable.setModal(true);
          inputTable.setVisible(true);
        }
      }

      @Override
      public void mousePressed(MouseEvent e) {
        // focus
        table.requestFocusInWindow();

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
        panelTable.setCursor(cursor);

        // moving
        if (getWorkspace().getSelectingRange().isSelecting(table)) {
          getWorkspace().getSelectingRange().movingStart(table, e.getPoint());
        } else {
          getWorkspace().getSelectingRange().clearAllMovers();
          movingStart(e.getPoint());
        }
      }

      @Override
      public void mouseReleased(MouseEvent e) {
        // focus
        table.requestFocusInWindow();

        // menu
        if (e.isPopupTrigger() || SwingUtilities.isRightMouseButton(e)) {
          doPop(e);
          return;
        }

        // selecting
        if (e.isShiftDown()) {
          if (getWorkspace().getSelectingRange().setOrUnsetMover(table)) {
            for (var c : getConnectors()) {
              c.zRestore(table);
            }
          } else {
            for (var c : getConnectors()) {
              c.zFirst(table, ErConnectorColor.SELECTED_COLOR);
            }
          }
          return;
        }

        // moving
        if (getWorkspace().getSelectingRange().isSelecting(table)) {
          getWorkspace().getSelectingRange().movingEnd();
        } else {
          movingEnd();
          getWorkspace().getSelectingRange().clearAllMovers();
        }

        // reverse cursor
        var cursor = Cursor.getDefaultCursor();
        panelTable.setCursor(cursor);
      }

      private void doPop(MouseEvent e) {
        showPopupMenu(e);
      }
    });
    add(panelTable);
    panelTable.add(labelTableName);

    // column
    panelColumn = new JPanel();
    panelColumn.setLocation(new Point(BORDER_SIZE, panelTable.getHeight() + BORDER_SIZE));
    panelColumn.setSize(new Dimension(getWidth() - BORDER_SIZE * 2,
        getHeight() - panelColumn.getHeight() - BORDER_SIZE * 2));
    panelColumn.setBackground(Color.WHITE);
    panelColumn.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseReleased(MouseEvent e) {
        // focus
        table.requestFocusInWindow();
      }
    });
    add(panelColumn);

    // column inner
    panelColumnInner = new JPanel();
    panelColumnInner.setLayout(new BoxLayout(panelColumnInner, BoxLayout.Y_AXIS));
    panelColumnInner.setBackground(Color.WHITE);
    panelColumn.add(panelColumnInner);

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
   * get ctx table.
   *
   * @return ctx table
   */
  public CtxTable getCtxTable() {
    return ctxTable;
  }

  /**
   * get show column name list.
   *
   * @return show column name list
   */
  public List<String> getShowColumnNameList() {
    var showColumnNameList = new ArrayList<String>();
    var dbDictColumnList = Bucket.getInstance().getBucketDict().dbDictColumnList;
    for (var dbTableColumn : ctxTable.dbTableColumnList) {
      var dbDictColumn = dbDictColumnList.stream()
          .filter(d -> d.dictColumnId.equals(dbTableColumn.dictColumnId))
          .findFirst().get();
      showColumnNameList.add(dbDictColumn.getShowColumnName());
    }
    return showColumnNameList;
  }

  /**
   * redraw.
   */
  public void redraw() {
    var table = this;

    labelTableName.setText("(T) " + ctxTable.dbTable.getShowTableName());
    var w = labelTableName.getFontMetrics(labelTableName.getFont())
        .stringWidth(labelTableName.getText());

    var dbDictColumnTypeList = Bucket.getInstance().getBucketDict().dbDictColumnTypeList;
    var dbDictColumnList = Bucket.getInstance().getBucketDict().dbDictColumnList;
    var dbDictGroupColumnList = Bucket.getInstance().getBucketDict().dbDictGroupColumnList;

    var targetDbDictColumList = new ArrayList<DbDictColumn>();
    if (ctxTable.dbTableColumnList.size() > 0) {
      for (var dbTableColumn : ctxTable.dbTableColumnList.stream()
          .sorted(Comparator.comparing(t -> t.ordinalPosition)).collect(Collectors.toList())) {
        var dbDictColumn = dbDictColumnList.stream()
            .filter(d -> d.dictColumnId.equals(dbTableColumn.dictColumnId)).findFirst().get();
        targetDbDictColumList.add(dbDictColumn);
        var tmpW = labelTableName.getFontMetrics(labelTableName.getFont())
            .stringWidth(dbDictColumn.getShowColumnName()) + 150;
        if (tmpW > w) {
          w = tmpW;
        }
      }
    }
    int noGroupLen = targetDbDictColumList.size();
    if (ctxTable.dbTableGroup != null) {
      var dictGroupId = ctxTable.dbTableGroup.dictGroupId;
      for (var dbDictGroupColumn : dbDictGroupColumnList.stream()
          .filter(d -> d.dictGroupId.equals(dictGroupId))
          .sorted(Comparator.comparing(t -> t.seq))
          .collect(Collectors.toList())) {
        var dbDictColumn = dbDictColumnList.stream()
            .filter(d -> d.dictColumnId.equals(dbDictGroupColumn.dictColumnId)).findFirst().get();
        targetDbDictColumList.add(dbDictColumn);
        var tmpW = labelTableName.getFontMetrics(labelTableName.getFont())
            .stringWidth(dbDictColumn.getShowColumnName()) + 150;
        if (tmpW > w) {
          w = tmpW;
        }
      }
    }
    if (w < MIN_WIDTH) {
      w = MIN_WIDTH;
    }
    if (w > MAX_WIDTH) {
      w = MAX_WIDTH;
    }

    panelTable.setSize(new Dimension(w - BORDER_SIZE * 2, panelTable.getHeight()));
    panelTable.setToolTipText(ctxTable.dbTable.getTipText());
    labelTableName.setPreferredSize(
        new Dimension(w - BORDER_SIZE * 2 - 10, panelTable.getHeight() - 10));

    var ph = 22;
    panelColumnInner.removeAll();
    if (targetDbDictColumList.size() > 0) {
      int idx = 0;
      for (var dbDictColumn : targetDbDictColumList) {
        var dbDictColumnType = dbDictColumnTypeList.stream()
            .filter(d -> d.dictColumnTypeId.equals(dbDictColumn.dictColumnTypeId))
            .findFirst()
            .get();

        var p = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        p.setName(dbDictColumn.dictColumnId);
        p.setPreferredSize(new Dimension(w - 10, ph));
        p.setBackground(Color.WHITE);
        p.setToolTipText(dbDictColumn.getTipText(dbDictColumnType));
        p.addMouseListener(new MouseAdapter() {
          @Override
          public void mousePressed(MouseEvent e) {
            // focus
            table.requestFocusInWindow();
          }

          @Override
          public void mouseReleased(MouseEvent e) {
            // focus
            table.requestFocusInWindow();
          }
        });
        panelColumnInner.add(p);

        // pk, uk, k
        var p1 = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0)) {
          @Override
          protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (ctxTable.ctxInnerPrimaryKey.dbTablePrimaryKeyColumnList.stream()
                .filter(c -> c.dictColumnId.equals(dbDictColumn.dictColumnId))
                .findFirst()
                .isPresent()) {
              Graphics2D g2 = (Graphics2D) g;
              if (dbDictColumn.autoIncrementDefinition.equals(AutoIncrement.AUTO_INCREMENT_VALUE)) {
                g2.drawImage(imageKeyPrimaryAi, 0, 4, 12, 12, null);
              } else {
                g2.drawImage(imageKeyPrimary, 0, 4, 12, 12, null);
              }
            }
            if (ctxTable.ctxInnerUniqueKeyList.stream()
                .filter(d -> d.dbTableUniqueKeyColumnList.stream()
                    .filter(c -> c.dictColumnId.equals(dbDictColumn.dictColumnId))
                    .findFirst()
                    .isPresent())
                .findFirst()
                .isPresent()) {
              Graphics2D g2 = (Graphics2D) g;
              g2.drawImage(imageKeyUnique, 12, 4, 12, 12, null);
            }
            if (ctxTable.ctxInnerKeyList.stream()
                .filter(d -> d.dbTableKeyColumnList.stream()
                    .filter(c -> c.dictColumnId.equals(dbDictColumn.dictColumnId))
                    .findFirst()
                    .isPresent())
                .findFirst()
                .isPresent()) {
              Graphics2D g2 = (Graphics2D) g;
              g2.drawImage(imageKey, 24, 4, 12, 12, null);
            }
          }
        };
        p1.setBackground(Color.WHITE);
        p1.setPreferredSize(new Dimension(40, ph));
        p.add(p1);

        // column
        var p2 = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        p2.setBackground(Color.WHITE);
        p2.setPreferredSize(new Dimension(w - 110, ph));
        p.add(p2);
        var showColumnName = dbDictColumn.getShowColumnName();
        var textFieldColumnName = new JTextField() {
          @Override
          protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            // search
            var searchText = workspace.getSearchText();
            if (Utils.isNullOrEmpty(searchText)) {
              return;
            }
            var targetText = getText();
            var startPos = targetText.indexOf(searchText);
            if (startPos < 0) {
              return;
            }
            var endPos = startPos + searchText.length();

            var metrics = getFontMetrics(getFont());
            String start = targetText.substring(0, startPos);
            String text = targetText.substring(startPos, endPos);
            int startX = metrics.stringWidth(start);
            int startY = 2;
            int width = metrics.stringWidth(text);
            int height = metrics.getHeight();

            g.setColor(ErConnectorColor.FOUND_COLOR);
            g.fillRect(startX, startY, width, height);
            workspace.addSearchHit(table);
          }
        };
        textFieldColumnName.setText(showColumnName);
        textFieldColumnName.setEditable(false);
        textFieldColumnName.setBackground(null);
        textFieldColumnName.setBorder(null);
        textFieldColumnName.setPreferredSize(new Dimension(w - 120, ph));
        p2.add(textFieldColumnName);
        if (idx >= noGroupLen) {
          textFieldColumnName.setForeground(Color.GRAY);
          textFieldColumnName.setText(textFieldColumnName.getText());
        }
        textFieldColumnName.addFocusListener(new FocusAdapter() {
          @Override
          public void focusLost(FocusEvent e) {
            textFieldColumnName.setScrollOffset(0);
          }
        });
        textFieldColumnName.addMouseListener(new MouseAdapter() {
          @Override
          public void mouseClicked(MouseEvent e) {
            if (e.getClickCount() >= 2) {
              if (!Utils.isNullOrEmpty(dbDictColumn.columnComment)) {
                try {
                  var range = new int[]{dbDictColumn.columnName.length() + 3,
                      dbDictColumn.getShowColumnName().length()};
                  var start = textFieldColumnName.getSelectionStart();
                  var end = textFieldColumnName.getSelectionEnd();
                  if (start >= range[0] && end <= range[1]) {
                    textFieldColumnName.select(range[0], range[1]);
                  }
                } catch (Exception ex) {
                  Log.error(ex);
                }
              }
              return;
            }
            table.requestFocusInWindow();
          }
        });
        textFieldColumnName.setToolTipText(p.getToolTipText());

        // data type
        var p3 = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0)) {
          @Override
          protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;
            var image = imageTypeOther;
            var typeGroup = DataType.getTypeGroup(dbDictColumnType.columnType);
            switch (typeGroup) {
              case NUMBER:
                image = imageTypeNumber;
                break;
              case TEXT:
                image = imageTypeText;
                break;
              case BINARY:
                image = imageTypeBinary;
                break;
              case DATE:
                image = imageTypeDate;
                break;
            }
            g2.drawImage(image, 0, 3, 12, 12, null);
          }
        };
        p3.setBackground(Color.WHITE);
        p3.setPreferredSize(new Dimension(20, ph));
        p.add(p3);

        // not null
        var p4 = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        p4.setBackground(Color.WHITE);
        p4.setPreferredSize(new Dimension(20, ph));
        p.add(p4);
        var notNull = "";
        if (dbDictColumn.notNullValue.equals(NotNull.NOT_NULL_VALUE)) {
          notNull = "NN";
        }
        p4.add(new JLabel(notNull));

        // fk
        var p5 = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0)) {
          @Override
          protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (ctxTable.ctxInnerForeignKeyList.stream()
                .filter(d -> d.dbTableForeignKeyColumnList.stream()
                    .filter(c -> c.dictColumnId.equals(dbDictColumn.dictColumnId))
                    .findFirst()
                    .isPresent())
                .findFirst()
                .isPresent()) {
              Graphics2D g2 = (Graphics2D) g;
              g2.drawImage(imageKeyForeign, 4, 4, 12, 12, null);
            }
          }
        };
        p5.setBackground(Color.WHITE);
        p5.setPreferredSize(new Dimension(20, ph));
        p.add(p5);

        idx++;
      }
      panelColumn.setSize(new Dimension(w - BORDER_SIZE * 2, targetDbDictColumList.size() * ph));
      setSize(new Dimension(w, panelTable.getHeight() + panelColumn.getHeight() + 10));

      if (ctxTable.dbTablePartition != null) {
        var hasPartitionPanel = new JPanel();
        hasPartitionPanel.setPreferredSize(new Dimension(w - BORDER_SIZE * 2, 10));
        hasPartitionPanel.setBackground(Color.YELLOW);
        panelColumnInner.add(hasPartitionPanel);

        panelColumn.setSize(
            new Dimension(w - BORDER_SIZE * 2, targetDbDictColumList.size() * ph + 10));
        setSize(new Dimension(w, panelTable.getHeight() + panelColumn.getHeight() + 4));
      }

    } else {
      panelColumn.setSize(
          new Dimension(w - BORDER_SIZE * 2, 100 - panelTable.getHeight() - BORDER_SIZE * 2));
      setSize(new Dimension(w, 100 + 10));
    }

    // redraw connector
    redrawAllConnectors();
  }

  @Override
  public void connectComplete(ErConnector connector, ErConnectorEndpoint otherEndpoint) {
    super.connectComplete(connector, otherEndpoint);

    if (otherEndpoint == null) {
      return;
    }
    if (otherEndpoint instanceof Table) {
      getWorkspace().addConnectorFromTableToTable(connector);
    }
  }

  @Override
  public void disconnectComplete(ErConnector connector, ErConnectorEndpoint otherEndpoint) {
    super.disconnectComplete(connector, otherEndpoint);

    if (otherEndpoint == null) {
      return;
    }
    if (otherEndpoint instanceof Note note) {
      workspace.removeConnectorFromNoteToTable(connector);

      try {
        // remove
        var opt = Bucket.getInstance().getBucketConnector().dbNoteConnectorTableList.stream()
            .filter(c -> c.noteId.equals(note.getCtxNote().dbNote.noteId) && c.tableId.equals(
                getCtxTable().dbTable.tableId))
            .findFirst();
        if (opt.isPresent()) {
          var dbNoteConnectorTable = opt.get();
          Bucket.getInstance().getBucketConnector().removeNoteToTable(dbNoteConnectorTable);
        }
      } catch (Exception e) {
        Log.error(e);
        JOptionPane.showMessageDialog(workspace.getRoot(), e.getMessage());
      }
    } else if (otherEndpoint instanceof Table) {
      workspace.removeConnectorFromTableToTable(connector);
    }
  }

  @Override
  public String getNameForSort() {
    return String.format("001-%s-%s", ctxTable.dbTable.tableName, ctxTable.dbTable.tableId);
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
      ctxTable.dbTableOption.posX = x;
      ctxTable.dbTableOption.posY = y;
      Bucket.getInstance().getBucketTable().saveOption(ctxTable);

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
  public void strengthening(List<String> data, Color color) {
    for (var c : panelColumnInner.getComponents()) {
      if (c instanceof JPanel && data.contains(c.getName())) {
        for (var cc : ((JPanel) c).getComponents()) {
          cc.setBackground(color);
        }
      }
    }
  }

  @Override
  public void weakening(List<String> data) {
    for (var c : panelColumnInner.getComponents()) {
      if (c instanceof JPanel && data.contains(c.getName())) {
        for (var cc : ((JPanel) c).getComponents()) {
          cc.setBackground(Color.WHITE);
        }
      }
    }
  }

  @Override
  public void focusGainedComplete() {
    workspace.notifySelectionTable(this);
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);

    // search
    var searchText = workspace.getSearchText();
    if (Utils.isNullOrEmpty(searchText)) {
      return;
    }
    var targetText = labelTableName.getText();
    var startPos = targetText.indexOf(searchText);
    if (startPos >= 0) {
      return;
    }

    var dbDictColumnList = Bucket.getInstance().getBucketDict().dbDictColumnList;
    for (var dbTableColumn : ctxTable.dbTableColumnList) {
      var dbDictColumn = dbDictColumnList.stream()
          .filter(d -> d.dictColumnId.equals(dbTableColumn.dictColumnId)).findFirst().get();
      targetText = dbDictColumn.getShowColumnName();
      startPos = targetText.indexOf(searchText);
      if (startPos >= 0) {
        return;
      }
    }

    var g2 = (Graphics2D) g;
    AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.1f);
    g2.setComposite(ac);
  }

  @Override
  public String toString() {
    var searchText = workspace.getSearchText();
    if (Utils.isNullOrEmpty(searchText)) {
      return ctxTable.dbTable.getShowTableName();
    }

    var targetText = labelTableName.getText();
    var startPos = targetText.indexOf(searchText);
    if (startPos >= 0) {
      return ctxTable.dbTable.getShowTableName();
    }

    var dbDictColumnList = Bucket.getInstance().getBucketDict().dbDictColumnList;
    for (var dbTableColumn : ctxTable.dbTableColumnList) {
      var dbDictColumn = dbDictColumnList.stream()
          .filter(d -> d.dictColumnId.equals(dbTableColumn.dictColumnId)).findFirst().get();
      targetText = dbDictColumn.getShowColumnName();
      startPos = targetText.indexOf(searchText);
      if (startPos >= 0) {
        return ctxTable.dbTable.getShowTableName();
      }
    }

    return String.format("<html><font color=#dcdcdc>%s</font></html>",
        ctxTable.dbTable.getShowTableName());
  }

  /**
   * PopupMenu.
   */
  public class PopupMenu extends JPopupMenu {

    /**
     * Constructor.
     *
     * @param table table
     */
    public PopupMenu(Table table) {
      addPopupMenuListener(new PopupMenuListener() {
        @Override
        public void popupMenuWillBecomeVisible(PopupMenuEvent popupMenuEvent) {
          table.requestFocusInWindow();
        }

        @Override
        public void popupMenuWillBecomeInvisible(PopupMenuEvent popupMenuEvent) {
          table.requestFocusInWindow();
        }

        @Override
        public void popupMenuCanceled(PopupMenuEvent popupMenuEvent) {
          table.requestFocusInWindow();
        }
      });

      // Edit
      var menuItemEdit = new JMenuItem("Edit table");
      menuItemEdit.addActionListener(actionEvent -> {
        var inputTable = new InputTable(table);
        inputTable.setModal(true);
        inputTable.setVisible(true);
      });
      add(menuItemEdit);
      addSeparator();

      // color
      var menuItemColor = new JMenuItem("Choose table color");
      menuItemColor.addActionListener(actionEvent -> {
        var color = ErColorChooser.showDialog(new Color(ctxTable.dbTableOption.color));
        ctxTable.dbTableOption.color = color.getRGB();

        try {
          // save
          Bucket.getInstance().getBucketTable().saveOption(ctxTable);
          panelTable.setBackground(color);
        } catch (Exception e) {
          Log.error(e);
          JOptionPane.showMessageDialog(workspace.getRoot(), e.getMessage());
        }
      });
      add(menuItemColor);
      addSeparator();

      // ddl
      var menuItemDdl = new JMenuItem("Show table ddl");
      menuItemDdl.addActionListener(actionEvent -> {
        var builder = new StringBuilder();
        builder.append(Bucket.getInstance().getTableDdl(ctxTable));
        var fkDdl = Bucket.getInstance().getForeignKeyDdl(ctxTable);
        if (!Utils.isNullOrEmpty(fkDdl)) {
          builder.append("\n");
          builder.append(fkDdl);
        }
        var showDdl = new ShowDdl(builder.toString());
        showDdl.setModal(true);
        showDdl.setVisible(true);
      });
      add(menuItemDdl);

      // class
      var menuItemClass = new JMenuItem("Show table class");
      menuItemClass.addActionListener(actionEvent -> {
        var showTableClass = new ShowTableClass(ctxTable);
        showTableClass.setModal(true);
        showTableClass.setVisible(true);
      });
      add(menuItemClass);

      // copy
      var menuItemCopy = new JMenuItem("Copy table");
      menuItemCopy.addActionListener(actionEvent -> {
        workspace.getRoot().getMain().setCopied(ctxTable, CtxTable.class);
      });
      addSeparator();
      add(menuItemCopy);

      // remove
      var menuItemRemove = new JMenuItem("Remove table");
      menuItemRemove.addActionListener(actionEvent -> {
        // confirm
        var tableShowName = ctxTable.dbTable.getShowTableName();
        if (!tableShowName.startsWith(Const.NEW_TABLE_PREFIX)
            && JOptionPane.showConfirmDialog(table,
            String.format("Remove table '%s' ?", tableShowName))
            != 0) {
          return;
        }

        try {
          // remove
          Bucket.getInstance().getBucketTable().remove(ctxTable);
          table.getWorkspace().removeTable(table);
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
