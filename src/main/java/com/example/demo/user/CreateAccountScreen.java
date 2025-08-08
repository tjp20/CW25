package com.example.demo.user;

import com.example.demo.view.gamescreens.MenuScreen;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Provides the UI for creating a new user account.
 */
public class CreateAccountScreen {

    /**
     * Sets up and displays the Create Account screen.
     *
     * @param root          the root group to add UI elements to
     * @param scene         the scene where the UI is displayed
     * @param accountScene  the scene to return to after creating or cancelling
     * @param primaryStage  the main application window
     */
    public static void addToCreateAccountScreen(Group root, Scene scene, Scene accountScene, Stage primaryStage) {
        // Set background color
        scene.setFill(Color.web("#ffedb5"));

        // Add background image
        try {
            Image bg = new Image(MenuScreen.class.getResourceAsStream("/com/example/demo/CreateBackground.png"));
            ImageView bgView = new ImageView(bg);
            bgView.setPreserveRatio(true);
            bgView.setFitWidth(1200);
            bgView.setX(150);
            bgView.setY(40);
            root.getChildren().add(bgView);
        } catch (Exception e) {
            System.out.println("CreateBackground.png not found.");
        }

        // Username input field
        TextField newUserField = new TextField();
        newUserField.setPromptText("Enter new username");
        newUserField.setLayoutX(620);
        newUserField.setLayoutY(410);
        newUserField.setPrefWidth(260);
        newUserField.setPrefHeight(30);
        root.getChildren().add(newUserField);

        // Feedback text
        Text feedback = new Text();
        feedback.setFont(Font.font("Arial", 18));
        feedback.setFill(Color.RED);
        feedback.setX(600);
        feedback.setY(480);
        root.getChildren().add(feedback);

        // Create Account button
        Image createNormal = new Image(MenuScreen.class.getResourceAsStream("/com/example/demo/CreateAccountButton1.png"));
        Image createHover = new Image(MenuScreen.class.getResourceAsStream("/com/example/demo/CreateAccountButton2.png"));

        Button confirmCreateBtn = new Button();
        ImageView createView = new ImageView(createNormal);
        createView.setFitWidth(270);
        createView.setFitHeight(190);
        createView.setPreserveRatio(true);
        confirmCreateBtn.setGraphic(createView);
        confirmCreateBtn.setStyle("-fx-background-color: transparent;");
        confirmCreateBtn.setLayoutX(650);
        confirmCreateBtn.setLayoutY(480);
        confirmCreateBtn.setOnMouseEntered(e -> createView.setImage(createHover));
        confirmCreateBtn.setOnMouseExited(e -> createView.setImage(createNormal));
        root.getChildren().add(confirmCreateBtn);

        // Back button
        Image backImg = new Image(MenuScreen.class.getResourceAsStream("/com/example/demo/BackButton.png"));
        ImageView backView = new ImageView(backImg);
        backView.setFitWidth(60);
        backView.setFitHeight(60);

        Button backButton = new Button();
        backButton.setGraphic(backView);
        backButton.setStyle("-fx-background-color: transparent;");
        backButton.setLayoutX(20);
        backButton.setLayoutY(20);
        root.getChildren().add(backButton);

        // Create button logic
        confirmCreateBtn.setOnAction(e -> {
            String username = newUserField.getText().trim();
            Account.loadAccounts();

            if (username.isEmpty()) {
                feedback.setText("Please enter a name.");
                feedback.setFill(Color.RED);
            } else if (!username.equals(username.toLowerCase())) {
                feedback.setText("Username must be in lowercase only.");
                feedback.setFill(Color.RED);
            } else if (Account.accountHaveBeenExist(username) != null) {
                feedback.setText("Account already exists. Please Login");
                feedback.setFill(Color.RED);
            } else {
                Account.makeNewAccount(username);
                feedback.setText("Account created. Redirecting...");
                feedback.setFill(Color.GREEN);

                new Thread(() -> {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                    javafx.application.Platform.runLater(() -> {
                        primaryStage.setScene(accountScene);
                    });
                }).start();
            }
        });

        // Back button logic
        backButton.setOnAction(e -> {
            primaryStage.setScene(accountScene);
        });
    }
}