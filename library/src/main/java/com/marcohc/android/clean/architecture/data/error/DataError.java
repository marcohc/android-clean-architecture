package com.marcohc.android.clean.architecture.data.error;

/**
 * An error which occurs on the data layer
 */
public class DataError {

    private String message;
    private int code;

    public DataError(String message, int code) {
        this.message = message;
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

}
