package com.example.demo.service;

import com.example.demo.domain.User;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserService {

    private Map<Long, User> users = new HashMap<>();
    private Long nextId = 0L;

    public User create(String name) {
        User user = User.of(nextId, name);
        users.put(user.getId(), user);
        nextId++;
        return user;
    }

    public User read() {
        return new User();
    }

    public User update() {
        return new User();
    }

    public User delete() {
        return new User();
    }

    @PostConstruct // 생성자가 만들어진후 불려진다
    private void portConstruct() {
        System.out.println("UserService가 잘 만들어졌다!");
    }
}
