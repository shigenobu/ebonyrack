package com.walksocket.er;

import com.walksocket.er.config.CfgProject;
import com.walksocket.er.sqlite.Dump;
import java.io.IOException;
import org.junit.jupiter.api.Test;

public class TestSql {

  private static final String path = "src/test/data/1_base.sql";

  @Test
  public void testParseCreateSequence() throws IOException {
    var ddlList = Dump.parseCreateSequence(path);
    for (var ddl : ddlList) {
      System.out.println(ddl);
    }
  }

  @Test
  public void testParseCreateTable() throws IOException {
    var ddlList = Dump.parseCreateTable(path);
    for (var ddl : ddlList) {
      System.out.println(ddl);
    }
  }

  @Test
  public void testParse() throws IOException {
    Dump.importFromDdl(new CfgProject(), path);
  }
}
