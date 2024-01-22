package com.walksocket.er.template;

import org.apache.velocity.app.event.ReferenceInsertionEventHandler;
import org.apache.velocity.context.Context;

/**
 * ErTemplateNoEscapeHandler.
 */
public class ErTemplateNoEscapeHandler implements ReferenceInsertionEventHandler {

  @Override
  public Object referenceInsert(Context context, String reference, Object value) {
    return value;
  }
}
