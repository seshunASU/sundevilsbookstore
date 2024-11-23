package com.sundevils.bookstore;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class UserDB {
    private Database db;

    public UserDB(Database db) {
        this.db = db;
        createTable();
        testInit();
    }

    public boolean createTable() {
        String createTableQuery = "CREATE TABLE IF NOT EXISTS users ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "displayName TEXT NOT NULL, "
                + "username TEXT NOT NULL, "
                + "password TEXT NOT NULL, "
                + "type INTEGER NOT NULL, "
                + "creationTime REAL NOT NULL)";
        
        if (db.executeUpdate(createTableQuery)) {
            return true;
        }

        System.err.println("Error creating the users table.");
        return false;
    }

    private void testInit() {
        // Check if first time running
        String checkTableQuery = "SELECT count(*) FROM users";
        ResultSet rs = db.executeQuery(checkTableQuery);
        boolean firstTime = false;

        try {
            if (rs.next()) {
                firstTime = rs.getInt(1) == 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Fill table with dummy values
        if (firstTime) {
            addUser("Pending User", "pending", "pass", AccountType.PENDING.getValue());
            addUser("Buyer User", "buyer", "pass", AccountType.BUYER.getValue());
            addUser("Seller User", "seller", "pass", AccountType.SELLER.getValue());
            addUser("Admin User", "admin", "pass", AccountType.ADMIN.getValue());
        }
    }
    
    public boolean addUser(String displayName, String username, String password, int type) {
        String insertQuery = "INSERT INTO users (displayName, username, password, type, creationTime) "
                + "VALUES (?, ?, ?, ?, ?)";

        double creationTime = System.currentTimeMillis() / 1000.0;
        
        try (PreparedStatement pstmt = db.prepareStatement(insertQuery)) {
            pstmt.setString(1, displayName);
            pstmt.setString(2, username);
            pstmt.setString(3, password);
            pstmt.setInt(4, type);
            pstmt.setDouble(5, creationTime);
            
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("Error adding user: " + e.getMessage()); // TODO: throw exception
            return false;
        }
    }

    public ArrayList<HashMap<String, Object>> findUsersByName(String username, boolean showPending) {
        ArrayList<HashMap<String, Object>> users = new ArrayList<>();
        String query = "SELECT * FROM users WHERE username = ?";
        if (!showPending) {
            query += " AND type != 0";
        }
        
        try (PreparedStatement pstmt = db.prepareStatement(query)) {
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                HashMap<String, Object> userData = new HashMap<>();
                userData.put("id", rs.getInt("id"));
                userData.put("displayName", rs.getString("displayName"));
                userData.put("username", rs.getString("username"));
                userData.put("password", rs.getString("password"));
                userData.put("type", rs.getInt("type"));
                userData.put("creationTime", rs.getDouble("creationTime"));
                
                users.add(userData);
            }
        } catch (SQLException e) {
            System.err.println("Error finding users by name: " + e.getMessage()); // TODO: throw exception?
        }
        
        return users;
    }
}