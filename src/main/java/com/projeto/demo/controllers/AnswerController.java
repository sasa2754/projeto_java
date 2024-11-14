package com.projeto.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projeto.demo.dto.AnswerData;
import com.projeto.demo.services.AnswerService;

@RestController
@RequestMapping("/space")
public class AnswerController {

    @Autowired
    AnswerService answerService;
    
    @PostMapping("/answer")
    public ResponseEntity<Object> createAnswer(@RequestBody AnswerData data) {
        return answerService.create(data.idQuestion(), data.text());
    }
}
