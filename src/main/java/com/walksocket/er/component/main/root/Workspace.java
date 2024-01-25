package com.walksocket.er.component.main.root;

import com.walksocket.er.App;
import com.walksocket.er.Const;
import com.walksocket.er.Date;
import com.walksocket.er.Log;
import com.walksocket.er.Pos;
import com.walksocket.er.Utils;
import com.walksocket.er.component.ImpSequence;
import com.walksocket.er.component.ImpTable;
import com.walksocket.er.component.main.Root;
import com.walksocket.er.component.main.root.workspace.Note;
import com.walksocket.er.component.main.root.workspace.Sequence;
import com.walksocket.er.component.main.root.workspace.Table;
import com.walksocket.er.config.CfgProject;
import com.walksocket.er.custom.ErConnector;
import com.walksocket.er.custom.ErConnectorEndpoint;
import com.walksocket.er.custom.ErConnectorPositioned;
import com.walksocket.er.custom.ErConnectorStyle;
import com.walksocket.er.custom.ErConnectorStyle.LineStyle;
import com.walksocket.er.sqlite.Bucket;
import com.walksocket.er.sqlite.context.CtxNote;
import com.walksocket.er.sqlite.context.CtxSequence;
import com.walksocket.er.sqlite.context.CtxTable;
import com.walksocket.er.sqlite.entity.DbNote;
import com.walksocket.er.sqlite.entity.DbNoteOption;
import com.walksocket.er.sqlite.entity.DbSequence;
import com.walksocket.er.sqlite.entity.DbSequenceOption;
import com.walksocket.er.sqlite.entity.DbTable;
import com.walksocket.er.sqlite.entity.DbTableOption;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.TexturePaint;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import javax.imageio.ImageIO;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.SwingUtilities;

/**
 * Workspace.
 */
public class Workspace extends ErConnectorPositioned {

  /**
   * texture background.
   */
  private static TexturePaint texturePaintBackground;

  static {
    try (var stream = App.class.getClassLoader().getResourceAsStream("image/bg_workspace.png")) {
      var image = ImageIO.read(stream);
      texturePaintBackground = new TexturePaint(image,
          new Rectangle(image.getWidth(), image.getHeight()));
    } catch (IOException e) {
      Log.error(e);
    }
  }

  /**
   * root.
   */
  private final Root root;

  /**
   * side.
   */
  private Side side;

  /**
   * positioned tables.
   */
  private final Set<Table> positionedTables = new HashSet<>();

  /**
   * positioned sequences.
   */
  private final Set<Sequence> positionedSequences = new HashSet<>();

  /**
   * positioned notes.
   */
  private final Set<Note> positionedNotes = new HashSet<>();

  /**
   * positioned connectors note to table.
   */
  private final Set<ErConnector> positionedConnectorsNoteToTable = new HashSet<>();

  /**
   * positioned connectors note to sequence.
   */
  private final Set<ErConnector> positionedConnectorsNoteToSequence = new HashSet<>();

  /**
   * positioned connectors table to table.
   */
  private final Set<ErConnector> positionedConnectorsTableToTable = new HashSet<>();

  /**
   * Constructor.
   *
   * @param root       root
   * @param cfgProject cfgProject
   */
  public Workspace(Root root, CfgProject cfgProject) {
    super();
    this.root = root;

    // layout
    setLayout(null);

    // mouse event
    var workspace = this;
    addMouseListener(new MouseAdapter() {

      @Override
      public void mousePressed(MouseEvent e) {
        // focus
        workspace.requestFocusInWindow();

        // menu
        if (e.isPopupTrigger() || SwingUtilities.isRightMouseButton(e)) {
          doPop(e);
        }
      }

      @Override
      public void mouseReleased(MouseEvent e) {
        // focus
        workspace.requestFocusInWindow();

        // menu
        if (e.isPopupTrigger() || SwingUtilities.isRightMouseButton(e)) {
          doPop(e);
          return;
        }

        // reorder
        reorder();
      }

      private void doPop(MouseEvent e) {
        var x = Utils.floorDegree(e.getX(), Pos.DEFAULT_UNIT);
        var y = Utils.floorDegree(e.getY(), Pos.DEFAULT_UNIT);
        Log.trace(String.format("x:y -> %s:%s", x, y));

        var menu = new PopupMenu(workspace, x, y);
        menu.show(e.getComponent(), x, y);
      }
    });
  }

