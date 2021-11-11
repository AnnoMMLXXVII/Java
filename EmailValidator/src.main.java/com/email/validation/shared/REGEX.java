package com.email.validation.shared;

public enum REGEX {

    ALPHANUMERIC_AFTER_SYMBOL("(\s+|\\.|-|_)(([A-Z|a-z|0-9])+)"),
    INVALID_DOUBLE_PERIOD("\\.\\."),
    INVALID_DOUBLE_UNDERSCORE("__"),
    INVALID_DOUBLE_DASH("--");

    private String value;

    REGEX(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
