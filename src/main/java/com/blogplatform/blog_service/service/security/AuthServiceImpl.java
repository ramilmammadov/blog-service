package com.blogplatform.blog_service.service.security;

import com.blogplatform.blog_service.dto.request.auth.LoginRequest;
import com.blogplatform.blog_service.dto.request.auth.RegisterRequest;
import com.blogplatform.blog_service.dto.response.auth.AuthResponse;
import com.blogplatform.blog_service.entity.User;
import com.blogplatform.blog_service.repository.UserRepository;
import com.blogplatform.blog_service.service.AuthService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public AuthResponse register(RegisterRequest request) {
        Optional<User> existingUser = userRepository.findByUsername(request.getUsername());
        if (existingUser.isPresent()) {
            return new AuthResponse("User already exists", null);
        }

        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        userRepository.save(user);

        return new AuthResponse("User registered successfully", null);
    }

    @Override
    public AuthResponse login(LoginRequest request) {
        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            return new AuthResponse("Invalid credentials", null);
        }

        String token = "mock-jwt-token";
        return new AuthResponse("Login successful", token);
    }
}
