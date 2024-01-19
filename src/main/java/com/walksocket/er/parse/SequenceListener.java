package com.walksocket.er.parse;

import com.walksocket.antlr4.MariaDBParser.CreateSequenceContext;
import com.walksocket.antlr4.MariaDBParser.DecimalLiteralContext;
import com.walksocket.antlr4.MariaDBParserBaseListener;
import com.walksocket.er.Json;
import com.walksocket.er.Log;
import com.walksocket.er.Utils;
import com.walksocket.er.sqlite.tmp.TmpSequence;

/**
 * SequenceListener.
 */
public class SequenceListener extends MariaDBParserBaseListener {

  /**
   * tmpSequence.
   */
  private final TmpSequence tmpSequence;

  /**
   * Constructor.
   *
   * @param tmpSequence tmpSequence
   */
  public SequenceListener(TmpSequence tmpSequence) {
    this.tmpSequence = tmpSequence;
  }

  @Override
  public void enterCreateSequence(CreateSequenceContext ctx) {
    tmpSequence.sequenceName = Utils.removeBackQuote(ctx.fullId().getText());

    for (var s : ctx.sequenceSpec()) {
      for (int i = 0; i < s.getChildCount(); i++) {
        var c = s.getChild(i);
        if (c == s.START() && s.getChildCount() > (i + 2)) {
          if (s.getChild(i + 2) instanceof DecimalLiteralContext d) {
            tmpSequence.startValue = d.getText();
          }
        }

        if (c == s.MINVALUE() && s.getChildCount() > (i + 1)) {
          if (s.getChild(i + 1) instanceof DecimalLiteralContext d) {
            tmpSequence.minimumValue = d.getText();
          }
        }
        if (c == s.NOMINVALUE()) {
          tmpSequence.minimumValue = c.getText();
        }

        if (c == s.MAXVALUE() && s.getChildCount() > (i + 1)) {
          if (s.getChild(i + 1) instanceof DecimalLiteralContext d) {
            tmpSequence.maximumValue = d.getText();
          }
        }
        if (c == s.NOMAXVALUE()) {
          tmpSequence.maximumValue = c.getText();
        }

        if (c == s.INCREMENT() && s.getChildCount() > (i + 2)) {
          if (s.getChild(i + 2) instanceof DecimalLiteralContext d) {
            tmpSequence.incrementValue = d.getText();
          }
        }

        if (c == s.CACHE() && s.getChildCount() > (i + 1)) {
          if (s.getChild(i + 1) instanceof DecimalLiteralContext d) {
            tmpSequence.cacheSize = d.getText();
          }
        }
        if (c == s.NOCACHE()) {
          tmpSequence.cacheSize = c.getText();
        }

        if (c == s.CYCLE() || c == s.NOCYCLE()) {
          tmpSequence.cycle = c.getText().toUpperCase();
        }
      }
    }
    Log.trace(Json.toJsonString(tmpSequence));
  }
}
