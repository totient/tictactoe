package org.totient;

import java.util.Scanner;

public class Player implements Bot {

  private final Denotation denot;

  public Player(Denotation d) {
    denot = d;
  }
  
  @Override
  public int[] pick(Board board) {
    Scanner scan = new Scanner(System.in);
    int p = scan.nextInt();
    while (board.isPickInvalid(p)) {
      System.out.print("Pick again: ");
      p = scan.nextInt();
    }
    return board.toDoubleDimension(p);
  }

  @Override
  public Denotation getDenot() {
    return denot;
  }
  
}
