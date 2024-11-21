package com.sundevils.bookstore;

public enum AccountType {
    PENDING(0),
    BUYER(1),
    SELLER(2),
    ADMIN(3);

    private final int value;

    AccountType(int value) {
        this.value = value;
    }

    public int getValue() { return value; }

    public static AccountType fromInt(int i) {
        for (AccountType accountType : AccountType.values()) {
            if (accountType.getValue() == i) {
                return accountType;
            }
        }
        throw new IllegalArgumentException("Unexpected value: " + i);
    }
}