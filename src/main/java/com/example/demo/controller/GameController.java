package com.example.demo.controller;

import com.example.demo.level.Cell;

public class GameController {

    private final Cell[][] cells;
    private final int n;
    private final ScoreUpdater scoreUpdater;


    public interface ScoreUpdater {
        void updateScore(long newScore);
    }
    public GameController(Cell[][] cells, int n, ScoreUpdater scoreUpdater) {
        this.cells = cells;
        this.n = n;
        this.scoreUpdater = scoreUpdater;
    }
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
    private void resetModifyFlags(int index, boolean isRow) {
        for (int k = 0; k < n; k++) {
            if (isRow) {
                cells[index][k].setModify(false);
            } else {
                cells[k][index].setModify(false);
            }
        }
    }

    public void moveLeft() {
        printBoardState("LEFT - BEFORE");
        for (int i = 0; i < n; i++) {
            for (int j = 1; j < n; j++) {
                moveHorizontally(i, j, passDestination(i, j, 'l'), -1);
            }
            for (int j = 0; j < n; j++) {
                cells[i][j].setModify(false);
            }
        }

        //debug

        printBoardState("LEFT - AFTER");


    }




    public void moveRight() {

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

    public void moveUp() {
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

    public void moveDown() {
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

    private boolean isValidDesH(int i, int j, int des, int sign) {
        if (des + sign < n && des + sign >= 0) {
            if (cells[i][des + sign].getNumber() == cells[i][j].getNumber() && !cells[i][des + sign].getModify()
                    && cells[i][des + sign].getNumber() != 0) {
                return true;
            }
        }
        return false;
    }

    private void moveHorizontally(int i, int j, int des, int sign) {
        /*if (isValidDesH(i, j, des, sign)) {
            cells[i][j].adder(cells[i][des + sign]);
            cells[i][des].setModify(true);
        } */
        if (isValidDesH(i, j, des, sign)) {
            int merged = cells[i][j].getNumber() + cells[i][des + sign].getNumber();
            cells[i][j].adder(cells[i][des + sign]);
            cells[i][des + sign].setModify(true);
            scoreUpdater.updateScore(merged);


        }
        else if (des != j) {
            cells[i][j].changeCell(cells[i][des]);
        }
    }

    private boolean isValidDesV(int i, int j, int des, int sign) {
        if (des + sign < n && des + sign >= 0)
            if (cells[des + sign][j].getNumber() == cells[i][j].getNumber() && !cells[des + sign][j].getModify()
                    && cells[des + sign][j].getNumber() != 0) {
                return true;
            }
        return false;
    }

    private void moveVertically(int i, int j, int des, int sign) {
       /* if (isValidDesV(i, j, des, sign)) {
            cells[i][j].adder(cells[des + sign][j]);
            cells[des][j].setModify(true);*/
        if (isValidDesV(i, j, des, sign)) {
            int merged = cells[i][j].getNumber() + cells[des + sign][j].getNumber();
            cells[i][j].adder(cells[des + sign][j]);
            cells[des + sign][j].setModify(true);
            scoreUpdater.updateScore(merged);
        }
        else if (des != i) {
            cells[i][j].changeCell(cells[des][j]);
        }
    }

    private boolean haveSameNumberNearly(int i, int j) {
        if (i < n - 1 && j < n - 1) {
            if (cells[i + 1][j].getNumber() == cells[i][j].getNumber())
                return true;
            if (cells[i][j + 1].getNumber() == cells[i][j].getNumber())
                return true;
        }
        return false;
    }

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

    public void sumCellNumbersToScore() {
        long total = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                total += cells[i][j].getNumber();
            }
        }
        scoreUpdater.updateScore(total);
    }

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