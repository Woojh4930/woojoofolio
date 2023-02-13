package com.woojoofolio.project.springboot.web;

import com.woojoofolio.project.springboot.service.openai.OpenAIService;
import com.woojoofolio.project.springboot.web.dto.ChatGptResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api/v1/openai")
@RestController
public class OpenAIApiController {

    private final OpenAIService openAIService;

    @PostMapping("/send")
    public ChatGptResponse send(@RequestBody String prompt) {
        return openAIService.askQuestion(prompt);
    }

}
