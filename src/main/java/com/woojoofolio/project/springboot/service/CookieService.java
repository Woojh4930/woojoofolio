package com.woojoofolio.project.springboot.service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class CookieService {
    private final String prompt_key = "allPrompts";

    public String setCookie(HttpServletResponse response) {
        Cookie cookie = new Cookie(prompt_key, "");
        cookie.setPath("/api/v1/openai/send");
        response.addCookie(cookie);
        return prompt_key;
    }

    public String getAllPrompts(String prompt, HttpServletRequest request, HttpServletResponse response) {
        Cookie cookie = setCookiePrompt(prompt, request);
        updateCookie(cookie, response);
        return URLDecoder.decode(cookie.getValue(), StandardCharsets.UTF_8);
    }

    private Cookie getCookie(HttpServletRequest request) {
        return Arrays.stream(request.getCookies())
                .filter(cookie -> prompt_key.equals(cookie.getName()))
                .findFirst().orElseThrow(() -> new IllegalArgumentException("잘못된 정보입니다."));
    }

    private Cookie setCookiePrompt(String prompt, HttpServletRequest request) {
        Cookie cookie = getCookie(request);
        Cookie updatedCookie = new Cookie(prompt_key, cookie.getValue() + URLEncoder.encode(prompt + "\\n", StandardCharsets.UTF_8));
        updatedCookie.setPath("/api/v1/openai/send");
        return updatedCookie;
    }

    private void updateCookie(Cookie cookie, HttpServletResponse response) {
        response.addCookie(cookie);
    }
}
