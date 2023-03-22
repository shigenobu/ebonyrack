package com.walksocket.er.component.main.root.wokspace;

import com.walksocket.er.App;
import com.walksocket.er.Const;
import com.walksocket.er.Date;
import com.walksocket.er.Log;
import com.walksocket.er.Utils;
import com.walksocket.er.component.InputTable;
import com.walksocket.er.component.ShowDdl;
import com.walksocket.er.component.main.root.Workspace;
import com.walksocket.er.custom.ErColorChooser;
import com.walksocket.er.custom.ErConnector;
import com.walksocket.er.custom.ErConnectorEndpoint;
import com.walksocket.er.custom.ErConnectorEndpointRelation;
import com.walksocket.er.definition.DataType;
import com.walksocket.er.definition.NotNull;
import com.walksocket.er.sqlite.Bucket;
import com.walksocket.er.sqlite.context.CtxTable;
import com.walksocket.er.sqlite.entity.DbDictColumn;
import com.walksocket.er.sqlite.entity.DbNoteConnectorTable;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
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
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

/**
 * Table.
 */
public class Table extends ErConnectorEndpoint implements ErConnectorEndpointRelation {

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
    setSize(new Dimension(200, 100));
    var table = this;

    // table
    panelTable = new JPanel(new FlowLayout(FlowLayout.CENTER));
    panelTable.setLocation(new Point(2, 2));
    panelTable.setSize(new Dimension(getWidth() - 4, 36));
    panelTable.setBackground(new Color(ctxTable.dbTableOption.color));
    panelTable.addMouseListener(new MouseAdapter() {

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
        // pressed
        pressed = Date.timestampMillis();

        // focus
        table.requestFocusInWindow();

        // menu
        if (e.isPopupTrigger() || SwingUtilities.isRightMouseButton(e)) {
          doPop(e);
          return;
        }

        // change cursor
        var cursor = Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR);
        panelTable.setCursor(cursor);
        pressedX = e.getX();
        pressedY = e.getY();
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

        // reverse cursor
        var cursor = Cursor.getDefaultCursor();
        panelTable.setCursor(cursor);

        // move
        if (Date.timestampMillis() - pressed > 250) {
          var startX = table.getX();
          var startY = table.getY();
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
            ctxTable.dbTableOption.posX = x;
            ctxTable.dbTableOption.posY = y;
            Bucket.getInstance().getBucketTable().saveOption(ctxTable);

            // position
            table.setLocation(x, y);

            // redraw connector
            redrawAllConnectors();

            // focus
            table.requestFocusInWindow();
          } catch (Exception ex) {
            Log.error(e);
            JOptionPane.showMessageDialog(workspace, ex.getMessage());
          }
        }
      }

      private void doPop(MouseEvent e) {
        var menu = new PopupMenu(table, e.getX(), e.getY());
        menu.show(e.getComponent(), e.getX(), e.getY());
      }
    });
    add(panelTable);
    panelTable.add(labelTableName);

    // column
    panelColumn = new JPanel();
    panelColumn.setLocation(new Point(2, panelTable.getHeight() + 2));
    panelColumn.setSize(new Dimension(getWidth() - 4, getHeight() - panelColumn.getHeight() - 4));
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
   * redraw.
   */
  public void redraw() {
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
    if (w < 200) {
      w = 200;
    }
    if (w > 500) {
      w = 500;
    }

    panelTable.setSize(new Dimension(w - 4, panelTable.getHeight()));
    panelTable.setToolTipText(ctxTable.dbTable.getTipText());
    labelTableName.setPreferredSize(new Dimension(w - 4 - 10, panelTable.getHeight() - 10));

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
              Graphics2D g2 = (Graphics2D) g.create();
              g2.drawImage(imageKeyPrimary, 0, 4, 12, 12, null);
              g2.dispose();
            }
            if (ctxTable.ctxInnerUniqueKeyList.stream()
                .filter(d -> d.dbTableUniqueKeyColumnList.stream()
                    .filter(c -> c.dictColumnId.equals(dbDictColumn.dictColumnId))
                    .findFirst()
                    .isPresent())
                .findFirst()
                .isPresent()) {
              Graphics2D g2 = (Graphics2D) g.create();
              g2.drawImage(imageKeyUnique, 12, 4, 12, 12, null);
              g2.dispose();
            }
            if (ctxTable.ctxInnerKeyList.stream()
                .filter(d -> d.dbTableKeyColumnList.stream()
                    .filter(c -> c.dictColumnId.equals(dbDictColumn.dictColumnId))
                    .findFirst()
                    .isPresent())
                .findFirst()
                .isPresent()) {
              Graphics2D g2 = (Graphics2D) g.create();
              g2.drawImage(imageKey, 24, 4, 12, 12, null);
              g2.dispose();
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
        var labelColumnName = new JLabel(showColumnName);
        labelColumnName.setVerticalAlignment(SwingConstants.TOP);
        labelColumnName.setPreferredSize(new Dimension(w - 110, ph));
        p2.add(labelColumnName);
        if (idx >= noGroupLen) {
          labelColumnName.setText(String.format("<html><font color='gray'>%s</font></html>",
              labelColumnName.getText()));
        }

        // data type
        var p3 = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0)) {
          @Override
          protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g.create();
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
            g2.dispose();
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
              Graphics2D g2 = (Graphics2D) g.create();
              g2.drawImage(imageKeyForeign, 4, 4, 12, 12, null);
              g2.dispose();
            }
          }
        };
        p5.setBackground(Color.WHITE);
        p5.setPreferredSize(new Dimension(20, ph));
        p.add(p5);

        idx++;
      }
      panelColumn.setSize(new Dimension(w - 4, targetDbDictColumList.size() * ph));
      setSize(new Dimension(w, panelTable.getHeight() + panelColumn.getHeight() + 10));
    } else {
      panelColumn.setSize(new Dimension(w - 4, 100 - panelTable.getHeight() - 4));
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
    if (otherEndpoint instanceof Note) {
      workspace.removeConnectorFromNoteToTable(connector);

      try {
        // remove
        var note = (Note) otherEndpoint;
        var dbNoteConnectorTable = new DbNoteConnectorTable();
        dbNoteConnectorTable.noteId = note.getCtxNote().dbNote.noteId;
        dbNoteConnectorTable.tableId = getCtxTable().dbTable.tableId;

        Bucket.getInstance().getBucketConnector().removeNoteToTable(dbNoteConnectorTable);
      } catch (Exception e) {
        Log.error(e);
        JOptionPane.showMessageDialog(workspace, e.getMessage());
      }
    } else if (otherEndpoint instanceof Table) {
      workspace.removeConnectorFromTableToTable(connector);
    }
  }

  @Override
  public String getNameForSort() {
    return toString();
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
  public String toString() {
    return ctxTable.dbTable.getShowTableName();
  }

  /**
   * PopupMenu.
   */
  public class PopupMenu extends JPopupMenu {

    /**
     * Constructor.
     *
     * @param table table
     * @param x     x
     * @param y     y
     */
    public PopupMenu(Table table, int x, int y) {
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
          JOptionPane.showMessageDialog(workspace, e.getMessage());
        }
      });
      add(menuItemColor);

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
          JOptionPane.showMessageDialog(workspace, e.getMessage());
        }
      });
      addSeparator();
      add(menuItemRemove);
    }
  }
}
