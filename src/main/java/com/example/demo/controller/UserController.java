package com.example.demo.controller;

import com.example.demo.domain.User;
import com.example.demo.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;

@RestController
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public User get() {
        return userService.read();
    }

    @PostMapping("/users")
    public String post() {
        return "post";
    }

    @PutMapping("/users")
    public String put() {
        return "put";
    }

    @DeleteMapping("/users")
    public String delete() {
        return "delete";
    }

    @PostConstruct // 생성자가 만들어진후 불려진다
    private void portConstruct() {
        System.out.println("UserController가 잘 만들어졌다!");
    }
}
