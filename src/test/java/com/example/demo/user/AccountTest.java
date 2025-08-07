package com.example.demo.user;

import com.example.demo.user.Account;
import org.junit.jupiter.api.*;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AccountTest {

    private static final String TEST_FILE = "accounts.txt";

    @BeforeEach
    void clearAccountsFile() throws Exception {

        Files.deleteIfExists(Paths.get(TEST_FILE));
        Files.createFile(Paths.get(TEST_FILE));
    }

    @Test
    void testMakeNewAccountAddsToListAndFile() throws Exception {
        Account acc = Account.makeNewAccount("testuser1");
        assertNotNull(acc);
        assertEquals("testuser1", acc.getUserName());

        // Check file
        List<String> lines = Files.readAllLines(Paths.get(TEST_FILE));
        assertTrue(lines.contains("testuser1"));
    }

    @Test
    void testAccountHaveBeenExist() {
        Account.makeNewAccount("user2");
        Account found = Account.accountHaveBeenExist("user2");
        assertNotNull(found);
        assertEquals("user2", found.getUserName());
    }

    @Test
    void testAddToScoreAndCompare() {
        Account a1 = new Account("alpha");
        Account a2 = new Account("beta");

        a1.addToScore(100);
        a2.addToScore(50);

        assertTrue(a1.compareTo(a2) < 0); // a1 has higher score so comes before a2
    }

    @Test
    void testLoadAccountsFromFile() throws Exception {
        // Simulate file write
        Files.write(Paths.get(TEST_FILE), List.of("userA", "userB", "userC"));

        Account.loadAccounts();
        Account found = Account.accountHaveBeenExist("userB");
        assertNotNull(found);
        assertEquals("userB", found.getUserName());
    }
}