  /**
   * get root.
   *
   * @return root
   */
  public Root getRoot() {
    return root;
  }

  /**
   * read data.
   */
  public void readData() {
    readTable();
    readSequence();
    readNote();
    readConnectorNoteToTable();
    readConnectorNoteToSequence();
    readConnectorTableToTable();

    if (positionedTables.size() == 0
        && positionedSequences.size() == 0
        && positionedNotes.size() == 0
        && positionedConnectorsNoteToTable.size() == 0
        && positionedConnectorsNoteToSequence.size() == 0
        && positionedConnectorsNoteToTable.size() == 0) {
      root.setVisible(true);
    }
  }

  /**
   * read table.
   */
  private void readTable() {
    for (var ctxTable : Bucket.getInstance().getBucketTable().ctxTableList) {
      // table
      var table = new Table(this, ctxTable);
      addTable(table);
    }
  }

  /**
   * read sequence.
   */
  private void readSequence() {
    for (var ctxSequence : Bucket.getInstance().getBucketSequence().ctxSequenceList) {
      // sequence
      var sequence = new Sequence(this, ctxSequence);
      addSequence(sequence);
    }
  }

  /**
   * read note.
   */
  private void readNote() {
    for (var ctxNote : Bucket.getInstance().getBucketNote().ctxNoteList) {
      var note = new Note(this, ctxNote);
      addNote(note);
    }
  }

  /**
   * read connector note to table
   */
  private void readConnectorNoteToTable() {
    if (positionedNotes.size() == 0) {
      return;
    }

    for (var dbNoteConnectorTable : Bucket.getInstance()
        .getBucketConnector().dbNoteConnectorTableList) {
      // note, table
      var optNote = positionedNotes.stream()
          .filter(e -> e.getCtxNote().dbNote.noteId.equals(dbNoteConnectorTable.noteId))
          .findFirst();
      var optTable = positionedTables.stream()
          .filter(e -> e.getCtxTable().dbTable.tableId.equals(
              dbNoteConnectorTable.tableId)).findFirst();
      if (optNote.isPresent() && optTable.isPresent()) {
        new ErConnector(this, optNote.get(), optTable.get(), LineStyle.SIMPLE);
      }
    }
  }

  /**
   * read connector note to sequence
   */
  private void readConnectorNoteToSequence() {
    if (positionedNotes.size() == 0) {
      return;
    }

    for (var dbNoteConnectorSequence : Bucket.getInstance()
        .getBucketConnector().dbNoteConnectorSequenceList) {
      // note, sequence
      var optNote = positionedNotes.stream()
          .filter(e -> e.getCtxNote().dbNote.noteId.equals(dbNoteConnectorSequence.noteId))
          .findFirst();
      var optSequence = positionedSequences.stream()
          .filter(e -> e.getCtxSequence().dbSequence.sequenceId.equals(
              dbNoteConnectorSequence.sequenceId)).findFirst();
      if (optNote.isPresent() && optSequence.isPresent()) {
        new ErConnector(this, optNote.get(), optSequence.get(), LineStyle.SIMPLE);
      }
    }
  }

  /**
   * read connector table to table.
   */
  private void readConnectorTableToTable() {
    if (positionedTables.size() == 0) {
      return;
    }

    for (var table : positionedTables) {
      for (var ctxInnerForeignKey : table.getCtxTable().ctxInnerForeignKeyList) {
        var referenceTableId = ctxInnerForeignKey.dbTableForeignKey.referenceTableId;

        var opt = positionedTables.stream()
            .filter(t -> t.getCtxTable().dbTable.tableId.equals(referenceTableId))
            .findFirst();
        if (opt.isPresent()) {
          var relationship = ctxInnerForeignKey.dbTableForeignKey.relationship;
          var c = new ErConnector(this, table, opt.get(),
              ErConnectorStyle.getLineStyle(relationship));

          var data1 = ctxInnerForeignKey.dbTableForeignKeyColumnList.stream()
              .map(e -> e.dictColumnId)
              .collect(Collectors.toList());
          c.setRelationData(table, data1);

          var data2 = ctxInnerForeignKey.dbTableForeignKeyColumnList.stream()
              .map(e -> e.referenceDictColumnId)
              .collect(Collectors.toList());
          c.setRelationData(opt.get(), data2);

          // self relation
          if (table == opt.get()) {
            mergeRelationData(c, table, data1, data2);
          }
        }
      }
    }
  }

