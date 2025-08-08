package com.example.demo;

import com.example.demo.level.LevelParent;
import com.example.demo.user.Account;
import com.example.demo.user.CreateAccountScreen;
import com.example.demo.user.LoginScreen;
import com.example.demo.view.gamescreens.MenuScreen;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.util.Scanner;

/**
 * The {@code Main} class initializes and launches the 2048 Retro Game application.
 * It sets up all the primary screens such as menu, login, level select, game, and account creation.
 */
public class Main extends Application {

    /** Root group for the game scene. */
    private Group gameRoot = new Group();

    /** The main game scene. */
    private Scene gameScene;

    /** Scanner for potential console input (currently unused). */
    private static final Scanner input = new Scanner(System.in);

    /**
     * Sets the current game scene.
     *
     * @param gameScene the game {@link Scene} to set
     */
    public void setGameScene(Scene gameScene) {
        this.gameScene = gameScene;
    }

    /**
     * Sets the root {@link Group} used for the game scene.
     *
     * @param gameRoot the root group to set
     */
    public void setGameRoot(Group gameRoot) {
        this.gameRoot = gameRoot;
    }

    /**
     * The entry point for JavaFX applications.
     * Initializes and links all screens: menu, login, game, level, etc.
     *
     * @param primaryStage the main application window
     * @throws Exception in case of any initialization error
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        // Get full screen dimensions
        double screenWidth = Screen.getPrimary().getBounds().getWidth();
        double screenHeight = Screen.getPrimary().getBounds().getHeight();

        primaryStage.setFullScreen(true);
        primaryStage.setTitle("2048 RETRO");
        Account.loadAccounts(); // Load account data

        // --- Initialize Scenes ---

        // Account Login Scene
        Group accountRoot = new Group();
        Scene accountScene = new Scene(accountRoot, screenWidth, screenHeight, Color.rgb(150, 20, 100, 0.2));

        // Menu Scene
        Group menuRoot = new Group();
        Scene menuScene = new Scene(menuRoot, screenWidth, screenHeight);
        MenuScreen.addToMenu(menuRoot, menuScene, accountScene, primaryStage);

        // Level Select Scene
        Group levelRoot = new Group();
        Scene levelScene = new Scene(levelRoot, screenWidth, screenHeight);

        // End Game Scene
        Group endgameRoot = new Group();
        Scene endGameScene = new Scene(endgameRoot, screenWidth, screenHeight, Color.rgb(250, 20, 100, 0.2));

        // Account Creation Scene
        Group getAccountRoot = new Group();
        Scene getAccountScene = new Scene(getAccountRoot, screenWidth, screenHeight, Color.rgb(200, 20, 100, 0.2));
        LoginScreen.addToAccountScreen(accountRoot, accountScene, getAccountScene, primaryStage, levelScene, levelRoot, endGameScene, endgameRoot);
        CreateAccountScreen.addToCreateAccountScreen(getAccountRoot, getAccountScene, accountScene, primaryStage);

        /*// Rank Scene (prepared, not yet linked)
        Group rankRoot = new Group();
        Scene rankScene = new Scene(rankRoot, screenWidth, screenHeight, Color.rgb(250, 50, 120, 0.3));
        BackgroundFill backgroundFill = new BackgroundFill(Color.rgb(120, 100, 100), CornerRadii.EMPTY, Insets.EMPTY);
        Background background = new Background(backgroundFill);*/

        // Game Scene
        Group gameRoot = new Group();
        setGameRoot(gameRoot);
        Scene gameScene = new Scene(gameRoot, screenWidth, screenHeight, Color.rgb(189, 177, 92));
        setGameScene(gameScene);

        // Show Menu initially
        launchMenu(primaryStage, menuScene);

        // Prepare game logic (not started here, only ready)
        LevelParent game = new LevelParent();
        game.game(gameScene, gameRoot, primaryStage, endGameScene, endgameRoot);

        primaryStage.show();
    }

    /**
     * Launches the main menu scene.
     *
     * @param primaryStage the main application window
     * @param menuScene    the menu {@link Scene} to display
     */
    public void launchMenu(Stage primaryStage, Scene menuScene) {
        primaryStage.setScene(menuScene);
    }

    /**
     * Main method that launches the JavaFX application.
     *
     * @param args command-line arguments (unused)
     */
    public static void main(String[] args) {
        launch(args);
    }
}