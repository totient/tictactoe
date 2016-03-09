package org.totient;

import java.io.PrintStream;
import java.util.concurrent.ThreadLocalRandom;
import static org.totient.Denotation.CROSS;

public class MaximBot implements IPlayer {

  private final PrintStream out;

  public MaximBot(PrintStream ps) {
    out = ps;
  }
  
  @Override
  public int[] pick(Board board) {
    int[] p = (board.getElapsedTurns() == 9)
            ? board.toDoubleDimension(ThreadLocalRandom.current().nextInt(1, 10))
            : Minimax.INSTANCE.minimaxPruned(board, 2, CROSS, Integer.MIN_VALUE, Integer.MAX_VALUE);

    out.printf("Maximbot turn: %d\n", board.toSingleDimension(p[0], p[1]));    
    return new int[]{p[0], p[1]};
  }

  @Override
  public Denotation getDenot() {
    return CROSS;
  }

  @Override
  public boolean shouldCont(Board board) {
    return true;
  }

}
