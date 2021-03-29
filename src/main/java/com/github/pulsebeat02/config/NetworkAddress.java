package com.github.pulsebeat02.config;

public class NetworkAddress {

  private static final String ENCRYPTION_KEY = "sixteen byte key";
  private static final int PORT = 42069;

  public static String getEncryptionKey() {
    return ENCRYPTION_KEY;
  }

  public static int getPort() {
    return PORT;
  }
}
