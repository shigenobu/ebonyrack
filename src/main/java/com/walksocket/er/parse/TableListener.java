package com.walksocket.er.parse;

import com.walksocket.antlr4.MariaDBParser.AutoIncrementColumnConstraintContext;
import com.walksocket.antlr4.MariaDBParser.CharSetContext;
import com.walksocket.antlr4.MariaDBParser.CharsetNameContext;
import com.walksocket.antlr4.MariaDBParser.CollationNameContext;
import com.walksocket.antlr4.MariaDBParser.CollectionOptionsContext;
import com.walksocket.antlr4.MariaDBParser.ColumnCreateTableContext;
import com.walksocket.antlr4.MariaDBParser.ColumnDeclarationContext;
import com.walksocket.antlr4.MariaDBParser.CommentColumnConstraintContext;
import com.walksocket.antlr4.MariaDBParser.ConstraintDeclarationContext;
import com.walksocket.antlr4.MariaDBParser.CurrentTimestampContext;
import com.walksocket.antlr4.MariaDBParser.DefaultColumnConstraintContext;
import com.walksocket.antlr4.MariaDBParser.IndexDeclarationContext;
import com.walksocket.antlr4.MariaDBParser.LengthOneDimensionContext;
import com.walksocket.antlr4.MariaDBParser.LengthTwoDimensionContext;
import com.walksocket.antlr4.MariaDBParser.LengthTwoOptionalDimensionContext;
import com.walksocket.antlr4.MariaDBParser.NullColumnConstraintContext;
import com.walksocket.antlr4.MariaDBParser.PrimaryKeyTableConstraintContext;
import com.walksocket.antlr4.MariaDBParser.SimpleIndexDeclarationContext;
import com.walksocket.antlr4.MariaDBParser.TableOptionAutoIncrementContext;
import com.walksocket.antlr4.MariaDBParser.TableOptionCharsetContext;
import com.walksocket.antlr4.MariaDBParser.TableOptionCollateContext;
import com.walksocket.antlr4.MariaDBParser.TableOptionCommentContext;
import com.walksocket.antlr4.MariaDBParser.TableOptionEngineContext;
import com.walksocket.antlr4.MariaDBParser.UniqueKeyTableConstraintContext;
import com.walksocket.antlr4.MariaDBParserBaseListener;
import com.walksocket.er.Json;
import com.walksocket.er.Log;
import com.walksocket.er.Utils;
import com.walksocket.er.parts.ColumnKeyOption;
import com.walksocket.er.sqlite.tmp.TmpColumn;
import com.walksocket.er.sqlite.tmp.TmpKey;
import com.walksocket.er.sqlite.tmp.TmpTable;
import java.util.List;
import java.util.stream.Collectors;

/**
 * TableListener.
 */
public class TableListener extends MariaDBParserBaseListener {

  /**
   * tmpTable.
   */
  private final TmpTable tmpTable;

  /**
   * tmpColumnList.
   */
  private final List<TmpColumn> tmpColumnList;

  /**
   * tmpPrimaryKey.
   */
  private final TmpKey tmpPrimaryKey;

  /**
   * tmpUniqueKeyList.
   */
  private final List<TmpKey> tmpUniqueKeyList;

  /**
   * tmpKeyList.
   */
  private final List<TmpKey> tmpKeyList;

  /**
   * Constructor.
   *
   * @param tmpTable         tmpTable
   * @param tmpColumnList    tmpColumnList
   * @param tmpPrimaryKey    tmpPrimaryKey
   * @param tmpUniqueKeyList tmpUniqueKeyList
   * @param tmpKeyList       tmpKeyList
   */
  public TableListener(TmpTable tmpTable, List<TmpColumn> tmpColumnList, TmpKey tmpPrimaryKey,
      List<TmpKey> tmpUniqueKeyList, List<TmpKey> tmpKeyList) {
    this.tmpTable = tmpTable;
    this.tmpColumnList = tmpColumnList;
    this.tmpPrimaryKey = tmpPrimaryKey;
    this.tmpUniqueKeyList = tmpUniqueKeyList;
    this.tmpKeyList = tmpKeyList;
  }

  @Override
  public void enterColumnCreateTable(ColumnCreateTableContext ctx) {
    // table
    parseTable(ctx);

    for (var definition : ctx.createDefinitions().createDefinition()) {
      // column
      if (definition instanceof ColumnDeclarationContext columnDeclarationContext) {
        parseColumn(columnDeclarationContext);
      }

      if (definition instanceof ConstraintDeclarationContext constraintDeclarationContext) {
        // primary key
        if (constraintDeclarationContext.tableConstraint() instanceof PrimaryKeyTableConstraintContext primaryKeyTableConstraintContext) {
          parsePrimaryKey(primaryKeyTableConstraintContext);
        }

        // unique key
        if (constraintDeclarationContext.tableConstraint() instanceof UniqueKeyTableConstraintContext uniqueKeyTableConstraintContext) {
          parseUniqueKey(uniqueKeyTableConstraintContext);
        }
      }
    }
  }

