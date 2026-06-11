package com.spring.sample.chatbot;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 *Main class of the application
 * Suse : <a href="http://localhost:8080/swagger-ui.html">...</a>
 *
 */
@Slf4j
@SpringBootApplication
public class ChatBootDemo {
    public static void main(String[] args) {
        log.info("Starting the Chat App");
        SpringApplication.run(ChatBootDemo.class);
        log.info("Chat App Starts");
    }
}