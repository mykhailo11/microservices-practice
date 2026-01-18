package com.pm.authservice.service.api;

import com.pm.authservice.exception.TokenValidationException;

public interface AuthTokenService {

    String generateToken(String email, String role);

    void validateToken(String token) throws TokenValidationException;
}
