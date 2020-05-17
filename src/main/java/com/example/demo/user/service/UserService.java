package com.example.demo.user.service;

import com.example.demo.user.PasswordEncryptor;
import com.example.demo.user.domain.User;
import com.example.demo.user.dto.CreateUserRequest;
import com.example.demo.user.dto.UserResponse;
import com.example.demo.user.exception.AuthenticationException;
import com.example.demo.user.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserResponse create(CreateUserRequest createUserRequest) {
        User user = User.builder()
                .email(createUserRequest.getEmail())
                .password(PasswordEncryptor.encrypt(createUserRequest.getPassword()))
                .name(createUserRequest.getName())
                .build();

        User saved = userRepository.save(user);
        return getUserResponse(saved);
    }

    public List<UserResponse> getAll() {
        return userRepository.findAll().stream()
                .map(this::getUserResponse)
                .collect(Collectors.toList());
    }

    public UserResponse getById(Long id) {
        return userRepository.findById(id)
                .map(this::getUserResponse)
                .orElseThrow(() -> new IllegalArgumentException("해당 id의 사용자가 없습니다."));
    }

//    public List<UserResponse> search(String keyword, List<String> excludes) {
//        if (CollectionUtils.isEmpty(excludes)) {
//            return users.values().stream()
//                    .filter(user -> user.getName().startsWith(keyword))
//                    .map(this::getUserResponse)
//                    .collect(Collectors.toList());
//        }
//
//        return users.values().stream()
//                .filter(user -> user.getName().startsWith(keyword))
//                .filter(user -> !excludes.contains(user.getName()))
//                .map(this::getUserResponse)
//                .collect(Collectors.toList());
//    }

    @Transactional
    public UserResponse update(Long id, String name) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 유저가 없습니다. id=" + id));
        user.update(name);
        return getUserResponse(user);
    }

    public void delete(Long id) {
        userRepository.deleteById(id);
    }


    private User getByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 email입니다."));
    }

    /**
     * 1. 로그인 이전 : 해당 세션ID로 아무런 Attribute 세팅 X
     * 2. 로그인 시도 후 성공 : 해당 세션 ID로 Attrubute 세팅
     * 3. 로그인 이후에는 어디서든지 세션에서 "LOGIN_USER" Attribute를 꺼내서 쓸 수 있다.
     */

    // TODO: 로그인한 사용자가 다시 로그인을 시도할 때 처리
    public void login(HttpSession httpSession, String email, String password) {
        Object loginUserAttr = httpSession.getAttribute("LOGIN_USER");
        if (loginUserAttr == null) {
            System.out.println("로그인을 아직 안했다.");
        } else {
            User loginUser = (User) loginUserAttr;
            throw new AuthenticationException("이미 로그인한 사용자입니다.");
        }

        String encryptedPasswordQuery = PasswordEncryptor.encrypt(password);
        User user = getByEmail(email);
        if (!user.matchPassword(encryptedPasswordQuery)) {
            throw new AuthenticationException("로그인에 실패했습니다.");
        }
        httpSession.setAttribute("LOGIN_USER", user);
        System.out.println("로그인에 성공했습니다.");
    }
//
//    public void logout(HttpSession httpSession) {
//        httpSession.invalidate();
//    }

    private UserResponse getUserResponse(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .email(user.getEmail())
                .name(user.getName())
                .build();
    }

    @PostConstruct // 생성자가 만들어진 후 불려진다
    private void portConstruct() {
        System.out.println("UserService가 잘 만들어졌다!");
    }
}
