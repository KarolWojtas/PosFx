package com.karol.model;

import java.util.Random;

public class Product {
    private Long id = new Random().nextLong();
    private Float price;
    private String name;
    private ProductCategory category;

    public Product(){

    }

    public Product(Float price, String name) {
        this.price = price;
        this.name = name;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ProductCategory getCategory() {
        return category;
    }

    public void setCategory(ProductCategory category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", price=" + price +
                ", name='" + name + '\'' +
                ", category=" + category +
                '}';
    }
}
