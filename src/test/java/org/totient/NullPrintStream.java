package org.totient;

import java.io.PrintStream;

class NullPrintStream extends PrintStream {

  final static NullPrintStream INSTANCE = new NullPrintStream();
  
  private NullPrintStream() {
    super(System.out);    
  }

  @Override
  public PrintStream printf(String format, Object... args) {
    return null;
  }

  @Override
  public void println(Object x) {
  }

  @Override
  public void println(String x) {
  }

  @Override
  public void println(char[] x) {
  }

  @Override
  public void println(double x) {
  }

  @Override
  public void println(float x) {
  }

  @Override
  public void println(long x) {
  }

  @Override
  public void println(int x) {
  }

  @Override
  public void println(char x) {
  }

  @Override
  public void println(boolean x) {
  }

  @Override
  public void println() {
  }
    
  @Override
  public void print(String x) {
  }

}
