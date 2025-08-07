package com.example.demo.debug;

import com.example.demo.debug.ScoreDebugger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ScoreDebuggerTest {

    @BeforeEach
    void resetDebugger() {
        ScoreDebugger.reset();
    }

    @Test
    void testZeroMerge() {
        ScoreDebugger.debugMergeScore(0, 0);
        assertEquals(0, getPrivateScore());
    }

    @Test
    void testSingleMergeUpdate() {
        ScoreDebugger.debugMergeScore(8, 8);
        assertEquals(8, getPrivateScore());
    }

    @Test
    void testMultipleMergesSequentially() {
        ScoreDebugger.debugMergeScore(8, 8);
        ScoreDebugger.debugMergeScore(16, 8);
        ScoreDebugger.debugMergeScore(48, 32);
        assertEquals(48, getPrivateScore());
    }

    // Helper to access private score field
    private long getPrivateScore() {
        try {
            var field = ScoreDebugger.class.getDeclaredField("previousScore");
            field.setAccessible(true);
            return field.getLong(null);
        } catch (Exception e) {
            fail("Could not access previousScore field: " + e.getMessage());
            return -1;
        }
    }
}