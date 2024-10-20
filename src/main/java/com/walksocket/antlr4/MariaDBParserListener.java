// Generated from MariaDBParser.g4 by ANTLR 4.13.2
package com.walksocket.antlr4;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link MariaDBParser}.
 */
public interface MariaDBParserListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#root}.
	 * @param ctx the parse tree
	 */
	void enterRoot(MariaDBParser.RootContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#root}.
	 * @param ctx the parse tree
	 */
	void exitRoot(MariaDBParser.RootContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#sqlStatements}.
	 * @param ctx the parse tree
	 */
	void enterSqlStatements(MariaDBParser.SqlStatementsContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#sqlStatements}.
	 * @param ctx the parse tree
	 */
	void exitSqlStatements(MariaDBParser.SqlStatementsContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#sqlStatement}.
	 * @param ctx the parse tree
	 */
	void enterSqlStatement(MariaDBParser.SqlStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#sqlStatement}.
	 * @param ctx the parse tree
	 */
	void exitSqlStatement(MariaDBParser.SqlStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#setStatementFor}.
	 * @param ctx the parse tree
	 */
	void enterSetStatementFor(MariaDBParser.SetStatementForContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#setStatementFor}.
	 * @param ctx the parse tree
	 */
	void exitSetStatementFor(MariaDBParser.SetStatementForContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#emptyStatement_}.
	 * @param ctx the parse tree
	 */
	void enterEmptyStatement_(MariaDBParser.EmptyStatement_Context ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#emptyStatement_}.
	 * @param ctx the parse tree
	 */
	void exitEmptyStatement_(MariaDBParser.EmptyStatement_Context ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#ddlStatement}.
	 * @param ctx the parse tree
	 */
	void enterDdlStatement(MariaDBParser.DdlStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#ddlStatement}.
	 * @param ctx the parse tree
	 */
	void exitDdlStatement(MariaDBParser.DdlStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#dmlStatement}.
	 * @param ctx the parse tree
	 */
	void enterDmlStatement(MariaDBParser.DmlStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#dmlStatement}.
	 * @param ctx the parse tree
	 */
	void exitDmlStatement(MariaDBParser.DmlStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#transactionStatement}.
	 * @param ctx the parse tree
	 */
	void enterTransactionStatement(MariaDBParser.TransactionStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#transactionStatement}.
	 * @param ctx the parse tree
	 */
	void exitTransactionStatement(MariaDBParser.TransactionStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#replicationStatement}.
	 * @param ctx the parse tree
	 */
	void enterReplicationStatement(MariaDBParser.ReplicationStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#replicationStatement}.
	 * @param ctx the parse tree
	 */
	void exitReplicationStatement(MariaDBParser.ReplicationStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#preparedStatement}.
	 * @param ctx the parse tree
	 */
	void enterPreparedStatement(MariaDBParser.PreparedStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#preparedStatement}.
	 * @param ctx the parse tree
	 */
	void exitPreparedStatement(MariaDBParser.PreparedStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#compoundStatement}.
	 * @param ctx the parse tree
	 */
	void enterCompoundStatement(MariaDBParser.CompoundStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#compoundStatement}.
	 * @param ctx the parse tree
	 */
	void exitCompoundStatement(MariaDBParser.CompoundStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#administrationStatement}.
	 * @param ctx the parse tree
	 */
	void enterAdministrationStatement(MariaDBParser.AdministrationStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#administrationStatement}.
	 * @param ctx the parse tree
	 */
	void exitAdministrationStatement(MariaDBParser.AdministrationStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#utilityStatement}.
	 * @param ctx the parse tree
	 */
	void enterUtilityStatement(MariaDBParser.UtilityStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#utilityStatement}.
	 * @param ctx the parse tree
	 */
	void exitUtilityStatement(MariaDBParser.UtilityStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#createDatabase}.
	 * @param ctx the parse tree
	 */
	void enterCreateDatabase(MariaDBParser.CreateDatabaseContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#createDatabase}.
	 * @param ctx the parse tree
	 */
	void exitCreateDatabase(MariaDBParser.CreateDatabaseContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#createEvent}.
	 * @param ctx the parse tree
	 */
	void enterCreateEvent(MariaDBParser.CreateEventContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#createEvent}.
	 * @param ctx the parse tree
	 */
	void exitCreateEvent(MariaDBParser.CreateEventContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#createIndex}.
	 * @param ctx the parse tree
	 */
	void enterCreateIndex(MariaDBParser.CreateIndexContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#createIndex}.
	 * @param ctx the parse tree
	 */
	void exitCreateIndex(MariaDBParser.CreateIndexContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#createLogfileGroup}.
	 * @param ctx the parse tree
	 */
	void enterCreateLogfileGroup(MariaDBParser.CreateLogfileGroupContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#createLogfileGroup}.
	 * @param ctx the parse tree
	 */
	void exitCreateLogfileGroup(MariaDBParser.CreateLogfileGroupContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#createProcedure}.
	 * @param ctx the parse tree
	 */
	void enterCreateProcedure(MariaDBParser.CreateProcedureContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#createProcedure}.
	 * @param ctx the parse tree
	 */
	void exitCreateProcedure(MariaDBParser.CreateProcedureContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#createFunction}.
	 * @param ctx the parse tree
	 */
	void enterCreateFunction(MariaDBParser.CreateFunctionContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#createFunction}.
	 * @param ctx the parse tree
	 */
	void exitCreateFunction(MariaDBParser.CreateFunctionContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#createRole}.
	 * @param ctx the parse tree
	 */
	void enterCreateRole(MariaDBParser.CreateRoleContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#createRole}.
	 * @param ctx the parse tree
	 */
	void exitCreateRole(MariaDBParser.CreateRoleContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#createServer}.
	 * @param ctx the parse tree
	 */
	void enterCreateServer(MariaDBParser.CreateServerContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#createServer}.
	 * @param ctx the parse tree
	 */
	void exitCreateServer(MariaDBParser.CreateServerContext ctx);
	/**
	 * Enter a parse tree produced by the {@code copyCreateTable}
	 * labeled alternative in {@link MariaDBParser#createTable}.
	 * @param ctx the parse tree
	 */
	void enterCopyCreateTable(MariaDBParser.CopyCreateTableContext ctx);
	/**
	 * Exit a parse tree produced by the {@code copyCreateTable}
	 * labeled alternative in {@link MariaDBParser#createTable}.
	 * @param ctx the parse tree
	 */
	void exitCopyCreateTable(MariaDBParser.CopyCreateTableContext ctx);
	/**
	 * Enter a parse tree produced by the {@code queryCreateTable}
	 * labeled alternative in {@link MariaDBParser#createTable}.
	 * @param ctx the parse tree
	 */
	void enterQueryCreateTable(MariaDBParser.QueryCreateTableContext ctx);
	/**
	 * Exit a parse tree produced by the {@code queryCreateTable}
	 * labeled alternative in {@link MariaDBParser#createTable}.
	 * @param ctx the parse tree
	 */
	void exitQueryCreateTable(MariaDBParser.QueryCreateTableContext ctx);
	/**
	 * Enter a parse tree produced by the {@code columnCreateTable}
	 * labeled alternative in {@link MariaDBParser#createTable}.
	 * @param ctx the parse tree
	 */
	void enterColumnCreateTable(MariaDBParser.ColumnCreateTableContext ctx);
	/**
	 * Exit a parse tree produced by the {@code columnCreateTable}
	 * labeled alternative in {@link MariaDBParser#createTable}.
	 * @param ctx the parse tree
	 */
	void exitColumnCreateTable(MariaDBParser.ColumnCreateTableContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#createTablespaceInnodb}.
	 * @param ctx the parse tree
	 */
	void enterCreateTablespaceInnodb(MariaDBParser.CreateTablespaceInnodbContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#createTablespaceInnodb}.
	 * @param ctx the parse tree
	 */
	void exitCreateTablespaceInnodb(MariaDBParser.CreateTablespaceInnodbContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#createTablespaceNdb}.
	 * @param ctx the parse tree
	 */
	void enterCreateTablespaceNdb(MariaDBParser.CreateTablespaceNdbContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#createTablespaceNdb}.
	 * @param ctx the parse tree
	 */
	void exitCreateTablespaceNdb(MariaDBParser.CreateTablespaceNdbContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#createTrigger}.
	 * @param ctx the parse tree
	 */
	void enterCreateTrigger(MariaDBParser.CreateTriggerContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#createTrigger}.
	 * @param ctx the parse tree
	 */
	void exitCreateTrigger(MariaDBParser.CreateTriggerContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#withClause}.
	 * @param ctx the parse tree
	 */
	void enterWithClause(MariaDBParser.WithClauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#withClause}.
	 * @param ctx the parse tree
	 */
	void exitWithClause(MariaDBParser.WithClauseContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#commonTableExpressions}.
	 * @param ctx the parse tree
	 */
	void enterCommonTableExpressions(MariaDBParser.CommonTableExpressionsContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#commonTableExpressions}.
	 * @param ctx the parse tree
	 */
	void exitCommonTableExpressions(MariaDBParser.CommonTableExpressionsContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#cteName}.
	 * @param ctx the parse tree
	 */
	void enterCteName(MariaDBParser.CteNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#cteName}.
	 * @param ctx the parse tree
	 */
	void exitCteName(MariaDBParser.CteNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#cteColumnName}.
	 * @param ctx the parse tree
	 */
	void enterCteColumnName(MariaDBParser.CteColumnNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#cteColumnName}.
	 * @param ctx the parse tree
	 */
	void exitCteColumnName(MariaDBParser.CteColumnNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#createView}.
	 * @param ctx the parse tree
	 */
	void enterCreateView(MariaDBParser.CreateViewContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#createView}.
	 * @param ctx the parse tree
	 */
	void exitCreateView(MariaDBParser.CreateViewContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#createSequence}.
	 * @param ctx the parse tree
	 */
	void enterCreateSequence(MariaDBParser.CreateSequenceContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#createSequence}.
	 * @param ctx the parse tree
	 */
	void exitCreateSequence(MariaDBParser.CreateSequenceContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#sequenceSpec}.
	 * @param ctx the parse tree
	 */
	void enterSequenceSpec(MariaDBParser.SequenceSpecContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#sequenceSpec}.
	 * @param ctx the parse tree
	 */
	void exitSequenceSpec(MariaDBParser.SequenceSpecContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#createDatabaseOption}.
	 * @param ctx the parse tree
	 */
	void enterCreateDatabaseOption(MariaDBParser.CreateDatabaseOptionContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#createDatabaseOption}.
	 * @param ctx the parse tree
	 */
	void exitCreateDatabaseOption(MariaDBParser.CreateDatabaseOptionContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#charSet}.
	 * @param ctx the parse tree
	 */
	void enterCharSet(MariaDBParser.CharSetContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#charSet}.
	 * @param ctx the parse tree
	 */
	void exitCharSet(MariaDBParser.CharSetContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#currentUserExpression}.
	 * @param ctx the parse tree
	 */
	void enterCurrentUserExpression(MariaDBParser.CurrentUserExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#currentUserExpression}.
	 * @param ctx the parse tree
	 */
	void exitCurrentUserExpression(MariaDBParser.CurrentUserExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#ownerStatement}.
	 * @param ctx the parse tree
	 */
	void enterOwnerStatement(MariaDBParser.OwnerStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#ownerStatement}.
	 * @param ctx the parse tree
	 */
	void exitOwnerStatement(MariaDBParser.OwnerStatementContext ctx);
	/**
	 * Enter a parse tree produced by the {@code preciseSchedule}
	 * labeled alternative in {@link MariaDBParser#scheduleExpression}.
	 * @param ctx the parse tree
	 */
	void enterPreciseSchedule(MariaDBParser.PreciseScheduleContext ctx);
	/**
	 * Exit a parse tree produced by the {@code preciseSchedule}
	 * labeled alternative in {@link MariaDBParser#scheduleExpression}.
	 * @param ctx the parse tree
	 */
	void exitPreciseSchedule(MariaDBParser.PreciseScheduleContext ctx);
	/**
	 * Enter a parse tree produced by the {@code intervalSchedule}
	 * labeled alternative in {@link MariaDBParser#scheduleExpression}.
	 * @param ctx the parse tree
	 */
	void enterIntervalSchedule(MariaDBParser.IntervalScheduleContext ctx);
	/**
	 * Exit a parse tree produced by the {@code intervalSchedule}
	 * labeled alternative in {@link MariaDBParser#scheduleExpression}.
	 * @param ctx the parse tree
	 */
	void exitIntervalSchedule(MariaDBParser.IntervalScheduleContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#timestampValue}.
	 * @param ctx the parse tree
	 */
	void enterTimestampValue(MariaDBParser.TimestampValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#timestampValue}.
	 * @param ctx the parse tree
	 */
	void exitTimestampValue(MariaDBParser.TimestampValueContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#intervalExpr}.
	 * @param ctx the parse tree
	 */
	void enterIntervalExpr(MariaDBParser.IntervalExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#intervalExpr}.
	 * @param ctx the parse tree
	 */
	void exitIntervalExpr(MariaDBParser.IntervalExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#intervalType}.
	 * @param ctx the parse tree
	 */
	void enterIntervalType(MariaDBParser.IntervalTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#intervalType}.
	 * @param ctx the parse tree
	 */
	void exitIntervalType(MariaDBParser.IntervalTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#enableType}.
	 * @param ctx the parse tree
	 */
	void enterEnableType(MariaDBParser.EnableTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#enableType}.
	 * @param ctx the parse tree
	 */
	void exitEnableType(MariaDBParser.EnableTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#indexType}.
	 * @param ctx the parse tree
	 */
	void enterIndexType(MariaDBParser.IndexTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#indexType}.
	 * @param ctx the parse tree
	 */
	void exitIndexType(MariaDBParser.IndexTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#indexOption}.
	 * @param ctx the parse tree
	 */
	void enterIndexOption(MariaDBParser.IndexOptionContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#indexOption}.
	 * @param ctx the parse tree
	 */
	void exitIndexOption(MariaDBParser.IndexOptionContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#procedureParameter}.
	 * @param ctx the parse tree
	 */
	void enterProcedureParameter(MariaDBParser.ProcedureParameterContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#procedureParameter}.
	 * @param ctx the parse tree
	 */
	void exitProcedureParameter(MariaDBParser.ProcedureParameterContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#functionParameter}.
	 * @param ctx the parse tree
	 */
	void enterFunctionParameter(MariaDBParser.FunctionParameterContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#functionParameter}.
	 * @param ctx the parse tree
	 */
	void exitFunctionParameter(MariaDBParser.FunctionParameterContext ctx);
	/**
	 * Enter a parse tree produced by the {@code routineComment}
	 * labeled alternative in {@link MariaDBParser#routineOption}.
	 * @param ctx the parse tree
	 */
	void enterRoutineComment(MariaDBParser.RoutineCommentContext ctx);
	/**
	 * Exit a parse tree produced by the {@code routineComment}
	 * labeled alternative in {@link MariaDBParser#routineOption}.
	 * @param ctx the parse tree
	 */
	void exitRoutineComment(MariaDBParser.RoutineCommentContext ctx);
	/**
	 * Enter a parse tree produced by the {@code routineLanguage}
	 * labeled alternative in {@link MariaDBParser#routineOption}.
	 * @param ctx the parse tree
	 */
	void enterRoutineLanguage(MariaDBParser.RoutineLanguageContext ctx);
	/**
	 * Exit a parse tree produced by the {@code routineLanguage}
	 * labeled alternative in {@link MariaDBParser#routineOption}.
	 * @param ctx the parse tree
	 */
	void exitRoutineLanguage(MariaDBParser.RoutineLanguageContext ctx);
	/**
	 * Enter a parse tree produced by the {@code routineBehavior}
	 * labeled alternative in {@link MariaDBParser#routineOption}.
	 * @param ctx the parse tree
	 */
	void enterRoutineBehavior(MariaDBParser.RoutineBehaviorContext ctx);
	/**
	 * Exit a parse tree produced by the {@code routineBehavior}
	 * labeled alternative in {@link MariaDBParser#routineOption}.
	 * @param ctx the parse tree
	 */
	void exitRoutineBehavior(MariaDBParser.RoutineBehaviorContext ctx);
	/**
	 * Enter a parse tree produced by the {@code routineData}
	 * labeled alternative in {@link MariaDBParser#routineOption}.
	 * @param ctx the parse tree
	 */
	void enterRoutineData(MariaDBParser.RoutineDataContext ctx);
	/**
	 * Exit a parse tree produced by the {@code routineData}
	 * labeled alternative in {@link MariaDBParser#routineOption}.
	 * @param ctx the parse tree
	 */
	void exitRoutineData(MariaDBParser.RoutineDataContext ctx);
	/**
	 * Enter a parse tree produced by the {@code routineSecurity}
	 * labeled alternative in {@link MariaDBParser#routineOption}.
	 * @param ctx the parse tree
	 */
	void enterRoutineSecurity(MariaDBParser.RoutineSecurityContext ctx);
	/**
	 * Exit a parse tree produced by the {@code routineSecurity}
	 * labeled alternative in {@link MariaDBParser#routineOption}.
	 * @param ctx the parse tree
	 */
	void exitRoutineSecurity(MariaDBParser.RoutineSecurityContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#serverOption}.
	 * @param ctx the parse tree
	 */
	void enterServerOption(MariaDBParser.ServerOptionContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#serverOption}.
	 * @param ctx the parse tree
	 */
	void exitServerOption(MariaDBParser.ServerOptionContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#createDefinitions}.
	 * @param ctx the parse tree
	 */
	void enterCreateDefinitions(MariaDBParser.CreateDefinitionsContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#createDefinitions}.
	 * @param ctx the parse tree
	 */
	void exitCreateDefinitions(MariaDBParser.CreateDefinitionsContext ctx);
	/**
	 * Enter a parse tree produced by the {@code columnDeclaration}
	 * labeled alternative in {@link MariaDBParser#createDefinition}.
	 * @param ctx the parse tree
	 */
	void enterColumnDeclaration(MariaDBParser.ColumnDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by the {@code columnDeclaration}
	 * labeled alternative in {@link MariaDBParser#createDefinition}.
	 * @param ctx the parse tree
	 */
	void exitColumnDeclaration(MariaDBParser.ColumnDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by the {@code constraintDeclaration}
	 * labeled alternative in {@link MariaDBParser#createDefinition}.
	 * @param ctx the parse tree
	 */
	void enterConstraintDeclaration(MariaDBParser.ConstraintDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by the {@code constraintDeclaration}
	 * labeled alternative in {@link MariaDBParser#createDefinition}.
	 * @param ctx the parse tree
	 */
	void exitConstraintDeclaration(MariaDBParser.ConstraintDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by the {@code indexDeclaration}
	 * labeled alternative in {@link MariaDBParser#createDefinition}.
	 * @param ctx the parse tree
	 */
	void enterIndexDeclaration(MariaDBParser.IndexDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by the {@code indexDeclaration}
	 * labeled alternative in {@link MariaDBParser#createDefinition}.
	 * @param ctx the parse tree
	 */
	void exitIndexDeclaration(MariaDBParser.IndexDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#columnDefinition}.
	 * @param ctx the parse tree
	 */
	void enterColumnDefinition(MariaDBParser.ColumnDefinitionContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#columnDefinition}.
	 * @param ctx the parse tree
	 */
	void exitColumnDefinition(MariaDBParser.ColumnDefinitionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code nullColumnConstraint}
	 * labeled alternative in {@link MariaDBParser#columnConstraint}.
	 * @param ctx the parse tree
	 */
	void enterNullColumnConstraint(MariaDBParser.NullColumnConstraintContext ctx);
	/**
	 * Exit a parse tree produced by the {@code nullColumnConstraint}
	 * labeled alternative in {@link MariaDBParser#columnConstraint}.
	 * @param ctx the parse tree
	 */
	void exitNullColumnConstraint(MariaDBParser.NullColumnConstraintContext ctx);
	/**
	 * Enter a parse tree produced by the {@code defaultColumnConstraint}
	 * labeled alternative in {@link MariaDBParser#columnConstraint}.
	 * @param ctx the parse tree
	 */
	void enterDefaultColumnConstraint(MariaDBParser.DefaultColumnConstraintContext ctx);
	/**
	 * Exit a parse tree produced by the {@code defaultColumnConstraint}
	 * labeled alternative in {@link MariaDBParser#columnConstraint}.
	 * @param ctx the parse tree
	 */
	void exitDefaultColumnConstraint(MariaDBParser.DefaultColumnConstraintContext ctx);
	/**
	 * Enter a parse tree produced by the {@code visibilityColumnConstraint}
	 * labeled alternative in {@link MariaDBParser#columnConstraint}.
	 * @param ctx the parse tree
	 */
	void enterVisibilityColumnConstraint(MariaDBParser.VisibilityColumnConstraintContext ctx);
	/**
	 * Exit a parse tree produced by the {@code visibilityColumnConstraint}
	 * labeled alternative in {@link MariaDBParser#columnConstraint}.
	 * @param ctx the parse tree
	 */
	void exitVisibilityColumnConstraint(MariaDBParser.VisibilityColumnConstraintContext ctx);
	/**
	 * Enter a parse tree produced by the {@code invisibilityColumnConstraint}
	 * labeled alternative in {@link MariaDBParser#columnConstraint}.
	 * @param ctx the parse tree
	 */
	void enterInvisibilityColumnConstraint(MariaDBParser.InvisibilityColumnConstraintContext ctx);
	/**
	 * Exit a parse tree produced by the {@code invisibilityColumnConstraint}
	 * labeled alternative in {@link MariaDBParser#columnConstraint}.
	 * @param ctx the parse tree
	 */
	void exitInvisibilityColumnConstraint(MariaDBParser.InvisibilityColumnConstraintContext ctx);
	/**
	 * Enter a parse tree produced by the {@code autoIncrementColumnConstraint}
	 * labeled alternative in {@link MariaDBParser#columnConstraint}.
	 * @param ctx the parse tree
	 */
	void enterAutoIncrementColumnConstraint(MariaDBParser.AutoIncrementColumnConstraintContext ctx);
	/**
	 * Exit a parse tree produced by the {@code autoIncrementColumnConstraint}
	 * labeled alternative in {@link MariaDBParser#columnConstraint}.
	 * @param ctx the parse tree
	 */
	void exitAutoIncrementColumnConstraint(MariaDBParser.AutoIncrementColumnConstraintContext ctx);
	/**
	 * Enter a parse tree produced by the {@code primaryKeyColumnConstraint}
	 * labeled alternative in {@link MariaDBParser#columnConstraint}.
	 * @param ctx the parse tree
	 */
	void enterPrimaryKeyColumnConstraint(MariaDBParser.PrimaryKeyColumnConstraintContext ctx);
	/**
	 * Exit a parse tree produced by the {@code primaryKeyColumnConstraint}
	 * labeled alternative in {@link MariaDBParser#columnConstraint}.
	 * @param ctx the parse tree
	 */
	void exitPrimaryKeyColumnConstraint(MariaDBParser.PrimaryKeyColumnConstraintContext ctx);
	/**
	 * Enter a parse tree produced by the {@code uniqueKeyColumnConstraint}
	 * labeled alternative in {@link MariaDBParser#columnConstraint}.
	 * @param ctx the parse tree
	 */
	void enterUniqueKeyColumnConstraint(MariaDBParser.UniqueKeyColumnConstraintContext ctx);
	/**
	 * Exit a parse tree produced by the {@code uniqueKeyColumnConstraint}
	 * labeled alternative in {@link MariaDBParser#columnConstraint}.
	 * @param ctx the parse tree
	 */
	void exitUniqueKeyColumnConstraint(MariaDBParser.UniqueKeyColumnConstraintContext ctx);
	/**
	 * Enter a parse tree produced by the {@code commentColumnConstraint}
	 * labeled alternative in {@link MariaDBParser#columnConstraint}.
	 * @param ctx the parse tree
	 */
	void enterCommentColumnConstraint(MariaDBParser.CommentColumnConstraintContext ctx);
	/**
	 * Exit a parse tree produced by the {@code commentColumnConstraint}
	 * labeled alternative in {@link MariaDBParser#columnConstraint}.
	 * @param ctx the parse tree
	 */
	void exitCommentColumnConstraint(MariaDBParser.CommentColumnConstraintContext ctx);
	/**
	 * Enter a parse tree produced by the {@code formatColumnConstraint}
	 * labeled alternative in {@link MariaDBParser#columnConstraint}.
	 * @param ctx the parse tree
	 */
	void enterFormatColumnConstraint(MariaDBParser.FormatColumnConstraintContext ctx);
	/**
	 * Exit a parse tree produced by the {@code formatColumnConstraint}
	 * labeled alternative in {@link MariaDBParser#columnConstraint}.
	 * @param ctx the parse tree
	 */
	void exitFormatColumnConstraint(MariaDBParser.FormatColumnConstraintContext ctx);
	/**
	 * Enter a parse tree produced by the {@code storageColumnConstraint}
	 * labeled alternative in {@link MariaDBParser#columnConstraint}.
	 * @param ctx the parse tree
	 */
	void enterStorageColumnConstraint(MariaDBParser.StorageColumnConstraintContext ctx);
	/**
	 * Exit a parse tree produced by the {@code storageColumnConstraint}
	 * labeled alternative in {@link MariaDBParser#columnConstraint}.
	 * @param ctx the parse tree
	 */
	void exitStorageColumnConstraint(MariaDBParser.StorageColumnConstraintContext ctx);
	/**
	 * Enter a parse tree produced by the {@code referenceColumnConstraint}
	 * labeled alternative in {@link MariaDBParser#columnConstraint}.
	 * @param ctx the parse tree
	 */
	void enterReferenceColumnConstraint(MariaDBParser.ReferenceColumnConstraintContext ctx);
	/**
	 * Exit a parse tree produced by the {@code referenceColumnConstraint}
	 * labeled alternative in {@link MariaDBParser#columnConstraint}.
	 * @param ctx the parse tree
	 */
	void exitReferenceColumnConstraint(MariaDBParser.ReferenceColumnConstraintContext ctx);
	/**
	 * Enter a parse tree produced by the {@code collateColumnConstraint}
	 * labeled alternative in {@link MariaDBParser#columnConstraint}.
	 * @param ctx the parse tree
	 */
	void enterCollateColumnConstraint(MariaDBParser.CollateColumnConstraintContext ctx);
	/**
	 * Exit a parse tree produced by the {@code collateColumnConstraint}
	 * labeled alternative in {@link MariaDBParser#columnConstraint}.
	 * @param ctx the parse tree
	 */
	void exitCollateColumnConstraint(MariaDBParser.CollateColumnConstraintContext ctx);
	/**
	 * Enter a parse tree produced by the {@code generatedColumnConstraint}
	 * labeled alternative in {@link MariaDBParser#columnConstraint}.
	 * @param ctx the parse tree
	 */
	void enterGeneratedColumnConstraint(MariaDBParser.GeneratedColumnConstraintContext ctx);
	/**
	 * Exit a parse tree produced by the {@code generatedColumnConstraint}
	 * labeled alternative in {@link MariaDBParser#columnConstraint}.
	 * @param ctx the parse tree
	 */
	void exitGeneratedColumnConstraint(MariaDBParser.GeneratedColumnConstraintContext ctx);
	/**
	 * Enter a parse tree produced by the {@code serialDefaultColumnConstraint}
	 * labeled alternative in {@link MariaDBParser#columnConstraint}.
	 * @param ctx the parse tree
	 */
	void enterSerialDefaultColumnConstraint(MariaDBParser.SerialDefaultColumnConstraintContext ctx);
	/**
	 * Exit a parse tree produced by the {@code serialDefaultColumnConstraint}
	 * labeled alternative in {@link MariaDBParser#columnConstraint}.
	 * @param ctx the parse tree
	 */
	void exitSerialDefaultColumnConstraint(MariaDBParser.SerialDefaultColumnConstraintContext ctx);
	/**
	 * Enter a parse tree produced by the {@code checkColumnConstraint}
	 * labeled alternative in {@link MariaDBParser#columnConstraint}.
	 * @param ctx the parse tree
	 */
	void enterCheckColumnConstraint(MariaDBParser.CheckColumnConstraintContext ctx);
	/**
	 * Exit a parse tree produced by the {@code checkColumnConstraint}
	 * labeled alternative in {@link MariaDBParser#columnConstraint}.
	 * @param ctx the parse tree
	 */
	void exitCheckColumnConstraint(MariaDBParser.CheckColumnConstraintContext ctx);
	/**
	 * Enter a parse tree produced by the {@code primaryKeyTableConstraint}
	 * labeled alternative in {@link MariaDBParser#tableConstraint}.
	 * @param ctx the parse tree
	 */
	void enterPrimaryKeyTableConstraint(MariaDBParser.PrimaryKeyTableConstraintContext ctx);
	/**
	 * Exit a parse tree produced by the {@code primaryKeyTableConstraint}
	 * labeled alternative in {@link MariaDBParser#tableConstraint}.
	 * @param ctx the parse tree
	 */
	void exitPrimaryKeyTableConstraint(MariaDBParser.PrimaryKeyTableConstraintContext ctx);
	/**
	 * Enter a parse tree produced by the {@code uniqueKeyTableConstraint}
	 * labeled alternative in {@link MariaDBParser#tableConstraint}.
	 * @param ctx the parse tree
	 */
	void enterUniqueKeyTableConstraint(MariaDBParser.UniqueKeyTableConstraintContext ctx);
	/**
	 * Exit a parse tree produced by the {@code uniqueKeyTableConstraint}
	 * labeled alternative in {@link MariaDBParser#tableConstraint}.
	 * @param ctx the parse tree
	 */
	void exitUniqueKeyTableConstraint(MariaDBParser.UniqueKeyTableConstraintContext ctx);
	/**
	 * Enter a parse tree produced by the {@code foreignKeyTableConstraint}
	 * labeled alternative in {@link MariaDBParser#tableConstraint}.
	 * @param ctx the parse tree
	 */
	void enterForeignKeyTableConstraint(MariaDBParser.ForeignKeyTableConstraintContext ctx);
	/**
	 * Exit a parse tree produced by the {@code foreignKeyTableConstraint}
	 * labeled alternative in {@link MariaDBParser#tableConstraint}.
	 * @param ctx the parse tree
	 */
	void exitForeignKeyTableConstraint(MariaDBParser.ForeignKeyTableConstraintContext ctx);
	/**
	 * Enter a parse tree produced by the {@code checkTableConstraint}
	 * labeled alternative in {@link MariaDBParser#tableConstraint}.
	 * @param ctx the parse tree
	 */
	void enterCheckTableConstraint(MariaDBParser.CheckTableConstraintContext ctx);
	/**
	 * Exit a parse tree produced by the {@code checkTableConstraint}
	 * labeled alternative in {@link MariaDBParser#tableConstraint}.
	 * @param ctx the parse tree
	 */
	void exitCheckTableConstraint(MariaDBParser.CheckTableConstraintContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#referenceDefinition}.
	 * @param ctx the parse tree
	 */
	void enterReferenceDefinition(MariaDBParser.ReferenceDefinitionContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#referenceDefinition}.
	 * @param ctx the parse tree
	 */
	void exitReferenceDefinition(MariaDBParser.ReferenceDefinitionContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#referenceAction}.
	 * @param ctx the parse tree
	 */
	void enterReferenceAction(MariaDBParser.ReferenceActionContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#referenceAction}.
	 * @param ctx the parse tree
	 */
	void exitReferenceAction(MariaDBParser.ReferenceActionContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#referenceControlType}.
	 * @param ctx the parse tree
	 */
	void enterReferenceControlType(MariaDBParser.ReferenceControlTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#referenceControlType}.
	 * @param ctx the parse tree
	 */
	void exitReferenceControlType(MariaDBParser.ReferenceControlTypeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code simpleIndexDeclaration}
	 * labeled alternative in {@link MariaDBParser#indexColumnDefinition}.
	 * @param ctx the parse tree
	 */
	void enterSimpleIndexDeclaration(MariaDBParser.SimpleIndexDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by the {@code simpleIndexDeclaration}
	 * labeled alternative in {@link MariaDBParser#indexColumnDefinition}.
	 * @param ctx the parse tree
	 */
	void exitSimpleIndexDeclaration(MariaDBParser.SimpleIndexDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by the {@code specialIndexDeclaration}
	 * labeled alternative in {@link MariaDBParser#indexColumnDefinition}.
	 * @param ctx the parse tree
	 */
	void enterSpecialIndexDeclaration(MariaDBParser.SpecialIndexDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by the {@code specialIndexDeclaration}
	 * labeled alternative in {@link MariaDBParser#indexColumnDefinition}.
	 * @param ctx the parse tree
	 */
	void exitSpecialIndexDeclaration(MariaDBParser.SpecialIndexDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by the {@code tableOptionEngine}
	 * labeled alternative in {@link MariaDBParser#tableOption}.
	 * @param ctx the parse tree
	 */
	void enterTableOptionEngine(MariaDBParser.TableOptionEngineContext ctx);
	/**
	 * Exit a parse tree produced by the {@code tableOptionEngine}
	 * labeled alternative in {@link MariaDBParser#tableOption}.
	 * @param ctx the parse tree
	 */
	void exitTableOptionEngine(MariaDBParser.TableOptionEngineContext ctx);
	/**
	 * Enter a parse tree produced by the {@code tableOptionEngineAttribute}
	 * labeled alternative in {@link MariaDBParser#tableOption}.
	 * @param ctx the parse tree
	 */
	void enterTableOptionEngineAttribute(MariaDBParser.TableOptionEngineAttributeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code tableOptionEngineAttribute}
	 * labeled alternative in {@link MariaDBParser#tableOption}.
	 * @param ctx the parse tree
	 */
	void exitTableOptionEngineAttribute(MariaDBParser.TableOptionEngineAttributeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code tableOptionAutoextendSize}
	 * labeled alternative in {@link MariaDBParser#tableOption}.
	 * @param ctx the parse tree
	 */
	void enterTableOptionAutoextendSize(MariaDBParser.TableOptionAutoextendSizeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code tableOptionAutoextendSize}
	 * labeled alternative in {@link MariaDBParser#tableOption}.
	 * @param ctx the parse tree
	 */
	void exitTableOptionAutoextendSize(MariaDBParser.TableOptionAutoextendSizeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code tableOptionAutoIncrement}
	 * labeled alternative in {@link MariaDBParser#tableOption}.
	 * @param ctx the parse tree
	 */
	void enterTableOptionAutoIncrement(MariaDBParser.TableOptionAutoIncrementContext ctx);
	/**
	 * Exit a parse tree produced by the {@code tableOptionAutoIncrement}
	 * labeled alternative in {@link MariaDBParser#tableOption}.
	 * @param ctx the parse tree
	 */
	void exitTableOptionAutoIncrement(MariaDBParser.TableOptionAutoIncrementContext ctx);
	/**
	 * Enter a parse tree produced by the {@code tableOptionAverage}
	 * labeled alternative in {@link MariaDBParser#tableOption}.
	 * @param ctx the parse tree
	 */
	void enterTableOptionAverage(MariaDBParser.TableOptionAverageContext ctx);
	/**
	 * Exit a parse tree produced by the {@code tableOptionAverage}
	 * labeled alternative in {@link MariaDBParser#tableOption}.
	 * @param ctx the parse tree
	 */
	void exitTableOptionAverage(MariaDBParser.TableOptionAverageContext ctx);
	/**
	 * Enter a parse tree produced by the {@code tableOptionCharset}
	 * labeled alternative in {@link MariaDBParser#tableOption}.
	 * @param ctx the parse tree
	 */
	void enterTableOptionCharset(MariaDBParser.TableOptionCharsetContext ctx);
	/**
	 * Exit a parse tree produced by the {@code tableOptionCharset}
	 * labeled alternative in {@link MariaDBParser#tableOption}.
	 * @param ctx the parse tree
	 */
	void exitTableOptionCharset(MariaDBParser.TableOptionCharsetContext ctx);
	/**
	 * Enter a parse tree produced by the {@code tableOptionChecksum}
	 * labeled alternative in {@link MariaDBParser#tableOption}.
	 * @param ctx the parse tree
	 */
	void enterTableOptionChecksum(MariaDBParser.TableOptionChecksumContext ctx);
	/**
	 * Exit a parse tree produced by the {@code tableOptionChecksum}
	 * labeled alternative in {@link MariaDBParser#tableOption}.
	 * @param ctx the parse tree
	 */
	void exitTableOptionChecksum(MariaDBParser.TableOptionChecksumContext ctx);
	/**
	 * Enter a parse tree produced by the {@code tableOptionCollate}
	 * labeled alternative in {@link MariaDBParser#tableOption}.
	 * @param ctx the parse tree
	 */
	void enterTableOptionCollate(MariaDBParser.TableOptionCollateContext ctx);
	/**
	 * Exit a parse tree produced by the {@code tableOptionCollate}
	 * labeled alternative in {@link MariaDBParser#tableOption}.
	 * @param ctx the parse tree
	 */
	void exitTableOptionCollate(MariaDBParser.TableOptionCollateContext ctx);
	/**
	 * Enter a parse tree produced by the {@code tableOptionComment}
	 * labeled alternative in {@link MariaDBParser#tableOption}.
	 * @param ctx the parse tree
	 */
	void enterTableOptionComment(MariaDBParser.TableOptionCommentContext ctx);
	/**
	 * Exit a parse tree produced by the {@code tableOptionComment}
	 * labeled alternative in {@link MariaDBParser#tableOption}.
	 * @param ctx the parse tree
	 */
	void exitTableOptionComment(MariaDBParser.TableOptionCommentContext ctx);
	/**
	 * Enter a parse tree produced by the {@code tableOptionCompression}
	 * labeled alternative in {@link MariaDBParser#tableOption}.
	 * @param ctx the parse tree
	 */
	void enterTableOptionCompression(MariaDBParser.TableOptionCompressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code tableOptionCompression}
	 * labeled alternative in {@link MariaDBParser#tableOption}.
	 * @param ctx the parse tree
	 */
	void exitTableOptionCompression(MariaDBParser.TableOptionCompressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code tableOptionConnection}
	 * labeled alternative in {@link MariaDBParser#tableOption}.
	 * @param ctx the parse tree
	 */
	void enterTableOptionConnection(MariaDBParser.TableOptionConnectionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code tableOptionConnection}
	 * labeled alternative in {@link MariaDBParser#tableOption}.
	 * @param ctx the parse tree
	 */
	void exitTableOptionConnection(MariaDBParser.TableOptionConnectionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code tableOptionDataDirectory}
	 * labeled alternative in {@link MariaDBParser#tableOption}.
	 * @param ctx the parse tree
	 */
	void enterTableOptionDataDirectory(MariaDBParser.TableOptionDataDirectoryContext ctx);
	/**
	 * Exit a parse tree produced by the {@code tableOptionDataDirectory}
	 * labeled alternative in {@link MariaDBParser#tableOption}.
	 * @param ctx the parse tree
	 */
	void exitTableOptionDataDirectory(MariaDBParser.TableOptionDataDirectoryContext ctx);
	/**
	 * Enter a parse tree produced by the {@code tableOptionDelay}
	 * labeled alternative in {@link MariaDBParser#tableOption}.
	 * @param ctx the parse tree
	 */
	void enterTableOptionDelay(MariaDBParser.TableOptionDelayContext ctx);
	/**
	 * Exit a parse tree produced by the {@code tableOptionDelay}
	 * labeled alternative in {@link MariaDBParser#tableOption}.
	 * @param ctx the parse tree
	 */
	void exitTableOptionDelay(MariaDBParser.TableOptionDelayContext ctx);
	/**
	 * Enter a parse tree produced by the {@code tableOptionEncryption}
	 * labeled alternative in {@link MariaDBParser#tableOption}.
	 * @param ctx the parse tree
	 */
	void enterTableOptionEncryption(MariaDBParser.TableOptionEncryptionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code tableOptionEncryption}
	 * labeled alternative in {@link MariaDBParser#tableOption}.
	 * @param ctx the parse tree
	 */
	void exitTableOptionEncryption(MariaDBParser.TableOptionEncryptionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code tableOptionEncrypted}
	 * labeled alternative in {@link MariaDBParser#tableOption}.
	 * @param ctx the parse tree
	 */
	void enterTableOptionEncrypted(MariaDBParser.TableOptionEncryptedContext ctx);
	/**
	 * Exit a parse tree produced by the {@code tableOptionEncrypted}
	 * labeled alternative in {@link MariaDBParser#tableOption}.
	 * @param ctx the parse tree
	 */
	void exitTableOptionEncrypted(MariaDBParser.TableOptionEncryptedContext ctx);
	/**
	 * Enter a parse tree produced by the {@code tableOptionPageCompressed}
	 * labeled alternative in {@link MariaDBParser#tableOption}.
	 * @param ctx the parse tree
	 */
	void enterTableOptionPageCompressed(MariaDBParser.TableOptionPageCompressedContext ctx);
	/**
	 * Exit a parse tree produced by the {@code tableOptionPageCompressed}
	 * labeled alternative in {@link MariaDBParser#tableOption}.
	 * @param ctx the parse tree
	 */
	void exitTableOptionPageCompressed(MariaDBParser.TableOptionPageCompressedContext ctx);
	/**
	 * Enter a parse tree produced by the {@code tableOptionPageCompressionLevel}
	 * labeled alternative in {@link MariaDBParser#tableOption}.
	 * @param ctx the parse tree
	 */
	void enterTableOptionPageCompressionLevel(MariaDBParser.TableOptionPageCompressionLevelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code tableOptionPageCompressionLevel}
	 * labeled alternative in {@link MariaDBParser#tableOption}.
	 * @param ctx the parse tree
	 */
	void exitTableOptionPageCompressionLevel(MariaDBParser.TableOptionPageCompressionLevelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code tableOptionEncryptionKeyId}
	 * labeled alternative in {@link MariaDBParser#tableOption}.
	 * @param ctx the parse tree
	 */
	void enterTableOptionEncryptionKeyId(MariaDBParser.TableOptionEncryptionKeyIdContext ctx);
	/**
	 * Exit a parse tree produced by the {@code tableOptionEncryptionKeyId}
	 * labeled alternative in {@link MariaDBParser#tableOption}.
	 * @param ctx the parse tree
	 */
	void exitTableOptionEncryptionKeyId(MariaDBParser.TableOptionEncryptionKeyIdContext ctx);
	/**
	 * Enter a parse tree produced by the {@code tableOptionIndexDirectory}
	 * labeled alternative in {@link MariaDBParser#tableOption}.
	 * @param ctx the parse tree
	 */
	void enterTableOptionIndexDirectory(MariaDBParser.TableOptionIndexDirectoryContext ctx);
	/**
	 * Exit a parse tree produced by the {@code tableOptionIndexDirectory}
	 * labeled alternative in {@link MariaDBParser#tableOption}.
	 * @param ctx the parse tree
	 */
	void exitTableOptionIndexDirectory(MariaDBParser.TableOptionIndexDirectoryContext ctx);
	/**
	 * Enter a parse tree produced by the {@code tableOptionInsertMethod}
	 * labeled alternative in {@link MariaDBParser#tableOption}.
	 * @param ctx the parse tree
	 */
	void enterTableOptionInsertMethod(MariaDBParser.TableOptionInsertMethodContext ctx);
	/**
	 * Exit a parse tree produced by the {@code tableOptionInsertMethod}
	 * labeled alternative in {@link MariaDBParser#tableOption}.
	 * @param ctx the parse tree
	 */
	void exitTableOptionInsertMethod(MariaDBParser.TableOptionInsertMethodContext ctx);
	/**
	 * Enter a parse tree produced by the {@code tableOptionKeyBlockSize}
	 * labeled alternative in {@link MariaDBParser#tableOption}.
	 * @param ctx the parse tree
	 */
	void enterTableOptionKeyBlockSize(MariaDBParser.TableOptionKeyBlockSizeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code tableOptionKeyBlockSize}
	 * labeled alternative in {@link MariaDBParser#tableOption}.
	 * @param ctx the parse tree
	 */
	void exitTableOptionKeyBlockSize(MariaDBParser.TableOptionKeyBlockSizeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code tableOptionMaxRows}
	 * labeled alternative in {@link MariaDBParser#tableOption}.
	 * @param ctx the parse tree
	 */
	void enterTableOptionMaxRows(MariaDBParser.TableOptionMaxRowsContext ctx);
	/**
	 * Exit a parse tree produced by the {@code tableOptionMaxRows}
	 * labeled alternative in {@link MariaDBParser#tableOption}.
	 * @param ctx the parse tree
	 */
	void exitTableOptionMaxRows(MariaDBParser.TableOptionMaxRowsContext ctx);
	/**
	 * Enter a parse tree produced by the {@code tableOptionMinRows}
	 * labeled alternative in {@link MariaDBParser#tableOption}.
	 * @param ctx the parse tree
	 */
	void enterTableOptionMinRows(MariaDBParser.TableOptionMinRowsContext ctx);
	/**
	 * Exit a parse tree produced by the {@code tableOptionMinRows}
	 * labeled alternative in {@link MariaDBParser#tableOption}.
	 * @param ctx the parse tree
	 */
	void exitTableOptionMinRows(MariaDBParser.TableOptionMinRowsContext ctx);
	/**
	 * Enter a parse tree produced by the {@code tableOptionPackKeys}
	 * labeled alternative in {@link MariaDBParser#tableOption}.
	 * @param ctx the parse tree
	 */
	void enterTableOptionPackKeys(MariaDBParser.TableOptionPackKeysContext ctx);
	/**
	 * Exit a parse tree produced by the {@code tableOptionPackKeys}
	 * labeled alternative in {@link MariaDBParser#tableOption}.
	 * @param ctx the parse tree
	 */
	void exitTableOptionPackKeys(MariaDBParser.TableOptionPackKeysContext ctx);
	/**
	 * Enter a parse tree produced by the {@code tableOptionPassword}
	 * labeled alternative in {@link MariaDBParser#tableOption}.
	 * @param ctx the parse tree
	 */
	void enterTableOptionPassword(MariaDBParser.TableOptionPasswordContext ctx);
	/**
	 * Exit a parse tree produced by the {@code tableOptionPassword}
	 * labeled alternative in {@link MariaDBParser#tableOption}.
	 * @param ctx the parse tree
	 */
	void exitTableOptionPassword(MariaDBParser.TableOptionPasswordContext ctx);
	/**
	 * Enter a parse tree produced by the {@code tableOptionRowFormat}
	 * labeled alternative in {@link MariaDBParser#tableOption}.
	 * @param ctx the parse tree
	 */
	void enterTableOptionRowFormat(MariaDBParser.TableOptionRowFormatContext ctx);
	/**
	 * Exit a parse tree produced by the {@code tableOptionRowFormat}
	 * labeled alternative in {@link MariaDBParser#tableOption}.
	 * @param ctx the parse tree
	 */
	void exitTableOptionRowFormat(MariaDBParser.TableOptionRowFormatContext ctx);
	/**
	 * Enter a parse tree produced by the {@code tableOptionStartTransaction}
	 * labeled alternative in {@link MariaDBParser#tableOption}.
	 * @param ctx the parse tree
	 */
	void enterTableOptionStartTransaction(MariaDBParser.TableOptionStartTransactionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code tableOptionStartTransaction}
	 * labeled alternative in {@link MariaDBParser#tableOption}.
	 * @param ctx the parse tree
	 */
	void exitTableOptionStartTransaction(MariaDBParser.TableOptionStartTransactionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code tableOptionSecondaryEngineAttribute}
	 * labeled alternative in {@link MariaDBParser#tableOption}.
	 * @param ctx the parse tree
	 */
	void enterTableOptionSecondaryEngineAttribute(MariaDBParser.TableOptionSecondaryEngineAttributeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code tableOptionSecondaryEngineAttribute}
	 * labeled alternative in {@link MariaDBParser#tableOption}.
	 * @param ctx the parse tree
	 */
	void exitTableOptionSecondaryEngineAttribute(MariaDBParser.TableOptionSecondaryEngineAttributeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code tableOptionRecalculation}
	 * labeled alternative in {@link MariaDBParser#tableOption}.
	 * @param ctx the parse tree
	 */
	void enterTableOptionRecalculation(MariaDBParser.TableOptionRecalculationContext ctx);
	/**
	 * Exit a parse tree produced by the {@code tableOptionRecalculation}
	 * labeled alternative in {@link MariaDBParser#tableOption}.
	 * @param ctx the parse tree
	 */
	void exitTableOptionRecalculation(MariaDBParser.TableOptionRecalculationContext ctx);
	/**
	 * Enter a parse tree produced by the {@code tableOptionPersistent}
	 * labeled alternative in {@link MariaDBParser#tableOption}.
	 * @param ctx the parse tree
	 */
	void enterTableOptionPersistent(MariaDBParser.TableOptionPersistentContext ctx);
	/**
	 * Exit a parse tree produced by the {@code tableOptionPersistent}
	 * labeled alternative in {@link MariaDBParser#tableOption}.
	 * @param ctx the parse tree
	 */
	void exitTableOptionPersistent(MariaDBParser.TableOptionPersistentContext ctx);
	/**
	 * Enter a parse tree produced by the {@code tableOptionSamplePage}
	 * labeled alternative in {@link MariaDBParser#tableOption}.
	 * @param ctx the parse tree
	 */
	void enterTableOptionSamplePage(MariaDBParser.TableOptionSamplePageContext ctx);
	/**
	 * Exit a parse tree produced by the {@code tableOptionSamplePage}
	 * labeled alternative in {@link MariaDBParser#tableOption}.
	 * @param ctx the parse tree
	 */
	void exitTableOptionSamplePage(MariaDBParser.TableOptionSamplePageContext ctx);
	/**
	 * Enter a parse tree produced by the {@code tableOptionTablespace}
	 * labeled alternative in {@link MariaDBParser#tableOption}.
	 * @param ctx the parse tree
	 */
	void enterTableOptionTablespace(MariaDBParser.TableOptionTablespaceContext ctx);
	/**
	 * Exit a parse tree produced by the {@code tableOptionTablespace}
	 * labeled alternative in {@link MariaDBParser#tableOption}.
	 * @param ctx the parse tree
	 */
	void exitTableOptionTablespace(MariaDBParser.TableOptionTablespaceContext ctx);
	/**
	 * Enter a parse tree produced by the {@code tableOptionTableType}
	 * labeled alternative in {@link MariaDBParser#tableOption}.
	 * @param ctx the parse tree
	 */
	void enterTableOptionTableType(MariaDBParser.TableOptionTableTypeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code tableOptionTableType}
	 * labeled alternative in {@link MariaDBParser#tableOption}.
	 * @param ctx the parse tree
	 */
	void exitTableOptionTableType(MariaDBParser.TableOptionTableTypeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code tableOptionTransactional}
	 * labeled alternative in {@link MariaDBParser#tableOption}.
	 * @param ctx the parse tree
	 */
	void enterTableOptionTransactional(MariaDBParser.TableOptionTransactionalContext ctx);
	/**
	 * Exit a parse tree produced by the {@code tableOptionTransactional}
	 * labeled alternative in {@link MariaDBParser#tableOption}.
	 * @param ctx the parse tree
	 */
	void exitTableOptionTransactional(MariaDBParser.TableOptionTransactionalContext ctx);
	/**
	 * Enter a parse tree produced by the {@code tableOptionUnion}
	 * labeled alternative in {@link MariaDBParser#tableOption}.
	 * @param ctx the parse tree
	 */
	void enterTableOptionUnion(MariaDBParser.TableOptionUnionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code tableOptionUnion}
	 * labeled alternative in {@link MariaDBParser#tableOption}.
	 * @param ctx the parse tree
	 */
	void exitTableOptionUnion(MariaDBParser.TableOptionUnionContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#tableType}.
	 * @param ctx the parse tree
	 */
	void enterTableType(MariaDBParser.TableTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#tableType}.
	 * @param ctx the parse tree
	 */
	void exitTableType(MariaDBParser.TableTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#tablespaceStorage}.
	 * @param ctx the parse tree
	 */
	void enterTablespaceStorage(MariaDBParser.TablespaceStorageContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#tablespaceStorage}.
	 * @param ctx the parse tree
	 */
	void exitTablespaceStorage(MariaDBParser.TablespaceStorageContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#partitionDefinitions}.
	 * @param ctx the parse tree
	 */
	void enterPartitionDefinitions(MariaDBParser.PartitionDefinitionsContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#partitionDefinitions}.
	 * @param ctx the parse tree
	 */
	void exitPartitionDefinitions(MariaDBParser.PartitionDefinitionsContext ctx);
	/**
	 * Enter a parse tree produced by the {@code partitionFunctionHash}
	 * labeled alternative in {@link MariaDBParser#partitionFunctionDefinition}.
	 * @param ctx the parse tree
	 */
	void enterPartitionFunctionHash(MariaDBParser.PartitionFunctionHashContext ctx);
	/**
	 * Exit a parse tree produced by the {@code partitionFunctionHash}
	 * labeled alternative in {@link MariaDBParser#partitionFunctionDefinition}.
	 * @param ctx the parse tree
	 */
	void exitPartitionFunctionHash(MariaDBParser.PartitionFunctionHashContext ctx);
	/**
	 * Enter a parse tree produced by the {@code partitionFunctionKey}
	 * labeled alternative in {@link MariaDBParser#partitionFunctionDefinition}.
	 * @param ctx the parse tree
	 */
	void enterPartitionFunctionKey(MariaDBParser.PartitionFunctionKeyContext ctx);
	/**
	 * Exit a parse tree produced by the {@code partitionFunctionKey}
	 * labeled alternative in {@link MariaDBParser#partitionFunctionDefinition}.
	 * @param ctx the parse tree
	 */
	void exitPartitionFunctionKey(MariaDBParser.PartitionFunctionKeyContext ctx);
	/**
	 * Enter a parse tree produced by the {@code partitionFunctionRange}
	 * labeled alternative in {@link MariaDBParser#partitionFunctionDefinition}.
	 * @param ctx the parse tree
	 */
	void enterPartitionFunctionRange(MariaDBParser.PartitionFunctionRangeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code partitionFunctionRange}
	 * labeled alternative in {@link MariaDBParser#partitionFunctionDefinition}.
	 * @param ctx the parse tree
	 */
	void exitPartitionFunctionRange(MariaDBParser.PartitionFunctionRangeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code partitionFunctionList}
	 * labeled alternative in {@link MariaDBParser#partitionFunctionDefinition}.
	 * @param ctx the parse tree
	 */
	void enterPartitionFunctionList(MariaDBParser.PartitionFunctionListContext ctx);
	/**
	 * Exit a parse tree produced by the {@code partitionFunctionList}
	 * labeled alternative in {@link MariaDBParser#partitionFunctionDefinition}.
	 * @param ctx the parse tree
	 */
	void exitPartitionFunctionList(MariaDBParser.PartitionFunctionListContext ctx);
	/**
	 * Enter a parse tree produced by the {@code subPartitionFunctionHash}
	 * labeled alternative in {@link MariaDBParser#subpartitionFunctionDefinition}.
	 * @param ctx the parse tree
	 */
	void enterSubPartitionFunctionHash(MariaDBParser.SubPartitionFunctionHashContext ctx);
	/**
	 * Exit a parse tree produced by the {@code subPartitionFunctionHash}
	 * labeled alternative in {@link MariaDBParser#subpartitionFunctionDefinition}.
	 * @param ctx the parse tree
	 */
	void exitSubPartitionFunctionHash(MariaDBParser.SubPartitionFunctionHashContext ctx);
	/**
	 * Enter a parse tree produced by the {@code subPartitionFunctionKey}
	 * labeled alternative in {@link MariaDBParser#subpartitionFunctionDefinition}.
	 * @param ctx the parse tree
	 */
	void enterSubPartitionFunctionKey(MariaDBParser.SubPartitionFunctionKeyContext ctx);
	/**
	 * Exit a parse tree produced by the {@code subPartitionFunctionKey}
	 * labeled alternative in {@link MariaDBParser#subpartitionFunctionDefinition}.
	 * @param ctx the parse tree
	 */
	void exitSubPartitionFunctionKey(MariaDBParser.SubPartitionFunctionKeyContext ctx);
	/**
	 * Enter a parse tree produced by the {@code partitionComparison}
	 * labeled alternative in {@link MariaDBParser#partitionDefinition}.
	 * @param ctx the parse tree
	 */
	void enterPartitionComparison(MariaDBParser.PartitionComparisonContext ctx);
	/**
	 * Exit a parse tree produced by the {@code partitionComparison}
	 * labeled alternative in {@link MariaDBParser#partitionDefinition}.
	 * @param ctx the parse tree
	 */
	void exitPartitionComparison(MariaDBParser.PartitionComparisonContext ctx);
	/**
	 * Enter a parse tree produced by the {@code partitionListAtom}
	 * labeled alternative in {@link MariaDBParser#partitionDefinition}.
	 * @param ctx the parse tree
	 */
	void enterPartitionListAtom(MariaDBParser.PartitionListAtomContext ctx);
	/**
	 * Exit a parse tree produced by the {@code partitionListAtom}
	 * labeled alternative in {@link MariaDBParser#partitionDefinition}.
	 * @param ctx the parse tree
	 */
	void exitPartitionListAtom(MariaDBParser.PartitionListAtomContext ctx);
	/**
	 * Enter a parse tree produced by the {@code partitionListVector}
	 * labeled alternative in {@link MariaDBParser#partitionDefinition}.
	 * @param ctx the parse tree
	 */
	void enterPartitionListVector(MariaDBParser.PartitionListVectorContext ctx);
	/**
	 * Exit a parse tree produced by the {@code partitionListVector}
	 * labeled alternative in {@link MariaDBParser#partitionDefinition}.
	 * @param ctx the parse tree
	 */
	void exitPartitionListVector(MariaDBParser.PartitionListVectorContext ctx);
	/**
	 * Enter a parse tree produced by the {@code partitionSimple}
	 * labeled alternative in {@link MariaDBParser#partitionDefinition}.
	 * @param ctx the parse tree
	 */
	void enterPartitionSimple(MariaDBParser.PartitionSimpleContext ctx);
	/**
	 * Exit a parse tree produced by the {@code partitionSimple}
	 * labeled alternative in {@link MariaDBParser#partitionDefinition}.
	 * @param ctx the parse tree
	 */
	void exitPartitionSimple(MariaDBParser.PartitionSimpleContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#partitionDefinerAtom}.
	 * @param ctx the parse tree
	 */
	void enterPartitionDefinerAtom(MariaDBParser.PartitionDefinerAtomContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#partitionDefinerAtom}.
	 * @param ctx the parse tree
	 */
	void exitPartitionDefinerAtom(MariaDBParser.PartitionDefinerAtomContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#partitionDefinerVector}.
	 * @param ctx the parse tree
	 */
	void enterPartitionDefinerVector(MariaDBParser.PartitionDefinerVectorContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#partitionDefinerVector}.
	 * @param ctx the parse tree
	 */
	void exitPartitionDefinerVector(MariaDBParser.PartitionDefinerVectorContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#subpartitionDefinition}.
	 * @param ctx the parse tree
	 */
	void enterSubpartitionDefinition(MariaDBParser.SubpartitionDefinitionContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#subpartitionDefinition}.
	 * @param ctx the parse tree
	 */
	void exitSubpartitionDefinition(MariaDBParser.SubpartitionDefinitionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code partitionOptionEngine}
	 * labeled alternative in {@link MariaDBParser#partitionOption}.
	 * @param ctx the parse tree
	 */
	void enterPartitionOptionEngine(MariaDBParser.PartitionOptionEngineContext ctx);
	/**
	 * Exit a parse tree produced by the {@code partitionOptionEngine}
	 * labeled alternative in {@link MariaDBParser#partitionOption}.
	 * @param ctx the parse tree
	 */
	void exitPartitionOptionEngine(MariaDBParser.PartitionOptionEngineContext ctx);
	/**
	 * Enter a parse tree produced by the {@code partitionOptionComment}
	 * labeled alternative in {@link MariaDBParser#partitionOption}.
	 * @param ctx the parse tree
	 */
	void enterPartitionOptionComment(MariaDBParser.PartitionOptionCommentContext ctx);
	/**
	 * Exit a parse tree produced by the {@code partitionOptionComment}
	 * labeled alternative in {@link MariaDBParser#partitionOption}.
	 * @param ctx the parse tree
	 */
	void exitPartitionOptionComment(MariaDBParser.PartitionOptionCommentContext ctx);
	/**
	 * Enter a parse tree produced by the {@code partitionOptionDataDirectory}
	 * labeled alternative in {@link MariaDBParser#partitionOption}.
	 * @param ctx the parse tree
	 */
	void enterPartitionOptionDataDirectory(MariaDBParser.PartitionOptionDataDirectoryContext ctx);
	/**
	 * Exit a parse tree produced by the {@code partitionOptionDataDirectory}
	 * labeled alternative in {@link MariaDBParser#partitionOption}.
	 * @param ctx the parse tree
	 */
	void exitPartitionOptionDataDirectory(MariaDBParser.PartitionOptionDataDirectoryContext ctx);
	/**
	 * Enter a parse tree produced by the {@code partitionOptionIndexDirectory}
	 * labeled alternative in {@link MariaDBParser#partitionOption}.
	 * @param ctx the parse tree
	 */
	void enterPartitionOptionIndexDirectory(MariaDBParser.PartitionOptionIndexDirectoryContext ctx);
	/**
	 * Exit a parse tree produced by the {@code partitionOptionIndexDirectory}
	 * labeled alternative in {@link MariaDBParser#partitionOption}.
	 * @param ctx the parse tree
	 */
	void exitPartitionOptionIndexDirectory(MariaDBParser.PartitionOptionIndexDirectoryContext ctx);
	/**
	 * Enter a parse tree produced by the {@code partitionOptionMaxRows}
	 * labeled alternative in {@link MariaDBParser#partitionOption}.
	 * @param ctx the parse tree
	 */
	void enterPartitionOptionMaxRows(MariaDBParser.PartitionOptionMaxRowsContext ctx);
	/**
	 * Exit a parse tree produced by the {@code partitionOptionMaxRows}
	 * labeled alternative in {@link MariaDBParser#partitionOption}.
	 * @param ctx the parse tree
	 */
	void exitPartitionOptionMaxRows(MariaDBParser.PartitionOptionMaxRowsContext ctx);
	/**
	 * Enter a parse tree produced by the {@code partitionOptionMinRows}
	 * labeled alternative in {@link MariaDBParser#partitionOption}.
	 * @param ctx the parse tree
	 */
	void enterPartitionOptionMinRows(MariaDBParser.PartitionOptionMinRowsContext ctx);
	/**
	 * Exit a parse tree produced by the {@code partitionOptionMinRows}
	 * labeled alternative in {@link MariaDBParser#partitionOption}.
	 * @param ctx the parse tree
	 */
	void exitPartitionOptionMinRows(MariaDBParser.PartitionOptionMinRowsContext ctx);
	/**
	 * Enter a parse tree produced by the {@code partitionOptionTablespace}
	 * labeled alternative in {@link MariaDBParser#partitionOption}.
	 * @param ctx the parse tree
	 */
	void enterPartitionOptionTablespace(MariaDBParser.PartitionOptionTablespaceContext ctx);
	/**
	 * Exit a parse tree produced by the {@code partitionOptionTablespace}
	 * labeled alternative in {@link MariaDBParser#partitionOption}.
	 * @param ctx the parse tree
	 */
	void exitPartitionOptionTablespace(MariaDBParser.PartitionOptionTablespaceContext ctx);
	/**
	 * Enter a parse tree produced by the {@code partitionOptionNodeGroup}
	 * labeled alternative in {@link MariaDBParser#partitionOption}.
	 * @param ctx the parse tree
	 */
	void enterPartitionOptionNodeGroup(MariaDBParser.PartitionOptionNodeGroupContext ctx);
	/**
	 * Exit a parse tree produced by the {@code partitionOptionNodeGroup}
	 * labeled alternative in {@link MariaDBParser#partitionOption}.
	 * @param ctx the parse tree
	 */
	void exitPartitionOptionNodeGroup(MariaDBParser.PartitionOptionNodeGroupContext ctx);
	/**
	 * Enter a parse tree produced by the {@code alterSimpleDatabase}
	 * labeled alternative in {@link MariaDBParser#alterDatabase}.
	 * @param ctx the parse tree
	 */
	void enterAlterSimpleDatabase(MariaDBParser.AlterSimpleDatabaseContext ctx);
	/**
	 * Exit a parse tree produced by the {@code alterSimpleDatabase}
	 * labeled alternative in {@link MariaDBParser#alterDatabase}.
	 * @param ctx the parse tree
	 */
	void exitAlterSimpleDatabase(MariaDBParser.AlterSimpleDatabaseContext ctx);
	/**
	 * Enter a parse tree produced by the {@code alterUpgradeName}
	 * labeled alternative in {@link MariaDBParser#alterDatabase}.
	 * @param ctx the parse tree
	 */
	void enterAlterUpgradeName(MariaDBParser.AlterUpgradeNameContext ctx);
	/**
	 * Exit a parse tree produced by the {@code alterUpgradeName}
	 * labeled alternative in {@link MariaDBParser#alterDatabase}.
	 * @param ctx the parse tree
	 */
	void exitAlterUpgradeName(MariaDBParser.AlterUpgradeNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#alterEvent}.
	 * @param ctx the parse tree
	 */
	void enterAlterEvent(MariaDBParser.AlterEventContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#alterEvent}.
	 * @param ctx the parse tree
	 */
	void exitAlterEvent(MariaDBParser.AlterEventContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#alterFunction}.
	 * @param ctx the parse tree
	 */
	void enterAlterFunction(MariaDBParser.AlterFunctionContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#alterFunction}.
	 * @param ctx the parse tree
	 */
	void exitAlterFunction(MariaDBParser.AlterFunctionContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#alterInstance}.
	 * @param ctx the parse tree
	 */
	void enterAlterInstance(MariaDBParser.AlterInstanceContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#alterInstance}.
	 * @param ctx the parse tree
	 */
	void exitAlterInstance(MariaDBParser.AlterInstanceContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#alterLogfileGroup}.
	 * @param ctx the parse tree
	 */
	void enterAlterLogfileGroup(MariaDBParser.AlterLogfileGroupContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#alterLogfileGroup}.
	 * @param ctx the parse tree
	 */
	void exitAlterLogfileGroup(MariaDBParser.AlterLogfileGroupContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#alterProcedure}.
	 * @param ctx the parse tree
	 */
	void enterAlterProcedure(MariaDBParser.AlterProcedureContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#alterProcedure}.
	 * @param ctx the parse tree
	 */
	void exitAlterProcedure(MariaDBParser.AlterProcedureContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#alterServer}.
	 * @param ctx the parse tree
	 */
	void enterAlterServer(MariaDBParser.AlterServerContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#alterServer}.
	 * @param ctx the parse tree
	 */
	void exitAlterServer(MariaDBParser.AlterServerContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#alterTable}.
	 * @param ctx the parse tree
	 */
	void enterAlterTable(MariaDBParser.AlterTableContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#alterTable}.
	 * @param ctx the parse tree
	 */
	void exitAlterTable(MariaDBParser.AlterTableContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#alterTablespace}.
	 * @param ctx the parse tree
	 */
	void enterAlterTablespace(MariaDBParser.AlterTablespaceContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#alterTablespace}.
	 * @param ctx the parse tree
	 */
	void exitAlterTablespace(MariaDBParser.AlterTablespaceContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#alterView}.
	 * @param ctx the parse tree
	 */
	void enterAlterView(MariaDBParser.AlterViewContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#alterView}.
	 * @param ctx the parse tree
	 */
	void exitAlterView(MariaDBParser.AlterViewContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#alterSequence}.
	 * @param ctx the parse tree
	 */
	void enterAlterSequence(MariaDBParser.AlterSequenceContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#alterSequence}.
	 * @param ctx the parse tree
	 */
	void exitAlterSequence(MariaDBParser.AlterSequenceContext ctx);
	/**
	 * Enter a parse tree produced by the {@code alterByTableOption}
	 * labeled alternative in {@link MariaDBParser#alterSpecification}.
	 * @param ctx the parse tree
	 */
	void enterAlterByTableOption(MariaDBParser.AlterByTableOptionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code alterByTableOption}
	 * labeled alternative in {@link MariaDBParser#alterSpecification}.
	 * @param ctx the parse tree
	 */
	void exitAlterByTableOption(MariaDBParser.AlterByTableOptionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code alterByAddColumn}
	 * labeled alternative in {@link MariaDBParser#alterSpecification}.
	 * @param ctx the parse tree
	 */
	void enterAlterByAddColumn(MariaDBParser.AlterByAddColumnContext ctx);
	/**
	 * Exit a parse tree produced by the {@code alterByAddColumn}
	 * labeled alternative in {@link MariaDBParser#alterSpecification}.
	 * @param ctx the parse tree
	 */
	void exitAlterByAddColumn(MariaDBParser.AlterByAddColumnContext ctx);
	/**
	 * Enter a parse tree produced by the {@code alterByAddColumns}
	 * labeled alternative in {@link MariaDBParser#alterSpecification}.
	 * @param ctx the parse tree
	 */
	void enterAlterByAddColumns(MariaDBParser.AlterByAddColumnsContext ctx);
	/**
	 * Exit a parse tree produced by the {@code alterByAddColumns}
	 * labeled alternative in {@link MariaDBParser#alterSpecification}.
	 * @param ctx the parse tree
	 */
	void exitAlterByAddColumns(MariaDBParser.AlterByAddColumnsContext ctx);
	/**
	 * Enter a parse tree produced by the {@code alterByAddIndex}
	 * labeled alternative in {@link MariaDBParser#alterSpecification}.
	 * @param ctx the parse tree
	 */
	void enterAlterByAddIndex(MariaDBParser.AlterByAddIndexContext ctx);
	/**
	 * Exit a parse tree produced by the {@code alterByAddIndex}
	 * labeled alternative in {@link MariaDBParser#alterSpecification}.
	 * @param ctx the parse tree
	 */
	void exitAlterByAddIndex(MariaDBParser.AlterByAddIndexContext ctx);
	/**
	 * Enter a parse tree produced by the {@code alterByAddPrimaryKey}
	 * labeled alternative in {@link MariaDBParser#alterSpecification}.
	 * @param ctx the parse tree
	 */
	void enterAlterByAddPrimaryKey(MariaDBParser.AlterByAddPrimaryKeyContext ctx);
	/**
	 * Exit a parse tree produced by the {@code alterByAddPrimaryKey}
	 * labeled alternative in {@link MariaDBParser#alterSpecification}.
	 * @param ctx the parse tree
	 */
	void exitAlterByAddPrimaryKey(MariaDBParser.AlterByAddPrimaryKeyContext ctx);
	/**
	 * Enter a parse tree produced by the {@code alterByAddUniqueKey}
	 * labeled alternative in {@link MariaDBParser#alterSpecification}.
	 * @param ctx the parse tree
	 */
	void enterAlterByAddUniqueKey(MariaDBParser.AlterByAddUniqueKeyContext ctx);
	/**
	 * Exit a parse tree produced by the {@code alterByAddUniqueKey}
	 * labeled alternative in {@link MariaDBParser#alterSpecification}.
	 * @param ctx the parse tree
	 */
	void exitAlterByAddUniqueKey(MariaDBParser.AlterByAddUniqueKeyContext ctx);
	/**
	 * Enter a parse tree produced by the {@code alterByAddSpecialIndex}
	 * labeled alternative in {@link MariaDBParser#alterSpecification}.
	 * @param ctx the parse tree
	 */
	void enterAlterByAddSpecialIndex(MariaDBParser.AlterByAddSpecialIndexContext ctx);
	/**
	 * Exit a parse tree produced by the {@code alterByAddSpecialIndex}
	 * labeled alternative in {@link MariaDBParser#alterSpecification}.
	 * @param ctx the parse tree
	 */
	void exitAlterByAddSpecialIndex(MariaDBParser.AlterByAddSpecialIndexContext ctx);
	/**
	 * Enter a parse tree produced by the {@code alterByAddForeignKey}
	 * labeled alternative in {@link MariaDBParser#alterSpecification}.
	 * @param ctx the parse tree
	 */
	void enterAlterByAddForeignKey(MariaDBParser.AlterByAddForeignKeyContext ctx);
	/**
	 * Exit a parse tree produced by the {@code alterByAddForeignKey}
	 * labeled alternative in {@link MariaDBParser#alterSpecification}.
	 * @param ctx the parse tree
	 */
	void exitAlterByAddForeignKey(MariaDBParser.AlterByAddForeignKeyContext ctx);
	/**
	 * Enter a parse tree produced by the {@code alterByAddCheckTableConstraint}
	 * labeled alternative in {@link MariaDBParser#alterSpecification}.
	 * @param ctx the parse tree
	 */
	void enterAlterByAddCheckTableConstraint(MariaDBParser.AlterByAddCheckTableConstraintContext ctx);
	/**
	 * Exit a parse tree produced by the {@code alterByAddCheckTableConstraint}
	 * labeled alternative in {@link MariaDBParser#alterSpecification}.
	 * @param ctx the parse tree
	 */
	void exitAlterByAddCheckTableConstraint(MariaDBParser.AlterByAddCheckTableConstraintContext ctx);
	/**
	 * Enter a parse tree produced by the {@code alterBySetAlgorithm}
	 * labeled alternative in {@link MariaDBParser#alterSpecification}.
	 * @param ctx the parse tree
	 */
	void enterAlterBySetAlgorithm(MariaDBParser.AlterBySetAlgorithmContext ctx);
	/**
	 * Exit a parse tree produced by the {@code alterBySetAlgorithm}
	 * labeled alternative in {@link MariaDBParser#alterSpecification}.
	 * @param ctx the parse tree
	 */
	void exitAlterBySetAlgorithm(MariaDBParser.AlterBySetAlgorithmContext ctx);
	/**
	 * Enter a parse tree produced by the {@code alterByChangeDefault}
	 * labeled alternative in {@link MariaDBParser#alterSpecification}.
	 * @param ctx the parse tree
	 */
	void enterAlterByChangeDefault(MariaDBParser.AlterByChangeDefaultContext ctx);
	/**
	 * Exit a parse tree produced by the {@code alterByChangeDefault}
	 * labeled alternative in {@link MariaDBParser#alterSpecification}.
	 * @param ctx the parse tree
	 */
	void exitAlterByChangeDefault(MariaDBParser.AlterByChangeDefaultContext ctx);
	/**
	 * Enter a parse tree produced by the {@code alterByChangeColumn}
	 * labeled alternative in {@link MariaDBParser#alterSpecification}.
	 * @param ctx the parse tree
	 */
	void enterAlterByChangeColumn(MariaDBParser.AlterByChangeColumnContext ctx);
	/**
	 * Exit a parse tree produced by the {@code alterByChangeColumn}
	 * labeled alternative in {@link MariaDBParser#alterSpecification}.
	 * @param ctx the parse tree
	 */
	void exitAlterByChangeColumn(MariaDBParser.AlterByChangeColumnContext ctx);
	/**
	 * Enter a parse tree produced by the {@code alterByRenameColumn}
	 * labeled alternative in {@link MariaDBParser#alterSpecification}.
	 * @param ctx the parse tree
	 */
	void enterAlterByRenameColumn(MariaDBParser.AlterByRenameColumnContext ctx);
	/**
	 * Exit a parse tree produced by the {@code alterByRenameColumn}
	 * labeled alternative in {@link MariaDBParser#alterSpecification}.
	 * @param ctx the parse tree
	 */
	void exitAlterByRenameColumn(MariaDBParser.AlterByRenameColumnContext ctx);
	/**
	 * Enter a parse tree produced by the {@code alterByLock}
	 * labeled alternative in {@link MariaDBParser#alterSpecification}.
	 * @param ctx the parse tree
	 */
	void enterAlterByLock(MariaDBParser.AlterByLockContext ctx);
	/**
	 * Exit a parse tree produced by the {@code alterByLock}
	 * labeled alternative in {@link MariaDBParser#alterSpecification}.
	 * @param ctx the parse tree
	 */
	void exitAlterByLock(MariaDBParser.AlterByLockContext ctx);
	/**
	 * Enter a parse tree produced by the {@code alterByModifyColumn}
	 * labeled alternative in {@link MariaDBParser#alterSpecification}.
	 * @param ctx the parse tree
	 */
	void enterAlterByModifyColumn(MariaDBParser.AlterByModifyColumnContext ctx);
	/**
	 * Exit a parse tree produced by the {@code alterByModifyColumn}
	 * labeled alternative in {@link MariaDBParser#alterSpecification}.
	 * @param ctx the parse tree
	 */
	void exitAlterByModifyColumn(MariaDBParser.AlterByModifyColumnContext ctx);
	/**
	 * Enter a parse tree produced by the {@code alterByDropColumn}
	 * labeled alternative in {@link MariaDBParser#alterSpecification}.
	 * @param ctx the parse tree
	 */
	void enterAlterByDropColumn(MariaDBParser.AlterByDropColumnContext ctx);
	/**
	 * Exit a parse tree produced by the {@code alterByDropColumn}
	 * labeled alternative in {@link MariaDBParser#alterSpecification}.
	 * @param ctx the parse tree
	 */
	void exitAlterByDropColumn(MariaDBParser.AlterByDropColumnContext ctx);
	/**
	 * Enter a parse tree produced by the {@code alterByDropConstraintCheck}
	 * labeled alternative in {@link MariaDBParser#alterSpecification}.
	 * @param ctx the parse tree
	 */
	void enterAlterByDropConstraintCheck(MariaDBParser.AlterByDropConstraintCheckContext ctx);
	/**
	 * Exit a parse tree produced by the {@code alterByDropConstraintCheck}
	 * labeled alternative in {@link MariaDBParser#alterSpecification}.
	 * @param ctx the parse tree
	 */
	void exitAlterByDropConstraintCheck(MariaDBParser.AlterByDropConstraintCheckContext ctx);
	/**
	 * Enter a parse tree produced by the {@code alterByDropPrimaryKey}
	 * labeled alternative in {@link MariaDBParser#alterSpecification}.
	 * @param ctx the parse tree
	 */
	void enterAlterByDropPrimaryKey(MariaDBParser.AlterByDropPrimaryKeyContext ctx);
	/**
	 * Exit a parse tree produced by the {@code alterByDropPrimaryKey}
	 * labeled alternative in {@link MariaDBParser#alterSpecification}.
	 * @param ctx the parse tree
	 */
	void exitAlterByDropPrimaryKey(MariaDBParser.AlterByDropPrimaryKeyContext ctx);
	/**
	 * Enter a parse tree produced by the {@code alterByDropIndex}
	 * labeled alternative in {@link MariaDBParser#alterSpecification}.
	 * @param ctx the parse tree
	 */
	void enterAlterByDropIndex(MariaDBParser.AlterByDropIndexContext ctx);
	/**
	 * Exit a parse tree produced by the {@code alterByDropIndex}
	 * labeled alternative in {@link MariaDBParser#alterSpecification}.
	 * @param ctx the parse tree
	 */
	void exitAlterByDropIndex(MariaDBParser.AlterByDropIndexContext ctx);
	/**
	 * Enter a parse tree produced by the {@code alterByRenameIndex}
	 * labeled alternative in {@link MariaDBParser#alterSpecification}.
	 * @param ctx the parse tree
	 */
	void enterAlterByRenameIndex(MariaDBParser.AlterByRenameIndexContext ctx);
	/**
	 * Exit a parse tree produced by the {@code alterByRenameIndex}
	 * labeled alternative in {@link MariaDBParser#alterSpecification}.
	 * @param ctx the parse tree
	 */
	void exitAlterByRenameIndex(MariaDBParser.AlterByRenameIndexContext ctx);
	/**
	 * Enter a parse tree produced by the {@code alterByAlterIndexVisibility}
	 * labeled alternative in {@link MariaDBParser#alterSpecification}.
	 * @param ctx the parse tree
	 */
	void enterAlterByAlterIndexVisibility(MariaDBParser.AlterByAlterIndexVisibilityContext ctx);
	/**
	 * Exit a parse tree produced by the {@code alterByAlterIndexVisibility}
	 * labeled alternative in {@link MariaDBParser#alterSpecification}.
	 * @param ctx the parse tree
	 */
	void exitAlterByAlterIndexVisibility(MariaDBParser.AlterByAlterIndexVisibilityContext ctx);
	/**
	 * Enter a parse tree produced by the {@code alterByDropForeignKey}
	 * labeled alternative in {@link MariaDBParser#alterSpecification}.
	 * @param ctx the parse tree
	 */
	void enterAlterByDropForeignKey(MariaDBParser.AlterByDropForeignKeyContext ctx);
	/**
	 * Exit a parse tree produced by the {@code alterByDropForeignKey}
	 * labeled alternative in {@link MariaDBParser#alterSpecification}.
	 * @param ctx the parse tree
	 */
	void exitAlterByDropForeignKey(MariaDBParser.AlterByDropForeignKeyContext ctx);
	/**
	 * Enter a parse tree produced by the {@code alterByDisableKeys}
	 * labeled alternative in {@link MariaDBParser#alterSpecification}.
	 * @param ctx the parse tree
	 */
	void enterAlterByDisableKeys(MariaDBParser.AlterByDisableKeysContext ctx);
	/**
	 * Exit a parse tree produced by the {@code alterByDisableKeys}
	 * labeled alternative in {@link MariaDBParser#alterSpecification}.
	 * @param ctx the parse tree
	 */
	void exitAlterByDisableKeys(MariaDBParser.AlterByDisableKeysContext ctx);
	/**
	 * Enter a parse tree produced by the {@code alterByEnableKeys}
	 * labeled alternative in {@link MariaDBParser#alterSpecification}.
	 * @param ctx the parse tree
	 */
	void enterAlterByEnableKeys(MariaDBParser.AlterByEnableKeysContext ctx);
	/**
	 * Exit a parse tree produced by the {@code alterByEnableKeys}
	 * labeled alternative in {@link MariaDBParser#alterSpecification}.
	 * @param ctx the parse tree
	 */
	void exitAlterByEnableKeys(MariaDBParser.AlterByEnableKeysContext ctx);
	/**
	 * Enter a parse tree produced by the {@code alterByRename}
	 * labeled alternative in {@link MariaDBParser#alterSpecification}.
	 * @param ctx the parse tree
	 */
	void enterAlterByRename(MariaDBParser.AlterByRenameContext ctx);
	/**
	 * Exit a parse tree produced by the {@code alterByRename}
	 * labeled alternative in {@link MariaDBParser#alterSpecification}.
	 * @param ctx the parse tree
	 */
	void exitAlterByRename(MariaDBParser.AlterByRenameContext ctx);
	/**
	 * Enter a parse tree produced by the {@code alterByOrder}
	 * labeled alternative in {@link MariaDBParser#alterSpecification}.
	 * @param ctx the parse tree
	 */
	void enterAlterByOrder(MariaDBParser.AlterByOrderContext ctx);
	/**
	 * Exit a parse tree produced by the {@code alterByOrder}
	 * labeled alternative in {@link MariaDBParser#alterSpecification}.
	 * @param ctx the parse tree
	 */
	void exitAlterByOrder(MariaDBParser.AlterByOrderContext ctx);
	/**
	 * Enter a parse tree produced by the {@code alterByConvertCharset}
	 * labeled alternative in {@link MariaDBParser#alterSpecification}.
	 * @param ctx the parse tree
	 */
	void enterAlterByConvertCharset(MariaDBParser.AlterByConvertCharsetContext ctx);
	/**
	 * Exit a parse tree produced by the {@code alterByConvertCharset}
	 * labeled alternative in {@link MariaDBParser#alterSpecification}.
	 * @param ctx the parse tree
	 */
	void exitAlterByConvertCharset(MariaDBParser.AlterByConvertCharsetContext ctx);
	/**
	 * Enter a parse tree produced by the {@code alterByDefaultCharset}
	 * labeled alternative in {@link MariaDBParser#alterSpecification}.
	 * @param ctx the parse tree
	 */
	void enterAlterByDefaultCharset(MariaDBParser.AlterByDefaultCharsetContext ctx);
	/**
	 * Exit a parse tree produced by the {@code alterByDefaultCharset}
	 * labeled alternative in {@link MariaDBParser#alterSpecification}.
	 * @param ctx the parse tree
	 */
	void exitAlterByDefaultCharset(MariaDBParser.AlterByDefaultCharsetContext ctx);
	/**
	 * Enter a parse tree produced by the {@code alterByDiscardTablespace}
	 * labeled alternative in {@link MariaDBParser#alterSpecification}.
	 * @param ctx the parse tree
	 */
	void enterAlterByDiscardTablespace(MariaDBParser.AlterByDiscardTablespaceContext ctx);
	/**
	 * Exit a parse tree produced by the {@code alterByDiscardTablespace}
	 * labeled alternative in {@link MariaDBParser#alterSpecification}.
	 * @param ctx the parse tree
	 */
	void exitAlterByDiscardTablespace(MariaDBParser.AlterByDiscardTablespaceContext ctx);
	/**
	 * Enter a parse tree produced by the {@code alterByImportTablespace}
	 * labeled alternative in {@link MariaDBParser#alterSpecification}.
	 * @param ctx the parse tree
	 */
	void enterAlterByImportTablespace(MariaDBParser.AlterByImportTablespaceContext ctx);
	/**
	 * Exit a parse tree produced by the {@code alterByImportTablespace}
	 * labeled alternative in {@link MariaDBParser#alterSpecification}.
	 * @param ctx the parse tree
	 */
	void exitAlterByImportTablespace(MariaDBParser.AlterByImportTablespaceContext ctx);
	/**
	 * Enter a parse tree produced by the {@code alterByForce}
	 * labeled alternative in {@link MariaDBParser#alterSpecification}.
	 * @param ctx the parse tree
	 */
	void enterAlterByForce(MariaDBParser.AlterByForceContext ctx);
	/**
	 * Exit a parse tree produced by the {@code alterByForce}
	 * labeled alternative in {@link MariaDBParser#alterSpecification}.
	 * @param ctx the parse tree
	 */
	void exitAlterByForce(MariaDBParser.AlterByForceContext ctx);
	/**
	 * Enter a parse tree produced by the {@code alterByValidate}
	 * labeled alternative in {@link MariaDBParser#alterSpecification}.
	 * @param ctx the parse tree
	 */
	void enterAlterByValidate(MariaDBParser.AlterByValidateContext ctx);
	/**
	 * Exit a parse tree produced by the {@code alterByValidate}
	 * labeled alternative in {@link MariaDBParser#alterSpecification}.
	 * @param ctx the parse tree
	 */
	void exitAlterByValidate(MariaDBParser.AlterByValidateContext ctx);
	/**
	 * Enter a parse tree produced by the {@code alterByAddDefinitions}
	 * labeled alternative in {@link MariaDBParser#alterSpecification}.
	 * @param ctx the parse tree
	 */
	void enterAlterByAddDefinitions(MariaDBParser.AlterByAddDefinitionsContext ctx);
	/**
	 * Exit a parse tree produced by the {@code alterByAddDefinitions}
	 * labeled alternative in {@link MariaDBParser#alterSpecification}.
	 * @param ctx the parse tree
	 */
	void exitAlterByAddDefinitions(MariaDBParser.AlterByAddDefinitionsContext ctx);
	/**
	 * Enter a parse tree produced by the {@code alterPartition}
	 * labeled alternative in {@link MariaDBParser#alterSpecification}.
	 * @param ctx the parse tree
	 */
	void enterAlterPartition(MariaDBParser.AlterPartitionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code alterPartition}
	 * labeled alternative in {@link MariaDBParser#alterSpecification}.
	 * @param ctx the parse tree
	 */
	void exitAlterPartition(MariaDBParser.AlterPartitionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code alterByAddPartition}
	 * labeled alternative in {@link MariaDBParser#alterPartitionSpecification}.
	 * @param ctx the parse tree
	 */
	void enterAlterByAddPartition(MariaDBParser.AlterByAddPartitionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code alterByAddPartition}
	 * labeled alternative in {@link MariaDBParser#alterPartitionSpecification}.
	 * @param ctx the parse tree
	 */
	void exitAlterByAddPartition(MariaDBParser.AlterByAddPartitionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code alterByDropPartition}
	 * labeled alternative in {@link MariaDBParser#alterPartitionSpecification}.
	 * @param ctx the parse tree
	 */
	void enterAlterByDropPartition(MariaDBParser.AlterByDropPartitionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code alterByDropPartition}
	 * labeled alternative in {@link MariaDBParser#alterPartitionSpecification}.
	 * @param ctx the parse tree
	 */
	void exitAlterByDropPartition(MariaDBParser.AlterByDropPartitionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code alterByDiscardPartition}
	 * labeled alternative in {@link MariaDBParser#alterPartitionSpecification}.
	 * @param ctx the parse tree
	 */
	void enterAlterByDiscardPartition(MariaDBParser.AlterByDiscardPartitionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code alterByDiscardPartition}
	 * labeled alternative in {@link MariaDBParser#alterPartitionSpecification}.
	 * @param ctx the parse tree
	 */
	void exitAlterByDiscardPartition(MariaDBParser.AlterByDiscardPartitionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code alterByImportPartition}
	 * labeled alternative in {@link MariaDBParser#alterPartitionSpecification}.
	 * @param ctx the parse tree
	 */
	void enterAlterByImportPartition(MariaDBParser.AlterByImportPartitionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code alterByImportPartition}
	 * labeled alternative in {@link MariaDBParser#alterPartitionSpecification}.
	 * @param ctx the parse tree
	 */
	void exitAlterByImportPartition(MariaDBParser.AlterByImportPartitionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code alterByTruncatePartition}
	 * labeled alternative in {@link MariaDBParser#alterPartitionSpecification}.
	 * @param ctx the parse tree
	 */
	void enterAlterByTruncatePartition(MariaDBParser.AlterByTruncatePartitionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code alterByTruncatePartition}
	 * labeled alternative in {@link MariaDBParser#alterPartitionSpecification}.
	 * @param ctx the parse tree
	 */
	void exitAlterByTruncatePartition(MariaDBParser.AlterByTruncatePartitionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code alterByCoalescePartition}
	 * labeled alternative in {@link MariaDBParser#alterPartitionSpecification}.
	 * @param ctx the parse tree
	 */
	void enterAlterByCoalescePartition(MariaDBParser.AlterByCoalescePartitionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code alterByCoalescePartition}
	 * labeled alternative in {@link MariaDBParser#alterPartitionSpecification}.
	 * @param ctx the parse tree
	 */
	void exitAlterByCoalescePartition(MariaDBParser.AlterByCoalescePartitionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code alterByReorganizePartition}
	 * labeled alternative in {@link MariaDBParser#alterPartitionSpecification}.
	 * @param ctx the parse tree
	 */
	void enterAlterByReorganizePartition(MariaDBParser.AlterByReorganizePartitionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code alterByReorganizePartition}
	 * labeled alternative in {@link MariaDBParser#alterPartitionSpecification}.
	 * @param ctx the parse tree
	 */
	void exitAlterByReorganizePartition(MariaDBParser.AlterByReorganizePartitionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code alterByExchangePartition}
	 * labeled alternative in {@link MariaDBParser#alterPartitionSpecification}.
	 * @param ctx the parse tree
	 */
	void enterAlterByExchangePartition(MariaDBParser.AlterByExchangePartitionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code alterByExchangePartition}
	 * labeled alternative in {@link MariaDBParser#alterPartitionSpecification}.
	 * @param ctx the parse tree
	 */
	void exitAlterByExchangePartition(MariaDBParser.AlterByExchangePartitionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code alterByAnalyzePartition}
	 * labeled alternative in {@link MariaDBParser#alterPartitionSpecification}.
	 * @param ctx the parse tree
	 */
	void enterAlterByAnalyzePartition(MariaDBParser.AlterByAnalyzePartitionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code alterByAnalyzePartition}
	 * labeled alternative in {@link MariaDBParser#alterPartitionSpecification}.
	 * @param ctx the parse tree
	 */
	void exitAlterByAnalyzePartition(MariaDBParser.AlterByAnalyzePartitionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code alterByCheckPartition}
	 * labeled alternative in {@link MariaDBParser#alterPartitionSpecification}.
	 * @param ctx the parse tree
	 */
	void enterAlterByCheckPartition(MariaDBParser.AlterByCheckPartitionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code alterByCheckPartition}
	 * labeled alternative in {@link MariaDBParser#alterPartitionSpecification}.
	 * @param ctx the parse tree
	 */
	void exitAlterByCheckPartition(MariaDBParser.AlterByCheckPartitionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code alterByOptimizePartition}
	 * labeled alternative in {@link MariaDBParser#alterPartitionSpecification}.
	 * @param ctx the parse tree
	 */
	void enterAlterByOptimizePartition(MariaDBParser.AlterByOptimizePartitionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code alterByOptimizePartition}
	 * labeled alternative in {@link MariaDBParser#alterPartitionSpecification}.
	 * @param ctx the parse tree
	 */
	void exitAlterByOptimizePartition(MariaDBParser.AlterByOptimizePartitionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code alterByRebuildPartition}
	 * labeled alternative in {@link MariaDBParser#alterPartitionSpecification}.
	 * @param ctx the parse tree
	 */
	void enterAlterByRebuildPartition(MariaDBParser.AlterByRebuildPartitionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code alterByRebuildPartition}
	 * labeled alternative in {@link MariaDBParser#alterPartitionSpecification}.
	 * @param ctx the parse tree
	 */
	void exitAlterByRebuildPartition(MariaDBParser.AlterByRebuildPartitionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code alterByRepairPartition}
	 * labeled alternative in {@link MariaDBParser#alterPartitionSpecification}.
	 * @param ctx the parse tree
	 */
	void enterAlterByRepairPartition(MariaDBParser.AlterByRepairPartitionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code alterByRepairPartition}
	 * labeled alternative in {@link MariaDBParser#alterPartitionSpecification}.
	 * @param ctx the parse tree
	 */
	void exitAlterByRepairPartition(MariaDBParser.AlterByRepairPartitionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code alterByRemovePartitioning}
	 * labeled alternative in {@link MariaDBParser#alterPartitionSpecification}.
	 * @param ctx the parse tree
	 */
	void enterAlterByRemovePartitioning(MariaDBParser.AlterByRemovePartitioningContext ctx);
	/**
	 * Exit a parse tree produced by the {@code alterByRemovePartitioning}
	 * labeled alternative in {@link MariaDBParser#alterPartitionSpecification}.
	 * @param ctx the parse tree
	 */
	void exitAlterByRemovePartitioning(MariaDBParser.AlterByRemovePartitioningContext ctx);
	/**
	 * Enter a parse tree produced by the {@code alterByUpgradePartitioning}
	 * labeled alternative in {@link MariaDBParser#alterPartitionSpecification}.
	 * @param ctx the parse tree
	 */
	void enterAlterByUpgradePartitioning(MariaDBParser.AlterByUpgradePartitioningContext ctx);
	/**
	 * Exit a parse tree produced by the {@code alterByUpgradePartitioning}
	 * labeled alternative in {@link MariaDBParser#alterPartitionSpecification}.
	 * @param ctx the parse tree
	 */
	void exitAlterByUpgradePartitioning(MariaDBParser.AlterByUpgradePartitioningContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#dropDatabase}.
	 * @param ctx the parse tree
	 */
	void enterDropDatabase(MariaDBParser.DropDatabaseContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#dropDatabase}.
	 * @param ctx the parse tree
	 */
	void exitDropDatabase(MariaDBParser.DropDatabaseContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#dropEvent}.
	 * @param ctx the parse tree
	 */
	void enterDropEvent(MariaDBParser.DropEventContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#dropEvent}.
	 * @param ctx the parse tree
	 */
	void exitDropEvent(MariaDBParser.DropEventContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#dropIndex}.
	 * @param ctx the parse tree
	 */
	void enterDropIndex(MariaDBParser.DropIndexContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#dropIndex}.
	 * @param ctx the parse tree
	 */
	void exitDropIndex(MariaDBParser.DropIndexContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#dropLogfileGroup}.
	 * @param ctx the parse tree
	 */
	void enterDropLogfileGroup(MariaDBParser.DropLogfileGroupContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#dropLogfileGroup}.
	 * @param ctx the parse tree
	 */
	void exitDropLogfileGroup(MariaDBParser.DropLogfileGroupContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#dropProcedure}.
	 * @param ctx the parse tree
	 */
	void enterDropProcedure(MariaDBParser.DropProcedureContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#dropProcedure}.
	 * @param ctx the parse tree
	 */
	void exitDropProcedure(MariaDBParser.DropProcedureContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#dropFunction}.
	 * @param ctx the parse tree
	 */
	void enterDropFunction(MariaDBParser.DropFunctionContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#dropFunction}.
	 * @param ctx the parse tree
	 */
	void exitDropFunction(MariaDBParser.DropFunctionContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#dropServer}.
	 * @param ctx the parse tree
	 */
	void enterDropServer(MariaDBParser.DropServerContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#dropServer}.
	 * @param ctx the parse tree
	 */
	void exitDropServer(MariaDBParser.DropServerContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#dropTable}.
	 * @param ctx the parse tree
	 */
	void enterDropTable(MariaDBParser.DropTableContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#dropTable}.
	 * @param ctx the parse tree
	 */
	void exitDropTable(MariaDBParser.DropTableContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#dropTablespace}.
	 * @param ctx the parse tree
	 */
	void enterDropTablespace(MariaDBParser.DropTablespaceContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#dropTablespace}.
	 * @param ctx the parse tree
	 */
	void exitDropTablespace(MariaDBParser.DropTablespaceContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#dropTrigger}.
	 * @param ctx the parse tree
	 */
	void enterDropTrigger(MariaDBParser.DropTriggerContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#dropTrigger}.
	 * @param ctx the parse tree
	 */
	void exitDropTrigger(MariaDBParser.DropTriggerContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#dropView}.
	 * @param ctx the parse tree
	 */
	void enterDropView(MariaDBParser.DropViewContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#dropView}.
	 * @param ctx the parse tree
	 */
	void exitDropView(MariaDBParser.DropViewContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#dropRole}.
	 * @param ctx the parse tree
	 */
	void enterDropRole(MariaDBParser.DropRoleContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#dropRole}.
	 * @param ctx the parse tree
	 */
	void exitDropRole(MariaDBParser.DropRoleContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#setRole}.
	 * @param ctx the parse tree
	 */
	void enterSetRole(MariaDBParser.SetRoleContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#setRole}.
	 * @param ctx the parse tree
	 */
	void exitSetRole(MariaDBParser.SetRoleContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#dropSequence}.
	 * @param ctx the parse tree
	 */
	void enterDropSequence(MariaDBParser.DropSequenceContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#dropSequence}.
	 * @param ctx the parse tree
	 */
	void exitDropSequence(MariaDBParser.DropSequenceContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#renameTable}.
	 * @param ctx the parse tree
	 */
	void enterRenameTable(MariaDBParser.RenameTableContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#renameTable}.
	 * @param ctx the parse tree
	 */
	void exitRenameTable(MariaDBParser.RenameTableContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#renameTableClause}.
	 * @param ctx the parse tree
	 */
	void enterRenameTableClause(MariaDBParser.RenameTableClauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#renameTableClause}.
	 * @param ctx the parse tree
	 */
	void exitRenameTableClause(MariaDBParser.RenameTableClauseContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#truncateTable}.
	 * @param ctx the parse tree
	 */
	void enterTruncateTable(MariaDBParser.TruncateTableContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#truncateTable}.
	 * @param ctx the parse tree
	 */
	void exitTruncateTable(MariaDBParser.TruncateTableContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#callStatement}.
	 * @param ctx the parse tree
	 */
	void enterCallStatement(MariaDBParser.CallStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#callStatement}.
	 * @param ctx the parse tree
	 */
	void exitCallStatement(MariaDBParser.CallStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#deleteStatement}.
	 * @param ctx the parse tree
	 */
	void enterDeleteStatement(MariaDBParser.DeleteStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#deleteStatement}.
	 * @param ctx the parse tree
	 */
	void exitDeleteStatement(MariaDBParser.DeleteStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#doStatement}.
	 * @param ctx the parse tree
	 */
	void enterDoStatement(MariaDBParser.DoStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#doStatement}.
	 * @param ctx the parse tree
	 */
	void exitDoStatement(MariaDBParser.DoStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#handlerStatement}.
	 * @param ctx the parse tree
	 */
	void enterHandlerStatement(MariaDBParser.HandlerStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#handlerStatement}.
	 * @param ctx the parse tree
	 */
	void exitHandlerStatement(MariaDBParser.HandlerStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#insertStatement}.
	 * @param ctx the parse tree
	 */
	void enterInsertStatement(MariaDBParser.InsertStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#insertStatement}.
	 * @param ctx the parse tree
	 */
	void exitInsertStatement(MariaDBParser.InsertStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#loadDataStatement}.
	 * @param ctx the parse tree
	 */
	void enterLoadDataStatement(MariaDBParser.LoadDataStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#loadDataStatement}.
	 * @param ctx the parse tree
	 */
	void exitLoadDataStatement(MariaDBParser.LoadDataStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#loadXmlStatement}.
	 * @param ctx the parse tree
	 */
	void enterLoadXmlStatement(MariaDBParser.LoadXmlStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#loadXmlStatement}.
	 * @param ctx the parse tree
	 */
	void exitLoadXmlStatement(MariaDBParser.LoadXmlStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#replaceStatement}.
	 * @param ctx the parse tree
	 */
	void enterReplaceStatement(MariaDBParser.ReplaceStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#replaceStatement}.
	 * @param ctx the parse tree
	 */
	void exitReplaceStatement(MariaDBParser.ReplaceStatementContext ctx);
	/**
	 * Enter a parse tree produced by the {@code simpleSelect}
	 * labeled alternative in {@link MariaDBParser#selectStatement}.
	 * @param ctx the parse tree
	 */
	void enterSimpleSelect(MariaDBParser.SimpleSelectContext ctx);
	/**
	 * Exit a parse tree produced by the {@code simpleSelect}
	 * labeled alternative in {@link MariaDBParser#selectStatement}.
	 * @param ctx the parse tree
	 */
	void exitSimpleSelect(MariaDBParser.SimpleSelectContext ctx);
	/**
	 * Enter a parse tree produced by the {@code parenthesisSelect}
	 * labeled alternative in {@link MariaDBParser#selectStatement}.
	 * @param ctx the parse tree
	 */
	void enterParenthesisSelect(MariaDBParser.ParenthesisSelectContext ctx);
	/**
	 * Exit a parse tree produced by the {@code parenthesisSelect}
	 * labeled alternative in {@link MariaDBParser#selectStatement}.
	 * @param ctx the parse tree
	 */
	void exitParenthesisSelect(MariaDBParser.ParenthesisSelectContext ctx);
	/**
	 * Enter a parse tree produced by the {@code unionSelect}
	 * labeled alternative in {@link MariaDBParser#selectStatement}.
	 * @param ctx the parse tree
	 */
	void enterUnionSelect(MariaDBParser.UnionSelectContext ctx);
	/**
	 * Exit a parse tree produced by the {@code unionSelect}
	 * labeled alternative in {@link MariaDBParser#selectStatement}.
	 * @param ctx the parse tree
	 */
	void exitUnionSelect(MariaDBParser.UnionSelectContext ctx);
	/**
	 * Enter a parse tree produced by the {@code unionParenthesisSelect}
	 * labeled alternative in {@link MariaDBParser#selectStatement}.
	 * @param ctx the parse tree
	 */
	void enterUnionParenthesisSelect(MariaDBParser.UnionParenthesisSelectContext ctx);
	/**
	 * Exit a parse tree produced by the {@code unionParenthesisSelect}
	 * labeled alternative in {@link MariaDBParser#selectStatement}.
	 * @param ctx the parse tree
	 */
	void exitUnionParenthesisSelect(MariaDBParser.UnionParenthesisSelectContext ctx);
	/**
	 * Enter a parse tree produced by the {@code withLateralStatement}
	 * labeled alternative in {@link MariaDBParser#selectStatement}.
	 * @param ctx the parse tree
	 */
	void enterWithLateralStatement(MariaDBParser.WithLateralStatementContext ctx);
	/**
	 * Exit a parse tree produced by the {@code withLateralStatement}
	 * labeled alternative in {@link MariaDBParser#selectStatement}.
	 * @param ctx the parse tree
	 */
	void exitWithLateralStatement(MariaDBParser.WithLateralStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#updateStatement}.
	 * @param ctx the parse tree
	 */
	void enterUpdateStatement(MariaDBParser.UpdateStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#updateStatement}.
	 * @param ctx the parse tree
	 */
	void exitUpdateStatement(MariaDBParser.UpdateStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#valuesStatement}.
	 * @param ctx the parse tree
	 */
	void enterValuesStatement(MariaDBParser.ValuesStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#valuesStatement}.
	 * @param ctx the parse tree
	 */
	void exitValuesStatement(MariaDBParser.ValuesStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#insertStatementValue}.
	 * @param ctx the parse tree
	 */
	void enterInsertStatementValue(MariaDBParser.InsertStatementValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#insertStatementValue}.
	 * @param ctx the parse tree
	 */
	void exitInsertStatementValue(MariaDBParser.InsertStatementValueContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#updatedElement}.
	 * @param ctx the parse tree
	 */
	void enterUpdatedElement(MariaDBParser.UpdatedElementContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#updatedElement}.
	 * @param ctx the parse tree
	 */
	void exitUpdatedElement(MariaDBParser.UpdatedElementContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#assignmentField}.
	 * @param ctx the parse tree
	 */
	void enterAssignmentField(MariaDBParser.AssignmentFieldContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#assignmentField}.
	 * @param ctx the parse tree
	 */
	void exitAssignmentField(MariaDBParser.AssignmentFieldContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#lockClause}.
	 * @param ctx the parse tree
	 */
	void enterLockClause(MariaDBParser.LockClauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#lockClause}.
	 * @param ctx the parse tree
	 */
	void exitLockClause(MariaDBParser.LockClauseContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#singleDeleteStatement}.
	 * @param ctx the parse tree
	 */
	void enterSingleDeleteStatement(MariaDBParser.SingleDeleteStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#singleDeleteStatement}.
	 * @param ctx the parse tree
	 */
	void exitSingleDeleteStatement(MariaDBParser.SingleDeleteStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#multipleDeleteStatement}.
	 * @param ctx the parse tree
	 */
	void enterMultipleDeleteStatement(MariaDBParser.MultipleDeleteStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#multipleDeleteStatement}.
	 * @param ctx the parse tree
	 */
	void exitMultipleDeleteStatement(MariaDBParser.MultipleDeleteStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#handlerOpenStatement}.
	 * @param ctx the parse tree
	 */
	void enterHandlerOpenStatement(MariaDBParser.HandlerOpenStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#handlerOpenStatement}.
	 * @param ctx the parse tree
	 */
	void exitHandlerOpenStatement(MariaDBParser.HandlerOpenStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#handlerReadIndexStatement}.
	 * @param ctx the parse tree
	 */
	void enterHandlerReadIndexStatement(MariaDBParser.HandlerReadIndexStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#handlerReadIndexStatement}.
	 * @param ctx the parse tree
	 */
	void exitHandlerReadIndexStatement(MariaDBParser.HandlerReadIndexStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#handlerReadStatement}.
	 * @param ctx the parse tree
	 */
	void enterHandlerReadStatement(MariaDBParser.HandlerReadStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#handlerReadStatement}.
	 * @param ctx the parse tree
	 */
	void exitHandlerReadStatement(MariaDBParser.HandlerReadStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#handlerCloseStatement}.
	 * @param ctx the parse tree
	 */
	void enterHandlerCloseStatement(MariaDBParser.HandlerCloseStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#handlerCloseStatement}.
	 * @param ctx the parse tree
	 */
	void exitHandlerCloseStatement(MariaDBParser.HandlerCloseStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#singleUpdateStatement}.
	 * @param ctx the parse tree
	 */
	void enterSingleUpdateStatement(MariaDBParser.SingleUpdateStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#singleUpdateStatement}.
	 * @param ctx the parse tree
	 */
	void exitSingleUpdateStatement(MariaDBParser.SingleUpdateStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#multipleUpdateStatement}.
	 * @param ctx the parse tree
	 */
	void enterMultipleUpdateStatement(MariaDBParser.MultipleUpdateStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#multipleUpdateStatement}.
	 * @param ctx the parse tree
	 */
	void exitMultipleUpdateStatement(MariaDBParser.MultipleUpdateStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#orderByClause}.
	 * @param ctx the parse tree
	 */
	void enterOrderByClause(MariaDBParser.OrderByClauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#orderByClause}.
	 * @param ctx the parse tree
	 */
	void exitOrderByClause(MariaDBParser.OrderByClauseContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#orderByExpression}.
	 * @param ctx the parse tree
	 */
	void enterOrderByExpression(MariaDBParser.OrderByExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#orderByExpression}.
	 * @param ctx the parse tree
	 */
	void exitOrderByExpression(MariaDBParser.OrderByExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#tableSources}.
	 * @param ctx the parse tree
	 */
	void enterTableSources(MariaDBParser.TableSourcesContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#tableSources}.
	 * @param ctx the parse tree
	 */
	void exitTableSources(MariaDBParser.TableSourcesContext ctx);
	/**
	 * Enter a parse tree produced by the {@code tableSourceBase}
	 * labeled alternative in {@link MariaDBParser#tableSource}.
	 * @param ctx the parse tree
	 */
	void enterTableSourceBase(MariaDBParser.TableSourceBaseContext ctx);
	/**
	 * Exit a parse tree produced by the {@code tableSourceBase}
	 * labeled alternative in {@link MariaDBParser#tableSource}.
	 * @param ctx the parse tree
	 */
	void exitTableSourceBase(MariaDBParser.TableSourceBaseContext ctx);
	/**
	 * Enter a parse tree produced by the {@code tableSourceNested}
	 * labeled alternative in {@link MariaDBParser#tableSource}.
	 * @param ctx the parse tree
	 */
	void enterTableSourceNested(MariaDBParser.TableSourceNestedContext ctx);
	/**
	 * Exit a parse tree produced by the {@code tableSourceNested}
	 * labeled alternative in {@link MariaDBParser#tableSource}.
	 * @param ctx the parse tree
	 */
	void exitTableSourceNested(MariaDBParser.TableSourceNestedContext ctx);
	/**
	 * Enter a parse tree produced by the {@code tableJson}
	 * labeled alternative in {@link MariaDBParser#tableSource}.
	 * @param ctx the parse tree
	 */
	void enterTableJson(MariaDBParser.TableJsonContext ctx);
	/**
	 * Exit a parse tree produced by the {@code tableJson}
	 * labeled alternative in {@link MariaDBParser#tableSource}.
	 * @param ctx the parse tree
	 */
	void exitTableJson(MariaDBParser.TableJsonContext ctx);
	/**
	 * Enter a parse tree produced by the {@code atomTableItem}
	 * labeled alternative in {@link MariaDBParser#tableSourceItem}.
	 * @param ctx the parse tree
	 */
	void enterAtomTableItem(MariaDBParser.AtomTableItemContext ctx);
	/**
	 * Exit a parse tree produced by the {@code atomTableItem}
	 * labeled alternative in {@link MariaDBParser#tableSourceItem}.
	 * @param ctx the parse tree
	 */
	void exitAtomTableItem(MariaDBParser.AtomTableItemContext ctx);
	/**
	 * Enter a parse tree produced by the {@code subqueryTableItem}
	 * labeled alternative in {@link MariaDBParser#tableSourceItem}.
	 * @param ctx the parse tree
	 */
	void enterSubqueryTableItem(MariaDBParser.SubqueryTableItemContext ctx);
	/**
	 * Exit a parse tree produced by the {@code subqueryTableItem}
	 * labeled alternative in {@link MariaDBParser#tableSourceItem}.
	 * @param ctx the parse tree
	 */
	void exitSubqueryTableItem(MariaDBParser.SubqueryTableItemContext ctx);
	/**
	 * Enter a parse tree produced by the {@code tableSourcesItem}
	 * labeled alternative in {@link MariaDBParser#tableSourceItem}.
	 * @param ctx the parse tree
	 */
	void enterTableSourcesItem(MariaDBParser.TableSourcesItemContext ctx);
	/**
	 * Exit a parse tree produced by the {@code tableSourcesItem}
	 * labeled alternative in {@link MariaDBParser#tableSourceItem}.
	 * @param ctx the parse tree
	 */
	void exitTableSourcesItem(MariaDBParser.TableSourcesItemContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#indexHint}.
	 * @param ctx the parse tree
	 */
	void enterIndexHint(MariaDBParser.IndexHintContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#indexHint}.
	 * @param ctx the parse tree
	 */
	void exitIndexHint(MariaDBParser.IndexHintContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#indexHintType}.
	 * @param ctx the parse tree
	 */
	void enterIndexHintType(MariaDBParser.IndexHintTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#indexHintType}.
	 * @param ctx the parse tree
	 */
	void exitIndexHintType(MariaDBParser.IndexHintTypeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code innerJoin}
	 * labeled alternative in {@link MariaDBParser#joinPart}.
	 * @param ctx the parse tree
	 */
	void enterInnerJoin(MariaDBParser.InnerJoinContext ctx);
	/**
	 * Exit a parse tree produced by the {@code innerJoin}
	 * labeled alternative in {@link MariaDBParser#joinPart}.
	 * @param ctx the parse tree
	 */
	void exitInnerJoin(MariaDBParser.InnerJoinContext ctx);
	/**
	 * Enter a parse tree produced by the {@code straightJoin}
	 * labeled alternative in {@link MariaDBParser#joinPart}.
	 * @param ctx the parse tree
	 */
	void enterStraightJoin(MariaDBParser.StraightJoinContext ctx);
	/**
	 * Exit a parse tree produced by the {@code straightJoin}
	 * labeled alternative in {@link MariaDBParser#joinPart}.
	 * @param ctx the parse tree
	 */
	void exitStraightJoin(MariaDBParser.StraightJoinContext ctx);
	/**
	 * Enter a parse tree produced by the {@code outerJoin}
	 * labeled alternative in {@link MariaDBParser#joinPart}.
	 * @param ctx the parse tree
	 */
	void enterOuterJoin(MariaDBParser.OuterJoinContext ctx);
	/**
	 * Exit a parse tree produced by the {@code outerJoin}
	 * labeled alternative in {@link MariaDBParser#joinPart}.
	 * @param ctx the parse tree
	 */
	void exitOuterJoin(MariaDBParser.OuterJoinContext ctx);
	/**
	 * Enter a parse tree produced by the {@code naturalJoin}
	 * labeled alternative in {@link MariaDBParser#joinPart}.
	 * @param ctx the parse tree
	 */
	void enterNaturalJoin(MariaDBParser.NaturalJoinContext ctx);
	/**
	 * Exit a parse tree produced by the {@code naturalJoin}
	 * labeled alternative in {@link MariaDBParser#joinPart}.
	 * @param ctx the parse tree
	 */
	void exitNaturalJoin(MariaDBParser.NaturalJoinContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#queryExpression}.
	 * @param ctx the parse tree
	 */
	void enterQueryExpression(MariaDBParser.QueryExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#queryExpression}.
	 * @param ctx the parse tree
	 */
	void exitQueryExpression(MariaDBParser.QueryExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#queryExpressionNointo}.
	 * @param ctx the parse tree
	 */
	void enterQueryExpressionNointo(MariaDBParser.QueryExpressionNointoContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#queryExpressionNointo}.
	 * @param ctx the parse tree
	 */
	void exitQueryExpressionNointo(MariaDBParser.QueryExpressionNointoContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#querySpecification}.
	 * @param ctx the parse tree
	 */
	void enterQuerySpecification(MariaDBParser.QuerySpecificationContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#querySpecification}.
	 * @param ctx the parse tree
	 */
	void exitQuerySpecification(MariaDBParser.QuerySpecificationContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#querySpecificationNointo}.
	 * @param ctx the parse tree
	 */
	void enterQuerySpecificationNointo(MariaDBParser.QuerySpecificationNointoContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#querySpecificationNointo}.
	 * @param ctx the parse tree
	 */
	void exitQuerySpecificationNointo(MariaDBParser.QuerySpecificationNointoContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#unionParenthesis}.
	 * @param ctx the parse tree
	 */
	void enterUnionParenthesis(MariaDBParser.UnionParenthesisContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#unionParenthesis}.
	 * @param ctx the parse tree
	 */
	void exitUnionParenthesis(MariaDBParser.UnionParenthesisContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#unionStatement}.
	 * @param ctx the parse tree
	 */
	void enterUnionStatement(MariaDBParser.UnionStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#unionStatement}.
	 * @param ctx the parse tree
	 */
	void exitUnionStatement(MariaDBParser.UnionStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#lateralStatement}.
	 * @param ctx the parse tree
	 */
	void enterLateralStatement(MariaDBParser.LateralStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#lateralStatement}.
	 * @param ctx the parse tree
	 */
	void exitLateralStatement(MariaDBParser.LateralStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#jsonTable}.
	 * @param ctx the parse tree
	 */
	void enterJsonTable(MariaDBParser.JsonTableContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#jsonTable}.
	 * @param ctx the parse tree
	 */
	void exitJsonTable(MariaDBParser.JsonTableContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#jsonColumnList}.
	 * @param ctx the parse tree
	 */
	void enterJsonColumnList(MariaDBParser.JsonColumnListContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#jsonColumnList}.
	 * @param ctx the parse tree
	 */
	void exitJsonColumnList(MariaDBParser.JsonColumnListContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#jsonColumn}.
	 * @param ctx the parse tree
	 */
	void enterJsonColumn(MariaDBParser.JsonColumnContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#jsonColumn}.
	 * @param ctx the parse tree
	 */
	void exitJsonColumn(MariaDBParser.JsonColumnContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#jsonOnEmpty}.
	 * @param ctx the parse tree
	 */
	void enterJsonOnEmpty(MariaDBParser.JsonOnEmptyContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#jsonOnEmpty}.
	 * @param ctx the parse tree
	 */
	void exitJsonOnEmpty(MariaDBParser.JsonOnEmptyContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#jsonOnError}.
	 * @param ctx the parse tree
	 */
	void enterJsonOnError(MariaDBParser.JsonOnErrorContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#jsonOnError}.
	 * @param ctx the parse tree
	 */
	void exitJsonOnError(MariaDBParser.JsonOnErrorContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#selectSpec}.
	 * @param ctx the parse tree
	 */
	void enterSelectSpec(MariaDBParser.SelectSpecContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#selectSpec}.
	 * @param ctx the parse tree
	 */
	void exitSelectSpec(MariaDBParser.SelectSpecContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#selectElements}.
	 * @param ctx the parse tree
	 */
	void enterSelectElements(MariaDBParser.SelectElementsContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#selectElements}.
	 * @param ctx the parse tree
	 */
	void exitSelectElements(MariaDBParser.SelectElementsContext ctx);
	/**
	 * Enter a parse tree produced by the {@code selectStarElement}
	 * labeled alternative in {@link MariaDBParser#selectElement}.
	 * @param ctx the parse tree
	 */
	void enterSelectStarElement(MariaDBParser.SelectStarElementContext ctx);
	/**
	 * Exit a parse tree produced by the {@code selectStarElement}
	 * labeled alternative in {@link MariaDBParser#selectElement}.
	 * @param ctx the parse tree
	 */
	void exitSelectStarElement(MariaDBParser.SelectStarElementContext ctx);
	/**
	 * Enter a parse tree produced by the {@code selectColumnElement}
	 * labeled alternative in {@link MariaDBParser#selectElement}.
	 * @param ctx the parse tree
	 */
	void enterSelectColumnElement(MariaDBParser.SelectColumnElementContext ctx);
	/**
	 * Exit a parse tree produced by the {@code selectColumnElement}
	 * labeled alternative in {@link MariaDBParser#selectElement}.
	 * @param ctx the parse tree
	 */
	void exitSelectColumnElement(MariaDBParser.SelectColumnElementContext ctx);
	/**
	 * Enter a parse tree produced by the {@code selectFunctionElement}
	 * labeled alternative in {@link MariaDBParser#selectElement}.
	 * @param ctx the parse tree
	 */
	void enterSelectFunctionElement(MariaDBParser.SelectFunctionElementContext ctx);
	/**
	 * Exit a parse tree produced by the {@code selectFunctionElement}
	 * labeled alternative in {@link MariaDBParser#selectElement}.
	 * @param ctx the parse tree
	 */
	void exitSelectFunctionElement(MariaDBParser.SelectFunctionElementContext ctx);
	/**
	 * Enter a parse tree produced by the {@code selectExpressionElement}
	 * labeled alternative in {@link MariaDBParser#selectElement}.
	 * @param ctx the parse tree
	 */
	void enterSelectExpressionElement(MariaDBParser.SelectExpressionElementContext ctx);
	/**
	 * Exit a parse tree produced by the {@code selectExpressionElement}
	 * labeled alternative in {@link MariaDBParser#selectElement}.
	 * @param ctx the parse tree
	 */
	void exitSelectExpressionElement(MariaDBParser.SelectExpressionElementContext ctx);
	/**
	 * Enter a parse tree produced by the {@code selectIntoVariables}
	 * labeled alternative in {@link MariaDBParser#selectIntoExpression}.
	 * @param ctx the parse tree
	 */
	void enterSelectIntoVariables(MariaDBParser.SelectIntoVariablesContext ctx);
	/**
	 * Exit a parse tree produced by the {@code selectIntoVariables}
	 * labeled alternative in {@link MariaDBParser#selectIntoExpression}.
	 * @param ctx the parse tree
	 */
	void exitSelectIntoVariables(MariaDBParser.SelectIntoVariablesContext ctx);
	/**
	 * Enter a parse tree produced by the {@code selectIntoDumpFile}
	 * labeled alternative in {@link MariaDBParser#selectIntoExpression}.
	 * @param ctx the parse tree
	 */
	void enterSelectIntoDumpFile(MariaDBParser.SelectIntoDumpFileContext ctx);
	/**
	 * Exit a parse tree produced by the {@code selectIntoDumpFile}
	 * labeled alternative in {@link MariaDBParser#selectIntoExpression}.
	 * @param ctx the parse tree
	 */
	void exitSelectIntoDumpFile(MariaDBParser.SelectIntoDumpFileContext ctx);
	/**
	 * Enter a parse tree produced by the {@code selectIntoTextFile}
	 * labeled alternative in {@link MariaDBParser#selectIntoExpression}.
	 * @param ctx the parse tree
	 */
	void enterSelectIntoTextFile(MariaDBParser.SelectIntoTextFileContext ctx);
	/**
	 * Exit a parse tree produced by the {@code selectIntoTextFile}
	 * labeled alternative in {@link MariaDBParser#selectIntoExpression}.
	 * @param ctx the parse tree
	 */
	void exitSelectIntoTextFile(MariaDBParser.SelectIntoTextFileContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#selectFieldsInto}.
	 * @param ctx the parse tree
	 */
	void enterSelectFieldsInto(MariaDBParser.SelectFieldsIntoContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#selectFieldsInto}.
	 * @param ctx the parse tree
	 */
	void exitSelectFieldsInto(MariaDBParser.SelectFieldsIntoContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#selectLinesInto}.
	 * @param ctx the parse tree
	 */
	void enterSelectLinesInto(MariaDBParser.SelectLinesIntoContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#selectLinesInto}.
	 * @param ctx the parse tree
	 */
	void exitSelectLinesInto(MariaDBParser.SelectLinesIntoContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#fromClause}.
	 * @param ctx the parse tree
	 */
	void enterFromClause(MariaDBParser.FromClauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#fromClause}.
	 * @param ctx the parse tree
	 */
	void exitFromClause(MariaDBParser.FromClauseContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#groupByClause}.
	 * @param ctx the parse tree
	 */
	void enterGroupByClause(MariaDBParser.GroupByClauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#groupByClause}.
	 * @param ctx the parse tree
	 */
	void exitGroupByClause(MariaDBParser.GroupByClauseContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#havingClause}.
	 * @param ctx the parse tree
	 */
	void enterHavingClause(MariaDBParser.HavingClauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#havingClause}.
	 * @param ctx the parse tree
	 */
	void exitHavingClause(MariaDBParser.HavingClauseContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#windowClause}.
	 * @param ctx the parse tree
	 */
	void enterWindowClause(MariaDBParser.WindowClauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#windowClause}.
	 * @param ctx the parse tree
	 */
	void exitWindowClause(MariaDBParser.WindowClauseContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#groupByItem}.
	 * @param ctx the parse tree
	 */
	void enterGroupByItem(MariaDBParser.GroupByItemContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#groupByItem}.
	 * @param ctx the parse tree
	 */
	void exitGroupByItem(MariaDBParser.GroupByItemContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#limitClause}.
	 * @param ctx the parse tree
	 */
	void enterLimitClause(MariaDBParser.LimitClauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#limitClause}.
	 * @param ctx the parse tree
	 */
	void exitLimitClause(MariaDBParser.LimitClauseContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#limitClauseAtom}.
	 * @param ctx the parse tree
	 */
	void enterLimitClauseAtom(MariaDBParser.LimitClauseAtomContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#limitClauseAtom}.
	 * @param ctx the parse tree
	 */
	void exitLimitClauseAtom(MariaDBParser.LimitClauseAtomContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#startTransaction}.
	 * @param ctx the parse tree
	 */
	void enterStartTransaction(MariaDBParser.StartTransactionContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#startTransaction}.
	 * @param ctx the parse tree
	 */
	void exitStartTransaction(MariaDBParser.StartTransactionContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#beginWork}.
	 * @param ctx the parse tree
	 */
	void enterBeginWork(MariaDBParser.BeginWorkContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#beginWork}.
	 * @param ctx the parse tree
	 */
	void exitBeginWork(MariaDBParser.BeginWorkContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#commitWork}.
	 * @param ctx the parse tree
	 */
	void enterCommitWork(MariaDBParser.CommitWorkContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#commitWork}.
	 * @param ctx the parse tree
	 */
	void exitCommitWork(MariaDBParser.CommitWorkContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#rollbackWork}.
	 * @param ctx the parse tree
	 */
	void enterRollbackWork(MariaDBParser.RollbackWorkContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#rollbackWork}.
	 * @param ctx the parse tree
	 */
	void exitRollbackWork(MariaDBParser.RollbackWorkContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#savepointStatement}.
	 * @param ctx the parse tree
	 */
	void enterSavepointStatement(MariaDBParser.SavepointStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#savepointStatement}.
	 * @param ctx the parse tree
	 */
	void exitSavepointStatement(MariaDBParser.SavepointStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#rollbackStatement}.
	 * @param ctx the parse tree
	 */
	void enterRollbackStatement(MariaDBParser.RollbackStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#rollbackStatement}.
	 * @param ctx the parse tree
	 */
	void exitRollbackStatement(MariaDBParser.RollbackStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#releaseStatement}.
	 * @param ctx the parse tree
	 */
	void enterReleaseStatement(MariaDBParser.ReleaseStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#releaseStatement}.
	 * @param ctx the parse tree
	 */
	void exitReleaseStatement(MariaDBParser.ReleaseStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#lockTables}.
	 * @param ctx the parse tree
	 */
	void enterLockTables(MariaDBParser.LockTablesContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#lockTables}.
	 * @param ctx the parse tree
	 */
	void exitLockTables(MariaDBParser.LockTablesContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#unlockTables}.
	 * @param ctx the parse tree
	 */
	void enterUnlockTables(MariaDBParser.UnlockTablesContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#unlockTables}.
	 * @param ctx the parse tree
	 */
	void exitUnlockTables(MariaDBParser.UnlockTablesContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#setAutocommitStatement}.
	 * @param ctx the parse tree
	 */
	void enterSetAutocommitStatement(MariaDBParser.SetAutocommitStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#setAutocommitStatement}.
	 * @param ctx the parse tree
	 */
	void exitSetAutocommitStatement(MariaDBParser.SetAutocommitStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#setTransactionStatement}.
	 * @param ctx the parse tree
	 */
	void enterSetTransactionStatement(MariaDBParser.SetTransactionStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#setTransactionStatement}.
	 * @param ctx the parse tree
	 */
	void exitSetTransactionStatement(MariaDBParser.SetTransactionStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#transactionMode}.
	 * @param ctx the parse tree
	 */
	void enterTransactionMode(MariaDBParser.TransactionModeContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#transactionMode}.
	 * @param ctx the parse tree
	 */
	void exitTransactionMode(MariaDBParser.TransactionModeContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#lockTableElement}.
	 * @param ctx the parse tree
	 */
	void enterLockTableElement(MariaDBParser.LockTableElementContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#lockTableElement}.
	 * @param ctx the parse tree
	 */
	void exitLockTableElement(MariaDBParser.LockTableElementContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#lockAction}.
	 * @param ctx the parse tree
	 */
	void enterLockAction(MariaDBParser.LockActionContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#lockAction}.
	 * @param ctx the parse tree
	 */
	void exitLockAction(MariaDBParser.LockActionContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#transactionOption}.
	 * @param ctx the parse tree
	 */
	void enterTransactionOption(MariaDBParser.TransactionOptionContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#transactionOption}.
	 * @param ctx the parse tree
	 */
	void exitTransactionOption(MariaDBParser.TransactionOptionContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#transactionLevel}.
	 * @param ctx the parse tree
	 */
	void enterTransactionLevel(MariaDBParser.TransactionLevelContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#transactionLevel}.
	 * @param ctx the parse tree
	 */
	void exitTransactionLevel(MariaDBParser.TransactionLevelContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#changeMaster}.
	 * @param ctx the parse tree
	 */
	void enterChangeMaster(MariaDBParser.ChangeMasterContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#changeMaster}.
	 * @param ctx the parse tree
	 */
	void exitChangeMaster(MariaDBParser.ChangeMasterContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#changeReplicationFilter}.
	 * @param ctx the parse tree
	 */
	void enterChangeReplicationFilter(MariaDBParser.ChangeReplicationFilterContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#changeReplicationFilter}.
	 * @param ctx the parse tree
	 */
	void exitChangeReplicationFilter(MariaDBParser.ChangeReplicationFilterContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#purgeBinaryLogs}.
	 * @param ctx the parse tree
	 */
	void enterPurgeBinaryLogs(MariaDBParser.PurgeBinaryLogsContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#purgeBinaryLogs}.
	 * @param ctx the parse tree
	 */
	void exitPurgeBinaryLogs(MariaDBParser.PurgeBinaryLogsContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#resetMaster}.
	 * @param ctx the parse tree
	 */
	void enterResetMaster(MariaDBParser.ResetMasterContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#resetMaster}.
	 * @param ctx the parse tree
	 */
	void exitResetMaster(MariaDBParser.ResetMasterContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#resetSlave}.
	 * @param ctx the parse tree
	 */
	void enterResetSlave(MariaDBParser.ResetSlaveContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#resetSlave}.
	 * @param ctx the parse tree
	 */
	void exitResetSlave(MariaDBParser.ResetSlaveContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#startSlave}.
	 * @param ctx the parse tree
	 */
	void enterStartSlave(MariaDBParser.StartSlaveContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#startSlave}.
	 * @param ctx the parse tree
	 */
	void exitStartSlave(MariaDBParser.StartSlaveContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#stopSlave}.
	 * @param ctx the parse tree
	 */
	void enterStopSlave(MariaDBParser.StopSlaveContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#stopSlave}.
	 * @param ctx the parse tree
	 */
	void exitStopSlave(MariaDBParser.StopSlaveContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#startGroupReplication}.
	 * @param ctx the parse tree
	 */
	void enterStartGroupReplication(MariaDBParser.StartGroupReplicationContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#startGroupReplication}.
	 * @param ctx the parse tree
	 */
	void exitStartGroupReplication(MariaDBParser.StartGroupReplicationContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#stopGroupReplication}.
	 * @param ctx the parse tree
	 */
	void enterStopGroupReplication(MariaDBParser.StopGroupReplicationContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#stopGroupReplication}.
	 * @param ctx the parse tree
	 */
	void exitStopGroupReplication(MariaDBParser.StopGroupReplicationContext ctx);
	/**
	 * Enter a parse tree produced by the {@code masterStringOption}
	 * labeled alternative in {@link MariaDBParser#masterOption}.
	 * @param ctx the parse tree
	 */
	void enterMasterStringOption(MariaDBParser.MasterStringOptionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code masterStringOption}
	 * labeled alternative in {@link MariaDBParser#masterOption}.
	 * @param ctx the parse tree
	 */
	void exitMasterStringOption(MariaDBParser.MasterStringOptionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code masterDecimalOption}
	 * labeled alternative in {@link MariaDBParser#masterOption}.
	 * @param ctx the parse tree
	 */
	void enterMasterDecimalOption(MariaDBParser.MasterDecimalOptionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code masterDecimalOption}
	 * labeled alternative in {@link MariaDBParser#masterOption}.
	 * @param ctx the parse tree
	 */
	void exitMasterDecimalOption(MariaDBParser.MasterDecimalOptionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code masterBoolOption}
	 * labeled alternative in {@link MariaDBParser#masterOption}.
	 * @param ctx the parse tree
	 */
	void enterMasterBoolOption(MariaDBParser.MasterBoolOptionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code masterBoolOption}
	 * labeled alternative in {@link MariaDBParser#masterOption}.
	 * @param ctx the parse tree
	 */
	void exitMasterBoolOption(MariaDBParser.MasterBoolOptionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code masterRealOption}
	 * labeled alternative in {@link MariaDBParser#masterOption}.
	 * @param ctx the parse tree
	 */
	void enterMasterRealOption(MariaDBParser.MasterRealOptionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code masterRealOption}
	 * labeled alternative in {@link MariaDBParser#masterOption}.
	 * @param ctx the parse tree
	 */
	void exitMasterRealOption(MariaDBParser.MasterRealOptionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code masterUidListOption}
	 * labeled alternative in {@link MariaDBParser#masterOption}.
	 * @param ctx the parse tree
	 */
	void enterMasterUidListOption(MariaDBParser.MasterUidListOptionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code masterUidListOption}
	 * labeled alternative in {@link MariaDBParser#masterOption}.
	 * @param ctx the parse tree
	 */
	void exitMasterUidListOption(MariaDBParser.MasterUidListOptionContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#stringMasterOption}.
	 * @param ctx the parse tree
	 */
	void enterStringMasterOption(MariaDBParser.StringMasterOptionContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#stringMasterOption}.
	 * @param ctx the parse tree
	 */
	void exitStringMasterOption(MariaDBParser.StringMasterOptionContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#decimalMasterOption}.
	 * @param ctx the parse tree
	 */
	void enterDecimalMasterOption(MariaDBParser.DecimalMasterOptionContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#decimalMasterOption}.
	 * @param ctx the parse tree
	 */
	void exitDecimalMasterOption(MariaDBParser.DecimalMasterOptionContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#boolMasterOption}.
	 * @param ctx the parse tree
	 */
	void enterBoolMasterOption(MariaDBParser.BoolMasterOptionContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#boolMasterOption}.
	 * @param ctx the parse tree
	 */
	void exitBoolMasterOption(MariaDBParser.BoolMasterOptionContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#channelOption}.
	 * @param ctx the parse tree
	 */
	void enterChannelOption(MariaDBParser.ChannelOptionContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#channelOption}.
	 * @param ctx the parse tree
	 */
	void exitChannelOption(MariaDBParser.ChannelOptionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code doDbReplication}
	 * labeled alternative in {@link MariaDBParser#replicationFilter}.
	 * @param ctx the parse tree
	 */
	void enterDoDbReplication(MariaDBParser.DoDbReplicationContext ctx);
	/**
	 * Exit a parse tree produced by the {@code doDbReplication}
	 * labeled alternative in {@link MariaDBParser#replicationFilter}.
	 * @param ctx the parse tree
	 */
	void exitDoDbReplication(MariaDBParser.DoDbReplicationContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ignoreDbReplication}
	 * labeled alternative in {@link MariaDBParser#replicationFilter}.
	 * @param ctx the parse tree
	 */
	void enterIgnoreDbReplication(MariaDBParser.IgnoreDbReplicationContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ignoreDbReplication}
	 * labeled alternative in {@link MariaDBParser#replicationFilter}.
	 * @param ctx the parse tree
	 */
	void exitIgnoreDbReplication(MariaDBParser.IgnoreDbReplicationContext ctx);
	/**
	 * Enter a parse tree produced by the {@code doTableReplication}
	 * labeled alternative in {@link MariaDBParser#replicationFilter}.
	 * @param ctx the parse tree
	 */
	void enterDoTableReplication(MariaDBParser.DoTableReplicationContext ctx);
	/**
	 * Exit a parse tree produced by the {@code doTableReplication}
	 * labeled alternative in {@link MariaDBParser#replicationFilter}.
	 * @param ctx the parse tree
	 */
	void exitDoTableReplication(MariaDBParser.DoTableReplicationContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ignoreTableReplication}
	 * labeled alternative in {@link MariaDBParser#replicationFilter}.
	 * @param ctx the parse tree
	 */
	void enterIgnoreTableReplication(MariaDBParser.IgnoreTableReplicationContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ignoreTableReplication}
	 * labeled alternative in {@link MariaDBParser#replicationFilter}.
	 * @param ctx the parse tree
	 */
	void exitIgnoreTableReplication(MariaDBParser.IgnoreTableReplicationContext ctx);
	/**
	 * Enter a parse tree produced by the {@code wildDoTableReplication}
	 * labeled alternative in {@link MariaDBParser#replicationFilter}.
	 * @param ctx the parse tree
	 */
	void enterWildDoTableReplication(MariaDBParser.WildDoTableReplicationContext ctx);
	/**
	 * Exit a parse tree produced by the {@code wildDoTableReplication}
	 * labeled alternative in {@link MariaDBParser#replicationFilter}.
	 * @param ctx the parse tree
	 */
	void exitWildDoTableReplication(MariaDBParser.WildDoTableReplicationContext ctx);
	/**
	 * Enter a parse tree produced by the {@code wildIgnoreTableReplication}
	 * labeled alternative in {@link MariaDBParser#replicationFilter}.
	 * @param ctx the parse tree
	 */
	void enterWildIgnoreTableReplication(MariaDBParser.WildIgnoreTableReplicationContext ctx);
	/**
	 * Exit a parse tree produced by the {@code wildIgnoreTableReplication}
	 * labeled alternative in {@link MariaDBParser#replicationFilter}.
	 * @param ctx the parse tree
	 */
	void exitWildIgnoreTableReplication(MariaDBParser.WildIgnoreTableReplicationContext ctx);
	/**
	 * Enter a parse tree produced by the {@code rewriteDbReplication}
	 * labeled alternative in {@link MariaDBParser#replicationFilter}.
	 * @param ctx the parse tree
	 */
	void enterRewriteDbReplication(MariaDBParser.RewriteDbReplicationContext ctx);
	/**
	 * Exit a parse tree produced by the {@code rewriteDbReplication}
	 * labeled alternative in {@link MariaDBParser#replicationFilter}.
	 * @param ctx the parse tree
	 */
	void exitRewriteDbReplication(MariaDBParser.RewriteDbReplicationContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#tablePair}.
	 * @param ctx the parse tree
	 */
	void enterTablePair(MariaDBParser.TablePairContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#tablePair}.
	 * @param ctx the parse tree
	 */
	void exitTablePair(MariaDBParser.TablePairContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#threadType}.
	 * @param ctx the parse tree
	 */
	void enterThreadType(MariaDBParser.ThreadTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#threadType}.
	 * @param ctx the parse tree
	 */
	void exitThreadType(MariaDBParser.ThreadTypeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code gtidsUntilOption}
	 * labeled alternative in {@link MariaDBParser#untilOption}.
	 * @param ctx the parse tree
	 */
	void enterGtidsUntilOption(MariaDBParser.GtidsUntilOptionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code gtidsUntilOption}
	 * labeled alternative in {@link MariaDBParser#untilOption}.
	 * @param ctx the parse tree
	 */
	void exitGtidsUntilOption(MariaDBParser.GtidsUntilOptionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code masterLogUntilOption}
	 * labeled alternative in {@link MariaDBParser#untilOption}.
	 * @param ctx the parse tree
	 */
	void enterMasterLogUntilOption(MariaDBParser.MasterLogUntilOptionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code masterLogUntilOption}
	 * labeled alternative in {@link MariaDBParser#untilOption}.
	 * @param ctx the parse tree
	 */
	void exitMasterLogUntilOption(MariaDBParser.MasterLogUntilOptionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code relayLogUntilOption}
	 * labeled alternative in {@link MariaDBParser#untilOption}.
	 * @param ctx the parse tree
	 */
	void enterRelayLogUntilOption(MariaDBParser.RelayLogUntilOptionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code relayLogUntilOption}
	 * labeled alternative in {@link MariaDBParser#untilOption}.
	 * @param ctx the parse tree
	 */
	void exitRelayLogUntilOption(MariaDBParser.RelayLogUntilOptionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code sqlGapsUntilOption}
	 * labeled alternative in {@link MariaDBParser#untilOption}.
	 * @param ctx the parse tree
	 */
	void enterSqlGapsUntilOption(MariaDBParser.SqlGapsUntilOptionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code sqlGapsUntilOption}
	 * labeled alternative in {@link MariaDBParser#untilOption}.
	 * @param ctx the parse tree
	 */
	void exitSqlGapsUntilOption(MariaDBParser.SqlGapsUntilOptionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code userConnectionOption}
	 * labeled alternative in {@link MariaDBParser#connectionOption}.
	 * @param ctx the parse tree
	 */
	void enterUserConnectionOption(MariaDBParser.UserConnectionOptionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code userConnectionOption}
	 * labeled alternative in {@link MariaDBParser#connectionOption}.
	 * @param ctx the parse tree
	 */
	void exitUserConnectionOption(MariaDBParser.UserConnectionOptionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code passwordConnectionOption}
	 * labeled alternative in {@link MariaDBParser#connectionOption}.
	 * @param ctx the parse tree
	 */
	void enterPasswordConnectionOption(MariaDBParser.PasswordConnectionOptionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code passwordConnectionOption}
	 * labeled alternative in {@link MariaDBParser#connectionOption}.
	 * @param ctx the parse tree
	 */
	void exitPasswordConnectionOption(MariaDBParser.PasswordConnectionOptionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code defaultAuthConnectionOption}
	 * labeled alternative in {@link MariaDBParser#connectionOption}.
	 * @param ctx the parse tree
	 */
	void enterDefaultAuthConnectionOption(MariaDBParser.DefaultAuthConnectionOptionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code defaultAuthConnectionOption}
	 * labeled alternative in {@link MariaDBParser#connectionOption}.
	 * @param ctx the parse tree
	 */
	void exitDefaultAuthConnectionOption(MariaDBParser.DefaultAuthConnectionOptionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code pluginDirConnectionOption}
	 * labeled alternative in {@link MariaDBParser#connectionOption}.
	 * @param ctx the parse tree
	 */
	void enterPluginDirConnectionOption(MariaDBParser.PluginDirConnectionOptionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code pluginDirConnectionOption}
	 * labeled alternative in {@link MariaDBParser#connectionOption}.
	 * @param ctx the parse tree
	 */
	void exitPluginDirConnectionOption(MariaDBParser.PluginDirConnectionOptionContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#gtuidSet}.
	 * @param ctx the parse tree
	 */
	void enterGtuidSet(MariaDBParser.GtuidSetContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#gtuidSet}.
	 * @param ctx the parse tree
	 */
	void exitGtuidSet(MariaDBParser.GtuidSetContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#xaStartTransaction}.
	 * @param ctx the parse tree
	 */
	void enterXaStartTransaction(MariaDBParser.XaStartTransactionContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#xaStartTransaction}.
	 * @param ctx the parse tree
	 */
	void exitXaStartTransaction(MariaDBParser.XaStartTransactionContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#xaEndTransaction}.
	 * @param ctx the parse tree
	 */
	void enterXaEndTransaction(MariaDBParser.XaEndTransactionContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#xaEndTransaction}.
	 * @param ctx the parse tree
	 */
	void exitXaEndTransaction(MariaDBParser.XaEndTransactionContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#xaPrepareStatement}.
	 * @param ctx the parse tree
	 */
	void enterXaPrepareStatement(MariaDBParser.XaPrepareStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#xaPrepareStatement}.
	 * @param ctx the parse tree
	 */
	void exitXaPrepareStatement(MariaDBParser.XaPrepareStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#xaCommitWork}.
	 * @param ctx the parse tree
	 */
	void enterXaCommitWork(MariaDBParser.XaCommitWorkContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#xaCommitWork}.
	 * @param ctx the parse tree
	 */
	void exitXaCommitWork(MariaDBParser.XaCommitWorkContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#xaRollbackWork}.
	 * @param ctx the parse tree
	 */
	void enterXaRollbackWork(MariaDBParser.XaRollbackWorkContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#xaRollbackWork}.
	 * @param ctx the parse tree
	 */
	void exitXaRollbackWork(MariaDBParser.XaRollbackWorkContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#xaRecoverWork}.
	 * @param ctx the parse tree
	 */
	void enterXaRecoverWork(MariaDBParser.XaRecoverWorkContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#xaRecoverWork}.
	 * @param ctx the parse tree
	 */
	void exitXaRecoverWork(MariaDBParser.XaRecoverWorkContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#prepareStatement}.
	 * @param ctx the parse tree
	 */
	void enterPrepareStatement(MariaDBParser.PrepareStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#prepareStatement}.
	 * @param ctx the parse tree
	 */
	void exitPrepareStatement(MariaDBParser.PrepareStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#executeStatement}.
	 * @param ctx the parse tree
	 */
	void enterExecuteStatement(MariaDBParser.ExecuteStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#executeStatement}.
	 * @param ctx the parse tree
	 */
	void exitExecuteStatement(MariaDBParser.ExecuteStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#deallocatePrepare}.
	 * @param ctx the parse tree
	 */
	void enterDeallocatePrepare(MariaDBParser.DeallocatePrepareContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#deallocatePrepare}.
	 * @param ctx the parse tree
	 */
	void exitDeallocatePrepare(MariaDBParser.DeallocatePrepareContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#routineBody}.
	 * @param ctx the parse tree
	 */
	void enterRoutineBody(MariaDBParser.RoutineBodyContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#routineBody}.
	 * @param ctx the parse tree
	 */
	void exitRoutineBody(MariaDBParser.RoutineBodyContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#blockStatement}.
	 * @param ctx the parse tree
	 */
	void enterBlockStatement(MariaDBParser.BlockStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#blockStatement}.
	 * @param ctx the parse tree
	 */
	void exitBlockStatement(MariaDBParser.BlockStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#caseStatement}.
	 * @param ctx the parse tree
	 */
	void enterCaseStatement(MariaDBParser.CaseStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#caseStatement}.
	 * @param ctx the parse tree
	 */
	void exitCaseStatement(MariaDBParser.CaseStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#ifStatement}.
	 * @param ctx the parse tree
	 */
	void enterIfStatement(MariaDBParser.IfStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#ifStatement}.
	 * @param ctx the parse tree
	 */
	void exitIfStatement(MariaDBParser.IfStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#iterateStatement}.
	 * @param ctx the parse tree
	 */
	void enterIterateStatement(MariaDBParser.IterateStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#iterateStatement}.
	 * @param ctx the parse tree
	 */
	void exitIterateStatement(MariaDBParser.IterateStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#leaveStatement}.
	 * @param ctx the parse tree
	 */
	void enterLeaveStatement(MariaDBParser.LeaveStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#leaveStatement}.
	 * @param ctx the parse tree
	 */
	void exitLeaveStatement(MariaDBParser.LeaveStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#loopStatement}.
	 * @param ctx the parse tree
	 */
	void enterLoopStatement(MariaDBParser.LoopStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#loopStatement}.
	 * @param ctx the parse tree
	 */
	void exitLoopStatement(MariaDBParser.LoopStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#repeatStatement}.
	 * @param ctx the parse tree
	 */
	void enterRepeatStatement(MariaDBParser.RepeatStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#repeatStatement}.
	 * @param ctx the parse tree
	 */
	void exitRepeatStatement(MariaDBParser.RepeatStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#returnStatement}.
	 * @param ctx the parse tree
	 */
	void enterReturnStatement(MariaDBParser.ReturnStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#returnStatement}.
	 * @param ctx the parse tree
	 */
	void exitReturnStatement(MariaDBParser.ReturnStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#whileStatement}.
	 * @param ctx the parse tree
	 */
	void enterWhileStatement(MariaDBParser.WhileStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#whileStatement}.
	 * @param ctx the parse tree
	 */
	void exitWhileStatement(MariaDBParser.WhileStatementContext ctx);
	/**
	 * Enter a parse tree produced by the {@code CloseCursor}
	 * labeled alternative in {@link MariaDBParser#cursorStatement}.
	 * @param ctx the parse tree
	 */
	void enterCloseCursor(MariaDBParser.CloseCursorContext ctx);
	/**
	 * Exit a parse tree produced by the {@code CloseCursor}
	 * labeled alternative in {@link MariaDBParser#cursorStatement}.
	 * @param ctx the parse tree
	 */
	void exitCloseCursor(MariaDBParser.CloseCursorContext ctx);
	/**
	 * Enter a parse tree produced by the {@code FetchCursor}
	 * labeled alternative in {@link MariaDBParser#cursorStatement}.
	 * @param ctx the parse tree
	 */
	void enterFetchCursor(MariaDBParser.FetchCursorContext ctx);
	/**
	 * Exit a parse tree produced by the {@code FetchCursor}
	 * labeled alternative in {@link MariaDBParser#cursorStatement}.
	 * @param ctx the parse tree
	 */
	void exitFetchCursor(MariaDBParser.FetchCursorContext ctx);
	/**
	 * Enter a parse tree produced by the {@code OpenCursor}
	 * labeled alternative in {@link MariaDBParser#cursorStatement}.
	 * @param ctx the parse tree
	 */
	void enterOpenCursor(MariaDBParser.OpenCursorContext ctx);
	/**
	 * Exit a parse tree produced by the {@code OpenCursor}
	 * labeled alternative in {@link MariaDBParser#cursorStatement}.
	 * @param ctx the parse tree
	 */
	void exitOpenCursor(MariaDBParser.OpenCursorContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#declareVariable}.
	 * @param ctx the parse tree
	 */
	void enterDeclareVariable(MariaDBParser.DeclareVariableContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#declareVariable}.
	 * @param ctx the parse tree
	 */
	void exitDeclareVariable(MariaDBParser.DeclareVariableContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#declareCondition}.
	 * @param ctx the parse tree
	 */
	void enterDeclareCondition(MariaDBParser.DeclareConditionContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#declareCondition}.
	 * @param ctx the parse tree
	 */
	void exitDeclareCondition(MariaDBParser.DeclareConditionContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#declareCursor}.
	 * @param ctx the parse tree
	 */
	void enterDeclareCursor(MariaDBParser.DeclareCursorContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#declareCursor}.
	 * @param ctx the parse tree
	 */
	void exitDeclareCursor(MariaDBParser.DeclareCursorContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#declareHandler}.
	 * @param ctx the parse tree
	 */
	void enterDeclareHandler(MariaDBParser.DeclareHandlerContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#declareHandler}.
	 * @param ctx the parse tree
	 */
	void exitDeclareHandler(MariaDBParser.DeclareHandlerContext ctx);
	/**
	 * Enter a parse tree produced by the {@code handlerConditionCode}
	 * labeled alternative in {@link MariaDBParser#handlerConditionValue}.
	 * @param ctx the parse tree
	 */
	void enterHandlerConditionCode(MariaDBParser.HandlerConditionCodeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code handlerConditionCode}
	 * labeled alternative in {@link MariaDBParser#handlerConditionValue}.
	 * @param ctx the parse tree
	 */
	void exitHandlerConditionCode(MariaDBParser.HandlerConditionCodeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code handlerConditionState}
	 * labeled alternative in {@link MariaDBParser#handlerConditionValue}.
	 * @param ctx the parse tree
	 */
	void enterHandlerConditionState(MariaDBParser.HandlerConditionStateContext ctx);
	/**
	 * Exit a parse tree produced by the {@code handlerConditionState}
	 * labeled alternative in {@link MariaDBParser#handlerConditionValue}.
	 * @param ctx the parse tree
	 */
	void exitHandlerConditionState(MariaDBParser.HandlerConditionStateContext ctx);
	/**
	 * Enter a parse tree produced by the {@code handlerConditionName}
	 * labeled alternative in {@link MariaDBParser#handlerConditionValue}.
	 * @param ctx the parse tree
	 */
	void enterHandlerConditionName(MariaDBParser.HandlerConditionNameContext ctx);
	/**
	 * Exit a parse tree produced by the {@code handlerConditionName}
	 * labeled alternative in {@link MariaDBParser#handlerConditionValue}.
	 * @param ctx the parse tree
	 */
	void exitHandlerConditionName(MariaDBParser.HandlerConditionNameContext ctx);
	/**
	 * Enter a parse tree produced by the {@code handlerConditionWarning}
	 * labeled alternative in {@link MariaDBParser#handlerConditionValue}.
	 * @param ctx the parse tree
	 */
	void enterHandlerConditionWarning(MariaDBParser.HandlerConditionWarningContext ctx);
	/**
	 * Exit a parse tree produced by the {@code handlerConditionWarning}
	 * labeled alternative in {@link MariaDBParser#handlerConditionValue}.
	 * @param ctx the parse tree
	 */
	void exitHandlerConditionWarning(MariaDBParser.HandlerConditionWarningContext ctx);
	/**
	 * Enter a parse tree produced by the {@code handlerConditionNotfound}
	 * labeled alternative in {@link MariaDBParser#handlerConditionValue}.
	 * @param ctx the parse tree
	 */
	void enterHandlerConditionNotfound(MariaDBParser.HandlerConditionNotfoundContext ctx);
	/**
	 * Exit a parse tree produced by the {@code handlerConditionNotfound}
	 * labeled alternative in {@link MariaDBParser#handlerConditionValue}.
	 * @param ctx the parse tree
	 */
	void exitHandlerConditionNotfound(MariaDBParser.HandlerConditionNotfoundContext ctx);
	/**
	 * Enter a parse tree produced by the {@code handlerConditionException}
	 * labeled alternative in {@link MariaDBParser#handlerConditionValue}.
	 * @param ctx the parse tree
	 */
	void enterHandlerConditionException(MariaDBParser.HandlerConditionExceptionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code handlerConditionException}
	 * labeled alternative in {@link MariaDBParser#handlerConditionValue}.
	 * @param ctx the parse tree
	 */
	void exitHandlerConditionException(MariaDBParser.HandlerConditionExceptionContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#procedureSqlStatement}.
	 * @param ctx the parse tree
	 */
	void enterProcedureSqlStatement(MariaDBParser.ProcedureSqlStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#procedureSqlStatement}.
	 * @param ctx the parse tree
	 */
	void exitProcedureSqlStatement(MariaDBParser.ProcedureSqlStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#caseAlternative}.
	 * @param ctx the parse tree
	 */
	void enterCaseAlternative(MariaDBParser.CaseAlternativeContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#caseAlternative}.
	 * @param ctx the parse tree
	 */
	void exitCaseAlternative(MariaDBParser.CaseAlternativeContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#elifAlternative}.
	 * @param ctx the parse tree
	 */
	void enterElifAlternative(MariaDBParser.ElifAlternativeContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#elifAlternative}.
	 * @param ctx the parse tree
	 */
	void exitElifAlternative(MariaDBParser.ElifAlternativeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code alterUserMysqlV56}
	 * labeled alternative in {@link MariaDBParser#alterUser}.
	 * @param ctx the parse tree
	 */
	void enterAlterUserMysqlV56(MariaDBParser.AlterUserMysqlV56Context ctx);
	/**
	 * Exit a parse tree produced by the {@code alterUserMysqlV56}
	 * labeled alternative in {@link MariaDBParser#alterUser}.
	 * @param ctx the parse tree
	 */
	void exitAlterUserMysqlV56(MariaDBParser.AlterUserMysqlV56Context ctx);
	/**
	 * Enter a parse tree produced by the {@code alterUserMysqlV80}
	 * labeled alternative in {@link MariaDBParser#alterUser}.
	 * @param ctx the parse tree
	 */
	void enterAlterUserMysqlV80(MariaDBParser.AlterUserMysqlV80Context ctx);
	/**
	 * Exit a parse tree produced by the {@code alterUserMysqlV80}
	 * labeled alternative in {@link MariaDBParser#alterUser}.
	 * @param ctx the parse tree
	 */
	void exitAlterUserMysqlV80(MariaDBParser.AlterUserMysqlV80Context ctx);
	/**
	 * Enter a parse tree produced by the {@code createUserMysqlV56}
	 * labeled alternative in {@link MariaDBParser#createUser}.
	 * @param ctx the parse tree
	 */
	void enterCreateUserMysqlV56(MariaDBParser.CreateUserMysqlV56Context ctx);
	/**
	 * Exit a parse tree produced by the {@code createUserMysqlV56}
	 * labeled alternative in {@link MariaDBParser#createUser}.
	 * @param ctx the parse tree
	 */
	void exitCreateUserMysqlV56(MariaDBParser.CreateUserMysqlV56Context ctx);
	/**
	 * Enter a parse tree produced by the {@code createUserMysqlV80}
	 * labeled alternative in {@link MariaDBParser#createUser}.
	 * @param ctx the parse tree
	 */
	void enterCreateUserMysqlV80(MariaDBParser.CreateUserMysqlV80Context ctx);
	/**
	 * Exit a parse tree produced by the {@code createUserMysqlV80}
	 * labeled alternative in {@link MariaDBParser#createUser}.
	 * @param ctx the parse tree
	 */
	void exitCreateUserMysqlV80(MariaDBParser.CreateUserMysqlV80Context ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#dropUser}.
	 * @param ctx the parse tree
	 */
	void enterDropUser(MariaDBParser.DropUserContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#dropUser}.
	 * @param ctx the parse tree
	 */
	void exitDropUser(MariaDBParser.DropUserContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#grantStatement}.
	 * @param ctx the parse tree
	 */
	void enterGrantStatement(MariaDBParser.GrantStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#grantStatement}.
	 * @param ctx the parse tree
	 */
	void exitGrantStatement(MariaDBParser.GrantStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#roleOption}.
	 * @param ctx the parse tree
	 */
	void enterRoleOption(MariaDBParser.RoleOptionContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#roleOption}.
	 * @param ctx the parse tree
	 */
	void exitRoleOption(MariaDBParser.RoleOptionContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#grantProxy}.
	 * @param ctx the parse tree
	 */
	void enterGrantProxy(MariaDBParser.GrantProxyContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#grantProxy}.
	 * @param ctx the parse tree
	 */
	void exitGrantProxy(MariaDBParser.GrantProxyContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#renameUser}.
	 * @param ctx the parse tree
	 */
	void enterRenameUser(MariaDBParser.RenameUserContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#renameUser}.
	 * @param ctx the parse tree
	 */
	void exitRenameUser(MariaDBParser.RenameUserContext ctx);
	/**
	 * Enter a parse tree produced by the {@code detailRevoke}
	 * labeled alternative in {@link MariaDBParser#revokeStatement}.
	 * @param ctx the parse tree
	 */
	void enterDetailRevoke(MariaDBParser.DetailRevokeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code detailRevoke}
	 * labeled alternative in {@link MariaDBParser#revokeStatement}.
	 * @param ctx the parse tree
	 */
	void exitDetailRevoke(MariaDBParser.DetailRevokeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code shortRevoke}
	 * labeled alternative in {@link MariaDBParser#revokeStatement}.
	 * @param ctx the parse tree
	 */
	void enterShortRevoke(MariaDBParser.ShortRevokeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code shortRevoke}
	 * labeled alternative in {@link MariaDBParser#revokeStatement}.
	 * @param ctx the parse tree
	 */
	void exitShortRevoke(MariaDBParser.ShortRevokeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code roleRevoke}
	 * labeled alternative in {@link MariaDBParser#revokeStatement}.
	 * @param ctx the parse tree
	 */
	void enterRoleRevoke(MariaDBParser.RoleRevokeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code roleRevoke}
	 * labeled alternative in {@link MariaDBParser#revokeStatement}.
	 * @param ctx the parse tree
	 */
	void exitRoleRevoke(MariaDBParser.RoleRevokeContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#revokeProxy}.
	 * @param ctx the parse tree
	 */
	void enterRevokeProxy(MariaDBParser.RevokeProxyContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#revokeProxy}.
	 * @param ctx the parse tree
	 */
	void exitRevokeProxy(MariaDBParser.RevokeProxyContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#setPasswordStatement}.
	 * @param ctx the parse tree
	 */
	void enterSetPasswordStatement(MariaDBParser.SetPasswordStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#setPasswordStatement}.
	 * @param ctx the parse tree
	 */
	void exitSetPasswordStatement(MariaDBParser.SetPasswordStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#userSpecification}.
	 * @param ctx the parse tree
	 */
	void enterUserSpecification(MariaDBParser.UserSpecificationContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#userSpecification}.
	 * @param ctx the parse tree
	 */
	void exitUserSpecification(MariaDBParser.UserSpecificationContext ctx);
	/**
	 * Enter a parse tree produced by the {@code hashAuthOption}
	 * labeled alternative in {@link MariaDBParser#userAuthOption}.
	 * @param ctx the parse tree
	 */
	void enterHashAuthOption(MariaDBParser.HashAuthOptionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code hashAuthOption}
	 * labeled alternative in {@link MariaDBParser#userAuthOption}.
	 * @param ctx the parse tree
	 */
	void exitHashAuthOption(MariaDBParser.HashAuthOptionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code stringAuthOption}
	 * labeled alternative in {@link MariaDBParser#userAuthOption}.
	 * @param ctx the parse tree
	 */
	void enterStringAuthOption(MariaDBParser.StringAuthOptionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code stringAuthOption}
	 * labeled alternative in {@link MariaDBParser#userAuthOption}.
	 * @param ctx the parse tree
	 */
	void exitStringAuthOption(MariaDBParser.StringAuthOptionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code moduleAuthOption}
	 * labeled alternative in {@link MariaDBParser#userAuthOption}.
	 * @param ctx the parse tree
	 */
	void enterModuleAuthOption(MariaDBParser.ModuleAuthOptionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code moduleAuthOption}
	 * labeled alternative in {@link MariaDBParser#userAuthOption}.
	 * @param ctx the parse tree
	 */
	void exitModuleAuthOption(MariaDBParser.ModuleAuthOptionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code simpleAuthOption}
	 * labeled alternative in {@link MariaDBParser#userAuthOption}.
	 * @param ctx the parse tree
	 */
	void enterSimpleAuthOption(MariaDBParser.SimpleAuthOptionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code simpleAuthOption}
	 * labeled alternative in {@link MariaDBParser#userAuthOption}.
	 * @param ctx the parse tree
	 */
	void exitSimpleAuthOption(MariaDBParser.SimpleAuthOptionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code module}
	 * labeled alternative in {@link MariaDBParser#authenticationRule}.
	 * @param ctx the parse tree
	 */
	void enterModule(MariaDBParser.ModuleContext ctx);
	/**
	 * Exit a parse tree produced by the {@code module}
	 * labeled alternative in {@link MariaDBParser#authenticationRule}.
	 * @param ctx the parse tree
	 */
	void exitModule(MariaDBParser.ModuleContext ctx);
	/**
	 * Enter a parse tree produced by the {@code passwordModuleOption}
	 * labeled alternative in {@link MariaDBParser#authenticationRule}.
	 * @param ctx the parse tree
	 */
	void enterPasswordModuleOption(MariaDBParser.PasswordModuleOptionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code passwordModuleOption}
	 * labeled alternative in {@link MariaDBParser#authenticationRule}.
	 * @param ctx the parse tree
	 */
	void exitPasswordModuleOption(MariaDBParser.PasswordModuleOptionContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#tlsOption}.
	 * @param ctx the parse tree
	 */
	void enterTlsOption(MariaDBParser.TlsOptionContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#tlsOption}.
	 * @param ctx the parse tree
	 */
	void exitTlsOption(MariaDBParser.TlsOptionContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#userResourceOption}.
	 * @param ctx the parse tree
	 */
	void enterUserResourceOption(MariaDBParser.UserResourceOptionContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#userResourceOption}.
	 * @param ctx the parse tree
	 */
	void exitUserResourceOption(MariaDBParser.UserResourceOptionContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#userPasswordOption}.
	 * @param ctx the parse tree
	 */
	void enterUserPasswordOption(MariaDBParser.UserPasswordOptionContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#userPasswordOption}.
	 * @param ctx the parse tree
	 */
	void exitUserPasswordOption(MariaDBParser.UserPasswordOptionContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#userLockOption}.
	 * @param ctx the parse tree
	 */
	void enterUserLockOption(MariaDBParser.UserLockOptionContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#userLockOption}.
	 * @param ctx the parse tree
	 */
	void exitUserLockOption(MariaDBParser.UserLockOptionContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#privelegeClause}.
	 * @param ctx the parse tree
	 */
	void enterPrivelegeClause(MariaDBParser.PrivelegeClauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#privelegeClause}.
	 * @param ctx the parse tree
	 */
	void exitPrivelegeClause(MariaDBParser.PrivelegeClauseContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#privilege}.
	 * @param ctx the parse tree
	 */
	void enterPrivilege(MariaDBParser.PrivilegeContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#privilege}.
	 * @param ctx the parse tree
	 */
	void exitPrivilege(MariaDBParser.PrivilegeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code currentSchemaPriviLevel}
	 * labeled alternative in {@link MariaDBParser#privilegeLevel}.
	 * @param ctx the parse tree
	 */
	void enterCurrentSchemaPriviLevel(MariaDBParser.CurrentSchemaPriviLevelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code currentSchemaPriviLevel}
	 * labeled alternative in {@link MariaDBParser#privilegeLevel}.
	 * @param ctx the parse tree
	 */
	void exitCurrentSchemaPriviLevel(MariaDBParser.CurrentSchemaPriviLevelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code globalPrivLevel}
	 * labeled alternative in {@link MariaDBParser#privilegeLevel}.
	 * @param ctx the parse tree
	 */
	void enterGlobalPrivLevel(MariaDBParser.GlobalPrivLevelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code globalPrivLevel}
	 * labeled alternative in {@link MariaDBParser#privilegeLevel}.
	 * @param ctx the parse tree
	 */
	void exitGlobalPrivLevel(MariaDBParser.GlobalPrivLevelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code definiteSchemaPrivLevel}
	 * labeled alternative in {@link MariaDBParser#privilegeLevel}.
	 * @param ctx the parse tree
	 */
	void enterDefiniteSchemaPrivLevel(MariaDBParser.DefiniteSchemaPrivLevelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code definiteSchemaPrivLevel}
	 * labeled alternative in {@link MariaDBParser#privilegeLevel}.
	 * @param ctx the parse tree
	 */
	void exitDefiniteSchemaPrivLevel(MariaDBParser.DefiniteSchemaPrivLevelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code definiteFullTablePrivLevel}
	 * labeled alternative in {@link MariaDBParser#privilegeLevel}.
	 * @param ctx the parse tree
	 */
	void enterDefiniteFullTablePrivLevel(MariaDBParser.DefiniteFullTablePrivLevelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code definiteFullTablePrivLevel}
	 * labeled alternative in {@link MariaDBParser#privilegeLevel}.
	 * @param ctx the parse tree
	 */
	void exitDefiniteFullTablePrivLevel(MariaDBParser.DefiniteFullTablePrivLevelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code definiteFullTablePrivLevel2}
	 * labeled alternative in {@link MariaDBParser#privilegeLevel}.
	 * @param ctx the parse tree
	 */
	void enterDefiniteFullTablePrivLevel2(MariaDBParser.DefiniteFullTablePrivLevel2Context ctx);
	/**
	 * Exit a parse tree produced by the {@code definiteFullTablePrivLevel2}
	 * labeled alternative in {@link MariaDBParser#privilegeLevel}.
	 * @param ctx the parse tree
	 */
	void exitDefiniteFullTablePrivLevel2(MariaDBParser.DefiniteFullTablePrivLevel2Context ctx);
	/**
	 * Enter a parse tree produced by the {@code definiteTablePrivLevel}
	 * labeled alternative in {@link MariaDBParser#privilegeLevel}.
	 * @param ctx the parse tree
	 */
	void enterDefiniteTablePrivLevel(MariaDBParser.DefiniteTablePrivLevelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code definiteTablePrivLevel}
	 * labeled alternative in {@link MariaDBParser#privilegeLevel}.
	 * @param ctx the parse tree
	 */
	void exitDefiniteTablePrivLevel(MariaDBParser.DefiniteTablePrivLevelContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#renameUserClause}.
	 * @param ctx the parse tree
	 */
	void enterRenameUserClause(MariaDBParser.RenameUserClauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#renameUserClause}.
	 * @param ctx the parse tree
	 */
	void exitRenameUserClause(MariaDBParser.RenameUserClauseContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#analyzeTable}.
	 * @param ctx the parse tree
	 */
	void enterAnalyzeTable(MariaDBParser.AnalyzeTableContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#analyzeTable}.
	 * @param ctx the parse tree
	 */
	void exitAnalyzeTable(MariaDBParser.AnalyzeTableContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#checkTable}.
	 * @param ctx the parse tree
	 */
	void enterCheckTable(MariaDBParser.CheckTableContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#checkTable}.
	 * @param ctx the parse tree
	 */
	void exitCheckTable(MariaDBParser.CheckTableContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#checksumTable}.
	 * @param ctx the parse tree
	 */
	void enterChecksumTable(MariaDBParser.ChecksumTableContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#checksumTable}.
	 * @param ctx the parse tree
	 */
	void exitChecksumTable(MariaDBParser.ChecksumTableContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#optimizeTable}.
	 * @param ctx the parse tree
	 */
	void enterOptimizeTable(MariaDBParser.OptimizeTableContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#optimizeTable}.
	 * @param ctx the parse tree
	 */
	void exitOptimizeTable(MariaDBParser.OptimizeTableContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#repairTable}.
	 * @param ctx the parse tree
	 */
	void enterRepairTable(MariaDBParser.RepairTableContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#repairTable}.
	 * @param ctx the parse tree
	 */
	void exitRepairTable(MariaDBParser.RepairTableContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#checkTableOption}.
	 * @param ctx the parse tree
	 */
	void enterCheckTableOption(MariaDBParser.CheckTableOptionContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#checkTableOption}.
	 * @param ctx the parse tree
	 */
	void exitCheckTableOption(MariaDBParser.CheckTableOptionContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#createUdfunction}.
	 * @param ctx the parse tree
	 */
	void enterCreateUdfunction(MariaDBParser.CreateUdfunctionContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#createUdfunction}.
	 * @param ctx the parse tree
	 */
	void exitCreateUdfunction(MariaDBParser.CreateUdfunctionContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#installPlugin}.
	 * @param ctx the parse tree
	 */
	void enterInstallPlugin(MariaDBParser.InstallPluginContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#installPlugin}.
	 * @param ctx the parse tree
	 */
	void exitInstallPlugin(MariaDBParser.InstallPluginContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#uninstallPlugin}.
	 * @param ctx the parse tree
	 */
	void enterUninstallPlugin(MariaDBParser.UninstallPluginContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#uninstallPlugin}.
	 * @param ctx the parse tree
	 */
	void exitUninstallPlugin(MariaDBParser.UninstallPluginContext ctx);
	/**
	 * Enter a parse tree produced by the {@code setVariable}
	 * labeled alternative in {@link MariaDBParser#setStatement}.
	 * @param ctx the parse tree
	 */
	void enterSetVariable(MariaDBParser.SetVariableContext ctx);
	/**
	 * Exit a parse tree produced by the {@code setVariable}
	 * labeled alternative in {@link MariaDBParser#setStatement}.
	 * @param ctx the parse tree
	 */
	void exitSetVariable(MariaDBParser.SetVariableContext ctx);
	/**
	 * Enter a parse tree produced by the {@code setCharset}
	 * labeled alternative in {@link MariaDBParser#setStatement}.
	 * @param ctx the parse tree
	 */
	void enterSetCharset(MariaDBParser.SetCharsetContext ctx);
	/**
	 * Exit a parse tree produced by the {@code setCharset}
	 * labeled alternative in {@link MariaDBParser#setStatement}.
	 * @param ctx the parse tree
	 */
	void exitSetCharset(MariaDBParser.SetCharsetContext ctx);
	/**
	 * Enter a parse tree produced by the {@code setNames}
	 * labeled alternative in {@link MariaDBParser#setStatement}.
	 * @param ctx the parse tree
	 */
	void enterSetNames(MariaDBParser.SetNamesContext ctx);
	/**
	 * Exit a parse tree produced by the {@code setNames}
	 * labeled alternative in {@link MariaDBParser#setStatement}.
	 * @param ctx the parse tree
	 */
	void exitSetNames(MariaDBParser.SetNamesContext ctx);
	/**
	 * Enter a parse tree produced by the {@code setPassword}
	 * labeled alternative in {@link MariaDBParser#setStatement}.
	 * @param ctx the parse tree
	 */
	void enterSetPassword(MariaDBParser.SetPasswordContext ctx);
	/**
	 * Exit a parse tree produced by the {@code setPassword}
	 * labeled alternative in {@link MariaDBParser#setStatement}.
	 * @param ctx the parse tree
	 */
	void exitSetPassword(MariaDBParser.SetPasswordContext ctx);
	/**
	 * Enter a parse tree produced by the {@code setTransaction}
	 * labeled alternative in {@link MariaDBParser#setStatement}.
	 * @param ctx the parse tree
	 */
	void enterSetTransaction(MariaDBParser.SetTransactionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code setTransaction}
	 * labeled alternative in {@link MariaDBParser#setStatement}.
	 * @param ctx the parse tree
	 */
	void exitSetTransaction(MariaDBParser.SetTransactionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code setAutocommit}
	 * labeled alternative in {@link MariaDBParser#setStatement}.
	 * @param ctx the parse tree
	 */
	void enterSetAutocommit(MariaDBParser.SetAutocommitContext ctx);
	/**
	 * Exit a parse tree produced by the {@code setAutocommit}
	 * labeled alternative in {@link MariaDBParser#setStatement}.
	 * @param ctx the parse tree
	 */
	void exitSetAutocommit(MariaDBParser.SetAutocommitContext ctx);
	/**
	 * Enter a parse tree produced by the {@code setNewValueInsideTrigger}
	 * labeled alternative in {@link MariaDBParser#setStatement}.
	 * @param ctx the parse tree
	 */
	void enterSetNewValueInsideTrigger(MariaDBParser.SetNewValueInsideTriggerContext ctx);
	/**
	 * Exit a parse tree produced by the {@code setNewValueInsideTrigger}
	 * labeled alternative in {@link MariaDBParser#setStatement}.
	 * @param ctx the parse tree
	 */
	void exitSetNewValueInsideTrigger(MariaDBParser.SetNewValueInsideTriggerContext ctx);
	/**
	 * Enter a parse tree produced by the {@code showMasterLogs}
	 * labeled alternative in {@link MariaDBParser#showStatement}.
	 * @param ctx the parse tree
	 */
	void enterShowMasterLogs(MariaDBParser.ShowMasterLogsContext ctx);
	/**
	 * Exit a parse tree produced by the {@code showMasterLogs}
	 * labeled alternative in {@link MariaDBParser#showStatement}.
	 * @param ctx the parse tree
	 */
	void exitShowMasterLogs(MariaDBParser.ShowMasterLogsContext ctx);
	/**
	 * Enter a parse tree produced by the {@code showBinLogEvents}
	 * labeled alternative in {@link MariaDBParser#showStatement}.
	 * @param ctx the parse tree
	 */
	void enterShowBinLogEvents(MariaDBParser.ShowBinLogEventsContext ctx);
	/**
	 * Exit a parse tree produced by the {@code showBinLogEvents}
	 * labeled alternative in {@link MariaDBParser#showStatement}.
	 * @param ctx the parse tree
	 */
	void exitShowBinLogEvents(MariaDBParser.ShowBinLogEventsContext ctx);
	/**
	 * Enter a parse tree produced by the {@code showRelayLogEvents}
	 * labeled alternative in {@link MariaDBParser#showStatement}.
	 * @param ctx the parse tree
	 */
	void enterShowRelayLogEvents(MariaDBParser.ShowRelayLogEventsContext ctx);
	/**
	 * Exit a parse tree produced by the {@code showRelayLogEvents}
	 * labeled alternative in {@link MariaDBParser#showStatement}.
	 * @param ctx the parse tree
	 */
	void exitShowRelayLogEvents(MariaDBParser.ShowRelayLogEventsContext ctx);
	/**
	 * Enter a parse tree produced by the {@code showObjectFilter}
	 * labeled alternative in {@link MariaDBParser#showStatement}.
	 * @param ctx the parse tree
	 */
	void enterShowObjectFilter(MariaDBParser.ShowObjectFilterContext ctx);
	/**
	 * Exit a parse tree produced by the {@code showObjectFilter}
	 * labeled alternative in {@link MariaDBParser#showStatement}.
	 * @param ctx the parse tree
	 */
	void exitShowObjectFilter(MariaDBParser.ShowObjectFilterContext ctx);
	/**
	 * Enter a parse tree produced by the {@code showColumns}
	 * labeled alternative in {@link MariaDBParser#showStatement}.
	 * @param ctx the parse tree
	 */
	void enterShowColumns(MariaDBParser.ShowColumnsContext ctx);
	/**
	 * Exit a parse tree produced by the {@code showColumns}
	 * labeled alternative in {@link MariaDBParser#showStatement}.
	 * @param ctx the parse tree
	 */
	void exitShowColumns(MariaDBParser.ShowColumnsContext ctx);
	/**
	 * Enter a parse tree produced by the {@code showCreateDb}
	 * labeled alternative in {@link MariaDBParser#showStatement}.
	 * @param ctx the parse tree
	 */
	void enterShowCreateDb(MariaDBParser.ShowCreateDbContext ctx);
	/**
	 * Exit a parse tree produced by the {@code showCreateDb}
	 * labeled alternative in {@link MariaDBParser#showStatement}.
	 * @param ctx the parse tree
	 */
	void exitShowCreateDb(MariaDBParser.ShowCreateDbContext ctx);
	/**
	 * Enter a parse tree produced by the {@code showCreateFullIdObject}
	 * labeled alternative in {@link MariaDBParser#showStatement}.
	 * @param ctx the parse tree
	 */
	void enterShowCreateFullIdObject(MariaDBParser.ShowCreateFullIdObjectContext ctx);
	/**
	 * Exit a parse tree produced by the {@code showCreateFullIdObject}
	 * labeled alternative in {@link MariaDBParser#showStatement}.
	 * @param ctx the parse tree
	 */
	void exitShowCreateFullIdObject(MariaDBParser.ShowCreateFullIdObjectContext ctx);
	/**
	 * Enter a parse tree produced by the {@code showCreatePackage}
	 * labeled alternative in {@link MariaDBParser#showStatement}.
	 * @param ctx the parse tree
	 */
	void enterShowCreatePackage(MariaDBParser.ShowCreatePackageContext ctx);
	/**
	 * Exit a parse tree produced by the {@code showCreatePackage}
	 * labeled alternative in {@link MariaDBParser#showStatement}.
	 * @param ctx the parse tree
	 */
	void exitShowCreatePackage(MariaDBParser.ShowCreatePackageContext ctx);
	/**
	 * Enter a parse tree produced by the {@code showCreateUser}
	 * labeled alternative in {@link MariaDBParser#showStatement}.
	 * @param ctx the parse tree
	 */
	void enterShowCreateUser(MariaDBParser.ShowCreateUserContext ctx);
	/**
	 * Exit a parse tree produced by the {@code showCreateUser}
	 * labeled alternative in {@link MariaDBParser#showStatement}.
	 * @param ctx the parse tree
	 */
	void exitShowCreateUser(MariaDBParser.ShowCreateUserContext ctx);
	/**
	 * Enter a parse tree produced by the {@code showEngine}
	 * labeled alternative in {@link MariaDBParser#showStatement}.
	 * @param ctx the parse tree
	 */
	void enterShowEngine(MariaDBParser.ShowEngineContext ctx);
	/**
	 * Exit a parse tree produced by the {@code showEngine}
	 * labeled alternative in {@link MariaDBParser#showStatement}.
	 * @param ctx the parse tree
	 */
	void exitShowEngine(MariaDBParser.ShowEngineContext ctx);
	/**
	 * Enter a parse tree produced by the {@code showInnoDBStatus}
	 * labeled alternative in {@link MariaDBParser#showStatement}.
	 * @param ctx the parse tree
	 */
	void enterShowInnoDBStatus(MariaDBParser.ShowInnoDBStatusContext ctx);
	/**
	 * Exit a parse tree produced by the {@code showInnoDBStatus}
	 * labeled alternative in {@link MariaDBParser#showStatement}.
	 * @param ctx the parse tree
	 */
	void exitShowInnoDBStatus(MariaDBParser.ShowInnoDBStatusContext ctx);
	/**
	 * Enter a parse tree produced by the {@code showGlobalInfo}
	 * labeled alternative in {@link MariaDBParser#showStatement}.
	 * @param ctx the parse tree
	 */
	void enterShowGlobalInfo(MariaDBParser.ShowGlobalInfoContext ctx);
	/**
	 * Exit a parse tree produced by the {@code showGlobalInfo}
	 * labeled alternative in {@link MariaDBParser#showStatement}.
	 * @param ctx the parse tree
	 */
	void exitShowGlobalInfo(MariaDBParser.ShowGlobalInfoContext ctx);
	/**
	 * Enter a parse tree produced by the {@code showErrors}
	 * labeled alternative in {@link MariaDBParser#showStatement}.
	 * @param ctx the parse tree
	 */
	void enterShowErrors(MariaDBParser.ShowErrorsContext ctx);
	/**
	 * Exit a parse tree produced by the {@code showErrors}
	 * labeled alternative in {@link MariaDBParser#showStatement}.
	 * @param ctx the parse tree
	 */
	void exitShowErrors(MariaDBParser.ShowErrorsContext ctx);
	/**
	 * Enter a parse tree produced by the {@code showCountErrors}
	 * labeled alternative in {@link MariaDBParser#showStatement}.
	 * @param ctx the parse tree
	 */
	void enterShowCountErrors(MariaDBParser.ShowCountErrorsContext ctx);
	/**
	 * Exit a parse tree produced by the {@code showCountErrors}
	 * labeled alternative in {@link MariaDBParser#showStatement}.
	 * @param ctx the parse tree
	 */
	void exitShowCountErrors(MariaDBParser.ShowCountErrorsContext ctx);
	/**
	 * Enter a parse tree produced by the {@code showSchemaFilter}
	 * labeled alternative in {@link MariaDBParser#showStatement}.
	 * @param ctx the parse tree
	 */
	void enterShowSchemaFilter(MariaDBParser.ShowSchemaFilterContext ctx);
	/**
	 * Exit a parse tree produced by the {@code showSchemaFilter}
	 * labeled alternative in {@link MariaDBParser#showStatement}.
	 * @param ctx the parse tree
	 */
	void exitShowSchemaFilter(MariaDBParser.ShowSchemaFilterContext ctx);
	/**
	 * Enter a parse tree produced by the {@code showRoutine}
	 * labeled alternative in {@link MariaDBParser#showStatement}.
	 * @param ctx the parse tree
	 */
	void enterShowRoutine(MariaDBParser.ShowRoutineContext ctx);
	/**
	 * Exit a parse tree produced by the {@code showRoutine}
	 * labeled alternative in {@link MariaDBParser#showStatement}.
	 * @param ctx the parse tree
	 */
	void exitShowRoutine(MariaDBParser.ShowRoutineContext ctx);
	/**
	 * Enter a parse tree produced by the {@code showGrants}
	 * labeled alternative in {@link MariaDBParser#showStatement}.
	 * @param ctx the parse tree
	 */
	void enterShowGrants(MariaDBParser.ShowGrantsContext ctx);
	/**
	 * Exit a parse tree produced by the {@code showGrants}
	 * labeled alternative in {@link MariaDBParser#showStatement}.
	 * @param ctx the parse tree
	 */
	void exitShowGrants(MariaDBParser.ShowGrantsContext ctx);
	/**
	 * Enter a parse tree produced by the {@code showIndexes}
	 * labeled alternative in {@link MariaDBParser#showStatement}.
	 * @param ctx the parse tree
	 */
	void enterShowIndexes(MariaDBParser.ShowIndexesContext ctx);
	/**
	 * Exit a parse tree produced by the {@code showIndexes}
	 * labeled alternative in {@link MariaDBParser#showStatement}.
	 * @param ctx the parse tree
	 */
	void exitShowIndexes(MariaDBParser.ShowIndexesContext ctx);
	/**
	 * Enter a parse tree produced by the {@code showOpenTables}
	 * labeled alternative in {@link MariaDBParser#showStatement}.
	 * @param ctx the parse tree
	 */
	void enterShowOpenTables(MariaDBParser.ShowOpenTablesContext ctx);
	/**
	 * Exit a parse tree produced by the {@code showOpenTables}
	 * labeled alternative in {@link MariaDBParser#showStatement}.
	 * @param ctx the parse tree
	 */
	void exitShowOpenTables(MariaDBParser.ShowOpenTablesContext ctx);
	/**
	 * Enter a parse tree produced by the {@code showProfile}
	 * labeled alternative in {@link MariaDBParser#showStatement}.
	 * @param ctx the parse tree
	 */
	void enterShowProfile(MariaDBParser.ShowProfileContext ctx);
	/**
	 * Exit a parse tree produced by the {@code showProfile}
	 * labeled alternative in {@link MariaDBParser#showStatement}.
	 * @param ctx the parse tree
	 */
	void exitShowProfile(MariaDBParser.ShowProfileContext ctx);
	/**
	 * Enter a parse tree produced by the {@code showSlaveStatus}
	 * labeled alternative in {@link MariaDBParser#showStatement}.
	 * @param ctx the parse tree
	 */
	void enterShowSlaveStatus(MariaDBParser.ShowSlaveStatusContext ctx);
	/**
	 * Exit a parse tree produced by the {@code showSlaveStatus}
	 * labeled alternative in {@link MariaDBParser#showStatement}.
	 * @param ctx the parse tree
	 */
	void exitShowSlaveStatus(MariaDBParser.ShowSlaveStatusContext ctx);
	/**
	 * Enter a parse tree produced by the {@code showUserstatPlugin}
	 * labeled alternative in {@link MariaDBParser#showStatement}.
	 * @param ctx the parse tree
	 */
	void enterShowUserstatPlugin(MariaDBParser.ShowUserstatPluginContext ctx);
	/**
	 * Exit a parse tree produced by the {@code showUserstatPlugin}
	 * labeled alternative in {@link MariaDBParser#showStatement}.
	 * @param ctx the parse tree
	 */
	void exitShowUserstatPlugin(MariaDBParser.ShowUserstatPluginContext ctx);
	/**
	 * Enter a parse tree produced by the {@code showExplain}
	 * labeled alternative in {@link MariaDBParser#showStatement}.
	 * @param ctx the parse tree
	 */
	void enterShowExplain(MariaDBParser.ShowExplainContext ctx);
	/**
	 * Exit a parse tree produced by the {@code showExplain}
	 * labeled alternative in {@link MariaDBParser#showStatement}.
	 * @param ctx the parse tree
	 */
	void exitShowExplain(MariaDBParser.ShowExplainContext ctx);
	/**
	 * Enter a parse tree produced by the {@code showPackageStatus}
	 * labeled alternative in {@link MariaDBParser#showStatement}.
	 * @param ctx the parse tree
	 */
	void enterShowPackageStatus(MariaDBParser.ShowPackageStatusContext ctx);
	/**
	 * Exit a parse tree produced by the {@code showPackageStatus}
	 * labeled alternative in {@link MariaDBParser#showStatement}.
	 * @param ctx the parse tree
	 */
	void exitShowPackageStatus(MariaDBParser.ShowPackageStatusContext ctx);
	/**
	 * Enter a parse tree produced by the {@code explainForConnection}
	 * labeled alternative in {@link MariaDBParser#explainStatement}.
	 * @param ctx the parse tree
	 */
	void enterExplainForConnection(MariaDBParser.ExplainForConnectionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code explainForConnection}
	 * labeled alternative in {@link MariaDBParser#explainStatement}.
	 * @param ctx the parse tree
	 */
	void exitExplainForConnection(MariaDBParser.ExplainForConnectionContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#variableClause}.
	 * @param ctx the parse tree
	 */
	void enterVariableClause(MariaDBParser.VariableClauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#variableClause}.
	 * @param ctx the parse tree
	 */
	void exitVariableClause(MariaDBParser.VariableClauseContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#showCommonEntity}.
	 * @param ctx the parse tree
	 */
	void enterShowCommonEntity(MariaDBParser.ShowCommonEntityContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#showCommonEntity}.
	 * @param ctx the parse tree
	 */
	void exitShowCommonEntity(MariaDBParser.ShowCommonEntityContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#showFilter}.
	 * @param ctx the parse tree
	 */
	void enterShowFilter(MariaDBParser.ShowFilterContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#showFilter}.
	 * @param ctx the parse tree
	 */
	void exitShowFilter(MariaDBParser.ShowFilterContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#showGlobalInfoClause}.
	 * @param ctx the parse tree
	 */
	void enterShowGlobalInfoClause(MariaDBParser.ShowGlobalInfoClauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#showGlobalInfoClause}.
	 * @param ctx the parse tree
	 */
	void exitShowGlobalInfoClause(MariaDBParser.ShowGlobalInfoClauseContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#showSchemaEntity}.
	 * @param ctx the parse tree
	 */
	void enterShowSchemaEntity(MariaDBParser.ShowSchemaEntityContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#showSchemaEntity}.
	 * @param ctx the parse tree
	 */
	void exitShowSchemaEntity(MariaDBParser.ShowSchemaEntityContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#showProfileType}.
	 * @param ctx the parse tree
	 */
	void enterShowProfileType(MariaDBParser.ShowProfileTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#showProfileType}.
	 * @param ctx the parse tree
	 */
	void exitShowProfileType(MariaDBParser.ShowProfileTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#binlogStatement}.
	 * @param ctx the parse tree
	 */
	void enterBinlogStatement(MariaDBParser.BinlogStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#binlogStatement}.
	 * @param ctx the parse tree
	 */
	void exitBinlogStatement(MariaDBParser.BinlogStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#cacheIndexStatement}.
	 * @param ctx the parse tree
	 */
	void enterCacheIndexStatement(MariaDBParser.CacheIndexStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#cacheIndexStatement}.
	 * @param ctx the parse tree
	 */
	void exitCacheIndexStatement(MariaDBParser.CacheIndexStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#flushStatement}.
	 * @param ctx the parse tree
	 */
	void enterFlushStatement(MariaDBParser.FlushStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#flushStatement}.
	 * @param ctx the parse tree
	 */
	void exitFlushStatement(MariaDBParser.FlushStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#killStatement}.
	 * @param ctx the parse tree
	 */
	void enterKillStatement(MariaDBParser.KillStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#killStatement}.
	 * @param ctx the parse tree
	 */
	void exitKillStatement(MariaDBParser.KillStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#loadIndexIntoCache}.
	 * @param ctx the parse tree
	 */
	void enterLoadIndexIntoCache(MariaDBParser.LoadIndexIntoCacheContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#loadIndexIntoCache}.
	 * @param ctx the parse tree
	 */
	void exitLoadIndexIntoCache(MariaDBParser.LoadIndexIntoCacheContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#resetStatement}.
	 * @param ctx the parse tree
	 */
	void enterResetStatement(MariaDBParser.ResetStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#resetStatement}.
	 * @param ctx the parse tree
	 */
	void exitResetStatement(MariaDBParser.ResetStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#shutdownStatement}.
	 * @param ctx the parse tree
	 */
	void enterShutdownStatement(MariaDBParser.ShutdownStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#shutdownStatement}.
	 * @param ctx the parse tree
	 */
	void exitShutdownStatement(MariaDBParser.ShutdownStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#tableIndexes}.
	 * @param ctx the parse tree
	 */
	void enterTableIndexes(MariaDBParser.TableIndexesContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#tableIndexes}.
	 * @param ctx the parse tree
	 */
	void exitTableIndexes(MariaDBParser.TableIndexesContext ctx);
	/**
	 * Enter a parse tree produced by the {@code simpleFlushOption}
	 * labeled alternative in {@link MariaDBParser#flushOption}.
	 * @param ctx the parse tree
	 */
	void enterSimpleFlushOption(MariaDBParser.SimpleFlushOptionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code simpleFlushOption}
	 * labeled alternative in {@link MariaDBParser#flushOption}.
	 * @param ctx the parse tree
	 */
	void exitSimpleFlushOption(MariaDBParser.SimpleFlushOptionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code channelFlushOption}
	 * labeled alternative in {@link MariaDBParser#flushOption}.
	 * @param ctx the parse tree
	 */
	void enterChannelFlushOption(MariaDBParser.ChannelFlushOptionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code channelFlushOption}
	 * labeled alternative in {@link MariaDBParser#flushOption}.
	 * @param ctx the parse tree
	 */
	void exitChannelFlushOption(MariaDBParser.ChannelFlushOptionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code tableFlushOption}
	 * labeled alternative in {@link MariaDBParser#flushOption}.
	 * @param ctx the parse tree
	 */
	void enterTableFlushOption(MariaDBParser.TableFlushOptionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code tableFlushOption}
	 * labeled alternative in {@link MariaDBParser#flushOption}.
	 * @param ctx the parse tree
	 */
	void exitTableFlushOption(MariaDBParser.TableFlushOptionContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#flushTableOption}.
	 * @param ctx the parse tree
	 */
	void enterFlushTableOption(MariaDBParser.FlushTableOptionContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#flushTableOption}.
	 * @param ctx the parse tree
	 */
	void exitFlushTableOption(MariaDBParser.FlushTableOptionContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#loadedTableIndexes}.
	 * @param ctx the parse tree
	 */
	void enterLoadedTableIndexes(MariaDBParser.LoadedTableIndexesContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#loadedTableIndexes}.
	 * @param ctx the parse tree
	 */
	void exitLoadedTableIndexes(MariaDBParser.LoadedTableIndexesContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#simpleDescribeStatement}.
	 * @param ctx the parse tree
	 */
	void enterSimpleDescribeStatement(MariaDBParser.SimpleDescribeStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#simpleDescribeStatement}.
	 * @param ctx the parse tree
	 */
	void exitSimpleDescribeStatement(MariaDBParser.SimpleDescribeStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#fullDescribeStatement}.
	 * @param ctx the parse tree
	 */
	void enterFullDescribeStatement(MariaDBParser.FullDescribeStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#fullDescribeStatement}.
	 * @param ctx the parse tree
	 */
	void exitFullDescribeStatement(MariaDBParser.FullDescribeStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#formatJsonStatement}.
	 * @param ctx the parse tree
	 */
	void enterFormatJsonStatement(MariaDBParser.FormatJsonStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#formatJsonStatement}.
	 * @param ctx the parse tree
	 */
	void exitFormatJsonStatement(MariaDBParser.FormatJsonStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#helpStatement}.
	 * @param ctx the parse tree
	 */
	void enterHelpStatement(MariaDBParser.HelpStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#helpStatement}.
	 * @param ctx the parse tree
	 */
	void exitHelpStatement(MariaDBParser.HelpStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#useStatement}.
	 * @param ctx the parse tree
	 */
	void enterUseStatement(MariaDBParser.UseStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#useStatement}.
	 * @param ctx the parse tree
	 */
	void exitUseStatement(MariaDBParser.UseStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#signalStatement}.
	 * @param ctx the parse tree
	 */
	void enterSignalStatement(MariaDBParser.SignalStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#signalStatement}.
	 * @param ctx the parse tree
	 */
	void exitSignalStatement(MariaDBParser.SignalStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#resignalStatement}.
	 * @param ctx the parse tree
	 */
	void enterResignalStatement(MariaDBParser.ResignalStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#resignalStatement}.
	 * @param ctx the parse tree
	 */
	void exitResignalStatement(MariaDBParser.ResignalStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#signalConditionInformation}.
	 * @param ctx the parse tree
	 */
	void enterSignalConditionInformation(MariaDBParser.SignalConditionInformationContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#signalConditionInformation}.
	 * @param ctx the parse tree
	 */
	void exitSignalConditionInformation(MariaDBParser.SignalConditionInformationContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#diagnosticsStatement}.
	 * @param ctx the parse tree
	 */
	void enterDiagnosticsStatement(MariaDBParser.DiagnosticsStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#diagnosticsStatement}.
	 * @param ctx the parse tree
	 */
	void exitDiagnosticsStatement(MariaDBParser.DiagnosticsStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#diagnosticsConditionInformationName}.
	 * @param ctx the parse tree
	 */
	void enterDiagnosticsConditionInformationName(MariaDBParser.DiagnosticsConditionInformationNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#diagnosticsConditionInformationName}.
	 * @param ctx the parse tree
	 */
	void exitDiagnosticsConditionInformationName(MariaDBParser.DiagnosticsConditionInformationNameContext ctx);
	/**
	 * Enter a parse tree produced by the {@code describeStatements}
	 * labeled alternative in {@link MariaDBParser#describeObjectClause}.
	 * @param ctx the parse tree
	 */
	void enterDescribeStatements(MariaDBParser.DescribeStatementsContext ctx);
	/**
	 * Exit a parse tree produced by the {@code describeStatements}
	 * labeled alternative in {@link MariaDBParser#describeObjectClause}.
	 * @param ctx the parse tree
	 */
	void exitDescribeStatements(MariaDBParser.DescribeStatementsContext ctx);
	/**
	 * Enter a parse tree produced by the {@code describeConnection}
	 * labeled alternative in {@link MariaDBParser#describeObjectClause}.
	 * @param ctx the parse tree
	 */
	void enterDescribeConnection(MariaDBParser.DescribeConnectionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code describeConnection}
	 * labeled alternative in {@link MariaDBParser#describeObjectClause}.
	 * @param ctx the parse tree
	 */
	void exitDescribeConnection(MariaDBParser.DescribeConnectionContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#fullId}.
	 * @param ctx the parse tree
	 */
	void enterFullId(MariaDBParser.FullIdContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#fullId}.
	 * @param ctx the parse tree
	 */
	void exitFullId(MariaDBParser.FullIdContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#tableName}.
	 * @param ctx the parse tree
	 */
	void enterTableName(MariaDBParser.TableNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#tableName}.
	 * @param ctx the parse tree
	 */
	void exitTableName(MariaDBParser.TableNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#roleName}.
	 * @param ctx the parse tree
	 */
	void enterRoleName(MariaDBParser.RoleNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#roleName}.
	 * @param ctx the parse tree
	 */
	void exitRoleName(MariaDBParser.RoleNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#fullColumnName}.
	 * @param ctx the parse tree
	 */
	void enterFullColumnName(MariaDBParser.FullColumnNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#fullColumnName}.
	 * @param ctx the parse tree
	 */
	void exitFullColumnName(MariaDBParser.FullColumnNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#indexColumnName}.
	 * @param ctx the parse tree
	 */
	void enterIndexColumnName(MariaDBParser.IndexColumnNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#indexColumnName}.
	 * @param ctx the parse tree
	 */
	void exitIndexColumnName(MariaDBParser.IndexColumnNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#simpleUserName}.
	 * @param ctx the parse tree
	 */
	void enterSimpleUserName(MariaDBParser.SimpleUserNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#simpleUserName}.
	 * @param ctx the parse tree
	 */
	void exitSimpleUserName(MariaDBParser.SimpleUserNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#hostName}.
	 * @param ctx the parse tree
	 */
	void enterHostName(MariaDBParser.HostNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#hostName}.
	 * @param ctx the parse tree
	 */
	void exitHostName(MariaDBParser.HostNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#userName}.
	 * @param ctx the parse tree
	 */
	void enterUserName(MariaDBParser.UserNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#userName}.
	 * @param ctx the parse tree
	 */
	void exitUserName(MariaDBParser.UserNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#mysqlVariable}.
	 * @param ctx the parse tree
	 */
	void enterMysqlVariable(MariaDBParser.MysqlVariableContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#mysqlVariable}.
	 * @param ctx the parse tree
	 */
	void exitMysqlVariable(MariaDBParser.MysqlVariableContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#charsetName}.
	 * @param ctx the parse tree
	 */
	void enterCharsetName(MariaDBParser.CharsetNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#charsetName}.
	 * @param ctx the parse tree
	 */
	void exitCharsetName(MariaDBParser.CharsetNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#collationName}.
	 * @param ctx the parse tree
	 */
	void enterCollationName(MariaDBParser.CollationNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#collationName}.
	 * @param ctx the parse tree
	 */
	void exitCollationName(MariaDBParser.CollationNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#engineName}.
	 * @param ctx the parse tree
	 */
	void enterEngineName(MariaDBParser.EngineNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#engineName}.
	 * @param ctx the parse tree
	 */
	void exitEngineName(MariaDBParser.EngineNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#engineNameBase}.
	 * @param ctx the parse tree
	 */
	void enterEngineNameBase(MariaDBParser.EngineNameBaseContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#engineNameBase}.
	 * @param ctx the parse tree
	 */
	void exitEngineNameBase(MariaDBParser.EngineNameBaseContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#encryptedLiteral}.
	 * @param ctx the parse tree
	 */
	void enterEncryptedLiteral(MariaDBParser.EncryptedLiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#encryptedLiteral}.
	 * @param ctx the parse tree
	 */
	void exitEncryptedLiteral(MariaDBParser.EncryptedLiteralContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#uuidSet}.
	 * @param ctx the parse tree
	 */
	void enterUuidSet(MariaDBParser.UuidSetContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#uuidSet}.
	 * @param ctx the parse tree
	 */
	void exitUuidSet(MariaDBParser.UuidSetContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#xid}.
	 * @param ctx the parse tree
	 */
	void enterXid(MariaDBParser.XidContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#xid}.
	 * @param ctx the parse tree
	 */
	void exitXid(MariaDBParser.XidContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#xuidStringId}.
	 * @param ctx the parse tree
	 */
	void enterXuidStringId(MariaDBParser.XuidStringIdContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#xuidStringId}.
	 * @param ctx the parse tree
	 */
	void exitXuidStringId(MariaDBParser.XuidStringIdContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#authPlugin}.
	 * @param ctx the parse tree
	 */
	void enterAuthPlugin(MariaDBParser.AuthPluginContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#authPlugin}.
	 * @param ctx the parse tree
	 */
	void exitAuthPlugin(MariaDBParser.AuthPluginContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#uid}.
	 * @param ctx the parse tree
	 */
	void enterUid(MariaDBParser.UidContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#uid}.
	 * @param ctx the parse tree
	 */
	void exitUid(MariaDBParser.UidContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#simpleId}.
	 * @param ctx the parse tree
	 */
	void enterSimpleId(MariaDBParser.SimpleIdContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#simpleId}.
	 * @param ctx the parse tree
	 */
	void exitSimpleId(MariaDBParser.SimpleIdContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#dottedId}.
	 * @param ctx the parse tree
	 */
	void enterDottedId(MariaDBParser.DottedIdContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#dottedId}.
	 * @param ctx the parse tree
	 */
	void exitDottedId(MariaDBParser.DottedIdContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#decimalLiteral}.
	 * @param ctx the parse tree
	 */
	void enterDecimalLiteral(MariaDBParser.DecimalLiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#decimalLiteral}.
	 * @param ctx the parse tree
	 */
	void exitDecimalLiteral(MariaDBParser.DecimalLiteralContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#fileSizeLiteral}.
	 * @param ctx the parse tree
	 */
	void enterFileSizeLiteral(MariaDBParser.FileSizeLiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#fileSizeLiteral}.
	 * @param ctx the parse tree
	 */
	void exitFileSizeLiteral(MariaDBParser.FileSizeLiteralContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#stringLiteral}.
	 * @param ctx the parse tree
	 */
	void enterStringLiteral(MariaDBParser.StringLiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#stringLiteral}.
	 * @param ctx the parse tree
	 */
	void exitStringLiteral(MariaDBParser.StringLiteralContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#booleanLiteral}.
	 * @param ctx the parse tree
	 */
	void enterBooleanLiteral(MariaDBParser.BooleanLiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#booleanLiteral}.
	 * @param ctx the parse tree
	 */
	void exitBooleanLiteral(MariaDBParser.BooleanLiteralContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#hexadecimalLiteral}.
	 * @param ctx the parse tree
	 */
	void enterHexadecimalLiteral(MariaDBParser.HexadecimalLiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#hexadecimalLiteral}.
	 * @param ctx the parse tree
	 */
	void exitHexadecimalLiteral(MariaDBParser.HexadecimalLiteralContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#nullNotnull}.
	 * @param ctx the parse tree
	 */
	void enterNullNotnull(MariaDBParser.NullNotnullContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#nullNotnull}.
	 * @param ctx the parse tree
	 */
	void exitNullNotnull(MariaDBParser.NullNotnullContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#constant}.
	 * @param ctx the parse tree
	 */
	void enterConstant(MariaDBParser.ConstantContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#constant}.
	 * @param ctx the parse tree
	 */
	void exitConstant(MariaDBParser.ConstantContext ctx);
	/**
	 * Enter a parse tree produced by the {@code stringDataType}
	 * labeled alternative in {@link MariaDBParser#dataType}.
	 * @param ctx the parse tree
	 */
	void enterStringDataType(MariaDBParser.StringDataTypeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code stringDataType}
	 * labeled alternative in {@link MariaDBParser#dataType}.
	 * @param ctx the parse tree
	 */
	void exitStringDataType(MariaDBParser.StringDataTypeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code nationalStringDataType}
	 * labeled alternative in {@link MariaDBParser#dataType}.
	 * @param ctx the parse tree
	 */
	void enterNationalStringDataType(MariaDBParser.NationalStringDataTypeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code nationalStringDataType}
	 * labeled alternative in {@link MariaDBParser#dataType}.
	 * @param ctx the parse tree
	 */
	void exitNationalStringDataType(MariaDBParser.NationalStringDataTypeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code nationalVaryingStringDataType}
	 * labeled alternative in {@link MariaDBParser#dataType}.
	 * @param ctx the parse tree
	 */
	void enterNationalVaryingStringDataType(MariaDBParser.NationalVaryingStringDataTypeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code nationalVaryingStringDataType}
	 * labeled alternative in {@link MariaDBParser#dataType}.
	 * @param ctx the parse tree
	 */
	void exitNationalVaryingStringDataType(MariaDBParser.NationalVaryingStringDataTypeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code dimensionDataType}
	 * labeled alternative in {@link MariaDBParser#dataType}.
	 * @param ctx the parse tree
	 */
	void enterDimensionDataType(MariaDBParser.DimensionDataTypeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code dimensionDataType}
	 * labeled alternative in {@link MariaDBParser#dataType}.
	 * @param ctx the parse tree
	 */
	void exitDimensionDataType(MariaDBParser.DimensionDataTypeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code simpleDataType}
	 * labeled alternative in {@link MariaDBParser#dataType}.
	 * @param ctx the parse tree
	 */
	void enterSimpleDataType(MariaDBParser.SimpleDataTypeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code simpleDataType}
	 * labeled alternative in {@link MariaDBParser#dataType}.
	 * @param ctx the parse tree
	 */
	void exitSimpleDataType(MariaDBParser.SimpleDataTypeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code collectionDataType}
	 * labeled alternative in {@link MariaDBParser#dataType}.
	 * @param ctx the parse tree
	 */
	void enterCollectionDataType(MariaDBParser.CollectionDataTypeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code collectionDataType}
	 * labeled alternative in {@link MariaDBParser#dataType}.
	 * @param ctx the parse tree
	 */
	void exitCollectionDataType(MariaDBParser.CollectionDataTypeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code spatialDataType}
	 * labeled alternative in {@link MariaDBParser#dataType}.
	 * @param ctx the parse tree
	 */
	void enterSpatialDataType(MariaDBParser.SpatialDataTypeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code spatialDataType}
	 * labeled alternative in {@link MariaDBParser#dataType}.
	 * @param ctx the parse tree
	 */
	void exitSpatialDataType(MariaDBParser.SpatialDataTypeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code longVarcharDataType}
	 * labeled alternative in {@link MariaDBParser#dataType}.
	 * @param ctx the parse tree
	 */
	void enterLongVarcharDataType(MariaDBParser.LongVarcharDataTypeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code longVarcharDataType}
	 * labeled alternative in {@link MariaDBParser#dataType}.
	 * @param ctx the parse tree
	 */
	void exitLongVarcharDataType(MariaDBParser.LongVarcharDataTypeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code longVarbinaryDataType}
	 * labeled alternative in {@link MariaDBParser#dataType}.
	 * @param ctx the parse tree
	 */
	void enterLongVarbinaryDataType(MariaDBParser.LongVarbinaryDataTypeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code longVarbinaryDataType}
	 * labeled alternative in {@link MariaDBParser#dataType}.
	 * @param ctx the parse tree
	 */
	void exitLongVarbinaryDataType(MariaDBParser.LongVarbinaryDataTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#collectionOptions}.
	 * @param ctx the parse tree
	 */
	void enterCollectionOptions(MariaDBParser.CollectionOptionsContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#collectionOptions}.
	 * @param ctx the parse tree
	 */
	void exitCollectionOptions(MariaDBParser.CollectionOptionsContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#convertedDataType}.
	 * @param ctx the parse tree
	 */
	void enterConvertedDataType(MariaDBParser.ConvertedDataTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#convertedDataType}.
	 * @param ctx the parse tree
	 */
	void exitConvertedDataType(MariaDBParser.ConvertedDataTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#lengthOneDimension}.
	 * @param ctx the parse tree
	 */
	void enterLengthOneDimension(MariaDBParser.LengthOneDimensionContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#lengthOneDimension}.
	 * @param ctx the parse tree
	 */
	void exitLengthOneDimension(MariaDBParser.LengthOneDimensionContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#lengthTwoDimension}.
	 * @param ctx the parse tree
	 */
	void enterLengthTwoDimension(MariaDBParser.LengthTwoDimensionContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#lengthTwoDimension}.
	 * @param ctx the parse tree
	 */
	void exitLengthTwoDimension(MariaDBParser.LengthTwoDimensionContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#lengthTwoOptionalDimension}.
	 * @param ctx the parse tree
	 */
	void enterLengthTwoOptionalDimension(MariaDBParser.LengthTwoOptionalDimensionContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#lengthTwoOptionalDimension}.
	 * @param ctx the parse tree
	 */
	void exitLengthTwoOptionalDimension(MariaDBParser.LengthTwoOptionalDimensionContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#uidList}.
	 * @param ctx the parse tree
	 */
	void enterUidList(MariaDBParser.UidListContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#uidList}.
	 * @param ctx the parse tree
	 */
	void exitUidList(MariaDBParser.UidListContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#tables}.
	 * @param ctx the parse tree
	 */
	void enterTables(MariaDBParser.TablesContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#tables}.
	 * @param ctx the parse tree
	 */
	void exitTables(MariaDBParser.TablesContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#indexColumnNames}.
	 * @param ctx the parse tree
	 */
	void enterIndexColumnNames(MariaDBParser.IndexColumnNamesContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#indexColumnNames}.
	 * @param ctx the parse tree
	 */
	void exitIndexColumnNames(MariaDBParser.IndexColumnNamesContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#expressions}.
	 * @param ctx the parse tree
	 */
	void enterExpressions(MariaDBParser.ExpressionsContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#expressions}.
	 * @param ctx the parse tree
	 */
	void exitExpressions(MariaDBParser.ExpressionsContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#expressionsWithDefaults}.
	 * @param ctx the parse tree
	 */
	void enterExpressionsWithDefaults(MariaDBParser.ExpressionsWithDefaultsContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#expressionsWithDefaults}.
	 * @param ctx the parse tree
	 */
	void exitExpressionsWithDefaults(MariaDBParser.ExpressionsWithDefaultsContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#constants}.
	 * @param ctx the parse tree
	 */
	void enterConstants(MariaDBParser.ConstantsContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#constants}.
	 * @param ctx the parse tree
	 */
	void exitConstants(MariaDBParser.ConstantsContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#simpleStrings}.
	 * @param ctx the parse tree
	 */
	void enterSimpleStrings(MariaDBParser.SimpleStringsContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#simpleStrings}.
	 * @param ctx the parse tree
	 */
	void exitSimpleStrings(MariaDBParser.SimpleStringsContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#userVariables}.
	 * @param ctx the parse tree
	 */
	void enterUserVariables(MariaDBParser.UserVariablesContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#userVariables}.
	 * @param ctx the parse tree
	 */
	void exitUserVariables(MariaDBParser.UserVariablesContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#defaultValue}.
	 * @param ctx the parse tree
	 */
	void enterDefaultValue(MariaDBParser.DefaultValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#defaultValue}.
	 * @param ctx the parse tree
	 */
	void exitDefaultValue(MariaDBParser.DefaultValueContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#currentTimestamp}.
	 * @param ctx the parse tree
	 */
	void enterCurrentTimestamp(MariaDBParser.CurrentTimestampContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#currentTimestamp}.
	 * @param ctx the parse tree
	 */
	void exitCurrentTimestamp(MariaDBParser.CurrentTimestampContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#expressionOrDefault}.
	 * @param ctx the parse tree
	 */
	void enterExpressionOrDefault(MariaDBParser.ExpressionOrDefaultContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#expressionOrDefault}.
	 * @param ctx the parse tree
	 */
	void exitExpressionOrDefault(MariaDBParser.ExpressionOrDefaultContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#ifExists}.
	 * @param ctx the parse tree
	 */
	void enterIfExists(MariaDBParser.IfExistsContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#ifExists}.
	 * @param ctx the parse tree
	 */
	void exitIfExists(MariaDBParser.IfExistsContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#ifNotExists}.
	 * @param ctx the parse tree
	 */
	void enterIfNotExists(MariaDBParser.IfNotExistsContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#ifNotExists}.
	 * @param ctx the parse tree
	 */
	void exitIfNotExists(MariaDBParser.IfNotExistsContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#orReplace}.
	 * @param ctx the parse tree
	 */
	void enterOrReplace(MariaDBParser.OrReplaceContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#orReplace}.
	 * @param ctx the parse tree
	 */
	void exitOrReplace(MariaDBParser.OrReplaceContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#waitNowaitClause}.
	 * @param ctx the parse tree
	 */
	void enterWaitNowaitClause(MariaDBParser.WaitNowaitClauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#waitNowaitClause}.
	 * @param ctx the parse tree
	 */
	void exitWaitNowaitClause(MariaDBParser.WaitNowaitClauseContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#lockOption}.
	 * @param ctx the parse tree
	 */
	void enterLockOption(MariaDBParser.LockOptionContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#lockOption}.
	 * @param ctx the parse tree
	 */
	void exitLockOption(MariaDBParser.LockOptionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code specificFunctionCall}
	 * labeled alternative in {@link MariaDBParser#functionCall}.
	 * @param ctx the parse tree
	 */
	void enterSpecificFunctionCall(MariaDBParser.SpecificFunctionCallContext ctx);
	/**
	 * Exit a parse tree produced by the {@code specificFunctionCall}
	 * labeled alternative in {@link MariaDBParser#functionCall}.
	 * @param ctx the parse tree
	 */
	void exitSpecificFunctionCall(MariaDBParser.SpecificFunctionCallContext ctx);
	/**
	 * Enter a parse tree produced by the {@code aggregateFunctionCall}
	 * labeled alternative in {@link MariaDBParser#functionCall}.
	 * @param ctx the parse tree
	 */
	void enterAggregateFunctionCall(MariaDBParser.AggregateFunctionCallContext ctx);
	/**
	 * Exit a parse tree produced by the {@code aggregateFunctionCall}
	 * labeled alternative in {@link MariaDBParser#functionCall}.
	 * @param ctx the parse tree
	 */
	void exitAggregateFunctionCall(MariaDBParser.AggregateFunctionCallContext ctx);
	/**
	 * Enter a parse tree produced by the {@code nonAggregateFunctionCall}
	 * labeled alternative in {@link MariaDBParser#functionCall}.
	 * @param ctx the parse tree
	 */
	void enterNonAggregateFunctionCall(MariaDBParser.NonAggregateFunctionCallContext ctx);
	/**
	 * Exit a parse tree produced by the {@code nonAggregateFunctionCall}
	 * labeled alternative in {@link MariaDBParser#functionCall}.
	 * @param ctx the parse tree
	 */
	void exitNonAggregateFunctionCall(MariaDBParser.NonAggregateFunctionCallContext ctx);
	/**
	 * Enter a parse tree produced by the {@code scalarFunctionCall}
	 * labeled alternative in {@link MariaDBParser#functionCall}.
	 * @param ctx the parse tree
	 */
	void enterScalarFunctionCall(MariaDBParser.ScalarFunctionCallContext ctx);
	/**
	 * Exit a parse tree produced by the {@code scalarFunctionCall}
	 * labeled alternative in {@link MariaDBParser#functionCall}.
	 * @param ctx the parse tree
	 */
	void exitScalarFunctionCall(MariaDBParser.ScalarFunctionCallContext ctx);
	/**
	 * Enter a parse tree produced by the {@code udfFunctionCall}
	 * labeled alternative in {@link MariaDBParser#functionCall}.
	 * @param ctx the parse tree
	 */
	void enterUdfFunctionCall(MariaDBParser.UdfFunctionCallContext ctx);
	/**
	 * Exit a parse tree produced by the {@code udfFunctionCall}
	 * labeled alternative in {@link MariaDBParser#functionCall}.
	 * @param ctx the parse tree
	 */
	void exitUdfFunctionCall(MariaDBParser.UdfFunctionCallContext ctx);
	/**
	 * Enter a parse tree produced by the {@code passwordFunctionCall}
	 * labeled alternative in {@link MariaDBParser#functionCall}.
	 * @param ctx the parse tree
	 */
	void enterPasswordFunctionCall(MariaDBParser.PasswordFunctionCallContext ctx);
	/**
	 * Exit a parse tree produced by the {@code passwordFunctionCall}
	 * labeled alternative in {@link MariaDBParser#functionCall}.
	 * @param ctx the parse tree
	 */
	void exitPasswordFunctionCall(MariaDBParser.PasswordFunctionCallContext ctx);
	/**
	 * Enter a parse tree produced by the {@code simpleFunctionCall}
	 * labeled alternative in {@link MariaDBParser#specificFunction}.
	 * @param ctx the parse tree
	 */
	void enterSimpleFunctionCall(MariaDBParser.SimpleFunctionCallContext ctx);
	/**
	 * Exit a parse tree produced by the {@code simpleFunctionCall}
	 * labeled alternative in {@link MariaDBParser#specificFunction}.
	 * @param ctx the parse tree
	 */
	void exitSimpleFunctionCall(MariaDBParser.SimpleFunctionCallContext ctx);
	/**
	 * Enter a parse tree produced by the {@code dataTypeFunctionCall}
	 * labeled alternative in {@link MariaDBParser#specificFunction}.
	 * @param ctx the parse tree
	 */
	void enterDataTypeFunctionCall(MariaDBParser.DataTypeFunctionCallContext ctx);
	/**
	 * Exit a parse tree produced by the {@code dataTypeFunctionCall}
	 * labeled alternative in {@link MariaDBParser#specificFunction}.
	 * @param ctx the parse tree
	 */
	void exitDataTypeFunctionCall(MariaDBParser.DataTypeFunctionCallContext ctx);
	/**
	 * Enter a parse tree produced by the {@code valuesFunctionCall}
	 * labeled alternative in {@link MariaDBParser#specificFunction}.
	 * @param ctx the parse tree
	 */
	void enterValuesFunctionCall(MariaDBParser.ValuesFunctionCallContext ctx);
	/**
	 * Exit a parse tree produced by the {@code valuesFunctionCall}
	 * labeled alternative in {@link MariaDBParser#specificFunction}.
	 * @param ctx the parse tree
	 */
	void exitValuesFunctionCall(MariaDBParser.ValuesFunctionCallContext ctx);
	/**
	 * Enter a parse tree produced by the {@code caseExpressionFunctionCall}
	 * labeled alternative in {@link MariaDBParser#specificFunction}.
	 * @param ctx the parse tree
	 */
	void enterCaseExpressionFunctionCall(MariaDBParser.CaseExpressionFunctionCallContext ctx);
	/**
	 * Exit a parse tree produced by the {@code caseExpressionFunctionCall}
	 * labeled alternative in {@link MariaDBParser#specificFunction}.
	 * @param ctx the parse tree
	 */
	void exitCaseExpressionFunctionCall(MariaDBParser.CaseExpressionFunctionCallContext ctx);
	/**
	 * Enter a parse tree produced by the {@code caseFunctionCall}
	 * labeled alternative in {@link MariaDBParser#specificFunction}.
	 * @param ctx the parse tree
	 */
	void enterCaseFunctionCall(MariaDBParser.CaseFunctionCallContext ctx);
	/**
	 * Exit a parse tree produced by the {@code caseFunctionCall}
	 * labeled alternative in {@link MariaDBParser#specificFunction}.
	 * @param ctx the parse tree
	 */
	void exitCaseFunctionCall(MariaDBParser.CaseFunctionCallContext ctx);
	/**
	 * Enter a parse tree produced by the {@code charFunctionCall}
	 * labeled alternative in {@link MariaDBParser#specificFunction}.
	 * @param ctx the parse tree
	 */
	void enterCharFunctionCall(MariaDBParser.CharFunctionCallContext ctx);
	/**
	 * Exit a parse tree produced by the {@code charFunctionCall}
	 * labeled alternative in {@link MariaDBParser#specificFunction}.
	 * @param ctx the parse tree
	 */
	void exitCharFunctionCall(MariaDBParser.CharFunctionCallContext ctx);
	/**
	 * Enter a parse tree produced by the {@code positionFunctionCall}
	 * labeled alternative in {@link MariaDBParser#specificFunction}.
	 * @param ctx the parse tree
	 */
	void enterPositionFunctionCall(MariaDBParser.PositionFunctionCallContext ctx);
	/**
	 * Exit a parse tree produced by the {@code positionFunctionCall}
	 * labeled alternative in {@link MariaDBParser#specificFunction}.
	 * @param ctx the parse tree
	 */
	void exitPositionFunctionCall(MariaDBParser.PositionFunctionCallContext ctx);
	/**
	 * Enter a parse tree produced by the {@code substrFunctionCall}
	 * labeled alternative in {@link MariaDBParser#specificFunction}.
	 * @param ctx the parse tree
	 */
	void enterSubstrFunctionCall(MariaDBParser.SubstrFunctionCallContext ctx);
	/**
	 * Exit a parse tree produced by the {@code substrFunctionCall}
	 * labeled alternative in {@link MariaDBParser#specificFunction}.
	 * @param ctx the parse tree
	 */
	void exitSubstrFunctionCall(MariaDBParser.SubstrFunctionCallContext ctx);
	/**
	 * Enter a parse tree produced by the {@code trimFunctionCall}
	 * labeled alternative in {@link MariaDBParser#specificFunction}.
	 * @param ctx the parse tree
	 */
	void enterTrimFunctionCall(MariaDBParser.TrimFunctionCallContext ctx);
	/**
	 * Exit a parse tree produced by the {@code trimFunctionCall}
	 * labeled alternative in {@link MariaDBParser#specificFunction}.
	 * @param ctx the parse tree
	 */
	void exitTrimFunctionCall(MariaDBParser.TrimFunctionCallContext ctx);
	/**
	 * Enter a parse tree produced by the {@code weightFunctionCall}
	 * labeled alternative in {@link MariaDBParser#specificFunction}.
	 * @param ctx the parse tree
	 */
	void enterWeightFunctionCall(MariaDBParser.WeightFunctionCallContext ctx);
	/**
	 * Exit a parse tree produced by the {@code weightFunctionCall}
	 * labeled alternative in {@link MariaDBParser#specificFunction}.
	 * @param ctx the parse tree
	 */
	void exitWeightFunctionCall(MariaDBParser.WeightFunctionCallContext ctx);
	/**
	 * Enter a parse tree produced by the {@code extractFunctionCall}
	 * labeled alternative in {@link MariaDBParser#specificFunction}.
	 * @param ctx the parse tree
	 */
	void enterExtractFunctionCall(MariaDBParser.ExtractFunctionCallContext ctx);
	/**
	 * Exit a parse tree produced by the {@code extractFunctionCall}
	 * labeled alternative in {@link MariaDBParser#specificFunction}.
	 * @param ctx the parse tree
	 */
	void exitExtractFunctionCall(MariaDBParser.ExtractFunctionCallContext ctx);
	/**
	 * Enter a parse tree produced by the {@code getFormatFunctionCall}
	 * labeled alternative in {@link MariaDBParser#specificFunction}.
	 * @param ctx the parse tree
	 */
	void enterGetFormatFunctionCall(MariaDBParser.GetFormatFunctionCallContext ctx);
	/**
	 * Exit a parse tree produced by the {@code getFormatFunctionCall}
	 * labeled alternative in {@link MariaDBParser#specificFunction}.
	 * @param ctx the parse tree
	 */
	void exitGetFormatFunctionCall(MariaDBParser.GetFormatFunctionCallContext ctx);
	/**
	 * Enter a parse tree produced by the {@code jsonValueFunctionCall}
	 * labeled alternative in {@link MariaDBParser#specificFunction}.
	 * @param ctx the parse tree
	 */
	void enterJsonValueFunctionCall(MariaDBParser.JsonValueFunctionCallContext ctx);
	/**
	 * Exit a parse tree produced by the {@code jsonValueFunctionCall}
	 * labeled alternative in {@link MariaDBParser#specificFunction}.
	 * @param ctx the parse tree
	 */
	void exitJsonValueFunctionCall(MariaDBParser.JsonValueFunctionCallContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#caseFuncAlternative}.
	 * @param ctx the parse tree
	 */
	void enterCaseFuncAlternative(MariaDBParser.CaseFuncAlternativeContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#caseFuncAlternative}.
	 * @param ctx the parse tree
	 */
	void exitCaseFuncAlternative(MariaDBParser.CaseFuncAlternativeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code levelWeightList}
	 * labeled alternative in {@link MariaDBParser#levelsInWeightString}.
	 * @param ctx the parse tree
	 */
	void enterLevelWeightList(MariaDBParser.LevelWeightListContext ctx);
	/**
	 * Exit a parse tree produced by the {@code levelWeightList}
	 * labeled alternative in {@link MariaDBParser#levelsInWeightString}.
	 * @param ctx the parse tree
	 */
	void exitLevelWeightList(MariaDBParser.LevelWeightListContext ctx);
	/**
	 * Enter a parse tree produced by the {@code levelWeightRange}
	 * labeled alternative in {@link MariaDBParser#levelsInWeightString}.
	 * @param ctx the parse tree
	 */
	void enterLevelWeightRange(MariaDBParser.LevelWeightRangeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code levelWeightRange}
	 * labeled alternative in {@link MariaDBParser#levelsInWeightString}.
	 * @param ctx the parse tree
	 */
	void exitLevelWeightRange(MariaDBParser.LevelWeightRangeContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#levelInWeightListElement}.
	 * @param ctx the parse tree
	 */
	void enterLevelInWeightListElement(MariaDBParser.LevelInWeightListElementContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#levelInWeightListElement}.
	 * @param ctx the parse tree
	 */
	void exitLevelInWeightListElement(MariaDBParser.LevelInWeightListElementContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#aggregateWindowedFunction}.
	 * @param ctx the parse tree
	 */
	void enterAggregateWindowedFunction(MariaDBParser.AggregateWindowedFunctionContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#aggregateWindowedFunction}.
	 * @param ctx the parse tree
	 */
	void exitAggregateWindowedFunction(MariaDBParser.AggregateWindowedFunctionContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#nonAggregateWindowedFunction}.
	 * @param ctx the parse tree
	 */
	void enterNonAggregateWindowedFunction(MariaDBParser.NonAggregateWindowedFunctionContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#nonAggregateWindowedFunction}.
	 * @param ctx the parse tree
	 */
	void exitNonAggregateWindowedFunction(MariaDBParser.NonAggregateWindowedFunctionContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#overClause}.
	 * @param ctx the parse tree
	 */
	void enterOverClause(MariaDBParser.OverClauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#overClause}.
	 * @param ctx the parse tree
	 */
	void exitOverClause(MariaDBParser.OverClauseContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#windowSpec}.
	 * @param ctx the parse tree
	 */
	void enterWindowSpec(MariaDBParser.WindowSpecContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#windowSpec}.
	 * @param ctx the parse tree
	 */
	void exitWindowSpec(MariaDBParser.WindowSpecContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#windowName}.
	 * @param ctx the parse tree
	 */
	void enterWindowName(MariaDBParser.WindowNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#windowName}.
	 * @param ctx the parse tree
	 */
	void exitWindowName(MariaDBParser.WindowNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#frameClause}.
	 * @param ctx the parse tree
	 */
	void enterFrameClause(MariaDBParser.FrameClauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#frameClause}.
	 * @param ctx the parse tree
	 */
	void exitFrameClause(MariaDBParser.FrameClauseContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#frameUnits}.
	 * @param ctx the parse tree
	 */
	void enterFrameUnits(MariaDBParser.FrameUnitsContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#frameUnits}.
	 * @param ctx the parse tree
	 */
	void exitFrameUnits(MariaDBParser.FrameUnitsContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#frameExtent}.
	 * @param ctx the parse tree
	 */
	void enterFrameExtent(MariaDBParser.FrameExtentContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#frameExtent}.
	 * @param ctx the parse tree
	 */
	void exitFrameExtent(MariaDBParser.FrameExtentContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#frameBetween}.
	 * @param ctx the parse tree
	 */
	void enterFrameBetween(MariaDBParser.FrameBetweenContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#frameBetween}.
	 * @param ctx the parse tree
	 */
	void exitFrameBetween(MariaDBParser.FrameBetweenContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#frameRange}.
	 * @param ctx the parse tree
	 */
	void enterFrameRange(MariaDBParser.FrameRangeContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#frameRange}.
	 * @param ctx the parse tree
	 */
	void exitFrameRange(MariaDBParser.FrameRangeContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#partitionClause}.
	 * @param ctx the parse tree
	 */
	void enterPartitionClause(MariaDBParser.PartitionClauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#partitionClause}.
	 * @param ctx the parse tree
	 */
	void exitPartitionClause(MariaDBParser.PartitionClauseContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#scalarFunctionName}.
	 * @param ctx the parse tree
	 */
	void enterScalarFunctionName(MariaDBParser.ScalarFunctionNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#scalarFunctionName}.
	 * @param ctx the parse tree
	 */
	void exitScalarFunctionName(MariaDBParser.ScalarFunctionNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#passwordFunctionClause}.
	 * @param ctx the parse tree
	 */
	void enterPasswordFunctionClause(MariaDBParser.PasswordFunctionClauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#passwordFunctionClause}.
	 * @param ctx the parse tree
	 */
	void exitPasswordFunctionClause(MariaDBParser.PasswordFunctionClauseContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#functionArgs}.
	 * @param ctx the parse tree
	 */
	void enterFunctionArgs(MariaDBParser.FunctionArgsContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#functionArgs}.
	 * @param ctx the parse tree
	 */
	void exitFunctionArgs(MariaDBParser.FunctionArgsContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#functionArg}.
	 * @param ctx the parse tree
	 */
	void enterFunctionArg(MariaDBParser.FunctionArgContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#functionArg}.
	 * @param ctx the parse tree
	 */
	void exitFunctionArg(MariaDBParser.FunctionArgContext ctx);
	/**
	 * Enter a parse tree produced by the {@code isExpression}
	 * labeled alternative in {@link MariaDBParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterIsExpression(MariaDBParser.IsExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code isExpression}
	 * labeled alternative in {@link MariaDBParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitIsExpression(MariaDBParser.IsExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code notExpression}
	 * labeled alternative in {@link MariaDBParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterNotExpression(MariaDBParser.NotExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code notExpression}
	 * labeled alternative in {@link MariaDBParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitNotExpression(MariaDBParser.NotExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code logicalExpression}
	 * labeled alternative in {@link MariaDBParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterLogicalExpression(MariaDBParser.LogicalExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code logicalExpression}
	 * labeled alternative in {@link MariaDBParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitLogicalExpression(MariaDBParser.LogicalExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code predicateExpression}
	 * labeled alternative in {@link MariaDBParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterPredicateExpression(MariaDBParser.PredicateExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code predicateExpression}
	 * labeled alternative in {@link MariaDBParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitPredicateExpression(MariaDBParser.PredicateExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code soundsLikePredicate}
	 * labeled alternative in {@link MariaDBParser#predicate}.
	 * @param ctx the parse tree
	 */
	void enterSoundsLikePredicate(MariaDBParser.SoundsLikePredicateContext ctx);
	/**
	 * Exit a parse tree produced by the {@code soundsLikePredicate}
	 * labeled alternative in {@link MariaDBParser#predicate}.
	 * @param ctx the parse tree
	 */
	void exitSoundsLikePredicate(MariaDBParser.SoundsLikePredicateContext ctx);
	/**
	 * Enter a parse tree produced by the {@code expressionAtomPredicate}
	 * labeled alternative in {@link MariaDBParser#predicate}.
	 * @param ctx the parse tree
	 */
	void enterExpressionAtomPredicate(MariaDBParser.ExpressionAtomPredicateContext ctx);
	/**
	 * Exit a parse tree produced by the {@code expressionAtomPredicate}
	 * labeled alternative in {@link MariaDBParser#predicate}.
	 * @param ctx the parse tree
	 */
	void exitExpressionAtomPredicate(MariaDBParser.ExpressionAtomPredicateContext ctx);
	/**
	 * Enter a parse tree produced by the {@code subqueryComparisonPredicate}
	 * labeled alternative in {@link MariaDBParser#predicate}.
	 * @param ctx the parse tree
	 */
	void enterSubqueryComparisonPredicate(MariaDBParser.SubqueryComparisonPredicateContext ctx);
	/**
	 * Exit a parse tree produced by the {@code subqueryComparisonPredicate}
	 * labeled alternative in {@link MariaDBParser#predicate}.
	 * @param ctx the parse tree
	 */
	void exitSubqueryComparisonPredicate(MariaDBParser.SubqueryComparisonPredicateContext ctx);
	/**
	 * Enter a parse tree produced by the {@code jsonMemberOfPredicate}
	 * labeled alternative in {@link MariaDBParser#predicate}.
	 * @param ctx the parse tree
	 */
	void enterJsonMemberOfPredicate(MariaDBParser.JsonMemberOfPredicateContext ctx);
	/**
	 * Exit a parse tree produced by the {@code jsonMemberOfPredicate}
	 * labeled alternative in {@link MariaDBParser#predicate}.
	 * @param ctx the parse tree
	 */
	void exitJsonMemberOfPredicate(MariaDBParser.JsonMemberOfPredicateContext ctx);
	/**
	 * Enter a parse tree produced by the {@code binaryComparisonPredicate}
	 * labeled alternative in {@link MariaDBParser#predicate}.
	 * @param ctx the parse tree
	 */
	void enterBinaryComparisonPredicate(MariaDBParser.BinaryComparisonPredicateContext ctx);
	/**
	 * Exit a parse tree produced by the {@code binaryComparisonPredicate}
	 * labeled alternative in {@link MariaDBParser#predicate}.
	 * @param ctx the parse tree
	 */
	void exitBinaryComparisonPredicate(MariaDBParser.BinaryComparisonPredicateContext ctx);
	/**
	 * Enter a parse tree produced by the {@code inPredicate}
	 * labeled alternative in {@link MariaDBParser#predicate}.
	 * @param ctx the parse tree
	 */
	void enterInPredicate(MariaDBParser.InPredicateContext ctx);
	/**
	 * Exit a parse tree produced by the {@code inPredicate}
	 * labeled alternative in {@link MariaDBParser#predicate}.
	 * @param ctx the parse tree
	 */
	void exitInPredicate(MariaDBParser.InPredicateContext ctx);
	/**
	 * Enter a parse tree produced by the {@code betweenPredicate}
	 * labeled alternative in {@link MariaDBParser#predicate}.
	 * @param ctx the parse tree
	 */
	void enterBetweenPredicate(MariaDBParser.BetweenPredicateContext ctx);
	/**
	 * Exit a parse tree produced by the {@code betweenPredicate}
	 * labeled alternative in {@link MariaDBParser#predicate}.
	 * @param ctx the parse tree
	 */
	void exitBetweenPredicate(MariaDBParser.BetweenPredicateContext ctx);
	/**
	 * Enter a parse tree produced by the {@code isNullPredicate}
	 * labeled alternative in {@link MariaDBParser#predicate}.
	 * @param ctx the parse tree
	 */
	void enterIsNullPredicate(MariaDBParser.IsNullPredicateContext ctx);
	/**
	 * Exit a parse tree produced by the {@code isNullPredicate}
	 * labeled alternative in {@link MariaDBParser#predicate}.
	 * @param ctx the parse tree
	 */
	void exitIsNullPredicate(MariaDBParser.IsNullPredicateContext ctx);
	/**
	 * Enter a parse tree produced by the {@code likePredicate}
	 * labeled alternative in {@link MariaDBParser#predicate}.
	 * @param ctx the parse tree
	 */
	void enterLikePredicate(MariaDBParser.LikePredicateContext ctx);
	/**
	 * Exit a parse tree produced by the {@code likePredicate}
	 * labeled alternative in {@link MariaDBParser#predicate}.
	 * @param ctx the parse tree
	 */
	void exitLikePredicate(MariaDBParser.LikePredicateContext ctx);
	/**
	 * Enter a parse tree produced by the {@code regexpPredicate}
	 * labeled alternative in {@link MariaDBParser#predicate}.
	 * @param ctx the parse tree
	 */
	void enterRegexpPredicate(MariaDBParser.RegexpPredicateContext ctx);
	/**
	 * Exit a parse tree produced by the {@code regexpPredicate}
	 * labeled alternative in {@link MariaDBParser#predicate}.
	 * @param ctx the parse tree
	 */
	void exitRegexpPredicate(MariaDBParser.RegexpPredicateContext ctx);
	/**
	 * Enter a parse tree produced by the {@code unaryExpressionAtom}
	 * labeled alternative in {@link MariaDBParser#expressionAtom}.
	 * @param ctx the parse tree
	 */
	void enterUnaryExpressionAtom(MariaDBParser.UnaryExpressionAtomContext ctx);
	/**
	 * Exit a parse tree produced by the {@code unaryExpressionAtom}
	 * labeled alternative in {@link MariaDBParser#expressionAtom}.
	 * @param ctx the parse tree
	 */
	void exitUnaryExpressionAtom(MariaDBParser.UnaryExpressionAtomContext ctx);
	/**
	 * Enter a parse tree produced by the {@code collateExpressionAtom}
	 * labeled alternative in {@link MariaDBParser#expressionAtom}.
	 * @param ctx the parse tree
	 */
	void enterCollateExpressionAtom(MariaDBParser.CollateExpressionAtomContext ctx);
	/**
	 * Exit a parse tree produced by the {@code collateExpressionAtom}
	 * labeled alternative in {@link MariaDBParser#expressionAtom}.
	 * @param ctx the parse tree
	 */
	void exitCollateExpressionAtom(MariaDBParser.CollateExpressionAtomContext ctx);
	/**
	 * Enter a parse tree produced by the {@code variableAssignExpressionAtom}
	 * labeled alternative in {@link MariaDBParser#expressionAtom}.
	 * @param ctx the parse tree
	 */
	void enterVariableAssignExpressionAtom(MariaDBParser.VariableAssignExpressionAtomContext ctx);
	/**
	 * Exit a parse tree produced by the {@code variableAssignExpressionAtom}
	 * labeled alternative in {@link MariaDBParser#expressionAtom}.
	 * @param ctx the parse tree
	 */
	void exitVariableAssignExpressionAtom(MariaDBParser.VariableAssignExpressionAtomContext ctx);
	/**
	 * Enter a parse tree produced by the {@code mysqlVariableExpressionAtom}
	 * labeled alternative in {@link MariaDBParser#expressionAtom}.
	 * @param ctx the parse tree
	 */
	void enterMysqlVariableExpressionAtom(MariaDBParser.MysqlVariableExpressionAtomContext ctx);
	/**
	 * Exit a parse tree produced by the {@code mysqlVariableExpressionAtom}
	 * labeled alternative in {@link MariaDBParser#expressionAtom}.
	 * @param ctx the parse tree
	 */
	void exitMysqlVariableExpressionAtom(MariaDBParser.MysqlVariableExpressionAtomContext ctx);
	/**
	 * Enter a parse tree produced by the {@code nestedExpressionAtom}
	 * labeled alternative in {@link MariaDBParser#expressionAtom}.
	 * @param ctx the parse tree
	 */
	void enterNestedExpressionAtom(MariaDBParser.NestedExpressionAtomContext ctx);
	/**
	 * Exit a parse tree produced by the {@code nestedExpressionAtom}
	 * labeled alternative in {@link MariaDBParser#expressionAtom}.
	 * @param ctx the parse tree
	 */
	void exitNestedExpressionAtom(MariaDBParser.NestedExpressionAtomContext ctx);
	/**
	 * Enter a parse tree produced by the {@code nestedRowExpressionAtom}
	 * labeled alternative in {@link MariaDBParser#expressionAtom}.
	 * @param ctx the parse tree
	 */
	void enterNestedRowExpressionAtom(MariaDBParser.NestedRowExpressionAtomContext ctx);
	/**
	 * Exit a parse tree produced by the {@code nestedRowExpressionAtom}
	 * labeled alternative in {@link MariaDBParser#expressionAtom}.
	 * @param ctx the parse tree
	 */
	void exitNestedRowExpressionAtom(MariaDBParser.NestedRowExpressionAtomContext ctx);
	/**
	 * Enter a parse tree produced by the {@code mathExpressionAtom}
	 * labeled alternative in {@link MariaDBParser#expressionAtom}.
	 * @param ctx the parse tree
	 */
	void enterMathExpressionAtom(MariaDBParser.MathExpressionAtomContext ctx);
	/**
	 * Exit a parse tree produced by the {@code mathExpressionAtom}
	 * labeled alternative in {@link MariaDBParser#expressionAtom}.
	 * @param ctx the parse tree
	 */
	void exitMathExpressionAtom(MariaDBParser.MathExpressionAtomContext ctx);
	/**
	 * Enter a parse tree produced by the {@code existsExpressionAtom}
	 * labeled alternative in {@link MariaDBParser#expressionAtom}.
	 * @param ctx the parse tree
	 */
	void enterExistsExpressionAtom(MariaDBParser.ExistsExpressionAtomContext ctx);
	/**
	 * Exit a parse tree produced by the {@code existsExpressionAtom}
	 * labeled alternative in {@link MariaDBParser#expressionAtom}.
	 * @param ctx the parse tree
	 */
	void exitExistsExpressionAtom(MariaDBParser.ExistsExpressionAtomContext ctx);
	/**
	 * Enter a parse tree produced by the {@code intervalExpressionAtom}
	 * labeled alternative in {@link MariaDBParser#expressionAtom}.
	 * @param ctx the parse tree
	 */
	void enterIntervalExpressionAtom(MariaDBParser.IntervalExpressionAtomContext ctx);
	/**
	 * Exit a parse tree produced by the {@code intervalExpressionAtom}
	 * labeled alternative in {@link MariaDBParser#expressionAtom}.
	 * @param ctx the parse tree
	 */
	void exitIntervalExpressionAtom(MariaDBParser.IntervalExpressionAtomContext ctx);
	/**
	 * Enter a parse tree produced by the {@code jsonExpressionAtom}
	 * labeled alternative in {@link MariaDBParser#expressionAtom}.
	 * @param ctx the parse tree
	 */
	void enterJsonExpressionAtom(MariaDBParser.JsonExpressionAtomContext ctx);
	/**
	 * Exit a parse tree produced by the {@code jsonExpressionAtom}
	 * labeled alternative in {@link MariaDBParser#expressionAtom}.
	 * @param ctx the parse tree
	 */
	void exitJsonExpressionAtom(MariaDBParser.JsonExpressionAtomContext ctx);
	/**
	 * Enter a parse tree produced by the {@code subqueryExpressionAtom}
	 * labeled alternative in {@link MariaDBParser#expressionAtom}.
	 * @param ctx the parse tree
	 */
	void enterSubqueryExpressionAtom(MariaDBParser.SubqueryExpressionAtomContext ctx);
	/**
	 * Exit a parse tree produced by the {@code subqueryExpressionAtom}
	 * labeled alternative in {@link MariaDBParser#expressionAtom}.
	 * @param ctx the parse tree
	 */
	void exitSubqueryExpressionAtom(MariaDBParser.SubqueryExpressionAtomContext ctx);
	/**
	 * Enter a parse tree produced by the {@code constantExpressionAtom}
	 * labeled alternative in {@link MariaDBParser#expressionAtom}.
	 * @param ctx the parse tree
	 */
	void enterConstantExpressionAtom(MariaDBParser.ConstantExpressionAtomContext ctx);
	/**
	 * Exit a parse tree produced by the {@code constantExpressionAtom}
	 * labeled alternative in {@link MariaDBParser#expressionAtom}.
	 * @param ctx the parse tree
	 */
	void exitConstantExpressionAtom(MariaDBParser.ConstantExpressionAtomContext ctx);
	/**
	 * Enter a parse tree produced by the {@code functionCallExpressionAtom}
	 * labeled alternative in {@link MariaDBParser#expressionAtom}.
	 * @param ctx the parse tree
	 */
	void enterFunctionCallExpressionAtom(MariaDBParser.FunctionCallExpressionAtomContext ctx);
	/**
	 * Exit a parse tree produced by the {@code functionCallExpressionAtom}
	 * labeled alternative in {@link MariaDBParser#expressionAtom}.
	 * @param ctx the parse tree
	 */
	void exitFunctionCallExpressionAtom(MariaDBParser.FunctionCallExpressionAtomContext ctx);
	/**
	 * Enter a parse tree produced by the {@code binaryExpressionAtom}
	 * labeled alternative in {@link MariaDBParser#expressionAtom}.
	 * @param ctx the parse tree
	 */
	void enterBinaryExpressionAtom(MariaDBParser.BinaryExpressionAtomContext ctx);
	/**
	 * Exit a parse tree produced by the {@code binaryExpressionAtom}
	 * labeled alternative in {@link MariaDBParser#expressionAtom}.
	 * @param ctx the parse tree
	 */
	void exitBinaryExpressionAtom(MariaDBParser.BinaryExpressionAtomContext ctx);
	/**
	 * Enter a parse tree produced by the {@code fullColumnNameExpressionAtom}
	 * labeled alternative in {@link MariaDBParser#expressionAtom}.
	 * @param ctx the parse tree
	 */
	void enterFullColumnNameExpressionAtom(MariaDBParser.FullColumnNameExpressionAtomContext ctx);
	/**
	 * Exit a parse tree produced by the {@code fullColumnNameExpressionAtom}
	 * labeled alternative in {@link MariaDBParser#expressionAtom}.
	 * @param ctx the parse tree
	 */
	void exitFullColumnNameExpressionAtom(MariaDBParser.FullColumnNameExpressionAtomContext ctx);
	/**
	 * Enter a parse tree produced by the {@code bitExpressionAtom}
	 * labeled alternative in {@link MariaDBParser#expressionAtom}.
	 * @param ctx the parse tree
	 */
	void enterBitExpressionAtom(MariaDBParser.BitExpressionAtomContext ctx);
	/**
	 * Exit a parse tree produced by the {@code bitExpressionAtom}
	 * labeled alternative in {@link MariaDBParser#expressionAtom}.
	 * @param ctx the parse tree
	 */
	void exitBitExpressionAtom(MariaDBParser.BitExpressionAtomContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#unaryOperator}.
	 * @param ctx the parse tree
	 */
	void enterUnaryOperator(MariaDBParser.UnaryOperatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#unaryOperator}.
	 * @param ctx the parse tree
	 */
	void exitUnaryOperator(MariaDBParser.UnaryOperatorContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#comparisonOperator}.
	 * @param ctx the parse tree
	 */
	void enterComparisonOperator(MariaDBParser.ComparisonOperatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#comparisonOperator}.
	 * @param ctx the parse tree
	 */
	void exitComparisonOperator(MariaDBParser.ComparisonOperatorContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#logicalOperator}.
	 * @param ctx the parse tree
	 */
	void enterLogicalOperator(MariaDBParser.LogicalOperatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#logicalOperator}.
	 * @param ctx the parse tree
	 */
	void exitLogicalOperator(MariaDBParser.LogicalOperatorContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#bitOperator}.
	 * @param ctx the parse tree
	 */
	void enterBitOperator(MariaDBParser.BitOperatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#bitOperator}.
	 * @param ctx the parse tree
	 */
	void exitBitOperator(MariaDBParser.BitOperatorContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#mathOperator}.
	 * @param ctx the parse tree
	 */
	void enterMathOperator(MariaDBParser.MathOperatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#mathOperator}.
	 * @param ctx the parse tree
	 */
	void exitMathOperator(MariaDBParser.MathOperatorContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#jsonOperator}.
	 * @param ctx the parse tree
	 */
	void enterJsonOperator(MariaDBParser.JsonOperatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#jsonOperator}.
	 * @param ctx the parse tree
	 */
	void exitJsonOperator(MariaDBParser.JsonOperatorContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#charsetNameBase}.
	 * @param ctx the parse tree
	 */
	void enterCharsetNameBase(MariaDBParser.CharsetNameBaseContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#charsetNameBase}.
	 * @param ctx the parse tree
	 */
	void exitCharsetNameBase(MariaDBParser.CharsetNameBaseContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#transactionLevelBase}.
	 * @param ctx the parse tree
	 */
	void enterTransactionLevelBase(MariaDBParser.TransactionLevelBaseContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#transactionLevelBase}.
	 * @param ctx the parse tree
	 */
	void exitTransactionLevelBase(MariaDBParser.TransactionLevelBaseContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#privilegesBase}.
	 * @param ctx the parse tree
	 */
	void enterPrivilegesBase(MariaDBParser.PrivilegesBaseContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#privilegesBase}.
	 * @param ctx the parse tree
	 */
	void exitPrivilegesBase(MariaDBParser.PrivilegesBaseContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#intervalTypeBase}.
	 * @param ctx the parse tree
	 */
	void enterIntervalTypeBase(MariaDBParser.IntervalTypeBaseContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#intervalTypeBase}.
	 * @param ctx the parse tree
	 */
	void exitIntervalTypeBase(MariaDBParser.IntervalTypeBaseContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#dataTypeBase}.
	 * @param ctx the parse tree
	 */
	void enterDataTypeBase(MariaDBParser.DataTypeBaseContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#dataTypeBase}.
	 * @param ctx the parse tree
	 */
	void exitDataTypeBase(MariaDBParser.DataTypeBaseContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#keywordsCanBeId}.
	 * @param ctx the parse tree
	 */
	void enterKeywordsCanBeId(MariaDBParser.KeywordsCanBeIdContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#keywordsCanBeId}.
	 * @param ctx the parse tree
	 */
	void exitKeywordsCanBeId(MariaDBParser.KeywordsCanBeIdContext ctx);
	/**
	 * Enter a parse tree produced by {@link MariaDBParser#functionNameBase}.
	 * @param ctx the parse tree
	 */
	void enterFunctionNameBase(MariaDBParser.FunctionNameBaseContext ctx);
	/**
	 * Exit a parse tree produced by {@link MariaDBParser#functionNameBase}.
	 * @param ctx the parse tree
	 */
	void exitFunctionNameBase(MariaDBParser.FunctionNameBaseContext ctx);
}