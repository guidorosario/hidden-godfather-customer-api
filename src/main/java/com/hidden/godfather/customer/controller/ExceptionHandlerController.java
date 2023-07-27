package com.hidden.godfather.customer.controller;


import com.hidden.godfather.customer.exception.HiddenGodfatherException;
import com.hidden.godfather.customer.model.StandardMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@RestControllerAdvice
public class ExceptionHandlerController {

    private static final Logger LOG = LoggerFactory.getLogger(ExceptionHandlerController.class);

    @ExceptionHandler(HiddenGodfatherException.class)
    public Mono<ResponseEntity<StandardMessage>> handlePasswordException(HiddenGodfatherException e, ServerWebExchange exchange) {
        LOG.info("[{}] [{}]", e.getClass().getName(), e.getMessage());
        return Mono.just(ResponseEntity.status(e.getHttpStatus())
                .body(new StandardMessage(e.getError(), e.getMessage())));
    }
}
