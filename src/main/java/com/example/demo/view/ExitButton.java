package com.example.demo.view;

import javafx.scene.Group;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.Optional;

/**
 * The {@code ExitButton} class provides a static method to add
 * a styled exit button to a JavaFX scene. Clicking the button
 * triggers a confirmation dialog before closing the application.
 */
public class ExitButton {

    /**
     * Adds an "X" shaped exit button to the given root group.
     * When clicked, a confirmation dialog appears to prevent accidental exits.
     *
     * @param root        the root {@link Group} to which the button is added
     * @param stage       the primary {@link Stage} of the application
     * @param sceneWidth  the width of the scene (not directly used in layout, but could assist in dynamic positioning)
     */
    public static void addTo(Group root, Stage stage, double sceneWidth) {
        // Create the exit button
        Button exitButton = new Button("X");
        exitButton.setFont(Font.font("Arial", 18));
        exitButton.setTextFill(Color.WHITE);
        exitButton.setStyle(
                "-fx-background-color: #c62828;" +
                        "-fx-background-radius: 12;" +
                        "-fx-min-width: 50;" +
                        "-fx-min-height: 50;" +
                        "-fx-max-width: 50;" +
                        "-fx-max-height: 50;"
        );

        // Set position (adjust as needed)
        exitButton.setLayoutX(132);
        exitButton.setLayoutY(50);

        // Hover effect using DropShadow
        DropShadow shadow = new DropShadow();
        exitButton.setOnMouseEntered(e -> {
            exitButton.setStyle(
                    "-fx-background-color: #b71c1c;" +
                            "-fx-background-radius: 12;" +
                            "-fx-min-width: 50;" +
                            "-fx-min-height: 50;" +
                            "-fx-max-width: 50;" +
                            "-fx-max-height: 50;"
            );
            exitButton.setEffect(shadow);
        });

        exitButton.setOnMouseExited(e -> {
            exitButton.setStyle(
                    "-fx-background-color: #c62828;" +
                            "-fx-background-radius: 12;" +
                            "-fx-min-width: 50;" +
                            "-fx-min-height: 50;" +
                            "-fx-max-width: 50;" +
                            "-fx-max-height: 50;"
            );
            exitButton.setEffect(null);
        });

        // Confirmation dialog before exit
        exitButton.setOnAction(e -> {
            Alert confirm = new Alert(AlertType.CONFIRMATION);
            confirm.setTitle("Exit Confirmation");
            confirm.setHeaderText("Are you sure you want to exit?");
            confirm.setContentText("Click OK to close the application.");

            Optional<ButtonType> result = confirm.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                stage.close();
            }
        });

        // Add the button to the root group
        root.getChildren().add(exitButton);
    }
}