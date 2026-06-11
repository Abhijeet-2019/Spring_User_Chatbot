package com.spring.sample.chatbot.controller;


import com.spring.sample.chatbot.domainObj.ChatRequest;
import com.spring.sample.chatbot.domainObj.PromptChatResponse;
import com.spring.sample.chatbot.service.ChatService;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.messages.Message;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/ai")
@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class ChatController {

    private  final ChatMemory chatMemory;

    private final ChatService chatService;

    ChatController(ChatService chatService, ChatMemory chatMemory) {
        this.chatService = chatService;
        this.chatMemory = chatMemory;
    }

    @PostMapping("/chat")
    public String chat(@RequestBody ChatRequest chatRequest, String conversationID){
        return chatService.chat(chatRequest.getPromptMessage());
    }

    /**
     *
     * @param chatRequest
     * @param conversationID
     * @return
     */
    @PostMapping("ChatWithBulletPoints")
    public PromptChatResponse chatWithBullets(@RequestBody ChatRequest chatRequest, @RequestParam(required = false)  String conversationID){
        PromptChatResponse promptChatResponse =chatService.chatWithBulletPoints(chatRequest, conversationID);
        System.out.println("the response---"+promptChatResponse);
        return promptChatResponse;
    }


    @GetMapping("/fetchConversation")
    public List<Message> fetchConversation(@RequestParam String conversationId){
        return chatMemory.get(conversationId);
    }

}
