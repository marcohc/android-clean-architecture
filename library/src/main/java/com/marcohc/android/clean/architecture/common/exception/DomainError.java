package com.marcohc.android.clean.architecture.common.exception;

public enum DomainError {

    UNKNOWN(1, "unknown"),
    AUTHENTICATION(2, "authentication");

    private final int code;
    private final String text;

    DomainError(final int code, final String text) {
        this.code = code;
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }

    public int getCode() {
        return code;
    }

}