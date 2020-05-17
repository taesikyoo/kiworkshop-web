package com.example.demo.user.advice;

import com.example.demo.user.exception.AuthenticationException;
import com.example.demo.user.exception.PasswordNotMatchedException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class UserControllerAdvice {

    @ExceptionHandler(AuthenticationException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public void authenticationException(AuthenticationException e) {
        System.err.println(e.getMessage());
    }

    @ExceptionHandler(PasswordNotMatchedException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public void authenticationException(PasswordNotMatchedException e) {
        System.err.println("패스워드 에러!!");
    }
}
