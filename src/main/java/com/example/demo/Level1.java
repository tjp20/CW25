package com.example.demo;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;


public class Level1 extends LevelParent {

    public void launch(Stage stage, Scene endScene, Group endRoot) {
        LevelParent.setN(5);
        setN(5); // 4x4 grid (default)
        Group root = new Group();
        Scene scene = new Scene(root, 900, 750, Color.web("#FFBFD2")); // any neutral color

        // Add level name display
        Text levelText = new Text("Level 1");
        levelText.setFont(Font.font("Arial", FontWeight.BOLD, 30));
        levelText.setX(750);
        levelText.setY(50);
        root.getChildren().add(levelText);


        this.game(scene, root, stage, endScene, endRoot);
        stage.setScene(scene);
        stage.setFullScreen(true);
    }
}