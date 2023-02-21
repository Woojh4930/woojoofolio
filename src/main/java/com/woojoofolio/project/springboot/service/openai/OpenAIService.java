package com.woojoofolio.project.springboot.service.openai;

import com.woojoofolio.project.springboot.service.CookieService;
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequiredArgsConstructor
@Service
public class OpenAIService {

    private static final RestTemplate restTemplate = new RestTemplate();
    private final ChatGptRequest chatGptRequest = new ChatGptRequest();
    private final CookieService cookieService = new CookieService();
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

    public String askQuestion(String prompt, HttpServletRequest request, HttpServletResponse response) {
        String translatedQuestion = papagoService.askTranslation("ko", "en", prompt).getTranslatedText();
        this.chatGptRequest.setPrompt(cookieService.getAllPrompts(translatedQuestion, request, response));
        String text = this.getResponse(this.buildHttpEntity(this.chatGptRequest)).getChoices().get(0).getText();
        cookieService.getAllPrompts(text, request, response);
        return papagoService.askTranslation("en", "ko", text).getTranslatedText();
    }
}
