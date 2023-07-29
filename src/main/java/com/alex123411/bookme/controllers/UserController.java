package com.alex123411.bookme.controllers;

import com.alex123411.bookme.entities.User;
import com.alex123411.bookme.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
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

    @PostMapping
    public User register(@RequestBody User user) {
        return userService.registerUser(user);
    }

    @PostMapping("/login")
    public User logIn(@RequestBody User user) {
        return userService.logIn(user);
    }

    @GetMapping
    public List<User> getAllUser(@RequestParam(required = false) Integer pageNum){
        return userService.getAllUsers(pageNum);
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable long id) {
        return userService.getUserById(id);
    }

    @PutMapping("/{id}")
    public User updateUser(@RequestBody User user, @PathVariable long id){
        System.out.printf(user.toString());
        return userService.updateUser(id, user);
    }
}
