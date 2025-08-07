package com.example.demo.user;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class Account implements Comparable<Account> {
    private long score = 0;
    public static String currentUsername;
    private String userName ;
    private static ArrayList<Account> accounts = new ArrayList<>();

    public Account(String userName){
        this.userName=userName;
    }

    @Override
    public int compareTo(Account o) {
        return Long.compare(o.getScore(), score);
    }

    public void addToScore(long score) {
        this.score += score;
    }

    private long getScore() {
        return score;
    }

    public String getUserName() {
        return userName;
    }

    static Account accountHaveBeenExist(String userName){
        for(Account account : accounts){
            if(account.getUserName().equals(userName)){
                return account;
            }
        }
        return null;

    }

    static Account makeNewAccount(String userName){
        Account account = new Account(userName);
        accounts.add(account);
        try (PrintWriter out = new PrintWriter(new FileWriter("accounts.txt", true))) {
            out.println(userName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return account;
    }
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