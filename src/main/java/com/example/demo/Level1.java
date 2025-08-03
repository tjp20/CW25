package com.example.demo;

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

    public void launch(Stage stage, Scene endScene, Group endRoot) {
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
        final Timeline[] countdown = new Timeline[1];

        countdown[0] = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            timeLeft[0]--;
            timerLabel.setText("Time Left: " + timeLeft[0] + "s");

            if (timeLeft[0] <= 0) {
                countdown[0].stop();

                //  Disable key input by removing handler
                scene.setOnKeyPressed(null);

                //  Show End Game Screen
                UserScore.updateScore(Account.currentUsername, getLevelName(), getScore()); // optional
                EndGame.getInstance().endGameShow(endScene, endRoot, stage, getScore());

                //  Change scene to end game
                stage.setScene(endScene);
            }
        }));
        countdown[0].setCycleCount(Timeline.INDEFINITE);
        countdown[0].play();


        stage.setScene(scene);
        stage.setFullScreen(true);
    }
}