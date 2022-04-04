package com.example.springdata_01.Dto;

import com.example.springdata_01.Domain.Product;
import org.springframework.data.relational.core.mapping.Table;

public class HistoryUserDto {
    private String name;
    private String username;
    private Integer amount;
    private Double price;
    private Double totalPrice;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public HistoryUserDto(String name, Integer amount, Double price, Double totalPrice) {
        this.name = name;
        this.amount = amount;
        this.price = price;
        this.totalPrice = totalPrice;
    }
    public HistoryUserDto(String name, Integer amount, Double price) {
        this.name = name;
        this.amount = amount;
        this.price = price;

    }
    public HistoryUserDto(Product p) {
        this.name = name;
        this.amount = amount;
        this.price = price;

    }

    public HistoryUserDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
