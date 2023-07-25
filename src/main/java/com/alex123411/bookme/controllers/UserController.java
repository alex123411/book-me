package com.alex123411.bookme.controllers;

import com.alex123411.bookme.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("api/v1/user")
public class UserController {

    private final UserService userService;

    //If we have one constructor @Autowired is optional annotation.
    @Autowired
    // If we have different implementations of UserService (for example it is an interface)
    // we must qualify the bean we want to use, for example
    // @Qualifier("mysqlUserService")
    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/register")
    public String register(@RequestBody String name) {
        return userService.registerUser(name);
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
