package com.hidden.godfather.customer.model.request;


import lombok.Builder;

@Builder


public record CustomerRequest(
        String name,
        String documentNumber,
        String email,
        String phone) {
}
