package com.example.demo;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.util.Scanner;

public class Main extends Application {
    private Group gameRoot = new Group();
    private Scene gameScene;
    private static Scanner input = new Scanner(System.in);

    public void setGameScene(Scene gameScene) {
        this.gameScene = gameScene;
    }

    public void setGameRoot(Group gameRoot) {
        this.gameRoot = gameRoot;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Get full screen dimensions
        double screenWidth = Screen.getPrimary().getBounds().getWidth();
        double screenHeight = Screen.getPrimary().getBounds().getHeight();

        primaryStage.setFullScreen(true);
        primaryStage.setTitle("2048 RETRO ");
        Account.loadAccounts();

        // ACCOUNT
        Group accountRoot = new Group();
        Scene accountScene = new Scene(accountRoot, screenWidth, screenHeight, Color.rgb(150, 20, 100, 0.2));

        // MENU
        Group menuRoot = new Group();
        Scene menuScene = new Scene(menuRoot, screenWidth, screenHeight);

        primaryStage.setScene(menuScene);
        primaryStage.setFullScreen(true);
        primaryStage.show();

        MenuScreen.addToMenu(menuRoot, menuScene, accountScene, primaryStage);

        // LEVEL SCREEN
        Group levelRoot = new Group();
        Scene levelScene = new Scene(levelRoot, screenWidth, screenHeight);

        // END GAME
        Group endgameRoot = new Group();
        Scene endGameScene = new Scene(endgameRoot, screenWidth, screenHeight, Color.rgb(250, 20, 100, 0.2));

        // GET ACCOUNT
        Group getAccountRoot = new Group();
        Scene getAccountScene = new Scene(getAccountRoot, screenWidth, screenHeight, Color.rgb(200, 20, 100, 0.2));
        LoginScreen.addToAccountScreen(accountRoot, accountScene, getAccountScene, primaryStage, levelScene, levelRoot, endGameScene, endgameRoot);
        CreateAccountScreen.addToCreateAccountScreen(getAccountRoot, getAccountScene, accountScene, primaryStage);

        /*Text testLabel = new Text("This is the GET ACCOUNT SCREEN");
        testLabel.setFill(Color.BLACK);
        testLabel.setFont(Font.font("Arial", 24));
        testLabel.setX(250);
        testLabel.setY(200);
        getAccountRoot.getChildren().add(testLabel);*/

        // RANK GAME
        Group rankRoot = new Group();
        Scene rankScene = new Scene(rankRoot, screenWidth, screenHeight, Color.rgb(250, 50, 120, 0.3));

        BackgroundFill background_fill = new BackgroundFill(Color.rgb(120, 100, 100), CornerRadii.EMPTY, Insets.EMPTY);
        Background background = new Background(background_fill);

        /*Rectangle backgroundOfMenu = new Rectangle(240, 120, Color.rgb(120, 120, 120, 0.2));
        backgroundOfMenu.setX(screenWidth / 2 - 120);
        backgroundOfMenu.setY(180);
        menuRoot.getChildren().add(backgroundOfMenu);

        Rectangle backgroundOfMenuForPlay = new Rectangle(240, 140, Color.rgb(120, 20, 100, 0.2));
        backgroundOfMenuForPlay.setX(screenWidth / 2 - 120);
        backgroundOfMenuForPlay.setY(180);
        accountRoot.getChildren().add(backgroundOfMenuForPlay);*/

        Group gameRoot = new Group();
        setGameRoot(gameRoot);
        Scene gameScene = new Scene(gameRoot, screenWidth, screenHeight, Color.rgb(189, 177, 92));
        setGameScene(gameScene);

        launchMenu(primaryStage, menuScene);

        LevelParent game = new LevelParent();
        game.game(gameScene, gameRoot, primaryStage, endGameScene, endgameRoot);

        primaryStage.show();
    }

    public void launchMenu(Stage primaryStage, Scene menuScene) {
        primaryStage.setScene(menuScene);
    }

    public static void main(String[] args) {
        launch(args);
    }
}