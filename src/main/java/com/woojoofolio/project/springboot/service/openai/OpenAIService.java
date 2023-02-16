package com.woojoofolio.project.springboot.service.openai;

import com.woojoofolio.project.springboot.service.papago.PapagoService;
import com.woojoofolio.project.springboot.web.dto.chatgpt.ChatGptRequest;
import com.woojoofolio.project.springboot.web.dto.chatgpt.ChatGptResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@RequiredArgsConstructor
@Service
public class OpenAIService {

    private static final RestTemplate restTemplate = new RestTemplate();
    private final ChatGptRequest chatGptRequest = new ChatGptRequest();
    private final PapagoService papagoService;

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
            responseEntity = restTemplate.postForEntity(
                    "https://api.openai.com/v1/completions",
                    chatGptRequestHttpEntity,
                    ChatGptResponse.class
            );
        }
        return responseEntity.getBody();
    }

    public String askQuestion(String prompt) {
        this.chatGptRequest.setPrompt(papagoService.askTranslation("ko", "en", prompt).getTranslatedText());
        String text = this.getResponse(this.buildHttpEntity(this.chatGptRequest)).getChoices().get(0).getText();
        return papagoService.askTranslation("en", "ko", text).getTranslatedText();
    }
}
