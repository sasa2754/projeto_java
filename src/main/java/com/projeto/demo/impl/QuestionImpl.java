package com.projeto.demo.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.projeto.demo.model.Questions;
import com.projeto.demo.model.User;
import com.projeto.demo.repositories.QuestionRepository;
import com.projeto.demo.repositories.SpaceRepository;
import com.projeto.demo.repositories.UserRepository;
import com.projeto.demo.services.QuestionService;

public class QuestionImpl implements QuestionService {

    @Autowired
    SpaceRepository spaceRepo;

    @Autowired
    QuestionRepository questionRepo;

    @Autowired
    UserRepository userRepo;

    @Override
    public ResponseEntity<Object> create(String nameSpace, String text, String idUser) {

        var space = spaceRepo.findByName(nameSpace);
        var userId = userRepo.findById(user.getId());
        
        //verifica se existe uma pergunta
        if(text == null) {
            return new ResponseEntity<>("Pergunda inválida!", HttpStatus.OK);
        }

        //se a pesquisa por namespace não retornar resultado
        if(space.isEmpty()) {
            return new ResponseEntity<>("Espaço inválido!", HttpStatus.OK);
        }

        //se a pesquisa por usuário não retornar resultado
        if(userId.isEmpty()) {
            return new ResponseEntity<>("Usuário inválido!", HttpStatus.OK);
        }

        var question = new Questions();
        question.setDescription(text);
        question.setSpace(space.get(0));
        question.setUser(user);
        questionRepo.saveAndFlush(question);

        return new ResponseEntity<>("Pergunda criada com sucesso!", HttpStatus.OK);
    }
    
}
