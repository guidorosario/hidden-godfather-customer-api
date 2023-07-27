package com.hidden.godfather.customer.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
public class WebSecurityConfiguration {

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        return http.csrf().disable()
                .formLogin()
                .disable()
                .httpBasic()
                .disable()
                .authorizeExchange()
                .pathMatchers("/customer/**","/actuator/**",
                        "/docs.html","/webjars/**", "/webjars/swagger-ui/**", "/swagger-ui.html","/docs/**", "/v3/api-docs/**")
                .permitAll()
                .anyExchange()
                .authenticated()
                .and().build();
    }
}