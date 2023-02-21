package com.woojoofolio.project.springboot.web;

import com.woojoofolio.project.springboot.service.openai.OpenAIService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequiredArgsConstructor
@RequestMapping("/api/v1/openai")
@RestController
public class OpenAIApiController {

    private final OpenAIService openAIService;

    @PostMapping("/send")
    public String send(@RequestBody String prompt, HttpServletRequest request, HttpServletResponse response) {
        return openAIService.askQuestion(prompt, request, response);
    }

}
