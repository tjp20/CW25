package com.example.demo.view;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 * The {@code UsernameDisplay} class provides a static method
 * to display the username inside a styled rectangular profile box
 * on a given JavaFX scene.
 */
public class UsernameDisplay {

    /**
     * Adds a gray profile box with the given username text to the provided root group.
     *
     * @param root     the {@link Group} to which the elements will be added
     * @param username the username string to display
     * @param x        the X-coordinate for positioning the profile box and text
     * @param y        the Y-coordinate for positioning the profile box and text
     */
    public static void addTo(Group root, String username, double x, double y) {
        // Create a rounded rectangle as the profile box background
        Rectangle profileBox = new Rectangle(200, 50);
        profileBox.setArcWidth(15);
        profileBox.setArcHeight(15);
        profileBox.setFill(Color.GRAY);
        profileBox.setStroke(Color.WHITE);
        profileBox.setStrokeWidth(2);
        profileBox.setLayoutX(x);
        profileBox.setLayoutY(y);

        // Create the username text
        Text profileText = new Text(username);
        profileText.setFont(Font.font("Arial", 18));
        profileText.setFill(Color.WHITE);
        profileText.setLayoutX(x + 20);
        profileText.setLayoutY(y + 30);

        // Add both elements to the scene graph
        root.getChildren().addAll(profileBox, profileText);
    }
}