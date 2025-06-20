package com.walksocket.er.component.main.root;

import com.walksocket.er.App;
import com.walksocket.er.Date;
import com.walksocket.er.Log;
import com.walksocket.er.component.main.Root;
import com.walksocket.er.component.main.root.outline.ViewFrame;
import com.walksocket.er.component.main.root.workspace.Note;
import com.walksocket.er.component.main.root.workspace.Sequence;
import com.walksocket.er.component.main.root.workspace.Table;
import com.walksocket.er.custom.ErConnectorColor;
import com.walksocket.er.custom.ErConnectorEndpoint;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.OverlayLayout;
import javax.swing.Timer;
import javax.swing.text.JTextComponent;

/**
 * Outline.
 */
public class Outline extends JPanel {

  /**
   * root.
   */
  private final Root root;

  /**
   * view frame.
   */
  private final ViewFrame viewFrame;

  /**
   * search space.
   */
  private final SearchSpace searchSpace;

  /**
   * workspace.
   */
  private Workspace workspace;

  /**
   * side.
   */
  private Side side;

  /**
   * workspace paint manager.
   */
  private WorkspacePaintManager workspacePaintManager;

  /**
   * Constructor.
   *
   * @param root root
   */
  public Outline(Root root) {
    this.root = root;

    setLayout(new OverlayLayout(this));

    // view frame
    viewFrame = new ViewFrame(this);
    add(viewFrame);

    // search space
    searchSpace = new SearchSpace(this);
    add(searchSpace, JLayeredPane.DEFAULT_LAYER, 0);

    addMouseListener(new MouseAdapter() {
      @Override
      public void mousePressed(MouseEvent e) {
        if (e.getX() >= viewFrame.getX()
            && e.getY() >= viewFrame.getY()
            && e.getX() <= viewFrame.getX() + viewFrame.getWidth()
            && e.getY() <= viewFrame.getY() + viewFrame.getHeight()) {
          viewFrame.dispatchEvent(e);
        }
      }

      @Override
      public void mouseReleased(MouseEvent e) {
        if (e.getX() >= viewFrame.getX()
            && e.getY() >= viewFrame.getY()
            && e.getX() <= viewFrame.getX() + viewFrame.getWidth()
            && e.getY() <= viewFrame.getY() + viewFrame.getHeight()) {
          viewFrame.dispatchEvent(e);
        }
      }
    });
  }

  /**
   * set side.
   *
   * @param side side
   */
  public void setSide(Side side) {
    this.side = side;
  }

  /**
   * get search space.
   *
   * @return search space
   */
  public SearchSpace getSearchSpace() {
    return searchSpace;
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    if (workspace == null) {
      return;
    }
    if (workspacePaintManager == null) {
      return;
    }
    var lastCaptureImageInfo = workspacePaintManager.getLastCaptureImageInfo();
    var rect = lastCaptureImageInfo.rect;
    var captureImage = lastCaptureImageInfo.image;

    var w = getWidth();
    double wRatio = (double) getWidth() / (double) rect.width;
    double scaleRatio = wRatio;
    var h = (int) (wRatio * rect.height);
    if (h > getHeight()) {
      h = getHeight();
      double hRatio = (double) getHeight() / (double) rect.height;
      scaleRatio = hRatio;
      w = (int) (hRatio * rect.width);
    }
    viewFrame.updateScaleRatio(scaleRatio);

    var g2 = (Graphics2D) g;
    g2.drawImage(captureImage.getScaledInstance(w, h, Image.SCALE_DEFAULT), 0, 0, w, h,
        new Color(73, 70, 44, 8), null);

    var p = workspace.getParent();
    var pw = scaleRatio * (double) p.getWidth();
    var ph = scaleRatio * (double) p.getHeight();

    var sp = (JScrollPane) workspace.getParent().getParent();
    var sx = scaleRatio * (double) sp.getHorizontalScrollBar().getValue();
    var sy = scaleRatio * (double) sp.getVerticalScrollBar().getValue();

    if (viewFrame.getX() != (int) sx || viewFrame.getY() != (int) sy) {
      viewFrame.setLocation((int) sx, (int) sy);
    }
    if (viewFrame.getWidth() != (int) pw || viewFrame.getHeight() != (int) ph) {
      viewFrame.setSize((int) pw, (int) ph);
    }
  }

  /**
   * set workspace.
   *
   * @param workspace workspace
   */
  public void setWorkspace(Workspace workspace) {
    this.workspace = workspace;
    this.workspacePaintManager = new WorkspacePaintManager();
    viewFrame.setWorkspace(workspace);
  }

