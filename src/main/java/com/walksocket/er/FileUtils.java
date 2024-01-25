package com.walksocket.er;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.charset.StandardCharsets;

/**
 * FileUtils.
 */
public class FileUtils {

  /**
   * read string from input stream.
   *
   * @param in input stream
   * @return string
   * @throws IOException close error
   */
  public static String readString(InputStream in) throws IOException {
    return new String(readByteArray(in), StandardCharsets.UTF_8);
  }

  /**
   * read byte from input stream.
   *
   * @param in input stream
   * @return byte array
   * @throws IOException close error
   */
  public static byte[] readByteArray(InputStream in) throws IOException {
    byte[] data;
    try (var channel = Channels.newChannel(in);
        var baos = new ByteArrayOutputStream()) {
      int bufferLen = 2048;
      var buffer = ByteBuffer.allocate(bufferLen);
      int len = 0;
      while ((len = channel.read(buffer)) >= 0) {
        baos.write(buffer.array(), 0, len);
        ((Buffer) buffer).clear();
      }
      data = baos.toByteArray();
    }
    return data;
  }

  /**
   * write string to file.
   *
   * @param out  output stream
   * @param data data
   * @throws IOException close error
   */
  public static void writeString(OutputStream out, String data) throws IOException {
    try (var channel = Channels.newChannel(out)) {
      var buffer = ByteBuffer.wrap(data.getBytes(StandardCharsets.UTF_8));
      channel.write(buffer);
    }
  }

  /**
   * Constructor.
   */
  private FileUtils() {
  }
}
