package com.walksocket.er.parse;

import com.walksocket.er.Utils;
import com.walksocket.er.antlr4.MariaDBParser.AutoIncrementColumnConstraintContext;
import com.walksocket.er.antlr4.MariaDBParser.CharSetContext;
import com.walksocket.er.antlr4.MariaDBParser.CharsetNameContext;
import com.walksocket.er.antlr4.MariaDBParser.CollationNameContext;
import com.walksocket.er.antlr4.MariaDBParser.ColumnCreateTableContext;
import com.walksocket.er.antlr4.MariaDBParser.ColumnDeclarationContext;
import com.walksocket.er.antlr4.MariaDBParser.CommentColumnConstraintContext;
import com.walksocket.er.antlr4.MariaDBParser.CurrentTimestampContext;
import com.walksocket.er.antlr4.MariaDBParser.DefaultColumnConstraintContext;
import com.walksocket.er.antlr4.MariaDBParser.NullColumnConstraintContext;
import com.walksocket.er.antlr4.MariaDBParser.TableOptionAutoIncrementContext;
import com.walksocket.er.antlr4.MariaDBParser.TableOptionCharsetContext;
import com.walksocket.er.antlr4.MariaDBParser.TableOptionCollateContext;
import com.walksocket.er.antlr4.MariaDBParser.TableOptionCommentContext;
import com.walksocket.er.antlr4.MariaDBParser.TableOptionEngineContext;
import com.walksocket.er.antlr4.MariaDBParserBaseListener;
import com.walksocket.er.sqlite.tmp.TmpColumn;
import com.walksocket.er.sqlite.tmp.TmpTable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * TableListener.
 */
public class TableListener extends MariaDBParserBaseListener {

  private final TmpTable tmpTable;
  private final List<TmpColumn> tmpColumnList;

  public TableListener(TmpTable tmpTable, List<TmpColumn> tmpColumnList) {
    this.tmpTable = tmpTable;
    this.tmpColumnList = tmpColumnList;
  }

  @Override
  public void enterColumnCreateTable(ColumnCreateTableContext ctx) {
    tmpTable.tableName = Utils.removeBackslash(ctx.tableName().getText());
    for (var opt : ctx.tableOption()) {
      if (opt instanceof TableOptionCommentContext tableOptionCommentContext) {
        tmpTable.tableComment = Utils.removeSingleQuote(tableOptionCommentContext.STRING_LITERAL().getText());
      }
      if (opt instanceof TableOptionEngineContext tableOptionEngineContext) {
        tmpTable.engine = tableOptionEngineContext.engineName().getText();
      }
      if (opt instanceof TableOptionCharsetContext tableOptionCharsetContext) {
        tmpTable.charsetValue = tableOptionCharsetContext.charsetName().getText();
      }
      if (opt instanceof TableOptionCollateContext tableOptionCollateContext) {
        tmpTable.collateValue = tableOptionCollateContext.collationName().getText();
      }
      if (opt instanceof TableOptionAutoIncrementContext tableOptionAutoIncrementContext) {
        tmpTable.autoIncrementValue = tableOptionAutoIncrementContext.decimalLiteral().getText();
      }
    }

    for (var definition : ctx.createDefinitions().createDefinition()) {
      if (definition instanceof ColumnDeclarationContext columnDeclarationContext) {
        var columnName = Utils.removeBackslash(columnDeclarationContext.uid().getText());
        if (Utils.isNullOrEmpty(columnName)) {
          continue;
        }

        var tmpColumn = new TmpColumn();
        tmpColumnList.add(tmpColumn);
        tmpColumn.columnName = columnName;

        var dataTypeList = new ArrayList<String>();
        for (var dc : columnDeclarationContext.columnDefinition().dataType().children) {
          if (dc instanceof CharSetContext) {
            continue;
          }
          if (dc instanceof CharsetNameContext charsetNameContext) {
            tmpColumn.charsetValue = charsetNameContext.getText();
            continue;
          }
          if (dc.getText().equalsIgnoreCase("COLLATE")) {
            continue;
          }
          if (dc instanceof CollationNameContext collationNameContext) {
            tmpColumn.collateValue = collationNameContext.getText();
            continue;
          }
          dataTypeList.add(dc.getText());
        }
        tmpColumn.columnType = dataTypeList.stream()
            .collect(Collectors.joining(" "));

        for (var constraint : columnDeclarationContext.columnDefinition().columnConstraint()) {
          if (constraint instanceof CommentColumnConstraintContext commentColumnConstraintContext) {
            tmpColumn.columnComment = Utils.removeSingleQuote(commentColumnConstraintContext.STRING_LITERAL()
                .getText());
          }

          if (constraint instanceof NullColumnConstraintContext nullColumnConstraintContext) {
            tmpColumn.notNullValue = nullColumnConstraintContext.nullNotnull().children.stream()
                .map(t -> t.getText())
                .collect(Collectors.joining(" "));
          }

          if (constraint instanceof DefaultColumnConstraintContext defaultColumnConstraintContext) {
            tmpColumn.defaultValue = defaultColumnConstraintContext.defaultValue().getText();
          }

          if (constraint instanceof AutoIncrementColumnConstraintContext autoIncrementColumnConstraintContext) {
            if (autoIncrementColumnConstraintContext.AUTO_INCREMENT() != null) {
              tmpColumn.autoIncrementDefinition = autoIncrementColumnConstraintContext.AUTO_INCREMENT()
                  .getText();
            } else if (autoIncrementColumnConstraintContext.ON() != null) {
              for (int ai = 0; ai < autoIncrementColumnConstraintContext.getChildCount(); ai++) {
                var aic = autoIncrementColumnConstraintContext.getChild(ai);
                if (aic instanceof CurrentTimestampContext currentTimestampContext) {
                  tmpColumn.onUpdate = currentTimestampContext.getText();
                }
              }
            }
          }
        }
      }
    }
  }
}
