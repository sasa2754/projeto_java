package com.projeto.demo.services;

import com.auth0.jwt.interfaces.DecodedJWT;

public interface JWTService {
    String generateToken(Long id, String name);
    DecodedJWT validateToken(String token);
    
}
