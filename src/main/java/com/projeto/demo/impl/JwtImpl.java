package com.projeto.demo.impl;

import java.util.Date;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.projeto.demo.services.JWTService;

public class JwtImpl implements  JWTService{

    @Override
    public String generateJWT(Long id, String name) {

      return JWT.create()
      .withSubject(id.toString()) // Define o identificador, nesse caso o username
      .withClaim("id", id)
      .withIssuedAt(new Date()) // Define a data que o token foi criado
      .withExpiresAt(new Date(System.currentTimeMillis() + 864000)) // Define a data de expiração do tokenpara 24 horas após a criação dele
      .sign(Algorithm.HMAC256("ODEIO ESSE TIPO DE JWT!!")); // Assina o token e retorna o JWT completo
    }

    @Override
    public DecodedJWT validateJWT(String token) throws JWTVerificationException{
      Algorithm algorithm = Algorithm.HMAC256("ODEIO ESSE TIPO DE JWT!!"); // Configura o verificador com o mesmo algoritmo e chave usados antes
			JWTVerifier verifier = JWT.require(algorithm) 
					.build();
      return verifier.verify(token);
    }

    
}
