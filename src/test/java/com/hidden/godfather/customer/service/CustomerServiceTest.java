package com.hidden.godfather.customer.service;

import com.hidden.godfather.customer.exception.CustomerException;
import com.hidden.godfather.customer.exception.ValidatorException;
import com.hidden.godfather.customer.model.request.CustomerRequest;
import com.hidden.godfather.customer.repository.CustomerRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static com.hidden.godfather.customer.model.CustomerMock.customerMock;
import static com.hidden.godfather.customer.model.CustomerMock.customerRequestMock;
import static com.hidden.godfather.customer.model.CustomerMock.customerRequestUpdateMock;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
public class CustomerServiceTest {

    @InjectMocks
    private CustomerService customerService;

    @Mock
    private CustomerRepository customerRepository;


    @Test
    @DisplayName("Deve retonar o cliente")
    void get_customer() {

        when(customerRepository.findByDocumentNumber(any())).thenReturn(Mono.just(customerMock()));

        StepVerifier.create(customerService.findCustomerByDocumentNumber(customerRequestMock().documentNumber()))
                .expectSubscription()
                .assertNext(customer -> {
                    assertEquals(customer.getId(), customerMock().getId());
                    assertEquals(customer.getName(), customerMock().getName());
                    assertEquals(customer.getActive(), customerMock().getActive());
                    assertEquals(customer.getDocumentType(), customerMock().getDocumentType());
                    assertEquals(customer.getPhone(), customerMock().getPhone());
                })
                .verifyComplete();
    }

    @Test
    @DisplayName("Deve retornar um lista de clientes")
    void list_customer() {

        when(customerRepository.findAll()).thenReturn(Flux.just(customerMock(), customerMock()));

        StepVerifier.create(customerService.getAllCustomer())
                .assertNext(customer -> {
                    assertEquals(customer.getId(), customerMock().getId());
                    assertEquals(customer.getName(), customerMock().getName());
                    assertEquals(customer.getActive(), customerMock().getActive());
                    assertEquals(customer.getDocumentType(), customerMock().getDocumentType());
                    assertEquals(customer.getPhone(), customerMock().getPhone());
                })
                .assertNext(customer -> {
                    assertEquals(customer.getId(), customerMock().getId());
                    assertEquals(customer.getName(), customerMock().getName());
                    assertEquals(customer.getActive(), customerMock().getActive());
                    assertEquals(customer.getDocumentType(), customerMock().getDocumentType());
                    assertEquals(customer.getPhone(), customerMock().getPhone());
                }).verifyComplete();
    }

    @Test
    @DisplayName("Deve criar um cliente")
    void create_customer() {

        when(customerRepository.findByDocumentNumber(any())).thenReturn(Mono.empty());

        when(customerRepository.save(any())).thenReturn(Mono.just(customerMock()));

        StepVerifier.create(customerService.createCustomer(customerRequestMock()))
                .expectSubscription()
                .assertNext(customer -> {
                    assertEquals(customer.getId(), customerMock().getId());
                    assertEquals(customer.getName(), customerMock().getName());
                    assertEquals(customer.getActive(), customerMock().getActive());
                    assertEquals(customer.getDocumentType(), customerMock().getDocumentType());
                    assertEquals(customer.getPhone(), customerMock().getPhone());
                }).verifyComplete();
    }

    @Test
    @DisplayName("Deve dar erro de validação de documento")
    void create_customer_invalid_documentNumber() {

        var customerRequest = CustomerRequest.builder().documentNumber("47587456987").build();

        when(customerRepository.findByDocumentNumber(any())).thenReturn(Mono.empty());

        StepVerifier.create(customerService.createCustomer(customerRequest))
                .expectError(ValidatorException.class)
                .log()
                .verify();
    }

    @Test
    @DisplayName("Deve criar um erro de 409")
    void create_customer_conflict() {

        when(customerRepository.findByDocumentNumber(any())).thenReturn(Mono.just(customerMock()));

        StepVerifier.create(customerService.createCustomer(customerRequestMock()))
                .expectError(CustomerException.class)
                .log()
                .verify();
    }

    @Test
    @DisplayName("Deve atualizar o cliente")
    void update_Customer() {

        var customerUp = customerMock();
        customerUp.setName(customerRequestUpdateMock().name());

        when(customerRepository.findByDocumentNumber(any())).thenReturn(Mono.just(customerMock()));

        when(customerRepository.save(any())).thenReturn(Mono.just(customerUp));

        StepVerifier.create(customerService.updateCustomer(customerRequestUpdateMock(), customerMock().getDocumentNumber()))
                .expectSubscription()
                .assertNext(customer -> {
                    assertNotEquals(customer.getName(), customerMock().getName());
                }).verifyComplete();
    }


    @Test
    @DisplayName("Deve ativar o cliente")
    void active_Customer() {

        var customerUp = customerMock();

        when(customerRepository.findByDocumentNumber(any())).thenReturn(Mono.just(customerMock()));

        when(customerRepository.save(any())).thenReturn(Mono.just(customerUp));

        StepVerifier.create(customerService.activeCustomer(customerMock().getDocumentNumber()))
                .expectSubscription()
                .assertNext(customer -> assertTrue(customer.getActive()))
                .verifyComplete();
    }

    @Test
    @DisplayName("Deve inativar o cliente")
    void inactive_Customer() {

        var customerUp = customerMock();
        customerUp.setActive(false);

        when(customerRepository.findByDocumentNumber(any())).thenReturn(Mono.just(customerMock()));

        when(customerRepository.save(any())).thenReturn(Mono.just(customerUp));

        StepVerifier.create(customerService.activeCustomer(customerMock().getDocumentNumber()))
                .expectSubscription()
                .assertNext(customer -> assertFalse(customer.getActive()))
                .verifyComplete();
    }

    @Test
    @DisplayName("Deve deletar um cliente")
    void delete_Customer() {

        when(customerRepository.findByDocumentNumber(any())).thenReturn(Mono.just(customerMock()));

        when(customerRepository.delete(any())).thenReturn(Mono.empty());

        StepVerifier.create(customerService.deleteByDocumentNumber(customerMock().getDocumentNumber()))
                .verifyComplete();
    }

}
