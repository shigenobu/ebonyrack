package com.walksocket.er.parse;

import com.walksocket.antlr4.MariaDBParser.AutoIncrementColumnConstraintContext;
import com.walksocket.antlr4.MariaDBParser.CharSetContext;
import com.walksocket.antlr4.MariaDBParser.CharsetNameContext;
import com.walksocket.antlr4.MariaDBParser.CheckColumnConstraintContext;
import com.walksocket.antlr4.MariaDBParser.CheckTableConstraintContext;
import com.walksocket.antlr4.MariaDBParser.CollationNameContext;
import com.walksocket.antlr4.MariaDBParser.CollectionOptionsContext;
import com.walksocket.antlr4.MariaDBParser.ColumnCreateTableContext;
import com.walksocket.antlr4.MariaDBParser.ColumnDeclarationContext;
import com.walksocket.antlr4.MariaDBParser.CommentColumnConstraintContext;
import com.walksocket.antlr4.MariaDBParser.ConstraintDeclarationContext;
import com.walksocket.antlr4.MariaDBParser.CurrentTimestampContext;
import com.walksocket.antlr4.MariaDBParser.DefaultColumnConstraintContext;
import com.walksocket.antlr4.MariaDBParser.ForeignKeyTableConstraintContext;
import com.walksocket.antlr4.MariaDBParser.GeneratedColumnConstraintContext;
import com.walksocket.antlr4.MariaDBParser.IndexDeclarationContext;
import com.walksocket.antlr4.MariaDBParser.LengthOneDimensionContext;
import com.walksocket.antlr4.MariaDBParser.LengthTwoDimensionContext;
import com.walksocket.antlr4.MariaDBParser.LengthTwoOptionalDimensionContext;
import com.walksocket.antlr4.MariaDBParser.NullColumnConstraintContext;
import com.walksocket.antlr4.MariaDBParser.NullNotnullContext;
import com.walksocket.antlr4.MariaDBParser.PartitionDefinitionsContext;
import com.walksocket.antlr4.MariaDBParser.PredicateContext;
import com.walksocket.antlr4.MariaDBParser.PredicateExpressionContext;
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
import com.walksocket.er.sqlite.tmp.TmpCheck;
import com.walksocket.er.sqlite.tmp.TmpColumn;
import com.walksocket.er.sqlite.tmp.TmpKey;
import com.walksocket.er.sqlite.tmp.TmpPartition;
import com.walksocket.er.sqlite.tmp.TmpTable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNodeImpl;

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
   * tmpCheckList.
   */
  private final List<TmpCheck> tmpCheckList;

  /**
   * tmpPartition.
   */
  private final TmpPartition tmpPartition;

  /**
   * partialForeignKeyDdlList.
   */
  private final List<String> partialForeignKeyDdlList;

  /**
   * Constructor.
   *
   * @param tmpTable                 tmpTable
   * @param tmpColumnList            tmpColumnList
   * @param tmpPrimaryKey            tmpPrimaryKey
   * @param tmpUniqueKeyList         tmpUniqueKeyList
   * @param tmpKeyList               tmpKeyList
   * @param tmpCheckList             tmpCheckList
   * @param tmpPartition             tmpPartition
   * @param partialForeignKeyDdlList partialForeignKeyDdlList
   */
  public TableListener(TmpTable tmpTable, List<TmpColumn> tmpColumnList, TmpKey tmpPrimaryKey,
      List<TmpKey> tmpUniqueKeyList, List<TmpKey> tmpKeyList, List<TmpCheck> tmpCheckList,
      TmpPartition tmpPartition, List<String> partialForeignKeyDdlList) {
    this.tmpTable = tmpTable;
    this.tmpColumnList = tmpColumnList;
    this.tmpPrimaryKey = tmpPrimaryKey;
    this.tmpUniqueKeyList = tmpUniqueKeyList;
    this.tmpKeyList = tmpKeyList;
    this.tmpCheckList = tmpCheckList;
    this.tmpPartition = tmpPartition;
    this.partialForeignKeyDdlList = partialForeignKeyDdlList;
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

        // check
        if (constraintDeclarationContext.tableConstraint() instanceof CheckTableConstraintContext checkTableConstraintContext) {
          parseCheck(checkTableConstraintContext);
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
    tmpTable.tableName = Utils.removeBackQuote(ctx.tableName().getText());
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
    var columnName = Utils.removeBackQuote(columnDeclarationContext.uid().getText());
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

    var optionList = new ArrayList<String>();
    for (var constraint : columnDeclarationContext.columnDefinition().columnConstraint()) {
      // column comment
      if (constraint instanceof CommentColumnConstraintContext commentColumnConstraintContext) {
        tmpColumn.columnComment = Utils.removeSingleQuote(
            commentColumnConstraintContext.STRING_LITERAL()
                .getText());
      }

      // not null
      if (constraint instanceof NullColumnConstraintContext nullColumnConstraintContext) {
        tmpColumn.notNullValue = nullColumnConstraintContext.nullNotnull().children.stream()
            .map(t -> t.getText().toUpperCase())
            .collect(Collectors.joining(" "));
      }

      // default
      if (constraint instanceof DefaultColumnConstraintContext defaultColumnConstraintContext) {
        tmpColumn.defaultValue = defaultColumnConstraintContext.defaultValue().getText();
        var len = defaultColumnConstraintContext.defaultValue().getChildCount();
        for (int i = 0; i < len; i++) {
          var c = defaultColumnConstraintContext.defaultValue().getChild(i);
          if (c.getText().equalsIgnoreCase("ON")) {
            if (i + 1 < len) {
              var cNext1 = defaultColumnConstraintContext.defaultValue().getChild(i + 1);
              if (cNext1.getText().equalsIgnoreCase("UPDATE")) {
                if (i + 2 < len) {
                  var cNext2 = defaultColumnConstraintContext.defaultValue().getChild(i + 2);
                  if (cNext2 instanceof CurrentTimestampContext currentTimestampContext) {
                    tmpColumn.defaultValue = currentTimestampContext.getText().toUpperCase();
                    tmpColumn.onUpdate = currentTimestampContext.getText().toUpperCase();
                  }
                }
              }
            }
          }
        }
      }

      // auto increment, on update
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

      // option
      if (constraint instanceof GeneratedColumnConstraintContext generatedColumnConstraintContext) {
        var generatedExpression = "";
        for (var c : generatedColumnConstraintContext.expression().children) {
          if (c instanceof PredicateContext predicateContext) {
            for (var cc : predicateContext.children) {
              var generatedExpressionList = new ArrayList<String>();
              for (int i = 0; i < cc.getChildCount(); i++) {
                generatedExpressionList.add(cc.getChild(i).getText());
              }
              generatedExpression = generatedExpressionList.stream()
                  .collect(Collectors.joining(" "));
            }
          }
        }
        var generatedType = "";
        if (generatedColumnConstraintContext.VIRTUAL() != null) {
          generatedType = generatedColumnConstraintContext.VIRTUAL().getText();
        } else if (generatedColumnConstraintContext.STORED() != null) {
          generatedType = generatedColumnConstraintContext.STORED().getText();
        } else if (generatedColumnConstraintContext.PERSISTENT() != null) {
          generatedType = generatedColumnConstraintContext.PERSISTENT().getText();
        }
        if (!Utils.isNullOrEmpty(generatedExpression) && !Utils.isNullOrEmpty(generatedType)) {
          optionList.add(
              String.format("GENERATED ALWAYS AS (%s) %s", generatedExpression, generatedType));
        }
      }
      if (constraint instanceof CheckColumnConstraintContext checkColumnConstraintContext) {
        var checkExpression = "";
        for (var c : checkColumnConstraintContext.children) {
          if (c instanceof PredicateExpressionContext predicateExpressionContext) {
            for (var cc : predicateExpressionContext.children) {
              var checkExpressionList = new ArrayList<String>();
              for (int i = 0; i < cc.getChildCount(); i++) {
                if (cc.getChild(i) instanceof NullNotnullContext nullNotnullContext) {
                  checkExpressionList.add(
                      nullNotnullContext.children.stream().map(t -> t.getText()).collect(
                          Collectors.joining(" ")));
                } else {
                  checkExpressionList.add(cc.getChild(i).getText());
                }
              }
              checkExpression = checkExpressionList.stream()
                  .collect(Collectors.joining(" "));
            }
          }
        }
        if (!Utils.isNullOrEmpty(checkExpression)) {
          optionList.add(String.format("CHECK (%s)", checkExpression));
        }
      }
    }
    if (!Utils.isNullOrEmpty(optionList)) {
      tmpColumn.option = optionList.stream().collect(Collectors.joining(" "));
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

      columnKeyOption.columnName = Utils.removeBackQuote(indexColumnName.uid().getText());
      columnKeyOption.seqInIndex = seqInIndex;

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

    if (uniqueKeyTableConstraintContext.index != null) {
      tmpUniqueKey.constraintName = Utils.removeBackQuote(
          uniqueKeyTableConstraintContext.index.getText());
      tmpUniqueKey.keyName = tmpUniqueKey.constraintName;
    }

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

      columnKeyOption.columnName = Utils.removeBackQuote(indexColumnName.uid().getText());
      columnKeyOption.seqInIndex = seqInIndex;

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

    if (Utils.isNullOrEmpty(tmpUniqueKey.constraintName)
        && tmpUniqueKey.columnKeyOptionList.size() > 0) {
      var constraintName = tmpUniqueKey.columnKeyOptionList.stream()
          .map(c -> c.columnName)
          .collect(Collectors.joining("_"));
      tmpUniqueKey.constraintName = constraintName;
      tmpUniqueKey.keyName = tmpUniqueKey.constraintName;
    }

    Log.trace(Json.toJsonString(tmpUniqueKey));
  }

  /**
   * parse check.
   *
   * @param checkTableConstraintContext checkTableConstraintContext
   */
  private void parseCheck(CheckTableConstraintContext checkTableConstraintContext) {
    var tmpCheck = new TmpCheck();
    tmpCheckList.add(tmpCheck);

    tmpCheck.constraintName = Utils.removeBackQuote(checkTableConstraintContext.uid().getText());
    for (var c : checkTableConstraintContext.children) {
      if (c instanceof PredicateExpressionContext predicateExpressionContext) {
        for (var cc : predicateExpressionContext.children) {
          var checkExpressionList = new ArrayList<String>();
          for (int i = 0; i < cc.getChildCount(); i++) {
            if (cc.getChild(i) instanceof NullNotnullContext nullNotnullContext) {
              checkExpressionList.add(
                  nullNotnullContext.children.stream().map(t -> t.getText()).collect(
                      Collectors.joining(" ")));
            } else {
              checkExpressionList.add(cc.getChild(i).getText());
            }
          }
          tmpCheck.expression = checkExpressionList.stream()
              .collect(Collectors.joining(" "));
        }
      }
    }

    Log.trace(Json.toJsonString(tmpCheck));
  }

  @Override
  public void enterIndexDeclaration(IndexDeclarationContext ctx) {
    var definition = ctx.indexColumnDefinition();
    if (definition instanceof SimpleIndexDeclarationContext simpleIndexDeclarationContext) {
      var tmpKey = new TmpKey();
      tmpKeyList.add(tmpKey);

      if (simpleIndexDeclarationContext.uid() != null) {
        tmpKey.constraintName = Utils.removeBackQuote(
            simpleIndexDeclarationContext.uid().getText());
        tmpKey.keyName = tmpKey.constraintName;
      }

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

        columnKeyOption.columnName = Utils.removeBackQuote(indexColumnName.uid().getText());
        columnKeyOption.seqInIndex = seqInIndex;

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

      if (Utils.isNullOrEmpty(tmpKey.constraintName) && tmpKey.columnKeyOptionList.size() > 0) {
        var constraintName = tmpKey.columnKeyOptionList.stream()
            .map(c -> c.columnName)
            .collect(Collectors.joining("_"));
        tmpKey.constraintName = constraintName;
        tmpKey.keyName = tmpKey.constraintName;
      }

      Log.trace(Json.toJsonString(tmpKey));
    }
  }

  @Override
  public void enterPartitionDefinitions(PartitionDefinitionsContext ctx) {
    var builder = new StringBuilder();
    for (int i = 0; i < ctx.getChildCount(); i++) {
      var c = ctx.getChild(i);
      parseRecursive(c, builder);
    }
    tmpPartition.expression = builder.toString();
    Log.trace(Json.toJsonString(tmpPartition));
  }

  @Override
  public void enterConstraintDeclaration(ConstraintDeclarationContext ctx) {
    if (!(ctx.tableConstraint() instanceof ForeignKeyTableConstraintContext foreignKeyTableConstraintContext)) {
      return;
    }

    if (Utils.isNullOrEmpty(tmpTable.tableName)) {
      return;
    }

    var builder = new StringBuilder();
    builder.append("ALTER TABLE `");
    builder.append(tmpTable.tableName);
    builder.append("` ADD CONSTRAINT ");
    builder.append(foreignKeyTableConstraintContext.name.getText());
    builder.append(" FOREIGN KEY ");

    var indexColumnNameList = new ArrayList<String>();
    foreignKeyTableConstraintContext.indexColumnNames().indexColumnName().forEach(c -> {
      indexColumnNameList.add(c.getText());
    });

    var indexName = "`fk_" + indexColumnNameList.stream()
        .map(c -> Utils.removeBackQuote(c))
        .collect(Collectors.joining("_")) + "`";
    if (foreignKeyTableConstraintContext.index != null) {
      indexName = foreignKeyTableConstraintContext.index.getText();
    }
    builder.append(indexName);
    builder.append(" (");
    for (var indexColumnName : indexColumnNameList) {
      builder.append(indexColumnName);
    }
    builder.append(")");
    builder.append(" REFERENCES ");
    builder.append(foreignKeyTableConstraintContext.referenceDefinition().tableName().getText());

    var referenceIndexColumnNameList = new ArrayList<String>();
    foreignKeyTableConstraintContext.referenceDefinition().indexColumnNames().indexColumnName()
        .forEach(c -> {
          referenceIndexColumnNameList.add(c.getText());
        });
    builder.append(" (");
    for (var referenceIndexColumnName : referenceIndexColumnNameList) {
      builder.append(referenceIndexColumnName);
    }
    builder.append(")");

    var referenceAction = foreignKeyTableConstraintContext.referenceDefinition().referenceAction();
    if (referenceAction != null) {
      var onUpdate = referenceAction.onUpdate;
      if (onUpdate != null) {
        builder.append(" ON UPDATE ");
        if (onUpdate.RESTRICT() != null) {
          builder.append("RESTRICT");
        } else if (onUpdate.CASCADE() != null) {
          builder.append("CASCADE");
        } else if (onUpdate.SET() != null) {
          builder.append("SET NULL");
        }
      }
      var onDelete = referenceAction.onDelete;
      if (onDelete != null) {
        builder.append(" ON DELETE ");
        if (onUpdate.RESTRICT() != null) {
          builder.append("RESTRICT");
        } else if (onUpdate.CASCADE() != null) {
          builder.append("CASCADE");
        } else if (onUpdate.SET() != null) {
          builder.append("SET NULL");
        }
      }
    }
    builder.append(";");

    var ddl = builder.toString();
    Log.trace(ddl);
    partialForeignKeyDdlList.add(ddl);
  }

  /**
   * parse recursive.
   *
   * @param parseTree parseTree
   * @param builder   builder
   */
  private static void parseRecursive(ParseTree parseTree, StringBuilder builder) {
    if (!(parseTree instanceof TerminalNodeImpl)) {
      for (int i = 0; i < parseTree.getChildCount(); i++) {
        var c = parseTree.getChild(i);
        parseRecursive(c, builder);
      }
      return;
    }
    builder.append(" ");
    builder.append(parseTree.getText());
  }
}
