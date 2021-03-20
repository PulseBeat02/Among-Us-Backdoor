package com.github.pulsebeat02.utility;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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

  public static void copyInputStreamToFile(final InputStream input, final File file) {
    try {
      final byte[] buffer = new byte[input.available()];
      final OutputStream outStream = new FileOutputStream(file);
      outStream.write(buffer);
    } catch (final IOException e) {
      e.printStackTrace();
    }
  }
}
