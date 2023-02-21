package com.woojoofolio.project.springboot.web;

import com.woojoofolio.project.springboot.service.posts.PostsService;
import com.woojoofolio.project.springboot.web.dto.posts.PostsListResponseDto;
import com.woojoofolio.project.springboot.web.dto.posts.PostsResponseDto;
import com.woojoofolio.project.springboot.web.dto.posts.PostsSaveRequestDto;
import com.woojoofolio.project.springboot.web.dto.posts.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/v1/posts")
@RestController
public class PostsApiController {

    private final PostsService postsService;

    @PostMapping
    public Long save(@RequestBody PostsSaveRequestDto requestDto) {
        return postsService.save(requestDto);
    }

    @PutMapping("/{id}")
    public Long update(@PathVariable Long id, @RequestBody PostsUpdateRequestDto requestDto) {
        return postsService.update(id, requestDto);
    }

    @DeleteMapping("/{id}")
    public Long delete(@PathVariable Long id) {
        return postsService.delete(id);
    }

    @GetMapping("/{id}")
    public PostsResponseDto findById(@PathVariable Long id) {
        return postsService.findById(id);
    }

    @GetMapping("/list")
    public List<PostsListResponseDto> findAll() {
        return postsService.findAllDesc();
    }
}
