package org.totient;

interface IPlayer {
  int[] pick(Board board);
  Denotation getDenot();  
  boolean shouldCont(Board board);
}
