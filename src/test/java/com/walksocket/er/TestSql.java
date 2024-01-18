package com.walksocket.er;

import com.walksocket.er.config.CfgProject;
import com.walksocket.er.sqlite.Dump;
import java.io.IOException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class TestSql {

  private static final String path = "";

  @BeforeAll
  public static void beforeClass() throws IOException {
    Log.open("STDOUT");
    Env.setDebug();
  }

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
    var cfgProject = new CfgProject();
    cfgProject.dbPath = "test.sqlite3";
    Dump.importFromDdl(cfgProject, path);
  }
}
