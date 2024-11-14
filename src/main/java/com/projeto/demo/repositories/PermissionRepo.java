package com.projeto.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projeto.demo.model.Permissions;
import com.projeto.demo.model.User;

/////CRIADO PELO GRUPO 2

public interface PermissionRepo extends JpaRepository<Permissions, Long>{
    List<Permissions> findByUser(User user);
}
