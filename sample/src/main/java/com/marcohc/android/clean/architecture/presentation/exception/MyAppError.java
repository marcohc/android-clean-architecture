package com.marcohc.android.clean.architecture.presentation.exception;

import com.marcohc.android.clean.architecture.presentation.error.AppError;

/**
 * A representation error of the app in high level
 */
public enum MyAppError implements AppError {

    UNKNOWN(1, "unknown"),
    AUTHENTICATION(2, "authentication");

    private final int code;
    private final String text;

    MyAppError(final int code, final String text) {
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