package com.github.pulsebeat02.backdoor;

public abstract class Leakable {

  public String information;

  public Leakable() {
    information = "";
  }

  protected void appendLine(final String line) {
    information += (line + "\n");
  }
}
