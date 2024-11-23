package com.sundevils.bookstore;

public enum BookCondition {
    USEDLIKENEW(0),
    MODERATELYUSED(1),
    HEAVILYUSED(2);
    
    private final int value;

    BookCondition(int value) {
        this.value = value;
    }

    public int getValue() { return value; }

    public static BookCondition fromInt(int i) {
        for (BookCondition enumValue : BookCondition.values()) {
            if (enumValue.getValue() == i) {
                return enumValue;
            }
        }
        throw new IllegalArgumentException("Unexpected value: " + i);
    }
}