package com.projeto.demo.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.projeto.demo.repositories.UserRepository;
import com.projeto.demo.services.UserService;
import com.projeto.demo.model.User;

public class UserImpl implements UserService {

    @Autowired
    UserRepository repo;

    @Override
    public ResponseEntity<Object> register(String name, String email, String EDV, String pass) {
        var validEmail = repo.findByEmail(email);
        var validEdv = repo.findByEDV(EDV);

        if(!validEmail.isEmpty() || !validEdv.isEmpty()) {
            return null;
        }

        var user = new User();
        user.setName(name);
        user.setEDV(EDV);
        user.setEmail(email);
        user.setPassword(pass);
        repo.saveAndFlush(user);

        return new ResponseEntity<>("Usuário criado com sucesso!", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> login(String email, String EDV, String pass) {
        var user = repo.findByEmail(email);
        
        if(user.isEmpty()) {
            user = repo.findByEDV(EDV);

            if(user.isEmpty()) {
                return null;
            }
        }

        //verificação de senha
        // if() {
        //     return null;
        // }

        return new ResponseEntity<>("Logado com sucesso!", HttpStatus.OK);
    }
}
