package com.example.springdata_01.Domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("user")
public class User {
    @Id
    private Long id;
    private String username;
    private String password;
    private Double totalpricebought;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Double getTotalpricebought() {
        return totalpricebought;
    }

    public void setTotalpricebought(Double totalpricebought) {
        this.totalpricebought = totalpricebought;
    }

    public User() {
    }

    public User(Long id, String username, String password, Double totalpricebought) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.totalpricebought = totalpricebought;
    }
}
