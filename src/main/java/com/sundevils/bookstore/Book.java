package com.sundevils.bookstore;

public class Book {
    private int id;
    private String title;
    private String author;
    private int year;
    private double originalPrice;
    private BookCategory bookCategory;

    public Book(int id, String title, String author, int year, double originalPrice, BookCategory bookCategory) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.year = year;
        this.originalPrice = originalPrice;
        this.bookCategory = bookCategory;
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
    
    public int getYear() {
        return year;
    }
    
    public double getOriginalPrice() {
        return originalPrice;
    }
    
    public BookCategory getBookCategory() {
        return bookCategory;
    }
}
