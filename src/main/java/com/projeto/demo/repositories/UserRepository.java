package com.projeto.demo.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.projeto.demo.model.User;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByName(String name);
    List<User> findByEmail(String email);
    List<User> findByEDV(String EDV);

    @Query("SELECT u FROM User u WHERE (:email IS NULL OR u.email = :email) OR (:EDV IS NULL OR u.EDV = :EDV)")
    Optional<User> findByEmailOrEDV(String email, String EDV);


    ///Grupo 2: 
    @Query("SELECT u FROM User u WHERE EDV = :query")
    List<User> findByQuery(@Param("query") String query);
}
