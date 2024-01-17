package com.walksocket.er.parse;

import com.walksocket.er.antlr4.MariaDBParser.CreateSequenceContext;
import com.walksocket.er.antlr4.MariaDBParserBaseListener;

public class SequenceListener extends MariaDBParserBaseListener {

  @Override
  public void enterCreateSequence(CreateSequenceContext ctx) {
    System.out.println(ctx.fullId().getText());
//    for (var s : ctx.sequenceSpec()) {
//      System.out.println(s.BY().getText());
//    }
  }
}
