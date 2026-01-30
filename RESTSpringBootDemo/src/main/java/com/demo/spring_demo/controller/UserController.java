package com.demo.spring_demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.demo.spring_demo.service.ExternalUserService;
import com.demo.spring_demo.service.MessageProducer;
import com.demo.spring_demo.service.CachedUserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.beans.factory.annotation.Autowired;

//controller class
@RestController
@RequestMapping("/users")
public class UserController {

    //logger                                                        ///Creating Logger
    private static final Logger log =
            LoggerFactory.getLogger(UserController.class);

    //external user service
    @Autowired
    private ExternalUserService externalUserService;

    //what would be the difference without @autowired?

    // message producer
    @Autowired
    private MessageProducer messageProducer;

    // cached user service
    @Autowired
    private CachedUserService cachedUserService;

    //Endpoints

    //REST endpoint - getting user by id
    @GetMapping("/{id}")
    public String getUser(@PathVariable int id)
    {
        log.info("HELLO FROM LOGGER:Fetching user with id: {}", id);            //logger here to log info

        if(id == 0)
        {
            //exception thrown
            throw new RuntimeException("Global Exception Handler - Invalid ID exception: " + id);
        }

        return "User " + id;

    }

    //REST endpoint to fetch external user
    @GetMapping("/external")
    public String getExternalUser() {
        return externalUserService.fetchUser();
    }

    ///REST endpoint to send message using producer and consumer
    @PostMapping("/message")
    public void sendMessage() {
        messageProducer.sendMessage("Hello! It's Kevin Martinez from Consumer to Producer.");
    }

    @GetMapping("/cached/{id}")
    public String getCachedUser(@PathVariable int id) {
        log.info("HELLO FROM LOGGER:Caching user data with id: {}", id); 
        return cachedUserService.getUser(id);
    }

    @GetMapping("/")
    public String home() {
        return "Spring Demo App is running!";
    }
    
    
    
}

// The UserController class is a REST controller in a Spring Boot application
// that handles HTTP requests related to users. It is annotated with @RestController
// and @RequestMapping("/users"), indicating that it will handle requests to the /users
// endpoint. The class contains a single method, getUser, which is mapped to GET requests
// at the /users/{id} endpoint using the @GetMapping annotation. This method takes
// a user ID as a path variable and logs the request. If the provided ID is 0, it throws
// a RuntimeException indicating that the user ID is invalid. Otherwise, it returns a simple string
// representing the user. The class also utilizes SLF4J for logging purposes.


/*
Commands to run:
mvn spring-boot:run
*/