package com.woojoofolio.project.springboot.web.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class ChatGptRequest {

    private final String model = "text-davinci-003";
    private String prompt = "The following is a conversation with an AI assistant. The assistant is helpful, creative, clever, and very friendly.\\n\\nHuman: Hello, who are you?\\nAI: I am an AI created by OpenAI. How can I help you today?\\nHuman: ";
    private final Integer max_tokens = 1024;
    private final double temperature = 0.9;
    private final double presence_penalty = 0.6;
    private final List<String> stop = List.of(" Human:", " AI:");

    public void setPrompt(String prompt) {
        this.prompt = "The following is a conversation with an AI assistant. The assistant is helpful, creative, clever, and very friendly.\\n\\nHuman: Hello, who are you?\\nAI: I am an AI created by OpenAI. How can I help you today?\\nHuman: " + prompt;
    }
}
