package com.example.demo.user;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

/**
 * Represents a user account with a username and a score.
 * Provides functionality for account creation, score management, and file persistence.
 */
public class Account implements Comparable<Account> {

    /** The score associated with this account. */
    private long score = 0;

    /** The currently logged-in username (shared globally). */
    public static String currentUsername;

    /** The username of this account. */
    private String userName;

    /** List of all accounts created in the session. */
    private static ArrayList<Account> accounts = new ArrayList<>();

    /**
     * Constructs a new {@code Account} with the specified username.
     *
     * @param userName the username for this account
     */
    public Account(String userName) {
        this.userName = userName;
    }

    /**
     * Compares this account to another account based on score.
     * Higher scores come first in the sort order.
     *
     * @param o the account to compare with
     * @return a negative number if this account has a lower score,
     *         a positive number if higher, or zero if equal
     */
    @Override
    public int compareTo(Account o) {
        return Long.compare(o.getScore(), score);
    }

    /**
     * Adds the specified value to this account's total score.
     *
     * @param score the amount to add to the current score
     */
    public void addToScore(long score) {
        this.score += score;
    }

    /**
     * Returns the total score of this account.
     *
     * @return the score
     */
    private long getScore() {
        return score;
    }

    /**
     * Returns the username of this account.
     *
     * @return the username
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Checks if an account with the given username already exists in the session.
     *
     * @param userName the username to search for
     * @return the matching {@code Account}, or {@code null} if not found
     */
    public static Account accountHaveBeenExist(String userName) {
        for (Account account : accounts) {
            if (account.getUserName().equals(userName)) {
                return account;
            }
        }
        return null;
    }

    /**
     * Creates a new account with the given username, stores it in memory,
     * and appends it to the {@code accounts.txt} file.
     *
     * @param userName the username to register
     * @return the newly created {@code Account}
     */
    public static Account makeNewAccount(String userName) {
        Account account = new Account(userName);
        accounts.add(account);
        try (PrintWriter out = new PrintWriter(new FileWriter("accounts.txt", true))) {
            out.println(userName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return account;
    }

    /**
     * Loads existing accounts from the {@code accounts.txt} file into memory.
     * Each line in the file represents one account username.
     */
    public static void loadAccounts() {
        File file = new File("accounts.txt");
        if (!file.exists()) return;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    accounts.add(new Account(line.trim()));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}