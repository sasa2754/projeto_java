package com.projeto.demo.services;

import com.auth0.jwt.interfaces.DecodedJWT;

public interface JWTService {
    String generateJWT(Long id);
    DecodedJWT validateJWT(String token);
}
