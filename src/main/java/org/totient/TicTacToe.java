package org.totient;

import static org.totient.Denotation.CROSS;
import static org.totient.Denotation.NOUT;

public class TicTacToe {

  private Board board = null;

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
    return board.getElapsedTurns()<=0;
  }

  public void turn(int[] pick, Denotation denot) {
    board.updateCell(pick, denot);
  }
  
  public Board getBoard() {
    return board.getState();
  }
    
}
