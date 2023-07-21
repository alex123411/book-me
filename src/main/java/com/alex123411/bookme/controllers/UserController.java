package com.alex123411.bookme.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("api/v1/user")
public class UserController {

    @PostMapping("/register")
    public String register() {

        return "registered";
    }

    @PostMapping("/login")
    public String logIn() {

        return "logged in";
    }

    @GetMapping("/")
    public String getUser() {

        return "user";
    }
}
