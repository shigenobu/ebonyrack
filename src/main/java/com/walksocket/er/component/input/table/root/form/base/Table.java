package com.walksocket.er.component.input.table.root.form.base;

import com.walksocket.er.Utils;
import com.walksocket.er.Word;
import com.walksocket.er.definition.Charset;
import com.walksocket.er.definition.Collate;
import com.walksocket.er.definition.Engine;
import com.walksocket.er.sqlite.Tmp;
import com.walksocket.er.sqlite.TmpResult;
import com.walksocket.er.sqlite.context.CtxTable;
import com.walksocket.er.sqlite.tmp.TmpTable;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.JTextComponent;

/**
 * Table.
 */
public class Table extends JPanel {

  /**
   * ctx table.
   */
  private final CtxTable ctxTable;

  /**
   * label table name.
   */
  private final JLabel labelTableName = new JLabel("Table name:");

  /**
   * text field table name.
   */
  private final JTextField textFieldTableName = new JTextField(20);

  /**
   * label table comment.
   */
  private final JLabel labelTableComment = new JLabel("Table comment:");

  /**
   * text field table comment.
   */
  private final JTextField textFieldTableComment = new JTextField(20);

  /**
   * label engine.
   */
  private final JLabel labelEngine = new JLabel("Engine:");

  /**
   * combo box engine
   */
  private final JComboBox comboBoxEngine = new JComboBox(Engine.getEngineListForTable().toArray());

  /**
   * label charset.
   */
  private final JLabel labelCharset = new JLabel("Charset:");

  /**
   * combo box charset.
   */
  private final JComboBox comboBoxCharset = new JComboBox(
      Charset.getCharsetListForTable().toArray());

  /**
   * label collate.
   */
  private final JLabel labelCollate = new JLabel("Collate:");

  /**
   * combo box collate.
   */
  private final JComboBox comboBoxCollate = new JComboBox(
      Collate.getCollateListForTable().toArray());

  /**
   * label auto increment.
   */
  private final JLabel labelAutoIncrement = new JLabel("Auto increment:");

  /**
   * text field auto increment.
   */
  private final JTextField textFieldAutoIncrement = new JTextField(20);

  /**
   * label option.
   */
  private final JLabel labelOption = new JLabel("Option:");

  /**
   * text field option.
   */
  private final JTextField textFieldOption = new JTextField(30);

  /**
   * Constructor.
   *
   * @param ctxTable ctxTable
   */
  public Table(CtxTable ctxTable) {
    this.ctxTable = ctxTable;

    // layout
    setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));

    // panel - table name
    var panel1 = new JPanel();
    add(panel1);
    panel1.add(labelTableName);
    textFieldTableName.addFocusListener(new FocusAdapter() {
      @Override
      public void focusGained(FocusEvent e) {
        ((JTextComponent) e.getComponent()).selectAll();
      }
    });
    panel1.add(textFieldTableName);

    // panel - table comment
    var panel2 = new JPanel();
    add(panel2);
    panel2.add(labelTableComment);
    textFieldTableComment.addFocusListener(new FocusAdapter() {
      @Override
      public void focusGained(FocusEvent e) {
        ((JTextComponent) e.getComponent()).selectAll();
      }
    });
    panel2.add(textFieldTableComment);

    // panel - engine
    var panel3 = new JPanel();
    add(panel3);
    panel3.add(labelEngine);
    comboBoxEngine.setPreferredSize(new Dimension(150, comboBoxEngine.getFont().getSize() * 2));
    comboBoxEngine.setEditable(true);
    panel3.add(comboBoxEngine);

    // panel - charset
    var panel4 = new JPanel();
    add(panel4);
    panel4.add(labelCharset);
    comboBoxCharset.setPreferredSize(new Dimension(150, comboBoxCharset.getFont().getSize() * 2));
    comboBoxCharset.setEditable(true);
    panel4.add(comboBoxCharset);

    // panel - collate
    var panel5 = new JPanel();
    add(panel5);
    panel5.add(labelCollate);
    comboBoxCollate.setPreferredSize(new Dimension(150, comboBoxCollate.getFont().getSize() * 2));
    comboBoxCollate.setEditable(true);
    panel5.add(comboBoxCollate);

    // panel - auto increment
    var panel6 = new JPanel();
    add(panel6);
    panel6.add(labelAutoIncrement);
    panel6.add(textFieldAutoIncrement);

    // panel - option
    var panel7 = new JPanel();
    add(panel7);
    panel7.add(labelOption);
    panel7.add(textFieldOption);

    // tmp table
    var tmpTable = Tmp.createTmpTable(ctxTable.dbTable);
    setTmpTable(tmpTable);
  }

  /**
   * set tmp table.
   *
   * @param tmpTable tmpTable
   */
  private void setTmpTable(TmpTable tmpTable) {
    textFieldTableName.setText(tmpTable.tableName);
    textFieldTableComment.setText(tmpTable.tableComment);
    if (Utils.isNullOrEmpty(tmpTable.engine)) {
      tmpTable.engine = Engine.DEFAULT_ENGINE;
    }
    comboBoxEngine.setSelectedItem(tmpTable.engine);
    if (Utils.isNullOrEmpty(tmpTable.charsetValue)) {
      tmpTable.charsetValue = Charset.DEFAULT_CHARSET;
    }
    comboBoxCharset.setSelectedItem(tmpTable.charsetValue);
    if (Utils.isNullOrEmpty(tmpTable.collateValue)) {
      tmpTable.collateValue = Collate.DEFAULT_COLLATE;
    }
    comboBoxCollate.setSelectedItem(tmpTable.collateValue);
    textFieldAutoIncrement.setText(tmpTable.autoIncrementValue);
    textFieldOption.setText(tmpTable.option);
  }

  /**
   * get tmp table.
   *
   * @return tmp table
   */
  public TmpTable getTmpTable() {
    var tmpTable = new TmpTable();
    tmpTable.tableName = Utils.getString(textFieldTableName);
    tmpTable.tableComment = Utils.getString(textFieldTableComment);
    tmpTable.engine = Utils.getString(comboBoxEngine);
    tmpTable.charsetValue = Utils.getString(comboBoxCharset);
    tmpTable.collateValue = Utils.getString(comboBoxCollate);
    tmpTable.autoIncrementValue = Utils.getString(textFieldAutoIncrement);
    tmpTable.option = Utils.getString(textFieldOption);
    return tmpTable;
  }

  /**
   * get result.
   *
   * @return result
   */
  public TmpResult<TmpTable> getResult() {
    var tmpTable = getTmpTable();

    return new TmpResult<TmpTable>(tmpTable) {
      @Override
      protected void validate() throws Exception {
        for (var tmp : tmpList) {
          if (Utils.isNullOrEmpty(tmp.tableName)) {
            throw new Exception("Required 'Table name'.");
          }
          if (!Word.isValid(tmp.tableName)) {
            throw new Exception("Invalid 'Table name'.");
          }

          if (tmp.engine.equals(Engine.DEFAULT_ENGINE)) {
            tmp.engine = "";
          }

          if (tmp.charsetValue.equals(Charset.DEFAULT_CHARSET)) {
            tmp.charsetValue = "";
          }

          if (tmp.collateValue.equals(Collate.DEFAULT_COLLATE)) {
            tmp.collateValue = "";
          }

          if (!Utils.isNullOrEmpty(tmp.autoIncrementValue) && !Utils.isNumber(
              tmp.autoIncrementValue)) {
            throw new Exception("Must be number 'Auto increment'.");
          }
        }
      }
    };
  }
}
