package com.karol.posfx.model.dto;

import com.karol.posfx.enums.ProductId;

public class OrderItemDto {
    public ProductId productId;
    public int quantity;

    public ProductId getProductId() {
        return productId;
    }

    public void setProductId(ProductId productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public OrderItemDto() {
    }

    public OrderItemDto(ProductId productId, int quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "OrderItemDto{" +
                "productId=" + productId +
                ", quantity=" + quantity +
                '}';
    }
}
