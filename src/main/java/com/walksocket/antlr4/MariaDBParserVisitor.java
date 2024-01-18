// Generated from MariaDBParser.g4 by ANTLR 4.13.1
package com.walksocket.antlr4;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link MariaDBParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface MariaDBParserVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#root}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRoot(MariaDBParser.RootContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#sqlStatements}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSqlStatements(MariaDBParser.SqlStatementsContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#sqlStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSqlStatement(MariaDBParser.SqlStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#setStatementFor}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSetStatementFor(MariaDBParser.SetStatementForContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#emptyStatement_}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEmptyStatement_(MariaDBParser.EmptyStatement_Context ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#ddlStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDdlStatement(MariaDBParser.DdlStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#dmlStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDmlStatement(MariaDBParser.DmlStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#transactionStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTransactionStatement(MariaDBParser.TransactionStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#replicationStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReplicationStatement(MariaDBParser.ReplicationStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#preparedStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPreparedStatement(MariaDBParser.PreparedStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#compoundStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCompoundStatement(MariaDBParser.CompoundStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#administrationStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAdministrationStatement(MariaDBParser.AdministrationStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#utilityStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUtilityStatement(MariaDBParser.UtilityStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#createDatabase}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCreateDatabase(MariaDBParser.CreateDatabaseContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#createEvent}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCreateEvent(MariaDBParser.CreateEventContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#createIndex}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCreateIndex(MariaDBParser.CreateIndexContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#createLogfileGroup}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCreateLogfileGroup(MariaDBParser.CreateLogfileGroupContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#createProcedure}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCreateProcedure(MariaDBParser.CreateProcedureContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#createFunction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCreateFunction(MariaDBParser.CreateFunctionContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#createRole}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCreateRole(MariaDBParser.CreateRoleContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#createServer}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCreateServer(MariaDBParser.CreateServerContext ctx);
	/**
	 * Visit a parse tree produced by the {@code copyCreateTable}
	 * labeled alternative in {@link MariaDBParser#createTable}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCopyCreateTable(MariaDBParser.CopyCreateTableContext ctx);
	/**
	 * Visit a parse tree produced by the {@code queryCreateTable}
	 * labeled alternative in {@link MariaDBParser#createTable}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitQueryCreateTable(MariaDBParser.QueryCreateTableContext ctx);
	/**
	 * Visit a parse tree produced by the {@code columnCreateTable}
	 * labeled alternative in {@link MariaDBParser#createTable}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitColumnCreateTable(MariaDBParser.ColumnCreateTableContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#createTablespaceInnodb}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCreateTablespaceInnodb(MariaDBParser.CreateTablespaceInnodbContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#createTablespaceNdb}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCreateTablespaceNdb(MariaDBParser.CreateTablespaceNdbContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#createTrigger}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCreateTrigger(MariaDBParser.CreateTriggerContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#withClause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWithClause(MariaDBParser.WithClauseContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#commonTableExpressions}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCommonTableExpressions(MariaDBParser.CommonTableExpressionsContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#cteName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCteName(MariaDBParser.CteNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#cteColumnName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCteColumnName(MariaDBParser.CteColumnNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#createView}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCreateView(MariaDBParser.CreateViewContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#createSequence}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCreateSequence(MariaDBParser.CreateSequenceContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#sequenceSpec}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSequenceSpec(MariaDBParser.SequenceSpecContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#createDatabaseOption}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCreateDatabaseOption(MariaDBParser.CreateDatabaseOptionContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#charSet}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCharSet(MariaDBParser.CharSetContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#currentUserExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCurrentUserExpression(MariaDBParser.CurrentUserExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#ownerStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOwnerStatement(MariaDBParser.OwnerStatementContext ctx);
	/**
	 * Visit a parse tree produced by the {@code preciseSchedule}
	 * labeled alternative in {@link MariaDBParser#scheduleExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPreciseSchedule(MariaDBParser.PreciseScheduleContext ctx);
	/**
	 * Visit a parse tree produced by the {@code intervalSchedule}
	 * labeled alternative in {@link MariaDBParser#scheduleExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIntervalSchedule(MariaDBParser.IntervalScheduleContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#timestampValue}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTimestampValue(MariaDBParser.TimestampValueContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#intervalExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIntervalExpr(MariaDBParser.IntervalExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#intervalType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIntervalType(MariaDBParser.IntervalTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#enableType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEnableType(MariaDBParser.EnableTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#indexType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIndexType(MariaDBParser.IndexTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#indexOption}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIndexOption(MariaDBParser.IndexOptionContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#procedureParameter}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProcedureParameter(MariaDBParser.ProcedureParameterContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#functionParameter}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionParameter(MariaDBParser.FunctionParameterContext ctx);
	/**
	 * Visit a parse tree produced by the {@code routineComment}
	 * labeled alternative in {@link MariaDBParser#routineOption}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRoutineComment(MariaDBParser.RoutineCommentContext ctx);
	/**
	 * Visit a parse tree produced by the {@code routineLanguage}
	 * labeled alternative in {@link MariaDBParser#routineOption}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRoutineLanguage(MariaDBParser.RoutineLanguageContext ctx);
	/**
	 * Visit a parse tree produced by the {@code routineBehavior}
	 * labeled alternative in {@link MariaDBParser#routineOption}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRoutineBehavior(MariaDBParser.RoutineBehaviorContext ctx);
	/**
	 * Visit a parse tree produced by the {@code routineData}
	 * labeled alternative in {@link MariaDBParser#routineOption}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRoutineData(MariaDBParser.RoutineDataContext ctx);
	/**
	 * Visit a parse tree produced by the {@code routineSecurity}
	 * labeled alternative in {@link MariaDBParser#routineOption}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRoutineSecurity(MariaDBParser.RoutineSecurityContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#serverOption}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitServerOption(MariaDBParser.ServerOptionContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#createDefinitions}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCreateDefinitions(MariaDBParser.CreateDefinitionsContext ctx);
	/**
	 * Visit a parse tree produced by the {@code columnDeclaration}
	 * labeled alternative in {@link MariaDBParser#createDefinition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitColumnDeclaration(MariaDBParser.ColumnDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by the {@code constraintDeclaration}
	 * labeled alternative in {@link MariaDBParser#createDefinition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstraintDeclaration(MariaDBParser.ConstraintDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by the {@code indexDeclaration}
	 * labeled alternative in {@link MariaDBParser#createDefinition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIndexDeclaration(MariaDBParser.IndexDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#columnDefinition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitColumnDefinition(MariaDBParser.ColumnDefinitionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code nullColumnConstraint}
	 * labeled alternative in {@link MariaDBParser#columnConstraint}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNullColumnConstraint(MariaDBParser.NullColumnConstraintContext ctx);
	/**
	 * Visit a parse tree produced by the {@code defaultColumnConstraint}
	 * labeled alternative in {@link MariaDBParser#columnConstraint}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDefaultColumnConstraint(MariaDBParser.DefaultColumnConstraintContext ctx);
	/**
	 * Visit a parse tree produced by the {@code visibilityColumnConstraint}
	 * labeled alternative in {@link MariaDBParser#columnConstraint}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVisibilityColumnConstraint(MariaDBParser.VisibilityColumnConstraintContext ctx);
	/**
	 * Visit a parse tree produced by the {@code invisibilityColumnConstraint}
	 * labeled alternative in {@link MariaDBParser#columnConstraint}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInvisibilityColumnConstraint(MariaDBParser.InvisibilityColumnConstraintContext ctx);
	/**
	 * Visit a parse tree produced by the {@code autoIncrementColumnConstraint}
	 * labeled alternative in {@link MariaDBParser#columnConstraint}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAutoIncrementColumnConstraint(MariaDBParser.AutoIncrementColumnConstraintContext ctx);
	/**
	 * Visit a parse tree produced by the {@code primaryKeyColumnConstraint}
	 * labeled alternative in {@link MariaDBParser#columnConstraint}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrimaryKeyColumnConstraint(MariaDBParser.PrimaryKeyColumnConstraintContext ctx);
	/**
	 * Visit a parse tree produced by the {@code uniqueKeyColumnConstraint}
	 * labeled alternative in {@link MariaDBParser#columnConstraint}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUniqueKeyColumnConstraint(MariaDBParser.UniqueKeyColumnConstraintContext ctx);
	/**
	 * Visit a parse tree produced by the {@code commentColumnConstraint}
	 * labeled alternative in {@link MariaDBParser#columnConstraint}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCommentColumnConstraint(MariaDBParser.CommentColumnConstraintContext ctx);
	/**
	 * Visit a parse tree produced by the {@code formatColumnConstraint}
	 * labeled alternative in {@link MariaDBParser#columnConstraint}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFormatColumnConstraint(MariaDBParser.FormatColumnConstraintContext ctx);
	/**
	 * Visit a parse tree produced by the {@code storageColumnConstraint}
	 * labeled alternative in {@link MariaDBParser#columnConstraint}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStorageColumnConstraint(MariaDBParser.StorageColumnConstraintContext ctx);
	/**
	 * Visit a parse tree produced by the {@code referenceColumnConstraint}
	 * labeled alternative in {@link MariaDBParser#columnConstraint}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReferenceColumnConstraint(MariaDBParser.ReferenceColumnConstraintContext ctx);
	/**
	 * Visit a parse tree produced by the {@code collateColumnConstraint}
	 * labeled alternative in {@link MariaDBParser#columnConstraint}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCollateColumnConstraint(MariaDBParser.CollateColumnConstraintContext ctx);
	/**
	 * Visit a parse tree produced by the {@code generatedColumnConstraint}
	 * labeled alternative in {@link MariaDBParser#columnConstraint}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGeneratedColumnConstraint(MariaDBParser.GeneratedColumnConstraintContext ctx);
	/**
	 * Visit a parse tree produced by the {@code serialDefaultColumnConstraint}
	 * labeled alternative in {@link MariaDBParser#columnConstraint}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSerialDefaultColumnConstraint(MariaDBParser.SerialDefaultColumnConstraintContext ctx);
	/**
	 * Visit a parse tree produced by the {@code checkColumnConstraint}
	 * labeled alternative in {@link MariaDBParser#columnConstraint}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCheckColumnConstraint(MariaDBParser.CheckColumnConstraintContext ctx);
	/**
	 * Visit a parse tree produced by the {@code primaryKeyTableConstraint}
	 * labeled alternative in {@link MariaDBParser#tableConstraint}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrimaryKeyTableConstraint(MariaDBParser.PrimaryKeyTableConstraintContext ctx);
	/**
	 * Visit a parse tree produced by the {@code uniqueKeyTableConstraint}
	 * labeled alternative in {@link MariaDBParser#tableConstraint}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUniqueKeyTableConstraint(MariaDBParser.UniqueKeyTableConstraintContext ctx);
	/**
	 * Visit a parse tree produced by the {@code foreignKeyTableConstraint}
	 * labeled alternative in {@link MariaDBParser#tableConstraint}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitForeignKeyTableConstraint(MariaDBParser.ForeignKeyTableConstraintContext ctx);
	/**
	 * Visit a parse tree produced by the {@code checkTableConstraint}
	 * labeled alternative in {@link MariaDBParser#tableConstraint}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCheckTableConstraint(MariaDBParser.CheckTableConstraintContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#referenceDefinition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReferenceDefinition(MariaDBParser.ReferenceDefinitionContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#referenceAction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReferenceAction(MariaDBParser.ReferenceActionContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#referenceControlType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReferenceControlType(MariaDBParser.ReferenceControlTypeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code simpleIndexDeclaration}
	 * labeled alternative in {@link MariaDBParser#indexColumnDefinition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSimpleIndexDeclaration(MariaDBParser.SimpleIndexDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by the {@code specialIndexDeclaration}
	 * labeled alternative in {@link MariaDBParser#indexColumnDefinition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSpecialIndexDeclaration(MariaDBParser.SpecialIndexDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by the {@code tableOptionEngine}
	 * labeled alternative in {@link MariaDBParser#tableOption}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTableOptionEngine(MariaDBParser.TableOptionEngineContext ctx);
	/**
	 * Visit a parse tree produced by the {@code tableOptionEngineAttribute}
	 * labeled alternative in {@link MariaDBParser#tableOption}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTableOptionEngineAttribute(MariaDBParser.TableOptionEngineAttributeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code tableOptionAutoextendSize}
	 * labeled alternative in {@link MariaDBParser#tableOption}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTableOptionAutoextendSize(MariaDBParser.TableOptionAutoextendSizeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code tableOptionAutoIncrement}
	 * labeled alternative in {@link MariaDBParser#tableOption}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTableOptionAutoIncrement(MariaDBParser.TableOptionAutoIncrementContext ctx);
	/**
	 * Visit a parse tree produced by the {@code tableOptionAverage}
	 * labeled alternative in {@link MariaDBParser#tableOption}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTableOptionAverage(MariaDBParser.TableOptionAverageContext ctx);
	/**
	 * Visit a parse tree produced by the {@code tableOptionCharset}
	 * labeled alternative in {@link MariaDBParser#tableOption}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTableOptionCharset(MariaDBParser.TableOptionCharsetContext ctx);
	/**
	 * Visit a parse tree produced by the {@code tableOptionChecksum}
	 * labeled alternative in {@link MariaDBParser#tableOption}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTableOptionChecksum(MariaDBParser.TableOptionChecksumContext ctx);
	/**
	 * Visit a parse tree produced by the {@code tableOptionCollate}
	 * labeled alternative in {@link MariaDBParser#tableOption}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTableOptionCollate(MariaDBParser.TableOptionCollateContext ctx);
	/**
	 * Visit a parse tree produced by the {@code tableOptionComment}
	 * labeled alternative in {@link MariaDBParser#tableOption}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTableOptionComment(MariaDBParser.TableOptionCommentContext ctx);
	/**
	 * Visit a parse tree produced by the {@code tableOptionCompression}
	 * labeled alternative in {@link MariaDBParser#tableOption}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTableOptionCompression(MariaDBParser.TableOptionCompressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code tableOptionConnection}
	 * labeled alternative in {@link MariaDBParser#tableOption}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTableOptionConnection(MariaDBParser.TableOptionConnectionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code tableOptionDataDirectory}
	 * labeled alternative in {@link MariaDBParser#tableOption}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTableOptionDataDirectory(MariaDBParser.TableOptionDataDirectoryContext ctx);
	/**
	 * Visit a parse tree produced by the {@code tableOptionDelay}
	 * labeled alternative in {@link MariaDBParser#tableOption}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTableOptionDelay(MariaDBParser.TableOptionDelayContext ctx);
	/**
	 * Visit a parse tree produced by the {@code tableOptionEncryption}
	 * labeled alternative in {@link MariaDBParser#tableOption}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTableOptionEncryption(MariaDBParser.TableOptionEncryptionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code tableOptionEncrypted}
	 * labeled alternative in {@link MariaDBParser#tableOption}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTableOptionEncrypted(MariaDBParser.TableOptionEncryptedContext ctx);
	/**
	 * Visit a parse tree produced by the {@code tableOptionPageCompressed}
	 * labeled alternative in {@link MariaDBParser#tableOption}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTableOptionPageCompressed(MariaDBParser.TableOptionPageCompressedContext ctx);
	/**
	 * Visit a parse tree produced by the {@code tableOptionPageCompressionLevel}
	 * labeled alternative in {@link MariaDBParser#tableOption}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTableOptionPageCompressionLevel(MariaDBParser.TableOptionPageCompressionLevelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code tableOptionEncryptionKeyId}
	 * labeled alternative in {@link MariaDBParser#tableOption}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTableOptionEncryptionKeyId(MariaDBParser.TableOptionEncryptionKeyIdContext ctx);
	/**
	 * Visit a parse tree produced by the {@code tableOptionIndexDirectory}
	 * labeled alternative in {@link MariaDBParser#tableOption}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTableOptionIndexDirectory(MariaDBParser.TableOptionIndexDirectoryContext ctx);
	/**
	 * Visit a parse tree produced by the {@code tableOptionInsertMethod}
	 * labeled alternative in {@link MariaDBParser#tableOption}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTableOptionInsertMethod(MariaDBParser.TableOptionInsertMethodContext ctx);
	/**
	 * Visit a parse tree produced by the {@code tableOptionKeyBlockSize}
	 * labeled alternative in {@link MariaDBParser#tableOption}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTableOptionKeyBlockSize(MariaDBParser.TableOptionKeyBlockSizeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code tableOptionMaxRows}
	 * labeled alternative in {@link MariaDBParser#tableOption}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTableOptionMaxRows(MariaDBParser.TableOptionMaxRowsContext ctx);
	/**
	 * Visit a parse tree produced by the {@code tableOptionMinRows}
	 * labeled alternative in {@link MariaDBParser#tableOption}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTableOptionMinRows(MariaDBParser.TableOptionMinRowsContext ctx);
	/**
	 * Visit a parse tree produced by the {@code tableOptionPackKeys}
	 * labeled alternative in {@link MariaDBParser#tableOption}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTableOptionPackKeys(MariaDBParser.TableOptionPackKeysContext ctx);
	/**
	 * Visit a parse tree produced by the {@code tableOptionPassword}
	 * labeled alternative in {@link MariaDBParser#tableOption}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTableOptionPassword(MariaDBParser.TableOptionPasswordContext ctx);
	/**
	 * Visit a parse tree produced by the {@code tableOptionRowFormat}
	 * labeled alternative in {@link MariaDBParser#tableOption}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTableOptionRowFormat(MariaDBParser.TableOptionRowFormatContext ctx);
	/**
	 * Visit a parse tree produced by the {@code tableOptionStartTransaction}
	 * labeled alternative in {@link MariaDBParser#tableOption}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTableOptionStartTransaction(MariaDBParser.TableOptionStartTransactionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code tableOptionSecondaryEngineAttribute}
	 * labeled alternative in {@link MariaDBParser#tableOption}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTableOptionSecondaryEngineAttribute(MariaDBParser.TableOptionSecondaryEngineAttributeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code tableOptionRecalculation}
	 * labeled alternative in {@link MariaDBParser#tableOption}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTableOptionRecalculation(MariaDBParser.TableOptionRecalculationContext ctx);
	/**
	 * Visit a parse tree produced by the {@code tableOptionPersistent}
	 * labeled alternative in {@link MariaDBParser#tableOption}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTableOptionPersistent(MariaDBParser.TableOptionPersistentContext ctx);
	/**
	 * Visit a parse tree produced by the {@code tableOptionSamplePage}
	 * labeled alternative in {@link MariaDBParser#tableOption}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTableOptionSamplePage(MariaDBParser.TableOptionSamplePageContext ctx);
	/**
	 * Visit a parse tree produced by the {@code tableOptionTablespace}
	 * labeled alternative in {@link MariaDBParser#tableOption}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTableOptionTablespace(MariaDBParser.TableOptionTablespaceContext ctx);
	/**
	 * Visit a parse tree produced by the {@code tableOptionTableType}
	 * labeled alternative in {@link MariaDBParser#tableOption}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTableOptionTableType(MariaDBParser.TableOptionTableTypeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code tableOptionTransactional}
	 * labeled alternative in {@link MariaDBParser#tableOption}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTableOptionTransactional(MariaDBParser.TableOptionTransactionalContext ctx);
	/**
	 * Visit a parse tree produced by the {@code tableOptionUnion}
	 * labeled alternative in {@link MariaDBParser#tableOption}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTableOptionUnion(MariaDBParser.TableOptionUnionContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#tableType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTableType(MariaDBParser.TableTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#tablespaceStorage}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTablespaceStorage(MariaDBParser.TablespaceStorageContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#partitionDefinitions}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPartitionDefinitions(MariaDBParser.PartitionDefinitionsContext ctx);
	/**
	 * Visit a parse tree produced by the {@code partitionFunctionHash}
	 * labeled alternative in {@link MariaDBParser#partitionFunctionDefinition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPartitionFunctionHash(MariaDBParser.PartitionFunctionHashContext ctx);
	/**
	 * Visit a parse tree produced by the {@code partitionFunctionKey}
	 * labeled alternative in {@link MariaDBParser#partitionFunctionDefinition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPartitionFunctionKey(MariaDBParser.PartitionFunctionKeyContext ctx);
	/**
	 * Visit a parse tree produced by the {@code partitionFunctionRange}
	 * labeled alternative in {@link MariaDBParser#partitionFunctionDefinition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPartitionFunctionRange(MariaDBParser.PartitionFunctionRangeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code partitionFunctionList}
	 * labeled alternative in {@link MariaDBParser#partitionFunctionDefinition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPartitionFunctionList(MariaDBParser.PartitionFunctionListContext ctx);
	/**
	 * Visit a parse tree produced by the {@code subPartitionFunctionHash}
	 * labeled alternative in {@link MariaDBParser#subpartitionFunctionDefinition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSubPartitionFunctionHash(MariaDBParser.SubPartitionFunctionHashContext ctx);
	/**
	 * Visit a parse tree produced by the {@code subPartitionFunctionKey}
	 * labeled alternative in {@link MariaDBParser#subpartitionFunctionDefinition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSubPartitionFunctionKey(MariaDBParser.SubPartitionFunctionKeyContext ctx);
	/**
	 * Visit a parse tree produced by the {@code partitionComparison}
	 * labeled alternative in {@link MariaDBParser#partitionDefinition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPartitionComparison(MariaDBParser.PartitionComparisonContext ctx);
	/**
	 * Visit a parse tree produced by the {@code partitionListAtom}
	 * labeled alternative in {@link MariaDBParser#partitionDefinition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPartitionListAtom(MariaDBParser.PartitionListAtomContext ctx);
	/**
	 * Visit a parse tree produced by the {@code partitionListVector}
	 * labeled alternative in {@link MariaDBParser#partitionDefinition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPartitionListVector(MariaDBParser.PartitionListVectorContext ctx);
	/**
	 * Visit a parse tree produced by the {@code partitionSimple}
	 * labeled alternative in {@link MariaDBParser#partitionDefinition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPartitionSimple(MariaDBParser.PartitionSimpleContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#partitionDefinerAtom}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPartitionDefinerAtom(MariaDBParser.PartitionDefinerAtomContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#partitionDefinerVector}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPartitionDefinerVector(MariaDBParser.PartitionDefinerVectorContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#subpartitionDefinition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSubpartitionDefinition(MariaDBParser.SubpartitionDefinitionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code partitionOptionEngine}
	 * labeled alternative in {@link MariaDBParser#partitionOption}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPartitionOptionEngine(MariaDBParser.PartitionOptionEngineContext ctx);
	/**
	 * Visit a parse tree produced by the {@code partitionOptionComment}
	 * labeled alternative in {@link MariaDBParser#partitionOption}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPartitionOptionComment(MariaDBParser.PartitionOptionCommentContext ctx);
	/**
	 * Visit a parse tree produced by the {@code partitionOptionDataDirectory}
	 * labeled alternative in {@link MariaDBParser#partitionOption}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPartitionOptionDataDirectory(MariaDBParser.PartitionOptionDataDirectoryContext ctx);
	/**
	 * Visit a parse tree produced by the {@code partitionOptionIndexDirectory}
	 * labeled alternative in {@link MariaDBParser#partitionOption}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPartitionOptionIndexDirectory(MariaDBParser.PartitionOptionIndexDirectoryContext ctx);
	/**
	 * Visit a parse tree produced by the {@code partitionOptionMaxRows}
	 * labeled alternative in {@link MariaDBParser#partitionOption}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPartitionOptionMaxRows(MariaDBParser.PartitionOptionMaxRowsContext ctx);
	/**
	 * Visit a parse tree produced by the {@code partitionOptionMinRows}
	 * labeled alternative in {@link MariaDBParser#partitionOption}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPartitionOptionMinRows(MariaDBParser.PartitionOptionMinRowsContext ctx);
	/**
	 * Visit a parse tree produced by the {@code partitionOptionTablespace}
	 * labeled alternative in {@link MariaDBParser#partitionOption}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPartitionOptionTablespace(MariaDBParser.PartitionOptionTablespaceContext ctx);
	/**
	 * Visit a parse tree produced by the {@code partitionOptionNodeGroup}
	 * labeled alternative in {@link MariaDBParser#partitionOption}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPartitionOptionNodeGroup(MariaDBParser.PartitionOptionNodeGroupContext ctx);
	/**
	 * Visit a parse tree produced by the {@code alterSimpleDatabase}
	 * labeled alternative in {@link MariaDBParser#alterDatabase}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAlterSimpleDatabase(MariaDBParser.AlterSimpleDatabaseContext ctx);
	/**
	 * Visit a parse tree produced by the {@code alterUpgradeName}
	 * labeled alternative in {@link MariaDBParser#alterDatabase}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAlterUpgradeName(MariaDBParser.AlterUpgradeNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#alterEvent}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAlterEvent(MariaDBParser.AlterEventContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#alterFunction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAlterFunction(MariaDBParser.AlterFunctionContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#alterInstance}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAlterInstance(MariaDBParser.AlterInstanceContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#alterLogfileGroup}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAlterLogfileGroup(MariaDBParser.AlterLogfileGroupContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#alterProcedure}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAlterProcedure(MariaDBParser.AlterProcedureContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#alterServer}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAlterServer(MariaDBParser.AlterServerContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#alterTable}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAlterTable(MariaDBParser.AlterTableContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#alterTablespace}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAlterTablespace(MariaDBParser.AlterTablespaceContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#alterView}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAlterView(MariaDBParser.AlterViewContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#alterSequence}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAlterSequence(MariaDBParser.AlterSequenceContext ctx);
	/**
	 * Visit a parse tree produced by the {@code alterByTableOption}
	 * labeled alternative in {@link MariaDBParser#alterSpecification}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAlterByTableOption(MariaDBParser.AlterByTableOptionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code alterByAddColumn}
	 * labeled alternative in {@link MariaDBParser#alterSpecification}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAlterByAddColumn(MariaDBParser.AlterByAddColumnContext ctx);
	/**
	 * Visit a parse tree produced by the {@code alterByAddColumns}
	 * labeled alternative in {@link MariaDBParser#alterSpecification}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAlterByAddColumns(MariaDBParser.AlterByAddColumnsContext ctx);
	/**
	 * Visit a parse tree produced by the {@code alterByAddIndex}
	 * labeled alternative in {@link MariaDBParser#alterSpecification}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAlterByAddIndex(MariaDBParser.AlterByAddIndexContext ctx);
	/**
	 * Visit a parse tree produced by the {@code alterByAddPrimaryKey}
	 * labeled alternative in {@link MariaDBParser#alterSpecification}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAlterByAddPrimaryKey(MariaDBParser.AlterByAddPrimaryKeyContext ctx);
	/**
	 * Visit a parse tree produced by the {@code alterByAddUniqueKey}
	 * labeled alternative in {@link MariaDBParser#alterSpecification}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAlterByAddUniqueKey(MariaDBParser.AlterByAddUniqueKeyContext ctx);
	/**
	 * Visit a parse tree produced by the {@code alterByAddSpecialIndex}
	 * labeled alternative in {@link MariaDBParser#alterSpecification}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAlterByAddSpecialIndex(MariaDBParser.AlterByAddSpecialIndexContext ctx);
	/**
	 * Visit a parse tree produced by the {@code alterByAddForeignKey}
	 * labeled alternative in {@link MariaDBParser#alterSpecification}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAlterByAddForeignKey(MariaDBParser.AlterByAddForeignKeyContext ctx);
	/**
	 * Visit a parse tree produced by the {@code alterByAddCheckTableConstraint}
	 * labeled alternative in {@link MariaDBParser#alterSpecification}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAlterByAddCheckTableConstraint(MariaDBParser.AlterByAddCheckTableConstraintContext ctx);
	/**
	 * Visit a parse tree produced by the {@code alterBySetAlgorithm}
	 * labeled alternative in {@link MariaDBParser#alterSpecification}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAlterBySetAlgorithm(MariaDBParser.AlterBySetAlgorithmContext ctx);
	/**
	 * Visit a parse tree produced by the {@code alterByChangeDefault}
	 * labeled alternative in {@link MariaDBParser#alterSpecification}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAlterByChangeDefault(MariaDBParser.AlterByChangeDefaultContext ctx);
	/**
	 * Visit a parse tree produced by the {@code alterByChangeColumn}
	 * labeled alternative in {@link MariaDBParser#alterSpecification}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAlterByChangeColumn(MariaDBParser.AlterByChangeColumnContext ctx);
	/**
	 * Visit a parse tree produced by the {@code alterByRenameColumn}
	 * labeled alternative in {@link MariaDBParser#alterSpecification}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAlterByRenameColumn(MariaDBParser.AlterByRenameColumnContext ctx);
	/**
	 * Visit a parse tree produced by the {@code alterByLock}
	 * labeled alternative in {@link MariaDBParser#alterSpecification}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAlterByLock(MariaDBParser.AlterByLockContext ctx);
	/**
	 * Visit a parse tree produced by the {@code alterByModifyColumn}
	 * labeled alternative in {@link MariaDBParser#alterSpecification}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAlterByModifyColumn(MariaDBParser.AlterByModifyColumnContext ctx);
	/**
	 * Visit a parse tree produced by the {@code alterByDropColumn}
	 * labeled alternative in {@link MariaDBParser#alterSpecification}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAlterByDropColumn(MariaDBParser.AlterByDropColumnContext ctx);
	/**
	 * Visit a parse tree produced by the {@code alterByDropConstraintCheck}
	 * labeled alternative in {@link MariaDBParser#alterSpecification}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAlterByDropConstraintCheck(MariaDBParser.AlterByDropConstraintCheckContext ctx);
	/**
	 * Visit a parse tree produced by the {@code alterByDropPrimaryKey}
	 * labeled alternative in {@link MariaDBParser#alterSpecification}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAlterByDropPrimaryKey(MariaDBParser.AlterByDropPrimaryKeyContext ctx);
	/**
	 * Visit a parse tree produced by the {@code alterByDropIndex}
	 * labeled alternative in {@link MariaDBParser#alterSpecification}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAlterByDropIndex(MariaDBParser.AlterByDropIndexContext ctx);
	/**
	 * Visit a parse tree produced by the {@code alterByRenameIndex}
	 * labeled alternative in {@link MariaDBParser#alterSpecification}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAlterByRenameIndex(MariaDBParser.AlterByRenameIndexContext ctx);
	/**
	 * Visit a parse tree produced by the {@code alterByAlterIndexVisibility}
	 * labeled alternative in {@link MariaDBParser#alterSpecification}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAlterByAlterIndexVisibility(MariaDBParser.AlterByAlterIndexVisibilityContext ctx);
	/**
	 * Visit a parse tree produced by the {@code alterByDropForeignKey}
	 * labeled alternative in {@link MariaDBParser#alterSpecification}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAlterByDropForeignKey(MariaDBParser.AlterByDropForeignKeyContext ctx);
	/**
	 * Visit a parse tree produced by the {@code alterByDisableKeys}
	 * labeled alternative in {@link MariaDBParser#alterSpecification}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAlterByDisableKeys(MariaDBParser.AlterByDisableKeysContext ctx);
	/**
	 * Visit a parse tree produced by the {@code alterByEnableKeys}
	 * labeled alternative in {@link MariaDBParser#alterSpecification}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAlterByEnableKeys(MariaDBParser.AlterByEnableKeysContext ctx);
	/**
	 * Visit a parse tree produced by the {@code alterByRename}
	 * labeled alternative in {@link MariaDBParser#alterSpecification}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAlterByRename(MariaDBParser.AlterByRenameContext ctx);
	/**
	 * Visit a parse tree produced by the {@code alterByOrder}
	 * labeled alternative in {@link MariaDBParser#alterSpecification}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAlterByOrder(MariaDBParser.AlterByOrderContext ctx);
	/**
	 * Visit a parse tree produced by the {@code alterByConvertCharset}
	 * labeled alternative in {@link MariaDBParser#alterSpecification}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAlterByConvertCharset(MariaDBParser.AlterByConvertCharsetContext ctx);
	/**
	 * Visit a parse tree produced by the {@code alterByDefaultCharset}
	 * labeled alternative in {@link MariaDBParser#alterSpecification}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAlterByDefaultCharset(MariaDBParser.AlterByDefaultCharsetContext ctx);
	/**
	 * Visit a parse tree produced by the {@code alterByDiscardTablespace}
	 * labeled alternative in {@link MariaDBParser#alterSpecification}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAlterByDiscardTablespace(MariaDBParser.AlterByDiscardTablespaceContext ctx);
	/**
	 * Visit a parse tree produced by the {@code alterByImportTablespace}
	 * labeled alternative in {@link MariaDBParser#alterSpecification}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAlterByImportTablespace(MariaDBParser.AlterByImportTablespaceContext ctx);
	/**
	 * Visit a parse tree produced by the {@code alterByForce}
	 * labeled alternative in {@link MariaDBParser#alterSpecification}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAlterByForce(MariaDBParser.AlterByForceContext ctx);
	/**
	 * Visit a parse tree produced by the {@code alterByValidate}
	 * labeled alternative in {@link MariaDBParser#alterSpecification}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAlterByValidate(MariaDBParser.AlterByValidateContext ctx);
	/**
	 * Visit a parse tree produced by the {@code alterByAddDefinitions}
	 * labeled alternative in {@link MariaDBParser#alterSpecification}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAlterByAddDefinitions(MariaDBParser.AlterByAddDefinitionsContext ctx);
	/**
	 * Visit a parse tree produced by the {@code alterPartition}
	 * labeled alternative in {@link MariaDBParser#alterSpecification}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAlterPartition(MariaDBParser.AlterPartitionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code alterByAddPartition}
	 * labeled alternative in {@link MariaDBParser#alterPartitionSpecification}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAlterByAddPartition(MariaDBParser.AlterByAddPartitionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code alterByDropPartition}
	 * labeled alternative in {@link MariaDBParser#alterPartitionSpecification}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAlterByDropPartition(MariaDBParser.AlterByDropPartitionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code alterByDiscardPartition}
	 * labeled alternative in {@link MariaDBParser#alterPartitionSpecification}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAlterByDiscardPartition(MariaDBParser.AlterByDiscardPartitionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code alterByImportPartition}
	 * labeled alternative in {@link MariaDBParser#alterPartitionSpecification}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAlterByImportPartition(MariaDBParser.AlterByImportPartitionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code alterByTruncatePartition}
	 * labeled alternative in {@link MariaDBParser#alterPartitionSpecification}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAlterByTruncatePartition(MariaDBParser.AlterByTruncatePartitionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code alterByCoalescePartition}
	 * labeled alternative in {@link MariaDBParser#alterPartitionSpecification}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAlterByCoalescePartition(MariaDBParser.AlterByCoalescePartitionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code alterByReorganizePartition}
	 * labeled alternative in {@link MariaDBParser#alterPartitionSpecification}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAlterByReorganizePartition(MariaDBParser.AlterByReorganizePartitionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code alterByExchangePartition}
	 * labeled alternative in {@link MariaDBParser#alterPartitionSpecification}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAlterByExchangePartition(MariaDBParser.AlterByExchangePartitionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code alterByAnalyzePartition}
	 * labeled alternative in {@link MariaDBParser#alterPartitionSpecification}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAlterByAnalyzePartition(MariaDBParser.AlterByAnalyzePartitionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code alterByCheckPartition}
	 * labeled alternative in {@link MariaDBParser#alterPartitionSpecification}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAlterByCheckPartition(MariaDBParser.AlterByCheckPartitionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code alterByOptimizePartition}
	 * labeled alternative in {@link MariaDBParser#alterPartitionSpecification}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAlterByOptimizePartition(MariaDBParser.AlterByOptimizePartitionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code alterByRebuildPartition}
	 * labeled alternative in {@link MariaDBParser#alterPartitionSpecification}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAlterByRebuildPartition(MariaDBParser.AlterByRebuildPartitionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code alterByRepairPartition}
	 * labeled alternative in {@link MariaDBParser#alterPartitionSpecification}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAlterByRepairPartition(MariaDBParser.AlterByRepairPartitionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code alterByRemovePartitioning}
	 * labeled alternative in {@link MariaDBParser#alterPartitionSpecification}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAlterByRemovePartitioning(MariaDBParser.AlterByRemovePartitioningContext ctx);
	/**
	 * Visit a parse tree produced by the {@code alterByUpgradePartitioning}
	 * labeled alternative in {@link MariaDBParser#alterPartitionSpecification}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAlterByUpgradePartitioning(MariaDBParser.AlterByUpgradePartitioningContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#dropDatabase}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDropDatabase(MariaDBParser.DropDatabaseContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#dropEvent}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDropEvent(MariaDBParser.DropEventContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#dropIndex}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDropIndex(MariaDBParser.DropIndexContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#dropLogfileGroup}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDropLogfileGroup(MariaDBParser.DropLogfileGroupContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#dropProcedure}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDropProcedure(MariaDBParser.DropProcedureContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#dropFunction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDropFunction(MariaDBParser.DropFunctionContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#dropServer}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDropServer(MariaDBParser.DropServerContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#dropTable}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDropTable(MariaDBParser.DropTableContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#dropTablespace}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDropTablespace(MariaDBParser.DropTablespaceContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#dropTrigger}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDropTrigger(MariaDBParser.DropTriggerContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#dropView}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDropView(MariaDBParser.DropViewContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#dropRole}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDropRole(MariaDBParser.DropRoleContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#setRole}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSetRole(MariaDBParser.SetRoleContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#dropSequence}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDropSequence(MariaDBParser.DropSequenceContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#renameTable}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRenameTable(MariaDBParser.RenameTableContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#renameTableClause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRenameTableClause(MariaDBParser.RenameTableClauseContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#truncateTable}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTruncateTable(MariaDBParser.TruncateTableContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#callStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCallStatement(MariaDBParser.CallStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#deleteStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeleteStatement(MariaDBParser.DeleteStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#doStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDoStatement(MariaDBParser.DoStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#handlerStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHandlerStatement(MariaDBParser.HandlerStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#insertStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInsertStatement(MariaDBParser.InsertStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#loadDataStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLoadDataStatement(MariaDBParser.LoadDataStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#loadXmlStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLoadXmlStatement(MariaDBParser.LoadXmlStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#replaceStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReplaceStatement(MariaDBParser.ReplaceStatementContext ctx);
	/**
	 * Visit a parse tree produced by the {@code simpleSelect}
	 * labeled alternative in {@link MariaDBParser#selectStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSimpleSelect(MariaDBParser.SimpleSelectContext ctx);
	/**
	 * Visit a parse tree produced by the {@code parenthesisSelect}
	 * labeled alternative in {@link MariaDBParser#selectStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParenthesisSelect(MariaDBParser.ParenthesisSelectContext ctx);
	/**
	 * Visit a parse tree produced by the {@code unionSelect}
	 * labeled alternative in {@link MariaDBParser#selectStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnionSelect(MariaDBParser.UnionSelectContext ctx);
	/**
	 * Visit a parse tree produced by the {@code unionParenthesisSelect}
	 * labeled alternative in {@link MariaDBParser#selectStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnionParenthesisSelect(MariaDBParser.UnionParenthesisSelectContext ctx);
	/**
	 * Visit a parse tree produced by the {@code withLateralStatement}
	 * labeled alternative in {@link MariaDBParser#selectStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWithLateralStatement(MariaDBParser.WithLateralStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#updateStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUpdateStatement(MariaDBParser.UpdateStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#valuesStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitValuesStatement(MariaDBParser.ValuesStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#insertStatementValue}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInsertStatementValue(MariaDBParser.InsertStatementValueContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#updatedElement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUpdatedElement(MariaDBParser.UpdatedElementContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#assignmentField}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignmentField(MariaDBParser.AssignmentFieldContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#lockClause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLockClause(MariaDBParser.LockClauseContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#singleDeleteStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSingleDeleteStatement(MariaDBParser.SingleDeleteStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#multipleDeleteStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMultipleDeleteStatement(MariaDBParser.MultipleDeleteStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#handlerOpenStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHandlerOpenStatement(MariaDBParser.HandlerOpenStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#handlerReadIndexStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHandlerReadIndexStatement(MariaDBParser.HandlerReadIndexStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#handlerReadStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHandlerReadStatement(MariaDBParser.HandlerReadStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#handlerCloseStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHandlerCloseStatement(MariaDBParser.HandlerCloseStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#singleUpdateStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSingleUpdateStatement(MariaDBParser.SingleUpdateStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#multipleUpdateStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMultipleUpdateStatement(MariaDBParser.MultipleUpdateStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#orderByClause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOrderByClause(MariaDBParser.OrderByClauseContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#orderByExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOrderByExpression(MariaDBParser.OrderByExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#tableSources}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTableSources(MariaDBParser.TableSourcesContext ctx);
	/**
	 * Visit a parse tree produced by the {@code tableSourceBase}
	 * labeled alternative in {@link MariaDBParser#tableSource}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTableSourceBase(MariaDBParser.TableSourceBaseContext ctx);
	/**
	 * Visit a parse tree produced by the {@code tableSourceNested}
	 * labeled alternative in {@link MariaDBParser#tableSource}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTableSourceNested(MariaDBParser.TableSourceNestedContext ctx);
	/**
	 * Visit a parse tree produced by the {@code tableJson}
	 * labeled alternative in {@link MariaDBParser#tableSource}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTableJson(MariaDBParser.TableJsonContext ctx);
	/**
	 * Visit a parse tree produced by the {@code atomTableItem}
	 * labeled alternative in {@link MariaDBParser#tableSourceItem}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAtomTableItem(MariaDBParser.AtomTableItemContext ctx);
	/**
	 * Visit a parse tree produced by the {@code subqueryTableItem}
	 * labeled alternative in {@link MariaDBParser#tableSourceItem}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSubqueryTableItem(MariaDBParser.SubqueryTableItemContext ctx);
	/**
	 * Visit a parse tree produced by the {@code tableSourcesItem}
	 * labeled alternative in {@link MariaDBParser#tableSourceItem}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTableSourcesItem(MariaDBParser.TableSourcesItemContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#indexHint}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIndexHint(MariaDBParser.IndexHintContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#indexHintType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIndexHintType(MariaDBParser.IndexHintTypeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code innerJoin}
	 * labeled alternative in {@link MariaDBParser#joinPart}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInnerJoin(MariaDBParser.InnerJoinContext ctx);
	/**
	 * Visit a parse tree produced by the {@code straightJoin}
	 * labeled alternative in {@link MariaDBParser#joinPart}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStraightJoin(MariaDBParser.StraightJoinContext ctx);
	/**
	 * Visit a parse tree produced by the {@code outerJoin}
	 * labeled alternative in {@link MariaDBParser#joinPart}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOuterJoin(MariaDBParser.OuterJoinContext ctx);
	/**
	 * Visit a parse tree produced by the {@code naturalJoin}
	 * labeled alternative in {@link MariaDBParser#joinPart}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNaturalJoin(MariaDBParser.NaturalJoinContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#queryExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitQueryExpression(MariaDBParser.QueryExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#queryExpressionNointo}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitQueryExpressionNointo(MariaDBParser.QueryExpressionNointoContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#querySpecification}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitQuerySpecification(MariaDBParser.QuerySpecificationContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#querySpecificationNointo}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitQuerySpecificationNointo(MariaDBParser.QuerySpecificationNointoContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#unionParenthesis}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnionParenthesis(MariaDBParser.UnionParenthesisContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#unionStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnionStatement(MariaDBParser.UnionStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#lateralStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLateralStatement(MariaDBParser.LateralStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#jsonTable}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitJsonTable(MariaDBParser.JsonTableContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#jsonColumnList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitJsonColumnList(MariaDBParser.JsonColumnListContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#jsonColumn}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitJsonColumn(MariaDBParser.JsonColumnContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#jsonOnEmpty}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitJsonOnEmpty(MariaDBParser.JsonOnEmptyContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#jsonOnError}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitJsonOnError(MariaDBParser.JsonOnErrorContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#selectSpec}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSelectSpec(MariaDBParser.SelectSpecContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#selectElements}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSelectElements(MariaDBParser.SelectElementsContext ctx);
	/**
	 * Visit a parse tree produced by the {@code selectStarElement}
	 * labeled alternative in {@link MariaDBParser#selectElement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSelectStarElement(MariaDBParser.SelectStarElementContext ctx);
	/**
	 * Visit a parse tree produced by the {@code selectColumnElement}
	 * labeled alternative in {@link MariaDBParser#selectElement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSelectColumnElement(MariaDBParser.SelectColumnElementContext ctx);
	/**
	 * Visit a parse tree produced by the {@code selectFunctionElement}
	 * labeled alternative in {@link MariaDBParser#selectElement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSelectFunctionElement(MariaDBParser.SelectFunctionElementContext ctx);
	/**
	 * Visit a parse tree produced by the {@code selectExpressionElement}
	 * labeled alternative in {@link MariaDBParser#selectElement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSelectExpressionElement(MariaDBParser.SelectExpressionElementContext ctx);
	/**
	 * Visit a parse tree produced by the {@code selectIntoVariables}
	 * labeled alternative in {@link MariaDBParser#selectIntoExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSelectIntoVariables(MariaDBParser.SelectIntoVariablesContext ctx);
	/**
	 * Visit a parse tree produced by the {@code selectIntoDumpFile}
	 * labeled alternative in {@link MariaDBParser#selectIntoExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSelectIntoDumpFile(MariaDBParser.SelectIntoDumpFileContext ctx);
	/**
	 * Visit a parse tree produced by the {@code selectIntoTextFile}
	 * labeled alternative in {@link MariaDBParser#selectIntoExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSelectIntoTextFile(MariaDBParser.SelectIntoTextFileContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#selectFieldsInto}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSelectFieldsInto(MariaDBParser.SelectFieldsIntoContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#selectLinesInto}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSelectLinesInto(MariaDBParser.SelectLinesIntoContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#fromClause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFromClause(MariaDBParser.FromClauseContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#groupByClause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGroupByClause(MariaDBParser.GroupByClauseContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#havingClause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHavingClause(MariaDBParser.HavingClauseContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#windowClause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWindowClause(MariaDBParser.WindowClauseContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#groupByItem}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGroupByItem(MariaDBParser.GroupByItemContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#limitClause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLimitClause(MariaDBParser.LimitClauseContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#limitClauseAtom}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLimitClauseAtom(MariaDBParser.LimitClauseAtomContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#startTransaction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStartTransaction(MariaDBParser.StartTransactionContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#beginWork}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBeginWork(MariaDBParser.BeginWorkContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#commitWork}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCommitWork(MariaDBParser.CommitWorkContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#rollbackWork}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRollbackWork(MariaDBParser.RollbackWorkContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#savepointStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSavepointStatement(MariaDBParser.SavepointStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#rollbackStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRollbackStatement(MariaDBParser.RollbackStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#releaseStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReleaseStatement(MariaDBParser.ReleaseStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#lockTables}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLockTables(MariaDBParser.LockTablesContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#unlockTables}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnlockTables(MariaDBParser.UnlockTablesContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#setAutocommitStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSetAutocommitStatement(MariaDBParser.SetAutocommitStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#setTransactionStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSetTransactionStatement(MariaDBParser.SetTransactionStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#transactionMode}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTransactionMode(MariaDBParser.TransactionModeContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#lockTableElement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLockTableElement(MariaDBParser.LockTableElementContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#lockAction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLockAction(MariaDBParser.LockActionContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#transactionOption}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTransactionOption(MariaDBParser.TransactionOptionContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#transactionLevel}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTransactionLevel(MariaDBParser.TransactionLevelContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#changeMaster}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitChangeMaster(MariaDBParser.ChangeMasterContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#changeReplicationFilter}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitChangeReplicationFilter(MariaDBParser.ChangeReplicationFilterContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#purgeBinaryLogs}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPurgeBinaryLogs(MariaDBParser.PurgeBinaryLogsContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#resetMaster}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitResetMaster(MariaDBParser.ResetMasterContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#resetSlave}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitResetSlave(MariaDBParser.ResetSlaveContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#startSlave}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStartSlave(MariaDBParser.StartSlaveContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#stopSlave}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStopSlave(MariaDBParser.StopSlaveContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#startGroupReplication}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStartGroupReplication(MariaDBParser.StartGroupReplicationContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#stopGroupReplication}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStopGroupReplication(MariaDBParser.StopGroupReplicationContext ctx);
	/**
	 * Visit a parse tree produced by the {@code masterStringOption}
	 * labeled alternative in {@link MariaDBParser#masterOption}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMasterStringOption(MariaDBParser.MasterStringOptionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code masterDecimalOption}
	 * labeled alternative in {@link MariaDBParser#masterOption}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMasterDecimalOption(MariaDBParser.MasterDecimalOptionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code masterBoolOption}
	 * labeled alternative in {@link MariaDBParser#masterOption}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMasterBoolOption(MariaDBParser.MasterBoolOptionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code masterRealOption}
	 * labeled alternative in {@link MariaDBParser#masterOption}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMasterRealOption(MariaDBParser.MasterRealOptionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code masterUidListOption}
	 * labeled alternative in {@link MariaDBParser#masterOption}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMasterUidListOption(MariaDBParser.MasterUidListOptionContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#stringMasterOption}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStringMasterOption(MariaDBParser.StringMasterOptionContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#decimalMasterOption}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDecimalMasterOption(MariaDBParser.DecimalMasterOptionContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#boolMasterOption}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBoolMasterOption(MariaDBParser.BoolMasterOptionContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#channelOption}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitChannelOption(MariaDBParser.ChannelOptionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code doDbReplication}
	 * labeled alternative in {@link MariaDBParser#replicationFilter}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDoDbReplication(MariaDBParser.DoDbReplicationContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ignoreDbReplication}
	 * labeled alternative in {@link MariaDBParser#replicationFilter}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIgnoreDbReplication(MariaDBParser.IgnoreDbReplicationContext ctx);
	/**
	 * Visit a parse tree produced by the {@code doTableReplication}
	 * labeled alternative in {@link MariaDBParser#replicationFilter}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDoTableReplication(MariaDBParser.DoTableReplicationContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ignoreTableReplication}
	 * labeled alternative in {@link MariaDBParser#replicationFilter}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIgnoreTableReplication(MariaDBParser.IgnoreTableReplicationContext ctx);
	/**
	 * Visit a parse tree produced by the {@code wildDoTableReplication}
	 * labeled alternative in {@link MariaDBParser#replicationFilter}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWildDoTableReplication(MariaDBParser.WildDoTableReplicationContext ctx);
	/**
	 * Visit a parse tree produced by the {@code wildIgnoreTableReplication}
	 * labeled alternative in {@link MariaDBParser#replicationFilter}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWildIgnoreTableReplication(MariaDBParser.WildIgnoreTableReplicationContext ctx);
	/**
	 * Visit a parse tree produced by the {@code rewriteDbReplication}
	 * labeled alternative in {@link MariaDBParser#replicationFilter}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRewriteDbReplication(MariaDBParser.RewriteDbReplicationContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#tablePair}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTablePair(MariaDBParser.TablePairContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#threadType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitThreadType(MariaDBParser.ThreadTypeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code gtidsUntilOption}
	 * labeled alternative in {@link MariaDBParser#untilOption}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGtidsUntilOption(MariaDBParser.GtidsUntilOptionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code masterLogUntilOption}
	 * labeled alternative in {@link MariaDBParser#untilOption}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMasterLogUntilOption(MariaDBParser.MasterLogUntilOptionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code relayLogUntilOption}
	 * labeled alternative in {@link MariaDBParser#untilOption}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRelayLogUntilOption(MariaDBParser.RelayLogUntilOptionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code sqlGapsUntilOption}
	 * labeled alternative in {@link MariaDBParser#untilOption}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSqlGapsUntilOption(MariaDBParser.SqlGapsUntilOptionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code userConnectionOption}
	 * labeled alternative in {@link MariaDBParser#connectionOption}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUserConnectionOption(MariaDBParser.UserConnectionOptionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code passwordConnectionOption}
	 * labeled alternative in {@link MariaDBParser#connectionOption}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPasswordConnectionOption(MariaDBParser.PasswordConnectionOptionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code defaultAuthConnectionOption}
	 * labeled alternative in {@link MariaDBParser#connectionOption}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDefaultAuthConnectionOption(MariaDBParser.DefaultAuthConnectionOptionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code pluginDirConnectionOption}
	 * labeled alternative in {@link MariaDBParser#connectionOption}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPluginDirConnectionOption(MariaDBParser.PluginDirConnectionOptionContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#gtuidSet}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGtuidSet(MariaDBParser.GtuidSetContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#xaStartTransaction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitXaStartTransaction(MariaDBParser.XaStartTransactionContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#xaEndTransaction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitXaEndTransaction(MariaDBParser.XaEndTransactionContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#xaPrepareStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitXaPrepareStatement(MariaDBParser.XaPrepareStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#xaCommitWork}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitXaCommitWork(MariaDBParser.XaCommitWorkContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#xaRollbackWork}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitXaRollbackWork(MariaDBParser.XaRollbackWorkContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#xaRecoverWork}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitXaRecoverWork(MariaDBParser.XaRecoverWorkContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#prepareStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrepareStatement(MariaDBParser.PrepareStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#executeStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExecuteStatement(MariaDBParser.ExecuteStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#deallocatePrepare}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeallocatePrepare(MariaDBParser.DeallocatePrepareContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#routineBody}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRoutineBody(MariaDBParser.RoutineBodyContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#blockStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlockStatement(MariaDBParser.BlockStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#caseStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCaseStatement(MariaDBParser.CaseStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#ifStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfStatement(MariaDBParser.IfStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#iterateStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIterateStatement(MariaDBParser.IterateStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#leaveStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLeaveStatement(MariaDBParser.LeaveStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#loopStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLoopStatement(MariaDBParser.LoopStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#repeatStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRepeatStatement(MariaDBParser.RepeatStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#returnStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReturnStatement(MariaDBParser.ReturnStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#whileStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWhileStatement(MariaDBParser.WhileStatementContext ctx);
	/**
	 * Visit a parse tree produced by the {@code CloseCursor}
	 * labeled alternative in {@link MariaDBParser#cursorStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCloseCursor(MariaDBParser.CloseCursorContext ctx);
	/**
	 * Visit a parse tree produced by the {@code FetchCursor}
	 * labeled alternative in {@link MariaDBParser#cursorStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFetchCursor(MariaDBParser.FetchCursorContext ctx);
	/**
	 * Visit a parse tree produced by the {@code OpenCursor}
	 * labeled alternative in {@link MariaDBParser#cursorStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOpenCursor(MariaDBParser.OpenCursorContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#declareVariable}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclareVariable(MariaDBParser.DeclareVariableContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#declareCondition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclareCondition(MariaDBParser.DeclareConditionContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#declareCursor}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclareCursor(MariaDBParser.DeclareCursorContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#declareHandler}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclareHandler(MariaDBParser.DeclareHandlerContext ctx);
	/**
	 * Visit a parse tree produced by the {@code handlerConditionCode}
	 * labeled alternative in {@link MariaDBParser#handlerConditionValue}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHandlerConditionCode(MariaDBParser.HandlerConditionCodeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code handlerConditionState}
	 * labeled alternative in {@link MariaDBParser#handlerConditionValue}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHandlerConditionState(MariaDBParser.HandlerConditionStateContext ctx);
	/**
	 * Visit a parse tree produced by the {@code handlerConditionName}
	 * labeled alternative in {@link MariaDBParser#handlerConditionValue}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHandlerConditionName(MariaDBParser.HandlerConditionNameContext ctx);
	/**
	 * Visit a parse tree produced by the {@code handlerConditionWarning}
	 * labeled alternative in {@link MariaDBParser#handlerConditionValue}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHandlerConditionWarning(MariaDBParser.HandlerConditionWarningContext ctx);
	/**
	 * Visit a parse tree produced by the {@code handlerConditionNotfound}
	 * labeled alternative in {@link MariaDBParser#handlerConditionValue}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHandlerConditionNotfound(MariaDBParser.HandlerConditionNotfoundContext ctx);
	/**
	 * Visit a parse tree produced by the {@code handlerConditionException}
	 * labeled alternative in {@link MariaDBParser#handlerConditionValue}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHandlerConditionException(MariaDBParser.HandlerConditionExceptionContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#procedureSqlStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProcedureSqlStatement(MariaDBParser.ProcedureSqlStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#caseAlternative}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCaseAlternative(MariaDBParser.CaseAlternativeContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#elifAlternative}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitElifAlternative(MariaDBParser.ElifAlternativeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code alterUserMysqlV56}
	 * labeled alternative in {@link MariaDBParser#alterUser}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAlterUserMysqlV56(MariaDBParser.AlterUserMysqlV56Context ctx);
	/**
	 * Visit a parse tree produced by the {@code alterUserMysqlV80}
	 * labeled alternative in {@link MariaDBParser#alterUser}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAlterUserMysqlV80(MariaDBParser.AlterUserMysqlV80Context ctx);
	/**
	 * Visit a parse tree produced by the {@code createUserMysqlV56}
	 * labeled alternative in {@link MariaDBParser#createUser}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCreateUserMysqlV56(MariaDBParser.CreateUserMysqlV56Context ctx);
	/**
	 * Visit a parse tree produced by the {@code createUserMysqlV80}
	 * labeled alternative in {@link MariaDBParser#createUser}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCreateUserMysqlV80(MariaDBParser.CreateUserMysqlV80Context ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#dropUser}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDropUser(MariaDBParser.DropUserContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#grantStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGrantStatement(MariaDBParser.GrantStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#roleOption}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRoleOption(MariaDBParser.RoleOptionContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#grantProxy}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGrantProxy(MariaDBParser.GrantProxyContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#renameUser}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRenameUser(MariaDBParser.RenameUserContext ctx);
	/**
	 * Visit a parse tree produced by the {@code detailRevoke}
	 * labeled alternative in {@link MariaDBParser#revokeStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDetailRevoke(MariaDBParser.DetailRevokeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code shortRevoke}
	 * labeled alternative in {@link MariaDBParser#revokeStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitShortRevoke(MariaDBParser.ShortRevokeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code roleRevoke}
	 * labeled alternative in {@link MariaDBParser#revokeStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRoleRevoke(MariaDBParser.RoleRevokeContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#revokeProxy}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRevokeProxy(MariaDBParser.RevokeProxyContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#setPasswordStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSetPasswordStatement(MariaDBParser.SetPasswordStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#userSpecification}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUserSpecification(MariaDBParser.UserSpecificationContext ctx);
	/**
	 * Visit a parse tree produced by the {@code hashAuthOption}
	 * labeled alternative in {@link MariaDBParser#userAuthOption}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHashAuthOption(MariaDBParser.HashAuthOptionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code stringAuthOption}
	 * labeled alternative in {@link MariaDBParser#userAuthOption}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStringAuthOption(MariaDBParser.StringAuthOptionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code moduleAuthOption}
	 * labeled alternative in {@link MariaDBParser#userAuthOption}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitModuleAuthOption(MariaDBParser.ModuleAuthOptionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code simpleAuthOption}
	 * labeled alternative in {@link MariaDBParser#userAuthOption}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSimpleAuthOption(MariaDBParser.SimpleAuthOptionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code module}
	 * labeled alternative in {@link MariaDBParser#authenticationRule}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitModule(MariaDBParser.ModuleContext ctx);
	/**
	 * Visit a parse tree produced by the {@code passwordModuleOption}
	 * labeled alternative in {@link MariaDBParser#authenticationRule}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPasswordModuleOption(MariaDBParser.PasswordModuleOptionContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#tlsOption}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTlsOption(MariaDBParser.TlsOptionContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#userResourceOption}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUserResourceOption(MariaDBParser.UserResourceOptionContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#userPasswordOption}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUserPasswordOption(MariaDBParser.UserPasswordOptionContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#userLockOption}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUserLockOption(MariaDBParser.UserLockOptionContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#privelegeClause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrivelegeClause(MariaDBParser.PrivelegeClauseContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#privilege}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrivilege(MariaDBParser.PrivilegeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code currentSchemaPriviLevel}
	 * labeled alternative in {@link MariaDBParser#privilegeLevel}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCurrentSchemaPriviLevel(MariaDBParser.CurrentSchemaPriviLevelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code globalPrivLevel}
	 * labeled alternative in {@link MariaDBParser#privilegeLevel}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGlobalPrivLevel(MariaDBParser.GlobalPrivLevelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code definiteSchemaPrivLevel}
	 * labeled alternative in {@link MariaDBParser#privilegeLevel}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDefiniteSchemaPrivLevel(MariaDBParser.DefiniteSchemaPrivLevelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code definiteFullTablePrivLevel}
	 * labeled alternative in {@link MariaDBParser#privilegeLevel}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDefiniteFullTablePrivLevel(MariaDBParser.DefiniteFullTablePrivLevelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code definiteFullTablePrivLevel2}
	 * labeled alternative in {@link MariaDBParser#privilegeLevel}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDefiniteFullTablePrivLevel2(MariaDBParser.DefiniteFullTablePrivLevel2Context ctx);
	/**
	 * Visit a parse tree produced by the {@code definiteTablePrivLevel}
	 * labeled alternative in {@link MariaDBParser#privilegeLevel}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDefiniteTablePrivLevel(MariaDBParser.DefiniteTablePrivLevelContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#renameUserClause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRenameUserClause(MariaDBParser.RenameUserClauseContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#analyzeTable}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAnalyzeTable(MariaDBParser.AnalyzeTableContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#checkTable}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCheckTable(MariaDBParser.CheckTableContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#checksumTable}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitChecksumTable(MariaDBParser.ChecksumTableContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#optimizeTable}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOptimizeTable(MariaDBParser.OptimizeTableContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#repairTable}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRepairTable(MariaDBParser.RepairTableContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#checkTableOption}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCheckTableOption(MariaDBParser.CheckTableOptionContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#createUdfunction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCreateUdfunction(MariaDBParser.CreateUdfunctionContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#installPlugin}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInstallPlugin(MariaDBParser.InstallPluginContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#uninstallPlugin}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUninstallPlugin(MariaDBParser.UninstallPluginContext ctx);
	/**
	 * Visit a parse tree produced by the {@code setVariable}
	 * labeled alternative in {@link MariaDBParser#setStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSetVariable(MariaDBParser.SetVariableContext ctx);
	/**
	 * Visit a parse tree produced by the {@code setCharset}
	 * labeled alternative in {@link MariaDBParser#setStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSetCharset(MariaDBParser.SetCharsetContext ctx);
	/**
	 * Visit a parse tree produced by the {@code setNames}
	 * labeled alternative in {@link MariaDBParser#setStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSetNames(MariaDBParser.SetNamesContext ctx);
	/**
	 * Visit a parse tree produced by the {@code setPassword}
	 * labeled alternative in {@link MariaDBParser#setStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSetPassword(MariaDBParser.SetPasswordContext ctx);
	/**
	 * Visit a parse tree produced by the {@code setTransaction}
	 * labeled alternative in {@link MariaDBParser#setStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSetTransaction(MariaDBParser.SetTransactionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code setAutocommit}
	 * labeled alternative in {@link MariaDBParser#setStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSetAutocommit(MariaDBParser.SetAutocommitContext ctx);
	/**
	 * Visit a parse tree produced by the {@code setNewValueInsideTrigger}
	 * labeled alternative in {@link MariaDBParser#setStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSetNewValueInsideTrigger(MariaDBParser.SetNewValueInsideTriggerContext ctx);
	/**
	 * Visit a parse tree produced by the {@code showMasterLogs}
	 * labeled alternative in {@link MariaDBParser#showStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitShowMasterLogs(MariaDBParser.ShowMasterLogsContext ctx);
	/**
	 * Visit a parse tree produced by the {@code showBinLogEvents}
	 * labeled alternative in {@link MariaDBParser#showStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitShowBinLogEvents(MariaDBParser.ShowBinLogEventsContext ctx);
	/**
	 * Visit a parse tree produced by the {@code showRelayLogEvents}
	 * labeled alternative in {@link MariaDBParser#showStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitShowRelayLogEvents(MariaDBParser.ShowRelayLogEventsContext ctx);
	/**
	 * Visit a parse tree produced by the {@code showObjectFilter}
	 * labeled alternative in {@link MariaDBParser#showStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitShowObjectFilter(MariaDBParser.ShowObjectFilterContext ctx);
	/**
	 * Visit a parse tree produced by the {@code showColumns}
	 * labeled alternative in {@link MariaDBParser#showStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitShowColumns(MariaDBParser.ShowColumnsContext ctx);
	/**
	 * Visit a parse tree produced by the {@code showCreateDb}
	 * labeled alternative in {@link MariaDBParser#showStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitShowCreateDb(MariaDBParser.ShowCreateDbContext ctx);
	/**
	 * Visit a parse tree produced by the {@code showCreateFullIdObject}
	 * labeled alternative in {@link MariaDBParser#showStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitShowCreateFullIdObject(MariaDBParser.ShowCreateFullIdObjectContext ctx);
	/**
	 * Visit a parse tree produced by the {@code showCreatePackage}
	 * labeled alternative in {@link MariaDBParser#showStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitShowCreatePackage(MariaDBParser.ShowCreatePackageContext ctx);
	/**
	 * Visit a parse tree produced by the {@code showCreateUser}
	 * labeled alternative in {@link MariaDBParser#showStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitShowCreateUser(MariaDBParser.ShowCreateUserContext ctx);
	/**
	 * Visit a parse tree produced by the {@code showEngine}
	 * labeled alternative in {@link MariaDBParser#showStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitShowEngine(MariaDBParser.ShowEngineContext ctx);
	/**
	 * Visit a parse tree produced by the {@code showInnoDBStatus}
	 * labeled alternative in {@link MariaDBParser#showStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitShowInnoDBStatus(MariaDBParser.ShowInnoDBStatusContext ctx);
	/**
	 * Visit a parse tree produced by the {@code showGlobalInfo}
	 * labeled alternative in {@link MariaDBParser#showStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitShowGlobalInfo(MariaDBParser.ShowGlobalInfoContext ctx);
	/**
	 * Visit a parse tree produced by the {@code showErrors}
	 * labeled alternative in {@link MariaDBParser#showStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitShowErrors(MariaDBParser.ShowErrorsContext ctx);
	/**
	 * Visit a parse tree produced by the {@code showCountErrors}
	 * labeled alternative in {@link MariaDBParser#showStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitShowCountErrors(MariaDBParser.ShowCountErrorsContext ctx);
	/**
	 * Visit a parse tree produced by the {@code showSchemaFilter}
	 * labeled alternative in {@link MariaDBParser#showStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitShowSchemaFilter(MariaDBParser.ShowSchemaFilterContext ctx);
	/**
	 * Visit a parse tree produced by the {@code showRoutine}
	 * labeled alternative in {@link MariaDBParser#showStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitShowRoutine(MariaDBParser.ShowRoutineContext ctx);
	/**
	 * Visit a parse tree produced by the {@code showGrants}
	 * labeled alternative in {@link MariaDBParser#showStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitShowGrants(MariaDBParser.ShowGrantsContext ctx);
	/**
	 * Visit a parse tree produced by the {@code showIndexes}
	 * labeled alternative in {@link MariaDBParser#showStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitShowIndexes(MariaDBParser.ShowIndexesContext ctx);
	/**
	 * Visit a parse tree produced by the {@code showOpenTables}
	 * labeled alternative in {@link MariaDBParser#showStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitShowOpenTables(MariaDBParser.ShowOpenTablesContext ctx);
	/**
	 * Visit a parse tree produced by the {@code showProfile}
	 * labeled alternative in {@link MariaDBParser#showStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitShowProfile(MariaDBParser.ShowProfileContext ctx);
	/**
	 * Visit a parse tree produced by the {@code showSlaveStatus}
	 * labeled alternative in {@link MariaDBParser#showStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitShowSlaveStatus(MariaDBParser.ShowSlaveStatusContext ctx);
	/**
	 * Visit a parse tree produced by the {@code showUserstatPlugin}
	 * labeled alternative in {@link MariaDBParser#showStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitShowUserstatPlugin(MariaDBParser.ShowUserstatPluginContext ctx);
	/**
	 * Visit a parse tree produced by the {@code showExplain}
	 * labeled alternative in {@link MariaDBParser#showStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitShowExplain(MariaDBParser.ShowExplainContext ctx);
	/**
	 * Visit a parse tree produced by the {@code showPackageStatus}
	 * labeled alternative in {@link MariaDBParser#showStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitShowPackageStatus(MariaDBParser.ShowPackageStatusContext ctx);
	/**
	 * Visit a parse tree produced by the {@code explainForConnection}
	 * labeled alternative in {@link MariaDBParser#explainStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExplainForConnection(MariaDBParser.ExplainForConnectionContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#variableClause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariableClause(MariaDBParser.VariableClauseContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#showCommonEntity}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitShowCommonEntity(MariaDBParser.ShowCommonEntityContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#showFilter}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitShowFilter(MariaDBParser.ShowFilterContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#showGlobalInfoClause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitShowGlobalInfoClause(MariaDBParser.ShowGlobalInfoClauseContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#showSchemaEntity}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitShowSchemaEntity(MariaDBParser.ShowSchemaEntityContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#showProfileType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitShowProfileType(MariaDBParser.ShowProfileTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#binlogStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBinlogStatement(MariaDBParser.BinlogStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#cacheIndexStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCacheIndexStatement(MariaDBParser.CacheIndexStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#flushStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFlushStatement(MariaDBParser.FlushStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#killStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitKillStatement(MariaDBParser.KillStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#loadIndexIntoCache}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLoadIndexIntoCache(MariaDBParser.LoadIndexIntoCacheContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#resetStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitResetStatement(MariaDBParser.ResetStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#shutdownStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitShutdownStatement(MariaDBParser.ShutdownStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#tableIndexes}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTableIndexes(MariaDBParser.TableIndexesContext ctx);
	/**
	 * Visit a parse tree produced by the {@code simpleFlushOption}
	 * labeled alternative in {@link MariaDBParser#flushOption}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSimpleFlushOption(MariaDBParser.SimpleFlushOptionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code channelFlushOption}
	 * labeled alternative in {@link MariaDBParser#flushOption}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitChannelFlushOption(MariaDBParser.ChannelFlushOptionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code tableFlushOption}
	 * labeled alternative in {@link MariaDBParser#flushOption}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTableFlushOption(MariaDBParser.TableFlushOptionContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#flushTableOption}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFlushTableOption(MariaDBParser.FlushTableOptionContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#loadedTableIndexes}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLoadedTableIndexes(MariaDBParser.LoadedTableIndexesContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#simpleDescribeStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSimpleDescribeStatement(MariaDBParser.SimpleDescribeStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#fullDescribeStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFullDescribeStatement(MariaDBParser.FullDescribeStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#formatJsonStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFormatJsonStatement(MariaDBParser.FormatJsonStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#helpStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHelpStatement(MariaDBParser.HelpStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#useStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUseStatement(MariaDBParser.UseStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#signalStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSignalStatement(MariaDBParser.SignalStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#resignalStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitResignalStatement(MariaDBParser.ResignalStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#signalConditionInformation}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSignalConditionInformation(MariaDBParser.SignalConditionInformationContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#diagnosticsStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDiagnosticsStatement(MariaDBParser.DiagnosticsStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#diagnosticsConditionInformationName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDiagnosticsConditionInformationName(MariaDBParser.DiagnosticsConditionInformationNameContext ctx);
	/**
	 * Visit a parse tree produced by the {@code describeStatements}
	 * labeled alternative in {@link MariaDBParser#describeObjectClause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDescribeStatements(MariaDBParser.DescribeStatementsContext ctx);
	/**
	 * Visit a parse tree produced by the {@code describeConnection}
	 * labeled alternative in {@link MariaDBParser#describeObjectClause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDescribeConnection(MariaDBParser.DescribeConnectionContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#fullId}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFullId(MariaDBParser.FullIdContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#tableName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTableName(MariaDBParser.TableNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#roleName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRoleName(MariaDBParser.RoleNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#fullColumnName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFullColumnName(MariaDBParser.FullColumnNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#indexColumnName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIndexColumnName(MariaDBParser.IndexColumnNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#simpleUserName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSimpleUserName(MariaDBParser.SimpleUserNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#hostName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHostName(MariaDBParser.HostNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#userName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUserName(MariaDBParser.UserNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#mysqlVariable}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMysqlVariable(MariaDBParser.MysqlVariableContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#charsetName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCharsetName(MariaDBParser.CharsetNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#collationName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCollationName(MariaDBParser.CollationNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#engineName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEngineName(MariaDBParser.EngineNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#engineNameBase}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEngineNameBase(MariaDBParser.EngineNameBaseContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#encryptedLiteral}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEncryptedLiteral(MariaDBParser.EncryptedLiteralContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#uuidSet}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUuidSet(MariaDBParser.UuidSetContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#xid}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitXid(MariaDBParser.XidContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#xuidStringId}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitXuidStringId(MariaDBParser.XuidStringIdContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#authPlugin}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAuthPlugin(MariaDBParser.AuthPluginContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#uid}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUid(MariaDBParser.UidContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#simpleId}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSimpleId(MariaDBParser.SimpleIdContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#dottedId}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDottedId(MariaDBParser.DottedIdContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#decimalLiteral}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDecimalLiteral(MariaDBParser.DecimalLiteralContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#fileSizeLiteral}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFileSizeLiteral(MariaDBParser.FileSizeLiteralContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#stringLiteral}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStringLiteral(MariaDBParser.StringLiteralContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#booleanLiteral}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBooleanLiteral(MariaDBParser.BooleanLiteralContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#hexadecimalLiteral}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHexadecimalLiteral(MariaDBParser.HexadecimalLiteralContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#nullNotnull}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNullNotnull(MariaDBParser.NullNotnullContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#constant}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstant(MariaDBParser.ConstantContext ctx);
	/**
	 * Visit a parse tree produced by the {@code stringDataType}
	 * labeled alternative in {@link MariaDBParser#dataType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStringDataType(MariaDBParser.StringDataTypeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code nationalStringDataType}
	 * labeled alternative in {@link MariaDBParser#dataType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNationalStringDataType(MariaDBParser.NationalStringDataTypeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code nationalVaryingStringDataType}
	 * labeled alternative in {@link MariaDBParser#dataType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNationalVaryingStringDataType(MariaDBParser.NationalVaryingStringDataTypeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code dimensionDataType}
	 * labeled alternative in {@link MariaDBParser#dataType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDimensionDataType(MariaDBParser.DimensionDataTypeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code simpleDataType}
	 * labeled alternative in {@link MariaDBParser#dataType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSimpleDataType(MariaDBParser.SimpleDataTypeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code collectionDataType}
	 * labeled alternative in {@link MariaDBParser#dataType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCollectionDataType(MariaDBParser.CollectionDataTypeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code spatialDataType}
	 * labeled alternative in {@link MariaDBParser#dataType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSpatialDataType(MariaDBParser.SpatialDataTypeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code longVarcharDataType}
	 * labeled alternative in {@link MariaDBParser#dataType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLongVarcharDataType(MariaDBParser.LongVarcharDataTypeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code longVarbinaryDataType}
	 * labeled alternative in {@link MariaDBParser#dataType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLongVarbinaryDataType(MariaDBParser.LongVarbinaryDataTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#collectionOptions}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCollectionOptions(MariaDBParser.CollectionOptionsContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#convertedDataType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConvertedDataType(MariaDBParser.ConvertedDataTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#lengthOneDimension}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLengthOneDimension(MariaDBParser.LengthOneDimensionContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#lengthTwoDimension}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLengthTwoDimension(MariaDBParser.LengthTwoDimensionContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#lengthTwoOptionalDimension}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLengthTwoOptionalDimension(MariaDBParser.LengthTwoOptionalDimensionContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#uidList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUidList(MariaDBParser.UidListContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#tables}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTables(MariaDBParser.TablesContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#indexColumnNames}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIndexColumnNames(MariaDBParser.IndexColumnNamesContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#expressions}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpressions(MariaDBParser.ExpressionsContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#expressionsWithDefaults}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpressionsWithDefaults(MariaDBParser.ExpressionsWithDefaultsContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#constants}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstants(MariaDBParser.ConstantsContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#simpleStrings}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSimpleStrings(MariaDBParser.SimpleStringsContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#userVariables}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUserVariables(MariaDBParser.UserVariablesContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#defaultValue}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDefaultValue(MariaDBParser.DefaultValueContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#currentTimestamp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCurrentTimestamp(MariaDBParser.CurrentTimestampContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#expressionOrDefault}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpressionOrDefault(MariaDBParser.ExpressionOrDefaultContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#ifExists}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfExists(MariaDBParser.IfExistsContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#ifNotExists}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfNotExists(MariaDBParser.IfNotExistsContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#orReplace}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOrReplace(MariaDBParser.OrReplaceContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#waitNowaitClause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWaitNowaitClause(MariaDBParser.WaitNowaitClauseContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#lockOption}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLockOption(MariaDBParser.LockOptionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code specificFunctionCall}
	 * labeled alternative in {@link MariaDBParser#functionCall}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSpecificFunctionCall(MariaDBParser.SpecificFunctionCallContext ctx);
	/**
	 * Visit a parse tree produced by the {@code aggregateFunctionCall}
	 * labeled alternative in {@link MariaDBParser#functionCall}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAggregateFunctionCall(MariaDBParser.AggregateFunctionCallContext ctx);
	/**
	 * Visit a parse tree produced by the {@code nonAggregateFunctionCall}
	 * labeled alternative in {@link MariaDBParser#functionCall}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNonAggregateFunctionCall(MariaDBParser.NonAggregateFunctionCallContext ctx);
	/**
	 * Visit a parse tree produced by the {@code scalarFunctionCall}
	 * labeled alternative in {@link MariaDBParser#functionCall}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitScalarFunctionCall(MariaDBParser.ScalarFunctionCallContext ctx);
	/**
	 * Visit a parse tree produced by the {@code udfFunctionCall}
	 * labeled alternative in {@link MariaDBParser#functionCall}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUdfFunctionCall(MariaDBParser.UdfFunctionCallContext ctx);
	/**
	 * Visit a parse tree produced by the {@code passwordFunctionCall}
	 * labeled alternative in {@link MariaDBParser#functionCall}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPasswordFunctionCall(MariaDBParser.PasswordFunctionCallContext ctx);
	/**
	 * Visit a parse tree produced by the {@code simpleFunctionCall}
	 * labeled alternative in {@link MariaDBParser#specificFunction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSimpleFunctionCall(MariaDBParser.SimpleFunctionCallContext ctx);
	/**
	 * Visit a parse tree produced by the {@code dataTypeFunctionCall}
	 * labeled alternative in {@link MariaDBParser#specificFunction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDataTypeFunctionCall(MariaDBParser.DataTypeFunctionCallContext ctx);
	/**
	 * Visit a parse tree produced by the {@code valuesFunctionCall}
	 * labeled alternative in {@link MariaDBParser#specificFunction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitValuesFunctionCall(MariaDBParser.ValuesFunctionCallContext ctx);
	/**
	 * Visit a parse tree produced by the {@code caseExpressionFunctionCall}
	 * labeled alternative in {@link MariaDBParser#specificFunction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCaseExpressionFunctionCall(MariaDBParser.CaseExpressionFunctionCallContext ctx);
	/**
	 * Visit a parse tree produced by the {@code caseFunctionCall}
	 * labeled alternative in {@link MariaDBParser#specificFunction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCaseFunctionCall(MariaDBParser.CaseFunctionCallContext ctx);
	/**
	 * Visit a parse tree produced by the {@code charFunctionCall}
	 * labeled alternative in {@link MariaDBParser#specificFunction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCharFunctionCall(MariaDBParser.CharFunctionCallContext ctx);
	/**
	 * Visit a parse tree produced by the {@code positionFunctionCall}
	 * labeled alternative in {@link MariaDBParser#specificFunction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPositionFunctionCall(MariaDBParser.PositionFunctionCallContext ctx);
	/**
	 * Visit a parse tree produced by the {@code substrFunctionCall}
	 * labeled alternative in {@link MariaDBParser#specificFunction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSubstrFunctionCall(MariaDBParser.SubstrFunctionCallContext ctx);
	/**
	 * Visit a parse tree produced by the {@code trimFunctionCall}
	 * labeled alternative in {@link MariaDBParser#specificFunction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTrimFunctionCall(MariaDBParser.TrimFunctionCallContext ctx);
	/**
	 * Visit a parse tree produced by the {@code weightFunctionCall}
	 * labeled alternative in {@link MariaDBParser#specificFunction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWeightFunctionCall(MariaDBParser.WeightFunctionCallContext ctx);
	/**
	 * Visit a parse tree produced by the {@code extractFunctionCall}
	 * labeled alternative in {@link MariaDBParser#specificFunction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExtractFunctionCall(MariaDBParser.ExtractFunctionCallContext ctx);
	/**
	 * Visit a parse tree produced by the {@code getFormatFunctionCall}
	 * labeled alternative in {@link MariaDBParser#specificFunction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGetFormatFunctionCall(MariaDBParser.GetFormatFunctionCallContext ctx);
	/**
	 * Visit a parse tree produced by the {@code jsonValueFunctionCall}
	 * labeled alternative in {@link MariaDBParser#specificFunction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitJsonValueFunctionCall(MariaDBParser.JsonValueFunctionCallContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#caseFuncAlternative}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCaseFuncAlternative(MariaDBParser.CaseFuncAlternativeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code levelWeightList}
	 * labeled alternative in {@link MariaDBParser#levelsInWeightString}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLevelWeightList(MariaDBParser.LevelWeightListContext ctx);
	/**
	 * Visit a parse tree produced by the {@code levelWeightRange}
	 * labeled alternative in {@link MariaDBParser#levelsInWeightString}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLevelWeightRange(MariaDBParser.LevelWeightRangeContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#levelInWeightListElement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLevelInWeightListElement(MariaDBParser.LevelInWeightListElementContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#aggregateWindowedFunction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAggregateWindowedFunction(MariaDBParser.AggregateWindowedFunctionContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#nonAggregateWindowedFunction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNonAggregateWindowedFunction(MariaDBParser.NonAggregateWindowedFunctionContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#overClause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOverClause(MariaDBParser.OverClauseContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#windowSpec}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWindowSpec(MariaDBParser.WindowSpecContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#windowName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWindowName(MariaDBParser.WindowNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#frameClause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFrameClause(MariaDBParser.FrameClauseContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#frameUnits}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFrameUnits(MariaDBParser.FrameUnitsContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#frameExtent}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFrameExtent(MariaDBParser.FrameExtentContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#frameBetween}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFrameBetween(MariaDBParser.FrameBetweenContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#frameRange}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFrameRange(MariaDBParser.FrameRangeContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#partitionClause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPartitionClause(MariaDBParser.PartitionClauseContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#scalarFunctionName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitScalarFunctionName(MariaDBParser.ScalarFunctionNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#passwordFunctionClause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPasswordFunctionClause(MariaDBParser.PasswordFunctionClauseContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#functionArgs}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionArgs(MariaDBParser.FunctionArgsContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#functionArg}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionArg(MariaDBParser.FunctionArgContext ctx);
	/**
	 * Visit a parse tree produced by the {@code isExpression}
	 * labeled alternative in {@link MariaDBParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIsExpression(MariaDBParser.IsExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code notExpression}
	 * labeled alternative in {@link MariaDBParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNotExpression(MariaDBParser.NotExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code logicalExpression}
	 * labeled alternative in {@link MariaDBParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLogicalExpression(MariaDBParser.LogicalExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code predicateExpression}
	 * labeled alternative in {@link MariaDBParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPredicateExpression(MariaDBParser.PredicateExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code soundsLikePredicate}
	 * labeled alternative in {@link MariaDBParser#predicate}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSoundsLikePredicate(MariaDBParser.SoundsLikePredicateContext ctx);
	/**
	 * Visit a parse tree produced by the {@code expressionAtomPredicate}
	 * labeled alternative in {@link MariaDBParser#predicate}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpressionAtomPredicate(MariaDBParser.ExpressionAtomPredicateContext ctx);
	/**
	 * Visit a parse tree produced by the {@code subqueryComparisonPredicate}
	 * labeled alternative in {@link MariaDBParser#predicate}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSubqueryComparisonPredicate(MariaDBParser.SubqueryComparisonPredicateContext ctx);
	/**
	 * Visit a parse tree produced by the {@code jsonMemberOfPredicate}
	 * labeled alternative in {@link MariaDBParser#predicate}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitJsonMemberOfPredicate(MariaDBParser.JsonMemberOfPredicateContext ctx);
	/**
	 * Visit a parse tree produced by the {@code binaryComparisonPredicate}
	 * labeled alternative in {@link MariaDBParser#predicate}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBinaryComparisonPredicate(MariaDBParser.BinaryComparisonPredicateContext ctx);
	/**
	 * Visit a parse tree produced by the {@code inPredicate}
	 * labeled alternative in {@link MariaDBParser#predicate}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInPredicate(MariaDBParser.InPredicateContext ctx);
	/**
	 * Visit a parse tree produced by the {@code betweenPredicate}
	 * labeled alternative in {@link MariaDBParser#predicate}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBetweenPredicate(MariaDBParser.BetweenPredicateContext ctx);
	/**
	 * Visit a parse tree produced by the {@code isNullPredicate}
	 * labeled alternative in {@link MariaDBParser#predicate}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIsNullPredicate(MariaDBParser.IsNullPredicateContext ctx);
	/**
	 * Visit a parse tree produced by the {@code likePredicate}
	 * labeled alternative in {@link MariaDBParser#predicate}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLikePredicate(MariaDBParser.LikePredicateContext ctx);
	/**
	 * Visit a parse tree produced by the {@code regexpPredicate}
	 * labeled alternative in {@link MariaDBParser#predicate}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRegexpPredicate(MariaDBParser.RegexpPredicateContext ctx);
	/**
	 * Visit a parse tree produced by the {@code unaryExpressionAtom}
	 * labeled alternative in {@link MariaDBParser#expressionAtom}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnaryExpressionAtom(MariaDBParser.UnaryExpressionAtomContext ctx);
	/**
	 * Visit a parse tree produced by the {@code collateExpressionAtom}
	 * labeled alternative in {@link MariaDBParser#expressionAtom}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCollateExpressionAtom(MariaDBParser.CollateExpressionAtomContext ctx);
	/**
	 * Visit a parse tree produced by the {@code variableAssignExpressionAtom}
	 * labeled alternative in {@link MariaDBParser#expressionAtom}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariableAssignExpressionAtom(MariaDBParser.VariableAssignExpressionAtomContext ctx);
	/**
	 * Visit a parse tree produced by the {@code mysqlVariableExpressionAtom}
	 * labeled alternative in {@link MariaDBParser#expressionAtom}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMysqlVariableExpressionAtom(MariaDBParser.MysqlVariableExpressionAtomContext ctx);
	/**
	 * Visit a parse tree produced by the {@code nestedExpressionAtom}
	 * labeled alternative in {@link MariaDBParser#expressionAtom}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNestedExpressionAtom(MariaDBParser.NestedExpressionAtomContext ctx);
	/**
	 * Visit a parse tree produced by the {@code nestedRowExpressionAtom}
	 * labeled alternative in {@link MariaDBParser#expressionAtom}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNestedRowExpressionAtom(MariaDBParser.NestedRowExpressionAtomContext ctx);
	/**
	 * Visit a parse tree produced by the {@code mathExpressionAtom}
	 * labeled alternative in {@link MariaDBParser#expressionAtom}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMathExpressionAtom(MariaDBParser.MathExpressionAtomContext ctx);
	/**
	 * Visit a parse tree produced by the {@code existsExpressionAtom}
	 * labeled alternative in {@link MariaDBParser#expressionAtom}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExistsExpressionAtom(MariaDBParser.ExistsExpressionAtomContext ctx);
	/**
	 * Visit a parse tree produced by the {@code intervalExpressionAtom}
	 * labeled alternative in {@link MariaDBParser#expressionAtom}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIntervalExpressionAtom(MariaDBParser.IntervalExpressionAtomContext ctx);
	/**
	 * Visit a parse tree produced by the {@code jsonExpressionAtom}
	 * labeled alternative in {@link MariaDBParser#expressionAtom}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitJsonExpressionAtom(MariaDBParser.JsonExpressionAtomContext ctx);
	/**
	 * Visit a parse tree produced by the {@code subqueryExpressionAtom}
	 * labeled alternative in {@link MariaDBParser#expressionAtom}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSubqueryExpressionAtom(MariaDBParser.SubqueryExpressionAtomContext ctx);
	/**
	 * Visit a parse tree produced by the {@code constantExpressionAtom}
	 * labeled alternative in {@link MariaDBParser#expressionAtom}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstantExpressionAtom(MariaDBParser.ConstantExpressionAtomContext ctx);
	/**
	 * Visit a parse tree produced by the {@code functionCallExpressionAtom}
	 * labeled alternative in {@link MariaDBParser#expressionAtom}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionCallExpressionAtom(MariaDBParser.FunctionCallExpressionAtomContext ctx);
	/**
	 * Visit a parse tree produced by the {@code binaryExpressionAtom}
	 * labeled alternative in {@link MariaDBParser#expressionAtom}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBinaryExpressionAtom(MariaDBParser.BinaryExpressionAtomContext ctx);
	/**
	 * Visit a parse tree produced by the {@code fullColumnNameExpressionAtom}
	 * labeled alternative in {@link MariaDBParser#expressionAtom}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFullColumnNameExpressionAtom(MariaDBParser.FullColumnNameExpressionAtomContext ctx);
	/**
	 * Visit a parse tree produced by the {@code bitExpressionAtom}
	 * labeled alternative in {@link MariaDBParser#expressionAtom}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBitExpressionAtom(MariaDBParser.BitExpressionAtomContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#unaryOperator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnaryOperator(MariaDBParser.UnaryOperatorContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#comparisonOperator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitComparisonOperator(MariaDBParser.ComparisonOperatorContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#logicalOperator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLogicalOperator(MariaDBParser.LogicalOperatorContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#bitOperator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBitOperator(MariaDBParser.BitOperatorContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#mathOperator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMathOperator(MariaDBParser.MathOperatorContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#jsonOperator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitJsonOperator(MariaDBParser.JsonOperatorContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#charsetNameBase}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCharsetNameBase(MariaDBParser.CharsetNameBaseContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#transactionLevelBase}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTransactionLevelBase(MariaDBParser.TransactionLevelBaseContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#privilegesBase}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrivilegesBase(MariaDBParser.PrivilegesBaseContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#intervalTypeBase}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIntervalTypeBase(MariaDBParser.IntervalTypeBaseContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#dataTypeBase}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDataTypeBase(MariaDBParser.DataTypeBaseContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#keywordsCanBeId}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitKeywordsCanBeId(MariaDBParser.KeywordsCanBeIdContext ctx);
	/**
	 * Visit a parse tree produced by {@link MariaDBParser#functionNameBase}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionNameBase(MariaDBParser.FunctionNameBaseContext ctx);
}