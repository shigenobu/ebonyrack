package com.walksocket.er;

import com.walksocket.er.antlr4.MariaDBLexer;
import com.walksocket.er.antlr4.MariaDBParser;
import com.walksocket.er.antlr4.MariaDBParser.AdministrationStatementContext;
import com.walksocket.er.antlr4.MariaDBParser.AlterByAddCheckTableConstraintContext;
import com.walksocket.er.antlr4.MariaDBParser.AlterByAddColumnContext;
import com.walksocket.er.antlr4.MariaDBParser.AlterByAddColumnsContext;
import com.walksocket.er.antlr4.MariaDBParser.AlterByAddDefinitionsContext;
import com.walksocket.er.antlr4.MariaDBParser.AlterByAddForeignKeyContext;
import com.walksocket.er.antlr4.MariaDBParser.AlterByAddIndexContext;
import com.walksocket.er.antlr4.MariaDBParser.AlterByAddPartitionContext;
import com.walksocket.er.antlr4.MariaDBParser.AlterByAddPrimaryKeyContext;
import com.walksocket.er.antlr4.MariaDBParser.AlterByAddSpecialIndexContext;
import com.walksocket.er.antlr4.MariaDBParser.AlterByAddUniqueKeyContext;
import com.walksocket.er.antlr4.MariaDBParser.AlterByAlterIndexVisibilityContext;
import com.walksocket.er.antlr4.MariaDBParser.AlterByAnalyzePartitionContext;
import com.walksocket.er.antlr4.MariaDBParser.AlterByChangeColumnContext;
import com.walksocket.er.antlr4.MariaDBParser.AlterByChangeDefaultContext;
import com.walksocket.er.antlr4.MariaDBParser.AlterByCheckPartitionContext;
import com.walksocket.er.antlr4.MariaDBParser.AlterByCoalescePartitionContext;
import com.walksocket.er.antlr4.MariaDBParser.AlterByConvertCharsetContext;
import com.walksocket.er.antlr4.MariaDBParser.AlterByDefaultCharsetContext;
import com.walksocket.er.antlr4.MariaDBParser.AlterByDisableKeysContext;
import com.walksocket.er.antlr4.MariaDBParser.AlterByDiscardPartitionContext;
import com.walksocket.er.antlr4.MariaDBParser.AlterByDiscardTablespaceContext;
import com.walksocket.er.antlr4.MariaDBParser.AlterByDropColumnContext;
import com.walksocket.er.antlr4.MariaDBParser.AlterByDropConstraintCheckContext;
import com.walksocket.er.antlr4.MariaDBParser.AlterByDropForeignKeyContext;
import com.walksocket.er.antlr4.MariaDBParser.AlterByDropIndexContext;
import com.walksocket.er.antlr4.MariaDBParser.AlterByDropPartitionContext;
import com.walksocket.er.antlr4.MariaDBParser.AlterByDropPrimaryKeyContext;
import com.walksocket.er.antlr4.MariaDBParser.AlterByEnableKeysContext;
import com.walksocket.er.antlr4.MariaDBParser.AlterByExchangePartitionContext;
import com.walksocket.er.antlr4.MariaDBParser.AlterByForceContext;
import com.walksocket.er.antlr4.MariaDBParser.AlterByImportPartitionContext;
import com.walksocket.er.antlr4.MariaDBParser.AlterByImportTablespaceContext;
import com.walksocket.er.antlr4.MariaDBParser.AlterByLockContext;
import com.walksocket.er.antlr4.MariaDBParser.AlterByModifyColumnContext;
import com.walksocket.er.antlr4.MariaDBParser.AlterByOptimizePartitionContext;
import com.walksocket.er.antlr4.MariaDBParser.AlterByOrderContext;
import com.walksocket.er.antlr4.MariaDBParser.AlterByRebuildPartitionContext;
import com.walksocket.er.antlr4.MariaDBParser.AlterByRemovePartitioningContext;
import com.walksocket.er.antlr4.MariaDBParser.AlterByRenameColumnContext;
import com.walksocket.er.antlr4.MariaDBParser.AlterByRenameContext;
import com.walksocket.er.antlr4.MariaDBParser.AlterByRenameIndexContext;
import com.walksocket.er.antlr4.MariaDBParser.AlterByReorganizePartitionContext;
import com.walksocket.er.antlr4.MariaDBParser.AlterByRepairPartitionContext;
import com.walksocket.er.antlr4.MariaDBParser.AlterBySetAlgorithmContext;
import com.walksocket.er.antlr4.MariaDBParser.AlterByTableOptionContext;
import com.walksocket.er.antlr4.MariaDBParser.AlterByTruncatePartitionContext;
import com.walksocket.er.antlr4.MariaDBParser.AlterByUpgradePartitioningContext;
import com.walksocket.er.antlr4.MariaDBParser.AlterByValidateContext;
import com.walksocket.er.antlr4.MariaDBParser.AlterEventContext;
import com.walksocket.er.antlr4.MariaDBParser.AlterFunctionContext;
import com.walksocket.er.antlr4.MariaDBParser.AlterInstanceContext;
import com.walksocket.er.antlr4.MariaDBParser.AlterLogfileGroupContext;
import com.walksocket.er.antlr4.MariaDBParser.AlterPartitionContext;
import com.walksocket.er.antlr4.MariaDBParser.AlterProcedureContext;
import com.walksocket.er.antlr4.MariaDBParser.AlterSequenceContext;
import com.walksocket.er.antlr4.MariaDBParser.AlterServerContext;
import com.walksocket.er.antlr4.MariaDBParser.AlterSimpleDatabaseContext;
import com.walksocket.er.antlr4.MariaDBParser.AlterTableContext;
import com.walksocket.er.antlr4.MariaDBParser.AlterTablespaceContext;
import com.walksocket.er.antlr4.MariaDBParser.AlterUpgradeNameContext;
import com.walksocket.er.antlr4.MariaDBParser.AlterViewContext;
import com.walksocket.er.antlr4.MariaDBParser.AssignmentFieldContext;
import com.walksocket.er.antlr4.MariaDBParser.AtomTableItemContext;
import com.walksocket.er.antlr4.MariaDBParser.AutoIncrementColumnConstraintContext;
import com.walksocket.er.antlr4.MariaDBParser.CallStatementContext;
import com.walksocket.er.antlr4.MariaDBParser.CharSetContext;
import com.walksocket.er.antlr4.MariaDBParser.CheckColumnConstraintContext;
import com.walksocket.er.antlr4.MariaDBParser.CheckTableConstraintContext;
import com.walksocket.er.antlr4.MariaDBParser.CollateColumnConstraintContext;
import com.walksocket.er.antlr4.MariaDBParser.CollationNameContext;
import com.walksocket.er.antlr4.MariaDBParser.ColumnCreateTableContext;
import com.walksocket.er.antlr4.MariaDBParser.ColumnDeclarationContext;
import com.walksocket.er.antlr4.MariaDBParser.ColumnDefinitionContext;
import com.walksocket.er.antlr4.MariaDBParser.CommentColumnConstraintContext;
import com.walksocket.er.antlr4.MariaDBParser.CommonTableExpressionsContext;
import com.walksocket.er.antlr4.MariaDBParser.CompoundStatementContext;
import com.walksocket.er.antlr4.MariaDBParser.ConstraintDeclarationContext;
import com.walksocket.er.antlr4.MariaDBParser.CopyCreateTableContext;
import com.walksocket.er.antlr4.MariaDBParser.CreateDatabaseContext;
import com.walksocket.er.antlr4.MariaDBParser.CreateDatabaseOptionContext;
import com.walksocket.er.antlr4.MariaDBParser.CreateDefinitionsContext;
import com.walksocket.er.antlr4.MariaDBParser.CreateEventContext;
import com.walksocket.er.antlr4.MariaDBParser.CreateFunctionContext;
import com.walksocket.er.antlr4.MariaDBParser.CreateIndexContext;
import com.walksocket.er.antlr4.MariaDBParser.CreateLogfileGroupContext;
import com.walksocket.er.antlr4.MariaDBParser.CreateProcedureContext;
import com.walksocket.er.antlr4.MariaDBParser.CreateRoleContext;
import com.walksocket.er.antlr4.MariaDBParser.CreateSequenceContext;
import com.walksocket.er.antlr4.MariaDBParser.CreateServerContext;
import com.walksocket.er.antlr4.MariaDBParser.CreateTablespaceInnodbContext;
import com.walksocket.er.antlr4.MariaDBParser.CreateTablespaceNdbContext;
import com.walksocket.er.antlr4.MariaDBParser.CreateTriggerContext;
import com.walksocket.er.antlr4.MariaDBParser.CreateViewContext;
import com.walksocket.er.antlr4.MariaDBParser.CteColumnNameContext;
import com.walksocket.er.antlr4.MariaDBParser.CteNameContext;
import com.walksocket.er.antlr4.MariaDBParser.CurrentUserExpressionContext;
import com.walksocket.er.antlr4.MariaDBParser.DdlStatementContext;
import com.walksocket.er.antlr4.MariaDBParser.DefaultColumnConstraintContext;
import com.walksocket.er.antlr4.MariaDBParser.DeleteStatementContext;
import com.walksocket.er.antlr4.MariaDBParser.DmlStatementContext;
import com.walksocket.er.antlr4.MariaDBParser.DoStatementContext;
import com.walksocket.er.antlr4.MariaDBParser.DropDatabaseContext;
import com.walksocket.er.antlr4.MariaDBParser.DropEventContext;
import com.walksocket.er.antlr4.MariaDBParser.DropFunctionContext;
import com.walksocket.er.antlr4.MariaDBParser.DropIndexContext;
import com.walksocket.er.antlr4.MariaDBParser.DropLogfileGroupContext;
import com.walksocket.er.antlr4.MariaDBParser.DropProcedureContext;
import com.walksocket.er.antlr4.MariaDBParser.DropRoleContext;
import com.walksocket.er.antlr4.MariaDBParser.DropSequenceContext;
import com.walksocket.er.antlr4.MariaDBParser.DropServerContext;
import com.walksocket.er.antlr4.MariaDBParser.DropTableContext;
import com.walksocket.er.antlr4.MariaDBParser.DropTablespaceContext;
import com.walksocket.er.antlr4.MariaDBParser.DropTriggerContext;
import com.walksocket.er.antlr4.MariaDBParser.DropViewContext;
import com.walksocket.er.antlr4.MariaDBParser.EmptyStatement_Context;
import com.walksocket.er.antlr4.MariaDBParser.EnableTypeContext;
import com.walksocket.er.antlr4.MariaDBParser.ForeignKeyTableConstraintContext;
import com.walksocket.er.antlr4.MariaDBParser.FormatColumnConstraintContext;
import com.walksocket.er.antlr4.MariaDBParser.FunctionParameterContext;
import com.walksocket.er.antlr4.MariaDBParser.GeneratedColumnConstraintContext;
import com.walksocket.er.antlr4.MariaDBParser.HandlerCloseStatementContext;
import com.walksocket.er.antlr4.MariaDBParser.HandlerOpenStatementContext;
import com.walksocket.er.antlr4.MariaDBParser.HandlerReadIndexStatementContext;
import com.walksocket.er.antlr4.MariaDBParser.HandlerReadStatementContext;
import com.walksocket.er.antlr4.MariaDBParser.HandlerStatementContext;
import com.walksocket.er.antlr4.MariaDBParser.IndexDeclarationContext;
import com.walksocket.er.antlr4.MariaDBParser.IndexHintContext;
import com.walksocket.er.antlr4.MariaDBParser.IndexHintTypeContext;
import com.walksocket.er.antlr4.MariaDBParser.IndexOptionContext;
import com.walksocket.er.antlr4.MariaDBParser.IndexTypeContext;
import com.walksocket.er.antlr4.MariaDBParser.InnerJoinContext;
import com.walksocket.er.antlr4.MariaDBParser.InsertStatementContext;
import com.walksocket.er.antlr4.MariaDBParser.InsertStatementValueContext;
import com.walksocket.er.antlr4.MariaDBParser.IntervalExprContext;
import com.walksocket.er.antlr4.MariaDBParser.IntervalScheduleContext;
import com.walksocket.er.antlr4.MariaDBParser.IntervalTypeContext;
import com.walksocket.er.antlr4.MariaDBParser.InvisibilityColumnConstraintContext;
import com.walksocket.er.antlr4.MariaDBParser.JsonColumnContext;
import com.walksocket.er.antlr4.MariaDBParser.JsonColumnListContext;
import com.walksocket.er.antlr4.MariaDBParser.JsonOnEmptyContext;
import com.walksocket.er.antlr4.MariaDBParser.JsonOnErrorContext;
import com.walksocket.er.antlr4.MariaDBParser.JsonTableContext;
import com.walksocket.er.antlr4.MariaDBParser.LateralStatementContext;
import com.walksocket.er.antlr4.MariaDBParser.LoadDataStatementContext;
import com.walksocket.er.antlr4.MariaDBParser.LoadXmlStatementContext;
import com.walksocket.er.antlr4.MariaDBParser.LockClauseContext;
import com.walksocket.er.antlr4.MariaDBParser.MultipleDeleteStatementContext;
import com.walksocket.er.antlr4.MariaDBParser.MultipleUpdateStatementContext;
import com.walksocket.er.antlr4.MariaDBParser.NaturalJoinContext;
import com.walksocket.er.antlr4.MariaDBParser.NullColumnConstraintContext;
import com.walksocket.er.antlr4.MariaDBParser.OrderByClauseContext;
import com.walksocket.er.antlr4.MariaDBParser.OrderByExpressionContext;
import com.walksocket.er.antlr4.MariaDBParser.OuterJoinContext;
import com.walksocket.er.antlr4.MariaDBParser.OwnerStatementContext;
import com.walksocket.er.antlr4.MariaDBParser.ParenthesisSelectContext;
import com.walksocket.er.antlr4.MariaDBParser.PartitionComparisonContext;
import com.walksocket.er.antlr4.MariaDBParser.PartitionDefinerAtomContext;
import com.walksocket.er.antlr4.MariaDBParser.PartitionDefinerVectorContext;
import com.walksocket.er.antlr4.MariaDBParser.PartitionDefinitionsContext;
import com.walksocket.er.antlr4.MariaDBParser.PartitionFunctionHashContext;
import com.walksocket.er.antlr4.MariaDBParser.PartitionFunctionKeyContext;
import com.walksocket.er.antlr4.MariaDBParser.PartitionFunctionListContext;
import com.walksocket.er.antlr4.MariaDBParser.PartitionFunctionRangeContext;
import com.walksocket.er.antlr4.MariaDBParser.PartitionListAtomContext;
import com.walksocket.er.antlr4.MariaDBParser.PartitionListVectorContext;
import com.walksocket.er.antlr4.MariaDBParser.PartitionOptionCommentContext;
import com.walksocket.er.antlr4.MariaDBParser.PartitionOptionDataDirectoryContext;
import com.walksocket.er.antlr4.MariaDBParser.PartitionOptionEngineContext;
import com.walksocket.er.antlr4.MariaDBParser.PartitionOptionIndexDirectoryContext;
import com.walksocket.er.antlr4.MariaDBParser.PartitionOptionMaxRowsContext;
import com.walksocket.er.antlr4.MariaDBParser.PartitionOptionMinRowsContext;
import com.walksocket.er.antlr4.MariaDBParser.PartitionOptionNodeGroupContext;
import com.walksocket.er.antlr4.MariaDBParser.PartitionOptionTablespaceContext;
import com.walksocket.er.antlr4.MariaDBParser.PartitionSimpleContext;
import com.walksocket.er.antlr4.MariaDBParser.PreciseScheduleContext;
import com.walksocket.er.antlr4.MariaDBParser.PreparedStatementContext;
import com.walksocket.er.antlr4.MariaDBParser.PrimaryKeyColumnConstraintContext;
import com.walksocket.er.antlr4.MariaDBParser.PrimaryKeyTableConstraintContext;
import com.walksocket.er.antlr4.MariaDBParser.ProcedureParameterContext;
import com.walksocket.er.antlr4.MariaDBParser.QueryCreateTableContext;
import com.walksocket.er.antlr4.MariaDBParser.QueryExpressionContext;
import com.walksocket.er.antlr4.MariaDBParser.QueryExpressionNointoContext;
import com.walksocket.er.antlr4.MariaDBParser.QuerySpecificationContext;
import com.walksocket.er.antlr4.MariaDBParser.QuerySpecificationNointoContext;
import com.walksocket.er.antlr4.MariaDBParser.ReferenceActionContext;
import com.walksocket.er.antlr4.MariaDBParser.ReferenceColumnConstraintContext;
import com.walksocket.er.antlr4.MariaDBParser.ReferenceControlTypeContext;
import com.walksocket.er.antlr4.MariaDBParser.ReferenceDefinitionContext;
import com.walksocket.er.antlr4.MariaDBParser.RenameTableClauseContext;
import com.walksocket.er.antlr4.MariaDBParser.RenameTableContext;
import com.walksocket.er.antlr4.MariaDBParser.ReplaceStatementContext;
import com.walksocket.er.antlr4.MariaDBParser.ReplicationStatementContext;
import com.walksocket.er.antlr4.MariaDBParser.RootContext;
import com.walksocket.er.antlr4.MariaDBParser.RoutineBehaviorContext;
import com.walksocket.er.antlr4.MariaDBParser.RoutineCommentContext;
import com.walksocket.er.antlr4.MariaDBParser.RoutineDataContext;
import com.walksocket.er.antlr4.MariaDBParser.RoutineLanguageContext;
import com.walksocket.er.antlr4.MariaDBParser.RoutineSecurityContext;
import com.walksocket.er.antlr4.MariaDBParser.SelectSpecContext;
import com.walksocket.er.antlr4.MariaDBParser.SequenceSpecContext;
import com.walksocket.er.antlr4.MariaDBParser.SerialDefaultColumnConstraintContext;
import com.walksocket.er.antlr4.MariaDBParser.ServerOptionContext;
import com.walksocket.er.antlr4.MariaDBParser.SetRoleContext;
import com.walksocket.er.antlr4.MariaDBParser.SetStatementForContext;
import com.walksocket.er.antlr4.MariaDBParser.ShowColumnsContext;
import com.walksocket.er.antlr4.MariaDBParser.SimpleIndexDeclarationContext;
import com.walksocket.er.antlr4.MariaDBParser.SimpleSelectContext;
import com.walksocket.er.antlr4.MariaDBParser.SingleDeleteStatementContext;
import com.walksocket.er.antlr4.MariaDBParser.SingleUpdateStatementContext;
import com.walksocket.er.antlr4.MariaDBParser.SpecialIndexDeclarationContext;
import com.walksocket.er.antlr4.MariaDBParser.SqlStatementContext;
import com.walksocket.er.antlr4.MariaDBParser.SqlStatementsContext;
import com.walksocket.er.antlr4.MariaDBParser.StorageColumnConstraintContext;
import com.walksocket.er.antlr4.MariaDBParser.StraightJoinContext;
import com.walksocket.er.antlr4.MariaDBParser.SubPartitionFunctionHashContext;
import com.walksocket.er.antlr4.MariaDBParser.SubPartitionFunctionKeyContext;
import com.walksocket.er.antlr4.MariaDBParser.SubpartitionDefinitionContext;
import com.walksocket.er.antlr4.MariaDBParser.SubqueryTableItemContext;
import com.walksocket.er.antlr4.MariaDBParser.TableJsonContext;
import com.walksocket.er.antlr4.MariaDBParser.TableOptionAutoIncrementContext;
import com.walksocket.er.antlr4.MariaDBParser.TableOptionAutoextendSizeContext;
import com.walksocket.er.antlr4.MariaDBParser.TableOptionAverageContext;
import com.walksocket.er.antlr4.MariaDBParser.TableOptionCharsetContext;
import com.walksocket.er.antlr4.MariaDBParser.TableOptionChecksumContext;
import com.walksocket.er.antlr4.MariaDBParser.TableOptionCollateContext;
import com.walksocket.er.antlr4.MariaDBParser.TableOptionCommentContext;
import com.walksocket.er.antlr4.MariaDBParser.TableOptionCompressionContext;
import com.walksocket.er.antlr4.MariaDBParser.TableOptionConnectionContext;
import com.walksocket.er.antlr4.MariaDBParser.TableOptionDataDirectoryContext;
import com.walksocket.er.antlr4.MariaDBParser.TableOptionDelayContext;
import com.walksocket.er.antlr4.MariaDBParser.TableOptionEncryptedContext;
import com.walksocket.er.antlr4.MariaDBParser.TableOptionEncryptionContext;
import com.walksocket.er.antlr4.MariaDBParser.TableOptionEncryptionKeyIdContext;
import com.walksocket.er.antlr4.MariaDBParser.TableOptionEngineAttributeContext;
import com.walksocket.er.antlr4.MariaDBParser.TableOptionEngineContext;
import com.walksocket.er.antlr4.MariaDBParser.TableOptionIndexDirectoryContext;
import com.walksocket.er.antlr4.MariaDBParser.TableOptionInsertMethodContext;
import com.walksocket.er.antlr4.MariaDBParser.TableOptionKeyBlockSizeContext;
import com.walksocket.er.antlr4.MariaDBParser.TableOptionMaxRowsContext;
import com.walksocket.er.antlr4.MariaDBParser.TableOptionMinRowsContext;
import com.walksocket.er.antlr4.MariaDBParser.TableOptionPackKeysContext;
import com.walksocket.er.antlr4.MariaDBParser.TableOptionPageCompressedContext;
import com.walksocket.er.antlr4.MariaDBParser.TableOptionPageCompressionLevelContext;
import com.walksocket.er.antlr4.MariaDBParser.TableOptionPasswordContext;
import com.walksocket.er.antlr4.MariaDBParser.TableOptionPersistentContext;
import com.walksocket.er.antlr4.MariaDBParser.TableOptionRecalculationContext;
import com.walksocket.er.antlr4.MariaDBParser.TableOptionRowFormatContext;
import com.walksocket.er.antlr4.MariaDBParser.TableOptionSamplePageContext;
import com.walksocket.er.antlr4.MariaDBParser.TableOptionSecondaryEngineAttributeContext;
import com.walksocket.er.antlr4.MariaDBParser.TableOptionStartTransactionContext;
import com.walksocket.er.antlr4.MariaDBParser.TableOptionTableTypeContext;
import com.walksocket.er.antlr4.MariaDBParser.TableOptionTablespaceContext;
import com.walksocket.er.antlr4.MariaDBParser.TableOptionTransactionalContext;
import com.walksocket.er.antlr4.MariaDBParser.TableOptionUnionContext;
import com.walksocket.er.antlr4.MariaDBParser.TableSourceBaseContext;
import com.walksocket.er.antlr4.MariaDBParser.TableSourceNestedContext;
import com.walksocket.er.antlr4.MariaDBParser.TableSourcesContext;
import com.walksocket.er.antlr4.MariaDBParser.TableSourcesItemContext;
import com.walksocket.er.antlr4.MariaDBParser.TableTypeContext;
import com.walksocket.er.antlr4.MariaDBParser.TablespaceStorageContext;
import com.walksocket.er.antlr4.MariaDBParser.TimestampValueContext;
import com.walksocket.er.antlr4.MariaDBParser.TransactionStatementContext;
import com.walksocket.er.antlr4.MariaDBParser.TruncateTableContext;
import com.walksocket.er.antlr4.MariaDBParser.UnionParenthesisContext;
import com.walksocket.er.antlr4.MariaDBParser.UnionParenthesisSelectContext;
import com.walksocket.er.antlr4.MariaDBParser.UnionSelectContext;
import com.walksocket.er.antlr4.MariaDBParser.UnionStatementContext;
import com.walksocket.er.antlr4.MariaDBParser.UniqueKeyColumnConstraintContext;
import com.walksocket.er.antlr4.MariaDBParser.UniqueKeyTableConstraintContext;
import com.walksocket.er.antlr4.MariaDBParser.UpdateStatementContext;
import com.walksocket.er.antlr4.MariaDBParser.UpdatedElementContext;
import com.walksocket.er.antlr4.MariaDBParser.UtilityStatementContext;
import com.walksocket.er.antlr4.MariaDBParser.ValuesStatementContext;
import com.walksocket.er.antlr4.MariaDBParser.VisibilityColumnConstraintContext;
import com.walksocket.er.antlr4.MariaDBParser.WithClauseContext;
import com.walksocket.er.antlr4.MariaDBParser.WithLateralStatementContext;
import com.walksocket.er.antlr4.MariaDBParserBaseListener;
import com.walksocket.er.antlr4.MariaDBParserListener;
import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.parser.CCJSqlParser;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.statement.create.table.CreateTable;
import net.sf.jsqlparser.util.validation.feature.MariaDbVersion;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.junit.jupiter.api.Test;

