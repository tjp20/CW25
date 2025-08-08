package com.example.demo.debug;

/**
 * Utility class for debugging score-related events during tile merges in the 2048 game.
 * Logs information when a merge occurs and verifies that the score increment matches the merged value.
 */
public class ScoreDebugger {

    /** Stores the previous score to calculate score increment. */
    private static long previousScore = 0;

    /**
     * Logs and verifies the score increment after a tile merge.
     * If the current score does not match the expected merged value, an error is printed.
     *
     * @param currentScore the current total score after merging
     * @param mergedValue the value of the merged tile (e.g., 4, 8, 16)
     */
    public static void debugMergeScore(long currentScore, int mergedValue) {
        if (mergedValue <= 0) return; // Skip logging non-merges

        long increment = currentScore - previousScore;

        // Defensive check: added score must match merge value
        if (increment != mergedValue) {
            System.err.println("[ScoreDebugger] Merge mismatch: Merged " + mergedValue +
                    ", but score increased by " + increment);
        } else {
            System.out.println("[ScoreDebugger] Merged: " + mergedValue +
                    " | Added to score: " + increment +
                    " | New Score: " + currentScore);
        }

        previousScore = currentScore;
    }

    /**
     * Resets the score tracker to zero. Should be called when starting a new game.
     */
    public static void reset() {
        previousScore = 0;
    }
}