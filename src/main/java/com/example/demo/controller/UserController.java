package com.example.demo.controller;

import com.example.demo.domain.User;
import com.example.demo.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;

@RestController
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users/{id}")
    public User get(@PathVariable Long id) {
        return userService.read(id);
    }

    @GetMapping("/users")
    public List<User> search(@RequestParam String keyword,
                             @RequestParam(required = false) List<String> excludes) {
        return userService.search(keyword, excludes);
    }

    @PostMapping("/users")
    public User post(@RequestBody CreateUserRequest createUserRequest) {
        return userService.create(createUserRequest);
    }

    @PutMapping("/users")
    public User put() {
        return userService.update(0L, "nameUpdated");
    }

    @DeleteMapping("/users")
    public void delete() {
        userService.delete(0L);
    }

    @PostConstruct // 생성자가 만들어진후 불려진다
    private void portConstruct() {
        System.out.println("UserController가 잘 만들어졌다!");
    }
}
