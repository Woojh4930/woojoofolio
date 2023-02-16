package com.woojoofolio.project.springboot.service.papago;

import com.woojoofolio.project.springboot.web.dto.PapagoRequest;
import com.woojoofolio.project.springboot.web.dto.PapagoResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PapagoService {

    private static RestTemplate restTemplate = new RestTemplate();

    @Value("${spring.security.oauth2.client.registration.naver.client-id}")
    private String XNaverClientId;

    @Value("${spring.security.oauth2.client.registration.naver.client-secret}")
    private String XNaverClientSecret;

    public HttpEntity<PapagoRequest> buildHttpEntity(PapagoRequest papagoRequest) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", "application/x-www-form-urlencoded; charset=UTF-8");
        headers.add("X-Naver-Client-Id", XNaverClientId);
        headers.add("X-Naver-Client-Secret", XNaverClientSecret);
        return new HttpEntity<>(papagoRequest, headers);
    }

    public PapagoResponse getResponse(HttpEntity<PapagoRequest> papagoRequestHttpEntity) {
        ResponseEntity<PapagoResponse> responseEntity = restTemplate.postForEntity(
                "https://openapi.naver.com/v1/papago/n2mt",
                papagoRequestHttpEntity,
                PapagoResponse.class
        );

        return responseEntity.getBody();
    }


    public PapagoResponse askTranslation(String source, String target, String text) {
        PapagoRequest papagoRequest = new PapagoRequest(source, target, text);
        return this.getResponse(this.buildHttpEntity(papagoRequest));
    }
}
