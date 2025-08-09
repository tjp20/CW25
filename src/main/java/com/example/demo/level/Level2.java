package com.example.demo.level;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/**
 * Level2 represents the second level of the game.
 * This level uses a 4x4 grid and displays its own scene.
 */
public class Level2 extends LevelParent {

    /**
     * Launches Level 2 of the game.
     * Sets up the level-specific layout, grid size, and visual elements.
     *
     * @param stage      the primary stage for the game
     * @param endScene   the scene to switch to upon game completion
     * @param endRoot    the root group for the end game screen
     */
    public void launch(Stage stage, Scene endScene, Group endRoot) {
        setN(4); // 4 by 4 grid
        setLevelName("Level 2");
        Group root = new Group();
        Scene scene = new Scene(root, 900, 750, Color.web("#BCB8F5"));

        // Add level name display
        Text levelText = new Text("Level 2");
        levelText.setFont(Font.font("Arial", FontWeight.BOLD, 30));
        levelText.setX(750);
        levelText.setY(50);  // Top margin
        root.getChildren().add(levelText);

        this.game(scene, root, stage, endScene, endRoot);
        stage.setScene(scene);
        stage.setFullScreen(true);
    }
}