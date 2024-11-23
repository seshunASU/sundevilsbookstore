package com.sundevils.bookstore;

public class Listing {
    private Book book;
    private BookCondition condition;
    private ListingStatus status;
    private Seller seller;
    private double price;

    public Listing(Book book, BookCondition condition, ListingStatus status, Seller seller, double price) {
        this.book = book;
        this.condition = condition;
        this.status = status;
        this.seller = seller;
        this.price = price;
    }
    
    public Book getBook() {
        return book;
    }

    public BookCondition getCondition() {
        return condition;
    }

    public ListingStatus getStatus() {
        return status;
    }

    public Seller getSeller() {
        return seller;
    }

    public double getPrice() {
        return price;
    }
}
