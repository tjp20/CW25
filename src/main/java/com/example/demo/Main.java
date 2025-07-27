package com.example.demo;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.Scanner;

public class Main extends Application {
    static final int WIDTH = 900;
    static final int HEIGHT = 750;
    private Group gameRoot = new Group();
    private Scene gameScene = new Scene(gameRoot, WIDTH, HEIGHT, Color.rgb(189, 177, 92));
    private static Scanner input= new Scanner(System.in);

    public void setGameScene(Scene gameScene) {
        this.gameScene = gameScene;
    }

    public void setGameRoot(Group gameRoot) {
        this.gameRoot = gameRoot;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setMaximized(true);
        primaryStage.setResizable(true);
        primaryStage.setTitle("2048 RETRO ");

        //ACCOUNT
        Group accountRoot = new Group();
        Scene accountScene = new Scene(accountRoot, WIDTH, HEIGHT, Color.rgb(150, 20, 100, 0.2));



        //MENU
        Group menuRoot = new Group();
        Scene menuScene = new Scene(menuRoot, WIDTH, HEIGHT);
        MenuScreen.addToMenu(menuRoot, menuScene, accountScene, primaryStage);


        Group levelRoot = new Group();
        Scene levelScene = new Scene(levelRoot, WIDTH, HEIGHT);

        //GET ACCOUNT
        Group getAccountRoot = new Group();
        Scene getAccountScene = new Scene(getAccountRoot, WIDTH, HEIGHT, Color.rgb(200, 20, 100, 0.2));
        AccountScreen.addToAccountScreen(accountRoot, accountScene, getAccountScene, primaryStage, levelScene, levelRoot);
        CreateAccountScreen.addToCreateAccountScreen(getAccountRoot, getAccountScene, accountScene,primaryStage);

        Text testLabel = new Text("This is the GET ACCOUNT SCREEN");
        testLabel.setFill(Color.BLACK);
        testLabel.setFont(Font.font("Arial", 24));
        testLabel.setX(250);
        testLabel.setY(200);
        getAccountRoot.getChildren().add(testLabel);


        //END GAME
        Group endgameRoot = new Group();
        Scene endGameScene = new Scene(endgameRoot, WIDTH, HEIGHT, Color.rgb(250, 20, 100, 0.2));

        //RANK GAME
        Group rankRoot = new Group();
        Scene rankScene = new Scene(rankRoot, WIDTH, HEIGHT, Color.rgb(250, 50, 120, 0.3));


        BackgroundFill background_fill = new BackgroundFill(Color.rgb(120, 100, 100), CornerRadii.EMPTY, Insets.EMPTY);
        Background background = new Background(background_fill);


        Rectangle backgroundOfMenu = new Rectangle(240, 120, Color.rgb(120, 120, 120, 0.2));
        backgroundOfMenu.setX(WIDTH / 2 - 120);
        backgroundOfMenu.setY(180);
        menuRoot.getChildren().add(backgroundOfMenu);

        Rectangle backgroundOfMenuForPlay = new Rectangle(240, 140, Color.rgb(120, 20, 100, 0.2));
        backgroundOfMenuForPlay.setX(WIDTH / 2 - 120);
        backgroundOfMenuForPlay.setY(180);
        accountRoot.getChildren().add(backgroundOfMenuForPlay);

        Group gameRoot = new Group();
        setGameRoot(gameRoot);
        Scene gameScene = new Scene(gameRoot, WIDTH, HEIGHT, Color.rgb(189, 177, 92));
        setGameScene(gameScene);
        // primaryStage.setScene(gameScene);
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