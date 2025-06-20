package com.walksocket.er.component.main.root;

import com.walksocket.er.component.main.Root;
import com.walksocket.er.component.main.root.workspace.Note;
import com.walksocket.er.component.main.root.workspace.Sequence;
import com.walksocket.er.component.main.root.workspace.Table;
import com.walksocket.er.config.CfgProject;
import com.walksocket.er.custom.ErConnectorEndpoint;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

/**
 * Side.
 */
public class Side extends JPanel {

  /**
   * root.
   */
  private final Root root;

  /**
   * workspace.
   */
  private Workspace workspace;

  /**
   * outline.
   */
  private Outline outline;

  /**
   * tree.
   */
  private final JTree tree;

  /**
   * tree node.
   */
  private final DefaultMutableTreeNode treeNode;

  /**
   * tree model.
   */
  private final DefaultTreeModel treeModel;

  /**
   * tree node table.
   */
  private final DefaultMutableTreeNode treeNodeTable;

  /**
   * children table.
   */
  private final Map<Table, DefaultMutableTreeNode> childrenTable = new HashMap<>();

  /**
   * tree node sequence.
   */
  private final DefaultMutableTreeNode treeNodeSequence;

  /**
   * children sequence.
   */
  private final Map<Sequence, DefaultMutableTreeNode> childrenSequence = new HashMap<>();

  /**
   * tree node note.
   */
  private final DefaultMutableTreeNode treeNodeNote;

  /**
   * children note.
   */
  private final Map<Note, DefaultMutableTreeNode> childrenNote = new HashMap<>();

