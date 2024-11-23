package com.sundevils.bookstore;

public class Book {
    private int id;
    private String title;
    private String author;
    private String year;
    private double originalPrice;
    private BookCategory category;

    public Book(int id, String title, String author, String year, double originalPrice, BookCategory category) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.year = year;
        this.originalPrice = originalPrice;
        this.category = category;
    }

    public int getId() {
        return id;
    }
    
    public String getTitle() {
        return title;
    }
    
    public String getAuthor() {
        return author;
    }
    
    public String getYear() {
        return year;
    }
    
    public double getOriginalPrice() {
        return originalPrice;
    }
    
    public BookCategory getCategory() {
        return category;
    }
}
