package com.walksocket.er.sqlite.tmp;

import com.walksocket.er.Utils;
import com.walksocket.er.sqlite.Tmp;

/**
 * TmpForeignKeyInfo.
 */
public class TmpForeignKeyInfo implements Tmp {

  /**
   * filter none.
   */
  public static final String FILTER_NONE = "None";

  /**
   * filter contains.
   */
  public static final String FILTER_CONTAINS = "Contains";

  /**
   * filter start with.
   */
  public static final String FILTER_START_WITH = "Start with";

  /**
   * filter end with.
   */
  public static final String FILTER_END_WITH = "End with";

  /**
   * filter contains in list.
   */
  public static final String FILTER_CONTAINS_IN_LIST = "Contains in list";

  /**
   * filter table action command.
   */
  public String filterTableActionCommand;

  /**
   * filter table value.
   */
  public String filterTableValue;

  /**
   * filter reference table action command.
   */
  public String filterReferenceTableActionCommand;

  /**
   * filter reference table value.
   */
  public String filterReferenceTableValue;

  /**
   * save path.
   */
  public String savePath;

  /**
   * get hash.
   *
   * @return hash
   */
  public String getHash() {
    return Utils.getHash(String.format("%s-%s-%s-%s-%s",
        filterTableActionCommand, filterTableValue,
        filterReferenceTableActionCommand, filterReferenceTableValue,
        savePath));
  }
}
