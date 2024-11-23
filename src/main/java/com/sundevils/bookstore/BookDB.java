package com.sundevils.bookstore;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BookDB {
    private Database db;

    public BookDB(Database db) {
        this.db = db;
        createTable();
        testInit();
    }

    public boolean createTable() {
        String createTableQuery = "CREATE TABLE IF NOT EXISTS books ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "title TEXT NOT NULL, "
                + "author TEXT NOT NULL, "
                + "year TEXT NOT NULL, "
                + "category INTEGER NOT NULL, "
                + "originalPrice REAL NOT NULL)";
        
        if (db.executeUpdate(createTableQuery)) {
            return true;
        }

        System.err.println("Error creating the books table.");
        return false;
    }

    private void testInit() {
        // Check if first time running
        String checkTableQuery = "SELECT count(*) FROM books";
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
            addBook("Introduction to Physics", "John Doe", "2020", BookCategory.NATURAL_SCIENCE.getValue(), 49.99);
            addBook("Advanced Chemistry", "Jane Smith", "2021", BookCategory.NATURAL_SCIENCE.getValue(), 59.99);
            addBook("Biology: A Journey", "Richard Lee", "2019", BookCategory.NATURAL_SCIENCE.getValue(), 39.99);
            addBook("The Universe Explained", "Susan Clark", "2022", BookCategory.NATURAL_SCIENCE.getValue(), 45.99);

            addBook("Mathematics for Engineers", "Albert White", "2018", BookCategory.MATH.getValue(), 69.99);
            addBook("Calculus Made Easy", "Emily Davis", "2020", BookCategory.MATH.getValue(), 29.99);
            addBook("Linear Algebra", "Michael Brown", "2021", BookCategory.MATH.getValue(), 54.99);
            addBook("Advanced Statistics", "Oliver Wilson", "2023", BookCategory.MATH.getValue(), 74.99);

            addBook("English Grammar 101", "Barbara Green", "2020", BookCategory.ENGLISH.getValue(), 19.99);
            addBook("Shakespeare's Works", "William Black", "2019", BookCategory.ENGLISH.getValue(), 34.99);
            addBook("English Vocabulary Builder", "Emma Moore", "2021", BookCategory.ENGLISH.getValue(), 24.99);
            addBook("The Power of Words", "James Taylor", "2022", BookCategory.ENGLISH.getValue(), 29.99);

            addBook("Introduction to Programming", "David Miller", "2020", BookCategory.COMPUTER.getValue(), 39.99);
            addBook("Java for Beginners", "Mary White", "2021", BookCategory.COMPUTER.getValue(), 49.99);
            addBook("Data Structures and Algorithms", "Robert Harris", "2022", BookCategory.COMPUTER.getValue(), 59.99);
            addBook("Advanced Python", "Sophia Johnson", "2023", BookCategory.COMPUTER.getValue(), 69.99);
        }
    }
    
    public boolean addBook(String title, String author, String year, int category, double originalPrice) {
        String insertQuery = "INSERT INTO books (title, author, year, category, originalPrice) "
                + "VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement pstmt = db.prepareStatement(insertQuery)) {
            pstmt.setString(1, title);
            pstmt.setString(2, author);
            pstmt.setString(3, year);
            pstmt.setInt(4, category);
            pstmt.setDouble(5, originalPrice);
            
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("Error adding book to database: " + e.getMessage()); // TODO: throw exception
            return false;
        }
    }

    public Book getBookFromId(int id) {
        String query = "SELECT * FROM books WHERE id = ?";
        
        try (PreparedStatement pstmt = db.prepareStatement(query)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                return new Book(
                    rs.getInt("id"),
                    rs.getString("title"),
                    rs.getString("author"),
                    rs.getString("year"),
                    rs.getDouble("originalPrice"),
                    BookCategory.fromInt(rs.getInt("category"))
                );
            }
        } catch (SQLException e) {
            System.err.println("Error finding book by id: " + e.getMessage()); // TODO: throw exception?
        }
        
        return null;
    }
}