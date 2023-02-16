package com.woojoofolio.project.springboot.web.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PapagoRequest {
    private String source;
    private String target;
    private String text;

    public PapagoRequest(String source, String target, String text) {
        this.source = source;
        this.target = target;
        this.text = text;
    }
}
