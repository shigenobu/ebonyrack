package com.walksocket.er.template;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.text.StringEscapeUtils;
import org.apache.velocity.app.event.ReferenceInsertionEventHandler;
import org.apache.velocity.context.Context;

/**
 * ErTemplateHandler.
 */
public class ErTemplateHandler implements ReferenceInsertionEventHandler {

  /**
   * non escape Strings.
   */
  private static final List<String> nonEscapes = new ArrayList<String>();

  static {
    nonEscapes.add("${" + ErTemplateUtils.class.getSimpleName() + ".");
    nonEscapes.add("$" + ErTemplateUtils.class.getSimpleName() + ".");
  }

  @Override
  public Object referenceInsert(Context context, String reference, Object value) {
    if (value == null) {
      return "";
    }
    var opt = nonEscapes.stream()
        .filter(nonEscape -> reference.startsWith(nonEscape))
        .findFirst();
    if (opt.isPresent()) {
      return value;
    }
    return StringEscapeUtils.escapeXml11(value.toString());
  }
}