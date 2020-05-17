package com.example.demo.post.service;

import com.example.demo.post.domain.Post;
import com.example.demo.post.dto.CreatePostRequest;
import com.example.demo.post.repository.PostRepository;
import com.example.demo.user.SessionUtils;
import com.example.demo.user.domain.User;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

// TODO: 바로 스니펫, 날짜적기
@Service
public class PostService {

    private PostRepository postRepository;

    public PostService(@Qualifier(value = "externalDBPostRepository") PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public Post create(HttpSession httpSession, CreatePostRequest createPostRequest) {
        User loginUser = SessionUtils.getLoginUser(httpSession);
        Post post = Post.builder()
                .content(createPostRequest.getContent())
                .author(loginUser)
                .build();
        postRepository.create(post);
        return post;
    }

    public Post get(Long id) {
        return postRepository.getById(id);
    }

    public Post update() {
        return postRepository.update(null);
    }

    public void delete(Long id) {
        postRepository.delete(id);
    }
}
