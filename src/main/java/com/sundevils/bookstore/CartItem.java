package com.sundevils.bookstore;

public class CartItem {
    private int id;
    private Listing listing;

    public CartItem(int id, Listing listing) {
        this.id = id;
        this.listing = listing;
    }

    public int getId() {
        return id;
    }

    public Listing getListing() {
        return listing;
    }
}
