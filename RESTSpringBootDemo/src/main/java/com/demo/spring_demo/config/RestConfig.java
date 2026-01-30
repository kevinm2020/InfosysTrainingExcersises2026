package com.demo.spring_demo.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.boot.web.client.RestTemplateBuilder;


@Configuration
public class RestConfig {

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }
}

// The RestConfig class is a configuration class in a Spring Boot application
// that defines a RestTemplate bean. It is annotated with @Configuration, indicating
// that it contains bean definitions. The restTemplate method is annotated with @Bean,
// which tells Spring to manage the returned RestTemplate instance as a bean in the
// application context. This RestTemplate bean can then be injected and used throughout
// the application for making RESTful web service calls.    