public class TestSql {

  @Test
  public void testCreateParseAutoIncrement() throws JSQLParserException {
    var ddl = "CREATE TABLE `m_adm_actibook_create_article` (\n"
        + "  `create_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '生成ID',\n"
        + "  `article_id` int(10) unsigned NOT NULL COMMENT '記事ID',\n"
        + "  `type` tinyint(3) unsigned NOT NULL COMMENT '記事タイプ',\n"
        + "  `file_name` text NOT NULL COMMENT '元記事ファイル名',\n"
        + "  `token` text DEFAULT NULL COMMENT 'トークン',\n"
        + "  `create_status` tinyint(3) unsigned NOT NULL COMMENT 'ブック生成ステータス',\n"
        + "  `progress_status` tinyint(3) unsigned NOT NULL COMMENT 'ブック変換ステータス',\n"
        + "  `in_date` datetime NOT NULL COMMENT '登録日時',\n"
        + "  `up_date` datetime NOT NULL COMMENT '更新日時',\n"
        + "  PRIMARY KEY (`create_id`)\n"
        + ") ENGINE=InnoDB AUTO_INCREMENT=33218 DEFAULT CHARSET=utf8mb4 COMMENT='M_ActiBook生成管理テーブル(管理ツール投入用)' ";
    var create = (CreateTable) CCJSqlParserUtil.parse(ddl);
    System.out.println(Json.toJsonStringFriendly(create));
  }

