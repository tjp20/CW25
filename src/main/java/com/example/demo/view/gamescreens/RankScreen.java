package com.example.demo.view.gamescreens;

import com.example.demo.user.UserScore;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.List;
import java.util.stream.Collectors;

/**
 * The {@code RankScreen} class displays the top 3 high scores
 * for a specific level in the 2048 game.
 */
public class RankScreen {

    /**
     * Displays the RankScreen showing the top 3 scores for a given level.
     *
     * @param root          The root {@link Group} where all UI elements are added.
     * @param levelName     The name of the level whose scores will be displayed.
     * @param primaryStage  The main application {@link Stage}.
     * @param username      The current player's username.
     * @param endGameScene  The scene to return to after ending the game.
     * @param endGameRoot   The root group of the end game scene.
     */
    public static void show(Group root, String levelName, Stage primaryStage, String username, Scene endGameScene, Group endGameRoot) {
        root.getChildren().clear();

        // Title
        Label title = new Label(" Top 3 Scores - " + levelName);
        title.setFont(Font.font("Consolas", 36));
        title.setTextFill(Color.DARKBLUE);
        title.setLayoutX(280);
        title.setLayoutY(50);
        root.getChildren().add(title);

        // Retrieve and filter top scores
        List<String[]> allScores = UserScore.getAllScoresSimple();

        List<String[]> topScores = allScores.stream()
                .filter(arr -> arr[1].equals(levelName))
                .sorted((a, b) -> Long.compare(Long.parseLong(b[2]), Long.parseLong(a[2])))
                .limit(3)
                .collect(Collectors.toList());

        // Display scores
        int yStart = 150;
        for (int i = 0; i < topScores.size(); i++) {
            String[] entry = topScores.get(i);
            String rankText = (i + 1) + ". " + entry[0] + " - " + entry[2];
            Label scoreLabel = new Label(rankText);
            scoreLabel.setFont(Font.font("Consolas", 26));
            scoreLabel.setTextFill(Color.BLACK);
            scoreLabel.setLayoutX(350);
            scoreLabel.setLayoutY(yStart + i * 60);
            root.getChildren().add(scoreLabel);
        }

        // No scores message
        if (topScores.isEmpty()) {
            Label empty = new Label("No scores yet for this level.");
            empty.setFont(Font.font("Consolas", 24));
            empty.setTextFill(Color.GRAY);
            empty.setLayoutX(330);
            empty.setLayoutY(180);
            root.getChildren().add(empty);
        }

        // Exit button to return to LevelSelectScreen
        Button exitButton = new Button("Exit");
        exitButton.setFont(Font.font("Consolas", 18));
        exitButton.setTextFill(Color.WHITE);
        exitButton.setStyle("-fx-background-color: darkred;");
        exitButton.setLayoutX(380);
        exitButton.setLayoutY(460);
        root.getChildren().add(exitButton);

        exitButton.setOnAction(e -> {
            Group levelRoot = new Group();
            Scene levelScene = new Scene(levelRoot, 900, 750);
            LevelSelectScreen.addToLevelScreen(levelRoot, levelScene, primaryStage, username, endGameScene, endGameRoot);
            primaryStage.setScene(levelScene);
            primaryStage.setFullScreen(true);
        });
    }
}