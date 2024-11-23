package com.sundevils.bookstore;

public enum BookCategory {
    NATURAL_SCIENCE(0),
    MATH(1),
    ENGLISH(2),
    COMPUTER(3);
    
    private final int value;

    BookCategory(int value) {
        this.value = value;
    }

    public int getValue() { return value; }

    public static BookCategory fromInt(int i) {
        for (BookCategory enumValue : BookCategory.values()) {
            if (enumValue.getValue() == i) {
                return enumValue;
            }
        }
        throw new IllegalArgumentException("Unexpected value: " + i);
    }
}