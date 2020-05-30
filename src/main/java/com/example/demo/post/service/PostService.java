package com.example.demo.post.service;

import com.example.demo.post.domain.Post;
import com.example.demo.post.dto.CreatePostRequest;
import com.example.demo.post.dto.PostResponse;
import com.example.demo.post.repository.PostRepository;
import com.example.demo.user.domain.User;
import com.example.demo.user.util.SessionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.stream.Collectors;

// TODO: 바로 스니펫, 날짜적기
@Service
public class PostService {

    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public PostResponse create(HttpSession httpSession, CreatePostRequest createPostRequest) {
        User loginUser = SessionUtils.getLoginUser(httpSession);
        Post post = Post.builder()
                .content(createPostRequest.getContent())
                .author(loginUser)
                .build();

        Post saved = postRepository.save(post);
        return getPostResponse(saved);
    }

    public List<PostResponse> getAll() {
        return postRepository.findAll().stream()
                .map(this::getPostResponse)
                .collect(Collectors.toList());
    }

    public PostResponse getById(Long id) {
        return postRepository.findById(id)
                .map(this::getPostResponse)
                .orElseThrow(() -> new IllegalArgumentException("해당 id의 게시물이 없습니다."));
    }

    @Transactional
    public PostResponse update(Long id, String content) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 id의 게시물이 없습니다. id=" + id));
        post.update(content);
        return getPostResponse(post);
    }

    public void delete(Long id) {
        postRepository.deleteById(id);
    }

    @Transactional
    public void likePost(HttpSession httpSession, Long id) {
        User loginUser = (User) httpSession.getAttribute("LOGIN_USER");
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 id의 게시물이 없습니다. id=" + id));
        post.addLike(loginUser);
    }

    private PostResponse getPostResponse(Post post) {
        return PostResponse.builder()
                .id(post.getId())
                .content(post.getContent())
                .build();
    }
}
