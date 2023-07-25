package com.hidden.godfather.customer.exception;


import lombok.ToString;

@ToString
public class HiddenGodfatherException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private int httpStatus;
    private String error;

    public HiddenGodfatherException(int httpStatus, String error, String message) {
        super(message);
        this.httpStatus = httpStatus;
        this.error = error;
    }

    public HiddenGodfatherException(int httpStatus, String error, String message, Throwable cause) {
        super(message, cause);
        this.httpStatus = httpStatus;
        this.error = error;
    }

    public HiddenGodfatherException() {

    }

    public int getHttpStatus() {
        return httpStatus;
    }

    public String getError() {
        return error;
    }


}
