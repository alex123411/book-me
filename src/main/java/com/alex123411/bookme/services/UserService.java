package com.alex123411.bookme.services;

import com.alex123411.bookme.entities.User;
import com.alex123411.bookme.exceptions.NotFoundException;
import com.alex123411.bookme.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String registerUser(User user) {
        return "registered with id:" + userRepository.save(user);
    }

    public User getUserById(long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User by id: " + id + "Not Found"));
    }
}
