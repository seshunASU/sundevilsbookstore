package com.sundevils.bookstore;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SalesHistoryDB {
    private Database db;

    public SalesHistoryDB(Database db) {
        this.db = db;
        createTable();
        testInit();
    }

    public boolean createTable() {
        String createTableQuery = "CREATE TABLE IF NOT EXISTS salesHistory ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "listingId INTEGER NOT NULL, "
                + "sellerId INTEGER NOT NULL, "
                + "time REAL NOT NULL)";
        
        if (db.executeUpdate(createTableQuery)) {
            return true;
        }

        System.err.println("Error creating the salesHistory table.");
        return false;
    }

    private void testInit() {
        // Check if first time running
        String checkTableQuery = "SELECT count(*) FROM salesHistory";
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
        }
    }
    
    public boolean addLog(int listingId, int sellerId) {
        String insertQuery = "INSERT INTO salesHistory (listingId, sellerId, time) "
                + "VALUES (?, ?, ?)";

        double time = System.currentTimeMillis() / 1000.0;
        
        try (PreparedStatement pstmt = db.prepareStatement(insertQuery)) {
            pstmt.setInt(1, listingId);
            pstmt.setInt(2, sellerId);
            pstmt.setDouble(3, time);
            
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("Error adding listing to database: " + e.getMessage()); // TODO: throw exception
            return false;
        }
    }

    public ArrayList<SalesHistoryLog> getHistory(int sellerId) {
        ArrayList<SalesHistoryLog> logs = new ArrayList<>();

        String query = "SELECT * FROM salesHistory WHERE sellerId = ?";

        try (PreparedStatement pstmt = db.prepareStatement(query)) {
            pstmt.setInt(1, sellerId);
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                App app = App.getInstance();
                Listing listing = app.listingDB.getListingFromId(rs.getInt("listingId"));

                logs.add(new SalesHistoryLog(
                    rs.getInt("id"),
                    listing,
                    rs.getDouble("time")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace(); // TODO: throw exception?
        }

        return logs;
    }
}