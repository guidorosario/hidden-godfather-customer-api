package com.hidden.godfather.customer.service;

import com.hidden.godfather.customer.exception.CustomerException;
import com.hidden.godfather.customer.model.request.CustomerRequest;
import com.hidden.godfather.customer.mongo.Customer;
import com.hidden.godfather.customer.repository.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.print.DocFlavor;

import java.util.Objects;

import static com.hidden.godfather.customer.mongo.mapper.CustomerMapper.customerUpdate;
import static com.hidden.godfather.customer.mongo.mapper.CustomerMapper.customerUpdateActive;
import static com.hidden.godfather.customer.mongo.mapper.CustomerMapper.toCustomer;
import static com.hidden.godfather.customer.util.DocumentUtils.validateDocument;

@Service
public class CustomerService {
    private static final Logger LOG = LoggerFactory.getLogger(CustomerService.class);

    private final CustomerRepository customerRepository;


    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }


    public Mono<Customer> createCustomer(CustomerRequest customerRequest) {
        LOG.info("Iniciando criação do Cliente");
        return customerRepository.findByDocumentNumber(customerRequest.documentNumber())
                .flatMap(customer -> Mono.error(new CustomerException(409, "Erro ao criar customer", "Já existe um cliente cadastro com esse Numero de documento")).thenReturn(customer))
                .switchIfEmpty(Mono.defer(() -> Mono.just(validateDocument(customerRequest.documentNumber())))
                        .doOnSuccess($-> LOG.info(""))
                        .map(documentType -> toCustomer(customerRequest, documentType))
                        .flatMap(customerRepository::save))
                .doOnSuccess($ -> LOG.info("Sucesso ao criar cliente"));

    }
    public Flux<Customer> getAllCustomer() {
        return customerRepository.findAll();
    }


    public Mono<Customer> findCustomerByDocumentNumber(String documentNumber){
        return Mono.just(validateDocument(documentNumber))
                .doOnSuccess($ -> LOG.info("Numero de documento validado com sucesso"))
                .flatMap($ -> customerRepository.findByDocumentNumber(documentNumber))
                .switchIfEmpty(Mono.defer(() -> Mono.error(new CustomerException(404, "Não Encontrado", "Não existe cliente com esse numero de documento"))));
    }

    public Mono<Customer> updateCustomer(CustomerRequest customerRequest, String documentNumber) {
        return Mono.just(validateDocument(documentNumber))
                .doOnSuccess($ -> LOG.info("Numero de documento validado com sucesso"))
                .flatMap($ -> customerRepository.findByDocumentNumber(documentNumber))
                .switchIfEmpty(Mono.defer(() -> Mono.error(new CustomerException(404, "Não Encontrado", "Não existe cliente com esse numero de documento"))))
                .doOnSuccess($ -> LOG.info("Sucesso ao encontrar o cliente"))
                .map(customer -> customerUpdate(customer, customerRequest))
                .flatMap(customerRepository::save)
                .doOnSuccess($ -> LOG.info("Sucesso ao atualizar o cliente"));
    }

    public Mono<Customer> activeCustomer(String documentNumber) {
        return Mono.just(validateDocument(documentNumber))
                .doOnSuccess($ -> LOG.info("Numero de documento validado com sucesso"))
                .flatMap($ -> customerRepository.findByDocumentNumber(documentNumber))
                .switchIfEmpty(Mono.defer(() -> Mono.error(new CustomerException(404, "Não Encontrado", "Não existe cliente com esse numero de documento"))))
                .map(customer -> customerUpdateActive(customer, true))
                .flatMap(customerRepository::save)
                .doOnSuccess($ -> LOG.info("Cliente ativado com sucesso"));
    }

    public Mono<Customer> inactiveCustomer(String documentNumber) {
        return Mono.just(validateDocument(documentNumber))
                .doOnSuccess($ -> LOG.info("Numero de documento validado com sucesso"))
                .flatMap($ -> customerRepository.findByDocumentNumber(documentNumber))
                .switchIfEmpty(Mono.defer(() -> Mono.error(new CustomerException(404, "Não Encontrado", "Não existe cliente com esse numero de documento"))))
                .map(customer -> customerUpdateActive(customer, false))
                .flatMap(customerRepository::save)
                .doOnSuccess($ -> LOG.info("Cliente inativado com sucesso"));
    }

    public Mono<Void> deleteByDocumentNumber(String documentNumber) {
     return Mono.just(validateDocument(documentNumber))
             .flatMap($ -> customerRepository.findByDocumentNumber(documentNumber))
             .switchIfEmpty(Mono.defer(() -> Mono.error(new CustomerException(404, "Não Encontrado", "Não existe cliente com esse numero de documento"))))
             .flatMap(customerRepository::delete);
    }


}
