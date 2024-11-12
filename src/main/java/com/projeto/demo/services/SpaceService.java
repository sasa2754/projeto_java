package com.projeto.demo.services;

import org.springframework.http.ResponseEntity;

public interface SpaceService {
    ResponseEntity<Object> create(String name); // Não pode ter o mesmo nome
    ResponseEntity<Object> delete(String name);
}
