package com.email.validation.shared;

public enum SYMBOLS {
    AT('@'), UNDERSCORE('_'), PERIOD('.'), DASH('-'), POUND('#');

    private char value;

    SYMBOLS(char value) {
        this.value = value;
    }

    public char getValue() {
        return value;
    }

    public String getCharToString() {
        return Character.toString(value);
    }
}