  @Test
  public void testCreateParseGenerated() throws JSQLParserException {
    var ddl = "CREATE TABLE `m_clinic_map_pos` (\n"
        + "  `map_pos_id` bigint(20) unsigned NOT NULL COMMENT 'マップ位置ID',\n"
        + "  `field_id` tinyint(3) unsigned NOT NULL COMMENT '所属領域ID',\n"
        + "  `ladder` tinyint(3) unsigned NOT NULL COMMENT 'クリニカルラダー',\n"
        + "  `map_address` int(10) unsigned NOT NULL COMMENT '番地',\n"
        + "  `map_pos_name` tinytext NOT NULL COMMENT 'マップ番地名',\n"
        + "  `common_flag` tinyint(3) unsigned GENERATED ALWAYS AS (case when `map_address` > 1000000 then 1 else 0 end) VIRTUAL COMMENT '共通フラグ',\n"
        + "  `category_l` text DEFAULT NULL COMMENT '大項目',\n"
        + "  `category_m` text DEFAULT NULL COMMENT '中項目ID',\n"
        + "  `category_s` text DEFAULT NULL COMMENT '小項目',\n"
        + "  `in_date` datetime NOT NULL COMMENT '登録日時',\n"
        + "  `up_date` datetime NOT NULL COMMENT '更新日時',\n"
        + "  `up_admin` tinytext NOT NULL COMMENT '更新者',\n"
        + "  `remarks` mediumtext NOT NULL COMMENT '備考',\n"
        + "  PRIMARY KEY (`map_pos_id`),\n"
        + "  UNIQUE KEY `field_id` (`field_id`,`ladder`,`map_address`)\n"
        + ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='M_マップ位置（臨床）'";
    var create = (CreateTable) CCJSqlParserUtil.parse(ddl);
    System.out.println(Json.toJsonStringFriendly(create));
  }

