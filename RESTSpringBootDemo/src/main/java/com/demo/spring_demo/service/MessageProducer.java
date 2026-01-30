package com.demo.spring_demo.service;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
public class MessageProducer {
    
    private final JmsTemplate jmsTemplate;

    public MessageProducer(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    public void sendMessage(String message) {
        jmsTemplate.convertAndSend("demo.queue", message);
    }


}

// The MessageProducer class is a Spring service that sends messages to a JMS queue.
// It uses the JmsTemplate provided by Spring to handle the messaging operations. The class
// is annotated with @Service, indicating that it is a service component managed by
// the Spring container. The constructor takes a JmsTemplate as a parameter, which is
// injected by Spring's dependency injection mechanism. The sendMessage method takes a
// String message as input and uses the JmsTemplate to send the message to a queue named
// "demo.queue".
