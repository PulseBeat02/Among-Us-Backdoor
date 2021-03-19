package com.github.pulsebeat02.utility;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TaskUtilities {

  public static boolean taskIsRunning(final String task) {
    try {
      String line;
      final StringBuilder pidInfo = new StringBuilder();
      final Process p =
          Runtime.getRuntime().exec(System.getenv("windir") + "\\system32\\" + "tasklist.exe");
      final BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
      while ((line = input.readLine()) != null) {
        pidInfo.append(line);
      }
      input.close();
      return pidInfo.toString().contains(task);
    } catch (final IOException ignored) {
    }
    return false;
  }

  public static void killAllTasks(final String task) {
    try {
      final Process p = Runtime.getRuntime().exec("killall " + task);
      p.destroy();
    } catch (final IOException e) {
      e.printStackTrace();
    }
  }
}
