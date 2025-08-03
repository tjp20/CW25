package com.example.demo;

import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.Random;
import javafx.animation.ScaleTransition;
import javafx.util.Duration;



public  class LevelParent {
    private static int HEIGHT = 700;
    private static int n = 4;
    private final static int distanceBetweenCells = 10;
    private static double LENGTH = (HEIGHT - ((n + 1) * distanceBetweenCells)) / (double) n;
    private TextMaker textMaker = TextMaker.getSingleInstance();
    //private Cell[][] cells = new Cell[n][n];
    private Cell[][] cells;

    private Group root; //the UI container
    private long score = 0;
    private Text scoreText = new Text();

    private String currentLevelName = "Level 1";

    public void setLevelName(String name) {
        this.currentLevelName = name;
    }




    static void setN(int number) {
        n = number;
        LENGTH = (HEIGHT - ((n + 1) * distanceBetweenCells)) / (double) n;
    }

    static double getLENGTH() {
        return LENGTH;
    }

    private void randomFillNumber(int turn) {

        Cell[][] emptyCells = new Cell[n][n];
        int a = 0;
        int b = 0;
        int aForBound=0,bForBound=0;
        outer:
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (cells[i][j].getNumber() == 0) {
                    emptyCells[a][b] = cells[i][j];
                    if (b < n-1) {
                        bForBound=b;
                        b++;

                    } else {
                        aForBound=a;
                        a++;
                        b = 0;
                        if(a==n)
                            break outer;
                    }
                }
            }
        }



        Text text;
        Random random = new Random();
        boolean putTwo = true;
        if (random.nextInt() % 2 == 0)
            putTwo = false;
        int xCell, yCell;
        xCell = random.nextInt(aForBound+1);
        yCell = random.nextInt(bForBound+1);
        if (putTwo) {
            text = textMaker.madeText("2", emptyCells[xCell][yCell].getX(), emptyCells[xCell][yCell].getY(), root);
            emptyCells[xCell][yCell].setTextClass(text);
            root.getChildren().add(text);
            emptyCells[xCell][yCell].setColorByNumber(2);
            //
            playPopAnimation(text);

        } else {
            text = textMaker.madeText("4", emptyCells[xCell][yCell].getX(), emptyCells[xCell][yCell].getY(), root);
            emptyCells[xCell][yCell].setTextClass(text);
            root.getChildren().add(text);
            emptyCells[xCell][yCell].setColorByNumber(4);
            //
            playPopAnimation(text);

        }
    }

    private int  haveEmptyCell() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (cells[i][j].getNumber() == 0)
                    return 1;
                if(cells[i][j].getNumber() == 2048)
                    return 0;
            }
        }
        return -1;
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

    private void moveLeft() {
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




    private void moveRight() {

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

    private void moveUp() {
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

    private void moveDown() {
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
            int mergedValue = cells[i][j].getNumber() + cells[i][des + sign].getNumber();
            cells[i][j].adder(cells[i][des + sign]);
            score += mergedValue;
            ScoreDebugger.debugMergeScore(score, mergedValue); // added this line
            scoreText.setText(String.valueOf(score));
            cells[i][des+sign].setModify(true);


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
            int mergedValue = cells[i][j].getNumber() + cells[des + sign][j].getNumber();
            cells[i][j].adder(cells[des + sign][j]);
            score += mergedValue;
            ScoreDebugger.debugMergeScore(score, mergedValue);//added this
            scoreText.setText(String.valueOf(score));
            cells[des+sign][j].setModify(true);
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

    private boolean canNotMove() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (haveSameNumberNearly(i, j)) {
                    return false;
                }
            }
        }
        return true;
    }

    private void sumCellNumbersToScore() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                score += cells[i][j].getNumber();
            }
        }
    }

    void game(Scene gameScene, Group root, Stage primaryStage, Scene endGameScene, Group endGameRoot) {
        this.root = root;
        this.cells = new Cell[n][n];  // Now uses updated grid size
        double gridOffsetX = 350; // move grid 400 pixels to the right
        double gridOffsetY = 50;


        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                cells[i][j] = new Cell(gridOffsetX+(j) * LENGTH + (j + 1) * distanceBetweenCells,
                        gridOffsetY+(i) * LENGTH + (i + 1) * distanceBetweenCells, LENGTH, root);
            }

        }


        // Username box
        UsernameDisplay.addTo(root, Account.currentUsername, 50, 30);

        //Best score value
        long bestScore = UserScore.getBestScore(Account.currentUsername, currentLevelName);

        // Box layout settings
        double boxWidth = 150;
        double boxHeight = 60;
        double spacing = 20;
        double topY = 200;
        double startX = 20;  // moved left from 550

        // === BEST SCORE BOX ===
        Rectangle bestBox = new Rectangle(boxWidth, boxHeight);
        bestBox.setArcWidth(20);
        bestBox.setArcHeight(20);
        bestBox.setFill(Color.web("#D7CCC8")); // Light brown / cream
        bestBox.setX(startX);
        bestBox.setY(topY);

        Text bestLabel = new Text("BEST SCORE");
        bestLabel.setFont(Font.font("Consolas", 18));
        bestLabel.setFill(Color.web("#4E342E")); // Deep brown text
        bestLabel.setX(startX + 15);
        bestLabel.setY(topY + 22);

        Text bestValue = new Text(String.valueOf(bestScore));
        bestValue.setFont(Font.font("Consolas", 20));
        bestValue.setFill(Color.web("#3E2723")); // Darker brown
        bestValue.setX(startX + 15);
        bestValue.setY(topY + 48);

        // === CURRENT SCORE BOX ===
        Rectangle scoreBox = new Rectangle(boxWidth, boxHeight);
        scoreBox.setArcWidth(20);
        scoreBox.setArcHeight(20);
        scoreBox.setFill(Color.web("#BCAAA4")); // Soft brown
        scoreBox.setX(startX + boxWidth + spacing);
        scoreBox.setY(topY);

        Text scoreLabel = new Text("CURRENT SCORE");
        scoreLabel.setFont(Font.font("Consolas", 18));
        scoreLabel.setFill(Color.web("#4E342E")); // Deep brown text
        scoreLabel.setX(startX + boxWidth + spacing + 15);
        scoreLabel.setY(topY + 22);

        scoreText = new Text("0");
        scoreText.setFont(Font.font("Consolas", 20));
        scoreText.setFill(Color.web("#3E2723")); // Darker brown
        scoreText.setX(startX + boxWidth + spacing + 15);
        scoreText.setY(topY + 48);

        // Add to root
        root.getChildren().addAll(
                bestBox, bestLabel, bestValue,
                scoreBox, scoreLabel, scoreText
        );

        randomFillNumber(1);
        randomFillNumber(1);

        gameScene.addEventHandler(KeyEvent.KEY_PRESSED, key ->{
            Platform.runLater(() -> {
                int haveEmptyCell;
                if (key.getCode() == KeyCode.DOWN) {
                    LevelParent.this.moveDown();
                } else if (key.getCode() == KeyCode.UP) {
                    LevelParent.this.moveUp();
                } else if (key.getCode() == KeyCode.LEFT) {
                    LevelParent.this.moveLeft();
                } else if (key.getCode() == KeyCode.RIGHT) {
                    LevelParent.this.moveRight();
                }
                // GameScene.this.sumCellNumbersToScore();// we are not adding number by just moving left and right
                scoreText.setText(score + "");
                haveEmptyCell = LevelParent.this.haveEmptyCell();
                if (haveEmptyCell == -1) {
                    if (LevelParent.this.canNotMove()) {
                        UserScore.updateScore(Account.currentUsername, currentLevelName, score);

                        primaryStage.setScene(endGameScene);

                        EndGame.getInstance().endGameShow(endGameScene, endGameRoot, primaryStage, score);
                        root.getChildren().clear();
                        score = 0;
                    }
                } else if(haveEmptyCell == 1)
                    LevelParent.this.randomFillNumber(2);
            });
        });
    }




    //ADDED
    //left
  /*  private void printRowMerge(int rowIndex, String direction) {
        StringBuilder before = new StringBuilder();
        StringBuilder after = new StringBuilder();

        // Get the current row before merging
        for (int j = 0; j < n; j++) {
            before.append("[").append(cells[rowIndex][j].getNumber()).append("]");
        }

        // Copy the row values into an array
        int[] row = new int[n];
        for (int j = 0; j < n; j++) {
            row[j] = cells[rowIndex][j].getNumber();
        }

        // Simulate a merge (only one pass, like 2048)
        for (int j = 0; j < n - 1; j++) {
            if (row[j] != 0 && row[j] == row[j + 1]) {
                row[j] *= 2;
                row[j + 1] = 0;
            }
        }

        // Compact the row (shift everything left)
        int[] compacted = new int[n];
        int idx = 0;
        for (int val : row) {
            if (val != 0) {
                compacted[idx++] = val;
            }
        }

        // Build the 'after' view
        for (int j = 0; j < n; j++) {
            after.append("[").append(compacted[j]).append("]");
        }

        // Print nicely formatted row
        System.out.println("Merged row " + rowIndex + " " + direction + ": " + before + " → " + after);
    }*/


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


    private void playPopAnimation(Text text) {
        ScaleTransition scale = new ScaleTransition(Duration.millis(200), text);
        scale.setFromX(0.1);
        scale.setFromY(0.1);
        scale.setToX(1.0);
        scale.setToY(1.0);
        scale.play();
    }

}