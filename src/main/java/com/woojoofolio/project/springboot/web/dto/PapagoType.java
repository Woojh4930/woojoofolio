package com.woojoofolio.project.springboot.web.dto;

import lombok.Getter;

@Getter
public class PapagoType {

    private String type;
    private String service;
    private String version;
    private PapagoResult result;
}
