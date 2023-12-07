package com.walksocket.er;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Optional;

/**
 * Value.
 */
public interface Value {

  /**
   * get for velocity.
   *
   * @param name name
   * @return value
   */
  default Object get(String name) {
    try {
      Field[] fields = getClass().getFields();
      Optional<Field> opt = Arrays.asList(fields)
          .stream()
          .filter(field -> field.getName().equals(name))
          .findFirst();
      if (opt.isPresent() && opt.get().get(this) != null) {
        return opt.get().get(this);
      }
    } catch (IllegalArgumentException | IllegalAccessException e) {
      Log.error(e);
    }
    return "";
  }
}
