package com.example.demo.user.util;

import com.example.demo.user.domain.User;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.servlet.http.HttpSession;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SessionUtils {

    public static final String LOGIN_USER_SESSION_KEY = "LOGIN_USER";

    public static User getLoginUser(HttpSession httpSession) {
        Object maybeLoginUser = httpSession.getAttribute(LOGIN_USER_SESSION_KEY);
        if (maybeLoginUser == null) {
            throw new IllegalArgumentException("로그인이 필요합니다.");
        }
        return (User) maybeLoginUser;
    }
}
