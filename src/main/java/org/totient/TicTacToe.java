package org.totient;

import static org.totient.Denotation.CROSS;
import static org.totient.Denotation.EMPTY;
import static org.totient.Denotation.NOUT;

public class TicTacToe {

  private Board board = null;
  
  private Denotation winner = EMPTY;
  private static final int[] WINS = {
    0b111000000, 0b000111000, 0b000000111, // rows
    0b100100100, 0b010010010, 0b001001001, // cols
    0b100010001, 0b001010100}; // diagonals


  public TicTacToe(Board b) {
    board = b;
  }

  public static void main(String[] args) {
    Board b = new Board(3);
    TicTacToe game = new TicTacToe(b);
    game.printBoard();

    Bot maxBot = new MaximBot();
    Bot player = new Player();

    while (!game.isOver()) {
      if (b.isMaxsTurn()) {
        int[] myPickInDD = maxBot.pick(game.getBoard());
        System.out.printf("My turn: %d\n", b.toSingleDimension(myPickInDD));
        game.turn(myPickInDD, CROSS);
      } else {
        System.out.print("Your turn: ");
        int[] urPickInDD = player.pick(game.getBoard());
        game.turn(urPickInDD, NOUT);
      }

      game.printBoard();
    }

  }

  private void printBoard() {
    System.out.println(board);
  }

  public boolean isOver() {
    return winner != EMPTY || board.getElapsedTurns()<=0;
  }

  public void turn(int[] pick, Denotation denot) {
    board.updateCell(pick, denot);
    winner = hasWon(denot);
  }

  public Board getBoard() {
    return board.getState();
  }

  private Denotation hasWon(Denotation denot) {
    int pattern = 0b000000000;  // 9-bit pattern for the 9 cells
    int s = board.size();
    for (int i = 0; i < s; ++i) {
      for (int j = 0; j < s; ++j) {
        if (board.cell(i, j) == denot) {
          pattern = pattern | (1 << (i * s + j));
        }
      }
    }

    for (int winningPattern : WINS) {
      if ((pattern & winningPattern) == winningPattern) {
        System.out.printf("%s has won.\n", denot);
        return denot;
      }
    }
    return EMPTY;
  }

}
