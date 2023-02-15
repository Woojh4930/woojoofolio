package com.woojoofolio.project.springboot.service.openai;

import com.woojoofolio.project.springboot.web.dto.ChatGptRequest;
import com.woojoofolio.project.springboot.web.dto.ChatGptResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OpenAIService {

    private static RestTemplate restTemplate = new RestTemplate();
    private final ChatGptRequest chatGptRequest = new ChatGptRequest();

    @Value("${openai.api.key:'none'}")
    private String API_KEY;

    public HttpEntity<ChatGptRequest> buildHttpEntity(ChatGptRequest chatGptRequest) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", "application/json");
        headers.add("Authorization", "Bearer " + API_KEY);
        return new HttpEntity<>(chatGptRequest, headers);
    }

    public ChatGptResponse getResponse(HttpEntity<ChatGptRequest> chatGptRequestHttpEntity) {
        ResponseEntity<ChatGptResponse> responseEntity;
        synchronized (this) {
            System.out.println(chatGptRequestHttpEntity.getBody().getPrompt());
            responseEntity = restTemplate.postForEntity(
                    "https://api.openai.com/v1/completions",
                    chatGptRequestHttpEntity,
                    ChatGptResponse.class
            );
        }
        return responseEntity.getBody();
    }

    public ChatGptResponse askQuestion(String prompt) {
        this.chatGptRequest.setPrompt(prompt);
        return this.getResponse(this.buildHttpEntity(this.chatGptRequest));
    }
}
