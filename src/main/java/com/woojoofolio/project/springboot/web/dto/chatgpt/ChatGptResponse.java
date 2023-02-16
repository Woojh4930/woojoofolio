package com.woojoofolio.project.springboot.web.dto.chatgpt;

import lombok.Getter;

import java.util.List;

@Getter
public class ChatGptResponse {
    private List<Choice> choices;
}
