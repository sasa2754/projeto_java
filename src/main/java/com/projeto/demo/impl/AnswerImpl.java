package com.projeto.demo.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.projeto.demo.model.Answer;
import com.projeto.demo.repositories.AnswerRepository;
import com.projeto.demo.repositories.QuestionRepository;
import com.projeto.demo.services.AnswerService;

public class AnswerImpl implements AnswerService{

    @Autowired
    QuestionRepository questionRepo;

    @Autowired
    AnswerRepository answerRepo;

    @Override
    public ResponseEntity<Object> create(Long idQuestion, String text) {
        var question = questionRepo.findById(idQuestion);

        //verifica se existe a pergunta
        if(question.isEmpty()) {
            return new ResponseEntity<>("A pergunta não existe", HttpStatus.OK);
        }

        var answer = new Answer();
        answer.setQuestion(question.get());
        answer.setAnswer(text);
        answerRepo.saveAndFlush(answer);

        return new ResponseEntity<>("Resposta criada com sucesso!", HttpStatus.OK);
    }
    
}
