package com.projeto.demo.services;

import org.springframework.http.ResponseEntity;

import com.projeto.demo.model.User;

public interface QuestionService {
    ResponseEntity<Object> create(String nameSpace, String text, String idUser);
}
