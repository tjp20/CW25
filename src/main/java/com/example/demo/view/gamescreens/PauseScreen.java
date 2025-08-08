package com.example.demo.view.gamescreens;

import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

/**
 * The {@code PauseScreen} class creates a pause menu overlay in the game,
 * including buttons to resume, retry, or quit to level selection.
 */
public class PauseScreen {

    /** Indicates whether the game is currently paused. */
    private boolean paused = false;

    /** Main pause button shown on the screen. */
    private final Button button;

    /** Overlay group containing the dim background and pause menu buttons. */
    private final Group overlay = new Group();

    /**
     * Constructs a {@code PauseScreen} and adds it to the provided root group.
     * When the pause button is clicked, an overlay with Resume, Retry, and Quit options appears.
     *
     * @param root            The root group to which UI components are added.
     * @param onPauseToggled Callback triggered when the pause state changes.
     * @param onGoLevels     Callback triggered when the Quit button is pressed.
     * @param onRetry        Callback triggered when the Retry button is pressed.
     */
    public PauseScreen(
            Group root,
            Runnable onPauseToggled,
            Runnable onGoLevels,
            Runnable onRetry
    ) {
        button = new Button();
        button.setLayoutX(35);
        button.setLayoutY(100);
        button.setStyle("-fx-background-color: transparent;");

        Image img = new Image(getClass().getResourceAsStream("/com/example/demo/PauseButton.png"));
        ImageView imageView = new ImageView(img);
        imageView.setFitWidth(63);
        imageView.setFitHeight(63);
        button.setGraphic(imageView);

        double sceneWidth = root.getScene().getWidth();
        double sceneHeight = root.getScene().getHeight();

        // Dim background for overlay
        Rectangle dimBackground = new Rectangle(sceneWidth, sceneHeight, Color.rgb(0, 0, 0, 0.5));

        // Button dimensions and positioning
        double buttonWidth = 200;
        double buttonHeight = 50;
        double centerX = (sceneWidth - buttonWidth) / 2 + 250;
        double startY = sceneHeight / 2 - 100;
        double spacing = 70;

        // Create pause menu buttons
        Button resumeBtn = createButton("Resume", centerX, startY, "#ffcc80", "#f2b14c", buttonWidth, buttonHeight, () -> {
            paused = false;
            root.getChildren().remove(overlay);
            onPauseToggled.run();
        });

        Button levelsBtn = createButton("Quit", centerX, startY + spacing, "#a5d6a7", "#81c784", buttonWidth, buttonHeight, onGoLevels);
        Button retryBtn  = createButton("Retry", centerX, startY + 2 * spacing, "#f48fb1", "#f06292", buttonWidth, buttonHeight, onRetry);

        overlay.getChildren().addAll(dimBackground, resumeBtn, levelsBtn, retryBtn);

        // Show overlay on pause button click
        button.setOnAction(e -> {
            paused = true;
            root.getChildren().add(overlay);
            onPauseToggled.run();
        });

        root.getChildren().add(button);

        // Make overlay responsive to scene resizing
        root.getScene().widthProperty().addListener((obs, oldVal, newVal) -> dimBackground.setWidth(newVal.doubleValue()));
        root.getScene().heightProperty().addListener((obs, oldVal, newVal) -> dimBackground.setHeight(newVal.doubleValue()));
    }

    /**
     * Helper method to create a styled button with hover effects and an action.
     *
     * @param label      The button's text label.
     * @param x          The x-coordinate for layout.
     * @param y          The y-coordinate for layout.
     * @param baseColor  The base background color.
     * @param hoverColor The background color on hover.
     * @param width      The width of the button.
     * @param height     The height of the button.
     * @param action     The action to execute when the button is clicked.
     * @return A styled JavaFX {@link Button}.
     */
    private Button createButton(String label, double x, double y, String baseColor, String hoverColor, double width, double height, Runnable action) {
        Button btn = new Button(label);
        btn.setFont(Font.font("Consolas", 20));
        btn.setPrefSize(width, height);
        btn.setLayoutX(x);
        btn.setLayoutY(y);
        btn.setStyle("-fx-background-color: " + baseColor + "; -fx-background-radius: 15; -fx-font-size: 18px; -fx-text-fill: black;");
        btn.setOnMouseEntered(e -> btn.setStyle("-fx-background-color: " + hoverColor + "; -fx-background-radius: 15; -fx-font-size: 18px; -fx-text-fill: black;"));
        btn.setOnMouseExited(e -> btn.setStyle("-fx-background-color: " + baseColor + "; -fx-background-radius: 15; -fx-font-size: 18px; -fx-text-fill: black;"));
        btn.setOnAction(e -> action.run());
        return btn;
    }

    /**
     * Returns whether the game is currently paused.
     *
     * @return {@code true} if paused, {@code false} otherwise.
     */
    public boolean isPaused() {
        return paused;
    }
}