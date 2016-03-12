package com.marcohc.architecture.data.error;

/**
 * Representation of a Rest error. Happens in data layer
 */
public class RestError {

    private Integer code;
    private String message;

    public RestError(String message, Integer code) {
        this.message = message;
        this.code = code;
    }

    public RestError(String message) {
        this.message = message;
        this.code = -1;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
