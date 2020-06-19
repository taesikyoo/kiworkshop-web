package com.example.demo.user.service;

import com.example.demo.user.dto.LoginRequest;
import com.example.demo.user.dto.UserRequest;
import com.example.demo.user.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.servlet.http.HttpSession;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
class UserServiceTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private HttpSession httpSession;

    @AfterEach
    void tearDown() {
        userRepository.deleteAll();
    }

    @Test
    @DisplayName("정상 로그인")
    void loginTest() {
        UserRequest userRequestDto = UserRequest.builder()
                .email("email")
                .password("password")
                .name("name")
                .build();
        Long id = userService.create(userRequestDto).getId();

        LoginRequest loginRequest = LoginRequest.builder()
                .email("email")
                .password("password")
                .build();

        userService.login(httpSession, loginRequest.getEmail(), loginRequest.getPassword());
        assertThat(httpSession.getAttribute("LOGIN_USER")).isNotNull();
    }

    @Test
    @DisplayName("정상 로그아웃")
    void logout() {
        UserRequest userRequestDto = UserRequest.builder()
                .email("email")
                .password("password")
                .name("name")
                .build();
        Long id = userService.create(userRequestDto).getId();

        LoginRequest loginRequest = LoginRequest.builder()
                .email("email")
                .password("password")
                .build();

        userService.login(httpSession, loginRequest.getEmail(), loginRequest.getPassword());
        assertThat(httpSession.getAttribute("LOGIN_USER")).isNotNull();
        userService.logout(httpSession);
        assertThat(httpSession.getAttribute("LOGIN_USER")).isNull();
    }

    @Test
    @DisplayName("이미 로그인 되어있는 상태")
    void alreadyLogined() {
        UserRequest userRequestDto = UserRequest.builder()
                .email("email")
                .password("password")
                .name("name")
                .build();
        Long id = userService.create(userRequestDto).getId();

        LoginRequest loginRequest = LoginRequest.builder()
                .email("email")
                .password("password")
                .build();

        userService.login(httpSession, loginRequest.getEmail(), loginRequest.getPassword());
        assertThatThrownBy(() -> userService.login(httpSession, loginRequest.getEmail(), loginRequest.getPassword()))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("이미 로그인한 사용자입니다.");
    }

    @Test
    @DisplayName("비밀번호가 다를 때 실패")
    void wrongPassword() {
        UserRequest userRequestDto = UserRequest.builder()
                .email("email")
                .password("password")
                .name("name")
                .build();
        Long id = userService.create(userRequestDto).getId();

        LoginRequest loginRequest = LoginRequest.builder()
                .email("email")
                .password("wrong password")
                .build();

        assertThatThrownBy(() -> userService.login(httpSession, loginRequest.getEmail(), loginRequest.getPassword()))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("잘못된 패스워드 입니다.");
    }
}