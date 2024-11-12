package com.projeto.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tbQuestions")
public class Questions {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private long id;

    @ManyToOne
    @JoinColumn(name = "idSpace", nullable = false)
    private Spaces space;

    
    @ManyToOne
    @JoinColumn(name = "idUser", nullable = false)
    private User user;

    private String description;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Spaces getSpace() {
        return space;
    }

    public void setSpace(Spaces space) {
        this.space = space;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
