package com.example.demo.user;

import com.example.demo.view.ExitButton;
import com.example.demo.view.gamescreens.LevelSelectScreen;
import com.example.demo.view.gamescreens.MenuScreen;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class LoginScreen {

    public static void addToAccountScreen(Group accountRoot, Scene accountScene, Scene getAccountScene, Stage primaryStage, Scene levelScene, Group levelRoot, Scene endGameScene, Group endGameRoot) {
        //  Fill background with matching color
        Rectangle bgFill = new Rectangle();
        bgFill.setFill(Color.web("#ffd0e2"));
        bgFill.widthProperty().bind(accountScene.widthProperty());
        bgFill.heightProperty().bind(accountScene.heightProperty());
        accountRoot.getChildren().add(bgFill);


        try {
            Image bg = new Image(MenuScreen.class.getResourceAsStream("/com/example/demo/LoginBackground.jpg"));
            ImageView bgView = new ImageView(bg);
            bgView.setPreserveRatio(true);
            bgView.setFitWidth(1200);  // fixed size
            bgView.setX(150);          // fixed position
            bgView.setY(40);
            accountRoot.getChildren().add(bgView);
        } catch (Exception e) {
            System.out.println("LoginBackground.jpg not found. Using fallback color.");
        }

        //  Username field
        TextField usernameField = new TextField();
        usernameField.setPromptText("Username");
        usernameField.setLayoutX(620);
        usernameField.setLayoutY(380);
        usernameField.setPrefHeight(30);
        usernameField.setPrefWidth(260);
        accountRoot.getChildren().add(usernameField);

        //  Buttons
        Image enterNormal = new Image(MenuScreen.class.getResourceAsStream("/com/example/demo/EnterButton1.png"));
        Image enterHover = new Image(MenuScreen.class.getResourceAsStream("/com/example/demo/EnterButton2.png"));
        Image createNormal = new Image(MenuScreen.class.getResourceAsStream("/com/example/demo/CreateAccountButton1.png"));
        Image createHover = new Image(MenuScreen.class.getResourceAsStream("/com/example/demo/CreateAccountButton2.png"));

        Button submitButton = new Button();
        ImageView enterView = new ImageView(enterNormal);
        enterView.setFitWidth(270);
        enterView.setFitHeight(190);
        enterView.setPreserveRatio(true);
        submitButton.setGraphic(enterView);
        submitButton.setStyle("-fx-background-color: transparent;");
        submitButton.setOnMouseEntered(e -> enterView.setImage(enterHover));
        submitButton.setOnMouseExited(e -> enterView.setImage(enterNormal));

        Button createButton = new Button();
        ImageView createView = new ImageView(createNormal);
        createView.setFitWidth(270);
        createView.setFitHeight(190);
        createView.setPreserveRatio(true);
        createButton.setGraphic(createView);
        createButton.setStyle("-fx-background-color: transparent;");
        createButton.setOnMouseEntered(e -> createView.setImage(createHover));
        createButton.setOnMouseExited(e -> createView.setImage(createNormal));

        HBox buttonBox = new HBox(20);
        buttonBox.setLayoutX(530);
        buttonBox.setLayoutY(480);
        buttonBox.getChildren().addAll(submitButton, createButton);
        accountRoot.getChildren().add(buttonBox);

        // Status text
        Text resultText = new Text();
        resultText.setFont(Font.font("Arial", 18));
        resultText.setFill(Color.GREEN);
        resultText.setX(610);
        resultText.setY(450);
        accountRoot.getChildren().add(resultText);

        //  Enter logic
        submitButton.setOnAction(e -> {
            String enteredName = usernameField.getText().trim();
            if (enteredName.isEmpty()) {
                resultText.setText("Please enter a name.");
                resultText.setFill(Color.RED);
                return;
            }

            Account existing = Account.accountHaveBeenExist(enteredName);
            if (existing != null) {
                Account.currentUsername = existing.getUserName();
                resultText.setText("Welcome back, " + existing.getUserName() + "!");
                resultText.setFill(Color.GREEN);
                LevelSelectScreen.addToLevelScreen(levelRoot, levelScene, primaryStage, existing.getUserName(), endGameScene, endGameRoot);
                primaryStage.setScene(levelScene);
            } else {
                resultText.setText("Account not found. Please Create Account");
                resultText.setFill(Color.RED);
            }
        });

        // Create Account logic
        createButton.setOnAction(e -> {
            usernameField.clear();
            resultText.setText("");
            primaryStage.setScene(getAccountScene);
        });

        // Exit button
        ExitButton.addTo(accountRoot, primaryStage, accountScene.getWidth());
    }
}