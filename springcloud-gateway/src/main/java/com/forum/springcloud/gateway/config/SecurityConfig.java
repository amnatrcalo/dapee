package com.forum.springcloud.gateway.config;

import com.forum.springcloud.gateway.error.ReactiveExceptionHandler;
import com.forum.springcloud.gateway.error.exception.WrappedException;
import org.springframework.boot.autoconfigure.web.WebProperties;
import org.springframework.boot.web.reactive.error.DefaultErrorAttributes;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

import java.util.Map;

import static org.springframework.security.config.Customizer.withDefaults;

@EnableWebFluxSecurity
@Configuration
public class SecurityConfig {

    @Bean
    public HttpStatus defaultStatus() {
        return HttpStatus.INTERNAL_SERVER_ERROR;
    }
    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        http.csrf().disable()
                .authorizeExchange(exchanges -> exchanges
                        .anyExchange().permitAll()
                )
                .httpBasic(withDefaults())
                .formLogin(withDefaults())
                .exceptionHandling();
        return http.build();
    }

    @Bean
    public ReactiveExceptionHandler reactiveExceptionHandler(WebProperties webProperties, ApplicationContext applicationContext, ServerCodecConfigurer configurer) {
        ReactiveExceptionHandler exceptionHandler = new ReactiveExceptionHandler(
                new DefaultErrorAttributes(), webProperties.getResources(), applicationContext, exceptionToStatusCode(), HttpStatus.INTERNAL_SERVER_ERROR
        );
        exceptionHandler.setMessageWriters(configurer.getWriters());
        exceptionHandler.setMessageReaders(configurer.getReaders());
        return exceptionHandler;
    }

    @Bean
    public Map<Class<? extends Exception>, HttpStatus> exceptionToStatusCode() {
        return Map.of(
                WrappedException.class, HttpStatus.BAD_REQUEST
        );
    }

}
