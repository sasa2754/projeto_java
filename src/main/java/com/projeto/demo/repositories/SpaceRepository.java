package com.projeto.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projeto.demo.model.Spaces;

@Repository
public interface SpaceRepository extends JpaRepository<Spaces, Long> {
    List<Spaces> findByName(String name);
}
