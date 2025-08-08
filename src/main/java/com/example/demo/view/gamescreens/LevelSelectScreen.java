package com.example.demo.view.gamescreens;

import com.example.demo.level.Level1;
import com.example.demo.level.Level2;
import com.example.demo.level.Level3;
import com.example.demo.view.ExitButton;
import com.example.demo.view.UsernameDisplay;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 * Handles the creation and display of the level selection screen.
 * Provides buttons for choosing different game levels and accessing the leaderboard.
 */
public class LevelSelectScreen {

    /**
     * Adds UI elements for level selection and leaderboard access to the given root group.
     * Also displays the current user's name and an exit button.
     *
     * @param levelRoot     The root group where UI elements will be added.
     * @param levelScene    The scene associated with the level selection screen.
     * @param primaryStage  The main application stage.
     * @param username      The current player's username.
     * @param endGameScene  The scene to switch to when the game ends.
     * @param endgameRoot   The root group used in the endgame scene.
     */
    public static void addToLevelScreen(Group levelRoot, Scene levelScene, Stage primaryStage,
                                        String username, Scene endGameScene, Group endgameRoot) {
        levelRoot.getChildren().clear();

        // Background fill
        Rectangle bgFill = new Rectangle();
        bgFill.setFill(Color.web("#a5e9eb"));
        bgFill.widthProperty().bind(levelScene.widthProperty());
        bgFill.heightProperty().bind(levelScene.heightProperty());
        levelRoot.getChildren().add(bgFill);

        // Background image
        try {
            Image bg = new Image(LevelSelectScreen.class.getResourceAsStream("/com/example/demo/LevelSelectBackground.png"));
            ImageView bgView = new ImageView(bg);
            bgView.setPreserveRatio(true);
            bgView.setFitWidth(1600);
            bgView.setX(-50);
            bgView.setY(0);
            levelRoot.getChildren().add(bgView);
        } catch (Exception e) {
            System.out.println("LevelSelectBackground.png not found.");
        }

        // Button styling
        String btnStyle = "-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill: white; -fx-background-radius: 10;";
        String hoverStyle = "-fx-background-color: #444;";
        double btnWidth = 200;
        double btnHeight = 50;
        double spacing = 50;

        // Screen positioning
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        double screenWidth = screenBounds.getWidth();
        double totalButtonWidth = 3 * btnWidth + 2 * spacing;
        double startX = (screenWidth - totalButtonWidth) / 2;
        double baseY = 370;

        // Level 1 button
        Button level1Btn = new Button("Level 1");
        level1Btn.setLayoutX(startX);
        level1Btn.setLayoutY(baseY);
        level1Btn.setPrefSize(btnWidth, btnHeight);
        level1Btn.setStyle(btnStyle + "-fx-background-color: #f67280;");
        level1Btn.setOnMouseEntered(e -> level1Btn.setStyle(btnStyle + hoverStyle));
        level1Btn.setOnMouseExited(e -> level1Btn.setStyle(btnStyle + "-fx-background-color: #f67280;"));

        // Level 2 button
        Button level2Btn = new Button("Level 2");
        level2Btn.setLayoutX(startX + btnWidth + spacing);
        level2Btn.setLayoutY(baseY);
        level2Btn.setPrefSize(btnWidth, btnHeight);
        level2Btn.setStyle(btnStyle + "-fx-background-color: #6c5ce7;");
        level2Btn.setOnMouseEntered(e -> level2Btn.setStyle(btnStyle + hoverStyle));
        level2Btn.setOnMouseExited(e -> level2Btn.setStyle(btnStyle + "-fx-background-color: #6c5ce7;"));

        // Level 3 button
        Button level3Btn = new Button("Level 3");
        level3Btn.setLayoutX(startX + 2 * (btnWidth + spacing));
        level3Btn.setLayoutY(baseY);
        level3Btn.setPrefSize(btnWidth, btnHeight);
        level3Btn.setStyle(btnStyle + "-fx-background-color: #00b894;");
        level3Btn.setOnMouseEntered(e -> level3Btn.setStyle(btnStyle + hoverStyle));
        level3Btn.setOnMouseExited(e -> level3Btn.setStyle(btnStyle + "-fx-background-color: #00b894;"));

        // Leaderboard button
        Button leaderboardBtn = new Button("Leaderboard");
        leaderboardBtn.setPrefSize(240, btnHeight);
        leaderboardBtn.setLayoutX((screenWidth - 240) / 2);
        leaderboardBtn.setLayoutY(baseY + 80);
        leaderboardBtn.setStyle(btnStyle + "-fx-background-color: #fdcb6e;");
        leaderboardBtn.setOnMouseEntered(e -> leaderboardBtn.setStyle(btnStyle + hoverStyle));
        leaderboardBtn.setOnMouseExited(e -> leaderboardBtn.setStyle(btnStyle + "-fx-background-color: #fdcb6e;"));

        // Username display
        double profileBoxX = screenWidth - 320;
        double profileBoxY = 60;
        UsernameDisplay.addTo(levelRoot, username, profileBoxX, profileBoxY);

        // Add buttons to root
        levelRoot.getChildren().addAll(level1Btn, level2Btn, level3Btn, leaderboardBtn);

        // Button actions
        level1Btn.setOnAction(e -> new Level1().launch(primaryStage, endGameScene, endgameRoot));
        level2Btn.setOnAction(e -> new Level2().launch(primaryStage, endGameScene, endgameRoot));
        level3Btn.setOnAction(e -> new Level3().launch(primaryStage, endGameScene, endgameRoot));
        leaderboardBtn.setOnAction(e -> LeaderboardScreen.show(primaryStage, levelScene));

        // Exit button
        ExitButton.addTo(levelRoot, primaryStage, levelScene.getWidth());
    }
}