package com.woojoofolio.project.springboot.config.auth.dto;

import com.woojoofolio.project.springboot.domain.user.Role;
import com.woojoofolio.project.springboot.domain.user.User;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class SessionUser implements Serializable {

    private String name;
    private String email;
    private String picture;
    private boolean authority;

    public SessionUser(User user) {
        this.name = user.getName();
        this.email = user.getEmail();
        this.picture = user.getPicture();
        this.authority = Role.USER.equals(user.getRole());
    }
}
