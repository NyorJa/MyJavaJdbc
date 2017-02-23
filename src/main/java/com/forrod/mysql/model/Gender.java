package com.forrod.mysql.model;

public enum Gender {
    MALE("MALE"),
    FEMALE("FEMALE"),
    UNDECIDED("UNDECIDED"),
    NONE("NONE");

    private String value;

    Gender(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
