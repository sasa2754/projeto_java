package com.projeto.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projeto.demo.dto.TokenData;
import com.projeto.demo.dto.UserData;
import com.projeto.demo.services.JWTService;
import com.projeto.demo.services.UserService;

@RestController
@RequestMapping("")
public class UserController {
    
    @Autowired
    UserService userService;
    
    @PostMapping("/user") 
    public ResponseEntity<Object> register(@RequestBody UserData data) {
        return userService.register(data.name(), data.email(), data.EDV(), data.password());
    }
    
    @PostMapping("/auth")
    public ResponseEntity<Object> login(@RequestBody UserData data) {
        return userService.login(data.email(), data.EDV(), data.password());
    }
}
