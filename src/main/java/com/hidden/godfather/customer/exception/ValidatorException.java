package com.hidden.godfather.customer.exception;

public class ValidatorException extends HiddenGodfatherException {

    public ValidatorException() {
        super();
    }

    public ValidatorException(int httpStatus, String errorCode, String description) {
        super(httpStatus, errorCode, description);
    }
}
