package com.pm.authservice.service;

import com.pm.authservice.dto.LoginRequestDTO;
import com.pm.authservice.exception.TokenValidationException;
import com.pm.authservice.service.api.AuthService;
import com.pm.authservice.service.api.AuthTokenService;
import com.pm.authservice.service.api.UserService;
import io.jsonwebtoken.JwtException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final AuthTokenService authTokenService;

    public AuthServiceImpl(
            UserService userService,
            PasswordEncoder passwordEncoder,
            AuthTokenService authTokenService
    ) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.authTokenService = authTokenService;
    }

    @Override
    public Optional<String> authenticate(LoginRequestDTO loginRequestDTO) {
        return userService.findByEmail(loginRequestDTO.getEmail())
                .filter(item -> passwordEncoder.matches(loginRequestDTO.getPassword(), item.getPassword()))
                .map(item -> authTokenService.generateToken(item.getEmail(), item.getRole()));
    }

    @Override
    public boolean validateToken(String token) {
        try {
            authTokenService.validateToken(token);
            return true;
        } catch (TokenValidationException exception) {
            return false;
        }
    }

}
