package com.projeto.demo.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.projeto.demo.model.Permissions;
import com.projeto.demo.repositories.PermissionRepo;
import com.projeto.demo.repositories.SpaceRepository;
import com.projeto.demo.repositories.UserRepository;
import com.projeto.demo.services.PermissionService;

public class PermissionImpl implements PermissionService {

    @Autowired
    SpaceRepository spaceRepo;

    @Autowired
    PermissionRepo permissionRepo;

    @Autowired
    UserRepository userRepo;

    @Override
    public ResponseEntity<Object> changePermission(String spaceName, String EDV, String permission) {
        
        var space = spaceRepo.findByName(spaceName);
        var user = userRepo.findByEDV(EDV);
        
        //verifica se existe uma permissão
        if(permission == null) {
            return new ResponseEntity<>("Permissão inválida!", HttpStatus.OK);
        }

        //se a pesquisa por namespace não retornar resultado
        if(space.isEmpty()) {
            return new ResponseEntity<>("Espaço inválido!", HttpStatus.OK);
        }

        //se a pesquisa por usuário não retornar resultado
        if(user.isEmpty()) {
            return new ResponseEntity<>("Usuário inválido!", HttpStatus.OK);
        }

        //retorna uma lista com as permissões do usuário
        var userPermissions = permissionRepo.findByUser(user.get(0));

        //procura pelas permissões do usuário no espaço informado
        for (Permissions permissions : userPermissions) {
            if(permissions.getSpace().getId() == space.get(0).getId()) {
                permissions.setPermition(permission);
            }
        }

        // se não tiver nenhuma permissão, irá criar uma nova
        if(userPermissions.isEmpty()) {
            var newPermission = new Permissions();
            newPermission.setSpace(space.get(0));
            newPermission.setUser(user.get(0));
            newPermission.setPermition(permission);
            permissionRepo.saveAndFlush(newPermission);
        }
        
        return new ResponseEntity<>("Permissão registrada com sucesso", HttpStatus.OK);
    }
    
}
