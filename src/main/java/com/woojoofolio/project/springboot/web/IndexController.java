package com.woojoofolio.project.springboot.web;

import com.woojoofolio.project.springboot.config.auth.LoginUser;
import com.woojoofolio.project.springboot.config.auth.dto.SessionUser;
import com.woojoofolio.project.springboot.service.posts.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;

@RequiredArgsConstructor
@Controller
public class IndexController extends HttpServlet {

    private final PostsService postsService;

    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user) {
        model.addAttribute("posts", postsService.findAllDesc());
        if (user != null) {
            model.addAttribute("name", user.getName());
            model.addAttribute("authority", user.isAuthority());
        }
        return "index";
    }

    @GetMapping("/posts/save")
    public String postsSave() {
        return "posts-save";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model) {
        model.addAttribute("post", postsService.findById(id));
        return "posts-update";
    }

    @GetMapping("/posts/read/{id}")
    public String postsRead(@PathVariable Long id, Model model) {
        model.addAttribute("post", postsService.findById(id));
        return "posts-read";
    }

    @GetMapping("/skills/read")
    public String skillsRead() {
        return "skills-read";
    }

    @GetMapping("/openai")
    public String openAi(HttpServletResponse response) {
        /*쿠키에 저장하는 함수*/
        Cookie cookie = new Cookie("prompt_key", "0");
        cookie.setPath("/api/v1/openai/send");
        cookie.setMaxAge(60 * 60 * 24);
        response.addCookie(cookie);
        return "openai";
    }
}
