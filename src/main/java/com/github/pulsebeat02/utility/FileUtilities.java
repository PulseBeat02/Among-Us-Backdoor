package com.github.pulsebeat02.utility;

import java.net.URI;
import java.net.URISyntaxException;

public class FileUtilities {

  public static URI getURIFromResource(final String file) {
    try {
      return ClassLoader.getSystemResource(file).toURI();
    } catch (final URISyntaxException e) {
      e.printStackTrace();
    }
    return null;
  }
}
