package com.sundevils.bookstore;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ListingDB {
    private Database db;

    public ListingDB(Database db) {
        this.db = db;
        createTable();
        testInit();
    }

    public boolean createTable() {
        String createTableQuery = "CREATE TABLE IF NOT EXISTS listings ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "bookId INTEGER NOT NULL, "
                + "condition INTEGER NOT NULL, "
                + "status INTEGER NOT NULL, "
                + "sellerId INTEGER NOT NULL, "
                + "price REAL NOT NULL, "
                + "creationTime REAL NOT NULL)";
        
        if (db.executeUpdate(createTableQuery)) {
            return true;
        }

        System.err.println("Error creating the listings table.");
        return false;
    }

    private void testInit() {
        // Check if first time running
        String checkTableQuery = "SELECT count(*) FROM listings";
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
    
    public boolean addListing(int bookId, int condition, int status, int sellerId, double price) {
        String insertQuery = "INSERT INTO listings (bookId, condition, status, sellerId, price, creationTime) "
                + "VALUES (?, ?, ?, ?, ?, ?)";

        double creationTime = System.currentTimeMillis() / 1000.0;
        
        try (PreparedStatement pstmt = db.prepareStatement(insertQuery)) {
            pstmt.setInt(1, bookId);
            pstmt.setInt(2, condition);
            pstmt.setInt(3, status);
            pstmt.setInt(4, sellerId);
            pstmt.setDouble(5, price);
            pstmt.setDouble(6, creationTime);
            
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("Error adding listing to database: " + e.getMessage()); // TODO: throw exception
            return false;
        }
    }

    public ArrayList<Listing> getListings() {
        ArrayList<Listing> listings = new ArrayList<>();

        String query = "SELECT * FROM listings";
        ResultSet rs = db.executeQuery(query);

        try {
            while (rs.next()) {
                App app = App.getInstance();
                Book book = app.bookDB.getBookFromId(rs.getInt("bookId"));
                Seller seller = app.userDB.getUserFromId(rs.getInt("sellerId")).convertToSeller();

                listings.add(new Listing(
                    rs.getInt("int"),
                    book,
                    BookCondition.fromInt(rs.getInt("condition")),
                    ListingStatus.fromInt(rs.getInt("status")),
                    seller,
                    rs.getDouble("price"),
                    rs.getDouble("creationTime")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        
        return null;
    }
}