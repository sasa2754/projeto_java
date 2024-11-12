package com.projeto.demo.services;

import org.springframework.http.ResponseEntity;

public interface PermissionService {
    ResponseEntity<Object> changePermission(String spaceName, String EDV, String permission); // Pega o nome do espaço, o EDV da pessoa que vai ter a permissão mudada e a permissão nova, mas tem que colocar um enum no front, pra ter certeza que o usuário n vai fazer merda :)
}
