package com.example.demo.user;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;

public class UserScore {
    private static final String SCORE_DIR = "scores";

    private static Map<String, Long> levelBestScores = new HashMap<>();

    public static void updateScore(String username, String level, long newScore) {
        loadScores(username);
        long current = levelBestScores.getOrDefault(level, 0L);
        if (newScore > current) {
            levelBestScores.put(level, newScore);
            saveScores(username);
        }
    }

    public static long getBestScore(String username, String level) {
        loadScores(username);
        return levelBestScores.getOrDefault(level, 0L);
    }

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
}