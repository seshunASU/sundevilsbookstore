package com.sundevils.bookstore;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class UserDB {
    private Database db;

    public UserDB(Database db) { // TODO: throw exception on failure
        this.db = db;
        createTable();
    }

    public boolean createTable() {
        String createTableQuery = "CREATE TABLE IF NOT EXISTS users ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "displayName TEXT NOT NULL, "
                + "username TEXT NOT NULL, "
                + "password TEXT NOT NULL, "
                + "creation REAL NOT NULL)";
        
        if (db.executeUpdate(createTableQuery)) {
            return true;
        }

        System.err.println("Error creating the users table.");
        return false;
    }
    
    public boolean addUser(String displayName, String username, String password, double creationTime) {
        String insertQuery = "INSERT INTO users (displayName, username, password, creation) "
                + "VALUES (?, ?, ?, ?)";
        
        try (PreparedStatement pstmt = db.prepareStatement(insertQuery)) {
            pstmt.setString(1, displayName);
            pstmt.setString(2, username);
            pstmt.setString(3, password);
            pstmt.setDouble(4, creationTime);
            
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("Error adding user: " + e.getMessage()); // TODO: throw exception
            return false;
        }
    }

    public ArrayList<HashMap<String, Object>> findUsersByName(String username) {
        ArrayList<HashMap<String, Object>> users = new ArrayList<>();
        String query = "SELECT * FROM users WHERE username = ?";
        
        try (PreparedStatement pstmt = db.prepareStatement(query)) {
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                HashMap<String, Object> userData = new HashMap<>();
                userData.put("id", rs.getInt("id"));
                userData.put("displayName", rs.getString("displayName"));
                userData.put("username", rs.getString("username"));
                userData.put("password", rs.getString("password"));
                userData.put("creation", rs.getDouble("creation"));
                
                users.add(userData);
            }
        } catch (SQLException e) {
            System.err.println("Error finding users by name: " + e.getMessage()); // TODO: throw exception?
        }
        
        return users;
    }
}