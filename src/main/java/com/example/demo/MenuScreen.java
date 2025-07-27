package com.example.demo;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class MenuScreen {

    public static void addToMenu(Group menuRoot, Scene scene, Scene accountScene, Stage primaryStage) {
        // Background fill color
        Rectangle backgroundFill = new Rectangle();
        backgroundFill.setFill(Color.web("#e7d9c6"));
        backgroundFill.widthProperty().bind(scene.widthProperty());
        backgroundFill.heightProperty().bind(scene.heightProperty());
        menuRoot.getChildren().add(backgroundFill);

        // Background image (centered with fixed width)
        Image backgroundImage = new Image(MenuScreen.class.getResourceAsStream("/com/example/demo/2048HomeBackground.jpg"));
        ImageView backgroundView = new ImageView(backgroundImage);
        double fixedWidth = 1300;
        backgroundView.setFitWidth(fixedWidth);
        backgroundView.setPreserveRatio(true);
        backgroundView.xProperty().bind(scene.widthProperty().subtract(fixedWidth).divide(2));
        backgroundView.setY(0);
        menuRoot.getChildren().add(backgroundView);

        // Play button with hover effect
        Image playNormal = new Image(MenuScreen.class.getResourceAsStream("/com/example/demo/PlayButton1.png"));
        Image playHover = new Image(MenuScreen.class.getResourceAsStream("/com/example/demo/PlayButton2.png"));
        ImageView playView = new ImageView(playNormal);

        // Resize the image (which effectively resizes the button)
        playView.setFitWidth(250);  // change this to your desired width
        playView.setFitHeight(250);  // change this to your desired height

        Button playButton = new Button();
        playButton.setGraphic(playView);
        playButton.setStyle("-fx-background-color: transparent;"); // Make button invisible

        // Set button position (centered under the image or fixed as you like)
        playButton.setLayoutX(920);
        playButton.setLayoutY(340);

        // Hover effect
        playButton.setOnMouseEntered(e -> playView.setImage(playHover));
        playButton.setOnMouseExited(e -> playView.setImage(playNormal));

        // Add to root
        menuRoot.getChildren().add(playButton);

        // Click action
        playButton.setOnAction(e -> {
            System.out.println("Play button clicked!");//debug statement
            primaryStage.setScene(accountScene);
            primaryStage.setMaximized(true);
        });
    }
}