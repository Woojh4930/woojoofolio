package com.woojoofolio.project.springboot.web.dto;

import lombok.Getter;

@Getter
public class PapagoResponse {

    private PapagoType message;

    public String getTranslatedText() {
        return this.message.getResult().getTranslatedText();
    }
}
