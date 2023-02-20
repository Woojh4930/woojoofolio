package com.woojoofolio.project.springboot.service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.Map;

public class CookieService {

    public String setCookie(HttpServletResponse response) {
        String prompt_key = RandomManager.getRandomKey();
        Cookie cookie = new Cookie(prompt_key, "");
        cookie.setPath("/api/v1/openai/send");
        cookie.setMaxAge(60 * 60 * 24);
        response.addCookie(cookie);
        return prompt_key;
    }

    public String getAllPrompts(Map<String, String> prompts, HttpServletRequest request, HttpServletResponse response) {
        Cookie cookie = setCookiePrompt(prompts, request);
        updateCookie(cookie, response);
        return cookie.getValue();
    }

    private Cookie getCookie(String prompt_key, HttpServletRequest request) {
        return Arrays.stream(request.getCookies())
                .filter(cookie -> prompt_key.equals(cookie.getName()))
                .findFirst().orElseThrow(() -> new IllegalArgumentException("잘못된 정보입니다."));
    }

    private Cookie setCookiePrompt(Map<String, String> prompts, HttpServletRequest request) {
        Cookie cookie = getCookie(prompts.get("prompt_key"), request);
        cookie.setMaxAge(0);
        System.out.println(cookie.getValue() + "\\n" + prompts.get("prompt"));
        try {
            Cookie updateCookie = new Cookie(prompts.get("prompt_key"), cookie.getValue() + URLEncoder.encode( "\\n" + prompts.get("prompt"),"UTF-8"));
            updateCookie.setPath("/api/v1/openai/send");
            updateCookie.setMaxAge(60 * 60 * 24);
            return updateCookie;
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    private void updateCookie(Cookie cookie, HttpServletResponse response) {
        response.addCookie(cookie);
    }
}
