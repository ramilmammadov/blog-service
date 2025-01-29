package com.blogplatform.blog_service.controller.auth;


import com.blogplatform.blog_service.dto.request.auth.LoginRequest;
import com.blogplatform.blog_service.dto.request.auth.RegisterRequest;
import com.blogplatform.blog_service.dto.response.auth.AuthResponse;
import com.blogplatform.blog_service.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }
}
