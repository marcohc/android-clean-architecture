package com.marcohc.architecture.data.error;

import com.marcohc.architecture.common.bus.BusEvent;

/**
 * An exception which occurs on the data layer
 */
public class DataException extends Exception implements BusEvent {

    private int code;

    public DataException() {
    }

    public DataException(String detailMessage) {
        super(detailMessage);
    }

    public DataException(String detailMessage, int code) {
        super(detailMessage);
        this.code = code;
    }

    public DataException(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }

    public DataException(Throwable throwable) {
        super(throwable);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
