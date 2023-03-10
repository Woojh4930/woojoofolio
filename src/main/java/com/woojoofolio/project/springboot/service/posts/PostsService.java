package com.woojoofolio.project.springboot.service.posts;

import com.woojoofolio.project.springboot.domain.posts.Posts;
import com.woojoofolio.project.springboot.domain.posts.PostsRepository;
import com.woojoofolio.project.springboot.web.dto.posts.PostsListResponseDto;
import com.woojoofolio.project.springboot.web.dto.posts.PostsResponseDto;
import com.woojoofolio.project.springboot.web.dto.posts.PostsSaveRequestDto;
import com.woojoofolio.project.springboot.web.dto.posts.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostsService {

    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 내용이 존재하지 않습니다. id=" + id));

        posts.update(requestDto.getTitle(), requestDto.getContent());

        return id;
    }
    @Transactional
    public Long delete(Long id) {
        postsRepository.deleteById(id);

        return id;
    }

    @Transactional(readOnly = true)
    public PostsResponseDto findById(Long id) {
        Posts entity = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 내용이 존재하지 않습니다. id=" + id));
        return new PostsResponseDto(entity);
    }

    @Transactional(readOnly = true)
    public List<PostsListResponseDto> findAllDesc() {
        return postsRepository.findAllDesc().stream()
                .map(PostsListResponseDto::new)
                .collect(Collectors.toList());
    }

}
