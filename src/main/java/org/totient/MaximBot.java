package org.totient;

import static org.totient.Denotation.CROSS;

public class MaximBot implements Bot {

  @Override
  public int[] pick(Board board) {
    int[] p = Minimax.INSTANCE.minimax(board, 2, CROSS);
    return new int[]{p[1], p[2]};
  }

  @Override
  public Denotation getDenot() {
    return CROSS;
  }

}
