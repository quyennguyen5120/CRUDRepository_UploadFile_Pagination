package com.example.springdata_01.Dto;

import com.example.springdata_01.Domain.Product;

public class CartDto {
    private Long id;
    private String name;
    private String image;
    private Integer amount;
    private Double price;
    private Double totalPrice;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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

    public CartDto() {
    }

    public CartDto(Long id,String name, String image, Integer amount, Double price, Double totalPrice) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.amount = amount;
        this.price = price;
        this.totalPrice = totalPrice;
    }
    public CartDto(Product p) {
        this.id = p.getId();
        this.name = p.getName();
        this.image = p.getImage();
        this.amount = 1;
        this.price = p.getPrice();
        this.totalPrice = p.getPrice();
    }
}