  /**
   * Constructor.
   *
   * @param root       root
   * @param cfgProject cfgProject
   */
  public Side(Root root, CfgProject cfgProject) {
    this.root = root;

    // layout
    setLayout(new FlowLayout(FlowLayout.LEFT));

    // color
    setBackground(Color.WHITE);

    // key event
    addKeyListener(new KeyAdapter() {
      @Override
      public void keyPressed(KeyEvent e) {
        doSearchAction(e);
      }
    });

    // table
    treeNodeTable = new DefaultMutableTreeNode(
        String.format("Tables (0/%s)", Table.MAX_POSITIONED));

    // sequence
    treeNodeSequence = new DefaultMutableTreeNode(
        String.format("Sequences (0/%s)", Sequence.MAX_POSITIONED));

    // note
    treeNodeNote = new DefaultMutableTreeNode(String.format("Notes (0/%s)", Note.MAX_POSITIONED));

    // tree
    treeNode = new DefaultMutableTreeNode(cfgProject.name);
    treeModel = new DefaultTreeModel(treeNode);
    treeNode.add(treeNodeTable);
    treeNode.add(treeNodeSequence);
    treeNode.add(treeNodeNote);
    tree = new JTree(treeModel);
    tree.addKeyListener(new KeyAdapter() {
      @Override
      public void keyPressed(KeyEvent e) {
        if (doSearchAction(e)) {
          return;
        }

        var treePath = tree.getSelectionPath();
        if (treePath == null) {
          return;
        }
        var endpoint = moveToEndpoint(treePath);
        if (endpoint != null && e.getKeyCode() == KeyEvent.VK_ENTER) {
          workspace.getSelectingRange().clearAllMovers();
          endpoint.requestFocusInWindow();
        }
      }
    });
    tree.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        if (e.isPopupTrigger() || SwingUtilities.isRightMouseButton(e)) {
          return;
        }

        var tree = (JTree) e.getSource();
        var treePath = tree.getPathForLocation(e.getX(), e.getY());
        if (treePath == null) {
          return;
        }
        tree.setSelectionPath(treePath);
        var endpoint = moveToEndpoint(treePath);

        // focus
        if (endpoint != null && e.getClickCount() >= 2) {
          workspace.getSelectingRange().clearAllMovers();
          endpoint.requestFocusInWindow();
        }
      }
    });
    add(tree);
  }

  /**
   * set outline.
   *
   * @param outline outline
   */
  public void setOutline(Outline outline) {
    this.outline = outline;
  }

  /**
   * do search action.
   *
   * @param e event
   * @return if happened
   */
  private boolean doSearchAction(KeyEvent e) {
    if (e.getKeyCode() == KeyEvent.VK_F && e.isControlDown()) {
      // show search
      if (outline != null) {
        outline.getSearchSpace().showSpace();
        return true;
      }
    } else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
      // hide search
      if (outline != null) {
        outline.getSearchSpace().hideSpace();
        return true;
      }
    }
    return false;
  }

  /**
   * movie to endpoint.
   *
   * @param treePath treePath
   * @return endpoint
   */
  public ErConnectorEndpoint moveToEndpoint(TreePath treePath) {
    var tmpNode = treePath.getLastPathComponent();
    if (tmpNode != null && tmpNode instanceof DefaultMutableTreeNode node) {
      var tmpObject = node.getUserObject();
      if (tmpObject != null) {
        JScrollPane sp = null;
        if (tmpObject instanceof Table table) {
          sp = (JScrollPane) table.getWorkspace().getParent().getParent();
        } else if (tmpObject instanceof Sequence sequence) {
          sp = (JScrollPane) sequence.getWorkspace().getParent().getParent();
        } else if (tmpObject instanceof Note note) {
          sp = (JScrollPane) note.getWorkspace().getParent().getParent();
        }

        if (sp != null && tmpObject instanceof ErConnectorEndpoint endpoint) {
          // move
          var bw = sp.getHorizontalScrollBar();
          var bh = sp.getVerticalScrollBar();
          bw.setValue(endpoint.getX());
          bh.setValue(endpoint.getY());

          return endpoint;
        }
      }
    }
    return null;
  }

  /**
   * set workspace.
   *
   * @param workspace workspace
   */
  public void setWorkspace(Workspace workspace) {
    this.workspace = workspace;
  }

  /**
   * notify add table
   *
   * @param table table
   */
  public void notifyAddTable(Table table) {
    if (!childrenTable.containsKey(table)) {
      var node = new DefaultMutableTreeNode(table);
      node.setUserObject(table);
      childrenTable.put(table, node);
    }

    reloadTable();
  }

  /**
   * notify remove table
   *
   * @param table table
   */
  public void notifyRemoveTable(Table table) {
    childrenTable.remove(table);

    reloadTable();
  }

  /**
   * notify selection table.
   *
   * @param table table
   */
  public void notifySelectionTable(Table table) {
    var node = childrenTable.get(table);
    tree.setSelectionPath(new TreePath(node.getPath()));
  }

  /**
   * reload table.
   */
  public void reloadTable() {
    treeNodeTable.removeAllChildren();
    var nodes = childrenTable.values().stream()
        .sorted(Comparator.comparing(DefaultMutableTreeNode::toString)).collect(
            Collectors.toList());
    for (var node : nodes) {
      treeNodeTable.add(node);
    }
    reloadTree();
  }

  /**
   * notify add sequence
   *
   * @param sequence sequence
   */
  public void notifyAddSequence(Sequence sequence) {
    if (!childrenSequence.containsKey(sequence)) {
      var node = new DefaultMutableTreeNode(sequence);
      node.setUserObject(sequence);
      childrenSequence.put(sequence, node);
    }

    reloadSequence();
  }

  /**
   * notify remove sequence.
   *
   * @param sequence sequence
   */
  public void notifyRemoveSequence(Sequence sequence) {
    childrenSequence.remove(sequence);

    reloadSequence();
  }

  /**
   * notify selection sequence.
   *
   * @param sequence sequence
   */
  public void notifySelectionSequence(Sequence sequence) {
    var node = childrenSequence.get(sequence);
    tree.setSelectionPath(new TreePath(node.getPath()));
  }

  /**
   * reload sequence.
   */
  public void reloadSequence() {
    treeNodeSequence.removeAllChildren();
    var nodes = childrenSequence.values().stream()
        .sorted(Comparator.comparing(DefaultMutableTreeNode::toString)).collect(
            Collectors.toList());
    for (var node : nodes) {
      treeNodeSequence.add(node);
    }
    reloadTree();
  }

  /**
   * notify add note
   *
   * @param note note
   */
  public void notifyAddNote(Note note) {
    if (!childrenNote.containsKey(note)) {
      var node = new DefaultMutableTreeNode(note);
      node.setUserObject(note);
      childrenNote.put(note, node);
    }

    reloadNote();
  }

  /**
   * notify remove note
   *
   * @param note note
   */
  public void notifyRemoveNote(Note note) {
    childrenNote.remove(note);

    reloadNote();
  }

  /**
   * notify selection note.
   *
   * @param note note
   */
  public void notifySelectionNote(Note note) {
    var node = childrenNote.get(note);
    tree.setSelectionPath(new TreePath(node.getPath()));
  }

  /**
   * reload note.
   */
  public void reloadNote() {
    treeNodeNote.removeAllChildren();
    var nodes = childrenNote.values().stream()
        .sorted(Comparator.comparing(DefaultMutableTreeNode::toString)).collect(
            Collectors.toList());
    for (var node : nodes) {
      treeNodeNote.add(node);
    }
    reloadTree();
  }

  /**
   * reload tree.
   */
  private void reloadTree() {
    treeModel.reload();

    treeNodeTable.setUserObject(
        String.format("Tables (%s/%s)", treeNodeTable.getChildCount(), Table.MAX_POSITIONED));
    if (treeNodeTable.getChildCount() > 0) {
      tree.expandPath(new TreePath(treeNodeTable.getPath()));
    }

    treeNodeSequence.setUserObject(
        String.format("Sequences (%s/%s)", treeNodeSequence.getChildCount(),
            Sequence.MAX_POSITIONED));
    if (treeNodeSequence.getChildCount() > 0) {
      tree.expandPath(new TreePath(treeNodeSequence.getPath()));
    }

    treeNodeNote.setUserObject(
        String.format("Notes (%s/%s)", treeNodeNote.getChildCount(), Note.MAX_POSITIONED));
    if (treeNodeNote.getChildCount() > 0) {
      tree.expandPath(new TreePath(treeNodeNote.getPath()));
    }
  }
}
