package com.example.demo;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class CreateAccountScreen {

    public static void addToCreateAccountScreen(Group root, Scene scene, Scene accountScene, Stage primaryStage) {
        // Title
        Text createTitle = new Text("Create a New Account");
        createTitle.setFont(Font.font("Arial", 32));
        createTitle.setFill(Color.BLACK);
        createTitle.setX(300);
        createTitle.setY(100);
        root.getChildren().add(createTitle);

        // Input field
        TextField newUserField = new TextField();
        newUserField.setPromptText("Enter new username");
        newUserField.setLayoutX(300);
        newUserField.setLayoutY(150);
        newUserField.setPrefWidth(300);
        root.getChildren().add(newUserField);

        // Create button
        Button confirmCreateBtn = new Button("Create");
        confirmCreateBtn.setLayoutX(400);
        confirmCreateBtn.setLayoutY(220);
        root.getChildren().add(confirmCreateBtn);

        // Login button
        Button backToLoginBtn = new Button("Login");
        backToLoginBtn.setLayoutX(405);
        backToLoginBtn.setLayoutY(270);
        root.getChildren().add(backToLoginBtn);

        // Feedback
        Text feedback = new Text();
        feedback.setFont(Font.font("Arial", 18));
        feedback.setX(300);
        feedback.setY(330);
        root.getChildren().add(feedback);

        // Create action
        confirmCreateBtn.setOnAction(e -> {
            String username = newUserField.getText().trim();
            if (username.isEmpty()) {
                feedback.setText("Username cannot be empty.");
                feedback.setFill(Color.RED);
            } else if (Account.accountHaveBeenExist(username) != null) {
                feedback.setText("Account already exists.");
                feedback.setFill(Color.ORANGE);
            } else {
                Account.makeNewAccount(username);
                feedback.setText("Account created: " + username);
                feedback.setFill(Color.GREEN);
            }
        });

        // Login action
        backToLoginBtn.setOnAction(e -> {
            primaryStage.setScene(accountScene);
        });
    }
}