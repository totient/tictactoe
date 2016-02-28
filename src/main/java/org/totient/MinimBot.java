package org.totient;

import static org.totient.Denotation.NOUT;

public class MinimBot implements Bot {

  @Override
  public int[] pick(Board board) {
    int[] p = Minimax.INSTANCE.minimax(board, 2, NOUT);
    return new int[]{p[1], p[2]};    
  }
  
}
