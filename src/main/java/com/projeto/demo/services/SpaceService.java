package com.projeto.demo.services;

import org.springframework.http.ResponseEntity;

import com.projeto.demo.model.User;

public interface SpaceService {
    // ResponseEntity<Object> create(String name); // Não pode ter o mesmo nome /// VERSÂO DO 1 Grupo!!
    ResponseEntity<Object> create(String name,User user); // VERSÂO DO 2 GRUPo!!
    ResponseEntity<Object> delete(String name);
}
