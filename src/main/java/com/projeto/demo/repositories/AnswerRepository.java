package com.projeto.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projeto.demo.model.Answer;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long>{
    // @Query("SELECT a FROM Answer a WHERE a.idQuestion = :idQuestion")
    // List<Answer> getAnswerByQuestion(Long idQuestion);
}