  /**
   * WorkspacePaintManager.
   */
  public class WorkspacePaintManager {

    /**
     * last capture image info.
     */
    private final CaptureImageInfo lastCaptureImageInfo = new CaptureImageInfo();

    /**
     * get last capture image info.
     *
     * @return capture image info
     */
    public CaptureImageInfo getLastCaptureImageInfo() {
      var now = Date.timestampMillis();

      if (now - lastCaptureImageInfo.lastTimestampMillis >= 500) {
        lastCaptureImageInfo.lastTimestampMillis = now;

        lastCaptureImageInfo.rect = workspace.getBounds();
        lastCaptureImageInfo.image =
            new BufferedImage(lastCaptureImageInfo.rect.width, lastCaptureImageInfo.rect.height,
                BufferedImage.TYPE_INT_ARGB);
        var cg = lastCaptureImageInfo.image.getGraphics();
        workspace.printComponents(cg);
        cg.dispose();
      }

      return lastCaptureImageInfo;
    }

    /**
     * CaptureImageInfo.
     */
    public class CaptureImageInfo {

      /**
       * last timestamp millis.
       */
      public long lastTimestampMillis;

      /**
       * image.
       */
      public BufferedImage image;

      /**
       * rect.
       */
      public Rectangle rect;
    }
  }

  /**
   * SearchSpace.
   */
  public class SearchSpace extends JPanel {

    /**
     * image icon prev.
     */
    private static BufferedImage imageIconPrev;

    static {
      try (var stream = App.class.getClassLoader().getResourceAsStream("image/icon_prev.png")) {
        imageIconPrev = ImageIO.read(stream);
      } catch (IOException e) {
        Log.error(e);
      }
    }

    /**
     * image icon next.
     */
    private static BufferedImage imageIconNext;

    static {
      try (var stream = App.class.getClassLoader().getResourceAsStream("image/icon_next.png")) {
        imageIconNext = ImageIO.read(stream);
      } catch (IOException e) {
        Log.error(e);
      }
    }

    /**
     * image icon close.
     */
    private static BufferedImage imageIconClose;

    static {
      try (var stream = App.class.getClassLoader().getResourceAsStream("image/icon_close.png")) {
        imageIconClose = ImageIO.read(stream);
      } catch (IOException e) {
        Log.error(e);
      }
    }

    /**
     * text field search.
     */
    private final JTextField textFieldSearch = new JTextField(10);

    /**
     * last search text
     */
    private String lastSearchText = "";

    /**
     * button prev.
     */
    private final JButton buttonPrev = new JButton(new ImageIcon(imageIconPrev));

    /**
     * button next.
     */
    private final JButton buttonNext = new JButton(new ImageIcon(imageIconNext));

    /**
     * label hit.
     */
    private final JLabel labelHit = new JLabel();

    /**
     * button close.
     */
    private final JButton buttonClose = new JButton(new ImageIcon(imageIconClose));

    /**
     * hit object list.
     */
    private final Set<ErConnectorEndpoint> hitObjectList = new HashSet<>();

    /**
     * direction.
     */
    private Direction direction = Direction.None;

    /**
     * cycled index.
     */
    private int cycledIndex;

    /**
     * Constructor.
     *
     * @param outline outline
     */
    public SearchSpace(Outline outline) {
      setLayout(new BorderLayout(0, 0));
      setVisible(false);
      setOpaque(false);

      var p = new JPanel(new FlowLayout(FlowLayout.LEFT));
      p.setBackground(Color.WHITE);
      add(p, BorderLayout.SOUTH);

      buttonClose.setBorder(null);
      buttonClose.setBackground(null);
      buttonClose.addMouseListener(new MouseAdapter() {
        @Override
        public void mousePressed(MouseEvent e) {
          hideSpace();
        }
      });
      p.add(buttonClose);

      textFieldSearch.addKeyListener(new KeyAdapter() {
        @Override
        public void keyReleased(KeyEvent e) {
          var tmpSearchText = textFieldSearch.getText();
          if (!tmpSearchText.equals(lastSearchText)) {
            hitObjectList.clear();
            lastSearchText = tmpSearchText;
          }

          if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            hideSpace();
            return;
          }

          if (e.isShiftDown() && e.getKeyCode() == KeyEvent.VK_ENTER) {
            movePrev();
          } else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            moveNext();
          }
        }
      });
      p.add(textFieldSearch);

