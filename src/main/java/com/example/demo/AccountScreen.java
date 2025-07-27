package com.example.demo;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class AccountScreen {

    public static void addToAccountScreen(Group accountRoot, Scene accountScene, Scene getAccountScene, Stage primaryStage) {
        // Title
        Text title = new Text("Enter Username");
        title.setFont(Font.font("Arial", 32));
        title.setFill(Color.BLACK);
        title.setX(350);
        title.setY(100);
        accountRoot.getChildren().add(title);

        // Username input field
        TextField usernameField = new TextField();
        usernameField.setPromptText("Username");
        usernameField.setLayoutX(320);
        usernameField.setLayoutY(150);
        usernameField.setPrefWidth(260);
        accountRoot.getChildren().add(usernameField);

        // Enter button
        Button submitButton = new Button("Enter");
        submitButton.setLayoutX(400);
        submitButton.setLayoutY(420);
        accountRoot.getChildren().add(submitButton);

        // Create Account button
        Button createButton = new Button("Create Account");
        createButton.setLayoutX(380);
        createButton.setLayoutY(470);
        accountRoot.getChildren().add(createButton);

        // Result text
        Text resultText = new Text();
        resultText.setFont(Font.font("Arial", 18));
        resultText.setFill(Color.GREEN);
        resultText.setX(320);
        resultText.setY(330);
        accountRoot.getChildren().add(resultText);

        // Enter button action (for existing accounts)
        submitButton.setOnAction(e -> {
            String enteredName = usernameField.getText().trim();

            if (enteredName.isEmpty()) {
                resultText.setText("Please enter a name.");
                resultText.setFill(Color.RED);
                return;
            }

            Account existing = Account.accountHaveBeenExist(enteredName);
            if (existing != null) {
                resultText.setText("Welcome back, " + existing.getUserName() + "!");
                resultText.setFill(Color.GREEN);
            } else {
                resultText.setText("Account not found.");
                resultText.setFill(Color.ORANGE);
            }
        });

        // Create Account button action
        createButton.setOnAction(e -> {
            String enteredName = usernameField.getText().trim();

            if (enteredName.isEmpty()) {
                resultText.setText("Please enter a name.");
                resultText.setFill(Color.RED);
                return;
            }

            Account existing = Account.accountHaveBeenExist(enteredName);
            if (existing != null) {
                resultText.setText("Account already exists!");
                resultText.setFill(Color.ORANGE);
            } else {
                Account newAccount = Account.makeNewAccount(enteredName);
                resultText.setText("New account created: " + newAccount.getUserName());
                resultText.setFill(Color.GREEN);
                primaryStage.setScene(getAccountScene);
            }
        });
    }
}