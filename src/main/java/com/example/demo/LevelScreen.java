package com.example.demo;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class LevelScreen {

    public static void addToLevelScreen(Group levelRoot, Scene levelScene, Stage primaryStage, String username, Scene endGameScene, Group endgameRoot) {
        // Welcome message with username
        Text welcomeText = new Text("Welcome, " + username);
        welcomeText.setFont(Font.font("Arial", 28));
        welcomeText.setFill(Color.BLACK);
        welcomeText.setX(300);
        welcomeText.setY(100);
        levelRoot.getChildren().add(welcomeText);

        // Button for Level 1
        Button level1Btn = new Button("Level 1");
        level1Btn.setLayoutX(400);
        level1Btn.setLayoutY(200);
        levelRoot.getChildren().add(level1Btn);

        // Button for Level 2
        Button level2Btn = new Button("Level 2");
        level2Btn.setLayoutX(400);
        level2Btn.setLayoutY(250);
        levelRoot.getChildren().add(level2Btn);

        // Button for Level 3
        Button level3Btn = new Button("Level 3");
        level3Btn.setLayoutX(400);
        level3Btn.setLayoutY(300);
        levelRoot.getChildren().add(level3Btn);

        // Button actions
        level1Btn.setOnAction(e -> {
            Level1 level1 = new Level1();
            level1.launch(primaryStage, endGameScene, endgameRoot);
        });

        level2Btn.setOnAction(e -> {
            Level2 level2 = new Level2();
            level2.launch(primaryStage, endGameScene, endgameRoot);
        });

        level3Btn.setOnAction(e -> {
            Level3 level3 = new Level3();
            level3.launch(primaryStage, endGameScene, endgameRoot);
        });
    }
}