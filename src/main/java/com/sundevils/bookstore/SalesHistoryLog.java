package com.sundevils.bookstore;

public class SalesHistoryLog {
    private int id;
    private Listing listing;
    private double time;

    public SalesHistoryLog(int id, Listing listing, double time) {
        this.id = id;
        this.listing = listing;
        this.time = time;
    }
    
    public int getId() {
        return id;
    }

    public Listing getListing() {
        return listing;
    }

    public double getTime() {
        return time;
    }
}
