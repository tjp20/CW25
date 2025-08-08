package com.example.demo.controller;

import com.example.demo.level.Cell;

/**
 * GameController manages the game logic for 2048, including tile movement,
 * merging, score updates, and checking end-game conditions.
 */
public class GameController {

    private final Cell[][] cells;
    private final int n;
    private final ScoreUpdater scoreUpdater;
    private boolean moved;

    /**
     * Returns whether a move or merge occurred in the last move.
     *
     * @return true if any tile was moved or merged, false otherwise
     */
    public boolean hasMoved() {
        return moved;
    }

    /**
     * Interface for updating the score externally.
     */
    public interface ScoreUpdater {
        /**
         * Updates the score by adding the specified value.
         *
         * @param newScore the value to add to the current score
         */
        void updateScore(long newScore);
    }

    /**
     * Constructs the GameController with the given board, size, and score updater.
     *
     * @param cells         the 2D array representing the game board
     * @param n             the size of the board (e.g., 4)
     * @param scoreUpdater  the callback to update the score externally
     */
    public GameController(Cell[][] cells, int n, ScoreUpdater scoreUpdater) {
        this.cells = cells;
        this.n = n;
        this.scoreUpdater = scoreUpdater;
    }

    /**
     * Handles leftward movement and merging of tiles.
     */
    public void moveLeft() {
        moved = false;
        printBoardState("LEFT - BEFORE");
        for (int i = 0; i < n; i++) {
            for (int j = 1; j < n; j++) {
                moveHorizontally(i, j, passDestination(i, j, 'l'), -1);
            }
            for (int j = 0; j < n; j++) {
                cells[i][j].setModify(false);
            }
        }
        printBoardState("LEFT - AFTER");
    }

    /**
     * Handles rightward movement and merging of tiles.
     */
    public void moveRight() {
        moved = false;
        printBoardState("RIGHT - BEFORE");
        for (int i = 0; i < n; i++) {
            for (int j = n - 1; j >= 0; j--) {
                moveHorizontally(i, j, passDestination(i, j, 'r'), 1);
            }
            for (int j = 0; j < n; j++) {
                cells[i][j].setModify(false);
            }
        }
        printBoardState("RIGHT - AFTER");
    }

    /**
     * Handles upward movement and merging of tiles.
     */
    public void moveUp() {
        moved = false;
        printBoardState("UP - BEFORE");
        for (int j = 0; j < n; j++) {
            for (int i = 1; i < n; i++) {
                moveVertically(i, j, passDestination(i, j, 'u'), -1);
            }
            for (int i = 0; i < n; i++) {
                cells[i][j].setModify(false);
            }
        }
        printBoardState("UP - AFTER");
    }

    /**
     * Handles downward movement and merging of tiles.
     */
    public void moveDown() {
        moved = false;
        printBoardState("DOWN - BEFORE");
        for (int j = 0; j < n; j++) {
            for (int i = n - 1; i >= 0; i--) {
                moveVertically(i, j, passDestination(i, j, 'd'), 1);
            }
            for (int i = 0; i < n; i++) {
                cells[i][j].setModify(false);
            }
        }
        printBoardState("DOWN - AFTER");
    }

