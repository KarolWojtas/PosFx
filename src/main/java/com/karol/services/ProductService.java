package com.karol.services;

import com.karol.model.Product;

import java.util.Collections;
import java.util.List;

public class ProductService {
    private static ProductService ourInstance = new ProductService();
    private List<Product> productList;
    public static ProductService getInstance() {
        return ourInstance;
    }

    private ProductService() {
    }

    public void addProducts(Product ...products){
        Collections.addAll(productList, products);
    }
    public List<Product> getAllProducts(){
        return productList;
    }
}
