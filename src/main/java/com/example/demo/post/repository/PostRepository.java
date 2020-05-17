package com.example.demo.post.repository;

import com.example.demo.post.Post;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository {

    Post create(Post post);

    Post getById(Long id);

    Post update(Post post);

    boolean delete(Long id);
}
