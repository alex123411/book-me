package com.alex123411.bookme.services;

import org.springframework.stereotype.Service;

@Service
public class UserService {

    public String registerUser(String name){

        return "registered" + name;
    }
}
