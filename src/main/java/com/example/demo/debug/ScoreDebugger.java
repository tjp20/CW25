package com.example.demo.debug;

public class ScoreDebugger {
    private static long previousScore = 0;


    public static void debugMergeScore(long currentScore, int mergedValue) {
        if (mergedValue <= 0) return; // Skip logging non-merges

        long increment = currentScore - previousScore;

        // Defensive check: added score must match merge value
        if (increment != mergedValue) {
            System.err.println("[ScoreDebugger ] Merge mismatch: Merged " + mergedValue +
                    ", but score increased by " + increment);
        } else {
            System.out.println("[ScoreDebugger] Merged: " + mergedValue +
                    " | Added to score: " + increment +
                    " | New Score: " + currentScore);
        }

        previousScore = currentScore;
    }


    public static void reset() {
        previousScore = 0;
    }
}