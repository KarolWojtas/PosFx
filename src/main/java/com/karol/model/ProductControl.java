package com.karol.model;

import com.karol.enums.Category;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class ProductControl {
    private Product product;
    private IntegerProperty quantity = new SimpleIntegerProperty(0);

    public ProductControl(Product product) {
        this.product = product;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity.get();
    }

    public IntegerProperty quantityProperty() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        quantity = quantity;
    }

    public Category getCategory(){
        return product.getCategory();
    }
    public int increase(){
        quantity.setValue(quantity.get() + 1);
        return quantity.get();
    }

    public int decrease(){
        int oldValue = quantity.get();
        if(oldValue > 0){
            quantity.set(oldValue - 1);
        }
        return quantity.get();
    }

    @Override
    public String toString() {
        return "ProductControl{" +
                "product=" + product +
                ", quantity=" + quantity +
                '}';
    }
}
