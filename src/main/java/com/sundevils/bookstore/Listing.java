package com.sundevils.bookstore;

public class Listing {
    private int id;
    private Book book;
    private BookCondition condition;
    private ListingStatus status;
    private Seller seller;
    private double price;
    private double creationTime;

    public Listing(int id, Book book, BookCondition condition, ListingStatus status, Seller seller, double price, double creationTime) {
        this.id = id;
        this.book = book;
        this.condition = condition;
        this.status = status;
        this.seller = seller;
        this.price = price;
        this.creationTime = creationTime;
    }
    
    public int getId() {
        return id;
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

    public double getCreationTime() {
        return creationTime;
    }

    public void updateStatus(ListingStatus newStatus) {
        App app = App.getInstance();
        app.listingDB.updateStatus(id, newStatus.getValue());
    }
}
