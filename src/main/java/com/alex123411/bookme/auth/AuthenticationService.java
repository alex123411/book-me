package com.alex123411.bookme.auth;

import com.alex123411.bookme.configs.JwtService;
import com.alex123411.bookme.entities.Role;
import com.alex123411.bookme.entities.User;
import com.alex123411.bookme.exceptions.BadRequestException;
import com.alex123411.bookme.exceptions.NotFoundException;
import com.alex123411.bookme.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) {
        validateRegisterRequest(request);
        User user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();
        userRepository.save(user);
        String jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new NotFoundException("Email or Password are incorrect."));
        String jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    private void validateRegisterRequest(RegisterRequest request) {
        if (request.getEmail() == null || request.getPassword() == null)
            throw new BadRequestException("Email or Password are empty, cannot register a user.");

        if (request.getPassword().length() < 12)
            throw new BadRequestException("Password is invalid. Make sure it is at least 14 characters.");

        if (!EmailValidator.getInstance().isValid(request.getEmail()))
            throw new BadRequestException("Email is invalid.");

        userRepository.findByEmail(request.getEmail())
                .ifPresent((val) -> {
                    throw new BadRequestException("User with such Email already exists.");
                });

    }
}
