package com.example.demo.controller;

import com.example.demo.level.Cell;
import javafx.scene.Group;
import javafx.scene.text.Text;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GameControllerTest {

    private Cell[][] cells;
    private int n = 4;
    private long score;
    private GameController controller;

    @BeforeEach
    void setUp() {
        Group root = new Group(); // Dummy root for Cell constructor
        cells = new Cell[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                cells[i][j] = new Cell(j * 100, i * 100, 100, root);
            }
        }

        score = 0;
        controller = new GameController(cells, n, merged -> score += merged);
    }

    @Test
    void testMoveLeft_MergeTwoTiles() {
        // Set two mergeable tiles
        cells[0][0].setTextClass(new Text("2"));
        cells[0][1].setTextClass(new Text("2"));

        controller.moveLeft();

        assertEquals(4, cells[0][0].getNumber());
        assertEquals(0, cells[0][1].getNumber());
        assertEquals(4, score);
        assertTrue(controller.hasMoved());
    }

    @Test
    void testMoveLeft_NoMerge_NoMove() {
        cells[0][0].setTextClass(new Text("2"));
        cells[0][1].setTextClass(new Text("4"));
        cells[0][2].setTextClass(new Text("8"));
        cells[0][3].setTextClass(new Text("16"));

        controller.moveLeft();

        // All tiles should stay where they are
        assertEquals(2, cells[0][0].getNumber());
        assertEquals(4, cells[0][1].getNumber());
        assertEquals(8, cells[0][2].getNumber());
        assertEquals(16, cells[0][3].getNumber());

        assertEquals(0, score);
        assertFalse(controller.hasMoved());
    }

    @Test
    void testMoveUp_Merge() {
        cells[0][0].setTextClass(new Text("4"));
        cells[1][0].setTextClass(new Text("4"));

        controller.moveUp();

        assertEquals(8, cells[0][0].getNumber());
        assertEquals(0, cells[1][0].getNumber());
        assertEquals(8, score);
        assertTrue(controller.hasMoved());
    }

    @Test
    void testSumCellNumbersToScore() {
        cells[0][0].setTextClass(new Text("2"));
        cells[0][1].setTextClass(new Text("4"));
        cells[1][0].setTextClass(new Text("8"));

        controller.sumCellNumbersToScore();

        assertEquals(14, score); // 2 + 4 + 8
    }
}