  @Test
  public void testCreateParseOnUpdate() throws JSQLParserException {
    var ddl = "CREATE TABLE `m_article3` (\n"
        + "  `article_id` bigint(20) NOT NULL COMMENT '記事ID',\n"
        + "  `file_name` varchar(256) NOT NULL COMMENT 'ファイル名',\n"
        + "  `title` text NOT NULL COMMENT 'タイトル',\n"
        + "  `detail` text NOT NULL COMMENT '詳細',\n"
        + "  `op_date` datetime NOT NULL COMMENT '配信開始日時',\n"
        + "  `ed_date` datetime NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE current_timestamp() COMMENT '配信終了日時',\n"
        + "  `in_date` datetime NOT NULL COMMENT '登録日時',\n"
        + "  `up_date` datetime NOT NULL COMMENT '更新日時',\n"
        + "  PRIMARY KEY (`article_id`),\n"
        + "  UNIQUE KEY `uk_file_name` (`file_name`),\n"
        + "  KEY `k_up_date` (`up_date`)\n"
        + ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='記事'";
    var create = (CreateTable) CCJSqlParserUtil.parse(ddl);
    System.out.println(Json.toJsonStringFriendly(create));
  }

  @Test
  public void testCreateParseAll() throws JSQLParserException {
    var ddl = "CREATE TABLE `t_all_types` (\n"
        + "  `id` int(11) NOT NULL,\n"
        + "  `c_tinyint_signed` tinyint(4) DEFAULT NULL,\n"
        + "  `c_tinyint_unsigned` tinyint(3) unsigned DEFAULT NULL,\n"
        + "  `c_tinyint_zerofill` tinyint(3) unsigned zerofill DEFAULT NULL,\n"
        + "  `c_smallint_signed` smallint(6) DEFAULT NULL,\n"
        + "  `c_smallint_unsigned` smallint(5) unsigned DEFAULT NULL,\n"
        + "  `c_smallint_zerofill` smallint(5) unsigned zerofill DEFAULT NULL,\n"
        + "  `c_mediumint_signed` mediumint(9) DEFAULT NULL,\n"
        + "  `c_mediumint_unsigned` mediumint(8) unsigned DEFAULT NULL,\n"
        + "  `c_mediumint_zerofill` mediumint(8) unsigned zerofill DEFAULT NULL,\n"
        + "  `c_int_signed` int(11) DEFAULT NULL,\n"
        + "  `c_int_unsigned` int(10) unsigned DEFAULT NULL,\n"
        + "  `c_int_zerofill` int(10) unsigned zerofill DEFAULT NULL,\n"
        + "  `c_bigint_signed` bigint(20) DEFAULT NULL,\n"
        + "  `c_bigint_unsigned` bigint(20) unsigned DEFAULT NULL,\n"
        + "  `c_bigint_zerofill` bigint(20) unsigned zerofill DEFAULT NULL,\n"
        + "  `c_decimal_signed` decimal(10,0) DEFAULT NULL,\n"
        + "  `c_decimal_unsigned` decimal(10,0) unsigned DEFAULT NULL,\n"
        + "  `c_decimal_zerofill` decimal(10,0) unsigned zerofill DEFAULT NULL,\n"
        + "  `c_float_signed` float DEFAULT NULL,\n"
        + "  `c_float_unsigned` float unsigned DEFAULT NULL,\n"
        + "  `c_float_zerofill` float unsigned zerofill DEFAULT NULL,\n"
        + "  `c_double_signed` double DEFAULT NULL,\n"
        + "  `c_double_unsigned` double unsigned DEFAULT NULL,\n"
        + "  `c_double_zerofill` double unsigned zerofill DEFAULT NULL,\n"
        + "  `c_bit` bit(1) DEFAULT NULL,\n"
        + "  `c_char` char(1) DEFAULT NULL,\n"
        + "  `c_varchar` varchar(16) DEFAULT NULL,\n"
        + "  `c_tinytext` tinytext DEFAULT NULL,\n"
        + "  `c_text` text DEFAULT NULL,\n"
        + "  `c_mediumtext` mediumtext DEFAULT NULL,\n"
        + "  `c_longtext` longtext DEFAULT NULL,\n"
        + "  `c_enum` enum('a','b','c') DEFAULT NULL,\n"
        + "  `c_set` set('A','B','C') DEFAULT NULL,\n"
        + "  `c_json` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,\n"
        + "  `c_date` date DEFAULT NULL,\n"
        + "  `c_time` time DEFAULT NULL,\n"
        + "  `c_datetime` datetime DEFAULT NULL,\n"
        + "  `c_timestamp` timestamp NULL DEFAULT NULL,\n"
        + "  `c_year` year(4) DEFAULT NULL,\n"
        + "  `c_binary` binary(1) DEFAULT NULL,\n"
        + "  `c_varbinary` varbinary(16) DEFAULT NULL,\n"
        + "  `c_tinyblob` tinyblob DEFAULT NULL,\n"
        + "  `c_blob` blob DEFAULT NULL,\n"
        + "  `c_mediumblob` mediumblob DEFAULT NULL,\n"
        + "  `c_longblob` longblob DEFAULT NULL,\n"
        + "  PRIMARY KEY (`id`)\n"
        + ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci";
    var lexer = new MariaDBLexer(CharStreams.fromString(ddl));
    var token = new CommonTokenStream(lexer);
    var parser = new MariaDBParser(token);
    ParseTreeWalker.DEFAULT.walk(new MariaDBParserBaseListener() {


      @Override
      public void enterColumnDefinition(ColumnDefinitionContext ctx) {
        System.out.println(ctx.dataType().getText());
      }
    }, parser.ddlStatement());

//    ddl = ddl.replaceAll("\n", "");
//    System.out.println(ddl);
//    var create = (CreateTable) CCJSqlParserUtil.parse(ddl);
//    System.out.println(Json.toJsonStringFriendly(create));
  }

