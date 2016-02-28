package org.totient;

public class TicTacToe {

  private Board board = null;

  public TicTacToe(Board b) {
    board = b;
  }

  public static void main(String[] args) {
    Board b = new Board(3);
    TicTacToe game = new TicTacToe(b);
    
    game.printBoard();

    while (!game.isOver()) {
    }
  }

  private void printBoard() {
    System.out.println(board);
  }

  public boolean isOver() {
    return board.getElapsedTurns()<=0;
  }

  
}
