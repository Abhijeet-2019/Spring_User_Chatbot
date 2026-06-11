package com.spring.sample.chatbot.service;

import com.spring.sample.chatbot.domainObj.ChatRequest;
import com.spring.sample.chatbot.domainObj.PromptChatResponse;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.metadata.Usage;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.memory.ChatMemory;

import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 *
 */
@Service
public class ChatService {

    private final ChatClient chatClient;
    ChatService(ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    public String chat(String message) {
        ChatResponse response = chatClient.prompt()
                .user(message)
                .advisors(advisor ->
                        advisor.param(ChatMemory.CONVERSATION_ID, "default"))
                .call()
                .chatResponse();
        Usage usage = response.getMetadata().getUsage();

        System.out.println("Prompt Tokens: " + usage.getPromptTokens());
        System.out.println("Completion Tokens: " + usage.getCompletionTokens());
        System.out.println("Total Tokens: " + usage.getTotalTokens());
        return response.getResult().getOutput().getText();
    }


    public PromptChatResponse chatWithBulletPoints(ChatRequest chatRequest, String conversationID) {
        String converID = (conversationID == null || conversationID.isEmpty()) ?
                UUID.randomUUID().toString(): conversationID;
        System.out.println(" The conversation id "+converID);
        ChatResponse response = chatClient.prompt()
                .system("Please send the response in Bullet Points and not more than 10 lines")
                .user(chatRequest.getPromptMessage())
                .advisors(advisor ->
                        advisor.param(ChatMemory.CONVERSATION_ID, converID))
                .call().chatResponse();
        Usage usage = response.getMetadata().getUsage();
        System.out.println("Prompt Tokens: " + usage.getPromptTokens());
        System.out.println("Completion Tokens: " + usage.getCompletionTokens());
        System.out.println("Total Tokens: " + usage.getTotalTokens());
        return new PromptChatResponse(response.getResult().getOutput().getText(), converID);
       }
}
