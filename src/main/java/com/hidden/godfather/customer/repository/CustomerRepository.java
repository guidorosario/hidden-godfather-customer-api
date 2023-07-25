package com.hidden.godfather.customer.repository;

import com.hidden.godfather.customer.mongo.Customer;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface CustomerRepository extends ReactiveMongoRepository<Customer, String> {

    Mono<Customer> save(Customer customer);

    Mono<Customer> findByDocumentNumber(String documentNumber);

    Mono<Void> delete(Customer customer);



}
