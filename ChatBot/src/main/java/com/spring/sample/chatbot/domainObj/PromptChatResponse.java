package com.spring.sample.chatbot.domainObj;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
public class PromptChatResponse {
    String chatResponse;
    String conversationID;
}
