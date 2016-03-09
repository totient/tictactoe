package org.totient;

import java.io.PrintStream;
import static org.totient.Denotation.NOUT;

public class MinimBot implements IPlayer {

  private final PrintStream out;

  public MinimBot(PrintStream ps) {
    out = ps;
  }
  
  @Override
  public int[] pick(Board board) {
    int[] p = Minimax.INSTANCE.minimaxPruned(board, 2, NOUT, Integer.MIN_VALUE, Integer.MAX_VALUE);
    out.printf("Minimbot turn: %d\n", board.toSingleDimension(p[0], p[1]));        
    return new int[]{p[0], p[1]};    
  }

  @Override
  public Denotation getDenot() {
    return NOUT;
  }  

  @Override
  public boolean shouldCont(Board board) {
    return true;
  }
  
}
