package com.example.demo;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class LevelScreen {

    public static void addToLevelScreen(Group levelRoot, Scene levelScene, Stage primaryStage, String username) {
        // Welcome message with username
        Text welcomeText = new Text("Welcome, " + username);
        welcomeText.setFont(Font.font("Arial", 28));
        welcomeText.setFill(Color.BLACK);
        welcomeText.setX(300);
        welcomeText.setY(100);
        levelRoot.getChildren().add(welcomeText);

        // Level 1 button
        Button level1 = new Button("Level 1");
        level1.setLayoutX(400);
        level1.setLayoutY(200);
        levelRoot.getChildren().add(level1);

        // Level 2 button
        Button level2 = new Button("Level 2");
        level2.setLayoutX(400);
        level2.setLayoutY(260);
        levelRoot.getChildren().add(level2);

        // Level 3 button
        Button level3 = new Button("Level 3");
        level3.setLayoutX(400);
        level3.setLayoutY(320);
        levelRoot.getChildren().add(level3);

        // Set the scene
        primaryStage.setScene(levelScene);
    }
}