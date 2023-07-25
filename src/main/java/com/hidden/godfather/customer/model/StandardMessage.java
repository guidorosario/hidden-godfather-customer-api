package com.hidden.godfather.customer.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;

public class StandardMessage  implements Serializable {
    private static final long serialVersionUID = 1L;

    private String error;
    private String error_description;


    public StandardMessage(String error, String error_description) {
        this.error = error;
        this.error_description = error_description;
    }

    public String getError() {
        return error;
    }

    public String getError_description() {
        return error_description;
    }

    public void updateMessage(String error, String error_message) {
        this.error = error;
        this.error_description = error_message;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("error", error)
                .append("error_description", error_description)
                .toString();
    }
}
