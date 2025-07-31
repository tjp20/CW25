package com.example.demo;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class Level3 extends LevelParent {

    public void launch(Stage stage, Scene endScene, Group endRoot) {
        setN(3); // 3x3 grid
        Group root = new Group();
        Scene scene = new Scene(root, 900, 750, Color.GREEN);

        // Add level name display
        Text levelText = new Text("Level 3");
        levelText.setFont(Font.font("Arial", FontWeight.BOLD, 30));
        levelText.setX(750); // Centered roughly for 900px width
        levelText.setY(50);  // Top margin
        root.getChildren().add(levelText);

        this.game(scene, root, stage, endScene, endRoot);
        stage.setScene(scene);
    }
}