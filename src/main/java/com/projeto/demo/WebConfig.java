package com.projeto.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.projeto.demo.interceptor.JwtInterceptor;
import com.projeto.demo.services.JWTService;

// import com.projeto.demo.interceptor.JwtInterceptor;


@Configuration
@EnableWebSecurity// Faça o middleware aqui 😄
public class WebConfig {
  @Autowired
  JWTService jwt;

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
      return http
          .csrf(csrf -> csrf.disable())
          .authorizeHttpRequests(authorize -> authorize
          .requestMatchers("/user").permitAll()
          .requestMatchers("/auth").permitAll()
          .requestMatchers("/**").authenticated()
          )
          .addFilterBefore(new JwtInterceptor(jwt), UsernamePasswordAuthenticationFilter.class)
          .build();
  }
}


