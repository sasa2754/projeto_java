package com.projeto.demo.services;

import com.auth0.jwt.interfaces.DecodedJWT;

public interface JWTService {
    String generateJWT(Long id, String name);
    DecodedJWT validateJWT(String token);
    
}
