package com.example.demo.level;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 * Singleton utility class for creating and modifying Text objects for game tiles.
 */
class TextMaker {

    /** The single instance of the TextMaker class. */
    private static TextMaker singleInstance = null;

    /** Private constructor to enforce singleton pattern. */
    private TextMaker() {}

    /**
     * Returns the single instance of the TextMaker.
     *
     * @return the singleton instance of TextMaker
     */
    static TextMaker getSingleInstance() {
        if (singleInstance == null) {
            singleInstance = new TextMaker();
        }
        return singleInstance;
    }

    /**
     * Creates and returns a styled Text node representing a tile number.
     *
     * @param input the string value to be displayed (e.g., "2", "4")
     * @param xCell the x-coordinate of the cell
     * @param yCell the y-coordinate of the cell
     * @param root  the root Group to which the Text node may be added
     * @return the styled Text node
     */
    Text madeText(String input, double xCell, double yCell, Group root) {
        double length = LevelParent.getLENGTH();
        double fontSize = (3 * length) / 7.0;

        Text text = new Text(input);
        text.setFont(Font.font(fontSize));
        text.relocate((xCell + (1.2) * length / 7.0), (yCell + 2 * length / 7.0));
        text.setFill(Color.WHITE);

        return text;
    }

    /**
     * Swaps the values and positions of two Text nodes.
     *
     * @param first  the first Text node
     * @param second the second Text node
     */
    static void changeTwoText(Text first, Text second) {
        String temp = first.getText();
        first.setText(second.getText());
        second.setText(temp);

        double tempNumber = first.getX();
        first.setX(second.getX());
        second.setX(tempNumber);

        tempNumber = first.getY();
        first.setY(second.getY());
        second.setY(tempNumber);
    }
}