package com.demo.spring_demo.messaging;

import org.springframework.stereotype.Component;
import org.springframework.jms.annotation.JmsListener;

@Component
public class MessageListener {

    @JmsListener(destination = "demo.queue")
    public void receive(String message) {
        System.out.println("Received message: " + message);
    }
}

// The MessageListener class is a Spring component that listens for messages on a JMS queue.
// It is annotated with @Component, indicating that it is a Spring-managed bean. The
// receive method is annotated with @JmsListener, specifying that it listens to the
// "demo.queue" destination. When a message is received on this queue, the receive method
// is invoked, and it prints the received message to the console.

