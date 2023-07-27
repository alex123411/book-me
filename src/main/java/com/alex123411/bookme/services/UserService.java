package com.alex123411.bookme.services;

import com.alex123411.bookme.entities.User;
import com.alex123411.bookme.exceptions.BadRequestException;
import com.alex123411.bookme.exceptions.NotFoundException;
import com.alex123411.bookme.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.commons.validator.routines.EmailValidator;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User registerUser(User user) {
        if (user.getEmail() == null || user.getPassword() == null)
            throw new BadRequestException("Email or Password are Empty, cannot register a user.");

        if(!EmailValidator.getInstance().isValid(user.getEmail()))
            throw new BadRequestException("Email is invalid.");

        userRepository.findByEmail(user.getEmail())
                .ifPresent((val) -> {
                    throw new BadRequestException("User with Email: " + user.getEmail() + " Already Exists.");
                });

        return userRepository.save(user);
    }

    public User getUserById(long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User by id: " + id + " Not Found"));
    }
}
