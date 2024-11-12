package com.projeto.demo.services;

import org.springframework.http.ResponseEntity;

public interface QuestionService {
    ResponseEntity<Object> create(String nameSpace, String text);
}
