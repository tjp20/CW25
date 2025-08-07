package com.example.demo.user;

import com.example.demo.user.UserScore;
import org.junit.jupiter.api.*;

import java.io.File;
import java.nio.file.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class UserScoreTest {

    private static final String SCORE_DIR = "scores";
    private static final String TEST_USER = "testuser";

    @BeforeEach
    void setup() throws Exception {
        // Clear test user score file before each test
        Path scoreFile = Paths.get(SCORE_DIR, TEST_USER + ".txt");
        Files.createDirectories(scoreFile.getParent());
        Files.deleteIfExists(scoreFile);
    }

    @AfterEach
    void teardown() throws Exception {
        // Clean up score file after test
        Files.deleteIfExists(Paths.get(SCORE_DIR, TEST_USER + ".txt"));
    }

    @Test
    void testUpdateScoreStoresBestScore() {
        UserScore.updateScore(TEST_USER, "Level 1", 150);
        long score = UserScore.getBestScore(TEST_USER, "Level 1");
        assertEquals(150, score);

        // Try to update with a lower score, should remain the same
        UserScore.updateScore(TEST_USER, "Level 1", 100);
        assertEquals(150, UserScore.getBestScore(TEST_USER, "Level 1"));

        // Update with higher score
        UserScore.updateScore(TEST_USER, "Level 1", 200);
        assertEquals(200, UserScore.getBestScore(TEST_USER, "Level 1"));
    }

    @Test
    void testGetAllScoresSimpleReadsCorrectly() {
        UserScore.updateScore(TEST_USER, "Level A", 300);
        UserScore.updateScore(TEST_USER, "Level B", 400);

        List<String[]> allScores = UserScore.getAllScoresSimple();
        assertFalse(allScores.isEmpty());

        boolean foundA = false, foundB = false;
        for (String[] entry : allScores) {
            if (entry[0].equals(TEST_USER)) {
                if (entry[1].equals("Level A") && entry[2].equals("300")) foundA = true;
                if (entry[1].equals("Level B") && entry[2].equals("400")) foundB = true;
            }
        }
        assertTrue(foundA && foundB);
    }
}