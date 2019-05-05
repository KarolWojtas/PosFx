package com.karol.posfx.model;

import com.karol.posfx.enums.Category;
import com.karol.posfx.enums.ProductId;

abstract public class Product {
    private ProductId productId;
    private Float price;
    private String name;
    private Category category;

    public Product(){

    }

    public Product(Float price, String name) {
        this.price = price;
        this.name = name;
    }
    public abstract static class Builder<T extends Product, B extends Builder>{
        protected T product;
        protected B self;
        protected abstract T createProduct();
        protected abstract B createSelf();

        protected Builder(){
            this.product = createProduct();
            this.self = createSelf();
        }
        public B productId(ProductId productId){
            product.setProductId(productId);
            product.setName(productId.getName());
            return self;
        }

        public B price(float price){
            product.setPrice(price);
            return self;
        }
        public B category(Category category){
            product.setCategory(category);
            return self;
        }
        public T build(){
            return product;
        }
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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    abstract public String[] getDescription();

    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", price=" + price +
                ", name='" + name + '\'' +
                '}';
    }
}
