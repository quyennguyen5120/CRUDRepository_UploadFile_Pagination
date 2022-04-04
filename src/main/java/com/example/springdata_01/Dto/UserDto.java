package com.example.springdata_01.Dto;

import com.example.springdata_01.Domain.Product;
import com.example.springdata_01.Domain.User;

import java.util.ArrayList;
import java.util.List;

public class UserDto {
    private Long id;
    private String username;
    private String password;
    private Double totalpricebought;
    private List<Product> lstProduct;

    public UserDto(User u) {
        this.id = u.getId();
        this.username = u.getUsername();
        this.password = u.getPassword();
        this.totalpricebought = u.getTotalpricebought();
        this.lstProduct = new ArrayList<>();
    }

    public UserDto() {
    }

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

    public List<Product> getLstProduct() {
        return lstProduct;
    }

    public void setLstProduct(List<Product> lstProduct) {
        this.lstProduct = lstProduct;
    }
}
