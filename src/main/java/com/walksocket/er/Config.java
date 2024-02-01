package com.walksocket.er;

import com.walksocket.er.config.CfgProject;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

/**
 * Config.
 */
public class Config {

  /**
   * instance.
   */
  private static Config instance = new Config();

  /**
   * file.
   */
  private static final File file = new File(Env.getHome(), "config.json");

  /**
   * file lock.
   */
  private static final File fileLock = new File(Env.getHome(), "config.json.lock");

  /**
   * lock.
   */
  private static FileLock lock;

  static {
    if (!file.exists()) {
      save();
    }

    try (var stream = new FileInputStream(file)) {
      var json = FileUtils.readString(stream);
      instance = Json.toObject(json, Config.class);
    } catch (FileNotFoundException e) {
      Log.error(e);
    } catch (IOException e) {
      Log.error(e);
    }
  }

  /**
   * lock.
   *
   * @throws IOException
   */
  public static void lock() throws IOException {
    if (lock != null) {
      lock.release();
    }

    var lockChannel = FileChannel.open(fileLock.toPath(), StandardOpenOption.CREATE,
        StandardOpenOption.WRITE);
    lock = lockChannel.tryLock();
    if (lock == null) {
      throw new IOException("Already locked at config.");
    }
  }

  /**
   * get instance.
   *
   * @return instance
   */
  public static Config getInstance() {
    return instance;
  }

  /**
   * save.
   */
  public static void save() {
    try (var stream = new FileOutputStream(file)) {
      FileUtils.writeString(stream, Json.toJsonStringFriendly(instance));
    } catch (FileNotFoundException e) {
      Log.error(e);
    } catch (IOException e) {
      Log.error(e);
    }
  }

  /**
   * cfgProjectList.
   */
  public List<CfgProject> cfgProjectList = new ArrayList<>();
}
