package com.woojoofolio.project.springboot.web.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PapagoRequest {
    private String source = "ko";
    private String target = "en";
    private String text;

    public void setText(String text) {
        this.text = text;
    }
}
