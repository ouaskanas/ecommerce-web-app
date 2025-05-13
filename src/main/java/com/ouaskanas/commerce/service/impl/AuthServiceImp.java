package com.ouaskanas.commerce.service.impl;

import com.ouaskanas.commerce.config.CookiesConfig;
import com.ouaskanas.commerce.dto.request.LoginDto;
import com.ouaskanas.commerce.dto.request.RegisterDto;
import com.ouaskanas.commerce.dto.response.AuthResponse;
import com.ouaskanas.commerce.model.User;
import com.ouaskanas.commerce.model.enums.Role;
import com.ouaskanas.commerce.repository.UserRepository;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jose.jws.SignatureAlgorithm;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Service
public class AuthServiceImp implements com.ouaskanas.commerce.service.services.AuthService {

    @Autowired
    private CookiesConfig cookiesConfig;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JwtEncoder jwtEncoder;

    public String GenerateToken(User user){
        JwtClaimsSet claims = JwtClaimsSet.builder().subject(user.getEmail()).issuedAt(Instant.now())
                                                    .claim("role", user.getRole().name())
                                                    .expiresAt(Instant.now().plus(15, ChronoUnit.DAYS))
                                                    .build();
        JwsHeader jwsHeader = JwsHeader.with(SignatureAlgorithm.RS256).build();
        return jwtEncoder.encode(JwtEncoderParameters.from(jwsHeader,claims)).getTokenValue();
    }

    @Override
    public AuthResponse Register(HttpServletResponse response, RegisterDto registerDto) {
        if (userRepository.existsByEmail(registerDto.getEmail())) {
            throw new RuntimeException("Email already exists");
        }
        if(registerDto.getPassword()==registerDto.getConfirmPassword()){
            throw new RuntimeException("Passwords do not match");
        }
        User user = User.builder()
                    .email(registerDto.getEmail()).age(registerDto.getAge())
                    .city(registerDto.getCity()).address(registerDto.getAddress()).password(passwordEncoder.encode(registerDto.getPassword()))
                    .role(Role.USER).firstName(registerDto.getFirstName()).lastName(registerDto.getLastName()).build();
        userRepository.save(user);
        var jwtToken = GenerateToken(user);
        cookiesConfig.CreateCookies(response, jwtToken);
        return AuthResponse.builder().message("Register successfully!").email(user.getEmail()).build();
    }

    @Override
    public AuthResponse Login(HttpServletResponse response, LoginDto loginDto) {
        if (!userRepository.existsByEmail(loginDto.getEmail())){
            throw new RuntimeException("Email not exists");
        }
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword()));
        var user = userRepository.findByEmail(loginDto.getEmail()).orElseThrow(()->new RuntimeException("User not found"));
        var token = GenerateToken(user);
        cookiesConfig.CreateCookies(response, token);
        return AuthResponse.builder().message("Login successfully! welcome back "+user.getFirstName()).email(user.getEmail()).build();
    }

    @Override
    public AuthResponse Admin(HttpServletResponse response, RegisterDto registerDto) {
        if (userRepository.existsByEmail(registerDto.getEmail())){
            throw new RuntimeException("Email already exists");
        }
        if(registerDto.getPassword().equals(registerDto.getConfirmPassword())){
            throw new RuntimeException("Passwords do not match");
        }
        User user = User.builder()
                .email(registerDto.getEmail()).age(registerDto.getAge())
                .city(registerDto.getCity()).address(registerDto.getAddress()).password(passwordEncoder.encode(registerDto.getPassword()))
                .role(Role.ADMIN).firstName(registerDto.getFirstName()).lastName(registerDto.getLastName()).build();
        userRepository.save(user);
        var token =  GenerateToken(user);
        cookiesConfig.CreateCookies(response, token);
        return AuthResponse.builder().message("Welcome back Admin "+user.getFirstName()).email(user.getEmail()).build();
    }

    @Override
    public void Logout(HttpServletResponse response){
        cookiesConfig.ClearCookies(response);
    }
}
