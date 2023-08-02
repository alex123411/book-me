package com.alex123411.bookme.services;

import com.alex123411.bookme.entities.User;
import com.alex123411.bookme.exceptions.BadRequestException;
import com.alex123411.bookme.exceptions.NotFoundException;
import com.alex123411.bookme.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.apache.commons.validator.routines.EmailValidator;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User registerUser(User user) {
        if (user.getEmail() == null || user.getPassword() == null)
            throw new BadRequestException("Email or Password are empty, cannot register a user.");

        if(user.getPassword().length() < 8)
            throw new BadRequestException("Password is invalid. Make sure it is at least 14 characters.");

        if(!EmailValidator.getInstance().isValid(user.getEmail()))
            throw new BadRequestException("Email is invalid.");

        userRepository.findByEmail(user.getEmail())
                .ifPresent((val) -> {
                    throw new BadRequestException("User with such Email already exists.");
                });

        return userRepository.save(user);
    }

    public User logIn(User user) {
        // Returning JWT token in future

        return userRepository.findByEmailAndPassword(user.getEmail(), user.getPassword())
                .orElseThrow(() -> new NotFoundException("Email or Password are incorrect."));
    }

    public List<User> getAllUsers(Integer pageNum){
        if(pageNum == null) pageNum = 0;
        int batchSize = 50;

        Pageable page = PageRequest.of(pageNum, batchSize);

        return userRepository.findAll(page).getContent();
    }

    public User getUserById(UUID id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Could not find User with id - " + id));
    }

    // PUT
    public User updateUser(UUID id, User updatedUser) {
        // Need JWT to validate if user changes his profile only using email

        User user = getUserById(id);

        updatedUser.setId(user.getId());
        updatedUser.setEmail(user.getEmail());
        updatedUser.setPassword(user.getPassword());

        return userRepository.save(updatedUser);
    }

    // DELETE {id}

}