  /**
   * reset connector table to table.
   *
   * @param table table
   */
  public void resetConnectorTableToTable(Table table) {
    // notice: ConcurrentModificationException
    var restoreConnectors = new ArrayList<ErConnector>();
    ErConnector connector;
    while ((connector = table.getConnectors().poll()) != null) {
      if (!positionedConnectorsTableToTable.remove(connector)) {
        restoreConnectors.add(connector);
        continue;
      }
      remove(connector);
      connector.clear(table);
    }
    table.getConnectors().addAll(restoreConnectors);

    for (var ctxInnerForeignKey : table.getCtxTable().ctxInnerForeignKeyList) {
      var referenceTableId = ctxInnerForeignKey.dbTableForeignKey.referenceTableId;

      var opt = positionedTables.stream()
          .filter(t -> t.getCtxTable().dbTable.tableId.equals(referenceTableId))
          .findFirst();
      if (opt.isPresent()) {
        var relationship = ctxInnerForeignKey.dbTableForeignKey.relationship;
        var c = new ErConnector(this, table, opt.get(),
            ErConnectorStyle.getLineStyle(relationship));

        var data1 = ctxInnerForeignKey.dbTableForeignKeyColumnList.stream()
            .map(e -> e.dictColumnId)
            .collect(Collectors.toList());
        c.setRelationData(table, data1);

        var data2 = ctxInnerForeignKey.dbTableForeignKeyColumnList.stream()
            .map(e -> e.referenceDictColumnId)
            .collect(Collectors.toList());
        c.setRelationData(opt.get(), data2);

        // self relation
        if (table == opt.get()) {
          mergeRelationData(c, table, data1, data2);
        }
      }
    }

    for (var t : positionedTables) {
      if (t == table) {
        continue;
      }
      for (var f : t.getCtxTable().ctxInnerForeignKeyList) {
        var tableId = f.dbTableForeignKey.referenceTableId;
        if (tableId.equals(table.getCtxTable().dbTable.tableId)) {
          var relationship = f.dbTableForeignKey.relationship;
          var c = new ErConnector(this, t, table, ErConnectorStyle.getLineStyle(relationship));

          var data1 = f.dbTableForeignKeyColumnList.stream()
              .map(e -> e.dictColumnId)
              .collect(Collectors.toList());
          c.setRelationData(t, data1);

          var data2 = f.dbTableForeignKeyColumnList.stream()
              .map(e -> e.referenceDictColumnId)
              .collect(Collectors.toList());
          c.setRelationData(table, data2);

          // self relation
          if (t == table) {
            mergeRelationData(c, table, data1, data2);
          }
        }
      }
    }
  }

  /**
   * merge relation data.
   *
   * @param c     connector
   * @param table table
   * @param data1 relation data
   * @param data2 relation data
   */
  private void mergeRelationData(ErConnector c, Table table, List<String> data1,
      List<String> data2) {
    data1.addAll(data2);
    c.setRelationData(table, data1);
  }

  /**
   * add table.
   *
   * @param table table.
   */
  public void addTable(Table table) {
    if (!positionedTables.add(table)) {
      return;
    }
    add(table);
    side.notifyAddTable(table);

    // reorder
    reorder();

    // focus
    table.requestFocusInWindow();
  }

  /**
   * remove table.
   *
   * @param table table
   */
  public void removeTable(Table table) {
    // remove connector
    removeAllConnectors(table);

    if (!positionedTables.remove(table)) {
      return;
    }
    side.notifyRemoveTable(table);
    remove(table);

    // reorder
    reorder();

    // focus
    requestFocusInWindow();
  }

