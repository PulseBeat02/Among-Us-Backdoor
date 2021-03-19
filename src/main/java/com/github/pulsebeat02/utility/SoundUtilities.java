package com.github.pulsebeat02.utility;

import com.github.pulsebeat02.appearance.AppearanceChanger;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.util.concurrent.CompletableFuture;

public class SoundUtilities {

  public static void playSound(final String path) {
    CompletableFuture.runAsync(
        () -> {
          try {
            final Clip clip = AudioSystem.getClip();
            final AudioInputStream inputStream =
                AudioSystem.getAudioInputStream(AppearanceChanger.class.getResourceAsStream(path));
            clip.open(inputStream);
            clip.start();
          } catch (final Exception e) {
            System.err.println(e.getMessage());
          }
        });
  }
}
