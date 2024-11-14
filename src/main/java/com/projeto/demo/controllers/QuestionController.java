package com.projeto.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projeto.demo.dto.QuestionData;
import com.projeto.demo.repositories.QuestionRepository;
import com.projeto.demo.services.QuestionService;

@RestController
@RequestMapping("")
public class QuestionController {
    
    @Autowired
    QuestionService questionService;

    @Autowired
    QuestionRepository questionRepo;

    @PostMapping("/question")
    public ResponseEntity<Object> createQuestion(@RequestAttribute("token") String token, @RequestBody QuestionData data) {
        
        if(token == null) {
            return new ResponseEntity<Object>("Token inválido!", HttpStatus.OK);
        }
        
        return questionService.create(data.nameSpace(), data.text(), token);
    }

    @DeleteMapping("/question/{id}")
    public ResponseEntity<Object> deleteQuestion(@PathVariable Long id) {
        
        questionRepo.deleteById(id);

        return new ResponseEntity<Object>("Questão deletada com sucesso!", HttpStatus.OK);
    }

    //implementar
    @GetMapping("/question/{id}")
    public ResponseEntity<Object> getQuestion(@PathVariable Long id) {
        return new ResponseEntity<Object>("", HttpStatus.OK);
    }

    //implementar
    @GetMapping("/question/{space}")
    public ResponseEntity<Object> getQuestion(@PathVariable String space, Integer page, Integer size) {
        return new ResponseEntity<Object>("", HttpStatus.OK);
    }
}
