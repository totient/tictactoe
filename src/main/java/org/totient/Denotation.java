package org.totient;

public enum Denotation {
  NOUT("O"), CROSS("X"), EMPTY(" ");

  public final String denotation;

  private Denotation(String c) {
    denotation = c;
  }

  public boolean isMaximising() {
    return this == CROSS;
  }

  @Override
  public String toString() {
    return denotation;
  }
}
