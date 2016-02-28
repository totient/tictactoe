package org.totient;

import java.util.ArrayList;
import java.util.List;
import static org.totient.Denotation.CROSS;
import static org.totient.Denotation.NOUT;

public class Minimax {

  public static final Minimax INSTANCE = new Minimax();

  private Minimax() {
  }

  public int[] minimax(Board board, int depth, Denotation denot) {
    
    int value;
    int[] suggestion = new int[]{-1,-1};
    
    int hValue = (denot.isMaximising()) ? Integer.MIN_VALUE : Integer.MAX_VALUE;
    
    List<int[]> picks = nextPossPicks(board);

    if (depth <= 0 || picks.isEmpty()) {
      hValue = evaluate(board);
    } else {
      for (int[] p : picks) {
        
        board.updateCell(p, denot);
        
        if (denot.isMaximising()) {
          value = minimax(board, depth - 1, NOUT)[0];
          if (value > hValue) {
            hValue = value;
            suggestion[0] = p[0];
            suggestion[1] = p[1];
          }
        } else {
          value = minimax(board, depth - 1, CROSS)[0];
          if (value < hValue) {
            hValue = value;
            suggestion[0] = p[0];
            suggestion[1] = p[1];
          }
        }
        
        board.clearCell(p);
      }
    }
    return new int[]{hValue, suggestion[0], suggestion[1]};
  }

  private List<int[]> nextPossPicks(Board board) {
    List<int[]> picks = new ArrayList<>();

    for (int i = 0; i < board.size(); ++i) {
      for (int j = 0; j < board.size(); ++j) {
        if (board.isCellEmpty(i, j)) {
          picks.add(new int[]{i, j});
        }
      }
    }

    return picks;
  }

  private int evaluate(Board board) {
    int score = 0;
    return score;
  }
    
}
