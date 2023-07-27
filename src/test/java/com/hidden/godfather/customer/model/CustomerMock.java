package com.hidden.godfather.customer.model;

import com.hidden.godfather.customer.model.enums.DocumentType;
import com.hidden.godfather.customer.model.request.CustomerRequest;
import com.hidden.godfather.customer.mongo.Customer;

import java.time.LocalDateTime;

public class CustomerMock {

    public static Customer customerMock() {
        return Customer.builder()
                .id("dfdsfdsfsdg-asfasdfad-afadfadf-sadf-ad-ffff")
                .name("Jõao Fiqueiredo")
                .documentType(DocumentType.CPF)
                .documentNumber("07636387069")
                .email("joao.fi@gmail.com")
                .phone("11234343456")
                .active(true)
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static CustomerRequest customerRequestMock() {
        return CustomerRequest.builder()
                .name("Jõao Fiqueiredo")
                .documentNumber("07636387069")
                .email("joao.fi@gmail.com")
                .phone("11234343456")
                .build();
    }

    public static CustomerRequest customerRequestUpdateMock() {
        return CustomerRequest.builder()
                .name("Ana Fiqueiredo")
                .documentNumber("07636387069")
                .email("joao.fi@gmail.com")
                .phone("11234343456")
                .build();
    }
}
