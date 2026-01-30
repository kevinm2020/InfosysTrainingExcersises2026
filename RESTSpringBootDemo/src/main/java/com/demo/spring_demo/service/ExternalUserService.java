package com.demo.spring_demo.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ExternalUserService {

    private final RestTemplate restTemplate;

    public ExternalUserService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String fetchUser() {
        return restTemplate.getForObject(
                "https://jsonplaceholder.typicode.com/users/1",
                String.class
        );
    }
}


/*
Here we are using RestTemplate to make an HTTP GET request to an external API
("https://jsonplaceholder.typicode.com/users/1") to fetch user data.
    The fetched data is returned as a String.
My application is now able to retrieve user information from an external source
and format it as a String for further processing or display.
*/