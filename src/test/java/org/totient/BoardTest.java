package org.totient;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.totient.Denotation.EMPTY;


public class BoardTest {

  @Test
  public void testInit() {
    final int n = 3;
    Board b = new Board(n);
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        assertEquals("("+i+", "+j+") is not EMPTY", EMPTY, b.cell(i, j));
      }
    }
    
  }
}
