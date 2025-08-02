package com.example.demo;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class Level2 extends LevelParent {

    public void launch(Stage stage, Scene endScene, Group endRoot) {
        setN(4); // 4x4 grid (default)
        Group root = new Group();
        Scene scene = new Scene(root, 900, 750, Color.web("#FFBFD2"));

        // Add level name display
        Text levelText = new Text("Level 2");
        levelText.setFont(Font.font("Arial", FontWeight.BOLD, 30));
        levelText.setX(750); // Centered roughly for 900px width
        levelText.setY(50);  // Top margin
        root.getChildren().add(levelText);

        this.game(scene, root, stage, endScene, endRoot);
        stage.setScene(scene);
        stage.setFullScreen(true);
    }
}