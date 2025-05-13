package com.ouaskanas.commerce.controller;

import com.ouaskanas.commerce.dto.request.LoginDto;
import com.ouaskanas.commerce.dto.request.RegisterDto;
import com.ouaskanas.commerce.dto.response.AuthResponse;
import com.ouaskanas.commerce.service.impl.AuthService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> Login(@RequestBody LoginDto loginDto, HttpServletResponse response){
        var auth = authService.Login(response, loginDto);
        return ResponseEntity.ok(auth);
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterDto registerDto, HttpServletResponse response){
        var auth = authService.Register(response, registerDto);
        return ResponseEntity.ok(auth);
    }

    @PostMapping("/admin")
    public ResponseEntity<AuthResponse> admin(@RequestBody RegisterDto registerDto, HttpServletResponse response){
        var auth = authService.Admin(response, registerDto);
        return ResponseEntity.ok(auth);
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpServletResponse response){
        authService.Logout(response);
        return ResponseEntity.ok("Logout Success");
    }
}
