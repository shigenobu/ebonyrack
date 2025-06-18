package com.walksocket.er.sqlite;

import com.walksocket.antlr4.MariaDBLexer;
import com.walksocket.antlr4.MariaDBParser;
import com.walksocket.er.Log;
import com.walksocket.er.Utils;
import com.walksocket.er.component.main.root.workspace.Sequence;
import com.walksocket.er.parse.SequenceListener;
import com.walksocket.er.sqlite.context.CtxSequence;
import com.walksocket.er.sqlite.entity.DbSequence;
import com.walksocket.er.sqlite.tmp.TmpSequence;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

/**
 * ImportSequence.
 */
public class ImportSequence {

  /**
   * con.
   */
  private final Connection con;

  /**
   * created sequence names.
   */
  private final List<String> createdSequenceNames = new ArrayList<>();

  /**
   * Constructor.
   *
   * @param con con
   * @throws SQLException
   */
  public ImportSequence(Connection con) throws SQLException {
    this.con = con;
  }

  /***
   * add existing sequences.
   *
   * @param sequenceNames sequenceNames
   */
  public void addExistingSequences(List<String> sequenceNames) {
    createdSequenceNames.addAll(sequenceNames);
  }

  /**
   * create sequence and get.
   *
   * @param ddl ddl
   * @return ctx
   * @throws SQLException
   */
  public CtxSequence createSequenceAndGet(String ddl) throws SQLException {
    if (createdSequenceNames.size() > Sequence.MAX_POSITIONED) {
      return null;
    }

    // parse
    Log.trace(ddl);
    var stream = CharStreams.fromString(ddl);
    var lexer = new MariaDBLexer(stream);
    var tokens = new CommonTokenStream(lexer);
    var parser = new MariaDBParser(tokens);

    var tmpSequence = new TmpSequence();
    var listener = new SequenceListener(tmpSequence);
    ParseTreeWalker.DEFAULT.walk(listener, parser.root());

    if (Utils.isNullOrEmpty(tmpSequence.sequenceName)) {
      return null;
    }
    if (createdSequenceNames.contains(tmpSequence.sequenceName)) {
      return null;
    }

    var ctxSequence = new CtxSequence();

    // DbSequence
    var dbSequence = new DbSequence();
    dbSequence.sequenceId = Utils.randomString();
    dbSequence.sequenceName = tmpSequence.sequenceName;
    if (!Utils.isNullOrEmpty(tmpSequence.startValue)) {
      dbSequence.startValue = tmpSequence.startValue;
    }
    if (!Utils.isNullOrEmpty(tmpSequence.minimumValue)) {
      dbSequence.minimumValue = tmpSequence.minimumValue;
    }
    if (!Utils.isNullOrEmpty(tmpSequence.maximumValue)) {
      dbSequence.maximumValue = tmpSequence.maximumValue;
    }
    if (!Utils.isNullOrEmpty(tmpSequence.incrementValue)) {
      dbSequence.incrementValue = tmpSequence.incrementValue;
    }
    if (!Utils.isNullOrEmpty(tmpSequence.cacheSize)) {
      dbSequence.cacheSize = tmpSequence.cacheSize;
    }
    if (!Utils.isNullOrEmpty(tmpSequence.cycle)) {
      dbSequence.cycle = tmpSequence.cycle;
    }
    con.executeInsert(dbSequence);
    createdSequenceNames.add(dbSequence.sequenceName);
    ctxSequence.dbSequence = dbSequence;

    return ctxSequence;
  }
}
