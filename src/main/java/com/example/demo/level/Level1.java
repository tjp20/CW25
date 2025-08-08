package com.example.demo.level;

import com.example.demo.user.Account;
import com.example.demo.user.UserScore;
import com.example.demo.view.gamescreens.EndGame;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * Level1 is a timed version of the 2048 game with a 60-second countdown.
 * When time runs out, the game ends and transitions to an end screen.
 */
public class Level1 extends LevelParent {

    /** Timer that handles the 60-second countdown. */
    private Timeline countdown;

    /**
     * Launches Level 1 of the game with a 5x5 grid and a 60-second timer.
     *
     * @param stage the JavaFX stage
     * @param endScene the scene to show when time ends
     * @param endRoot the root group for the end screen
     */
    public void launch(Stage stage, Scene endScene, Group endRoot) {
        // Stop any existing countdown
        if (countdown != null) {
            countdown.stop();
        }

        // Set level size and name
        LevelParent.setN(5);
        setN(5);
        setLevelName("Level 1");

        // Create root and scene for the level
        Group root = new Group();
        Scene scene = new Scene(root, 900, 750, Color.web("#FFBFD2")); // pink background

        // --- UI: Level Title ---
        Text levelText = new Text("Level 1");
        levelText.setFont(Font.font("Arial", FontWeight.BOLD, 30));
        levelText.setX(750);
        levelText.setY(50);
        root.getChildren().add(levelText);

        // --- UI: Timer Label ---
        Label timerLabel = new Label("Time Left: 60s");
        timerLabel.setFont(Font.font("Consolas", 24));
        timerLabel.setLayoutX(1050);
        timerLabel.setLayoutY(100);
        root.getChildren().add(timerLabel);


        this.game(scene, root, stage, endScene, endRoot);

        // Start 60-second countdown
        final int[] timeLeft = {60};

        countdown = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            timeLeft[0]--;
            timerLabel.setText("Time Left: " + timeLeft[0] + "s");

            // When time runs out
            if (timeLeft[0] <= 0) {
                countdown.stop();
                scene.setOnKeyPressed(null);
                endRoot.getChildren().clear();

                // Save user score and go to end screen
                UserScore.updateScore(Account.currentUsername, getLevelName(), getScore());
                EndGame.getInstance().endGameShow(endScene, endRoot, stage, getScore(), this);
                stage.setScene(endScene);
            }
        }));
        countdown.setCycleCount(Timeline.INDEFINITE);
        countdown.play();


        stage.setScene(scene);
        stage.setFullScreen(true);
    }

    /**
     * Stops the countdown timer (e.g., when game is paused).
     */
    public void stopCountdown() {
        if (countdown != null) {
            countdown.stop();
        }
    }

    /**
     * Resumes the countdown timer (e.g., when game is resumed).
     */
    public void resumeCountdown() {
        if (countdown != null) {
            countdown.play();
        }
    }
}