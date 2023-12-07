package com.walksocket.er.config;

/**
 * CfgProject.
 */
public class CfgProject {

  /**
   * name.
   */
  public String name;

  /**
   * db path.
   */
  public String dbPath;

  /**
   * last opened.
   */
  public int lastOpened;

  /**
   * last ddl save path.
   */
  public String lastDdlSavePath;

  /**
   * last image save path.
   */
  public String lastImageSavePath;

  /**
   * last html save path.
   */
  public String lastHtmlSavePath;

  /**
   * last write out path.
   */
  public String lastWriteOutPath;

  /**
   * last read from path.
   */
  public String lastReadFromPath;

  /**
   * pos.
   */
  public CfgProjectPos pos = new CfgProjectPos();
}
