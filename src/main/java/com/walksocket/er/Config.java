package com.walksocket.er;

import com.walksocket.er.config.CfgProject;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
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
