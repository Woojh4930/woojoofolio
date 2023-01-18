package com.woojoofolio.project.springboot.domain.user;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @After
    public void cleanup() {
        userRepository.deleteAll();
    }

    @Test
    public void 유저정보_불러오기() {
        String name = "name";
        String email = "email";

        userRepository.save(User.builder().name(name).email(email).role(Role.GUEST).build());

        List<User> users = userRepository.findAll();

        User user = users.get(0);
        assertThat(user.getName()).isEqualTo(name);
        assertThat(user.getEmail()).isEqualTo(email);
    }

    @Test
    public void BaseTimeEntity_등록() {
        LocalDateTime now = LocalDateTime.of(2023, 1, 17, 0, 0, 0);
        userRepository.save(User.builder()
                .name("name")
                .email("email")
                .role(Role.GUEST)
                .build());

        List<User> users = userRepository.findAll();

        User user = users.get(0);

        System.out.println(">>>>>>>>>>>>>> createDate=" + user.getCreateDate() + ", modifiedDate=" + user.getModifiedDate());

        assertThat(user.getCreateDate()).isAfter(now);
        assertThat(user.getModifiedDate()).isAfter(now);
    }
}
