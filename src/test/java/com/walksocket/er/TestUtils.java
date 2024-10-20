package com.walksocket.er;

import org.junit.jupiter.api.Test;

public class TestUtils {

  @Test
  public void testRandomString() {
    for (int i = 0; i < 10; i++) {
      var random = Utils.randomString();
      System.out.println(random);
      assert random.length() == 32;
    }
  }
}