  /**
   * add sequence
   *
   * @param sequence sequence
   */
  public void addSequence(Sequence sequence) {
    if (!positionedSequences.add(sequence)) {
      return;
    }
    add(sequence);
    side.notifyAddSequence(sequence);

    // reorder
    reorder();

    // focus
    sequence.requestFocusInWindow();
  }

  /**
   * remove sequence
   *
   * @param sequence sequence
   */
  public void removeSequence(Sequence sequence) {
    // remove connector
    removeAllConnectors(sequence);

    if (!positionedSequences.remove(sequence)) {
      return;
    }
    side.notifyRemoveSequence(sequence);
    remove(sequence);

    // reorder
    reorder();

    // focus
    requestFocusInWindow();
  }

  /**
   * add note
   *
   * @param note note
   */
  public void addNote(Note note) {
    if (!positionedNotes.add(note)) {
      return;
    }
    add(note);
    side.notifyAddNote(note);

    // reorder
    reorder();

    // focus
    note.requestFocusInWindow();
  }

  /**
   * remove note
   *
   * @param note note
   */
  public void removeNote(Note note) {
    // remove connector
    removeAllConnectors(note);

    if (!positionedNotes.remove(note)) {
      return;
    }
    side.notifyRemoveNote(note);
    remove(note);

    // reorder
    reorder();

    // focus
    requestFocusInWindow();
  }

  /**
   * add connector from note to table.
   *
   * @param connector connector
   */
  public void addConnectorFromNoteToTable(ErConnector connector) {
    if (!positionedConnectorsNoteToTable.add(connector)) {
      return;
    }
    Log.trace(String.format("Add connector from note to table '%s' on workspace", connector));
    add(connector);

    // reorder
    reorder();

    // focus
    connector.requestFocusInWindow();
  }

  /**
   * remove connector from note to table.
   *
   * @param connector connector
   */
  public void removeConnectorFromNoteToTable(ErConnector connector) {
    if (!positionedConnectorsNoteToTable.remove(connector)) {
      return;
    }
    Log.trace(String.format("Remove connector from note to table '%s' on workspace", connector));
    remove(connector);

    // focus
    requestFocusInWindow();
  }

  /**
   * add connector from note to sequence.
   *
   * @param connector connector
   */
  public void addConnectorFromNoteToSequence(ErConnector connector) {
    if (!positionedConnectorsNoteToSequence.add(connector)) {
      return;
    }
    Log.trace(String.format("Add connector from note to sequence '%s' on workspace", connector));
    add(connector);

    // reorder
    reorder();

    // focus
    connector.requestFocusInWindow();
  }

  /**
   * remove connector from note to sequence.
   *
   * @param connector connector
   */
  public void removeConnectorFromNoteToSequence(ErConnector connector) {
    if (!positionedConnectorsNoteToSequence.remove(connector)) {
      return;
    }
    Log.trace(String.format("Remove connector from note to sequence '%s' on workspace", connector));
    remove(connector);

    // focus
    requestFocusInWindow();
  }

  /**
   * add connector from table to table.
   *
   * @param connector connector
   */
  public void addConnectorFromTableToTable(ErConnector connector) {
    if (!positionedConnectorsTableToTable.add(connector)) {
      return;
    }
    Log.trace(String.format("Add connector from table to table '%s' on workspace", connector));
    add(connector);

    // reorder
    reorder();

    // focus
    connector.requestFocusInWindow();
  }

  /**
   * remove connector from table to table.
   *
   * @param connector connector
   */
  public void removeConnectorFromTableToTable(ErConnector connector) {
    if (!positionedConnectorsTableToTable.remove(connector)) {
      return;
    }
    Log.trace(String.format("Remove connector from table to table '%s' on workspace", connector));
    remove(connector);

    // focus
    requestFocusInWindow();
  }

  /**
   * remove all connectors.
   *
   * @param endpoint endpoint
   */
  public void removeAllConnectors(ErConnectorEndpoint endpoint) {
    for (var connector : endpoint.getConnectors()) {
      positionedConnectorsNoteToTable.remove(connector);
      positionedConnectorsNoteToSequence.remove(connector);
      positionedConnectorsTableToTable.remove(connector);
      remove(connector);
    }
    endpoint.removeAllConnectors();
  }

