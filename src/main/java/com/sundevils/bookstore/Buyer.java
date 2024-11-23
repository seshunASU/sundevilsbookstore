package com.sundevils.bookstore;

import java.util.ArrayList;

public class Buyer extends User {
    public ArrayList<Listing> cart;

    public Buyer(int id, String displayName, String username, String password, double creationTime) {
        super(id, displayName, username, password, AccountType.BUYER, creationTime);
        cart = new ArrayList<>();    
    }
}