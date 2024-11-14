package com.projeto.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projeto.demo.model.Questions;

/////CRIADO PELO GRUPO 2

public interface QuestionRepository extends JpaRepository<Questions, Long> {
    List<Questions> findByDescription(String description);
}