  /**
   * reorder.
   */
  private void reorder() {
    removeAll();

    // note
    if (positionedNotes.size() > 0) {
      var values = positionedNotes.stream()
          .sorted(Comparator.comparing(t -> t.getCtxNote().dbNote.subject))
          .collect(Collectors.toList());
      for (var v : values) {
        add(v);

        // connector
        for (var c : v.getConnectors()) {
          c.setSortName(v.getCtxNote().dbNote.subject);
        }
      }
    }

    // sequence
    if (positionedSequences.size() > 0) {
      var values = positionedSequences.stream()
          .sorted(Comparator.comparing(t -> t.getCtxSequence().dbSequence.sequenceName))
          .collect(Collectors.toList());
      for (var v : values) {
        add(v);
      }
    }

    // table
    if (positionedTables.size() > 0) {
      var values = positionedTables.stream()
          .sorted(Comparator.comparing(t -> t.getCtxTable().dbTable.tableName))
          .collect(Collectors.toList());
      for (var v : values) {
        add(v);
      }
    }

    // connector note to sequence
    if (positionedConnectorsNoteToSequence.size() > 0) {
      var values = positionedConnectorsNoteToSequence.stream()
          .sorted(Comparator.comparing(t -> t.getSortName()))
          .collect(Collectors.toList());
      for (var v : values) {
        add(v);
      }
    }

    // connector note to table
    if (positionedConnectorsNoteToTable.size() > 0) {
      var values = positionedConnectorsNoteToTable.stream()
          .sorted(Comparator.comparing(t -> t.getSortName()))
          .collect(Collectors.toList());
      for (var v : values) {
        add(v);
      }
    }

    // connector table to table
    if (positionedConnectorsTableToTable.size() > 0) {
      var values = positionedConnectorsTableToTable.stream()
          .sorted(Comparator.comparing(t -> t.getSortName()))
          .collect(Collectors.toList());
      for (var v : values) {
        add(v);
      }
    }
  }

  /**
   * reload table.
   */
  public void reloadTable() {
    reorder();
    side.reloadTable();
  }

  /**
   * reload sequence.
   */
  public void reloadSequence() {
    reorder();
    side.reloadSequence();
  }

  /**
   * reload note.
   */
  public void reloadNote() {
    reorder();
    side.reloadNote();
  }

  /**
   * set side.
   *
   * @param side side
   */
  public void setSide(Side side) {
    this.side = side;
  }

