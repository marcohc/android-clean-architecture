package com.marcohc.architecture.aca.presentation.bus.data.error;

/**
 * An exception which occurs on the data layer.
 *
 * @author Marco Hernaiz
 * @since 09/08/16
 */
public class DataException extends Exception {

    public DataException() {
    }

    public DataException(String detailMessage) {
        super(detailMessage);
    }

    public DataException(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }

    public DataException(Throwable throwable) {
        super(throwable);
    }

}
