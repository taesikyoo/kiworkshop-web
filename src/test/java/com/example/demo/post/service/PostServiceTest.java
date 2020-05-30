package com.example.demo.post.service;

import com.example.demo.post.domain.Post;
import com.example.demo.post.repository.PostRepository;
import com.example.demo.user.domain.User;
import com.example.demo.user.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PostServiceTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @Test
    void postCreatTest() {
        User user = userRepository.save(User.builder()
                .name("yoo")
                .email("yooooo@gmail.com")
                .password("password")
                .build()
        );

        Post post = postRepository.save(Post.builder()
                .content("a")
                .author(user)
                .build()
        );
    }
}