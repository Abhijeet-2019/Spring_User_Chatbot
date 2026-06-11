package com.spring.sample.chatbot.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
public class ChatHistroyController {

    @GetMapping("/api/recent")
    public List<String> recentChats(){

        return List.of(
                "What is Spring Boot?",
                "Explain Kafka partition",
                "How does OAuth2 work?"
        );
    }
}
