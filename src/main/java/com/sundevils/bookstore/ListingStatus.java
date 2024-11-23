package com.sundevils.bookstore;

public enum ListingStatus {
    PENDING(0),
    UNAVAILABLE(1),
    AVAILABLE(2);
    
    private final int value;

    ListingStatus(int value) {
        this.value = value;
    }

    public int getValue() { return value; }

    public static ListingStatus fromInt(int i) {
        for (ListingStatus enumValue : ListingStatus.values()) {
            if (enumValue.getValue() == i) {
                return enumValue;
            }
        }
        throw new IllegalArgumentException("Unexpected value: " + i);
    }
}