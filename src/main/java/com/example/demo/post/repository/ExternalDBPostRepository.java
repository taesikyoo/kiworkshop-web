package com.example.demo.post.repository;

import com.example.demo.post.Post;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;

@Repository
public class ExternalDBPostRepository implements PostRepository {

    @PostConstruct
    private void  postConstruct() {
        System.out.println("ExternalDBPostRepository가 생성되었습니다.");
    }

    @Override
    public Post create(Post post) {
        return null;
    }

    @Override
    public Post getById(Long id) {
        return null;
    }

    @Override
    public Post update(Post post) {
        return null;
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }
}
