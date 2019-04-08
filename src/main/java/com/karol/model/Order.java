package com.karol.model;

import java.time.ZonedDateTime;
import java.util.List;

public class Order {
    private String id;
    private List<ProductControl> products;
    private String orderer;
    private ZonedDateTime created;
    private double totalPrice;

    public List<ProductControl> getProducts() {
        return products;
    }

    public void setProducts(List<ProductControl> products) {
        this.products = products;
    }

    public String getOrderer() {
        return orderer;
    }

    public void setOrderer(String orderer) {
        this.orderer = orderer;
    }

    public ZonedDateTime getCreated() {
        return created;
    }

    public void setCreated(ZonedDateTime created) {
        this.created = created;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
