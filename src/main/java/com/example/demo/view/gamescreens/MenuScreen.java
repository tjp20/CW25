package com.example.demo.view.gamescreens;

import com.example.demo.view.ExitButton;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 * The {@code MenuScreen} class is responsible for creating and displaying
 * the main menu screen of the game, including background visuals and the play button.
 */
public class MenuScreen {

    /**
     * Adds the menu layout and elements to the given root group, including:
     * - Background color and image
     * - Play button with hover effect
     * - Exit button
     *
     * @param menuRoot      The root {@link Group} to which UI elements are added.
     * @param scene         The current {@link Scene} for binding width and height.
     * @param accountScene  The {@link Scene} to switch to after the play button is clicked.
     * @param primaryStage  The primary {@link Stage} of the application.
     */
    public static void addToMenu(Group menuRoot, Scene scene, Scene accountScene, Stage primaryStage) {
        // Set background fill color
        Rectangle backgroundFill = new Rectangle();
        backgroundFill.setFill(Color.web("#e7d9c6"));
        backgroundFill.widthProperty().bind(scene.widthProperty());
        backgroundFill.heightProperty().bind(scene.heightProperty());
        menuRoot.getChildren().add(backgroundFill);

        // Load and display background image (centered)
        Image backgroundImage = new Image(MenuScreen.class.getResourceAsStream("/com/example/demo/2048HomeBackground.jpg"));
        ImageView backgroundView = new ImageView(backgroundImage);
        double fixedWidth = 1300;
        backgroundView.setFitWidth(fixedWidth);
        backgroundView.setPreserveRatio(true);
        backgroundView.xProperty().bind(scene.widthProperty().subtract(fixedWidth).divide(2));
        backgroundView.setY(0);
        menuRoot.getChildren().add(backgroundView);

        // Load play button images (normal and hover states)
        Image playNormal = new Image(MenuScreen.class.getResourceAsStream("/com/example/demo/PlayButton1.png"));
        Image playHover = new Image(MenuScreen.class.getResourceAsStream("/com/example/demo/PlayButton2.png"));
        ImageView playView = new ImageView(playNormal);

        // Resize play button image
        playView.setFitWidth(250);
        playView.setFitHeight(250);

        // Create transparent button and set graphic
        Button playButton = new Button();
        playButton.setGraphic(playView);
        playButton.setStyle("-fx-background-color: transparent;");

        // Position the button manually
        playButton.setLayoutX(920);
        playButton.setLayoutY(340);

        // Add hover effect to play button
        playButton.setOnMouseEntered(e -> playView.setImage(playHover));
        playButton.setOnMouseExited(e -> playView.setImage(playNormal));

        // Set click action to navigate to account screen
        playButton.setOnAction(e -> {
            System.out.println("Play button clicked!"); // Debug message
            primaryStage.setScene(accountScene);
        });

        // Add play button to root
        menuRoot.getChildren().add(playButton);

        // Add exit button at the top-right
        ExitButton.addTo(menuRoot, primaryStage, scene.getWidth());
    }
}