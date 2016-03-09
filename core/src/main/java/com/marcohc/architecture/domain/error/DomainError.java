package com.marcohc.architecture.domain.error;

/**
 * An error which occurs on the domain layer
 */
public class DomainError {

    private String message;
    private int code;

    public DomainError(String message, int code) {
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