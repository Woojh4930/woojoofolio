package com.woojoofolio.project.springboot.web.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ChatGptRequest {

    private final String model = "text-davinci-003";
    private String prompt;
    private final Integer max_tokens = 1024;
    private final double temperature = 0.7;

    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }
}
