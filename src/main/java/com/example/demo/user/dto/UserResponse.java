package com.example.demo.user.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class UserResponse {

    private Long id;

    private String email;
    private String password;
    private String name;

    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private LocalDateTime nameModifiedAt;

    @Builder
    public UserResponse(Long id, String email, String password, String name) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
    }
}
