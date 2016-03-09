package org.totient;

interface IPlayer {
  int[] pick(Board board);
  Denotation getDenot();  
  boolean shudCont(Board board);
}
