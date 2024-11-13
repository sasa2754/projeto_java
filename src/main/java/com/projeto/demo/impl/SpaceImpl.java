package com.projeto.demo.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.projeto.demo.model.Permissions;
import com.projeto.demo.model.Spaces;
import com.projeto.demo.model.User;
import com.projeto.demo.repositories.PermissionRepo;
import com.projeto.demo.repositories.SpaceRepository;
import com.projeto.demo.services.SpaceService;

public class SpaceImpl implements SpaceService{
  
  @Autowired
  SpaceRepository repo;

  @Autowired 
  PermissionRepo perRepo;

  @Override
  public ResponseEntity<Object> create(String name,User User) {
    Spaces newSpace = new Spaces();
    newSpace.setName(name);
    newSpace = repo.save(newSpace);

    Permissions newPer = new Permissions();
    newPer.setSpace(newSpace);
    newPer.setUser(User);

    perRepo.save(newPer);

    return new ResponseEntity<>("Espaço ativado com sucesso!!!",HttpStatus.OK);
  }

  @Override
  public ResponseEntity<Object> delete(String name) {

    Spaces a = new Spaces();
    a.setName(name);
    
    var list = repo.findAll(Example.of(a));

    if(list.isEmpty())
      return new ResponseEntity<>("Space não encontrado!!!",HttpStatus.BAD_REQUEST);

    repo.delete(list.get(0));

    return new ResponseEntity<>("Space Deletado!!!",HttpStatus.OK);
  }
  
}
