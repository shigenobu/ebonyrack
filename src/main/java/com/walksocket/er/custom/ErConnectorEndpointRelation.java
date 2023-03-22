package com.walksocket.er.custom;

import java.awt.Color;
import java.util.List;

/**
 * ErConnectorEndpointRelation.
 */
public interface ErConnectorEndpointRelation {

  /**
   * get name for sort.
   *
   * @return name for sort
   */
  String getNameForSort();

  /**
   * strengthening.
   *
   * @param data  data
   * @param color color
   */
  void strengthening(List<String> data, Color color);

  /**
   * weakening.
   *
   * @param data data
   */
  void weakening(List<String> data);
}
