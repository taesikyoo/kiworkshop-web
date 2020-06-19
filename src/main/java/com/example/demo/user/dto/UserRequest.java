package com.example.demo.user.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class UserRequest {

    private String email;
    private String password;
    private String name;

    @Builder
    public UserRequest(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
    }
}
