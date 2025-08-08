package com.example.demo.user;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;

/**
 * Handles user score management for different game levels.
 * Scores are stored per user in text files and managed in-memory during execution.
 */
public class UserScore {

    /** Directory where score files are stored. */
    private static final String SCORE_DIR = "scores";

    /** Stores the best scores for each level during a session. */
    private static final Map<String, Long> levelBestScores = new HashMap<>();

    /**
     * Updates the score for a user if the new score is higher than the current best.
     *
     * @param username the username of the player
     * @param level the level name
     * @param newScore the score to update
     */
    public static void updateScore(String username, String level, long newScore) {
        loadScores(username);
        long current = levelBestScores.getOrDefault(level, 0L);
        if (newScore > current) {
            levelBestScores.put(level, newScore);
            saveScores(username);
        }
    }

    /**
     * Retrieves the best score for a given user and level.
     *
     * @param username the username of the player
     * @param level the level name
     * @return the best score for that level
     */
    public static long getBestScore(String username, String level) {
        loadScores(username);
        return levelBestScores.getOrDefault(level, 0L);
    }

    /**
     * Returns all stored scores in simple string format for display.
     *
     * @return a list of score entries, each as a String array: [username, level, score]
     */
    public static List<String[]> getAllScoresSimple() {
        List<String[]> entries = new ArrayList<>();
        File dir = new File(SCORE_DIR);
        if (!dir.exists()) return entries;

        for (File file : dir.listFiles()) {
            String username = file.getName().replace(".txt", "");
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(":");
                    if (parts.length == 2) {
                        String level = parts[0];
                        String score = parts[1];
                        entries.add(new String[]{username, level, score});
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return entries;
    }

    /**
     * Loads the user's saved scores from file into memory.
     *
     * @param username the username whose scores to load
     */
    private static void loadScores(String username) {
        levelBestScores.clear();
        File file = new File(SCORE_DIR, username + ".txt");
        if (!file.exists()) return;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":");
                if (parts.length == 2) {
                    levelBestScores.put(parts[0], Long.parseLong(parts[1]));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Saves the current user's best scores to file.
     *
     * @param username the username whose scores to save
     */
    private static void saveScores(String username) {
        try {
            File dir = new File(SCORE_DIR);
            if (!dir.exists()) dir.mkdir();

            File file = new File(dir, username + ".txt");
            try (PrintWriter writer = new PrintWriter(file)) {
                for (Map.Entry<String, Long> entry : levelBestScores.entrySet()) {
                    writer.println(entry.getKey() + ":" + entry.getValue());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}