  @Test
  public void testAntlr4() {
    var ddl = "CREATE TABLE `m_article3` (\n"
        + "  `article_id` bigint(20) NOT NULL COMMENT '記事ID',\n"
        + "  `file_name` varchar(256) NOT NULL COMMENT 'ファイル名',\n"
        + "  `title` text NOT NULL COMMENT 'タイトル',\n"
        + "  `detail` text NOT NULL COMMENT '詳細',\n"
        + "  `op_date` datetime NOT NULL COMMENT '配信開始日時',\n"
        + "  `ed_date` datetime NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE current_timestamp() COMMENT '配信終了日時',\n"
        + "  `in_date` datetime NOT NULL COMMENT '登録日時',\n"
        + "  `up_date` datetime NOT NULL COMMENT '更新日時',\n"
        + "  PRIMARY KEY (`article_id`),\n"
        + "  UNIQUE KEY `uk_file_name` (`file_name`),\n"
        + "  KEY `k_up_date` (`up_date`)\n"
        + ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='記事'";
    var lexer = new MariaDBLexer(CharStreams.fromString(ddl));
    var token = new CommonTokenStream(lexer);
    var parser = new MariaDBParser(token);
    ParseTreeWalker.DEFAULT.walk(new MariaDBParserBaseListener() {


      @Override
      public void enterCommentColumnConstraint(CommentColumnConstraintContext ctx) {
        System.out.println(ctx.STRING_LITERAL().getText());
      }
    }, parser.ddlStatement());

  }
}
