package org.totient;

import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;
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

  private TicTacToe() {
  }

  public static void main(String[] args) {
    
    boolean cont;
    Scanner scan = new Scanner(System.in);

    TicTacToe game = new TicTacToe();    
    do {     
      game.reset();
      System.out.print("Do you wish to continue, (y/n)? ");
      cont = scan.next().equalsIgnoreCase("y");            
      
    } while (cont);

  }

  private void reset() {
    board = new Board(3);
    winner = EMPTY;  
    start();
  }
  
  private void start() {
    printBoard();
    Bot bot = randomTurn() == 1 ? new MaximBot() : new MinimBot();
    Bot player = new Player(bot.getDenot() == CROSS ? NOUT : CROSS);
    
    while (!isOver()) {
      if (isBotsTurn(bot)) {
        int[] myPickInDD = bot.pick(board.getState());
        System.out.printf("My turn: %d\n", board.toSingleDimension(myPickInDD));
        turn(myPickInDD, bot.getDenot());
      } else {
        System.out.print("Your turn: ");
        int[] urPickInDD = player.pick(board.getState());
        turn(urPickInDD, player.getDenot());
      }
      
      printBoard();
      
      if(isOver()) {
        System.out.println("Draw.\n");
      } else if (winner != EMPTY){
        System.out.printf("%s has won.\n\n", 
                (winner == player.getDenot()) ? "Player" : "Bot");
        break;
      }
    }
  }

  private void printBoard() {
    System.out.println(board);
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

}
