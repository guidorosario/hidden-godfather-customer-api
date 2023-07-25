package com.hidden.godfather.customer.mongo.mapper;

import com.hidden.godfather.customer.model.enums.DocumentType;
import com.hidden.godfather.customer.model.request.CustomerRequest;
import com.hidden.godfather.customer.mongo.Customer;

import java.time.LocalDateTime;

import static com.hidden.godfather.customer.util.DateUtil.localDateTimeDefaultZone;
import static com.hidden.godfather.customer.util.DocumentUtils.validateDocument;
import static com.hidden.godfather.customer.util.DocumentUtils.validateDocumentNumber;

public class CustomerMapper {

    public static Customer toCustomer(CustomerRequest customerRequest, DocumentType documentType){
        return Customer.builder()
                .name(customerRequest.name())
                .email(customerRequest.email())
                .phone(customerRequest.phone())
                .documentNumber(customerRequest.documentNumber())
                .documentType(documentType)
                .active(true)
                .createdAt(localDateTimeDefaultZone())
                .build();
    }

    public static Customer customerUpdate(Customer customer, CustomerRequest customerRequest){

        customer.setName(customerRequest.name() == null ? customer.getName() : customerRequest.name());
        customer.setDocumentNumber(customerRequest.documentNumber() == null ? customer.getDocumentNumber() : validateDocumentNumber(customerRequest.documentNumber()));
        customer.setDocumentType(customerRequest.documentNumber() == null ? customer.getDocumentType() : validateDocument(customerRequest.documentNumber()));
        customer.setEmail(customerRequest.email() == null ? customer.getEmail() : customerRequest.email());
        customer.setUpdatedAt(localDateTimeDefaultZone());
        return customer;

    }

    public static Customer customerUpdateActive(Customer customer, Boolean active){
        customer.setActive(active);
        customer.setUpdatedAt(localDateTimeDefaultZone());
        return customer;
    }
}
