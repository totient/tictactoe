package org.totient;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.totient.Denotation.CROSS;
import static org.totient.Denotation.EMPTY;
import static org.totient.Denotation.NOUT;


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
  
  @Test
  public void testCell() {
    final int n = 3;
    Board b = new Board(n);
    assertTrue(b.isCellEmpty(0, 1));
    
    b.updateCell(new int[]{0,1}, NOUT);
    assertFalse(b.isCellEmpty(0, 1));
    assertTrue(b.cell(0, 1) == NOUT);
    
    b.updateCell(new int[]{0,1}, CROSS);
    assertTrue(b.cell(0, 1) == CROSS);
  }
  
}
