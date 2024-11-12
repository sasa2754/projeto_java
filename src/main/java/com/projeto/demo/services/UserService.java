package com.projeto.demo.services;

import org.springframework.http.ResponseEntity;

public interface UserService {
    ResponseEntity<Object> register(String name, String email, String EDV, String pass);
    ResponseEntity<Object> login(String email, String EDV, String pass); // Pode logar tanto com EDV, quanto com email
}
