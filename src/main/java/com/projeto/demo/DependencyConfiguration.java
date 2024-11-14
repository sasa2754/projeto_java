package com.projeto.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.projeto.demo.impl.AnswerImpl;
import com.projeto.demo.impl.BcryptImpl;
import com.projeto.demo.impl.JwtImpl;
import com.projeto.demo.impl.PermissionImpl;
import com.projeto.demo.impl.QuestionImpl;
import com.projeto.demo.impl.SpaceImpl;
import com.projeto.demo.impl.UserImpl;
import com.projeto.demo.services.AnswerService;
import com.projeto.demo.services.BcryptService;
import com.projeto.demo.services.JWTService;
import com.projeto.demo.services.PermissionService;
import com.projeto.demo.services.QuestionService;
import com.projeto.demo.services.SpaceService;
import com.projeto.demo.services.UserService;

@Configuration
public class DependencyConfiguration {
    @Bean
    public JWTService jwtService(){
      return new JwtImpl();
    }

    @Bean
    public UserService userService() {
      return new UserImpl();
    }

    @Bean
    public QuestionService questionService() {
      return new QuestionImpl();
    }

    @Bean
    public AnswerService answerService() {
      return new AnswerImpl();
    }

    @Bean
    public BcryptService bcryptService() {
      return new BcryptImpl();
    }

    @Bean
    public SpaceService spaceService(){
      return new SpaceImpl();
    }
    
    @Bean
    public PermissionService permissionService() {
      return new PermissionImpl();
    }
}
