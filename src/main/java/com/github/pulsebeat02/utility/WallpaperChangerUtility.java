package com.github.pulsebeat02.utility;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.win32.W32APIOptions;

public class WallpaperChangerUtility {

  public static void setBackground(final String background) {
    User32.INSTANCE.SystemParametersInfo(0x0014, 0, background, 1);
  }

  public interface User32 extends Library {
    User32 INSTANCE = Native.loadLibrary("user32", User32.class, W32APIOptions.DEFAULT_OPTIONS);

    boolean SystemParametersInfo(int one, int two, String s, int three);
  }
}
