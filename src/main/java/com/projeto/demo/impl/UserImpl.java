package com.projeto.demo.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.projeto.demo.dto.TokenData;
import com.projeto.demo.model.User;
import com.projeto.demo.repositories.UserRepository;
import com.projeto.demo.services.JWTService;
import com.projeto.demo.services.UserService;

public class UserImpl implements UserService {

    @Autowired
    UserRepository repo;

    @Autowired
    BcryptImpl service;

    @Autowired
    JWTService jwtService;

    @Override
    public ResponseEntity<Object> register(String name, String email, String EDV, String pass) {
        var validEmail = repo.findByEmail(email);
        var validEdv = repo.findByEDV(EDV);

        Boolean letterUp = false;
        Boolean number = false;
        Boolean letter = false;
        
        //caso não ache nenhum user que corresponde, vai retornar null
        if(!validEmail.isEmpty() || !validEdv.isEmpty()) {
            return new ResponseEntity<>("Usuário inválido", HttpStatus.OK);
        }
        
        if(pass.length() < 12) {
            return new ResponseEntity<>("A senha deve possuir mais de 12 caracteres.", HttpStatus.OK);
        }

        //verifica se a senha possui número, letra maiúscula e minúscula
        for (char c : pass.toCharArray()) {
            if(Character.isDigit(c))
                number = true;
            else if(Character.isLowerCase(c))
                letter = true;
            else if(Character.isUpperCase(c))
                letterUp = true;
            else {
                return new ResponseEntity<>("A senha não deve possuir caracteres especiais.", HttpStatus.OK);
            }
        }
        
        if(!number) {
            return new ResponseEntity<>("A senha deve possuir números.", HttpStatus.OK);
        }
        
        if(!letter) {
            return new ResponseEntity<>("A senha deve possuir letras minúsculas.", HttpStatus.OK);
        }
        
        if(!letterUp) {
            return new ResponseEntity<>("A senha deve possuir letras maiúsculas.", HttpStatus.OK);
        }

        var user = new User();
        user.setName(name);
        user.setEDV(EDV);
        user.setEmail(email);
        user.setPassword(service.encode(pass));
        repo.saveAndFlush(user);

        return new ResponseEntity<>("Usuário criado com sucesso!", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> login(String email, String EDV, String pass) {
        var user = repo.findByEmail(email);
        
        if(user.isEmpty()) {
            user = repo.findByEDV(EDV);

            if(user.isEmpty()) {
                return new ResponseEntity<>(new TokenData("Dados inválidos", ""), HttpStatus.OK);
            }
        }

        //verificação de senha
        if(!service.math(pass, user.get(0).getPassword())) {
            return new ResponseEntity<>(new TokenData("Senha incorreta", ""), HttpStatus.OK);
        }

        //chama o service para gerar o token de usuário
        var token = jwtService.generateJWT(user.get(0).getId());

        return new ResponseEntity<>(new TokenData("Logado com sucesso", token), HttpStatus.OK);
    }
}
