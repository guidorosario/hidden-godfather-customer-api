package com.hidden.godfather.customer.controller;

import com.hidden.godfather.customer.service.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static com.hidden.godfather.customer.model.CustomerMock.customerMock;
import static com.hidden.godfather.customer.model.CustomerMock.customerRequestMock;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@WebFluxTest(controllers = CustomerController.class)
public class CustomerControllerIntegrationTest {
    @InjectMocks
    private CustomerController customerController;

    @MockBean
    private CustomerService customerService;

    @Autowired
    private WebTestClient webTestClient;



    public CustomerControllerIntegrationTest() {
    }
    private static final String CPF = "65142125495";
    private static final String CNPJ = "84392874000121";
    private final String BASE_URL = "http://localhost:8080";
    private final String URI_CHECK_CUSTOMER_BY_DOCUMENT_NUMBER = "/customers/documentNumber/%s";


    @BeforeEach
    void setup(){
        webTestClient = WebTestClient.bindToController(new CustomerController(customerService))
                .configureClient()
                .baseUrl(BASE_URL)
                .build();
    }

    @Test
    @DisplayName("Deve Buscar um cliente com CPF na base")
    void findCustomerByDocumentNumber(){
        var expected = customerMock();

        var finalUrl = String.format("/customer/%s", CPF);

        when(customerService.findCustomerByDocumentNumber(any())).thenReturn(Mono.just(expected));

        webTestClient
                .get()
                .uri(finalUrl)
                .exchange()
                .expectStatus()
                .is2xxSuccessful()
                .expectHeader()
                .valueEquals("Content-Type", "application/json")
                .expectBody()
                .jsonPath( "id").isEqualTo(expected.getId())
                .jsonPath("name").isEqualTo(expected.getName())
                .jsonPath("documentNumber").isEqualTo(expected.getDocumentNumber())
                .jsonPath("phone").isEqualTo(expected.getPhone())
                .jsonPath("email").isEqualTo(expected.getEmail())
                .jsonPath("documentType").isEqualTo(expected.getDocumentType().name())
                .jsonPath("active").isEqualTo(expected.getActive());

    }


    @Test
    @DisplayName("Deve Buscar uma lista cliente com CPF na base")
    void listFindCustomerByDocumentNumber(){
        var expected = customerMock();

        var finalUrl = String.format("/customer");

        when(customerService.getAllCustomer()).thenReturn(Flux.just(expected));

        webTestClient
                .get()
                .uri(finalUrl)
                .exchange()
                .expectStatus()
                .is2xxSuccessful()
                .expectHeader()
                .valueEquals("Content-Type", "application/json");

    }


    @Test
    @DisplayName("Deve atualizar o customer")
    void update_customer(){
        var expected = customerMock();

        var finalUrl = String.format("/customer/%s", CPF);

        when(customerService.updateCustomer(any(), any())).thenReturn(Mono.just(expected));


        webTestClient
                .put()
                .uri(finalUrl)
                .bodyValue(customerRequestMock())
                .exchange()
                .expectStatus()
                .is2xxSuccessful();

    }

    @Test
    @DisplayName("Deve inativar o customer")
    void inactive_customer(){

        var expected = customerMock();

        var finalUrl = String.format("/customer/inactive/%s", CPF);

        when(customerService.inactiveCustomer(any())).thenReturn(Mono.just(expected));

        webTestClient
                .patch()
                .uri(finalUrl)
                .exchange()
                .expectStatus()
                .is2xxSuccessful();

    }

    @Test
    @DisplayName("Deve ativar o customer")
    void active_customer(){

        var expected = customerMock();

        var finalUrl = String.format("/customer/active/%s", CPF);

        when(customerService.activeCustomer(any())).thenReturn(Mono.just(expected));

        webTestClient
                .patch()
                .uri(finalUrl)
                .exchange()
                .expectStatus()
                .is2xxSuccessful();

    }
    @Test
    @DisplayName("Deve ativar o customer")
    void delete_customer(){

        var expected = customerMock();

        var finalUrl = String.format("/customer/%s", CPF);

        when(customerService.deleteByDocumentNumber(any())).thenReturn(Mono.empty());

        webTestClient
                .delete()
                .uri(finalUrl)
                .exchange()
                .expectStatus()
                .is2xxSuccessful();

    }


}
