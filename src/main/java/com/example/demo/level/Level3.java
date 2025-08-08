package com.example.demo.level;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/**
 * Level3 represents the third level of the 2048 game.
 * This level features a 3x3 grid with its own scene configuration.
 */
public class Level3 extends LevelParent {

    /**
     * Launches Level 3.
     * Initializes a 3x3 game grid and sets up the game screen for this level.
     *
     * @param stage     the main game stage
     * @param endScene  the scene to transition to after the game ends
     * @param endRoot   the root group for the end game scene
     */
    public void launch(Stage stage, Scene endScene, Group endRoot) {
        setN(3);
        setLevelName("Level 3");

        Group root = new Group();
        Scene scene = new Scene(root, 900, 750, Color.GREEN);

        // Display level title
        Text levelText = new Text("Level 3");
        levelText.setFont(Font.font("Arial", FontWeight.BOLD, 30));
        levelText.setX(750);
        levelText.setY(50);
        root.getChildren().add(levelText);

        this.game(scene, root, stage, endScene, endRoot);
        stage.setScene(scene);
        stage.setFullScreen(true);
    }
}