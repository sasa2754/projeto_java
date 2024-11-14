package com.projeto.demo.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projeto.demo.dto.CreateSpace;
import com.projeto.demo.dto.DeleteSpace;
import com.projeto.demo.model.Permissions;
import com.projeto.demo.model.Spaces;
import com.projeto.demo.repositories.PermissionRepo;
import com.projeto.demo.repositories.SpaceRepository;
import com.projeto.demo.repositories.UserRepository;
import com.projeto.demo.services.SpaceService;

@RestController
@RequestMapping("/space")
public class SpaceController {

  @Autowired
  SpaceRepository repo;

  @Autowired
  UserRepository userRepo;

  @Autowired
  PermissionRepo perRepo;

  @Autowired
  SpaceService service;

  @GetMapping
  public ResponseEntity<List<Spaces>> getSpaces(String name,Integer page,Integer size){
    List<Spaces> spaces;
    ArrayList<Spaces> newlList = new ArrayList<>();

    if(name!=null){
        spaces = repo.findByName(name);
    }else{
        spaces = repo.findAll();
    }

    //paginação
    int start = size * (page-1);
    int sizeReal = start + size-1;

    if(page<1 || size<1){
        start = 0;
        sizeReal = spaces.size();
    }

    for(int i=start;i<sizeReal;i++){
        var space = spaces.get(i);
        newlList.add(space);
    }
    
    return new ResponseEntity<>(newlList,HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<Object> createSpace(@RequestAttribute("token") String token,@RequestBody CreateSpace data){
    var user = userRepo.findById(Long.parseLong(token));
    if(user.isEmpty())
      return new ResponseEntity<>("Usuario não encotrado!!",HttpStatus.BAD_REQUEST);

    return service.create(data.name(),user.get());
  }

  @DeleteMapping
  public ResponseEntity<Object> deleteSpace(@RequestAttribute("token") String token,@RequestBody DeleteSpace data){
    var spaceOP = repo.findById(data.id());

    if(spaceOP.isEmpty())
      return new ResponseEntity<>("Espaço não encontrado",HttpStatus.BAD_REQUEST);
    
    var space = spaceOP.get();

    var userOP = userRepo.findById(Long.parseLong(token));

    if(userOP.isEmpty())
      return new ResponseEntity<>("Usuario não encontrado",HttpStatus.BAD_REQUEST);
    var user = userOP.get();

    Permissions per = new Permissions();
    per.setSpace(space);
    per.setUser(user);

    var perOp = perRepo.findAll(Example.of(per));

    if(perOp.isEmpty())
      return new ResponseEntity<>("Usuario sem Permissão encontrado",HttpStatus.BAD_REQUEST);
    
    return service.delete(space.getName());
  }


}
