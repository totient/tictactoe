package org.totient;

import java.io.PrintStream;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.totient.Denotation.*;

public class MinimaxTest {

  @Test
  public void testDraw() {
    PrintStream out = NullPrintStream.INSTANCE;
    
    IPlayer p1 = new MaximBot(out);
    IPlayer p2 = new MinimBot(out);
    TicTacToe game = new TicTacToe(out);    
    
    for(int i=0; i<100; i++) {
      game.reset();
      game.start(p1, p2);      
    }
    
    assertEquals(EMPTY, game.getWinner());      
  }
}
