package com.example.demo;

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


public class EndGame {
    private static EndGame singleInstance = null;
    private EndGame(){

    }
    public static EndGame getInstance(){
        if(singleInstance == null)
            singleInstance= new EndGame();
        return singleInstance;
    }

    public void endGameShow(Scene endGameScene, Group root, Stage primaryStage,long score){
        endGameScene.setFill(Color.web("#8df0b0"));
        double sceneWidth = 900;

        // VBox to hold everything
        VBox vbox = new VBox(30);
        vbox.setAlignment(Pos.TOP_CENTER);
        vbox.setLayoutY(100);
        vbox.setLayoutX((sceneWidth - 300) / 2);

        Text text = new Text("GAME OVER");
        //text.relocate(250,250);
        text.setFont(Font.font(70));
        text.setFill(Color.BLACK);
        //root.getChildren().add(text);

        //score text
        Text scoreText = new Text("Score: " + score);
        scoreText.setFill(Color.BLACK);
        //scoreText.relocate(250,600);
        scoreText.setFont(Font.font(50));
        //root.getChildren().add(scoreText);

        // Button styling
        String baseStyle = "-fx-font-size: 18px; -fx-font-family: Consolas; -fx-text-fill: white; "
                + "-fx-background-color: darkgreen; -fx-background-radius: 12;";
        String hoverStyle = "-fx-background-color: #2e8b57;";

        Button quitButton = new Button("QUIT");
        quitButton.setPrefSize(200,45);
        //quitButton.setTextFill(Color.PINK);
        //root.getChildren().add(quitButton);
        //quitButton.relocate(100,800);
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
                if (result.get() == ButtonType.OK){
                    //root.getChildren().clear();
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
            Group levelRoot = new Group();
            Scene levelScene = new Scene(levelRoot, 900, 750);
            LevelScreen.addToLevelScreen(levelRoot, levelScene, primaryStage, Account.currentUsername, endGameScene, root);
            primaryStage.setScene(levelScene);
        });

        // Add all to VBox
        vbox.getChildren().addAll(text, scoreText, quitButton, rankButton, levelButton);
        root.getChildren().add(vbox);



    }
}