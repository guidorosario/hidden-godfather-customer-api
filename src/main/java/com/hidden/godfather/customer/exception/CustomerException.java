package com.hidden.godfather.customer.exception;

public class CustomerException extends HiddenGodfatherException {

    public CustomerException() {
        super();
    }

    public CustomerException(int httpStatus, String errorCode, String description) {
        super(httpStatus, errorCode, description);
    }
}
