package com.pm.authservice.service.api;

import com.pm.authservice.dto.LoginRequestDTO;

import java.util.Optional;

public interface AuthService {

    Optional<String> authenticate(LoginRequestDTO loginRequestDTO);
    boolean validateToken(String token);
}
