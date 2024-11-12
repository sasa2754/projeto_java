package com.projeto.demo.services;

import org.springframework.http.ResponseEntity;

public interface AnswerService {
    ResponseEntity<Object> create(Long idQuestion, String text);
}
