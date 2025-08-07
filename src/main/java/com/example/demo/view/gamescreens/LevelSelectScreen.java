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

public class LevelSelectScreen {

    public static void addToLevelScreen(Group levelRoot, Scene levelScene, Stage primaryStage, String username, Scene endGameScene, Group endgameRoot) {
        levelRoot.getChildren().clear();

        Rectangle bgFill = new Rectangle();
        bgFill.setFill(Color.web("#a5e9eb"));
        bgFill.widthProperty().bind(levelScene.widthProperty());
        bgFill.heightProperty().bind(levelScene.heightProperty());
        levelRoot.getChildren().add(bgFill);

        try {
            Image bg = new Image(LevelSelectScreen.class.getResourceAsStream("/com/example/demo/LevelSelectBackground.png"));
            ImageView bgView = new ImageView(bg);
            bgView.setPreserveRatio(true);
            double fixedWidth = 1600;
            bgView.setFitWidth(fixedWidth);
            bgView.setX(-50);
            bgView.setY(0);
            levelRoot.getChildren().add(bgView);
        } catch (Exception e) {
            System.out.println("LevelSelectBackground.png not found.");
        }

        String btnStyle = "-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill: white; -fx-background-radius: 10;";
        String hoverStyle = "-fx-background-color: #444;";
        double btnWidth = 200;
        double btnHeight = 50;
        double spacing = 50;

        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        double screenWidth = screenBounds.getWidth();

        double totalButtonWidth = 3 * btnWidth + 2 * spacing;
        double startX = (screenWidth - totalButtonWidth) / 2;
        double baseY = 370;

        Button level1Btn = new Button("Level 1");
        level1Btn.setLayoutX(startX);
        level1Btn.setLayoutY(baseY);
        level1Btn.setPrefSize(btnWidth, btnHeight);
        level1Btn.setStyle(btnStyle + "-fx-background-color: #f67280;");
        level1Btn.setOnMouseEntered(e -> level1Btn.setStyle(btnStyle + hoverStyle));
        level1Btn.setOnMouseExited(e -> level1Btn.setStyle(btnStyle + "-fx-background-color: #f67280;"));

        Button level2Btn = new Button("Level 2");
        level2Btn.setLayoutX(startX + btnWidth + spacing);
        level2Btn.setLayoutY(baseY);
        level2Btn.setPrefSize(btnWidth, btnHeight);
        level2Btn.setStyle(btnStyle + "-fx-background-color: #6c5ce7;");
        level2Btn.setOnMouseEntered(e -> level2Btn.setStyle(btnStyle + hoverStyle));
        level2Btn.setOnMouseExited(e -> level2Btn.setStyle(btnStyle + "-fx-background-color: #6c5ce7;"));

        Button level3Btn = new Button("Level 3");
        level3Btn.setLayoutX(startX + 2 * (btnWidth + spacing));
        level3Btn.setLayoutY(baseY);
        level3Btn.setPrefSize(btnWidth, btnHeight);
        level3Btn.setStyle(btnStyle + "-fx-background-color: #00b894;");
        level3Btn.setOnMouseEntered(e -> level3Btn.setStyle(btnStyle + hoverStyle));
        level3Btn.setOnMouseExited(e -> level3Btn.setStyle(btnStyle + "-fx-background-color: #00b894;"));

        Button leaderboardBtn = new Button("Leaderboard");
        leaderboardBtn.setPrefSize(240, btnHeight);
        leaderboardBtn.setLayoutX((screenWidth - 240) / 2);
        leaderboardBtn.setLayoutY(baseY + 80);
        leaderboardBtn.setStyle(btnStyle + "-fx-background-color: #fdcb6e;");
        leaderboardBtn.setOnMouseEntered(e -> leaderboardBtn.setStyle(btnStyle + hoverStyle));
        leaderboardBtn.setOnMouseExited(e -> leaderboardBtn.setStyle(btnStyle + "-fx-background-color: #fdcb6e;"));

        double profileBoxX = screenWidth - 320;
        double profileBoxY = 60;
        UsernameDisplay.addTo(levelRoot, username, profileBoxX, profileBoxY);

        levelRoot.getChildren().addAll(
                level1Btn, level2Btn, level3Btn,
                leaderboardBtn
        );

        level1Btn.setOnAction(e -> new Level1().launch(primaryStage, endGameScene, endgameRoot));
        level2Btn.setOnAction(e -> new Level2().launch(primaryStage, endGameScene, endgameRoot));
        level3Btn.setOnAction(e -> new Level3().launch(primaryStage, endGameScene, endgameRoot));
        leaderboardBtn.setOnAction(e -> LeaderboardScreen.show(primaryStage, levelScene));

        ExitButton.addTo(levelRoot, primaryStage, levelScene.getWidth());
    }
}