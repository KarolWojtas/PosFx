package com.karol.model;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Order {
    private String id;
    private List<OrderItem> products = new ArrayList<>();
    private String orderer;
    private ZonedDateTime created;
    private double totalPrice;

    public List<OrderItem> getProducts() {
        return products;
    }

    public void setProducts(List<ProductControl> products) {
        products.forEach(productControl -> {
           this.products.add(productControl.toOrderItem());
        });
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id='" + id + '\'' +
                ", products=" + products.stream().map(productControl -> productControl.getProduct().getName() + productControl.getQuantity()).collect(Collectors.joining(", ")) +
                ", orderer='" + orderer + '\'' +
                ", created=" + created +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
