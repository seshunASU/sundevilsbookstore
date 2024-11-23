package com.sundevils.bookstore;

public class Seller extends User {
    public Seller(int id, String displayName, String username, String password, double creationTime) {
        super(id, displayName, username, password, AccountType.SELLER, creationTime);
    }
}