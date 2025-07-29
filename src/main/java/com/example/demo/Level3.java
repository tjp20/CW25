package com.example.demo;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Level3 extends LevelParent {

    public void launch(Stage stage, Scene endScene, Group endRoot) {
        setN(3); // 3x3 grid
        Group root = new Group();
        Scene scene = new Scene(root, 900, 750, Color.GREEN); // green background
        this.game(scene, root, stage, endScene, endRoot);
        stage.setScene(scene);
    }
}