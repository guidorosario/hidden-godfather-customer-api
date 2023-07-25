package com.hidden.godfather.customer.model.request;

public record CustomerRequest(
        String name,
        String documentNumber,
        String email,
        String phone) {
}
