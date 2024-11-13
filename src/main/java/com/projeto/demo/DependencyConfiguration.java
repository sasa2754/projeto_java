package com.projeto.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.projeto.demo.impl.JwtImpl;
import com.projeto.demo.services.JWTService;

@Configuration
public class DependencyConfiguration {
    @Bean
    public JWTService jwtService(){
      return new JwtImpl();
    }
}
