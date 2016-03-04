package org.totient;

import java.io.PrintStream;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.totient.Denotation.*;

public class MinimaxTest {

  @Test
  public void testDraw() {
    PrintStream out = NullPrintStream.INSTANCE;
    
    Bot p1 = new MaximBot(out);
    Bot p2 = new MinimBot(out);
    TicTacToe game = new TicTacToe(out);    
    
    game.reset();
    game.start(p1, p2);
    
    assertEquals(EMPTY, game.getWinner());      
  }
}
