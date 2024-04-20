package com.walksocket.er.custom;

import com.walksocket.er.Log;
import java.util.Arrays;

/**
 * ErHeaderFormatter.
 */
public class ErHeaderFormatter {

  /**
   * Type.
   */
  public enum Type {
    /**
     * ordinal.
     */
    ordinal("", ""),

    /**
     * required.
     */
    required("<b>", "</b>"),

    /**
     * openDialog.
     */
    openDialog("<font color=red>", "</font>"),

    /**
     * showOnly.
     */
    showOnly("<font color=gray>", "</font>"),
    ;

    /**
     * begin.
     */
    private final String begin;

    /**
     * end.
     */
    private final String end;

    /**
     * Constructor.
     *
     * @param begin begin
     * @param end   end
     */
    Type(String begin, String end) {
      this.begin = begin;
      this.end = end;
    }

    /**
     * block begin.
     *
     * @return block
     */
    public String blockBegin() {
      return begin;
    }

    /**
     * block end.
     *
     * @return block
     */
    public String blockEnd() {
      return end;
    }
  }

  /**
   * format.
   *
   * @param name  name
   * @param types types
   * @return block
   */
  public static String format(String name, Type... types) {
    var typeList = Arrays.asList(types);
    var builder = new StringBuilder("<html>");
    for (int i = 0; i < Type.values().length; i++) {
      var t = Type.values()[i];
      if (typeList.contains(t)) {
        builder.append(t.blockBegin());
      }
    }
    builder.append(name);
    for (int i = Type.values().length - 1; i >= 0; i--) {
      var t = Type.values()[i];
      if (typeList.contains(t)) {
        builder.append(t.blockEnd());
      }
    }
    builder.append("</html>");
    Log.trace(builder.toString());
    return builder.toString();
  }
}
