package com.example.demo;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Level2 extends LevelParent {

    public void launch(Stage stage, Scene endScene, Group endRoot) {
        setN(4); // 4x4 grid (default)
        Group root = new Group();
        Scene scene = new Scene(root, 900, 750, Color.web("#FFBFD2")); // any neutral color
        this.game(scene, root, stage, endScene, endRoot);
        stage.setScene(scene);
    }
}