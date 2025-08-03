package com.example.demo;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.Comparator;
import java.util.List;

public class LeaderboardScreen {

    public static void show(Stage primaryStage, Scene backScene) {
        Group root = new Group();
        Scene scene = new Scene(root, 900, 750, Color.BLACK); // Arcade black background

        // Title
        Label title = new Label("2048 LEADERBOARD");
        title.setFont(Font.font("Consolas", 38));
        title.setTextFill(Color.LIME);
        title.setLayoutX(180);
        title.setLayoutY(40);
        root.getChildren().add(title);

        // Headers
        int startY = 120;
        int rowHeight = 45;

        String[] headers = { "RANK", "PLAYER", "LEVEL", "SCORE" };
        int[] colX = { 70, 200, 450, 620 };

        for (int i = 0; i < headers.length; i++) {
            Label header = new Label(headers[i]);
            header.setFont(Font.font("Consolas", 24));
            header.setTextFill(Color.CYAN);
            header.setLayoutX(colX[i]);
            header.setLayoutY(startY);
            root.getChildren().add(header);
        }

        // Fetch and sort scores
        List<String[]> entries = UserScore.getAllScoresSimple();
        entries.sort(Comparator.comparingLong((String[] e) -> Long.parseLong(e[2])).reversed());

        int maxDisplay = Math.min(10, entries.size());
        for (int i = 0; i < maxDisplay; i++) {
            String[] entry = entries.get(i);
            String[] rowData = {
                    String.valueOf(i + 1),
                    entry[0],
                    entry[1],
                    entry[2]
            };

            for (int j = 0; j < rowData.length; j++) {
                Label cell = new Label(rowData[j]);
                cell.setFont(Font.font("Consolas", 20));
                cell.setTextFill(Color.HOTPINK);
                cell.setLayoutX(colX[j]);
                cell.setLayoutY(startY + ((i + 1) * rowHeight));
                root.getChildren().add(cell);
            }
        }

        //
        try {
            Image backImg = new Image(MenuScreen.class.getResourceAsStream("/com/example/demo/BackButton.png"));
            ImageView backView = new ImageView(backImg);
            backView.setFitWidth(60);
            backView.setFitHeight(60);

            Button backButton = new Button();
            backButton.setGraphic(backView);
            backButton.setStyle("-fx-background-color: transparent;");
            backButton.setLayoutX(20);
            backButton.setLayoutY(20);
            backButton.setOnAction(e -> primaryStage.setScene(backScene));
            root.getChildren().add(backButton);
        } catch (Exception e) {
            System.out.println("BackButton.png not found.");
        }

        primaryStage.setScene(scene);

        javafx.application.Platform.runLater(() -> {
            primaryStage.setFullScreen(true);
        });

    }
}