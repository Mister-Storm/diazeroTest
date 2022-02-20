package com.diazero.incidentsapi.infra.security.user;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity(name = "user")
public class UserInfra {
    @Id
    @Column(name = "id", nullable = false)
    @Access(AccessType.FIELD)
    private Long id;
    @Access(AccessType.FIELD)
    private String userName;
    @Access(AccessType.FIELD)
    @JsonIgnore
    private String password;

    public UserInfra() {}
    public UserInfra(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }
    void setPassword(String password) {
        this.password=password;
    }
}
