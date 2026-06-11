package com.spring.sample.chatbot.service;


public class RagChatService {
//
//    private final ChatClient chatClient;
//    private final VectorStoreService vectorStoreService;
//    private final ChatMemory chatMemory;
//
//    /**
//     * This method is used when we need to fetch details from a RAG Vector store...
//     *
//     * @param chatRequest
//     * @param conversationId
//     * @return
//     */
//    public PromptChatResponse fetchDetailsFromRag(ChatRequest chatRequest, String conversationId) {
//
//        String requestConID = (conversationId == null || conversationId.isBlank())
//                ? UUID.randomUUID().toString()
//                : conversationId;
//        System.out.println("Conversation Id : " + requestConID);
//        QuestionAnswerAdvisor qaAdvisor =
//                QuestionAnswerAdvisor.builder(vectorStoreService.getVectorStore())
//                        .build();
//        ChatResponse response = chatClient.prompt()
//                .system("Please send the response in Bullet Points and not more than 10 lines")
//                .user(chatRequest.getPromptMessage())
//                .advisors(
//                        qaAdvisor,
//                        MessageChatMemoryAdvisor.builder(chatMemory)
//                                .build()
//                )
//                .advisors(spec ->
//                        spec.param(ChatMemory.CONVERSATION_ID, requestConID)
//                )
//                .call()
//                .chatResponse();
//        Usage usage = response.getMetadata().getUsage();
//        if (usage != null) {
//            System.out.println("Prompt Tokens: " + usage.getPromptTokens());
//            System.out.println("Completion Tokens: " + usage.getCompletionTokens());
//            System.out.println("Total Tokens: " + usage.getTotalTokens());
//        }
//        return new PromptChatResponse(
//                response.getResult().getOutput().getText(),
//                requestConID
//        );
//    }
}
