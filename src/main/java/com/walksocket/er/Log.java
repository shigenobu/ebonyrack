package com.walksocket.er;

import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.function.Supplier;

/**
 * Log.
 */
public class Log {

  /**
   * stdout pattern.
   */
  private static final String LOG_STDOUT = "STDOUT";

  /**
   * stderr pattern.
   */
  private static final String LOG_STDERR = "STDERR";

  /**
   * log writer.
   */
  private static OutputStreamWriter writer;

  /**
   * open logger.
   *
   * @param logPath log path
   * @throws IOException no file error
   */
  public static void open(String logPath) throws IOException {
    if (Utils.isNullOrEmpty(logPath)) {
      return;
    }

    if (writer == null) {
      if (logPath.equalsIgnoreCase(LOG_STDOUT)) {
        // stdout
        writer = new OutputStreamWriter(System.out);
      } else if (logPath.equalsIgnoreCase(LOG_STDERR)) {
        // stderr
        writer = new OutputStreamWriter(System.err);
      } else {
        // file
        writer = new FileWriter(logPath, true);
      }
    }
  }

  /**
   * close.
   */
  public static void close() {
    if (writer != null) {
      try {
        writer.close();
      } catch (IOException e) {
      }
    }
    writer = null;
  }

  /**
   * sql logger.
   *
   * @param message message
   */
  public static void sql(Object message) {
    if (Env.isDebug()) {
      out("SQL", message);
    }
  }

  /**
   * trace logger.
   *
   * @param message message
   */
  public static void trace(Object message) {
    if (Env.isDebug()) {
      out("TRACE", message);
    }
  }

  /**
   * trace logger.
   *
   * @param message message
   */
  public static void trace(Supplier<Object> message) {
    if (Env.isDebug()) {
      out("TRACE", message.get());
    }
  }

  /**
   * error logger.
   *
   * @param message message
   */
  public static void error(Object message) {
    out("ERROR", message);
  }

  /**
   * out log.
   *
   * @param level   level
   * @param message message
   */
  private static void out(String level, Object message) {
    if (writer == null) {
      return;
    }

    try {
      var info = "";
      var ste = Thread.currentThread().getStackTrace();
      for (var s : ste) {
        if (s.getClassName().startsWith("com.walksocket.er")
            && !s.getClassName().equals("com.walksocket.er.Log")
            && !s.getMethodName().startsWith("lambda")) {
          info = s.getClassName() + ":" + s.getMethodName() + ":" + s.getLineNumber();
          break;
        }
      }

      var msg =
          "[" + Date.now() + "][" + String.format("%010d", Thread.currentThread().getId()) + "]["
              + level + "]" + "[" + info + "]" + message.toString();
      writer.write(msg + "\n");
      writer.flush();

      if (message instanceof Throwable) {
        var stacks = ((Throwable) message).getStackTrace();
        var buffer = new StringBuffer();
        for (var stack : stacks) {
          buffer.append("\n");
          buffer.append("<" + stack.getClassName() + ">");
          buffer.append("<" + stack.getFileName() + ">");
          buffer.append("<" + stack.getLineNumber() + ">");
          buffer.append("<" + stack.getMethodName() + ">");
        }
        writer.write(buffer + "\n");
        writer.flush();
      }
    } catch (Exception e) {
    }
  }
}
