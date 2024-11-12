package com.projeto.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tbPermissions")
public class Permissions {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private long id;

    @ManyToOne // Relaciona muitos pra um com a tabela User
    @JoinColumn(name = "idUser", nullable = false)
    private User user;

    @ManyToOne // Relaciona muitos para um com a tabela Spaces
    @JoinColumn(name = "idSpace", nullable = false)
    private Spaces space;

    private String permission;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPermition() {
        return permission;
    }

    public void setPermition(String permission) {
        this.permission = permission;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Spaces getSpace() {
        return space;
    }

    public void setSpace(Spaces space) {
        this.space = space;
    }

}
