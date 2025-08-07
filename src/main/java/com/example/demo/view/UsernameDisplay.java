package com.example.demo.view;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class UsernameDisplay {

    public static void addTo(Group root, String username, double x, double y) {
        Rectangle profileBox = new Rectangle(200, 50);
        profileBox.setArcWidth(15);
        profileBox.setArcHeight(15);
        profileBox.setFill(Color.GRAY);
        profileBox.setStroke(Color.WHITE);
        profileBox.setStrokeWidth(2);
        profileBox.setLayoutX(x);
        profileBox.setLayoutY(y);

        Text profileText = new Text(username);
        profileText.setFont(Font.font("Arial", 18));
        profileText.setFill(Color.WHITE);
        profileText.setLayoutX(x +20);
        profileText.setLayoutY(y + 30);

        root.getChildren().addAll(profileBox, profileText);
    }
}