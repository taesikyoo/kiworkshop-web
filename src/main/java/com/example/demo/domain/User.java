package com.example.demo.domain;

import lombok.Getter;

@Getter
public class User {

    private Long id;
    private String name;

    public User() {
    }

    public User(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public static User of(Long id, String name) {
        return new User(id, name);
    }

    public void update(String name) {

    }
}


