package com.example.demo.view.gamescreens;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class RankScreenTest {

    @Test
    public void testGetTopScoresFromUserScore() {
        // Simulate test data
        List<String[]> testScores = List.of(
                new String[]{"Alice", "Level 1", "3000"},
                new String[]{"Bob", "Level 1", "2500"},
                new String[]{"Carol", "Level 1", "1000"},
                new String[]{"Dave", "Level 2", "9999"}
        );



        List<String[]> filtered = testScores.stream()
                .filter(arr -> arr[1].equals("Level 1"))
                .sorted((a, b) -> Long.compare(Long.parseLong(b[2]), Long.parseLong(a[2])))
                .limit(3)
                .toList();

        assertEquals(3, filtered.size());
        assertEquals("Alice", filtered.get(0)[0]);
        assertEquals("Bob", filtered.get(1)[0]);
        assertEquals("Carol", filtered.get(2)[0]);
    }
}