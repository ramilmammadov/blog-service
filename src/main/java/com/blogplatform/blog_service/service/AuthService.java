package com.blogplatform.blog_service.service;


import com.blogplatform.blog_service.dto.request.auth.LoginRequest;
import com.blogplatform.blog_service.dto.request.auth.RegisterRequest;
import com.blogplatform.blog_service.dto.response.auth.AuthResponse;

public interface AuthService {
    AuthResponse register(RegisterRequest request);
    AuthResponse login(LoginRequest request);
}
