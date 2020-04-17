package com.example.demo.service;

import com.example.demo.controller.CreateUserRequest;
import com.example.demo.domain.User;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class UserService {

    private Map<Long, User> users = new HashMap<>();
    private Long nextId = 0L;

    public User create(CreateUserRequest createUserRequest) {
        User user = User.of(nextId, createUserRequest.getName());
        users.put(user.getId(), user);
        nextId++;
        return user;
    }

    public User read(Long id) {
        if (users.containsKey(id)) {
            return users.get(id);
        }
        throw new IllegalArgumentException("해당 id의 사용자가 없습니다.");
    }

    public List<User> search(String keyword, List<String> excludes) {
        if (CollectionUtils.isEmpty(excludes)) {
            return users.values().stream()
                    .filter(user -> user.getName().startsWith(keyword))
                    .collect(Collectors.toList());
        }

        return users.values().stream()
                .filter(user -> user.getName().startsWith(keyword))
                .filter(user -> !excludes.contains(user.getName()))
                .collect(Collectors.toList());
    }

    public User update(Long id, String name) {
        User user = read(id);
        user.update(name);
        return user;
    }

    public void delete(Long id) {
        users.remove(id);
    }

    @PostConstruct // 생성자가 만들어진 후 불려진다
    private void portConstruct() {
        System.out.println("UserService가 잘 만들어졌다!");
    }
}