  /**
   * parse table.
   *
   * @param ctx ctx
   */
  private void parseTable(ColumnCreateTableContext ctx) {
    tmpTable.tableName = Utils.removeBackslash(ctx.tableName().getText());
    for (var opt : ctx.tableOption()) {
      if (opt instanceof TableOptionCommentContext tableOptionCommentContext) {
        tmpTable.tableComment = Utils.removeSingleQuote(
            tableOptionCommentContext.STRING_LITERAL().getText());
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
    Log.trace(Json.toJsonString(tmpTable));
  }

  /**
   * parse column.
   *
   * @param columnDeclarationContext columnDeclarationContext
   */
  private void parseColumn(ColumnDeclarationContext columnDeclarationContext) {
    var columnName = Utils.removeBackslash(columnDeclarationContext.uid().getText());
    if (Utils.isNullOrEmpty(columnName)) {
      return;
    }

    var tmpColumn = new TmpColumn();
    tmpColumnList.add(tmpColumn);
    tmpColumn.columnName = columnName;

    var dataTypeBuilder = new StringBuilder();
    var sep = "";
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

      var dataType = dc.getText();
      if (!(dc instanceof LengthOneDimensionContext
          || dc instanceof LengthTwoDimensionContext
          || dc instanceof LengthTwoOptionalDimensionContext
          || dc instanceof CollectionOptionsContext)) {
        dataType = sep + dataType;
        sep = " ";
      }
      dataTypeBuilder.append(dataType);
    }
    tmpColumn.columnType = dataTypeBuilder.toString();

    for (var constraint : columnDeclarationContext.columnDefinition().columnConstraint()) {
      if (constraint instanceof CommentColumnConstraintContext commentColumnConstraintContext) {
        tmpColumn.columnComment = Utils.removeSingleQuote(
            commentColumnConstraintContext.STRING_LITERAL()
                .getText());
      }

      if (constraint instanceof NullColumnConstraintContext nullColumnConstraintContext) {
        tmpColumn.notNullValue = nullColumnConstraintContext.nullNotnull().children.stream()
            .map(t -> t.getText().toUpperCase())
            .collect(Collectors.joining(" "));
      }

      if (constraint instanceof DefaultColumnConstraintContext defaultColumnConstraintContext) {
        tmpColumn.defaultValue = defaultColumnConstraintContext.defaultValue().getText();
      }

      if (constraint instanceof AutoIncrementColumnConstraintContext autoIncrementColumnConstraintContext) {
        if (autoIncrementColumnConstraintContext.AUTO_INCREMENT() != null) {
          tmpColumn.autoIncrementDefinition = autoIncrementColumnConstraintContext.AUTO_INCREMENT()
              .getText().toUpperCase();
        } else if (autoIncrementColumnConstraintContext.ON() != null) {
          for (int ai = 0; ai < autoIncrementColumnConstraintContext.getChildCount(); ai++) {
            var aic = autoIncrementColumnConstraintContext.getChild(ai);
            if (aic instanceof CurrentTimestampContext currentTimestampContext) {
              tmpColumn.onUpdate = currentTimestampContext.getText().toUpperCase();
            }
          }
        }
      }
    }
    Log.trace(Json.toJsonString(tmpColumn));
  }

  /**
   * parse primary key.
   *
   * @param primaryKeyTableConstraintContext primaryKeyTableConstraintContext
   */
  private void parsePrimaryKey(PrimaryKeyTableConstraintContext primaryKeyTableConstraintContext) {
    tmpPrimaryKey.constraintName = "PRIMARY";
    tmpPrimaryKey.keyName = tmpPrimaryKey.constraintName;

    for (var opt : primaryKeyTableConstraintContext.indexOption()) {
      if (opt.STRING_LITERAL() != null) {
        tmpPrimaryKey.indexComment = Utils.removeSingleQuote(opt.STRING_LITERAL().getText());
      }

      if (opt.indexType() != null) {
        if (opt.indexType().BTREE() != null) {
          tmpPrimaryKey.indexType = opt.indexType().BTREE().getText().toUpperCase();
        } else if (opt.indexType().HASH() != null) {
          tmpPrimaryKey.indexType = opt.indexType().HASH().getText().toUpperCase();
        } else if (opt.indexType().RTREE() != null) {
          tmpPrimaryKey.indexType = opt.indexType().RTREE().getText().toUpperCase();
        }
      }
    }

    var seqInIndex = 1;
    var indexColumnNames = primaryKeyTableConstraintContext.indexColumnNames();
    for (var indexColumnName : indexColumnNames.indexColumnName()) {
      var columnKeyOption = new ColumnKeyOption();
      tmpPrimaryKey.columnKeyOptionList.add(columnKeyOption);

      columnKeyOption.columnName = Utils.removeBackslash(indexColumnName.uid().getText());
      columnKeyOption.seqInIndex = String.valueOf(seqInIndex);

      if (indexColumnName.decimalLiteral() != null) {
        columnKeyOption.length = indexColumnName.decimalLiteral().getText();
      }
      if (indexColumnName.ASC() != null) {
        columnKeyOption.collation = indexColumnName.ASC().getText().toUpperCase();
      }
      if (indexColumnName.DESC() != null) {
        columnKeyOption.collation = indexColumnName.DESC().getText().toUpperCase();
      }

      seqInIndex++;
    }
    Log.trace(Json.toJsonString(tmpPrimaryKey));
  }

  /**
   * parse unique key.
   *
   * @param uniqueKeyTableConstraintContext uniqueKeyTableConstraintContext
   */
  private void parseUniqueKey(UniqueKeyTableConstraintContext uniqueKeyTableConstraintContext) {
    var tmpUniqueKey = new TmpKey();
    tmpUniqueKeyList.add(tmpUniqueKey);

    tmpUniqueKey.constraintName = Utils.removeBackslash(
        uniqueKeyTableConstraintContext.index.getText());
    tmpUniqueKey.keyName = tmpUniqueKey.constraintName;

    for (var opt : uniqueKeyTableConstraintContext.indexOption()) {
      if (opt.STRING_LITERAL() != null) {
        tmpUniqueKey.indexComment = Utils.removeSingleQuote(opt.STRING_LITERAL().getText());
      }

      if (opt.indexType() != null) {
        if (opt.indexType().BTREE() != null) {
          tmpUniqueKey.indexType = opt.indexType().BTREE().getText().toUpperCase();
        } else if (opt.indexType().HASH() != null) {
          tmpUniqueKey.indexType = opt.indexType().HASH().getText().toUpperCase();
        } else if (opt.indexType().RTREE() != null) {
          tmpUniqueKey.indexType = opt.indexType().RTREE().getText().toUpperCase();
        }
      }
    }

    var seqInIndex = 1;
    var indexColumnNames = uniqueKeyTableConstraintContext.indexColumnNames();
    for (var indexColumnName : indexColumnNames.indexColumnName()) {
      var columnKeyOption = new ColumnKeyOption();
      tmpUniqueKey.columnKeyOptionList.add(columnKeyOption);

      columnKeyOption.columnName = Utils.removeBackslash(indexColumnName.uid().getText());
      columnKeyOption.seqInIndex = String.valueOf(seqInIndex);

      if (indexColumnName.decimalLiteral() != null) {
        columnKeyOption.length = indexColumnName.decimalLiteral().getText();
      }
      if (indexColumnName.ASC() != null) {
        columnKeyOption.collation = indexColumnName.ASC().getText().toUpperCase();
      }
      if (indexColumnName.DESC() != null) {
        columnKeyOption.collation = indexColumnName.DESC().getText().toUpperCase();
      }

      seqInIndex++;
    }
    Log.trace(Json.toJsonString(tmpUniqueKey));
  }

  @Override
  public void enterIndexDeclaration(IndexDeclarationContext ctx) {
    var definition = ctx.indexColumnDefinition();
    if (definition instanceof SimpleIndexDeclarationContext simpleIndexDeclarationContext) {
      var tmpKey = new TmpKey();
      tmpKeyList.add(tmpKey);

      tmpKey.constraintName = Utils.removeBackslash(simpleIndexDeclarationContext.uid().getText());
      tmpKey.keyName = tmpKey.constraintName;

      for (var opt : simpleIndexDeclarationContext.indexOption()) {
        if (opt.STRING_LITERAL() != null) {
          tmpKey.indexComment = Utils.removeSingleQuote(opt.STRING_LITERAL().getText());
        }

        if (opt.indexType() != null) {
          if (opt.indexType().BTREE() != null) {
            tmpKey.indexType = opt.indexType().BTREE().getText().toUpperCase();
          } else if (opt.indexType().HASH() != null) {
            tmpKey.indexType = opt.indexType().HASH().getText().toUpperCase();
          } else if (opt.indexType().RTREE() != null) {
            tmpKey.indexType = opt.indexType().RTREE().getText().toUpperCase();
          }
        }
      }

      var seqInIndex = 1;
      var indexColumnNames = simpleIndexDeclarationContext.indexColumnNames();
      for (var indexColumnName : indexColumnNames.indexColumnName()) {
        var columnKeyOption = new ColumnKeyOption();
        tmpKey.columnKeyOptionList.add(columnKeyOption);

        columnKeyOption.columnName = Utils.removeBackslash(indexColumnName.uid().getText());
        columnKeyOption.seqInIndex = String.valueOf(seqInIndex);

        if (indexColumnName.decimalLiteral() != null) {
          columnKeyOption.length = indexColumnName.decimalLiteral().getText();
        }
        if (indexColumnName.ASC() != null) {
          columnKeyOption.collation = indexColumnName.ASC().getText().toUpperCase();
        }
        if (indexColumnName.DESC() != null) {
          columnKeyOption.collation = indexColumnName.DESC().getText().toUpperCase();
        }

        seqInIndex++;
      }
      Log.trace(Json.toJsonString(tmpKey));
    }
  }
}
