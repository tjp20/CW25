package com.example.demo.level;

import com.example.demo.controller.GameController;
import com.example.demo.debug.ScoreDebugger;
import com.example.demo.user.Account;
import com.example.demo.user.UserScore;
import com.example.demo.view.UsernameDisplay;
import com.example.demo.view.gamescreens.EndGame;
import com.example.demo.view.gamescreens.LevelSelectScreen;
import com.example.demo.view.gamescreens.PauseScreen;
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
    private GameController gameController;
    private long score = 0;
    private Text scoreText = new Text();
    private PauseScreen pauseScreen;

    private String currentLevelName = "Level 1";
    private static String levelName = "Level 1";



    public void setLevelName(String name) {
        this.currentLevelName = name;
        levelName = name;
    }
    public static String getLevelName() {
        return levelName;
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


    public void game(Scene gameScene, Group root, Stage primaryStage, Scene endGameScene, Group endGameRoot) {
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

        gameController = new GameController(cells, n, mergedScore -> {
            score += mergedScore;
            scoreText.setText(String.valueOf(score));
            ScoreDebugger.debugMergeScore(score,(int) mergedScore);
        });

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


        // === PAUSE BUTTON ===

        pauseScreen = new PauseScreen(
                root,
                () -> {
                    if (!pauseScreen.isPaused()) {
                        Platform.runLater(() -> root.requestFocus());
                    }
                },
                () -> { // onGoLevels
                    Group levelRoot = new Group();
                    Scene levelScene = new Scene(levelRoot, 900, 750);
                    LevelSelectScreen.addToLevelScreen(levelRoot, levelScene, primaryStage, Account.currentUsername, endGameScene, endGameRoot);
                    primaryStage.setScene(levelScene);
                    primaryStage.setFullScreen(true);
                },
                () -> { // onRetry
                    root.getChildren().clear();
                    if (currentLevelName.equals("Level 1")) {
                        Level1 retry = new Level1();
                        retry.launch(primaryStage, endGameScene, endGameRoot); // includes timer
                    } else {
                        LevelParent retry = new LevelParent();
                        retry.setLevelName(currentLevelName);
                        retry.game(gameScene, root, primaryStage, endGameScene, endGameRoot);
                        Platform.runLater(() -> root.requestFocus());
                    }
                }
        );




        // root.getChildren().add(pauseButton);
        Platform.runLater(() -> gameScene.getRoot().requestFocus());

        randomFillNumber(1);
        randomFillNumber(1);

        gameScene.addEventHandler(KeyEvent.KEY_PRESSED, key ->{
            Platform.runLater(() -> {
                if (pauseScreen != null && pauseScreen.isPaused()) return;
                KeyCode code = key.getCode();
                if (code != KeyCode.UP && code != KeyCode.DOWN && code != KeyCode.LEFT && code != KeyCode.RIGHT) {
                    return;  // Ignore Enter, Space, or other keys
                }
                int haveEmptyCell;
                if (key.getCode() == KeyCode.DOWN) {
                    gameController.moveDown();
                } else if (key.getCode() == KeyCode.UP) {
                    gameController.moveUp();
                } else if (key.getCode() == KeyCode.LEFT) {
                    gameController.moveLeft();
                } else if (key.getCode() == KeyCode.RIGHT) {
                    gameController.moveRight();
                }
                // GameScene.this.sumCellNumbersToScore();// we are not adding number by just moving left and right
                scoreText.setText(score + "");
                haveEmptyCell = LevelParent.this.haveEmptyCell();
                if (haveEmptyCell == -1) {
                    if (gameController.canNotMove()) {
                        UserScore.updateScore(Account.currentUsername, currentLevelName, score);

                        primaryStage.setScene(endGameScene);

                        EndGame.getInstance().endGameShow(endGameScene, endGameRoot, primaryStage, score);
                        root.getChildren().clear();
                        score = 0;
                    }
                } else if(haveEmptyCell == 1 && gameController.hasMoved())
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





    private void playPopAnimation(Text text) {
        ScaleTransition scale = new ScaleTransition(Duration.millis(200), text);
        scale.setFromX(0.1);
        scale.setFromY(0.1);
        scale.setToX(1.0);
        scale.setToY(1.0);
        scale.play();
    }

    public long getScore() {
        return this.score;
    }

}