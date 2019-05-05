package com.karol.posfx.model;

import com.karol.posfx.enums.Category;
import javafx.beans.Observable;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.util.Callback;

import java.io.Serializable;

public class ProductControl implements Serializable {
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

    public void resetQuantity(){
        quantity.setValue(0);
    }

    @Override
    public String toString() {
        return String.format("%s: ilość: %d", product.getName(), quantity.get());
    }

    public static Callback<ProductControl, Observable[]> extractor(){
        return productControl -> new Observable[]{productControl.quantityProperty()};
    }

    public OrderItem toOrderItem(){
        return new OrderItem(quantity.get(), product);
    }
}