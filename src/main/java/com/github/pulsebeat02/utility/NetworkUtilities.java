package com.github.pulsebeat02.utility;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class NetworkUtilities {

  public static String executeCommand(final String commandStr) {
    String result = null;
    BufferedReader br = null;
    try {
      final Process p = Runtime.getRuntime().exec(commandStr);
      br = new BufferedReader(new InputStreamReader(p.getInputStream()));
      String line;
      final StringBuilder sb = new StringBuilder();
      while ((line = br.readLine()) != null) {
        sb.append(line).append("\n");
      }
      result = sb.toString();
    } catch (final Exception e) {
      e.printStackTrace();
    } finally {
      if (br != null) {
        try {
          br.close();
        } catch (final Exception e) {
          e.printStackTrace();
        }
      }
    }

    return result;
  }

  public static void printWiFiPassword(final String result) {
    final Scanner scanner = new Scanner(result);
    String line;
    String wifi;
    String passworld;
    while ((line = scanner.nextLine()) != null) {
      if (line.contains("SSID name")) {
        wifi = line.substring(line.lastIndexOf("“") + 1, line.length() - 1);
        System.out.println("wireless:" + wifi.trim());
      } else if (line.contains("Key content")) {
        passworld = line.substring(line.lastIndexOf(":") + 1);
        System.out.println("password:" + passworld.trim());
      }
    }
  }

  public static String getWiFiMap(final String result) {
    final Scanner scanner = new Scanner(result);
    String line;
    String wifi;
    String passworld;
    final StringBuilder buff = new StringBuilder();
    try {
      final String WiFiNameLineFlag = "Interface WLAN configuration file";
      while ((line = scanner.nextLine()) != null) {
        if (line.contains(WiFiNameLineFlag)) {
          wifi =
              line.substring(
                  line.lastIndexOf(WiFiNameLineFlag) + WiFiNameLineFlag.length(),
                  line.lastIndexOf(":"));
          buff.append("wireless:").append(wifi.trim()).append("|");
        }
        if (line.contains("Key content")) {
          passworld = line.substring(line.lastIndexOf(":") + 1);
          buff.append("password:").append(passworld.trim());
        }
      }
    } catch (final Exception ignored) {
    }
    return buff.toString();
  }

  public static List<String> getWiFiNameList() {
    final String allWiFiName = "netsh wlan show profiles";
    final String cmdResult = executeCommand(allWiFiName);
    final Scanner scanner = new Scanner(cmdResult);
    final List<String> WiFiNameList = new ArrayList<>();
    String line;
    try {
      while ((line = scanner.nextLine()) != null) {
        if (line.contains(":")) {
          final String name = line.substring(line.lastIndexOf(":") + 1).trim();
          if (!name.equals("")) {
            WiFiNameList.add(name);
          }
        }
      }
    } catch (final Exception ignored) {
    }
    return WiFiNameList;
  }

  public static String getPasswordByName(final String name) {
    final String commandStr = "netsh wlan show profile name=" + name + " key=clear";
    return executeCommand(commandStr);
  }

  public static String getPublicIPAddress() {
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