  @Override
  public Dimension getPreferredSize() {
    // --------------------
    // width
    int maxWidth = getWidth();

    // table
    var optTableX = positionedTables
        .stream()
        .collect(Collectors.maxBy(Comparator.comparing(t -> t.getX() + t.getWidth())));
    if (optTableX.isPresent()) {
      var tmpMaxWidth = optTableX.get().getX() + optTableX.get().getWidth();
      if (tmpMaxWidth > maxWidth) {
        maxWidth = tmpMaxWidth;
      }
    }

    // sequence
    var optSequenceX = positionedSequences
        .stream()
        .collect(Collectors.maxBy(Comparator.comparing(s -> s.getX() + s.getWidth())));
    if (optSequenceX.isPresent()) {
      var tmpMaxWidth = optSequenceX.get().getX() + optSequenceX.get().getWidth();
      if (tmpMaxWidth > maxWidth) {
        maxWidth = tmpMaxWidth;
      }
    }

    // note
    var optNoteX = positionedNotes
        .stream()
        .collect(Collectors.maxBy(Comparator.comparing(n -> n.getX() + n.getWidth())));
    if (optNoteX.isPresent()) {
      var tmpMaxWidth = optNoteX.get().getX() + optNoteX.get().getWidth();
      if (tmpMaxWidth > maxWidth) {
        maxWidth = tmpMaxWidth;
      }
    }

    // --------------------
    // height
    int maxHeight = getHeight();

    // table
    var optTableY = positionedTables
        .stream()
        .collect(Collectors.maxBy(Comparator.comparing(t -> t.getY() + t.getHeight())));
    if (optTableY.isPresent()) {
      var tmpMaxHeight = optTableY.get().getY() + optTableY.get().getHeight();
      if (tmpMaxHeight > maxHeight) {
        maxHeight = tmpMaxHeight;
      }
    }

    // sequence
    var optSequenceY = positionedSequences
        .stream()
        .collect(Collectors.maxBy(Comparator.comparing(s -> s.getY() + s.getHeight())));
    if (optSequenceY.isPresent()) {
      var tmpMaxHeight = optSequenceY.get().getY() + optSequenceY.get().getHeight();
      if (tmpMaxHeight > maxHeight) {
        maxHeight = tmpMaxHeight;
      }
    }

    // note
    var optNoteY = positionedNotes
        .stream()
        .collect(Collectors.maxBy(Comparator.comparing(n -> n.getY() + n.getHeight())));
    if (optNoteY.isPresent()) {
      var tmpMaxHeight = optNoteY.get().getY() + optNoteY.get().getHeight();
      if (tmpMaxHeight > maxHeight) {
        maxHeight = tmpMaxHeight;
      }
    }

    return new Dimension(maxWidth, maxHeight);
  }


  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);

    Graphics2D g2 = (Graphics2D) g;
    g2.setPaint(texturePaintBackground);
    g2.fillRect(0, 0, getWidth(), getHeight());
  }

  /**
   * get ordered positioned tables.
   *
   * @return tables
   */
  public List<Table> getOrderedPositionedTables() {
    return positionedTables.stream()
        .sorted(Comparator.comparing(t -> t.getCtxTable().dbTable.tableName))
        .collect(Collectors.toList());
  }

  /**
   * get ordered positioned sequences.
   *
   * @return sequences
   */
  public List<Sequence> getOrderedPositionedSequences() {
    return positionedSequences.stream()
        .sorted(Comparator.comparing(t -> t.getCtxSequence().dbSequence.sequenceName))
        .collect(Collectors.toList());
  }

  /**
   * get ordered positioned notes.
   *
   * @return notes
   */
  public List<Note> getOrderedPositionedNotes() {
    return positionedNotes.stream()
        .sorted(Comparator.comparing(t -> t.getNameForSort()))
        .collect(Collectors.toList());
  }

  /**
   * get ordered positioned connectors note to table.
   *
   * @return connectors
   */
  public List<ErConnector> getOrderedPositionedConnectorsNoteToTableList() {
    return positionedConnectorsNoteToTable.stream()
        .sorted(Comparator.comparing(t -> t.getSortName()))
        .collect(Collectors.toList());
  }

  /**
   * get ordered positioned connectors note to sequence.
   *
   * @return connectors
   */
  public List<ErConnector> getOrderedPositionedConnectorsNoteToSequenceList() {
    return positionedConnectorsNoteToSequence.stream()
        .sorted(Comparator.comparing(t -> t.getSortName()))
        .collect(Collectors.toList());
  }

  /**
   * PopupMenu.
   */
  public class PopupMenu extends JPopupMenu {

    /**
     * Constructor.
     *
     * @param workspace workspace
     * @param x         x
     * @param y         y
     */
    public PopupMenu(Workspace workspace, int x, int y) {
      // new table
      var menuItemNewTable = new JMenuItem("New table");
      menuItemNewTable.addActionListener(actionEvent -> {
        // check
        if (positionedTables.size() > Table.MAX_POSITIONED) {
          JOptionPane.showMessageDialog(workspace, "No more create table.");
          return;
        }

        try {
          // register
          var dbTable = new DbTable();
          dbTable.tableId = Utils.randomString();
          dbTable.tableName = String.format("%s_%s", Const.NEW_TABLE_PREFIX,
              Date.timestamp());

          var dbTableOption = new DbTableOption();
          dbTableOption.tableId = dbTable.tableId;
          dbTableOption.posX = x;
          dbTableOption.posY = y;

          var ctxTable = new CtxTable();
          ctxTable.dbTable = dbTable;
          ctxTable.dbTableOption = dbTableOption;

          Bucket.getInstance().getBucketTable().register(ctxTable);

          // add
          var table = new Table(workspace, ctxTable);
          workspace.addTable(table);

        } catch (Exception e) {
          Log.error(e);
          JOptionPane.showMessageDialog(workspace.getRoot(), e.getMessage());
        }
      });
      add(menuItemNewTable);

      // new sequence
      var menuItemNewSequence = new JMenuItem("New sequence");
      menuItemNewSequence.addActionListener(actionEvent -> {
        if (positionedSequences.size() > Sequence.MAX_POSITIONED) {
          JOptionPane.showMessageDialog(workspace, "No more create sequence.");
          return;
        }

        try {
          // register
          var dbSequence = new DbSequence();
          dbSequence.sequenceId = Utils.randomString();
          dbSequence.sequenceName = String.format("%s_%s", Const.NEW_SEQUENCE_PREFIX,
              Date.timestamp());

          var dbSequenceOption = new DbSequenceOption();
          dbSequenceOption.sequenceId = dbSequence.sequenceId;
          dbSequenceOption.posX = x;
          dbSequenceOption.posY = y;

          var ctxSequence = new CtxSequence();
          ctxSequence.dbSequence = dbSequence;
          ctxSequence.dbSequenceOption = dbSequenceOption;

          Bucket.getInstance().getBucketSequence().register(ctxSequence);

          // add
          var sequence = new Sequence(workspace, ctxSequence);
          workspace.addSequence(sequence);

        } catch (Exception e) {
          Log.error(e);
          JOptionPane.showMessageDialog(workspace.getRoot(), e.getMessage());
        }
      });
      add(menuItemNewSequence);

      // new note
      var menuItemNewNote = new JMenuItem("New note");
      menuItemNewNote.addActionListener(actionEvent -> {
        // check
        if (positionedNotes.size() > Note.MAX_POSITIONED) {
          JOptionPane.showMessageDialog(workspace, "No more create note.");
          return;
        }

        try {
          // register
          var dbNote = new DbNote();
          dbNote.noteId = Utils.randomString();
          dbNote.subject = String.format("%s_%s", Const.NEW_NOTE_PREFIX, Date.timestamp());
          dbNote.body = "";

          var dbNoteOption = new DbNoteOption();
          dbNoteOption.noteId = dbNote.noteId;
          dbNoteOption.posX = x;
          dbNoteOption.posY = y;

          var ctxNote = new CtxNote();
          ctxNote.dbNote = dbNote;
          ctxNote.dbNoteOption = dbNoteOption;

          Bucket.getInstance().getBucketNote().register(ctxNote);

          // add
          var note = new Note(workspace, ctxNote);
          workspace.addNote(note);

        } catch (Exception e) {
          Log.error(e);
          JOptionPane.showMessageDialog(workspace.getRoot(), e.getMessage());
        }
      });
      add(menuItemNewNote);
      addSeparator();

      // paste table
      var menuItemPasteTable = new JMenuItem("Paste table");
      if (!getRoot().getMain().existCopied(CtxTable.class)) {
        menuItemPasteTable.setEnabled(false);
      }
      menuItemPasteTable.addActionListener(actionEvent -> {
        // check
        if (positionedTables.size() > Table.MAX_POSITIONED) {
          JOptionPane.showMessageDialog(workspace, "No more create table.");
          return;
        }

        var copiedCtxTable = getRoot().getMain().getCopied(CtxTable.class);
        if (copiedCtxTable == null) {
          JOptionPane.showMessageDialog(workspace, "No copied table.");
          return;
        }

        try {
          // register for copy
          copiedCtxTable.dbTableOption.posX = x;
          copiedCtxTable.dbTableOption.posY = y;
          Bucket.getInstance().getBucketTable().registerForCopy(copiedCtxTable);

          // add
          var table = new Table(workspace, copiedCtxTable);
          workspace.addTable(table);

        } catch (Exception e) {
          Log.error(e);
          JOptionPane.showMessageDialog(workspace.getRoot(), e.getMessage());
        }
      });
      add(menuItemPasteTable);

      // paste sequence
      var menuItemPasteSequence = new JMenuItem("Paste sequence");
      if (!getRoot().getMain().existCopied(CtxSequence.class)) {
        menuItemPasteSequence.setEnabled(false);
      }
      menuItemPasteSequence.addActionListener(actionEvent -> {
        if (positionedSequences.size() > Sequence.MAX_POSITIONED) {
          JOptionPane.showMessageDialog(workspace, "No more create sequence.");
          return;
        }

        var copiedCtxSequence = getRoot().getMain().getCopied(CtxSequence.class);
        if (copiedCtxSequence == null) {
          JOptionPane.showMessageDialog(workspace, "No copied sequence.");
          return;
        }

        try {
          // register for copy
          copiedCtxSequence.dbSequenceOption.posX = x;
          copiedCtxSequence.dbSequenceOption.posY = y;

          Bucket.getInstance().getBucketSequence().registerForCopy(copiedCtxSequence);

          // add
          var sequence = new Sequence(workspace, copiedCtxSequence);
          workspace.addSequence(sequence);

        } catch (Exception e) {
          Log.error(e);
          JOptionPane.showMessageDialog(workspace.getRoot(), e.getMessage());
        }
      });
      add(menuItemPasteSequence);

      // paste note
      var menuItemPasteNote = new JMenuItem("Paste note");
      if (!getRoot().getMain().existCopied(CtxNote.class)) {
        menuItemPasteNote.setEnabled(false);
      }
      menuItemPasteNote.addActionListener(actionEvent -> {
        if (positionedNotes.size() > Note.MAX_POSITIONED) {
          JOptionPane.showMessageDialog(workspace, "No more create note.");
          return;
        }

        var copiedCtxNote = getRoot().getMain().getCopied(CtxNote.class);
        if (copiedCtxNote == null) {
          JOptionPane.showMessageDialog(workspace, "No copied note.");
          return;
        }

        try {
          // register for copy
          copiedCtxNote.dbNoteOption.posX = x;
          copiedCtxNote.dbNoteOption.posY = y;

          Bucket.getInstance().getBucketNote().registerForCopy(copiedCtxNote);

          // add
          var note = new Note(workspace, copiedCtxNote);
          workspace.addNote(note);

        } catch (Exception e) {
          Log.error(e);
          JOptionPane.showMessageDialog(workspace.getRoot(), e.getMessage());
        }
      });
      add(menuItemPasteNote);
      addSeparator();

      // import table
      var menuItemImportTable = new JMenuItem("Import table");
      menuItemImportTable.addActionListener(actionEvent -> {
        if (positionedTables.size() > Table.MAX_POSITIONED) {
          JOptionPane.showMessageDialog(workspace, "No more create table.");
          return;
        }

        var impTable = new ImpTable(workspace, new Point(x, y));
        impTable.setModal(true);
        impTable.setVisible(true);
      });
      add(menuItemImportTable);

      // import sequence
      var menuItemImportSequence = new JMenuItem("Import sequence");
      menuItemImportSequence.addActionListener(actionEvent -> {
        if (positionedSequences.size() > Sequence.MAX_POSITIONED) {
          JOptionPane.showMessageDialog(workspace, "No more create sequence.");
          return;
        }

        var impSequence = new ImpSequence(workspace, new Point(x, y));
        impSequence.setModal(true);
        impSequence.setVisible(true);
      });
      add(menuItemImportSequence);
    }
  }

  /**
   * complete table ddl.
   *
   * @param ddl   ddl
   * @param point point
   * @return if success
   */
  public boolean completeTableDdl(String ddl, Point point) {
    try {
      var newCtxTable = Bucket.getInstance().getBucketTable().importFromDdl(ddl, point);

      // add
      var table = new Table(this, newCtxTable);
      addTable(table);

      return true;

    } catch (Exception e) {
      Log.error(e);
      JOptionPane.showMessageDialog(getRoot(), e.getMessage());
    }

    return false;
  }

  /**
   * complete sequence ddl.
   *
   * @param ddl   ddl
   * @param point point
   * @return if success
   */
  public boolean completeSequenceDdl(String ddl, Point point) {
    try {
      var newCtxSequence = Bucket.getInstance().getBucketSequence().importFromDdl(ddl, point);

      // add
      var sequence = new Sequence(this, newCtxSequence);
      addSequence(sequence);

      return true;

    } catch (Exception e) {
      Log.error(e);
      JOptionPane.showMessageDialog(getRoot(), e.getMessage());
    }

    return false;
  }
}
