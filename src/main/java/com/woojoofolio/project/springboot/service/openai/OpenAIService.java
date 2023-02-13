package com.woojoofolio.project.springboot.service.openai;

import com.woojoofolio.project.springboot.web.dto.ChatGptRequest;
import com.woojoofolio.project.springboot.web.dto.ChatGptResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OpenAIService {

    private static RestTemplate restTemplate = new RestTemplate();

    @Value("${openai.api.key:'none'}")
    private String API_KEY;

    public HttpEntity<ChatGptRequest> buildHttpEntity(ChatGptRequest chatGptRequest) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", "application/json");
        headers.add("Authorization", "Bearer " + "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");
        return new HttpEntity<>(chatGptRequest, headers);
    }

    public ChatGptResponse getResponse(HttpEntity<ChatGptRequest> chatGptRequestHttpEntity) {
        ResponseEntity<ChatGptResponse> responseEntity = restTemplate.postForEntity(
                "https://api.openai.com/v1/completions",
                chatGptRequestHttpEntity,
                ChatGptResponse.class
        );

        return responseEntity.getBody();
    }

    public ChatGptResponse askQuestion(String prompt) {
        ChatGptRequest chatGptRequest = new ChatGptRequest();
        chatGptRequest.setPrompt(prompt);
        return this.getResponse(this.buildHttpEntity(chatGptRequest));
    }
}
