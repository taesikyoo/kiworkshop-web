package com.example.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

@RestController
public class AController {

    @RequestMapping(value = "/a", method = RequestMethod.GET)
    public String getAllUsers() {
        return "a 컨트롤러";
    }
}
