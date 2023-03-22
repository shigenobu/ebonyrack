package com.walksocket.er;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Word.
 */
public class Word {

  /**
   * reserved words.
   */
  private static final List<String> reservedWords = new ArrayList<>();

  static {
    try (var stream = App.class.getClassLoader()
        .getResourceAsStream("database/reserved.txt")) {
      var lines = File.readString(stream).split("\n");
      for (var w : lines) {
        w = w.trim();
        if (Utils.isNullOrEmpty(w)) {
          continue;
        }
        reservedWords.add(w);
      }
    } catch (IOException e) {
      Log.error(e);
    }
  }

  /**
   * name pattern.
   */
  private static final Pattern namePattern = Pattern.compile("^[0-9a-zA-Z\\$_]+$");

  /**
   * is valid.
   *
   * @param someName someName
   * @return if empty, true
   */
  public static boolean isValid(String someName) {
    if (!namePattern.matcher(someName).find()) {
      return false;
    }

    return !(reservedWords.stream()
        .filter(w -> w.equalsIgnoreCase(someName))
        .findFirst()
        .isPresent());
  }
}
