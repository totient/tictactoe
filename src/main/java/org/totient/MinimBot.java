package org.totient;

import java.io.PrintStream;
import static org.totient.Denotation.NOUT;

public class MinimBot implements Bot {

  private final PrintStream out;

  public MinimBot(PrintStream ps) {
    out = ps;
  }
  
  @Override
  public int[] pick(Board board) {
    int[] p = Minimax.INSTANCE.minimax(board, 2, NOUT);
    out.printf("Minimbot turn: %d\n", board.toSingleDimension(p[1], p[2]));        
    return new int[]{p[1], p[2]};    
  }

  @Override
  public Denotation getDenot() {
    return NOUT;
  }  
  
}
