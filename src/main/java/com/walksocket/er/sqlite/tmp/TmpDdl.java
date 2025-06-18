package com.walksocket.er.sqlite.tmp;

import com.walksocket.er.Utils;
import com.walksocket.er.sqlite.Tmp;

/**
 * TmpDdl.
 */
public class TmpDdl implements Tmp {

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
   * schema value.
   */
  public String schemaValue;

  /**
   * selected sequence.
   */
  public boolean selectedSequence;

  /**
   * selected table.
   */
  public boolean selectedTable;

  /**
   * selected foreign key.
   */
  public boolean selectedForeignKey;

  /**
   * filter sequence action command.
   */
  public String filterSequenceActionCommand;

  /**
   * filter sequence value.
   */
  public String filterSequenceValue;

  /**
   * filter table action command.
   */
  public String filterTableActionCommand;

  /**
   * filter table value.
   */
  public String filterTableValue;

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
    return Utils.getHash(String.format("%s-%s-%s-%s-%s-%s-%s-%s-%s",
        schemaValue,
        selectedSequence, filterSequenceActionCommand, filterSequenceValue,
        selectedTable, selectedForeignKey, filterTableActionCommand, filterTableValue,
        savePath));
  }
}