    /**
     * Checks whether there are no more valid moves left.
     *
     * @return true if the player cannot make any move, false otherwise
     */
    public boolean canNotMove() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (haveSameNumberNearly(i, j)) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Sums up all tile values on the board and adds the total to the score.
     */
    public void sumCellNumbersToScore() {
        long total = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                total += cells[i][j].getNumber();
            }
        }
        scoreUpdater.updateScore(total);
    }

    /**
     * Checks if a horizontal merge is possible at the given position.
     *
     * @param i     the row index
     * @param j     the column index of the current tile
     * @param des   the target destination column
     * @param sign  the movement direction (-1 or +1)
     * @return true if a merge is possible, false otherwise
     */
    private boolean isValidDesH(int i, int j, int des, int sign) {
        if (des + sign < n && des + sign >= 0) {
            if (cells[i][des + sign].getNumber() == cells[i][j].getNumber() && !cells[i][des + sign].getModify()
                    && cells[i][des + sign].getNumber() != 0) {
                return true;
            }
        }
        return false;
    }

    /**
     * Moves a tile horizontally and handles merging if valid.
     *
     * @param i     the row index
     * @param j     the column index
     * @param des   the destination column
     * @param sign  the movement direction (-1 or +1)
     */
    private void moveHorizontally(int i, int j, int des, int sign) {
        if (isValidDesH(i, j, des, sign)) {
            int merged = cells[i][j].getNumber() + cells[i][des + sign].getNumber();
            cells[i][j].adder(cells[i][des + sign]);
            cells[i][des + sign].setModify(true);
            scoreUpdater.updateScore(merged);
            moved = true;
        } else if (des != j && cells[i][j].getNumber() != 0) {
            cells[i][j].changeCell(cells[i][des]);
            moved = true;
        }
    }

    /**
     * Checks if a vertical merge is possible at the given position.
     *
     * @param i     the row index
     * @param j     the column index
     * @param des   the target row
     * @param sign  the movement direction (-1 or +1)
     * @return true if a merge is possible, false otherwise
     */
    private boolean isValidDesV(int i, int j, int des, int sign) {
        if (des + sign < n && des + sign >= 0)
            if (cells[des + sign][j].getNumber() == cells[i][j].getNumber() && !cells[des + sign][j].getModify()
                    && cells[des + sign][j].getNumber() != 0) {
                return true;
            }
        return false;
    }

    /**
     * Moves a tile vertically and handles merging if valid.
     *
     * @param i     the current row index
     * @param j     the column index
     * @param des   the destination row
     * @param sign  the movement direction (-1 or +1)
     */
    private void moveVertically(int i, int j, int des, int sign) {
        if (isValidDesV(i, j, des, sign)) {
            int merged = cells[i][j].getNumber() + cells[des + sign][j].getNumber();
            cells[i][j].adder(cells[des + sign][j]);
            cells[des + sign][j].setModify(true);
            scoreUpdater.updateScore(merged);
            moved = true;
        } else if (des != i && cells[i][j].getNumber() != 0) {
            cells[i][j].changeCell(cells[des][j]);
            moved = true;
        }
    }

    /**
     * Checks if the tile has a horizontally or vertically adjacent tile with the same number.
     *
     * @param i the row index
     * @param j the column index
     * @return true if a merge is possible with any neighbor
     */
    private boolean haveSameNumberNearly(int i, int j) {
        if (i < n - 1 && j < n - 1) {
            if (cells[i + 1][j].getNumber() == cells[i][j].getNumber())
                return true;
            if (cells[i][j + 1].getNumber() == cells[i][j].getNumber())
                return true;
        }
        return false;
    }

    /**
     * Determines how far a tile can move in a given direction.
     *
     * @param i      the row index
     * @param j      the column index
     * @param direct the direction character: 'l', 'r', 'u', or 'd'
     * @return the destination index in that direction
     */
    private int passDestination(int i, int j, char direct) {
        int coordinate = j;
        if (direct == 'l') {
            for (int k = j - 1; k >= 0; k--) {
                if (cells[i][k].getNumber() != 0) {
                    coordinate = k + 1;
                    break;
                } else if (k == 0) {
                    coordinate = 0;
                }
            }
            return coordinate;
        }
        coordinate = j;
        if (direct == 'r') {
            for (int k = j + 1; k <= n - 1; k++) {
                if (cells[i][k].getNumber() != 0) {
                    coordinate = k - 1;
                    break;
                } else if (k == n - 1) {
                    coordinate = n - 1;
                }
            }
            return coordinate;
        }
        coordinate = i;
        if (direct == 'd') {
            for (int k = i + 1; k <= n - 1; k++) {
                if (cells[k][j].getNumber() != 0) {
                    coordinate = k - 1;
                    break;
                } else if (k == n - 1) {
                    coordinate = n - 1;
                }
            }
            return coordinate;
        }
        coordinate = i;
        if (direct == 'u') {
            for (int k = i - 1; k >= 0; k--) {
                if (cells[k][j].getNumber() != 0) {
                    coordinate = k + 1;
                    break;
                } else if (k == 0) {
                    coordinate = 0;
                }
            }
            return coordinate;
        }
        return -1;
    }

    /**
     * Prints the current board state to the console for debugging.
     *
     * @param direction the direction of the move
     */
    private void printBoardState(String direction) {
        System.out.println("\n" + direction + " MOVE");
        for (int i = 0; i < n; i++) {
            StringBuilder row = new StringBuilder();
            for (int j = 0; j < n; j++) {
                row.append("[").append(cells[i][j].getNumber()).append("]");
            }
            System.out.println("Row " + i + ": " + row);
        }
    }

}