package com.projeto.demo.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projeto.demo.dto.UserData;
import com.projeto.demo.dto.UsersReturn;
import com.projeto.demo.model.User;
import com.projeto.demo.repositories.UserRepository;
import com.projeto.demo.services.UserService;

@RestController
@RequestMapping
public class UserController {
    
    @Autowired
    UserService userService;

    @Autowired
    UserRepository repo;
    
    @PostMapping("/user") 
    public ResponseEntity<Object> register(@RequestBody UserData data) {
        return userService.register(data.name(), data.email(), data.EDV(), data.password());
    }
    
    @PostMapping("/auth")
    public ResponseEntity<Object> login(@RequestBody UserData data) {
        return userService.login(data.email(), data.EDV(), data.password());
    }

    @GetMapping("/user")
    public ResponseEntity<List<UsersReturn>> getUsers(String edv,Integer page,Integer size){ //Query vai ser tudo depois do WHERE Exemplo EDV=Juan@email.com || PAge comeca em 1
        
    List<User> users;
    ArrayList<UsersReturn> newlList = new ArrayList<>();


    if(edv!=null){
        users = repo.findByQuery(edv);
    }else{
        users = repo.findAll();
    }

    int start = size * (page-1);
    int sizeReal = start + page-1;

    if(page<1 || size<1){
        start = 0;
        sizeReal = users.size();
    }

    for(int i=start;i<sizeReal;i++){
        var user = users.get(i);
        UsersReturn newUser = new UsersReturn(user.getName(),user.getEmail(),user.getEDV());
        newlList.add(newUser);
    }
    
    return new ResponseEntity<>(newlList,HttpStatus.OK);
    }
}
