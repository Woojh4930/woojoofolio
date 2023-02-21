package com.woojoofolio.project.springboot.web.dto.papago;

import com.woojoofolio.project.springboot.service.CookieService;
import lombok.Getter;

@Getter
public class PapagoResponse {

    private PapagoType message;
    private final CookieService cookieService = new CookieService();

    public String getTranslatedText() {
        return this.message.getResult().getTranslatedText();
    }
}
