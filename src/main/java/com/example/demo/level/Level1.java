package com.example.demo.level;

import com.example.demo.user.Account;
import com.example.demo.view.gamescreens.EndGame;
import com.example.demo.user.UserScore;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.util.Duration;



public class Level1 extends LevelParent {

    private Timeline countdown;


    public void launch(Stage stage, Scene endScene, Group endRoot) {

        if (countdown != null) {
            countdown.stop();
        }
        LevelParent.setN(5);
        setN(5);
        setLevelName("Level 1");

        Group root = new Group();
        Scene scene = new Scene(root, 900, 750, Color.web("#FFBFD2")); // any neutral color

        // Add level name display
        Text levelText = new Text("Level 1");
        levelText.setFont(Font.font("Arial", FontWeight.BOLD, 30));
        levelText.setX(750);
        levelText.setY(50);
        root.getChildren().add(levelText);

        // Add TIMER text
        Label timerLabel = new Label("Time Left: 60s");
        timerLabel.setFont(Font.font("Consolas", 24));
        timerLabel.setLayoutX(1050);
        timerLabel.setLayoutY(100);
        root.getChildren().add(timerLabel);

        this.game(scene, root, stage, endScene, endRoot);

        // Timer countdown: 60 seconds
        final int[] timeLeft = {60};
        // final Timeline[] countdown = new Timeline[1];

        countdown = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            timeLeft[0]--;
            timerLabel.setText("Time Left: " + timeLeft[0] + "s");

            if (timeLeft[0] <= 0) {
                countdown.stop();

                //  Disable key input by removing handler
                scene.setOnKeyPressed(null);

                // Clear previous end screen elements
                endRoot.getChildren().clear();

                //  Show End Game Screen
                UserScore.updateScore(Account.currentUsername, getLevelName(), getScore()); // optional
                EndGame.getInstance().endGameShow(endScene, endRoot, stage, getScore(),this);

                //  Change scene to end game
                stage.setScene(endScene);
            }
        }));
        countdown.setCycleCount(Timeline.INDEFINITE);
        countdown.play();






        stage.setScene(scene);
        stage.setFullScreen(true);
    }
    public void stopCountdown() {
        if (countdown != null) {
            countdown.stop();
        }
    }
    public void resumeCountdown() {
        if (countdown != null) {
            countdown.play();
        }
    }


}