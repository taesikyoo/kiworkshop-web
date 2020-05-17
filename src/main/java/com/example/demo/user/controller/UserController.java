package com.example.demo.user.controller;

import com.example.demo.user.dto.CreateUserRequest;
import com.example.demo.user.dto.UserResponse;
import com.example.demo.user.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/users")
    public UserResponse create(@RequestBody CreateUserRequest createUserRequest) {
        return userService.create(createUserRequest);
    }

    @GetMapping("/users")
    public List<UserResponse> getAll() {
        return userService.getAll();
    }

    @GetMapping("/users/{id}")
    public UserResponse get(@PathVariable Long id) {
        return userService.getById(id);
    }

//    @GetMapping("/users/search")
//    public List<UserResponse> search(@RequestParam String keyword,
//                                     @RequestParam(required = false) List<String> excludes) {
//        return userService.search(keyword, excludes);
//    }

    @PutMapping("/users/{id}")
    public UserResponse update(@PathVariable Long id, @RequestParam String name) {
        return userService.update(id, name);
    }

    @DeleteMapping("/users/{id}")
    public void delete(@PathVariable Long id) {
        userService.delete(id);
    }

    @GetMapping("/login")
    public void login(HttpSession httpSession, String email, String password) {
        userService.login(httpSession, email, password);
    }

    @PostConstruct // 생성자가 만들어진후 불려진다
    private void portConstruct() {
        System.out.println("UserController가 잘 만들어졌다!");
    }
}
