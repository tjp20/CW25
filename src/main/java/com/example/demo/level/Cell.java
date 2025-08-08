package com.example.demo.level;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

/**
 * Represents a single cell in the 2048 game grid.
 * Each cell is a rectangle with a number (Text) displayed on top.
 * Supports merging, movement, and dynamic color updates.
 */
public class Cell {

    /** Rectangle representing the visual tile. */
    private Rectangle rectangle;

    /** Text object showing the number on the tile. */
    private Text textClass;

    /** The JavaFX group the cell is part of. */
    private Group root;

    /** Flag indicating whether the cell has been modified in the current move. */
    private boolean modify = false;

    /**
     * Constructs a new Cell at a specific position with a specific size.
     *
     * @param x     the x-coordinate
     * @param y     the y-coordinate
     * @param scale the width and height of the cell
     * @param root  the JavaFX group to which this cell belongs
     */
    public Cell(double x, double y, double scale, Group root) {
        this.rectangle = new Rectangle();
        this.rectangle.setX(x);
        this.rectangle.setY(y);
        this.rectangle.setHeight(scale);
        this.rectangle.setWidth(scale);
        this.rectangle.setFill(Color.rgb(224, 226, 226, 0.5));

        this.root = root;
        this.textClass = TextMaker.getSingleInstance().madeText("0", x, y, root);
        root.getChildren().add(rectangle);
    }

    /**
     * Sets whether the cell has been modified.
     *
     * @param modify true if modified, false otherwise
     */
    public void setModify(boolean modify) {
        this.modify = modify;
    }

    /**
     * Returns whether the cell has been modified.
     *
     * @return true if modified, false otherwise
     */
    public boolean getModify() {
        return modify;
    }

    /**
     * Returns the numeric value displayed in the cell.
     *
     * @return the number shown
     */
    public int getNumber() {
        return Integer.parseInt(textClass.getText());
    }

    /**
     * Updates the text object of the cell (used during merging).
     *
     * @param textClass the new Text object
     */
    public void setTextClass(Text textClass) {
        this.textClass = textClass;
    }

    /**
     * Changes this cell's content to match another cell (text and color).
     * Used when shifting tiles during movement.
     *
     * @param cell the cell to copy from
     */
    public void changeCell(Cell cell) {
        TextMaker.changeTwoText(this.textClass, cell.getTextClass());

        root.getChildren().remove(cell.getTextClass());
        root.getChildren().remove(this.textClass);

        if (!cell.getTextClass().getText().equals("0")) {
            root.getChildren().add(cell.getTextClass());
        }
        if (!this.textClass.getText().equals("0")) {
            root.getChildren().add(this.textClass);
        }

        setColorByNumber(getNumber());
        cell.setColorByNumber(cell.getNumber());
    }

    /**
     * Merges this cell into another cell by adding their values.
     * The current cell is reset to 0.
     *
     * @param cell the cell to merge into
     */
    public void adder(Cell cell) {
        int sum = cell.getNumber() + this.getNumber();
        cell.getTextClass().setText(String.valueOf(sum));
        this.textClass.setText("0");

        root.getChildren().remove(this.textClass);
        cell.setColorByNumber(cell.getNumber());
        setColorByNumber(getNumber());
    }

    /**
     * Sets the background color of the cell based on its number.
     *
     * @param number the numeric value
     */
    public void setColorByNumber(int number) {
        switch (number) {
            case 0 -> rectangle.setFill(Color.rgb(224, 226, 226, 0.5));
            case 2 -> rectangle.setFill(Color.rgb(232, 255, 100, 0.5));
            case 4 -> rectangle.setFill(Color.rgb(232, 220, 50, 0.5));
            case 8 -> rectangle.setFill(Color.rgb(232, 200, 44, 0.8));
            case 16 -> rectangle.setFill(Color.rgb(232, 170, 44, 0.8));
            case 32 -> rectangle.setFill(Color.rgb(180, 120, 44, 0.7));
            case 64 -> rectangle.setFill(Color.rgb(180, 100, 44, 0.7));
            case 128 -> rectangle.setFill(Color.rgb(180, 80, 44, 0.7));
            case 256 -> rectangle.setFill(Color.rgb(180, 60, 44, 0.8));
            case 512 -> rectangle.setFill(Color.rgb(180, 30, 44, 0.8));
            case 1024 -> rectangle.setFill(Color.rgb(250, 0, 44, 0.8));
            case 2048 -> rectangle.setFill(Color.rgb(250, 0, 0, 1));
        }
    }

    /**
     * Gets the X position of the cell's rectangle.
     *
     * @return the X coordinate
     */
    public double getX() {
        return rectangle.getX();
    }

    /**
     * Gets the Y position of the cell's rectangle.
     *
     * @return the Y coordinate
     */
    public double getY() {
        return rectangle.getY();
    }

    /**
     * Returns the internal Text object of this cell.
     * Used by other classes to update the number.
     *
     * @return the Text object
     */
    public Text getTextClass() {
        return textClass;
    }
}