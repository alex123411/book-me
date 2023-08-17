package com.alex123411.bookme.user;

import com.alex123411.bookme.configs.JwtService;
import com.alex123411.bookme.user.User;
import com.alex123411.bookme.exceptions.NotFoundException;
import com.alex123411.bookme.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final JwtService jwtService;

    public Page<User> getAllUsers(Integer pageNum) {
        if (pageNum == null) pageNum = 0;
        int batchSize = 50;

        Pageable page = PageRequest.of(pageNum, batchSize);

        return userRepository.findAll(page);
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

    public User getAuthenticatedUser(String token) {
        String jwt = token.substring(7);
        String userEmail = jwtService.extractUsername(jwt);
        return userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new NotFoundException("Could not find User"));
    }

    // DELETE {id}

}
