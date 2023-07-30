package com.hidden.godfather.customer.controller;

import com.hidden.godfather.customer.model.request.CustomerRequest;
import com.hidden.godfather.customer.mongo.Customer;
import com.hidden.godfather.customer.service.CustomerService;
import com.hidden.godfather.customer.service.TeamsWebHookService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    private final CustomerService customerService;
    private final TeamsWebHookService teamsWebHookService;

    public CustomerController(CustomerService customerService, TeamsWebHookService teamsWebHookService) {
        this.customerService = customerService;
        this.teamsWebHookService = teamsWebHookService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @GetMapping()
    Flux<Customer> getAllCustomer() {
        return customerService.getAllCustomer();
    }


    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping()
    Mono<Void> createCustomer(@RequestBody CustomerRequest customerRequest) {
        teamsWebHookService.
                createWebHook("Criação de Novo Usuário", "Usuário " + customerRequest.name() + "criado", "Criado");
        return customerService.createCustomer(customerRequest).then();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{documentNumber}")
    Mono<Customer> findCustomer(@PathVariable String documentNumber){
        return customerService.findCustomerByDocumentNumber(documentNumber);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{documentNumber}")
    Mono<Void> updateGrantee(@PathVariable String documentNumber,
                             @RequestBody CustomerRequest customerRequest) {
        return customerService.updateCustomer(customerRequest, documentNumber).then();
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping("/active/{documentNumber}")
    Mono<Void> activeCustomer(@PathVariable String documentNumber){
        return customerService.activeCustomer(documentNumber).then();
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping("/inactive/{documentNumber}")
    Mono<Void> inactiveCustomer(@PathVariable String documentNumber){
        return customerService.inactiveCustomer(documentNumber).then();
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{documentNumber}")
    Mono<Void> deleteCustomerByDocumentNumber(@PathVariable String documentNumber){
        return customerService.deleteByDocumentNumber(documentNumber);
    }






}
