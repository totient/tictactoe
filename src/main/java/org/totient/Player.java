package org.totient;

import java.io.PrintStream;
import java.util.Scanner;

public class Player implements Bot {

  private final Denotation denot;
  private final PrintStream out;

  public Player(Denotation d, PrintStream ps) {
    denot = d;
    out = ps;
  }
  
  @Override
  public int[] pick(Board board) {
    Scanner scan = new Scanner(System.in);
    out.print("Your turn: ");    
    int p = scan.nextInt();
    
    while (board.isPickInvalid(p)) {
      out.print("Pick again: ");
      p = scan.nextInt();
    }
    
    return board.toDoubleDimension(p);
  }

  @Override
  public Denotation getDenot() {
    return denot;
  }
  
}
