package com.walksocket.er.definition;

import java.util.ArrayList;
import java.util.List;

/**
 * Key.
 */
public class Key {

  /**
   * primary key name value.
   */
  public static final String PRIMARY_KEY_NAME_VALUE = "PRIMARY";

  /**
   * reference option list.
   */
  private static final List<String> referenceOptionList = new ArrayList<>();

  static {
    referenceOptionList.add("");
    referenceOptionList.add("RESTRICT");
    referenceOptionList.add("CASCADE");
    referenceOptionList.add("SET NULL");
  }

  /**
   * get reference option list.
   *
   * @return reference option list
   */
  public static List<String> getReferenceOptionList() {
    return referenceOptionList;
  }
}
