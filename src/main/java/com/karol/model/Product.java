package com.karol.model;

import java.util.Random;

public class Product {
    private ProductId productId;
    private Float price;
    private String name;

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

    public ProductId getProductId() {
        return productId;
    }

    public void setProductId(ProductId productId) {
        this.productId = productId;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", price=" + price +
                ", name='" + name + '\'' +
                '}';
    }
}
