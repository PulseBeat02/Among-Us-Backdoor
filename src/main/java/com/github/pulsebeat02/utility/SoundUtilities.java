package com.github.pulsebeat02.utility;

import com.github.pulsebeat02.appearance.AppearanceChanger;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class SoundUtilities {

  public static void playSound(final String path) {
    try {
      final Clip clip = AudioSystem.getClip();
      final AudioInputStream inputStream =
          AudioSystem.getAudioInputStream(SoundUtilities.class.getResourceAsStream(path));
      clip.open(inputStream);
      clip.start();
    } catch (final Exception ignored) {
    }
  }
}