      buttonPrev.setBorder(null);
      buttonPrev.setBackground(null);
      buttonPrev.addMouseListener(new MouseAdapter() {
        @Override
        public void mousePressed(MouseEvent e) {
          movePrev();
        }
      });
      p.add(buttonPrev);

      buttonNext.setBorder(null);
      buttonNext.setBackground(null);
      buttonNext.addMouseListener(new MouseAdapter() {
        @Override
        public void mousePressed(MouseEvent e) {
          moveNext();
        }
      });
      p.add(buttonNext);

      p.add(labelHit);
    }

    /**
     * show space.
     */
    public void showSpace() {
      setVisible(true);
      textFieldSearch.addFocusListener(new FocusAdapter() {
        @Override
        public void focusGained(FocusEvent e) {
          ((JTextComponent) e.getComponent()).selectAll();
        }
      });
      textFieldSearch.requestFocusInWindow();
    }

    /**
     * hide space.
     */
    public void hideSpace() {
      setVisible(false);
      textFieldSearch.setText("");
      hitObjectList.forEach(o -> o.setBorder(ErConnectorColor.DEFAULT_BORDER));
      hitObjectList.clear();
    }

    /**
     * get search text.
     *
     * @return search text
     */
    public String getSearchText() {
      return textFieldSearch.getText();
    }

    /**
     * add hit object.
     *
     * @param hitObject hitObject
     */
    public void addHitObject(ErConnectorEndpoint hitObject) {
      hitObjectList.add(hitObject);
    }

    /**
     * movie prev.
     */
    private void movePrev() {
      if (hitObjectList.isEmpty()) {
        return;
      }

      if (direction == Direction.Next) {
        cycledIndex -= 2;
      }
      direction = Direction.Prev;

      if (cycledIndex < 0) {
        cycledIndex = hitObjectList.size() - 1;
      }

      foundObject();

      cycledIndex--;
    }

    /**
     * movie next.
     */
    private void moveNext() {
      if (hitObjectList.isEmpty()) {
        return;
      }

      if (direction == Direction.Prev) {
        cycledIndex += 2;
      }
      direction = Direction.Next;

      if (cycledIndex >= hitObjectList.size()) {
        cycledIndex = 0;
      }

      foundObject();

      cycledIndex++;
    }

    /**
     * found object.
     */
    private void foundObject() {
      hitObjectList.forEach(o -> {
        o.setBorder(ErConnectorColor.DEFAULT_BORDER);
        workspace.remove(o.getFoundPanelInTime());
      });

      var focused = hitObjectList.stream()
          .sorted(Comparator.comparing(o -> o.getNameForSort()))
          .collect(Collectors.toUnmodifiableList())
          .get(cycledIndex);
      workspace.add(focused.getFoundPanelInTime());

      var timer = new Timer(2000, new java.awt.event.ActionListener() {
        @Override
        public void actionPerformed(java.awt.event.ActionEvent e) {
          workspace.remove(focused.getFoundPanelInTime());
        }
      });
      timer.setRepeats(false);
      timer.start();

      var sp = (JScrollPane) workspace.getParent().getParent();
      var bw = sp.getHorizontalScrollBar();
      var bh = sp.getVerticalScrollBar();
      bw.setValue(focused.getX());
      bh.setValue(focused.getY());

      if (side == null) {
        return;
      }
      if (focused instanceof Table table) {
        side.notifySelectionTable(table);
      } else if (focused instanceof Sequence sequence) {
        side.notifySelectionSequence(sequence);
      } else if (focused instanceof Note note) {
        side.notifySelectionNote(note);
      }
    }

    @Override
    public void paintComponent(Graphics g) {
      super.paintComponent(g);

      if (hitObjectList.isEmpty()) {
        buttonPrev.setEnabled(false);
        buttonPrev.setVisible(false);

        buttonNext.setEnabled(false);
        buttonNext.setVisible(false);

        labelHit.setVisible(false);

        direction = Direction.None;
        cycledIndex = 0;
      } else {
        buttonPrev.setEnabled(true);
        buttonPrev.setVisible(true);

        buttonNext.setEnabled(true);
        buttonNext.setVisible(true);

        labelHit.setVisible(true);
        labelHit.setText(String.format("%s OBJ", hitObjectList.size()));
      }
    }

    /**
     * Direction.
     */
    public enum Direction {
      None,
      Prev,
      Next
    }
  }
}
