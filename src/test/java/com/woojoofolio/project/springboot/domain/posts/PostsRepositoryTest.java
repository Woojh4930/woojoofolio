package com.woojoofolio.project.springboot.domain.posts;

import com.woojoofolio.project.springboot.service.openai.OpenAIService;
import com.woojoofolio.project.springboot.service.papago.PapagoService;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostsRepositoryTest {

    @Autowired
    PostsRepository postsRepository;

    @MockBean
    OpenAIService openAIService;

    @MockBean
    PapagoService papagoService;

    @After
    public void cleanup() {
        postsRepository.deleteAll();
    }

    @Test
    public void 게시글저장_불러오기() {
        String title = "테스트 게시글";
        String content = "테스트 본문";

        postsRepository.save(Posts.builder()
                .title(title)
                .content(content)
                .author("juhong4930@naver.com")
                .build());

        List<Posts> posts = postsRepository.findAll();

        Posts post = posts.get(0);
        assertThat(post.getTitle()).isEqualTo(title);
        assertThat(post.getContent()).isEqualTo(content);
    }

    @Test
    public void BaseTimeEntity_등록() {
        //given
        LocalDateTime now = LocalDateTime.of(2023, 1, 10, 0, 0, 0);
        postsRepository.save(Posts.builder()
                .title("title")
                .content("content")
                .author("author")
                .build());

        //when
        List<Posts> posts = postsRepository.findAll();

        //then
        Posts post = posts.get(0);


        assertThat(post.getCreateDate()).isAfter(now);
        assertThat(post.getModifiedDate()).isAfter(now);
    }
}
