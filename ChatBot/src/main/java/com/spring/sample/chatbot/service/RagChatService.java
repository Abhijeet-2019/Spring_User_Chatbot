package com.spring.sample.chatbot.service;


import com.spring.sample.chatbot.Rag.VectorStoreService;
import com.spring.sample.chatbot.domainObj.PromptChatResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.rag.retrieval.search.VectorStoreDocumentRetriever;
import org.springframework.stereotype.Service;
import com.spring.sample.chatbot.domainObj.ChatRequest;
import org.springframework.ai.chat.metadata.Usage;
import org.springframework.ai.chat.model.ChatResponse;

import java.util.UUID;

import org.springframework.ai.rag.advisor.RetrievalAugmentationAdvisor;

@Service
@RequiredArgsConstructor
public class RagChatService {

    private final ChatClient chatClient;
    private final VectorStoreService vectorStoreService;
    private final ChatMemory chatMemory;

    /**
     * This method is used when we need to fetch details from a RAG Vector store...
     *
     * @param chatRequest
     * @param conversationId
     * @return
     */
    public PromptChatResponse fetchDetailsFromRag(ChatRequest chatRequest, String conversationId) {
        String requestConID = (conversationId == null || conversationId.isBlank())
                ? UUID.randomUUID().toString()
                : conversationId;
        ChatResponse response = null;
        try {
            System.out.println("Conversation Id : " + requestConID);
            RetrievalAugmentationAdvisor qaAdvisor =
                    RetrievalAugmentationAdvisor.builder()
                            .documentRetriever(
                                    VectorStoreDocumentRetriever.builder()
                                            .vectorStore(vectorStoreService.getVectorStore())
                                            .build()).build();
            response = chatClient.prompt()
                    .system("Please send the response in Bullet Points and not more than 10 lines")
                    .user(chatRequest.getPromptMessage())
                    .advisors(qaAdvisor,
                            MessageChatMemoryAdvisor.builder(chatMemory)
                                    .build())
                    .advisors(spec ->
                            spec.param(ChatMemory.CONVERSATION_ID, requestConID)
                    )
                    .call()
                    .chatResponse();
            Usage usage = response.getMetadata().getUsage();
            System.out.println("The response of the Chat API" + response.getResult().toString());
            if (usage != null) {
                System.out.println("Prompt Tokens: " + usage.getPromptTokens());
                System.out.println("Completion Tokens: " + usage.getCompletionTokens());
                System.out.println("Total Tokens: " + usage.getTotalTokens());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new PromptChatResponse(
                response.getResult().getOutput().getText(),
                requestConID
        );
    }
}
