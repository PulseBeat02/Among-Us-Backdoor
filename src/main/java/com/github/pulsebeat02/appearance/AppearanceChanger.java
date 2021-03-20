package com.github.pulsebeat02.appearance;

import com.github.pulsebeat02.utility.FileUtilities;
import com.github.pulsebeat02.utility.SoundUtilities;
import com.github.pulsebeat02.utility.WallpaperChangerUtility;

import java.io.File;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class AppearanceChanger {

  private static final Random RANDOM;

  static {
    RANDOM = new Random();
  }

  private boolean sound;

  public AppearanceChanger() {
    sound = true;
    // setWallpaper();
    playSound();
  }

  public void setWallpaper() {
    WallpaperChangerUtility.setBackground(
        Objects.requireNonNull(FileUtilities.getURIFromResource("background.jpg")).toString());
  }

  public synchronized void playSound() {
    final ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
    final File[] sounds =
        new File(Objects.requireNonNull(FileUtilities.getURIFromResource("sounds"))).listFiles();
    if (sounds != null) {
      executor.scheduleAtFixedRate(
          () -> SoundUtilities.playSound(sounds[RANDOM.nextInt(sounds.length)].getAbsolutePath()),
          0,
          RANDOM.nextInt(1000 * 60 * 45),
          TimeUnit.MILLISECONDS);
    }
  }

  public void stopSounds() {
    sound = false;
  }
}
