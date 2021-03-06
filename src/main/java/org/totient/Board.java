package org.totient;

import static org.totient.Denotation.EMPTY;

public class Board {

  private Denotation[][] grid = null;
  private int elapsedTurns = -1;

  public Board(int n) {
    elapsedTurns = n * n;
    grid = new Denotation[n][n];
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        grid[i][j] = EMPTY;
      }
    }
  }

  private Board(Denotation[][] g, int elapsedTurns) {
    this.elapsedTurns = elapsedTurns;
    int n = g.length;
    this.grid = new Denotation[n][n];
    for (int i = 0; i < n; i++) {
      System.arraycopy(g[i], 0, grid[i], 0, n);
    }
  }

  public Denotation cell(int i, int j) {
    return grid[i][j];
  }

  public int size() {
    return grid.length;
  }

  boolean isCellEmpty(int i, int j) {
    return grid[i][j] == EMPTY;
  }

  void updateCell(int[] p, Denotation denot) {
    grid[p[0]][p[1]] = denot;
    elapsedTurns--;
  }

  void clearCell(int[] p) {
    grid[p[0]][p[1]] = EMPTY;
  }

  int getElapsedTurns() {
    return elapsedTurns;
  }

  public boolean isPickInvalid(int p) {
    if ((p < 1) || p > (size() * size())) {
      return true;
    }

    int[] pdd = toDoubleDimension(p);
    return grid[pdd[0]][pdd[1]] != EMPTY;
  }

  public int toSingleDimension(int... arr) {
    return ((arr[0] + 1) * size()) - (size() - (arr[1] + 1));
  }

  public int[] toDoubleDimension(int p) {
    return new int[]{(p - 1) / size(), (p - 1) % size()};
  }

  Board getState() {
    return new Board(grid, elapsedTurns);
  }

  String getLayout() {
    return String.format("\n%s | %s | %s \n\n"
            + "%s | %s | %s \n\n"
            + "%s | %s | %s \n\n",
            grid[0][0], grid[0][1], grid[0][2],
            grid[1][0], grid[1][1], grid[1][2],
            grid[2][0], grid[2][1], grid[2][2]);
  }

  String getLayoutWithHints() {
    return String.format("\n                   Hint:\n"
            + "%s | %s | %s          %s | %s | %s\n\n"
            + "%s | %s | %s          %s | %s | %s\n\n"
            + "%s | %s | %s          %s | %s | %s\n\n",
            grid[0][0], grid[0][1], grid[0][2], 1, 2, 3,
            grid[1][0], grid[1][1], grid[1][2], 4, 5, 6,
            grid[2][0], grid[2][1], grid[2][2], 7, 8, 9);
  }

}
