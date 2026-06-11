package com.spring.sample.chatbot.controller;

import com.spring.sample.chatbot.domainObj.ChatRequest;
import com.spring.sample.chatbot.domainObj.PromptChatResponse;
import com.spring.sample.chatbot.service.RagChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/ai")
@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequiredArgsConstructor
public class RagVectorStoreController {


//    @PostMapping("/fetchDetailsFromRag")
//    public PromptChatResponse chat(@RequestBody ChatRequest chatRequest, String conversationId) {
//        return ragChatService.fetchDetailsFromRag(chatRequest, conversationId );
//    }
}
