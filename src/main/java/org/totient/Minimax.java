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
    int[][][] possTriples = new int[][][]{
                                          {{0, 0}, {0, 1}, {0, 2}}, // 1st row
                                          {{1, 0}, {1, 1}, {1, 2}}, // 2nd row
                                          {{2, 0}, {2, 1}, {2, 2}}, // 3rd row
                                          {{0, 0}, {1, 0}, {2, 0}}, // 1st col
                                          {{0, 1}, {1, 1}, {2, 1}}, // 2nd col
                                          {{0, 2}, {1, 2}, {2, 2}}, // 3rd col
                                          {{0, 0}, {1, 1}, {2, 2}}, // diagonal
                                          {{0, 2}, {1, 1}, {2, 0}}, // flipped diagonal
                                    };
    
    for(int i = 0; i < 8; i++) {
      score += evaluateTriple(board, possTriples[i]);
    }
    return score;
  }
  
  
  /**
   * The heuristic method to evaluate triple
   */
  private int evaluateTriple(Board board, int[]... possTriple) {    

    int score = 0;
    for(int i = 0; i < 3; i++) {
      int[] couple = possTriple[i];

      switch (i) {
        case 0: {
          if (board.cell(couple[0], couple[1]) == CROSS) {        
            score = 1;
          } else if (board.cell(couple[0], couple[1]) == NOUT) {
            score = -1;        
          }
          break;
        }
        
        case 1: {
          if (board.cell(couple[0], couple[1]) == CROSS) {                    
            score = (score == 1) ? 10 : (score == -1) ? 0 : 1;
          } else if (board.cell(couple[0], couple[1]) == NOUT) {
            score = (score == -1) ? -10 : (score == 1) ? 0 : -1;
          }
          if (score == 0) return 0;
          break;
        }
        
        default: {
          if (board.cell(couple[0], couple[1]) == CROSS) {                    
            score = (score > 0) ? score*10 : (score < 0) ? 0 : 1;
          } else if (board.cell(couple[0], couple[1]) == NOUT) {
            score = (score < 0) ? score*10 : (score > 0) ? 0 : -1;
          }
          break;
        }
      }
    }

    return score;
  }
  
}
