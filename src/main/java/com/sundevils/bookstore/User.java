package com.sundevils.bookstore;

import java.util.ArrayList;
import java.util.HashMap;

public class User {
    public int id;
    public String displayName;
    public String username;
    private String password;
    public AccountType type;
    public double creationTime;

    public User(int id, String displayName, String username, String password, AccountType type, double creationTime) {
        this.id = id;
        this.displayName = displayName;
        this.username = username;
        this.password = password;
        this.type = type;
        this.creationTime = creationTime;
    }

    // Object methods
    public boolean validatePassword(String passwordInput) {
        return passwordInput.equals(password);
    }
    
    // Class methods
    public static ArrayList<User> findUsersByName(String username, boolean showPending) {
        ArrayList<User> users = new ArrayList<>();
        ArrayList<HashMap<String, Object>> userEntries = App.getUserDB().findUsersByName(username, showPending);

        for (HashMap<String, Object> userEntry : userEntries) {
            users.add(new User(
                (int) userEntry.get("id"),
                (String) userEntry.get("displayName"),
                (String) userEntry.get("username"),
                (String) userEntry.get("password"),
                AccountType.fromInt((int) userEntry.get("type")),
                (double) userEntry.get("creationTime")
            ));
        }

        return users;
    }
}
