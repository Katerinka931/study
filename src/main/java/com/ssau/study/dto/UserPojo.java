package com.ssau.study.dto;

import com.ssau.study.entity.User;
import com.ssau.study.entity.enums.UserRole;
import lombok.*;

@Getter
@Setter
public class UserPojo {
    private Long id;
    private String username;
    private UserRole role;
    private String password;

    public static UserPojo fromEntity(User user) {
        UserPojo pojo = new UserPojo();
        pojo.setId(user.getId());
        pojo.setUsername(user.getUsername());
        pojo.setRole(user.getRole());
        return pojo;
    }

    public static User toEntity(UserPojo pojo) {
        User user = new User();
        user.setUsername(pojo.getUsername());
        user.setRole(pojo.getRole());
        return user;
    }
}
