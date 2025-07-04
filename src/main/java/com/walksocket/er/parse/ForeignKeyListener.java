package com.walksocket.er.parse;

import com.walksocket.antlr4.MariaDBParser.AlterByAddForeignKeyContext;
import com.walksocket.antlr4.MariaDBParser.AlterTableContext;
import com.walksocket.antlr4.MariaDBParserBaseListener;
import com.walksocket.er.Json;
import com.walksocket.er.Log;
import com.walksocket.er.Utils;
import com.walksocket.er.parts.ColumnForeignKeyOption;
import com.walksocket.er.sqlite.tmp.TmpForeignKey;
import java.util.stream.Collectors;

/**
 * ForeignKeyListener.
 */
public class ForeignKeyListener extends MariaDBParserBaseListener {

  /**
   * tmp foreign key.
   */
  private final TmpForeignKey tmpForeignKey;

  /**
   * Constructor.
   *
   * @param tmpForeignKey tmpForeignKey
   */
  public ForeignKeyListener(TmpForeignKey tmpForeignKey) {
    this.tmpForeignKey = tmpForeignKey;
  }

  @Override
  public void enterAlterTable(AlterTableContext ctx) {
    tmpForeignKey.tableName = Utils.removeBackQuote(ctx.tableName().getText());
  }

  @Override
  public void enterAlterByAddForeignKey(AlterByAddForeignKeyContext ctx) {
    tmpForeignKey.constraintName = Utils.removeBackQuote(ctx.name.getText());

    tmpForeignKey.columnForeignKeyOptionList.clear();
    var seq = 1;
    for (var indexColumn : ctx.indexColumnNames().indexColumnName()) {
      var columnForeignKeyOption = new ColumnForeignKeyOption();
      columnForeignKeyOption.columnName = Utils.removeBackQuote(indexColumn.getText());
      columnForeignKeyOption.seqInIndex = seq;
      tmpForeignKey.columnForeignKeyOptionList.add(columnForeignKeyOption);

      seq++;
    }

    if (ctx.indexName != null) {
      tmpForeignKey.keyName = Utils.removeBackQuote(ctx.indexName.getText());
    } else {
      tmpForeignKey.keyName = "fk_" + tmpForeignKey.columnForeignKeyOptionList.stream()
          .map(e -> e.columnName)
          .collect(Collectors.joining("_"));
    }

    tmpForeignKey.referenceTableName = Utils.removeBackQuote(
        ctx.referenceDefinition().tableName().getText());

    tmpForeignKey.referenceColumnForeignKeyOptionList.clear();
    var seqRef = 1;
    for (var indexColumn : ctx.referenceDefinition().indexColumnNames().indexColumnName()) {
      var columnForeignKeyOption = new ColumnForeignKeyOption();
      columnForeignKeyOption.columnName = Utils.removeBackQuote(indexColumn.getText());
      columnForeignKeyOption.seqInIndex = seqRef;
      tmpForeignKey.referenceColumnForeignKeyOptionList.add(columnForeignKeyOption);

      seqRef++;
    }

    tmpForeignKey.onUpdate = "";
    tmpForeignKey.onDelete = "";
    var referenceAction = ctx.referenceDefinition().referenceAction();
    if (referenceAction != null) {
      var onUpdate = referenceAction.onUpdate;
      if (onUpdate != null) {
        if (onUpdate.RESTRICT() != null) {
          tmpForeignKey.onUpdate = "RESTRICT";
        } else if (onUpdate.CASCADE() != null) {
          tmpForeignKey.onUpdate = "CASCADE";
        } else if (onUpdate.SET() != null) {
          tmpForeignKey.onUpdate = "SET NULL";
        }
      }
      var onDelete = referenceAction.onDelete;
      if (onDelete != null) {
        if (onDelete.RESTRICT() != null) {
          tmpForeignKey.onDelete = "RESTRICT";
        } else if (onDelete.CASCADE() != null) {
          tmpForeignKey.onDelete = "CASCADE";
        } else if (onDelete.SET() != null) {
          tmpForeignKey.onDelete = "SET NULL";
        }
      }
    }

    tmpForeignKey.relationship = "";
    Log.trace(Json.toJsonString(tmpForeignKey));
  }
}
