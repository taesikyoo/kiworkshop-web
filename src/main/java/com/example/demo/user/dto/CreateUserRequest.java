package com.example.demo.user.dto;

import lombok.Getter;

@Getter
public class CreateUserRequest {

    private String email;
    private String password;
    private String name;
}
