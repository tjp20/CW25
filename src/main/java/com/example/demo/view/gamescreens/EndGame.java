package com.example.demo.view.gamescreens;

import com.example.demo.level.Level1;
import com.example.demo.level.LevelParent;
import com.example.demo.user.Account;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.Optional;

/**
 * Singleton class to display the End Game screen.
 * Shows the player's final score and provides options to quit,
 * view rankings, or return to the level selection screen.
 */
public class EndGame {
    private static EndGame singleInstance = null;

    /**
     * Private constructor to enforce singleton pattern.
     */
    private EndGame() { }

    /**
     * Returns the single instance of EndGame.
     *
     * @return the singleton EndGame instance
     */
    public static EndGame getInstance() {
        if (singleInstance == null)
            singleInstance = new EndGame();
        return singleInstance;
    }

    /**
     * Displays the End Game screen with the player's score and options.
     *
     * @param endGameScene The scene to display end game content.
     * @param root The root node of the end game scene.
     * @param primaryStage The main stage of the application.
     * @param score The final score to display.
     * @param level1 The current Level1 instance (can be null), used to stop the timer if present.
     */
    public void endGameShow(Scene endGameScene, Group root, Stage primaryStage, long score, Level1 level1) {
        endGameScene.setFill(Color.web("#8df0b0"));
        double sceneWidth = 900;

        // VBox container for layout
        VBox vbox = new VBox(30);
        vbox.setAlignment(Pos.TOP_CENTER);
        vbox.setLayoutY(100);
        vbox.setLayoutX((sceneWidth - 300) / 2);

        // Game over title
        Text text = new Text("GAME OVER");
        text.setFont(Font.font(70));
        text.setFill(Color.BLACK);

        // Score display
        Text scoreText = new Text("Score: " + score);
        scoreText.setFill(Color.BLACK);
        scoreText.setFont(Font.font(50));

        // Button styling
        String baseStyle = "-fx-font-size: 18px; -fx-font-family: Consolas; -fx-text-fill: white; "
                + "-fx-background-color: darkgreen; -fx-background-radius: 12;";
        String hoverStyle = "-fx-background-color: #2e8b57;";

        // Quit Button
        Button quitButton = new Button("QUIT");
        quitButton.setPrefSize(200, 45);
        quitButton.setStyle(baseStyle);
        quitButton.setOnMouseEntered(e -> quitButton.setStyle(baseStyle + hoverStyle));
        quitButton.setOnMouseExited(e -> quitButton.setStyle(baseStyle));
        quitButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Quit Dialog");
                alert.setHeaderText("Quit from this page");
                alert.setContentText("Are you sure?");

                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK) {
                    primaryStage.close();
                }
            }
        });

        // View Ranks Button
        Button rankButton = new Button("VIEW RANKS");
        rankButton.setPrefSize(200, 45);
        rankButton.setStyle(baseStyle);
        rankButton.setOnMouseEntered(e -> rankButton.setStyle(baseStyle + hoverStyle));
        rankButton.setOnMouseExited(e -> rankButton.setStyle(baseStyle));
        rankButton.setOnAction(e -> {
            if (level1 != null) level1.stopCountdown();
            Group rankRoot = new Group();
            Scene rankScene = new Scene(rankRoot, 900, 750);
            RankScreen.show(rankRoot, LevelParent.getLevelName(), primaryStage, Account.currentUsername, endGameScene, root);
            primaryStage.setScene(rankScene);
            primaryStage.setFullScreen(true);
        });

        // Levels Button
        Button levelButton = new Button("LEVELS");
        levelButton.setPrefSize(200, 45);
        levelButton.setStyle(baseStyle);
        levelButton.setOnMouseEntered(e -> levelButton.setStyle(baseStyle + hoverStyle));
        levelButton.setOnMouseExited(e -> levelButton.setStyle(baseStyle));
        levelButton.setOnAction(e -> {
            if (level1 != null) level1.stopCountdown();
            Group levelRoot = new Group();
            Scene levelScene = new Scene(levelRoot, 900, 750);
            LevelSelectScreen.addToLevelScreen(levelRoot, levelScene, primaryStage, Account.currentUsername, endGameScene, root);
            primaryStage.setScene(levelScene);
            primaryStage.setFullScreen(true);
        });

        // Add all components to the VBox and root
        vbox.getChildren().addAll(text, scoreText, quitButton, rankButton, levelButton);
        root.getChildren().add(vbox);
    }
}