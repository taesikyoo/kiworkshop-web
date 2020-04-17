package com.example.demo.controller;

public class CreateUserRequest {

    private String name;

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "CreateUserRequest{" +
                "name='" + name + '\'' +
                '}';
    }
}
