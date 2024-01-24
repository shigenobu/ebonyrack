package com.walksocket.er;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Json.
 */
public class Json {

  /**
   * gson.
   */
  private static final Gson gson
      = new GsonBuilder()
      .serializeNulls()
      .create();

  /**
   * gson pretty.
   */
  private static final Gson gsonPretty
      = new GsonBuilder()
      .serializeNulls()
      .setPrettyPrinting()
      .create();

  /**
   * constructor.
   */
  private Json() {
  }

  /**
   * to object from json string.
   *
   * @param json json string
   * @param cls  binding class
   * @param <T>  binding type
   * @return binding object
   */
  public static <T> T toObject(String json, Class<T> cls) {
    if (json == null) {
      return null;
    }

    try {
      return gson.fromJson(json, cls);
    } catch (Exception e) {
      Log.error(e);
    }
    return null;
  }

  /**
   * to json string from object.
   *
   * @param src object
   * @return json string
   */
  public static String toJsonString(Object src) {
    if (src == null) {
      return null;
    }

    try {
      return gson.toJson(src);
    } catch (Exception e) {
      Log.error(e);
    }
    return null;
  }

  /**
   * to json string from object at friendly.
   *
   * @param src object
   * @return json string
   */
  public static String toJsonStringFriendly(Object src) {
    if (src == null) {
      return null;
    }

    try {
      return gsonPretty.toJson(src);
    } catch (Exception e) {
      Log.error(e);
    }
    return null;
  }

  /**
   * copy.
   *
   * @param src src
   * @param cls class
   * @param <T> type
   * @return obj
   */
  public static <T> T copy(T src, Class<T> cls) {
    if (src == null) {
      return null;
    }

    try {
      var json = toJsonString(src);
      return toObject(json, cls);
    } catch (Exception e) {
      Log.error(e);
    }
    return null;
  }
}
