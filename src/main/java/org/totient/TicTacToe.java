package org.totient;

import java.io.PrintStream;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;
import static org.totient.Denotation.CROSS;
import static org.totient.Denotation.EMPTY;
import static org.totient.Denotation.NOUT;

public class TicTacToe {

  private Board board = null;
  private Denotation winner = EMPTY;
  private final PrintStream out;
  
  private static final int[] WINS = {
    0b111000000, 0b000111000, 0b000000111, // rows
    0b100100100, 0b010010010, 0b001001001, // cols
    0b100010001, 0b001010100}; // diagonals

  TicTacToe(PrintStream out) {
    this.out = out;
  }

  public static void main(String[] args) {
    
    boolean cont;
    Scanner scan = new Scanner(System.in);
    TicTacToe game = new TicTacToe(System.out);    
    
    do {     
      Bot p1 = game.randomTurn() == 1 ? new MaximBot(game.out) : new MinimBot(game.out);
      Bot p2 = new Player(p1.getDenot() == CROSS ? NOUT : CROSS, game.out);        
      game.reset();
      game.start(p1, p2);    
      game.out.print("Do you wish to continue, (y/n)? ");
      cont = scan.next().equalsIgnoreCase("y");            
      
    } while (cont);

  }

  public void start(Bot p1, Bot p2) {
    printBoard();
    
    while (!isOver()) {
      if (isBotsTurn(p1)) {
        int[] myPickInDD = p1.pick(board.getState());
        turn(myPickInDD, p1.getDenot());
      } else {
        int[] urPickInDD = p2.pick(board.getState());
        turn(urPickInDD, p2.getDenot());
      }
      
      printBoard();
      
      if(isOver()) {
        out.println("Draw.\n");
      } else if (winner != EMPTY){
        out.printf("%s has won.\n\n", 
                (winner == p2.getDenot()) ? "Player" : "Bot");
        break;
      }
    }
  }

  public void reset() {
    board = new Board(3);
    winner = EMPTY;  
  }
  
  private void printBoard() {
    out.println(board);
  }

  private boolean isOver() {
    return board.getElapsedTurns() <= 0;
  }

  private void turn(int[] pick, Denotation denot) {
    board.updateCell(pick, denot);
    winner = hasWon(denot);
  }

  private boolean isBotsTurn(Bot bot) {
    int et = board.getElapsedTurns();
    return bot.getDenot() == CROSS ? et % 2 == 1 : et % 2 == 0;
  }

  private int randomTurn() {
    return ThreadLocalRandom.current().nextInt(2);
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
        return denot;
      }
    }
    return EMPTY;
  }

  public Denotation getWinner() {
    return winner;
  }
  
}
