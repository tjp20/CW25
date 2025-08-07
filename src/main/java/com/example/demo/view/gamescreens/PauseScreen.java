package com.example.demo.view.gamescreens;

import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

public class PauseScreen {
    private boolean paused = false;
    private final Button button;
    private final Group overlay = new Group();

    public PauseScreen(
            Group root,
            Runnable onPauseToggled,
            Runnable onGoLevels,
            Runnable onRetry
    ) {
        //  Main Pause Button
        button = new Button();
        button.setLayoutX(35);
        button.setLayoutY(100);
        button.setStyle("-fx-background-color: transparent;");

        Image img = new Image(getClass().getResourceAsStream("/com/example/demo/PauseButton.png"));
        ImageView imageView = new ImageView(img);
        imageView.setFitWidth(63);
        imageView.setFitHeight(63);
        button.setGraphic(imageView);

        // Overlay Setup
        Rectangle dimBackground = new Rectangle(900, 750, Color.rgb(0, 0, 0, 0.5));

        double buttonWidth = 200;
        double buttonHeight = 50;
        double centerX = (900 - buttonWidth) / 2;
        double startY = 275;
        double spacing = 70;

        Button resumeBtn = createButton("Resume", centerX, startY, "#ffcc80", "#f2b14c", buttonWidth, buttonHeight, () -> {
            paused = false;
            root.getChildren().remove(overlay);
            onPauseToggled.run();
        });

        Button levelsBtn = createButton("Quit", centerX, startY + spacing, "#a5d6a7", "#81c784", buttonWidth, buttonHeight, onGoLevels);
        Button retryBtn  = createButton("Retry",  centerX, startY + 2 * spacing, "#f48fb1", "#f06292", buttonWidth, buttonHeight, onRetry);

        overlay.getChildren().addAll(dimBackground, resumeBtn, levelsBtn, retryBtn);

        button.setOnAction(e -> {
            paused = true;
            root.getChildren().add(overlay);
            onPauseToggled.run();
        });

        root.getChildren().add(button);
    }

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

    public boolean isPaused() {
        return paused;
    }
}