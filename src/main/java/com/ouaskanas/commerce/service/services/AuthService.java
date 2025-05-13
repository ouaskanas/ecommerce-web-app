package com.ouaskanas.commerce.service.services;

import com.ouaskanas.commerce.dto.request.LoginDto;
import com.ouaskanas.commerce.dto.request.RegisterDto;
import com.ouaskanas.commerce.dto.response.AuthResponse;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Service;

@Service
public interface AuthService {
    public AuthResponse Register(HttpServletResponse response, RegisterDto registerDto);
    public AuthResponse Login(HttpServletResponse response, LoginDto loginDto);
    public AuthResponse Admin(HttpServletResponse response, RegisterDto loginDto);
    public void Logout(HttpServletResponse response);
}
