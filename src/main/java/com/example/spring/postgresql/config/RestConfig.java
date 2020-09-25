package com.example.spring.postgresql.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestConfig {

    @Bean
    public RestTemplate restTemplate() {
        // Para esse caso é necessário apenas para termos o método http PATCH no RestTemplate
        return new RestTemplate(new HttpComponentsClientHttpRequestFactory());
    }
}
