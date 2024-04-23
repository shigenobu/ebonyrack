package com.walksocket.er;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpClient.Version;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/**
 * Http.
 */
public class Http {

  /**
   * last version.
   */
  private static String latestVersion;

  /**
   * get last version.
   *
   * @return
   * @throws IOException
   * @throws InterruptedException
   */
  public static String getLatestVersion() throws IOException, InterruptedException {
    if (latestVersion != null) {
      Log.trace(String.format("Cached latest version:%s", latestVersion));
      return latestVersion;
    }

    var client = HttpClient.newBuilder().version(Version.HTTP_2).build();
    var request = HttpRequest.newBuilder(
        URI.create(Const.VERSION_CHECK_URL)).build();
    var response = client.send(request, HttpResponse.BodyHandlers.ofString());
    var opt = response.headers().firstValue("location");
    if (!opt.isPresent()) {
      return null;
    }
    var latestUrl = opt.get();
    var latestUrlArray = latestUrl.split("/");
    latestVersion = latestUrlArray[latestUrlArray.length - 1].substring(1);
    Log.trace(String.format("Network latest version:%s", latestVersion));
    return latestVersion;
  }
}
