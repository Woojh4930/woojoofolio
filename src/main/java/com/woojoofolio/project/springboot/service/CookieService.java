package com.woojoofolio.project.springboot.service;

import javax.servlet.http.Cookie;

public class CookieService {

    private static final String PROMPT_KEY = "prompt_key";
    public Cookie setCookie(String value) {
        return new Cookie(PROMPT_KEY,value);
    }
}
